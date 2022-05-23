package com.example.iss_ltbtp.Data.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bug_table")
public class Bug {

    public enum Status {
        UNSOLVED,
        PROGRESS,
        SOLVED,
    }

    @Id
    private int id;
    private String description;
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    public Bug() {}

    public Bug(final int id, final String description, final Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status + "\t\t\t" + description;
    }
}
