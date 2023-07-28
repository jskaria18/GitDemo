package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import POJO.Serialization_GoogleMaps;
import POJO.Serialization_Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils /*
						 * StepDefinition class inherits the properties of Utils class here, so method
						 * requestSpecification() of the parent class Utils is called here directly
						 */

{

	RequestSpecification res;
	ResponseSpecification spec_res;
	Response response;
	static String place_id; // this is declared static so that all testcases within the feature file can use the same place_id value for all execution
	//String place_id;
	//JsonPath js;

	TestDataBuild data = new TestDataBuild();

	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		spec_res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		// Pass object of Request spec builder in spec()

		res = given().spec(requestSpecification()).log().all().body(data.addPlacePayload(name, language,address)); 
		
							/* Object of TestDataBuild class is created and its method addPlacePayload() is
							 * called here so that payload can be accessible
							 */

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	{
		APIResources resourceAPI = APIResources.valueOf(resource); //this method valueOf() will automatically invoke the constructor created in APIResources.java
		//Also created object of APIResources class resourceAPI to call methods of APIResources class
	     System.out.println(resourceAPI.getResource());
		
		spec_res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResource());
				//.post("/maps/api/place/add/json")
		//resourceAPI.getResource() is used here instead of the above commented string
		
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
			
	}
		
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String value) {

	
		 
		assertEquals(getJsonPath(response,key), value);

	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
	 place_id= getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_request(resource, "GET"); //calling method
		
		String actualName= getJsonPath(response, "name"); // to verify whether name is Anand
		assertEquals(actualName,expectedName);
	}
	

@Given("DeletePlace payload")
public void delete_place_payload() throws IOException {
   
	res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	
}
}
