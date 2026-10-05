package ru.javacourse;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javacourse.model.Comment;
import ru.javacourse.model.NoteItem;
import ru.javacourse.model.Product;
import ru.javacourse.model.User;
import ru.javacourse.model.UserWithLazyExtra;
import ru.javacourse.model.strategies.CatalogItem;
import ru.javacourse.model.strategies.Country;
import ru.javacourse.model.strategies.StatItem;

public final class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(UserWithLazyExtra.class)
                .addAnnotatedClass(NoteItem.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CatalogItem.class)
                .addAnnotatedClass(StatItem.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}
