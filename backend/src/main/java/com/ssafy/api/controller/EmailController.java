package com.ssafy.api.controller;

import com.ssafy.api.service.DepartmentService;
import com.ssafy.api.service.MailServiceImpl;
import com.ssafy.db.entity.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.List;

@Api(value = "이메일 send API", tags = {"Email"})
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private MailServiceImpl mailService;

    @GetMapping("/{userName}/{userEmail}")
    public ResponseEntity sendMail(@PathVariable String userName, @PathVariable String userEmail) throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append("<html align='center'><body>");
        sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
//        sb.append("<h1>"+"오늘도 화이팅!"+"<h1><br>");
//        sb.append("신간 도서를 소개합니다.<br><br>");
        sb.append("<a href='https://i5b103.p.ssafy.io:8443/'>");
        sb.append("<img  src='https://user-images.githubusercontent.com/41180841/129719939-9dde6a07-cd25-4f19-bc1d-143df3cd63a2.png' /> </a><br>");
        sb.append("</a>");
//        sb.append("<a href='http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788956746425&orderClick=LAG&Kc=#N'>상품보기</a>");
        sb.append("</body></html>");
        String html = sb.toString();
        mailService.sendMail(userEmail,userName + "님 그룹웨어 Ants 가입을 환영합니다!", html);

        return ResponseEntity.ok().build();
    }
}
