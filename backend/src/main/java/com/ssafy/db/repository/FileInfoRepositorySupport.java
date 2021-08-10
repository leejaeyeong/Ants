package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
}
