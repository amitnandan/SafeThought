# SafeThoughts - Backend for Blog Application

SafeThoughts is the backend component of a Blog Application that enables users to create, update, delete, and post blogs. It includes features like a comment section, post categorization, and strong authentication and authorization mechanisms using Spring Security and JWT tokens. The backend is deployed on AWS Elastic Beanstalk.

## Features

- User Authentication: Secure user registration and login using Spring Security.
- Role-based Authorization: Distinguish between admin and regular users with customized access.
- Blog Management: Create, update, delete, and publish blogs.
- Comment Section: Engage in discussions through the comment feature for each blog post.
- Categorization: Organize posts using categories for enhanced content management.
- JWT Tokens: Ensure secure communication between client and server for multiple requests.
- API Documentation: Explore and test endpoints using Swagger.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Swagger
- MySQL (or your preferred database)
- AWS Elastic Beanstalk (for deployment)


## Installation

1. Clone the repository: `git clone https://github.com/your-username/SafeThoughts.git`
2. Navigate to the project directory: `cd SafeThoughts`

## Configuration

1. Configure your application properties in `src/main/resources/application.properties` or `.yml` files.
2. Set up your database configurations, including connection details and credentials.
3. Adjust Spring Security settings and user roles according to your requirements.

## Deployment

The backend is deployed on AWS Elastic Beanstalk.

1. Build a deployable JAR or WAR file for your application.
2. Log in to the AWS Management Console.
3. Open the Elastic Beanstalk console.
4. Create a new environment and upload the deployable file.
5. Connect your MySQL to RDS in AWS
6. Configure environment properties, such as database connection settings and application properties.

## Usage

1. Access the deployed backend endpoints through your browser or a tool like Postman.
2. Register an account or use the provided admin credentials.
3. Utilize the blog creation, updating, and commenting features.
4. Access the Swagger documentation at `http://...hosting_url../swagger-ui.html` to test the endpoints.

## Contributors

- Author: Amit 
.

---

Feel free to adapt this README according to your application's specifics. Best of luck with your SafeThoughts Backend for the Blog Application! Should you have any questions or require further assistance, don't hesitate to reach out.
