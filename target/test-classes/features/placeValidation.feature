Feature: Validating Place APIs 

@AddPlace
Scenario Outline: Verify if Place is added successfully using Add Place API 

	Given Add Place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request 
	Then the API call is success with status code 200 
	And "status" in the response body is "OK"
	And "scope" in the response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:

   |name    | language | address|
   |Anand   | English  |  WTC   |
#  |Sameer  | French | AAC  |
	
@DeletePlace
Scenario: Verify id Delete Place functionality is working

Given DeletePlace payload
When user calls "deletePlaceAPI" with "Post" http request
Then the API call is success with status code 200 
And "status" in the response body is "OK"