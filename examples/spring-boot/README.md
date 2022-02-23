# Spring-boot example


#### It uses _Spring-boot_ version `2.1.4.RELEASE` and _Gradle_ version `5.2.1`

#### The example shows a very basic server. Proper error handling should be implemented before being used in production environments.


**How to run it**:
- Open the example in VSCode (or any other IDE).
- Edit the `src/main/java/co/dapi/examples/springBoot/ExampleApplication.java` file to replace the `appSecret` value(_YOUR_APPSECRET_) with your Dapi application's `AppSecret`.
- Build the `Gradle` project.
- Run the `ExampleApplication` class.
- The endpoint is now exposed on: http://localhost:9099/handleSDKRequest (by default).
- Use Postman (or any equivalent app), to POST valid Dapi requests to the endpoint `URL` (mentioned above).
    - All requests' bodies should be in `JSON` format.
