CREATE OR REPLACE PROCEDURE OPENACCESS."TEST1" (MY_FEEDERID VARCHAR2,MY_monthyear VARCHAR2)
AS  BEGIN  
declare
msd VARCHAR2(200);tmrhid VARCHAR2(200);mcmi VARCHAR2(200);orgid  VARCHAR2(200);orgname VARCHAR2(200);MULFACT NUMBER;
msname varchar2(200);maxmcmi  VARCHAR2(200);TEG VARCHAR2(200);S1 VARCHAR2(200);S1UNITS VARCHAR2(200);
S2 VARCHAR2(200);S2UNITS VARCHAR2(200);S3 VARCHAR2(200);S3UNITS VARCHAR2(200);S4 VARCHAR2(200);S4UNITS VARCHAR2(200);
S5 VARCHAR2(200);S5UNITS VARCHAR2(200);S_CONS   VARCHAR2(200);diff  VARCHAR2(200);lossper VARCHAR2(200);S_final VARCHAR2(200);
counttmeter NUMBER;countvcs NUMBER;extgssid  VARCHAR2(200);extgmonth VARCHAR2(200);extgyear VARCHAR2(200);
FSSID VARCHAR2(200);FCOUNT VARCHAR2(200);MULFACT1 VARCHAR2(200);MULFACT2 VARCHAR2(200);MULFACT3 VARCHAR2(200);
MULFACT4 VARCHAR2(200);MULFACT5 VARCHAR2(200); MF1 VARCHAR2(200);
S11 VARCHAR2(200);S22 VARCHAR2(200);S33 VARCHAR2(200);S44 VARCHAR2(200);S55 VARCHAR2(200);

v_var1 VARCHAR2(200) := 0;
v_var2 VARCHAR2(200) := 0;
v_var3 VARCHAR2(200) := 0; 
v_var4 VARCHAR2(200) := 0;
v_var5 VARCHAR2(200) := 0;
begin
select  M_SUBSTATION_ID into fssid from M_FEEDER   where id=MY_FEEDERID;
select  COUNT(*) INTO FCOUNT from V_COMPANY_SERVICE where M_SUBSTATION_ID =fssid;
select  distinct M_SUBSTATION_NAME into msname  from v_company_service where M_SUBSTATION_ID = fssid  ; ---ok
select  distinct M_ORG_ID into orgid  from v_company_service where M_SUBSTATION_ID = fssid  ;
select  distinct M_ORG_NAME into orgname  from v_company_service where M_SUBSTATION_ID = fssid  ; ---ok
select count(TMHR.m_company_meter_id) into counttmeter from  t_meter_reading_hdr TMHR WHERE 
TMHR.reading_month ||  substr(TMHR.reading_YEAR ,3,2) = MY_monthyear AND 
TMHR.m_company_meter_id in(select  m_company_meter_id   from v_company_service where M_SUBSTATION_ID = fssid ); ---ok
dbms_output.put_line('FCOUNT - '||FCOUNT);
dbms_output.put_line (' COUNTmeter '||counttmeter);
IF counttmeter = FCOUNT then   ---added
select sum(TMHR.TOTAL_EXPORT_GEN) INTO  TEG  FROM t_meter_reading_hdr TMHR WHERE 
TMHR.reading_month||substr(TMHR.reading_YEAR ,3,2)   = MY_monthyear AND 
TMHR.m_company_meter_id in(select  m_company_meter_id   from v_company_service where M_SUBSTATION_ID = fssid );

FOR A IN (SELECT * FROM v_company_service where M_SUBSTATION_ID = fssid )
LOOP
SELECT SUM((EXP_FINAL_S1- EXP_INIT_S1)*mf ) INTO S11 from TANGEDCO.uv_tbl_history_slot@AMRLINK.TNEBNET.ORG  
where serviceno = A."number"  and to_char(final_reading_date,'mmyy') = MY_monthyear ;
v_var1:= v_var1+S11;  --121950000
dbms_output.put_line (' v_var1 '||v_var1);
end  loop;
FOR A IN (SELECT * FROM v_company_service where M_SUBSTATION_ID = fssid )
LOOP
SELECT SUM((EXP_FINAL_S2- EXP_INIT_S2)*mf ) INTO S22 from TANGEDCO.uv_tbl_history_slot@AMRLINK.TNEBNET.ORG  
where serviceno = A."number"  and to_char(final_reading_date,'mmyy') = MY_monthyear ;
v_var2:= v_var2+S22;  --2418000
dbms_output.put_line (' v_var2 '||v_var2);
end  loop;
FOR A IN (SELECT * FROM v_company_service where M_SUBSTATION_ID = fssid )
LOOP
SELECT SUM((EXP_FINAL_S3- EXP_INIT_S3)*mf ) INTO S33 from TANGEDCO.uv_tbl_history_slot@AMRLINK.TNEBNET.ORG  
where serviceno = A."number"  and to_char(final_reading_date,'mmyy') = MY_monthyear ;
v_var3:= v_var3+S33;  --2418000
dbms_output.put_line (' v_var3 '||v_var3);
end  loop;

FOR A IN (SELECT * FROM v_company_service where M_SUBSTATION_ID = fssid )
LOOP
SELECT SUM((EXP_FINAL_S4- EXP_INIT_S4)*mf ) INTO S44 from TANGEDCO.uv_tbl_history_slot@AMRLINK.TNEBNET.ORG  
where serviceno = A."number"  and to_char(final_reading_date,'mmyy') = MY_monthyear ;
v_var4:= v_var4+S44;  --2418000
dbms_output.put_line (' v_var4 '||v_var4);
end  loop;

FOR A IN (SELECT * FROM v_company_service where M_SUBSTATION_ID = fssid )
LOOP
SELECT SUM((EXP_FINAL_S5- EXP_INIT_S5)*mf ) INTO S55 from TANGEDCO.uv_tbl_history_slot@AMRLINK.TNEBNET.ORG  
where serviceno = A."number"  and to_char(final_reading_date,'mmyy') = MY_monthyear ;
v_var5:= v_var5+S55;  --2418000
dbms_output.put_line (' v_var5 '||v_var5);
end  loop;

S_CONS:= v_var1+ v_var2 +v_var3 +v_var4 +v_var5; 
S_final:=  S_CONS/1000;
diff:= TEG - S_final ;
lossper :=(diff/TEG)*100 ;

insert into  MY_T_SUBSTATION_LOSS_30092021(ID,BATCH_KEY,M_ORG_ID,M_SUBSTATION_ID,MONTH,YEAR,LOSS_PERCENT,
BULK_METER_READING,TOTAL_ALL_WEGS,ENABLED,CREATED_DT,M_ORG_NAME,M_SUBSTATION_NAME) 
VALUES(MY_T_SUBSTATION_LOSS_30092021_SEQ.nextval,MY_T_SUBSTATION_LOSS_30092021_SEQ.nextval,orgid,MY_FEEDERID,
substr(MY_monthyear,1,2),substr(MY_monthyear,3,2),round(lossper,3),S_final,TEG,'Y',sysdate,orgname,msname);
END IF; 
end;
END TEST1;