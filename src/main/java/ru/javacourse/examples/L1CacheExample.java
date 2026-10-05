package ru.javacourse.examples;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;
import ru.javacourse.model.User;

public final class L1CacheExample {

    private L1CacheExample() {
    }

    public static void run() {

        DemoData.seedProductsIfEmpty();

        Session session = HibernateUtil.getSessionFactory().openSession();
        User p1 = session.find(User.class, 1);
        System.out.println(p1);

        session.close();

        Session session1 = HibernateUtil.getSessionFactory().openSession();
        User p2 = session1.find(User.class, 1);
        System.out.println(p2);

        System.out.println(p1 == p2);
        session.close();
    }
}
