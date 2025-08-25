package ru.job4j.socialmedia.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmedia.dto.UserPostsDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.service.PostService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@Validated
public class PostsController {

    private final PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAll(@PathVariable
                             @NotNull
                             @Min(value = 1, message = "msg")
                             Long userId) {
        return postService.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserPostsDto> getAllPostsByUsersId(@RequestBody List<Long> usersId) {
        return postService.findAllPostsByUsersId(usersId);
    }
}