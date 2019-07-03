- mariadb 버전 확인
  - select version();
  - 버전이 10.1.3 이상일 경우에 동작

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
file_key_management_filekey=cafe24_mariadb_secret_key@this_is_secret_key_for_cafe24

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

- 주의
  - 테이블의 권한이 있는 사람에게는 정상적으로 복호화 된 상태로 보이기 때문에 워크밴치에서 확인할 경우에는 암호화가 되어 있는지 되어있지 않은지 확인이 불가능하다.