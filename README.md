# Redis Collections Testing Instruction


## Prerequisites

Before starting, ensure your system has the following installed:

- **Docker**
- **Docker Compose**
- **Java 21**
- **Gradle**

## Project Setup

1. **Clone the Repository**  

   ```bash
   git clone https://github.com/HildaKar/redis-collections.git
   cd redis-collections
   ```

2. **Build the Project**  

   ```bash
   ./gradlew clean build
   ```


## Running tests for RedisBackedQueue and RedisBackedMap

You can validate the functionality of the RedisBackedQueue and RedisBackedMap in two ways:

1. **Run the Application**  
   Start the Spring Boot application that performs basic put and get operations on the Redis-backed collections:
   
   In the `docker` directory, run (to start redis):

   ```bash
   docker-compose up -d
   ```
   then run the application:

   ```bash
   ./gradlew bootRun
   ```

   Observe the console output to verify the operations.

2. **Run the tests**  

   Have docker running in your environment, then execute the tests to ensure that the Redis-backed collections work as expected:

   ```bash
   ./gradlew test
   ```

By following these instructions, you can set up your environment, launch Redis using Docker Compose, and test both the Map and Queue implementations integrated with Redis.
