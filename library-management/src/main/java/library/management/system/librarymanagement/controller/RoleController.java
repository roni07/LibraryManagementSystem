package library.management.system.librarymanagement.controller;

import library.management.system.librarymanagement.exception.ResourceNotFoundException;
import library.management.system.librarymanagement.model.Role;
import library.management.system.librarymanagement.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("role")
public class RoleController {

    private RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping("save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        roleRepository.save(role);
        try {
            ResponseEntity<Role> roleResponseEntity;
            roleResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(role);
            return roleResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("read_roles")
    public ResponseEntity<Role> readRole(){
        try {
            ResponseEntity responseEntity = ResponseEntity
                .status(HttpStatus.FOUND)
                .body(roleRepository.findAll());
            return responseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("update_role/{roleName}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role,
                                           @PathVariable String roleName){
        Optional<Role> optionalRole = roleRepository.findByRole(roleName);
        if (optionalRole.isPresent()){
            role.setRole(roleName);
            roleRepository.save(role);
        }
        else {
            throw new ResourceNotFoundException(roleName +
                    "role does not exist in the table");
        }
        try {
            ResponseEntity<Role> roleResponseEntity = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(role);
            return roleResponseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("delete_role/{roleName}")
    public ResponseEntity<?> deleteRole(@PathVariable String roleName){
        Optional<Role> optionalRole = roleRepository.findByRole(roleName);
        if (optionalRole.isPresent()){
            roleRepository.deleteByRole(roleName);
        }
        else {
            throw new ResourceNotFoundException(roleName +
                    "role does not exist in table");
        }
        try {
            ResponseEntity responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("deleted");
            return responseEntity;
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
