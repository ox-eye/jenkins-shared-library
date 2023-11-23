// def call(String token = '', String host = 'api.oxeye.net', String client_id, String secret, String workspace_id, String release, String excludes = '') {
def call(Map config = [:]) {
    String token = config.getOrDefault("token", 'NA')
    String host = config.getOrDefault("host",'api.oxeye.net')
    String client_id = config.getOrDefault("client_id",'')
    if (client_id == '')
        error("Miissing client_id")
    String secret = config.getOrDefault("secret",'')
    if (secret == '')
        error("Miissing secret")
    String workspace_id = config.getOrDefault("workspace_id",'')
    if (workspace_id == '')
        error("Miissing workspace_id")
    String release = config.getOrDefault("releas",'release')
    String excludes = config.getOrDefault("excludes",'')
    script {
        docker.image('ghcr.io/ox-eye/github-actions/oxeye-scan:v0.0.19').inside("--pull always -u 0 --entrypoint=") {
            sh 'git config --global --add safe.directory "*"'
            sh "/entrypoint.sh ${token} ${host} ${client_id} ${secret} ${workspace_id} ${release} \"${excludes}\""
        }
    }
}