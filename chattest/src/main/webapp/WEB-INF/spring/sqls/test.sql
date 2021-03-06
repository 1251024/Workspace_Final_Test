DROP SEQUENCE MYNOSEQ;
DROP TABLE MYBOARD;

CREATE SEQUENCE MYNOSEQ;

CREATE TABLE MYBOARD(
	MYNO NUMBER PRIMARY KEY,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYTITLE VARCHAR2(2000) NOT NULL,
	MYCONTENT VARCHAR2(4000) NOT NULL,
	MYDATE DATE NOT NULL
);

INSERT INTO MYBOARD VALUES(MYNOSEQ.NEXTVAL, '관리자', '스프링 재밌다', '정말 재밌다', SYSDATE);

SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD ORDER BY MYNO DESC;




----------------------------------------------------------------


DROP SEQUENCE MYMEMBERSEQ;
DROP TABLE MYMEMBER;

CREATE SEQUENCE MYMEMBERSEQ;

CREATE TABLE MYMEMBER(
	MEMBERNO NUMBER PRIMARY KEY,
	MEMBERID VARCHAR2(1000) NOT NULL,
	MEMBERPW VARCHAR2(1000) NOT NULL,
	MEMBERNAME VARCHAR2(1000) NOT NULL
);

INSERT INTO MYMEMBER VALUES(MYMEMBERSEQ.NEXTVAL, 'admin', '1234', '관리자');

SELECT MEMBERNO, MEMBERID, MEMBERPW, MEMBERNAME FROM MYMEMBER;





-------------------------------------------




DROP SEQUENCE USERSEQ;
DROP TABLE USERS;


CREATE SEQUENCE USERSEQ;

CREATE TABLE USERS(
	USERSEQ NUMBER PRIMARY KEY,
	USERID VARCHAR2(100) unique NOT NULL,
	PASSWORD VARCHAR2(100) NOT NULL,
	USEREMAIL VARCHAR2(1000) NOT NULL,
	USERPHONE VARCHAR2(1000) NOT NULL,
	USEROADDRESS VARCHAR2(4000) NOT NULL,
	USERADDRESS VARCHAR2(4000) NOT NULL,
	USERDETAILADDRESS VARCHAR2(4000) NOT NULL,
	USERROLE VARCHAR2(100) CHECK(USERROLE IN ('admin', 'user')),
	USERNAME VARCHAR2(100) NOT NULL,
	USERBIRTHDAY VARCHAR2(100) NOT NULL
);


INSERT INTO USERS
VALUES(USERSEQ.NEXTVAL, 'test01', '1234', 'jd@naver.com', 01012311231, '경기도 고양시', 
'admin', '김대진', '931031');


INSERT INTO USERS
VALUES(USERSEQ.NEXTVAL, 'dh', '3333', 'dh@naver.com', 010123423112, '경기도 남양주시', 
'admin', '김도한', '950511');

INSERT INTO USERS
VALUES(USERSEQ.NEXTVAL, 'kim123', '1234', 'ehgks3323@naver.com', 010123423112, '47500','서울 건대근처', '서울 연제구 거제동',
'user', '김도한', '941211');


SELECT * FROM USERS;


DELETE FROM USERS WHERE USEREMAIL = 'ehgks3323@naver.com';





SELECT COUNT(*) FROM USERS WHERE USEREMAIL = 'ehgks3323@naver.com';