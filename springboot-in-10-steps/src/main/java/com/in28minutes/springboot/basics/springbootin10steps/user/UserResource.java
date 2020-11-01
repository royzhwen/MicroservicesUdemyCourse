package com.in28minutes.springboot.basics.springbootin10steps.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//we are exposing this class as a resource even though it's also a controller
@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		List<User> allUsers = service.finalAll();
		if (allUsers == null)
			throw new UserNotFoundException("Null users found");
		return allUsers;
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id)
	{
		//We will get 200 response code when we should not if there is no error handling
		User user = service.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id-"+ id);
		
		//"all-users", SERVERPATH + "/users"
		//retrieveAllUsers
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		 
		//HATEOAS - Hypermedia as the Engine of Application State
		
		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		User user = service.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("id-"+ id);
	}
	
	// input - details of user
	// output - CREATED & return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) 
	{
		if (user == null)
			throw new UserNotFoundException("Invalid user input");
		try
		{
			User savedUser = service.save(user);
			
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		catch (NullPointerException e)
		{
			throw new UserServiceException(e);
		}
		
	}
}
