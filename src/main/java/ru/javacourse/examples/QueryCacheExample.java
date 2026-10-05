package ru.javacourse.examples;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

import java.util.List;

public final class QueryCacheExample {

    private QueryCacheExample() {
    }

    public static void run() {

        DemoData.seedProductsIfEmpty();

        Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
        stats.clear();

        String hql = "from Product p where p.price > 90000";

        Session s1 = HibernateUtil.getSessionFactory().openSession();
        List<Product> list1 = s1.createQuery(hql, Product.class)
                .setCacheable(true)
                .getResultList();
        System.out.println("найдено: " + list1.size());
        s1.close();

        Session s2 = HibernateUtil.getSessionFactory().openSession();
        List<Product> list2 = s2.createQuery(hql, Product.class)
                .setCacheable(true)
                .getResultList();
        System.out.println("найдено: " + list2.size());
        System.out.println(list1 == list2);
        s2.close();

        System.out.println("Кэш запросов попадания: " + stats.getQueryCacheHitCount());
        System.out.println("Кэш запросов промахи: " + stats.getQueryCacheMissCount());
        System.out.println("Кэш запросов записи: " + stats.getQueryCachePutCount());
    }
}
