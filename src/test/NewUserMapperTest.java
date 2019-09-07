package test;

import dao.user.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.Address;
import pojo.User;
import utils.MyBatisUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewUserMapperTest {
    /*引入日志文件*/
    public Logger logger=Logger.getLogger(NewUserMapperTest.class);


    @Test
    public void test(){
        int count=0;
        SqlSession sqlSession=null;
        try {
            //调用工具类生成sqlSession
            sqlSession= MyBatisUtils.createSqlSession();
            //4、调用mapper文件，来对数据进行操作>>
            // 在执行此步骤之前，必须将mapper文件引入到mybatis-config.xml文件中声明
            count=sqlSession.selectOne("dao.user.UserMapper.count");

            /*打印日志*/
            logger.debug("用户总记录数为："+count);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

    }

    //查询所有用户
    @Test
    public void listTest(){
        List<User> list=null;

        SqlSession sqlSession=null;
        try {
            //调用工具类生成sqlSession
            sqlSession= MyBatisUtils.createSqlSession();
            //4、调用mapper文件，来对数据进行操作>>
            // 在执行此步骤之前，必须将mapper文件引入到mybatis-config.xml文件中声明
            list=sqlSession.selectList("dao.user.UserMapper.getUserList");



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                /*打印日志*/
                new UserMapperTest().logger.debug("用户名："+list.get(i).getUserName());
                new UserMapperTest().logger.debug("密码："+list.get(i).getUserPassword());
            }
        }

    }

    //使用接口查询所有用户
    @Test
    public void listInterTest(){
        List<User> list=null;

        SqlSession sqlSession=null;
        try {
            //调用工具类生成sqlSession
            sqlSession= MyBatisUtils.createSqlSession();
            //4、调用mapper文件，来对数据进行操作>>
            // 使用接口方式，调用对应的接口方法
            list=sqlSession.getMapper(UserMapper.class).getUserList();



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                /*打印日志*/
                new UserMapperTest().logger.debug("用户名："+list.get(i).getUserName());
                new UserMapperTest().logger.debug("密码："+list.get(i).getUserPassword());
            }
        }

    }

    //使用接口模糊条件查询用户
    @Test
    public void getlistByNameInterTest(){
        List<User> list=null;
        String userName="赵";
        SqlSession sqlSession=null;
        try {
            //调用工具类生成sqlSession
            sqlSession= MyBatisUtils.createSqlSession();
            //4、调用mapper文件，来对数据进行操作>>
            // 使用接口方式，调用对应的接口方法
            list=sqlSession.getMapper(UserMapper.class).getUserListByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                /*打印日志*/
                new UserMapperTest().logger.debug("用户名："+list.get(i).getUserName());
                new UserMapperTest().logger.debug("密码："+list.get(i).getUserPassword());
            }
        }

    }

    //使用接口模糊多条件查询用户
    @Test
    public void getlistByNameAndRoleInterTest(){
        List<User> list=null;
        User user=new User();
        /*多参动态sql语句查询*/
        user.setUserRole(1);
        SqlSession sqlSession=null;
        try {
            //调用工具类生成sqlSession
            sqlSession= MyBatisUtils.createSqlSession();
            //4、调用mapper文件，来对数据进行操作>>
            // 使用接口方式，调用对应的接口方法
            list=sqlSession.getMapper(UserMapper.class).getUserListByUserNameAndUserRole(user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MyBatisUtils.closeSqlSession(sqlSession);
        }

        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                /*打印日志*/
                new UserMapperTest().logger.debug("用户名："+list.get(i).getUserName());
                new UserMapperTest().logger.debug("密码："+list.get(i).getUserPassword());
            }
        }

    }

    /*根据用户信息查询用户地址列表*/
    @Test
    public void getAddressListByUserId(){
        List<User> userList=new ArrayList<>();
        SqlSession sqlSession=null;
        Integer id=1;
        sqlSession =MyBatisUtils.createSqlSession();
        userList=sqlSession.getMapper(UserMapper.class).getAddressListByUserId(id);
        MyBatisUtils.closeSqlSession(sqlSession);

        for (User user:userList){
            new UserMapperTest().logger.debug("用户名："+user.getUserName()+"的收货地址有："+user.getAddressList().size());
            for (Address address:user.getAddressList()){
                new UserMapperTest().logger.debug("收货人："+address.getContact()
                                    +",地址："+address.getAddressDesc()
                                    +",电话："+address.getTel());
            }
        }
    }

    /*使用多个用户角色id获取用户列表*/
    @Test
    public void getUserByRoleId_foreach_array(){
        Integer [] roleId=new Integer[]{1,3};
        SqlSession sqlSession=MyBatisUtils.createSqlSession();
        List<User> users=sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_array(roleId);
        MyBatisUtils.closeSqlSession(sqlSession);
        for(User user:users){
            new UserMapperTest().logger.debug("用户名："+user.getUserName()+",角色id："+user.getUserRole());
        }
    }

}
