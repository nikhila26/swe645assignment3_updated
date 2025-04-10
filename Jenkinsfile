pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Ensure 'dockerhub' is configured in Jenkins
    }
    stages {
        stage('Clean Up Old Docker Image') {
            steps {
                // Remove any existing Docker images if they exist
                sh 'docker rmi -f satluri2/survey:latest || true'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build the Docker image using the Dockerfile
                sh 'docker build --no-cache -t satluri2/survey:latest .'
            }
        }

        stage('Docker Login') {
            steps {
                // Log in to Docker Hub using credentials stored in Jenkins
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                // Push the Docker image to Docker Hub
                sh 'docker push satluri2/survey:latest'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Update Kubernetes deployment with the new Docker image
                sh 'kubectl set image deployment/surveyassign3-deploy container-0=satluri2/survey:latest -n default'

                // Roll out the deployment to apply the new image
                sh 'kubectl rollout restart deployment/surveyassign3-deploy -n default'

                // Verify rollout status
                sh 'kubectl rollout status deployment/surveyassign3-deploy -n default'
            }
        }
    }

    post {
        always {
            // Ensure Docker logout happens regardless of the outcome
            sh 'docker logout'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
    }
}
