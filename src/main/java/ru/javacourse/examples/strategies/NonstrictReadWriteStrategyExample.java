package ru.javacourse.examples.strategies;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.strategies.StatItem;

public final class NonstrictReadWriteStrategyExample {

    private NonstrictReadWriteStrategyExample() {
    }

    public static void run() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        StatItem item = new StatItem("Просмотры", 10);
        session.persist(item);
        session.getTransaction().commit();
        Long id = item.getId();
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        session.find(StatItem.class, id);
        session.close();
        System.out.println("NONSTRICT после первого поиска: в L2=" + CacheHelper.inL2(StatItem.class, id));

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.find(StatItem.class, id).setHits(99);
        session.getTransaction().commit();
        session.close();
        System.out.println("NONSTRICT после изменения, есть ли данные в кеше: в L2=" + CacheHelper.inL2(StatItem.class, id));


        session = HibernateUtil.getSessionFactory().openSession();
        session.find(StatItem.class, id);
        session.close();
        System.out.println("NONSTRICT после третьего поиска: в L2=" + CacheHelper.inL2(StatItem.class, id));

        session.close();
    }
}
