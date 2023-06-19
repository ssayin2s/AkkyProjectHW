# AkkyProjectHW

Bu proje, Docker kullanarak MongoDB ve Kafka'nın entegrasyonunu gösteren örnek bir uygulamadır.

## Kurulum

1. Depoyu klonlayın.
2. Docker'ı yükleyin.
3. Sağlanan Docker Dosyalarını kullanarak A ve B uygulamaları için Docker görüntülerini oluşturun.
4. Docker Compose kullanarak Docker kapsayıcılarını oluşturun ve çalıştırın.
5. Uygulamanın işlevselliğini sınayın.

## Kullanım

- Uygulama A, MongoDB koleksiyonunu periyodik olarak sorgular ve Kafka'ya yeni belgeler yayınlar.
- B Uygulaması Kafka'dan gelen mesajları tüketir ve konsola yazdırır.

## Önkoşullar

Bu uygulamayı çalıştırmadan önce, aşağıdaki bağımlılıkların yüklü olduğundan emin olun:

- Docker
- MongoDB
- Apache Kafka
- JDK
- Java çalıştırabilecek bir IDE

## Yapılandırma

Docker görüntülerini oluşturmak için bir terminal veya komut istemi açın, Dockerfile Dosyasının bulunduğu dizine gidin ve aşağıdaki komutu çalıştırın:
docker build -t my-application-a:1.0.0 .
docker build -t my-application-b:1.0.0 .
Docker Compose dosyasında tanımlanan hizmetleri başlatmak için bir terminal veya komut istemi açın,
Docker Compose dosyasının bulunduğu dizine gidin ve aşağıdaki komutu çalıştırın:
docker-compose up

Uygulamayı kullandıktan sonra, Docker kapsayıcılarını durdurabilir ve kaldırabilirsiniz. 
Yeni bir terminal veya komut istemi açın, Docker Compose dosyasının bulunduğu dizine gidin ve aşağıdaki komutu çalıştırın:
docker-compose down
