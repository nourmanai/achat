pipeline {
    agent any
     tools {
        maven 'maven'
    }
 stages {
         stage("cleaning code") {
            steps {
                script {
                   sh 'mvn -B -DskipTests clean '
                }
            }
        }}
   
}
