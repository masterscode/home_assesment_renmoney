#DockerFile Setup
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/renmoney.jar renmoneyha.jar
ENTRYPOINT ["java", "-jar", "renmoneyha.jar"]