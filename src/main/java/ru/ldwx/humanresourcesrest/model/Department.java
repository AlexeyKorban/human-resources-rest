package ru.ldwx.humanresourcesrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Department {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "seq", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    private Integer id;
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
