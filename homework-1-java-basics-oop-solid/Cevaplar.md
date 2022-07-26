## 1. Java’nın platform bağımsızlığını nasıl sağladığını anlatınız.
Java kodu derleyici tarafından bytecode denilen intermediate language'a derlenir. Bu bytecode (.class uzantılı) tüm platformlarda çalıştırılabilir. Java Virtual Machine adı verile ve her platform için ayrı olan yazılım çalıştırma anında bytecoduu yorumlayarak makine koduna çevirir. C, C++ gibi direkt makine koduna derlenen dillerde ise derleyici her platform için ayrı bir çalıştırılabilir çıktısı verir.

Ayrıca Java Software Development Kit (SDK) ağ, grafik, thread gibi her platformda farklı bir şekilde uygulanmış olan özelliklere birleştirilmiş bir erişim yöntemi sağlar.


## 2. Java neden çoklu kalıtımı desteklemez? Hangi diller bu duruma neden izin verir?

Java'nın Çoklu kalıtımı desteklememesi diamond(elmas) problemi denilen, örnek bir D sınıfının, A sınıfından kalıtım alan B ve C sınıflarının ikisinden de kalıtım almak istediğinde ve hem B hem C'de tanımlanmış ve/veya ezilmiş bir metod olduğunda D sınıfının hangi sıfın metodunu kullanacağının kesin olmaması problemi sebebiyledir. 
C++, Python ve Perl buna izin verir. C++ çakışma olması durumun hangi ata sınıfın kullanılacağını açık olarak belirtilmesini gerektirir. Trait'ler ile PHP, Scala, Groovy ve default metodlu interfaceler Kotlin multiple inheritance'a izin verir(Classlarda izin vermezler.) Ayrıca Java default metodu olan interfacelerde bir çakışma olduğunda kalıtım alan interfacein çakışan metodu yeniden tanımlamasını gerektirir.

## 3. Build Tool nadir? Java ekosistemindeki toolar neler?

Build tool bir yazılan kodunun derlenmesi, linklenmesi, , bağımlılıkların çözümlenmesi, indirilmesi, çalıştırılması, test edilmesi ve paketlenmesi adımlarının  yapılmasını sağlayan ve/veya bu adımların otomatize edilmesini, CI pipelinenına entegre edilmesini sağlayan araçlardır. Java ekosistemindeki build toollar Apache Maven, Gradle, Ant, SBT'dir.


## 4 Collection framework içerisindeki yapıları örnekleyip açıklayınız.

Collections framework temel olarak `Collection` ve `Map` interfacelerinden oluşur. `Collection` ise `List`, `Set`, `Deque` interfaceleri tarafından kalıtımlanır. Diğer tarafta ise `Map` interfacei `SortedMap` interfaceiyle benzer işlevi görür. Bu yapılar kademeli olarak Collections Framework'ün çeşitli methodlarını tanımlayarak yazılımcının istediği hassaslıkta koleksiyon türünü kabul etmesini sağlar. Örneğin metodumuz sadece `add` metoduna ihtiyaç duyuyorsa `void ornekMetod(Collection<T> koleksiyon)` gibi genel bir imza tanımlayabiliriz ancak eğer sadece `SortedSet`'te bulunan `last()` metoduna ihtiyaç duyuyorsak imzada bu interfacei kullanırız. 

Bu interfaceler `ArrayList`, `HashSet` gibi classlar tarafından implemente edilir. Burada bu türeten classların isimlendirmesi \<*implementasyonyöntemi*>\<*interfaceismi*> şeklindedir. 

### Collection interfacei 

`Map` hariç diğer koleksiyon türlerinde olması gereken temel metodları tanımlar. Örneğin `removeAll`, `addAll` implemente eden sınıfları tekli metodlarını her elemanla polimorfizm yardımıyla çağırarak her alt-sınıf için tek tek bu metodları tanımlama ihtiyacımızı ortadan kaldırır. 

`Collection` interfacei `Iterator` interfaceini kalıtımlayarak her JCF koleksiyon türü için tüm elemanlar üzerinde işlem yapmamızı sağlayar `stream`, `for-each', `iterator` yöntemlerini kullanmamızı sağlar. 

```
//ArrayList yerine herhangi bir `Collection` türünü kullanabiliriz.
Collection<Int> faturalar = new ArrayList<Int>();
//Java 8' gelen Stream<T> stream() metodu ile
faturalar.stream()
    .map(it -> it*2)
    .filter(it -> it > 150)
    .forEach(it ->öde(it))

//for-each yapısı ile
for (var fatura: faturalar){
    fatura = fatura * 2;
    if (fatura > 150){
        öde(fatura);
    }
}

//Direkt Iterator'u kullanarak
for (Iterator<?> it = faturalar.iterator(); it.hasNext();){
    Integer fatura = it.next()*2;
    if ( fatura > 150){
        öde(fatura)
    }
}
```

`Collection` interfacei `Array` ile dönüştüren `toArray` metodunu tanımlar.


### List interfaceinden türeyen koleksiyon türleri.
`ArrayList` ve `LinkedList` olmak üzere temelde iki türdür.
 
 Bunlardan en sık kullanılan `ArrayList` içindeki objeleri sıralı bir şekilde saklar. Bu sabit zamanda ( O(n))  `get()`, `iterator()` metodlarına izin verir ancak yeni eleman ekleme veya silme o elemandan sonraki tüm elemanların kaymasını gerektirdiği için O(n) kompleksitededir.  `RandomAccess` interfaceini tanımlar. 

`LinkedList` ise rastgele erişime izin vermez, bunun yerine `get()` metodu başlangıç yada sondan sırayla tüm elemanları geçerek istenilen indeksteki elemanı bulur ve onu döndürür. 
    
**Örnek:**

```
    ArrayList<Integer> ints = new ArrayList<>();
    ints.add(1);
    ints.add(10);
    ints.add(15);
    ints.add(20);
    ints.remove(10);
    ints.removeif(i -> {i > 14}); // sadece 14'ten büyük elmanları kaldır.
    lastTwoInts = ints.sublist(ints.size -3, int.size -1); //son elemandan önceki iki eleman.

```

### Set interfaceinden türeyen koleksiyon türleri.
JCF'te temelde üç tür `Set` türü vardır. `HashSet` elemanlarını bir hash table'da saklar, hızlı erişime izin verir ancak erişim sırası hakkında bize bir garanti vermez. `TreeSet` elemanları red-black tree adı verilen veri yapısında sıralı bir şekilde saklar. `LinkedHashSet` ise hash table'da saklar ancak ek olarak elemanlar bir linked list ile birbirlerine bağlıdır. 

`Set` ayrıca `addAll`, `containsAll`, `toArray` gibi tüm `Set` çeşitlerinde var olacak olan bazı metodları tanımlar. Bu toplu (bulk) metodlar implemente eden sınıfın tekli metodunu çağırarak çalışır. Burada bir polimorfizm örneği vardır. (`Set` interfacinden gelen `addAll()` her argüman için `TreeSet`in `add()` metodunu çağırır.)

**Örnek**:
```
    HashSet<String> ayrıKelimeler1 = new HashSet(Arrays.asList(birKitabınTümKelimeleri));
    HashSet<String> ayrıKelimeler2 = new HashSet(Arrays.asList(ikinciBirKitabınTümKelimeleri));
    //Tüm koleksiyon tiplerinde tanımlı olan add, remove, size vb. metodlar burada yine kullanılabilir.
    aynıKelimeler1.retainAll(aynıKelimeler2); // iki kümenin kesişimi.
    aynıKelimeler1.addAll(aynıKelimeler2); // iki kümenin birleşimi.
```

# Queue interfaceinden türeyen koleksiyon türleri.
İşlem yapılacak elemanları sıralı bir şekilde tutmaya yarar. Genel olarak queue First in First Out şeklinde kullanılır. Bu interface eleman eklemek için `add`
 ve `offer`, elemanı kaldırmak için `remove` ve `poll`, elemanı incelemek için `element` ve `peek` metodlarını tanımlar. Bunlardan ilkleri exceptionları kullanırken ikinciler özel değerler döndürerek çalışırlar.
 `LinkedList` `Queue` interfacini implemented eder.

 **Örnek:**
 ```
 //Bir bankada sıra bekleyen müşterilerin simulasyonu. Benzer bir durum işlemek 
 // için sıraladığımı HTTP bağlantıları yada bekleyen threadler için de kullanılabilir.
 Queue<Integer> müşteriler =  new LinkedList<>();
 müşteriler.add(1);
 işlemYap(müşteriler.remove());
 müşteriler.add(2);
 müşteriler.add(3);
 sadeceBak(müşteriler.peek());
 işlemYap(müşteriler.remove());
 işlemYap(müşteriler.remove());
 ```

### Deque interfaceinden türeyen koleksiyon türleri.

 `Queue` interfacine benzer ancak iki taraflıdır yani hem sondan hem baştan işlem yapılabilir. `addLast`, `addFirst`, `peekFirst`, `getLast` gibi `Queue` benzer metodlar tanımlar. En yaygın kullanılan implemente eden koleksiyon türleri `ArrayDeque` ve yine `LinkedList`tir. 

### Map interfaceinden türeyen koleksiyon türleri.

Anahtar ve değer çiftlerini saklamamızı sağlar. `HashMap`, `TreeMap` ve `LiskedHashMap` tarafından implemente edilirirler ve `Set`'i tanımlayan sınıflar gibi davranırlar. Burada diğer koleksiyon türlerinden fark Map sınıfları `Collection` interfaceini tanımlamazlar yani bir `HashMap` `Collection` beklenen yerde kullanılamaz. 

Elemanları görmek için 3 farklı yöntem tanımlar. Bunlardan ilki tüm anahtarları, ikincisi tüm değerleri, sonuncusu ise anahtar-değer çiftlerini dönndürür.

**Örnek:**

```
Map<String, Integer> isimler = new Map<>();
isimler.put("Mustafa", 1);
isimler.put("Ali", 2);
for(var isim : isimler.keys()){
    System.out.println(isim + " : "+ isimler.get(isim)); 
}
//yada 
isimler.entrySet()
    .stream()
    .forEach(it ->System.out.println(it.getKey()+ " : "+it.getValue() );
```

## Kaynaklar
(https://en.wikipedia.org/wiki/Multiple_inheritance#The_diamond_problem)

[Oracle Collection Tutorial](https://docs.oracle.com/javase/tutorial/collections/interfaces/index.html)

[Oracle Java SE8 Dokümentasyonu](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)