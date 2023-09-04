<center>
  <p align="center">
    <img src="https://user-images.githubusercontent.com/20674439/158480514-a529b310-bc19-46a5-ac95-fddcfa4776ee.png" width="150"/>&nbsp;
    <img src="https://upload.wikimedia.org/wikipedia/commons/0/06/Kotlin_Icon.svg"  width="150" />
  </p>  
  <h1 align="center">🚀 Microservice: Video Catalog Admin with Kotlin</h1>
  <p align="center">
    "Microservice related to the backend of the Video Catalog Administration <br />
    Using Clean Architecture, DDD (Domain-Driven Design), TDD (Test-Driven Development), and current best practices in the market."
  </p>
</center>
<br />

## Necessary Tools

To get started with this microservice, you'll need the following tools:

- JDK 17
- Your preferred Integrated Development Environment (IDE)
- Docker

## How to Run?

Follow these steps to run the microservice:

1. Clone the repository:

```sh
git clone https://github.com/codeedu/micro-admin-videos-java.git
```

3. Run the migrations with Flyway:

```shell
./gradlew flywayMigrate
```

4. Run the application as a SpringBoot app:

```shell
./gradlew bootRun
``` 

## Database

The main database is MYSQL e to run it, you'll need to run the following command:

```shell
docker-compose up -d
```

Wait while MySQL is being setup and after that proceed.

### Database migrations with Flyway

#### Run migrations

If this is the first time that you are running the database, you'll need to
run the SQL migrations with the `flyway` tool. To do that, run the following command:

```shell
./gradlew flywayMigrate
```

Done! Now the database is ready.

<br/>

#### Clean database migrations

You can clean your database by running the following command:

```shell
./gradlew flywayClean
```

Be careful when using this command, because it will delete all your data.

<br/>

#### Fixing Flyway inconsistencies

If you had any problem with the migrations, you can fix it by running the following command:

To execute the repair, fix the files and run:

```shell
./gradlew flywayRepair
```

<br/>

#### Other useful Flyway commands

```shell
./gradlew flywayInfo
./gradlew flywayValidate
```

To see more: [Flyway Gradle Plugin](https://flywaydb.org/documentation/usage/gradle/info)

<br/>
