# Selects OpenJDK as base image
FROM openjdk:latest

# Copy the jar to tmp directory
COPY ./target/seMethods-0.1.0.2-jar-with-dependencies.jar /tmp

# Set tmp directory as working directory
WORKDIR /tmp

# Main command of the image (runs the jar file)
ENTRYPOINT ["java", "-jar", "seMethods-0.1.0.2-jar-with-dependencies.jar"]