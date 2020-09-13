package softuni.fundexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.fundexamprep.model.binding.UserLoginBindingModel;
import softuni.fundexamprep.model.entity.User;
import softuni.fundexamprep.model.service.UserServiceModel;
import softuni.fundexamprep.repository.UserRepository;
import softuni.fundexamprep.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository.getUserByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
