# assignment4
## Book Management Exercise

### Description
  - Administrators login:
      + Managing all information of users login ( add new, update, delete of user)
      + Managing all book of users (add new, update, delete,change status of book)
      + Searching info users book
      
  - User login
      + Edit personal information 
      + Manage books created by that user (add new, update, delete)
  - Guest 
      + View all books enable of system
### Guide start project
  - Clone this repository (link clone https://github.com/novahub-internship-2017-web/BE_2017_TUYEN.git)
  
  - Configure connect database.
	+ Open file application.properties (../assignment4/src/main/resources/application.properties)
	+ Edit username, password
		+ spring.datasource.username=...
		+ spring.datasource.password=...
  - Import database assignment4.sql from ..assignment4/src/main/resources/assignment4.sql (use MySQL)

  - Run project by terminal: 
    + mvn install clean 
    + mvn spring-boot:run
  - Visit the link below to see results as a guest:   
  	+ http://localhost:8080/
  	+ 
  - Account:
      + account admin: username = admin@gmail.com , password = 123456
      + account user: username  =  a@gmail.com , password = 123456
