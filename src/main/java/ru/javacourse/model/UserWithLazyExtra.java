package ru.javacourse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users_lazy_extra")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "notes")
public class UserWithLazyExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owner")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OrderColumn(name = "note_order")
    private List<NoteItem> notes = new ArrayList<>();

    public UserWithLazyExtra(String name) {
        this.name = name;
    }
}
