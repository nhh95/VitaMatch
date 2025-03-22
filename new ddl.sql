


DROP TABLE ingredientDaily;
DROP TABLE ingredientHealth;
DROP TABLE ingredientOrgan;
DROP TABLE ingredientGenderAge;
DROP TABLE surveyDaily;
DROP TABLE surveyOrgan;
DROP TABLE surveyHealth;
DROP TABLE surveyGenderAge;
DROP TABLE memberAddress;
DROP TABLE address;
DROP TABLE adminAuthList;
DROP TABLE adminAuth;
DROP TABLE healthIngredient;
DROP TABLE logError;
DROP TABLE logApplication;
DROP TABLE logLevel;
DROP TABLE userActivity;
DROP TABLE activityType;
DROP TABLE emailVerification;
DROP TABLE badCombination;
DROP TABLE goodCombination;
DROP TABLE ingredientProduct;
DROP TABLE ingredientContent;
DROP TABLE ingredient;
DROP TABLE news;
DROP TABLE logSession;
DROP TABLE sessionStatus;
DROP TABLE sessionType;
DROP TABLE health;
DROP TABLE communityComment;
DROP TABLE communityPost;
DROP TABLE faqPost;
DROP TABLE noticePost;
DROP TABLE admin;
DROP TABLE medicationRecord;
DROP TABLE productMedication;
DROP TABLE reviewImage;
DROP TABLE reviewComment;
DROP TABLE review;
DROP TABLE member;
DROP TABLE productInfo;

CREATE TABLE `productInfo` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`productImage`	VARCHAR(1000)	NULL,
	`CompanyName`	VARCHAR(500)	NULL,
	`productName`	VARCHAR(500)	NOT NULL,
	`ReportNo`	VARCHAR(500)	NOT NULL,
	`registrationDate`	TIMESTAMP	NOT NULL,
	`expirationDate`	VARCHAR(500)	NULL,
	`medicationType`	VARCHAR(1000)	NULL,
	`ingestionMethod`	VARCHAR(1000)	NULL,
	`packagingMaterial`	VARCHAR(1000)	NULL,
	`packagingMethod`	VARCHAR(1000)	NULL,
	`preservation`	VARCHAR(1000)	NULL,
	`precautionsForIngestion`	VARCHAR(2000)	NULL,
	`functionalContent`	TEXT 	NULL,
	`standardsAndSpecifications`	TEXT 	NULL
);

CREATE TABLE `member` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`username`	VARCHAR(100)	NOT NULL,
	`email`	VARCHAR(100)	NOT NULL,
	`password`	VARCHAR(300)	NOT NULL,
	`name`	VARCHAR(100)	NOT NULL,
	`nickname`	VARCHAR(50)	NOT NULL,
	`dob`	TIMESTAMP	NOT NULL,
	`gender`	CHAR(1)	NOT NULL,
	`telephone`	VARCHAR(20)	NOT NULL,
	`address`	VARCHAR(300)	NULL,
	`status`	INT	NOT NULL	DEFAULT 1,
	`createTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`updateTime`	TIMESTAMP	NULL	DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (username)
);




CREATE TABLE `review` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`title`	VARCHAR(300)	NOT NULL,
   	`category`	VARCHAR(100)	NOT NULL,
    `name`	VARCHAR(300)	NOT NULL,
	`content`	VARCHAR(1000)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_seq) REFERENCES member(seq)  
);

CREATE TABLE `reviewComment` (
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`review_seq`	INT	NOT NULL,
	`member_seq`	INT	NOT NULL,
	`content`	VARCHAR(500)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (review_seq) REFERENCES review(seq),
    FOREIGN KEY (member_seq) REFERENCES member(seq)
);

CREATE TABLE `reviewImage` (
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`review_seq`	INT	NOT NULL,
	`image`	VARCHAR(300)	NULL,
    FOREIGN KEY (review_seq) REFERENCES review(seq)
);

CREATE TABLE `productMedication` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`productInfo_seq`	INT	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq),
    FOREIGN KEY (productInfo_seq) REFERENCES productInfo(seq)
);

CREATE TABLE `medicationRecord` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`medicationList_seq`	INT	NOT NULL,
	`startDate`	TIMESTAMP	NOT NULL,
	`duration`	INT	NOT NULL,
	`alertTime`	TIME	NOT NULL,
	`status`	VARCHAR(1)	NULL	DEFAULT 1,
    FOREIGN KEY (medicationList_seq) REFERENCES productMedication(seq)
);

CREATE TABLE `admin` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`id`	VARCHAR(50)	NOT NULL,
	`pw`	VARCHAR(100)	NOT NULL,
	`name`	VARCHAR(50)	NOT NULL,
	`birthDate`	TIMESTAMP	NOT NULL,
	`email`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `noticePost` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`admin_seq`	INT	NOT NULL,
	`title`	VARCHAR(50)	NOT NULL,
	`content`	VARCHAR(1000)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL,
    FOREIGN KEY (admin_seq) REFERENCES admin(seq)
);

CREATE TABLE `faqPost` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`admin_seq`	INT	NOT NULL,
	`title`	VARCHAR(50)	NOT NULL,
	`content`	VARCHAR(1000)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL,
    FOREIGN KEY (admin_seq) REFERENCES admin(seq)
    
);

CREATE TABLE `communityPost` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`title`	VARCHAR(50)	NOT NULL,
	`content`	VARCHAR(1000)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq)
);

CREATE TABLE `communityComment` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`communityPost_seq`	INT	NOT NULL,
	`content`	VARCHAR(300)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq),
    FOREIGN KEY (communityPost_seq) REFERENCES communityPost(seq)
    
);

CREATE TABLE `health` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`health`	VARCHAR(150)	NOT NULL
);

CREATE TABLE `sessionType` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(30)	NOT NULL
);

CREATE TABLE `sessionStatus` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `logSession` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`type_seq`	INT	NOT NULL,
	`logTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`ip`	VARCHAR(50)	NOT NULL,
	`status_seq`	INT	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq),
    FOREIGN KEY (type_seq) REFERENCES sessionType(seq),
    FOREIGN KEY (status_seq) REFERENCES sessionStatus(seq)
);

CREATE TABLE `news` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`title`	VARCHAR(100)	NOT NULL,
	`link`	VARCHAR(300)	NOT NULL,
	`originalLink`	VARCHAR(300)	NOT NULL,
	`description`	VARCHAR(500)	NOT NULL,
	`regDate`	TIMESTAMP	NOT NULL
);


CREATE TABLE `ingredient` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(500)	NOT NULL,
	`category`	INT	NOT NULL
);

CREATE TABLE `ingredientContent` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`functionalContent`	VARCHAR(1000)	NULL,
	`dailyIntake`	VARCHAR(1000)	NULL,
	`precautionsForIngestion`	VARCHAR(1000)	NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `ingredientProduct` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`product_seq`	INT	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (product_seq) REFERENCES productInfo(seq),
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `goodCombination` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`good`	VARCHAR(50)	NOT NULL,
	`reason`	VARCHAR(1500)	NOT NULL,
	`link`	VARCHAR(1000)	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `badCombination` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`bad`	VARCHAR(50)	NOT NULL,
	`reason`	VARCHAR(1500)	NOT NULL,
	`link`	VARCHAR(1000)	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `emailVerification` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`token`	VARCHAR(255)	NOT NULL,
	`expirationTime`	TIMESTAMP	NOT NULL,
	`requestTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`VerificationTime`	TIMESTAMP	NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq)
);

CREATE TABLE `activityType` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(30)	NOT NULL,
	`message`	VARCHAR(300)	NOT NULL
);

CREATE TABLE `userActivity` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`type_seq`	INT	NOT NULL,
	`url`	VARCHAR(500)	NOT NULL,
	`startTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`endTime`	TIMESTAMP	NULL,
	`status`	VARCHAR(30)	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq),
    FOREIGN KEY (type_seq) REFERENCES activityType(seq)
);

CREATE TABLE `logLevel` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(30)	NOT NULL,
	`message`	VARCHAR(300)	NOT NULL
);

CREATE TABLE `logApplication` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`level_seq`	INT	NOT NULL,
	`message`	VARCHAR(4000)	NOT NULL,
	`logTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`source`	VARCHAR(100)	NOT NULL,
    FOREIGN KEY (level_seq) REFERENCES logLevel(seq)
);

CREATE TABLE `logError` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`code`	VARCHAR(50)	NOT NULL,
	`message`	VARCHAR(4000)	NOT NULL,
	`errorTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`ip`	VARCHAR(50)	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq)
);

CREATE TABLE `healthIngredient` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`health_seq`	INT	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (health_seq) REFERENCES health(seq),
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `adminAuth` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`role`	VARCHAR(50)	NOT NULL
);

CREATE TABLE `adminAuthList` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`admin_seq`	INT	NOT NULL,
	`adminAuth_seq`	INT	NOT NULL,
    FOREIGN KEY (admin_seq) REFERENCES admin(seq),
    FOREIGN KEY (adminAuth_seq) REFERENCES adminAuth(seq)
);

CREATE TABLE `address` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`addressRoad`	VARCHAR(500)	NULL,
	`addressLot`	VARCHAR(500)	NULL,
	`postalCode`	VARCHAR(20)	NULL,
	`latitude`	VARCHAR(20)	NULL,
	`longitude`	VARCHAR(20)	NULL,
	`createTime`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`updateTime`	TIMESTAMP	NULL
);

CREATE TABLE `memberAddress` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`member_seq`	INT	NOT NULL,
	`address_seq`	INT	NOT NULL,
    FOREIGN KEY (member_seq) REFERENCES member(seq),
    FOREIGN KEY (address_seq) REFERENCES address(seq)
);

CREATE TABLE `surveyGenderAge` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`gender`	CHAR(1)	NOT NULL,
	`age`	INT	NOT NULL
);

CREATE TABLE `surveyHealth` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `surveyOrgan` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(100)	NOT NULL
);

CREATE TABLE `surveyDaily` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(100)	NOT NULL
);


CREATE TABLE `ingredientGenderAge` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`genderAge_seq`	INT	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (genderAge_seq) REFERENCES surveyGenderAge(seq),
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `ingredientOrgan` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`organ_seq`	INT	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (organ_seq) REFERENCES surveyOrgan(seq),
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `ingredientHealth` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`health_seq`	INT	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (health_seq) REFERENCES surveyHealth(seq),
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);

CREATE TABLE `ingredientDaily` (--
	`seq`	INT	AUTO_INCREMENT PRIMARY KEY,
	`daily_seq`	INT	NOT NULL,
	`ingredient_seq`	INT	NOT NULL,
    FOREIGN KEY (daily_seq) REFERENCES surveyDaily(seq),
    FOREIGN KEY (ingredient_seq) REFERENCES ingredient(seq)
);



DROP VIEW vwGoodCombination;

CREATE VIEW vwGoodCombination AS --
SELECT g.seq, 
       g.ingredient_seq, 
       i.name AS ingredientName, 
       g.good, 
       i2.name AS name, 
       g.reason, 
       g.link,
       c.functionalContent
FROM goodCombination g 
LEFT JOIN ingredient i ON g.ingredient_seq = i.seq
LEFT JOIN ingredient i2 ON g.good = i2.seq
LEFT JOIN ingredientContent c ON g.ingredient_seq = c.ingredient_seq;

DROP VIEW vwBadCombination;

CREATE VIEW vwBadCombination AS --
SELECT g.seq, 
       g.ingredient_seq, 
       i.name AS ingredientName, 
       g.bad, 
       i2.name AS name, 
       g.reason, 
       g.link,
       c.functionalContent
FROM badCombination g 
LEFT JOIN ingredient i ON g.ingredient_seq = i.seq
LEFT JOIN ingredient i2 ON g.bad = i2.seq
LEFT JOIN ingredientContent c ON g.ingredient_seq = c.ingredient_seq;

-- 성별 나이대별 영양제 성분 및 내용 뷰
drop view vwGenderAgeRecommend;

CREATE OR REPLACE VIEW vwGenderAgeRecommend AS --
SELECT 
    iga.seq,
    ga.gender as gender,
    ga.age as age,
    iga.genderAge_seq as genderAgeSeq,
    iga.ingredient_seq as ingredientSeq,
    i.name as ingredientName,
    ic.functionalContent
FROM surveyGenderAge ga
INNER JOIN ingredientGenderAge iga ON ga.seq = iga.genderAge_seq
INNER JOIN ingredient i ON iga.ingredient_seq = i.seq
INNER JOIN ingredientContent ic ON i.seq = ic.ingredient_seq;

-- 건강검진 영양제 성분 및 내용 뷰
drop view vwHealthRecommend;

CREATE VIEW vwHealthRecommend AS --
SELECT ih.seq, 
       h.name,
       ih.health_seq as healthSeq,
       ih.ingredient_seq as ingredientSeq,
       i.name as ingredientName,
       ic.functionalContent
FROM surveyHealth h
INNER JOIN ingredientHealth ih ON h.seq = ih.health_seq
INNER JOIN ingredient i ON ih.ingredient_seq = i.seq
INNER JOIN ingredientContent ic ON i.seq = ic.ingredient_seq;

-- 주요 장기 영양제 성분 및 내용 뷰
drop view vwOrganRecommend;

CREATE VIEW vwOrganRecommend AS --
SELECT io.seq, 
       o.name,
       io.organ_seq as organSeq,
       io.ingredient_seq as ingredientSeq,
       i.name as ingredientName,
       ic.functionalContent
FROM surveyOrgan o
INNER JOIN ingredientOrgan io ON o.seq = io.organ_seq
INNER JOIN ingredient i ON io.ingredient_seq = i.seq
INNER JOIN ingredientContent ic ON i.seq = ic.ingredient_seq;

-- 일상생활 영양제 성분 및 내용 뷰
drop view vwDailyRecommend;

CREATE VIEW vwDailyRecommend AS
SELECT id.seq, 
       d.name,
       id.daily_seq as dailySeq,
       id.ingredient_seq as ingredientSeq,
       i.name as ingredientName,
       ic.functionalContent
FROM surveyDaily d
INNER JOIN ingredientDaily id ON d.seq = id.daily_seq
INNER JOIN ingredient i ON id.ingredient_seq = i.seq
INNER JOIN ingredientContent ic ON i.seq = ic.ingredient_seq;


CREATE VIEW vwReview AS
SELECT
    r.seq as seq, 
    m.nickname as nickname, 
    r.regDate as createDate,
    r.title as title,
    r.category as category,
    r.name as name,
    r.content as content,
    i.image as image
FROM review r
    inner join member m on r.member_seq = m.seq
    inner join reviewImage i on r.seq = i.review_seq
        order by createDate desc;


       
       
CREATE OR REPLACE VIEW healthCategories AS
SELECT 
    E.product_seq AS id,
    F.productImage AS productImage,
    F.productName AS productName,
    F.companyName AS companyName,
    E.ingredient_name AS categoryName,
    E.health AS health
FROM (
    SELECT 
        C.health,
        C.ingredient_seq,
        C.ingredient_name,
        D.product_seq
    FROM (
        SELECT 
            B.myseq,
            B.health,
            I.seq AS ingredient_seq,
            I.name AS ingredient_name
        FROM (
            SELECT 
                A.seq AS myseq,
                A.ingredient_seq,
                H.health
            FROM healthIngredient A
            INNER JOIN health H
            ON A.health_seq = H.seq
        ) B
        INNER JOIN ingredient I
        ON B.ingredient_seq = I.seq
    ) C
    INNER JOIN ingredientProduct D
    ON C.ingredient_seq = D.ingredient_seq
) E
INNER JOIN productInfo F
ON E.product_seq = F.seq;





commit;
