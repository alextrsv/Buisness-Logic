package buisnesslogic.Services.ServicesImpl;


import buisnesslogic.Repositories.RoleRepository;
import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.Services.UserService;
import buisnesslogic.model.entity.Role;
import buisnesslogic.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        Role role = roleRepository.findById(3).get();
        user.setRoles(Set.of(role));

        return userRepository.save(user);
    }
}
