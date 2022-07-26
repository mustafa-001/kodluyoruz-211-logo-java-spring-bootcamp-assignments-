****### ActiveMQ, Kafka ve RabbitMQ karşılaştırın. Örnek kodlar ile nasıl çalıştığını anlatın. (10 Puan)

ActiveMQ ve Rabbit MQ message queue, Kafka is distributed streaming platformdur. Kafka mesajlaşma, dağıtılmış veri
depolama ve işleme amaçları ile kullanılabilir.
 Gelen veriler diske kaydedilip, gerektiğinde eski bir noktaya
dönülebilir. Active MQ ve RabbitMQ ise sadece mesajlaşma işlevi görür.
ActiveMQ ve RabbitMQ ise AMQP, MQTT protokolleriyle çalışan, daha kompleks mesajlaşma kalıplarını desteklerler.
Kafka ve ActiveMQ Java ile, RabbitMQ Erlang ile yazılmıştır.
- Rabbit MQ kod örneği:
```java
ConnectionFactory factory = new ConnectionFactory();
factory.setHost("localhost");
Connection connection = factory.newConnection();
Channel channel = connection.createChannel();
channel.queueDeclare("isbasi.email", false, false, false, null);
String message = "kullanıcı@email.com";
channel.basicPublish("", "isbasi.email", null, message.getBytes());
```
https://www.rabbitmq.com/tutorials/tutorial-one-java.html
- Active MQ kod örneği:

 ```java
ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("vm://localhost");
Connection connection=connectionFactory.createConnection();
connection.start();
Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
Destination destination=session.createQueue("isbasi.email");
MessageProducer producer=session.createProducer(destination);
producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
TextMessage message=session.createTextMessage("kullanıcı@email.com");
producer.send(message);
session.close();
connection.close();
```
https://activemq.apache.org/hello-world

- Kafka kod örneği:

```java
Properties props=new Properties();
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
props.put(ProducerConfig.CLIENT_ID_CONFIG,"isbasi");
Producer<Long, String> producer=KafkaProducer<>(props);
ProducerRecord<Long, String> record=new ProducerRecord<Long, String>("isbasi.email","kullanıcı@email.com");
RecordMetadata metadata=producer.send(record).get();
```
https://dzone.com/articles/kafka-producer-and-consumer-example

### Microservis ve monolith mimariyi karşılaştırın.(10 Puan)

Microservis mimari her servisin ayrı ayrı scale edilmesine , servislerin bağımsız ekipler tarafından geliştirilmesine
izin verir.
Sistemin kompleksitesinin sınırları belli katmanlara ayrılmasını sağlar. Her servisin geliştirilmesinde o ekibin tercih
ettiği dil ve teknolojilerin kullanılmasına izin verir.
Ancak microservisler dağıtılmış sistemler olduğu için aralarında haberleşme sistemine ihtiyaç duyar; dolayısıyla bir ek
kompleksite ve donanım gereksinimi vardır.
Veritabanı sistemleri dağıtılmış olduğu için consistency sağlamak daha karmaşık hale gelir.

Monolitik mimariler tek parça oldukları için test ve deploy edilmesi daha kolaydır.
Ancak uygulama boyutları büyük ve kompleks olduğu için geliştiriciler tarafından anlaşılması ve değişikler yapılması
uzun sürebilir.
Her güncellemede sistemin tamamen güncellenmesi gerektiği için bir hata tüm sistemi kapatabilir.

### SOAP - RESTful karşılaştırın (10 Puan)

SOAP uygulama mantığını gönderip alırken REST veriyi kullanmayı hedefler. SOAP mesajları cache'lenemez, sadece XML
dilini destekler ve stateful'dürler.
REST JSON, XML, gRPC ve diğer formatları destekler, JSON sayesinde tüm tarayıcılar tarafından desteklenir. SOAP sadece
HTTP protokolünü kullanırken REST FTP, QUIC gibi diğer protokolleri de destekler.
SOAP ACID transaction garantisi sağlar ve güvenlik birinci sınıf öneme sahiptir.

Richardson Maturity Scale'de SOAP servisleri 0. seviyede iken REST servisler genelde 2. yada 3. seviyededirler.
Yani SOAP bir URI ve HTTP metodu ile tüm işlemleri ifade ederken, REST 2. seviyede değişik URI ve HTTP metodlarını, 3.
seviyede hypermedia linklerini kullanır. Bu API'da keşfedilebilirliği artırır.

### Kaynaklar

[What is the difference between Apache kafka vs ActiveMQ - Stack Overflow](https://stackoverflow.com/questions/44792604/what-is-the-difference-between-apache-kafka-vs-activemq)

[Kafka: a Distributed Messaging System for Log Processing.pdf](https://notes.stephenholiday.com/Kafka.pdf)

[web services - SOAP vs REST (differences) - Stack Overflow](https://stackoverflow.com/questions/19884295/soap-vs-rest-differences)

[Richardson Maturity Model](https://restfulapi.net/richardson-maturity-model/)

[Types of Web Services - The Java EE 6 Tutorial](https://docs.oracle.com/javaee/6/tutorial/doc/giqsx.html)


