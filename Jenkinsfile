pipeline {
    agent any

    tools {
        maven 'M3'
    }

    environment {
        IMAGE_NAME = "springboot-demo"
    }

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/sandunwclc/springboot-demo.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Docker Image') {
            steps {
                sh 'docker run --rm -d -p 8081:8080 --name app $IMAGE_NAME'
            }
        }
    }

    post {
        always {
            sh 'docker stop app || true'
        }
    }
}
