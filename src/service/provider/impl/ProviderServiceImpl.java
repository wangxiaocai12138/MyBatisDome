package service.provider.impl;

import dao.provider.ProviderMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Provider;
import service.provider.ProviderService;
import utils.MyBatisUtils;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {
    SqlSession sqlSession= MyBatisUtils.createSqlSession();
    @Override
    public List<Provider> qureProviderAll() {
        return sqlSession.getMapper(ProviderMapper.class).qureProviderAll();
    }
}
