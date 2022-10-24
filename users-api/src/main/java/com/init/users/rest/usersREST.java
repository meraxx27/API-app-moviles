package com.init.users.rest;

import org.springframework.web.bind.annotation.*;

import com.init.users.dao.UserDao;
import com.init.users.entity.userEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/user")
public class usersREST {
	

	@Autowired
	@Lazy
	private UserDao UserDAO;
	
	@RequestMapping(value="Registro", method=RequestMethod.POST)
	public Boolean registerUser(@RequestBody  userEntity user ) {
        userEntity res = UserDAO.findUserByEmail(user.getEmail());
        System.out.println(res);
        if (res == null) {
            System.out.println("Entered here");
            userEntity userSaved = UserDAO.save(user);
            ResponseEntity.ok(userSaved);
            return true;
        } else {
            System.out.println("Entered else here");
            return false;
        }
    }		
	@RequestMapping(value="deleteUser", method=RequestMethod.POST)
	public String deleteUser(@RequestBody userEntity user) {
        userEntity res = UserDAO.findUserByEmail(user.getEmail());
        UserDAO.deleteById(res.getId());
        return "El usuario fue eliminado";
    }
    
	@RequestMapping(value="checkUser", method=RequestMethod.POST)
	 public Boolean checkUser( @RequestBody userEntity user){
        Optional<userEntity> res = UserDAO.findUser(user.getEmail(), user.getPassword());
        System.out.print(res);
        if(res.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
	
	@RequestMapping(value="fetchUsers", method=RequestMethod.GET)
	public ResponseEntity<List<userEntity>> fetchUsers(){
		List<userEntity> users = UserDAO.findAll();
		return ResponseEntity.ok(users);
	}
	
}
