select * from users;
select * from books;
commit

-- ���ɸ� ���̺� Ȯ��
SELECT  DO.OBJECT_NAME, DO.OWNER, DO.OBJECT_TYPE, DO.OWNER,
        VO.XIDUSN, VO.SESSION_ID, VO.LOCKED_MODE
FROM    V$LOCKED_OBJECT VO, DBA_OBJECTS DO
WHERE   VO.OBJECT_ID = DO.OBJECT_ID;

grant delete, insert, select, update on users to admin;

select * from users where ȸ��ID='admin' and �н�����='q1w2e3r4';

insert into users(ȸ����ȣ, ȸ��ID, �н�����, ȸ���̸�, ����ó, �������) values (user_seq_no.nextval, 'oh', '1234', '������', '010-0000-0000', sysdate);

delete from users where ȸ��ID='zxcv';


select * from books;

update users set �н����� = 'ccc',����ó = '111-111-1111' where ȸ��ID ='aaa';

select * from books order by å��ȣ desc;

select * from books where å��ȣ=3;

select * from books where ������ like ('%��%') order by å��ȣ;

insert into books (å��ȣ, ������, ���ڸ�, ���ǻ��, �Ⱓ��, �о�, ����) values (BOOK_SEQ_NO.nextval, 'ù ���̽�', '������', '�����ۺ���', SYSDATE, '��ǻ��/IT', 0);

select * from users where ȸ��ID='bbb';

delete from users where ȸ����ȣ=33;

select ��ٱ��Ϲ�ȣ, å��ȣ, ������, ���ڸ�, ���ǻ��, �Ⱓ��, �о�, ���� from cart_detail join books using(å��ȣ);

update users set �н�����=1111 where ȸ��ID='admin';
commit

