package com.mulesoft.training.custom.transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.mulesoft.training.Flight;

public class FlightSorter implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		MuleMessage message = eventContext.getMessage();
		
		if(message.getPayload() instanceof ArrayList){
			List<Flight> payload = (ArrayList<Flight>) message.getPayload();
			Collections.sort(payload);
			message.setPayload(payload);
			return message;
		}		
		return message;
	}

}
