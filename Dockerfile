FROM maven:3.9.2-eclipse-temurin-17-alpine

# setup tesseract
RUN apk update
RUN apk add --no-cache tesseract-ocr
RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata/raw/main/por.traineddata /usr/share/tessdata/por.traineddata

ENV TESSDATA_PREFIX=/usr/share/tessdata
ENV SERVER_PORT=3000
ENV DOCKER_BUILDKIT=1

EXPOSE 3000

# package application
COPY ./ ./
RUN mvn clean package -DskipTests

# run
ENTRYPOINT ["java","-jar","target/jeto-1.0.0-BETA.jar"]