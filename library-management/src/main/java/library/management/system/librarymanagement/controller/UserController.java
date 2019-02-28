package library.management.system.librarymanagement.controller;

import library.management.system.librarymanagement.model.User;
import library.management.system.librarymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("save/{role}")
    public ResponseEntity<User> saveUser(@RequestBody User user,
                                             @PathVariable String role) {
        try {
            userService.saveUser(user, role);
            ResponseEntity<User> userResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(user);
            return userResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("all")
    public Iterable<User> getAllUser() {
        return userService.getAllUser();
    }


    @GetMapping("{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        try {
            User user = userService.getUser(userId);
            ResponseEntity userResponseEntity = ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(user);
            return userResponseEntity;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("update_user/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @PathVariable String userId){
        userService.editUser(user, userId);
        try {
            ResponseEntity<User> userResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(user);
            return userResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("delete_user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        userService.removeUser(userId);
        try {
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Deleted");
            return responseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
