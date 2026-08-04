FROM maven:3.9.11-eclipse-temurin-17

WORKDIR /app

COPY pom.xml ./
RUN mvn -B -q -DskipTests dependency:go-offline

COPY config ./config
COPY src ./src
COPY docker-run-tests.sh /usr/local/bin/docker-run-tests.sh

RUN chmod +x /usr/local/bin/docker-run-tests.sh

ENTRYPOINT ["/usr/local/bin/docker-run-tests.sh"]

