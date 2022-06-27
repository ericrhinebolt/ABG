# ABG - Alpha, Beta, Gold

ABG is a full stack web applications for video game players. Users can search video games, see game details, review
games, add games to their list of favorites and see news about their favorite games.

<hr>

### INSTRUCTIONS

1. Navigate to ABG. Click the "Login" button on the top right of the navigation bar. If you already have an account, you
   can log in now. If you don't you can click on the "Register" button on the top right. Registering will take you back
   to the login page where you can proceed with the login process.

2. The homepage will display a list of news articles and screenshots associated with your favorite games.

3. Navigate to the "Games" link. Once there, search a game. Highlight the desired game and click on it. Once on the game
   screen, there will be info about the game if available. There are also 2 buttons on the right to add the game to your
   list of favorites or to write a new review for the game. If you select favorites, proceed to step 4, if you select
   review, proceed to step 5.

4. Once you add the game to your favorites, it will navigate you to your personal list of favorites. Here if you hover
   over a game, you will see a remove favorite button pop up. Click it if you'd like to remove it. Otherwise, you can
   click the logo or the home button to navigate back to home to see your list of news and screenshots for your favorite
   games.

5. Once you click the new review button, you will be on the new review page. It shows the title and your username as the
   author. If the username is not yours, you can click the button next to it to log out. Otherwise, proceed to fill out
   your review and click submit. Once you submit, it will take you to a list of reviews by all users. Your review will
   be at the top. Any review that you have created you will have the ability to edit that review or delete it. You
   cannot edit other users reviews.

<hr>

### TECHNICAL SPECS

- HTML
- CSS
- Bootstrap
- Thymeleaf
- JavaScript
- Java
- Spring Boot
- Spring Data
- Spring Security
- MariaDB

<hr>
<details><summary>

### CHALLENGES

</summary>

 - The third-party API I used cannot be accessed directly - I had to create my own API to communicate with it.
 - Integrating Thymeleaf and persisting values between views - I had to learn Thymeleaf more in depth.
 - Working with such a large data set. The games table has over 140,000 rows - I created a JSON parser to read a JSON file and populate my database.

</details>

<details><summary>

### USER STORIES

</summary>

- [ ] As a user, I want to be able to search reviews by rating.
- [ ] As an admin, I would like to create a role so that I can log in and maintain all user reviews.
- [ ] As a user, I want to be able to see reviews when I search a game.
- [ ] As a user, I want to be able to give games ratings.
- [ ] As an admin, I would like to give users a way to contact me if they have issues with the site or would like to suggest features.
- [x] As a user, I want to be able to search game titles to get a description and info.
- [x] As a developer, I want to design a functional, easy to navigate website.
- [x] As a user, I want an easy to use, appealing site.
- [x] As a user, I want to see news about my favorite games.
- [x] As a user, I would like to see a list of the most recent reviews.
- [x] As a user, I want to be able to review games.

</details>

<hr>
<details><summary>

### PROJECT REQUIREMENTS

</summary>
<p>

#### VIEWS

- Use an external CSS stylesheet (internal styling may be used along with frameworks such as Bootstrap, but you must
  still include and utilize a custom CSS external file)

- Your application should include six different views/pages (wireframes of the pages should be submitted with the
  project)
- Use HTML to layout the pages and Thymeleaf to make the pages dynamic (frameworks such as Angular or React can be used
  as well but will not be covered in the course. The application’s presentation must meet the general view
  requirements.)
- Use CSS to style the web pages
- Use at least one JavaScript script linked from an external script file (internal scripts may be used along with
  frameworks such a jQuery, but you must still include and utilize a custom JavaScript external file)
- Include a navigation section that is included across multiple pages

#### MODELS AND DATABASE

- The configuration file must be set up correctly (e.g., persistence.xml or application.properties)
- Include at least three custom queries
- Test each custom query created in each repository
- Test at least one method in each of the service classes
- Include at least one parameterized test
- Include at least one test suite
- Use MariaDB as your DBMS
- Have at least four models along with tables in a relational database (if four models/tables do not make sense for your
  application, discuss this with your instructor)
- Include a schema diagram of the tables
- Use Jakarta Persistence API (JPA) directly or through Spring Data JPA
- Include and implement repository and service classes/interfaces
- Models should be annotated for binding using Spring data binding through Jakarta and/or Hibernate validation
- Your application should include at least one example of each of the CRUD operations
- Use JUnit to perform unit tests on the JPA repositories/services

#### SPRING

- Use Spring Boot to implement the project
- Include at least two ways of creating a managed bean/object
- Use correct implementations of dependency injection with appropriate use of the @Autowired annotation
- Include at least one example of session management (Spring Security can be used for session management)
- Apply exception handling where required by the code
- Use of Web Services (when applicable)

#### MISC

- Include sign up and login functionality with encrypted passwords using bcrypt (use of Spring Security will satisfy
  this requirement)
- The project package structure should be shown in class where the models, DAO/repositories, services, controllers,
  exceptions, etc., have a package. Views or templates don’t require a package.
- Standard Java naming conventions should be followed:
    - Classes should be written in Pascal case
    - Variables, methods, and URLs should be written in camel case
    - Files, including view files, should be written in snake case
    - Packages should be in all lowercase with each word separated by dots (.)
    - Packages should include the name of your project and your name (e.g., “org.johndoe.myprojectname”)
- Each class should include comments to describe the class and the methods (see Java - JavaDoc discussion topic in
  Canvas)
- Have the project hosted on GitHub with a “readme” file documenting user stories and technical challenges along with
  how they were resolved.

</p>
</details>
<hr>

<details><summary>

### FUTURE ADDITIONS

</summary>

- Dynamic review ratings with animated stars
- User profiles where users can add their steam ID, get their player info and change their usernames or passwords
- Search feature for games by genre
- Ability to change list sorting direction

</details>

### DEVELOPER

Eric Rhinebolt
https://github.com/ericrhinebolt