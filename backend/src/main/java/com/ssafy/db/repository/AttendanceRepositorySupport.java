package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Attendance;
import com.ssafy.db.entity.QAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class AttendanceRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QAttendance qAttendance = QAttendance.attendance;
    @Autowired
    AttendanceRepository attendanceRepository;
    public Attendance findAttendanceByUserId(String userId) {
        LocalDate now = LocalDate.now();
        Attendance attendance = jpaQueryFactory
                .select(qAttendance)
                .from(qAttendance)
                .where(qAttendance.userId.eq(userId).and(qAttendance.date.eq(now))).offset(0).limit(1).fetchFirst();

        if(attendance == null) return null;
        return attendance;
    }

    // 퇴근
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

    // 근태 기록 조회 1개월
    public List<Attendance> findAllByDateBetween(Map<String, Object> dateMap) {
        String userId = (String)dateMap.get("userId");
        int year = (Integer)dateMap.get("year");
        int month = (Integer)dateMap.get("month");

        // 해당 월 전체 조회 구간
        LocalDate start = LocalDate.of(year, month,1);
        LocalDate end = LocalDate.of(year, month, start.with(lastDayOfMonth()).getDayOfMonth());

        List<Attendance> attendances = attendanceRepository.findAllByDateBetween(start, end).stream().filter(
                a -> a.getUserId().equals(userId)
        ).collect(Collectors.toList());

        if (attendances == null)
            return null;
        return attendances;
    }
}
