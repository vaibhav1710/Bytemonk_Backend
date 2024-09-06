
# Incident Management API

This API allows for managing incident reports with various functionalities like creating, updating, retrieving, and filtering incidents. It is built with an in-memory database and provides basic authentication for security.

## Features

1. **Incidents Management:**
   - Create a new incident report.
   - Update an existing incident report.
   - List all incidents with optional filtering by severity level and date range.
   - Retrieve a specific incident report by its ID.

2. **Database:**
   - Uses an in-memory database (e.g., H2) for storage.
   - The entity model is designed for `Incident` and is set up with a JPA repository.

3. **Business Logic:**
   - Incidents cannot be created with a past date greater than 30 days or a future date.
   - Severity levels are validated (allowed values: "Low", "Medium", "High").
   - The title of the incident must be unique and descriptive (minimum length of 10 characters).

4. **Security:**
   - Basic authentication is used to secure the API with a hardcoded username and password.
   - All API requests are logged to a file, including timestamp, IP address, accessed endpoint, and HTTP method.

5. **Error Handling:**
   - Basic error handling is implemented for invalid input (e.g., invalid date or severity level) and non-existent records.
   - Appropriate HTTP status codes are returned for errors.

6. **Testing:**
   - Unit tests for service methods are written using JUnit.
   - Includes tests that validate security restrictions, such as unauthenticated access.

## API Endpoints

### 1. Incidents

- **Create a New Incident Report**
  - **Endpoint:** `POST /addIncidents`
  - **Response:** Returns the created incident report.

- **Update an Existing Incident Report**
  - **Endpoint:** `PUT /updateIncident/{id}`
  - **Response:** Returns the updated incident report.

- **List All Incidents**
  - **Endpoint:** `GET /getIncident`
  - **Response:** Returns a list of incidents based on filters.

- **Retrieve a Specific Incident Report by ID**
  - **Endpoint:** `GET /getIncident/{id}`
  - **Response:** Returns the incident report with the specified ID.

### 2. Authentication

- The API is secured with basic authentication. Use the following credentials:
  - **Username:** `admin`
  - **Password:** `pass`

## Database Setup

- This project uses an in-memory H2 database. No additional setup is required. The H2 console can be accessed for debugging and development purposes.

## Error Handling

- Invalid input data, such as an invalid date or severity level, will result in a `400 Bad Request` response.
- If an incident is not found, a `404 Not Found` response is returned.
- All error responses include a descriptive message.


## How to Run

1. Clone the repository.
2. Ensure you have Java and Maven installed.
3. Run `mvn spring-boot:run` to start the application.
4. Access the API using the base URL: `http://localhost:8080`.

## Security Considerations

- Basic authentication is used for simplicity. For production use, consider implementing more robust authentication mechanisms like OAuth2.

