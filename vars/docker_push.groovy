def call(String Project, String ImageTag, String credentialsId) {

    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {

        // Secure login
        sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
        '''

        // tag image for push
        sh "docker tag ${Project}:${ImageTag}  ${DOCKER_USER}/${Project}:${ImageTag}"

        // Push image
        sh """
            docker push ${DOCKER_USER}/${Project}:${ImageTag}
        """
    }
}
