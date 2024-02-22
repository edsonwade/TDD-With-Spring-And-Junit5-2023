package code.vanilson.startup.service;

import code.vanilson.startup.dto.ProductDto;
import code.vanilson.startup.exception.ObjectWithIdNotFound;
import code.vanilson.startup.mapper.ProductMapper;
import code.vanilson.startup.model.Product;
import code.vanilson.startup.repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static code.vanilson.startup.mapper.ProductMapper.toProduct;
import static code.vanilson.startup.mapper.ProductMapper.toProductDtoList;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return toProductDtoList(products);

    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ObjectWithIdNotFound(" product with " + id + " not found")));
        logger.info("Find product with id: {}", id);
        return toProduct(product);
    }

    @Override
    public ProductDto save(@NotNull ProductDto productDto) {
        // Convert ProductDto to Product
        Product product = ProductMapper.toProduct(productDto);
        product.setVersion(1);
        Product savedProduct = productRepository.save(product);
        logger.info("Saved product to the database: {}", savedProduct);
        return ProductMapper.toProductDto(savedProduct);
    }

    @Override
    public boolean update(ProductDto productDto) {
        Product product = ProductMapper.toProduct(productDto);
        logger.info("Update product: {}", productDto);
        return productRepository.update(product);
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        var productDto = toProduct(product);
        if (productDto.isEmpty()) {
            throw new ObjectWithIdNotFound(" product with " + id + " not found");
        }
        logger.info("Delete product with id: {}", id);
        return productRepository.delete(id);
    }

}