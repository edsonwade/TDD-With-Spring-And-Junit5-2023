package code.vanilson.startup.product.controller;

import code.vanilson.startup.product.Product;
import code.vanilson.startup.product.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @MockBean
    private ProductServiceImpl productService;
    @Autowired
    private MockMvc mockMvc;


    //    @Test
//    @DisplayName("GET /product/1 - Found")
//    void testGetProductByIdFound() throws Exception {
//        //Setup our mocked service
//        Product mockProduct = new Product(1, "Product Name", 10, 1, 200.00);
//        doReturn(Optional.of(mockProduct)).when(productService).getProductById(1);
//
//        //Execute the Get Request
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/{id}", 1))
//
//                // validate the response code and content type
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//
//                // validate the headers
//                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
//                .andExpect(header().string(HttpHeaders.LOCATION, "/Product/1"))
//
//                //Validate the returned fields
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Product Name")))
//                .andExpect(jsonPath("$.quantity", is(10)))
//                .andExpect(jsonPath("$.version", is(1)))
//                .andExpect(jsonPath("$.price", is(200.00)));
//    }
//
    @Test
    @DisplayName("GET /product/1 - Not Found")
    void testGetProductByIdNotFound() throws Exception {
        //Setup our mocked service
        doReturn(Optional.empty()).when(productService).getProductById(1);

        //Execute the Get Request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/{id}", 1))

                // validate the response code and content type
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /products")
    void testGetProduct() throws Exception {
        //Setup our mocked service
        Iterable<Product> products = List.of(new Product(1, "Product Name", 10, 1, 200.00),
                new Product(2, "Product Name 2", 20, 2, 300.00));

        doReturn(products).when(productService).findAllProducts();

        //Execute the Get Request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))

                // validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate the returned fields
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product Name"))
                .andExpect(jsonPath("$[0].quantity").value(10))
                .andExpect(jsonPath("$[0].version").value(1))
                .andExpect(jsonPath("$[0].price").value(200.00))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product Name 2"))
                .andExpect(jsonPath("$[1].quantity").value(20))
                .andExpect(jsonPath("$[1].version").value(2))
                .andExpect(jsonPath("$[1].price").value(300.00));


    }

    @Test
    @DisplayName("Post /product - Success")
    void testCreateProduct() throws Exception {
        // setup mock service
        Product postProduct = new Product("Product Name", 10, 200.00);
        Product mockProduct = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(mockProduct).when(productService).createNewProduct(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(postProduct)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                // validate the  headers
                .andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/product/1"))
                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product Name")))
                .andExpect(jsonPath("$.quantity", is(10)))
                .andExpect(jsonPath("$.version", is(1)))
                .andExpect(jsonPath("$.price", is(200.00)));

    }

  /*  @Test
    @DisplayName("Put /product/1 - Success")
    void testUpdateProductWithSuccess() throws Exception {
        // setup mock service
        Product putProduct = new Product("Product Name", 10, 200.00);
        Product mockProduct = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(Optional.of(mockProduct)).when(productService).getProductById(1);
        doReturn(true).when(productService).updateProduct(any());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.IF_MATCH, 1)
                        .content(asJsonString(putProduct)))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                // validate the  headers
                .andExpect(header().string(HttpHeaders.ETAG, "\"2\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/product/1"))
                // Validate the returned fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product Name")))
                .andExpect(jsonPath("$.quantity", is(10)))
                .andExpect(jsonPath("$.version", is(2)))
                .andExpect(jsonPath("$.price", is(200.00)));

    }

    @Test
    @DisplayName("Put /product/1 - Version mismacth")
    void testUpdateProductVersionMisMacth() throws Exception {
        // setup mock service
        Product putProduct = new Product("Product Name", 10, 200.00);
        Product mockProduct = new Product(1, "Product Name", 10, 1, 200.00);
        doReturn(Optional.of(mockProduct)).when(productService).getProductById(1);
        doReturn(true).when(productService).updateProduct(any());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.IF_MATCH, 1)
                        .content(asJsonString(putProduct)))

                // Validate the response code and content type
                .andExpect(status().isConflict());

    }*/


    @Test
    @DisplayName("Put /product/1 - Not Found ")
    void testUpdateProductNotFound() throws Exception {
        // setup mock service
        Product putProduct = new Product("Product Name", 10, 200.00);
        doReturn(Optional.empty()).when(productService).getProductById(1);


        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/product/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.IF_MATCH, 1)
                        .content(asJsonString(putProduct)))

                // Validate the response code and content type
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete /product/1 - Success ")
    void testDeleteProductSuccess() throws Exception {
        // setup mock  product
        Product mockProduct = new Product(1, "Product Name", 10, 1, 200.00);
        // setup mock service
        doReturn(Optional.of(mockProduct)).when(productService).getProductById(1);
        doReturn(true).when(productService).deleteProduct(1);

        //Execute our DELETE Request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/delete/{id}", 1))

                // Validate the response code and content type
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Delete /product/1 - Not Found ")
    void testDeleteProductNotFound() throws Exception {
        // setup mock service
        doReturn(Optional.empty()).when(productService).getProductById(1);

        //Execute our DELETE Request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/delete/{id}", 1))

                // Validate the response code and content type
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @DisplayName("Delete /product/1 - Failure ")
    void testDeleteProductFailure() throws Exception {
        // setup mock product
        Product mockProduct = new Product("Product Name", 10, 200.00);
        // setup mock service
        doReturn(Optional.of(mockProduct)).when(productService).getProductById(1);
        doReturn(false).when(productService).deleteProduct(1);


        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/delete/{id}", 1))


                // Validate the response code and content type
                .andExpect(status().isInternalServerError());
    }


    static String asJsonString(final Object ob) {
        try {
            return new ObjectMapper().writeValueAsString(ob);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}