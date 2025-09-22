프로젝트 개요
 - 프로젝트 목적: 배포와 관련된 기술을 배우고, 코드의 효율성을 높임
 - 주요 학습 목표: 코드의 이해, 수정, 배포
 
기술 스택
 - Language: Java 17
 - IDE: IntelliJ IDEA
 - AWS: EC2, RDS

레벨 별 작업 요약
내용 참고 : https://github.com/doldollee00/spring-plus/pulls?q=is%3Apr+is%3Aclosed

Level 1.
 - saveTodo 부분에 @transactional 추가
Level 2.
 - user엔티티에 nickname 추가
 - 추가된 nickname을 필요로 하는 부분에 모두 추가
Level 3.
 - TodoRepository에 JPQL 쿼리문 추가 및 서비스,컨트롤러 단 수정
Level 4.
 - nickname 추가로 인한 수정 및 예상응답 수정
Level 5.
 - @after -> @before 변경
Level 6.
 - Todo 엔티티에 cascade 구문 추가
Level 7.
 - FETCH JOIN 을 사용한 쿼리문
Level 8.
 - 기존 JPQL 쿼리문을 삭제하고 QueryDSL로 변경
Level 9.
 - FilterConfig, WebConfig 삭제 및 SecurityConfig 생성
 - Auth 관련 어노테이션 삭제
Level 10.
 - QueryDSL을 활용한 검색 API 추가
Level 11.
 - log 테이블 생성 및 manager와 log 의 서비스 부분 수정
Level 12.
 - AWS EC2를 활용한 어플리케이션 구현
 - helpth check를 위한 API 생성
 - RDS 데이터베이스 구축 및 EC2 연동

# SPRING PLUS
<img width="828" height="146" alt="image" src="https://github.com/user-attachments/assets/85f7cc70-f979-4178-9dae-0ca1a5826b23" />
- IP:8080/health


 
트러블 슈팅
 - 구현 과정에서 겪은 어려움과 해결 방법 기록 (https://dinga87.tistory.com/category/SPARTA%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)
 



