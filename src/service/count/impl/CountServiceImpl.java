package service.count.impl;

import dao.count.CountMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Count;
import service.count.CountService;
import utils.MyBatisUtils;

import java.util.List;

public class CountServiceImpl implements CountService {
    SqlSession sqlSession= MyBatisUtils.createSqlSession();

    @Override
    public List<Count> getCountByDateDescOrLimitOne() {
        return sqlSession.getMapper(CountMapper.class).getCountByDateDescOrLimitOne();
    }

    @Override
    public int addCountByDate(Count count) {
        return sqlSession.getMapper(CountMapper.class).addCountByDate(count);
    }

    @Override
    public int updateCountById(Count count) {
        return sqlSession.getMapper(CountMapper.class).updateCountById(count);
    }
}
