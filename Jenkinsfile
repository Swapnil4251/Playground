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
            steps {
                echo "Building the image from Dockerfile"
                script {
                    docker.withRegistry('http://127.0.0.1:5000') {
                        docker.build("node-app", "-f ./NodeJs/Dockerfile ./NodeJs").push("latest")
                    }
                }
            }
        }
    }
}
