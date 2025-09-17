package org.example.expert.domain.log.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requesterId;
    private Long todoId;
    private Long targetUserId;

    @Enumerated(EnumType.STRING)
    private LogStatus status;
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Log(Long requesterId, Long todoId, Long targetUserId, LogStatus status, String message) {
        this.requesterId = requesterId;
        this.todoId = todoId;
        this.targetUserId = targetUserId;
        this.status = status;
        this.message = message;
    }
}
