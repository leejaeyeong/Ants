package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Grp;
import com.ssafy.db.entity.QGrp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class FileInfoRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;


}
