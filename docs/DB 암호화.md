### ��ȣȭ �ؾߵǴ� �׸�
- https://ecsupport.cafe24.com/article/%EC%87%BC%ED%95%91%EB%AA%B0-%EC%9A%B4%EC%98%81/1/1725/?page=
![��ȣȭ �׸�](https://user-images.githubusercontent.com/20277647/60649068-3ee77400-9e7c-11e9-9de1-46d5673184dc.PNG)

### MariaDB TDE

- TDE ����� ȥ������ ���ƾ��� ����
  - ���̺��� ������ �ִ� ������Դ� ���������� ��ȣȭ �� ���·� ���̱� ������ ��ũ��ġ���� Ȯ���� ��쿡�� ��ȣȭ�� �Ǿ� �ִ��� �Ǿ����� ������ Ȯ���� �Ұ����ϴ�.
  - ��, SQL �������� ��å���δ� �Ұ��� ������ ������ ������ ��ŷ ���ϴ��� �ش� ���̺��� ���� ������ �ִ� ������ �𸣸� �����͸� ���� �� ����.
  - TDE�� ������, �α�, ���, ���� �� �� �����ͺ��̽� ���纻�� ���� �������� �����ͺ��̽� ������ �����Ϸ����ϴ� �������� �ൿ�� ���� �� �ִ�.

- mariadb ���� Ȯ��
  - select version();
  - ������ 10.1 �̻��� ��쿡 ����

- �÷������� ��ġ�Ǿ� �ִ��� Ȯ��
  - ������ 10.1.3 �̻��� mariadb�� ��ġ���� ��� �÷������� ��ġ�Ǿ� ����
  - /usr/local/cafe24/mariadb/lib/plugin/file_key_management.so ������ �ִ��� Ȯ��

- ��ȣȭ �� ���� hex �� ���� (������ ��ɾ�� ����)
  - ������ ��� ���� /usr/local/cafe24/mariadb/keytest/
```
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
openssl rand -hex 16 >> keys.txt
```

- ���� hex ���鿡 ���� 1; 2; 3; ... ���ڸ� �ٿ��ش�
  - ����
```
1;3dea955315790286f1edb9f3ea184424
2;ae2e33cda90a3173300bec520f79eadd
3;d1f5e3b6c95ca7ab1f844f7736698bc7
4;a32dcb20b1ce84075d7445466a9ac31f
5;f7d8e9de7000e1dd9474dd9cc6157fd5
```

- �������� ������ hex ���� Ư�� ��ȣ�� �������� ��ȣȭ ����
  - ����
```
openssl enc -aes-256-cbc -md sha1 -k cafe24_mariadb_secret_key@this_is_secret_key_for_cafe24 -in keys.txt -out keys.enc
```

- ��ȣȭ�� ���� key ���� ����
  - vi .key
```
cafe24_mariadb_secret_key@this_is_secret_key_for_cafe24
```

- key ���丮 ���� ���� ����
  - chmod 755 keytest

- key �� ���ִ� ���丮�� mysql�� ������ ����
  - chown -R mysql:mysql keytest/

- mariadb�� �����ϴ� ���񽺿� ��ȣȭ ���� ���
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

- mariadb ���� �����
  - service mariadb restart

- ���̺� ��ȣȭ �׽�Ʈ
```
create table enc_table( id int, value varchar(255) );
alter table enc_table ENCRYPTED=YES ENCRYPTION_KEY_ID=1;

insert into enc_table values(11, 'this is value11');
insert into enc_table values(22, 'this is value22');
insert into enc_table values(33, 'this is value33');
insert into enc_table values(44, 'this is value44');
insert into enc_table values(55, 'this is value55');
```

- ��ȣȭ�� ������� ���� ���̺��� ���� Ȯ��
strings /usr/local/cafe24/mariadb/data/webdb/user.ibd

- ��ȣȭ ����� ���̺��� ���� Ȯ��
strings /usr/local/cafe24/mariadb/data/webdb/enc_table.ibd

- ��ȣȭ�� ������ ��, key�� ����ִ� ���丮�� �����ϵ� � �Ű� ���� ������ �������� ����
  - /mnt �� ����Ʈ �ϴ� ������� Ű ���丮�� �����ϴ� ����� ����
  
# DB �÷� ��ȣȭ
- ������� password�� ��ȣȭ�� �Ұ����� �ܹ��� ��ȣȭ/�ؽ��� ����ؾ� �Ѵ�.

### MariaDB�� PASSWORD() function
- �Էµ� ���� �ؽ� �Ͽ� ��ȣȭ�ϱ� ������ ��ȣȭ�� �Ұ��� ������, salt�� ������� �ʱ� ������ brute force attack�� ����� �� ����
- ��, ������� �� �������� ��ȣȭ�� ������ �� ��ȣȭ �� ���� DB�� �����ϴ� ����� ä��
- scrypt, bcrypt, PBKDF2 ���� �˰����� ����
- Spring Security�� �˰����� ���� ��ȣȭ���� �����Ǿ� ������, ���Ŀ� ����� password�� ��ȣȭ�ϱ� ���� bcrypt�� ����� ����
  - DB���� password Į���� CHAR(60) ���� ����
  - ���� ��ȿ�� �˻翡�� ������� ��ȣ�� 72����Ʈ�� �Ѿ�� �ʵ��� ���� (bcrypt�� maximum length�� 72����Ʈ)
### MariaDB�� AES_ENCRYPT() function
- ȸ�� ���̺��� �̸�, �ּ�, ��ȭ��ȣ, �޴�����ȣ, �������, ȯ�� ���� ����, ȯ�� ���� ��ȣ �÷��� ����� ��ȣ�� �� ����
- ������ ����� ����
```
insert into enc_table
values(66, hex(aes_encrypt('this is password', SHA2('this is dummy salt', 512))));

select cast(aes_decrypt(unhex(value), SHA2('this is dummy salt',512)) as char)
from enc_table where id=66;
```