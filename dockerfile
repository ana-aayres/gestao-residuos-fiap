# Use a imagem base do OpenJDK
FROM openjdk:17

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR empacotado para o contêiner
COPY target/GestaoResiduos-1.0-SNAPSHOT.jar /app/GestaoResiduos-1.0-SNAPSHOT.jar

# Exponha a porta que a aplicação usa
EXPOSE 8080

# Defina o comando para iniciar a aplicação
CMD ["java", "-jar", "/app/GestaoResiduos-1.0-SNAPSHOT.jar"]
