pipeline {
    agent any
    tools {
        maven 'Maven'  // Ensure Maven tool is configured in Jenkins
        jdk 'JDK_1.8'  // Ensure JDK 1.8 is configured in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonarqube-token')  // Securely load SonarQube token
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
                bat 'mvn clean package'  // Use `sh` for Unix-based systems
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('SonarQube-Scanner') {
                    bat """
                    mvn sonar:sonar \
                      -Dsonar.projectKey=maven-aut \
                      -Dsonar.projectName='maven-aut' \
                      -Dsonar.sources=src/test/java/com/example/automation \
                      -Dsonar.host.url=http://localhost:9000 \
                      -Dsonar.login=sqp_e67734429c7116e33c331329b574a05da10389ff
                    """
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
