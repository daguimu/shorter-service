// my-awesome-app/Jenkinsfile
@Library('jenkins-shared-library') _

// 调用通用流水线，并传入本项目特有的配置
standardDeployPipeline([
    // ========== 1. 项目基础配置 ==========
    appName: 'short',
    gitUrl: 'git@github.com:daguimu/shorter-service.git',
    gitCredentialsId: 'c60bb0d8-7cd0-48d4-9d43-5a46f5d26318',
    
    // ========== 2. 构建配置 ==========
    buildCmd: 'clean package -DskipTests',
    
    // ========== 3. 健康检查与环境配置 ==========
    healthCheckUrl: 'https://dagm.com/way/shorter/health',
    deployEnvs: ['production'],
    
    // ========== 4. 部署逻辑配置 ==========
    deployClosure: { config, params ->
        // --- 定义完整的远程服务器配置 ---
        def remote = [:]
        remote.name = 'deployment-server'
        remote.host = '8.140.158.233'
        remote.user = 'root'
        remote.port = 22
        remote.allowAnyHosts = true
        
        def remoteWorkspace = "/guimu/jenkins/workspace/${config.appName}"
        def remoteDeployScript = '/guimu/launch/deploy.sh'
        
        def appPort = 9011
        def javaOpts = "-Duser=normal -Dpasswd=normal"
        
        echo "🚀 开始部署应用 ${config.appName} 到服务器 ${remote.host}"
        
        // 使用 withCredentials 来提供 SSH 凭证
        withCredentials([sshUserPrivateKey(credentialsId: config.gitCredentialsId, keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
            // 设置远程连接的用户和密钥文件
            remote.user = userName ?: 'root'  // 如果 userName 为空，使用 'root'
            remote.identityFile = identity
            
            // --- 步骤 4.1: 创建远程目录并传输构建产物 ---
            echo "🚀 STEP 4.1: 创建远程目录并传输构建产物到 ${remote.host}:${remoteWorkspace}"
            
            // 首先创建远程目录
            sshCommand remote: remote,
                       command: "mkdir -p ${remoteWorkspace}"
            
            // 传输 target 目录到远程服务器
            sshPut remote: remote,
                   from: 'target/',
                   into: remoteWorkspace
            
            echo "✅ target/ 目录传输完成"
            
            // 如果 Dockerfile 存在，也传输过去
            if (fileExists('Dockerfile')) {
                sshPut remote: remote,
                       from: 'Dockerfile',
                       into: remoteWorkspace
                echo "✅ Dockerfile 传输完成"
            }
            
            // --- 步骤 4.2: 执行远程部署脚本 ---
            echo "🚀 STEP 4.2: 在 ${remote.host} 上执行部署脚本"
            
            // 构造要执行的完整命令
            def commandToExecute = "bash ${remoteDeployScript} ${config.appName} ${appPort} '${javaOpts}'"
            
            echo "执行命令: ${commandToExecute}"
            
            sshCommand remote: remote,
                       command: commandToExecute
            
            echo "✅ 部署完成！应用 ${config.appName} 已成功部署到 ${remote.host}"
        }
    }
])