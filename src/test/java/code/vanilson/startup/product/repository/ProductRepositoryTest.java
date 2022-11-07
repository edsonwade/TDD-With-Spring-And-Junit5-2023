package code.vanilson.startup.product.repository;

import code.vanilson.startup.product.ProductRepositoryImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ProductRepositoryImpl productRepository;

    public ConnectionHolder getConnectionHolder() throws SQLException {
        // Return a function that retrievs a connection from our data source
        return (ConnectionHolder) dataSource.getConnection();
    }


}
