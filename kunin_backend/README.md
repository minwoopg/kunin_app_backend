# KIP Backend (Spring Boot)

쇼핑몰 앱(KIP 2026)의 백엔드 프로젝트입니다.
1차 목표: **카테고리/상품 API 3개** 구현

```
GET /api/categories
GET /api/products
GET /api/products/{id}
```

---

## 1. 사전 준비

### MySQL 데이터베이스 생성

로컬 MySQL에 접속해서 데이터베이스만 만들어주세요. (테이블은 자동 생성됩니다)

```sql
CREATE DATABASE kip CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### application.yml 수정

`src/main/resources/application.yml` 에서 본인 MySQL 계정 정보로 수정하세요.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/kip?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul
    username: root        # ← 본인 계정으로 수정
    password: 1234        # ← 본인 비밀번호로 수정
```

---

## 2. 실행 방법 (IntelliJ)

1. IntelliJ에서 `kip-backend` 폴더를 **Open** (build.gradle 인식 → Gradle 프로젝트로 열림)
2. Gradle 동기화가 끝나면 `ShopApplication.java` 실행 (▶ 버튼)
3. 콘솔에 `Tomcat started on port 8080` 뜨면 성공

> 최초 실행 시 테이블이 자동 생성되고, `data.sql`의 시드 데이터(카테고리 5개 + 상품 14개)가 자동으로 들어갑니다.
> 현재 `ddl-auto: create-drop` 설정이라 **재시작할 때마다 테이블이 초기화되고 시드 데이터가 다시 들어갑니다.** (개발 단계용 설정)

---

## 3. 동작 확인

### Swagger UI
브라우저에서 접속:
```
http://localhost:8080/swagger-ui.html
```

### API 직접 테스트 (curl)

```bash
# 카테고리 목록
curl http://localhost:8080/api/categories

# 상품 목록 (전체)
curl http://localhost:8080/api/products

# 상품 목록 (카테고리 필터)
curl "http://localhost:8080/api/products?categoryCode=medicalDevice"

# 상품 목록 (검색)
curl "http://localhost:8080/api/products?keyword=초음파"

# 상품 상세
curl http://localhost:8080/api/products/1

# 존재하지 않는 상품 (404 확인)
curl http://localhost:8080/api/products/999
```

---

## 4. 프로젝트 구조

```
src/main/java/com/kip/shop/
├── ShopApplication.java
├── common/
│   ├── ApiResponse.java          # 공통 응답 포맷 { success, message, data }
│   ├── ErrorCode.java             # 에러 코드 enum
│   ├── CustomException.java
│   └── GlobalExceptionHandler.java
├── config/
│   └── CorsConfig.java            # Flutter 앱 통신 허용
├── category/
│   ├── Category.java              # 엔티티
│   ├── CategoryRepository.java
│   ├── CategoryResponse.java      # DTO
│   ├── CategoryService.java
│   └── CategoryController.java
└── product/
    ├── Product.java               # 엔티티
    ├── ProductTag.java             # enum (NONE/NEW/BEST)
    ├── ProductRepository.java
    ├── ProductService.java
    ├── ProductController.java
    └── dto/
        ├── ProductSummaryResponse.java  # 목록용
        └── ProductDetailResponse.java   # 상세용
```

---

## 5. 다음 단계

이 API가 정상 동작하면, Flutter의 `mock_products.dart`를 실제 API 호출(Dio)로 교체합니다.

이후 순서:
```
2차: 회원가입 / 로그인 / JWT / 내 정보 조회
3차: 장바구니 API
4차: 주문 API
```
