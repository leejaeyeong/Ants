package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.QTodo;
import com.ssafy.db.entity.Todo;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Repository
public class TodoRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    TodoRepository todoRepository;

    QTodo qTodo = QTodo.todo;

    public Optional<List<Todo>> getTodoList(Long departmentId) {
        List<Todo> list = jpaQueryFactory.select(qTodo).from(qTodo)
                .where(qTodo.department.id.eq(departmentId).and(qTodo.date.eq(LocalDate.now()))).fetch();
        if(list == null) return Optional.empty();
        return Optional.of(list);
    }

    public List<Todo> getTodoListByUserId(String userId) {
        List<Todo> list = jpaQueryFactory.select(qTodo).from(qTodo)
                .where(qTodo.user.userId.eq(userId)).fetch();
        if(list == null) return new ArrayList<>();
        return list;
    }

    // 특정 부서의 투두 리스트 1개월 단위 반환
    public List<Todo> findAllByDateBetween(Map<String, Object> dateMap) {
        int year = (Integer)dateMap.get("year");
        int month = (Integer)dateMap.get("month");
        Long departmentId = (Long) dateMap.get("departmentId");

        LocalDate start = convertToLocalDate(year, month,1);
        LocalDate end = convertToLocalDate(year, month, start.with(lastDayOfMonth()).getDayOfMonth());

        List<Todo> todos = todoRepository.findAllByDateBetween(start, end).stream().filter(
                a -> a.getDepartment().getId().equals(departmentId)
        ).collect(Collectors.toList());
        return todos;
    }

    public List<Todo> findTodoByDate(LocalDate date, Long departmentId) {
        List<Todo> list = jpaQueryFactory.select(qTodo).from(qTodo)
                .where(qTodo.date.eq(date).and(qTodo.department.id.eq(departmentId)))
                .fetch();
        return list;
    }
    public LocalDate convertToLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
