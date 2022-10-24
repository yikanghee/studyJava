# atto-research 과제입니다
---

## DDL SQL문 
create table host_entity (
id bigint not null auto_increment,
created_date datetime(6),
modified_date datetime(6),
host_check varchar(255),
ip varchar(255),
name varchar(255),
primary key (id)
)

alter table host_entity
add constraint UK_tpj3uqno9qlue2ph5nobtjf36 unique (ip)

alter table host_entity
add constraint UK_30ixuao4mx0g9ekrkxyfdvncu unique (name)
---
## 기능설명
1. IP, NAME 등록 기능
2. IP 한개를 조회 기능
3. 등록된 IP 전부 조회 기능
4. 수정기능
5. 삭제기능
6. 전체 삭제 기능
7. 상시 IP Alive 여부 조회 기능
---
## 과제조건
1. 호스트 조회/등록/수정/삭제 API
2. 호스트 등록 시 필드 name, ip 고정
3. name, ip는 중복 불가능
4. 조회 결과 필드에는 등록/수정 시간을 포함
5. 서버가 재시작되어도 등록된 호스트들은 유지
6. 호스트 등록은 100개로 제한
7. InetAddress.isReachable()를 사용하여 Alive 여부 확인
8. 조회 결과 필드에는 Avlie 상태와 마지막 Alive 시간을 포함
9. 조회의 단위는 한 호스트, 전체 호스트를 제공
10. 전체 조회 시 100개의 호스트가 모두 Unreachable 상태여도 조회는 1초 이내에 응답
11. 10번 조건을 위해서 상시 조회 기능을 추가
12. MariaDB 사용
