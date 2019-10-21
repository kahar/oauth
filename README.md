Following flow to access secured data is required:  
http://localhost:8081/oauth_login  
http://localhost:8081/account/create  
http://localhost:8081/account/confirm  

now you can access following url's:  
http://localhost:8081/account/authentication    
http://localhost:8081/account/principal    
http://localhost:8081/account/principal/name  


For H2 database console use url:  
http://localhost:8081/console/login.do  
Driver Class: org.h2.Driver  
JDBC URL: jdbc:h2:mem:testdb  
User Name: sa  
Password: //empty  


For swagger documentation use browser extension, and url:  
http://localhost:8081/v2/api-docs