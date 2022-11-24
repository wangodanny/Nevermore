package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FRIENDSHIPS")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sender;
    private Long receiver;
    private String sender_username;
    private String status = "pending";

    public Friendship() {}

    public void senderSetter(Long id) { this.sender = id; }
    public void receiverSetter(Long id) { this.receiver = id; }
    public void statusSetter(String status) { this.status = status; }
    public void senderUsernameSetter( String username) { this.sender_username = username; }
    public Long receiverGetter() { return this.receiver; }
    public Long senderGetter() { return this.sender; }
    public String statusGetter() { return this.status; }
    public String senderUsernameGetter() { return this.sender_username; }
    public Long idGetter() { return this.id; }

}
