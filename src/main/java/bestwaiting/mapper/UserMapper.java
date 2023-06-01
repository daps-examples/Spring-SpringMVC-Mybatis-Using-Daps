package bestwaiting.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import bestwaiting.model.UserBean;


public interface UserMapper {
	/**
	 * 登录验证
	 * @param user_name
	 * @param user_pwd
	 * @return
	 */
	@Select("select * from users where user_name=#{name} and user_pwd=#{pwd}")
	@Results({
		@Result(id=true,property="id",column="id",javaType=Integer.class),
		@Result(property="user_name",column="user_name",javaType=String.class),
		@Result(property="user_pwd",column="user_pwd",javaType=String.class),
		@Result(property="user_phone",column="user_phone",javaType=String.class),
		@Result(property="user_email",column="user_email",javaType=String.class),
		@Result(property="user_note",column="user_note",javaType=String.class)
	})
	public UserBean login(@Param("name")String user_name,@Param("pwd")String user_pwd);
	
	/**
	 * 添加用户
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	@Insert("insert into users value(null,user.user_name,user。user_pwd,user.user_phone,user.user_email,user.user_note)")
	@Options(useGeneratedKeys=true,keyProperty="user.id")
	public int insertUser(@Param("user")UserBean user)throws Exception;
	
	@Delete("delete from users where id=#{id}")
	public int delUser(int id) throws Exception;
}
