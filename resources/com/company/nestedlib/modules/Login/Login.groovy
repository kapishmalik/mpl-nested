echo "Docker login command to artifactory ${CFG.'docker.DOCKER_ARTIFACTORY_URL'} using credentials ${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}"
withCredentials([usernamePassword(credentialsId: "${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
    sh "echo ${PASSWORD} | docker login -u ${USERNAME} --password-stdin ${CFG.'docker.DOCKER_ARTIFACTORY_URL'}"
}