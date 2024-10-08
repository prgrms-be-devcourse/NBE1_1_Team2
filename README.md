#  커피-주문 API 서버 만들기

## 👨‍🏫 프로젝트 소개

Spring Boot를 기반으로 한 커피 주문 시스템으로 제품 관리, 주문 처리, 쿠폰 시스템 및 사용자 관리를 포함하며, RESTful API를 통해 다양한 기능을 제공합니다.


## 🧑‍🤝‍🧑멤버 구성 및 역할 (TEAM 2)

- 이범수(팀장) : 뷰 연결 , jpa -> mybatis 변경
- 김민우 : 유저 추가
- 김지은 : 스웨거 적용
- 문재경 : 쿠폰 생성 및 발급을 위한 바우처 추가
- 송경훈 : JPA 쿼리 최적화

## 📌 주요 기능

- 제품 관리: 추가, 수정, 삭제, 조회
- 주문 관리: 생성, 수정, 삭제, 조회
- 배송 처리: 상태 변경
- 쿠폰 관리: 생성, 삭제, 조회, 발급, 사용
- 쿠폰 관리: 생성, 삭제, 조회, 발급, 사용


## 📝 메인 시나리오

1. 제품 CRUD
    - [x] 제품 생성 기능
        - `product_name`, `category`, `price`, `description` 으로 제품을 생성할 수 있다.
    - [x] 제품 조회 기능
        - 제품 전체를 조회 할 수 있다.
            - `product_name`
        - 제품 상세 페이지를 조회 할 수 있다.
            - `product_name`, `category`, `price`, `description`
    - [x] 제품 수정 기능
        - `제품 식별값`으로 제품을 수정할 수 있다.
        - 수정 항목: `price`, `description`
    - [x] 제품 삭제 기능
        - `제품 식별값`으로 제품을 삭제할 수 있다.

2. 주문 CRUD
    - [x] 주문 생성 기능
        - `order_items`, `email`, `address`, `postcode`으로 주문을 생성할 수 있다.
            - `order_items`는 `product_id`, `quantity`으로 구성된다.
    - [x] 주문 조회 기능
        - `email` 값으로 주문 리스트를 조회할 수 있다.
        - `주문 식별` 값으로 단건 주문을 조회할 수 있다.
    - [x] 주문 수정 기능
        - `주문 식별값`으로 주문을 수정할 수 있다.
        - 수정 항목: `address`, `postcode`
    - [x] 주문 삭제 기능
        - `주문 식별값`으로 주문을 삭제할 수 있다.
        - 주문 상태: “주문 완료”, “배송 시작”
            - “주문 완료” 인 경우에만 주문 삭제 가능

3. 배송 처리 기능
    - [x] 주문 완료인 주문을 배송 시작 상태로 변경할 수 있다.
        - 14시에 주문 완료 상태의 주문을 배송 시작 상태로 변경한다.
        - 혹은 별도 API 사양 파서 구현

## 💡 추가 시나리오
 
4. 쿠폰 기능
    - [x] 쿠폰 생성 기능 (관리자)
        - 관리자는 쿠폰을 생성할 수 있다
            - 입력 항목: `coupon_name`, `discount`, `expiration_date`, `coupon_code`
    - [x] 쿠폰 삭제 기능 (관리자)
        - `쿠폰 식별값` 통해 쿠폰을 삭제할 수 있다.
    - [x] 쿠폰 발급 기능
        - 유저는 `쿠폰 코드`를 통해 쿠폰을 발급받을 수 있다.
            - 동일한 쿠폰은 한 번만 발급 가능하다.
    - [x] 쿠폰 적용 및 구매 기능
        - 유저는 발급된 쿠폰을 사용하여 할인된 금액으로 구매할 수 있다.
            - 주문 시 한 개의 쿠폰만 적용 가능하다.

5. 사용자 관리 기능
    - [x] 일반 사용자 로그인
        - 유저는 `email`과 암호화된 `비밀번호`를 통해 로그인할 수 있다.
            - 시큐리티 프레임워크 없이 로그인 구현.
        - 로그인 후 JWT 발급.
    - [x] 관리자 로그인
        - 관리자는 admin `유저네임`과 `비밀번호`로 로그인하여 JWT를 발급받을 수 있다.
    - [x] JWT 인증 및 인가 필터
        - 인증이 필요한 요청 전, JWT를 이용한 인가 처리가 필터에서 수행된다.


## 💡 별도 추가 기능
- Mybatis + view (별도 Branch)
    - [mybatis + view branch 이동](https://github.com/prgrms-be-devcourse/NBE1_1_Team2/tree/mybatis)
- 쿼리 최적화
    - [link](https://equal-mountain-a78.notion.site/318aee943fb1410f8f40e1fd3e2b4931?pvs=4)


## 🥹 구현하지 못한 기능
- 인증 인가를 반영한 VIEW 페이지
  - REST API 통해서만 구현된 상태
- 주문시 쿠폰과의 비지니스로직 통합
  - 시간 관계상, 주문과 쿠폰 별도


## ERD
![ERD](resources/images/coffee-order-erd.png)

## API 명세
- [Notion API 명세서](https://bird-airboat-008.notion.site/API-24-1-2-1-711772806cda424db74c341876e7be34)
- [swagger-ui](http://localhost:8080/swagger-ui/index.html#)
  - 어플리케이션 실행시 페이지 활성화

    
## 💻 개발 환경
<details>
<summary>토글 접기/펼치기</summary>
<div markdown="1">

**JDK**
- Java 17

**프레임워크**
- Spring Boot 3.x

**빌드 도구**
- Gradle

**개발도구**
- IntelliJ IDEA (IDE)

**기술 및 라이브러리**
- Spring Data JPA
- Swagger
- Lombok

**협업 도구**
- Notion: 프로젝트 관리 및 문서화
- GitHub: 버전 관리 및 코드 저장소
  버전 관리 및 협업 도구

</div>
</details>

## ✅ 코드 작성 규칙
<details>
<summary>토글 접기/펼치기</summary>
<div markdown="1">

### Commit Convention

- **태그이름은 모두 소문자로 사용**

- **태그 목록**:
    - `feat`: 새로운 기능 추가
    - `fix`: 오류 해결
    - `design`: CSS 등 UI 변경
    - `style`: 코드 포맷 변경, 코드 수정 없음
    - `refactor`: 코드 리팩토링
    - `docs`: 문서 수정
    - `test`: 테스트 추가/수정
    - `chore`: 빌드, 패키지 매니저 설정 등
    - `rename`: 폴더 이름, 경로 변경

- **커밋 메시지 포맷**: `태그이름: 내용`
    - 예: `feat: 회원가입 API 구현`

### Flow

1. **이슈 생성**: 이슈 템플릿에 맞춰 생성
    - 태그: `기능구현(feat)` | `오류해결(fix)` | `환경설정(chore)` | `리팩토링(refactor)`

2. **브랜치 이름**: `태그이름/#{이슈넘버}`
    - 예: `feat/#21`

3. **Commit 메시지**: `태그이름: 내용`
    - 예: `feat: 회원가입 API 구현`

4. **PR 작성**: PR 템플릿에 맞춰 작성
    - 실행결과 스크린샷 첨부
    - `close #{이슈넘버}`로 자동 이슈 닫기

5. **리뷰 및 Merge**: 최소 한 명의 팀원에게 리뷰 받은 후 스스로 merge
6. **브랜치 삭제**: main 브랜치에 merge된 브랜치는 삭제

</div>
</details>
