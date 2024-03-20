# WeatherApp

### Steps to run in local
1. clone repository
2. run `./gradlew clean build` to build the project
3. export api key using command `export API_KEY=api_key_to_replace` . I will share my api key in email
4. run the application from command line using `./gradlew bootrun` command
5. Once the application is up you can hit below api to get the response
``http://localhost:8080/weather/v1/forecast?zipcode=32061``

### Limitation
1. This api can only take US zipcode. We can make it more flexible to use other params aqs well.
2. This api accept zipcode and days which restrict the forecast to these days. Default value for days is 3.

### Sample api response

Request: 
``http://localhost:8080/weather/v1/forecast?zipcode=32061``

#### Response Without Caching:

![ResponseWithOutCache](https://github.com/prarty/WeatherApp/blob/main/src/main/resources/static/ResponseWithoutCache.png)


#### Response With Caching:

![ResponseWithCache](https://github.com/prarty/WeatherApp/blob/main/src/main/resources/static/ResponseWithCache.png)
