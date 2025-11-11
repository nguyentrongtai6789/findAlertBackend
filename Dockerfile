# ===============================
# 🏗️ Stage 1: Build ứng dụng bằng Maven
# ===============================
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

# Copy toàn bộ mã nguồn vào container build
COPY . .

# Build JAR (bỏ qua test cho nhanh)
RUN mvn clean package -DskipTests


# ===============================
# 🚀 Stage 2: Runtime (chạy ứng dụng)
# ===============================
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy file JAR đã build từ stage trước
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

# Expose port (Render tự gán port runtime, nhưng nên khai báo)
EXPOSE 8082

# Chạy ứng dụng
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
