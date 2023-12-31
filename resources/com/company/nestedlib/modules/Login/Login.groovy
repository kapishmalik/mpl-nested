echo "Docker login command to artifactory ${CFG.'docker.DOCKER_ARTIFACTORY_URL'} by fetching credentials from Jenkins ID ${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}"
withCredentials([usernamePassword(credentialsId: "${CFG.'docker.DOCKER_ARTIFACTORY_CREDENTIAL_ID'}",
                                  usernameVariable: 'USERNAME',
                                  passwordVariable: 'PASSWORD')]) {
    echo "${CFG.'use_podman'}"
    if (CFG.'use_podman')
    {
        sh "echo ${PASSWORD} | podman login -u ${USERNAME} --password-stdin ${CFG.'docker.DOCKER_ARTIFACTORY_URL'}"
    }
    else
    {
        sh "echo ${PASSWORD} | docker login -u ${USERNAME} --password-stdin ${CFG.'docker.DOCKER_ARTIFACTORY_URL'}"
    }
}