package com.charlie.gwtmodel.client;

import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	//get
	void getUsers( AsyncCallback<Users> callback)  throws IllegalArgumentException;
	//post
	@SuppressWarnings("rawtypes")
	void createUsers(Users users, AsyncCallback callback)  throws IllegalArgumentException;
	//put
	void updateUser(int id, User user, AsyncCallback<User> callback)  throws IllegalArgumentException;
	//delete
	void deleteUser(int id, AsyncCallback<Integer> callback) throws IllegalArgumentException;

	void getUsersByCountry(String country, AsyncCallback callback) throws IllegalArgumentException;

	void getUsersCompetition(AsyncCallback callback) throws IllegalArgumentException;

	void getUsersByName(String name, AsyncCallback callback) throws IllegalArgumentException;

	void getUsersSearch(AsyncCallback callback) throws IllegalArgumentException;

	void sync(AsyncCallback<Boolean> callback) throws IllegalArgumentException;
}