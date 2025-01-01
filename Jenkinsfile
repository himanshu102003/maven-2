pipeline {
    agent any
    tools {
        maven 'Maven'  // Ensure Maven tool is configured in Jenkins
        jdk 'JDK_1.8'  // Ensure JDK 1.8 is configured in Jenkins
    }
    environment {
        SONAR_TOKEN = credentials('sonarqube-token')
        SONARQUBE_SERVER = 'sonarqube-server' // Replace with your SonarQube server URL
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
                // Build the project and run tests
                bat 'mvn clean test'
            }
        }
        stage('Generate Coverage Report') {
            steps {
                // Generate coverage report using JaCoCo
                bat 'mvn jacoco:report'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('SONARQUBE_SERVER') {
                    bat """
                    mvn sonar:sonar \
                      -Dsonar.projectKey=maven-aut2 \
                      -Dsonar.projectName='maven-aut2' \
                      -Dsonar.sources=src/main/java \
                      -Dsonar.tests=src/test/java \
                      -Dsonar.java.binaries=target/classes \
                      -Dsonar.coverage.jacoco.xmlReportPaths=${basedir}/coverage.xml \
                      -Dsonar.host.url=http://localhost:9000 \
                      -Dsonar.login=%SONAR_TOKEN%
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
