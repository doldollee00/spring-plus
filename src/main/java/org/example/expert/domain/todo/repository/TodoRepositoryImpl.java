package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo result = jpaQueryFactory
                .selectFrom(todo)
                .join(todo.user, user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public Page<Todo> findByTextAndnicknameAndCreatedAtBetween(String title, LocalDateTime start, LocalDateTime end, String nickname, Pageable pageable) {
        List<Todo> result = jpaQueryFactory
                .selectFrom(todo)
                .where(
                        todo.title.contains(title),
                        todo.user.nickname.contains(nickname),
                        todo.createdAt.between(start, end)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .join(todo.user, user)
                .where(
                        todo.title.contains(title),
                        todo.user.nickname.contains(nickname),
                        todo.createdAt.between(start, end)
                );
        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Todo> findByTitle(String title, Pageable pageable) {
        List<Todo> result = jpaQueryFactory
                .selectFrom(todo)
                .where(todo.title.contains(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .where(todo.title.contains(title));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Todo> findByNickname(String nickname, Pageable pageable) {
        List<Todo> result = jpaQueryFactory
                .selectFrom(todo)
                .join(todo.user, user).fetchJoin()
                .where(todo.user.nickname.contains(nickname))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .where(todo.user.nickname.contains(nickname));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Todo> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        List<Todo> result = jpaQueryFactory
                .selectFrom(todo)
                .where(todo.createdAt.between(start, end))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .where(todo.createdAt.between(start, end));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }
}
