/**
 * Example of using the custom nested pipeline
 */
def call(body)
{
    // Init the MPL library
    MPLInit()

    def MPL = MPLPipelineConfig(body, [agent_label: ''], )

    pipeline {
        options {
            skipDefaultCheckout true
            timestamps()
        }
        stages {
            stage('Checkout') {
                steps {
                    MPLModule()
                }
            }
            stage('Build') {
              steps {
                MPLModule('Docker Build')
              }
            }
            stage('Push') {
                steps {
                    MPLModule()
                }
            }

        }
    }
}
