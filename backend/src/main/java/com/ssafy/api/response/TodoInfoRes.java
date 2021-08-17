package com.ssafy.api.response;

import com.ssafy.db.entity.Todo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ApiModel("TodoResponse")
public class TodoInfoRes extends CalendarDetail {
    LocalDate date;
    LocalTime time;
    String title;
    String departmentName;
    boolean isDone;
    String userName;
    String userId;
    String profileLocation;
    String type = "todo-write";

    public static TodoInfoRes of (Todo todo) {
        TodoInfoRes tir = new TodoInfoRes();
        tir.setDate(todo.getDate());
        tir.setTime(todo.getTime());
        tir.setTitle(todo.getTitle());
        tir.setDepartmentName(todo.getDepartment().getDepartmentName());
        tir.setDone(todo.isDone());
        tir.setUserId(todo.getUser().getUserId());
        tir.setUserName(todo.getUser().getName());
        tir.setProfileLocation(todo.getUser().getProfileLocation());
        return tir;
    }
}
