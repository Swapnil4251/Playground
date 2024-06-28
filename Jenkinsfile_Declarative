pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Build the application
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run the tests
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                // Deploy the application
                sh 'deploy.sh'
            }
        }
    }
}
