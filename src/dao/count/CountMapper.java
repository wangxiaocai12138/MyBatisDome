package dao.count;

import pojo.Count;

public interface CountMapper {
    //以时间字段升序查询第一条数据
    Count getCountByDateDescOrLimitOne();
    //添加
    int addCountByDate(Count count);

    //修改
    int updateCountById(Count count);

}
