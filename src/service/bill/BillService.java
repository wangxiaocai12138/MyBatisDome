package service.bill;

import pojo.Bill;

import java.util.List;

public interface BillService {
    List<Bill> qureBillAll();

    int addBill(Bill bill);

    int delBillById(Integer id);

    Bill qureBillById(Integer id);

    int modifyBillById(Bill bill);
}
