If the container is based on Alpine:
kubectl exec -it <pod-name> -- apk add --no-cache curl

If it's based on Debian/Ubuntu:
kubectl exec -it <pod-name> -- apt update && apt install -y curl

kubectl rollout restart deployment springboot-config-demo




docker
-------------
docker ps -a --filter ancestor=sandundocker1976/springbootdockerdemo:latest

& minikube -p minikube docker-env --shell powershell | Invoke-Expression


 Id CommandLine
  -- -----------
   1 & minikube -p minikube docker-env --shell powershell | Invoke-Expression
   2 docker build -t springboot-configmap-demo .
   3 mvn clean package
   4 docker build -t springboot-configmap-demo .
   5 kubectl apply -f app-configmap.yml
   6 docker images
   7 docker images config
   8 docker images springboot-configmap*
   9 kubectl apply -f app-deployment.yml
  10 kubectl get pods
  11 docker images springboot-configmap*
  12 kubectl apply -f app-deployment.yml
  13 kubectl get pods
  14 docker tag springboot-configmap-demo springboot-configmap-demo:latest
  15 docker images springboot-configmap*
  16 kubectl apply -f app-deployment.yml
  17 kubectl get pods
  18 kubectl delete -f app-deployment.yml
  19 kubectl get pods
  20 kubectl apply -f app-deployment.yml
  21 kubectl get pods
  22 kubectl get pods
  23 kubectl get pods
  24 kubectl get pods
  25 kubectl get pods
  26 kubectl get pods
  27 kubectl delete -f app-deployment.yml
  28 docker images springboot-configmap*
  29 docker rmi 1a793f2000f7
  30 docker images springboot-configmap*
  31 docker build -t springboot-configmap-demo:latest .
  32 docker images springboot-configmap*
  33 kubectl delete pod -l app=springboot-config-demo
  34 kubectl apply -f app-deployment.yml
  35 kubectl get pods
  36 docker images
  37 kubectl delete pod -l app=springboot-config-demo
  38 docker images springboot-configmap*
  39 docker rmi 1a793f2000f7
  40 docker build -t springboot-configmap-demo:1.0 .
  41 docker images springboot-configmap*
  42 kubectl apply -f app-deployment.yml
  43 kubectl get pods
  44 docker tag springboot-configmap-demo springboot-configmap-demo:1.0
  45 docker tag springboot-configmap-demo:1.0  springboot-configmap-demo:1.0
  46 kubectl apply -f app-deployment.yml
  47 kubectl get pods
  48 kubectl delete pod -l app=springboot-config-demo
  49 kubectl get pods
  50 kubectl get pods
  51 kubectl get pods
  52 kubectl get pods
  53 kubectl get pods
  54 kubectl get pods
  55 kubectl get pods
  56 kubectl get pods
  57 kubectl delete pod -l app=springboot-config-demo
  58 kubectl delete -f app-deployment.yml
  59 kubectl get pods
  60 kubectl apply -f app-deployment.yml
  61 kubectl get pods
  62 kubectl get pods
  63 kubectl apply -f app-service.yml
  64 kubectl get svc
  65 minikube ip
  66 kubectl apply -f app-service.yml
  67 kubectl apply -f app-service.yml
  68 kubectl get svc
  69 kubectl get services
  70 kubectl get pods
  71 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- /bin/sh
  72 kubectl apply -f app-configmap.yml
  
  

  Id CommandLine
  -- -----------
   1 kubectl get svc
   2 docker info
   3 history
   4 & minikube -p minikube docker-env --shell powershell | Invoke-Expression
   5 kubectl get pods
   6 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
   7 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
   8 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
   9 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
  10 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
  11 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
  12 kubectl rollout restart deployment springboot-config-demo
  13 kubectl get pods
  14 kubectl get pods
  15 kubectl exec -it springboot-config-demo-5f844f45cd-6c95b -- curl http://localhost:8080/message
  16 kubectl exec -it springboot-config-demo-58c787bfbc-wh4nd -- curl http://localhost:8080/message
  



pipeline {
    agent any

    tools {
        maven 'M3'
    }

    environment {
        IMAGE_NAME = "springboot-demo"
        VERSION = "1.0"
    }

    stages {
        stage('Clone') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sandunwclc/springboot-demo']])
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$VERSION .'
            }
        }

        stage('Run Docker Image') {
            steps {
                sh 'docker run --rm -d -p 8081:8080 --name app $IMAGE_NAME'
            }
        }
        
        stage('Test with curl') {
            steps {
                // Give the app a few seconds to start up
                sh 'sleep 2'
                // Use curl to test the endpoint
                sh 'curl http://localhost:8081/api || curl http://host.docker.internal:8081/api'
            }
        }
    }

    post {
        always {
            sh 'docker stop app || true'
        }
    }
}


pipeline {
  agent any
  stages {
    stage('Clone') {
      steps {
        git 'https://github.com/your-username/springboot-demo.git'
      }
    }
    stage('Build') {
      steps {
        sh './mvnw clean package -DskipTests'
      }
    }
    stage('Docker Build') {
      steps {
        sh 'docker build -t springboot-demo:latest .'
      }
    }
    stage('K8s Deploy') {
      steps {
        sh 'kubectl apply -f k8s-deployment.yaml'
      }
    }
  }
}


pipeline {
    agent any

    tools {
        maven 'M3'
    }

    environment {
        IMAGE_NAME = "springboot-demo"
        VERSION = "1.0"
    }

    stages {
        stage('Clone') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sandunwclc/springboot-demo']])
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$VERSION .'
            }
        }

        stage('Run Docker Image') {
            steps {
                sh 'docker run --rm -d -p 8081:8080 --name app $IMAGE_NAME'
            }
        }
        
        stage('Test with curl') {
            steps {
                // Give the app a few seconds to start up
                sh 'sleep 2'
                // Use curl to test the endpoint
                sh 'curl http://localhost:8081/api || curl http://host.docker.internal:8081/api'
            }
        }
    }

    post {
        always {
            sh 'docker stop app || true'
        }
    }
}


apt-get update && apt-get install -y curl gnupg apt-transport-https ca-certificates

curl -fsSLo /usr/share/keyrings/kubernetes-archive-keyring.gpg https://packages.cloud.google.com/apt/doc/apt-key.gpg

echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" \
> /etc/apt/sources.list.d/kubernetes.list

apt-get update && apt-get install -y kubectl

----------------------------------------------------
apt-get update && apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg

# 2. Add the Kubernetes GPG key properly
curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.30/deb/Release.key | gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg

# 3. Add the repository using the new GPG location
echo "deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.30/deb/ /" > /etc/apt/sources.list.d/kubernetes.list

# 4. Update and install kubectl
apt-get update && apt-get install -y kubectl





mkdir -p /tmp/kube
cp /root/.kube/config /tmp/kube/config

sed -i 's|C:\\\\Users\\\\srabe|/root|g' /tmp/kube/config
sed -i 's|\\\\|/|g' /tmp/kube/config
sed -i 's|127\.0\.0\.1:[0-9]\+|192.168.49.2:8443|g' /tmp/kube/config

kubectl get nodes --kubeconfig=/tmp/kube/config





S C:\sandun\jenkins-docker-maven> history

  Id CommandLine
  -- -----------
   1 docker-compose up -d
   2 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   3 docker ps
   4 docker exec jenkins-docker-maven-jenkins cat /var/jenkins_home/secr...
   5 docker exec e7e707e98fca cat /var/jenkins_home/secrets/initialAdmin...
   6 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
   7 docker exec jenkins --bash
   8 docker exec jenkins bash
   9 docker exec jenkins -bash
  10 docker exec -it jenkins -bash
  11 docker exec -it jenkins bash
  12 docker-compose up -d
  13 docker ps
  14 docker exec -it jenkins bash
  15 docker-compose build --no-cache
  16 docker-compose up -d
  17 docker-compose up -d
  18 docker-compose build --no-cache
  19 docker-compose up -d
  20 docker-compose down -v
  21 docker-compose build --no-cache
  22 docker-compose up -d
  23 docker-compose down -v
  24 docker-compose build --no-cache
  25 docker-compose up -d
  26 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  27 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  28 docker exec -it jenkins bash
  29 docker build --build-arg DOCKER_GID=$(getent group docker | cut -d:...
  30 docker exec -it jenkins bash
  31 docker ps
  32 docker-compose up -d
  33 docker exec -it jenkins bash
  34 docker-compose down -v
  35 docker-compose build --no-cache
  36 docker-compose up -d
  37 docker-compose down -v
  38 docker-compose build --no-cache
  39 docker-compose up -d
  40 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  41 docker-compose build --build-arg DOCKER_GID=$DOCKER_GID
  42 export DOCKER_GID=999
  43 docker-compose down -v
  44 docker-compose up -d
  45 docker-compose build --build-arg DOCKER_GID=$DOCKER_GID
  46 docker-compose up -d
  47 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  48 docker exec -it jenkins bash
  49 docker-compose down -v
  50 docker-compose build --no-cache
  51 docker-compose up -d
  52 docker exec -it jenkins bash
  53 docker-compose down -v
  54 docker-compose build --no-cache
  55  docker-compose up -d
  56 docker exec -it jenkins bash
  57 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  58 docker exec -it jenkins bash
  59 docker-compose down -v
  60 docker-compose build --no-cache
  61 docker-compose build --no-cache
  62 docker-compose build --no-cache
  63 docker-compose build --no-cache
  64 docker-compose down -v
  65 docker-compose build --no-cache
  66 docker-compose build --no-cache
  67 docker-compose build --no-cache
  68 docker-compose build --no-cache
  69 docker build -t jenkins-kubectl .
  70 docker-compose build --no-cache
  71  docker-compose up -d
  72 docker exec -it jenkins bash
  73 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
  74 docker exec -it jenkins bash
  75 docker-compose build --no-cache
  76  docker-compose up -d
  77 docker exec -it jenkins bash
  78 docker-compose build --no-cache
  79  docker-compose up -d
  80 docker exec -it jenkins bash
  81 docker-compose build --no-cache
  82  docker-compose up -d
  83 docker exec -it jenkins bash
  84 docker-compose build --no-cache
  85  docker-compose up -d
  86 docker exec -it jenkins bash
  87 docker-compose build --no-cache
  88  docker-compose up -d
  89 docker exec -it jenkins bash
  90 docker-compose build --no-cache
  91  docker-compose up -d
  92 docker exec -it jenkins bash
  93 docker-compose down
  94 docker-compose up -d --build
  95 docker-compose down -v
  96 docker-compose down -v
  97 docker-compose build --no-cache
  98  docker-compose up -d
  99 docker-compose build --no-cache
 100  docker-compose up -d --build
 101 docker network ls
 102 docker network inspect bridge
 103 docker network create --subnet=10.77.0.0/16 isolated-net
 104 docker network rm isolated-net
 105 docker network create --subnet=10.77.0.0/16 isolated-net
 106  docker-compose up -d --build
 107 docker exec -it jenkins bash
 108 docker-compose down
 109 docker-compose up -d --build
 110 docker-compose up -d --build
 111 docker exec -it jenkins bash
 112 docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
 113 docker exec -it jenkins bash
 114 :qw
 115 docker exec -it jenkins bash
 116 clear
 117 docker-compose down
 118 docker-compose up -d
 119 docker-compose down
 120 docker-compose build --no-cache
 121 docker-compose up -d
 122 docker-compose up -d
 123  & minikube -p minikube docker-env --shell powershell | Invoke-Expr...
 124 docker info
 125 docker-compose build --no-cache
 126 docker images
 127 docker run -d -p 8080:8080 --name jenkins \
 128   -v jenkins_home:/var/jenkins_home \
 129   -v /var/run/docker.sock:/var/run/docker.sock \
 130   -v ~/.kube:/root/.kube \
 131   -v ~/.minikube:/root/.minikube \
 132   jenkins/jenkins:lts
 133 docker run -d -p 8080:8080 --name jenkins -v jenkins_home:/var/jenk...
 134 docker infor
 135 docker info
 136 minikube
 137 docke info
 138 docker info
 139 docker ps
 140 docker run -d -p 8080:8080 --name jenkins jenkins/jenkins:lts
 141 minikube ssh
 142 curl http://localhost:8080
 143 minikube ssh
 144 docker-compose build --no-cache
 145 docker info
 
 
 
   Id CommandLine
  -- -----------
   1 & minikube -p minikube docker-env --shell powershell | Invoke-Expression
   2 kubectl apply -f k8s/deployment.yaml
   3 kubectl get pods
   4 kubectl delete -f k8s/deployment.yaml
   5 kubectl get pods
   6 kubectl delete -f k8s/deployment.yaml
   7 kubectl apply -f k8s/deployment.yaml
   8 kubectl get pods
   9 kubectl get pods
  10 kubectl get pods
  11 kubectl get pods
  12 kubectl get pods
  13 docker build -t demo-minikube:latest .
  14 kubectl apply -f k8s/deployment.yaml
  15 kubectl get pods
  16 kubectl delete -f k8s/deployment.yaml
  17 kubectl apply -f k8s/deployment.yaml
  18 kubectl get pods
  19 kubectl get pods
  20 kubectl get pods
  21 kubectl get pods
  22 kubectl get pods
  23 kubectl get pods
  24 kubectl get pods
  25 docker images
  26 docker images demo-minikube
  27 docker build -t demo-minikube:latest .
  28 docker images
  29 docker images demo-minikube
  30 kubectl apply -f k8s/deployment.yaml
  31 kubectl get pods
  32 kubectl exec -it demo-deployment-86d86dc8d-7q4lq bash
  33 kubectl exec -it demo-deployment-86d86dc8d-7q4lq -- bash
  34 docker build -t demo-minikube:latest .
  35 kubectl get pods
  36 kubectl apply -f k8s/deployment.yaml
  37 kubectl delete -f k8s/deployment.yaml
  38 kubectl apply -f k8s/deployment.yaml
  39 kubectl get pods
  40 kubectl exec -it demo-deployment-86d86dc8d-vvdpp -- bash
  41 kubectl exec -it demo-deployment-86d86dc8d-vvdpp -- curl http://localhost:8080/hello
  42 kubectl apply -f k8s/*
  43 kubectl apply -f k8s/service.yaml
  44 kubectl apply -f k8s/service.yaml
  45 kubectl get svc
  46 minikube service demo-service
  47 choco install kubernetes-helm
  48 scoop install helm
  49  docker build -t demo-minikube:1.0 .
  50 helm create demo-chart
  51 helm install demo-app ./demo-chart
  52 helm install demo-app ./demo-chart
  53 helm install demo-app ./demo-chart
  54 helm install demo-app ./demo-chart
  55 helm install demo-app ./demo-chart
  56 helm install demo-app ./demo-chart
  57 kubectl delete -f k8s/deployment.yaml
  58 kubectl delete -f .\k8s\service.yaml
  59 helm install demo-app ./demo-chart
  60 helm uninstall demo-app
  61 helm install demo-app ./demo-chart
  62 helm list
  63 kubectl get pods
  64 helm install demo-app ./demo-chart
  65 helm uninstall demo-app
  66 helm install demo-app ./demo-chart
  67 helm install demo-app ./demo-chart
  68 helm create demo-chart1
  69 helm install demo-app ./demo-chart
  70 kubectl get pods
  71 kubectl get pods
  72 docker build -t demo-app:1.0 .
  73 helm install demo-app ./demo-chart
  74 helm uninstall demo-app
  75 helm install demo-app ./demo-chart
  76 kubectl get pods
  77 kubectl get svc
  78 minikube service demo-app-service
  79 minikube service demo-app-service
  80 kubectl get svc
  81 helm uninstall demo-app
  82 kubectl get pods
  83 kubectl get pods
  84 kubectl get pods
  85 kubectl get pods
  
  PS C:\sandun\jenkins-docker-maven\jenkin1> docker run -d --name jenkins1 -p 8081:8080 -p 50000:50000 -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home -u root jenkins-with-docker
  
  docker run -d --name jenkins1 -p 8081:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
  
  docker run -d --name jenkins -p 8081:8080 -p 50000:50000  -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts


docker run -d --name jenkins1 -p 8081:8080 -p 50000:50000 -v /var/run/docker.sock:/var/run/docker.sock -v jenkins_home:/var/jenkins_home jenkins-with-docker


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
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
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
  
   