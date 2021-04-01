pipeline {
    agent any
    environment {
        APPLICATION_NAME = 'utopiapassengerms'
        APPLICATION_REPOSITORY = 'utopia/utopiapassengerms'
        COMMIT_HASH = "${sh(script:'git rev-parse --short HEAD', returnStdout: true).trim()}"
    }

    stages {
        stage('Package') {
            steps {
                echo 'Building..'
                script {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build') {
            steps {
                echo 'Deploying....'
                sh "$AWS_LOGIN"
                sh "docker build --tag $APPLICATION_NAME:$COMMIT_HASH ."
                sh "docker tag $APPLICATION_NAME:$COMMIT_HASH $AWS_ID/$APPLICATION_REPOSITORY:$COMMIT_HASH"
                sh "docker push $AWS_ID/$APPLICATION_REPOSITORY:$COMMIT_HASH"
            }
        }
        stage('Deploy') {
            steps {
                // Grabs the Cloud Formation Template
                sh 'touch CloudDeploymentTemplate.yml'
                sh 'rm CloudDeploymentTemplate.yml'
                sh 'touch UtopiaAirlinesServicesTemplate.yml'
                sh 'rm UtopiaAirlinesServicesTemplate.yml'
                sh 'wget https://raw.githubusercontent.com/SmoothstackUtopiaProject/CloudFormationTemplates/main/UtopiaAirlinesServicesTemplate.yml'
                sh 'mv UtopiaAirlinesServicesTemplate.yml CloudDeploymentTemplate.yml'

                // Grabs the Cloud Deployment Script
                sh 'touch CloudDeploy.sh'
                sh 'rm CloudDeploy.sh'
                sh 'wget https://raw.githubusercontent.com/SmoothstackUtopiaProject/CloudFormationTemplates/main/CloudDeploy.sh'
                sh 'chmod 777 ./CloudDeployment.sh'
                sh 'exec ./CloudDeployment.sh'
            }
        }
        stage('Cleanup') {
            steps {
                sh 'docker system prune -f'
            }
        }
    }
}
