/**
 * Author: vanilson muhongo
 * Date:23/06/2024
 * Time:00:59
 */

package code.vanilson.marketplace.integration;

import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @SuppressWarnings("unused")
    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet(value = "datasets/customers.yml")
    @DisplayName("GET /api/customers -Success")
    void testGetCustomers() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @DisplayName("GET /api/customers/99 - Not Found")
    @DataSet("datasets/customers.yml")
    void testGetProductByIdNotFound() throws Exception {
        // Execute the GET request
        mockMvc.perform(get("/api/customers/{id}", 99))

                // Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/customers.yml")
    @DisplayName("GET /api/customers/1 - Found")
    void testGetCustomerById() throws Exception {
        long customerId = 1L;
        mockMvc.perform(get("/api/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.test"))
                .andExpect(jsonPath("$.address").value("test 1"));
    }

    @Test
    @DataSet(value = "datasets/customers.yml")
    @DisplayName("POST /api/customers/create -Success")
    void testCreateCustomer() throws Exception {
        mockMvc.perform(post("/api/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Customer\",\"email\":\"new@example.com\",\"address\":\"test 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Customer"))
                .andExpect(jsonPath("$.email").value("new@example.com"))
                .andExpect(jsonPath("$.address").value("test 1"));
    }


    @Test
    @DataSet(value = "datasets/customers.yml")
    @DisplayName("PUT /api/customers/update/{id} -Success")
    void testUpdateCustomer() throws Exception {
        mockMvc.perform(put("/api/customers/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"name\":\"Updated Name\",\"email\":\"updated@example.com\",\"address\":\"Updated Address\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"))
                .andExpect(jsonPath("$.address").value("Updated Address"));
    }


    @Test
    @DisplayName("PUT /api/customers/update/99 - Bad request")
    @DataSet("datasets/customers.yml")
    void testUpdateFailedWithBadRequest() throws Exception {
        // Execute the PUT request
        mockMvc.perform(put("/api/customers/update/{id}", 99))

                // Validate that we get a 4OO bad request response
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Delete /api/customers/delete/1 - Success")
    @DataSet("datasets/customers.yml")
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/customers/delete/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete /api/customers/delete/99 - Not Found")
    @DataSet("datasets/customers.yml")
    void testDeleteCustomerCustomerNotFound() throws Exception {

        mockMvc.perform(delete("/api/customers/delete/{id}", 99))
                .andExpect(status().isNotFound());
    }

}
