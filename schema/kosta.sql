select * from users;
select * from books where ������ = 'aaa';
select * from books;
select * from cart;
select * from cart_detail;
select * from rental;
select * from Reservation;
select * from rental where å��ȣ = 122;
commit

alter table books drop column �Ⱓ��;
update books set �Ⱓ�� = null ;
select �Ⱓ�� from books;
select * from rental;

-- ���ɸ� ���̺� Ȯ��
SELECT  DO.OBJECT_NAME, DO.OWNER, DO.OBJECT_TYPE, DO.OWNER,
        VO.XIDUSN, VO.SESSION_ID, VO.LOCKED_MODE
FROM    V$LOCKED_OBJECT VO, DBA_OBJECTS DO
WHERE   VO.OBJECT_ID = DO.OBJECT_ID;

grant delete, insert, select, update on users to admin;

select * from users where ȸ��ID='admin' and �н�����='q1w2e3r4';

insert into users(ȸ����ȣ, ȸ��ID, �н�����, ȸ���̸�, ����ó, �������) values (user_seq_no.nextval, 'oh', '1234', '������', '010-0000-0000', sysdate);

delete from users where ȸ��ID='ojw123';
delete from books where ȸ��


select * from books;

update users set �н����� = 'ccc',����ó = '111-111-1111' where ȸ��ID ='aaa';

select * from books order by å��ȣ desc;

select * from books where å��ȣ=3;

select * from books where ������ like ('%��%') order by å��ȣ;

insert into books (å��ȣ, ������, ���ڸ�, ���ǻ��, �Ⱓ��, �о�, ����) values (BOOK_SEQ_NO.nextval, 'ù ���̽�', '������', '�����ۺ���', SYSDATE, '��ǻ��/IT', 0);


select * from users where ȸ��ID='bbb';
select * from users where ȸ����ȣ=31;

delete from users where ȸ����ȣ=33;
delete from rental where ȸ����ȣ=70;
select * from rental where ȸ����ȣ=70;

select ��ٱ��Ϲ�ȣ, å��ȣ, ������, ���ڸ�, ���ǻ��, �Ⱓ��, �о�, ���� from cart_detail join books using(å��ȣ);
select ��ٱ��Ϲ�ȣ, å��ȣ, ������, ���ڸ�, ���ǻ��, to_char(�Ⱓ��, 'yy/mm/dd') �Ⱓ��, �о�, ���� from cart_detail join books using(å��ȣ);

select * from reservation;
select �����ȣ, å��ȣ, ȸ����ȣ, to_char(��������, 'yy/mm/dd')�������� from reservation;

select * from rental;
select �뿩��ȣ, å��ȣ, ȸ����ȣ, to_char(�ݳ���������, 'yy/mm/dd')�ݳ���������, to_char(�뿩����, 'yy/mm/dd')�뿩����, to_char(�ݳ�����, 'yy/mm/dd')�ݳ�����, �ݳ�����, ��ü���� from rental;

select * from cart;
select ��ٱ��Ϲ�ȣ, ȸ����ȣ from cart;

select * from cart_detail;
select ��ٱ��Ϲ�ȣ, å��ȣ from cart_detail;

delete from cart where ȸ����ȣ=;

select * from books;
select å��ȣ, ������, ���ڸ�, ���ǻ��, to_char(�Ⱓ��, 'yy/mm/dd')�Ⱓ��, �о�, ���� from books;

update users set �н�����=1111 where ȸ��ID='admin';
commit

select * from books where ���� = 1 order by å��ȣ

select * from rental where ȸ����ȣ = 31;