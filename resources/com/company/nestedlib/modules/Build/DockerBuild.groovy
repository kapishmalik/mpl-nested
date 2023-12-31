echo "Code for docker build process"
DOCKER_IMAGE_NAME = "${CFG.'docker.DOCKER_ARTIFACTORY_URL'}${CFG.'docker.DOCKER_ARTIFACTORY_PATH'}:${CFG.'docker.IMAGE_TAG'}"
if (CFG.'use_podman')
{
    sh "ls -alth ."
    sh "podman build -t ${DOCKER_IMAGE_NAME} ."
}
else
{
    sh "docker build --pull -t ${PGWEB_DOCKER_IMAGE_NAME} ."
}
