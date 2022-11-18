package com.api.rest.assured.testservices;

import java.io.IOException;

import com.api.rest.assured.constants.Endpoints;
import com.api.rest.assured.model.GetData;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetDataService {
	
	
	ObjectMapper MAPPER = new ObjectMapper();
	
	
	public GetData getDataForSingleUser() throws IOException {


		Response response  = RestAssured.given()
				.contentType(ContentType.JSON)
				.log().all()
				.get(Endpoints.GET_DATA);
				


		String  getAsString =response.getBody().asString();

		GetData getData = MAPPER.readValue(getAsString, GetData.class);
		return getData;
	}
	
	}
