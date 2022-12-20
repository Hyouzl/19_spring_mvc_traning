USE SPRING_MVC_TRAINING2;

CREATE TABLE T_SPRING_BOARD_ADVANCE (
	NUM  		INT PRIMARY KEY,
	WRITER 		VARCHAR(50),
	EMAIL 		VARCHAR(50),
	SUBJECT 	VARCHAR(50),
	PASSWORD 	VARCHAR(500),
	REG_DATE 	DATETIME,
	REF 		INT,             
	RE_STEP 	INT,     	  
	RE_LEVEL 	INT,		  
	READ_COUNT 	INT,
	CONTENT 	VARCHAR(1000)
);

SELECT
		*
FROM
		T_SPRING_BOARD_ADVANCE;