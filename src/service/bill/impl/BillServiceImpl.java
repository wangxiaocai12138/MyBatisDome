package service.bill.impl;

import dao.bill.BillMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.Bill;
import service.bill.BillService;
import utils.MyBatisUtils;

import java.util.List;

public class BillServiceImpl implements BillService {
   SqlSession sqlSession= MyBatisUtils.createSqlSession();

    @Override
    public List<Bill> qureBillAll() {
        return sqlSession.getMapper(BillMapper.class).qureBillAll();
    }

    @Override
    public int addBill(Bill bill) {
        return sqlSession.getMapper(BillMapper.class).addBill(bill);
    }

    @Override
    public int delBillById(Integer id) {
        return sqlSession.getMapper(BillMapper.class).delBillById(id);
    }

    @Override
    public Bill qureBillById(Integer id) {
        return sqlSession.getMapper(BillMapper.class).qureBillById(id);
    }

    @Override
    public int modifyBillById(Bill bill) {
        return sqlSession.getMapper(BillMapper.class).modifyBillById(bill);
    }
}
