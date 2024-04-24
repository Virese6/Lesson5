package app.model;

import jakarta.persistence.EntityManager;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CarRepositoryHibernate implements CarRepository{

    private EntityManager entityManager;

    public CarRepositoryHibernate() {
        this.entityManager = new Configuration()
                .configure("postgres.cfg.xml")
                .buildSessionFactory()
                .createEntityManager();
    }

    @Override
    public Car save(Car car) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(car);
            entityManager.getTransaction().commit();
        return car;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Car getById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public List<Car> getAll() {
        return entityManager.createQuery("from Car", Car.class).getResultList();
    }

    @Override
    public void update(Car car) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(car);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Car car = entityManager.find(Car.class, id);
            entityManager.remove(car);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
