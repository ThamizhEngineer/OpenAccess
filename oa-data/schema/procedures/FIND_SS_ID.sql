CREATE OR REPLACE PROCEDURE OPENACCESS."FIND_SS_ID" AS
BEGIN

FOR i IN (
select f.id f_id, f.ss_name, s.id from m_feeder f, m_substation s
where f.ss_name = s.name
) LOOP
      update m_feeder set m_substation_id = i.id where ss_name = i.ss_name;
    END LOOP;

END FIND_SS_ID;


