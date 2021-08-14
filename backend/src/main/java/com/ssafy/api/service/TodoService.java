package com.ssafy.api.service;

import com.ssafy.api.request.TodoRegisterReq;
import com.ssafy.api.response.TodoInfoRes;
import com.ssafy.db.entity.Todo;
import com.ssafy.db.repository.DepartmentRepository;
import com.ssafy.db.repository.TodoRepository;
import com.ssafy.db.repository.TodoRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TodoRepositorySupport todoRepositorySupport;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserService userService;

    public Todo registerTodo(TodoRegisterReq todoRegisterReq){
        Todo todo = new Todo();
        todo.setDepartment(departmentRepository.findDepartmentById(todoRegisterReq.getDepartmentId()).get());
        todo.setTime(todoRegisterReq.getTime());
        todo.setTitle(todoRegisterReq.getTitle());
        todo.setUser(userService.getUserByUserId(todoRegisterReq.getUserId()));
        System.out.println("등록 전");
        todoRepository.save(todo);
        System.out.println("등록 후");

        return todo;
    }

    public List<Todo> getTodoList(Long departmentId){
        return todoRepositorySupport.getTodoList(departmentId).get();
    }

    public List<TodoInfoRes> findTodoByDate(Map<String, Object> dateMap) {
        int year = (Integer)dateMap.get("year");
        int month = (Integer)dateMap.get("month");
        int day = (Integer)dateMap.get("day");
        Long departmentId = (Long) dateMap.get("departmentId");
        LocalDate date = LocalDate.of(year, month, day);

        List<Todo> todoList = todoRepositorySupport.findTodoByDate(date, departmentId);
        if (todoList == null) return new ArrayList<>();
        List<TodoInfoRes> todoInfoResList = new ArrayList<>();
        for (Todo todo : todoList) {
            todoInfoResList.add(TodoInfoRes.of(todo));
        }
        return todoInfoResList;
    }
}
