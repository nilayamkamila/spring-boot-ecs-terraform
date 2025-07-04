# Prerequisites
- Java 17
- Maven
- Docker
- AWS CLI
- Terraform
# How to Build
```
mvn --version
mvn clean install
```
# How to Run Local
```
mvn --version
mvn clean install
docker buildx build -t my-spring-app:latest .
docker run -p 8080:8080 my-spring-app:latest

```
#Note: If you use for ECS build, please follow the below command
```
docker buildx build --platform linux/amd64 -t my-spring-app:latest .
```


# How to Push to ECR
- Check if ECR repository exists, if not create it:
```
aws ecr create-repository --repository-name docker-artifact-hub/my-spring-app
```
- Authenticate Docker and Push to ECR:
```
aws --version
aws ecr get-login-password | docker login --username AWS --password-stdin 337550871092.dkr.ecr.us-east-1.amazonaws.com
docker images
Docker Image (my-spring-app:latest) Built in local run (through docker buildx build --platform linux/amd64 -t my-spring-app:latest .)
docker tag my-spring-app:latest 337550871092.dkr.ecr.us-east-1.amazonaws.com/docker-artifact-hub/my-spring-app:latest
Or 
docker buildx build --platform linux/amd64 -t 337550871092.dkr.ecr.us-east-1.amazonaws.com/docker-artifact-hub/my-spring-app:latest .
docker push 337550871092.dkr.ecr.us-east-1.amazonaws.com/docker-artifact-hub/my-spring-app:latest
```

# How to Deploy to ECS
```
cd terraform
terraform --version
terraform init
terraform plan
terraform apply
```
# How to Deploy to Delete
```
terraform --version
terraform destroy
```