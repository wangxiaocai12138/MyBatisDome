package dao.bill;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import pojo.Bill;

import java.util.List;

public interface BillMapper {
    List<Bill> qureBillAll();

    int addBill(Bill bill);
                    /*参数注解！，多参时一定要添加注解*/
    int delBillById(@Param("id") Integer id);
                    /*使用参数时最好使用其类型包装类*/
    Bill qureBillById(@Param("id") Integer id);

    int modifyBillById(Bill bill);
}
