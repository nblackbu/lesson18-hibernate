package ru.javacourse.examples;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.User;

import java.util.List;

public final class JoinFetchExample {

    private JoinFetchExample() {
    }

    public static void run() {

        DemoData.seedSeveralUsers();

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<User> users = session.createQuery(
                "select distinct u from User u left join fetch u.comments",
                User.class
        ).list();

        for (User user : users) {
            System.out.println(user.getName() + ": " + user.getComments().size() + " comments");
        }

        session.close();
    }
}
