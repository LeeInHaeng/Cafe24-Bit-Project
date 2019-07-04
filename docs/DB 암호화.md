### 암호화 해야되는 항목
- https://ecsupport.cafe24.com/article/%EC%87%BC%ED%95%91%EB%AA%B0-%EC%9A%B4%EC%98%81/1/1725/?page=
![암호화 항목](https://user-images.githubusercontent.com/20277647/60649068-3ee77400-9e7c-11e9-9de1-46d5673184dc.PNG)

### MariaDB TDE

- TDE 적용시 혼동하지 말아야할 사항
  - 테이블의 권한이 있는 사람에게는 정상적으로 복호화 된 상태로 보이기 때문에 워크밴치에서 확인할 경우에는 암호화가 되어 있는지 되어있지 않은지 확인이 불가능하다.
  - 즉, SQL 인젝션의 대책으로는 불가능 하지만 리눅스 서버가 해킹 당하더라도 해당 테이블의 접근 권한이 있는 계정을 모르면 데이터를 얻을 수 없다.
  - TDE는 데이터, 로그, 백업, 스냅 샷 및 데이터베이스 복사본과 같은 도난당한 데이터베이스 파일을 복원하려고하는 악의적인 행동을 막을 수 있다.

- mariadb 버전 확인
  - select version();
  - 버전이 10.1 이상일 경우에 동작

- 플러그인이 설치되어 있는지 확인
  - 버전이 10.1.3 이상의 mariadb를 설치했을 경우 플러그인이 설치되어 있음
  - /usr/local/cafe24/mariadb/lib/plugin/file_key_management.so 파일이 있는지 확인

- 암호화 할 랜덤 hex 값 생성 (리눅스 명령어로 수행)
  - 수행할 경로 예시 /usr/local/cafe24/mariadb/keytest/
```
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
```

- 나온 hex 값들에 대해 1; 2; 3; ... 숫자를 붙여준다
  - 예시
```
1;3dea955315790286f1edb9f3ea184424
2;ae2e33cda90a3173300bec520f79eadd
3;d1f5e3b6c95ca7ab1f844f7736698bc7
4;a32dcb20b1ce84075d7445466a9ac31f
5;f7d8e9de7000e1dd9474dd9cc6157fd5
```

- 랜덤으로 생성한 hex 값을 특정 암호를 바탕으로 암호화 수행
  - 예시
```
openssl enc -aes-256-cbc -md sha1 -k cafe24_mariadb_secret_key@this_is_secret_key_for_cafe24 -in keys.txt -out keys.enc
```

- 복호화를 위한 key 파일 생성
  - vi .key
```
cafe24_mariadb_secret_key@this_is_secret_key_for_cafe24
```

- key 디렉토리 파일 권한 변경
  - chmod 755 keytest

- key 가 들어가있는 디렉토리를 mysql로 소유자 변경
  - chown -R mysql:mysql keytest/

- mariadb를 수행하는 서비스에 암호화 셋팅 등록
  - vi /etc/my.cnf.d/server.cnf
```
[mysqld]
plugin_dir=/usr/local/cafe24/mariadb/lib/plugin/
plugin-load-add=file_key_management.so
file_key_management_encryption_algorithm=aes_cbc
file_key_management_filename=/usr/local/cafe24/mariadb/keytest/keys.enc
file_key_management_filekey=FILE:/usr/local/cafe24/mariadb/keytest/.key

innodb-encrypt-tables=ON
innodb-encrypt-log=1
innodb-encryption-threads=4
innodb-encryption-rotate-key-age=0 # Do not rotate key
```

- mariadb 서비스 재시작
  - service mariadb restart

- 테이블 암호화 테스트
```
create table enc_table( id int, value varchar(255) );
alter table enc_table ENCRYPTED=YES ENCRYPTION_KEY_ID=1;

insert into enc_table values(11, 'this is value11');
insert into enc_table values(22, 'this is value22');
insert into enc_table values(33, 'this is value33');
insert into enc_table values(44, 'this is value44');
insert into enc_table values(55, 'this is value55');
```

- 암호화가 적용되지 않은 테이블의 내용 확인
strings /usr/local/cafe24/mariadb/data/webdb/user.ibd

- 암호화 적용된 테이블의 내용 확인
strings /usr/local/cafe24/mariadb/data/webdb/enc_table.ibd

- 암호화를 적용한 후, key가 들어있는 디렉토리를 외장하드 등에 옮겨 놓고 리눅스 서버에는 삭제
  - /mnt 에 마운트 하는 방식으로 키 디렉토리를 관리하는 방법을 권장
  
# DB 컬럼 암호화
- 사용자의 password는 복호화가 불가능한 단방향 암호화/해싱을 사용해야 한다.

### MariaDB의 PASSWORD() function
- 입력된 값을 해싱 하여 암호화하기 때문에 복호화는 불가능 하지만, salt가 적용되지 않기 때문에 brute force attack에 취약할 수 있음
- 즉, 대안으로 웹 서버에서 암호화를 진행한 후 암호화 된 값을 DB에 저장하는 방식을 채택
- scrypt, bcrypt, PBKDF2 등의 알고리즘이 있음
- Spring Security에 알고리즘을 통한 암호화들이 구현되어 있으며, 이후에 사용자 password를 암호화하기 위해 bcrypt를 사용할 예정
  - DB에서 password 칼럼을 CHAR(60) 으로 지정
  - 이후 유효성 검사에서 사용자의 암호가 72바이트를 넘어가지 않도록 제한 (bcrypt의 maximum length가 72바이트)
### MariaDB의 AES_ENCRYPT() function
- 회원 테이블에서 이름, 주소, 전화번호, 휴대폰번호, 생년월일, 환불 계좌 은행, 환불 계좌 번호 컬럼은 양방향 암호를 할 예정
- 간단한 양방향 예제
```
insert into enc_table
values(66, hex(aes_encrypt('this is password', SHA2('this is dummy salt', 512))));

select cast(aes_decrypt(unhex(value), SHA2('this is dummy salt',512)) as char)
from enc_table where id=66;
```