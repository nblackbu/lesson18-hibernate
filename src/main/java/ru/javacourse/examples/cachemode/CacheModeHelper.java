package ru.javacourse.examples.cachemode;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

import java.math.BigDecimal;

public final class CacheModeHelper {

    private CacheModeHelper() {
    }

    public static Long createProduct(String name, String price) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = new Product(name, new BigDecimal(price));
        session.persist(product);
        session.getTransaction().commit();
        Long id = product.getId();
        session.close();
        return id;
    }

    public static void warmL2(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.byId(Product.class).with(CacheMode.NORMAL).load(id);
        session.close();
    }

    public static void evict(Long id) {
        HibernateUtil.getSessionFactory().getCache().evictEntityData(Product.class, id);
    }

    public static boolean inL2(Long id) {
        return HibernateUtil.getSessionFactory().getCache().containsEntity(Product.class, id);
    }
}
