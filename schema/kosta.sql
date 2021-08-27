select * from users;
select * from books;
commit

grant delete, insert, select, update on users to admin;

select * from users where 회원ID='admin' and 패스워드='q1w2e3r4';

insert into users(회원번호, 회원ID, 패스워드, 회원이름, 연락처, 등록일자) values (user_seq_no.nextval, 'oh', '1234', '오지원', '010-0000-0000', sysdate);

delete from users where 회원ID='zxcv';

select * from books;