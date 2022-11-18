library 'reference-pipeline'
library 'AppServiceAccount'
//@Library('jenkins-pipeline-library')_


pipeline {
  agent {
            node {
                label 'java11'
            }
        }
triggers {
        cron('0 0 * * 4')
    }
  options {
    timeout(time: 60, unit: 'MINUTES')
  }
environment {
                MICROSOFT_TEAM_WEBHOOK = "https://mycompany.webhook.office.com/webhookb2/f16668ac-7527-4435-8cd1-3167a808286b@b945c813-dce6-41f8-8457-5a12c2fe15bf/IncomingWebhook/8316b04edf7b448fa149e348531b011d/bc836d10-e4f1-4645-ac89-badbeb423ded"; 
 }
 
 tools {
            jdk 'JAVA_11'
            maven 'Maven 3.3.3'
            nodejs 'NodeJS-8.9.4'
        }
        
  stages {
  
  stage("Scanning"){
      parallel {
        stage('sonarqube') {
          steps {
            script{
              try{
                sonarqube(sqIgnoreProperties: 'false')
              }catch(error){
                sendNotification("${env.BRANCH_NAME} : ${env.STAGE_NAME}", "'Application ${env.STAGE_NAME}' Status - ${error.toString()}", "FAILED")
                previousStageStatus = false
              }
            }
          }
        }
     }
  }
  
 
    stage("Testing") 
   {
   steps{
       script{
      
      try{
      sh 'mvn -DsuiteXmlFile=testng.xml test'
      //sh 'mvn -Dtest=TestRunner test'
	  
	  publishHTML([
	  allowMissing : false,
      alwaysLinkToLastBuild : true,
      keepAll : true,
      reportDir : './ExtentReport/tests',
      reportFiles : 'report.html',
      reportName : 'Extent Report',
      ])
	  sendNotification("${env.BRANCH_NAME} : ${env.STAGE_NAME}", "'Application ${env.STAGE_NAME}' Status", "SUCCESS")
      }
	  catch(all){
      publishHTML([
      currentBuild.result='FAILURE'
      allowMissing : false,
      alwaysLinkToLastBuild : true,
      keepAll : true,
      reportDir : './ExtentReport/tests',
      reportFiles : 'report.html',
      reportName : 'Extent Report',
      ])
	  sendNotification("${env.BRANCH_NAME} : ${env.STAGE_NAME}", "'Application ${env.STAGE_NAME}' Status", "FAILED")
      }
	  
            }
        }
    
    }
    }
   
    post {
    success {
                mail(to: 'abc@gmail.com', subject: "Pipeline Success ", body: "Job '${env.JOB_NAME} [${env.BRANCH_NAME}]' (${env.BUILD_URL})")
                office365ConnectorSend color: '#00FF00', message: "SUCCESSFUL", status: "Build Success", webhookUrl: MICROSOFT_TEAM_WEBHOOK
            }
            failure {
                mail(to: 'abc@gmail.com',subject: "Pipeline failure ", body: "Job '${env.JOB_NAME} [${env.BRANCH_NAME}]' (${env.BUILD_URL})")
                office365ConnectorSend color: '#FF0000', message: "FAILED", status: "Build Failure", webhookUrl: MICROSOFT_TEAM_WEBHOOK
            }

    /*always {
      cleanWs()
      
    }*/
  }
}
def sendNotification(String branch, String message, String status){
script{
office365ConnectorSend webhookUrl: 'https://mycompany.webhook.office.com/webhookb2/eabea0ea-e3be-4624-8441-233e8c717282@b945c813-dce6-41f8-8457-5a12c2fe15bf/IncomingWebhook/b0d3ea51faa048c699f8635bbda2557c/e5cc4e42-f5c6-427a-af8c-505544b08d30',
message: "Notification : Team | Application_Name ${branch} | ${message}",
status: "${status}"
}
}
