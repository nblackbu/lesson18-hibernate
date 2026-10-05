package ru.javacourse.examples;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.Comment;
import ru.javacourse.model.Product;
import ru.javacourse.model.User;

import java.math.BigDecimal;
import java.util.List;

public final class DemoData {

    private DemoData() {
    }
    public static Long seedUserWithComments(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User(name, name.toLowerCase() + "@mail.test");
        user.addComment("Комментарий 1");
        user.addComment("Комментарий 2");
        user.addComment("Комментарий 3");
        session.persist(user);

        session.getTransaction().commit();
        Long id = user.getId();
        session.close();
        return id;
    }
    public static void seedSeveralUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        for (int i = 1; i <= 3; i++) {
            User user = new User("User" + i, "user" + i + "@mail.test");
            user.addComment("c-" + i + "-a");
            user.addComment("c-" + i + "-b");
            session.persist(user);
        }

        session.getTransaction().commit();
        session.close();
    }

    public static Long seedProduct(String name, String price) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Product product = new Product(name, new BigDecimal(price));
        session.persist(product);

        session.getTransaction().commit();
        Long id = product.getId();
        session.close();
        return id;
    }

    public static void seedProductsIfEmpty() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = session.createQuery("select count(p) from Product p", Long.class)
                .uniqueResult();
        session.close();
        if (count != null && count > 0) {
            return;
        }
        seedProduct("iPhone", "100000");
        seedProduct("Samsung", "80000");
        seedProduct("Xiaomi", "45000");
    }

    public static List<Comment> loadComments(Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Comment> list = session.createQuery(
                "from Comment c where c.user.id = :uid",
                Comment.class
        ).setParameter("uid", userId).list();
        session.close();
        return list;
    }
}
