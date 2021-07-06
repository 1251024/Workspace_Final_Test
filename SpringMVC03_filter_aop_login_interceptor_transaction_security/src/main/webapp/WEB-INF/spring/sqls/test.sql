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

INSERT INTO MYBOARD
VALUES(MYNOSEQ.NEXTVAL, '관리자', '스프링 재밌다.', '정말 재밌다.', SYSDATE);

SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE
FROM MYBOARD
ORDER BY MYNO DESC;

SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE
FROM MYBOARD	
WHERE MYNO = 1;

--------------------------------------------------------------------------------------

--login table
DROP SEQUENCE MYMEMBERSEQ;
DROP TABLE MYMEMBER;

CREATE SEQUENCE MYMEMBERSEQ;

CREATE TABLE MYMEMBER(
	MEMBERNO NUMBER PRIMARY KEY,
	MEMBERID VARCHAR2(1000) NOT NULL,
	MEMBERPW VARCHAR2(1000) NOT NULL,
	MEMBERNAME VARCHAR2(1000) NOT NULL
);

INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'admin', '1234', '관리자');

SELECT MEMBERNO, MEMBERID, MEMBERPW, MEMBERNAME
FROM MYMEMBER;

INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'ee', 'ee', '관리자');

DELETE FROM MYMEMBER
WHERE MEMBERNO = 22;