### Java dünyasındaki framework’ler ve çözdükleri problemler nedir?(Spring MVC, JSP, Struct, Vaadin). Örnekler ile açıklayın. (20 Puan)

Framework daha önceden yazılmış, konfigüre edilmiş, taslak olarak kullanılabilen projelerdir.
Herşeyi yeniden yazmak ve konfigüre etmek yerine kullanıcıların sadece kendilerine özel kodu yazarak uygulamayı tamamlamasını sağlarlar.
Frameworkler database access, routing, authorization, authentication, templating gibi görevleri yapacak uygulamaları içerirler.

Spring MVC 

### Katmanlı mimari nedir? (10 Puan)

Katmanlı mimari bir uygulamanın farklı sorumluluklara ait parçalara ayrılarak dizayn edilmesidir.
Bu şekilde uygulamanın test edilmesi, okunabilmesi kolaylaşır.
Örneğin client'in isteklerini kontrol eden parça ayrı, veritabanı ile iletişim kuran parçanın ayrı olmasıdır.

Her katmanın görevini soyutlaştırarak, seperation of concerns uygulamamızı sağlar.

### Garbage collector nedir, nasıl çalışır? Diğer C++ ile karşılaştırın. (10 Puan)
Hafızada yer ayrılan nesneleri artık ihtiyaç kalmadığında temizleyerek hafıza yönetimi sağlar.
Yeni hafızaya ihtiyaç duyulduğunda yada programdan GC'ın çalışması istenildiğinde tüm threadler durdurulur ve GC ihtiyaç duyulmayan nesneleri hafızadan siler, gerekli gördüğü nesneleri yeni konuma taşır.
JVM'de default olan G1 GC bu threadlerin durduğu zamanı kısaltmak için hafızayı iki bölgeye(region)(young ve old region) ayırır.
Genç bölgedeki nesneler her GC durdurması taranır, yaşlı bölgedekiler ise program çok durmayacaksa taranır. Tarama sonucunda başka bir yerde referanslanmayan nesneler silinir.
Genö bölgede silinmeyen nesneler yaşlı bölgeye taşınır. GC'ler bu durmaları yavaşlatmak için taramaları program çalışırken yapıp sadece silmeleri durma anına bırakabilir(concurrent GC) ve/veya
birkaç threadde aynı anda çalışabilir(paraller GC).
GC sadece heapteki nesneler için gereklidir, stackteki nesnelerin temizlenmesi için bir işleme gerek yoktur. Scope bittiğinde otomatik olarak silinir.

C++'ta her `new` komutuyla yaratılan nesnenin ona karşılık gelen bir `delete` komutuyla silinmesi gerekir.
C++11 ile birlikte gelen `unique_ptr` ve `shared_ptr` ile RAII yardımıyla scope bittiğinde nesnelerin otomatik silinmesi sağlanabilir.

### Spring frameworkünün kullandığı design patternlar neler? (10 Puan)
- [Model View Controller](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html)
- [Dependency Injection](https://www.baeldung.com/spring-dependency-injection)
- [Singleton Pattern](https://stackoverflow.com/questions/22184639/spring-autowired-and-singletons),
- [Factory Pattern](https://springframework.guru/gang-of-four-design-patterns/factory-method-design-pattern/)
- [Template Pattern](https://docs.spring.io/spring-framework/docs/2.5.x/reference/jdbc.html#jdbc-JdbcTemplate)
- [Front Controller Pattern](https://www.baeldung.com/spring-controllers)
- [Proxy Pattern](https://stackoverflow.com/questions/36637702/proxy-pattern-used-by-spring)
- [Aspect Oriented Programming](https://docs.spring.io/spring-framework/docs/4.3.15.RELEASE/spring-framework-reference/html/aop.html)

### Creational Patterns neler? Önceki ödevde oluşturulan nesnelerinizi factory Design patterni ile oluşacak şekilde düzenleyin. (25 Puan)
Nesnelerin oluşturulmasında kullanılan patternlardır. Sistemin kullandığı nesnenin tam bilgisini encapsulate eder (interface ile abstractiona izin verir) ve nesnelerin nasıl oluşturulduğu bilgisini/sorumluluğunu nesnenin kullanımından ayırır.
- Abstract Factory Pattern
- Factory Method Pattern
- Builder Pattern
- Prototype Pattern
- Singleton Pattern

### Singleton ve AbstractFactory patterlerini implemente eden kodu yazın.(25 Puan) 
- [Singleton olarak implemente edilmiş CustomerDatabse sınıfı](src/main/java/com/fatura/database/CompanyDatabase.java) 
- [Abstract Invoice sınıfını üreten AbstractInvoiceFactory implementasyonu](src/main/java/com/fatura/entities/factories/AbstractInvoiceFactory.java)




