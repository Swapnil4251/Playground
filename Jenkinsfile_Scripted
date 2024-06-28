node {
    stage('Build') {
        // Build the application
        sh 'mvn clean install'
    }
    stage('Test') {
        // Run the tests
        sh 'mvn test'
    }
    stage('Deploy') {
        // Deploy the application
        sh 'deploy.sh'
    }
}
