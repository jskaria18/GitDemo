package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.Serialization_GoogleMaps;
import POJO.Serialization_Location;

public class TestDataBuild {

	
	//public Serialization_GoogleMaps addPlacePayload()
	
	/* Now the below method expects 3 parameters name, language and address, so the above line is commented */
	public Serialization_GoogleMaps addPlacePayload(String name, String language, String address)

	{
		Serialization_GoogleMaps sg = new Serialization_GoogleMaps();

		sg.setAccuracy(50);
		sg.setAddress(address);
		sg.setLanguage(language);
		sg.setPhone_number("91-88745562131");
		sg.setWebsite("https://rahulshettyacademy.com");
		sg.setName(name);

		List<String> myList = new ArrayList<String>();
		myList.add("Shoe park");
		myList.add("Shop");

		sg.setTypes(myList);

		Serialization_Location sl = new Serialization_Location();

		// sg.setLocation(null) //this expects Location class objects as parameters, so
		// we have to first create object of Location class, so we use the below line
		// now

		sl.setLat(-3855214);
		sl.setLng(-78.3451);

		sg.setLocation(sl); // passing object of Location class to set Location
		return sg;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeId+"\"\r\n"
				+ "}\r\n"
				+ "" ;
	}

}
