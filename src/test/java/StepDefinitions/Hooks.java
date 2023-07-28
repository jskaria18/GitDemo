package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//write code that gives place id 
		//execute this code when place id is null
		StepDefinition stp = new StepDefinition();
		
		if(stp.place_id == null) // if this is not null, then there is obviously some place id string and it uses it
		{
		stp.add_place_payload_with("Amit","English","Spain");
		stp.user_calls_with_http_request("AddPlaceAPI","POST");
		stp.verify_place_id_created_maps_to_using("Amit","getPlaceAPI");
		}
		
	}

}
