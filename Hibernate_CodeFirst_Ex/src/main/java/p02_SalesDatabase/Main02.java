package p02_SalesDatabase;

import p02_SalesDatabase.Entities.Customer;
import p02_SalesDatabase.Entities.Product;
import p02_SalesDatabase.Entities.Sale;
import p02_SalesDatabase.Entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main02 {
    public static void main(String[] args) {


        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product("product1", 15.0, BigDecimal.TEN);
        Customer customer = new Customer("name1", "mail", "asd");
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
