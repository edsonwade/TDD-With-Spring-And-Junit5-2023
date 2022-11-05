package code.vanilson.startup.product.controller;

import code.vanilson.startup.product.Product;
import code.vanilson.startup.product.ProductRepository;
import code.vanilson.startup.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /product/1 - Found")
    void testGetProductByIdFound() throws Exception {
        //Setup our mocked service
        Product mockProduct = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(Optional.of(mockProduct)).when(productRepository).findById(1);

        //Execute the Get Request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/{id}", 1))

                // validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                // validate the headers
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/Product/1"))

                //Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product Name")))
                .andExpect(jsonPath("$.quantity", is(10)))
                .andExpect(jsonPath("$.version", is(1)))
                .andExpect(jsonPath("$.price", is((int) 200.00)));


    }

    @Test
    void getProduct() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }
}