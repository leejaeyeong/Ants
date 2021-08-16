package com.ssafy.db.repository;

import com.ssafy.db.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findFileById(Long id);
    List<FileInfo> findAllByDate(LocalDate date);
    List<FileInfo> findAll();
}