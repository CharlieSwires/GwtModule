package com.charlie.gwtmodel.client;

import java.util.List;

import com.charlie.gwtmodel.shared.User;
import com.charlie.gwtmodel.shared.Users;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class GwtModelFirst extends Composite {
	private static GwtModelFirstUiBinder uiBinder = GWT.create(GwtModelFirstUiBinder.class);

	/*
	 * @UiTemplate is not mandatory but allows multiple XML templates
	 * to be used for the same widget. 
	 * Default file loaded will be <class-name>.ui.xml
	 */

	@UiTemplate("GwtModel.ui.xml")
	interface GwtModelFirstUiBinder extends UiBinder<Widget, GwtModelFirst> {
	}

	
	@UiField
	Button refresh;
	

	@UiField
	Button add;

	@UiField
	Button update;  	   

	@UiField
	Button delete;  

	@UiField
	VerticalPanel celltable;

	@UiField
	Button search1;

	/**
	 * The list of data to display.
	 */

	private static List<User> CONTACTS;
	CellTable<User> table = new CellTable<User>();
	// Add a selection model to handle user selection.
	final SingleSelectionModel<User> selectionModel 
	= new SingleSelectionModel<User>();

	private void setUsers(Users users) {
		CONTACTS = users.getUsers();
		// Set the total row count. This isn't strictly necessary,
		// but it affects paging calculations, so its good habit to
		// keep the row count up to date.
		if (CONTACTS == null || CONTACTS.isEmpty()) {
			table.setRowCount(0, true);
			table.setRowData(0, null);

		}else {
			table.setRowCount(CONTACTS.size(), true);

			// Push the data into the widget.
			table.setRowData(0, CONTACTS);

		}
	}
	public GwtModelFirst() {
		initWidget(uiBinder.createAndBindUi(this));
		getUsers();
		refresh.setVisible(true);
		search1.setVisible(true);
		add.setVisible(true);
		update.setVisible(false);
		delete.setVisible(false);
		// Create a CellTable.
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<User> idColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return ""+object.getId();
			}
		};
		table.addColumn(idColumn, "id");

		TextColumn<User> nameColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getName();
			}
		};
		table.addColumn(nameColumn, "name");

		TextColumn<User> lastNameColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getLastName();
			}
		};
		table.addColumn(lastNameColumn, "last name");

		TextColumn<User> nickNameColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getNickname();
			}
		};
		table.addColumn(nickNameColumn, "nickname");

		TextColumn<User> passwordColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getPassword();
			}
		};
		table.addColumn(passwordColumn, "password");

		TextColumn<User> emailColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getEmail();
			}
		};
		table.addColumn(emailColumn, "email");

		TextColumn<User> countryColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getCountry();
			}
		};
		table.addColumn(countryColumn, "country");


		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(
				new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						User selected = selectionModel.getSelectedObject();
						if (selected != null) {
							refresh.setVisible(true);
							search1.setVisible(false);
							add.setVisible(false);
							update.setVisible(true);
							delete.setVisible(true);
						}
					}
				});


		VerticalPanel panel = new VerticalPanel();
		panel.setBorderWidth(1);	    
		panel.setWidth("400");
		panel.add(table);
		celltable.add(panel);
	}

	@UiHandler("refresh")
	void doClickRefresh(ClickEvent event) {
		getUsers();
		selectionModel.clear();
		refresh.setVisible(true);
		search1.setVisible(true);
		add.setVisible(true);
		update.setVisible(false);
		delete.setVisible(false);
		//Window.alert("refresh");

	}
	
	GwtSearch gwtSearch;
	@UiHandler("search1")
	void doClickSearch1(ClickEvent event) {
		gwtSearch = new GwtSearch();

	}
	
	GwtUser gwtUser;
	@UiHandler("add")
	void doClickAdd(ClickEvent event) {
		gwtUser = new GwtUser(null);
		//Window.alert("add");

	}
	@UiHandler("update")
	void doClickUpdate(ClickEvent event) {
		gwtUser = new GwtUser(selectionModel.getSelectedObject());

		//Window.alert("update");

	}
	@UiHandler("delete")
	void doClickDelete(ClickEvent event) {
		User selected = selectionModel.getSelectedObject();
		if (selected != null) {
			deleteUser(selected.getId());
			selectionModel.clear();
			refresh.setVisible(true);
			add.setVisible(true);
			update.setVisible(false);
			delete.setVisible(false);
		}

		//Window.alert("delete");

	}


	private void deleteUser(final int id) {
		MyAsyncCallback<Integer> call = new MyAsyncCallback<Integer>("deleteUser",new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("OnFailure");
			}

			@Override
			public void onSuccess(Integer result) {
				getUsers();
				table.redraw();

			}
		}) {

			@Override
			public void run() {
				GreetingServiceFactory.createGreetingService().deleteUser(id,this.getCallback());

			}

		};
		call.run();
	}
	
	private void getUsers() {
		MyAsyncCallback<Users> call = new MyAsyncCallback<Users>("getUsers",new AsyncCallback<Users>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("OnFailure");
			}

			@Override
			public void onSuccess(Users result) {
				setUsers(result);

			}
		}) {

			@Override
			public void run() {
				GreetingServiceFactory.createGreetingService().getUsers(this.getCallback());

			}

		};
		call.run();
	}


}
