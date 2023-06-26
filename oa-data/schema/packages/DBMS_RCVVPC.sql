CREATE OR REPLACE PACKAGE dbms_rcvvpc
IS
 
  FUNCTION filter_pass_all (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_vpc_databases (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_db (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_dbinc (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bsf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bs (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_conf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_deleted_object (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_do_seq (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_node (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_pdb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rout (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_watermarks (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_sbt_template_db (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_pdbinc (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_pdb_dbinc (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_al (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bcf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bdf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_brl (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_ccf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_cdf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_ckp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_df (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_fb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_grsp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_nrsp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_offr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_orl (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rlh (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rsr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_tf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_tsatt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_ts (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_xal (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_xcf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_xdf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_scr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rrcache (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bcb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_ccb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_scrl (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_cfs (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_config (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_orsevent (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_rcfile (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_xmlstore (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_bcr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_server (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_vpc_users (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_site_dfatt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
  FUNCTION f_site_tfatt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2;
 
END dbms_rcvvpc;









CREATE OR REPLACE PACKAGE BODY dbms_rcvvpc
AS
 
  g_catowner_uid                  CONSTANT user_users.username%TYPE :=
                                    SYS_CONTEXT('USERENV', 'CURRENT_USERID');
 
  FUNCTION is_ra RETURN BOOLEAN;
  this_is_ra                      CONSTANT BOOLEAN := is_ra;
 
  SUBTYPE predicate_t IS VARCHAR2(256);
  TYPE predicates_t IS VARRAY(14) OF predicate_t;
 
  g_p                             CONSTANT predicates_t :=
    predicates_t(
      'filter_user = SYS_CONTEXT(''USERENV'', ''SESSION_USER'')'  -- 1
    , 'db_id IN (SELECT vpc.db_id FROM vpc_databases vpc)'        -- 2
    , 'db_key IN (SELECT db.db_key FROM db)'                      -- 3
    , 'pdb_key IN (SELECT pdb.pdb_key FROM pdb)'                  -- 4
    , 'dbinc_key IN (SELECT dbinc.dbinc_key FROM dbinc)'          -- 5
    , 'db_key IN (SELECT db.db_key FROM db) OR db_key IS NULL'    -- 6
    , 'bdf_key IN (SELECT bdf.bdf_key FROM bdf)'                  -- 7
    , 'cdf_key IN (SELECT cdf.cdf_key FROM cdf)'                  -- 8
    , 'scr_key IN (SELECT scr.scr_key FROM scr)'                  -- 9
    , 'EXISTS (SELECT NULL FROM vpc_databases)'                   -- 10
    , 'df_key IN (SELECT df.df_key FROM df)'                      -- 11
    , 'created_user = SYS_CONTEXT(''USERENV'', ''SESSION_USER'')' -- 12
    , 'filter_user = SYS_CONTEXT(''USERENV'', ''SESSION_USER'')'  -- 13
    , 'site_key IN (SELECT node.site_key FROM node)'              -- 14
    );
 
  FUNCTION is_ra
  RETURN BOOLEAN
  IS
    l_owner                        all_users.username%TYPE;
  BEGIN
    EXECUTE IMMEDIATE 'BEGIN :owner := dbms_rai_owner; END;'
      USING OUT l_owner;
    RETURN (l_owner IS NOT NULL);
  EXCEPTION
    WHEN OTHERS
    THEN RETURN FALSE;
  END is_ra;
 
  PROCEDURE p (
    i_table                        VARCHAR2
  , i_msg                          VARCHAR2 DEFAULT NULL
  )
  IS
  BEGIN
$IF FALSE
$THEN
    sys.dbms_system.ksdwrt (
       sys.dbms_system.alert_file
     , 'filter_client_data: '
    || SYS_CONTEXT('USERENV', 'SID')
    || ' '
    || i_table
    || ' '
    || SYS_CONTEXT('USERENV', 'CURRENT_USER')
    || ' '
    || SYS_CONTEXT('USERENV', 'SESSION_USER')
    || ' ['
    || i_msg
    || ']'
    );
$ELSE
    NULL;
$END
  END p;
 
  FUNCTION filter_pass_all (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN NULL;
  END filter_pass_all;
 
  FUNCTION f_p (
    i_table                        IN VARCHAR2
  , i_predicate                    IN BINARY_INTEGER
  )
  RETURN VARCHAR2
  IS
  BEGIN
--
--
--
--
--
--
--
--
--
--
--
--
--
--
--
    IF (SYS_CONTEXT('USERENV', 'SESSION_USERID') IN (g_catowner_uid, 0))
    THEN
      p(i_table, NULL);
      RETURN NULL;
    ELSE
      p(i_table, g_p(i_predicate));
      RETURN
         g_p(i_predicate)
      || CASE
           WHEN this_is_ra
           THEN
           q'{ OR SYS_CONTEXT('SYS_SESSION_ROLES','RA_CATALOG_SELECT')='TRUE'}'
         END
      || q'{ OR SYS_CONTEXT('SYS_SESSION_ROLES','RMAN_CATALOG_ACCESS')='TRUE'}';
    END IF;
  END f_p;
 
  FUNCTION f_vpc_databases (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 1);
  END f_vpc_databases;
 
  FUNCTION f_db (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 2);
  END f_db;
 
  FUNCTION f_dbinc (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_dbinc;
 
  FUNCTION f_bp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_bp;
 
  FUNCTION f_bsf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_bsf;
 
  FUNCTION f_bs (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_bs;
 
  FUNCTION f_conf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_conf;
 
  FUNCTION f_deleted_object (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_deleted_object;
 
  FUNCTION f_do_seq (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_do_seq;
 
  FUNCTION f_node (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_node;
 
  FUNCTION f_pdb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_pdb;
 
  FUNCTION f_rout (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_rout;
 
  FUNCTION f_watermarks (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_watermarks;
 
  FUNCTION f_sbt_template_db (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_sbt_template_db;
 
  FUNCTION f_rrcache (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 3);
  END f_rrcache;
 
  FUNCTION f_pdbinc (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 4);
  END f_pdbinc;
 
  FUNCTION f_pdb_dbinc (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 4);
  END f_pdb_dbinc;
 
  FUNCTION f_al (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_al;
 
  FUNCTION f_bcf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_bcf;
 
  FUNCTION f_bdf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_bdf;
 
  FUNCTION f_brl (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_brl;
 
  FUNCTION f_ccf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_ccf;
 
  FUNCTION f_cdf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_cdf;
 
  FUNCTION f_ckp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_ckp;
 
  FUNCTION f_df (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_df;
 
  FUNCTION f_fb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_fb;
 
  FUNCTION f_grsp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_grsp;
 
  FUNCTION f_nrsp (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_nrsp;
 
  FUNCTION f_offr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_offr;
 
  FUNCTION f_orl (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_orl;
 
  FUNCTION f_rlh (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_rlh;
 
  FUNCTION f_rr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_rr;
 
  FUNCTION f_rsr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_rsr;
 
  FUNCTION f_rt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_rt;
 
  FUNCTION f_tf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_tf;
 
  FUNCTION f_tsatt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_tsatt;
 
  FUNCTION f_ts (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_ts;
 
  FUNCTION f_xal (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_xal;
 
  FUNCTION f_xcf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_xcf;
 
  FUNCTION f_xdf (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 5);
  END f_xdf;
 
  FUNCTION f_scr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 6);
  END f_scr;
 
  FUNCTION f_bcb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 7);
  END f_bcb;
 
  FUNCTION f_ccb (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 8);
  END f_ccb;
 
  FUNCTION f_scrl (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 9);
  END f_scrl;
 
  FUNCTION f_cfs (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 10);
  END f_cfs;
 
  FUNCTION f_config (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 10);
  END f_config;
 
  FUNCTION f_orsevent (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 10);
  END f_orsevent;
 
  FUNCTION f_rcfile (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 10);
  END f_rcfile;
 
  FUNCTION f_bcr (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 11);
  END f_bcr;
 
  FUNCTION f_xmlstore (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 12);
  END f_xmlstore;
 
  FUNCTION f_server (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 13);
  END f_server;
 
  FUNCTION f_vpc_users (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 13);
  END f_vpc_users;
 
  FUNCTION f_site_dfatt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 14);
  END f_site_dfatt;
 
  FUNCTION f_site_tfatt (
    schema_p                       IN VARCHAR2
  , table_p                        IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    RETURN f_p(table_p, 14);
  END f_site_tfatt;
 
END dbms_rcvvpc;
