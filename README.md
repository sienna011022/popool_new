## POPOOL
> MSA 기반 인사내역을 거래하고 관리하는 시스템입니다
> 한이음 프로젝트에 참여하는 5명의 대학생과 현업 멘토분이 참여 중 입니다.

- 프로젝트 기간: 2022.04 - continue
![image](https://user-images.githubusercontent.com/90383376/196314580-23022e7f-2639-4d45-9d8a-ca5cf052b4d0.png)

## 주제 및 목적

> 스프링을 기반으로 전반적인 백엔드 개발에 대한 이해를 높인다.
> 팀을 이루어 협업 프로젝트를 진행해본다.

## 기술 스택
> 해당 프로젝트를 수행하며 사용한 기술 스택입니다.

- Java 8
- Spring boot , Spring Data Jpa , Spring Cloud 
- QueryDSL
- Gradle
- Swagger
- Redis
- Github Action
- AWS codeDeploy, AWS S3
- H2, mysql 
```
팀원 중 김성윤이 맡은 Career 모듈에만 해당되는 README입니다
```
## Career 모듈 도메인 설계
- API 명세서, 기능 정의서,ERD가 포함되어 있습니다
[도메인 설계](https://www.notion.so/CAREER-46ffed72a0f0494f8a330fb9725ef7d5)
## Career모듈 세세한 기능 목록
> Career 모듈의 세부적인 기능 목록을 나열한 것입니다

##### 인사
- 인사 등록
- 인사조회
- 인사수정
- 인사삭제

##### 평가
- 평가등록(최초 등록)
- 평가조회
- 평가수정
- 평가삭제


##### 등급
- 평가 평균 계산 후 등급 산출
- 인사내역과 평가 등급 매핑
- 최종 인사 내역 산출

##### MSA
- gateway 연결
- member 정보 feign client로 받아오기
- Eureka 등록

##### ETC
- 에외처리
- AWS S3 파일 저장
- AWS S3 파일 조회
- JPA Auditing
- swagger를 이용한 API
- queryDsl
- redis

##### CI/CD (진행 중)
- Travis
- AWS CodeDeploy


## 프로젝트 진행 포스팅
> 프로젝트 개발 과정에서 다양한 고민과 좌충우돌을 적은 포스팅이며 링크로 연결되어 있습니다.

 - [MSA란?](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-MSA-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-1)
 - [Eureka](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-MSA%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-2-Eureka-%EC%84%9C%EB%B2%84%ED%81%B4%EB%9D%BC%EC%9D%B4%EC%96%B8%ED%8A%B8-%EC%84%A4%EC%A0%95)
 - [Eureka호출 구현](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8CMSA%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B83-%EC%84%9C%EB%B9%84%EC%8A%A4-%ED%98%B8%EC%B6%9C-%EA%B5%AC%ED%98%84)
 - [Feign 적용기](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-MSA-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B84-feign-client-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0)
 - [GateWay 적용기](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-MSA-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B85-Spring-Cloud-Gateway-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)
 - [AWS S3 파일 업로드](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-%EC%8A%A4%ED%94%84%EB%A7%81-AWS-S3-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-%EB%B0%8F-controller-%EC%97%90%EB%9F%AC-%ED%95%B4%EA%B2%B0)
 - [AWS S3 파일 조회](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-AWS-S3-%EC%A0%80%EC%9E%A5%ED%95%9C-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EA%B0%80%EC%A0%B8%EC%99%80%EC%84%9C-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EC%A1%B0%ED%9A%8C%ED%95%98%EA%B8%B0)
 - [Controller의 SRP에 대한 고민](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8CController%EB%8A%94-SRP%EB%8B%A8%EC%9D%BC-%EC%B1%85%EC%9E%84-%EC%9B%90%EC%B9%99%EC%9D%84-%EC%A7%80%ED%82%A4%EB%8A%94%EA%B1%B8%EA%B9%8C)
 - [CI/CD](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-CICD)
 - [CI/CD와 AWS S3](https://sienna1022.tistory.com/m/entry/%ED%95%9C%EC%9D%B4%EC%9D%8C-CICD-%EC%99%80-S3)





