CREATE OR REPLACE PROCEDURE OPENACCESS."FEEDERLOSS" (MY_FEEDERID VARCHAR2,MY_monthyear VARCHAR2)
AS  BEGIN  
declare
orgid  VARCHAR2(200);orgname VARCHAR2(200);MULFACT NUMBER;
msname varchar2(200);TEG VARCHAR2(200);S1 VARCHAR2(200);S1UNITS VARCHAR2(200);
S_CONS   VARCHAR2(200);diff  VARCHAR2(200);lossper VARCHAR2(200);S_final VARCHAR2(200);
counttmeter NUMBER;countvcs NUMBER;extgssid  VARCHAR2(200);extgmonth VARCHAR2(200);extgyear VARCHAR2(200);
FCOUNT VARCHAR2(200);MULFACT1 VARCHAR2(200);MULFACT2 VARCHAR2(200);MULFACT3 VARCHAR2(200);
MULFACT4 VARCHAR2(200);MULFACT5 VARCHAR2(200); MF1 VARCHAR2(200);MSID VARCHAR2(200);fueltype  VARCHAR2(200);
S11 VARCHAR2(200);METERNOoa VARCHAR2(200);
wegidamr VARCHAR2(200);
begin
select  COUNT(*) INTO FCOUNT from V_COMPANY_SERVICE where M_FEEDER_ID =MY_FEEDERID;
select distinct FUEL_TYPE_CODE into fueltype from V_COMPANY_SERVICE where M_FEEDER_ID =MY_FEEDERID;
dbms_output.put_line('FCOUNT - '||FCOUNT);
select  distinct M_SUBSTATION_NAME into msname  from v_company_service where M_FEEDER_ID = MY_FEEDERID  ; ---ok
select  distinct M_SUBSTATION_ID into MSID  from v_company_service where M_FEEDER_ID = MY_FEEDERID  ; ---ok
select  distinct M_ORG_ID into orgid  from v_company_service where M_FEEDER_ID = MY_FEEDERID  ;  ---ok
select  distinct M_ORG_NAME into orgname  from v_company_service where  M_FEEDER_ID = MY_FEEDERID  ; ---ok
select count(TMHR.m_company_meter_id) into counttmeter from  t_meter_reading_hdr TMHR WHERE 
TMHR.reading_month ||  substr(TMHR.reading_YEAR ,3,2) = MY_monthyear AND 
TMHR.m_company_meter_id in(select  m_company_meter_id   from v_company_service where M_FEEDER_ID = MY_FEEDERID  ); ---ok
dbms_output.put_line('FCOUNT - '||FCOUNT);
dbms_output.put_line (' COUNTmeter '||counttmeter);
IF counttmeter = FCOUNT then   ---added
select sum(TMHR.TOTAL_EXPORT_GEN) INTO  TEG  FROM t_meter_reading_hdr TMHR WHERE 
TMHR.reading_month||substr(TMHR.reading_YEAR ,3,2)   = MY_monthyear AND 
TMHR.m_company_meter_id in(select  m_company_meter_id   from v_company_service where M_FEEDER_ID =MY_FEEDERID);  ---ok

select METERNO into METERNOoa from m_feeder where id = MY_FEEDERID;
select wegid into wegidamr  from tangedco.UV_TBL_MASTER_METER@AMRLINK.TNEBNET.ORG   where  METERNO=METERNOoa;
SELECT (SUM((EXP_FINAL_S1- EXP_INIT_S1)*mf ) +   SUM((EXP_FINAL_S2- EXP_INIT_S2)*mf ) +SUM((EXP_FINAL_S3- EXP_INIT_S3)*mf ) +
SUM((EXP_FINAL_S4- EXP_INIT_S4)*mf ) +SUM((EXP_FINAL_S5- EXP_INIT_S5)*mf ) )  INTO S11 
from tangedco.uv_tbl_history_slot@AMRLINK.TNEBNET.ORG   
where  to_char(INITIAL_reading_date,'mmyy') = MY_monthyear and wegid=wegidamr;

S_final:=  S11/1000;
diff:= TEG - S_final ;
lossper :=(diff/TEG)*100 ;

insert into  T_SUBSTATION_LOSS(ID,BATCH_KEY,M_ORG_ID,M_SUBSTATION_ID,MONTH,YEAR,LOSS_PERCENT,
BULK_METER_READING,TOTAL_ALL_WEGS,ENABLED,CREATED_DT,M_ORG_NAME,M_SUBSTATION_NAME,FUEL_TYPE) 
VALUES(MY_T_SUBSTATION_LOSS_30092021_SEQ.nextval,MY_T_SUBSTATION_LOSS_30092021_SEQ.nextval,orgid,MSID,
substr(MY_monthyear,1,2),20||substr(MY_monthyear,3,2),round(lossper,3),S_final,TEG,'Y',sysdate,orgname,msname,fueltype);
END IF; 
end;
END FEEDERLOSS;