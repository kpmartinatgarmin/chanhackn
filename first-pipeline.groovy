node {
    stage('CAPS') {
        try {
            sh """newman run ${env.GIT_URL}CAPS.postman_collection.json \
  -e ${env.GIT_URL}DCISVCS_Prod.postman_environment.json \
  --reporters junit,cli \
  --reporter-junit-export CAPS.xml"""
            currentBuild.result = 'SUCCESS'
        } catch(Exception ex) {
            currentBuild.result = 'FAILURE'
        }
        junit 'CAPS.xml'
    }
    stage('GCSS') {
        try {
            sh """newman run ${env.GIT_URL}GCSS.postman_collection.json \
  -e ${env.GIT_URL}DCISVCS_Prod.postman_environment.json \
  --reporters junit,cli \
  --reporter-junit-export GCSS.xml"""
            currentBuild.result = 'SUCCESS'
        } catch(Exception ex) {
            currentBuild.result = 'FAILURE'
        }
        junit 'GCSS.xml'
    }
}
