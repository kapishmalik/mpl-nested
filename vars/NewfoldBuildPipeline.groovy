/**
 * Example of using the custom nested pipeline
 */
def call(body)
{
    // Init the MPL library
    MPLInit()

    def MPL = MPLPipelineConfig(body, [
            agent_label: '',
            release_run: (env.BRANCH_NAME ?: '') == 'master'
    ])

    pipeline {
        agent {
            label MPL.agentLabel
        }
        options {
            skipDefaultCheckout true
        }
        stages {
            stage('Build') {
                steps {
                    MPLModule()
                }
            }
            stage('Login') {
                steps {
                    MPLModule()
                }
            }
            stage('Push') {
                steps {
                    MPLModule()
                }
            }
            stage('Test') {
                steps {
                    MPLModule()
                }
            }
            stage("Update Release in Deployment Status View") {

                when {
                    branch "$MAIN_BRANCH"
                }

                steps {
                    script {
                        addDeployToDashboard(env: 'BUILD', buildNumber: $ { CFG.'docker.IMAGE_TAG' })
                    }
                }
            }
        }
    }
}
