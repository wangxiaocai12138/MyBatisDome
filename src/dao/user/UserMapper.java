package dao.user;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

//一般叫做接口映射器
public interface UserMapper {
    void count();

    List<User> getUserList();

    List<User> getUserListByUserName(String userName);

    List<User> getUserListByUserNameAndUserRole(User user);
    /*根据用户信息查询用户地址列表*/
    List<User> getAddressListByUserId(@Param("id")Integer id);
    /*根据角色id列表，查询所有符合用户列表(动态sql>>foreach标签array数组为参数)*/
    List<User> getUserByRoleId_foreach_array(Integer[] roleIds);

    //上面全是测试

    //用户登录查询
    User loginByCodeAndPwd(User user);
}
