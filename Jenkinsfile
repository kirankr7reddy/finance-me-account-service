pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'kiranreddykr7'
        IMAGE_NAME = 'financeme-account-service'
        IMAGE_TAG = 'latest'
        DOCKER_IMAGE = "${DOCKERHUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}"
    }

    tools {
        maven 'Maven 3'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/kirankr7reddy/finance-me-account-service.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
                sh 'mvn test'
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin'
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                sshagent(['ec2-ssh-key']) {
                    withEnv(["ANSIBLE_HOST_KEY_CHECKING=False"]) {
                        sh 'ansible-playbook -i ansible/hosts.ini ansible/setup.yml'
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment completed successfully!'
        }
        failure {
            echo '❌ Deployment failed!'
        }
    }
}

