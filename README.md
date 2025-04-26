
![istockphoto-1758173843-612x612](https://github.com/user-attachments/assets/a5b01cb8-8f91-4e49-b189-3c8ae804e1e4)

**Cheeta**는 미끼상품을 통해 사람들의 구매 욕구를 촉진시키는 이커머스 플랫폼입니다.

---

## 📌 프로젝트 소개

- **프로젝트 목표**: 선착순 구매 기능을 제공하는 유연한 쇼핑몰 시스템 구축
- **핵심 기능**: 선착순 구매, 드로우, 래플, 오픈런
- **아키텍처**: MSA 기반으로 설계된 유연한 확장 가능 시스템

---

## 🚧 아키텍처

![Architecture Diagram](https://github.com/user-attachments/assets/f71f320a-fb99-4ea8-a828-65f56c4be67f)

- **마이크로서비스 아키텍처**를 기반으로 각 서비스가 독립적으로 배포 및 관리됩니다.
- **API Gateway**와 **Eureka Server**를 활용하여 서비스 간 통신 및 라우팅을 처리합니다.

---

## 🧑‍💻 사용 기술

### 🛠 Language
- **Java(JDK 21)**

### 🛠 Framework & Library
- **Spring Boot(3.3.2)**: REST API 개발
- **Spring Security(6.3.3)**: 사용자 인증 및 인가 처리
- **Spring Cloud(4.1.0)**: 마이크로서비스 아키텍처 지원
- **Spring Data JPA(3.2.5)**: 데이터 접근 및 관리

### 🛠 Database & Caching
- **MySQL(8.0.29)**: 관계형 데이터베이스
- **Redis(3.0.504)**: 캐싱 및 세션 관리

### 🛠 Infra
- **Docker(27.0.3)**: 컨테이너 기반 배포 및 관리

---

## 📁 ERD

![ERD Diagram](https://github.com/user-attachments/assets/2148d763-5aef-4e52-8ac9-5c53d4ed2110)

---

## 🔥 주요 기능

- **Spring Security**: 회원 인증 및 보안 처리
- **API Gateway**: 라우팅 및 회원 인가 처리
- **Eureka Server-Client**: 마이크로서비스 아키텍처 기반 서비스 관리
- **Redis Caching**: 상품 정보 캐싱 및 성능 최적화
- **MySQL Pessimistic Lock**: 선착순 구매 시 동시성 문제 해결
- **Entity Lifecycle**: `@PreUpdate`, `@PrePersist`를 활용한 상품 및 주문 상태 관리

---

## 💣 Trouble Shooting & Development

1. **구매 프로세스 선착순 주문 처리**  
   자세한 내용은 [블로그 포스트](https://kibeom2000.tistory.com/153)에서 확인하세요.

2. **Redis 상품 정보 조회 성능 향상**  
   자세한 내용은 [블로그 포스트](https://kibeom2000.tistory.com/154)에서 확인하세요.

---
