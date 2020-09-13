package softuni.fundexamprep.service;

import softuni.fundexamprep.model.binding.UserLoginBindingModel;
import softuni.fundexamprep.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel findByUsername(String username);


    UserServiceModel registerUser(UserServiceModel userServiceModel);
}
