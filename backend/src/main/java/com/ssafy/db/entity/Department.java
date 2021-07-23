package com.ssafy.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department extends BaseEntity{
    String departmentName;

    public Department(long id, String departmentName){
        this.id = id;
        this.departmentName = departmentName;
    }
}
