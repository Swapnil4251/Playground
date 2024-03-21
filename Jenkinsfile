pipeline {
    agent any

    stages {
        stage("Build application") {
            steps {
                echo "Building the application"
                sh """
                    cd NodeJs
                    npm install
                """
            }
        }
    }
}
