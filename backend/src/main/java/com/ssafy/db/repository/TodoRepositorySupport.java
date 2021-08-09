package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QTodo;
import com.ssafy.db.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QTodo qTodo = QTodo.todo;

    public Optional<List<Todo>> getTodoList(Long departmentId) {
        List<Todo> list = jpaQueryFactory.select(qTodo).from(qTodo)
                .where(qTodo.department.id.eq(departmentId).and(qTodo.date.eq(LocalDate.now()))).fetch();
        if(list == null) return Optional.empty();
        return Optional.of(list);
    }
}
