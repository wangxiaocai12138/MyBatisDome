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
    public List<Provider> qureProviderAll(String queryProCode, String queryProName) {
        return sqlSession.getMapper(ProviderMapper.class).qureProviderAll(queryProCode,queryProName);
    }

    @Override
    public int addProvider(Provider provider) {
        return sqlSession.getMapper(ProviderMapper.class).addProvider(provider);
    }
}
