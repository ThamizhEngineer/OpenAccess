CREATE OR REPLACE PACKAGE BODY OPENACCESS.METER_NUMBER_CHANGE AS

  
PROCEDURE IMPORT(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.IMPORT';
BEGIN
  BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.IMPORT','IMPORT','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

    -------------------------------------------------------- LOGIC
    if I_BATCH_KEY is not null then
      METER_NUMBER_CHANGE.METER_CREATION(I_BATCH_KEY , I_MONTH ,I_YEAR ,O_RESULT_CODE , O_RESULT_DESC ) ;
      METER_NUMBER_CHANGE.IDENTIFY_ERRORS(I_BATCH_KEY , I_MONTH ,I_YEAR ,O_RESULT_CODE , O_RESULT_DESC ) ;
      METER_NUMBER_CHANGE.CLEANSE(I_BATCH_KEY , I_MONTH ,I_YEAR ,O_RESULT_CODE , O_RESULT_DESC ) ;
      METER_NUMBER_CHANGE.CREATE_METER_READING(I_BATCH_KEY , I_MONTH ,I_YEAR ,O_RESULT_CODE , O_RESULT_DESC ) ;
      METER_NUMBER_CHANGE.CREATE_TEMP_GENERATION_STATEMENT(I_BATCH_KEY , I_MONTH ,I_YEAR ,O_RESULT_CODE , O_RESULT_DESC ) ;
      METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT(I_BATCH_KEY , I_MONTH ,I_YEAR ,O_RESULT_CODE , O_RESULT_DESC ) ;
    end if;
    --------------------------------------------------------
  exception
  when others then
    v_exception_code := SQLCODE;
    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
    o_result_code := 'FAILURE';
    o_result_desc := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.IMPORT','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION ENDS HERE
END IMPORT;

PROCEDURE METER_CREATION(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.METER_CREATION';
  v_imp_mc_line_cursor sys_refcursor ;
  v_cur_imp_mr_lines IMP_MC_MR_LINES%ROWTYPE;
  v_cur_imp_mr_loop_lines IMP_MC_MR_LINES%ROWTYPE;
  v_imp_count  NUMBER;
  v_loop_cursor sys_refcursor;
  v_service_count  NUMBER;
  v_meter_count  NUMBER;
  v_company_Service_view M_COMPANY_SERVICE%ROWTYPE;
  v_meter_number  VARCHAR2(50);
  meter_date date;
  meter_count  number;
BEGIN
  BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.METER_CREATION','METER_CREATION','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

    -------------------------------------------------------- LOGIC
    UPDATE IMP_MR_LINES SET sys_dt = TO_CHAR(SYSDATE,'yyyy-mm-dd'),  INIT_READING_DT = (READING_YEAR||'-'||READING_MONTH||'-'||'01' ) ,
      FINAL_READING_DT = to_char(LAST_DAY( to_date((READING_YEAR||'-'||READING_MONTH||'-'||'01' ),'YYYY-MM-DD')),'YYYY-MM-DD')
      WHERE IMP_MR_HEADER_ID = I_BATCH_KEY;
    OPEN v_imp_mc_line_cursor for SELECT * FROM IMP_MC_MR_LINES WHERE IMP_MC_MR_HEADER_ID=I_BATCH_KEY and READING_MONTH=I_MONTH and READING_YEAR=I_YEAR;
    LOOP
    FETCH v_imp_mc_line_cursor INTO v_cur_imp_mr_lines;
    EXIT WHEN v_imp_mc_line_cursor%NOTFOUND;
      select count(*) into v_imp_count from IMP_MC_MR_LINES WHERE IMP_MC_MR_HEADER_ID=I_BATCH_KEY and READING_MONTH=I_MONTH and READING_YEAR=I_YEAR AND SERVICE_NO=v_cur_imp_mr_lines.SERVICE_NO;
      --       v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.METER_CREATION','v_imp_count','v_imp_count - '||v_imp_count,'','', sysdate,I_BATCH_KEY);
      if v_imp_count=2 then
        OPEN v_loop_cursor for SELECT * FROM IMP_MC_MR_LINES WHERE IMP_MC_MR_HEADER_ID=I_BATCH_KEY and READING_MONTH=I_MONTH and READING_YEAR=I_YEAR and service_no=v_cur_imp_mr_lines.service_no;
        LOOP
        FETCH v_loop_cursor INTO v_cur_imp_mr_loop_lines;
        EXIT WHEN v_loop_cursor%NOTFOUND;
            
            --Check if service exists
          select count(*) into v_service_count from m_company_service where "number"=v_cur_imp_mr_loop_lines.service_no;
          select * into v_company_Service_view from m_company_Service where "number"=v_cur_imp_mr_loop_lines.service_no;
          if v_service_count=1 then
            select count(*) into meter_count from m_company_meter where meter_number=v_cur_imp_mr_loop_lines.METER_NO;
            if meter_count=1 then
              --             select * into v_company_Service_view from v_company_Service where "number"=v_cur_imp_mr_loop_lines.service_no;
              select CREATED_DATE into meter_date from m_company_meter where meter_number=v_cur_imp_mr_loop_lines.METER_NO;
              if meter_date<sysdate then
                UPDATE m_company_meter SET enabled='N' WHERE METER_NUMBER=v_cur_imp_mr_loop_lines.METER_NO;
              end if;
            elsif  meter_count=0 then
              v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.METER_CREATION','METER-EXISTS','METER_NO - '||v_cur_imp_mr_loop_lines.METER_NO,'','', sysdate,I_BATCH_KEY);
              INSERT INTO M_COMPANY_METER --mF from amr is used here
              (ID, M_COMPANY_SERVICE_ID, METER_NUMBER, METER_MAKE_CODE, ACCURACY_CLASS_CODE, IS_ABTMETER, MF, REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, MODEM_NUMBER, ENABLED, METER_CT1, METER_CT2, METER_CT3, METER_PT1, METER_PT2, METER_PT3, IMPORT_REMARKS, CT_RATIO, PT_RATIO)
              VALUES(M_COMPANY_METER_SEQ.NEXTVAL, v_company_Service_view.ID, v_cur_imp_mr_loop_lines.METER_NO, '', '', '', v_cur_imp_mr_loop_lines.MF, '',v_created_By, sysdate, '', '', '', 'Y', '', '', '', '', '', '', 'METER_NUMBER_CHANGE.CLEANSE', '', '');
                v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.METER_CREATION','METER-CREATED','METER_NO - '||v_cur_imp_mr_loop_lines.METER_NO,'','', sysdate,I_BATCH_KEY);
            end if; 
          end if;
        END LOOP;
      end if;
    END LOOP;
    --------------------------------------------------------
  exception
  when others then
    v_exception_code := SQLCODE;
    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
    o_result_code := 'FAILURE';
    o_result_desc := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CLEANSE','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION ENDS HERE
END METER_CREATION;
  
PROCEDURE CLEANSE(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.CLEANSE';
  v_mri_rec IMP_MC_MR_LINES%ROWTYPE;
  v_prev_read_in_oa boolean;
  v_prev_read_in_imp_table boolean;
  v_older_read_in_oa boolean;
  v_older_read_in_imp_table boolean;
  v_prev_month varchar2(100);
  v_prev_year varchar2(100);
  v_curr_month_first_date date;
  v_count NUMBER; 
  v_mr_id varchar2(100);
  v_mri_id varchar2(100);
BEGIN
  BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CLEANSE','CLEANSE','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

  -------------------------------------------------------- LOGIC
    FOR mril IN (SELECT  line.id, line.IMP_MC_MR_HEADER_ID ,line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR
        FROM IMP_MC_MR_LINES line
        WHERE 1=1
        and line.IMP_MC_MR_HEADER_ID = I_BATCH_KEY
        and status_code = 'CREATED'
        ORDER BY line.SERVICE_NO,line.METER_NO,line.INIT_READING_DT)
    LOOP
    begin --loop exception starts here
      v_prev_read_in_oa := false;  
      v_prev_read_in_imp_table := false;  
      v_older_read_in_oa := false;  
      v_older_read_in_imp_table := false;
      v_prev_month := TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'MM');
      v_prev_year := TO_CHAR(to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')-1,'YYYY');
      v_curr_month_first_date := to_date((mril.READING_YEAR||'-'||mril.READING_MONTH||'-'||'01' ),'YYYY-MM-DD') ;
      SELECT * INTO v_mri_rec FROM IMP_MC_MR_LINES WHERE id = mril.id;
      dbms_output.put_line(' v_mri_rec===='||v_mri_rec.id);
      --        dbms_output.put_line(' v_prev_year===='|| v_prev_year);
      SELECT count( mr.id )INTO v_count FROM T_METER_READING_HDR mr
          JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
          JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
          WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
          AND mr.READING_MONTH = v_prev_month
          AND mr.READING_YEAR = v_prev_year;
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CLEANSE','CLEANSE','v_count - '||v_count,'v_prev_month'||v_prev_month,'v_prev_year'||v_prev_year, sysdate,I_BATCH_KEY);
      if(v_count > 0 ) then v_prev_read_in_oa := true;  end if;
      SELECT count(*) INTO v_count FROM  IMP_MC_MR_LINES where IMP_MC_MR_HEADER_ID = I_BATCH_KEY AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
          AND READING_MONTH = v_prev_month
          AND READING_YEAR = v_prev_month ;
      if(v_count > 0 ) then v_prev_read_in_imp_table := true;  end if;

      SELECT count( mr.id )INTO v_count FROM T_METER_READING_HDR mr
          JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
          JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
          WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
          AND mr.FINAL_READING_DT  < v_curr_month_first_date;
      if(v_count > 0 ) then v_older_read_in_oa := true;     end if;
          
      SELECT count(*) INTO v_count FROM  IMP_MC_MR_LINES mr where IMP_MC_MR_HEADER_ID = I_BATCH_KEY AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
        AND to_date(mr.FINAL_READING_DT,'YYYY-MM-DD')	  < v_curr_month_first_date;
      if(v_count > 0 ) then v_older_read_in_imp_table := true;     end if;
      if(v_prev_read_in_oa)THEN
        SELECT  mr.id INTO v_mr_id FROM T_METER_READING_HDR mr
              JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
              JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
              WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
              AND mr.READING_MONTH = v_prev_month
              AND mr.READING_YEAR = v_prev_year;
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.EXP_INIT_S1  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C1';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S2, v_mri_rec.EXP_INIT_S2  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C2';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S3, v_mri_rec.EXP_INIT_S3  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C3';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S4, v_mri_rec.EXP_INIT_S4  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C4';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S5, v_mri_rec.EXP_INIT_S5  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C5';
          
        select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0), to_char(FINAL_READING_DT+1,'YYYY-MM-DD' )
          INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT , v_mri_rec.INIT_READING_DT
          from T_METER_READING_HDR where id = v_mr_id;
      ELSIF (v_prev_read_in_imp_table) THEN
        SELECT  id INTO  v_mri_id FROM  IMP_MC_MR_LINES where IMP_MC_MR_HEADER_ID = I_BATCH_KEY AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
          AND READING_MONTH = v_prev_month
          AND READING_YEAR = v_prev_year;

        SELECT nvl(IMP_FINAL_S1,0), nvl(IMP_FINAL_S2,0), nvl(IMP_FINAL_S3,0), nvl(IMP_FINAL_S4,0), nvl(IMP_FINAL_S5,0),
                    nvl(EXP_FINAL_S1,0), nvl(EXP_FINAL_S2,0), nvl(EXP_FINAL_S3,0), nvl(EXP_FINAL_S4,0), nvl(EXP_FINAL_S5,0)
              INTO   v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
                    v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5
              FROM   IMP_MC_MR_lines where id = v_mri_id;
          
        select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0) ,to_char(to_date(FINAL_READING_DT,'YYYY-MM-DD')+1,'YYYY-MM-DD' )
          INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  , v_mri_rec.INIT_READING_DT
          from IMP_MC_MR_lines where id = v_mri_id;  
          elsif(v_older_read_in_oa)THEN

        SELECT  id INTO v_mr_id from(
          SELECT mr.id FROM T_METER_READING_HDR mr
          JOIN M_COMPANY_METER meter ON meter.id = mr.M_COMPANY_METER_ID
          JOIN M_COMPANY_SERVICE ser ON meter.M_COMPANY_SERVICE_ID = ser.id
          WHERE ser."number" = mril.SERVICE_NO AND meter.METER_NUMBER = mril.meter_NO
          AND mr.FINAL_READING_DT  <  v_curr_month_first_date
          ORDER BY mr.FINAL_READING_DT desc) WHERE  rownum =1;

        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.EXP_INIT_S1  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C1';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S2, v_mri_rec.EXP_INIT_S2  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C2';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S3, v_mri_rec.EXP_INIT_S3  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C3';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S4, v_mri_rec.EXP_INIT_S4  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C4';
        SELECT nvl(IMP_FINAL,0),nvl(EXP_FINAL,0) INTO v_mri_rec.IMP_INIT_S5, v_mri_rec.EXP_INIT_S5  FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mr_id AND SLOT_CODE='C5';

        select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0), to_char(FINAL_READING_DT+1,'YYYY-MM-DD' )
          INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT  , v_mri_rec.INIT_READING_DT
          from T_METER_READING_HDR where id = v_mr_id;
                
      ELSIF (v_older_read_in_imp_table) THEN
        SELECT  id INTO  v_mri_id from(
          SELECT mr.id FROM  IMP_MC_MR_lines mr where IMP_MC_MR_HEADER_ID = I_BATCH_KEY AND SERVICE_NO = mril.SERVICE_NO AND meter_no = mril.meter_no
          AND to_date(mr.FINAL_READING_DT,'YYYY-MM-DD')  < v_curr_month_first_date
          ORDER BY mr.FINAL_READING_DT DESC) WHERE  rownum = 1;
        SELECT nvl(IMP_FINAL_S1,0), nvl(IMP_FINAL_S2,0), nvl(IMP_FINAL_S3,0), nvl(IMP_FINAL_S4,0), nvl(IMP_FINAL_S5,0),
            nvl(EXP_FINAL_S1,0), nvl(EXP_FINAL_S2,0), nvl(EXP_FINAL_S3,0), nvl(EXP_FINAL_S4,0), nvl(EXP_FINAL_S5,0),
            nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0)
          INTO
            v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
            v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,
            v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT
          FROM  IMP_MR_lines where id = v_mri_id;
      
            select nvl(IMP_RKVAH_FINAL,0),nvl(EXP_RKVAH_FINAL,0),to_char(to_date(FINAL_READING_DT,'YYYY-MM-DD')+1,'YYYY-MM-DD' )
            INTO v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT,    v_mri_rec.INIT_READING_DT
            from T_METER_READING_HDR where id = v_mri_id;
      ELSE
        SELECT 'Y',0,0,0,0,0,0,0,0,0,0,0,0 INTO v_mri_rec.is_first_entry,v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
            v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,v_mri_rec.IMP_RKVAH_INIT, v_mri_rec.EXP_RKVAH_INIT   FROM dual;
      end if;  
                
      UPDATE IMP_MC_MR_lines SET ROW = v_mri_rec WHERE id = v_mri_rec.id;
	  exception
    when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CLEANSE-LOOP','EXCEPTION',v_reason,O_RESULT_DESC,'', sysdate,I_BATCH_KEY);
    end;--loop exception ends here
    END LOOP;
    UPDATE IMP_MC_MR_LINES SET STATUS_CODE = 'CLEANSED', remarks = ''
	    WHERE  IMP_MC_MR_HEADER_ID = I_BATCH_KEY AND STATUS_CODE = 'CREATED';
    --------------------------------------------------------
  exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
        o_result_desc := v_exception_code || ' - ' || v_exception_msg;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CLEANSE','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION ENDS HERE
END CLEANSE;
    
PROCEDURE IDENTIFY_ERRORS(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.IDENTIFY_ERRORS';
  v_mri_rec IMP_MC_MR_LINES%ROWTYPE;

BEGIN
  BEGIN --EXCEPTION HANDLING STARTS HERE
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.IDENTIFY_ERRORS','IDENTIFY_ERRORS','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);
    -------------------------------------------------------- LOGIC
    --if final ReadingDate <> InitialReadingDate and if Day of FinalReadingDate is 01, then update reduce the FinalReadingDate by one
    UPDATE IMP_MC_MR_LINES SET final_READING_DT= to_char(to_date(final_READING_DT,'yyyy-mm-dd') -1,'yyyy-mm-dd')
      WHERE IMP_MC_MR_HEADER_ID =  I_BATCH_KEY
      AND to_date(INIT_READING_DT,'yyyy-mm-dd') != to_date(final_READING_DT,'yyyy-mm-dd')
      AND SUBSTR(final_READING_DT,9,2) = '01';
	  -- dbms_output.put_line('Date stripped');
    /*
    --Ignore any record older than May-2017 - Mark as error with remarks
    UPDATE IMP_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Too old records'
    WHERE IMP_MR_HEADER_ID =  I_BATCH_KEY
    AND (final_READING_DT IS NOT NULL AND TO_NUMBER(SUBSTR(final_READING_DT,6,2)) < 5) ;
    */
    --Mark all records as error with remarks if Final-ReadingDate < InitialReadingDate and not already marked as error with remarks
    UPDATE IMP_MC_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'FinalReadingDate < InitialReadingDate '
      WHERE IMP_MC_MR_HEADER_ID =  I_BATCH_KEY
      AND STATUS_CODE = 'CREATED'
      AND (final_READING_DT IS NOT NULL AND  init_READING_DT IS NOT NULL AND to_date(final_READING_DT,'yyyy-mm-dd') < to_date(init_READING_DT,'yyyy-mm-dd'));
    --If the Service-No doesnt exists in m_company_service, mark as error with remarks
    UPDATE IMP_MC_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Unknown Service-no'
      WHERE IMP_MC_MR_HEADER_ID =  I_BATCH_KEY
      AND STATUS_CODE = 'CREATED'
      AND SERVICE_NO  IN
      (
      SELECT distinct SERVICE_NO  FROM IMP_MC_MR_LINES where IMP_MC_MR_HEADER_ID= I_BATCH_KEY
        minus
      SELECT distinct  "number"  FROM M_COMPANY_SERVICE
      );

    --If   Final values is null, mark as error with remarks
    UPDATE IMP_MC_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Initial/Final values is null'
      WHERE IMP_MC_MR_HEADER_ID =  I_BATCH_KEY
      AND STATUS_CODE = 'CREATED'
      AND ( EXP_FINAL_S1 is null or EXP_FINAL_S2 is null or EXP_FINAL_S3 is null or EXP_FINAL_S4 is null or EXP_FINAL_S5 is null
           or   IMP_FINAL_S1 is null or IMP_FINAL_S2 is null or IMP_FINAL_S3 is null or IMP_FINAL_S4 is null or IMP_FINAL_S5 is null );

    --If the initial or final reading date is null, mark as error with remarks
    UPDATE IMP_MC_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Initial or Final Reading date is null'
      WHERE IMP_MC_MR_HEADER_ID =  I_BATCH_KEY
      AND STATUS_CODE = 'CREATED'
      AND (init_READING_DT IS NULL OR final_READING_DT IS NULL );

	  -- make sure the readings from mri, doesnt exist in MeterReading table
    UPDATE IMP_MC_MR_LINES SET STATUS_CODE= 'ERROR', REMARKS = 'Meter Reading Already Exists'
    WHERE id IN (SELECT mri.id
    FROM IMP_MC_MR_LINES mri
      JOIN M_COMPANY_METER meter ON meter.METER_NUMBER = mri.METER_NO
      JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id
      JOIN T_METER_READING_HDR mrh ON meter.id = mrh.M_COMPANY_METER_ID AND mri.READING_MONTH = mrh.READING_MONTH AND mri.READING_YEAR = mrh.READING_YEAR
    WHERE mri.IMP_MC_MR_HEADER_ID = I_BATCH_KEY
    and mri.status_code = 'CREATED');




    -- as MF from AMR is wrong/inconsistent, MF from OA is used
    for oa_data in (select mrl.id,  mrl.service_no,mrl.meter_no, ser.mf  from imp_mr_lines mrl
                  join v_company_service ser on mrl.service_no=ser."number" and mrl.meter_no=ser.meter_number 
                  where mrl.imp_mr_header_id= I_BATCH_KEY and mrl.status_code = 'CREATED')
    loop 
      update IMP_MC_MR_LINES set mf = oa_data.mf, MODIFIED_BY='admin', MODIFIED_DATE=sysdate 
        where IMP_MC_MR_HEADER_ID= I_BATCH_KEY and service_no= oa_data.service_no and meter_no = oa_data.meter_no; 
    end loop;

    --return 'MIDDLE';

    -- if dual entries entry exists for a meter in same month
    -- Find MRI with multiple entries for same month if dual entries entry exists for a meter in same reading month-year in same batch
    FOR mul_entry IN (SELECT  line.IMP_MC_MR_HEADER_ID ,line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR,  line.mf,count(line.METER_NO) readings_per_meter
        FROM IMP_MC_MR_LINES line
        LEFT JOIN IMP_MC_MR_header head ON line.IMP_MC_MR_HEADER_ID = head.id
        WHERE line.IMP_MC_MR_HEADER_ID = I_BATCH_KEY
        and line.status_code = 'CREATED'
        GROUP BY line.IMP_MC_MR_HEADER_ID, line.SERVICE_NO,line.METER_NO, line.READING_MONTH, line.READING_YEAR, line.mf
        HAVING count(line.METER_NO) >1)
    LOOP
    begin
      v_mri_rec.id := IMP_MR_lines_SEQ.nextval;
      v_mri_rec.IMP_MC_MR_HEADER_ID := mul_entry.IMP_MC_MR_HEADER_ID;
      v_mri_rec.STATUS_CODE := 'CREATED';
          v_mri_rec.SERVICE_NO := mul_entry.SERVICE_NO;
      v_mri_rec.METER_NO := mul_entry.METER_NO;
      v_mri_rec.READING_MONTH := mul_entry.READING_MONTH;
      v_mri_rec.READING_YEAR := mul_entry.READING_YEAR;
      v_mri_rec.mf := mul_entry.mf;
      v_mri_rec.sys_dt := TO_CHAR(SYSDATE,'yyyy-mm-dd');
      v_mri_rec.INIT_READING_DT := (mul_entry.READING_YEAR||'-'||mul_entry.READING_MONTH||'-'||'01' );
      v_mri_rec.FINAL_READING_DT := to_char(LAST_DAY( to_date((mul_entry.READING_YEAR||'-'||mul_entry.READING_MONTH||'-'||'01' ),'YYYY-MM-DD')),'YYYY-MM-DD');

        --find the min values for all INIT values for the given batchNo, serviceNo, meterNo, month, year
      SELECT  min(to_number(nvl(IMP_INIT_S1,0))) IMP_INIT_S1,min(to_number(nvl(IMP_INIT_S2,0))) IMP_INIT_S2,min(to_number(nvl(IMP_INIT_S3,0))) IMP_INIT_S3,min(to_number(nvl(IMP_INIT_S4,0))) IMP_INIT_S4,min(to_number(nvl(IMP_INIT_S5,0))) IMP_INIT_S5,
          min(to_number(nvl(EXP_INIT_S1,0))) EXP_INIT_S1,min(to_number(nvl(EXP_INIT_S2,0))) EXP_INIT_S2,min(to_number(nvl(EXP_INIT_S3,0))) EXP_INIT_S3,min(to_number(nvl(EXP_INIT_S4,0))) EXP_INIT_S4,min(to_number(nvl(EXP_INIT_S5,0))) EXP_INIT_S5,
          min(to_number(nvl(IMP_RKVAH_INIT,0))) IMP_RKVAH_INIT,min(to_number(nvl(EXP_RKVAH_INIT,0))) EXP_RKVAH_INIT,min(to_number(nvl(IMP_KVAH_INIT,0))) IMP_KVAH_INIT,min(to_number(nvl(EXP_KVAH_INIT,0))) EXP_KVAH_INIT
        INTO v_mri_rec.IMP_INIT_S1, v_mri_rec.IMP_INIT_S2, v_mri_rec.IMP_INIT_S3, v_mri_rec.IMP_INIT_S4, v_mri_rec.IMP_INIT_S5,
            v_mri_rec.EXP_INIT_S1, v_mri_rec.EXP_INIT_S2, v_mri_rec.EXP_INIT_S3, v_mri_rec.EXP_INIT_S4, v_mri_rec.EXP_INIT_S5,
            v_mri_rec.IMP_RKVAH_INIT ,v_mri_rec.EXP_RKVAH_INIT,v_mri_rec.IMP_KVAH_INIT,v_mri_rec.EXP_KVAH_INIT
        FROM  IMP_MR_lines lines
        WHERE lines.IMP_MR_HEADER_ID = I_BATCH_KEY AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no
        AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
        group by lines.IMP_MR_HEADER_ID,lines.SERVICE_NO ,lines.METER_NO,lines.READING_MONTH ,lines.READING_YEAR ;


      --find the max values for all final values for the given batchNo, serviceNo, meterNo, month, year
      SELECT  max(to_number(nvl(IMP_FINAL_S1,0))) IMP_FINAL_S1,max(to_number(nvl(IMP_FINAL_S2,0))) IMP_FINAL_S2,max(to_number(nvl(IMP_FINAL_S3,0))) IMP_FINAL_S3,max(to_number(nvl(IMP_FINAL_S4,0))) IMP_FINAL_S4,max(to_number(nvl(IMP_FINAL_S5,0))) IMP_FINAL_S5,
				max(to_number(nvl(EXP_FINAL_S1,0))) EXP_FINAL_S1,max(to_number(nvl(EXP_FINAL_S2,0))) EXP_FINAL_S2,max(to_number(nvl(EXP_FINAL_S3,0))) EXP_FINAL_S3,max(to_number(nvl(EXP_FINAL_S4,0))) EXP_FINAL_S4,max(to_number(nvl(EXP_FINAL_S5,0))) EXP_FINAL_S5,
				max(to_number(nvl(IMP_RKVAH_FINAL,0))) IMP_RKVAH_FINAL,max(to_number(nvl(EXP_RKVAH_FINAL,0))) EXP_RKVAH_FINAL,max(to_number(nvl(IMP_KVAH_FINAL,0))) IMP_KVAH_FINAL,max(to_number(nvl(EXP_KVAH_FINAL,0))) EXP_KVAH_FINAL
			 INTO v_mri_rec.IMP_FINAL_S1, v_mri_rec.IMP_FINAL_S2, v_mri_rec.IMP_FINAL_S3, v_mri_rec.IMP_FINAL_S4, v_mri_rec.IMP_FINAL_S5,
					v_mri_rec.EXP_FINAL_S1, v_mri_rec.EXP_FINAL_S2, v_mri_rec.EXP_FINAL_S3, v_mri_rec.EXP_FINAL_S4, v_mri_rec.EXP_FINAL_S5,
					v_mri_rec.IMP_RKVAH_FINAL ,v_mri_rec.EXP_RKVAH_FINAL,v_mri_rec.IMP_KVAH_FINAL,v_mri_rec.EXP_KVAH_FINAL
        FROM  IMP_MR_lines lines
        WHERE lines.IMP_MR_HEADER_ID = I_BATCH_KEY AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no
        AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR
        group by lines.IMP_MR_HEADER_ID,lines.SERVICE_NO ,lines.METER_NO,lines.READING_MONTH ,lines.READING_YEAR ;

		UPDATE IMP_MR_LINES lines SET STATUS_CODE= 'ERROR', REMARKS = 'Multiple entries in same month', REF_NO = v_mri_rec.id
			WHERE IMP_MR_HEADER_ID =  I_BATCH_KEY AND lines.SERVICE_NO = mul_entry.service_no AND lines.METER_NO = mul_entry.meter_no
			AND lines.READING_MONTH = mul_entry.READING_MONTH AND lines.READING_YEAR = mul_entry.READING_YEAR;
		INSERT INTO IMP_MC_MR_LINES VALUES v_mri_rec;

  exception
  when others then
    v_exception_code := SQLCODE;
    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','MRI_IDENTIFY_ERRORS-LOOP','EXCEPTION',v_reason,v_result,'', sysdate,I_BATCH_KEY);
    UPDATE IMP_MR_LINES SET STATUS_CODE = 'ERROR', remarks = 'Unknown Issues - possiblly data issue'
      WHERE  id = v_mri_rec.id;
  end;
	END LOOP;
  --------------------------------------------------------

  exception
  when others then
    v_exception_code := SQLCODE;
    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
    o_result_code := 'FAILURE';
    o_result_desc := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.IDENTIFY_ERRORS','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION ENDS HERE
END IDENTIFY_ERRORS;
  
PROCEDURE CREATE_METER_READING(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.CREATE_METER_READING';
  v_process_count number;
  v_total_count number;
  v_mrh T_METER_READING_HDR%ROWTYPE;
  v_mr_c1 T_METER_READING_SLOT%ROWTYPE;v_mr_c2 T_METER_READING_SLOT%ROWTYPE;
  v_mr_c3 T_METER_READING_SLOT%ROWTYPE;v_mr_c4 T_METER_READING_SLOT%ROWTYPE;v_mr_c5 T_METER_READING_SLOT%ROWTYPE;
  v_meter_id VARCHAR2(100);
BEGIN
  BEGIN --EXCEPTION STARTS HERE
  v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','CREATE_METER_READING','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);
  -------------------------------------------------------- LOGIC
  select count(id) into v_process_count from IMP_MC_MR_HEADER where status ='PROCESSING';
  -- find the total no. of records to be imported
  select count(import_remarks) into v_total_count from IMP_MC_MR_HEADER where id =I_BATCH_KEY;
  -- set the batch to PROCESSING
  update IMP_MC_MR_HEADER set status='PROCESSING', total_count=v_total_count, MODIFIED_DATE = sysdate where id = I_BATCH_KEY; 

  --loop through clean imported meter entries
  FOR mri IN (SELECT mri.* FROM IMP_MC_MR_LINES mri WHERE mri.IMP_MC_MR_HEADER_ID = I_BATCH_KEY and mri.status_code = 'CLEANSED')
  LOOP
  begin 
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','loop start',mri.meter_no,null,'', sysdate,I_BATCH_KEY);
    -- get meter-id
    SELECT DISTINCT meter.id INTO v_meter_id FROM M_COMPANY_METER  meter
      JOIN M_COMPANY_SERVICE ser ON ser."number" = mri.SERVICE_NO AND meter.M_COMPANY_SERVICE_ID = ser.id WHERE METER_NUMBER = mri.meter_no;
    --setting meter-header record
    v_mrh.id := T_METER_READING_SEQ.nextval;
    v_mrh.M_COMPANY_METER_ID := v_meter_id;
    v_mrh.STATUS_CODE := 'CREATED';
    v_mrh.IMP_BATCH_ID :=  mri.IMP_MC_MR_HEADER_ID;
    v_mrh.sys_dt := v_created_Date;
    v_mrh.mf :=  mri.mf;
    v_mrh.READING_MONTH := mri.READING_MONTH;
    v_mrh.READING_YEAR := mri.READING_YEAR;
    v_mrh.INIT_READING_DT := to_date(mri.INIT_READING_DT,'yyyy-mm-dd');
    v_mrh.FINAL_READING_DT := to_date(mri.FINAL_READING_DT,'yyyy-mm-dd');
    v_mrh.MERGE_WITH_NEXT_BILLING := NVL(mri.MERGE_WITH_NEXT_BILLING,'N');
    v_mrh.EXP_RKVAH_FINAL := mri.EXP_RKVAH_FINAL; v_mrh.EXP_RKVAH_INIT := mri.EXP_RKVAH_INIT;
    v_mrh.IMP_RKVAH_FINAL := mri.IMP_RKVAH_FINAL; v_mrh.IMP_RKVAH_INIT := mri.IMP_RKVAH_INIT;
    v_mrh.EXP_KVAH_FINAL := mri.EXP_KVAH_FINAL; v_mrh.EXP_KVAH_INIT := mri.EXP_KVAH_INIT;
    v_mrh.IMP_KVAH_FINAL := mri.IMP_KVAH_FINAL; v_mrh.IMP_KVAH_INIT := mri.IMP_KVAH_INIT;
    v_mrh.RKVAH_DIFF := nvl(mri.IMP_RKVAH_FINAL,0) - nvl(mri.IMP_RKVAH_INIT, 0);
    v_mrh.RKVAH_UNITS := v_mrh.RKVAH_DIFF*mri.mf;
    v_mrh.KVAH_DIFF := nvl((mri.EXP_KVAH_FINAL - mri.EXP_KVAH_INIT)- (mri.IMP_KVAH_FINAL - mri.IMP_KVAH_INIT), 0);
    --          v_mrh.KVAH_DIFF := nvl(( mri.IMP_KVAH_FINAL - mri.IMP_KVAH_INIT), 0);
    v_mrh.KVAH_UNITS := v_mrh.KVAH_DIFF*mri.mf;
    --v_mrh.TOTAL_IMPORT_GEN := ;
    --v_mrh.TOTAL_EXPORT_GEN := ;
    v_mrh.CREATED_BY := v_created_By;
    v_mrh.CREATED_DATE := v_created_Date;
    INSERT INTO T_METER_READING_HDR VALUES v_mrh;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','insert meter-header record',null,'', sysdate,I_BATCH_KEY);

    --setting meter-reading for slot1
    v_mr_c1.ID := T_METER_READING_SLOT_SEQ.nextval;
    v_mr_c1.T_METER_READING_HDR_ID :=  v_mrh.id;
    v_mr_c1.SLOT_CODE := 'C1' ;
    v_mr_c1.IMP_INIT := nvl(mri.IMP_INIT_S1,0);
    v_mr_c1.IMP_FINAL := nvl(mri.IMP_FINAL_S1,0);
    v_mr_c1.EXP_INIT := nvl(mri.EXP_INIT_S1,0);
    v_mr_c1.EXP_FINAL := nvl(mri.EXP_FINAL_S1,0);
    v_mr_c1.IMP_DIFF := v_mr_c1.IMP_FINAL - v_mr_c1.IMP_INIT ;
    v_mr_c1.EXP_DIFF := v_mr_c1.EXP_FINAL - v_mr_c1.EXP_INIT ;
    v_mr_c1.IMP_UNITS := round(nvl(v_mr_c1.IMP_DIFF * mri.mf,0),0);
    v_mr_c1.EXP_UNITS :=  round(nvl(v_mr_c1.EXP_DIFF * mri.mf,0),0);
    v_mr_c1.net_units := v_mr_c1.EXP_UNITS - v_mr_c1.IMP_UNITS ;
    if(v_mr_c1.net_units <0) THEN v_mr_c1.net_units := 0; END IF;
    v_mr_c1.CREATED_BY := v_created_By;
    v_mr_c1.CREATED_DATE := v_created_Date;
    INSERT INTO T_METER_READING_SLOT VALUES v_mr_c1;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','slot-1',null,'', sysdate,I_BATCH_KEY);


    --setting meter-reading for slot2
    v_mr_c2.ID := T_METER_READING_SLOT_SEQ.nextval;
    v_mr_c2.T_METER_READING_HDR_ID :=  v_mrh.id;
    v_mr_c2.SLOT_CODE := 'C2' ;
    v_mr_c2.IMP_INIT := nvl(mri.IMP_INIT_S2,0);
    v_mr_c2.IMP_FINAL := nvl(mri.IMP_FINAL_S2,0);
    v_mr_c2.EXP_INIT := nvl(mri.EXP_INIT_S2,0);
    v_mr_c2.EXP_FINAL := nvl(mri.EXP_FINAL_S2,0);
    v_mr_c2.IMP_DIFF := v_mr_c2.IMP_FINAL - v_mr_c2.IMP_INIT ;
    v_mr_c2.EXP_DIFF := v_mr_c2.EXP_FINAL - v_mr_c2.EXP_INIT ;
    v_mr_c2.IMP_UNITS := round(nvl(v_mr_c2.IMP_DIFF * mri.mf,0),0);
    v_mr_c2.EXP_UNITS := round(nvl(v_mr_c2.EXP_DIFF * mri.mf,0),0);
    v_mr_c2.net_units := v_mr_c2.EXP_UNITS - v_mr_c2.IMP_UNITS ;
    if(v_mr_c2.net_units <0) THEN v_mr_c2.net_units := 0; END IF;
    v_mr_c2.CREATED_BY := v_created_By;
    v_mr_c2.CREATED_DATE := v_created_Date;
    INSERT INTO T_METER_READING_SLOT VALUES v_mr_c2;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','slot-2',null,'', sysdate,I_BATCH_KEY);


    --setting meter-reading for slot3
    v_mr_c3.ID := T_METER_READING_SLOT_SEQ.nextval;
    v_mr_c3.T_METER_READING_HDR_ID :=  v_mrh.id;
    v_mr_c3.SLOT_CODE := 'C3' ;
    v_mr_c3.IMP_INIT := nvl(mri.IMP_INIT_S3,0);
    v_mr_c3.IMP_FINAL := nvl(mri.IMP_FINAL_S3,0);
    v_mr_c3.EXP_INIT := nvl(mri.EXP_INIT_S3,0);
    v_mr_c3.EXP_FINAL := nvl(mri.EXP_FINAL_S3,0);
    v_mr_c3.IMP_DIFF := v_mr_c3.IMP_FINAL - v_mr_c3.IMP_INIT ;
    v_mr_c3.EXP_DIFF := v_mr_c3.EXP_FINAL - v_mr_c3.EXP_INIT ;
    v_mr_c3.IMP_UNITS := round(nvl(v_mr_c3.IMP_DIFF * mri.mf,0),0);
    v_mr_c3.EXP_UNITS := round(nvl(v_mr_c3.EXP_DIFF * mri.mf,0),0);
    v_mr_c3.net_units := v_mr_c3.EXP_UNITS - v_mr_c3.IMP_UNITS ;
    if(v_mr_c3.net_units <0) THEN v_mr_c3.net_units := 0; END IF;
    v_mr_c3.CREATED_BY := v_created_By;
    v_mr_c3.CREATED_DATE := v_created_Date;
    INSERT INTO T_METER_READING_SLOT VALUES v_mr_c3;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','slot-3',null,'', sysdate,I_BATCH_KEY);

    --setting meter-reading for slot4
    v_mr_c4.ID := T_METER_READING_SLOT_SEQ.nextval;
    v_mr_c4.T_METER_READING_HDR_ID :=  v_mrh.id;
    v_mr_c4.SLOT_CODE := 'C4' ;
    v_mr_c4.IMP_INIT := nvl(mri.IMP_INIT_S4,0);
    v_mr_c4.IMP_FINAL := nvl(mri.IMP_FINAL_S4,0);
    v_mr_c4.EXP_INIT := nvl(mri.EXP_INIT_S4,0);
    v_mr_c4.EXP_FINAL := nvl(mri.EXP_FINAL_S4,0);
    v_mr_c4.IMP_DIFF := v_mr_c4.IMP_FINAL - v_mr_c4.IMP_INIT ;
    v_mr_c4.EXP_DIFF := v_mr_c4.EXP_FINAL - v_mr_c4.EXP_INIT ;
    v_mr_c4.IMP_UNITS := round(nvl(v_mr_c4.IMP_DIFF * mri.mf,0),0);
    v_mr_c4.EXP_UNITS := round(nvl(v_mr_c4.EXP_DIFF * mri.mf,0),0);
    v_mr_c4.net_units := v_mr_c4.EXP_UNITS - v_mr_c4.IMP_UNITS ;
    if(v_mr_c4.net_units <0) THEN v_mr_c4.net_units := 0; END IF;
    v_mr_c4.CREATED_BY := v_created_By;
    v_mr_c4.CREATED_DATE := v_created_Date;
    INSERT INTO T_METER_READING_SLOT VALUES v_mr_c4;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','slot-4',null,'', sysdate,I_BATCH_KEY);


    --setting meter-reading for slot5
    v_mr_c5.ID := T_METER_READING_SLOT_SEQ.nextval;
    v_mr_c5.T_METER_READING_HDR_ID :=  v_mrh.id;
    v_mr_c5.SLOT_CODE := 'C5' ;
    v_mr_c5.IMP_INIT := nvl(mri.IMP_INIT_S5,0);
    v_mr_c5.IMP_FINAL := nvl(mri.IMP_FINAL_S5,0);
    v_mr_c5.EXP_INIT := nvl(mri.EXP_INIT_S5,0);
    v_mr_c5.EXP_FINAL := nvl(mri.EXP_FINAL_S5,0);
    v_mr_c5.IMP_DIFF := v_mr_c5.IMP_FINAL - v_mr_c5.IMP_INIT ;
    v_mr_c5.EXP_DIFF := v_mr_c5.EXP_FINAL - v_mr_c5.EXP_INIT ;
    v_mr_c5.IMP_UNITS := round(nvl(v_mr_c5.IMP_DIFF * mri.mf,0),0);
    v_mr_c5.EXP_UNITS := round(nvl(v_mr_c5.EXP_DIFF * mri.mf,0),0);
    v_mr_c5.net_units := v_mr_c5.EXP_UNITS - v_mr_c5.IMP_UNITS ;
    if(v_mr_c5.net_units <0) THEN v_mr_c5.net_units := 0; END IF;
    v_mr_c5.CREATED_BY := v_created_By;
    v_mr_c5.CREATED_DATE := v_created_Date;
    INSERT INTO T_METER_READING_SLOT VALUES v_mr_c5;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','slot-5',null,'', sysdate,I_BATCH_KEY);


    UPDATE T_METER_READING_HDR SET
        total_import_gen = v_mr_c1.IMP_UNITS + v_mr_c2.IMP_UNITS + v_mr_c3.IMP_UNITS + v_mr_c4.IMP_UNITS + v_mr_c5.IMP_UNITS,
        total_export_gen = v_mr_c1.EXP_UNITS + v_mr_c2.EXP_UNITS + v_mr_c3.EXP_UNITS + v_mr_c4.EXP_UNITS + v_mr_c5.EXP_UNITS,
        net_gen_units = v_mr_c1.NET_UNITS + v_mr_c2.NET_UNITS + v_mr_c3.NET_UNITS + v_mr_c4.NET_UNITS + v_mr_c5.NET_UNITS
        WHERE id = v_mrh.id;
                  
    if mri.DOWNLOADSTATUS='METER CHANGE' then                   	
      UPDATE T_METER_READING_HDR SET IS_METER_CHANGE='Y' WHERE id = v_mrh.id;
    end if;
                  
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','log','updated',null,'', sysdate,I_BATCH_KEY);

    update IMP_MC_MR_LINES set STATUS_CODE = 'IMPORTED' where id = mri.id;
    v_result := 'SUCCESS'; -- resetting result after every loop. as individual loop's result doesnt affect overall procedure's result.
  exception
  when others then
    v_exception_code := SQLCODE;
    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING-LOOP','EXCEPTION','End - '||I_BATCH_KEY,v_result || ' - ' || v_reason,v_created_By, sysdate,I_BATCH_KEY, mri.SERVICE_NO, mri.METER_NO);
    -- dbms_output.put_line('IMPORT_MR - '||v_reason);
  END;
  END LOOP;
  --------------------------------------------------------
  exception
      when others then
        v_exception_code := SQLCODE;
        v_exception_msg := SUBSTR(SQLERRM, 1, 200);
        o_result_code := 'FAILURE';
        o_result_desc := v_exception_code || ' - ' || v_exception_msg;
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_METER_READING','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION ENDS HERE
END CREATE_METER_READING;

PROCEDURE CREATE_TEMP_GENERATION_STATEMENT(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.CREATE_GENERATION_STATEMENT';
  v_process_id  VARCHAR2(50);
  v_records_processed number:=0;
  v_service_number varchar2(50);
BEGIN
  BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_TEMP_GENERATION_STATEMENT','CREATE_GENERATION_STATEMENT','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

   -------------------------------------------------------- LOGIC
    v_process_id := T_PROCESS_GS_SEQ.nextval;
    INSERT INTO T_PROCESS_GS (ID,SYS_DT,STATUS,START_DT,END_DT,REMARKS)
    VALUES (v_process_id,v_created_Date,'PROCESSING',v_created_Date,NULL,NULL) ;
    
    FOR mr IN (SELECT mh.id, m_company_meter_id FROM T_METER_READING_HDR mh  WHERE mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL
                      AND READING_MONTH = I_MONTH AND READING_YEAR = I_YEAR AND mh.IS_METER_CHANGE='Y')
    LOOP
      --        select "number" into v_service_number from v_company_service where m_company_meter_id=mr.m_company_meter_id; 
      --        BANKING_BALANCE.open_balance(v_service_number,I_MONTH,I_YEAR,O_RESULT_CODE,O_RESULT_DESC);   
      v_result:=METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT(I_BATCH_KEY,v_process_id,mr.id);
      if (v_result='SUCCESS') then
          v_records_processed := v_records_processed +1;
      end if;
    END LOOP;
    --------------------------------------------------------

  exception
  when others then
    v_exception_code := SQLCODE;
    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
    o_result_code := 'FAILURE';
    o_result_desc := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_TEMP_GENERATION_STATEMENT','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION ENDS HERE
END CREATE_TEMP_GENERATION_STATEMENT;
  

FUNCTION PROCESS_TEMP_GENERATION_STATEMENT( I_BATCH_KEY in varchar2, v_gs_process_id in varchar2, v_mr_id in varchar2) RETURN VARCHAR2 AS
  v_stage varchar2(50);
  v_process_id  VARCHAR2(50); 
  v_gen_count  NUMBER:=0;
  v_mrh T_METER_READING_HDR%ROWTYPE;
  v_mr_c1 T_METER_READING_SLOT%ROWTYPE;
  v_mr_c2 T_METER_READING_SLOT%ROWTYPE;
  v_mr_c3 T_METER_READING_SLOT%ROWTYPE;
  v_mr_c4 T_METER_READING_SLOT%ROWTYPE;
  v_mr_c5 T_METER_READING_SLOT%ROWTYPE;
  v_gs TMP_GEN_STMT%ROWTYPE;
  v_gs_c1 TMP_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c2 TMP_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c3 TMP_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c4 TMP_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c5 TMP_GEN_STMT_SLOT%ROWTYPE;
  v_no_records BOOLEAN:=TRUE;
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'admin';
  v_status varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='';
  v_plant_class_code VARCHAR2(200);
  v_plant_class_desc VARCHAR2(200);
  v_gen_month  VARCHAR2(50); 
  v_gen_year  VARCHAR2(50); 
  v_gen_comp_servi_id VARCHAR2(200);
  tariff_rates VARCHAR2(200);
  v_log_result varchar(300):='SUCCESS';
  v_imported BOOLEAN;

  v_gen_voltage_desc VARCHAR2(50); 
  v_gen_org_code VARCHAR2(50); 
  v_gen_iscaptive VARCHAR2(50); 
  v_gen_isstb VARCHAR2(50);
  v_gen_isstb_count NUMBER; 
  v_gen_ssid VARCHAR2(50) ;
  v_gen_ssname  VARCHAR2(50);
  v_gen_sstype  VARCHAR2(50);
  v_gen_ffid   VARCHAR2(50);
  v_gen_ffname VARCHAR2(50);
  v_tariff_net_amount VARCHAR2(150);  
  v_net_payable VARCHAR2(150);
  v_total_charged_amount  VARCHAR2(150);
  v_flow_type_code  VARCHAR2(150);

  v_prev_mr_id varchar2(50);
  v_prev_mr T_METER_READING_HDR%ROWTYPE;
  v_prev_mr_c1 T_METER_READING_SLOT%ROWTYPE;
  v_prev_mr_c2 T_METER_READING_SLOT%ROWTYPE;
  v_prev_mr_c3 T_METER_READING_SLOT%ROWTYPE;
  v_prev_mr_c4 T_METER_READING_SLOT%ROWTYPE;
  v_prev_mr_c5 T_METER_READING_SLOT%ROWTYPE;
BEGIN
	BEGIN	
    v_stage := '-1-start';
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT','Start',v_reason,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);

		FOR meter IN (SELECT mh.M_COMPANY_METER_ID, READING_MONTH, READING_YEAR,
						nvl(bg.C1,0) bc1, nvl(bg.C2,0) bc2, nvl(bg.C3,0) bc3,nvl(bg.C4,0) bc4,nvl(bg.C5,0) bc5,  
						cs.M_ORG_ID, c.ID M_COMPANY_ID,cm.M_COMPANY_SERVICE_ID, c.NAME DISP_COMPANY_NAME, cs."number" DISP_SERVICE_NUMBER, cs.VOLTAGE_CODE INJECTING_VOLTAGE_CODE, o.NAME DISP_ORG_NAME,cs.flow_type_code,
						pp.FUEL_TYPE_CODE DISP_FUEL_TYPE_CODE, fuel_codes.VALUE_DESC  DISP_FUEL_TYPE_NAME,pp.COMMISSION_DATE COMMISSION_DATE,cs.IS_REC IS_REC,cs.TOTAL_CAPACITY TOTAL_CAPACITY
						FROM T_METER_READING_HDR mh 
						INNER JOIN M_COMPANY_METER cm ON mh.M_COMPANY_METER_ID = cm.ID
						INNER JOIN M_COMPANY_SERVICE cs ON cm.M_COMPANY_SERVICE_ID = cs.id
						INNER JOIN M_COMPANY c ON cs.M_COMPANY_ID = c.id
						INNER JOIN M_ORG o ON cs.M_ORG_ID = o.id
            LEFT JOIN T_BANKING_BALANCE bg ON bg.BANKING_SERVICE_ID = cs.BANKING_SERVICE_ID and to_number(bg.month)= to_number(mh.READING_MONTH) and to_number(bg.year)= to_number(mh.READING_YEAR)
            LEFT JOIN M_POWERPLANT pp ON cs.id = pp.M_SERVICE_ID
            LEFT JOIN V_CODES fuel_codes ON pp.FUEL_TYPE_CODE = fuel_codes.VALUE_CODE AND fuel_codes.list_code = 'FUEL_TYPE_CODE'				
						WHERE  mh.M_GEN_STMT_ID IS NULL AND mh.GS_BATCH_ID IS NULL AND mh.IS_METER_CHANGE='Y' and mh.id = v_mr_id)
		LOOP
		BEGIN

      v_stage := '-2-meter-loop-start';

      -- as there is meter-readings to process, we have to set the flag accordingly and start the generation process
      if(v_no_records) THEN
        v_no_records := FALSE;
      END IF;
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT','Start',meter.M_COMPANY_SERVICE_ID,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);

      --dbms_output.put_line('meter.M_COMPANY_METER_ID - '||meter.M_COMPANY_METER_ID);
      --dbms_output.put_line('meter.M_COMPANY_SERVICE_ID - '||meter.M_COMPANY_SERVICE_ID);
      --dbms_output.put_line('meter.READING_Year - '||meter.READING_Year);
      --dbms_output.put_line('meter.READING_MONTH - '||meter.READING_MONTH);

      -- to find generator capacity
      SELECT count(*) INTO v_gen_count FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;
      v_stage := '-3-find generator capacity';
      --dbms_output.put_line('v_gen_count- '||v_gen_count);

      if(v_gen_count =0) THEN
        -- setup issue - generator not configured properly for this service
        v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT','ISSUE','Setup Issue - '||v_process_id||' - No generator configured for service-id-->'||meter.M_COMPANY_SERVICE_ID,null,v_created_By, v_created_Date);
        CONTINUE;
      ELSE	
        -- TODO - Sum() should be revisited
        v_stage := '-4-Sum() should be revisited';
        --SELECT sum(nvl(CAPACITY,0))  INTO v_gs.MACHINE_CAPACITY FROM M_GENERATOR g JOIN M_POWERPLANT p ON g.M_POWERPLANT_ID = p.ID WHERE p.M_SERVICE_ID = meter.M_COMPANY_SERVICE_ID ;
        v_gs.MACHINE_CAPACITY:=meter.TOTAL_CAPACITY;
      END IF;
      --dbms_output.put_line('v_gs.MACHINE_CAPACITY - '||v_gs.MACHINE_CAPACITY);
      v_stage := '-5-Getmr header';
      SELECT * INTO v_mrh FROM T_METER_READING_HDR WHERE  M_COMPANY_METER_ID = meter.M_COMPANY_METER_ID AND READING_MONTH = meter.READING_MONTH AND READING_Year = meter.READING_Year;
      --dbms_output.put_line('v_mrh.id - '||v_mrh.id);
      v_stage := '-6-';
      SELECT * INTO v_mr_c1 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C1';
      SELECT * INTO v_mr_c2 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C2';
      SELECT * INTO v_mr_c3 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C3';
      SELECT * INTO v_mr_c4 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C4';
      SELECT * INTO v_mr_c5 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_mrh.id AND SLOT_CODE = 'C5';
      --check if there previous meter-reading is to be included in this gen-stmt
      v_prev_mr_id := FIND_PREV_MR(v_mrh.id,'Y'); 
      if(v_prev_mr_id is not null )then
        -- previous meter reading is to be merged with the current meter reading
        SELECT * INTO v_prev_mr FROM T_METER_READING_HDR WHERE id =  v_prev_mr_id;
        SELECT * INTO v_prev_mr_c1 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C1';
        SELECT * INTO v_prev_mr_c2 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C2';
        SELECT * INTO v_prev_mr_c3 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C3';
        SELECT * INTO v_prev_mr_c4 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C4';
        SELECT * INTO v_prev_mr_c5 FROM T_METER_READING_SLOT WHERE T_METER_READING_HDR_ID = v_prev_mr_id AND SLOT_CODE = 'C5';

        ----- modify current meter reading record according to the same ----

        --modify header
        v_mrh.INIT_READING_DT := v_prev_mr.INIT_READING_DT;
        v_mrh.EXP_RKVAH_INIT := v_prev_mr.EXP_RKVAH_INIT;
        v_mrh.IMP_RKVAH_INIT := v_prev_mr.IMP_RKVAH_INIT;
        v_mrh.EXP_KVAH_INIT := v_prev_mr.EXP_KVAH_INIT;
        v_mrh.IMP_KVAH_INIT := v_prev_mr.IMP_KVAH_INIT;
        v_mrh.RKVAH_DIFF := nvl(v_mrh.IMP_RKVAH_FINAL,0) - nvl(v_mrh.IMP_RKVAH_INIT, 0);
        v_mrh.RKVAH_UNITS := v_mrh.RKVAH_DIFF*v_mrh.mf;
        v_mrh.KVAH_DIFF := nvl((v_mrh.EXP_KVAH_FINAL - v_mrh.EXP_KVAH_INIT)- (v_mrh.IMP_KVAH_FINAL - v_mrh.IMP_KVAH_INIT), 0);
        v_mrh.KVAH_UNITS := v_mrh.KVAH_DIFF*v_mrh.mf;

        -- modify slot data

        --slot1 changes
        v_mr_c1.IMP_INIT := nvl(v_prev_mr_c1.IMP_INIT,0); v_mr_c1.EXP_INIT := nvl(v_prev_mr_c1.EXP_INIT,0);
        v_mr_c1.IMP_DIFF := v_mr_c1.IMP_FINAL - v_mr_c1.IMP_INIT ;v_mr_c1.EXP_DIFF := v_mr_c1.EXP_FINAL - v_mr_c1.EXP_INIT ;
        v_mr_c1.IMP_UNITS := v_mr_c1.IMP_DIFF * v_mrh.mf; v_mr_c1.EXP_UNITS := v_mr_c1.EXP_DIFF * v_mrh.mf;
        v_mr_c1.net_units := ROUND(v_mr_c1.EXP_UNITS - v_mr_c1.IMP_UNITS,0) ; if(to_number(v_mr_c1.net_units) <0) THEN v_mr_c1.net_units := 0; END IF;

        --slot2 changes
        v_mr_c2.IMP_INIT := nvl(v_prev_mr_c2.IMP_INIT,0); v_mr_c2.EXP_INIT := nvl(v_prev_mr_c2.EXP_INIT,0);
        v_mr_c2.IMP_DIFF := v_mr_c2.IMP_FINAL - v_mr_c2.IMP_INIT ;v_mr_c2.EXP_DIFF := v_mr_c2.EXP_FINAL - v_mr_c2.EXP_INIT ;
        v_mr_c2.IMP_UNITS := v_mr_c2.IMP_DIFF * v_mrh.mf; v_mr_c2.EXP_UNITS := v_mr_c2.EXP_DIFF * v_mrh.mf;
        v_mr_c2.net_units := ROUND(v_mr_c2.EXP_UNITS - v_mr_c2.IMP_UNITS,0) ; if(to_number(v_mr_c2.net_units) <0) THEN v_mr_c2.net_units := 0; END IF;

        --slot3 changes
        v_mr_c3.IMP_INIT := nvl(v_prev_mr_c3.IMP_INIT,0); v_mr_c3.EXP_INIT := nvl(v_prev_mr_c3.EXP_INIT,0);
        v_mr_c3.IMP_DIFF := v_mr_c3.IMP_FINAL - v_mr_c3.IMP_INIT ;v_mr_c3.EXP_DIFF := v_mr_c3.EXP_FINAL - v_mr_c3.EXP_INIT ;
        v_mr_c3.IMP_UNITS := v_mr_c3.IMP_DIFF * v_mrh.mf; v_mr_c3.EXP_UNITS := v_mr_c3.EXP_DIFF * v_mrh.mf;
        v_mr_c3.net_units := ROUND(v_mr_c3.EXP_UNITS - v_mr_c3.IMP_UNITS,0) ; if(to_number(v_mr_c3.net_units) <0) THEN v_mr_c3.net_units := 0; END IF;  

        --slot4 changes
        v_mr_c4.IMP_INIT := nvl(v_prev_mr_c4.IMP_INIT,0); v_mr_c4.EXP_INIT := nvl(v_prev_mr_c4.EXP_INIT,0);
        v_mr_c4.IMP_DIFF := v_mr_c4.IMP_FINAL - v_mr_c4.IMP_INIT ;v_mr_c4.EXP_DIFF := v_mr_c4.EXP_FINAL - v_mr_c4.EXP_INIT ;
        v_mr_c4.IMP_UNITS := v_mr_c4.IMP_DIFF * v_mrh.mf; v_mr_c4.EXP_UNITS := v_mr_c4.EXP_DIFF * v_mrh.mf;
        v_mr_c4.net_units := ROUND(v_mr_c4.EXP_UNITS - v_mr_c4.IMP_UNITS,0) ; if(to_number(v_mr_c4.net_units) <0) THEN v_mr_c4.net_units := 0; END IF;

        --slot5 changes
        v_mr_c5.IMP_INIT := nvl(v_prev_mr_c5.IMP_INIT,0); v_mr_c5.EXP_INIT := nvl(v_prev_mr_c5.EXP_INIT,0);
        v_mr_c5.IMP_DIFF := v_mr_c5.IMP_FINAL - v_mr_c5.IMP_INIT ;v_mr_c5.EXP_DIFF := v_mr_c5.EXP_FINAL - v_mr_c5.EXP_INIT ;
        v_mr_c5.IMP_UNITS := v_mr_c5.IMP_DIFF * v_mrh.mf; v_mr_c5.EXP_UNITS := v_mr_c5.EXP_DIFF * v_mrh.mf;
        v_mr_c5.net_units := ROUND(v_mr_c5.EXP_UNITS - v_mr_c5.IMP_UNITS,0) ; if(to_number(v_mr_c5.net_units) <0) THEN v_mr_c5.net_units := 0; END IF;  

        -- update summary info in header 
        v_mrh.total_import_gen := v_mr_c1.IMP_UNITS + v_mr_c2.IMP_UNITS + v_mr_c3.IMP_UNITS + v_mr_c4.IMP_UNITS + v_mr_c5.IMP_UNITS;
        v_mrh.total_export_gen := v_mr_c1.EXP_UNITS + v_mr_c2.EXP_UNITS + v_mr_c3.EXP_UNITS + v_mr_c4.EXP_UNITS + v_mr_c5.EXP_UNITS;
        v_mrh.net_gen_units := v_mr_c1.NET_UNITS + v_mr_c2.NET_UNITS + v_mr_c3.NET_UNITS + v_mr_c4.NET_UNITS + v_mr_c5.NET_UNITS;

        ----- modify current meter reading record - end ----
      end if;

      --dbms_output.put_line('all slots- '||v_mrh.id);
      -- intialise PK, FKs for GenStmt and GenSlots
      v_gs.id := T_GEN_STMT_SEQ.nextval;
      v_gs_c1.id := T_GEN_STMT_SEQ.nextval;v_gs_c1.TMP_GEN_STMT_ID := v_gs.id;
      v_gs_c2.id := T_GEN_STMT_SEQ.nextval;v_gs_c2.TMP_GEN_STMT_ID := v_gs.id;
      v_gs_c3.id := T_GEN_STMT_SEQ.nextval;v_gs_c3.TMP_GEN_STMT_ID := v_gs.id;
      v_gs_c4.id := T_GEN_STMT_SEQ.nextval;v_gs_c4.TMP_GEN_STMT_ID := v_gs.id;
      v_gs_c5.id := T_GEN_STMT_SEQ.nextval;v_gs_c5.TMP_GEN_STMT_ID := v_gs.id;

      -- set values in gen stmt
      v_gs.STATUS_CODE := 'CREATED';
      v_gs.M_COMPANY_METER_ID := v_mrh.M_COMPANY_METER_ID ;
      v_gs.T_MR_IDS := v_mrh.M_COMPANY_METER_ID ;
      v_gs.REF_NUMBER := v_gs_process_id;
      --v_gs.gs_batch_id := v_process_id;
      v_gs.mf := v_mrh.mf;
      v_gs.STMT_GEN_DATE := v_created_Date ;
      v_gs.STMT_MONTH :=  v_mrh.READING_MONTH;
      v_gs.STMT_YEAR := v_mrh.READING_YEAR;
      v_gs.INIT_STMT_DT := v_mrh.INIT_READING_DT;
      v_gs.FINAL_STMT_DT := v_mrh.FINAL_READING_DT;
      -- RKVAH_INIT, RKVAH_FINAL, KVAH_INIT, KVAH_FINAL are not relevant currently
      --RKVAH_INIT, RKVAH_FINAL, KVAH_INIT, KVAH_FINAL - IMPORT VALUES 
      v_gs.KVAH_INIT := v_mrh.IMP_KVAH_INIT;
      v_gs.KVAH_FINAL := v_mrh.IMP_KVAH_FINAL;
      v_gs.RKVAH_INIT := v_mrh.IMP_RKVAH_INIT;
      v_gs.RKVAH_FINAL := v_mrh.IMP_RKVAH_FINAL;
      v_gs.KVAH_DIFF := v_mrh.KVAH_DIFF;
      v_gs.KVAH_UNITS := v_mrh.KVAH_UNITS;
      v_gs.RKVAH_DIFF := v_mrh.RKVAH_DIFF;
      v_gs.RKVAH_UNITS := v_mrh.RKVAH_UNITS;
      v_mr_c1.EXP_UNITS := round(v_mr_c1.EXP_UNITS,0); v_mr_c1.IMP_UNITS := round(v_mr_c1.IMP_UNITS,0);v_mr_c1.NET_UNITS := round(v_mr_c1.NET_UNITS,0);
      v_mr_c2.EXP_UNITS := round(v_mr_c2.EXP_UNITS,0); v_mr_c2.IMP_UNITS := round(v_mr_c2.IMP_UNITS,0);v_mr_c2.NET_UNITS := round(v_mr_c2.NET_UNITS,0);
      v_mr_c3.EXP_UNITS := round(v_mr_c3.EXP_UNITS,0); v_mr_c3.IMP_UNITS := round(v_mr_c3.IMP_UNITS,0);v_mr_c3.NET_UNITS := round(v_mr_c3.NET_UNITS,0);
      v_mr_c4.EXP_UNITS := round(v_mr_c4.EXP_UNITS,0); v_mr_c4.IMP_UNITS := round(v_mr_c4.IMP_UNITS,0);v_mr_c4.NET_UNITS := round(v_mr_c4.NET_UNITS,0);
      v_mr_c5.EXP_UNITS := round(v_mr_c5.EXP_UNITS,0); v_mr_c5.IMP_UNITS := round(v_mr_c5.IMP_UNITS,0);v_mr_c5.NET_UNITS := round(v_mr_c5.NET_UNITS,0);
  
      v_gs.TOTAL_EXPORT_GEN := v_mr_c1.EXP_UNITS+v_mr_c2.EXP_UNITS+v_mr_c3.EXP_UNITS+v_mr_c4.EXP_UNITS+v_mr_c5.EXP_UNITS;
      v_gs.TOTAL_IMPORT_GEN := v_mr_c1.IMP_UNITS+v_mr_c2.IMP_UNITS+v_mr_c3.IMP_UNITS+v_mr_c4.IMP_UNITS+v_mr_c5.IMP_UNITS;
      v_gs.M_ORG_ID := meter.M_ORG_ID;
      v_gs.M_COMPANY_ID := meter.M_COMPANY_ID;
      v_gs.M_COMPANY_SERVICE_ID := meter.M_COMPANY_SERVICE_ID;
      v_gs.DISP_COMPANY_NAME := meter.DISP_COMPANY_NAME;
      v_gs.DISP_SERVICE_NUMBER := meter.DISP_SERVICE_NUMBER;
      v_gs.INJECTING_VOLTAGE_CODE := meter.INJECTING_VOLTAGE_CODE;
      v_gs.DISP_ORG_NAME := meter.DISP_ORG_NAME; 
      v_gs.FLOW_TYPE_CODE := meter.FLOW_TYPE_CODE; 
      v_gs.net_generation := v_mr_c1.NET_UNITS+v_mr_c2.NET_UNITS+v_mr_c3.NET_UNITS+v_mr_c4.NET_UNITS+v_mr_c5.NET_UNITS;
      v_gs.C1 := v_mr_c1.NET_UNITS;
      v_gs.C2 := v_mr_c2.NET_UNITS;
      v_gs.C3 := v_mr_c3.NET_UNITS;
      v_gs.C4 := v_mr_c4.NET_UNITS;
      v_gs.C5 := v_mr_c5.NET_UNITS;
      v_gs.disp_fuel_type_code := meter.disp_fuel_type_code;
      v_gs.disp_fuel_type_name := meter.disp_fuel_type_name;
      v_gs.COMMISSION_DATE:= meter.COMMISSION_DATE;
      v_gs.IS_REC := meter.IS_REC;
      v_gs.created_by := v_created_By;
      v_gs.created_dt := SYSDATE;
      v_gs.enabled := 'Y';
      v_gs.batch_key:=I_BATCH_KEY;
      v_gs.TOTAL_CHARGED_AMOUNT := 0; --actual value calculated by calc_gs_charges() 
      v_gs.NET_PAYABLE := 0; --actual value calculated by calc_gs_charges() 
      if(to_number(v_gs.TOTAL_EXPORT_GEN)*0.01 >= to_number(v_gs.RKVAH_UNITS)) THEN
        v_gs.penalty_rate := '0.25';
      ELSE
        v_gs.penalty_rate := '0.50';
      END IF;
              v_stage := '-7-';


      --dbms_output.put_line('gen stmt - values set');

      SELECT v_mr_c1.SLOT_CODE,v_mr_c1.IMP_INIT, v_mr_c1.IMP_FINAL, v_mr_c1.IMP_DIFF, round(v_mr_c1.IMP_UNITS,0),v_mr_c1.EXP_INIT, v_mr_c1.EXP_FINAL, v_mr_c1.EXP_DIFF, round(v_mr_c1.EXP_UNITS,0), meter.bc1, round(v_mr_c1.NET_UNITS,0), 'Y',sysdate
      INTO  v_gs_c1.SLOT_CODE,v_gs_c1.IMP_INIT, v_gs_c1.IMP_FINAL, v_gs_c1.IMP_DIFF, v_gs_c1.IMP_UNITS, v_gs_c1.EXP_INIT, v_gs_c1.EXP_FINAL, v_gs_c1.EXP_DIFF, v_gs_c1.EXP_UNITS, v_gs_c1.BANKED_BALANCE, v_gs_c1.NET_UNITS, v_gs_c1.enabled, v_gs_c1.created_Date
      FROM dual;

      SELECT  v_mr_c2.SLOT_CODE,v_mr_c2.IMP_INIT, v_mr_c2.IMP_FINAL, v_mr_c2.IMP_DIFF, round(v_mr_c2.IMP_UNITS,0), v_mr_c2.EXP_INIT, v_mr_c2.EXP_FINAL, v_mr_c2.EXP_DIFF, round(v_mr_c2.EXP_UNITS,0), meter.bc2, round(v_mr_c2.NET_UNITS,0), 'Y',sysdate
      INTO  v_gs_c2.SLOT_CODE,v_gs_c2.IMP_INIT, v_gs_c2.IMP_FINAL, v_gs_c2.IMP_DIFF, v_gs_c2.IMP_UNITS, v_gs_c2.EXP_INIT, v_gs_c2.EXP_FINAL, v_gs_c2.EXP_DIFF, v_gs_c2.EXP_UNITS, v_gs_c2.BANKED_BALANCE, v_gs_c2.NET_UNITS, v_gs_c2.enabled, v_gs_c2.created_Date
      FROM dual;

      SELECT  v_mr_c3.SLOT_CODE,v_mr_c3.IMP_INIT, v_mr_c3.IMP_FINAL, v_mr_c3.IMP_DIFF, round(v_mr_c3.IMP_UNITS,0), v_mr_c3.EXP_INIT, v_mr_c3.EXP_FINAL, v_mr_c3.EXP_DIFF, round(v_mr_c3.EXP_UNITS,0), meter.bc3, round(v_mr_c3.NET_UNITS,0), 'Y',sysdate
      INTO  v_gs_c3.SLOT_CODE,v_gs_c3.IMP_INIT, v_gs_c3.IMP_FINAL, v_gs_c3.IMP_DIFF, v_gs_c3.IMP_UNITS, v_gs_c3.EXP_INIT, v_gs_c3.EXP_FINAL, v_gs_c3.EXP_DIFF, v_gs_c3.EXP_UNITS, v_gs_c3.BANKED_BALANCE, v_gs_c3.NET_UNITS, v_gs_c3.enabled, v_gs_c3.created_Date
      FROM dual;

      SELECT  v_mr_c4.SLOT_CODE,v_mr_c4.IMP_INIT, v_mr_c4.IMP_FINAL, v_mr_c4.IMP_DIFF, round(v_mr_c4.IMP_UNITS,0), v_mr_c4.EXP_INIT, v_mr_c4.EXP_FINAL, v_mr_c4.EXP_DIFF, round(v_mr_c4.EXP_UNITS,0), meter.bc4, round(v_mr_c4.NET_UNITS,0), 'Y',sysdate
      INTO  v_gs_c4.SLOT_CODE,v_gs_c4.IMP_INIT, v_gs_c4.IMP_FINAL, v_gs_c4.IMP_DIFF, v_gs_c4.IMP_UNITS, v_gs_c4.EXP_INIT, v_gs_c4.EXP_FINAL, v_gs_c4.EXP_DIFF, v_gs_c4.EXP_UNITS, v_gs_c4.BANKED_BALANCE, v_gs_c4.NET_UNITS, v_gs_c4.enabled, v_gs_c4.created_Date
      FROM dual;

      SELECT  v_mr_c5.SLOT_CODE,v_mr_c5.IMP_INIT, v_mr_c5.IMP_FINAL, v_mr_c5.IMP_DIFF, round(v_mr_c5.IMP_UNITS,0), v_mr_c5.EXP_INIT, v_mr_c5.EXP_FINAL, v_mr_c5.EXP_DIFF, round(v_mr_c5.EXP_UNITS,0), meter.bc5, round(v_mr_c5.NET_UNITS,0), 'Y',sysdate
      INTO  v_gs_c5.SLOT_CODE,v_gs_c5.IMP_INIT, v_gs_c5.IMP_FINAL, v_gs_c5.IMP_DIFF, v_gs_c5.IMP_UNITS, v_gs_c5.EXP_INIT, v_gs_c5.EXP_FINAL, v_gs_c5.EXP_DIFF, v_gs_c5.EXP_UNITS, v_gs_c5.BANKED_BALANCE, v_gs_c5.NET_UNITS, v_gs_c5.enabled, v_gs_c1.created_Date
      FROM dual;


      --dbms_output.put_line('gen stmt slots - insert');

      INSERT INTO  TMP_GEN_STMT VALUES  v_gs;
      INSERT INTO  TMP_GEN_STMT_SLOT VALUES  v_gs_c1;
      INSERT INTO  TMP_GEN_STMT_SLOT VALUES  v_gs_c2;
      INSERT INTO  TMP_GEN_STMT_SLOT VALUES  v_gs_c3;
      INSERT INTO  TMP_GEN_STMT_SLOT VALUES  v_gs_c4;
      INSERT INTO  TMP_GEN_STMT_SLOT VALUES  v_gs_c5;
      -- dbms_output.put_line('gen stmt slots - calling charges');

			v_result := METER_NUMBER_CHANGE.CALC_TMP_GS_CHARGES(v_gs.id);

      --dbms_output.put_line('gen stmt slots - after charges');

      update t_meter_reading_hdr set gs_batch_id = v_gs_process_id, M_GEN_STMT_ID = v_gs.id where id = v_mr_id;

      v_stage := '-8-';

      SELECT STMT_MONTH,STMT_YEAR,M_COMPANY_SERVICE_ID INTO v_gen_month,v_gen_year,v_gen_comp_servi_id FROM TMP_GEN_STMT gen WHERE gen.ID= v_gs.id;

      SELECT pp.PLANT_CLASS_TYPE_CODE,tariff.WEG_GROUP_NAME,tariff.RATE into  v_plant_class_code,v_plant_class_desc,tariff_rates FROM M_POWERPLANT pp
        LEFT JOIN M_TARIFF tariff ON pp.PLANT_CLASS_TYPE_CODE=tariff.WEG_GROUP_CODE 
        LEFT JOIN TMP_GEN_STMT gen ON pp.m_service_id = gen.M_COMPANY_SERVICE_ID and gen.ID=v_gs.id
        WHERE  gen.M_COMPANY_SERVICE_ID=v_gen_comp_servi_id;

      --        dbms_output.put_line('tariff rate- '||tariff_rates);
      --        dbms_output.put_line('v_plant_class_code- '||v_plant_class_code);
      --        dbms_output.put_line('v_plant_class_desc- '||v_plant_class_desc);
      --        dbms_output.put_line('tariff rate- '||v_gen_comp_servi_id);


      UPDATE TMP_GEN_STMT SET PLANT_CLASS_TYPE_CODE=v_plant_class_code,PLANT_CLASS_TYPE_DESC=v_plant_class_desc,TARIFF_RATE=tariff_rates WHERE ID=v_gs.id;

                      v_stage := '-9-';

      SELECT companygs.IS_CAPTIVE INTO v_gen_iscaptive FROM M_COMPANY companygs
      LEFT JOIN TMP_GEN_STMT gengs ON companygs.ID = gengs.M_COMPANY_ID WHERE gengs.ID=v_gs.id;

      SELECT COUNT(tradegs.IS_CAPTIVE) into v_gen_isstb_count from M_TRADE_RELATIONSHIP  tradegs
      LEFT JOIN TMP_GEN_STMT stbgs on tradegs.M_SELLER_COMP_SERVICE_ID = stbgs.M_COMPANY_SERVICE_ID and tradegs.M_BUYER_COMPANY_ID='TNEB' WHERE stbgs.ID=v_gs.id ;

      SELECT orggs.CODE INTO v_gen_org_code FROM M_ORG orggs
      LEFT JOIN TMP_GEN_STMT gengs1 ON orggs.ID = gengs1.M_ORG_ID WHERE gengs1.ID=v_gs.id;


      SELECT codes.VALUE_DESC INTO v_gen_voltage_desc FROM V_CODES codes
      LEFT JOIN TMP_GEN_STMT gengs2 ON codes.VALUE_CODE=INJECTING_VOLTAGE_CODE AND codes.LIST_NAME='Voltage'
      WHERE gengs2.ID=v_gs.id;


      if ( v_gen_isstb_count) > 0 THEN
        v_gen_isstb := 'Y'; 
        ELSE 
        v_gen_isstb := 'N'; 
      END IF;

        --IF(v_gen_isstb_count) > 0 THEN
           v_tariff_net_amount :=  to_number((v_gs.net_generation)* tariff_rates) ;
           -- dbms_output.put_line('v_tariff_net_amount- '||v_tariff_net_amount );
           SELECT gs.TOTAL_CHARGED_AMOUNT INTO v_total_charged_amount FROM TMP_GEN_STMT gs where gs.ID=v_gs.id;
           -- dbms_output.put_line('v_total_charged_amoun- '||v_total_charged_amount );
           v_net_payable := v_tariff_net_amount - v_total_charged_amount;
           --dbms_output.put_line('v_net_payable- '||v_net_payable);
        --   ELSE
        --   CONTINUE;
        --END IF;


      UPDATE TMP_GEN_STMT SET IS_CAPTIVE=v_gen_iscaptive,IS_STB=v_gen_isstb,DISP_ORG_CODE=v_gen_org_code,INJECTING_VOLTAGE_DESC=v_gen_voltage_desc,TARIFF_NET_AMOUNT=v_tariff_net_amount,NET_PAYABLE = v_net_payable WHERE ID=v_gs.id;


      --select  ss.ID,ss.NAME,ss.TYPE_OF_SS INTO v_gen_ssid, v_gen_ssname,v_gen_sstype  FROM  M_SUBSTATION  ss
      --LEFT JOIN TMP_GEN_STMT gengs3 ON  ss.M_ORG_ID = gengs3.M_ORG_ID   WHERE gengs3.ID=v_gs.id ;
      --
      --select  ff.ID,ff.NAME INTO v_gen_ffid, v_gen_ffname  FROM  M_FEEDER ff
      --LEFT JOIN TMP_GEN_STMT gengs4 ON  ff.VOLTAGE_CODE= gengs4.INJECTING_VOLTAGE_CODE  WHERE gengs4.ID=v_gs.id ;
      v_stage := '-10-';
      select service.M_SUBSTATION_ID,ss.NAME,ss.TYPE_OF_SS ,service.m_feeder_id,feeder.NAME 
        INTO v_gen_ssid, v_gen_ssname,v_gen_sstype,v_gen_ffid, v_gen_ffname
        from m_company_service service
        left join m_substation ss on service.m_substation_id=ss.id
        left join tmp_gen_stmt gengs3 ON service.id = gengs3.M_COMPANY_SERVICE_ID
        left join m_company_meter meter on meter.m_company_service_id=service.id
        left join m_feeder feeder on feeder.id=service.m_feeder_id 
        WHERE gengs3.ID=v_gs.id AND ROWNUM=1;
        
      UPDATE TMP_GEN_STMT SET M_SUBSTATION_ID = v_gen_ssid, M_SUBSTATION_NAME = v_gen_ssname,M_FEEDER_ID = v_gen_ffid,M_FEEDER_NAME = v_gen_ffname ,TYPE_OF_SS =v_gen_sstype  WHERE ID=v_gs.id;

      COMMIT;
			exception
			  when others then
			    v_exception_code := SQLCODE;
			    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
			    v_result := 'FAILURE';
			    v_reason := v_exception_code || ' - ' || v_exception_msg;
          v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT','EH','Error while processing each meter-reading - '||v_reason,v_result,v_created_By, sysdate,v_mr_id,v_gs_process_id);
			END;
		END LOOP;


       -- --dbms_output.put_line('all slots- '||v_mrh.id);
		if(v_no_records) THEN
			v_result := 'FAILURE';
      if(v_reason='') then v_reason := 'No records to process'; end if;
		END IF;

        if( V_RESULT != '') THEN
			v_result := 'SUCCESS'; 
		END IF; 

	exception
	  when others then
	    v_exception_code := SQLCODE;
	    v_exception_msg := SUBSTR(SQLERRM, 1, 200);
	    v_result := 'FAILURE';
	    v_reason := v_exception_code || ' - ' || v_exception_msg;
	    -- dbms_output.put_line(v_reason);
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT','EH',v_result,v_reason||v_stage,v_created_By, sysdate,v_mr_id,v_gs_process_id);
	END;
  <<THE_END>>
  v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.PROCESS_TEMP_GENERATION_STATEMENT','End',v_result,v_reason||v_stage,v_created_By, sysdate,v_mr_id,v_gs_process_id);
  COMMIT;
  return V_RESULT; 
END PROCESS_TEMP_GENERATION_STATEMENT;

FUNCTION CALC_TMP_GS_CHARGES (v_gs_id IN VARCHAR2 ) RETURN VARCHAR2 AS
  v_is_stb   VARCHAR2(150);
  v_evaluated      NUMBER;
  v_id             NUMBER;
  v_no_of_days number:=0;
  v_machine_capacity number:=0;
  v_penalty_rate number:=0;
  v_rkvah_inits number:=0;
  v_total_charges number:=0;
  v_grand_total_charges number:=0;
  v_formula        VARCHAR2(150);
  v_charge_type    VARCHAR2(150);
  v_unit_charge    VARCHAR2(150);
  v_charge_code         VARCHAR2(150);
  v_charge         VARCHAR2(150):='0';
  v_fuel_type_Code VARCHAR2(50);
  v_charge_percentage NUMBER:=0;
  v_net_generation VARCHAR2(150);
  v_result varchar(300):='SUCCESS';
  v_log_result varchar(300):='SUCCESS';
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
  v_reason VARCHAR2(300);
  v_cal number:=0;
  v_gen_iscaptive VARCHAR2(50); 
  v_gen_isstb_count NUMBER; 
  v_gen_sstype  VARCHAR2(50);
  v_is_rec VARCHAR2(50); 
BEGIN
  BEGIN
    SELECT gs.machine_capacity, (gs.FINAL_STMT_DT - gs.INIT_STMT_DT)+1 no_of_days, gs.penalty_rate, gs.RKVAH_UNITS ,gs.NET_GENERATION, gs.disp_fuel_type_Code ,nvl(gs.IS_STB,''),gs.TYPE_OF_SS,gs.IS_REC
      INTO v_machine_capacity,v_no_of_days,v_penalty_rate, v_rkvah_inits,v_net_generation, v_fuel_type_Code, v_is_stb ,v_gen_sstype,v_is_rec
      FROM TMP_GEN_STMT gs where id = v_gs_id;
    SELECT companygs.IS_CAPTIVE INTO v_gen_iscaptive FROM M_COMPANY companygs
      LEFT JOIN TMP_GEN_STMT gengs ON companygs.ID = gengs.M_COMPANY_ID WHERE gengs.ID=v_gs_id;
    SELECT COUNT(tradegs.IS_CAPTIVE) into v_gen_isstb_count from M_TRADE_RELATIONSHIP  tradegs
      LEFT JOIN TMP_GEN_STMT stbgs on tradegs.M_SELLER_COMP_SERVICE_ID = stbgs.M_COMPANY_SERVICE_ID and tradegs.M_BUYER_COMPANY_ID='TNEB' WHERE stbgs.ID=v_gs_id ;
    select ss.TYPE_OF_SS INTO v_gen_sstype from m_company_service service
      left join m_substation ss on service.m_substation_id=ss.id
      left join tmp_gen_stmt gengs3 ON service.id = gengs3.M_COMPANY_SERVICE_ID
      left join m_company_meter meter on meter.m_company_service_id=service.id WHERE gengs3.ID=v_gs_id AND ROWNUM=1 ;

    --    select service.TYPE_OF_SS  INTO v_gen_sstype
    --
    --    from v_company_service service LEFT JOIN TMP_GEN_STMT gengs3 ON  service.id = gengs3.M_COMPANY_SERVICE_ID and service.m_company_meter_id=gengs3.m_company_meter_id  WHERE gengs3.ID=v_gs_id ;
    if ( v_gen_isstb_count) > 0 THEN
      v_is_stb := 'Y'; 
    ELSE 
      v_is_stb := 'N'; 
    END IF;

    if(v_gen_sstype = 'SECTION 10(1)SS') then
      v_gen_sstype := 'Y';
    else
      v_gen_sstype := 'N';
    end if;

    if(v_is_stb = 'Y') then
      v_is_stb := 'Y';
    else
      v_is_stb := 'N';
    end if;
    FOR charge IN (SELECT CHARGE_CODE, CHARGE_DESC, CHARGE_TYPE_CODE, UNIT_CHARGE FROM M_CHARGE_defn d JOIN  M_CHARGES_MAP m ON d.id = m.M_CHARGE_ID AND CONTEXT = 'GENERATOR_STATEMENT' )
    LOOP
    BEGIN
      v_charge_code :=charge.charge_code ;
      if(v_charge_code in ('C001','C002','C005','C006') or v_is_rec = 'Y') then
        v_charge_percentage:= 100;
      elsif(v_is_rec = 'N' and v_fuel_type_Code = '02') then
        v_charge_percentage:= 50;
      elsif(v_is_rec = 'N' and v_fuel_type_Code = '18') THEN
        v_charge_percentage:= 40;
      else
        v_charge_percentage:= 100;
      end if;



      IF(v_charge_code = 'C001') then
        v_total_charges:= CEIL(to_number(CALC_CHARGES(v_charge_code,'','','')));   
      ELSIF(v_charge_code = 'C002') then
        IF(v_gen_sstype = 'Y') then
          --dbms_output.put_line('No o&M charges  for 10(1) ss');
          continue; --  No o&M charges  for 10(1) ss
        else  
          v_total_charges:= CEIL(to_number(CALC_CHARGES(charge.charge_code,v_machine_capacity,v_no_of_days,'')));
        end if;
        ELSIF(v_charge_code = 'C003') then
          IF(v_is_stb = 'Y') then
            continue; --  No transmission charges for stb
          else
            v_total_charges:= CEIL(to_number(CALC_CHARGES(v_charge_code,v_machine_capacity,v_no_of_days,'')));
          end if;
        ELSIF(v_charge_code = 'C004') then
          v_total_charges:= CEIL(to_number(CALC_CHARGES(v_charge_code,v_machine_capacity,v_no_of_days,'')));
        ELSIF(v_charge_code = 'C005') then
          IF((to_number(v_net_generation)*0.1)>=v_rkvah_inits) then
            v_penalty_rate:= 0.25;
          ELSE
            v_penalty_rate:=0.5;
          end if;
          v_total_charges:= CEIL(to_number(CALC_CHARGES(v_charge_code,v_penalty_rate,v_rkvah_inits,'')));
          if(v_total_charges<0)then
            v_total_charges:=0;
          else
            v_total_charges:=v_total_charges;
          end if;
        ELSIF(v_charge_code = 'C006') then
          v_total_charges:= CEIL(to_number(METER_NUMBER_CHANGE.CALC_TMP_NEGATIVE_CHARGES(v_gs_id)));
          --          dbms_output.put_line(' v_total_charges'|| v_total_charges);
        ELSIF(v_charge_code = 'C007') then
          v_total_charges:= CEIL(to_number(CALC_CHARGES(v_charge_code,v_no_of_days,'','')));
        else
          continue;
        end if;
        v_total_charges := CEIL(v_total_charges * v_charge_percentage/100);
        --  dbms_output.put_line('  v_total_charges  - '||  v_total_charges );
        INSERT INTO TMP_GEN_STMT_CHARGE (ID, TMP_GEN_STMT_ID, CHARGE_CODE, CHARGE_DESC, CHARGE_TYPE_CODE, UNIT_CHARGE, TOTAL_CHARGES)
        VALUES(T_GEN_STMT_CHARGE_SEQ.nextval, v_gs_id, charge.charge_code,charge.charge_desc,charge.CHARGE_TYPE_CODE, charge.UNIT_CHARGE, v_total_charges);
        v_grand_total_charges := CEIL((v_grand_total_charges+NVL(v_total_charges,0)));
        --        dbms_output.put_line(' v_grand_total_charges - '|| v_grand_total_charges);
      EXCEPTION
      WHEN OTHERS THEN
        v_exception_code := SQLCODE;
        v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
        v_result := 'FAILURE';
        v_reason := v_exception_code || ' - ' || v_exception_msg;
        v_log_result := log_activity('PROCEDURE','CALC_GS_CHARGES','EH-LOOP','Error while processing charge ('||v_charge_code ||')- '||v_reason,v_result,'admin', sysdate,v_gs_id);
      END;
      v_reason := 'SUCCESS'; -- resetting loop result
    END LOOP;
    update TMP_GEN_STMT set TOTAL_CHARGED_AMOUNT = v_grand_total_charges where id = v_gs_id;
  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','CALC_GS_CHARGES','EH','Issue in CALC_GS_CHARGES - '||v_reason,v_result,'admin', sysdate,v_gs_id);
  END;
RETURN v_result;
END CALC_TMP_GS_CHARGES;

FUNCTION  CALC_TMP_NEGATIVE_CHARGES(v_gs_id IN VARCHAR2 ) RETURN VARCHAR2 AS
  v_result varchar2(300);
 	v_reason VARCHAR2(300);
  v_total_exp_gen varchar2(300);
 	v_total_imp_gen varchar2(300);
	v_diff_total_gen varchar(300);
	v_c1_units varchar(300);
	v_c2_units varchar(300);
	v_c3_units varchar(300);
	v_c4_units varchar(300);
	v_c5_units varchar(300);
  v_c5_units_tax varchar(300);
  v_log_result varchar(300):='SUCCESS';
  v_exception_code VARCHAR2(150);
  v_exception_msg  VARCHAR2(150);
  v_slot_code VARCHAR(300);
  v_tax NUMBER;
BEGIN	
  BEGIN 
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CALC_NEGATIVE_CHARGES','START','START - ','','admin', sysdate,v_gs_id);
    SELECT gs.TOTAL_EXPORT_GEN INTO v_total_exp_gen FROM TMP_GEN_STMT gs WHERE gs.ID= v_gs_id ;
    SELECT gs.TOTAL_IMPORT_GEN INTO v_total_imp_gen FROM TMP_GEN_STMT gs WHERE gs.ID= v_gs_id ;
          dbms_output.put_line(' v_total_exp_gen - '|| v_total_exp_gen);
          dbms_output.put_line(' v_total_imp_gen - '|| v_total_imp_gen);

    --	if(TO_NUMBER(v_total_exp_gen) < TO_NUMBER(v_total_imp_gen))THEN --strat
    v_diff_total_gen := TO_NUMBER(v_total_exp_gen) - TO_NUMBER(v_total_imp_gen);
    dbms_output.put_line(' v_diff_total_gen - '|| v_diff_total_gen);
    FOR genSlot IN (SELECT gs.SLOT_CODE,gs.IMP_UNITS,gs.EXP_UNITS FROM TMP_GEN_STMT_SLOT gs WHERE gs.TMP_GEN_STMT_ID=v_gs_id)
    LOOP
    BEGIN
      v_slot_code :=genSlot.SLOT_CODE ;
      ----------------------------------------------------------
      if(genSlot.SLOT_CODE = 'C1') THEN --1
        --  	    dbms_output.put_line(' genSlot.EXP_UNITS - c1===='|| genSlot.EXP_UNITS);
        --          dbms_output.put_line(' genSlot.IMP_UNITS- c1===='|| genSlot.IMP_UNITS);
        if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN --2
          v_c1_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*7.62);
          v_tax :=TO_NUMBER(v_c1_units)*0.05;
          v_c1_units:=TO_NUMBER(v_c1_units)+v_tax;
          dbms_output.put_line(' v_c1_units - '|| v_c1_units);
        ELSE
          v_c1_units :=0;
        END IF;	--2
      END IF;--1
            
      ----------------------------------------------------------
      if(genSlot.SLOT_CODE = 'C2') THEN
        if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
          --	        dbms_output.put_line(' genSlot.EXP_UNITS - c2===='|| genSlot.EXP_UNITS);
          --          dbms_output.put_line(' genSlot.IMP_UNITS- c2===='|| genSlot.IMP_UNITS);
          v_c2_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*7.62);
          v_tax :=TO_NUMBER(v_c2_units)*0.05;
          v_c2_units:=TO_NUMBER(v_c2_units)+v_tax;
          dbms_output.put_line(' v_c2_units - '|| v_c2_units);
        ELSE
          v_c2_units :=0;
        END IF;
      END IF;
      ----------------------------------------------------------
      if(genSlot.SLOT_CODE = 'C3') THEN
        if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
          v_c3_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*6.35);
          v_tax :=TO_NUMBER(v_c3_units)*0.05;
          v_c3_units:=TO_NUMBER(v_c3_units)+v_tax;
          dbms_output.put_line(' v_c3_units - '|| v_c3_units);
        ELSE
          v_c3_units :=0;
          dbms_output.put_line(' v_c3_units - '|| v_c3_units);
        END IF;
      END IF;
      ----------------------------------------------------------
      if(genSlot.SLOT_CODE = 'C4') THEN
        if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
          v_c4_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*6.35);
          v_tax :=TO_NUMBER(v_c4_units)*0.05;
          v_c4_units:=TO_NUMBER(v_c4_units)+v_tax;
          dbms_output.put_line(' v_c4_units - '|| v_c4_units);
        ELSE
          v_c4_units :=0;
        END IF;
      END IF;
      ----------------------------------------------------------
      if(genSlot.SLOT_CODE = 'C5') THEN
        if(TO_NUMBER(genSlot.EXP_UNITS) < TO_NUMBER(genSlot.IMP_UNITS))THEN
          v_c5_units :=((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*6.0325);
          dbms_output.put_line(' v_c5_units - '|| v_c5_units);
          v_c5_units_tax :=(((TO_NUMBER(genSlot.EXP_UNITS) - TO_NUMBER(genSlot.IMP_UNITS))*0.3175));
          dbms_output.put_line(' v_c5_units_tax - '|| v_c5_units_tax);
          v_c5_units := (TO_NUMBER(v_c5_units) + TO_NUMBER(v_c5_units_tax));
          dbms_output.put_line(' v_c5_units - '|| v_c5_units);
        ELSE
          v_c5_units :=0;
        END IF;
      END IF;
      ----------------------------------------------------------
    EXCEPTION
    WHEN OTHERS THEN
      v_exception_code := SQLCODE;
      v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
      v_result := 'FAILURE';
      v_reason := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CALC_NEGATIVE_CHARGES','EH','Issue in CALC_NEGATIVE_CHARGES - '||v_reason,v_result,'admin', sysdate,v_gs_id);
    END;
      v_reason := 'SUCCESS'; -- resetting loop result
    END LOOP;
    --    else
    --      v_result := 0; 
    --  dbms_output.put_line(' v_result'|| v_result);
    --END IF; -- end
    dbms_output.put_line(' v_c5_units - '|| v_c5_units);
    v_result := (TO_NUMBER(v_c1_units ) + TO_NUMBER(v_c2_units )+ TO_NUMBER(v_c3_units )+ TO_NUMBER(v_c4_units )+ TO_NUMBER(v_c5_units ));
    IF(v_result<0)THEN
      dbms_output.put_line(' v_result less than 0 - '|| v_result);
      v_result := v_result*(-1);
    END IF;
    dbms_output.put_line(' v_result - '|| v_result);

  EXCEPTION
  WHEN OTHERS THEN
    v_exception_code := SQLCODE;
    v_exception_msg  := SUBSTR(SQLERRM, 1, 100);
    v_result := 'FAILURE';
    v_reason := v_exception_code || ' - ' || v_exception_msg;
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CALC_NEGATIVE_CHARGES','EH','Issue in CALC_NEGATIVE_CHARGES - '||v_reason,v_result,'admin', sysdate,v_gs_id);
  END;
  dbms_output.put_line(' v_result - '|| v_result); 
RETURN v_result;
END CALC_TMP_NEGATIVE_CHARGES;

PROCEDURE CREATE_CONSOLIDATED_GENERATION_STATEMENT(I_BATCH_KEY IN VARCHAR2, I_MONTH IN VARCHAR2,I_YEAR IN VARCHAR2,O_RESULT_CODE OUT VARCHAR2, O_RESULT_DESC OUT VARCHAR2) IS
  v_log_result  varchar2(50);
  v_reason varchar2(200):='';
  v_exception_code  NUMBER;
  v_tmp_gen_count number;
  v_exception_msg  VARCHAR2(200);
  v_result varchar(300):='SUCCESS';   
  v_created_Date DATE := SYSDATE;
  v_created_By  varchar2(50):= 'METER_NUMBER_CHANGE.CREATE_GENERATION_STATEMENT';
  v_Service_number  VARCHAR2(200);
  v_count number;
  serv_count number;
  serv_number varchar2(100);
  v_cursor sys_refcursor ;
  v_total_cursor sys_refcursor ;
  v_slot_cursor sys_refcursor ;
  v_charge_cursor sys_refcursor ;
    v_test varchar2(50);
  v_temp_gen_stmt TMP_GEN_STMT%ROWTYPE;
  v_gs T_GEN_STMT%ROWTYPE;
  slot T_GEN_STMT_SLOT%ROWTYPE;
  charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c1 T_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c2 T_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c3 T_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c4 T_GEN_STMT_SLOT%ROWTYPE;
  v_gs_c5 T_GEN_STMT_SLOT%ROWTYPE;
  
  v_gs_c1_charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c2_charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c3_charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c4_charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c5_charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c6_charge T_GEN_STMT_CHARGE%ROWTYPE;
  v_gs_c7_charge T_GEN_STMT_CHARGE%ROWTYPE;
    
    

BEGIN
  BEGIN --EXCEPTION STARTS HERE
    v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT','CREATE_GENERATION_STATEMENT','Start - '||I_BATCH_KEY,'','', sysdate,I_BATCH_KEY);

   -------------------------------------------------------- LOGIC
    OPEN v_total_cursor for SELECT DISTINCT disp_Service_number ser_num FROM TMP_GEN_STMT WHERE BATCH_KEY=I_BATCH_KEY AND STMT_MONTH=I_MONTH AND STMT_YEAR=I_YEAR;
    LOOP
    FETCH v_total_cursor INTO serv_number;
    EXIT WHEN v_total_cursor%NOTFOUND;
      SELECT count(disp_Service_number)  into  serv_count FROM TMP_GEN_STMT WHERE disp_Service_number= serv_number AND BATCH_KEY=I_BATCH_KEY AND STMT_MONTH=I_MONTH AND STMT_YEAR=I_YEAR;
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT','serv_count','cHECK - '||serv_count,'','', sysdate,I_BATCH_KEY);

      IF serv_count=2 then

        OPEN v_cursor for SELECT * FROM TMP_GEN_STMT where batch_key=I_BATCH_KEY and stmt_month=I_MONTH and stmt_year=I_YEAR and disp_Service_number=serv_number;
        LOOP
        FETCH v_cursor INTO v_temp_gen_stmt;
        EXIT WHEN v_cursor%NOTFOUND;
                  
          v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT','v_cursor-loop','serv_number - '||serv_number,'','', sysdate,I_BATCH_KEY);
            
          v_gs.id := T_GEN_STMT_SEQ.nextval;
          v_gs_c1.id := T_GEN_STMT_SEQ.nextval;v_gs_c1.T_GEN_STMT_ID := v_gs.id;
          v_gs_c2.id := T_GEN_STMT_SEQ.nextval;v_gs_c2.T_GEN_STMT_ID := v_gs.id;
          v_gs_c3.id := T_GEN_STMT_SEQ.nextval;v_gs_c3.T_GEN_STMT_ID := v_gs.id;
          v_gs_c4.id := T_GEN_STMT_SEQ.nextval;v_gs_c4.T_GEN_STMT_ID := v_gs.id;
          v_gs_c5.id := T_GEN_STMT_SEQ.nextval;v_gs_c5.T_GEN_STMT_ID := v_gs.id;
          v_gs_c1_charge.ID:= T_GEN_STMT_SEQ.nextval; v_gs_c1_charge.T_GEN_STMT_ID:= v_gs.id;
          v_gs_c2_charge.ID:= T_GEN_STMT_SEQ.nextval; v_gs_c2_charge.T_GEN_STMT_ID := v_gs.id;
          v_gs_c3_charge.ID:= T_GEN_STMT_SEQ.nextval; v_gs_c3_charge.T_GEN_STMT_ID := v_gs.id;
          v_gs_c4_charge.ID:= T_GEN_STMT_SEQ.nextval; v_gs_c4_charge.T_GEN_STMT_ID := v_gs.id;
          v_gs_c5_charge.ID:= T_GEN_STMT_SEQ.nextval;v_gs_c5_charge.T_GEN_STMT_ID := v_gs.id;
          v_gs_c6_charge.ID:= T_GEN_STMT_SEQ.nextval; v_gs_c6_charge.T_GEN_STMT_ID := v_gs.id;
          v_gs_c7_charge.ID:= T_GEN_STMT_SEQ.nextval; v_gs_c7_charge.T_GEN_STMT_ID := v_gs.id;
          -- set values in gen stmt
          v_gs.STATUS_CODE := 'CREATED';
          v_gs.M_COMPANY_METER_ID := v_temp_gen_stmt.M_COMPANY_METER_ID ;
          v_gs.T_MR_IDS := v_temp_gen_stmt.M_COMPANY_METER_ID ;
          v_gs.REF_NUMBER := v_temp_gen_stmt.ID;
          v_gs.mf := v_temp_gen_stmt.mf;
          v_gs.STMT_GEN_DATE := v_created_Date ;
          v_gs.STMT_MONTH :=  v_temp_gen_stmt.STMT_MONTH;
          v_gs.STMT_YEAR := v_temp_gen_stmt.STMT_YEAR;
          v_gs.INIT_STMT_DT := v_temp_gen_stmt.INIT_STMT_DT;
          v_gs.FINAL_STMT_DT := v_temp_gen_stmt.FINAL_STMT_DT;
          v_gs.KVAH_INIT :=to_number(nvl(v_gs.KVAH_INIT,0)) +to_number(nvl(v_temp_gen_stmt.KVAH_INIT,0)); 
          v_gs.KVAH_FINAL :=to_number(nvl(v_gs.KVAH_FINAL,0)) +to_number(nvl(v_temp_gen_stmt.KVAH_FINAL,0)); 
          v_gs.RKVAH_INIT := to_number(nvl(v_gs.RKVAH_INIT,0)) +to_number(nvl(v_temp_gen_stmt.RKVAH_INIT,0)); 
          v_gs.RKVAH_FINAL := to_number(nvl(v_gs.RKVAH_FINAL,0)) +to_number(nvl(v_temp_gen_stmt.RKVAH_FINAL,0)); 
          v_gs.KVAH_DIFF := to_number(nvl(v_gs.KVAH_DIFF,0)) +to_number(nvl(v_temp_gen_stmt.KVAH_DIFF,0)); 
          v_gs.KVAH_UNITS := to_number(nvl(v_gs.KVAH_UNITS,0)) +to_number(nvl(v_temp_gen_stmt.KVAH_UNITS,0)); 
          v_gs.RKVAH_DIFF := to_number(nvl(v_gs.RKVAH_DIFF,0)) +to_number(nvl(v_temp_gen_stmt.RKVAH_DIFF,0)); 
          v_gs.RKVAH_UNITS := to_number(nvl(v_gs.RKVAH_UNITS,0)) +to_number(nvl(v_temp_gen_stmt.RKVAH_UNITS,0)); 
          v_gs.TOTAL_EXPORT_GEN :=to_number(nvl(v_gs.TOTAL_EXPORT_GEN,0)) +to_number(nvl(v_temp_gen_stmt.TOTAL_EXPORT_GEN,0)); 
          v_gs.TOTAL_IMPORT_GEN := to_number(nvl(v_gs.TOTAL_IMPORT_GEN,0)) +to_number(nvl(v_temp_gen_stmt.TOTAL_IMPORT_GEN,0)); 
          v_gs.M_ORG_ID := v_temp_gen_stmt.M_ORG_ID;
          v_gs.M_COMPANY_ID := v_temp_gen_stmt.M_COMPANY_ID;
          v_gs.M_COMPANY_SERVICE_ID := v_temp_gen_stmt.M_COMPANY_SERVICE_ID;
          v_gs.DISP_COMPANY_NAME := v_temp_gen_stmt.DISP_COMPANY_NAME;
          v_gs.DISP_SERVICE_NUMBER := v_temp_gen_stmt.DISP_SERVICE_NUMBER;
          v_gs.INJECTING_VOLTAGE_CODE := v_temp_gen_stmt.INJECTING_VOLTAGE_CODE;
          v_gs.DISP_ORG_NAME := v_temp_gen_stmt.DISP_ORG_NAME; 
          v_gs.FLOW_TYPE_CODE := v_temp_gen_stmt.FLOW_TYPE_CODE; 
          v_gs.net_generation := v_temp_gen_stmt.net_generation;
          v_gs.C1 := to_number(nvl(v_gs.C1,0)) +to_number(nvl(v_temp_gen_stmt.C1,0));
          v_gs.C2 := to_number(nvl(v_gs.C2,0)) +to_number(nvl(v_temp_gen_stmt.C2,0)); 
          v_gs.C3 := to_number(nvl(v_gs.C3,0)) +to_number(nvl(v_temp_gen_stmt.C3,0)); 
          v_gs.C4 := to_number(nvl(v_gs.C4,0)) +to_number(nvl(v_temp_gen_stmt.C4,0)); 
          v_gs.C5 := to_number(nvl(v_gs.C5,0)) +to_number(nvl(v_temp_gen_stmt.C5,0)); 
          v_gs.disp_fuel_type_code := v_temp_gen_stmt.disp_fuel_type_code;
          v_gs.disp_fuel_type_name := v_temp_gen_stmt.disp_fuel_type_name;
                  v_gs.COMMISSION_DATE:= v_temp_gen_stmt.COMMISSION_DATE;
                  v_gs.IS_REC := v_temp_gen_stmt.IS_REC;
          v_gs.created_by := v_created_By;
          v_gs.created_dt := SYSDATE;
          v_gs.enabled := 'Y';
          v_gs.IS_METER_CHANGE:='Y';
          --                v_gs.batch_key:=I_BATCH_KEY;
          v_gs.TOTAL_CHARGED_AMOUNT := 0; --actual value calculated by calc_gs_charges() 
          v_gs.NET_PAYABLE := 0; --actual value calculated by calc_gs_charges() 
          if(to_number(v_gs.TOTAL_EXPORT_GEN)*0.01 >= to_number(v_gs.RKVAH_UNITS)) THEN
            v_gs.penalty_rate := '0.25';
          ELSE
            v_gs.penalty_rate := '0.50';
          END IF;
          --                v_stage := '-7-';
          OPEN v_slot_cursor for select * from tmp_gen_stmt_slot where TMP_GEN_STMT_ID =v_temp_gen_stmt.id ;
          LOOP
          FETCH v_slot_cursor INTO slot;
          EXIT WHEN v_slot_cursor%NOTFOUND;
            v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT','v_slot_cursor-loop','serv_number - '||serv_number,'','', sysdate,I_BATCH_KEY);

            if slot.slot_code='C1' then
              v_gs_c1.slot_code:='C1';
              v_gs_c1.IMP_INIT:=to_number(nvl(v_gs_c1.IMP_INIT,0)) +to_number(nvl(slot.IMP_INIT,0));
              v_gs_c1.IMP_FINAL:=to_number(nvl(v_gs_c1.IMP_FINAL,0)) +to_number(nvl(slot.IMP_FINAL,0));
              v_gs_c1.IMP_DIFF:=to_number(nvl(v_gs_c1.IMP_DIFF,0)) +to_number(nvl(slot.IMP_DIFF,0));
              v_gs_c1.IMP_UNITS:=to_number(nvl(v_gs_c1.IMP_UNITS,0)) +to_number(nvl(slot.IMP_UNITS,0));
              v_gs_c1.EXP_INIT:=to_number(nvl(v_gs_c1.EXP_INIT,0)) +to_number(nvl(slot.EXP_INIT,0));
              v_gs_c1.EXP_FINAL:=to_number(nvl(v_gs_c1.EXP_FINAL,0)) +to_number(nvl(slot.EXP_FINAL,0));
              v_gs_c1.EXP_DIFF:=to_number(nvl(v_gs_c1.EXP_DIFF,0)) +to_number(nvl(slot.EXP_DIFF,0));
              v_gs_c1.EXP_UNITS:=to_number(nvl(v_gs_c1.EXP_UNITS,0)) +to_number(nvl(slot.EXP_UNITS,0));
              v_gs_c1.BANKED_BALANCE:=nvl(slot.BANKED_BALANCE,0);
              v_gs_c1.NET_UNITS:=to_number(nvl(v_gs_c1.NET_UNITS,0)) +to_number(nvl(slot.NET_UNITS,0));  
              v_gs_c1.CREATED_DATE:=sysdate;
              v_gs_c1.CREATED_BY:='METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT';
            end if;
                      
            if slot.slot_code='C2' then
              v_gs_c2.slot_code:='C2';
              v_gs_c2.IMP_INIT:=to_number(nvl(v_gs_c2.IMP_INIT,0)) +to_number(nvl(slot.IMP_INIT,0));
              v_gs_c2.IMP_FINAL:=to_number(nvl(v_gs_c2.IMP_FINAL,0)) +to_number(nvl(slot.IMP_FINAL,0));
              v_gs_c2.IMP_DIFF:=to_number(nvl(v_gs_c2.IMP_DIFF,0)) +to_number(nvl(slot.IMP_DIFF,0));
              v_gs_c2.IMP_UNITS:=to_number(nvl(v_gs_c2.IMP_UNITS,0)) +to_number(nvl(slot.IMP_UNITS,0));
              v_gs_c2.EXP_INIT:=to_number(nvl(v_gs_c2.EXP_INIT,0)) +to_number(nvl(slot.EXP_INIT,0));
              v_gs_c2.EXP_FINAL:=to_number(nvl(v_gs_c2.EXP_FINAL,0)) +to_number(nvl(slot.EXP_FINAL,0));
              v_gs_c2.EXP_DIFF:=to_number(nvl(v_gs_c2.EXP_DIFF,0)) +to_number(nvl(slot.EXP_DIFF,0));
              v_gs_c2.EXP_UNITS:=to_number(nvl(v_gs_c2.EXP_UNITS,0)) +to_number(nvl(slot.EXP_UNITS,0));
              v_gs_c2.BANKED_BALANCE:=nvl(slot.BANKED_BALANCE,0);
              v_gs_c2.NET_UNITS:=to_number(nvl(v_gs_c2.NET_UNITS,0)) +to_number(nvl(slot.NET_UNITS,0));  
              v_gs_c2.CREATED_DATE:=sysdate;
              v_gs_c2.CREATED_BY:='METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT';
            end if;
                      
            if slot.slot_code='C3' then
              v_gs_c3.slot_code:='C3';
              v_gs_c3.IMP_INIT:=to_number(nvl(v_gs_c3.IMP_INIT,0)) +to_number(nvl(slot.IMP_INIT,0));
              v_gs_c3.IMP_FINAL:=to_number(nvl(v_gs_c3.IMP_FINAL,0)) +to_number(nvl(slot.IMP_FINAL,0));
              v_gs_c3.IMP_DIFF:=to_number(nvl(v_gs_c3.IMP_DIFF,0)) +to_number(nvl(slot.IMP_DIFF,0));
              v_gs_c3.IMP_UNITS:=to_number(nvl(v_gs_c3.IMP_UNITS,0)) +to_number(nvl(slot.IMP_UNITS,0));
              v_gs_c3.EXP_INIT:=to_number(nvl(v_gs_c3.EXP_INIT,0)) +to_number(nvl(slot.EXP_INIT,0));
              v_gs_c3.EXP_FINAL:=to_number(nvl(v_gs_c3.EXP_FINAL,0)) +to_number(nvl(slot.EXP_FINAL,0));
              v_gs_c3.EXP_DIFF:=to_number(nvl(v_gs_c3.EXP_DIFF,0)) +to_number(nvl(slot.EXP_DIFF,0));
              v_gs_c3.EXP_UNITS:=to_number(nvl(v_gs_c3.EXP_UNITS,0)) +to_number(nvl(slot.EXP_UNITS,0));
              v_gs_c3.BANKED_BALANCE:=nvl(slot.BANKED_BALANCE,0);
              v_gs_c3.NET_UNITS:=to_number(nvl(v_gs_c3.NET_UNITS,0)) +to_number(nvl(slot.NET_UNITS,0));  
              v_gs_c3.CREATED_DATE:=sysdate;
              v_gs_c3.CREATED_BY:='METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT';
            end if;
                      
            if slot.slot_code='C4' then
              v_gs_c4.slot_code:='C4';
              v_gs_c4.IMP_INIT:=to_number(nvl(v_gs_c4.IMP_INIT,0)) +to_number(nvl(slot.IMP_INIT,0));
              v_gs_c4.IMP_FINAL:=to_number(nvl(v_gs_c4.IMP_FINAL,0)) +to_number(nvl(slot.IMP_FINAL,0));
              v_gs_c4.IMP_DIFF:=to_number(nvl(v_gs_c4.IMP_DIFF,0)) +to_number(nvl(slot.IMP_DIFF,0));
              v_gs_c4.IMP_UNITS:=to_number(nvl(v_gs_c4.IMP_UNITS,0)) +to_number(nvl(slot.IMP_UNITS,0));
              v_gs_c4.EXP_INIT:=to_number(nvl(v_gs_c4.EXP_INIT,0)) +to_number(nvl(slot.EXP_INIT,0));
              v_gs_c4.EXP_FINAL:=to_number(nvl(v_gs_c4.EXP_FINAL,0)) +to_number(nvl(slot.EXP_FINAL,0));
              v_gs_c4.EXP_DIFF:=to_number(nvl(v_gs_c4.EXP_DIFF,0)) +to_number(nvl(slot.EXP_DIFF,0));
              v_gs_c4.EXP_UNITS:=to_number(nvl(v_gs_c4.EXP_UNITS,0)) +to_number(nvl(slot.EXP_UNITS,0));
              v_gs_c4.BANKED_BALANCE:=nvl(slot.BANKED_BALANCE,0);
              v_gs_c4.NET_UNITS:=to_number(nvl(v_gs_c4.NET_UNITS,0)) +to_number(nvl(slot.NET_UNITS,0));  
              v_gs_c4.CREATED_DATE:=sysdate;
              v_gs_c4.CREATED_BY:='METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT';
            end if;
                      
            if slot.slot_code='C5' then
              v_gs_c5.slot_code:='C5';
              v_gs_c5.IMP_INIT:=to_number(nvl(v_gs_c5.IMP_INIT,0)) +to_number(nvl(slot.IMP_INIT,0));
              v_gs_c5.IMP_FINAL:=to_number(nvl(v_gs_c5.IMP_FINAL,0)) +to_number(nvl(slot.IMP_FINAL,0));
              v_gs_c5.IMP_DIFF:=to_number(nvl(v_gs_c5.IMP_DIFF,0)) +to_number(nvl(slot.IMP_DIFF,0));
              v_gs_c5.IMP_UNITS:=to_number(nvl(v_gs_c5.IMP_UNITS,0)) +to_number(nvl(slot.IMP_UNITS,0));
              v_gs_c5.EXP_INIT:=to_number(nvl(v_gs_c5.EXP_INIT,0)) +to_number(nvl(slot.EXP_INIT,0));
              v_gs_c5.EXP_FINAL:=to_number(nvl(v_gs_c5.EXP_FINAL,0)) +to_number(nvl(slot.EXP_FINAL,0));
              v_gs_c5.EXP_DIFF:=to_number(nvl(v_gs_c5.EXP_DIFF,0)) +to_number(nvl(slot.EXP_DIFF,0));
              v_gs_c5.EXP_UNITS:=to_number(nvl(v_gs_c5.EXP_UNITS,0)) +to_number(nvl(slot.EXP_UNITS,0));
              v_gs_c5.BANKED_BALANCE:=nvl(slot.BANKED_BALANCE,0);
              v_gs_c5.NET_UNITS:=to_number(nvl(v_gs_c5.NET_UNITS,0)) +to_number(nvl(slot.NET_UNITS,0));   
              v_gs_c5.CREATED_DATE:=sysdate;
              v_gs_c5.CREATED_BY:='METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT';
            end if;
            v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
          end loop;
                      
          OPEN v_charge_cursor for select * from tmp_gen_stmt_charge where TMP_GEN_STMT_ID =v_temp_gen_stmt.id ;
          LOOP
          FETCH v_charge_cursor INTO charge;
          EXIT WHEN v_charge_cursor%NOTFOUND;
                              
            if charge.CHARGE_CODE='C001' then
              v_gs_c1_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c1_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c1_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c1_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c1_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c1_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c1_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c1_charge.CREATED_DATE:=sysdate;
            end if;
                          
            if charge.CHARGE_CODE='C002' then
              v_gs_c2_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c2_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c2_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c2_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c2_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c2_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c2_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c2_charge.CREATED_DATE:=sysdate;
            end if;
                          
            if charge.CHARGE_CODE='C003' then
              v_gs_c3_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c3_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c3_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c3_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c3_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c3_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c3_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c3_charge.CREATED_DATE:=sysdate;
            end if;
                          
            if charge.CHARGE_CODE='C004' then
              v_gs_c4_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c4_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c4_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c4_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c4_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c4_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c4_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c4_charge.CREATED_DATE:=sysdate;
            end if;
                          
            if charge.CHARGE_CODE='C005' then
              v_gs_c5_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c5_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c5_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c5_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c5_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c5_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c5_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c5_charge.CREATED_DATE:=sysdate;
            end if;

            if charge.CHARGE_CODE='C006' then
              v_gs_c6_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c6_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c6_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c6_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c6_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c6_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c6_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c6_charge.CREATED_DATE:=sysdate;
            end if;

            if charge.CHARGE_CODE='C007' then
              v_gs_c7_charge.CHARGE_CODE:=charge.CHARGE_CODE;
              v_gs_c7_charge.CHARGE_DESC:=charge.CHARGE_DESC;
              v_gs_c7_charge.CHARGE_TYPE_CODE:=charge.CHARGE_DESC;
              v_gs_c7_charge.UNIT_CHARGE:=to_number(nvl(v_gs_c7_charge.UNIT_CHARGE,0)) +to_number(nvl(charge.UNIT_CHARGE,0));    
              v_gs_c7_charge.TOTAL_CHARGES:=to_number(nvl(v_gs_c7_charge.TOTAL_CHARGES,0)) +to_number(nvl(charge.TOTAL_CHARGES,0));    
              v_gs_c7_charge.CREATED_DATE:=sysdate;
            end if;
          END LOOP;
                      
        END LOOP;
      end if;
    end loop;
       
    INSERT INTO  T_GEN_STMT VALUES  v_gs;
    INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c1;
    INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c2;
    INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c3;
    INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c4;
    INSERT INTO  T_GEN_STMT_SLOT VALUES  v_gs_c5;
                
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c1_charge;
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c2_charge;
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c3_charge;
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c4_charge;
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c5_charge;
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c6_charge;
    INSERT INTO  T_GEN_STMT_CHARGE VALUES  v_gs_c7_charge;
    -----------------------------------------------------
  exception when others then
      v_exception_code := SQLCODE;
      v_exception_msg := SUBSTR(SQLERRM, 1, 200);
      o_result_code := 'FAILURE';
      o_result_desc := v_exception_code || ' - ' || v_exception_msg;
      v_log_result := log_activity('PROCEDURE','METER_NUMBER_CHANGE.CREATE_CONSOLIDATED_GENERATION_STATEMENT','EXCEPTION',O_RESULT_DESC,'','', sysdate,I_BATCH_KEY);
  END;--EXCEPTION STARTS HERE
      
END CREATE_CONSOLIDATED_GENERATION_STATEMENT;
  
END METER_NUMBER_CHANGE;