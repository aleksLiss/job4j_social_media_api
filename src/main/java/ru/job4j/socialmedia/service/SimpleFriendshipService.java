package ru.job4j.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.model.Friendship;
import ru.job4j.socialmedia.model.Subscription;
import ru.job4j.socialmedia.repository.FriendshipRepository;
import ru.job4j.socialmedia.repository.SubscriptionRepository;

@Service
public class SimpleFriendshipService implements FriendshipService {

    private final SubscriptionRepository subscriptionRepository;
    private final FriendshipRepository friendshipRepository;

    public SimpleFriendshipService(SubscriptionRepository subscriptionRepository, FriendshipRepository friendshipRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.friendshipRepository = friendshipRepository;
    }

    @Transactional
    @Override
    public void deleteFromFriends(long fromUserId, long toUserId) {
        Friendship deleteFriendshipFrom = new Friendship(fromUserId, toUserId);
        Friendship deleteFriendshipTo = new Friendship(toUserId, fromUserId);
        Subscription deleteSubscriptionFrom = new Subscription(fromUserId, toUserId);
        friendshipRepository.delete(deleteFriendshipFrom);
        friendshipRepository.delete(deleteFriendshipTo);
        subscriptionRepository.delete(deleteSubscriptionFrom);
    }

    @Transactional
    @Override
    public void getFriend(long fromUserId, long toUserId, boolean isFriend) {
        Friendship friendshipFrom;
        Friendship friendshipTo;
        Subscription subscriptionFrom;
        Subscription subscriptionTo;
        subscriptionFrom = new Subscription(fromUserId, toUserId);
        if (isFriend) {
            friendshipFrom = new Friendship(fromUserId, toUserId);
            friendshipTo = new Friendship(toUserId, fromUserId);
            subscriptionTo = new Subscription(toUserId, fromUserId);
            subscriptionRepository.save(subscriptionTo);
            friendshipRepository.save(friendshipFrom);
            friendshipRepository.save(friendshipTo);
        }
        subscriptionRepository.save(subscriptionFrom);
    }
}
