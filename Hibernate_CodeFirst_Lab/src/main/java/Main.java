import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();

        Plate_number plate_number = new Plate_number("AB2020");
        em.persist(plate_number);

        Vehicle car = new Car("model", new BigDecimal(1500), "diesel", 5, plate_number);
        em.persist(car);

        Vehicle bike = new Bike("BMX", new BigDecimal(250), "no");
        em.persist(bike);

        Companies company = new Companies("SomeName");
        em.persist(company);

        Vehicle plane = new Plane("TU", new BigDecimal(900000), "cerosin", 200, "lines", company);
        em.persist(plane);

        Vehicle truck = new Truck("GAS", new BigDecimal(9500), "diesel", 4000.00);
        Drivers driver = new Drivers("Driver1");
        em.persist(truck);
        em.persist(driver);

        em.getTransaction().commit();
        em.close();
    }
}
