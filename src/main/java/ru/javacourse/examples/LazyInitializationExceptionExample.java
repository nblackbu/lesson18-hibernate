package ru.javacourse.examples;

import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.User;

public final class LazyInitializationExceptionExample {

    private LazyInitializationExceptionExample() {
    }

    public static void run() {

        Long userId = DemoData.seedUserWithComments("Петя");

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.find(User.class, userId);
        session.close();

        try {
            System.out.println(user.getComments().size());
        } catch (LazyInitializationException e) {
            System.out.println("Session уже закрыта");
            System.out.println("Сообщение: " + e.getMessage());
        }
    }
}
