package tech.escalab.userapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.escalab.userapi.model.dto.UserRequest;
import tech.escalab.userapi.model.entity.User;
import tech.escalab.userapi.service.impl.UserServiceImpl;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController  {

    UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    //GET localhost:8080/users/
    @GetMapping("/")
    @Operation(summary = "Obtiene todos los users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "204", description = "NO CONTENT", content = {
                    @Content(schema = @Schema(implementation = User.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = {
                    @Content(schema = @Schema())
            })
    })

    public ResponseEntity<List<UserRequest>> getUsers(){

        List<UserRequest> users = userService.getUsers();

        if(users.size() > 0){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //GET localhost:8080/users/3
    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUser(@PathVariable("id") UUID userId){

        UserRequest user = userService.getUser(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //post localhost:8080/users/
    @PostMapping("/")
    public ResponseEntity<?> insertUser (@Valid @RequestBody UserRequest userRequest){

        UserRequest user = userService.insertUser(userRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //PUT localhost:8080/users/
    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User userRequest){

        User user = userService.updateUser(userRequest);
        if(user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userRequest, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRequest> deleteUser(@PathVariable("id") UUID userId){

        UserRequest user = userService.getUser(userId);

        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.deleteUser(userId);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED) ;
    }

    //localhost:8080/users/?name=Docker
    @GetMapping(path = "/", params = {"name"})
    public ResponseEntity<UserRequest> getUserByName(@RequestParam String name){
        UserRequest user = userService.getUserByName(name);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //localhost:8080/users/phone/?phoneNumber=Docker
    @GetMapping(path = "/phone", params = {"phoneNumber"})
    public ResponseEntity<UserRequest> getUserByPhone(@RequestParam Integer phoneNumber){
        UserRequest user = userService.getUserByPhone(phoneNumber);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
