package code.vanilson.startup.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying   // to mark delete or update query
    @Query("DELETE from Product p where p.id=:id")
    boolean delete(@Param("id") Integer id);

    @Query("SELECT p from Product p where p.id=:id ")
    Optional<Product> findProductById(@Param("id") Integer id);
}
