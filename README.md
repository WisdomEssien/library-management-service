# Library-management-service
A simple book library application API service with some features such as

- Add Category
- Edit Category
- List Categories
- Add Book
- Edit Book
- List Books
- Add Books to category
- Delete Category/Book

## Tech/framework used

- Springboot 2.6.3
- jdk 1.8
- maven
- Swagger 2
- Lombok
- H2 Database

## Step-By-Step guide to setup project on local system

- Clone project from the git repository using this link [Git Repo](https://github.com/WisdomEssien/library-management-service.git).
	
- Open command prompt and navigate to the desired directory. Copy, paste and execute the git command below on the command prompt.
_You have to have git installed on your system._

```
	git clone -b master https://github.com/WisdomEssien/library-management-service.git
```

- Once the project is completely downloaded, launch and import project to your favourite IDE as a maven project.
- Run application.  
- Launch the swagger documentation page which can also be used to test the service. [Swagger Documentation](http://localhost:7070/api/swagger-ui.html#/)
- Launch the database console using this link: [H2 Database Console](http://localhost:7070/api/h2-console/).
Copy and paste link directly on the browser if you are experiencing any difficulty redirecting.
- Use the credentials below when prompted to login

```
   - User Name: sa
   - Password:
   - JDBC URL:	jdbc:h2:mem:mydb
   - Driver Class: org.h2.Driver
```

Hope this was helpful to get you started

[wisdom essien](https://github.com/WisdomEssien/library-management-service.git) © 2022 
