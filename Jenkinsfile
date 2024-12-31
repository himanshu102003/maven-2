pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK_1.8'
    }
    environment {
        
        SONAR_TOKEN = credentials('sonarqube-token')
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout code from GitHub
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Build project using Maven
                bat 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('SonarQube-Scanner') {
                    bat '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=maven-automation \
                      -Dsonar.projectName='maven-automation' \
                      -Dsonar.sources=src/main/java/com/example/automation \
                      -Dsonar.host.url=http://localhost:9000 \
                      -Dsonar.login=sqp_367df051eb12fd8d4d4b20eb25a9bfd3151e3c34
                    '''
                }
            }
        }
        
    }
  post {
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
        always {
            echo 'This runs regardless of the result.'
        }
    }
}
