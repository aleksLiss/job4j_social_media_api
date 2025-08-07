package ru.job4j.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Post;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByUserId(long id);

    List<Post> findByCreateDateGreaterThanEqualAndLessThanEqual(LocalDateTime startDate, LocalDateTime endDate);

    Page<Post> findByOrderByCreateDateDesc(Pageable pageable);
}
