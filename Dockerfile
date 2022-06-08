FROM openjdk:11.0.15-jdk
#ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
ADD target/social-0.0.1-SNAPSHOT.jar social.jar
ENTRYPOINT ["java", "-jar", "social.jar"]
