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
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class GwtSearch  extends Composite {
	private static GwtSearchUiBinder uiBinder = GWT.create(GwtSearchUiBinder.class);

	/*
	 * @UiTemplate is not mandatory but allows multiple XML templates
	 * to be used for the same widget. 
	 * Default file loaded will be <class-name>.ui.xml
	 */

	@UiTemplate("GwtSearch.ui.xml")
	interface GwtSearchUiBinder extends UiBinder<Widget, GwtSearch> {
	}

	@UiField
	PopupPanel popup2;

	@UiField
	VerticalPanel verticalp;

	SuggestBox suggest;
	
	@UiField
	SuggestBox suggest2;

	@UiField
	VerticalPanel celltable2;

	@UiField
	Button search2;

	@UiField
	Button cancel2;  	   


	/**
	 * The list of data to display.
	 */

	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();  

	public GwtSearch() {
		initWidget(uiBinder.createAndBindUi(this));
		//create the suggestion data 	
		loadData();

		//create the suggestion box and pass it the data created above
		suggest = new SuggestBox(oracle);

		//set width to 200px.
		suggest.setWidth("200");
		suggest.setVisible(true);
		int index = suggest2.getTabIndex();
		verticalp.remove(suggest2);
		verticalp.insert(suggest,index+1);
		search2.setVisible(true);
		celltable2.setVisible(true);
		popup2.show();
	}




	private void loadData() {
		MyAsyncCallback<Users> call = new MyAsyncCallback<Users>("getUsers2",new AsyncCallback<Users>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("OnFailure");
			}

			@Override
			public void onSuccess(Users result) {
				oracle.clear();
				for(User user: result.getUsers()) {
					oracle.add(user.getCountry());
				}
			}
		}) {

			@Override
			public void run() {
				GreetingServiceFactory.createGreetingService().getUsers(this.getCallback());
			}

		};
		call.run();
	}

	private static List<User> CONTACTS;
	CellTable<User> table2 = new CellTable<User>();
	// Add a selection model to handle user selection.
	final SingleSelectionModel<User> selectionModel 
	= new SingleSelectionModel<User>();

	private void setUsers(Users users) {
		CONTACTS = users.getUsers();
		// Set the total row count. This isn't strictly necessary,
		// but it affects paging calculations, so its good habit to
		// keep the row count up to date.
		if (CONTACTS == null || CONTACTS.isEmpty()) {
			table2.setRowCount(0, true);
			table2.setRowData(0, null);

		}else {
			table2.setRowCount(CONTACTS.size(), true);

			// Push the data into the widget.
			table2.setRowData(0, CONTACTS);

		}
	}
	Boolean done = false;
	@UiHandler("search2")
	void doClickAdd(ClickEvent event) {
		search(suggest.getText());
		// Create a CellTable.
		if(!done) {
		table2.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		TextColumn<User> idColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return ""+object.getId();
			}
		};
		table2.addColumn(idColumn, "id");

		TextColumn<User> nameColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getName();
			}
		};
		table2.addColumn(nameColumn, "name");

		TextColumn<User> lastNameColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getLastName();
			}
		};
		table2.addColumn(lastNameColumn, "last name");

		TextColumn<User> nickNameColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getNickname();
			}
		};
		table2.addColumn(nickNameColumn, "nickname");

		TextColumn<User> passwordColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getPassword();
			}
		};
		table2.addColumn(passwordColumn, "password");

		TextColumn<User> emailColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getEmail();
			}
		};
		table2.addColumn(emailColumn, "email");

		TextColumn<User> countryColumn = 
				new TextColumn<User>() {
			@Override
			public String getValue(User object) {
				return object.getCountry();
			}
		};
		table2.addColumn(countryColumn, "country");


		table2.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(
				new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						User selected = selectionModel.getSelectedObject();
						if (selected != null) {
						}
					}
				});
		done = true;
		}
		VerticalPanel panel = new VerticalPanel();
		panel.setBorderWidth(1);	    
		panel.setWidth("400");
		panel.add(table2);
		int i = celltable2.getWidgetCount();
		if (i == 0) {
			celltable2.add(panel);
		}else {
			celltable2.remove(0);
			celltable2.insert(panel,0);
		}

		//Window.alert("add");

	}
	private void search(final String country) {
		MyAsyncCallback<Users> call = new MyAsyncCallback<Users>("search",new AsyncCallback<Users>() {

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
				GreetingServiceFactory.createGreetingService().getUsersByCountry(country, this.getCallback());

			}

		};
		call.run();
	}


	@UiHandler("cancel2")
	void doClickCancel(ClickEvent event) {
		popup2.hide();
		this.removeFromParent();
	}

}
