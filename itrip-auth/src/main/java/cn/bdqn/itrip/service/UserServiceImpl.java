package cn.bdqn.itrip.service;

import cn.itrip.common.MD5;
import cn.itrip.common.RedisHelp;
import cn.itrip.dao.itripUser.ItripUserMapper;
import cn.itrip.pojo.ItripUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements   UserService{

    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private MailService mailService;
    @Resource
    private RedisHelp redisHelp;

    @Override
    public void ItriptxcreateUser(ItripUser user) throws Exception {
        //1添加用户信息
        itripUserMapper.insertItripUser(user);
        //2生成激活码
        String activetionCode=MD5.getMd5(new Date().toLocaleString(),32);
        //3发送邮件
        mailService.sendActivationMail(user.getUserCode(),activetionCode);
        //4激活码存入Redis
        redisHelp.set("activation:"+user.getUserCode(),30*60,activetionCode);

    }











    /*    @Resource
    private RedisHelp redisHelp;
    @Resource
    private ItripUserMapper dao;
    @Resource
    private SmsService smsService;
    @Override
    public void findUserByUserPhone(ItripUser user)throws  Exception {
    //1 创建用户
       dao.insertItripUser(user);
    //2 生成验证码
        int code= MD5.getRandomCode();
    //3 发送验证啊
        smsService.send("15135355491","1",new String[]{String.valueOf(code),"1"});
    //4缓存验证码 Redis
        redisHelp.set("activation:"+user.getUserCode(),60,String.valueOf(code));

    }*/
   /* //短信验证的方法
    public boolean validatephone(String phoneNum,String code)throws Exception{

        //1比对验证码
        String key = "activation:"+phoneNum;
        String value = redisHelp.getkey(key);
        if(null!=value&&value.equals(code)){

            //2更新用户激活状态
            return true;
        }
        return false;
    }
*/

    @Override
    public ItripUser login(String userCode, String userPassword) throws Exception {
        ItripUser itripUser=this.login(userCode,userPassword);
        if(itripUser!=null&&itripUser.getUserPassword()==userPassword){
           if(itripUser.getActivated()!=1){
               throw new Exception("用户未激活");
           }
            //生成Token 保存到redis库中
        }
        return null;
    }
}
