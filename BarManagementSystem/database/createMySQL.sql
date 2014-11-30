
-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'BM_ITEM'
-- 
-- ---
DROP database BAR_MANAGEMENT;
CREATE DATABASE BAR_MANAGEMENT;
USE BAR_MANAGEMENT;
		
CREATE TABLE BM_ITEM (
  ITM_ID INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  ITM_NAME VARCHAR(50) NOT NULL UNIQUE,
  ITM_PRICE DOUBLE precision  NOT NULL,
  ITM_TYPE VARCHAR(50) NOT NULL,
  ITM_DATE_CREATED TIMESTAMP DEFAULT NOW(),
  ITM_DATE_UPDATED TIMESTAMP DEFAULT NOW(),
  ITM_CREATED_BY_BM_USER_ID INTEGER NULL,
  ITM_UPDATED_BY_BM_USER_ID INTEGER NULL,
  PRIMARY KEY (ITM_ID)
);

-- ---
-- Table 'BM_USER'
-- 
-- ---


	
CREATE TABLE BM_USER (
  USR_ID INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  USR_FIRSTNAME VARCHAR(50) NOT NULL,
  USR_LASTNAME VARCHAR(50) NOT NULL,
  USR_USERNAME VARCHAR(50) NOT NULL UNIQUE,
  USR_PASSWORD VARCHAR(1000) NOT NULL,
  USR_ROLE VARCHAR(50) NOT NULL,
  USR_STATUS INTEGER NOT NULL,
  USR_DATE_CREATED TIMESTAMP DEFAULT NOW(),
  USR_DATE_UPDATED TIMESTAMP DEFAULT NOW(),
  USR_CREATED_BY_BM_USER_ID INTEGER NULL,
  USR_UPDATED_BY_BM_USER_ID INTEGER NULL,
  PRIMARY KEY (USR_ID)
);

-- ---
-- Table 'BM_ORDER_STATUS'
-- 
-- ---

		
CREATE TABLE BM_ORDER_STATUS (
  ORDST_ID INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  ORDRST_VALUE VARCHAR(50) NOT NULL,
  ORDST_DATE_CREATED TIMESTAMP DEFAULT NOW(),
  ORDST_DATE_UPDATED TIMESTAMP DEFAULT NOW(),
  ORDST_CREATED_BY_BM_USER_ID INTEGER  NULL,
  ORDST_UPDATED_BY_BM_USER_ID INTEGER  NULL,
  PRIMARY KEY (ORDST_ID)
);

-- ---
-- Table 'BM_ORDER'
-- 
-- ---

		
CREATE TABLE BM_ORDER (
  ORD_ID INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  ORDR_STATUS_ID INTEGER NOT NULL,
  ORDR_TABLE_ID INTEGER NOT NULL,
  ORDR_BARMAN_ID INTEGER NULL,
  ORD_DATE_CREATED TIMESTAMP DEFAULT NOW(),
  ORD_DATE_UPDATED TIMESTAMP DEFAULT NOW(),
  ORD_CREATED_BY_BM_USER_ID INTEGER NOT NULL,
  ORD_UPDATED_BY_BM_USER_ID INTEGER  NULL,
  PRIMARY KEY (ORD_ID)
);

-- ---
-- Table 'TABLE'
-- 
-- ---

		
CREATE TABLE BM_TABLE (
  TBL_ID INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  TBL_NUMBER INTEGER NOT NULL UNIQUE,
  TBL_DATE_CREATED TIMESTAMP DEFAULT NOW(),
  TBL_DATE_UPDATED TIMESTAMP DEFAULT NOW(),
  TBL_CREATED_BY_BM_USER_ID INTEGER  NULL,
  TBL_UPDATED_BY_BM_USER_ID INTEGER  NULL,
  PRIMARY KEY (TBL_ID)
);

-- ---
-- Table 'BM_ORDER_TO_BM_ITEM'
-- 
-- ---

		
CREATE TABLE BM_ORDER_TO_BM_ITEM (
  ORDER_TO_ITEM_ID INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  ORDR_ID INTEGER NOT NULL,
  ITM_ID INTEGER NOT NULL,
  PRIMARY KEY (ORDER_TO_ITEM_ID)
);

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE BM_ITEM ADD FOREIGN KEY (ITM_CREATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ITEM ADD FOREIGN KEY (ITM_UPDATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_USER ADD FOREIGN KEY (USR_CREATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_USER ADD FOREIGN KEY (USR_UPDATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ORDER_STATUS ADD FOREIGN KEY (ORDST_CREATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ORDER_STATUS ADD FOREIGN KEY (ORDST_UPDATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ORDER ADD FOREIGN KEY (ORDR_STATUS_ID) REFERENCES BM_ORDER_STATUS (ORDST_ID);
ALTER TABLE BM_ORDER ADD FOREIGN KEY (ORDR_TABLE_ID) REFERENCES BM_TABLE (TBL_ID);
ALTER TABLE BM_ORDER ADD FOREIGN KEY (ORDR_BARMAN_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ORDER ADD FOREIGN KEY (ORD_CREATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ORDER ADD FOREIGN KEY (ORD_UPDATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_TABLE ADD FOREIGN KEY (TBL_CREATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_TABLE ADD FOREIGN KEY (TBL_UPDATED_BY_BM_USER_ID) REFERENCES BM_USER (USR_ID);
ALTER TABLE BM_ORDER_TO_BM_ITEM ADD FOREIGN KEY (ORDR_ID) REFERENCES BM_ORDER (ORD_ID);
ALTER TABLE BM_ORDER_TO_BM_ITEM ADD FOREIGN KEY (ITM_ID) REFERENCES BM_ITEM (ITM_ID);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE BM_ITEM ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE BM_USER ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE BM_ORDER_STATUS ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE BM_ORDER ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE TABLE ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE BM_ORDER_TO_BM_ITEM ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO BM_ITEM (ITM_ID,ITM_NAME,ITM_PRICE,ITM_TYPE,ITM_DATE_CREATED,ITM_DATE_UPDATED,ITM_CREATED_BY_BM_USER_ID,ITM_UPDATED_BY_BM_USER_ID) VALUES
-- ('','','','','','','','');
-- INSERT INTO BM_USER (USR_ID,USR_FIRSTNAME,USR_LASTNAME,USR_USENAME,USR_PASSOWORD,USR_ROLE,USR_STATUS,USR_DATE_CREATED,USR_DATE_UPDATED,USR_CREATED_BY_BM_USER_ID,USR_UPDATED_BY_BM_USER_ID) VALUES
-- ('','','','','','','','','','','');
-- INSERT INTO BM_ORDER_STATUS (ORDST_ID,ORDRST_VALUE,ORDST_DATE_CREATED,ORDST_DATE_UPDATED,ORDST_CREATED_BY_BM_USER_ID,ORDST_UPDATED_BY_BM_USER_ID) VALUES
-- ('','','','','','');
-- INSERT INTO BM_ORDER (ORD_ID,ORDR_STATUS_ID,ORDR_TABLE_ID,ORDR_BARMAN_ID,ORD_DATE_CREATED,ORD_DATE_UPDATED,ORD_CREATED_BY_BM_USER_ID,ORD_UPDATED_BY_BM_USER_ID) VALUES
-- ('','','','','','','','');
-- INSERT INTO TABLE (TBL_ID,TBL_NUMBER,TBL_DATE_CREATED,TBL_DATE_UPDATED,TBL_CREATED_BY_BM_USER_ID,TBL_UPDATED_BY_BM_USER_ID) VALUES
-- ('','','','','','');
-- INSERT INTO BM_ORDER_TO_BM_ITEM (ORDR_ID,ITM_ID) VALUES
-- ('','');

