package code.vanilson.startup.product;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @NotNull
    @NotEmpty
    public Product createNewProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean update(Product p) {
        return productRepository.existsById(p.getId());
    }

    public boolean delete(Integer id) {
        return id > 0;
    }
}
