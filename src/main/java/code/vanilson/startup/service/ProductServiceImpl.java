package code.vanilson.startup.service;

import code.vanilson.startup.exception.ObjectWithIdNotFound;
import code.vanilson.startup.model.Product;
import code.vanilson.startup.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        logger.info("Find all products");
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        logger.info("Find product with id: {}", id);
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ObjectWithIdNotFound(" product with " + id + " not found")));
    }

    @Override
    public boolean update(Product product) {
        logger.info("Update product: {}", product);
        return productRepository.update(product);
    }

    @Override
    public Product save(@NotNull Product product) {
        // Set the product version to 1 as we're adding a new product to the database
        product.setVersion(1);
        logger.info("Save product to the database: {}", product);
        return productRepository.save(product);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ObjectWithIdNotFound(" product with " + id + " not found");
        }
        logger.info("Delete product with id: {}", id);
        return productRepository.delete(id);
    }
}