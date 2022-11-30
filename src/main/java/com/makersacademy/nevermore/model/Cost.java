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
@Table(name = "COSTS")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private String content;
    private int price;
    private String category;
    public Date date;

    public Cost() {
        
    }

    public Cost(String content, int price, String category) {
        this.content = content;
        this.price = price;
        this.category = category;
    }

    
    //Getters
    public Long getUserid() { return this.user_id; }
    public String getcontent() { return this.content; }
    public Integer getPrice()  { return this.price;}
    public String getCategory() { return this.category;}
    public Long getId() { return this.id; }
    
    //Setters
    public void setUserid(Long user_id) { this.user_id = user_id;}
    public void setContent(String content) { this.content = content; }
    public void setPrice(Integer price) { this.price = price;}
    public void setCategory(String category) { this.category = category;}
    public void setDate(Date date) { this.date = date;}
}
