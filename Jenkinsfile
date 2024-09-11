pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from Git
                git branch: 'main', url: 'https://github.com/yourusername/yourrepo.git'
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
