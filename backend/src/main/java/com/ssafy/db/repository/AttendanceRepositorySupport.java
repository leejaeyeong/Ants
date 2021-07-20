package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.QAttendance;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class AttendanceRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAttendance qAttendance = QAttendance.attendance;

    public Attendance findAttendanceByUserId(String userId) {
        LocalDate now = LocalDate.now();
        Attendance attendance = jpaQueryFactory
                .select(qAttendance)
                .from(qAttendance)
                .where(qAttendance.userId.eq(userId).and(qAttendance.date.eq(now))).offset(0).limit(1).fetchFirst();

        if(attendance == null) return null;
        return attendance;
    }

    @Transactional
    public boolean checkOutUser(Attendance attendance) {
        LocalDate now = LocalDate.now();
        Long row = jpaQueryFactory.update(qAttendance)
                .set(qAttendance.checkOutTime, attendance.getCheckOutTime())
                .where(qAttendance.date.eq(now).and(qAttendance.userId.eq(attendance.getUserId())))
                .execute();
        if (row > 0)
            return true;
        return false;
    }

    // 퇴근

    // 근태 기록 조회

}
