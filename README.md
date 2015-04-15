# Stack Development ArtiVisi 2015 #

Aplikasi terdiri dari beberapa bagian:

* Authorization Server : menangani login dan logout
* Resource Server : RESTful API
* Web Client : Aplikasi web dengan AngularJS

## Fitur Aplikasi ##

* Login
* Logout
* User Info
* Add ToDo list
* Remove ToDo list
* Set as completed

## Roadmap ##

* Aplikasi Client dengan Android
* Dynamic Menu

## Teknologi yang digunakan ##

### Server Side ###

* Spring Boot

    * Spring MVC
    * Spring Data JPA

* Spring OAuth 2
* Flyway Database Migration
* MySQL InnoDB
* Rest-Assured
* Apache Maven


### Client Side ###

* AngularJS
* Bootstrap
* Yeoman

## Build dan Run ##

### Persiapan Database ###

1. Install MySQL Server

2. Login ke MySQL Server

    ```
    mysql -u root -p
    ```

3. Buat user untuk mengakses database

    ```
    grant all on stack2015.* to stack2015@localhost identified by 'stack2015';
    ```

4. Buat databasenya

    ```
    create database stack2015;
    ```

### Authorization Server ###

1. Masuk ke folder authorization-server

    ```
    cd authorization-server
    ```

2. Jalankan aplikasinya

    ```
    mvn clean spring-boot:run
    ```

3. URL yang bisa diakses :

    * Login : [http://localhost:10000/login]
    * User Info : [http://localhost:10000/uaa/me]
    * Public Key untuk verifikasi token JWT : [http://localhost:10000/uaa/oauth/token_key]
    * Authorization Grant : [http://localhost:10000/uaa/oauth/authorize]

### Resource Server ###

1. Masuk ke folder resource-server

    ```
    cd resource-server
    ```

2. Jalankan aplikasi

    ```
    mvn clean spring-boot:run
    ```

3. Browse ke [http://localhost:10001/api/halo?nama=endy](http://localhost:10002/api/halo?nama=endy)

### UI Web dengan Angular ###

1. Masuk ke folder ui-web

    ```
    cd ui-web
    ```

2. Jalankan aplikasi

    ```
    grunt serve
    ```

3. Browse ke [http://localhost:10002/]

4. Klik Sign In, anda akan disuruh login di `authorization-server`
5. Login dengan username berikut
    
    * User Admin : endy/123
    * User Staff : maya/456

6. Buka menu Halo. Menu ini bisa dijalankan oleh user dengan role `admin`, tapi tidak bisa dijalankan oleh user `staff`.

### Packaging modul UI Web ###

Modul ini dibungkus dengan aplikasi Spring Boot, sehingga bisa dideploy dengan mudah ke Heroku atau Openshift. Karena sama dengan aplikasi Spring Boot biasa, dia bisa juga dibundel menjadi `*.war`.

Berikut cara menjalankannya menjadi aplikasi Spring Boot

1. Compile dulu seluruh file JavaScript, CSS, dan HTML menggunakan Yeoman

    ```
    cd ui-web
    grunt
    ```

2. Grunt akan melakukan proses kompilasi, hasilnya diletakkan di folder `src/main/resources/static`

3. Aplikasi Spring Boot bisa dijalankan dengan cara biasa

    ```
    mvn clean spring-boot:run
    ```

4. Browse ke tempat yang sama, yaitu [http://localhost:10002]