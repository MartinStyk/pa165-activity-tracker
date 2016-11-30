#Activity tracker REST API

### Rest API implemented for entity
* User

##How to run
* Run ``mvn clean install`` in top level project directory
* Run ``mvn`` in activity-tracker-rest directory

##Hot to test
* Rest API is available at localhost:8080/pa165/rest


List all users
```
curl -i -X GET http://localhost:8080/pa165/rest/users
```
Find users by email
```
curl -i -X GET http://localhost:8080/pa165/rest/users?email=marianHossa@gmail.com
```
Find users by id
```
curl -i -X GET http://localhost:8080/pa165/rest/users/4
```
Find team of user
```
curl -i -X GET http://localhost:8080/pa165/rest/users/4/team
```
Delete user
```
curl -i -X DELETE http://localhost:8080/pa165/rest/users/2
```
Create new user
```
curl -X POST -i -H "Content-Type: application/json" --data '{"email":"mstyk@redhat.com","firstName":"Martin","lastName":"Styk","passwordHash":"200aaa","dateOfBirth":"2008-02-15","role":"ADMIN","sex":"MALE","height":"111","weight":"100"}' http://localhost:8080/pa165/rest/users/create
```
Update many attributes of user
```
curl -i -X PUT -H "Content-Type: application/json" --data '{"email":"peterSagan@gmail.com","firstName":"Peter","lastName":"Sagan","passwordHash":"200aaa","dateOfBirth":"1990-01-28","role":"ADMIN","sex":"MALE","height":"111","weight":"100","team":"Rychle holky"}' http://localhost:8080/pa165/rest/users/4
```
Update few attributes of user
```
curl -i -X PUT -H "Content-Type: application/json" --data '{"firstName":"Petko"}' http://localhost:8080/pa165/rest/users/4
```


