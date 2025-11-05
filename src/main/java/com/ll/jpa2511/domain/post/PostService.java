package com.ll.jpa2511.domain.post;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public List<Post> findByUserName(String userName) {
        return postRepository.findByUserName(userName);
    }

    public Post create(String subject, String content, String userName){
        Post post = Post.builder()
                .subject(subject)
                .content(content)
                .userName(userName)
                .build();

        return postRepository.save(post);
    }

    @SneakyThrows //자동 예외 처리(try, catch를 사용 안해도 자동으로 됨)
    public Optional<Post> findWithShareLockById(Long id) {
        postRepository.findWithShareLockById(id);
        Thread.sleep(10000); //예외 발생

        return postRepository.findWithShareLockById(id);
    }

    public Optional<Post> findWithWriteLockById(Long id) {
        return postRepository.findWithWriteLockById(id);
    }

    @SneakyThrows
    @Transactional
    public Post modifyOptimistic(Long id) {
        Post post =postRepository.findById(id).orElseThrow();

        Thread.sleep(10000);

        post.setUserName(post.getUserName()+ "!");
        return post;
    }
}