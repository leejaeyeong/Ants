package com.ssafy.api.service;

import com.ssafy.api.response.BoardRes;
import com.ssafy.common.util.FileUtil;
import com.ssafy.db.entity.FileInfo;
import com.ssafy.db.entity.User;
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
