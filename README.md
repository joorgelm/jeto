<p align="center">
<img src="./assets/logo-transp.png" alt="Alt Text" width="65%">
</p>

# Jeto: Java OCR Rest API

## What is it?

A Java Rest API built with [Tess4j](https://github.com/nguyenq/tess4j) for text extraction from images and pdf files.

## Supported formats

* TIFF, JPEG, GIF, PNG, and BMP image formats
* Multi-page TIFF images
* PDF document format

## Built with

* Java 17
* Spring Boot 3
* Tess4j
* Tesseract-ocr
* Maven
* Docker

## Requisites
To run the project on a unix operating system you need

* docker
* docker-compose
* Maven
* Java 17
* permissions for docker to run without sudo

## Example

### Extract text from a pdf document
`POST /v1/jeto/extract`

```
curl --request POST \
  --url http://localhost:3000/v1/jeto/extract \
  --header 'Content-Type: multipart/form-data' \
  --form file=file.pdf
```

```
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 3145
Date: Sat, 24 Jun 2023 21:00:56 GMT
```

## Author
Jorge Melgarejo