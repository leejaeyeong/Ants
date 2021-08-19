헤드폰 2 -realtek
마이크 2-realtek


1. 
[java version]
openjdk version 1.8.0_192
[사용 IDE]
IntelliJ IDEA Community Edition 2021.1.3 x64

2.
[window 환경]
build.gradle 파일에 추가
applicationDefaultJvmArgs = ["-Dkms.url=ws://i5b103.p.ssafy.io:8888/kurento"]

[ubuntu환경]
docker compose.yml 파일에 추가
JVAV_TOOL_OPTIONS: "-Dkms.url=ws://kurento:8888/kurento"

3. 
특이사항없음

4. 
root / root
