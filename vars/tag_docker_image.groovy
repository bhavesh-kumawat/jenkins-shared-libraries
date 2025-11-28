def call(String ProjectName, String ImageTag, String credentialsId){
  withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'DOCKER_USER',
        )
    ]) {

  sh "docker tag ${ProjectName}:${ImageTag}  ${DOCKER_USER}/${ProjectName}:latest"
  }
}
