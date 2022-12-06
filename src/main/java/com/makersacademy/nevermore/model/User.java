package com.makersacademy.nevermore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String image;
    private boolean enabled;
    private Integer salary;

    // This is a uni-directional relationship
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Cost> costs;


    public User() {
        this.enabled = TRUE;
    }


    public User(String username, String password, String image, Integer salary) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.enabled = TRUE;
        this.salary = salary;
    }

    public User(String username, String password, boolean enabled, String image) {
        this.username = username;
        this.password = password;
        this.image = image;
        this.enabled = enabled;
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getImage() { return this.image; }
    public Integer getSalary() {return this.salary;}



    public Long getId() { return this.id; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public List<Cost> getCosts() {return this.costs;}
    public void setSalary(Integer salary) { this.salary = salary; }

    public void setImage(String image) { this.image = image; }
    public void addCost(Cost cost) {this.costs.add(cost);}

    // When passed in the results of getCosts() calles the getPrice() method on each object returned. (map maybe)

    public List<String> getPricesInList() {
        List<String> prices = new ArrayList<String>();
        for (Cost price:getCosts()) {
            price.getPrice();
            prices.add((price.getPrice()).toString());
        }
    return prices;
    }

    public List<String> getContentInList() {
        List<String> contents = new ArrayList<String>();
        for (Cost content:getCosts()) {
            content.getcontent();
            contents.add((content.getcontent()).toString());
        }
    return contents;
    }

    public List<Integer> getDateInList() {
        List<Integer> dates = new ArrayList<Integer>();
        for (Cost date:getCosts()) {
            dates.add((date.getMonth()));
            
        }


    return dates;
    }

}
