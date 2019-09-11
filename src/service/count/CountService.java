package service.count;

import pojo.Count;

public interface CountService {
    //以时间字段升序查询第一条数据
    Count getCountByDateDescOrLimitOne();

    int addCountByDate(Count count);
}
