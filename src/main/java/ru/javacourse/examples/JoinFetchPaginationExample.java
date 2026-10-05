package ru.javacourse.examples;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.User;

import java.util.List;

public final class JoinFetchPaginationExample {

    private JoinFetchPaginationExample() {
    }

    public static void run() {

        DemoData.seedSeveralUsers();

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<User> query = session.createQuery(
                "select distinct u from User u left join fetch u.comments order by u.id",
                User.class
        );
        query.setFirstResult(0);
        query.setMaxResults(1);

        List<User> page = query.list();
        System.out.println("Вернули записей: " + page.size());
        if (!page.isEmpty()) {
            System.out.println("Первый: " + page.get(0).getName()
                    + ", comments=" + page.get(0).getComments().size());
        }

        session.close();
    }
}
