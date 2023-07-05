
bin\elasticsearch-keystore.bat show xpack.security.http.ssl.keystore.secure_password

bin\elasticsearch-keystore.bat show xpack.security.transport.ssl.keystore.secure_password


bin\elasticsearch-keystore add "xpack.security.http.ssl.keystore.secure_password" 


bin\elasticsearch-certutil ca --out certs\elastic-stack-ca.p12 --pass 123456
bin\elasticsearch-certutil cert --silent --in certs\instances.yml --out certs\depf.zip --pass 123456 --ca certs\elastic-stack-ca.p12