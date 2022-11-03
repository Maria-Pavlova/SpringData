

import Entities.Customer;
import Entities.Product;
import Entities.Sale;
import Entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main02 {
    public static void main(String[] args) {


        EntityManagerFactory f = Persistence.createEntityManagerFactory("sales_db");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product("product1", 15.0, BigDecimal.TEN);
        Customer customer = new Customer("name1", "mail", "card123");
        StoreLocation location = new StoreLocation("local");
        Sale sale = new Sale(product, customer, location);

        em.persist(product);
        em.persist(customer);
        em.persist(location);
        em.persist(sale);

        em.getTransaction().commit();
        em.close();
    }
}
