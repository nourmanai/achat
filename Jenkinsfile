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
                               bat 'c:/maven/mvn clean verify'


            }
        }   
      stage('Sonar') {
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        bat "c:/maven/mvn sonar:sonar"
   
        }
        }
       
       
    }
}
