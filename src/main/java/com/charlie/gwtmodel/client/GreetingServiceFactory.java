package com.charlie.gwtmodel.client;

import com.google.gwt.core.client.GWT;

public class GreetingServiceFactory {
	  private static GreetingServiceAsync exampleService;

	  public static GreetingServiceAsync createGreetingService() {
	    if (exampleService == null) {
	      exampleService = (GreetingServiceAsync) GWT.create(GreetingService.class);
	    }
	    return exampleService;
	  }
}
