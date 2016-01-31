package com.hello.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer userID;

    public String username;

    public String gender;

    public User() {

    }

    @Override
    public String toString() {
        return username + " " + gender;
    }
}
