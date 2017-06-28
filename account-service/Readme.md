
## Command  default port
%java_home%/bin/java -jar account-service-0.0.1-SNAPSHOT.jar


## Command [new port]
%java_home%/bin/java -jar -Dserver.port 1112 account-service-0.0.1-SNAPSHOT.jar


App service will register to eureka-service add port 8080


## List instance registered on eureka-service 
http://localhost:8080/eureka/apps/ACCOUNTS-SERVICE
