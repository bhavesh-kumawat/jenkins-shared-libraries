def call(String Project, String ImageTag, String dockerHubUser){
  withCredentials([usernamePassword(credentialsId: 'DockerHubCred', passwordVariable: 'dockerHubPass', usernameVariable: 'dockerHubUser')]) {
      sh """
            echo "$dockerHubPass" | docker login -u "$dockerHubUser" --password-stdin
        """
  }
  sh "docker push ${dockerHubUser}/${Project}:${ImageTag}"
}
