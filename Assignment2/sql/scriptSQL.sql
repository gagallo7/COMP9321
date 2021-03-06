CREATE TABLE MOVIE (
	ID INTEGER UNIQUE NOT NULL,
	TITLE VARCHAR(20),
	AGE_RATING INTEGER,
	RATING INTEGER
);

    create table CINEMA (
        ID bigint NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
        LOCATION varchar(255),
        SEATINGCAPACITY integer,
        ATM integer default 0,
        WIDESCREEN integer default 0,
        SNACKBAR integer default 0,
        RESTAURANT integer default 0,
        primary key (ID)
    ); 

INSERT INTO  CINEMA(LOCATION, SEATINGCAPACITY,ATM,WIDESCREEN,SNACKBAR,RESTAURANT) VALUES ('SYDNEY', 20, 0, 1, 0, 1); 
INSERT INTO  MOVIE VALUES (1, 'Piratas do Caribe', 18, 8);
INSERT INTO  MOVIE VALUES (2, 'Harry Potter', 14, 9);

INSERT INTO MOVIE (ID, TITLE, URLPOST, GENRE, RATINGSUM, RATINGNUM, AGERATING) VALUES (0, 'Highlander', '', 'thriller', 0, 0, 6);

UPDATE MOVIE set RATINGSUM = 0 WHERE RATINGSUM IS NULL;
UPDATE MOVIE set RATINGNUM = 0 WHERE RATINGNUM IS NULL;
UPDATE MOVIE set RATING = 0 WHERE RATING IS NULL;
UPDATE MOVIE set AGERATING = 0 WHERE AGERATING IS NULL;
UPDATE MOVIE set ACTORS = 'UNKNOW' WHERE ACTORS IS NULL;
UPDATE MOVIE set DIRECTOR = 'UNKNOW' WHERE DIRECTOR IS NULL;

UPDATE MOVIE set RELEASEDATE = '2014-10-29 23:03:20' WHERE RELEASEDATE IS NULL;
UPDATE MOVIE set SYNOPSIS = 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.' WHERE SYNOPSIS IS NULL;

SELECT * from USERLOGIN WHERE USERNAME LIKE '*scri*';

UPDATE USERLOGIN set CONFIRMED = 1;

DELETE FROM USERLOGIN WHERE ID = 21;

SELECT * FROM MOVIE ORDER BY ID;
SELECT * FROM CINEMA;
SELECT * FROM USERLOGIN;
SELECT * FROM CINEMASESSION;
SELECT * FROM REVIEW;
SELECT * FROM BOOKING;

SELECT * FROM CINEMASESSION WHERE MOVIEID IN (SELECT ID FROM MOVIE);

INSERT INTO CINEMASESSION VALUES
    (0, 0, 55, '2014-10-29 23:03:20', 7);


INSERT INTO CINEMASESSION VALUES
    ((SELECT COUNT(ID) FROM CINEMASESSION), MOD((SELECT MAX(ID) FROM CINEMASESSION),22), 55, '2014-10-29 23:03:20', 7);

DROP TABLE CINEMASESSION;
DROP TABLE CINEMA;
DROP TABLE MOVIE;
DROP TABLE REVIEW;
DROP TABLE USERLOGIN;
DROP TABLE BOOKING;

select * from SYS.SYSTABLES;
