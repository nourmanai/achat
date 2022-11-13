  pipeline{
    agent any
 
    stages{
       stage('Git'){
            steps{
               git 'https://github.com/nourmanai/achat.git'
                
            }
        }
       stage('maven test'){
            steps{
                                bat 'c:/maven/mvn --version'

            }
        }   
      stage('maven build'){
            steps{
                               sh 'c:/maven/mvn clean verify'


            }
        }   
      stage('Sonar') {
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        sh "mvn sonar:sonar \
  -Dsonar.projectKey=chaima \
  -Dsonar.host.url=http://192.168.33.10:9000 \
  -Dsonar.login=ff92b883ccbc579fa7cf0464fcf5218c5a8149b8"
   
        }
        }
       
       
    }
}
