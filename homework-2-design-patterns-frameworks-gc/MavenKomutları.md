## Proje Oluşturma

```
mvn archetype:generate -DgroupId=com.fatura -DartifactId=OrderManagement -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

# Maven Komutları

## Derleme
```
mvn compile
```

## Çalıştırma
```
mvn exec:java -Dexec.mainClass=com.fatura.App
```