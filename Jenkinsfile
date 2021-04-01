pipeline {
    agent any
    environment {
        APPLICATION_NAME="UtopiaPassengerMS"
        APPLICATION_REPOSITORY="utopia/utopiapassengerms"
        COMMIT_HASH="${sh(script:'git rev-parse --short HEAD', returnStdout: true).trim()}"
    }

    stages {
        stage('Package') {
            steps {
                echo 'Building..'
                script {
                    sh "mvn clean package"
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
               sh "touch ECSService.yml"
               sh "rm ECSService.yml"
               sh "wget https://raw.githubusercontent.com/SmoothstackUtopiaProject/CloudFormationTemplates/main/ECSService.yml"
               ./CloudFormation.sh.execute();
           }
        }
        stage('Cleanup') {
            steps {
                sh "docker system prune -f"
            }
        }
    }
}
