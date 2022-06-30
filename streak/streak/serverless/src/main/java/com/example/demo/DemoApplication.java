package com.example.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Map;

// Handler value: example.Handler
public class DemoApplication implements RequestHandler<Map<String,String>, String>{

	@Override
	public String handleRequest(Map<String,String> event, Context context)
	{
		LambdaLogger logger = context.getLogger();
		String response = "200 OK";
		// log execution details
		logger.log("######Some Stuff");
		logger.log("EVENT TYPE: " + event.getClass());
		return response;
	}
}