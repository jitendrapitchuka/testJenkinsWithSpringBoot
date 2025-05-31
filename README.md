> **📝 Jenkins Docker Pipeline Setup Notes**

---

### 1. Run Jenkins Docker Container as Root and Map Home Directory
```bash
docker run -d
--name jenkins
--user root
-p 8080:8080 -p 50000:50000
-v /your/local/jenkins_home:/var/jenkins_home
jenkins/jenkins:lts
```


- `--user root` runs Jenkins as root, allowing Docker installation inside the container.
- `-v /your/local/jenkins_home:/var/jenkins_home` persists Jenkins data to your host.

---

### 2. Jenkins Initial Setup

- Access Jenkins at `http://localhost:8080`
- Complete the setup wizard.
- Go to **Manage Jenkins** → **Global Tool Configuration**
  - Add **JDK** (e.g., OpenJDK 21)
  - Add **Maven** (e.g., Maven 3.8.8)

---


### 3. Install Docker Inside Jenkins Container
```bash
docker exec -it jenkins bash
apt-get update
apt-get install -y docker.io
exit
```

---

### 4. Pipeline Workflow

- Use your Jenkins pipeline to:
  - Build the JAR file with Maven.
  - Transfer the JAR to your EC2 instance (e.g., using `scp`).
  - SSH into the EC2 instance and run the JAR.

---

####  Pipeline  script

``` groovy

pipeline {
    agent any
    tools{
        maven 'maven'
        jdk 'jdk21'
    }
    
     environment {
        EC2_USER = credentials('EC2_USER')
        EC2_IP = credentials('EC2_IP')
    }
    
    stages {
        stage('Clone Repo') {
            steps {
                git url:'https://github.com/jitendrapitchuka/testJenkinsWithSpringBoot.git',branch:'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t my-springboot-app .'
            }
        }
    
    stage('push to ec2'){
        steps{
             withCredentials([file(credentialsId: 'Key', variable: 'Key')]){
            sh '''
            scp -o StrictHostKeyChecking=no -i ${Key} my-springboot-app.tar ${EC2_USER}@${EC2_IP}:/home/${EC2_USER}
            ssh -o StrictHostKeyChecking=no -i ${Key} ${EC2_USER}@${EC2_IP} "
                sudo docker load < /home/${EC2_USER}/my-springboot-app.tar &&
                sudo docker stop my-springboot-app || true &&
                sudo docker rm my-springboot-app || true &&
                sudo docker run -d --name my-springboot-app -p 8082:8082 my-springboot-app
"
'''

             }
        }
    }
        
    
}
}
```
---

### 5. Prepare Your EC2 Instance

- **Install Docker:**  
  Ensure Docker is installed and running on your EC2 instance.  

---

### 6. Deploy and Run

- The Jenkins pipeline will build your Spring Boot application, create a Docker image, and deploy it to your EC2 instance.
- Ensure your EC2 instance's security group allows inbound traffic on the necessary ports (e.g., 8082 for your Spring Boot app).

---

### 7. Triggering the Pipeline

- You can trigger the Jenkins pipeline manually from the Jenkins UI.
- Or, set up a webhook in your GitHub repository to automatically trigger the build on every code push.

---
### 8. Progress Tracker

- ✅ **Using Above steps pipeline construction and deployment in Ec2 are done**
- 🔄 **Integrate MySQL into the project**
-  🔄 **Build JAR with MySQL integration and deploy to EC2 using the pipeline**
- 🔄 **Set up GitHub Actions trigger for automation**








