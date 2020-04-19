/*package cn.bdqn.itrip.service;



import java.util.HashMap;

public class SmsServiceimpl implements  SmsService {


    @Override
    public void send(String to, String templated, String[] datas) throws Exception {
        //实例化发送短信的实例
        CCPRestSmsSDK sdk =new CCPRestSmsSDK();
        //把我们的账号信息告诉他
        sdk.init("app.cloopen.com","8883");
        sdk.setAccount("8aaf07086dcdca52016de331d35f0e5c","a9e393b7c67a48c78f176cb8ada96ae4");
        sdk.setAppId("8aaf07086dcdca52016de331d3a80e62");
        //发送短信
       HashMap result = sdk.sendTemplateSMS(to,templated,datas);
       //查看是否发送成功
      if("000000".equals(result.get("statusCode"))) {
          System.err.print("发送成功");
      }else{
          throw new Exception(result.get("statusCode").toString()+":"+result.get("statusMsg").toString());
      }
    }
}*/
