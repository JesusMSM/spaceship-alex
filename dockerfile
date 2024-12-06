# Stage 1: Image and downloads

# MODEL IMAGE
FROM eclipse-temurin:17.0.13_11-jdk

# Port where the container is executed (informative)
EXPOSE 8080

# WE DEFINE THE ROOT DIRECTORY OF OUR CONTAINER
# (it is a folder inside the container where you will store files)

WORKDIR /root

# COPY AND PASTE FILES INSIDE THE CONTAINER

    # Here are the files that we want to have in the container and that will allow us to deploy the project.
    COPY ./Spaceship/Spaceship/pom.xml /root/

    # Install Maven
    RUN apt-get update && apt-get install -y maven

# DOWNLOAD THE DEPENDENCES (having Maven by the previous step we can now use its commands )
RUN mvn dependency:go-offline -B

# COPY THE SOURCE CODE INSIDE THE CONTAINER
COPY ./Spaceship/Spaceship /root/

# Step 2: Build our application

# COMMAND TO SKIP UNIT TESTS AND COMPILE (will generate a target folder)
RUN mvn clean package -DskipTests

# RAISE OUR APPLICATION WHEN THE CONTAINER STARTS (it's a command)
CMD ["java", "-jar", "./target/Spaceship-0.0.1-SNAPSHOT.jar"]
