package com.charlie.gwtmodel.client;

import java.util.ArrayList;
import java.util.List;

import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GwtUser extends Composite {
	private static GwtUserUiBinder uiBinder = GWT.create(GwtUserUiBinder.class);

	/*
	 * @UiTemplate is not mandatory but allows multiple XML templates
	 * to be used for the same widget. 
	 * Default file loaded will be <class-name>.ui.xml
	 */

	@UiTemplate("GwtUser.ui.xml")
	interface GwtUserUiBinder extends UiBinder<Widget, GwtUser> {
	}

	@UiField
	PopupPanel popup;
	@UiField
	TextBox name;
	@UiField
	TextBox lastname;
	@UiField
	TextBox password;
	@UiField
	TextBox email;
	@UiField
	TextBox nickname;
	@UiField
	TextBox country;
	
	@UiField
	Button add2;

	@UiField
	Button update2;  	   
	@UiField
	Button cancel;  	   


	/**
	 * The list of data to display.
	 */

	int id;
	public GwtUser(User user) {
		initWidget(uiBinder.createAndBindUi(this));
		if (user != null) {
			add2.setVisible(false);
			update2.setVisible(true);
			id = user.getId();
			name.setText(user.getName());
			lastname.setText(user.getLastName());
			password.setText(user.getPassword());
			email.setText(user.getEmail());
			nickname.setText(user.getNickname());
			country.setText(user.getCountry());
		}else {
			add2.setVisible(true);
			update2.setVisible(false);
			
		}
		popup.show();
	}



	
	@UiHandler("add2")
	void doClickAdd(ClickEvent event) {

		User user = new User();
		user.setName(name.getText());
		user.setLastName(lastname.getText());
		user.setPassword(password.getText());
		user.setEmail(email.getText());
		user.setNickname(nickname.getText());
		user.setCountry(country.getText());
		user.setId(1);
		addUser(user);
		
		//Window.alert("add");

	}
	private void addUser(final User user) {
		MyAsyncCallback<Users> call = new MyAsyncCallback<Users>("addUser",new AsyncCallback<Users>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("OnFailure");
			}

			@Override
			public void onSuccess(Users result) {
				//Window.alert("OnSuccess");
				popup.hide();

			}
		}) {

			@Override
			public void run() {
				List<User> us = new ArrayList<User>();
				us.add(user);
				Users users = new Users();
				users.setUsers(us);;
				GreetingServiceFactory.createGreetingService().createUsers(users, this.getCallback());

			}

		};
		call.run();
	}


	@UiHandler("cancel")
	void doClickCancel(ClickEvent event) {
		popup.hide();
		this.removeFromParent();
	}

	@UiHandler("update2")
	void doClickUpdate(ClickEvent event) {
		
		User user = new User();
		user.setName(name.getText());
		user.setLastName(lastname.getText());
		user.setPassword(password.getText());
		user.setEmail(email.getText());
		user.setNickname(nickname.getText());
		user.setCountry(country.getText());
		
		updateUser(id,user);

		//Window.alert("update");

	}


	private void updateUser(final int id, final User user) {
		MyAsyncCallback<User> call = new MyAsyncCallback<User>("updateUser",new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("OnFailure");
			}

			@Override
			public void onSuccess(User result) {
				//Window.alert("OnSuccess");
				popup.hide();

			}
		}) {

			@Override
			public void run() {
				GreetingServiceFactory.createGreetingService().updateUser(id,user, this.getCallback());

			}

		};
		call.run();
	}
}
