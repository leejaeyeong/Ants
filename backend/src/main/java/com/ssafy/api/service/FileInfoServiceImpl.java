package com.ssafy.api.service;

import com.ssafy.api.response.BoardRes;
import com.ssafy.api.response.FileInfoRes;
import com.ssafy.common.util.FileUtil;
import com.ssafy.db.entity.Department;
import com.ssafy.db.entity.FileInfo;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.DepartmentRepository;
import com.ssafy.db.repository.FileInfoRepository;
import com.ssafy.db.repository.FileInfoRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service("fileInfoService")
public class FileInfoServiceImpl implements FileInfoService {
    @Autowired
    FileInfoRepository fileInfoRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserService userService;

    @Autowired
    FileInfoRepositorySupport fileInfoRepositorySupport;

    FileUtil fileUtil = FileUtil.getInstance();

    @Override
    public Map<String, Object> downloadFile(Long id) throws IOException {
        Map<String, Object> fileMap = new HashMap<>();

        FileInfo fileInfo = fileInfoRepository.findFileById(id);


//
        java.io.File t = new File("..");
        String paths = t.getCanonicalPath();
        System.out.println("자동으로 찍어주는 path :");
        System.out.println(paths);
        System.out.println("자동으로 찍어주는 path :");

        Path path = Paths.get(paths + "/" + fileInfo.getFileLocation());
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(fileInfo.getFileName(), StandardCharsets.UTF_8)
                .build());

        Resource resource = new InputStreamResource(Files.newInputStream(path));
//

        fileMap.put("resource",resource);
        fileMap.put("headers",headers);

        return fileMap;
    }

    @Override
    public FileInfo uploadFile(MultipartFile multiFile, String userId) throws IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUser(userService.getUserByUserId(userId));
//        fileInfo.setDepartment(userService.getUserByUserId(userId).getDepartment());
//        fileInfo.setDate(LocalDate.now());
        if (multiFile.getOriginalFilename().contains(".")) {
            StringTokenizer st = new StringTokenizer(multiFile.getOriginalFilename(), ".");
            st.nextToken();
            fileInfo.setFileExtension("." + st.nextToken());
        }

        File t = new File("..");
        String path = t.getCanonicalPath();
        fileUtil.createFilePath(path += "/media");
        fileUtil.createFilePath(path += "/files");
        String uuid = UUID.randomUUID().toString();
        File file = fileUtil.createFilePath(path + "/" + uuid);

        fileInfo.setFileLocation("/media/files/" + uuid +"/" + multiFile.getOriginalFilename());
        fileInfo.setFileName(multiFile.getOriginalFilename());
        fileInfo.setSize(computeFileSize(multiFile.getSize()));
        String filePath = file.getAbsoluteFile() + "/" + multiFile.getOriginalFilename();

        File dest = new File(filePath);
        multiFile.transferTo(dest);
        return fileInfoRepository.save(fileInfo);
    }

    @Override
    public List<FileInfoRes> findAll() {
        List<FileInfo> files = fileInfoRepository.findAll();
        List<FileInfoRes> fileInfoResList = new ArrayList<>();
        for (FileInfo fileInfo : files) {
            fileInfoResList.add(FileInfoRes.of(fileInfo));
        }
        return fileInfoResList;
    }

    @Override
    public List<FileInfoRes> findByFileExtension(String extension) {
        List<FileInfoRes> fileInfoResList = null;
        if (fileInfoRepositorySupport.findAllByFileExtension(extension).isPresent()) {
            List<FileInfo> files = fileInfoRepositorySupport.findAllByFileExtension(extension).get();
            fileInfoResList = new ArrayList<>();
            for (FileInfo fileInfo : files) {
                fileInfoResList.add(FileInfoRes.of(fileInfo));
            }
        }
        return fileInfoResList;
    }

    @Override
    public List<FileInfoRes> findByFileName(String fileName) {
        List<FileInfoRes> fileInfoResList = null;
        if (fileInfoRepositorySupport.findByFileName(fileName).isPresent()) {
            List<FileInfo> files = fileInfoRepositorySupport.findByFileName(fileName).get();
            fileInfoResList = new ArrayList<>();
            for (FileInfo fileInfo : files) {
                fileInfoResList.add(FileInfoRes.of(fileInfo));
            }
        }
        return fileInfoResList;
    }

    @Override
    public List<FileInfoRes> findByDepartment(Long id) {
        List<FileInfoRes> fileInfoResList = null;
        Department department = departmentRepository.findDepartmentById(id).get();
        if (fileInfoRepositorySupport.findByDepartment(department).isPresent()) {
            List<FileInfo> files = fileInfoRepositorySupport.findByDepartment(department).get();
            fileInfoResList = new ArrayList<>();
            for (FileInfo fileInfo : files) {
                fileInfoResList.add(FileInfoRes.of(fileInfo));
            }
        }
        return fileInfoResList;
    }

    public String computeFileSize(Long size) {
        Long ret = size;
        String unit;
        if (size / 1000000000 >= 1) {
            ret = size / 1000000000;
            unit = "GB";
        } else if (size / 1000000 >= 1) {
            ret = size / 1000000;
            unit = "MB";
        } else if (size / 1000 >= 1) {
            ret = size / 1000;
            unit = "KB";
        } else {
            unit = "B";
        }
        return Long.toString(ret) + unit;
    }
}
