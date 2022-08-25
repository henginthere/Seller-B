<div align="center">
  <br />
  <img src="./assets/SellerB_logo.png" alt="SellerB" width = "250"/>
  <br />
  <h1>비대면 제품 상담 서비스 SellerB</h1>
  <br />
</div>

## :pushpin: 목차

1. [팀원 소개](#1)
2. [서비스 소개](#2)
3. [기술 스택](#3)
4. [시스템 아키텍처](#4)
5. [주요 기능](#5)
6. [프로젝트 기간](#6)
7. [프로젝트 산출물](#7)

<div id="1"></div>

## :sparkling_heart: 팀원 소개

<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/MilanoBeer">
            <img src="https://avatars.githubusercontent.com/u/86315623?v=4" width="140px" /> <br><br> 신혜연 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/dlacogus5239">
            <img src="https://avatars.githubusercontent.com/u/71282435?v=4" width="140px" /> <br><br> 임채현 <br>(Front-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/henginthere">
            <img src="https://avatars.githubusercontent.com/u/58303100?v=4" width="140px" /> <br><br> 배혜연 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/ljhyung">
            <img src="https://avatars.githubusercontent.com/u/97655625?v=4" width="140px" /> <br><br> 이주형 <br>(Back-End) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/GideokLee">
            <img src="https://avatars.githubusercontent.com/u/76939190?v=4" width="140px" /> <br><br> 이기덕 <br>(AOS) </a> <br></td>
    </tr>
    <tr>
        <td align="center">UI/UX
        <td align="center">UI/UX
        <td align="center">REST API
        <td align="center">REST API
        <td align="center">Android
    </tr>
</table>

<div id="2"></div>

## :bulb: 서비스 소개

### SellerB는 비대면 화상 일대일 전자제품 맞춤 상담 서비스입니다.

    전자제품을 판매하는 기업은 오프라인 매장을 운영합니다. 
    기업 입장에서 고객이 많이 몰리는 매장에서는 인력 부족 현상을, 그렇지 않은 매장에서는 인력을 낭비하는 상황을 겪게 됩니다.
    그리고 고객이 전자제품에 대한 사전지식이 부족한 경우 전자제품을 구매하기 위해서는 전문가와의 상담이 필요합니다. 
    이때 매장에 상주하는 직원이 모두 상담중인 경우, 고객은 상담을 위해 기다려야합니다.
    때로는 상담원과의 대면 상담이 부담스러운 고객이 있을 수도 있습니다.
    이러한 문제 상황들을 개선하기 위해 SellerB를 기획하였습니다.

### :clapper: SellerB 소개 영상

[![SellerB UCC](http://img.youtube.com/vi/_2xRYUEkumY/0.jpg)](https://youtu.be/_2xRYUEkumY?t=0s)

<div id="3"></div>

## :hammer: 기술 스택

<details>
 <summary><h3>기술 스택</h3></summary>
 <div markdown="1">
  <table border="1">
<thead>
  <tr>
    <th>구분</th>
    <th>기술 스택</th>
    <th>상세 내용</th>
    <th>버전</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>공통</td>
    <td>형상관리</td>
    <td>Gitlab</td>
    <td>-</td>
  </tr>
  <tr>
    <td></td>
    <td>이슈관리</td>
    <td>Jira</td>
    <td>-</td>
  </tr>
  <tr>
    <td></td>
    <td>커뮤니케이션</td>
    <td>Mattermost, Notion</td>
    <td>-</td>
  </tr>
  <tr>
    <td>BackEnd</td>
    <td>DB</td>
    <td>MariaDB</td>
    <td>10.8.3</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td>JPA</td>
    <td>-</td>
  </tr>
  <tr>
    <td></td>
    <td>Java</td>
    <td>Zulu</td>
    <td>11.0.16</td>
  </tr>
  <tr>
    <td></td>
    <td>Spring</td>
    <td>Springboot</td>
    <td>2.7.2</td>
  </tr>
  <tr>
    <td></td>
    <td>IDE</td>
    <td>Intellij</td>
    <td>2022.1.3</td>
  </tr>
  <tr>
    <td></td>
    <td>Cloud Storage</td>
    <td>AWS S3</td>
    <td>-</td>
  </tr>
  <tr>
    <td></td>
    <td>Build</td>
    <td>Gradle</td>
    <td>7.5</td>
  </tr>
  <tr>
    <td></td>
    <td>WebRTC</td>
    <td>Kurento Media Server</td>
    <td>6.16.0</td>
  </tr>
  <tr>
    <td></td>
    <td>API Docs</td>
    <td>Openapi</td>
    <td>1.6.6</td>
  </tr>
  <tr>
    <td></td>
    <td>firebase</td>
    <td>firebase fcm</td>
    <td>9.0.0</td>
  </tr>
  <tr>
    <td>FrontEnd</td>
    <td>IDE</td>
    <td>Visual Studio Code</td>
    <td>-</td>
  </tr>
  <tr>
    <td></td>
    <td>HTML5</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td>CSS3</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td>JavaScript(ES6)</td>
    <td></td>
    <td></td>
  </tr>
    <tr>
    <td></td>
    <td>React</td>
    <td>React</td>
    <td>18.2.0</td>
  </tr>
  <tr>
    <td>Android</td>
    <td>IDE</td>
    <td>Android Studio</td>
    <td>7.2.1</td>
  </tr>
  <tr>
    <td>Server</td>
    <td>Server</td>
    <td>AWS EC2</td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td>Platform</td>
    <td>Ubuntu</td>
    <td>20.04 LTS</td>
  </tr>
  <tr>
    <td></td>
    <td>CI/CD</td>
    <td>Docker</td>
    <td>20.10.17</td>
  </tr>
  <tr>
    <td></td>
    <td>CI/CD</td>
    <td>Jenkins</td>
    <td>2.346</td>
  </tr>
</tbody>
    </table>
 </div> 
</details>

### :mag: 파트별 사용 기술을 자세히 보려면?

- [Back-End](BE)
- [Front-End](FE)
- [Android](AOS)

<div id="4"></div>

## :computer: 시스템 아키텍처

### 시스템 구성

<img src="./assets/System_Architecture.png" alt="Architecture" width = "800"/>

### CI/CD 배포 흐름도

<img src="./assets/CICD.jpg" alt="CI/CD" width = "800">

<div id="5"></div>

## :star: 주요 기능

- 메인 화면 및 로그인

<img src="./assets/사이트 메인 상담사 로그인.gif" alt="">

- 공지사항 작성

<img title="" src="assets/3cf0638a2171092d18ed2eb57484386f2e474b2a.gif" alt="">

- 공지사항 수정

<img src="./assets/매니저 공지사항 수정.gif">

- 매니저 마이페이지

<img src="./assets/매니저 마이페이지.gif" alt="">

- 제품 등록

<img src="./assets/제품 등록.gif" alt="">







<div id="6"></div>



## :clock1: 프로젝트 기간

### 22.07.11 ~ 22.08.19

- 기획 및 설계 : 22.07.11 ~ 22.07.22
- 프로젝트 구현 : 22.07.25 ~ 22.08.15
- 버그 수정 및 산출물 정리 : 22.08.15 ~ 22.08.18

<div id="7"></div>

## :clipboard: 프로젝트 산출물

- [와이어프레임](https://www.figma.com/file/TvetUR9WZYSJzDOtnD8qs8/%EC%85%80%EB%9F%AC%EB%B9%84?node-id=5%3A3)
- [컨벤션 목록](https://www.notion.so/66fed91b5656473b93ef3e120bed1119)
- [ERD](https://www.erdcloud.com/d/Dsw5aLs3sden8Kgsu)
- [시연 시나리오](./exec/SellerB 시연 시나리오.pdf)
- 발표 자료 -
- [포팅 매뉴얼](./exec/포팅매뉴얼 및 외부 서비스 정보.pdf)
