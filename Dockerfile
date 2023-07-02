FROM maven:3.9.2-eclipse-temurin-17-alpine

# setup tesseract
RUN apk update
RUN apk add --upgrade --no-cache tesseract-ocr
RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata/raw/main/por.traineddata /usr/share/tessdata/por.traineddata
ADD https://github.com/tesseract-ocr/tessdata/raw/main/osd.traineddata /usr/share/tessdata/osd.traineddata

RUN wget https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-17.0.7/graalvm-community-jdk-17.0.7_linux-x64_bin.tar.gz
RUN mkdir /opt/java/graalvm
RUN tar -xzf graalvm-community-jdk-17.0.7_linux-x64_bin.tar.gz -C /opt/java/
RUN mv /opt/java/graalvm-community-openjdk-17.0.7+7.1 /opt/java/graalvm-community-openjdk-17
RUN export JAVA_HOME=/opt/java/graalvm-community-openjdk-17
RUN export PATH=/opt/java/graalvm-community-openjdk-17/bin:$PATH

EXPOSE ${PORT}

# package application
COPY ./ ./
RUN mvn clean package -DskipTests

# run
ENTRYPOINT ["java","-jar","target/jeto-1.0.0-BETA.jar", "-Xmx768m"]