pipeline {
    agent any
     tools {
        maven 'maven'
    }
 
         stage("cleaning code") {
            steps {
                script {
                   sh 'mvn -B -DskipTests clean '
                }
            }
        }
   
}
