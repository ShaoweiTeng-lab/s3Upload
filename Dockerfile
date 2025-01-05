# 使用 Maven 官方映像作為基底映像
FROM maven:3.8.4-openjdk-17-slim AS build

# 設定工作目錄
WORKDIR /usr/src/app

# 複製所有程式碼到容器內的工作目錄
COPY . ./

# 使用 Maven 构建並指定輸出的 jar 名稱為 s3Build.jar
RUN mvn clean package 


# 運行應用程式
CMD java -jar target/s3demo-0.0.1-SNAPSHOT.jar