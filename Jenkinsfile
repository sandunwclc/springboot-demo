pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "springboot-demo:latest"
    }

    stages {
        
        stage('Clean workspace') {
            steps {
                deleteDir()
            }
        }
        
        stage('Checkout') {
            steps {
                checkout([
                  $class: 'GitSCM',
                  branches: [[name: '*/main']],
                  userRemoteConfigs: [[
                    url: 'https://github.com/sandunwclc/springboot-demo',
                    credentialsId: 'github-creds'
                  ]]
                ])
            }
        }
        
        stage('Build') {
            steps {
				sh 'echo "sandun...................................."'
                sh 'chmod +x mvnw'
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}")
                }
            }
        }

       stage('Run Container') {
    steps {
        sh '''
        if [ "$(docker ps -aq -f name=springboot-demo)" ]; then
            docker stop springboot-demo
            docker rm springboot-demo
        else
            echo "No existing springboot-demo container found."
        fi

        docker run -d --name springboot-demo -p 8082:8080 springboot-demo:latest
        '''
    }
}

    }
}
  