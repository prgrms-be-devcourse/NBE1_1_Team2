# 커피-주문 API 서버 만들기

## 팀 분석 시나리오

### 1. 공통 DB 스키마

```sql
CREATE TABLE products
(
    product_id   BINARY(16) PRIMARY KEY,
    product_name VARCHAR(20) NOT NULL,
    category     VARCHAR(50) NOT NULL,
    price        BIGINT      NOT NULL,
    description  VARCHAR(500) DEFAULT NULL,
    created_at   DATETIME(6) NOT NULL,
    updated_at   DATETIME(6)  DEFAULT NULL
);

CREATE TABLE orders
(
    order_id     BINARY(16) PRIMARY KEY,
    email        VARCHAR(50)  NOT NULL,
    address      VARCHAR(200) NOT NULL,
    postcode     VARCHAR(200) NOT NULL,
    order_status VARCHAR(50)  NOT NULL,
    created_at   DATETIME(6)  NOT NULL,
    updated_at   DATETIME(6) DEFAULT NULL
);

CREATE TABLE order_items
(
    seq        BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id   BINARY(16)  NOT NULL,
    product_id BINARY(16)  NOT NULL,
    category   VARCHAR(50) NOT NULL,
    price      BIGINT      NOT NULL,
    quantity   INT         NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) DEFAULT NULL,
    INDEX (order_id),
    CONSTRAINT fk_order_items_to_order FOREIGN KEY (order_id) REFERENCES orders (order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_to_product FOREIGN KEY (product_id) REFERENCES products (product_id)
);

```

2. 사용 시나리오
    - 팀 분석 시나리오
        1. 제품 CRUD
            - [ ]  제품 생성 기능
                - `product_name`, `category`, `price`, `description` 으로 제품을 생성할 수 있다.
            - [ ]  제품 조회 기능
                - 제품 전체를 조회 할 수 있다.
                    - `product_name`
                - 제품 상세 페이지를 조회 할 수 있다.
                    - `product_name`, `category`, `price`, `description`
            - [ ]  제품 수정 기능
                - `제품 식별값`으로 제품을 수정할 수 있다.
                - 수정 항목: `price` , `description`
            - [ ]  제품 삭제 기능
                - `제품 식별값`으로 제품을 삭제할 수 있다.
        2. 주문 CRUD
            - [ ]  주문 생성 기능
                - `order_items`, `email, address`, `postcode`으로 주문을 생성할 수 있다.
                    - `order_items`는 `product_id`, `quantity`으로 구성된다.
            - [ ]  주문 조회 기능
                - `email` 값으로 주문 리스트를 조회할 수 있다.
                - `주문 식별` 값으로 단건 주문을 조회할 수 있다.
            - [ ]  주문 수정 기능
                - `주문 식별값`으로 주문을 수정할 수 있다.
                - 수정 항목: `address`,  `postcode`
            - [ ]  주문 삭제 기능
                - `주문 식별값`으로 주문을 수정할 수 있다.
                - 주문 상태 : “주문 완료” , “배송 시작”
                    - “주문 완료” 인 경우에만 주문 삭제 가능
        3. 배송 처리 기능
            - [ ]  주문 완료인 주문을 배송 시작 상태로 변경할 수 있다.
                - 14시에 주문 완료 상태의 주문을 배송 시작 상태로 변경한다.
                - 혹은 별도 api사양 파서 구현

## 코드 작성 규칙

- 필수 사용 기술
    - 스프링 부트 3.xx, 자바 17
    - JPA, Lombok, mysql, SLF4J
        - @SLF4J
- 코드 컨벤션
    - google java convention 참조
    - 함수 네이밍
  
      https://tecoble.techcourse.co.kr/post/2020-04-26-Method-Naming/

      https://jacking75.github.io/ETC_good_function_name/

      https://cocobi.tistory.com/27