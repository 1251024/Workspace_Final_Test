DROP SEQUENCE farmSeq;
DROP TABLE weekendFarm;

CREATE SEQUENCE farmSeq;

--위도 경도 추가해야할듯
CREATE TABLE weekendFarm(
	farmSeq NUMBER PRIMARY KEY NOT NULL,
	farmName VARCHAR2(100) NOT NULL,
	farmAddress VARCHAR2(200) NOT NULL,
	farmPhone NUMBER(30) NOT NULL
	
);

INSERT INTO weekendFarm
VALUES (farmSeq.NEXTVAL, '대진이네 농장', '경기도 고양시', '01097954578');

SELECT * FROM WEEKENDFARM;

----------------------boardtable--------------------------------

DROP SEQUENCE entireBoardSeq;
DROP TABLE entireBoard;

CREATE SEQUENCE entireBoardSeq;

CREATE TABLE entireBoard(
	entireBoardSeq NUMBER PRIMARY KEY NOT NULL,
	boardKind NUMBER NOT NULL,
	boardDate DATE NOT NULL,
	boardTitle VARCHAR2(200),
	boardContents VARCHAR2(4000), 
	userId VARCHAR2(30) NOT NULL,
	userSeq NUMBER NOT NULL,
	boardImg VARCHAR2(4000),
	boardThumbImg VARCHAR2(4000),
	likeCount NUMBER
);

ALTER TABLE entireBoard
ADD CONSTRAINT BOARD_USER_FK
FOREIGN KEY (userSeq)
REFERENCES USERS (userSeq)
ON DELETE CASCADE;

---sns:1, knowhow:2, notice:3

INSERT INTO entireBoard
VALUES (entireBoardSeq.NEXTVAL, 1, SYSDATE, '테스트글입니다.', '테스트내용입니다.', 'test01', 1, NULL, NULL, NULL);

INSERT INTO entireBoard
VALUES (entireBoardSeq.NEXTVAL, 1, SYSDATE, '테스트글입니다2.', '테스트내용입니다2.', 'dh', 2, NULL, NULL, NULL);

INSERT INTO entireBoard
VALUES (entireBoardSeq.NEXTVAL, 1, SYSDATE, '테스트글입니다3.', '테스트내용입니다3.', 'dh', 2, NULL, NULL, NULL);

SELECT * FROM entireBoard;

SELECT *
FROM entireBoard
WHERE boardKind = 1
ORDER BY boardDate ASC;
----------------------------------------------------
--첨부파일을 위한 테이블 
DROP SEQUENCE boardFileSeq;
DROP TABLE boardFile;

CREATE SEQUENCE boardFileSeq;

CREATE TABLE boardFile(
	boardFileName VARCHAR2(1000) NOT NULL,
	entireBoardSeq NUMBER,
	regdate DATE
);

-- 게시글 첨부파일 테이블 참조키 설정
ALTER TABLE boardFile ADD CONSTRAINT FK_board_file
FOREIGN KEY (entireBoardSeq) REFERENCES entireBoard (entireBoardSeq)
ON DELETE CASCADE;

SELECT * FROM boardFile;


-------------------------상품 --------------------------------------
DROP SEQUENCE productSeq;
DROP TABLE Product;

CREATE SEQUENCE productSeq;

CREATE TABLE Product (
	productSeq NUMBER PRIMARY KEY NOT NULL,
	productName VARCHAR2(100) NOT NULL,
	productPrice NUMBER(30) NOT NULL,
	sellerAddress VARCHAR2(30),
	productRegDate DATE,
	productInfo VARCHAR2(4000),
	productImg VARCHAR2(4000),
	productThumb VARCHAR2(4000),
	saleStatus VARCHAR2(20),
	userRole VARCHAR2(10) NOT NULL,
	userSeq NUMBER NOT NULL,
	userId VARCHAR2(30),
	userLatitude VARCHAR2(1000),
	userLongitude VARCHAR2(1000) 
);

-------userRole = > ADMIN, USER
ALTER TABLE Product ADD CONSTRAINT FK_Product
FOREIGN KEY (userSeq) REFERENCES Users (userSeq)
ON DELETE CASCADE;

INSERT INTO Product 
VALUES (productSeq.NEXTVAL, '1인용 텃밭 세트', 12800, '운영자판매', NULL, NULL, NULL, NULL, 'Y', 'ADMIN', 1, '운영자', NULL, NULL);

INSERT INTO Product 
VALUES (productSeq.NEXTVAL, '가정용 호미 세트', 9800, '운영자판매', NULL, NULL, NULL, NULL, 'Y', 'ADMIN', 1, '운영자', NULL, NULL);

INSERT INTO Product 
VALUES (productSeq.NEXTVAL, '친환경 물뿌리개', 6800, '운영자판매', NULL, NULL, NULL, NULL, 'Y', 'ADMIN', 1, '운영자', NULL, NULL);


INSERT INTO Product 
VALUES (productSeq.NEXTVAL, '대진이가 심은 상추', 8000, '유저판매', SYSDATE, NULL, NULL, NULL, 'N', 'USER', 1, 'test', NULL, NULL);

INSERT INTO Product 
VALUES (productSeq.NEXTVAL, '태린이가 심은 고구마', 8800, '유저판매', SYSDATE, NULL, NULL, NULL, 'N','USER', 1, 'test', NULL, NULL);


SELECT * FROM Product;

SELECT * FROM Product
WHERE userRole = 'ADMIN'
ORDER BY productPrice DESC;

SELECT * FROM Product
WHERE userRole = 'USER'
ORDER BY productRegDate DESC;

----------------------댓글/대댓글------------------------
DROP SEQUENCE commentNoSeq;
DROP TABLE commentBoard;

CREATE SEQUENCE commentNoSeq;

CREATE TABLE commentBoard(
	commentNoSeq NUMBER PRIMARY KEY,    --댓글번호
	userSeq NUMBER NOT NULL,
	userId VARCHAR2(30) NOT NULL,
	reContent VARCHAR2(2000) NOT NULL,
	reRegDate DATE NOT NULL,
	entireBoardSeq NUMBER NOT NULL,
	groupNo NUMBER NOT NULL,   --댓글그룹번호
	reRepSeq NUMBER NOT NULL  --대댓글 번호 
);
ALTER TABLE commentBoard ADD CONSTRAINT FK_CommentBoard_ID
FOREIGN KEY (userSeq) REFERENCES Users (userSeq)
ON DELETE CASCADE;

ALTER TABLE commentBoard ADD CONSTRAINT FK_commentBoard_Ogigin
FOREIGN KEY (entireBoardSeq) REFERENCES entireBoard (entireBoardSeq)
ON DELETE CASCADE;

SELECT * FROM commentBoard;

--1번째 댓글 
INSERT INTO commentBoard
VALUES(commentNoSeq.NEXTVAL, 1, 'test', '댓글테스트입니다.', SYSDATE, 2, 1, 0);

--2번째 댓글
INSERT INTO commentBoard
VALUES(
	commentNoSeq.NEXTVAL, 1, 'test', '두번째댓글테스트입니다.', SYSDATE, 2,
	(SELECT groupNo FROM commentBoard WHERE commentNoSeq = 1) + 1,
	(SELECT reRepSeq FROM commentBoard WHERE commentNoSeq = 1) + 1
);

--1번째 댓글의 1번째 대댓글
INSERT INTO commentBoard
VALUES(
	commentNoSeq.NEXTVAL, 1, 'test', '1번째 대댓글 테스트입니다.', SYSDATE, 2, 1, 1
);

--1번째 댓글의 2번째 대댓글
INSERT INTO commentBoard
VALUES(
	commentNoSeq.NEXTVAL, 1, 'test', '1번째 대댓글 테스트입니다.', SYSDATE, 2, 1, 2
);
--2번째 댓글의 대댓글
INSERT INTO commentBoard
VALUES(
	commentNoSeq.NEXTVAL, 1, 'test', '1번째 대댓글 테스트입니다.', SYSDATE, 2, 2, 1
);
---------------------팔로우-----------------------------
--------------------좋아요------------------------------
--------------------해시태그----------------------------
























