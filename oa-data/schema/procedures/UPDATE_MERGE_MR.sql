CREATE OR REPLACE PROCEDURE OPENACCESS."UPDATE_MERGE_MR" AS 
BEGIN
for mr in (select distinct id from t_meter_reading_slot where t_meter_reading_hdr_id in
                (select id from t_meter_reading_hdr where MERGE_WITH_NEXT_BILLING = 'Y' and  reading_month='12' and reading_year='2018' )
                and imp_init = '0')
  loop
    update t_meter_reading_slot set IMP_INIT = IMP_FINAL,  EXP_INIT = EXP_FINAL,  IMP_DIFF=0, IMP_UNITS=0, EXP_DIFF=0, EXP_UNITS=0, NET_UNITS=0, MODIFIED_BY='UPDATE_MERGE_MR', MODIFIED_DATE=SYSDATE where ID=mr.id; 
  end loop;
END UPDATE_MERGE_MR;

