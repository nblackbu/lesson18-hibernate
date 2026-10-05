package ru.javacourse.examples.cachemode;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class GetExample {

    private GetExample() {
    }

    public static void run() {
        Long id = CacheModeHelper.createProduct("GET", "200");
        CacheModeHelper.evict(id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.byId(Product.class).with(CacheMode.GET).load(id);
        session.close();

        System.out.println("GET: в L2=" + CacheModeHelper.inL2(id));
    }
}
