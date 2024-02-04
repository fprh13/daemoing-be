## 📚 목차

- [인원 소개](##-웹개발-인원 소개)
- [사용 기술](##-사용-기술-및-환경)
- [프로젝트 소개](##-프로젝트-소개)
- [주요 기능](##-주요-기능)
- [프로젝트 구조](##-프로젝트-구조)

## 웹개발 인원 소개

|                                                                조영무                                                                |                                                                                                              
|:---------------------------------------------------------------------------------------------------------------------------------:|
| <img width="160px" src="https://avatars.githubusercontent.com/u/75081608?s=400&u=c4c22f3af10105e0fb18a9d346988e9403a533f6&v=4" /> |
|                                               [@fprh13](https://github.com/fprh13)                                                |
|                                                                백엔드                                                                |

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

## 프로젝트 소개

### 대학생 모임 중계 서비스 (대모중)

> **개발기간: 2024.01.04 ~ 2024.01.25**

`대모중`은 대학생들을 위한 대학생 모임 중계 서비스입니다.<br/>
원래는 팀 프로젝트로 기획되었으나 예기치 못한 사정으로 진행이 불가능해져서, 저 개인이 만든 프로젝트입니다.<br/>



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


## 프로젝트 구조

```
daemo
│   │       │               ├── DaemoApplication.class
│   │       │               ├── controller
│   │       │               │   ├── AuthController.class
│   │       │               │   ├── BoardController.class
│   │       │               │   ├── CategoryController.class
│   │       │               │   ├── ClubController.class
│   │       │               │   ├── UserController.class
│   │       │               │   └── response
│   │       │               │       ├── ResponseDto$ResponseDtoBuilder.class
│   │       │               │       └── ResponseDto.class
│   │       │               ├── domain
│   │       │               │   ├── Board.class
│   │       │               │   ├── Category.class
│   │       │               │   ├── Club.class
│   │       │               │   ├── QBoard.class
│   │       │               │   ├── QCategory.class
│   │       │               │   ├── QClub.class
│   │       │               │   ├── QUniv.class
│   │       │               │   ├── QUser.class
│   │       │               │   ├── QUserClub.class
│   │       │               │   ├── Univ.class
│   │       │               │   ├── User.class
│   │       │               │   ├── UserClub.class
│   │       │               │   └── type
│   │       │               │       ├── ClubAccessState.class
│   │       │               │       ├── Gender.class
│   │       │               │       └── Role.class
│   │       │               ├── dto
│   │       │               │   ├── AuthDto$LoginDto.class
│   │       │               │   ├── AuthDto$TokenDto.class
│   │       │               │   ├── AuthDto.class
│   │       │               │   ├── BoardDto$DetailDto.class
│   │       │               │   ├── BoardDto$PageDto.class
│   │       │               │   ├── BoardDto$UpdateDto.class
│   │       │               │   ├── BoardDto$WriteDto.class
│   │       │               │   ├── BoardDto.class
│   │       │               │   ├── CategoryDto$ClubListDto.class
│   │       │               │   ├── CategoryDto$DeleteDto.class
│   │       │               │   ├── CategoryDto$ReadReqDto.class
│   │       │               │   ├── CategoryDto$ReadResDto.class
│   │       │               │   ├── CategoryDto$SaveDto.class
│   │       │               │   ├── CategoryDto$UpdateDto.class
│   │       │               │   ├── CategoryDto.class
│   │       │               │   ├── ClubDto$ApplicantListDto.class
│   │       │               │   ├── ClubDto$DetailDto.class
│   │       │               │   ├── ClubDto$PageResDto.class
│   │       │               │   ├── ClubDto$SaveDto.class
│   │       │               │   ├── ClubDto$UpdateDto.class
│   │       │               │   ├── ClubDto$pageReqDto.class
│   │       │               │   ├── ClubDto.class
│   │       │               │   ├── QCategoryDto_ClubListDto.class
│   │       │               │   ├── UserDto$InfoDto.class
│   │       │               │   ├── UserDto$JoinDto.class
│   │       │               │   ├── UserDto$UpdateDto.class
│   │       │               │   └── UserDto.class
│   │       │               ├── global
│   │       │               │   ├── auditing
│   │       │               │   │   ├── BaseCreateByEntity.class
│   │       │               │   │   ├── BaseTimeEntity.class
│   │       │               │   │   ├── QBaseCreateByEntity.class
│   │       │               │   │   ├── QBaseTimeEntity.class
│   │       │               │   │   └── UserAuditorAware.class
│   │       │               │   ├── common
│   │       │               │   │   ├── CustomErrorResponseDto$CustomErrorResponseDtoBuilder.class
│   │       │               │   │   ├── CustomErrorResponseDto.class
│   │       │               │   │   ├── ErrorCode.class
│   │       │               │   │   └── exception
│   │       │               │   │       ├── CustomException.class
│   │       │               │   │       └── CustomExceptionHandler.class
│   │       │               │   ├── configuration
│   │       │               │   │   ├── OpenApiConfig.class
│   │       │               │   │   ├── RedisRepositoryConfig.class
│   │       │               │   │   └── SecurityConfig.class
│   │       │               │   └── security
│   │       │               │       ├── JwtAccessDeniedHandler.class
│   │       │               │       ├── JwtAuthenticationEntryPoint.class
│   │       │               │       ├── JwtAuthenticationFilter.class
│   │       │               │       ├── JwtTokenProvider.class
│   │       │               │       ├── UserDetailsImpl.class
│   │       │               │       └── UserDetailsServiceImpl.class
│   │       │               ├── repository
│   │       │               │   ├── BoardRepository.class
│   │       │               │   ├── CategoryRepository.class
│   │       │               │   ├── ClubRepository.class
│   │       │               │   ├── UserClubRepository.class
│   │       │               │   ├── UserRepository.class
│   │       │               │   ├── custom
│   │       │               │   │   ├── CategoryRepositoryCustom.class
│   │       │               │   │   └── CategoryRepositoryCustomImpl.class
│   │       │               │   └── init
│   │       │               │       ├── CategoryInitDb$InitService.class
│   │       │               │       └── CategoryInitDb.class
│   │       │               └── service
│   │       │                   ├── AuthService.class
│   │       │                   ├── BoardService.class
│   │       │                   ├── CategoryService.class
│   │       │                   ├── ClubService.class
│   │       │                   ├── RedisService.class
│   │       │                   ├── UserService.class
│   │       │                   └── validator
│   │       │                       └── ClubValidator.class

```
