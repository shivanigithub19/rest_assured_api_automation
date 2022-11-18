package com.api.rest.assured.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import io.restassured.RestAssured;

public class TestBase extends ExtentReportListener {

	public Logger logger;

	@BeforeClass
	public void setup() throws IOException {

		
		logger = LoggerFactory.getLogger("API Automation - Rest Assured");
		
		logger.debug("TestSuite Execution Started!");
		
		
				 
		Properties pro;
		File src = new File("config.properties");
		FileInputStream fis = new FileInputStream(src);
		pro = new Properties();
		pro.load(fis);
		RestAssured.baseURI = pro.getProperty("baseUrl");
		return;
		
		
		

	}
	
	

	}
