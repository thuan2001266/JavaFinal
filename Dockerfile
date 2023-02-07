FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
RUN dos2unix mvnw
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw dependency:resolve
CMD ["./mvnw", "spring-boot:run"]

