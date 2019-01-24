package com.charlie.gwtmodel.client;

import com.charlie.gwtmodel.shared.Users;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyAsyncCallback<T> implements AsyncCallback<T> {
	String fred;
	AsyncCallback george;
	public MyAsyncCallback(String string, AsyncCallback<T> asyncCallback) {
		fred = string;
		george = asyncCallback;
	}

	public AsyncCallback getCallback() {
		return george;
	}
	public void run() {}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(T result) {
		// TODO Auto-generated method stub
		
	}

}
