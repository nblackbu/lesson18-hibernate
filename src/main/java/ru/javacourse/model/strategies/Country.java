package ru.javacourse.model.strategies;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "strategy_countries")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
