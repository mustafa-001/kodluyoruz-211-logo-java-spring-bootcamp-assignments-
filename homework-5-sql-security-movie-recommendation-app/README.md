## **HOMEWORK-5**
1. Spring Profile nedir? Properties ya da yml dosya formları ile isbasi uygulamasına test
profile ekleyin.(5 Puan)

Spring Profile uygulamanın farklı versiyonları/aşamalarında çalışacak Bean'leri seçmemize yarayan teknolojidir.
Örneğin dev, test ve production profilleri belirleyerek geliştirme, test ve canlı aşamalarında farklı veritabanı konfigürasyonları kullanabiliriz. Yada testler sırasında farklı bir profile ait kodu kullanabiliriz.

`@Profile` anotasyonu yada XML'de tanımlayarak bir bean'in hangi profile ait olduğunu belirtebiliriz. 

`context.setInıtParameter("spring.profile.active", "dev") metodu, web.xml, JVM parametresii, ortam değişkeni yada Maven ile o an aktif olan profili seçebiliriz. Örn:

```
@Profile("dev")
public class DataGenerator {
```
Burada DataGenerator sınıfı verilen profile göre çalışıp çalışmayacaktır. [DataGenerator](movies/src/main/java/mutlu/movies/config/DataGenerator.java)


movies/src/main/resources/application.properties

https://www.baeldung.com/spring-profiles

[4. Hafta ödevindeki isbası uygulamasına eklenen test profili](https://github.com/LogoYazilimJavaSpringBootcamp/homework-4-mustafa-001/commit/3235916740e22516f68e730ca2feb6e73290a449)



2. SQL injection örnekleyin. Nasıl korunabiliriz?(5 Puan)

SQL injection kullanıcı girdisine göre oluşturulan SQL komutlarının istenilen dışında veritabanına zarar verebilecek SQL komutlarının çalıştırılmasıdır. Örneğin `"SELECT * FROM users WHERE name = ?"` komutunda kullanıcıdan bir isim onu kullanmak üzere tasarlanmışken gelen bir `isim; DROP TABLE users;` komutu veritabanının users tablosunun silinmesine neden olur.

```
//Eğer client paymentId değişkenini gönderirken "5; drop table payments;" gönderirse tüm tablo silinir.
String paymentId = 5;
 jdbcTemplate.queryForObject("select * from payment where payment_id = "+ paymentId , new PaymentRowMapper(),
```

Korunmak için veritabanı kullanıcı ve yetki kontrolü,  Data Sanitization, `NamedTemplate`, SQL escaping gibi yöntemler kullanılmalıdır. Burada ana mantık kullanıcıdan gelen girdinin SQL'de komut olarak çalıştırlmasını engellemektdir.

https://www.baeldung.com/sql-injection

3. Aşağıdaki kurallara göre bir film öneri uygulaması yazın. (90 Puan)

Ana uygulama http://localhost:8080'de çalışır.  Bağımlı olduğu movies-payment-service containerda 8081'de, movies-email-service 8082'de çalışır.
Ayrıca uygulama PostgreSQL, RabbitMQ ve MongoDB bağımlılıklarının containerda çalışmasını bekliyor. Uygulamayı çalıştırmak için:

1. [package.sh](package.sh) betiğini çalıştırın yada her servis için `./mvnw clean package` deyin.
2. `docker compose up --build` deyin. [docker-compose.yml](docker-compose.yml) 'daki containerlar ayağa kalkacaktır.


http://localhost:8080/index.html 'de SwaggerUI dokümantasyonu var. Ödevde istenilen gereksinimlerin koddaki karşılıkları her bir maddenin altında linklenmiştir.


### **Teknolojiler;**
* Min Java8
* Spring Boot
* Restfull
* MySQL - Postgre - Mongo(Her servis farklı database kullanabilir)
* RabbitMQ

### **Gereksinimler;**

* Kullanıcı sisteme kayıt olup, login olabilmelidir.(Login işlemi için email ve şifre bilgileri
gereklidir.)

    /users/login endpointi. [UserService](movies/src/main/java/mutlu/movies/service/UserService.java)


* Kullanıcı şifresi istediğiniz bir hashing algoritmasıyla database kaydedilmelidir.

    [MovieApplication PasswordEncoder bean'i configure ediliyor.](movies/src/main/java/mutlu/movies/MoviesApplication.java)

    [UserService add metodu veritabanına eklemeden önce parolayı encode ediyor.](movies/src/main/java/mutlu/movies/service/UserService.java)


* Kullanıcılar sisteme film ekleyebilir ve bu filmler herkes tarafından görülebilir.
 
    POST /movies/ endpoint 

   [MovieService](movies/src/main/java/mutlu/movies/service/MovieService.java)


* Kullanıcı kendi eklediği filmleri görebilmeli.(Profil sayfası gibi düşünün)
 
    GET /movies/username/{username} enpointi.
 
    [MovieService#getByUsername metodu.](movies/src/main/java/mutlu/movies/service/MovieService.java)


* Kullanıcı şifresini ve ismini değiştirebilir.
 
    POST /users/changePassword ve POST /user/changeUsername endpointleri.


* Ücretli üye olmayan kullanıcılar sadece 3 film ekleyebilir.
 
    [MovieService#add metodunda kontrolü yapılıyor. ](/movies/src/main/java/mutlu/movies/service/MovieService.java)

 
* Ücretli üye olmayan kullanıcılar filmlere yorum yapamaz.
 
    [CommentService#add metodunda kontrolü yapılıyor.](/movies/src/main/java/mutlu/movies/service/CommentService.java)
 

* Sisteme yeni bir film girildiğinde kullanıcılara email gider.
 
    [MovieService#add metodunda email servisine RabbitMQ üzerinden bir EmailDto gönderiliyor.. ](/movies/src/main/java/mutlu/movies/service/MovieService.java)
 

* Sistemi takip edebilmek için gerekli gördüğünüz yerlere Log ekleyin.

### **Sistem Kabulleri;**

* Ödeme işlemi senkron gerçekleşmelidir.
 
    [UserService#makePayment metodunda ödeme servisine RabbitMQ üzerinde bir RPC isteği yapılıyor. Cevap gelene kadar beklenip duruma göre kullanıcı bilgileri güncelleniyor. ](/movies/src/main/java/mutlu/movies/service/UserService.java)
 

* Ödeme servisi sadece ödeme bilgilerini kaydeder ve başarılı response döner.
 
    [Ödeme servisi PaymentListener#paymentRequestListener metodunda yapılıyor.](movies-payment-service/src/main/java/com/movies_payment/listener/PaymentRequestListener.java)

 
* Email gönderme işlemi asenkron gerçekleşmelidir.
 
    [MovieService#add metodundan eposta servisine RabbitMQ EmailDto'su gönderiliyor.](/movies/src/main/java/mutlu/movies/service/MovieService.java)

 
* Üyelikler 1-3-6-12 ay olarak alınabilir.
 
    [Client'tan gelen isteklerde ve ödeme servisine gönderilen isteklerde PaymentType enum'ı kullanılıyor.](/movies/src/main/java/mutlu/movies/dto/PaymentType.java)
 
* Diğer notlar:
 
  İlk çalıştırıldığında ana veritabanı boş ise örnek veriler eklenir eklenir.
