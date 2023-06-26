CREATE OR REPLACE PROCEDURE OPENACCESS."NEW_FEEDERLOSS" (MY_SUBSTATIONID VARCHAR2,MY_month VARCHAR2,MY_year VARCHAR2)
AS  
BEGIN  
declare
msd VARCHAR2(200);
tmrhid VARCHAR2(200);
mcmi VARCHAR2(200);
orgid  VARCHAR2(200);
orgname VARCHAR2(200);
MULFACT NUMBER;
msname varchar2(200);
maxmcmi  VARCHAR2(200);
TEG VARCHAR2(200);
S1 VARCHAR2(200);
S1UNITS VARCHAR2(200);
S2 VARCHAR2(200);
S2UNITS VARCHAR2(200);
S3 VARCHAR2(200);
S3UNITS VARCHAR2(200);
S4 VARCHAR2(200);
S4UNITS VARCHAR2(200);
S5 VARCHAR2(200);
S5UNITS VARCHAR2(200);
S_CONS   VARCHAR2(200);
diff  VARCHAR2(200);
lossper VARCHAR2(200);
S_final VARCHAR2(200);
counttmeter NUMBER;
countvcs NUMBER;
extgssid  VARCHAR2(200);extgmonth VARCHAR2(200);extgyear VARCHAR2(200);
 
begin
select distinct M_SUBSTATION_ID into msd  from v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID  ;
select  distinct M_SUBSTATION_NAME into msname  from v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID  ;
select  distinct M_ORG_ID into orgid  from v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID  ;
select  distinct M_ORG_NAME into orgname  from v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID  ;

---select M_SUBSTATION_ID , MONTH , YEAR into extgssid,extgmonth ,extgyear from MY_T_SUBSTATION_LOSS_30092021;
---if (MY_SUBSTATIONID<>extgssid and   MY_month <>extgmonth and MY_year <>extgyear) then  ----now  added
select count(TMHR.m_company_meter_id) into counttmeter from  t_meter_reading_hdr TMHR WHERE TMHR.reading_month  = MY_month   and
TMHR.reading_YEAR  = MY_year AND 
TMHR.m_company_meter_id in(select  m_company_meter_id   from v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID);

select count(m_company_meter_id) into countvcs from  v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID  ;

IF counttmeter = countvcs then   ---added
select sum(TMHR.TOTAL_EXPORT_GEN) INTO  TEG  FROM t_meter_reading_hdr TMHR WHERE TMHR.reading_month  = MY_month   and
TMHR.reading_YEAR  = MY_year AND 
TMHR.m_company_meter_id in(select  m_company_meter_id   from v_company_service where M_SUBSTATION_ID = MY_SUBSTATIONID);

SELECT (EXP_FINAL_S1- EXP_INIT_S1) INTO S1 from uv_tbl_bulk_history_slot
where SSID=MY_SUBSTATIONID and MONTH = MY_month 
AND YEAR = MY_year;
SELECT MF    INTO MULFACT  from uv_tbl_bulk_history_slot
where SSID=MY_SUBSTATIONID and MONTH = MY_month 
AND YEAR = MY_year; 
S1UNITS:= S1 * MULFACT ;
dbms_output.put_line (' S1UNITS '||S1UNITS);

SELECT (EXP_FINAL_S2- EXP_INIT_S2)INTO S2  from uv_tbl_bulk_history_slot 
where SSID=MY_SUBSTATIONID and MONTH = MY_month 
AND YEAR = MY_year;
S2UNITS:= S2 * MULFACT ;
dbms_output.put_line (' S2UNITS '||S2UNITS);
SELECT (EXP_FINAL_S3- EXP_INIT_S3)    INTO S3  from uv_tbl_bulk_history_slot
where SSID=MY_SUBSTATIONID and MONTH = MY_month 
AND YEAR = MY_year;
S3UNITS:= S3* MULFACT ;
dbms_output.put_line (' S3UNITS '||S3UNITS);
SELECT (EXP_FINAL_S4- EXP_INIT_S4)    INTO S4  from uv_tbl_bulk_history_slot
where SSID=MY_SUBSTATIONID and MONTH = MY_month 
AND YEAR = MY_year;
S4UNITS:= S4 * MULFACT;
dbms_output.put_line (' S4UNITS '||S4UNITS);
SELECT (EXP_FINAL_S5- EXP_INIT_S5)    INTO S5  from UV_TBL_BULK_HISTORY_SLOT
where SSID=MY_SUBSTATIONID and MONTH = MY_month 
AND YEAR = MY_year;
S5UNITS:= S5 * MULFACT ;
dbms_output.put_line (' S5UNITS '||S5UNITS);

S_CONS:= S1UNITS + S2UNITS +S3UNITS +S4UNITS +S5UNITS ;
S_final:=  S_CONS/1000;
diff:= TEG - S_final ;
lossper :=(diff/TEG)*100 ;

insert into  MY_T_SUBSTATION_LOSS_30092021(ID,BATCH_KEY,M_ORG_ID,M_SUBSTATION_ID,MONTH,YEAR,LOSS_PERCENT,
BULK_METER_READING,TOTAL_ALL_WEGS,ENABLED,CREATED_DT,M_ORG_NAME,M_SUBSTATION_NAME) 
VALUES(MY_T_SUBSTATION_LOSS_30092021_SEQ.nextval,MY_T_SUBSTATION_LOSS_30092021_SEQ.nextval,orgid,MY_SUBSTATIONID,MY_month,MY_year,round(lossper,3),
S_final,TEG,'Y',sysdate,orgname,msname);
END IF;  ---added
---END IF;  ---now  added
end;
END NEW_FEEDERLOSS;