package service.provider;

import pojo.Provider;

import java.util.List;

public interface ProviderService {

    List<Provider> qureProviderAll(String queryProCode, String queryProName);

    int addProvider(Provider provider);
}
