// vars/dockerOperations.groovy
def buildAndPushImage(String dockerRegistry, String imageName, String imageVersion) {
    echo "Building Docker image..."
    sh """
        docker build -t ${dockerRegistry}/${imageName}:${imageVersion} .
    """
    
    echo "Pushing Docker image..."
    sh """
        docker push ${dockerRegistry}/${imageName}:${imageVersion}
    """
}

def copyFilesToServer(String serverUserName, String serverIp, String serverPassword, String deployPath) {
    echo "Copying application files to the server..."
    sh """
        sshpass -p ${serverPassword} scp -o StrictHostKeyChecking=no -P 22 -r * ${serverUserName}@${serverIp}:${deployPath}
    """
}

def deployWithDockerCompose(String serverUserName, String serverIp, String serverPassword, String dockerComposeFile, String deployPath) {
    echo "Copying Docker Compose file to the server..."
    sh """
        sshpass -p ${serverPassword} scp -o StrictHostKeyChecking=no -P 22 ${dockerComposeFile} ${serverUserName}@${serverIp}:${deployPath}
    """
    
    echo "Deploying Docker containers with Docker Compose..."
    sh """
        sshpass -p ${serverPassword} ssh -o StrictHostKeyChecking=no ${serverUserName}@${serverIp} 'cd ${deployPath} && docker-compose pull && docker-compose up -d'
    """
   // sh """
   //     sshpass -p ${serverPassword} ssh -o StrictHostKeyChecking=no ${serverUserName}@${serverIp} 'cd ${deployPath} && docker-compose pull ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_VERSION} && docker-compose up -d ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_VERSION}'
   // """

}
