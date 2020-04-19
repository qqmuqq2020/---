package cn.bdqn.itrip.service;



import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mailService")
public class mainServiceImpl implements  MailService{



    @Resource
    private SimpleMailMessage mailMessage;
    @Resource
    private MailSender mailSender;

    @Override
    public void sendActivationMail(String mailTo, String activeCode) {

        mailMessage.setTo(mailTo);//设置发送人
        mailMessage.setText("您的激活码是"+activeCode);//设置发送的内容
        mailSender.send(mailMessage);//发送用邮件


    }
}
