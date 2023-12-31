/**
 * Example of using the custom nested pipeline
 */
def call(body)
{
    // Init the MPL library
    MPLInit()

    def MPL = MPLPipelineConfig(body, [agent_label: ''])

    pipeline {
        agent {
            label MPL.agentLabel
        }
        options {
            skipDefaultCheckout true
        }
        stages {
            stage('Checkout') {
                steps {
                    MPLModule()
                }
            }
            stage('Login') {
                steps {
                    MPLModule()
                }
            }
            stage('Build') {
                steps {
                    MPLModule()
                }
            }
            stage('Push') {
                steps {
                    MPLModule()
                    script {
                        addDeployToDashboard(env: 'BUILD', buildNumber: "${currentBuild.number}")
                    }
                }
            }
        }
    }
}
