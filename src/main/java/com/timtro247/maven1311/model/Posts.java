package com.timtro247.maven1311.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts extends BaseModel {

    private String title;
    private String content;
    private LocalDate startTime;
    private Integer savePostDayNumber;

    @ManyToOne
    private Users user;

    @OneToMany(mappedBy = "post")
    private Set<Comments> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rooms room;

    @OneToOne(mappedBy = "post",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Transactions transaction;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public Integer getSavePostDayNumber() {
        return savePostDayNumber;
    }

    public void setSavePostDayNumber(Integer savePostDayNumber) {
        this.savePostDayNumber = savePostDayNumber;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }
}
