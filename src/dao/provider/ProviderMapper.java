package dao.provider;

import org.apache.ibatis.annotations.Param;
import pojo.Provider;

import java.util.List;

public interface ProviderMapper {

    List<Provider> qureProviderAll(@Param("proCode") String queryProCode,
                                   @Param("proName") String queryProName);

    int addProvider(Provider provider);
}
