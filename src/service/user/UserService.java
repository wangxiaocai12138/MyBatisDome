package service.user;

import pojo.User;

public interface UserService {
    User loginByCodeAndPwd(User user);
}
