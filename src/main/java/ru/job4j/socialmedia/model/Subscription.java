package ru.job4j.socialmedia.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "followers")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long followingUserId;
    private Long followerUserId;

    public Subscription() {
    }

    public Subscription(Long followingUserId, Long followerUserId) {
        this.followingUserId = followingUserId;
        this.followerUserId = followerUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(Long followingUserId) {
        this.followingUserId = followingUserId;
    }

    public Long getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(Long followerUserId) {
        this.followerUserId = followerUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
