# πλΉλλ©΄ νμμ μν κ·Έλ£Ήμ¨μ΄ μλΉμ€ Ants
## **Ants**
 - **Ants**λ λΉλλ©΄ νμκ³Ό ν¨μ¨μ μΈ μλ¬΄ μνμ μ§μνλ μΉ μλΉμ€μλλ€.
 - Antsλ μμ©νλ μ¬λ¬ κ·Έλ£Ήμ¨μ΄ μλΉμ€λ₯Ό λͺ¨ν°λΈλ‘ νκ³  μμ΅λλ€.


## **λΈλμΉ κ΄λ¦¬ μ λ΅**
![](https://images.velog.io/images/nunddu/post/0c7eb99c-771c-469b-bee2-4cc8d1d84c91/image.png)

Git Flow Branch μ λ΅μ λ³ννμ¬ λΈλμΉλ₯Ό κ΄λ¦¬νκ³  μμ΅λλ€.   
`master` - νλ‘μ νΈ λ°°ν¬μ μ¬μ©νλ λΈλμΉμλλ€.  
`release` - λ°°ν¬λ₯Ό μ€λΉνλ λ¨κ³μμ μ¬μ©ν©λλ€.  
`development` - κΈ°λ₯μ΄ μΆκ°λ  λ μ¬μ©νλ λΈλμΉμλλ€.   
`κ°μΈ μμ λΈλμΉ` - κ°μΈλ³ κΈ°λ₯ κ°λ°μ μ§ννλ λΈλμΉμλλ€.   

## **νλ‘μ νΈ κ΅¬μ±λ**
![](https://images.velog.io/images/nunddu/post/5d026103-961f-4b2a-8988-676ffaf42a5f/image.png)

## ERD
![](https://images.velog.io/images/nunddu/post/185263d5-54f1-432d-bddc-5df4bcce4865/image.png)
## **μ£Όμ κΈ°λ₯**
 - λ‘κ·ΈμΈ / νμκ°μ
 - μ¬μ©μ κ·Όνκ΄λ¦¬(μΆ/ν΄κ·Ό)
 - μ€μκ° νμνμ κΈ°λ₯
 - μ€μκ° μ±ν κΈ°λ₯
 - κ°μΈ λ©μ μ 
 - κ²μν κ΄λ¦¬
 - κ°μ μΉμΈ λ©μΌ μ μ‘
 - κ·Έλ£Ή κ΄λ¦¬ 
 - μΊλ¦°λ κ΄λ¦¬

## **UI/UX**
<div align="center">
<figure>
    <img src="https://images.velog.io/images/nunddu/post/a6f3351e-7f6b-4f0a-ae8e-ff24d1747e70/image.png" title="λ©μΈνλ©΄">    
    <h3><i>λ©μΈνλ©΄</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/74cb5318-6820-4d31-b4db-6d7c49587c1f/image.png" title="μΊλ¦°λ">    
    <h3><i>μΊλ¦°λ κ΄λ¦¬</i></h2>
</figure>
<figure>
    <img src="https://user-images.githubusercontent.com/41180841/143521139-3601d529-4cab-4e92-a150-fa318fcba455.png" title="νμνμ">
    <h3><i>νμνμ</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/24d620b7-4816-49a8-94bf-a98a7cbd3d9d/image.png" title="κ²μν νλ©΄">    
    <h3><i>κ²μν νλ©΄</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/26c50e9d-1332-497e-a3b7-00e5425682c9/image.png" title="μλ£μ€ νλ©΄">    
    <h3><i>μλ£μ€ νλ©΄</i></h2>
</figure>
</div>

## **κ°λ° μ΄μ**
 - Open Graph Protocol
   - μΉ μ¬μ΄νΈμ λ©νλ°μ΄ν°λ₯Ό μΆμΆν΄ μΉ μ¬μ΄νΈ μ λ³΄μ λ―Έλ¦¬ λ³΄κΈ° μ΄λ―Έμ§λ₯Ό κ°μ Έμ¨ ν λΆλ§ν¬ κΈ°λ₯μ κ΅¬νν¨.
 - Kurento WebRTC Media Server
     - μ²μ μ¬μ©νλ WebRTC μ€νμμ€μ httpsλ₯Ό μ μ©νμ΅λλ€.
     - kurento media server, application, dbλ₯Ό κ° docker μ»¨νμ΄λλ‘ λΆλ¦¬ν΄μ κ΅¬μ±νλλ°, containerκ° ν΅μ μ΄ μλλ λ¬Έμ  λ°μ
     - docker container ν¬νΈ ν¬μλ©κ³Ό docker-compose.yml νμΌ network μ€μ  λ¬Έμ λ₯Ό μμ νμ¬ ν΄κ²°
 - AWS 20λ§μ κ³ΌκΈ μ¬κ±΄
   - μ¬κ±΄μ λ°λ¨
     - AWS νλ¦¬ν°μ΄ μκΈμ λ‘ ec2 instanceλ₯Ό μ¬μ©νλ μ€ λ©λͺ¨λ¦¬ λΆμ‘± λ¬Έμ λ‘ μλ²κ° λ€μ΄λλ νμλ°μ
     - νμ€νΈμ©μΌλ‘ μ¬μ©νλ ec2μκΈ° λλ¬Έμ μμμ μΌλ‘ μκΈμ λ₯Ό λμ
   - μ§ν
     - λ©μΈμΌλ‘ μ¬μ©νλ ec2μ μ€μ μ΄ μ λ§λ¬΄λ¦¬λλ©΄μ νμ€νΈμ© ec2λ μνμ§
   - κ²°λ§
     - μΌλ§ ν, AWSλ‘λΆν° 20λ§μ μλΉμ μ²­κ΅¬μκ° λ λΌμ΄
     - AWS μΈ‘μ μκΈμ  λ³κ²½ ν ec2λ₯Ό κ±°μ μ¬μ©νμ§ μμμ μ μ΄ννμ¬ λ©΄μ  λ°μ μ μμλ€.

## **μ¬μ© κΈ°μ  λ° νκ²½**
### **FrontEnd**
 - Vue.js v3.0
 - Quasar Framework v3.0.2
   

### **BackEnd**

- SpringBoot
  - Gradle v6.7
  - SpringBoot v2.4.5
- MySQL v5.7
- JPA
- QueryDSL v4.4.0
- Open Source
  - [Kurento WebRTC Media Server](https://github.com/Kurento/kurento-tutorial-java) (release 6.16.0)


### **Infra**
 - AWS
 - Docker



