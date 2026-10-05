package ru.javacourse.examples.strategies;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.strategies.Country;

public final class ReadOnlyStrategyExample {

    private ReadOnlyStrategyExample() {
    }

    public static void run() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Country country = new Country("RU", "Россия");
        session.persist(country);
        session.getTransaction().commit();
        Long id = country.getId();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Country loaded = session.find(Country.class, id);
        loaded.setName("Россия 2");
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        session.close();
    }
}
