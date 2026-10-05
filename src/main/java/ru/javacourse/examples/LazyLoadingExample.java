package ru.javacourse.examples;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.User;

public final class LazyLoadingExample {

    private LazyLoadingExample() {
    }

    public static void run() {
        Long userId = DemoData.seedUserWithComments("Вася");

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.find(User.class, userId);
        System.out.println("name = " + user.getName());
        System.out.println("email = " + user.getEmail());

//        System.out.println("comments size = " + user.getComments().size());

        session.close();
    }
}
