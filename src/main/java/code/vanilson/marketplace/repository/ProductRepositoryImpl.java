package code.vanilson.marketplace.repository;

import code.vanilson.marketplace.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private static final Logger logger = LogManager.getLogger(ProductRepositoryImpl.class);
    public static final String PRODUCT_ID = "product_id";
    public static final String VERSION = "version";
    public static final String QUANTITY = "quantity";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;

        // Build a SimpleJdbcInsert object from the specified data source
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tb_products")
                .usingGeneratedKeyColumns(PRODUCT_ID);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM tb_products",
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNumber) throws SQLException {
                        Product product = new Product();
                        product.setProductId(rs.getInt(PRODUCT_ID));
                        product.setName(rs.getString("name"));
                        product.setQuantity(rs.getInt(QUANTITY));
                        product.setVersion(rs.getInt(VERSION));
                        return product;
                    }
                });
    }

    @Override
    public Optional<Product> findById(Integer id) {
        try {
            Product product = jdbcTemplate.queryForObject("SELECT * FROM tb_products WHERE product_id = ?",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Product p = new Product();
                        p.setProductId(rs.getInt(PRODUCT_ID));
                        p.setName(rs.getString("name"));
                        p.setQuantity(rs.getInt(QUANTITY));
                        p.setVersion(rs.getInt(VERSION));
                        return p;
                    });
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Product product) {
        return jdbcTemplate.update("UPDATE tb_products SET name = ?, quantity = ?, version = ? WHERE product_id = ?",
                product.getName(),
                product.getQuantity(),
                product.getVersion(),
                product.getProductId()) == 1;
    }

    @Override
    public Product save(Product product) {
        // Build the product parameters we want to save
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("name", product.getName());
        parameters.put(QUANTITY, product.getQuantity());
        parameters.put(VERSION, product.getVersion());

        // Execute the query and get the generated key
        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);

        logger.info("Inserting product into database, generated key is: {}", newId);

        // Update the product's ID with the new key
        product.setProductId((Integer) newId);

        // Return the complete product
        return product;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM tb_products WHERE product_id = ?", id) == 1;
    }
}
