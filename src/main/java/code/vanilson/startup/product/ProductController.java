package code.vanilson.startup.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductRepository productRepository;


    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    /**
     * returns all products in tne databases.
     *
     * @return All Products in the database.
     */

    @GetMapping(value = "/products")
    public ResponseEntity<Iterable<Product>> getProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    /**
     * Returns the product with the specified ID.
     *
     * @param id The id of the product to retrieve
     * @return The Product with the specified ID.
     */
    @GetMapping(value = " /product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Integer id) {
        return productRepository
                .findById(id)
                .map(product -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(product.getVersion()))
                                .location(new URI("/product/" + product.getId()))
                                .body(product);

                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new product
     *
     * @param product the product to create.
     * @return the created product.
     */
    @PostMapping(value = "/product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        logger.info("Creating new product with name:{},quatity:{}", product.getName(), product.getQuantity());
        // create new product
        Product newProduct = productService.createNewProduct(product);
        try {
            //Build created response
            return ResponseEntity
                    .created(new URI("/product" + newProduct.getId()))
                    .eTag(Integer.toString(newProduct.getVersion()))
                    .body(newProduct);

        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates the fields in the specified product with specified Id.
     *
     * @param product the product field values to update.
     * @param id      the ID of product to update.
     * @param ifMatch the eTage Version of the product.
     * @return A responseEntity the contains thr update product or one of the following error statuses:
     * NOT_FOUND if there is no product in database with the specified ID.
     * CONFLIT if the eTage version does not match the version of the product to update
     * INTERNAL_SERVICE_ERROR  if there is a problem createing the location URI.
     */

    @PutMapping(value = "/product/update/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestBody Product product,
            @PathVariable(name = "id") Integer id,
            @RequestHeader("If-Match") Integer ifMatch
    ) {
        logger.info("Creating new product with name:{},quatity:{} ,price:{}", product.getName(), product.getQuantity(), product.getPrice());

        // Get the existing product
        Optional<Product> existingProduct = productRepository.findById(id);

        return existingProduct.map(p -> {
            //compare eTags
            logger.info("Product with ID :" + id + " has a version of " + p.getVersion() + ". Update is for IfMatch: " + ifMatch);

            if (!p.getVersion().equals(ifMatch)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            //Update the product

            p.setName(product.getName());
            p.setQuantity(product.getQuantity());
            p.setVersion(product.getVersion() + 1);
            p.setPrice(product.getPrice());

            logger.info("Updating product with ID: " + p.getId()
                    + " -> name = " + p.getName()
                    + ", quantity = " + p.getQuantity()
                    + ", version = " + p.getVersion()
                    + " ,price n = " + p.getPrice());

            try {
                //Update the product and return an ok response
                if (productService.update(p)) {
                    return ResponseEntity
                            .ok()
                            .eTag(Integer.toString(p.getVersion()))
                            .location(new URI("/product/" + p.getId()))
                            .body(p);
                } else return ResponseEntity.notFound().build();
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }).orElse(ResponseEntity.notFound().build());
    }
}
