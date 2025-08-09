package ru.job4j.socialmedia.service;

public interface FriendshipService {

    void deleteFromFriends(long fromUserId, long toUserId);

    void getFriend(long fromUserId, long toUserId, boolean isFriend);
}
