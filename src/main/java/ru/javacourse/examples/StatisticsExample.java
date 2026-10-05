package ru.javacourse.examples;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class StatisticsExample {

    private StatisticsExample() {
    }

    public static void run() {
        Long id = DemoData.seedProduct("Stats-Demo", "555");
        Statistics statistics = HibernateUtil.getSessionFactory().getStatistics();
        statistics.clear();

        for (int i = 0; i < 5; i++) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.find(Product.class, id);
            session.close();
        }

        System.out.println("L2 попадания: " + statistics.getSecondLevelCacheHitCount());
        System.out.println("L2 промахи: " + statistics.getSecondLevelCacheMissCount());
        System.out.println("L2 записи: " + statistics.getSecondLevelCachePutCount());
        System.out.println("Загрузок сущностей: " + statistics.getEntityLoadCount());
        System.out.println("Выполнений запросов: " + statistics.getQueryExecutionCount());
    }
}
