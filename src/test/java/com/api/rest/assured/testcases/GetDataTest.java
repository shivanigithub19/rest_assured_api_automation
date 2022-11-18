package com.api.rest.assured.testcases;

import java.io.IOException;
import java.sql.SQLException;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.rest.assured.model.GetData;
import com.api.rest.assured.testservices.GetDataService;
import com.api.rest.assured.utils.ExtentReportListener;
import com.api.rest.assured.utils.TestBase;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;



@Listeners(ExtentReportListener.class)
public class GetDataTest extends TestBase {
	
	GetDataService getData = new GetDataService();
	


@Test(description="Retrieve single user details")
	
	public void testGetDataForSingleUser() throws IOException, SQLException {

		logger.info("********* Started TC001_Retrieve single user details **********");
		
		SoftAssertions softAssert = new SoftAssertions();
		GetData response =getData.getDataForSingleUser() ;
		
		softAssert.assertThat(response.getData().getId()).isNotNull();
		
		softAssert.assertAll();
		
		
		

				//response in extent reports
				extentTest.log(Status.INFO,MarkupHelper.createLabel("RESPONSE :", ExtentColor.BLUE));
				extentTest.info(MarkupHelper.createJsonCodeBlock(response));
	}



}
