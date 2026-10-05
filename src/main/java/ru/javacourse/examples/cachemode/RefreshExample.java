package ru.javacourse.examples.cachemode;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class RefreshExample {

    private RefreshExample() {
    }

    public static void run() {
        Long id = CacheModeHelper.createProduct("REFRESH", "500");
        CacheModeHelper.evict(id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.byId(Product.class).with(CacheMode.REFRESH).load(id);
        session.close();

        System.out.println("REFRESH: в L2=" + CacheModeHelper.inL2(id));
    }
}
