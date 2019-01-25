package com.charlie.gwtmodel.server;

import com.charlie.gwtmodel.client.GreetingService;
import com.charlie.gwtmodel.shared.FieldVerifier;
import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.charlie.gwtmodel.utils.JerseyClient;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	//get
	public Users getUsers() {
		JerseyClient jc = new JerseyClient();
		return jc.getUsers();     
	}
	
	public Users getUsersCompetition() {
		JerseyClient jc = new JerseyClient();
		return jc.getUsersCompetition();     
	}
	//post
	public void createUsers(Users users) {
		JerseyClient jc = new JerseyClient();
		jc.createUsers(users);
	}
	//put
	public User updateUser(int id, User user) {
		JerseyClient jc = new JerseyClient();
		return jc.updateUser(id, user);
	}
	//delete
	public int deleteUser(int id) {
		JerseyClient jc = new JerseyClient();
		return jc.deleteUser(id);
	}
	//get
	public Users getUsersByCountry(String country) {
		JerseyClient jc = new JerseyClient();
		return jc.getUsersByCountry(country);     
	}
	//get
	public Users getUsersByName(String name) {
		JerseyClient jc = new JerseyClient();
		return jc.getUsersByName(name);     
	}
	public Users getUsersSearch() {
		JerseyClient jc = new JerseyClient();
		return jc.getUsersSearch();     
	}
	public Boolean sync() {
		JerseyClient jc = new JerseyClient();
		return jc.sync();
	}
}
