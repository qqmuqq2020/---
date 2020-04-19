package cn.itrip.dao.itripUser;

import cn.itrip.pojo.ItripUser;
import cn.itrip.vo.ItripUserVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripUserMapper {
	//根据id查询用户
	public ItripUser getItripUserById(@Param(value = "id") Long id)throws Exception;
	//根据map查询用户
	public List<ItripUser>	getItripUserListByMap(Map<String, Object> param)throws Exception;
	//查询总数
	public Integer getItripUserCountByMap(Map<String, Object> param)throws Exception;
	//添加用户
	public Integer insertItripUser(ItripUser itripUser)throws Exception;
	//更新用户
	public Integer updateItripUser(ItripUser itripUser)throws Exception;
	//更新用户状态通过激活码
	public Integer updateStatus(@Param("code")String code)throws Exception;
	//删除用户通过id
	public Integer deleteItripUserById(@Param(value = "id") Long id)throws Exception;



	//登录验证
	public int loginInsert(ItripUserVO vo);

	public  ItripUser GetLogin(Map<String, Object> param);
}
