CREATE SEQUENCE OPENACCESS.INT_ADJUSTMENT_BATCH_KEY INCREMENT BY 1 MINVALUE 10000 MAXVALUE 9999999999999999999999999999 NOCYCLE CACHE 20 NOORDER;

CREATE SEQUENCE OPENACCESS.INT_ADJUSTMENT_ID INCREMENT BY 1 MINVALUE 10000 MAXVALUE 9999999999999999999999999999 NOCYCLE CACHE 20 NOORDER;


BEGIN 
DBMS_SCHEDULER.CREATE_PROGRAM
(
program_name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS', 
program_action => 'IMP_INT_ADJUSTED_UNIT.DIRECT_ADJUSTMENT_FROMHT', 
program_type => 'STORED_PROCEDURE',
NUMBER_OF_ARGUMENTS => 4,
ENABLED =>  FALSE,
COMMENTS => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS');
END;


BEGIN 
DBMS_SCHEDULER.DEFINE_PROGRAM_ARGUMENT(
        PROGRAM_NAME => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS',
        argument_name     => 'from_date',
        argument_position => 1,
        argument_type     => 'VARCHAR2',
        default_value     => 'SHEDULER'
    );
    END;
   
   BEGIN 
  DBMS_SCHEDULER.DEFINE_PROGRAM_ARGUMENT(
        PROGRAM_NAME => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS',
        argument_name     => 'to_datea',
        argument_position => 2,
        argument_type     => 'VARCHAR2',
        default_value     => 'SHEDULER'
    );
    END;
   
   BEGIN 
  DBMS_SCHEDULER.DEFINE_PROGRAM_ARGUMENT(
        PROGRAM_NAME => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS',
        argument_name     => 'output1',
        argument_position => 3,
        argument_type     => 'VARCHAR2',
        default_value     => null
    );
    END;
   
   BEGIN 
  DBMS_SCHEDULER.DEFINE_PROGRAM_ARGUMENT(
        PROGRAM_NAME => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS',
        argument_name     => 'output2',
        argument_position => 4,
        argument_type     => 'VARCHAR2',
        default_value     => null
    );
    END;
   
BEGIN
dbms_scheduler.enable (name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS');
END;   

BEGIN 
DBMS_SCHEDULER.CREATE_SCHEDULE ( 
schedule_name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_SHEDULER', 
start_date => SYSTIMESTAMP, 
repeat_interval => 'FREQ=HOURLY;INTERVAL=2', 
end_date => NULL, 
comments => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_SHEDULER'
);
END;

BEGIN
DBMS_SCHEDULER.SET_ATTRIBUTE (
name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_SHEDULER',
attribute => 'start_date',
value => );
END;



BEGIN 
DBMS_SCHEDULER.CREATE_JOB ( 
job_name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_JOB', 
program_name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS', 
schedule_name => 'DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_SHEDULER'); 
END;


BEGIN 
dbms_scheduler.enable('DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_JOB');
END;

BEGIN 
dbms_scheduler.disable('DIRECT_HT_IMPORT_ADJUSTMENT_UNITS_JOB');
END;
