FROM adoptopenjdk/openjdk11
ARG JAR_FILE=build/libs/sellerB-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} sellerB.jar
ENTRYPOINT ["java","-jar","/sellerB.jar"]