package ru.javacourse.examples;

import org.hibernate.Session;
import ru.javacourse.HibernateUtil;
import ru.javacourse.model.NoteItem;
import ru.javacourse.model.UserWithLazyExtra;

public final class LazyCollectionExtraExample {

    private LazyCollectionExtraExample() {
    }

    public static void run() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        UserWithLazyExtra user = new UserWithLazyExtra("EXTRA-demo");
        NoteItem n0 = new NoteItem("1", user);
        NoteItem n1 = new NoteItem("2", user);
        NoteItem n2 = new NoteItem("3", user);
        user.getNotes().add(n0);
        user.getNotes().add(n1);
        user.getNotes().add(n2);
        session.persist(user);

        session.getTransaction().commit();
        Long id = user.getId();
        session.clear();

        UserWithLazyExtra loaded = session.find(UserWithLazyExtra.class, id);
        System.out.println("user = " + loaded.getName());
        System.out.println("notes[1] = " + loaded.getNotes().get(1).getText());
        System.out.println("notes.size() = " + loaded.getNotes().size());

        session.close();
    }
}
