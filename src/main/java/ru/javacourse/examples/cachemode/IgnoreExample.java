package ru.javacourse.examples.cachemode;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class IgnoreExample {

    private IgnoreExample() {
    }

    public static void run() {
        Long id = CacheModeHelper.createProduct("IGNORE", "400");
        CacheModeHelper.evict(id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.byId(Product.class).with(CacheMode.IGNORE).load(id);
        session.close();

        System.out.println("IGNORE: в L2=" + CacheModeHelper.inL2(id));
    }
}
