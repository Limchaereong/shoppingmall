# Shoppingmall

이 프로젝트는 JSP/Servlet을 사용하여 개발한 쇼핑몰 웹 애플리케이션입니다.  
사용자들은 물건을 등록하고 판매하며, 주문을 통해 구매할 수 있습니다.

## 프로젝트 개요
- **데이터베이스:** MySQL
- **개발 기간:** 2024.04.22 ~ 05.10
- **사용 기술:** JSP, Servlet, MySQL, DBCP2

## 주요 기능

### 회원 관리
- **회원가입 및 탈퇴:** 사용자는 회원가입 시 100만 포인트가 기본 지급됩니다. 회원은 관리자(ROLE_ADMIN)와 일반 회원(ROLE_USER)로 구분되며, 회원가입과 탈퇴 기능이 제공됩니다.
- **주소 관리:** 회원은 1개 이상의 주소를 설정할 수 있습니다.
- **포인트 관리:** 회원은 매일 첫 로그인 시 1만 포인트가 적립되며, 주문 시 결제 금액의 10%를 포인트로 적립 받습니다. 포인트 사용 내역은 조회할 수 있습니다.

### 쇼핑 기능
- **상품 관리:** 관리자는 상품을 등록, 수정, 삭제할 수 있으며, 각 상품은 최대 3개의 카테고리에 속할 수 있습니다. 상품은 리스트에 노출될 썸네일 이미지와 상세 페이지에 노출될 이미지를 제공해야 하며, 이미지가 없을 경우 no-image로 대체됩니다.
- **카테고리 관리:** 관리자는 1단계 카테고리를 관리할 수 있으며, 카테고리는 정렬 순서에 따라 오름차순으로 정렬됩니다.
- **장바구니:** 사용자는 상품을 장바구니에 담을 수 있으며, 중복 담기는 방지됩니다. 주문이 완료된 상품은 장바구니에서 삭제됩니다.

### 주문 및 결제
- **결제:** 회원은 포인트를 사용하여 결제할 수 있으며, 주문이 완료되면 포인트가 적립됩니다. 결제 과정에서 예외가 발생할 경우 모든 거래는 RollBack됩니다.
- **주문 명세 조회:** 회원은 자신의 주문 내역을 조회할 수 있으며, 최근 주문 순으로 정렬되고 페이징 처리됩니다.

### 마이 페이지
- **회원 정보 수정:** 회원은 자신의 정보를 수정할 수 있습니다.
- **포인트 사용 내역 조회:** 포인트 사용 내역을 확인할 수 있으며, 최근 사용 내역 순으로 정렬됩니다.
- **주소 관리:** 회원의 주소를 등록, 수정, 삭제할 수 있습니다.

### 관리자 페이지
- **회원 관리:** 관리자는 회원 리스트와 상세 정보를 확인할 수 있으며, 회원은 관리자와 일반 회원으로 구분하여 정렬됩니다.
- **상품 및 카테고리 관리:** 상품과 카테고리를 관리할 수 있으며, 페이징 처리가 지원됩니다.

## 추가 기능

### 포인트 시스템
- 주문 결제 시 결제 금액의 10%가 포인트로 적립되며, 포인트 적립은 독립된 스레드에서 처리됩니다.
- 포인트 적립이 실패하더라도 주문은 정상적으로 처리됩니다.

### View 및 UX
- **Index 페이지:** 카테고리별로 상품이 노출되며, 카테고리가 선택되지 않은 경우 전체 상품이 노출됩니다. 페이징 및 상품명 검색 기능이 제공됩니다.
- **최근 본 상품:** 최근 본 상품 목록이 index 페이지에 노출되며, 쿠키나 세션을 사용하여 구현되었습니다.

## 기술적 구현 및 관리

### 데이터베이스 및 트랜잭션 관리
- **DBCP2:** 데이터베이스 연결을 위해 DBCP2를 설정하였으며, `initialSize`, `maxTotal`, `maxIdle`, `minIdle`을 모두 5로 설정했습니다.
- **트랜잭션 관리:** Isolation level을 READ COMMITTED로 설정하고, Auto Commit을 사용하지 않습니다. Error 발생 시 RollBack 처리하며, Connection은 ThreadLocal을 사용하여 관리합니다.

### 테스트
- **테스트 코드:** Repository와 Service에 대한 테스트 코드를 작성하여 기능의 정상 동작을 보장합니다. Mockito를 사용하여 테스트 케이스를 작성하고, 테스트 데이터는 RollBack 처리됩니다.
