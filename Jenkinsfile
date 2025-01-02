pipeline {
    agent any
    tools {
        maven 'Maven'  // Ensure Maven tool is configured in Jenkins
        jdk 'JDK_1.8'  // Ensure JDK 1.8 is configured in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonarqube-token')  // SonarQube token
        SONARQUBE_SERVER = 'sonarqube-server' // SonarQube server URL
        PATH = "C:\\Windows\\System32;${env.PATH}" // Ensure cmd is in the PATH (Windows)
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm  // Checkout the source code
            }
        }
        stage('Build') {
            steps {
                script {
                    echo 'Running Maven Build...'
                    bat 'mvn clean test'  // Windows-based command to build and test with Maven
                }
            }
        }
        stage('Generate Coverage Report') {
            steps {
                script {
                    echo 'Generating Coverage Report using JaCoCo...'
                    bat 'mvn jacoco:report'  // Generate coverage report using JaCoCo
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    echo 'Running SonarQube Analysis...'
                withSonarQubeEnv('sonarqube-server') {
                    bat """
                    mvn clean verify sonar:sonar \
                      -Dsonar.projectKey=maven-aut2 \
                      -Dsonar.projectName='maven-aut2' \
                      -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
                      -Dsonar.host.url=http://localhost:9000 \
                      -Dsonar.login=sqp_6d8f7f1d05d6cb0135d16104f56345d4c6826aac
                    """
                }
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
