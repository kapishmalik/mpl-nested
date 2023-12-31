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
            label MPL.agent_label
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
        }
    }
}
