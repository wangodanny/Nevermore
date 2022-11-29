package com.makersacademy.nevermore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.util.Date;

@Data
@Entity
@Table(name = "USERS")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private String content;
    private int price;
    private String category;
    public Date date;

    // public Cost() {
        
    // }

    public Cost(Long user_id, String content, int price, String category, Date date) {
        this.user_id = user_id;
        this.content = content;
        this.price = price;
        this.category = category;
        this.date = date;
    }

    // public Cost() {
        
    // }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }

    public String getPicture()  { return this.picture;}


    public Long getId() { return this.id; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setPicture(String pic) { this.picture = pic;}
}
