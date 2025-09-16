package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepositoryCustom {
    Optional<Todo> findByIdWithUser(Long todoId);

    Page<Todo> findByTextAndnicknameAndCreatedAtBetween(@Param("title") String title,
                                                        @Param("start") LocalDateTime start,
                                                        @Param("end") LocalDateTime end,
                                                        @Param("nickname") String nickname,
                                                        Pageable pageable);

    Page<Todo> findByTitle(@Param("title") String title, Pageable pageable);

    Page<Todo> findByCreatedAtBetween(@Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end,
                                          Pageable pageable);

    Page<Todo> findByNickname(@Param("nickname") String nickname, Pageable pageable);
}
