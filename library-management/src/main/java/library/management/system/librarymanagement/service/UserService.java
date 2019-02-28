package library.management.system.librarymanagement.service;

import library.management.system.librarymanagement.exception.ResourceNotFoundException;
import library.management.system.librarymanagement.model.Role;
import library.management.system.librarymanagement.model.User;
import library.management.system.librarymanagement.repository.RoleRepository;
import library.management.system.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(User user, String role){

        List<Role> roleList = new ArrayList<>();
        Optional<Role> optionalRole = roleRepository.findByRole(role);

        Optional<User> optionalUser = userRepository.findUserByUserId(user.getUserId());

        if (!optionalRole.isPresent() && optionalUser.isPresent()){
            return null;
        }
        else {
            roleList.add(optionalRole.get());
            user.setRoles(roleList);
            String pass = new BCryptPasswordEncoder().encode(user.getUserPassword());
            user.setUserPassword(pass);
            return userRepository.save(user);
        }

    }


    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(String userId){
        Optional<User> optionalUser = userRepository.findUserByUserId(userId);
            if (optionalUser.isPresent()){
                 return optionalUser.get();
            }
            else {
                throw new ResourceNotFoundException("user id "+userId +" is not present");
            }
    }

    public User editUser(User user, String userId){
        Optional<User> optionalUser = userRepository.findUserByUserId(userId);
        if (optionalUser.isPresent()){
            user.setUserId(userId);
            return userRepository.save(user);
        }
        else {
            throw new ResourceNotFoundException("user id "+userId+" does not exist in table");
        }
    }

    public void removeUser(String userId){
        Optional<User> optionalUser = userRepository.findUserByUserId(userId);
        if (optionalUser.isPresent()){
            userRepository.deleteByUserId(userId);
        }
        else {
            throw new ResourceNotFoundException("user id "+userId+" does not exist in table");
        }
    }
}
