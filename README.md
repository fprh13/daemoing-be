## 📚 목차

- [인원 소개](#-인원-소개)
- [사용 기술 및 환경](#-사용-기술-및-환경)
- [프로젝트 소개](#-프로젝트-소개)
- [주요 기능](#-주요-기능)
- [프로젝트 구조](#-프로젝트-구조)

  <br/>

## 🧑‍💻 인원 소개
|                                                                조영무                                                                |                                                                                                              
|:---------------------------------------------------------------------------------------------------------------------------------:|
| <img width="160px" src="https://avatars.githubusercontent.com/u/75081608?s=400&u=c4c22f3af10105e0fb18a9d346988e9403a533f6&v=4" /> |
|                                               [@fprh13](https://github.com/fprh13)                                                |
|                                                                백엔드                                                                |

<br/>

## ⚒️ 사용 기술 및 환경
### ⚙️ Backend

| 기술 & 환경   | 버전     |
|-----------|--------|
| Spring Boot | 3.2    |
| Gradle    | 8.5    |
| Java      | 17.0.9 |
| MySQL     | 8.0.33 |
| Redis     | 6.2.12 |
| Qeurydsl  | 5.0.0  |

<br/>

## 📌 프로젝트 소개
### 대학생 모임 중계 서비스 (대모중)

> **개발기간: 2024.01.04 ~ 2024.01.25**

`대모중`은 대학생들을 위한 대학생 모임 중계 서비스입니다.<br/>
원래는 팀 프로젝트로 기획되었으나 예기치 못한 사정으로 진행이 불가능해져서, 저 개인이 만든 프로젝트입니다.<br/>

<br/>

## ✨ 주요 기능
### 💡 원하는 모임에 자유롭게 신청할 수 있습니다.

- 모임 수 상관 없이 자유롭게 모임에 신청
- 방장이 모임 참가에 수락하면 모임의 참가자가 됩니다.

### 💡 자유롭게 원하는 모임을 생성 하여 관리할 수 있습니다.

- 모임의 방장이되어 신청자에 대한 대기,거절,수락을 선택하여 관리 할 수 있습니다.

### 💡 원하는 모임을 찾기 위한 검색 조건을 세부적으로 결정 할 수 있습니다.

- 검색어, 대학, 지역, 카테고리, 인기순, 조회수 순 등 원하는 조건으로 조회 할 수 있습니다.

### 💡 자유롭게 자기 생각을 글로 남길 수 있습니다.

- 커뮤니티를 통해 자기 생각을 공유할 수 있습니다.

<br/>

## 📁 프로젝트 구조
```
 src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── daemoing
    │   │           └── daemo
    │   │               ├── DaemoApplication.java
    │   │               ├── controller
    │   │               │   ├── AuthController.java
    │   │               │   ├── BoardController.java
    │   │               │   ├── CategoryController.java
    │   │               │   ├── ClubController.java
    │   │               │   ├── UserController.java
    │   │               │   └── response
    │   │               │       └── ResponseDto.java
    │   │               ├── domain
    │   │               │   ├── Board.java
    │   │               │   ├── Category.java
    │   │               │   ├── Club.java
    │   │               │   ├── Univ.java
    │   │               │   ├── User.java
    │   │               │   ├── UserClub.java
    │   │               │   └── type
    │   │               │       ├── ClubAccessState.java
    │   │               │       ├── Gender.java
    │   │               │       └── Role.java
    │   │               ├── dto
    │   │               │   ├── AuthDto.java
    │   │               │   ├── BoardDto.java
    │   │               │   ├── CategoryDto.java
    │   │               │   ├── ClubDto.java
    │   │               │   └── UserDto.java
    │   │               ├── global
    │   │               │   ├── auditing
    │   │               │   │   ├── BaseCreateByEntity.java
    │   │               │   │   ├── BaseTimeEntity.java
    │   │               │   │   └── UserAuditorAware.java
    │   │               │   ├── common
    │   │               │   │   ├── CustomErrorResponseDto.java
    │   │               │   │   ├── ErrorCode.java
    │   │               │   │   └── exception
    │   │               │   │       ├── CustomException.java
    │   │               │   │       └── CustomExceptionHandler.java
    │   │               │   ├── configuration
    │   │               │   │   ├── OpenApiConfig.java
    │   │               │   │   ├── RedisRepositoryConfig.java
    │   │               │   │   └── SecurityConfig.java
    │   │               │   └── security
    │   │               │       ├── JwtAccessDeniedHandler.java
    │   │               │       ├── JwtAuthenticationEntryPoint.java
    │   │               │       ├── JwtAuthenticationFilter.java
    │   │               │       ├── JwtTokenProvider.java
    │   │               │       ├── UserDetailsImpl.java
    │   │               │       └── UserDetailsServiceImpl.java
    │   │               ├── repository
    │   │               │   ├── BoardRepository.java
    │   │               │   ├── CategoryRepository.java
    │   │               │   ├── ClubRepository.java
    │   │               │   ├── UserClubRepository.java
    │   │               │   ├── UserRepository.java
    │   │               │   ├── custom
    │   │               │   │   ├── CategoryRepositoryCustom.java
    │   │               │   │   └── CategoryRepositoryCustomImpl.java
    │   │               │   └── init
    │   │               │       └── CategoryInitDb.java
    │   │               └── service
    │   │                   ├── AuthService.java
    │   │                   ├── BoardService.java
    │   │                   ├── CategoryService.java
    │   │                   ├── ClubService.java
    │   │                   ├── RedisService.java
    │   │                   ├── UserService.java
    │   │                   └── validator
    │   │                       └── ClubValidator.java

```
