package com.george.facebook.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;




@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    // also in validationmessages file
    @NotNull(message = "{post.text.notnull}")

    // also in controller
    @Size(min=2, max=300, message = "{post.text.size}")
    private String text;

    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;

    @PrePersist
    protected void onCreate() {
        if (added == null) {
            added = new Date();
        }
    }

//    //...
//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
//    private Cart cart;

    //...
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Post() {
    }

    public Post(String text) {
        this.text = text;
    }

    public Post(String text, Date added) {
        this.text = text;
        this.added = added;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //
}
