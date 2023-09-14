package code.vanilson.startup.service;

import code.vanilson.startup.exception.ObjectNotFoundById;
import code.vanilson.startup.model.Product;
import code.vanilson.startup.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    /**
     * code.vanilson.startup.product.ProductSteps Repository Mock
     */
    ProductRepository productRepositoryMock;
    /**
     * current instance
     */
    ProductServiceImpl currentInstance;
    /**
     * code.vanilson.startup.product.ProductSteps instance
     */
    Product product;
    /**
     * Lists of Products
     */
    List<Product> products;

    /**
     * setup
     */
    @BeforeEach
    void setUp() {
        productRepositoryMock = mock(ProductRepository.class);
        currentInstance = new ProductServiceImpl(productRepositoryMock);

        product = new Product(1, "Keyboard", 34, 1);
        products = List.of(
                new Product(1, "Computer", 34, 2004),
                new Product(2, "Mouse", 10, 1)
        );
    }


    @Test
    @DisplayName("List all products - Success")
    void testShouldReturnAllProducts() {
        when(productRepositoryMock.findAll()).thenReturn(products);
        assertEquals(currentInstance.findAll(), productRepositoryMock.findAll(), "Return all Products");
        assertEquals(2, currentInstance.findAll().size(), "Should return 2");
        verify(productRepositoryMock, times(3)).findAll();

    }

    @Test
    @DisplayName("Return product by id - Found")
    void testShouldReturnTheProductsWhenTheGivenIdIsFound() {
        when(productRepositoryMock.findById(1)).thenReturn(Optional.of(product));
        assertSame(currentInstance.findById(1).get(), product, "Products should be the same");
        assertTrue(currentInstance.findById(1).isPresent(), "code.vanilson.startup.product.ProductSteps was  found");
        assertFalse(currentInstance.findById(1).isEmpty());
        assertNotEquals(234, currentInstance.findById(1)
                .get()
                .getProductId());
        verify(productRepositoryMock, times(4)).findById(1);
    }

    @Test
    @DisplayName(" product by id - Not Found")
    void testShouldThrowExceptionsWhenTheGivenIdIsNotFound() {
        when(productRepositoryMock.findById(1)).thenReturn(Optional.empty());
        //assert
        assertThrows(ObjectNotFoundById.class, () -> currentInstance.findById(1));
    }

    @Test
    @DisplayName("create a new product - Success")
    void testShouldCreateNewProduct() {
        Product mockProduct = new Product(1, "keyboard", 10, 1);
        when(productRepositoryMock.save(any())).thenReturn(mockProduct);
        var actualCurrent = currentInstance.save(mockProduct);
        //asserts
        assertEquals(mockProduct, actualCurrent);
        assertEquals(1, actualCurrent.getProductId());
        assertEquals(1, actualCurrent.getVersion());

        verify(productRepositoryMock,atLeastOnce()).save(mockProduct);
    }

    @Test
    @DisplayName("update product - Success")
    void testShouldUpdateProduct() {
        Product mockProduct = new Product(1, "keyboard", 10, 1);
        when(productRepositoryMock.update(mockProduct)).thenReturn(true);
        var actualCurrent = currentInstance.update(mockProduct);

        //asserts
        assertTrue(actualCurrent);
        verify(productRepositoryMock,atLeastOnce()).update(mockProduct);

    }


    @Test
    @DisplayName("Delete product by id - Success")
    void testShouldDeleteProductsWhenTheGivenIdIsFound() {
        when(productRepositoryMock.findById(1)).thenReturn(Optional.of(product));
        when(productRepositoryMock.delete(1)).thenReturn(true);
        var current = currentInstance.delete(1);
        assertTrue(current);
        verify(productRepositoryMock, times(1)).delete(1);
    }
}