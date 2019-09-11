package service.count.impl;

import dao.count.CountMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Count;
import service.count.CountService;
import utils.MyBatisUtils;

public class CountServiceImpl implements CountService {
    SqlSession sqlSession= MyBatisUtils.createSqlSession();

    @Override
    public Count getCountByDateDescOrLimitOne() {
        return sqlSession.getMapper(CountMapper.class).getCountByDateDescOrLimitOne();
    }

    @Override
    public int addCountByDate(Count count) {
        return sqlSession.getMapper(CountMapper.class).addCountByDate(count);
    }
}
