# Springboot-mongodb
This is demo application to connect mongodb from springboot application.
We have used mongodb/mongo-xpress docker image from dockerhub to run mongodb db and mongo-xpress.
This example contains the CRUD functionality.
 
## REST API Details
#### Create new user
POST - /mongo/students
<br>
#### Retrieve all user
GET - /mongo/students
#### Retrieve user by id
GET - /mongo/students/{id}
#### Retrieve user by firstName
GET - /mongo/students/name/{fname}
<br>
#### Update user
PUT - /mongo/students
<br>
#### Delete user
DELETE - /mongo/students/{id}

## Maven Configuration
Add below springboot starter dpenedency in pom.xml
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```
## Setup mongodb and mongo-xpress using Docker
Make sure you have docker desktop installed in you windows machine.
Create below docker-compose file. 
### docker-compose.yaml
```yaml
version: '3.8'

services:
  mongodb:
    image: mongo
    container_name: mongodb
    ports:
      - "27017:27017"
    volumes:
      - data:/data
    environment:
      MONGO_INITDB_ROOT_USERNAME: rootuser
      MONGO_INITDB_ROOT_PASSWORD: rootpass
  mongo-express:
    image: mongo-express
    container_name: mongo-xpress
    restart: always
    ports:
      - "8081:8081"
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: rootuser
      ME_CONFIG_MONGODB_ADMINPASSWORD: rootpass
      ME_CONFIG_MONGODB_SERVER: mongodb

volumes:
  data: {}      
          
networks:
  default:
    name: mongodb_network
```

### On the same location open powershell and run below commands : 
```shell 
>docker compose -f docker-compose.yaml up
>docker ps
>docker exec -it <mongodb id from previous command output>
>mongosh mongodb://localhost:27017 –u rootuser –p rootpass
```
After executing last command it will log into mongodb , now we can execute mongodb commands to insertd delete data.

### We can create a database and collections(Table) from command line
```shell
>use demodb
```

### Sample Data for collection(Table)
```JSON
demodb>studentData = [{
	"firstName": "Hasanath Hossain",
	"lastName": "Molla",
	"email":"abcd@gmail.com",
	"gender": "MALE",
	"address": {
	   "country": "India",
	   "postcode": "34567",
	   "city": "Howrah"
	},
	"isStudentActive": false,
	"favouriteSubject": ["maths","english","it"],
	"totalSpentInBooks": 0.0
},
{
	"firstName": "Farheen Sultana",
	"lastName": "NA",
	"email":"fep@gmail.com",
	"gender": "FEMALE",
	"address": {
	   "country": "India",
	   "postcode": "23456",
	   "city": "Arambagh"
	},
	"isStudentActive": false,
	"favouriteSubject": ["maths","english","Nutrition"],
	"totalSpentInBooks": 100.0
},
{
	"firstName": "Toufique Hossain",
	"lastName": "Molla",
	"email":"toh@gmail.com",
	"gender": "MALE",
	"address": {
	   "country": "India",
	   "postcode": "34567",
	   "city": "Howrah"
	},
	"isStudentActive": false,
	"favouriteSubject": ["maths","english","Political Science"],
	"totalSpentInBooks": 2000.0
},
{
	"firstName": "Bihan Hossain",
	"lastName": "Molla",
	"email":"bon@gmail.com",
	"gender": "MALE",
	"address": {
	   "country": "India",
	   "postcode": "34567",
	   "city": "Howrah"
	},
	"isStudentActive": true,
	"favouriteSubject": ["maths","english","drawing"],
	"totalSpentInBooks": 50.0
},
{
	"firstName": "Arafat Hossain",
	"lastName": "Molla",
	"email":"aom@gmail.com",
	"gender": "MALE",
	"address": {
	   "country": "India",
	   "postcode": "645872",
	   "city": "Bankura"
	},
	"isStudentActive": true,
	"favouriteSubject": ["maths","english","Medical Science"],
	"totalSpentInBooks": 5000.0
}]
```

Paste the above data in mongodb commandline and hit enter. Then run below command to insert the records into collection.
```shell
demodb>db.student.insertMany(studentData)
```
This will create a collection named student and insert the records present in studentData.

We have spin up mongo xpress along with mongodb using docker compose.
We can create a database using Mongo Express GUI also. Access mongo express by hitting http://localhost:8081. 


## Run Apllication
1.Run the SpringbootMongodbApplication. [port used : 8070]
<br>2.Use Postman to test the api.
<br>3. Sample user for create user :
<br>
```json
{
	"firstName": "Sample name",
	"lastName": "Last",
	"email":"abc.efg@gmail.com",
	"gender": "Female",
	"address": {
	   "country": "Country",
	   "postcode": "12345",
	   "city": "City"
	},
	"isStudentActive": true,
	"favouriteSubject": ["maths","english","Science"],
	"totalSpentInBooks": 1020.00
}
```
