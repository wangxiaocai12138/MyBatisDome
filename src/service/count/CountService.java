package service.count;

import pojo.Count;

import java.util.List;

public interface CountService {
    //以时间字段升序查询第一条数据
    List<Count> getCountByDateDescOrLimitOne();

    int addCountByDate(Count count);

    int updateCountById(Count count);
}
