package library.management.system.librarymanagement;

import library.management.system.librarymanagement.model.Address;
import library.management.system.librarymanagement.model.Role;
import library.management.system.librarymanagement.model.User;
import library.management.system.librarymanagement.repository.RoleRepository;
import library.management.system.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LibraryManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        List<User> userList = userRepository.findUsersByUserIdIsNotNull();

        if (userList.size() == 0) {
            {
                List<Role> roleList = new ArrayList<>();
                Role role = new Role(1, "ADMIN");
                roleRepository.save(role);
                roleList.add(role);

                Address address = new Address("Progoti Soroni", "Dhaka", "1230", "Bangladesh",
                        "john@gmail.com", "01546544");

                User user = new User("A2019N1", "John Doe",
                        new BCryptPasswordEncoder().encode("a"),
                        0, "All Department", address,
                        roleList);
                userRepository.save(user);

            }
        }
    }
}
