package cn.bdqn.itrip.service;

import cn.itrip.pojo.ItripUser;

public interface UserService {

    //很多的子操作构成的
    public void ItriptxcreateUser(ItripUser user)throws  Exception;


    //用户登录的方法
    public ItripUser login (String userCode , String userPassword)throws  Exception;

     /*  //用户注册的方法
    public void findUserByUserPhone(ItripUser user)throws  Exception;
    //短信验证的方法
    public boolean validatephone(String phoneNum,String code)throws Exception;
*/
}
