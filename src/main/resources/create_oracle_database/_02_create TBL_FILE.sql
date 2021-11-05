
-----------

CREATE TABLE TBL_FILE (
            "N_ID"                      NUMBER(19,0) NOT NULL PRIMARY KEY,
        	"VCH_NAME"                  VARCHAR2(255 CHAR),
        	"VCH_LOCATION"              VARCHAR2(255 CHAR),
        	"VCH_ACTIONS"               VARCHAR2(255 CHAR),
        	"VCH_LAST_UPDATE_DATE_TIME" VARCHAR2(255 CHAR),
        	"VCH_LAST_UPDATE_USER"      VARCHAR2(255 CHAR),
        	"VCH_HISTORY"               VARCHAR2(255 CHAR)
         );
COMMIT;

-----------

INSERT INTO TBL_FILE (N_ID, VCH_NAME, VCH_LOCATION, VCH_ACTIONS, VCH_LAST_UPDATE_DATE_TIME, VCH_LAST_UPDATE_USER, VCH_HISTORY)
VALUES (1, 'tabref.properties', '/opt/wildfly', 'Edit / Revert', '11.11.2021 14:15', 'admin', 'View');

INSERT INTO TBL_FILE (N_ID, VCH_NAME, VCH_LOCATION, VCH_ACTIONS, VCH_LAST_UPDATE_DATE_TIME, VCH_LAST_UPDATE_USER, VCH_HISTORY)
VALUES (2, 'log4j.properties', '/opt/wildfly', 'Edit / Revert', '11.11.2021 14:15', 'admin', 'View');

INSERT INTO TBL_FILE (N_ID, VCH_NAME, VCH_LOCATION, VCH_ACTIONS, VCH_LAST_UPDATE_DATE_TIME, VCH_LAST_UPDATE_USER, VCH_HISTORY)
VALUES (3, 'application.properties', '/opt/wildfly', 'Edit / Revert', '11.11.2021 14:15', 'admin', 'View');

INSERT INTO TBL_FILE (N_ID, VCH_NAME, VCH_LOCATION, VCH_ACTIONS, VCH_LAST_UPDATE_DATE_TIME, VCH_LAST_UPDATE_USER, VCH_HISTORY)
VALUES (4, 'application.yml', '/opt/wildfly', 'Edit / Revert', '11.11.2021 14:15', 'admin', 'View');

COMMIT;

-----------



