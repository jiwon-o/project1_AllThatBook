select * from users;
select * from books;
commit

grant delete, insert, select, update on users to admin;

select * from users where ȸ��ID='admin' and �н�����='q1w2e3r4';

insert into users(ȸ����ȣ, ȸ��ID, �н�����, ȸ���̸�, ����ó, �������) values (user_seq_no.nextval, 'oh', '1234', '������', '010-0000-0000', sysdate);

delete from users where ȸ��ID='zxcv';

select * from books;