CREATE OR REPLACE FUNCTION OPENACCESS."FIND_PREV_MR" 
(
  V_METER_READING_HDR_ID IN VARCHAR2 , FETCH_RECORD_TO_MERGE IN VARCHAR2 DEFAULT 'Y'
) RETURN VARCHAR2 is
v_result varchar(25) := null;
BEGIN
  /**
    GET FIRST RECORD
    if FETCH_RECORD_TO_MERGE = 'Y',
      check if the previous meter reading is labelled for merge.
        return id
    else
      return id
  **/
  for mr in (select mr.id, nvl(mr.merge_with_next_billing,'N') merge_with_next_billing from t_meter_reading_hdr mr join t_meter_reading_hdr curr on curr.m_company_meter_id=mr.m_company_meter_id and curr.id = V_METER_READING_HDR_ID
                  where  mr.final_reading_dt < curr.init_reading_dt
                  order by mr.final_reading_dt desc)
  loop
    if(FETCH_RECORD_TO_MERGE = 'Y') then
      if(mr.merge_with_next_billing = 'Y') then
        v_result:=mr.id;
      end if;
    else
      v_result:=mr.id;
    end if;
    exit;
  end loop;

  RETURN v_result;
END;

