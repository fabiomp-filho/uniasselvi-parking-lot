FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM tomcat:9.0.53-jdk11-openjdk
COPY --from=build /app/target/parking-lot.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run", "-Xmx512m", "-Xms256m"]
