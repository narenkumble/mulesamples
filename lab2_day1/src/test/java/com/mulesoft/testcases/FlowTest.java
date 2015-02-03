package com.mulesoft.testcases;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;

public class FlowTest extends FunctionalTestCase {

	@Test
	public void testUnited() throws Exception{
		MuleClient client = muleContext.getClient();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("http.method", "GET");
		
		MuleMessage response = client.send("http://localhost:8083/united/SFO", "", headers, 10000);
		
		String flowResponse = response.getPayloadAsString();
		
		Assert.assertEquals("{\"flights\":[{\"airlineName\":\"United\",\"price\":400,\"departureDate\":\"2015/03/20\",\"planeType\":\"Boeing 737\",\"origin\":\"MUA\",\"emptySeats\":0,\"code\":\"ER38sd\",\"destination\":\"SFO\"},{\"airlineName\":\"United\",\"price\":945,\"departureDate\":\"2015/09/11\",\"planeType\":\"Boeing 757\",\"origin\":\"MUA\",\"emptySeats\":54,\"code\":\"ER39rk\",\"destination\":\"SFO\"},{\"airlineName\":\"United\",\"price\":954,\"departureDate\":\"2015/02/12\",\"planeType\":\"Boeing 777\",\"origin\":\"MUA\",\"emptySeats\":23,\"code\":\"ER39rj\",\"destination\":\"SFO\"}]}", flowResponse);
		Assert.assertTrue(flowResponse.contains("United"));
		Assert.assertFalse(flowResponse.contains("fault"));
	}
	
	@Test
	public void testDelta() throws Exception{
		MuleClient client = muleContext.getClient();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("http.method", "GET");

		String url = "http://localhost:8082/delta?destination=SFO";
//		MuleMessage response = client.send(url, "", headers, 10000);
		
		client.dispatch(url, "", headers);
		MuleMessage response = client.request(url, 10000);
		
		String flowResponse = response.getPayloadAsString();
		Assert.assertTrue(flowResponse.contains("Delta"));
		Assert.assertFalse(flowResponse.contains("fault"));
	}
	
	
	@Test
	public void testAmerican() throws Exception{
		MuleClient client = muleContext.getClient();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("http.method", "GET");

		String url = "http://localhost:8084/american";
		MuleMessage response = client.send(url, "", headers, 10000);		
//		client.dispatch(url, "", headers);
//		MuleMessage response = client.request(url, 10000);
		
		String flowResponse = response.getPayloadAsString();
		Assert.assertTrue(flowResponse.contains("American"));
		Assert.assertFalse(flowResponse.contains("fault"));
	}
	
	protected String getConfigResources(){
		return "src/main/app/lab2_day1.xml";
	}
}
