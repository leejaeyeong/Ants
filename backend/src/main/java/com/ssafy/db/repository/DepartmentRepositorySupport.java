package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.QDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DepartmentRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QDepartment qDepartment = QDepartment.department;

    public Optional<Department> findDepartmentById(Long id) {
        Department department = jpaQueryFactory.select(qDepartment).from(qDepartment)
                .where(qDepartment.id.eq(id)).fetchOne();
        if(department == null) return Optional.empty();
        return Optional.ofNullable(department);
    }
}
