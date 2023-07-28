package resources;

//Enum is special class in JAVA which is collection of constants and methods

public enum APIResources {
	
	//if there are more than one methods, we can separate it with comma as below
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;

	APIResources(String resource) // the methods has arguments, so the constructors request to add arguments
	{
		this.resource = resource; //assign this resource to the local keyword resource, this refers to current class variable 'resource'
	}
	
	//to return the resource we have to create the below method
	
	public String getResource()
	{
		return resource;
	}

}
