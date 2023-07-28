package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification spec_req; //this is mandate,changing this to static so that multiple tcs can use the same object 'spec_req' for all the defined tcs.

	public RequestSpecification requestSpecification() throws IOException
	

	
	{
		if(spec_req==null) // this is added because in the Logging.text while running 2 sets of data for a tc, only the second one was getting logged in
		{

		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt")); // this is used to log in all the request and response into the file named Logging.txt
		 spec_req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)) //addFilter() is used here which takes arguments of the PrintStream class
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		 return spec_req;
		}
		return spec_req;
		
	}
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\SeleniumTrainings\\APIAutomation\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		//prop.getProperty("baseUrl");
		return prop.getProperty(key);
		//return prop;
		/* We can use getGlobalValue() to get any key other than "baseUrl from the properties file, So instead of hardcoding, 
		 the method should be able to accept any form of key from the properties file. hence adding argument to this method as String key and also changing from baseUrl to key */
	}
	
	public String getJsonPath(Response response, String key) //jsonPath will actually accept response first so adding response argument first
	{
		
		
		String resp = response.asString();
		 JsonPath js = new JsonPath(resp);
		 return js.get(key).toString();
		 
	}

}
