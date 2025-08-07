package ru.job4j.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Post;

import java.util.Collection;
import java.util.Date;

public interface PostRepository extends CrudRepository<Post, Long> {

    Collection<Post> findByUserId(long id);

    Collection<Post> findByCreatedGreaterThanEqualAndCreatedLessThanEqual(Date startCreated, Date finishCreated);

    Page<Post> findByOrderByCreatedDesc(Pageable pageable);
}
