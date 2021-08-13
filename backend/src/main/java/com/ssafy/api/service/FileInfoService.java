package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.CommentRegisterPostReq;
import com.ssafy.api.response.BoardCommentRes;
import com.ssafy.api.response.BoardRes;
import com.ssafy.api.response.FileInfoRes;
import com.ssafy.db.entity.BoardType;
import com.ssafy.db.entity.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */

public interface FileInfoService {
    Map<String, Object> downloadFile(Long id) throws IOException;
    FileInfo uploadFile(MultipartFile file, String userId) throws IOException;
    List<FileInfoRes> findAll();
    List<FileInfoRes> findByFileExtension(String extension);
    List<FileInfoRes> findByFileName(String fileName);
    List<FileInfoRes> findByDepartment(Long id);
}
