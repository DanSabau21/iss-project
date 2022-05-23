package com.example.iss_ltbtp.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class User {

    public enum Type {
        DEVELOPER,
        TESTER,
        NONE
    }

    @Id
    int id;
    private String name;
    private String password;
    @Enumerated(value = EnumType.ORDINAL)
    private Type type;

    public User() {}

    public User(final int id, final String name, final String password, final Type type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
