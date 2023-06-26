
  -- USER SQL
CREATE USER open_access IDENTIFIED BY open_access 
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- QUOTAS

-- ROLES
GRANT "DATAPUMP_EXP_FULL_DATABASE" TO open_access ;
GRANT "CONNECT" TO open_access ;
GRANT "DATAPUMP_IMP_FULL_DATABASE" TO open_access ;
GRANT "RESOURCE" TO open_access ;
GRANT "EXP_FULL_DATABASE" TO open_access ;
GRANT "IMP_FULL_DATABASE" TO open_access ;

-- SYSTEM PRIVILEGES

DROP VIEW v_codes 
;

DROP TABLE m_tariff CASCADE CONSTRAINTS ;

DROP TABLE m_company CASCADE CONSTRAINTS ;

DROP TABLE m_company_employee CASCADE CONSTRAINTS ;

DROP TABLE m_company_location CASCADE CONSTRAINTS ;

DROP TABLE m_company_shareholders CASCADE CONSTRAINTS ;

DROP TABLE m_feeder CASCADE CONSTRAINTS ;

DROP TABLE m_powerplant CASCADE CONSTRAINTS ;

DROP TABLE m_generator CASCADE CONSTRAINTS ;

DROP TABLE m_lov_detail CASCADE CONSTRAINTS ;

DROP TABLE m_lov_header CASCADE CONSTRAINTS ;

DROP TABLE m_org CASCADE CONSTRAINTS ;

DROP TABLE m_service CASCADE CONSTRAINTS ;

DROP TABLE m_substation CASCADE CONSTRAINTS ;

DROP TABLE m_user CASCADE CONSTRAINTS ;

DROP TABLE t_grid_conn_appln CASCADE CONSTRAINTS ;

DROP TABLE m_signup CASCADE CONSTRAINTS ;

DROP TABLE m_Company_meter CASCADE CONSTRAINTS ;

DROP TABLE m_Company_shareholder CASCADE CONSTRAINTS ;

DROP TABLE m_Company_shareholder CASCADE CONSTRAINTS ;

  CREATE TABLE "M_TARIFF" 
   (	"ID" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"TYPE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"FROM_DATE" DATE, 
	"TO_DATE" DATE, 
	"WEG_GROUP_CODE" VARCHAR2(20 BYTE), 
	"RATE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"REFERENCE" VARCHAR2(100 BYTE), 
	 CONSTRAINT "M_TARIFF_PK" PRIMARY KEY ("ID"));
	 

--  1. Store Company records
--  2. Compay can be related to generator or ht consumer
--  3. company will have many services - generator ht services or consumer ht
--  services
--  4. company_type is GENERATOR or HT_CONSUMER
CREATE TABLE m_company
  (
    id                VARCHAR2 (50) NOT NULL ,
    code              VARCHAR2 (50) ,
    name              VARCHAR2 (100) NOT NULL ,
    type              VARCHAR2 (50) NOT NULL ,
    registration_no   VARCHAR2 (50) ,
    registration_date DATE ,
    cob_date          DATE ,
    incorp_place      VARCHAR2 (50) ,
    is_captive        VARCHAR2 (25) ,
    captive_purpose   VARCHAR2 (100) ,
    pan               VARCHAR2 (50) ,
    tan               VARCHAR2 (50) ,
    cst               VARCHAR2 (50) ,
    gst               VARCHAR2 (50) ,
    enabled           CHAR (1)
  ) ;
ALTER TABLE m_company ADD CONSTRAINT m_company_PK PRIMARY KEY ( id ) ;


CREATE TABLE m_company_employee
  (
    id              VARCHAR2 (50) NOT NULL ,
    m_company_id    VARCHAR2 (50) ,
    purpose         VARCHAR2 (50) ,
    name            VARCHAR2 (100) ,
    employee_no     VARCHAR2 (50) ,
    designationCode VARCHAR2 (50) ,
    landline        VARCHAR2 (50) ,
    mobile          VARCHAR2 (50) ,
    fax             VARCHAR2 (50) ,
    email           VARCHAR2 (50) ,
    enabled         CHAR (1)
  ) ;
ALTER TABLE m_company_employee ADD CONSTRAINT m_company_employee_PK PRIMARY KEY ( id ) ;


CREATE TABLE m_company_location
  (
    id           VARCHAR2 (50) NOT NULL ,
    m_company_id VARCHAR2 (50) ,
    type         VARCHAR2 (50) ,
    line1        VARCHAR2 (100) ,
    city         VARCHAR2 (50) ,
    districtCode VARCHAR2 (50) ,
    stateCode    VARCHAR2 (50) ,
    pinCode      VARCHAR2 (50) ,
    enabled      CHAR (1)
  ) ;
ALTER TABLE m_company_location ADD CONSTRAINT m_company_location_PK PRIMARY KEY ( id ) ;



CREATE TABLE m_feeder
  (
    id              VARCHAR2 (50) NOT NULL ,
    code            VARCHAR2 (50) ,
    name            VARCHAR2 (50) NOT NULL ,
    voltage         VARCHAR2 (25) ,
    m_substation_id VARCHAR2 (50) ,
    remarks         VARCHAR2 (100) ,
	SS_NAME 		VARCHAR2 (200),
    enabled         CHAR (1)
  ) ;
ALTER TABLE m_feeder ADD CONSTRAINT m_feeder_PK PRIMARY KEY ( id ) ;

CREATE TABLE m_powerplant
  (
    id                          VARCHAR2 (50) NOT NULL ,
    is_primary                  CHAR (1) ,
    version                     NUMBER (2) ,
    code                        VARCHAR2 (50) ,
    name                        VARCHAR2 (100) ,
    plant_type_code             VARCHAR2 (50) ,
    fuel_type_code              VARCHAR2 (50) ,
    m_company_service_id                VARCHAR2 (50) ,
    m_org_id                    VARCHAR2 (50) ,
    t_grid_conn_appln_id        VARCHAR2 (50) ,
    total_capacity              VARCHAR2 (25) ,
    m_substation_id             VARCHAR2 (50) ,
    interface_voltage_phase     VARCHAR2 (25) ,
    interface_voltage_frequency VARCHAR2 (25) ,
    commission_date             DATE ,
    purpose                     VARCHAR2 (500) ,
    enabled                     CHAR (1) ,
    status                      VARCHAR2 (25) ,
    line1                       VARCHAR2 (100) ,
    city                        VARCHAR2 (50) ,
    state_code                  VARCHAR2 (50) ,
    pincode                     VARCHAR2 (50) ,
    village                     VARCHAR2 (50) ,
    taluk_code                  VARCHAR2 (50) ,
    district_code               VARCHAR2 (50) ,
    pls_sf_no                   VARCHAR2 (50) ,
    pl_village                  VARCHAR2 (50) ,
    pl_town                     VARCHAR2 (50) ,
    pl_taluk_code               VARCHAR2 (50) ,
    pl_district_code            VARCHAR2 (50) ,
    wind_pass_code              VARCHAR2 (50) ,
    wind_zone_area_code         VARCHAR2 (50)
  ) ;
ALTER TABLE m_powerplant ADD CONSTRAINT m_powerplant_PK PRIMARY KEY ( id ) ;
ALTER TABLE m_powerplant ADD CONSTRAINT m_powerplant_m_org_FK FOREIGN KEY ( m_org_id ) REFERENCES m_org ( id ) ;
ALTER TABLE m_powerplant ADD CONSTRAINT m_powerplant_m_service_FK FOREIGN KEY ( m_service_id ) REFERENCES m_service ( id ) ;
ALTER TABLE m_powerplant ADD CONSTRAINT m_powerplant_m_substation_FK FOREIGN KEY ( m_substation_id ) REFERENCES m_substation ( id ) ;
--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_powerplant ADD CONSTRAINT m_powerplant_t_grid_conn_appln_FK FOREIGN KEY ( t_grid_conn_appln_id ) REFERENCES t_grid_conn_appln ( id ) ;

CREATE TABLE m_generator
  (
    id              VARCHAR2 (50) NOT NULL ,
    m_powerplant_id VARCHAR2 (50) NOT NULL ,
    name            VARCHAR2 (100) ,
    make_code       VARCHAR2 (50) ,
    serial_no       VARCHAR2 (50) ,
    rotor_dia       VARCHAR2 (25) ,
    hub_height      VARCHAR2 (25) ,
    capacity        VARCHAR2 (25) ,
    reference_id    VARCHAR2 (50) ,
    voltage         VARCHAR2 (25) ,
    enabled         CHAR (1)
  ) ;
ALTER TABLE m_generator ADD CONSTRAINT m_generator_PK PRIMARY KEY ( id ) ;
ALTER TABLE m_generator ADD CONSTRAINT m_generator_m_powerplant_FK FOREIGN KEY ( m_powerplant_id ) REFERENCES m_powerplant ( id ) ;


CREATE TABLE m_lov_detail
  (
    m_lov_code VARCHAR2 (50) NOT NULL ,
    code       VARCHAR2 (50) NOT NULL ,
    value_desc VARCHAR2 (50) ,
    enabled    CHAR (1) ,
    attrib1    VARCHAR2 (25) ,
    attrib2    VARCHAR2 (25) ,
    attrib3    VARCHAR2 (25)
  ) ;
ALTER TABLE m_lov_detail ADD CONSTRAINT m_lov_detail_PK PRIMARY KEY ( code ) ;
--  ERROR: UK name length exceeds maximum allowed length(30)
ALTER TABLE m_lov_detail ADD CONSTRAINT m_lov_detail_code_m_lov_code_UN UNIQUE ( code , m_lov_code ) ;


CREATE TABLE m_lov_header
  (
    code    VARCHAR2 (50) NOT NULL ,
    name    VARCHAR2 (50) ,
    remarks VARCHAR2 (50)
  ) ;
ALTER TABLE m_lov_header ADD CONSTRAINT m_lov_header_PK PRIMARY KEY ( code ) ;
ALTER TABLE m_lov_header ADD CONSTRAINT m_lov_header_code_UN UNIQUE ( code ) ;


--  entity_type_code - EDC, SLDC, NCES, PPP
CREATE TABLE m_org
  (
    id          VARCHAR2 (50) NOT NULL ,
    code        VARCHAR2 (50) NOT NULL ,
    name        VARCHAR2 (50) NOT NULL ,
    type_code   VARCHAR2 (50) NOT NULL ,
    parent_code VARCHAR2 (50) ,
    address     VARCHAR2 (200) ,
    landline    VARCHAR2 (25) ,
    mobile      VARCHAR2 (25) ,
    email       VARCHAR2 (25)
  ) ;
ALTER TABLE m_org ADD CONSTRAINT m_org_PK PRIMARY KEY ( id ) ;
ALTER TABLE m_org ADD CONSTRAINT m_org_code_UN UNIQUE ( code ) ;


--  service_type could be GENERATOR or HT_CONSUMER
--  once service can be related to multuple generators or multiple ht consumers
--  however a service cannot be related both generator and consumer at same time
CREATE TABLE m_company_service
  (
    id                 VARCHAR2 (50) NOT NULL ,
    type               VARCHAR2 (25) NOT NULL ,
    "number"           VARCHAR2 (50) ,
    m_company_id       VARCHAR2 (50) ,
    m_org_id           VARCHAR2 (50) ,
    capacity           VARCHAR2 (50) ,
    m_substation_id    VARCHAR2 (50) ,
    m_feeder_id        VARCHAR2 (50) ,
    ref_number         VARCHAR2 (50) ,
    voltage            VARCHAR2 (50) ,
    tariff             VARCHAR2 (50) ,
    sanctioned_quantum VARCHAR2 (50) ,
    enabled            CHAR (1)
  ) ;
ALTER TABLE m_service ADD CONSTRAINT m_service_PK PRIMARY KEY ( id ) ;


CREATE TABLE m_substation
  (
    id       VARCHAR2 (50) NOT NULL ,
    code     VARCHAR2 (50) ,
    name     VARCHAR2 (50) NOT NULL ,
    remarks  VARCHAR2 (100) ,
    m_org_id VARCHAR2 (50)
  ) ;
ALTER TABLE m_substation ADD CONSTRAINT m_substation_PK PRIMARY KEY ( id ) ;


CREATE TABLE m_user
  (
    id         VARCHAR2 (50) NOT NULL ,
    first_name VARCHAR2 (50) ,
    last_name  VARCHAR2 (50) ,
    --  A(admin) or I(Internal) or E(external)
    type         VARCHAR2 (50) NOT NULL ,
    enabled      CHAR (1) ,
    m_org_id     VARCHAR2 (50) ,
    m_company_id VARCHAR2 (50)
  ) ;
ALTER TABLE m_user ADD CONSTRAINT m_user_PK PRIMARY KEY ( id ) ;
-- Error - Unique Constraint m_user.m_user__UN doesn't have columns 

CREATE TABLE t_grid_conn_appln
  (
    id             VARCHAR2 (50) NOT NULL ,
    appln_no       VARCHAR2 (50) ,
    applied_date   DATE ,
    approval_date  DATE ,
    activated_date DATE ,
    status         VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE t_grid_conn_appln ADD CONSTRAINT t_grid_conn_appln_PK PRIMARY KEY ( id ) ;

CREATE TABLE m_signup
  (
    id                 VARCHAR2 (50) ,
    company_name       VARCHAR2 (100) ,
    purpose            VARCHAR2 (50) ,
    registration_no    VARCHAR2 (50) ,
    registration_date  DATE ,
    commission_date    DATE ,
    voltage            VARCHAR2 (50) ,
    htsc_number        VARCHAR2 (100) ,
    tariff             VARCHAR2 (50) ,
    sanctioned_quantum VARCHAR2 (25) ,
    total_capacity     VARCHAR2 (25) ,
    is_captive         CHAR (1) ,
    user_name          VARCHAR2 (100) ,
    user_id            VARCHAR2 (100) ,
    password           VARCHAR2 (100) ,
    powerplant_type    VARCHAR2 (50) ,
    powerplant_name    VARCHAR2 (100) ,
    fuel               VARCHAR2 (50) ,
    no_of_generator    NUMBER (2) ,
    generator_type     VARCHAR2 (50) ,
    generator_model    VARCHAR2 (50) ,
    is_rec          CHAR (1) ,
    wind_pass_code  VARCHAR2 (50) ,
    location        VARCHAR2 (500) ,
    address_line    VARCHAR2 (500) ,
    village         VARCHAR2 (100) ,
    taluk_code      VARCHAR2 (50) ,
    city            VARCHAR2 (100) ,
    district_code   VARCHAR2 (50) ,
    state_code      VARCHAR2 (50) ,
    m_org_id        VARCHAR2 (50) NOT NULL ,
    m_substation_id VARCHAR2 (50) NOT NULL ,
    m_feeder_id     VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE m_signup ADD CONSTRAINT m_signup_m_feeder_FK FOREIGN KEY ( m_feeder_id ) REFERENCES m_feeder ( id ) ;
ALTER TABLE m_signup ADD CONSTRAINT m_signup_m_org_FK FOREIGN KEY ( m_org_id ) REFERENCES m_org ( id ) ;
ALTER TABLE m_signup ADD CONSTRAINT m_signup_m_substation_FK FOREIGN KEY ( m_substation_id ) REFERENCES m_substation ( id ) ;




CREATE TABLE m_company_meter
  (
    id                  VARCHAR2 (50) NOT NULL ,
    m_company_service_id        VARCHAR2 (50) NOT NULL ,
    meter_number        VARCHAR2 (100) ,
    meter_make_code     VARCHAR2 (50) ,
    accuracy_class_code VARCHAR2 (50) ,
    is_abtmeter         CHAR (1) ,
    mf                  VARCHAR2 (50)
  ) ;
ALTER TABLE m_company_meter ADD CONSTRAINT m_company_meter_PK PRIMARY KEY ( id ) ;
ALTER TABLE m_company_meter ADD CONSTRAINT m_company_meter_m_service_FK FOREIGN KEY ( m_service_id ) REFERENCES m_company_service ( id ) ;

CREATE TABLE m_company_shareholder
  (
    id                       VARCHAR2 (50) NOT NULL ,
    m_company_id             VARCHAR2 (50) NOT NULL ,
    m_shareholder_company_id VARCHAR2 (50) NOT NULL ,
    "share"                  VARCHAR2 (25) ,
    measure                  VARCHAR2 (50) ,
    enabled                  CHAR (1)
  ) ;
ALTER TABLE m_company_shareholders ADD CONSTRAINT m_company_shareholders_PK PRIMARY KEY ( id ) ;
--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_company_shareholders ADD CONSTRAINT m_company_shareholders_m_company_FK FOREIGN KEY ( m_shareholder_company_id ) REFERENCES m_company ( id ) ;
--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_company_shareholders ADD CONSTRAINT m_company_shareholders_m_company_FKv1 FOREIGN KEY ( m_company_id ) REFERENCES m_company ( id ) ;


CREATE TABLE m_trade_relationships
  (
    id                       VARCHAR2 (50) NOT NULL ,
    quantum                  VARCHAR2 (50) ,
    from_date                DATE ,
    to_date                  DATE ,
    status_code              VARCHAR2 (50) ,
    m_seller_company_id      VARCHAR2 (50) NOT NULL ,
    m_seller_comp_service_id VARCHAR2 (50) NOT NULL ,
    m_buyer_company_id       VARCHAR2 (50) NOT NULL ,
    m_buyer_comp_seller_id   VARCHAR2 (50) NOT NULL
  ) ;
ALTER TABLE m_trade_relationships ADD CONSTRAINT m_trade_relationships_PK PRIMARY KEY ( id ) ;


--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_company_employee ADD CONSTRAINT m_company_employee_m_company_FK FOREIGN KEY ( m_company_id ) REFERENCES m_company ( id ) ;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_company_location ADD CONSTRAINT m_company_location_m_company_FK FOREIGN KEY ( m_company_id ) REFERENCES m_company ( id ) ;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_company_shareholders ADD CONSTRAINT m_company_shareholders_m_company_FK FOREIGN KEY ( m_shareholder_company_id ) REFERENCES m_company ( id ) ;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_company_shareholders ADD CONSTRAINT m_company_shareholders_m_company_FKv1 FOREIGN KEY ( m_company_id ) REFERENCES m_company ( id ) ;

ALTER TABLE m_generator ADD CONSTRAINT m_generator_m_org_FK FOREIGN KEY ( m_org_id ) REFERENCES m_org ( id ) ;

ALTER TABLE m_generator ADD CONSTRAINT m_generator_m_service_FK FOREIGN KEY ( m_service_id ) REFERENCES m_service ( id ) ;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_generator ADD CONSTRAINT m_generator_t_grid_conn_appln_FK FOREIGN KEY ( t_grid_conn_appln_id ) REFERENCES t_grid_conn_appln ( id ) ;

--  ERROR: FK name length exceeds maximum allowed length(30)
ALTER TABLE m_generator_unit ADD CONSTRAINT m_generator_unit_m_generator_FK FOREIGN KEY ( m_generator_id ) REFERENCES m_generator ( id ) ;

ALTER TABLE m_lov_detail ADD CONSTRAINT m_lov_detail_m_lov_header_FK FOREIGN KEY ( m_lov_code ) REFERENCES m_lov_header ( code ) ;

ALTER TABLE m_service ADD CONSTRAINT m_service_m_company_FK FOREIGN KEY ( m_company_id ) REFERENCES m_company ( id ) ;

ALTER TABLE m_service ADD CONSTRAINT m_service_m_feeder_FK FOREIGN KEY ( m_feeder_id ) REFERENCES m_feeder ( id ) ;

ALTER TABLE m_service ADD CONSTRAINT m_service_m_org_FK FOREIGN KEY ( m_org_id ) REFERENCES m_org ( id ) ;

ALTER TABLE m_substation ADD CONSTRAINT m_substation_m_org_FK FOREIGN KEY ( m_org_id ) REFERENCES m_org ( id ) ;

ALTER TABLE m_user ADD CONSTRAINT m_user_m_company_FK FOREIGN KEY ( m_company_id ) REFERENCES m_company ( id ) ;

ALTER TABLE m_user ADD CONSTRAINT m_user_m_org_FK FOREIGN KEY ( m_org_id ) REFERENCES m_org ( id ) ;

--  ERROR: Invalid View v_codes 

/