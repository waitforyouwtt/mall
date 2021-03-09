生成证书的命令：cmd:
keytool -genkeypair -alias mowa666 -keyalg RSA -keypass mowa666 -keystore mowa666.jks -storepass mowa666

文件存放目录：C:\Users\luoxian

keytool 是一个Java提供的证书管理工具：
-alias:密钥的别名
-keyalg: 使用的算法：hash / RSA
-keypass: 密钥的访问密码
-keystore:密钥的文件名 ,xc.keystore保存了生成的证书
-storepass: 密钥库的访问密码

查询证书的命令：keytool -list -keystore mowa666.jks

删除别名： keytool -delete -alias mowa666 -keystore mowa666.jks

导出公钥：openssl 是一个加解密工具包，使用openssl 来导出公钥信息
安装：openssl:http://slproweb.com/products/Win32OpenSSL.html

提取公钥命令： keytool -list -rfc --keystore mowa666.jks | openssl x509 -inform pem -pubkey



Oauth2授权模式：
1.授权码模式：Authorization_code [常用]
2.隐式授权模式：Implicit [不常用]
3.密码模式： resource owner password credentials [常用]
4.客户端模式：client credentials [不常用]


申请授权码(请求认证服务获取授权码)：
http://localhost:9001/oauth/authorize?client_id=mowa666&response_type=code&scop=app&redirect_uri=http://localhost


client_id: 客户端id,和授权配置类中设置的客户端id一致。
response_type:授权码模式固定为code。
scop:客户端范围，和授权配置类中设置的scop一致。
redirect_uri:跳转uri,当授权码申请成功后会跳转到此地址，并在后边带上code参数(授权码)

