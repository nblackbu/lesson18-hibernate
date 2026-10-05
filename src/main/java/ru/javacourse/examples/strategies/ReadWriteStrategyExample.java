package ru.javacourse.examples.strategies;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.strategies.CatalogItem;

import java.math.BigDecimal;

public final class ReadWriteStrategyExample {

    private ReadWriteStrategyExample() {
    }

    public static void run() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CatalogItem item = new CatalogItem("Телефон", new BigDecimal("100000"));
        session.persist(item);
        session.getTransaction().commit();
        Long id = item.getId();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.find(CatalogItem.class, id);
        session.close();
        System.out.println("READ_WRITE после первого поиска: в L2=" + CacheHelper.inL2(CatalogItem.class, id));

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.find(CatalogItem.class, id).setPrice(new BigDecimal("50000"));
        session.getTransaction().commit();
        session.close();
        System.out.println("READ_WRITE после изменения: в L2=" + CacheHelper.inL2(CatalogItem.class, id));
    }
}
