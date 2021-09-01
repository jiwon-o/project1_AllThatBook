select * from users;
select * from books where 도서명 = 'aaa';
select * from books;
select * from cart;
select * from cart_detail;
select * from rental;
select * from Reservation;
select * from rental where 책번호 = 122;
commit

alter table books drop column 출간일;
update books set 출간일 = null ;
select 출간일 from books;
select * from rental;

-- 락걸린 테이블 확인
SELECT  DO.OBJECT_NAME, DO.OWNER, DO.OBJECT_TYPE, DO.OWNER,
        VO.XIDUSN, VO.SESSION_ID, VO.LOCKED_MODE
FROM    V$LOCKED_OBJECT VO, DBA_OBJECTS DO
WHERE   VO.OBJECT_ID = DO.OBJECT_ID;

grant delete, insert, select, update on users to admin;

select * from users where 회원ID='admin' and 패스워드='q1w2e3r4';

insert into users(회원번호, 회원ID, 패스워드, 회원이름, 연락처, 등록일자) values (user_seq_no.nextval, 'oh', '1234', '오지원', '010-0000-0000', sysdate);

delete from users where 회원ID='ojw123';
delete from books where 회워


select * from books;

update users set 패스워드 = 'ccc',연락처 = '111-111-1111' where 회원ID ='aaa';

select * from books order by 책번호 desc;

select * from books where 책번호=3;

select * from books where 도서명 like ('%가%') order by 책번호;

insert into books (책번호, 도서명, 저자명, 출판사명, 출간일, 분야, 상태) values (BOOK_SEQ_NO.nextval, '첫 파이썬', '엘리스', '이지퍼블리싱', SYSDATE, '컴퓨터/IT', 0);


select * from users where 회원ID='bbb';
select * from users where 회원번호=31;

delete from users where 회원번호=33;
delete from rental where 회원번호=70;
select * from rental where 회원번호=70;

select 장바구니번호, 책번호, 도서명, 저자명, 출판사명, 출간일, 분야, 상태 from cart_detail join books using(책번호);
select 장바구니번호, 책번호, 도서명, 저자명, 출판사명, to_char(출간일, 'yy/mm/dd') 출간일, 분야, 상태 from cart_detail join books using(책번호);

select * from reservation;
select 예약번호, 책번호, 회원번호, to_char(예약일자, 'yy/mm/dd')예약일자 from reservation;

select * from rental;
select 대여번호, 책번호, 회원번호, to_char(반납예정일자, 'yy/mm/dd')반납예정일자, to_char(대여일자, 'yy/mm/dd')대여일자, to_char(반납일자, 'yy/mm/dd')반납일자, 반납여부, 연체여부 from rental;

select * from cart;
select 장바구니번호, 회원번호 from cart;

select * from cart_detail;
select 장바구니번호, 책번호 from cart_detail;

delete from cart where 회원번호=;

select * from books;
select 책번호, 도서명, 저자명, 출판사명, to_char(출간일, 'yy/mm/dd')출간일, 분야, 상태 from books;

update users set 패스워드=1111 where 회원ID='admin';
commit

select * from books where 상태 = 1 order by 책번호

select * from rental where 회원번호 = 31;