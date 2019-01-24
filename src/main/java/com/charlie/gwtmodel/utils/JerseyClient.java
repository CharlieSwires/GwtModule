package com.charlie.gwtmodel.utils;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

public class JerseyClient {

	//get
	public Users getUsers() {
		Client client = ClientBuilder.newClient( new ClientConfig().register(GensonJsonConverter.class) );
		WebTarget webTarget = client.target("http://localhost:8080/Model").path("users");
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		 
		Users employees = response.readEntity(Users.class);
		return employees;     
	}
	//post
	public void createUsers(Users users) {
		Client client = ClientBuilder.newClient( new ClientConfig().register(GensonJsonConverter.class) );
		WebTarget webTarget = client.target("http://localhost:8080/Model").path("users");
		 
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(users, MediaType.APPLICATION_JSON));
		 	
	}
	//put
	public User updateUser(int id, User user) {
		Client client = ClientBuilder.newClient( new ClientConfig().register(GensonJsonConverter.class) );
		WebTarget webTarget = client.target("http://localhost:8080/Model").path("users").path(""+id);
		 
		user.setId(id);		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));
		 
		User employee = response.readEntity(User.class);
		return employee;
	}
	//delete
	public int deleteUser(int id) {
		Client client = ClientBuilder.newClient( new ClientConfig().register(GensonJsonConverter.class));
		WebTarget webTarget = client.target("http://localhost:8080/Model").path("users").path(""+id);
		 
		Invocation.Builder invocationBuilder =  webTarget.request();
		Response response = invocationBuilder.delete();
		return response.getStatus();
	}
	public Users getUsersByCountry(String country) {
		Client client = ClientBuilder.newClient( new ClientConfig().register(GensonJsonConverter.class) );
		WebTarget webTarget = client.target("http://localhost:8080/Model").path("users/country").path(country);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		 
		Users employees = response.readEntity(Users.class);
		return employees;     
	}
}
