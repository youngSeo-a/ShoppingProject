package project.web.code.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service
@Slf4j

public class EmailSendService {
    @Autowired
     JavaMailSender mailSender;
    public void mailsend(String html, String subject, String fromEmail, String toEmail) {
        /* 메일링하는 기본 문법 */
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            msg.setHeader("content-type", "text/html; charset=UTF-8");
            msg.setContent(html, "text/html; charset=UTF-8"); // 내용
            msg.setSubject(subject); // 제목
            msg.setFrom(new InternetAddress(fromEmail)); // 보내는 사람
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail)); // 받는 사람
            mailSender.send(msg); // 이메일 전송
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}