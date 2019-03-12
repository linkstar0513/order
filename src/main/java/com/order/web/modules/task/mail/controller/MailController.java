package com.order.web.modules.task.mail.controller;

import com.order.web.bean.ApiResult;
import com.order.web.util.ApiTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/mail")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ThreadSendMail threadSendMail;

    @RequestMapping("/send")
    public ApiResult sendMail(){
        Thread thread1 = new Thread(threadSendMail);
       // Thread thread2 = new Thread(threadSendMail);
        thread1.start();
        //thread2.start();
        return ApiTools.result();
    }
}
