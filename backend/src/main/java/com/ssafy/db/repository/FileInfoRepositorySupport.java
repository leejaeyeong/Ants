package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FileInfoRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QFileInfo qFileInfo = QFileInfo.fileInfo;

    public Optional<List<FileInfo>> findAllByFileExtension(String extension) {
        List<FileInfo> fileInfos =  jpaQueryFactory.select(qFileInfo).from(qFileInfo)
                .where(qFileInfo.fileExtension.eq(extension)).fetch();
        if(fileInfos == null) return Optional.empty();
        return Optional.ofNullable(fileInfos);
    }

    public Optional<List<FileInfo>> findByFileName(String fileName) {
        List<FileInfo> fileInfos  =  jpaQueryFactory.select(qFileInfo).from(qFileInfo)
                .where(qFileInfo.fileName.like("%" + fileName + "%")).fetch();
        if(fileInfos == null) return Optional.empty();
        return Optional.ofNullable(fileInfos);
    }

    public Optional<List<FileInfo>> findByDepartment(Department department) {
        List<FileInfo> fileInfos  =  jpaQueryFactory.select(qFileInfo).from(qFileInfo)
                .where(qFileInfo.department.eq(department)).fetch();
        if(fileInfos == null) return Optional.empty();
        return Optional.ofNullable(fileInfos);
    }

    public List<FileInfo> findByUserId(String userId) {
        List<FileInfo> fileInfos  =  jpaQueryFactory.select(qFileInfo).from(qFileInfo)
                .where(qFileInfo.user.userId.eq(userId)).fetch();
        if(fileInfos == null) return new ArrayList<>();
        return fileInfos;
    }
}
