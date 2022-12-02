package com.makersacademy.nevermore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.util.Date;

// Note that there's no reference to user here

@Data
@Entity
@Table(name = "COSTS")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Double price;
    private String category;
    public Date date;

    public Cost() {
        
    }

    public Cost(String content, Double price, String category) {
        this.content = content;
        this.price = price;
        this.category = category;
        
    }

    public Cost(String content, Double price, String category, Date date) {
        this.content = content;
        this.price = price;
        this.category = category;
        this.date = date;
    }

    
    //Getters
    public String getcontent() { return this.content; }
    public Double getPrice()  { return this.price;}
    public String getCategory() { return this.category;}
    public Long getId() { return this.id; }
    public Date getDate() {return this.date;}

    //Setters
    public void setContent(String content) { this.content = content; }
    public void setPrice(Double price) { this.price = price;}
    public void setCategory(String category) { this.category = category;}
    public void setDate(Date date) { this.date = date;}
}
