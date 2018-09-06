# Job Repository API
The WaCoDiS Job Repository API provides capabilities for managing processing job.
## Implementation
### Dropwizard
The implementation in this branch is based on the [Dropwizard](https://www.dropwizard.io) framework, that provides a light-weight bundle of stable libraries for developing stable microservices.
#### Quick Start
Just `mvn package` to build a fat Jar. After the build process you can run the application with `java -jar ./target/job-repository-0.0.1-SNAPSHOT.jar server ./jobrepositoryConfig.yml`.  
The application servlets then will be available at `http://localhost:8080/jobrepository/` (e.g. http://localhost:8080/jobrepository/jobs).  
The administration servlets will be available at http://localhost:8080/admin/.
