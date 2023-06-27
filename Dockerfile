FROM maven:3.9.2-eclipse-temurin-17-alpine

# setup tesseract
RUN apk update
RUN apk add --no-cache tesseract-ocr
RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata/raw/main/por.traineddata /usr/share/tessdata/por.traineddata

EXPOSE ${PORT}

# package application
COPY ./ ./
RUN mvn clean package -DskipTests

# run
ENTRYPOINT ["java","-jar","target/jeto-1.0.0-BETA.jar", "-Xmx512m"]