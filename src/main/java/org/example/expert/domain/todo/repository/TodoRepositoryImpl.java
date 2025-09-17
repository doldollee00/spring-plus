package org.example.expert.domain.todo.repository;

import com.querydsl.core.BooleanBuilder;
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
    public Page<Todo> findByConditions(String title, LocalDateTime start, LocalDateTime end, String nickname, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (title != null && !title.isEmpty()) {
            builder.and(todo.title.contains(title));
        }

        if (start != null && end != null) {
            builder.and(todo.createdAt.between(start, end));
        }

        if (nickname != null && !nickname.isEmpty()) {
            builder.and(todo.user.nickname.contains(nickname));
        }

        List<Todo> content = jpaQueryFactory
                .selectFrom(todo)
                .join(todo.user, user).fetchJoin()
                .where(builder)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(todo.count())
                .from(todo)
                .join(todo.user, user)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
