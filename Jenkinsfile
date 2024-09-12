pipeline {
    agent any
    tools {
        maven 'Maven 3.9.9'  // Replace with the name you used in Global Tool Configuration
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from Git
                git branch: 'main', url: 'https://github.com/saichaitanya50/EverestTechnologies-SpringBoot-API.git'
            }
        }
        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run tests
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy to a server (customize as needed)
                echo 'Deploying...'
            }
        }
    }
    post {
        always {
            // Actions that should always be performed, such as cleaning up
            cleanWs()
        }
    }
}
