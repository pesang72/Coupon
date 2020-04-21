## **소개**

글쓴이 블로그 : https://duooo-story.tistory.com/

Rest API 기반 쿠폰시스템
필수 문제 구현 및 선택 사항 구현(JWT, csv Import)

쿠폰 코드 (XXXXX-XXXXXX-XXXXXXXX) 의 경우, 각 쿠폰별로 고유한 앞자리(5글자)를 가지며, 코드 생성시 중간(6단어), 마지막(8단어) 를 각각 랜덤하게 생성, 중복을 체크하면서 겹치지 않게 생성하게 됩니다.

Alarm 의 경우, 5분 단위로 현재시간의 3일, 3일 5분전 시간 정보를 조회하여 Queue에 넣고, 해당 queue를 계속 확인하는 별도 스케쥴러를 통해 log를 찍도록 하였습니다.

사용 기술 : Java, Spring Boot, mysql



## **실행 방법**

1. mysql 에 dump 파일 import진행. couponDump.sql( table, store procedure )
2. tb_user_mapping_info 에 유저정보 기입,패스워드 암호화 필요 (Test코드에 암호화 하는 코드 존재(BcryptTest.class))
3. application.properties  에서 connection 정보 수정
4. du.kakaocrop.coupon.bootstrap.CouponApplication 실행 또는 maven package후 target에 있는 jar실행



## **API 리스트 (postman을 통해 문서화, 무료버전..)**

- https://documenter.getpostman.com/view/11034591/Szf6YpDy



## **예상 서비스 아키텍쳐 구조**

![](https://sgimage.netmarble.com/images/netmarble/kofkr/20200421/s8v11587404601332.PNG)



## **DB** (SP 는 Dump에 포함, 별도 생성코드 첨부)

![](https://sgimage.netmarble.com/images/netmarble/kofkr/20200421/9war1587404538180.PNG)

- SP_PROVIDE_COUPON : 유저에게 쿠폰 발급하는 SP
- SP_REGISTER_COUPON : 쿠폰 등록하는 SP,

- TB_COUPON_INFO : 쿠폰의 정보를 가지고 있는 DB

```
CREATE TABLE `tb_coupon_info` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `COUPON_SEQ` int DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `THUMBNAIL` varchar(70) DEFAULT NULL,
  `START_DATE` timestamp NULL DEFAULT NULL,
  `END_DATE` timestamp NULL DEFAULT NULL,
  `REG_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `COUPON_COUNT` bigint DEFAULT '0',
  `IS_SERVICE` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`SEQ`),
  UNIQUE KEY `COUPON_IDX` (`COUPON_SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

| 값 | 의미 |
|---|:---:|
| COUPON_SEQ |        쿠폰 고유SEQ         |
| TITLE |    쿠폰에 관련된 타이틀     |
| THUMBNAIL |  해당 쿠폰의 섬네일 이미지  |
| START_DATE |  쿠폰 시작 일자.              |
| END_DATE |              쿠폰 마감 일자.              |
| REG_DATE |              쿠폰 등록 일자               |
| COUPON_COUNT |             쿠폰 발급 카운트              |
| IS_SERVICE   | 쿠폰 발급 상태( 쿠폰 생성후, 재생성 불가) |




- TB_COUPON_CODE_#{SEQ} : 각 쿠폰 #{SEQ} 별 쿠폰정보를 가지고 있는 테이블

```
CREATE TABLE `tb_coupon_code_#{SEQ}` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `COUPON_SEQ` mediumint DEFAULT NULL,
  `COUPON_CODE` varchar(20) DEFAULT NULL,
  `TARGET_USER_ID` varchar(70) DEFAULT NULL,
  `IS_USED` varchar(1) DEFAULT 'N',
  `REG_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MOD_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=15601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```
| 값 | 의미 |
|---|:---:|
| COUPON_SEQ |        쿠폰 고유SEQ         |
| COUPON_CODE |   쿠폰 코드     |
| TARGET_USER_ID |  해당 쿠폰을 소유한 유저SEQ  |
| IS_USED |  사용 여부             |
| REG_DATE |             쿠폰 등록 시간             |
| MOD_DATE |             쿠폰 업데이트 시간            |



- tb_coupon_prefix: 각 쿠폰별 고유한 앞자리를 기록하는 테이블
```
CREATE TABLE `tb_coupon_prefix` (
  `COUPON_SEQ` int NOT NULL,
  `COUPON_PREFIX` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`COUPON_SEQ`),
  UNIQUE KEY `prefix_uk` (`COUPON_PREFIX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
| 값 | 의미 |
|---|:---:|
| COUPON_SEQ |        쿠폰 고유SEQ         |
| COUPON_PREFIX |   쿠폰 SEQ별 고유한 앞 5자리     |



- tb_coupon_count: 각 쿠폰별 발급 개수를 나타내는 테이블
```
CREATE TABLE `tb_coupon_count` (
  `COUPON_SEQ` int NOT NULL,
  `COUNT` int DEFAULT '0',
  PRIMARY KEY (`COUPON_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```
| 값 | 의미 |
|---|:---:|
| COUPON_SEQ |        쿠폰 고유SEQ         |
| COUNT |   현재 발급된 쿠폰 수. 쿠폰 발급시, 해당 값 증가     |




- tb_coupon_user_info: 유저가 발급받은 쿠폰을 저장하고 있는 테이블
```
CREATE TABLE `tb_coupon_user_info` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `USER_SEQ` int DEFAULT NULL,
  `COUPON_SEQ` int DEFAULT NULL,
  `COUPON_CODE_SEQ` int DEFAULT NULL,
  `IS_USED` varchar(1) DEFAULT NULL,
  `REG_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SEQ`),
  UNIQUE KEY `USER_SEQ` (`USER_SEQ`,`COUPON_SEQ`),
  KEY `user_info` (`USER_SEQ`,`COUPON_SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
| 값 | 의미 |
|---|:---:|
| USER_SEQ |        유저 고유SEQ       |
| COUPON_SEQ |        쿠폰 고유SEQ         |
| COUPON_CODE_SEQ |   tb_coupon_code_#{SEQ} 의 고유SEQ값    |
| IS_USED |        사용 여부      |
| REG_DATE |       등록 일자.       |
| UPD_DATE |      업데이트(사용) 일자      |



- tb_user_mapping_info: 유저의 계정정보를 기록하는 테이블
```
CREATE TABLE `tb_user_mapping_info` (
  `USER_SEQ` int NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  `TOKEN` varchar(500) DEFAULT NULL,
  `REG_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UPD_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
| 값 | 의미 |
|---|:---:|
| USER_SEQ |        유저 고유SEQ       |
| EMAIL |        유저 계정       |
| PASSWORD |  페스워드    |
| TOKEN |        유저 토큰 정보     |
| REG_DATE |       등록 일자.       |
| UPD_DATE |       업데이트 시간       |
