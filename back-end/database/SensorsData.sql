/**
* To initialize SensorsData database
 */

-- ----------------------------
--  Clean
-- ----------------------------
\connect "template1";

DROP OWNED BY "directace";
DROP OWNED BY "dataaccessor";
DROP OWNED BY "dataprocessor";

DROP DATABASE IF EXISTS "MatchLog";

DROP USER dataprocessor;
DROP USER dataaccessor;

-- ----------------------------
--  Users
-- ----------------------------

CREATE USER "directace" CREATEDB CREATEUSER LOGIN PASSWORD 'directace' CREATEROLE;
CREATE USER "dataaccessor" LOGIN PASSWORD 'dataaccessor';
CREATE USER "dataprocessor" LOGIN PASSWORD 'dataprocessor';

-- ----------------------------
--  Database
-- ----------------------------
CREATE DATABASE "Match"  WITH OWNER "directace" ENCODING 'UTF8';

-- ----------------------------
--  Table structure for SensorsData
-- ----------------------------
\connect "Match";

DROP TABLE IF EXISTS "public"."MatchLog";
CREATE TABLE "public"."MatchLog" (
	"id" varchar NOT NULL COLLATE "default",
	"event" varchar NOT NULL COLLATE "default",
	"time_ms" varchar NOT NULL COLLATE "default",
	"val" varchar NOT NULL COLLATE "default",
	"player_id" varchar NOT NULL COLLATE "default",
	"match_id" varchar NOT NULL COLLATE "default"
)

WITH (OIDS=FALSE);
ALTER TABLE "public"."MatchLog" OWNER TO "directace";

-- ----------------------------
--  Grant
-- ----------------------------
GRANT SELECT ON "MatchLog" TO "dataaccessor";

GRANT SELECT ON "MatchLog" TO "dataprocessor";
GRANT INSERT ON "MatchLog" TO "dataprocessor";
GRANT UPDATE ON "MatchLog" TO "dataprocessor";
GRANT DELETE ON "MatchLog" TO "dataprocessor";

-- ----------------------------
--  Primary key structure for table SensorsData
-- ----------------------------
ALTER TABLE "public"."MatchLog" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;
