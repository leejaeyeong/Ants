package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(String userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userId.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }

    @Transactional
    public boolean updateUser(User user) {
        Long row = jpaQueryFactory.update(qUser)
                .set(qUser.department, user.getDepartment())
                .set(qUser.position, user.getPosition())
                .set(qUser.name, user.getName())
                .where(qUser.userId.eq(user.getUserId()))
                .execute();
        if (row > 0)
            return true;
        return false;
    }
    @Transactional
    public boolean deleteUserByUserId(String userId) {
        Long affectedRows = jpaQueryFactory.delete(qUser).where(qUser.userId.eq(userId)).execute();
        if(affectedRows >= 1) return true;
        return false;
    }

}
