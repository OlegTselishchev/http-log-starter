### HttpLoggerStarter - Spring Boot Starter для логирования HTTP запросов

### Применение:
1. Склонировать проект
    ```bash
   $ git clone https://github.com/OlegTselishchev/http-log-starter.git
   ```
2. Добавить http-logger-spring-boot-starter в локальный репозиторий Maven.
   ```bash
      $ mvn clean install
   ```
3. Добавить зависимость в приложение.
   ```
   <dependency>
       <groupId>com.example</groupId>
       <artifactId>http-logger-spring-boot-starter</artifactId>
       <version>0.0.1-SNAPSHOT</version>
   </dependency>
   ```
4. Нстроики для **application.properties** свойство по умолчанеию.
   Логирование отключено по умолчанию.
   ```
   http-log.enabled=false
   http-log.cache=false
   http-log.level=INFO
   ```
   для активации лигирования измените настройки в **application.properties**
   пример:
    ```
   http-log.enabled=true
   http-log.cache=false
   http-log.level=WARN
   ```

### Вывод логов:
Логи будут выводиться в следующем формате:
   ```
   2024-09-22T13:07:14.863+04:00  WARN 22048 --- [http-loger] [nio-8080-exec-1] c.e.h.s.log.impl.HttpLogServiceInfo      : HTTP LOG: 
   -------------------------------------------
   Status: 200
   Method: GET
   Url: /v1/rest/test
   Work_Timestamp: 5055
   Request_Header: {sec-fetch-mode=navigate, sec-fetch-site=none, accept-language=en-US,en;q=0.9, sec-fetch-user=?1, accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7, sec-ch-ua="Microsoft Edge";v="129", "Not=A?Brand";v="8", "Chromium";v="129", sec-ch-ua-mobile=?0, sec-ch-ua-platform="Windows", host=localhost:8080, upgrade-insecure-requests=1, connection=keep-alive, cache-control=max-age=0, accept-encoding=gzip, deflate, br, zstd, user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0, sec-fetch-dest=document}
   Response_Header: {Transfer-Encoding=chunked, Keep-Alive=timeout=60, Connection=keep-alive, Date=Sun, 22 Sep 2024 09:07:14 GMT, Content-Type=application/json}
   -------------------------------------------
   ```
5. Для проверки используйте тестовый ендпоинт: хост:порт/v1/rest/test

    Пример: http://localhost:8080/v1/rest/test


6. Для просмотра текущих настроек используйте ендпоинт:
   http://localhost:8080/v1/rest/properties
   ```
   enabled=true cache=false level=INFO
   ``` 
7. Стартер имеет ендпоинты для изменения уровня логирования на рабочем приложении:

   http://localhost:8080/v1/rest/level/info

   http://localhost:8080/v1/rest/level/warn

   http://localhost:8080/v1/rest/level/trace

   http://localhost:8080/v1/rest/level/error

   http://localhost:8080/v1/rest/level/infodebug


8. Стартер имеет ендпоинт для отключения логирования на рабочем приложении:

   http://localhost:8080/v1/rest/logging/enabled

   http://localhost:8080/v1/rest/logging/disable

   
### 🏄 Стек:
Java 21, SpringBoot 3, Maven, Interceptors