package cn.bdqn.itrip.service;

import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import cn.itrip.common.RedisHelp;
import cn.itrip.pojo.ItripUser;
import com.alibaba.fastjson.JSON;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

//generate Token
@Component
public class TokenSerViceImpl implements TokenService {
    //注解的作用就是在我们的spring的配置文件中去怎加一个<bean ></bean> 也就是相当于替代了以前的new
    @Resource
    private RedisHelp redisHelp;
    @Override
    public String generateToken(String userAgent,ItripUser user) throws Exception {
      StringBuffer buffer = new StringBuffer();
      buffer.append("token:-");
      UserAgent agent = UserAgent.parseUserAgentString(userAgent);
      if(agent.getOperatingSystem().isMobileDevice())
      {
          buffer.append("MOBILE-");
      }else {
          buffer.append("PC-");
          buffer.append(MD5.getMd5(user.getUserCode(),32)+"");
          buffer.append(new SimpleDateFormat(("yyyyMMddHHmmsss").format(new Date()+"-")));
          buffer.append(MD5.getMd5(userAgent,6));
      }
        return buffer.toString();
    }


    @Override
    public boolean validate(String userAgent, String token) {
    if(!RedisHelp.exist(token)){
      String agentMD5=token.split("-")[4];
      if(!MD5.getMd5(userAgent,6).equals(agentMD5)){
    return false;
      }
    }
        return true;
    }


    @Override
    public void save(String token,ItripUser itripUser) throws Exception {
   RedisAPI redisAPI=new RedisAPI();
    if(token.startsWith("token:-")){
        redisAPI.set(token,2*60*60,JSON.toJSONString(itripUser));
    }else {
        redisAPI.set(token,JSON.toJSONString(itripUser));
    }
    }

}
