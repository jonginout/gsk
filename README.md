### Develop local Database (mysql5.6)
```
docker-compose -f ./depoly/dev/docker-compose.yml up -d 
```
### generate Qdomin and proto
```
./gradlew kaptKotlin generateProto --info --stacktrace
```
spring, jpa, rest-api, grpc, kotlin 예제 프로젝트
