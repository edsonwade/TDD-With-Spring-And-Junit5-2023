package code.vanilson.startup.product;

import java.util.Optional;

public interface ProductService {


    /**
     * Returns all product's int the database
     *
     * @return All products int the database
     */
    Iterable<Product> findAllProducts();

    /**
     * Returns the product with the specified id
     *
     * @param id ID of the preoduct to retrieve
     * @return the requested Product if found
     */
    Optional<Product> getProductById(Integer id);

    /**
     * create a new product and save,  specified product to the database
     *
     * @param product The product to  save to the database.
     * @return the saved pred product
     */
    Product createNewProduct(Product product);

    /**
     * Updates the specified product , identified by its id.
     *
     * @param product The Product to update.
     * @return True if the update succeeded , otherwise false.
     */
    Product updateProduct(Product product);

    /**
     * Deletes the product with the specified id.
     *
     * @param id The id of the product to delete
     * @return true if the operation was successful.
     */
    boolean deleteProduct(Integer id);
}


