
# Hotel App
A RESTful web service for Hotel App.

## Stack
This app runs on Spring Boot Java framework with embedded tomcat (port 2000) as the server and with H2 as the in memory SQL database.

## Setup
1. Clone the repository.
2. Run the following command in the root path to start the server:
   * ```./mvnw clean install -DskipTests=true spring-boot:run``` for UNIX systems
   * ```.\mvnw.cmd clean install -DskipTests=true spring-boot:run``` for Windows systems
   
   (Note that this command downloads required jar files for the project, which may take some time. The files will be located by default in ```~/.m2/repository``` for UNIX systems and ```C:\Users\<username>\.m2\repository``` for Windows systems)
   
3. Use a REST client of your choice (Postman/Insomnia/Curl/...) for testing the endpoints.
   
## H2 Console
To access the H2 console and see the tables, start the server and go to ```http://localhost:2000/h2-console``` and use the following:
1. JDBC URL: ```jdbc:h2:mem:testdb```
2. username: ```sa```
3. password: empty

## Endpoints:
1. **Hotel Endpoints**
* GET ```/hotel```: to get list of all hotels
* GET ```/hotel/{id}```: to get list of hotel with id ```id```
* POST ```/hotel```: to add a hotel
```
   {
    "hotelName": "Mariott",
    "stars": 4,
    "restaurant": true,
    "meals": true,
    "rating": 7.6,
    "city": "Berlin"
  }
   ```
* POST ```/hotel/search```: to search all hotels by provided criteria
```
{
	"pageNumber": 0,
	"pageItems": 10,
	"sortBy": "bookedEndDate",
	"ascending": true,
	"criteria": [
		{
			"key": "hotel.city",
			"value": "Bangalore,London",
			"operation": ":"
		},
		{
			"key": "bookedEndDate",
			"value": "2021-05-22",
			"operation": ">"
		},
		{
			"key": "pricePerNight",
			"value": 500,
			"operation": "<"
		},
		{
			"key": "wifi",
			"value": true,
			"operation": "="
		}
	]
}
```
* DELETE ```/hotel/{id}```: to delete hotel with id ```id```
2. **Review Endpoints**
* GET ```/review/{hotelId}```: to get list of reviews of the hotel with id ```hotelId```
* POST ```/review```: to add or update a review
```
{
	"ratingValue": 10,
	"comment": "Very good service!",
	"hotel": {
		"hotelId": 4
	},
	"traveler": {
		"travelerId": 13
	}
}
```
* POST ```/review/search```: to search all hotels by provided criteria
```
{
	"pageNumber": 0,
	"pageItems": 10,
	"criteria": [
		{
			"key": "traveler.gender",
			"value": "F",
			"operation": "="
		}
	]
}
```
* DELETE ```/review/{id}```: to delete review with id ```id```

3. **Traveler Endpoints**
* GET ```/traveler```: to get list of all travelers
* GET ```/traveler/{id}```: to get traveler with id ```id```
* POST ```/traveler```: to add or update traveler
```
{
  "firstName": "Matt",
  "lastName": "Joseph",
  "gender": "M",
  "residentialCity": "New Hampshire"
}
```
* DELETE ```/traveler/{id}```: to delete traveler with id ```id```





