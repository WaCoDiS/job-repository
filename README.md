# WaCoDiS Job Definition API
![Java CI](https://github.com/WaCoDiS/metadata-connector/workflows/Java%20CI/badge.svg)
  
The **WaCoDiS Job Definition API** provides an API for managing processing jobs (WaCoDiS jobs). 
  
**Table of Content**  

1. [WaCoDiS Project Information](#wacodis-project-information)
  * [Architecture Overview](#architecture-overview)
2. [Overview](#overview)
  * [Modules](#modules)
  * [Utilized Technologies](#utilized-technologies)
3. [Installation / Building Information](#installation--building-information)
  * [Build from Source](#build-from-source)
  * [Build using Docker](#build-using-docker)
  * [Configuration](#configuration)
    * [Parameters](#parameters)
  * [Deployment](#deployment)
    * [Preconditions](#preconditions)
4. [User Guide](#user-guide)
  * [Run Job Definition API](#run-job-definition-api)
    * [Using Docker](#using-docker)
5. [Contribution - Developer Information](#contribution---developer-information)
  * [Branching](#branching) 
  * [License and Third Party Lib POM Plugins](#license-and-third-party-lib-pom-plugins)
6. [Contact](#contact)
7. [Credits and Contributing Organizations](#credits-and-contributing-organizations)


## WaCoDiS Project Information
<p align="center">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/wacodis.png" width="200">
</p>
Climate changes and the ongoing intensification of agriculture effect in increased material inputs in watercourses and dams.
Thus, water industry associations, suppliers and municipalities face new challenges. To ensure an efficient and environmentally
friendly water supply for the future, adjustments on changing conditions are necessary. Hence, the research project WaCoDiS
aims to geo-locate and quantify material outputs from agricultural areas and to optimize models for sediment and material
inputs (nutrient contamination) into watercourses and dams. Therefore, approaches for combining heterogeneous data sources,
existing interoperable web based information systems and innovative domain oriented models will be explored.

### Architecture Overview

For a detailed overview about the WaCoDiS system architecture please visit the 
**[WaCoDiS Core Engine](https://github.com/WaCoDiS/core-engine)** repository.  

## Overview  
The **WaCoDiS Job Definition API** stores processing jobs (WaCoDiS jobs) and provides an API for managing processing jobs. The API offers endpoints for creating, updating and deleting processing jobs as well as an endpoint to retrieve stored processing jobs. If a processing job is created or deleted the Job Definition component publish a notification ([WacodisJobDefinition](https://github.com/WaCoDiS/apis-and-workflows/blob/master/openapi/src/main/definitions/wacodis-schemas.yml)) via the WaCoDiS system's message broker (RabbitMQ). 
### Modules
The WaCoDiS Job Definition API is a stand-alone Spring Boot application comprisiung only a single module.
### Utilized Technologies
* Java  
WaCoDiS Job Definition API uses (as most of the WaCoDiS components) the java programming language. WaCoDiS Job Defintion API is tested with Oracle JDK 8 and OpenJDK 8. Unless stated otherwise later Java versions can be used as well.
* Maven  
The project WaCoDiS Job Definition API uses the build-management tool Apache [maven](https://maven.apache.org/)
* Spring Boot  
WaCoDiS Job Definition API is a standalone application built with the [Spring Boot](https://spring.io/projects/spring-boot) framework.
* Spring Cloud  
[Spring Cloud](https://spring.io/projects/spring-cloud) is used for exploiting some ready-to-use features in order to implement
an event-driven workflow. In particular, [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) is used
for subscribing to asynchronous messages within thw WaCoDiS system.
* RabbitMQ  
For communication with other WaCoDiS components of the WaCoDiS system the message broker [RabbitMQ](https://www.rabbitmq.com/) is utilized. RabbitMQ is not part of WaCoDiS Job Definition API and therefore [must be deployed separately](#preconditions) if WaCoDIS Job Definition API is deployed as part of the whole WaCoDiS system. 
* Elasticsearch
For storing processing jobs WaCoDiS Job Defintion API uses the search engine technology [Elasticsearch](https://www.elastic.co/downloads/elasticsearch). Elasticsearch is not part of WaCoDiS Job Definition API and therefore must be deployed separately in order [to run this application](#preconditions).  
* OpenAPI  
OpenAPI is used for the specification of core WaCoDiS data model and APIs.  


## Installation / Building Information
### Build from Source
In order to build the WaCoDiS Job Definition API from source _Java Development Kit_ (JDK) must be available. Job Definition API
is tested with Oracle JDK 8 and OpenJDK 8. Unless stated otherwise later JDK versions can be used.  

Since this is a Maven project, [Apache Maven](https://maven.apache.org/) must be available for building it. Then, you
can build the project by running `mvn clean install` from root directory

### Build using Docker
The project contains a Dockerfile for building a Docker image. Simply run `docker build -t wacodis/job-definition-api:latest .`
in order to build the image. You will find some detailed information about running the Job Definition API as Docker container
within the [run section](#using-docker) .

### Configuration
Configuration is fetched from [WaCoDiS Config Server](https://github.com/WaCoDiS/config-server). If config server is not
available, configuration values located at *src/main/resources/application.yml*.   
#### Parameters
The following section contains descriptions for configuration parameters structured by configuration section.

##### spring/cloud/stream/bindings/tools-execute
parameters related to messages on started processing jobs

| value     | description       | note  |
| ------------- |-------------| -----|
| destination     | topic used to receive messages about started WaCoDiS jobs | e.g. *wacodis.test.tools.execute* |
| binder      | defines the binder (message broker)   | |
| content-type      | content type of  DataEnvelope acknowledgement messages (mime type)   | should always be *application/json* |


##### spring/cloud/stream/bindings/wacodis.test.tools-finished
parameters related to message on successfully finished proessing jobs

| value     | description       | note  |
| ------------- |-------------| -----|
| destination     | topic used to receive message about successfully executed WaCoDiS jobs | e.g. *wacodis.test.tools.finished* |
| binder      | defines the binder (message broker)   |  |
| content-type      | content type of  DataEnvelope acknowledgement messages (mime type)   | should always be *application/json*  |

##### spring/cloud/stream/bindings/wacodis.test.tools-failure
parameters related to messages on failed processing jobs

| value     | description       | note  |
| ------------- |-------------| -----|
| destination     | topic used to receive messages about failed WacoDiS jobs| e.g. *wacodis.test.tools.failure* |
| binder      | defines the binder (message broker)   |  |
| content-type      | content type of  DataEnvelope acknowledgement messages (mime type)   | should always be *application/json*  |

##### spring/rabbitmq
parameters related to WaCoDis message broker

| value     | description       | note  |
| ------------- |-------------| -----|
| host | RabbitMQ host (WaCoDiS message broker) | e.g. *localhost* |
| port | RabbitMQ port (WaCoDiS message broker)   | e.g. *5672*|
| username | RabbitMQ username (WaCoDiS message broker)   | |
| password | RabbitMQ password (WaCoDiS message broker)   | |

##### spring/jobdefinitionapi
parameters to configure connection to WaCoDiS Job Manager

| value     | description       | note  |
| ------------- |-------------| -----|
| baseurl| base URL of Job Manager service  | e.g. *http://localhost:8080* |
| apiendpoint | API endpoint (path) for job status update   | e.g. */jobDefinitions/jobstatus/*|
| httpmethod | HTTP-method to be used   | optional, default is PATCH |



### Deployment
This section describes deployment scenarios, options and preconditions.
#### Preconditions
* (without using Docker) In order to run Job Definition API Java Runtime Environment (JRE) (version >= 8) must be available. In order to [build Job Definition API from source](#installation--building-information) Java Development Kit (JDK) version >= 8) must be abailable. Job Definition API is tested with Oracle JDK 8 and OpenJDK 8.
* A running instance of [elasticsearch](https://www.elastic.co/downloads/elasticsearch) must be available.  
* In order to receive message a running instance a running instance of [RabbitMQ message broker](https://www.rabbitmq.com/) must be available.  

  
The server addresses are [configurable](#configuration).  
  
 * If [configuration](#configuration) should be fetched from Configuration Server a running instance of [WaCoDiS Config Server](https://github.com/WaCoDiS/config-server) must be available.

## User Guide
### Run Job Definition API
Currently there are no pre-compiled binaries available for WaCoDiS Job Definition API. Job Definition API must be [built from source](#installation--building-information). Alternatively Docker can be used to (build and) run WaCoDiS Job Definition API.

Job Definition API is a Spring Boot application. Execute the compiled jar (`java -jar  job-definition-api.jar`) or run *de.wacodis.jobdefinition.JobDefinitionApiApplication.java* in IDE to start the Job Definition API.

#### Using Docker
1. Build Docker Image from [Dockerfile](https://github.com/WaCoDiS/job-definition-api/blob/master/Dockerfile) that resides in the project's root folder.
2. Run created Docker Image.

Alternatively, latest available docker image (automatically built from master branch) can be pulled from [Docker Hub](https://hub.docker.com/r/wacodis/job-definition-api). See [WaCoDiS Docker repository](https://github.com/WaCoDiS/wacodis-docker) for pre-configured Docker Compose files to run WaCoDiS system components and backend services (RabbitMQ and Elasticsearch).

## Contribution - Developer Information
This section contains information for developers.

### Branching
The master branch provides sources for stable builds. The develop branch represents the latest (maybe unstable) state of development.

### License and Third Party Lib POM Plugins
[optional]

## Contact
|    Name   |   Organization    |    Mail    |
| :-------------: |:-------------:| :-----:|
| Sebastian Drost | Bochum University of Applied Sciences | sebastian.drost@hs-bochum.de |
| Arne Vogt | Bochum University of Applied Sciences | arne.vogt@hs-bochum.de |
| Andreas Wytzisk  | Bochum University of Applied Sciences | andreas.wytzisk@hs-bochum.de |
| Matthes Rieke | 52° North GmbH | m.rieke@52north.org |

## Credits and Contributing Organizations
- Department of Geodesy, Bochum University of Applied Sciences, Bochum
- 52° North Initiative for Geospatial Open Source Software GmbH, Münster
- Wupperverband, Wuppertal
- EFTAS Fernerkundung Technologietransfer GmbH, Münster

The research project WaCoDiS is funded by the BMVI as part of the [mFund programme](https://www.bmvi.de/DE/Themen/Digitales/mFund/Ueberblick/ueberblick.html)  
<p align="center">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/mfund.jpg" height="100">
  <img src="https://raw.githubusercontent.com/WaCoDiS/apis-and-workflows/master/misc/logos/bmvi.jpg" height="100">
</p>

