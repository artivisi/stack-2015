## Langkah mengetes Auth Server ##

1. Minta grant token dulu. Buka url berikut di browser, kemudian login dengan user/pass : user/password

    ```
    http://localhost:10000/uaa/oauth/authorize?response_type=code&client_id=uiweb
    ```


2. Minta bearer token

    ```
    curl -X POST uiweb:uiwebsecret123@localhost:10000/uaa/oauth/token  \
    -d grant_type=authorization_code -d client_id=uiweb     \
    -d code=Ft0d7U
    ```

3. Akses URL yang diproteksi menggunakan token

    ```
    curl -H "Authorization: Bearer efc5e246-45b3-471b-a613-276285637f8f" http://localhost:10000/uaa/me
    ```

