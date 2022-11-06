package code.vanilson.startup.product.service;

import code.vanilson.startup.product.Product;
import code.vanilson.startup.product.ProductRepository;
import code.vanilson.startup.product.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


class ProductServiceImplTest {


    private final ProductRepository productRepository = mock(ProductRepository.class);
    // the service we want tot test
    private final ProductServiceImpl serviceImplTest = new ProductServiceImpl(productRepository);

    @Test
    @DisplayName("Test Find All Products")
    void shouldRetunAllProducts() {
        // setup our mock
        Iterable<Product> products = List.of(new Product(1, "Product Name", 10, 1, 200.00),
                new Product(2, "Product Name 2", 20, 2, 300.00));
        doReturn((products)).when(productRepository).findAll();

        //Execute the service call
        var retornedProducts = serviceImplTest.findAllProducts();
        //Assert the response
        assertEquals(retornedProducts, products, "should return all products");


    }

    @Test
    @DisplayName("Test Find Product By Id - Success")
    void testFindProductByIdSuccess() {
        //Setup our mock
        var product = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(Optional.of(product)).when(productRepository).findProductById(1);

        // Execute the service call
        Optional<Product> returnedProduct = serviceImplTest.getProductById(1);

        //Assert the response
        assertTrue(returnedProduct.isPresent(), "Product was not found");
        assertSame(returnedProduct.get(), product, "Product should be the same");
    }

    @Test
    @DisplayName("Test Find Product By Id Not Found - ")
    void testFindProductByIdNotFound() {
        //Setup our mock
        var product = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(Optional.empty()).when(productRepository).findProductById(1);

        // Execute the service call
        Optional<Product> returnedProduct = serviceImplTest.getProductById(1);

        //Assert the response
        assertFalse(returnedProduct.isPresent(), "Product was  found, when it shoudn't be");

    }

    @Test
    @DisplayName("Test Create New Product and save in database")
    void shouldCreateANewProduct() {
        var product = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(product).when(productRepository).save(any());

        // Execute the service call
        Product createProduct = serviceImplTest.createNewProduct(product);

        //Assert the response
        assertNotNull(createProduct, "The Created product should not be null");
        assertEquals(1, createProduct.getVersion().intValue(), "The version for a new product should be 1");

    }


}