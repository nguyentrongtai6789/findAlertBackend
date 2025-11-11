# Sử dụng OpenJDK 21 chính thức (Eclipse Temurin - nhẹ, ổn định)
FROM eclipse-temurin:21-jdk-alpine

# Tạo thư mục làm việc
WORKDIR /app

# Copy file JAR đã build (dùng wildcard để linh hoạt)
COPY target/*.jar app.jar

# Expose port (Render yêu cầu, dù port động)
EXPOSE 8082

# Chạy ứng dụng với tối ưu JVM cho container
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]