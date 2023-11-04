FROM eclipse-temurin:17-jre
worker /app

COPY . .
ENTRYPOINT java -Dfile.encoding=UTF-8 $JAVA_OPTS -jar /app.war $RUN_ARGS