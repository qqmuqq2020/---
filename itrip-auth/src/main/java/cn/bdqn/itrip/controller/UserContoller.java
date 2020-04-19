package cn.bdqn.itrip.controller;


import cn.itrip.common.DtoUtil;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisHelp;
import cn.itrip.dao.itripUser.ItripUserMapper;
import cn.itrip.dto.Dto;
import cn.itrip.pojo.ItripUser;
import cn.itrip.vo.ItripTokenVO;

import cn.itrip.vo.ItripUserVO;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;



/*用在类上面说明这个类的作用*/
@Api(value = "appinfo",description = "用户模块")
@Controller
@RequestMapping("api")

public class UserContoller {

    @Resource
    RedisHelp redisHelp;

    @Resource
    ItripUserMapper dao;


    @RequestMapping(value="/retoken",method = RequestMethod.POST
        ,produces = "application/json",
            headers="token")

        public @ResponseBody Dto reloadToken(String token,ItripUser user
                                ,HttpServletRequest request) throws Exception{
        //置换新的token



        ItripTokenVO tokenVO=new ItripTokenVO(token,
                Calendar.getInstance().getTimeInMillis()+60*60*2,
                        Calendar.getInstance().getTimeInMillis());



        return null;

    }
    @RequestMapping(value="/dologin",method=RequestMethod.POST,produces= "application/json")

    /*用在方法上说明方法的信息ApiOperation*/
    /*Apiaram 对参数的描述*/
    @ApiOperation(value="用户登录的token信息",httpMethod="POST",
                protocols = "HTTP",produces="application/json",
                notes = "详细说明  返回神魔标识成功，什么表示失败，")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",required=true,value="用户名",name="name",defaultValue="itrip@163.com"),
            @ApiImplicitParam(paramType="query",required=true,value="密码",name="password",defaultValue="111111"),
    })

    public @ResponseBody Dto Getlist(String name, String password, HttpServletRequest request)
    {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("n",name);
        map.put("p",password);
        ItripUser user=dao.GetLogin(map);

        if(user==null)
        {
            return DtoUtil.returnFail("登录失败","1000");
        }
        //当用户登录成功，把用户名存入Redis中
        //key为token=PC：“前缀PC-USERCODE-USERID-CREATIONDATE-RONDEM[6位]”
        //value用户的对象转成字符串 fastjson
        String newtoken="";

            newtoken=Token(user,request.getHeader("User-Agent"));
            redisHelp.setRedis(newtoken, JSONArray.toJSONString(user), 60*60*2);

        ItripTokenVO tokenVO=new ItripTokenVO(newtoken,Calendar.getInstance().getTimeInMillis()+60*60*2,Calendar.getInstance().getTimeInMillis());

        return DtoUtil.returnDataSuccess(tokenVO);
    }





   /* @RequestMapping(value="/registerbyphone",method=RequestMethod.POST,produces= "application/json")
    public @ResponseBody Dto Register(@RequestBody ItripUserVO userVO)
    {
     ItripUser user = new ItripUser();
     user.setUserCode(userVO.getUserCode());
     user.setUserName(userVO.getUserName());

    }*/



    //生成token的方法
    public static String Token(ItripUser user,String Agent)
    {
         StringBuilder builder=new StringBuilder();
         builder.append("PC-");
         builder.append(user.getUserCode()+"-");
         builder.append(user.getId()+"-");
         builder.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
         builder.append(MD5.getMd5("",6));
         return  builder.toString();

    }















}
