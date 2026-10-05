package ru.javacourse.examples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Product;

public final class CacheEvictExample {

    private CacheEvictExample() {
    }

    public static void run() {
        Long id = DemoData.seedProduct("Evict-Demo", "444");
        SessionFactory sf = HibernateUtil.getSessionFactory();

        Session s1 = sf.openSession();
        s1.find(Product.class, id);
        s1.close();

        System.out.println("Удалили Product из L2 по id");
        sf.getCache().evictEntityData(Product.class, id);

        Session s2 = sf.openSession();
        System.out.println("После очистки снова идём в БД:");
        System.out.println(s2.find(Product.class, id));
        s2.close();

        System.out.println("Очистили весь регион Product в L2");
        sf.getCache().evictEntityData(Product.class);
    }
}
