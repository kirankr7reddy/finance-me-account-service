pipeline {
    agent any

    tools {
        maven 'Maven 3'  
    }

    environment {
        GIT_REPO = 'https://github.com/kirankr7reddy/finance-me-account-service.git'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: "${GIT_REPO}"
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Run TestNG & Generate HTML Report') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Package Application') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build and Test completed successfully!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
