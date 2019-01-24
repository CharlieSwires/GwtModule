package com.charlie.gwtmodel.client;

import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	//get
	public Users getUsers() throws IllegalArgumentException; 
	//post
	public void createUsers(Users users) throws IllegalArgumentException; 
	//put
	public User updateUser(int id, User user) throws IllegalArgumentException;
	//delete
	public int deleteUser(int id) throws IllegalArgumentException;
	//
	public Users getUsersByCountry(String country) throws IllegalArgumentException;
}