DOCKER_IMAGE_NAME = "${CFG.'docker.DOCKER_ARTIFACTORY_URL'}${CFG.'docker.DOCKER_ARTIFACTORY_PATH'}:${CFG.'docker.IMAGE_TAG'}"
if (CFG.'use_podman')
{
    sh "podman push $DOCKER_IMAGE_NAME"
}
else
{
    sh "docker push $DOCKER_IMAGE_NAME"
}
