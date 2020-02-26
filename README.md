# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.8.RELEASE/maven-plugin/)

### USE NGINX DEPLOY
__install nginx__

npm run build `vue-development` 

compile `development` 

run    _`java -Dfile.encoding=UTF-8 -jar development-0.0.1-SNAPSHOT.jar`_


_edit_ `nginx.conf`

```
server {
        listen       8088;
		server_name  localhost;	

		location / {
			root E:\WebStormProjects\vue-development\dist;
			index index.html index.htm;
		}
		
		location /pcp/ {
            proxy_set_header Host $host;
            proxy_pass   http://127.0.0.1:8090/pcp/;
		}
}
```

run `nginx`
