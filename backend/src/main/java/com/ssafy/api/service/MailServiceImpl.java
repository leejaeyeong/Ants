package com.ssafy.api.service;

import com.ssafy.db.entity.Department;
import com.ssafy.db.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject(subject);
            messageHelper.setTo(to);
            messageHelper.setFrom("ants-user@naver.com", "Ants<엔츠>");
            messageHelper.setText(body,true);
            javaMailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
