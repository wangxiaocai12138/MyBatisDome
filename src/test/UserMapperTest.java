package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

public class UserMapperTest {
    /*引入日志文件*/
    public Logger logger=Logger.getLogger(UserMapperTest.class);


    @Test
    public void test(){
        String resource ="mybatis-config.xml";
        int count=0;
        SqlSession sqlSession=null;

            //1、获取mybatis-config.xml的输入流
        InputStream is= null;
        try {
            is = Resources.getResourceAsStream(resource);
            //2、创建SqlSessionFactory对象，完成对配置文件的读取
            SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
            //3、获取SqlSession对象
            sqlSession=factory.openSession();
            //4、调用mapper文件，来对数据进行操作>>
            // 在执行此步骤之前，必须将mapper文件引入到mybatis-config.xml文件中声明
            count=sqlSession.selectOne("dao.user.UserMapper.count");

            /*打印日志*/
            new UserMapperTest().logger.debug("用户总记录数为："+count);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }


}
