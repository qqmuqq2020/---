package cn.bdqn.itrip.service;

import org.springframework.context.annotation.ComponentScan;


public interface MailService {

    /**
     *  发送激活email的 method
     *  param1:sendPerpleEmail
     *  param2:激活码
     */

    public void sendActivationMail(String mailTo,String activeCode);
}