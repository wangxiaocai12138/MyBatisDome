package dao.count;

import pojo.Count;

import java.util.List;

public interface CountMapper {
    //以时间字段升序查询第一条数据
    List<Count> getCountByDateDescOrLimitOne();
    //添加
    int addCountByDate(Count count);

    //修改
    int updateCountById(Count count);

}
