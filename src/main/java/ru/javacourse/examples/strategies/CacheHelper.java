package ru.javacourse.examples.strategies;

import org.hibernate.stat.Statistics;
import ru.javacourse.HibernateUtil;

public final class CacheHelper {

    private CacheHelper() {
    }

    public static boolean inL2(Class<?> entityClass, Object id) {
        return HibernateUtil.getSessionFactory().getCache().containsEntity(entityClass, id);
    }

    public static Statistics stats() {
        return HibernateUtil.getSessionFactory().getStatistics();
    }
}
