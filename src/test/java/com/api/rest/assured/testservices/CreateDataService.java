package com.api.rest.assured.testservices;

import java.io.IOException;

import com.api.rest.assured.constants.Endpoints;
import com.api.rest.assured.model.CreateRequest;
import com.api.rest.assured.model.CreateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateDataService extends JsonFilesPath {
	
	ObjectMapper MAPPER = new ObjectMapper();
	CreateResponse createResponse = new CreateResponse();
	
	public CreateResponse createUser() throws IOException {

		CreateRequest createData = MAPPER.readValue(CreateDataFile, CreateRequest.class);
		
		
		Response response = RestAssured.given()
				
				
				.contentType(ContentType.JSON)
				.body(createData)
				.log().all()
				.post(Endpoints.POST_DATA);


		String  postRules =response.getBody().asString();

		createResponse = MAPPER.readValue(postRules, CreateResponse.class);

		
		
		return  createResponse;

}

}
