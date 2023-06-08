# odata_v4
OData V4 implementation of generated_app service
This is a base spring boot project containing sap jpa processor v4 support. This project is build by taking reference from this doc. https://github.com/SAP/olingo-jpa-processor-v4/blob/master/jpa-tutorial/QuickStart/QuickStart.md

# To build the entire project
docker-compose build

# To build a specific service/application in the project (in the below command "app" is a service)
docker-compose build app

##############
Step to run this application
docker-compose up -d

Step to stop the application
docker-compose stop app

Step to shutdown the application
docker-compose down app
################


To run Testcases:
mvn verify

To compile
mvn clean install -DnoTest=true
