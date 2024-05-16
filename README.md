# Urban Swap Backend

## Overview

This is the backend api for Urban Swap, a green mobility ride hailing application.

## Technologies Used

1. [Spring Boot](https://spring.io/projects/spring-boot) 
2. [PostgreSQL](https://www.postgresql.org/docs/): An open-source relational database system.
3. [Docker Compose](https://docs.docker.com/compose/): A tool for defining and running multi-container Docker applications.
4. [Flyway](https://flywaydb.org/): A database migration tool that allows version control over database changes.
5. [Firebase Authentication](https://firebase.google.com/docs/auth): A service provided by Google to authenticate users using only client-side code.

## Prerequisites

1. Java Development Kit (JDK 17) installed on your machine.
2. Docker and Docker Compose installed to run the PostgreSQL database.
3. Firebase project set up with authentication enabled.

## Setup Instructions

1. Clone the Repository
2. Configure Firebase
   1. Set up a Firebase project on the Firebase Console. 
   2. Enable Firebase Authentication for your project and configure it according to use phone authentication. 
   3. Download the Firebase configuration file and rename it as `firebaseServiceAccountKey.json` and place it in `/src/main/resources` directory.
3. Setup environment variables. (replace [FILLIN] with your configs)
   ```
    URBAN_SWAP_DB_HOST=localhost
    URBAN_SWAP_DB_PORT=5432
    URBAN_SWAP_DB_NAME=[FILLIN]
    URBAN_SWAP_DB_USER=[FILLIN]
    URBAN_SWAP_DB_PASSWORD=[FILLIN]
   ```
      1. Application environment variables 
         1. Option 1: System environment variables
   
              You can choose to place the above environment vairables in the `/etc/environment` file. 
   
         2. Option 2: Run from IntelliJ IDEA 
         
            1. Create a file (eg. urban-swap-dev-env-vars.txt) anywhere in your machine and take note of its path. Place in it the above configurations.
            
            2. Edit configurations > Environment variables. Set it as the above file path.
            
      2. Database environment variables 
         
Create a `.env` file in the root project directory and add the following configurations (replace [FILLIN] with your configs):

    ```
       URBAN_SWAP_DB_HOST=localhost
       URBAN_SWAP_DB_PORT=5432
       URBAN_SWAP_DB_NAME=[FILLIN]
       URBAN_SWAP_DB_USER=[FILLIN]
       URBAN_SWAP_DB_PASSWORD=[FILLIN]
    ```

4. Start PostgreSQL using Docker Compose

    Navigate to the root directory of your project.
    Run the following command to start the PostgreSQL database:
    ```bash
        docker compose up
    ```

5. Run the Spring Boot Application

#  Contributing
1. Clone repository
    ```bash
    git clone git@github.com:GloibGroup/urban-swap-backend.git
    ```
2. Change to the `development` branch
    ```bash
    cd /urban-swap-backend
    ```
3. Create a new feature branch off this branch
    ```bash
    git checkout -b feature-example
    ```
4. Make your changes and commit them.
5. Pull the latest changes from the `development` branch and merge them into your working branch.
    ```bash
    git checkout development
    git pull origin development
    git checkout feature-example
    git merge development
    ```
6. Resolve any merge conflicts that you may have, then push your feature branch
    ```bash
    git push origin feature-example
    ```
7. Create a pull request against the base branch (`development`).
8. Give a summary of what changes your working branch introduces. 

See [example pull request](https://github.com/GloibGroup/urban-swap-backend/pull/2).
