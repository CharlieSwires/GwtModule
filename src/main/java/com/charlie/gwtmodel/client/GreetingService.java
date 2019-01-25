package com.charlie.gwtmodel.client;

import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	public Users getUsersCompetition() throws IllegalArgumentException;
	public Users getUsersByName(String name) throws IllegalArgumentException;
	public Users getUsersSearch() throws IllegalArgumentException;
	public Boolean sync() throws IllegalArgumentException;

}