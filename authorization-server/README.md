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

## Generate Key Pair untuk JWT ##

1. Generate keypair dan simpan di `keystore.jks`. Untuk keperluan ilustrasi, keysize 512 sudah cukup. Gunakan 2048 untuk production.

    ```
    keytool -genkey -keystore authstore.jks -alias authserver -keyalg RSA -keysize 512
    ```

2. Jawab pertanyaan

    ```
    Enter keystore password:
    Re-enter new password: 
    What is your first and last name?
      [Unknown]:  Authorization Server
    What is the name of your organizational unit?
      [Unknown]:  Training Division
    What is the name of your organization?
      [Unknown]:  ArtiVisi Intermedia
    What is the name of your City or Locality?
      [Unknown]:  Jakarta
    What is the name of your State or Province?
      [Unknown]:  Jakarta
    What is the two-letter country code for this unit?
      [Unknown]:  ID
    Is CN=Authorization Server, OU=Training Division, O=ArtiVisi Intermedia, L=Jakarta, ST=Jakarta, C=ID correct?
      [no]:  yes

    Enter key password for <authserver>
	    (RETURN if same as keystore password):
    ```

3. Jalankan authorization-server supaya bisa menampilkan public key

4. Akses token key di [`http://localhost:10000/uaa/oauth/token_key`]

    ```
    curl  http://localhost:10000/uaa/oauth/token_key
    {"alg":"SHA256withRSA","value":"-----BEGIN PUBLIC KEY-----\nMFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMgxvg8ptG+JXJ9a//uo1eSA0xGA5tIo2Gh7jUrWpU8yZd0H/pf2Nl102tIpdK1kOzt9ch7AlxtcRCMQrCV74cUCAwEAAQ==\n-----END PUBLIC KEY-----"}
    ```

5. Pasang di resource server. Contohnya:

    ```
    spring.oauth2.resource.jwt.keyValue=-----BEGIN PUBLIC KEY-----\nMFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMgxvg8ptG+JXJ9a//uo1eSA0xGA5tIo2Gh7jUrWpU8yZd0H/pf2Nl102tIpdK1kOzt9ch7AlxtcRCMQrCV74cUCAwEAAQ==\n-----END PUBLIC KEY-----
    ```

6. Hapus verifikasi user menggunakan token

    ```
    spring.oauth2.resource.userInfoUri: http://localhost:10000/uaa/me
    ```
