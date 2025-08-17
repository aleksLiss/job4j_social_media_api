package ru.job4j.socialmedia.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmedia.dto.PostSaveDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody PostSaveDto postSaveDto) {
        Post savedPost = postService.savePost(postSaveDto);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(uri)
                .body(savedPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostSaveDto> getById(@PathVariable
                                               @NotNull
                                               @Min(value = 1, message = "идентификатор должен быть 1 и более")
                                               Long postId) {
        return postService.findById(postId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteById(@PathVariable
                                           @NotNull
                                           @Min(value = 1,  message = "идентификатор должен быть 1 и более")
                                           Long postId) {
        if (postService.deletePost(postId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Post post) {
        if (postService.updatePost(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void change(@RequestBody Post post) {
        postService.updatePost(post);
    }
}
