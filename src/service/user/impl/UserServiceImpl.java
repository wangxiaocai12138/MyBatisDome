package service.user.impl;

import dao.user.UserMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.User;
import service.user.UserService;
import utils.MyBatisUtils;

public class UserServiceImpl implements UserService {
    SqlSession sqlSession= MyBatisUtils.createSqlSession();

    @Override
    public User loginByCodeAndPwd(User user) {
        User user2=sqlSession.getMapper(UserMapper.class).loginByCodeAndPwd(user);
        return user2;
    }
}
