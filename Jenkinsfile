pipeline {
    agent any

    tools {
        nodejs 'NodeJS-21.7.1'
    }

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

        stage('Build image') {
            step {
                docker.withRegistry('http://registry.local:5000') {
                    docker.build("getintodevops/hellonode").push("latest")
                }
            }
        }
    }
}
