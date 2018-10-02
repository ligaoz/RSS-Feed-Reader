# RSS-Feed-Reader

## Simple RSS Feed reader application.
 Save Feed's URL and access 5 latest items from saved RSS Feed

### Configuration
- Application server is configured to run on port 80
- Database connection is configured on port 3306 on localhost and connects to default Schema "feeds"

### Running Application
- To run application using maven plugin run mvn spring-boot:run 
    - To use custom configuration settings override the default setting by adding -Dspring-boot.run.arguments 
- To run application as packaged JAR file execute the jar file - RSSfeed-0.0.1-SNAPSHOT.jar 
    - To use custom configuration use JAVA_OPTS="-Dpropertykey=propvalue" 

#### System Environment Prerequistes 
- JRE
