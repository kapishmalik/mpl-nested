/**
 * Example of using the custom nested pipeline
 */
def call(body)
{
    // Init the MPL library
    MPLInit()

    def MPL = MPLPipelineConfig(body, [
            agent_label: '',
            release_run: (env.BRANCH_NAME ?: '') == 'master',
            environment: 'dev',
    ], [
                                        nexus             : [
                                                credentials: 'nexus-deploy-account',
                                                server_id  : env.NEXUS_SERVER_ID,
                                                repo       : [
                                                        snapshots: env.NEXUS_REPO_SNAPSHOTS,
                                                        releases : env.NEXUS_REPO_RELEASES,
                                                ],
                                        ],
                                        environment_access: [
                                                dev : ['example-org-devops-team', 'example-org-dev-team'],
                                                qa  : ['example-org-devops-team', 'example-org-qa-team'],
                                                prod: ['example-org-devops-team', 'example-org-prod-team'],
                                        ],
                                ])

    pipeline {
        agent none // No need to leave agent while we waiting
        options {
            skipDefaultCheckout true
            buildDiscarder logRotator(numToKeepStr: '50', artifactNumToKeepStr: '50')
            timestamps()
        }
        stages {
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
            stage('Test') {
                steps {
                    MPLModule()
                }
            }
        }
        post {
            always {
                MPLPostStepsRun('always')
            }
            success {
                MPLPostStepsRun('success')
            }
            failure {
                MPLPostStepsRun('failure')
            }
        }
    }
}
