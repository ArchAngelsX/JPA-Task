package tugas1.service;

import tugas1.kelas.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional; // Ensure this import is correct
import java.util.List;

@Stateless // This makes the class a stateless session bean
public class ProductService {

    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager em; // Injected EntityManager

    public List<Product> getAllProducts() {
        try {
            return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        } catch (PersistenceException e) {
            // Handle the exception (e.g., log it)
            e.printStackTrace();
            return null; // or throw a custom exception
        }
    }

    @Transactional // This indicates that the method should be executed within a transaction
    public void deleteProduct(int id) {
        Product product = em.find(Product.class, id); // Find product by id
        if (product != null) {
            em.remove(product); // Remove product if found
        }
    }

    @Transactional // This indicates that the method should be executed within a transaction
    public void addProduct(Product product) {
        em.persist(product); // Persist the product
    }

    @Transactional // This indicates that the method should be executed within a transaction
    public void updateProduct(Product product) {
        em.merge(product); // Update the product
    }

    @Transactional // This indicates that the method should be executed within a transaction
    public Product getProductById(int id) {
        return em.find(Product.class, id); // Find the product by ID
    }
}