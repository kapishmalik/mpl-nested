echo "Docker login command to artifactory ${CFG.'docker.DOCKER_ARTIFACTORY_URL'} using credentials ${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}"
DOCKER_ARTIFACTORY_CREDENTIALS = credentials("${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}")
withCredentials([string(credentialsId: "${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}", variable: 'DOCKER_ARTIFACTORY_CREDENTIALS')]) {
    sh "echo ${DOCKER_ARTIFACTORY_CREDENTIALS_PSW} | docker login -u ${DOCKER_ARTIFACTORY_CREDENTIALS_USR} --password-stdin ${CFG.'docker.DOCKER_ARTIFACTORY_URL'}"
}