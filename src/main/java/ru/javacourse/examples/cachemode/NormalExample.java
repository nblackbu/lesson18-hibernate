package ru.javacourse.examples.cachemode;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class NormalExample {

    private NormalExample() {
    }

    public static void run() {
        Long id = CacheModeHelper.createProduct("NORMAL", "100");
        CacheModeHelper.evict(id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.byId(Product.class).with(CacheMode.NORMAL).load(id);
        session.close();

        System.out.println("NORMAL: в L2=" + CacheModeHelper.inL2(id));
    }
}
