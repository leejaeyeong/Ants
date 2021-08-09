package com.ssafy.api.service;

import com.ssafy.api.request.TodoRegisterReq;
import com.ssafy.db.entity.Todo;
import com.ssafy.db.repository.DepartmentRepository;
import com.ssafy.db.repository.TodoRepository;
import com.ssafy.db.repository.TodoRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TodoRepositorySupport todoRepositorySupport;

    @Autowired
    private TodoRepository todoRepository;

    public Todo registerTodo(TodoRegisterReq todoRegisterReq){
        Todo todo = new Todo();
        todo.setDepartment(departmentRepository.findDepartmentById(todoRegisterReq.getDepartmentId()).get());
        todo.setTime(todoRegisterReq.getTime());
        todo.setTitle(todoRegisterReq.getTitle());

        System.out.println("등록 전");
        todoRepository.save(todo);
        System.out.println("등록 후");

        return todo;
    }

    public List<Todo> getTodoList(Long departmentId){
        return todoRepositorySupport.getTodoList(departmentId).get();
    }
}
