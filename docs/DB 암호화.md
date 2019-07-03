- mariadb ���� Ȯ��
  - select version();
  - ������ 10.1.3 �̻��� ��쿡 ����

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
file_key_management_filekey=cafe24_mariadb_secret_key@this_is_secret_key_for_cafe24

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

- ����
  - ���̺��� ������ �ִ� ������Դ� ���������� ��ȣȭ �� ���·� ���̱� ������ ��ũ��ġ���� Ȯ���� ��쿡�� ��ȣȭ�� �Ǿ� �ִ��� �Ǿ����� ������ Ȯ���� �Ұ����ϴ�.