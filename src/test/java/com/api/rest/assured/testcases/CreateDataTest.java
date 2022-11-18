package com.api.rest.assured.testcases;

import java.io.IOException;
import java.sql.SQLException;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.rest.assured.model.CreateResponse;
import com.api.rest.assured.model.GetData;
import com.api.rest.assured.testservices.CreateDataService;
import com.api.rest.assured.testservices.GetDataService;
import com.api.rest.assured.utils.ExtentReportListener;
import com.api.rest.assured.utils.TestBase;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



@Listeners(ExtentReportListener.class)
public class CreateDataTest extends TestBase {
	
	CreateDataService createData = new CreateDataService();
	CreateResponse createResponse = new CreateResponse();


@Test(description="create single user ")
	
	public void testCreateUser() throws IOException, SQLException {

		logger.info("********* Started TC001_create single user **********");
		
		SoftAssertions softAssert = new SoftAssertions();
		createResponse = createData.createUser();
		
		softAssert.assertThat(createResponse.getId()).isNotNull();
		
		softAssert.assertAll();
		
		
		

				//response in extent reports
				extentTest.log(Status.INFO,MarkupHelper.createLabel("RESPONSE :", ExtentColor.BLUE));
				extentTest.info(MarkupHelper.createJsonCodeBlock(createResponse));
	}



}
