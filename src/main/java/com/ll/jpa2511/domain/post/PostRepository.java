package com.ll.jpa2511.domain.post;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserName(String userName);

    @Lock(LockModeType.PESSIMISTIC_READ) //비관적인 읽기 락(누군가 수정하기 전 미리 처리하는 락)
    Optional<Post> findWithShareLockById(Long id);
}