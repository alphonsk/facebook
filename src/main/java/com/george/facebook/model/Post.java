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
    // also in controller
    @NotNull
    @Size(min=2, max=30, message = "Please enter a longer message")
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

    //
}
