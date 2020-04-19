package cn.bdqn.itrip.service;

import cn.itrip.pojo.ItripUser;

public interface TokenService {
    //生成Token的方法
    public String generateToken(String userAgent,ItripUser user) throws Exception;
    //保存
    public void  save(String token,ItripUser itripUser) throws  Exception;
    //验证是否有效
    public boolean validate (String userAgent,String token);
   /* //删除token的方法
    //刷新token的方法
    public  String  reloadToken(String userAgent,String token);*/
}
