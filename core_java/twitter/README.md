# Introduction
This twitter CRUD application allows users to post, read and delete Tweets via the official Twitter REST API, using their Twitter Developer Account. This app was developed using an MVC architecture which is comprised of a model, controller layer, data access object and service layer. It was Unit and Integration tested using JUnit and Mockito respectively. Some of the technologies featured in this application include:
- Maven
- Java (JDBC)
- HTTP & JSON
- JUnit and Mockito
- Spring
- Docker
- Twitter REST API

# Quick Start
### POST
Allows users to post a tweet with a geotag.
- `TwitterApp post tweet_text longitude:latitude`
### SHOW
Show the user a Tweet which corresponds to the ID being supplied.
- `TwitterApp show tweet_id [field1, field2, ...]`
### DELETE
Delete the Tweets associated with each id
- `TwitterApp delete [id1, id2, ...]`

# Installation
## 1. Maven
To install program using Maven:
```
mvn clean package -Dmaven.test.skip=true
java -jar target/twitter-1.0-SNAPSHOT.jar post|show|delete [options]
```