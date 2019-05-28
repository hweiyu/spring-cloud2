# 加解密说明

* 官网下载 jce_policy-8.zip 并解压。  
 
* 把解压得到的 local_policy.jar 和 US_export_policy.jar 覆盖到jdk安装目录下:%JAVA_HOME%\jre\lib\security   

* 调相应的端点接口，例如：  
    [对称加密]   
        1. 设置服务器的密钥 encrypt.key=iminKey  
        2. 要加密的串为 abc123456   
        3. curl http://imin:imin123@localhost:6107/config/encrypt -d abc123456   
        4. 加密后的串 47e31671b42f4ede0b41c7123ad777f0f453cba84d38f53edfbdf3fc20b544fc   
        5. 使用方式 xxx.yyy.zzz={cipher}47e31671b42f4ede0b41c7123ad777f0f453cba84d38f53edfbdf3fc20b544fc   
    [非对称加密]   
        1. ......   
