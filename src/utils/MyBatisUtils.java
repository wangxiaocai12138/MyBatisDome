package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//获得sqlSessionFactory实例工具类
public class MyBatisUtils {

    private static SqlSessionFactory factory;

    static {
        InputStream is= null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //2、创建SqlSessionFactory对象，完成对配置文件的读取
            factory=new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打开一个sqlSession
    public static SqlSession createSqlSession(){
                        //开启事务控制(false)(true关闭事务)
        return factory.openSession(true);
    }

    //关闭
    public  static void closeSqlSession(SqlSession sqlSession){
        if(sqlSession!=null){
            sqlSession.close();
        }
    }

}
