package ru.javacourse.examples;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.User;

import java.util.List;

public final class NPlusOneExample {

    private NPlusOneExample() {
    }

    public static void run() {

        DemoData.seedSeveralUsers();

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("from User", User.class).list();
        for (User user : users) {
            System.out.println(user.getName() + "  " + user.getComments().size() + " comments");
        }

        session.close();
    }
}
