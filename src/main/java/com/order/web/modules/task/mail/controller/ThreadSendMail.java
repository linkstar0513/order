package com.order.web.modules.task.mail.controller;

import com.order.web.bean.ApiResult;
import com.order.web.util.ApiTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class ThreadSendMail implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Autowired
    private JavaMailSender javaMailSender;
    private int value = 0;
    @Override
    public void run() {
            while(value<100){
                synchronized (this){
                    value += 1;
                    System.out.println(value+ Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
            sendMail();


    }
    public ApiResult sendMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("wydlink@163.com");
        simpleMailMessage.setTo("wydlink@163.com");
        simpleMailMessage.setSubject("主题：邮件");
        simpleMailMessage.setText("正文");
        System.out.println("hello");
        javaMailSender.send(simpleMailMessage);
        return ApiTools.result();
    }
}
