select * from users;
select * from books;
commit

-- 락걸린 테이블 확인
SELECT  DO.OBJECT_NAME, DO.OWNER, DO.OBJECT_TYPE, DO.OWNER,
        VO.XIDUSN, VO.SESSION_ID, VO.LOCKED_MODE
FROM    V$LOCKED_OBJECT VO, DBA_OBJECTS DO
WHERE   VO.OBJECT_ID = DO.OBJECT_ID;

grant delete, insert, select, update on users to admin;

select * from users where 회원ID='admin' and 패스워드='q1w2e3r4';

insert into users(회원번호, 회원ID, 패스워드, 회원이름, 연락처, 등록일자) values (user_seq_no.nextval, 'oh', '1234', '오지원', '010-0000-0000', sysdate);

delete from users where 회원ID='zxcv';


select * from books;

update users set 패스워드 = 'ccc',연락처 = '111-111-1111' where 회원ID ='aaa';

select * from books order by 책번호 desc;

select * from books where 책번호=3;

select * from books where 도서명 like ('%가%') order by 책번호;

insert into books (책번호, 도서명, 저자명, 출판사명, 출간일, 분야, 상태) values (BOOK_SEQ_NO.nextval, '첫 파이썬', '엘리스', '이지퍼블리싱', SYSDATE, '컴퓨터/IT', 0);

select * from users where 회원ID='bbb';

delete from users where 회원번호=33;

select 장바구니번호, 책번호, 도서명, 저자명, 출판사명, 출간일, 분야, 상태 from cart_detail join books using(책번호);

update users set 패스워드=1111 where 회원ID='admin';
commit

