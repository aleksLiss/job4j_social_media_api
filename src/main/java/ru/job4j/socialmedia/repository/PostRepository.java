package ru.job4j.socialmedia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.model.Post;

import java.util.Collection;
import java.util.Date;

public interface PostRepository extends JpaRepository<Post, Long> {

    Collection<Post> findByUserId(long id);

    Collection<Post> findByCreatedGreaterThanEqualAndCreatedLessThanEqual(Date startCreated, Date finishCreated);

    Page<Post> findByOrderByCreatedDesc(Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """
            UPDATE Posts
            SET title = :postTitle, description = :postDescription
            WHERE id = :postId
            """, nativeQuery = true)
    int updateTitleAndDescription(@Param("postTitle") String title,
                                  @Param("postDescription") String description,
                                  @Param("postId") Long postId);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            UPDATE Posts
            SET path_to_file = NULL
            WHERE id = :postId
            """, nativeQuery = true)
    int deleteImageFromPost(@Param("postId") long postId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """
            DELETE FROM Posts WHERE id = :postId
            """, nativeQuery = true)
    int deletePost(@Param("postId") long postId);
}
