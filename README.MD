each Service is run on springboot application.properties.

make sure you start these as first stepsp.

unfortunatly Docker didnt work for me as a option since on windows its pury misery..


![docker.png](READMEIMAGES%2Fdocker.png)

THE PORTS:

API gateway= 9090 seperating it away from the main services
Order Service = 8081
Product Service = 8080

i didnt get the time for rabbitmq unfortunatly.

to communicate manually between the services is as follows
http://localhost:8080/cars post method raw json
{
"make": "jaguar",
"releasedYear": 2022,
"color": "Blue",
"price": 25000
} change it as you like, the id inside in memory database
is the one you use to search up the cars. all of the communication
is set up trough API gateway.

GET method http://localhost:8081/Order/cars/1 after you have
added cars with the post, you should be getting full values of the car
with the id, also done with full communication both ways from order-api-product 

database was successfully set up for Product Service, i did manage 
set up the code for Order service where i wanted to add 1 more services
but didnt get the time, you can see the code at OrderServiceimpl.

do check the application.properties for database configuration.
once you start all springboot applications do use h2 console
to check the service interactions. http://localhost:8082/h2-console

![plan.png](READMEIMAGES%2Fplan.png)