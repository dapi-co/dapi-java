# WildFly(formerly JBoss) example


#### It uses _WildFly_ version `17.0.0`

#### The example shows a very basic server.


**How to run it**:
- Open the example in VisualStudio (or any equivalent IDE).
- Edit the `src/main/java/co/dapi/examples/wildfly/DapiService.java` file to replace the `appSecret` value with your Dapi application's `AppSecret`.
- Start your _WildFly_ server.
- Open a terminal session in the example folder.
- Run this command `mvn clean package wildfly:deploy`
- The endpoint is now exposed on: http://localhost:8080/DapiSDKExample/HandleSDKRequests (by default).
- Use Postman (or any equivalent app), to POST valid Dapi requests to the endpoint `URL` (mentioned above).
    - All requests' bodies should be in `JSON` format.
