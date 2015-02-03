package com.mulesoft.training;

public class FlightRequest implements java.io.Serializable {

	String destination;

	public FlightRequest()	{
	}

	public FlightRequest(String destination)	{
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
