package code.vanilson.startup.product.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration   // configuration class
@Profile("test")   // spring Profile " test" telling spring to use this profile only 4 test.
public class ProductRepositoryTestConfiguration {
    @Primary
    @Bean //create a new DataSource bean for testing. spring criará essa classe automatica pra nos.
    public DataSource dataSource() {
        //Setup a data source for our test
        DriverManagerDataSource dataSource = new DriverManagerDataSource(); //configure the data source
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        return dataSource;

    }
}
