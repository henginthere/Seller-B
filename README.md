<div align="center">
  <br />
  <img src="./assets/SellerB_logo.png" alt="SellerB" width = "250"/>
  <br />
  <h1>비대면 전자제품 상담 서비스 SellerB</h1>
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

<div id="1">
<br><br>
</div>

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
        <td align="center"> 👑팀장 <br> UI/UX <br> 주요 화면 UI구현 <br> API 연결
        <td align="center">UI/UX <br> WebRTC <br> API연결 
        <td align="center">REST API <br> JWT <br> 서버관리 및 배포 <br> UCC 제작 
        <td align="center">REST API <br> WebRTC <br> 서버관리 및 배포 
        <td align="center">Android <br> 앱개발 <br> WebRTC <br> 인프라 구축
    </tr>
</table>

<div id="2">
<br><br>
</div>

## :bulb: 서비스 소개

### SellerB는 비대면 화상으로 일대일 전자제품 맞춤 상담 서비스를 제공합니다.

> 전자제품을 판매하는 기업들은 가까이에서 소비자들을 만나기 위해 전국에 오프라인 매장을 운영하고 있습니다. 
하지만 고객이 많이 몰리는 매장에서는 인력 부족 현상, 반대 상황의 매장에서는 인력이 낭비되는 현상이 발생합니다. 
 가까이에서 소비자들을 만나지만, 고객들에게 아직 제품의 정보는 멀기만 합니다. 고객이 사전지식 없이 방문한다면, 매장의 직원이 모두 상담중이라면, 대면 상담이 부담스럽다면? 
>
> **💡 저희는 이런 문제상황들을 인식하고, 기업과 고객 모두에게 솔루션을 제공하기 위해 SellerB를 기획하였습니다.**
>

<div id="3">
<br><br>
</div>

## ✨ SellerB와 함께라면

✔️ 고객은 QR코드 스캔으로 간단하게 궁금한 제품에 대해 상담을 신청할 수 있습니다.

✔️ 매장 내 직원이 모두 상담중이라도, 기약없이 기다리지 않아도 됩니다. 

✔️ 상담원은 어디서나 근무 가능! 매장에 구애받지 않고 일할 수 있습니다. 

✔️ 대면 상담이 부담스럽다면 바로 비대면 상담을 신청할 수 있습니다.

<div id="4">
<br><br>
</div>

### :clapper: SellerB 소개 영상

[![SellerB UCC](http://img.youtube.com/vi/_2xRYUEkumY/0.jpg)](https://youtu.be/_2xRYUEkumY?t=0s)


<div id="5">
<br><br>
</div>


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

<div id="6">
<br><br>
</div>

## :computer: 시스템 아키텍처

### 시스템 구성

<img src="./assets/System_Architecture.png" alt="Architecture" width = "800"/>

### CI/CD 배포 흐름도

<img src="./assets/CICD.jpg" alt="CI/CD" width = "800">

<div id="7">
<br><br>
</div>

## :star: 주요 기능

📍 관리자는 상담사들과 전체 회의를 진행하면서 소통할 수 있습니다. 

<img src="./assets/전체회의최신본.gif" alt="" width = "800">

📍 메인 화면에서 전환 후, 괸리자 혹은 상담사가 로그인합니다. 

<img src="./assets/메인화면+로그인.gif" alt="" width = "800">

📍 등록해둔 제품 전체 목록을 조회하고, 제품군 분류 및 검색어로도 조회가 가능합니다.

<img src="./assets/제품관리.gif" alt="" width = "800">

📍 제품 상세 정보를 조회하는 페이지입니다. 

<img src="./assets/제품상세.gif" alt="" width = "800">

<div id="8">
<br><br>
</div>

## :clock1: 프로젝트 기간

### 22.07.11 ~ 22.08.19

- 기획 및 설계 : 22.07.11 ~ 22.07.22
- 프로젝트 구현 : 22.07.25 ~ 22.08.15
- 버그 수정 및 산출물 정리 : 22.08.15 ~ 22.08.18

<div id="9">
<br>
</div>

## :clipboard: 프로젝트 산출물

- [와이어프레임](https://www.figma.com/file/TvetUR9WZYSJzDOtnD8qs8/%EC%85%80%EB%9F%AC%EB%B9%84?node-id=5%3A3)
- [컨벤션 목록](https://www.notion.so/66fed91b5656473b93ef3e120bed1119)
- [ERD](https://www.erdcloud.com/d/Dsw5aLs3sden8Kgsu)
- [시연 시나리오](./exec/SellerB%20%EC%8B%9C%EC%97%B0%20%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4.pdf)
- 발표 자료 -
- [포팅 매뉴얼](./exec/%ED%8F%AC%ED%8C%85%EB%A7%A4%EB%89%B4%EC%96%BC%20%EB%B0%8F%20%EC%99%B8%EB%B6%80%20%EC%84%9C%EB%B9%84%EC%8A%A4%20%EC%A0%95%EB%B3%B4.pdf)
