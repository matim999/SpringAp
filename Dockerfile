FROM demo/maven:3.3-jdk-8
WORKDIR /var/www/java
RUN javac app.java