package ru.javacourse.examples;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class L2CacheExample {

    private L2CacheExample() {
    }

    public static void run() {

        DemoData.seedProductsIfEmpty();
        Long id = DemoData.seedProduct("L2-Demo", "222");

        Statistics stats = HibernateUtil.getSessionFactory().getStatistics();

        Session s1 = HibernateUtil.getSessionFactory().openSession();
        Product p1 = s1.find(Product.class, 1L);
        System.out.println(p1);
        s1.close();

        Session s2 = HibernateUtil.getSessionFactory().openSession();
        Product p2 = s2.find(Product.class, 1L);
        System.out.println(p2);
        s2.close();

        System.out.println("Запрос кеша L2: " + stats.getSecondLevelCacheHitCount());
        System.out.println("L2 промахи: " + stats.getSecondLevelCacheMissCount());
        System.out.println("L2 записи: " + stats.getSecondLevelCachePutCount());

    }
}
