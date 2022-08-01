# # eip-ero-register-checker-api
Spring Boot microservice exposing a REST API to update register checker status

## Running the application
Either `./gradlew bootRun` or run the class `RegisterCheckerApiApplication`

### Authentication
Requests are assumed pre-authenticated which carry a header defined by property `{dluhc.request.header.name}` that is the authenticated EMS client certificate serial number

### External Environment Variables
The following environment variables must be set in order to run the application: 
- `dluhc.request.header.name` is the name of header required in request
- `api.ier-management.url` is the base URL of the external IER management API service
