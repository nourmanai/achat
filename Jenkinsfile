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
                                bat 'c:/maven/bin/mvn --version'

            }
        }   
      stage('maven build'){
            steps{
                               bat 'c:/maven/bin/mvn clean verify'


            }
        }   
      stage('Sonar') {
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        bat "c:/maven/bin/mvn sonar:sonar"
   
        }
        }
       
       
    }
}
