package ru.javacourse.examples.cachemode;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class PutExample {

    private PutExample() {
    }

    public static void run() {
        Long id = CacheModeHelper.createProduct("PUT", "300");
        CacheModeHelper.evict(id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.byId(Product.class).with(CacheMode.PUT).load(id);
        session.close();

        System.out.println("PUT: в L2=" + CacheModeHelper.inL2(id));
    }
}
