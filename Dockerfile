# Etapa 1: Usando uma imagem base do Maven para construir o projeto
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app

# Copiar o código fonte para o diretório de trabalho
COPY . .

# Construir o projeto usando Maven
RUN mvn clean package -DskipTests

# Etapa 2: Usar uma imagem base do Tomcat para rodar a aplicação
FROM tomcat:9.0.53-jdk11-openjdk

# Copiar o arquivo WAR gerado pelo Maven para o diretório webapps do Tomcat
COPY --from=build /app/target/parking-lot.war /usr/local/tomcat/webapps/ROOT.war

# Expõe a porta 8080 para acessar a aplicação
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
