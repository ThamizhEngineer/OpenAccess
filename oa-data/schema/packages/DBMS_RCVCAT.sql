CREATE OR REPLACE package dbms_rcvcat authid current_user is
 
--
TRUE#  CONSTANT number := 1;
FALSE# CONSTANT number := 0;
 
--
UPGRADE_COMPLETED CONSTANT number := 1;
 
--
RESYNC_FULL    CONSTANT number := 1;
RESYNC_PARTIAL CONSTANT number := 2;
RESYNC_NONE    CONSTANT number := 3;
 
CONFIGRESYNC_NO        CONSTANT number := 0;
CONFIGRESYNC_TORC      CONSTANT number := 1;
CONFIGRESYNC_TOCF      CONSTANT number := 2;
CONFIGRESYNC_TORC_TOCF CONSTANT number := 3;
 
--
--
CF_CURRENT     CONSTANT number := 1;
CF_BACKUP      CONSTANT number := 2;
CF_CREATED     CONSTANT number := 3;
CF_STANDBY     CONSTANT number := 4;
CF_CLONE       CONSTANT number := 5;
CF_NOMOUNT     CONSTANT number := 6;
 
--
--
this_db_key      number := NULL;
this_dbinc_key   number := NULL;
 
type registerDbPending_t is record
(
   dbid        number  := null,
   con_id      number  := null,
   guid        raw(16) := null
);
registerDbPending registerDbPending_t;
 
RESYNC_REASON_NOACTION    CONSTANT number := 1;  -- do not display reasons
RESYNC_REASON_NONE        CONSTANT number := 2;  -- no reason is yet set
RESYNC_REASON_DF          CONSTANT number := 3; 
RESYNC_REASON_TF          CONSTANT number := 4;
RESYNC_REASON_TS          CONSTANT number := 5;
RESYNC_REASON_THR         CONSTANT number := 6;
RESYNC_REASON_ORL         CONSTANT number := 7;
RESYNC_REASON_CONF        CONSTANT number := 8;
RESYNC_REASON_CF          CONSTANT number := 9;
RESYNC_REASON_RSL         CONSTANT number := 10;
RESYNC_REASON_INC         CONSTANT number := 11;
RESYNC_REASON_RESET       CONSTANT number := 12;
RESYNC_REASON_PDB         CONSTANT number := 13;
resync_reason     number  := RESYNC_REASON_NONE;
doResyncReasons   boolean := FALSE;
 
RESYNC_ACTION_ADD         CONSTANT number := 1;
RESYNC_ACTION_DROP        CONSTANT number := 2;
RESYNC_ACTION_CHANGE      CONSTANT number := 3;
RESYNC_ACTION_RECREATE    CONSTANT number := 4;
RESYNC_ACTION_RENAME      CONSTANT number := 5;
RESYNC_ACTION_RESIZE      CONSTANT number := 6;
 
TYPE resyncActionNames_t    IS VARRAY(6) OF varchar2(12);
 
--
RESYNC_ACTION_NAMES         CONSTANT resyncActionNames_t := 
                            resyncActionNames_t('added',   'dropped',
                                                'changed', 'recreated',
                                                'renamed', 'resized');
 
TYPE resyncActionTaken_t    IS VARRAY(6) OF boolean;
 
TYPE resyncActionCounts_t   IS VARRAY(6) OF number;
 
RESYNC_OBJECT_TABLESPACE    CONSTANT number := 1;
RESYNC_OBJECT_DATAFILE      CONSTANT number := 2;
RESYNC_OBJECT_TEMPFILE      CONSTANT number := 3;
RESYNC_OBJECT_REDOTHREAD    CONSTANT number := 4;
RESYNC_OBJECT_ONLINELOG     CONSTANT number := 5;
RESYNC_OBJECT_PDB           CONSTANT number := 6;
 
TYPE resyncActionObjects_t  IS VARRAY(5) OF varchar2(16);
 
--
RESYNC_ACTION_OBJECTS       CONSTANT resyncActionObjects_t :=
                            resyncActionObjects_t('Tablespace', 'Datafile', 
                                                  'Tempfile',   'Redo thread', 
                                                  'Online redo log');
--
RCVCAT_LEVEL_ZERO           CONSTANT number := 0;  
RCVCAT_LEVEL_MIN            CONSTANT number := 1;
RCVCAT_LEVEL_LOW            CONSTANT number := 5;
RCVCAT_LEVEL_MID            CONSTANT number := 9;
RCVCAT_LEVEL_HI             CONSTANT number := 12;
RCVCAT_LEVEL_MAX            CONSTANT number := 15;
RCVCAT_LEVEL_DEFAULT        CONSTANT number := RCVCAT_LEVEL_MID; 
 
TYPE fullResyncActions_t IS RECORD
(
   active          boolean,
   valid           boolean,
   lastobjno       number,
   objtype         number,
   actTaken        resyncActionTaken_t,
   actCount        resyncActionCounts_t
);
 
fullResyncAction   fullResyncActions_t; -- :=
--
--
--
 
/*-----------------------*
 * Debugging functions   *
 *-----------------------*/
 
PROCEDURE setDebugOn(dbglevel IN NUMBER DEFAULT RCVCAT_LEVEL_DEFAULT);
 
PROCEDURE setDebugOff;
 
/*-----------------------*
 * Database Registration *
 *-----------------------*/
 
PROCEDURE registerDatabase(
   db_id          IN number
  ,db_name        IN varchar2
  ,reset_scn      IN number
  ,reset_time     IN date
  ,db_unique_name IN varchar2 default null
  ,con_id         IN number   default 0
  ,guid           IN raw      default null
);
 
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
--
--
--
--
 
 
procedure resetDatabase(
  db_id             IN number
 ,db_name           IN varchar2
 ,reset_scn         IN number
 ,reset_time        IN date
 ,parent_reset_scn  IN number
 ,parent_reset_time IN date
);
 
function resetDatabase(
  db_id             IN number
 ,db_name           IN varchar2
 ,reset_scn         IN number
 ,reset_time        IN date
 ,parent_reset_scn  IN number
 ,parent_reset_time IN date
) return number;
 
procedure resetDatabase(
  dbinc_key  IN number
 ,db_name    IN varchar2
);
 
procedure resetDatabase(
  dbinc_key  IN number
 ,db_name    IN varchar2
 ,reset_scn  OUT number
 ,reset_time OUT date
 ,db_id      IN number DEFAULT NULL
);
 
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
 
 
procedure unregisterDatabase(
  db_key     IN NUMBER DEFAULT NULL
 ,db_id      IN NUMBER
);
 
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
--
--
--
--
--
 
/*--------------------------*
 * Set Database Incarnation *
 *--------------------------*/
 
procedure setDatabase(
  db_name         IN varchar2
 ,reset_scn       IN number
 ,reset_time      IN date
 ,db_id           IN number
 ,db_unique_name  IN varchar2
 ,dummy_instance  IN boolean
 ,cf_type         IN number
 ,site_aware      IN boolean default FALSE
 ,ors_instance    IN boolean default FALSE
);
 
procedure setDatabase(
  db_name         IN varchar2
 ,reset_scn       IN number
 ,reset_time      IN date
 ,db_id           IN number
 ,db_unique_name  IN varchar2 default NULL);
 
procedure setDatabase(dbinc_key number);
 
procedure setDatabase;
 
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
--
--
--
 
/*-----------------------------*
 * Recovery Catalog Checkpoint *
 *-----------------------------*/
function ckptNeeded(
  ckp_scn          IN number
 ,ckp_cf_seq       IN number
 ,cf_version       IN date
 ,cf_type          IN number
 ,high_df_recid    IN number
 ,high_orl_recid   IN number
 ,high_cdf_recid   IN number
 ,high_al_recid    IN number
 ,high_bp_recid    IN number
 ,high_do_recid    IN number
 ,high_offr_recid  IN number
 ,high_pc_recid    IN number  DEFAULT NULL -- for compatibility
 ,high_conf_recid  IN number  DEFAULT NULL -- for compatibility
 ,rltime           IN DATE    DEFAULT NULL -- for compatibility
 ,high_ts_recid    IN number  DEFAULT NULL -- for compatibility
 ,high_bs_recid    IN number  DEFAULT NULL -- for compatibility
 ,lopen_reset_scn  IN number  DEFAULT NULL -- for compatibility
 ,lopen_reset_time IN DATE    DEFAULT NULL -- for compatibility
 ,high_ic_recid    IN number  DEFAULT NULL -- for compatibility
 ,high_tf_recid    IN number  DEFAULT NULL -- for compatibility
 ,high_rt_recid    IN number  DEFAULT NULL -- for compatibility
 ,high_grsp_recid  IN number  DEFAULT NULL -- for compatibility
 ,high_nrsp_recid  IN number  DEFAULT NULL -- for compatibility
 ,high_bcr_recid   IN number  DEFAULT NULL -- for compatibility
 ,high_pdb_recid   IN number  DEFAULT NULL -- for compatibility
 ,high_pic_recid   IN number  DEFAULT NULL -- for compatibility
) return number;
 
PROCEDURE lockForCkpt(ors_inspect IN boolean DEFAULT FALSE);
 
procedure beginCkpt(
  ckp_scn       IN number
 ,ckp_cf_seq    IN number
 ,cf_version    IN date
 ,ckp_time      IN date
 ,ckp_type      IN varchar2
 ,ckp_db_status IN varchar2
 ,high_df_recid IN number
 ,cf_type       IN varchar2 DEFAULT 'CURRENT'   -- for compatibility reasons
);
 
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
--
--
--
--
--
--
--
 
procedure endCkpt;
 
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
 
procedure cancelCkpt;
 
--
--
--
 
--
--
function lastFullCkpt return number;
 
--
--
FUNCTION getPolledRec(rec_type OUT NUMBER,
                      recid    OUT NUMBER,
                      stamp    OUT NUMBER,
                      fname    OUT VARCHAR2) RETURN BOOLEAN;
 
/*-------------------*
 * Resync            *
 *-------------------*/
 
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
 
/*---------------------*
 * Pluggable DB Resync *
 *---------------------*/
 
FUNCTION beginPluggableDBResync(
  high_pdb_recid IN NUMBER)
RETURN BOOLEAN;
 
--
--
--
 
PROCEDURE checkPluggableDB(
  name       IN VARCHAR2
 ,con_id     IN NUMBER
 ,db_id      IN NUMBER
 ,create_scn IN NUMBER
 ,guid       IN RAW
 ,noBackup   IN VARCHAR2 DEFAULT 'N'
);
 
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
--
--
--
 
PROCEDURE endPluggableDBResync;
 
--
--
 
/*-------------------*
 * Tablespace Resync *
 *-------------------*/
 
function beginTableSpaceResync(
  high_ts_recid IN NUMBER,
  force         IN BOOLEAN DEFAULT FALSE
) return boolean;
 
--
--
 
--
--
--
 
--
--
 
 
procedure checkTableSpace(
  ts_name                     IN varchar2
 ,ts#                         IN number
 ,create_scn                  IN number
 ,create_time                 IN date
 ,rbs_count                   IN number   DEFAULT NULL
 ,included_in_database_backup IN varchar2 DEFAULT NULL
 ,bigfile                     IN varchar2 DEFAULT NULL
 ,temporary                   IN varchar2 DEFAULT NULL
 ,encrypt_in_backup           IN varchar2 DEFAULT NULL
 ,plugin_scn                  IN number   DEFAULT 0
 ,con_id                      IN number   DEFAULT 0
 ,pdb_dict_check              IN boolean  DEFAULT FALSE
);
 
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
 
procedure endTableSpaceResync;
 
--
 
--
--
 
 
/*-----------------*
 * Datafile Resync *
 *-----------------*/
 
function beginDataFileResync(
  high_df_recid IN number
) return boolean;
 
 
--
--
--
 
--
--
 
procedure checkDataFile(file#               IN NUMBER,
                        fname               IN VARCHAR2,
                        create_scn          IN NUMBER,
                        create_time         IN DATE,
                        blocks              IN NUMBER,
                        block_size          IN NUMBER,
                        ts#                 IN NUMBER,
                        stop_scn            IN NUMBER,
                        read_only           IN NUMBER,
                        stop_time           IN DATE     DEFAULT NULL,
                        rfile#              IN NUMBER   DEFAULT NULL,
                        aux_fname           IN VARCHAR2 DEFAULT NULL,
                        foreign_dbid        IN NUMBER   DEFAULT 0,
                        foreign_create_scn  IN NUMBER   DEFAULT 0,
                        foreign_create_time IN DATE     DEFAULT NULL,
                        plugged_readonly    IN VARCHAR2 DEFAULT 'NO',
                        plugin_scn          IN NUMBER   DEFAULT 0,
                        plugin_reset_scn    IN NUMBER   DEFAULT 0,
                        plugin_reset_time   IN DATE     DEFAULT NULL,
                        create_thread       IN NUMBER   DEFAULT NULL,
                        create_size         IN NUMBER   DEFAULT NULL,
                        con_id              IN NUMBER   DEFAULT 0,
                        pdb_closed          IN NUMBER   DEFAULT 0,
                        pdb_dict_check      IN BOOLEAN  DEFAULT FALSE,
                        pdb_foreign_dbid    IN NUMBER   DEFAULT 0);
 
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
--
--
 
--
--
 
procedure endDataFileResync;
 
--
 
--
--
 
--
--
 
function beginDataFileResyncForStandby(
  high_df_recid IN number
) return boolean;
 
procedure checkDataFileForStandby(
                        file#               IN NUMBER,
                        fname               IN VARCHAR2,
                        create_scn          IN NUMBER,
                        create_time         IN DATE,
                        blocks              IN NUMBER,
                        block_size          IN NUMBER,
                        ts#                 IN NUMBER,
                        rfile#              IN NUMBER,
                        stop_scn            IN NUMBER,
                        read_only           IN NUMBER,
                        foreign_dbid        IN NUMBER,
                        plugin_scn          IN NUMBER);
 
--
--
--
--
--
--
 
procedure endDataFileResyncForStandby;
 
function beginTempFileResyncForStandby(
  high_tf_recid IN number
) return boolean;
 
procedure checkTempFileForStandby
                       (file#          IN NUMBER,
                        fname          IN VARCHAR2,
                        create_scn     IN NUMBER,
                        create_time    IN DATE,
                        blocks         IN NUMBER,
                        block_size     IN NUMBER,
                        ts#            IN NUMBER,
                        rfile#         IN NUMBER,
                        autoextend     IN VARCHAR2,
                        max_size       IN NUMBER,
                        next_size      IN NUMBER,
                        con_id         IN NUMBER DEFAULT 0);
 
procedure endTempFileResyncForStandby;
 
procedure setDatafileSize(
  file#       IN number
 ,create_scn  IN number
 ,blocks      IN number
 ,plugin_scn  IN number DEFAULT 0
);
 
/*-----------------*
 * TempFile Resync *
 *-----------------*/
 
function tempFileToResync(
  high_tf_recid IN number
) return boolean;
 
--
--
--
--
--
 
function beginTempFileResync(
  high_tf_recid IN number
) return boolean;
 
 
--
--
--
 
--
--
 
procedure checkTempFile(file#          IN NUMBER,
                        fname          IN VARCHAR2,
                        create_scn     IN NUMBER,
                        create_time    IN DATE,
                        blocks         IN NUMBER,
                        block_size     IN NUMBER,
                        ts#            IN NUMBER,
                        rfile#         IN NUMBER,
                        autoextend     IN VARCHAR2,
                        max_size       IN NUMBER,
                        next_size      IN NUMBER,
                        con_id         IN NUMBER  DEFAULT 0,
                        pdb_dict_check IN BOOLEAN DEFAULT FALSE);
 
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
--
--
--
--
 
--
--
 
procedure endTempFileResync;
 
--
 
--
--
 
/*---------------------*
 * Redo Thread resync  *
 *---------------------*/
 
function beginThreadResync(
  high_rt_recid IN number
) return boolean;
 
 
--
 
 
procedure checkThread(
  thread#        IN number
 ,last_sequence# IN number
 ,enable_scn     IN number
 ,enable_time    IN date
 ,disable_scn    IN number
 ,disable_time   IN date
 ,status         IN varchar2
);
 
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
--
--
 
 
procedure endThreadResync;
 
--
 
 
 
/*------------------------*
 * Online Redo Log resync *
 *------------------------*/
 
function beginOnlineRedoLogResync(
  high_orl_recid IN number
) return boolean;
 
--
 
procedure checkOnlineRedoLog(
  thread#        IN number
 ,group#         IN number
 ,fname          IN varchar2
 ,bytes          IN number   default null
 ,type           IN varchar2 default 'ONLINE'
);
 
--
--
--
 
procedure endOnlineRedoLogResync;
 
--
 
/*---------------------------------*
 * Guaranteed Restore Point Resync *
 *---------------------------------*/
 
function beginGuaranteedRPResync(
  high_grsp_recid IN number
) return boolean;
 
--
 
PROCEDURE checkGuaranteedRP(
  rspname            IN VARCHAR2
 ,from_scn           IN NUMBER
 ,to_scn             IN NUMBER
 ,resetlogs_change#  IN NUMBER
 ,resetlogs_time     IN DATE
 ,create_time        IN DATE DEFAULT NULL
 ,rsp_time           IN DATE DEFAULT NULL
 ,guaranteed         IN VARCHAR2 DEFAULT 'YES'
 ,con_id             IN NUMBER   DEFAULT NULL
 ,clean              IN VARCHAR2 DEFAULT 'NO'
);
 
--
--
--
 
procedure endGuaranteedRPResync;
 
--
--
 
 
/*----------------------------------*
 * RMAN Configration records resync *
 *----------------------------------*/
 
function beginConfigResync(
  high_conf_recid IN number
) return number;
 
function beginConfigResync2(
  high_conf_recid IN number
) return number;
 
--
--
 
procedure endConfigResync;
 
procedure endConfigResync2(sync_to_cf_pending IN boolean DEFAULT FALSE);
 
--
--
 
procedure getConfig(
   conf#          OUT    number
  ,name           IN OUT varchar2
  ,value          IN OUT varchar2
  ,first          IN     boolean);
 
--
--
 
PROCEDURE setKeepOutputForSession(days IN number);
--
 
/*-----------------------------*
 * Redo Log History resync     *
 *-----------------------------*/
 
function beginLogHistoryResync return number;
 
--
--
--
--
 
function getLogHistoryLowSCN return number;
 
--
--
--
--
 
procedure checkLogHistory(
  rlh_recid   IN number
 ,rlh_stamp   IN number
 ,thread#     IN number
 ,sequence#   IN number
 ,low_scn     IN number
 ,low_time    IN date
 ,next_scn    IN number
 ,reset_scn   IN number default NULL
 ,reset_time  IN date default NULL
);
 
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
--
--
--
--
--
--
--
--
--
 
 
procedure endLogHistoryResync;
 
--
--
 
 
 
/*-------------------------*
 * Archived Log resync     *
 *-------------------------*/
 
function beginArchivedLogResync return number;
 
--
 
 
procedure checkArchivedLog(
  al_recid    IN number
 ,al_stamp    IN number
 ,thread#     IN number
 ,sequence#   IN number
 ,reset_scn   IN number
 ,reset_time  IN date
 ,low_scn     IN number
 ,low_time    IN date
 ,next_scn    IN number
 ,next_time   IN date
 ,blocks      IN number
 ,block_size  IN number
 ,fname       IN varchar2
 ,archived    IN varchar2
 ,completion_time IN date
 ,status      IN varchar2
 ,is_standby  IN varchar2 DEFAULT NULL
 ,dictionary_begin IN varchar2 DEFAULT NULL
 ,dictionary_end   IN varchar2 DEFAULT NULL
 ,is_recovery_dest_file 
                   IN varchar2 default 'NO'
 ,compressed       IN varchar2 default 'NO'
 ,creator          IN varchar2 default NULL
 ,terminal         IN varchar2 default 'NO'
 ,chk_last_recid   IN boolean  DEFAULT TRUE
);
 
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
--
--
--
--
--
--
--
--
--
 
 
 
procedure endArchivedLogResync;
 
--
--
 
 
 
/*-------------------------*
 * Offline range resync    *
 *-------------------------*/
 
function beginOfflineRangeResync return number;
 
--
--
 
 
procedure checkOfflineRange(
  offr_recid     IN number
 ,offr_stamp     IN number
 ,file#          IN number
 ,create_scn     IN number
 ,offline_scn    IN number
 ,online_scn     IN number
 ,online_time    IN date
 ,cf_create_time IN date
 ,reset_scn   IN number default NULL
 ,reset_time  IN date default NULL
);
 
--
 
 
--
--
--
--
--
 
 
procedure endOfflineRangeResync;
 
--
 
 
 
/*-------------------------*
 * Backup Set resync       *
 *-------------------------*/
 
function beginBackupSetResync return number;
 
--
--
 
 
procedure checkBackupSet(
  bs_recid        IN number
 ,bs_stamp        IN number
 ,set_stamp       IN number
 ,set_count       IN number
 ,bck_type        IN varchar2
 ,incr_level      IN number         DEFAULT NULL
 ,pieces          IN number
 ,start_time      IN date
 ,completion_time IN date
 ,controlfile_included
                  IN VARCHAR2       DEFAULT NULL
 ,input_file_scan_only
                  IN VARCHAR2       DEFAULT NULL
 ,keep_options    IN number         DEFAULT 0
 ,keep_until      IN date           DEFAULT NULL
 ,block_size      IN number         DEFAULT NULL
 ,multi_section   IN varchar2       DEFAULT NULL
 ,chk_last_recid  IN boolean        DEFAULT TRUE
 ,guid            IN raw            DEFAULT NULL
 ,dropped_pdb     IN binary_integer DEFAULT 0 
);
 
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
--
 
procedure endBackupSetResync;
 
--
 
 
 
/*-------------------------*
 * Backup piece resync     *
 *-------------------------*/
 
function beginBackupPieceResync return number;
 
 
procedure checkBackupPiece(
  bp_recid                IN number
 ,bp_stamp                IN number
 ,set_stamp               IN number
 ,set_count               IN number
 ,piece#                  IN number
 ,tag                     IN varchar2
 ,device_type             IN varchar2
 ,handle                  IN varchar2
 ,comments                IN varchar2
 ,media                   IN varchar2
 ,concur                  IN varchar2
 ,start_time              IN date
 ,completion_time         IN date
 ,status                  IN varchar2
 ,copy#                   IN number         default 1
 ,media_pool              IN number         default 0
 ,bytes                   IN number         default NULL
 ,is_recovery_dest_file   IN varchar2       default 'NO'
 ,rsr_recid               IN number         default NULL
 ,rsr_stamp               IN number         default NULL
 ,compressed              IN varchar2       default 'NO'
 ,encrypted               IN varchar2       default 'NO'
 ,backed_by_osb           IN varchar2       default 'NO'
 ,ba_access               IN varchar2       default 'U'
 ,vbkey                   IN number         default NULL
 ,chk_last_recid          IN boolean        default TRUE
 ,lib_key                 IN number         default NULL
 ,guid                    IN raw            default NULL
 ,template_key            IN number         default NULL
 ,dropped_pdb             IN binary_integer default 0
);
 
function checkBackupPiece(
  bp_recid                IN number
 ,bp_stamp                IN number
 ,set_stamp               IN number
 ,set_count               IN number
 ,piece#                  IN number
 ,tag                     IN varchar2
 ,device_type             IN varchar2
 ,handle                  IN varchar2
 ,comments                IN varchar2
 ,media                   IN varchar2
 ,concur                  IN varchar2
 ,start_time              IN date
 ,completion_time         IN date
 ,status                  IN varchar2
 ,copy#                   IN number         default 1
 ,media_pool              IN number         default 0
 ,bytes                   IN number         default NULL
 ,is_recovery_dest_file   IN varchar2       default 'NO'
 ,rsr_recid               IN number         default NULL
 ,rsr_stamp               IN number         default NULL
 ,compressed              IN varchar2       default 'NO'
 ,encrypted               IN varchar2       default 'NO'
 ,backed_by_osb           IN varchar2       default 'NO'
 ,ba_access               IN varchar2       default 'U'
 ,vbkey                   IN number         default NULL
 ,chk_last_recid          IN boolean        default TRUE
 ,lib_key                 IN number         default NULL
 ,guid                    IN raw            default NULL
 ,template_key            IN number         default NULL
 ,dropped_pdb             IN binary_integer default 0
) return number;
 
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
--
--
--
--
 
procedure endBackupPieceResync;
 
 
 
/*-------------------------*
 * Backup Datafile resync  *
 *-------------------------*/
 
function beginBackupDataFileResync return number;
 
procedure checkBackupDataFile(
  bdf_recid           IN number
 ,bdf_stamp           IN number
 ,set_stamp           IN number
 ,set_count           IN number
 ,file#               IN number
 ,create_scn          IN number
 ,create_time         IN date
 ,reset_scn           IN number
 ,reset_time          IN date
 ,incr_level          IN number
 ,incr_scn            IN number
 ,ckp_scn             IN number
 ,ckp_time            IN date
 ,abs_fuzzy_scn       IN number
 ,datafile_blocks     IN number
 ,blocks              IN number
 ,block_size          IN number
 ,min_offr_recid      IN number
 ,completion_time     IN date
 ,controlfile_type    IN varchar2       DEFAULT NULL
 ,cfile_abck_year     IN number         DEFAULT NULL
 ,cfile_abck_mon_day  IN number         DEFAULT NULL
 ,cfile_abck_seq      IN number         DEFAULT NULL
 ,chk_last_recid      IN boolean        DEFAULT TRUE
 ,blocks_read         IN number         DEFAULT NULL
 ,used_chg_track      IN varchar2       DEFAULT 'NO'
 ,used_optim          IN varchar2       DEFAULT 'NO'
 ,foreign_dbid        IN number         DEFAULT 0
 ,plugged_readonly    IN varchar2       DEFAULT 'NO'
 ,plugin_scn          IN number         DEFAULT 0
 ,plugin_reset_scn    IN number         DEFAULT 0
 ,plugin_reset_time   IN date           DEFAULT NULL
 ,section_size        IN number         DEFAULT NULL
 ,guid                IN raw            DEFAULT NULL
 ,sparse_backup       IN varchar2       DEFAULT 'NO'
 ,isResync            IN boolean        DEFAULT TRUE
 ,isVirtual           IN boolean        DEFAULT FALSE
 ,dropped_pdb         IN binary_integer DEFAULT 0
);
 
 
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
--
--
--
 
procedure endBackupDataFileResync;
 
/*-------------------------*
 * Backup SPFILE resync  *
 *-------------------------*/
 
function beginBackupSpFileResync return number;
 
procedure checkBackupSpFile(
  bsf_recid         IN number
 ,bsf_stamp         IN number
 ,set_stamp         IN number
 ,set_count         IN number
 ,modification_time IN date
 ,bytes             IN number
 ,chk_last_recid    IN boolean        DEFAULT TRUE
 ,db_unique_name    IN varchar2       DEFAULT NULL
 ,guid              IN raw            DEFAULT NULL
 ,dropped_pdb       IN binary_integer DEFAULT 0
);
 
 
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
--
--
--
--
--
 
 
procedure endBackupSpFileResync;
 
/*-------------------------*
 * Backup Redo Log resync  *
 *-------------------------*/
 
function beginBackupRedoLogResync return number;
 
procedure checkBackupRedoLog(
  brl_recid      IN number
 ,brl_stamp      IN number
 ,set_stamp      IN number
 ,set_count      IN number
 ,thread#        IN number
 ,sequence#      IN number
 ,reset_scn      IN number
 ,reset_time     IN date
 ,low_scn        IN number
 ,low_time       IN date
 ,next_scn       IN number
 ,next_time      IN date
 ,blocks         IN number
 ,block_size     IN number
 ,chk_last_recid IN boolean  DEFAULT TRUE
 ,terminal       IN varchar2 DEFAULT 'NO'
 ,activation     IN varchar2 DEFAULT NULL
);
 
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
 
procedure endBackupRedoLogResync;
 
 
/*----------------------------*
 * Copy Datafile resync       *
 *----------------------------*/
 
function beginDataFileCopyResync return number;
 
procedure checkDataFileCopy(
  cdf_recid             IN number
 ,cdf_stamp             IN number
 ,fname                 IN varchar2
 ,tag                   IN varchar2
 ,file#                 IN number
 ,create_scn            IN number
 ,create_time           IN date
 ,reset_scn             IN number
 ,reset_time            IN date
 ,incr_level            IN number
 ,ckp_scn               IN number
 ,ckp_time              IN date
 ,onl_fuzzy             IN varchar2
 ,bck_fuzzy             IN varchar2
 ,abs_fuzzy_scn         IN number
 ,rcv_fuzzy_scn         IN number
 ,rcv_fuzzy_time        IN date
 ,blocks                IN number
 ,block_size            IN number
 ,min_offr_recid        IN number
 ,completion_time       IN date
 ,status                IN varchar2
 ,controlfile_type      IN varchar2       DEFAULT NULL
 ,keep_options          IN number         DEFAULT 0
 ,keep_until            IN date           DEFAULT NULL
 ,scanned               IN varchar2       DEFAULT 'NO'
 ,is_recovery_dest_file IN varchar2       DEFAULT 'NO'
 ,rsr_recid             IN number         DEFAULT NULL
 ,rsr_stamp             IN number         DEFAULT NULL
 ,marked_corrupt        IN number         DEFAULT NULL
 ,foreign_dbid          IN number         DEFAULT 0
 ,plugged_readonly      IN varchar2       DEFAULT 'NO'
 ,plugin_scn            IN number         DEFAULT 0
 ,plugin_reset_scn      IN number         DEFAULT 0
 ,plugin_reset_time     IN date           DEFAULT NULL
 ,guid                  IN raw            DEFAULT NULL
 ,sparse_backup         IN varchar2       DEFAULT 'NO'
 ,dropped_pdb           IN binary_integer DEFAULT 0
);
 
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
--
 
procedure endDataFileCopyResync;
 
 
/*----------------------------*
 * Corrupt Block resync       *
 *----------------------------*/
 
function beginBackupCorruptionResync return number;
 
procedure checkBackupCorruption(
  bcb_recid       IN number
 ,bcb_stamp       IN number
 ,set_stamp       IN number
 ,set_count       IN number
 ,piece#          IN number
 ,file#           IN number
 ,block#          IN number
 ,blocks          IN number
 ,corrupt_scn     IN number
 ,marked_corrupt  IN varchar2
 ,corruption_type IN varchar2 default NULL
);
 
procedure endBackupCorruptionResync;
 
 
function beginCopyCorruptionResync return number;
 
procedure checkCopyCorruption(
  ccb_recid       IN number
 ,ccb_stamp       IN number
 ,cdf_recid       IN number
 ,cdf_stamp       IN number
 ,file#           IN number
 ,block#          IN number
 ,blocks          IN number
 ,corrupt_scn     IN number
 ,marked_corrupt  IN varchar2
 ,corruption_type IN varchar2 default NULL
);
 
procedure endCopyCorruptionResync;
 
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
--
--
--
 
 
 
/*----------------------------*
 * Deleted Object resync      *
 *----------------------------*/
 
function beginDeletedObjectResync return number;
 
procedure checkDeletedObject(
  do_recid          IN number
 ,do_stamp          IN number
 ,object_type       IN varchar2
 ,object_recid      IN number
 ,object_stamp      IN number
 ,object_data       IN number   DEFAULT NULL
 ,object_fname      IN varchar2 DEFAULT NULL
 ,object_create_scn IN number   DEFAULT NULL
 ,set_stamp         IN number   DEFAULT NULL
 ,set_count         IN number   DEFAULT NULL
 ,handle            IN VARCHAR2 DEFAULT NULL
 ,device_type       IN VARCHAR2 DEFAULT NULL
);
 
procedure endDeletedObjectResync;
 
/*-------------------*
 * Proxy Copy resync *
 *-------------------*/
 
function beginProxyResync return number;
 
procedure checkProxyDataFile(
  xdf_recid         IN number
 ,xdf_stamp         IN number
 ,tag               IN varchar2
 ,file#             IN number
 ,create_scn        IN number
 ,create_time       IN date
 ,reset_scn         IN number
 ,reset_time        IN date
 ,incr_level        IN number
 ,ckp_scn           IN number
 ,ckp_time          IN date
 ,onl_fuzzy         IN varchar2
 ,bck_fuzzy         IN varchar2
 ,abs_fuzzy_scn     IN number
 ,rcv_fuzzy_scn     IN number
 ,rcv_fuzzy_time    IN date
 ,blocks            IN number
 ,block_size        IN number
 ,min_offr_recid    IN number
 ,device_type       IN varchar2
 ,handle            IN varchar2
 ,comments          IN varchar2
 ,media             IN varchar2
 ,media_pool        IN number
 ,start_time        IN date
 ,completion_time   IN date
 ,status            IN varchar2
 ,controlfile_type  IN varchar2       DEFAULT NULL
 ,keep_options      IN number         DEFAULT 0
 ,keep_until        IN date           DEFAULT NULL
 ,rsr_recid         IN number         DEFAULT NULL
 ,rsr_stamp         IN number         DEFAULT NULL
 ,foreign_dbid      IN number         DEFAULT 0
 ,plugged_readonly  IN varchar2       DEFAULT 'NO'
 ,plugin_scn        IN number         DEFAULT 0
 ,plugin_reset_scn  IN number         DEFAULT 0
 ,plugin_reset_time IN date           DEFAULT NULL
 ,guid              IN raw            DEFAULT NULL
 ,dropped_pdb       IN binary_integer DEFAULT 0
);
 
PROCEDURE checkProxyArchivedLog(
  xal_recid          IN NUMBER
 ,xal_stamp          IN NUMBER
 ,tag                IN VARCHAR2
 ,thread#            IN NUMBER
 ,sequence#          IN NUMBER
 ,resetlogs_change#  IN NUMBER
 ,resetlogs_time     IN DATE
 ,first_change#      IN NUMBER
 ,first_time         IN DATE
 ,next_change#       IN NUMBER
 ,next_time          IN DATE
 ,blocks             IN NUMBER
 ,block_size         IN NUMBER
 ,device_type        IN VARCHAR2
 ,handle             IN VARCHAR2
 ,comments           IN VARCHAR2
 ,media              IN VARCHAR2
 ,media_pool         IN NUMBER
 ,start_time         IN DATE
 ,completion_time    IN DATE
 ,status             IN VARCHAR2
 ,rsr_recid          IN NUMBER
 ,rsr_stamp          IN NUMBER
 ,terminal           IN VARCHAR2 default 'NO'
 ,keep_until         IN DATE     default NULL
 ,keep_options       IN NUMBER   default 0
);
 
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
 
procedure endProxyResync;
 
/*-------------------------*
 * Incarnation resync     *
 *-------------------------*/
 
FUNCTION beginIncarnationResync(return_Recid in boolean DEFAULT FALSE) 
                               return number;
--
--
--
--
 
function checkIncarnation(reset_scn         IN NUMBER,
                          reset_time        IN DATE,
                          prior_reset_scn   IN NUMBER DEFAULT NULL,
                          prior_reset_time  IN DATE DEFAULT NULL,
                          db_name           IN VARCHAR2 DEFAULT 'UNKNOWN')
                         return number;
--
--
--
 
procedure endIncarnationResync(high_kccdivts IN NUMBER, 
                               high_ic_recid IN NUMBER DEFAULT 0);
--
--
 
/*--------------------------------*
 * Pluggable DB Incaration Resync *
 *--------------------------------*/
FUNCTION beginPluggableDbincResync RETURN NUMBER;
 
--
--
--
 
PROCEDURE checkPluggableDbinc(
  recid               IN NUMBER
 ,guid                IN RAW
 ,curr_pdbinc         IN VARCHAR2
 ,inc_scn             IN NUMBER
 ,begin_reset_scn     IN NUMBER
 ,begin_reset_time    IN DATE
 ,end_reset_scn       IN NUMBER
 ,db_reset_scn        IN NUMBER
 ,db_reset_time       IN DATE
 ,pr_inc_scn          IN NUMBER
 ,pr_end_reset_scn    IN NUMBER
 ,pr_db_reset_scn     IN NUMBER
 ,pr_db_reset_time    IN DATE
 ,chk_last_recid      IN BOOLEAN
);
--
--
--
 
PROCEDURE endPluggableDbincResync(high_pic_recid IN NUMBER);
 
--
--
 
/*-----------------------------*
 * Normal restore point Resync *
 *-----------------------------*/
 
FUNCTION beginRestorePointResync RETURN NUMBER;
 
PROCEDURE checkRestorePoint(
  nrsp_recid    IN NUMBER
 ,nrsp_stamp    IN NUMBER
 ,nrsp_name     IN VARCHAR2
 ,reset_scn     IN NUMBER
 ,reset_time    IN DATE
 ,to_scn        IN NUMBER
 ,nrsp_time     IN DATE
 ,create_time   IN DATE
 ,deleted       IN NUMBER
 ,con_id        IN NUMBER   DEFAULT NULL
 ,clean         IN VARCHAR2 DEFAULT 'NO'
);
 
PROCEDURE endRestorePointResync(lowrecid IN number);
 
 
/*----------------------------*
 * RMAN Status resync         *
 *----------------------------*/
 
FUNCTION beginRmanStatusResync RETURN NUMBER;
--
--
 
 
PROCEDURE checkRmanStatus( recid            IN NUMBER
                          ,stamp            IN NUMBER
                          ,parent_recid     IN NUMBER
                          ,parent_stamp     IN NUMBER
                          ,row_level        IN NUMBER
                          ,row_type         IN VARCHAR2
                          ,command_id       IN VARCHAR2
                          ,operation        IN VARCHAR2
                          ,status           IN VARCHAR2
                          ,mbytes_processed IN NUMBER
                          ,start_time       IN DATE
                          ,end_time         IN DATE
                          ,ibytes           IN NUMBER default null
                          ,obytes           IN NUMBER default null
                          ,optimized        IN VARCHAR2 default null
                          ,otype            IN VARCHAR2 default null
                          ,session_recid    IN NUMBER default null
                          ,session_stamp    IN NUMBER default null
                          ,odevtype         IN VARCHAR2 default null
                          ,osb_allocated    IN VARCHAR2 default 'NO');
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
--
--
 
PROCEDURE endRmanStatusResync(recid number);
--
 
 
PROCEDURE updateRmanStatusRow( recid   IN number
                              ,stamp   IN number
                              ,mbytes  IN number
                              ,status  IN binary_integer);
--
 
/*----------------------------*
 * RMAN Output resync         *
 *----------------------------*/
 
FUNCTION beginRmanOutputResync(start_timestamp in NUMBER) RETURN NUMBER;
--
--
 
FUNCTION beginRmanOutputResync(start_timestamp IN  NUMBER
                              ,doRoutMining    OUT BOOLEAN) RETURN NUMBER;
 
PROCEDURE checkRmanOutput( recid             IN NUMBER
                          ,stamp             IN NUMBER
                          ,session_recid     IN NUMBER
                          ,session_stamp     IN NUMBER
                          ,rman_status_recid IN NUMBER
                          ,rman_status_stamp IN NUMBER
                          ,output       IN VARCHAR2);
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
--
--
 
PROCEDURE endRmanOutputResync;
--
 
/*----------------------------*
 * Block Corruption Resync    *
 *----------------------------*/
function beginBlockCorruptionResync(
  low_bcr_recid   IN number)
 return number;
 
procedure checkBlockCorruption(
  bcr_recid       IN number
 ,bcr_stamp       IN number
 ,file#           IN number
 ,create_scn      IN number
 ,create_time     IN date
 ,block#          IN number
 ,blocks          IN number
 ,corrupt_scn     IN number
 ,corruption_type IN varchar2
);
 
procedure endBlockCorruptionResync;
 
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
--
--
 
/*----------------------------*
 * Change Procedures          *
 *----------------------------*/
 
procedure changeDataFileCopy(
  cdf_recid    IN number
 ,cdf_stamp    IN number
 ,status       IN varchar2
 ,keep_options IN number DEFAULT NULL -- null means don't update
 ,keep_until   IN date   DEFAULT NULL
 ,osite_key    IN number DEFAULT NULL
 ,nsite_key    IN number DEFAULT NULL
);
 
--
--
--
 
procedure changeControlfileCopy(
  cdf_recid    IN number
 ,cdf_stamp    IN number
 ,status       IN varchar2
 ,keep_options IN number DEFAULT NULL -- null means don't update
 ,keep_until   IN date   DEFAULT NULL
 ,osite_key    IN number DEFAULT NULL
 ,nsite_key    IN number DEFAULT NULL
);
 
--
--
 
procedure changeArchivedLog(
  al_recid  IN number
 ,al_stamp  IN number
 ,status    IN varchar2
 ,osite_key IN number DEFAULT NULL
 ,nsite_key IN number DEFAULT NULL
);
 
--
--
 
 
procedure changeBackupSet(
  recid        IN number
 ,stamp        IN number
 ,keep_options IN number   -- null means don't update
 ,keep_until   IN date
 ,osite_key    IN number DEFAULT NULL
 ,nsite_key    IN number DEFAULT NULL
);
 
--
--
--
 
procedure changeBackupPiece(
  bp_recid  IN number
 ,bp_stamp  IN number
 ,status    IN varchar2
 ,set_stamp IN number DEFAULT NULL
 ,set_count IN number DEFAULT NULL
 ,osite_key IN number DEFAULT NULL
 ,nsite_key IN number DEFAULT NULL
 ,handle    IN VARCHAR2  DEFAULT NULL
 ,device_type IN VARCHAR2 DEFAULT NULL
 
);
 
--
--
 
procedure changeProxyCopy(
  pc_recid     IN number
 ,pc_stamp     IN number
 ,status       IN varchar2
 ,keep_options IN number  DEFAULT NULL -- null means don't update
 ,keep_until   IN date    DEFAULT NULL
 ,osite_key IN number DEFAULT NULL
 ,nsite_key    IN number DEFAULT NULL
);
 
--
--
 
 
/*----------------------------*
 * Reconcile for Replication  *
 *----------------------------*/
 
PROCEDURE doReplicationReconcile(p_db_key IN NUMBER, 
                                 p_lib_key IN NUMBER,
                                 p_server_key IN NUMBER);
 
 
--
 
 
/*----------------------------*
 * Stored Script Procedures   *
 *----------------------------*/
 
procedure createScript(name IN varchar2);
 
procedure createScript(name IN varchar2,
                       scr_com IN varchar2,
                       global  IN boolean);
 
procedure replaceScript(name IN varchar2);
 
procedure replaceScript(name IN varchar2,
                        scr_com IN varchar2,
                        global  IN boolean);
 
--
--
--
--
 
procedure putLine(line IN varchar2);
 
--
--
--
--
 
procedure deleteScript(name IN varchar2);
 
procedure deleteScript(name IN varchar2, glob IN number);
 
--
 
procedure getScript(name IN varchar2);
 
procedure getScript(name IN varchar2, glob IN number);
 
--
--
--
 
function getLine return varchar2;
 
--
--
 
procedure commitChanges(msg IN varchar2 default NULL);
 
--
 
/*---------------------------------------*
 * Procedures for EM xml store support   
 * The initial user of this API will be EM, however other client that intends 
 * to save XML data in RMAN's recovery catalog can also use the API.
 * The API provides the versioned file system functionality, i.e. the clients
 * can save multiple version of same XML file, with additional option to
 * overwrite the latest version.
 *---------------------------------------*/
 
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
--
--
procedure createXMLFile (name        IN varchar2 
                        ,name_tag    IN varchar2 default null
                        ,xmldoc      IN clob
                        ,doctype     IN varchar2 default null
                        ,xml_comment IN varchar2 default null
                        ,schema_ver  IN varchar2 default null);
 
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
procedure updateXMLFile (name        IN varchar2
                        ,name_tag    IN varchar2 default null
                        ,xmldoc      IN clob     default null
                        ,xml_comment IN varchar2 default null
                        ,schema_ver  IN varchar2 default null
                        ,new_name    IN varchar2 default null
                        ,new_name_tag IN varchar2 default null
                        ,new_version  IN BOOLEAN default FALSE);
 
--
procedure deleteXMLFile (name       IN varchar2
                        ,name_tag   IN varchar2 default null);
 
--
--
--
--
--
--
--
--
procedure readXMLFile   (name        IN varchar2
                        ,name_tag    IN varchar2 default null
                        ,version_num IN number default null
                        ,xmldoc      OUT clob);
 
--
--
--
--
--
--
--
--
--
procedure getXMLFileAttr (name        IN varchar2
                         ,name_tag    IN varchar2 default null
                         ,version_num IN number default null
                         ,doctype     OUT varchar2
                         ,file_size   OUT number
                         ,xml_comment OUT varchar2
                         ,schema_ver  OUT varchar2);
 
/*---------------------------------------*
 * Procedures for clone database support *
 *---------------------------------------*/
 
procedure setCloneName(file#            IN     number
                      ,creation_change# IN     number
                      ,new_clone_fname  IN     varchar2
                      ,old_clone_fname  IN     varchar2
                      ,changedauxname   OUT    boolean
                      ,plugin_change#   IN number   DEFAULT 0);
 
FUNCTION  getCloneName(file#            IN number
                      ,creation_change# IN number
                      ,plugin_change#   IN number  DEFAULT 0) RETURN VARCHAR2;
 
 
/*-----------------------------------*
 * Procedures for RMAN configuration *
 *-----------------------------------*/
 
PROCEDURE setConfig(conf#            IN NUMBER
                   ,name             IN VARCHAR2
                   ,value            IN VARCHAR2);
 
 
PROCEDURE resetConfig;
 
PROCEDURE setConfig2(conf#           IN NUMBER
                    ,name            IN VARCHAR2
                    ,value           IN VARCHAR2
                    ,nodespec        IN BOOLEAN);
 
PROCEDURE resetConfig2(nodespec      IN BOOLEAN, 
                       high_conf_recid IN NUMBER DEFAULT NULL);
 
PROCEDURE deleteConfig(conf#         IN NUMBER);
 
FUNCTION  setConfig3(name            IN VARCHAR2
                    ,value           IN VARCHAR2
                    ,db_unique_name  IN VARCHAR2) RETURN NUMBER;
 
PROCEDURE deleteConfig3(conf#        IN NUMBER
                    ,db_unique_name  IN VARCHAR2);
 
/*----------------------------*
 * Version info               *
 *----------------------------*/
 
function getPackageVersion return varchar2;
function getCatalogVersion return varchar2;
 
/*-------------------*
 * Utility functions *
 *-------------------*/
 
/*
 * NAME
 *   bsStstusRecalc
 * DESCRIPTION
 *   Recompute the backupset status for all backupset whose current
 *   status is a specified value.  This is intended to be used when
 *   new values are introduced for the bs.status column.
 */
 
PROCEDURE bsStatusRecalc(status IN varchar2);
 
--
--
--
 
--
--
--
--
--
--
 
PROCEDURE reNormalize(newname IN varchar2, oldname OUT varchar2);
 
--
--
--
 
PROCEDURE sanityCheck;
 
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
 
FUNCTION getDbid RETURN NUMBER;
 
PROCEDURE listScriptNames(glob IN number, 
                          allnames IN number);
 
--
--
--
--
--
--
 
PROCEDURE getScriptNames(dbname  OUT varchar2,
                         scnm    OUT varchar2,
                         sccom   OUT varchar2);
 
--
--
--
--
--
 
PROCEDURE updateOldestFlashbackSCN(
                oldest_flashback_scn     IN NUMBER,
                oldest_flashback_time    IN DATE   DEFAULT NULL);
 
--
--
 
FUNCTION getDbinc RETURN NUMBER;
 
--
 
FUNCTION isDuplicateRecord(recid    IN NUMBER
                          ,stamp    IN NUMBER
                          ,type     IN VARCHAR2) RETURN BOOLEAN;
--
--
--
--
--
--
 
FUNCTION doDuplicateMining RETURN BOOLEAN;
--
--
 
--
FUNCTION isRoutDuplicateRecord(recid             IN NUMBER
                              ,stamp             IN NUMBER
                              ,session_recid     IN NUMBER
                              ,session_stamp     IN NUMBER
                              ,rman_status_recid IN NUMBER
                              ,rman_status_stamp IN NUMBER)
RETURN BOOLEAN;
 
--
--
PROCEDURE unregisterSite(db_unique_name IN VARCHAR2, 
                         incbcks IN binary_integer);
 
--
--
PROCEDURE renameSite(from_db_unique_name IN VARCHAR2, 
                     to_db_unique_name IN VARCHAR2);
 
--
--
PROCEDURE resyncAddDBUname(cdbunstr IN varchar2);
 
--
--
FUNCTION  getThisSiteKey(db_unique_name in VARCHAR2 DEFAULT NULL) 
   return NUMBER;
 
--
FUNCTION  isAMSchema RETURN BOOLEAN;
 
--
FUNCTION  getAMTstlevel RETURN NUMBER;
 
PROCEDURE enableResyncActions;
 
PROCEDURE setReason(reason IN number, forceSet IN boolean default FALSE);
 
FUNCTION getReason RETURN number;
 
PROCEDURE incResyncActions(action      IN number,
                           objno       IN number,
                           objname     IN varchar2);
 
PROCEDURE getResyncActions(valid      OUT boolean
                           ,added     OUT number
                           ,dropped   OUT number
                           ,changed   OUT number
                           ,recreated OUT number
                           ,renamed   OUT number
                           ,resized   OUT number);
 
PROCEDURE clearResyncActions;
 
PROCEDURE dumpResyncActions;
 
FUNCTION debOK (level IN number DEFAULT RCVCAT_LEVEL_DEFAULT)  RETURN boolean;
 
--
--
--
--
--
PROCEDURE createTempResource(
   name       IN varchar2
  ,data_type  IN varchar2);
 
--
--
--
--
--
--
--
FUNCTION lockTempResource(
   name      IN varchar2
  ,data_type IN varchar2)
RETURN BOOLEAN;
 
--
--
--
--
--
--
--
PROCEDURE cleanupTempResource;
 
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
PROCEDURE addDbidToImport(
   first    IN binary_integer
  ,idb      IN varchar2
  ,idbinc   IN varchar2
  ,dbid     IN number    DEFAULT NULL
  ,dbname   IN varchar2  DEFAULT NULL);
 
--
--
--
--
--
--
--
PROCEDURE lockDbidToImport(
   idb   IN varchar2);
 
--
--
--
--
--
--
--
--
PROCEDURE importSchema(
   dblink   IN varchar2
  ,idb      IN varchar2
  ,idbinc   IN varchar2);
 
--
--
--
--
--
PROCEDURE setArchiveFileScopeAttributes(logs_shared IN NUMBER);
 
--
PROCEDURE setBackupFileScopeAttributes(
                 disk_backups_shared IN NUMBER,
                 tape_backups_shared IN NUMBER);
 
 
--
--
--
--
--
--
PROCEDURE unregisterDatabase(
   idb      IN varchar2);
 
--
PROCEDURE clearUnarchivedLogs;
 
/*--------------------------------------*
 * Virtual Private Catalog Procedures   *
 *--------------------------------------*/
 
PROCEDURE grant_catalog(userid IN varchar2, dbname IN varchar2);
PROCEDURE grant_catalog(userid IN varchar2, 
                        dbid IN number,
                        reg_db_unique_name IN varchar2 default null);
PROCEDURE grant_register(userid IN varchar2);
PROCEDURE revoke_catalog(userid IN varchar2, dbname IN varchar2);
PROCEDURE revoke_catalog(userid IN varchar2, 
                         dbid IN number,
                         reg_db_unique_name IN varchar2 default null);
PROCEDURE revoke_register(userid IN varchar2);
PROCEDURE revoke_all(userid IN varchar2);
PROCEDURE create_virtual_catalog;
PROCEDURE drop_virtual_catalog;
PROCEDURE setupVPD(i_oper IN NUMBER);
 
PROCEDURE dumpPkgState (msg in varchar2 default NULL);
 
/*--------------------------------------*
 * Recovery Server Catalog Procedures   *
 *--------------------------------------*/
 
PROCEDURE put_bucket(bktname in varchar2);
FUNCTION get_bucket(bktname in varchar2) return CLOB;
PROCEDURE delete_bucket(bktname in varchar2);
--
--
--
 
PROCEDURE put_object(bktname in varchar2, objname in varchar2,
                     objtype in varchar2, objval in out CLOB);
FUNCTION get_object(bktname in varchar2, 
                    objname in varchar2,
                    parms   in varchar2 DEFAULT null) return CLOB;
PROCEDURE delete_object(bktname in varchar2, objname in varchar2);
 
PROCEDURE writeFixedSections(bktname IN VARCHAR2 DEFAULT NULL);
PROCEDURE readFixedSections(input_xml_filename  IN VARCHAR2,
                            bktname IN VARCHAR2 DEFAULT NULL);
PROCEDURE rsWriteWaterMarks (input_xml_filename  IN VARCHAR2,
                              bktname IN VARCHAR2 DEFAULT NULL);
PROCEDURE writeBackupSections(input_xml_filename  IN VARCHAR2,
                              bktname IN VARCHAR2 DEFAULT NULL);
PROCEDURE readBackupSections(bktname IN VARCHAR2 DEFAULT NULL);
 
PROCEDURE rsDeleteBackupPiece(bp_key IN number, purged IN varchar2);
 
PROCEDURE addTimeZone(
   db_unique_name IN VARCHAR2
  ,db_timezone    IN VARCHAR2
  ,tmz_src        IN VARCHAR2
  ,incarnations   IN VARCHAR2 default 'CURRENT'
);
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
 
FUNCTION getValueFromConfig(entry IN VARCHAR2) RETURN VARCHAR2;
--
--
 
PROCEDURE throttle_me(p_oam_job_id         IN VARCHAR2,
                      p_channels_reqd      IN NUMBER,
                      p_request_time       IN DATE,
                      p_wait               OUT BOOLEAN,
                      p_error_str          OUT VARCHAR2);
 
end dbms_rcvcat;









CREATE OR REPLACE PACKAGE BODY dbms_rcvcat IS
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
 
 
type version_list_type is table of varchar2(14) index by binary_integer;
version_list version_list_type;
version_max_index binary_integer;
version_counter binary_integer := 1;
 
/*-----------*
 * Constants *
 *-----------*/
 
MAXNUMVAL      CONSTANT NUMBER := 2**32-1;
catalogVersion CONSTANT VARCHAR2(11) := '12.02.00.01';
ZERO_GUID      CONSTANT RAW(16) := HEXTORAW(RPAD('0', 32, '0'));
 
/*-------------------------*
 * Package State Variables *
 *-------------------------*/
 
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
--
--
--
--
--
--
--
--
 
debug               BOOLEAN := FALSE;
this_ckp_key        NUMBER  := NULL;
this_ckp_scn        NUMBER  := NULL;
this_ckp_time       DATE    := NULL;
this_lib_key        NUMBER  := NULL;
last_full_ckp_scn   NUMBER  := NULL;
last_guid           PDB.GUID%TYPE := NULL;
last_con_id_ts#     NUMBER;
last_ts#            NUMBER;
last_file#          NUMBER;
last_thread#        NUMBER;
last_fname          site_dfatt.fname%type;
last_pdb_recid      NUMBER;
last_pic_recid      NUMBER;
last_ts_recid       NUMBER;
last_df_recid       NUMBER;
last_tf_recid       NUMBER;
last_rt_recid       NUMBER;
last_orl_recid      NUMBER;
last_conf_recid     NUMBER;
force_resync2cf     VARCHAR2(3) := 'NO';
last_rlh_recid      NUMBER;
last_al_recid       NUMBER;
last_offr_recid     NUMBER;
last_bs_recid       NUMBER;
last_bp_recid       NUMBER;
last_bdf_recid      NUMBER;
last_bsf_recid      NUMBER;
last_brl_recid      NUMBER;
last_cdf_recid      NUMBER;
last_bcb_recid      NUMBER;
last_ccb_recid      NUMBER;
last_do_recid       NUMBER;
last_xdf_recid      NUMBER := NULL;
last_xal_recid      NUMBER := NULL;
last_rsr_recid      NUMBER;
last_rout_stamp     NUMBER := NULL;
last_inst_startup_stamp NUMBER := NULL;
lrsr_key            NUMBER;
lrout_skey          NUMBER;
lsession_recid      NUMBER;
lsession_stamp      NUMBER;
lrman_status_recid  NUMBER;
lrman_status_stamp  NUMBER;
--
krbmror_llength_bytes NUMBER := 130;
--
--
type rout_list     is table of rout%ROWTYPE index by binary_integer;
lrout_table        rout_list;
lrout_curridx      binary_integer := 0;
 
--
--
last_ic_recid       NUMBER := NULL;
scr_key             NUMBER := NULL;
scr_line            NUMBER;
scr_glob            BOOLEAN;
kccdivts            NUMBER;
type bskeys_v       is table of number index by varchar2(128);
duplicatebs         bskeys_v;
type bskeys is table of number index by binary_integer;
cntbs               NUMBER := 0;
updatebs            bskeys;
last_reset_scn      NUMBER;
last_reset_time     DATE;
last_dbinc_key      NUMBER;
do_temp_ts_resync   BOOLEAN := FALSE;  -- indicates if temp_ts is resynced
last_cf_version_time DATE;
dbglvl              NUMBER := RCVCAT_LEVEL_DEFAULT;
low_nrsp_recid      NUMBER;
last_nrsp_recid     NUMBER;
last_grsp_recid     NUMBER;
last_rspname        grsp.rspname%type;
last_pdb_key        NUMBER;
last_bcr_recid      NUMBER;
last_resync_cksum   NUMBER;
 
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
--
--
--
this_cf_type    VARCHAR2(7) := NULL;
this_cf_version DATE := NULL;
this_ckp_type   VARCHAR2(10) := NULL;
 
--
this_db_unique_name    node.db_unique_name%TYPE;
this_site_key          NUMBER; -- Never NULL even for 9i RMAN client
client_site_aware      boolean := FALSE;
 
--
logs_shared           number := FALSE#; -- used only when client_site_aware
disk_backups_shared   number := TRUE#;  -- indicates shared accross all sites
tape_backups_shared   number := TRUE#;  -- indicates shared accross all sites
 
--
session_keep_output   number := NULL;
rman_keep_output      number := NULL;
 
reNorm_state binary_integer;
RENORM_DFATT CONSTANT binary_integer := 1;
RENORM_ORL   CONSTANT binary_integer := 2;
RENORM_AL    CONSTANT binary_integer := 3;
RENORM_BP    CONSTANT binary_integer := 4;
RENORM_CCF   CONSTANT binary_integer := 5;
RENORM_CDF   CONSTANT binary_integer := 6;
RENORM_TFATT CONSTANT binary_integer := 7;
 
--
--
--
--
--
--
type sessionWaterMarks_t is record
(
last_kccdivts      number := 0,            -- check if ctl version is diff 
high_rout_stamp    number := 0,            -- for rman_output resync
high_ic_recid      number := 0,            -- incarnation recid
high_offr_recid    number := 0,            -- offline range (kkor) recid
high_rlh_recid     number := 0,            -- log history (kcclh) recid
high_al_recid      number := 0,            -- archived log (kccal) recid
high_bs_recid      number := 0,            -- backup set (kccbs) recid
high_bp_recid      number := 0,            -- backup piece (kccbp) recid
high_bdf_recid     number := 0,            -- backup datafile (kccbf) recid
high_cdf_recid     number := 0,            -- datafile copy (kccdc) recid
high_brl_recid     number := 0,            -- backup redo log (kccbl) recid
high_bcb_recid     number := 0,            -- backup datafile corruption recid
high_ccb_recid     number := 0,            -- datafile copy corruption recid
high_do_recid      number := 0,            -- deleted object recid
high_pc_recid      number := 0,            -- proxy copy (kccpc) recid
high_bsf_recid     number := 0,            -- backup SPFILE (kccbi) recid
high_rsr_recid     number := 0,            -- RMAN status (kccrsr) recid
high_nrsp_recid    number := 0,            -- normal restore point recid
high_bcr_recid     number := 0,            -- high blk crpt (kccblkcor) recid
high_pic_recid     number := 0             -- high pdbinc recid
);
 
init_sessionWaterMarks sessionWaterMarks_t;
prev_sessionWaterMarks sessionWaterMarks_t;
sessionWaterMarks      sessionWaterMarks_t;
 
--
type ts_name_list     is table of ts.ts_name%type index by binary_integer;
type numTab_t         is table of number index by binary_integer; 
type key_columns_list is table of varchar2(30);
type sp_columns_list  is table of varchar2(1);
 
--
--
--
import_dbid numTab_t;
--
--
--
--
--
--
--
--
key_columns  CONSTANT key_columns_list := key_columns_list
   ('DB_KEY'          , 'DBINC_KEY'      , 'CURR_DBINC_KEY'    ,
    'PARENT_DBINC_KEY', 'CKP_KEY'        , 'START_CKP_KEY'     ,
    'END_CKP_KEY'     , 'OFFR_KEY'       , 'RR_KEY'            ,
    'RLH_KEY'         , 'AL_KEY'         , 'BS_KEY'            ,
    'BP_KEY'          , 'BCF_KEY'        , 'CCF_KEY'           ,
    'XCF_KEY'         , 'BSF_KEY'        , 'BDF_KEY'           ,
    'CDF_KEY'         , 'XDF_KEY'        , 'XAL_KEY'           ,
    'BRL_KEY'         , 'BDF_KEY'        , 'RSR_KEY'           ,
    'RSR_PKEY'        , 'RSR_L0KEY'      , 'SCR_KEY'           ,
    'ROUT_SKEY'       , 'SITE_KEY'       , 'DF_KEY'            ,
    'TF_KEY'          , 'PDB_KEY'        , 'LIB_KEY'           ,
    'VB_KEY'          , 'CT_KEY'         , 'PDBINC_KEY'        ,
    'CURR_PDBINC_KEY' , 'BORN_DBINC_KEY' , 'PARENT_PDBINC_KEY' ,
    'TS_PDBINC_KEY'   , 'TEMPLATE_KEY');
 
--
--
import_dblink tempres.name%type;
import_offset number;
 
/*---------------------------------------------------*
 * package variables used for recovery server        *
 *---------------------------------------------------*/
this_is_ors              BOOLEAN := FALSE;
this_is_ors_admin        BOOLEAN := FALSE;
orsadmin_user            ALL_USERS.USERNAME%TYPE := NULL;
this_server              SERVER%ROWTYPE;
this_amazon_server       BOOLEAN := FALSE;
this_server_uname        VARCHAR2(512):= NULL;
this_server_passw        VARCHAR2(512):= NULL;
this_server_url          VARCHAR2(512):= NULL;
this_xml_signature_beg   VARCHAR2(50) := '<?xml version="1.0"?> <ALL_TABLES> ';
this_xml_signature_end   VARCHAR2(50) := ' </ALL_TABLES> ';
this_forwatermarks       VARCHAR2(50) := 'forwatermarks_';
this_watermarks          VARCHAR2(50) := 'watermarks_';
this_backupsets          VARCHAR2(50) := 'backupsets_';
this_database            VARCHAR2(50) := 'database_';
this_url_db_unique_name  node.db_unique_name%TYPE;
this_lock_ors_inspect    BOOLEAN      := FALSE;
this_clr_ba_newinc_err   BOOLEAN      := FALSE;
this_upstream_site_key   NUMBER       := NULL;
this_enable_populate_rsr NUMBER       := NULL;
--
--
--
this_verrec                  RC_RCVER%ROWTYPE;
this_curr_inc                RC_DATABASE_INCARNATION%ROWTYPE;
this_wmrec                   RC_WATERMARKS%ROWTYPE;
this_upstream_dbrec          RCI_RA_UPSTREAM_DATABASE%ROWTYPE;
this_rsiterec                RC_SITE%ROWTYPE;
this_v_wmrec                 CLOB;
this_curr_bp_recid           NUMBER;
this_high_bp_recid           NUMBER;
this_bp_key_poll             NUMBER;
 
FUNCTION assert_schema (
  i_schema                   VARCHAR2
, i_enquote                  BOOLEAN DEFAULT FALSE
)
RETURN VARCHAR2;
 
PROCEDURE assert_schema (
  o_schema                   OUT NOCOPY VARCHAR2
, o_eschema                  OUT NOCOPY VARCHAR2
, i_schema                   IN VARCHAR2
);
 
g_catowner                   CONSTANT user_users.username%TYPE :=
  assert_schema(dbms_catowner);
g_ecatowner                  CONSTANT VARCHAR2(130) :=
  assert_schema(dbms_catowner, TRUE);
 
e_no_vpd_setup               CONSTANT NUMBER := -20153;
e_no_vpd_setup_m             CONSTANT VARCHAR2(64) :=
  'recovery catalog does not have VPD support enabled!';
 
--
--
--
--
--
TYPE vtr IS VARRAY(4) OF VARCHAR2(256);
TYPE vtr_t IS TABLE OF vtr;
vpd_table_list CONSTANT vtr_t :=
vtr_t
(
    vtr('VPC_DATABASES', 'SELECT', 'T', 'T')
  , vtr('DB',             NULL,    'T', 'F')
  , vtr('DBINC',          NULL,    'T', 'T')
  , vtr('BP',             NULL,    'T', 'T')
  , vtr('BSF',            NULL,    'T', 'T')
  , vtr('BS',             NULL,    'T', 'T')
  , vtr('CONF',           NULL,    'T', 'T')
  , vtr('DELETED_OBJECT', NULL,    'T', 'T')
  , vtr('DO_SEQ',         NULL,    'T', 'T')
  , vtr('NODE',           NULL,    'T', 'T')
  , vtr('PDB',            NULL,    'T', 'T')
  , vtr('ROUT',           NULL,    'T', 'T')
  , vtr('WATERMARKS',     NULL,    'T', 'T')
  , vtr('PDBINC',         NULL,    'T', 'T')
  , vtr('PDB_DBINC',      NULL,    'T', 'T')
  , vtr('AL',             NULL,    'T', 'T')
  , vtr('BCF',            NULL,    'T', 'T')
  , vtr('BDF',            NULL,    'T', 'T')
  , vtr('BRL',            NULL,    'T', 'T')
  , vtr('CCF',            NULL,    'T', 'T')
  , vtr('CDF',            NULL,    'T', 'T')
  , vtr('CKP',            NULL,    'T', 'T')
  , vtr('DF',             NULL,    'T', 'T')
  , vtr('FB',             NULL,    'T', 'T')
  , vtr('GRSP',           NULL,    'T', 'T')
  , vtr('NRSP',           NULL,    'T', 'T')
  , vtr('OFFR',           NULL,    'T', 'T')
  , vtr('ORL',            NULL,    'T', 'T')
  , vtr('RLH',            NULL,    'T', 'T')
  , vtr('RR',             NULL,    'T', 'T')
  , vtr('RSR',            NULL,    'T', 'T')
  , vtr('RT',             NULL,    'T', 'T')
  , vtr('TF',             NULL,    'T', 'T')
  , vtr('TSATT',          NULL,    'T', 'T')
  , vtr('TS',             NULL,    'T', 'T')
  , vtr('XAL',            NULL,    'T', 'T')
  , vtr('XCF',            NULL,    'T', 'T')
  , vtr('XDF',            NULL,    'T', 'T')
  , vtr('SCR',            NULL,    'T', 'T')
  , vtr('BCB',            NULL,    'T', 'T')
  , vtr('CCB',            NULL,    'T', 'T')
  , vtr('SCRL',           NULL,    'T', 'T')
  , vtr('CFS',           'SELECT', 'T', 'T')
  , vtr('CONFIG',        'SELECT', 'T', 'T')
  , vtr('ORSEVENT',       NULL,    'T', 'T')
  , vtr('RCFILE',         NULL,    'T', 'T')
  , vtr('XMLSTORE',      'SELECT', 'T', 'F')
  , vtr('BCR',            NULL,    'T', 'T')
  , vtr('SERVER',         NULL,    'T', 'F')
  , vtr('VPC_USERS',     'SELECT', 'T', 'T')
  , vtr('SITE_DFATT',     NULL,    'T', 'T')
  , vtr('SITE_TFATT',     NULL,    'T', 'T')
  , vtr('RCVER',         'SELECT', 'F', 'F')
  , vtr('RASCHEMAVER',   'SELECT', 'F', 'F')
  , vtr('RRCACHE',       'SELECT', 'T', 'T')  
  , vtr('SBT_TEMPLATE_DB',NULL,    'T', 'T')
);
--
--
g_vpd_required_policies      NUMBER := 0;
 
/*---------*
 * Cursors *
 *---------*/
 
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
--
--
--
--
 
--
cursor pdbQ IS
  SELECT pdb.pdb_key, pdb.name, pdb.con_id, pdb.guid,
         pdb.create_scn, pdb.nobackup
    FROM pdb
   WHERE pdb.db_key = this_db_key
     AND pdb.con_id > 1
   ORDER BY pdb.guid;
 
cursor picQ IS
   SELECT pdbinc.pdb_key, pdbinc.pdbinc_key, pdb.con_id,
          pdb.guid, pdb.create_scn, pdbinc.inc_scn, pdbinc.begin_reset_scn,
          pdbinc.begin_reset_time, pdbinc.end_reset_scn,
          dbinc.reset_scn db_reset_scn, dbinc.reset_time db_reset_time,
          pdbinc.parent_pdbinc_key
     FROM pdbinc, pdb, dbinc
    WHERE pdb.pdb_key     = pdbinc.pdb_key
      AND dbinc.dbinc_key = pdbinc.born_dbinc_key
      AND pdb.con_id      > 1
      AND pdb.db_key      = this_db_key
    ORDER BY guid, con_id, create_scn, begin_reset_scn, begin_reset_time,
             end_reset_scn;
--
 
--
cursor tsQ IS
  SELECT ts.ts_name, ts.ts#, ts.create_scn, ts.create_time, tsatt.rbs_count,
         ts.included_in_database_backup, ts.bigfile, ts.temporary,
         ts.encrypt_in_backup, ts.plugin_scn, ts.pdbinc_key, pdbinc.con_id,
         pdbinc.curr_pdbinc_key, pdbinc.pdb_key, pdbinc.drop_scn pdb_drop_scn
  FROM ts, tsatt, rci_pdbinc_this_dbinc pdbinc
  WHERE ts.dbinc_key     = tsatt.dbinc_key
    AND ts.ts#           = tsatt.ts#
    AND ts.pdbinc_key    = pdbinc.pdbinc_key
    AND tsatt.pdbinc_key = pdbinc.pdbinc_key
    AND ts.create_scn    = tsatt.create_scn
    AND ts.dbinc_key     = this_dbinc_key
    AND ts.plugin_scn    = tsatt.plugin_scn
    AND ts.drop_scn       IS NULL              --skip ones we know were dropped
    AND tsatt.end_ckp_key IS NULL
    AND pdbinc.dbinc_key = this_dbinc_key
    AND ts.create_scn    < pdbinc.next_inc_scn --skip ones in orphan scn
  ORDER BY pdbinc.con_id, ts.ts#; -- client passes rows to checkTs in
--
--
 
--
cursor dfQ IS
  SELECT df.file#, df.create_scn, df.create_time, df.plugin_scn, df.ts#,
         site_dfatt.fname, df.blocks, df.clone_fname, df.stop_scn,
         df.read_only, df.plugged_readonly, df.create_thread, df.create_size,
         df.foreign_dbid, df.pdb_closed, df.pdbinc_key,
         pdbinc.curr_pdbinc_key, pdbinc.pdb_key
  FROM df, site_dfatt, rci_pdbinc_this_dbinc pdbinc
  WHERE df.dbinc_key  = this_dbinc_key  -- our dbinc please
    AND df.drop_scn  IS NULL            -- df not dropped
    AND this_site_key = site_dfatt.site_key(+)  -- select names for the site
    AND df.df_key     = site_dfatt.df_key(+)    -- join site_dfatt to df
    AND df.pdbinc_key = pdbinc.pdbinc_key
    AND df.create_scn < pdbinc.next_inc_scn     -- skip ones in orphan scn
    AND df.plugin_scn < pdbinc.next_inc_scn
    AND pdbinc.dbinc_key = this_dbinc_key
  ORDER BY df.file#;                    -- client passes rows to checkDf in
--
--
 
--
cursor tfQ IS
  SELECT tf.file#, tf.create_scn, tf.create_time, tf.ts#,
         site_tfatt.fname, site_tfatt.blocks, site_tfatt.autoextend,
         site_tfatt.max_size, site_tfatt.next_size, tf.tf_key tf_key,
         tf.pdb_key, pdb.con_id
  FROM tf, site_tfatt, pdb
  WHERE tf.dbinc_key  = this_dbinc_key       -- our dbinc please
    AND this_site_key = site_tfatt.site_key  -- select names for the site
    AND tf.tf_key     = site_tfatt.tf_key    -- join site_tfatt to tf
    AND site_tfatt.drop_scn IS NULL          -- tf not dropped
    AND tf.pdb_key    = pdb.pdb_key
  ORDER BY tf.file#;                    -- client passes rows to checkTf in
--
--
 
--
cursor rtQ IS
  SELECT rt.thread#, rt.sequence#, rt.enable_scn,
         rt.enable_time, rt.status
  FROM rt
  WHERE rt.dbinc_key = this_dbinc_key
  ORDER BY rt.thread#;
 
 
--
cursor orlQ IS
  SELECT orl.thread#, orl.group#, orl.fname
  FROM orl
  WHERE orl.dbinc_key = this_dbinc_key
    AND orl.site_key  = this_site_key
  ORDER BY nlssort(orl.fname, 'NLS_COMP=ANSI NLS_SORT=ASCII7'); -- bug 2107554
 
--
cursor grspQ IS
  SELECT grsp.rspname, grsp.from_scn, grsp.to_scn, grsp.pdb_key
  FROM grsp, dbinc, pdb
  WHERE grsp.dbinc_key = dbinc.dbinc_key
    AND dbinc.db_key = this_db_key
    AND grsp.site_key = this_site_key
    AND grsp.pdb_key = pdb.pdb_key
  ORDER BY pdb.con_id, grsp.pdb_key,
           nlssort(grsp.rspname, 'NLS_COMP=ANSI NLS_SORT=ASCII7');
 
--
--
cursor bpq(device_type VARCHAR2, handle VARCHAR2,
           bp_recid VARCHAR2, bp_stamp VARCHAR2) IS
  SELECT bp_key, bs_key
  FROM bp
  WHERE db_key = this_db_key
  AND   status != 'D'
  AND   device_type = bpq.device_type
  AND   ((disk_backups_shared = TRUE# AND bp.device_type = 'DISK') OR
         (tape_backups_shared = TRUE# AND bp.device_type <> 'DISK') OR
         (this_site_key = nvl(bp.site_key, this_site_key)))
  AND   handle = bpq.handle
  AND   handle_hashkey = substr(bpq.device_type,1,10) ||
                         substr(bpq.handle,1,10) ||
                         substr(bpq.handle,-10)
  AND   NOT (bp_recid = bpq.bp_recid AND
             bp_stamp = bpq.bp_stamp);
 
 
--
cursor scrlQ(key NUMBER) IS
  SELECT text
  FROM scrl
  WHERE scr_key = key
  ORDER BY linenum;
 
--
cursor rcverQ IS
  SELECT version
  FROM rcver
  ORDER BY version;
 
cursor reNorm_dfatt_c IS
  SELECT fname
  FROM site_dfatt
  WHERE df_key in (select df_key from df, dbinc 
                    where df.dbinc_key = dbinc.dbinc_key 
                      and dbinc.db_key = this_db_key)
  FOR UPDATE OF site_dfatt.df_key;
 
cursor reNorm_orl_c IS
  SELECT fname
  FROM orl
  WHERE dbinc_key in (select dbinc_key from dbinc where db_key = this_db_key)
  FOR UPDATE OF orl.dbinc_key;
 
cursor reNorm_al_c IS
  SELECT fname
  FROM al
  where dbinc_key in (select dbinc_key from dbinc where db_key = this_db_key)
  FOR UPDATE OF al.al_key;
 
cursor reNorm_bp_c IS
  SELECT handle
  FROM bp
  WHERE device_type = 'DISK' and db_key = this_db_key
  FOR UPDATE OF bp.bp_key;
 
cursor reNorm_ccf_c IS
  SELECT fname
  FROM ccf
  WHERE dbinc_key in (select dbinc_key from dbinc where db_key = this_db_key)
  FOR UPDATE OF ccf.ccf_key;
 
cursor reNorm_cdf_c IS
  SELECT fname
  FROM cdf
  WHERE dbinc_key in (select dbinc_key from dbinc where db_key = this_db_key)
  FOR UPDATE OF cdf.dbinc_key;
 
cursor reNorm_tfatt_c IS
  SELECT fname
  FROM site_tfatt
  WHERE tf_key in (select tf_key from tf, dbinc 
                    where tf.dbinc_key = dbinc.dbinc_key 
                      and dbinc.db_key = this_db_key)
  FOR UPDATE OF site_tfatt.tf_key;
 
cursor lscrnames_c(glob number, allnames number) IS
    select 1 oby, rdbi.db_name dname, s.scr_name sname, s.scr_comment scomm
      from db rdb, dbinc rdbi, scr s
     where lscrnames_c.glob is null
       and lscrnames_c.allnames is null
       and rdbi.dbinc_key = rdb.curr_dbinc_key
       and rdb.db_key = s.db_key
       and s.db_key = this_db_key
       and s.db_key is not NULL
    UNION ALL
    select 2, 'ORA%GLOB', s.scr_name, s.scr_comment
      from scr s
     where s.db_key IS NULL
    UNION ALL
    select 3, rdbi.db_name, s.scr_name, s.scr_comment
      from db rdb, dbinc rdbi, scr s
     where lscrnames_c.glob is null
       and lscrnames_c.allnames is not null
       and rdbi.dbinc_key = rdb.curr_dbinc_key
       and rdb.db_key = s.db_key
       and s.db_key is not NULL
    order by 1 asc, 2 asc, 3 asc;
 
getPolled_AL BOOLEAN := FALSE;
getPolled_BP BOOLEAN := FALSE;
 
--
RTYP_ARCHIVED_LOG         CONSTANT number := 11;
RTYP_BACKUP_PIECE         CONSTANT number := 13;
RTYP_DFILE_COPY           CONSTANT number := 16;
 
CURSOR feedback_al(bp_key_poll NUMBER) IS
   SELECT al_recid recid, al_stamp stamp, fname
     FROM al, (SELECT distinct dbinc_key, thread#, sequence# 
                  FROM brl
                  WHERE bs_key IN 
                     (SELECT bs_key FROM bp 
                         WHERE db_key = this_db_key
                           AND bp_key > bp_key_poll 
                           AND purged = 'N'
                           AND device_type = 'SBT_TAPE' 
                           AND ba_access IN ('L', 'T')
                           AND site_key IN
                              (SELECT site_key FROM node WHERE
                                  db_unique_name like '$%' AND 
                                  db_key=this_db_key))) bal 
     WHERE al.site_key = this_site_key
       AND al.is_recovery_dest_file = 'YES'
       AND bal.dbinc_key=al.dbinc_key 
       AND bal.thread# = al.thread# 
       AND bal.sequence# = al.sequence#;
 
CURSOR feedback_bp(bp_key_poll NUMBER) IS
--
--
   SELECT bs_key, piece#, device_type, site_key,
          bp_recid recid, bp_stamp stamp, ba_access, handle
     FROM bp
     WHERE db_key = this_db_key
       AND ((site_key=this_site_key AND
             is_recovery_dest_file = 'YES') OR
            (device_type = 'SBT_TAPE' and ba_access in ('L', 'T', 'D')))
       AND bs_key in (SELECT bs_key FROM bp 
                         WHERE db_key = this_db_key
                           AND bp_key > bp_key_poll 
                           AND purged = 'N'
                           AND device_type = 'SBT_TAPE' 
                           AND ba_access IN ('L', 'T')
                           AND site_key IN
                              (SELECT site_key FROM node WHERE
                                  db_unique_name like '$%' AND 
                                  db_key=this_db_key))
   UNION
 
--
--
   SELECT bp.bs_key, piece#, device_type, site_key,
          bp_recid recid, bp_stamp stamp, ba_access, handle
      FROM bp,
            (SELECT distinct a.bs_key FROM bdf a,
                (SELECT bs_key, file#, create_scn, ckp_scn, dbinc_key
                    FROM bdf
                    WHERE bs_key IN 
                       (SELECT bs_key FROM bp
                           WHERE db_key = this_db_key
                             AND bp_key > bp_key_poll
                             AND purged = 'N'
                             AND device_type = 'SBT_TAPE'
                             AND ba_access IN ('L', 'T')
                             AND site_key IN
                                (SELECT site_key FROM node WHERE
                                    db_unique_name like '$%' AND
                                    db_key=this_db_key))) b
                 WHERE b.file#=a.file#
                   AND b.create_scn=a.create_scn
                   AND b.ckp_scn=a.ckp_scn
                   AND b.dbinc_key=a.dbinc_key
                   AND b.bs_key <> a.bs_key) c
     WHERE db_key = this_db_key
       AND site_key = this_site_key
       AND bp.bs_key = c.bs_key
       AND is_recovery_dest_file = 'YES'
 
     ORDER BY bs_key, piece#, device_type, ba_access desc;
 
disk_bp_rec  feedback_bp%ROWTYPE;
curr_bp_key_poll NUMBER;
 
 
/*----------------------*
 * Cursor Row Variables *
 *----------------------*/
 
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
 
pdbRec          pdbQ%rowtype;
picRec          picQ%rowtype;
tsRec           tsQ%rowtype;
dfRec           dfQ%rowtype;
tfRec           tfQ%rowtype;
rtRec           rtQ%rowtype;
orlRec          orlQ%rowtype;
grspRec         grspQ%rowtype;
 
/*---------------*
 * Private Types *
 *---------------*/
 
/*-------------------*
 * Private functions *
 *-------------------*/
 
FUNCTION vtr_tname(i IN number) RETURN VARCHAR2 IS
BEGIN
  RETURN vpd_table_list(i)(1);
END;
 
FUNCTION vtr_privs(i IN number) RETURN VARCHAR2 IS
BEGIN
  RETURN NVL(vpd_table_list(i)(2), 'SELECT,UPDATE,DELETE,INSERT');
END;
 
FUNCTION vtr_policy_required(i IN number) RETURN BOOLEAN IS
BEGIN
  RETURN vpd_table_list(i)(3) = 'T';
END;
 
FUNCTION vtr_update_check(i IN number) RETURN BOOLEAN IS
BEGIN
  RETURN vpd_table_list(i)(4) = 'T';
END;
 
PROCEDURE setDebugOn(dbglevel IN NUMBER DEFAULT RCVCAT_LEVEL_DEFAULT) IS
BEGIN
--
--
--
--
   dbms_output.enable(buffer_size => null);
   debug := TRUE;
   dbglvl := dbglevel;
END;
 
PROCEDURE setDebugOff IS
BEGIN
   dumpPkgState('Debug off');
   dbms_output.disable;  -- free memory
   debug := FALSE;
END;
 
PROCEDURE deb(line IN varchar2
              ,level IN number DEFAULT RCVCAT_LEVEL_DEFAULT) IS
BEGIN
   IF debOK(level) THEN
      IF this_is_ors_admin THEN
         EXECUTE IMMEDIATE 'BEGIN sys.kbrsi_icd.rsTrace(:1); END;' USING
            'RCVCAT: ' || line;
      END IF;
      dbms_output.put_line('DBGRCVCAT: '||line);
   END IF;
EXCEPTION
   WHEN others THEN
      dbms_output.put_line('(DO_NOT_IGNORE)caught exception during deb ' ||
                           substr(sqlerrm, 1, 512));
END deb;
 
PROCEDURE deb_put(line IN varchar2
              ,level IN number DEFAULT RCVCAT_LEVEL_DEFAULT) IS
BEGIN
   if debOK(level) then
      dbms_output.put('DBGRCVCAT: '||line);
   end if;
EXCEPTION
   WHEN others THEN
      dbms_output.put_line('(DO_NOT_IGNORE)caught exception during deb ' ||
                           substr(sqlerrm, 1, 512));
END deb_put;
 
FUNCTION debOK(level IN number DEFAULT RCVCAT_LEVEL_DEFAULT) RETURN boolean IS
BEGIN
  return (debug and dbglvl >= level);
END debOK;
 
PROCEDURE init_package_vars(p_server_key NUMBER DEFAULT NULL) IS
  proxy   varchar2(512);
  got_srvr_info  boolean := TRUE;
  no_ds_err         EXCEPTION;
  PRAGMA EXCEPTION_INIT(no_ds_err, -1034); -- Oracle not available
BEGIN
  BEGIN
    IF p_server_key IS NOT NULL THEN 
      SELECT * INTO this_server 
        FROM server 
        WHERE filter_user=user
          AND server_key = p_server_key;
    ELSE 
      SELECT * INTO this_server FROM server where filter_user=user;
    END IF;
         
--
--
    IF (INSTR(this_server.server_host, 'amazonaws.com') <> 0) THEN
      this_amazon_server := TRUE;
      this_server_url    := this_server.server_host;
      if (this_server.server_port is not null) then
         this_server_url := this_server_url || ':' || this_server.server_port;
      end if;
      this_server_uname  := NULL;
      this_server_passw  := NULL;
    ELSE
      this_amazon_server := FALSE;
      IF p_server_key IS NOT NULL THEN 
         BEGIN
           execute immediate 'begin dbms_rai_wallet2url(
                                wallet_loc   => :wallet_path,
                                cred_alias   => :wallet_alias,
                                username     => :username,
                                password     => :password,
                                url          => :url,
                                client       => ''REPLICATION''); end;'
                  using in this_server.wallet_path, in this_server.wallet_alias,
                        out this_server_uname, out this_server_passw,
                        out this_server_url;
         EXCEPTION
            WHEN no_ds_err THEN
               got_srvr_info := FALSE;
               deb('INIT_PKG_VARS:  Unable to communicate with downstream '||
                   'replication server');
         END;
      END IF;
    END IF;
    if got_srvr_info then
       if this_server.proxy_url is not null then
          proxy := this_server.proxy_url;
          if (this_server.proxy_port is not null) then
             proxy := proxy || ':' || this_server.proxy_port;
          end if;
          utl_http.set_proxy(proxy, null /* noproxy */);
       end if;
       utl_http.set_response_error_check(TRUE);
       utl_http.set_detailed_excp_support(TRUE);
       utl_http.set_wallet(path     => this_server.wallet_path,
                           password => null);
    end if;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      deb('init_package_vars(no_data_found): ignore it');
    WHEN TOO_MANY_ROWS THEN
      deb('init_package_vars(too_many_rows): ignore it');
    WHEN OTHERS THEN
      deb('init_package_vars(fail): p_server_key=' || p_server_key ||
          ',error='|| sqlerrm);
      RAISE;
  END;
END init_package_vars;
 
--
--
PROCEDURE checkResync IS
BEGIN
 
  IF (this_ckp_key IS NULL) THEN
    raise_application_error(-20031, 'Resync not started');
  END IF;
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
  IF (this_site_key IS NULL) THEN
    raise_application_error(-20199, 'Database site key not set');
  END IF;
 
END checkResync;
 
--
function date2stamp(dt IN date) return number is
  stamp number;
begin
  stamp := (((((to_number(to_char(dt, 'YYYY'))-1988)*12
         +     (to_number(to_char(dt, 'MM'))-1))*31
         +     (to_number(to_char(dt, 'DD'))-1))*24
         +     (to_number(to_char(dt, 'HH24'))))*60
         +     (to_number(to_char(dt, 'MI'))))*60
         +     (to_number(to_char(dt, 'SS')));
  return stamp;
end;
 
--
function stamp2date(stamp IN number) return date IS
x number;
dt varchar2(19);
begin
   if stamp is null then
       return null;
   end if;
 
   x := stamp;
 
   dt := to_char(mod(x,60), 'FM09'); -- seconds
   x := floor(x/60);
 
   dt := to_char(mod(x,60), 'FM09') || ':' || dt; -- minutes
   x := floor(x/60);
 
   dt := to_char(mod(x,24), 'FM09') || ':' || dt; -- hours
   x := floor(x/24);
 
   dt := to_char(mod(x,31)+1, 'FM09') || ' ' || dt; -- days
   x := floor(x/31);
 
   dt := to_char(mod(x,12)+1, 'FM09') || '/' || dt; -- months
 
   dt := to_char(floor(x/12)+1988)   || '/' || dt;
 
   return to_date(dt, 'YYYY/MM/DD HH24:MI:SS');
end;
 
--
PROCEDURE recomputeDbincStatus(db_key IN NUMBER,
                               dbinc_key IN NUMBER) IS
--
 PROCEDURE updateDbincStatus(db_key IN NUMBER, dbinc_key IN NUMBER) IS
 parent_key NUMBER;
 BEGIN
  BEGIN
   deb('updateDbincStatus - for db_key='||db_key||' dbinc='||dbinc_key);
   update dbinc set dbinc_status='PARENT'
      where dbinc_key =  updateDbincStatus.dbinc_key and
            db_key = updateDbincStatus.db_key;
 
--
   select parent_dbinc_key into parent_key from dbinc
      where dbinc_key= updateDbincStatus.dbinc_key and
            db_key = updateDbincStatus.db_key;
 
   updateDbincStatus(db_key, parent_key);
   deb('updateDbincStatus - normal return for dbinc=' || dbinc_key);
 
  EXCEPTION
    WHEN no_data_found THEN
       deb('updateDbincStatus- Last parent is  ' || dbinc_key);
       IF (dbinc_key is NOT NULL) THEN -- set last incarnation in the chain
           update dbinc set dbinc_status='PARENT'
              where dbinc_key=updateDbincStatus.dbinc_key and
                    db_key = updateDbincStatus.db_key;
       END IF;
       return;  -- reached the last known parent
    WHEN OTHERS THEN
       deb('updateDbincStatus - rollback all, release locks');
       rollback;
       RAISE;
  END;
 END updateDbincStatus;
BEGIN
 
--
  UPDATE db SET curr_dbinc_key = recomputeDbincStatus.dbinc_key
    WHERE db_key = recomputeDbincStatus.db_key;
 
  UPDATE dbinc SET dbinc_status='ORPHAN'
     WHERE dbinc.db_key = recomputeDbincStatus.db_key;
  updateDbincStatus(db_key, dbinc_key);
  UPDATE dbinc SET dbinc_status='CURRENT'
    where dbinc_key=recomputeDbincStatus.dbinc_key and
          db_key = recomputeDbincStatus.db_key;
END recomputeDbincStatus;
 
--
--
FUNCTION isOrsMedia(media IN VARCHAR2) RETURN BOOLEAN IS
BEGIN
   IF (media LIKE '%Recovery Appliance%') THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END isOrsMedia;
 
--
PROCEDURE initOrsAdmin IS
BEGIN
   EXECUTE IMMEDIATE 'begin :owner := dbms_rai_owner; end;'
      USING OUT orsadmin_user;
EXCEPTION
   WHEN OTHERS THEN
      deb('initOrsAdmin(DO_NOT_IGNORE):' || substr(sqlerrm, 1, 132));
END initOrsAdmin;
 
--
PROCEDURE inheritPdbInc(
   db_key           IN NUMBER
  ,dbinc_key        IN NUMBER
  ,reset_scn        IN NUMBER
  ,parent_dbinc_key IN NUMBER) IS
BEGIN
   deb('inheritPdbInc - dbinc_key=' || dbinc_key ||
       ' parent_dbinc_key=' || parent_dbinc_key);
 
--
   FOR r IN (SELECT pdb.pdb_key, pdbinc.pdbinc_key
               FROM pdbinc, pdb
              WHERE pdb.pdb_key = pdbinc.pdb_key
                AND pdb.db_key  = inheritPdbInc.db_key
                AND pdb.con_id in (0, 1))
   LOOP
      deb('inheritPdbInc root');
      INSERT INTO pdb_dbinc
         (dbinc_key, pdb_key, drop_scn, drop_time, curr_pdbinc_key)
      VALUES
         (inheritPdbInc.dbinc_key, r.pdb_key, NULL, NULL, r.pdbinc_key);
   END LOOP;
 
   IF (parent_dbinc_key IS NULL) THEN
      RETURN;
   END IF;
 
--
   FOR r IN (SELECT pdb.pdb_key, pdb_dbinc.curr_pdbinc_key
               FROM pdb_dbinc, pdb
              WHERE pdb_dbinc.dbinc_key = inheritPdbInc.parent_dbinc_key
                AND pdb.pdb_key = pdb_dbinc.pdb_key
                AND pdb.create_scn < inheritPdbInc.reset_scn
                AND (pdb_dbinc.drop_scn IS NULL OR
                     pdb_dbinc.drop_scn > inheritPdbInc.reset_scn)
                AND pdb.con_id > 1)
   LOOP
      deb('inheritPdbInc - pdb_key=' || r.pdb_key ||
          ' curr_pdbinc_key=' || r.curr_pdbinc_key);
 
      INSERT INTO pdb_dbinc
         (dbinc_key, pdb_key, drop_scn, drop_time, curr_pdbinc_key)
      VALUES
         (inheritPdbInc.dbinc_key, r.pdb_key, NULL, NULL, r.curr_pdbinc_key);
   END LOOP;
END inheritPdbInc;
 
--
PROCEDURE recomputePluggableDbincStatus(dbinc_key IN NUMBER) IS
   CURSOR pdbincQ(dbinc_key NUMBER) IS
      SELECT curr_pdbinc_key, pdb_key
        FROM pdb_dbinc
       WHERE dbinc_key = pdbincQ.dbinc_key;
 
--
   PROCEDURE updatePluggableDbincStatus(pdbinc_key IN NUMBER) IS
      parent_key NUMBER;
   BEGIN
      UPDATE pdbinc SET pdbinc_status='PARENT'
       WHERE pdbinc_key = updatePluggableDbincStatus.pdbinc_key;
 
--
      SELECT parent_pdbinc_key INTO parent_key FROM pdbinc
       WHERE pdbinc_key= updatePluggableDbincStatus.pdbinc_key;
 
      updatePluggableDbincStatus(parent_key);
   EXCEPTION
      WHEN no_data_found THEN
         RETURN; -- reached the last known parent
   END updatePluggableDbincStatus;
BEGIN
   deb('recomputePluggableDbincStatus for dbinc_key ' || dbinc_key);
   FOR pdbincQrec in pdbincQ(recomputePluggableDbincStatus.dbinc_key) LOOP
--
      UPDATE pdbinc SET pdbinc_status = 'ORPHAN'
       WHERE pdbinc.pdb_key = pdbincQrec.pdb_key;
 
--
      updatePluggableDbincStatus(pdbincQrec.curr_pdbinc_key);
 
      UPDATE pdbinc SET pdbinc_status='CURRENT'
       WHERE pdbinc.pdbinc_key = pdbincQrec.curr_pdbinc_key;
   END LOOP;
END recomputePluggableDbincStatus;
 
FUNCTION getPdbInc(
   birth_scn      IN NUMBER
  ,con_id         IN NUMBER
  ,out_pdb_key   OUT NUMBER
) RETURN NUMBER IS
   local_pdbinc_key   NUMBER;
BEGIN
   IF (last_full_ckp_scn IS NULL) THEN
      deb('getPdbInc - full resync for first time');
      SELECT pdbinc_key, pdb_key
        INTO local_pdbinc_key, out_pdb_key
        FROM rci_pdbinc_this_dbinc pdbinc
       WHERE pdbinc.dbinc_key = this_dbinc_key
         AND pdbinc.drop_scn IS NULL
         AND pdbinc.con_id = getPdbInc.con_id
         AND pdbinc.pdbinc_status = 'CURRENT';
   ELSE
      deb('getPdbInc - last_full_ckp_scn ' || last_full_ckp_scn ||
                     ' birth_scn ' || birth_scn);
      FOR r IN (SELECT pdbinc.pdbinc_key, pdbinc.pdb_key, pdbinc.inc_scn,
                       pdbinc.next_inc_scn
                  FROM rci_pdbinc_this_dbinc pdbinc
                 WHERE pdbinc.dbinc_key = this_dbinc_key
                   AND pdbinc.drop_scn IS NULL
                   AND pdbinc.con_id  = getPdbInc.con_id
                   AND pdbinc.next_inc_scn > last_full_ckp_scn
                 ORDER BY pdbinc.inc_scn)
      LOOP
--
         IF (birth_scn >= r.inc_scn      AND
             birth_scn <= r.next_inc_scn AND
             birth_scn >= last_full_ckp_scn) THEN
            local_pdbinc_key := r.pdbinc_key;
            out_pdb_key      := r.pdb_key;
            EXIT;
         END IF;
 
         local_pdbinc_key := r.pdbinc_key;
         out_pdb_key      := r.pdb_key;
      END LOOP;
   END IF;
 
   IF (local_pdbinc_key IS NULL) THEN
      raise_application_error(-20999, 'getPdbInc - pdbinc not known');
   ELSE
      deb('getPdbInc - pdbinc ' || local_pdbinc_key);
   END IF;
   RETURN local_pdbinc_key;
END getPdbInc;
 
/* convert guid to pdb key */
FUNCTION guidToPdbKey(guid IN RAW, dropped_pdb IN binary_integer)
RETURN NUMBER IS
   l_pdb_key NUMBER;
BEGIN
   IF (guid IS NULL OR guid = ZERO_GUID) THEN
      SELECT pdb_key INTO l_pdb_key
        FROM pdb
       WHERE pdb.db_key = this_db_key
         AND pdb.con_id IN (1, 0);
   ELSE
      SELECT max(pdb_key) INTO l_pdb_key
        FROM pdb
       WHERE pdb.db_key = this_db_key
         AND pdb.guid   = guidToPdbKey.guid;
   END IF;
 
--
--
--
--
--
   IF (l_pdb_key IS NULL) THEN
      IF (dropped_pdb > 0) THEN
         SELECT pdb_key INTO l_pdb_key
           FROM pdb
          WHERE pdb.db_key = this_db_key
            AND pdb.con_id IN (1, 0);
      ELSE
         RAISE no_data_found;
      END IF;
   END IF;
 
   RETURN l_pdb_key;
EXCEPTION
   WHEN OTHERS THEN
      deb('guidToPdbKey translation failed for ' || guid);
      RAISE;
END guidToPdbKey;
 
/*-------------------*
 * Register Database *
 *-------------------*/
PROCEDURE registerDatabase(db_id          IN NUMBER
                          ,db_name        IN VARCHAR2
                          ,reset_scn      IN NUMBER
                          ,reset_time     IN DATE
                          ,db_unique_name IN VARCHAR2 default null
                          ,con_id         IN NUMBER   default 0
                          ,guid           IN RAW      default null
                          ) IS
 
  loc_dbinc_key number;
  loc_db_key    number;
  loc_pdb_key   number;
  p_dbun        node.db_unique_name%TYPE := upper(db_unique_name);
 
BEGIN
--
  BEGIN
    SELECT NULL INTO loc_dbinc_key FROM rcver WHERE version = catalogVersion;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20298, 'Not compatible recovery catalog');
  END;
 
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030 , 'Resync in progress');
  END IF;
 
  this_db_key := NULL;
  this_dbinc_key := NULL;
 
--
--
  IF p_dbun is NULL and registerDbPending.dbid is null THEN
     registerDbPending.dbid   := db_id;
     registerDbPending.con_id := con_id;
     registerDbPending.guid   := guid;
     deb('registerDatabase pushed to setDatabase stage for dbid = ' || db_id);
     RETURN;
  END IF;
 
  BEGIN
    INSERT INTO db(db_key, db_id, reg_db_unique_name) 
      VALUES(rman_seq.nextval, db_id, p_dbun)
    RETURNING db_key INTO loc_db_key;
  EXCEPTION
    WHEN dup_val_on_index THEN
      raise_application_error(-20002, 'Database already registered');
  END;
 
  INSERT INTO dbinc
    (dbinc_key, db_key, db_name, reset_scn, reset_time)
  VALUES
    (rman_seq.nextval, loc_db_key, upper(db_name), reset_scn, reset_time)
  RETURNING dbinc_key INTO loc_dbinc_key;
 
--
  recomputeDbincStatus(loc_db_key, loc_dbinc_key);
 
  deb('registerDatabase - ' ||
      'adding node row, db_unique_name value=' || p_dbun);
  INSERT INTO node(db_key, force_resync2cf, database_role, site_key,
                   db_unique_name)
     VALUES(loc_db_key, 'NO', 'PRIMARY', rman_seq.nextval,
            p_dbun);
 
  deb('registerDatabase - adding a row to pdb for rootdb, con_id=' ||
       con_id || ' guid=' || nvl(guid, ZERO_GUID));
  IF (con_id IS NULL OR con_id NOT IN (0, 1)) THEN
     raise_application_error(-20999, 
        'internal error: only con_id 0 or 1 can register database');
  END IF;
 
  INSERT INTO pdb
     (pdb_key, db_key, name, con_id, db_id, create_scn, guid)
  VALUES
     (rman_seq.nextval, loc_db_key, NULL, con_id, db_id, 1,
      nvl(guid, ZERO_GUID))
  RETURNING pdb_key INTO loc_pdb_key;
 
  INSERT INTO pdbinc
     (pdbinc_key, pdb_key, born_dbinc_key, inc_scn,
      begin_reset_scn, begin_reset_time, end_reset_scn,
      parent_pdbinc_key,  pdbinc_status)
  VALUES
     (rman_seq.nextval, loc_pdb_key, loc_dbinc_key, 1,
      reset_scn, reset_time, 1,
      NULL, 'CURRENT');
 
  INSERT INTO pdb_dbinc
     (dbinc_key, pdb_key, drop_scn, drop_time, curr_pdbinc_key)
  VALUES
     (loc_dbinc_key, loc_pdb_key, NULL, NULL, rman_seq.currval);
 
  setReason(RESYNC_REASON_NOACTION);
  commitChanges('registerDatabase');
 
--
  registerDbPending.dbid := null;
 
--
EXCEPTION
  WHEN OTHERS THEN
    deb('registerDatabase - rollback, release locks');
    rollback;
--
    registerDbPending.dbid := null;
    RAISE;
 
END registerDatabase;
 
--
--
 
PROCEDURE resetDatabase(db_id             IN NUMBER
                       ,db_name           IN VARCHAR2
                       ,reset_scn         IN NUMBER
                       ,reset_time        IN DATE
                       ,parent_reset_scn  IN NUMBER
                       ,parent_reset_time IN DATE
                       ) IS
 
local          dbinc%rowtype;                    -- local variables
BEGIN
--
  BEGIN
    SELECT NULL INTO local.db_key FROM rcver WHERE version = catalogVersion;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20298, 'Not compatible with recovery catalog');
  END;
 
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
  IF (db_id IS NULL) THEN
    raise_application_error(-20007, 'db_id is null');
  END IF;
 
  this_db_key := NULL;
  this_dbinc_key := NULL;
 
  BEGIN
    SELECT db_key, curr_dbinc_key INTO local.db_key, local.dbinc_key
    FROM db
    WHERE db.db_id = resetDatabase.db_id               -- should return 1 row
    FOR UPDATE OF db.db_key;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20001, 'Database not found');
  END;
 
--
  BEGIN
    SELECT dbinc_key INTO local.parent_dbinc_key
    FROM dbinc
    WHERE dbinc.db_key = local.db_key
    AND   dbinc.reset_scn = resetDatabase.parent_reset_scn
    AND   dbinc.reset_time = resetDatabase.parent_reset_time;
  EXCEPTION
    WHEN no_data_found THEN
      local.parent_dbinc_key := NULL;
  END;
 
--
  BEGIN
    INSERT INTO dbinc
      (dbinc_key, db_key, db_name, reset_scn, reset_time, parent_dbinc_key)
    VALUES
      (rman_seq.nextval, local.db_key, upper(db_name), reset_scn,
       reset_time, local.parent_dbinc_key)
    RETURNING dbinc_key INTO local.dbinc_key;
  EXCEPTION
    WHEN dup_val_on_index THEN
      raise_application_error(-20009, 'Db incarnation already registered');
  END;
 
  inheritPdbInc(
     local.db_key, local.dbinc_key, reset_scn, local.parent_dbinc_key);
 
--
  recomputeDbincStatus(local.db_key, local.dbinc_key);
  recomputePluggableDbincStatus(local.dbinc_key);
  commitChanges('resetDatabase');
 
--
EXCEPTION
  WHEN OTHERS THEN
    deb('resetDatabase - rollback, release locks');
    rollback;
    RAISE;
 
END resetDatabase;
 
--
FUNCTION resetDatabase(db_id             IN NUMBER
                      ,db_name           IN VARCHAR2
                      ,reset_scn         IN NUMBER
                      ,reset_time        IN DATE
                      ,parent_reset_scn  IN NUMBER
                      ,parent_reset_time IN DATE
                       ) RETURN NUMBER IS
local    dbinc%rowtype;                    -- local variables
BEGIN
--
  BEGIN
    SELECT NULL INTO local.db_key FROM rcver WHERE version = catalogVersion;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20298, 'Not compatible with recovery catalog');
  END;
 
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
  IF (db_id IS NULL) THEN
    raise_application_error(-20007, 'db_id is null');
  END IF;
 
  BEGIN
    SELECT db_key INTO local.db_key
      FROM db
     WHERE db.db_id = resetDatabase.db_id;              -- should return 1 row
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20001, 'Database not found');
  END;
 
  SELECT dbinc_key INTO local.dbinc_key
    FROM dbinc
   WHERE dbinc.db_key = local.db_key
     AND dbinc.reset_scn = resetDatabase.reset_scn
     AND dbinc.reset_time = resetDatabase.reset_time;
 
  resetDatabase(local.dbinc_key, db_name);
 
  RETURN local.dbinc_key;
END resetDatabase;
 
--
 
PROCEDURE resetDatabase(
  dbinc_key  IN NUMBER
 ,db_name    IN VARCHAR2
) IS
 
local     dbinc%rowtype;                    -- local variables
BEGIN
--
  BEGIN
    SELECT NULL INTO local.db_key FROM rcver WHERE version = catalogVersion;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20298, 'Not compatible with recovery catalog');
  END;
 
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
  IF (dbinc_key IS NULL) THEN
    raise_application_error(-20008, 'Database incarnation key is missing');
  END IF;
 
  this_db_key := NULL;
  this_dbinc_key := NULL;
 
  BEGIN
    SELECT db_key, db_name
    INTO local.db_key, local.db_name
    FROM dbinc
    WHERE dbinc.dbinc_key = resetDatabase.dbinc_key;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20010, 'Database incarnation not found');
  END;
 
--
  IF (upper(db_name) <> local.db_name) THEN
     IF (db_name != 'UNKNOWN') THEN
        UPDATE dbinc
           SET db_name = upper(resetDatabase.db_name)
         WHERE dbinc.dbinc_key = resetDatabase.dbinc_key;
     ELSE
        raise_application_error(-20004, 'Database name does not match');
     END IF;
  END IF;
 
--
  recomputeDbincStatus(local.db_key, resetDatabase.dbinc_key);
  recomputePluggableDbincStatus(resetDatabase.dbinc_key);
 
  commitChanges('resetDatabase');
 
--
EXCEPTION
  WHEN OTHERS THEN
    deb('resetDatabase - rollback, release locks');
    rollback;
    RAISE;
END resetDatabase;
 
procedure resetDatabase(
  dbinc_key  IN  number
 ,db_name    IN  varchar2
 ,reset_scn  OUT number
 ,reset_time OUT date
 ,db_id      IN  number DEFAULT NULL
) IS
  local_db_key dbinc.db_key%TYPE;
BEGIN
  IF db_id IS NOT NULL THEN
     BEGIN
       SELECT db_key INTO local_db_key
         FROM db
        WHERE db.db_id = resetDatabase.db_id;           -- should return 1 row
     EXCEPTION
       WHEN no_data_found THEN
         raise_application_error(-20001, 'Database not found');
     END;
  ELSE
     local_db_key := this_db_key;
  END IF;
 
  BEGIN
    SELECT reset_scn, reset_time
    INTO resetDatabase.reset_scn, resetDatabase.reset_time
    FROM dbinc
    WHERE dbinc.dbinc_key = resetDatabase.dbinc_key AND
          (db_id IS NULL OR dbinc.db_key = local_db_key);
 
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20010, 'Database incarnation not found');
  END;
 
  resetDatabase(dbinc_key, db_name);
END resetDatabase;
 
PROCEDURE unRegisterDatabase(
  db_key     IN NUMBER DEFAULT NULL
 ,db_id      IN NUMBER
) IS
 
tmp         NUMBER;
cnt         NUMBER := 0;
 
BEGIN
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
 
--
  BEGIN
    SELECT 0 INTO tmp FROM db
    WHERE db.db_id = unRegisterDatabase.db_id;
  EXCEPTION
  WHEN no_data_found THEN
    raise_application_error(-20001, 'Database not found');
  END;
 
--
--
  SELECT count(*) INTO cnt FROM db
   WHERE storage_prov = 'Y'
     AND db.db_id = unRegisterDatabase.db_id;
 
  IF (cnt <> 0) THEN
    raise_application_error(-20301, 'Cannot unregister database');
  END IF;
 
--
--
  SELECT 0 INTO tmp FROM db
    WHERE db.db_id = unRegisterDatabase.db_id FOR UPDATE OF db.db_key;
 
  DELETE FROM bp 
    WHERE db_key = 
      (select db_key from db where db.db_id = unRegisterDatabase.db_id);
   
  DELETE FROM db WHERE db.db_id = unRegisterDatabase.db_id;
  commitChanges('unRegisterDatabase');
 
--
EXCEPTION
  WHEN OTHERS THEN
    deb('unregisterDatabase - rollback, release locks');
    rollback;
    RAISE;
 
END unRegisterDatabase;
 
--
PROCEDURE setArchiveFileScopeAttributes(logs_shared  IN NUMBER) IS
BEGIN
   deb('setArchiveFileScopeAttributes');
 
   IF logs_shared > 0 THEN
      dbms_rcvcat.logs_shared := TRUE#;
   ELSE
      dbms_rcvcat.logs_shared := FALSE#;
   END IF;
   deb('logs_shared = ' || dbms_rcvcat.logs_shared);
 
   dbms_rcvman.setArchiveFileScopeAttributes(logs_shared);
 
   deb('exiting setArchiveFileScopeAttributes');
END setArchiveFileScopeAttributes;
 
--
PROCEDURE setBackupFileScopeAttributes(
                 disk_backups_shared IN NUMBER,
                 tape_backups_shared IN NUMBER) IS
   lsite_key NUMBER;
BEGIN
   deb('setBackupFileScopeAttributes');
 
   IF disk_backups_shared IS NOT NULL THEN
      IF disk_backups_shared > 0 THEN
         dbms_rcvcat.disk_backups_shared := TRUE#;
      ELSE
         dbms_rcvcat.disk_backups_shared := FALSE#;
      END IF;
   END IF;
 
   IF tape_backups_shared IS NOT NULL THEN
      IF tape_backups_shared > 0 THEN
         dbms_rcvcat.tape_backups_shared := TRUE#;
      ELSE
         dbms_rcvcat.tape_backups_shared := FALSE#;
      END IF;
   END IF;
 
   deb('disk_backups_shared='||dbms_rcvcat.disk_backups_shared);
   deb('tape_backups_shared='||dbms_rcvcat.tape_backups_shared);
 
   dbms_rcvman.setBackupFileScopeAttributes(disk_backups_shared,
                                            tape_backups_shared);
 
   deb('exiting setBackupFileScopeAttributes');
END setBackupFileScopeAttributes;
 
/*--------------*
 * Set Database *
 *--------------*/
 
--
 
PROCEDURE setDatabase(db_name        IN VARCHAR2
                     ,reset_scn      IN NUMBER
                     ,reset_time     IN DATE
                     ,db_id          IN NUMBER
                     ,db_unique_name IN VARCHAR2
                     ,dummy_instance IN BOOLEAN
                     ,cf_type        IN NUMBER
                     ,site_aware     IN BOOLEAN default FALSE
                     ,ors_instance   IN BOOLEAN default FALSE) IS
 
   local             dbinc%rowtype;                    -- local variables
   current_inc       VARCHAR2(3);
   dbnm              dbinc.db_name%TYPE;
   dbnm_in           dbinc.db_name%TYPE;
   rid               varchar2(18);
   local_site_key    number;
   dbunqnm           node.db_unique_name%TYPE;
   db_role           node.database_role%type;
   dbunqnm_in        node.db_unique_name%TYPE;
   l_reg_dbunqnm     node.db_unique_name%TYPE;
   cat_version       varchar2(12);
   vpd_version       varchar2(12);
   prim_dbunqnm_in   node.db_unique_name%TYPE;
   db_id_in          number;
   tmp_dbunqnm_cnt   number;
   tmp_primary_cnt   number;
   cf_type_in        number;
   my_dbunqnm        watermarks.db_unique_name%TYPE;
BEGIN
--
  BEGIN
    SELECT NULL INTO local.db_key FROM rcver WHERE version = catalogVersion;
  EXCEPTION
    WHEN no_data_found THEN
      raise_application_error(-20298, 'Not compatible with recovery catalog');
  END;
 
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
 
  this_db_key := NULL;                  -- clear in case exception raised
  this_dbinc_key := NULL;
  kccdivts := NULL;
  dbnm_in := upper(db_name);
  dbunqnm_in := upper(db_unique_name);
  db_id_in := db_id;
  cf_type_in := cf_type;
 
--
--
  IF registerDbPending.dbid = db_id AND NOT ors_instance THEN
     registerDatabase(db_id          => db_id,
                      db_name        => dbnm_in,
                      reset_scn      => reset_scn,
                      reset_time     => reset_time,
                      db_unique_name => dbunqnm_in,
                      con_id         => registerDbPending.con_id,
                      guid           => registerDbPending.guid);
  END IF;  
 
  deb('setDatabase(db_unique_name)='||db_unique_name);
 
--
  IF ors_instance THEN
    IF db_unique_name IS NULL OR substr(db_unique_name, 1, 1) = '$' THEN
      raise_application_error(-20999, 
         'internal error: db_unique_name value is invalid=' || db_unique_name);
    END IF;
    IF db_id IS NULL THEN
      raise_application_error(-20999, 
         'internal error: db_id must be specified for ORS instance');
    END IF;
    cf_type_in := CF_STANDBY;
 
--
--
--
    IF instr(dbunqnm_in, '$'|| db_id) = 0 THEN
       dbunqnm_in := dbunqnm_in || '$' || db_id;
    END IF;
    dbunqnm_in := '$' || dbunqnm_in;
  END IF;
 
<<now_try_with_dbid>>
 
--
--
--
--
 
  IF (db_id_in IS NOT NULL) THEN
    BEGIN
      SELECT db_key, curr_dbinc_key, db_name, reg_db_unique_name
        INTO local.db_key, local.dbinc_key, local.db_name, l_reg_dbunqnm
      FROM db
      WHERE db.db_id = db_id_in;                        -- should return 1 row
    EXCEPTION
      WHEN no_data_found THEN
        raise_application_error(-20001, 'Database not found');
    END;
 
--
    IF (dbnm_in is NOT NULL AND db_id is NOT NULL) THEN
--
--
--
--
 
       BEGIN
         SELECT decode(dbinc.dbinc_key, db.curr_dbinc_key, 'YES', 'NO'),
                dbinc.db_name, dbinc.rowid
         INTO   current_inc, dbnm, rid
         FROM db, dbinc
         WHERE db.db_key = dbinc.db_key
         AND   db.db_id = setDatabase.db_id
         AND   dbinc.reset_scn = setDatabase.reset_scn
         AND   dbinc.reset_time = setDatabase.reset_time;
       EXCEPTION
         WHEN no_data_found THEN
           raise_application_error(-20003, 'Database incarnation not found');
       END;
 
       IF (current_inc = 'NO') THEN
         raise_application_error(-20011, 'Database incarnation not current');
       END IF;
       IF (dbnm != dbnm_in) THEN
          UPDATE dbinc
             SET dbinc.db_name = dbnm_in
           WHERE rowid = rid;
          commitChanges('setDatabase');
       END IF;
    END IF;
 
    IF (NOT dummy_instance AND dbunqnm_in IS NOT NULL) THEN
       deb('setDatabase - check db_unique_name= ' || dbunqnm_in ||
           ' cf_type_in= ' || cf_type_in);
 
--
--
--
--
       IF l_reg_dbunqnm IS NULL THEN
          UPDATE db
             SET db.reg_db_unique_name = dbunqnm_in
           WHERE db.db_key = local.db_key
             AND db.db_id = setDatabase.db_id
             AND db.reg_db_unique_name IS NULL;
          deb('setDatabase: updating null db_unique_name in DB with  ' ||
              dbunqnm_in || 'number of rows updated ' || sql%rowcount);
          commitChanges('setDatabase1');
       END IF;
 
--
--
--
--
--
--
       SELECT count(*) into tmp_dbunqnm_cnt
          FROM node
          WHERE node.db_unique_name is NULL
            AND node.db_key = local.db_key;
       IF tmp_dbunqnm_cnt = 1 THEN
          SELECT count(*) into tmp_dbunqnm_cnt
             FROM node
             WHERE node.db_unique_name is not NULL
               AND node.db_key = local.db_key;
          IF tmp_dbunqnm_cnt > 0 THEN
             raise_application_error(-20999, 
                'internal error: found non-null and null site name');
          END IF;
          UPDATE NODE SET node.db_unique_name = dbunqnm_in
             WHERE node.db_unique_name is NULL
               AND node.db_key = local.db_key;
          deb('setDatabase: updating null db_unique_name with ' ||dbunqnm_in ||
              'number of rows updated ' || sql%rowcount);
       END IF;
 
       BEGIN
--
          SELECT node.database_role, site_key
            INTO db_role, local_site_key
            FROM node
           WHERE node.db_key = local.db_key
             AND node.db_unique_name = dbunqnm_in;
 
--
--
          SELECT count(*) into tmp_primary_cnt
            FROM node
           WHERE node.database_role = 'PRIMARY'
             AND site_key <> local_site_key
             AND node.db_key = local.db_key;
 
          deb('setDatabase - check database_role');
          IF (cf_type_in = CF_STANDBY AND db_role != 'STANDBY') THEN
--
             deb('setDatabase - database role not standby - updating');
             UPDATE node
                SET node.database_role = 
                       decode(substr(node.db_unique_name,1,1), '$',
                             'RA', 'STANDBY'),
                    node.high_conf_recid = 0
              WHERE site_key = local_site_key
                AND (node.database_role <>
                     decode(substr(node.db_unique_name,1,1), '$',
                            'RA', 'STANDBY'));
             commitChanges('setDatabase2');
          ELSIF ((cf_type_in = CF_CURRENT OR cf_type_in = CF_BACKUP) AND 
                 (db_role != 'PRIMARY' OR tmp_primary_cnt > 1)) THEN
--
             deb('setDatabase - not primary or primary_cnt='||tmp_primary_cnt);
--
             UPDATE node
                SET node.database_role =
                    decode(substr(node.db_unique_name,1,1), '$',
                          'RA', 'STANDBY'),
                    node.high_conf_recid = 0
              WHERE site_key <> local_site_key
                AND db_key = local.db_key;
 
--
--
--
--
             UPDATE node
                SET node.database_role = 'PRIMARY',
                    node.high_conf_recid = 0,
                    high_ic_recid = 0,  
                    high_ts_recid = NULL,
                    high_df_recid = NULL,
                    high_rt_recid = NULL,
                    high_orl_recid = NULL,
                    high_tf_recid = 0,
                    high_pdb_recid = NULL,
                    high_pic_recid = 0
              WHERE site_key = local_site_key
                AND db_key = local.db_key;
 
             sessionWaterMarks.high_ic_recid  := 0;
             sessionWaterMarks.high_pic_recid := 0;
 
             commitChanges('setDatabase3');
             prev_sessionWaterMarks := sessionWaterMarks;
          END IF;
       EXCEPTION
          WHEN no_data_found THEN
             IF (cf_type_in = CF_CURRENT OR cf_type_in = CF_BACKUP) THEN
                deb('setDatabase: adding node row, new primary database...');
--
                UPDATE node
                   SET node.database_role = 
                          decode(substr(node.db_unique_name,1,1),'$',
                                 'RA', 'STANDBY'),
                       node.high_conf_recid = 0
                   WHERE db_key = local.db_key;
                INSERT INTO node(db_unique_name, db_key, force_resync2cf,
                                 database_role, site_key)
                VALUES(dbunqnm_in, local.db_key, 'NO', 
                       'PRIMARY', rman_seq.nextval);
                commitChanges('setDatabase4');
             ELSIF cf_type_in = CF_STANDBY THEN
--
                deb('setDatabase: adding node row, new standby database...');
                BEGIN
                   INSERT INTO node(db_unique_name, db_key, force_resync2cf,
                                    database_role, 
                                    site_key)
                   VALUES(dbunqnm_in, local.db_key, 'NO', 
                          decode(substr(dbunqnm_in,1,1), '$',
                                 'RA', 'STANDBY'), 
                          rman_seq.nextval);
                   commitChanges('setDatabase5');
                EXCEPTION
                  WHEN dup_val_on_index THEN
                     deb('setDatabase - someone inserted same standby');
                END;
             ELSE
--
--
--
--
--
--
                BEGIN
                   deb('setDatabase - falking db_unique_name from'||
                       dbunqnm_in);
                   SELECT db_unique_name into prim_dbunqnm_in from node
                      WHERE db_key = local.db_key AND
                            database_role = 'PRIMARY';
                   dbunqnm_in := prim_dbunqnm_in;
                   deb('setDatabase - changing dbunqnm_in to ' || dbunqnm_in);
                EXCEPTION
                   WHEN no_data_found THEN
                      deb('setDatabase - unknown dbunqnm_in set to null');
                      dbunqnm_in := null;
                END;
             END IF;
       END;
    END IF;
--
  ELSIF (dbnm_in IS NOT NULL) THEN
 
    BEGIN
      SELECT db.db_key, db.curr_dbinc_key, db.db_id
        INTO local.db_key, local.dbinc_key, db_id_in
        FROM db, dbinc
       WHERE db.curr_dbinc_key = dbinc.dbinc_key
         AND dbinc.db_name   = dbnm_in;
    EXCEPTION
      WHEN no_data_found THEN
        raise_application_error(-20001, 'Database not found');
      WHEN too_many_rows THEN
        raise_application_error(-20005, 'Database name is ambiguous');
    END;
    GOTO now_try_with_dbid;
 
  ELSE
    raise_application_error(-20006, 'Database name is missing');
  END IF;
 
--
--
  this_db_unique_name := dbunqnm_in;
  this_db_key         := local.db_key;
  this_dbinc_key      := local.dbinc_key;
  deb('setDatabase - this_db_unique_name='||this_db_unique_name);
  deb('setDatabase - this_dbinc_key:'||to_char(this_dbinc_key));
  deb('setDatabase - this_db_key:'||to_char(this_db_key));
 
  BEGIN
    select site_key into this_site_key from node
        where db_unique_name=upper(dbunqnm_in)
          AND db_key = this_db_key;
    deb('setDatabase - this_site_key:'||this_site_key);
  EXCEPTION
    WHEN no_data_found THEN
      BEGIN
         select site_key, db_unique_name 
             into this_site_key, dbunqnm_in from node
             where database_role='PRIMARY'
               AND db_key = this_db_key;
         deb('setDatabase - this_site_key(primary):'||this_site_key);
      EXCEPTION
         WHEN no_data_found THEN
--
--
            deb('setDatabase - this_site_key is null');
            this_site_key := null;
      END;
  END;
 
  cntbs := 0;
 
--
--
  dbms_rcvman.setDatabase (dbnm_in, reset_scn, reset_time,
                           db_id, this_db_unique_name, site_aware,
                           dummy_instance, ors_instance);
 
  client_site_aware := site_aware;
 
  IF client_site_aware THEN
     setArchiveFileScopeAttributes(logs_shared => 0);
     setBackupFileScopeAttributes (disk_backups_shared => 0, 
                                   tape_backups_shared => 1);
  END IF;
 
  IF this_is_ors THEN
    BEGIN
      IF this_is_ors_admin THEN
         EXECUTE IMMEDIATE 'select rtrim(ltrim(value)) 
            from sys.v_$parameter where lower(name)=''db_unique_name'''
            into my_dbunqnm;
      ELSE
         my_dbunqnm := this_db_unique_name;
      END IF;
      INSERT INTO do_seq(db_key) VALUES (this_db_key);
      INSERT INTO watermarks(db_key, db_unique_name, rs_version_stamp)
        VALUES (this_db_key, my_dbunqnm, SYSDATE);
      commitChanges('setDatabase6 - added a row to watermarks,'||this_db_key);
    EXCEPTION
      WHEN dup_val_on_index THEN
        NULL;
    END;
  END IF;
END setDatabase;
 
--
PROCEDURE setDatabase(db_name        IN VARCHAR2
                     ,reset_scn      IN NUMBER
                     ,reset_time     IN DATE
                     ,db_id          IN NUMBER
                     ,db_unique_name IN VARCHAR2 DEFAULT NULL) IS
BEGIN
   setDatabase(db_name        => db_name,
               reset_scn      => reset_scn,
               reset_time     => reset_time,
               db_id          => db_id,
               db_unique_name => db_unique_name,
               dummy_instance => FALSE,
               cf_type        => CF_CURRENT);
END setDatabase;
 
--
--
--
--
 
PROCEDURE setDatabase(dbinc_key number) IS
  dbinc_row dbinc%ROWTYPE;
  db_row    db%ROWTYPE;
BEGIN
 
  select * into dbinc_row from dbinc where dbinc_key = setDatabase.dbinc_key;
  select * into db_row from db where db_key = dbinc_row.db_key;
  setDatabase(db_name        => dbinc_row.db_name,
              reset_scn      => dbinc_row.reset_scn,
              reset_time     => dbinc_row.reset_time,
              db_id          => db_row.db_id);
 
END setDatabase;
 
procedure setDatabase IS
  dbinckey number;
BEGIN
 
  select curr_dbinc_key into dbinckey from db;
  setDatabase(dbinckey);
 
END setDatabase;
 
 
--
--
--
--
--
--
--
--
--
PROCEDURE doReplicationReconcile(p_db_key           IN NUMBER, 
                                 p_lib_key          IN NUMBER, 
                                 p_server_key    IN NUMBER) IS
  dbinckey              NUMBER;
  dbinc_row             dbinc%ROWTYPE;
  db_row                db%ROWTYPE;
  l_dbun                VARCHAR2(512);
  l_save_this_server    SERVER%ROWTYPE;
  resync_done           NUMBER;
BEGIN
 
  deb('doReplicationReconcile: ' ||
      '( p_db_key=>' || p_db_key||
      ', p_lib_key=>' || p_lib_key||
      ', p_server_key=>' || p_server_key|| ')');
 
  select curr_dbinc_key into dbinckey from db WHERE db_key=p_db_key;
  select * into dbinc_row from dbinc where dbinc_key = dbinckey;
  select * into db_row from db where db_key = dbinc_row.db_key;
 
  execute immediate 'begin :url :=  dbms_ra_int.dbkey2name(:db_key); end;'
          using out this_url_db_unique_name, in dbinc_row.db_key;
 
  execute immediate 'select  rtrim(ltrim(value)) from sys.v_$parameter ' ||
                    'where lower(name)=''db_unique_name''' into l_dbun;
 
  deb('doReplicationReconcile: info for setDatabase ' ||
      '  db_name=>' || dbinc_row.db_name ||
      ', reset_scn=>' ||dbinc_row.reset_scn||
      ', reset_time=>' ||dbinc_row.reset_time||
      ', db_id=>' ||db_row.db_id||
      ', db_unique_name=>' ||l_dbun||
      ', this_url_db_unique_name=>' || this_url_db_unique_name);
 
--
  l_save_this_server  := this_server;
  
--
  this_lib_key := p_lib_key;
 
  init_package_vars(p_server_key);
   
  setDatabase(db_name        => dbinc_row.db_name,
              reset_scn      => dbinc_row.reset_scn,
              reset_time     => dbinc_row.reset_time,
              db_id          => db_row.db_id,
              db_unique_name => l_dbun,
              dummy_instance => FALSE,
              cf_type        => CF_STANDBY,
              site_aware     => TRUE,
              ors_instance   => TRUE);
  
--
  this_verrec := null;
  this_curr_inc := null;
  this_wmrec := null;
  this_v_wmrec := null;
  deb('clearing all variables used for reconcile');
  select nvl(count(*), 0) into resync_done 
     from ts where dbinc_key = this_dbinc_key;
  IF resync_done > 0 THEN
     writeFixedSections;
     readBackupSections;
  ELSE
     deb('doReplicationReconcile: resync not done, skipping reconcile');
  END IF;
  
  this_server  := l_save_this_server;
  this_lib_key := NULL;
  this_url_db_unique_name := NULL;
  this_verrec := null;
  this_curr_inc := null;
  this_wmrec := null;
  this_v_wmrec := null;
  deb('doReplicationReconcile: resync (suc)');
  
EXCEPTION
  WHEN OTHERS THEN
    deb('doReplicationReconcile: resync (fail):' || substr(sqlerrm, 1, 512));
    this_server := l_save_this_server;
    this_url_db_unique_name := NULL;
    this_verrec := null;
    this_curr_inc := null;
    this_wmrec := null;
    this_v_wmrec := null;
    RAISE;
    
END doReplicationReconcile;
 
 
/*-----------------------------*
 * Recovery Catalog Checkpoint *
 *-----------------------------*/
 
PROCEDURE lockForCkpt(ors_inspect IN boolean DEFAULT FALSE) IS
   local_dbid     NUMBER;
   local_dbkey    NUMBER;
   start_time     DATE := sysdate;
BEGIN
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
--
--
--
--
  SELECT db_id INTO local_dbid FROM db
  WHERE db_key = this_db_key FOR UPDATE OF db.db_key;
 
--
--
  IF this_is_ors THEN
    SELECT high_bp_recid INTO this_curr_bp_recid FROM watermarks
      WHERE db_key = this_db_key FOR UPDATE OF watermarks.high_bp_recid;
    this_high_bp_recid := this_curr_bp_recid;
  END IF;
 
  this_lock_ors_inspect := ors_inspect;
  deb('lockForCkpt - took ' || ((sysdate - start_time) * 86400) || ' seconds');
  deb('lockForCkpt - Obtained all locks for db ' || to_char(this_db_key));
 
--
--
--
--
END lockForCkpt;
 
FUNCTION ckptNeeded(
  ckp_scn          IN NUMBER
 ,ckp_cf_seq       IN NUMBER
 ,cf_version       IN DATE
 ,cf_type          IN NUMBER
 ,high_df_recid    IN NUMBER
 ,high_orl_recid   IN NUMBER
 ,high_cdf_recid   IN NUMBER
 ,high_al_recid    IN NUMBER
 ,high_bp_recid    IN NUMBER
 ,high_do_recid    IN NUMBER
 ,high_offr_recid  IN NUMBER
 ,high_pc_recid    IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_conf_recid  IN NUMBER  DEFAULT NULL -- for compatibility
 ,rltime           IN DATE    DEFAULT NULL -- for compatibility
 ,high_ts_recid    IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_bs_recid    IN NUMBER  DEFAULT NULL -- for compatibility
 ,lopen_reset_scn  IN number  DEFAULT NULL -- for compatibility
 ,lopen_reset_time IN DATE    DEFAULT NULL -- for compatibility
 ,high_ic_recid    IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_tf_recid    IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_rt_recid    IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_grsp_recid  IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_nrsp_recid  IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_bcr_recid   IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_pdb_recid   IN NUMBER  DEFAULT NULL -- for compatibility
 ,high_pic_recid   IN NUMBER  DEFAULT NULL -- for compatibility
) RETURN NUMBER IS
 
ckp_type              NUMBER;
local                 node%rowtype;
local_dbid            NUMBER := 0;
local_reset_time      DATE;
local_reset_scn       NUMBER := 0;
cksum                 NUMBER;
ckp_desc              VARCHAR2(30);
BEGIN
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
  IF (this_site_key IS NULL) THEN
    raise_application_error(-20199, 'Site key is not set');
  END IF;
 
  SELECT db_id
  INTO local_dbid
  FROM db
  WHERE db.db_key = this_db_key FOR UPDATE OF db.db_key;
 
  deb('ckptNeeded - Obtained all locks for database ' ||
      to_char(this_db_key));
 
--
  cksum := high_df_recid           + high_orl_recid          +
           high_cdf_recid          + high_al_recid           +
           high_bp_recid           + high_do_recid           +
           high_offr_recid         + nvl(high_pc_recid, 0)   +
           nvl(high_conf_recid, 0) + nvl(high_ts_recid, 0)   +
           nvl(high_bs_recid, 0)   + nvl(high_ic_recid, 0)   +
           nvl(high_tf_recid, 0)   + nvl(high_rt_recid, 0)   +
           nvl(high_grsp_recid, 0) + nvl(high_nrsp_recid, 0) +
           nvl(high_bcr_recid, 0)  + nvl(high_pdb_recid, 0)  +
           nvl(high_pic_recid, 0);
 
--
--
--
--
--
--
  SELECT cf_create_time, nvl(high_df_recid,0), nvl(high_ts_recid,0),
         nvl(high_orl_recid,0), nvl(high_cdf_recid,0), nvl(high_al_recid,0),
         nvl(high_bp_recid,0), nvl(high_do_recid,0), nvl(high_offr_recid,0),
         nvl(high_pc_recid,0), full_ckp_cf_seq, job_ckp_cf_seq,
         nvl(high_ic_recid,0), nvl(high_bs_recid,0), nvl(high_tf_recid, 0),
         nvl(high_rt_recid, 0), nvl(high_grsp_recid, 0),
         nvl(high_nrsp_recid, 0), nvl(high_bcr_recid, 0),
         high_conf_recid, force_resync2cf, nvl(high_pdb_recid, -1),
         nvl(high_pic_recid, 0)
    INTO   local.cf_create_time, local.high_df_recid, local.high_ts_recid,
         local.high_orl_recid, local.high_cdf_recid, local.high_al_recid,
         local.high_bp_recid,  local.high_do_recid, local.high_offr_recid,
         local.high_pc_recid,  local.full_ckp_cf_seq, local.job_ckp_cf_seq,
         local.high_ic_recid, local.high_bs_recid, local.high_tf_recid,
         local.high_rt_recid, local.high_grsp_recid, local.high_nrsp_recid,
         local.high_bcr_recid, local.high_conf_recid, local.force_resync2cf,
         local.high_pdb_recid, local.high_pic_recid
    FROM node
    WHERE site_key = this_site_key;
 
  SELECT reset_scn, reset_time into local_reset_scn, local_reset_time 
    FROM dbinc
    WHERE dbinc_key = this_dbinc_key;
 
  ckp_type := RESYNC_NONE;
  setReason(RESYNC_REASON_NONE);
 
  IF (rltime IS NOT NULL AND rltime != local_reset_time) THEN
--
--
--
--
--
--
    deb('ckptNeeded - rltime='||to_char(rltime)||
         ', local_reset_time='||to_char(local_reset_time));
    ckp_type := RESYNC_NONE;
    GOTO ret;
  ELSIF (cf_version = local.cf_create_time) THEN
    deb('ckptNeeded - local_reset_scn='||local_reset_scn||
                    ' lopen_reset_scn='||lopen_reset_scn);
    deb('ckptNeeded - local_reset_time='||local_reset_time||
                    ' lopen_reset_time='||lopen_reset_time);
--
--
--
--
    IF (cf_type = CF_CURRENT AND
        (lopen_reset_scn IS NULL or local_reset_scn = lopen_reset_scn) AND
        (lopen_reset_time IS NULL or local_reset_time = lopen_reset_time)) THEN
 
      deb('ckptNeeded - high_pdb_recid='||to_char(high_pdb_recid)||
               ', local.high_pdb_recid='||to_char(local.high_pdb_recid));
      IF (high_pdb_recid > local.high_pdb_recid) THEN
        ckp_type := RESYNC_FULL;
        IF local.high_pdb_recid <= 0 THEN
           setReason(RESYNC_REASON_NOACTION);
        ELSE
           setReason(RESYNC_REASON_PDB);
        END IF;
        GOTO ret;
      ELSIF (high_pdb_recid < local.high_pdb_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
 
      deb('ckptNeeded - high_ts_recid='||to_char(high_ts_recid)||
               ', local.high_ts_recid='||to_char(local.high_ts_recid));
 
      IF (high_ts_recid > local.high_ts_recid) THEN
        ckp_type := RESYNC_FULL;
        IF local.high_ts_recid = 0 THEN
           setReason(RESYNC_REASON_NOACTION);
        ELSE
           setReason(RESYNC_REASON_TS);
        END IF;
        GOTO ret;
      ELSIF (high_ts_recid < local.high_ts_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
 
      deb('ckptNeeded - high_df_recid='||to_char(high_df_recid)||
               ', local.high_df_recid='||to_char(local.high_df_recid));
      IF (high_df_recid > local.high_df_recid) THEN
        ckp_type := RESYNC_FULL;
        setReason(RESYNC_REASON_DF);
        GOTO ret;
      ELSIF (high_df_recid < local.high_df_recid) THEN
--
--
--
--
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
 
      deb('ckptNeeded - high_tf_recid='||to_char(high_tf_recid)||
               ', local.high_tf_recid='||to_char(local.high_tf_recid));
      IF (high_tf_recid > local.high_tf_recid) THEN
        ckp_type := RESYNC_FULL;
        setReason(RESYNC_REASON_TF);
        GOTO ret;
      ELSIF (high_tf_recid < local.high_tf_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
 
      deb('ckptNeeded - high_rt_recid='||to_char(high_rt_recid)||
               ', local.high_rt_recid='||to_char(local.high_rt_recid));
      IF (high_rt_recid > local.high_rt_recid) THEN
        ckp_type := RESYNC_FULL;
        setReason(RESYNC_REASON_THR);
        GOTO ret;
      ELSIF (high_rt_recid < local.high_rt_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
 
      deb('ckptNeeded - high_orl_recid='||to_char(high_orl_recid)||
               ', local.high_orl_recid='||to_char(local.high_orl_recid));
      IF (high_orl_recid > local.high_orl_recid) THEN
        ckp_type := RESYNC_FULL;
        setReason(RESYNC_REASON_ORL);
        GOTO ret;
      ELSIF (high_orl_recid < local.high_orl_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
--
--
--
       deb('ckptNeeded - high_conf_recid='||high_conf_recid||
                     ', local.high_conf_recid='||local.high_conf_recid);
       deb('       local.force_resync2cf='||local.force_resync2cf);
      IF (high_conf_recid != local.high_conf_recid OR
          local.force_resync2cf = 'YES')
      THEN
         ckp_type := RESYNC_FULL;
         setReason(RESYNC_REASON_CONF);
         GOTO ret;
      END IF;
 
--
--
--
 
      deb('ckptNeeded - high_cdf_recid='||to_char(high_cdf_recid)||
               ', local.high_cdf_recid='||to_char(local.high_cdf_recid));
      IF (high_cdf_recid > local.high_cdf_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_cdf_recid < local.high_cdf_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_al_recid='||to_char(high_al_recid)||
               ', local.high_al_recid='||to_char(local.high_al_recid));
      IF (high_al_recid > local.high_al_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_al_recid < local.high_al_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_bp_recid='||to_char(high_bp_recid)||
               ', local.high_bp_recid='||to_char(local.high_bp_recid));
      IF (high_bp_recid > local.high_bp_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_bp_recid < local.high_bp_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_bs_recid='||to_char(high_bs_recid)||
               ', local.high_bs_recid='||to_char(local.high_bs_recid));
      IF (high_bs_recid > local.high_bs_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_bs_recid < local.high_bs_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_do_recid='||to_char(high_do_recid)||
               ', local.high_do_recid='||to_char(local.high_do_recid));
      IF (high_do_recid > local.high_do_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_do_recid < local.high_do_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_offr_recid='||to_char(high_offr_recid)||
               ', local.high_offr_recid='||to_char(local.high_offr_recid));
      IF (high_offr_recid > local.high_offr_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_offr_recid < local.high_offr_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_pc_recid='||to_char(high_pc_recid)||
               ', local.high_pc_recid='||to_char(local.high_pc_recid));
      IF (high_pc_recid > local.high_pc_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_pc_recid < local.high_pc_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_ic_recid='||to_char(high_ic_recid)||
               ', local.high_ic_recid='||to_char(local.high_ic_recid));
      IF (high_ic_recid > local.high_ic_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_ic_recid < local.high_ic_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded: high_grsp_recid='||to_char(high_grsp_recid)||
                    ', local.high_grsp_recid='||to_char(local.high_grsp_recid));
      IF (high_grsp_recid > local.high_grsp_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_grsp_recid < local.high_grsp_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded: high_bcr_recid='||to_char(high_bcr_recid)||
                    ', local.high_bcr_recid='||to_char(local.high_bcr_recid));
      IF (high_bcr_recid > local.high_bcr_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_bcr_recid < local.high_bcr_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded: high_nrsp_recid='||to_char(high_nrsp_recid)||
                    ', local.high_nrsp_recid='||to_char(local.high_nrsp_recid));
      IF (high_nrsp_recid > local.high_nrsp_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_nrsp_recid < local.high_nrsp_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
      deb('ckptNeeded - high_pic_recid='||to_char(high_pic_recid)||
               ', local.high_pic_recid='||to_char(local.high_pic_recid));
      IF (high_pic_recid > local.high_pic_recid) THEN
        ckp_type := RESYNC_PARTIAL;
        GOTO ret;
      ELSIF (high_pic_recid < local.high_pic_recid) THEN
        raise_application_error(-20035, 'Invalid high recid');
      END IF;
    ELSE
--
      IF (cksum = last_resync_cksum AND kccdivts = date2stamp(cf_version)) THEN
         deb('ckptNeeded - resync checksum same as last checksum');
         ckp_type := RESYNC_NONE;
      ELSE
         ckp_type := RESYNC_PARTIAL;
         last_resync_cksum := cksum;
      END IF;
    END IF;
  ELSE
--
--
--
--
    IF (cf_type = CF_CURRENT) THEN
      deb('ckptNeeded - cf_type = CF_CURRENT');
      ckp_type := RESYNC_FULL;
      setReason(RESYNC_REASON_CF);
    ELSE
      deb('ckptNeeded - cf_type != CF_CURRENT');
      IF (cksum = last_resync_cksum AND kccdivts = date2stamp(cf_version)) THEN
         deb('ckptNeeded - resync checksum same as last checksum');
         ckp_type := RESYNC_NONE;
      ELSE
         ckp_type := RESYNC_PARTIAL;
         last_resync_cksum := cksum;
      END IF;
    END IF;
  END IF;
 
<<ret>>
--
--
--
--
--
--
  IF (ckp_type = RESYNC_PARTIAL AND
      cf_version = local.cf_create_time AND
      ckp_cf_seq = greatest(local.job_ckp_cf_seq, local.full_ckp_cf_seq)) THEN
     deb('ckptNeeded - cf_seq has not advanced - do not need a resync');
     deb('ckptNeeded - ' || ckp_cf_seq || ',' || local.job_ckp_cf_seq ||
                     ',' || local.full_ckp_cf_seq);
     ckp_type := RESYNC_NONE;
  END IF;
 
--
  IF (ckp_type = RESYNC_NONE) THEN
    deb('ckptNeeded - resync not needed, rollback, release locks');
    rollback;
  END IF;
 
  select decode(ckp_type, RESYNC_NONE, 'RESYNC_NONE',
                          RESYNC_PARTIAL, 'RESYNC_PARTIAL',
                          RESYNC_FULL, 'RESYNC_FULL',
                          'UNKNOWN') into ckp_desc from dual;
  deb('ckptNeeded - resync type is ' || ckp_desc);
  deb('ckptNeeded - returning ckp_type=' ||ckp_type);
  RETURN ckp_type;
 
--
EXCEPTION
  WHEN OTHERS THEN
    deb('ckptNeeded - error, rollback, release locks');
    rollback;
    RAISE;
END ckptNeeded;
 
PROCEDURE addTimeZone(
   db_unique_name IN VARCHAR2
  ,db_timezone    IN VARCHAR2
  ,tmz_src        IN VARCHAR2
  ,incarnations   IN VARCHAR2 default 'CURRENT'
)IS
prev_tmz        node.db_timezone%type;
prev_src        node.timezone_src%type;
l_db_key        NUMBER;
l_dbinc_key     NUMBER;
update_cur      NUMBER :=0;
comma_pos       NUMBER;
is_valid        NUMBER;
is_tmz_valid    VARCHAR2(7);
cur_dbinc       NUMBER;
tail_dbinc      VARCHAR2(256);
invalid_keys    VARCHAR2(256);
l_invalid_val   VARCHAR2(256);
l_err_no        CONSTANT NUMBER := -20244; -- modify in upate_db
--
tmz_err         EXCEPTION;
PRAGMA EXCEPTION_INIT(tmz_err, -20244);
BEGIN
--
--
--
  IF tmz_src = 'P' OR tmz_src = 'A'
  THEN
    BEGIN
      select tz_offset(db_timezone)
        into is_tmz_valid
      from dual;
   EXCEPTION
      when others THEN
        l_invalid_val := 'timezone - ' || db_timezone; 
        RAISE tmz_err;
   END;
  END IF;
 
--
  IF tmz_src = 'R'        -- db_key already available during resync
  THEN
    l_db_key := this_db_key;
    select db_timezone, timezone_src
      into prev_tmz, prev_src
      from node
    where node.db_unique_name = upper(addTimeZone.db_unique_name)
      AND db_key = l_db_key;
  ELSE                   -- need to fetch db_key
    select db_key, db_timezone, timezone_src
      into l_db_key, prev_tmz, prev_src
      from node
    where node.db_unique_name = upper(addTimeZone.db_unique_name);
  END IF;
 
--
  select dbinc_key 
    into l_dbinc_key
    from dbinc
   where db_key = l_db_key
     AND dbinc_status = 'CURRENT';
 
--
  IF incarnations != 'CURRENT'
  THEN
--
    is_valid := LENGTH(TRIM(TRANSLATE(incarnations, 
                                       ',0123456789', ' '))); 
    IF is_valid IS NOT NULL
    THEN
      l_invalid_val := 'incarnations - ' || incarnations;
      RAISE tmz_err;
    END IF;
 
    tail_dbinc := incarnations;
    IF LENGTH(TRIM(tail_dbinc)) > 0
    THEN
      LOOP
        comma_pos := instr(tail_dbinc, ',');
        IF comma_pos = 0
        THEN
          cur_dbinc := TO_NUMBER(TRIM(tail_dbinc));
          tail_dbinc := NULL;
        ELSE
          cur_dbinc := TO_NUMBER(TRIM(substr(tail_dbinc,1, comma_pos-1)));
          tail_dbinc := substr(tail_dbinc, comma_pos+1);
        END IF;
 
--
        IF l_dbinc_key = cur_dbinc
        THEN
          update_cur := 1;
        END IF;
 
        IF cur_dbinc IS NOT NULL
        THEN
          select count(*)
            into is_valid
            from dbinc
           where db_key = l_db_key
             AND dbinc_key = cur_dbinc;
 
          IF is_valid = 0
          THEN
            IF invalid_keys is NULL
            THEN
              invalid_keys := cur_dbinc;
            ELSE
              invalid_keys := invalid_keys || ',' || cur_dbinc;
            END IF;
          END IF;
        END IF;
        
        EXIT WHEN tail_dbinc IS NULL;
      END LOOP;
    END IF;
  END IF;
 
--
  IF invalid_keys IS NOT NULL
  THEN
      l_invalid_val := 'invalid keys in incarnations - '
                                          || invalid_keys;
      RAISE tmz_err;
  END IF;
 
--
--
  IF (prev_src IS NULL AND (update_cur = 1 OR 
                              incarnations = 'CURRENT')) OR
     (                         --no need to re-update
      NOT(prev_src = tmz_src AND prev_tmz = db_timezone)  AND 
      (
       (tmz_src = 'P') OR      -- update if coming from parameter file
--
       (tmz_src = 'A' AND prev_src IN ('A', 'S', 'R') AND (update_cur=1
                                         OR incarnations = 'CURRENT')) OR
--
--
--
       (tmz_src = 'S' AND prev_src IN ('S', 'P', 'R')) OR
--
--
       (tmz_src = 'R' AND prev_src = 'R')
       )
      )
  THEN
    UPDATE node
       SET node.db_timezone = addTimeZone.db_timezone,
           timezone_src = tmz_src
     WHERE node.db_unique_name
              = upper(addTimeZone.db_unique_name)
      AND db_key = l_db_key;      -- could be from resync
 
    UPDATE dbinc
       SET dbinc_timezone = db_timezone
     WHERE db_key = l_db_key
      AND dbinc_status = 'CURRENT';
 
--
    IF tmz_src = 'A'
    THEN
      update_cur := 2;
    END IF;
  END IF;
 
--
  IF incarnations != 'CURRENT'
  THEN
--
    IF update_cur = 1 AND      -- did not allow
--
       NOT(prev_src = tmz_src AND prev_tmz = db_timezone)
    THEN
      l_invalid_val := 'Current incarnation cannot be updated';
      RAISE tmz_err;
    ELSE
      tail_dbinc := incarnations;
      IF NVL(LENGTH(TRIM(tail_dbinc)),0) > 0
      THEN
        LOOP
          comma_pos := NVL(instr(tail_dbinc, ','),0);
          IF comma_pos = 0
          THEN
            cur_dbinc := TO_NUMBER(TRIM(tail_dbinc));
            tail_dbinc := NULL;
          ELSE
            cur_dbinc := TO_NUMBER(TRIM(substr(tail_dbinc,1, comma_pos-1)));
            tail_dbinc := substr(tail_dbinc, comma_pos+1);
          END IF; 
 
          IF cur_dbinc != l_dbinc_key   -- also checks cur_dbinc is null
          THEN
            UPDATE dbinc
               SET dbinc_timezone = db_timezone
             WHERE dbinc_key = cur_dbinc
               AND db_key = l_db_key;
          END IF;
        
          EXIT WHEN tail_dbinc IS NULL;
        END LOOP;
      END IF;
    END IF;
  END IF;
  commitChanges('addTimeZone');
EXCEPTION
  WHEN tmz_err THEN
       deb(l_invalid_val);
       raise_application_error(l_err_no, l_invalid_val);
  WHEN OTHERS THEN
       deb('Error in addTimeZone, release locks');
       ROLLBACK;
       RAISE;
END addTimeZone;
 
 
PROCEDURE beginCkpt(
  ckp_scn       IN NUMBER
 ,ckp_cf_seq    IN NUMBER
 ,cf_version    IN DATE
 ,ckp_time      IN DATE
 ,ckp_type      IN VARCHAR2
 ,ckp_db_status IN VARCHAR2
 ,high_df_recid IN NUMBER
 ,cf_type       IN VARCHAR2 DEFAULT 'CURRENT'   -- for compatibility reasons
) IS
 
local      ckp%rowtype;
node_count NUMBER;
db_role    node.database_role%type;
local_dbid NUMBER;
local_reset_watermarks boolean := TRUE;
BEGIN
  IF (this_ckp_key IS NOT NULL) THEN
    raise_application_error(-20030, 'Resync in progress');
  END IF;
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
  IF (this_site_key IS NULL) THEN
    raise_application_error(-20199, 'Site key is not set');
  END IF;
  IF (cf_type != 'CURRENT' AND ckp_type = 'FULL') THEN
    raise_application_error(-20072, 'full resync from noncurrent controlfile');
  END IF;
 
 
  clearResyncActions;
 
  deb('beginCkpt - ckp_type = '||ckp_type||', cf_type ='||cf_type ||
      ', ckp_scn =' || ckp_scn || ', site_key = ' || this_site_key);
 
--
  SELECT db_id INTO local_dbid FROM db
  WHERE db_key = this_db_key FOR UPDATE OF db.db_key;
 
  deb('beginCkpt - Obtained all locks for db '|| to_char(this_db_key));
 
--
--
--
--
--
--
--
--
  kccdivts := date2stamp(cf_version);           -- save in pkg global
 
--
  SELECT ckp_scn, cf_create_time,
         decode(beginCkpt.ckp_type, 'FULL', full_ckp_cf_seq,
                greatest(job_ckp_cf_seq, full_ckp_cf_seq)), dbinc_key
  INTO local.ckp_scn, local.cf_create_time, local.ckp_cf_seq, local.dbinc_key
  FROM node
  WHERE site_key = this_site_key;
 
--
  last_cf_version_time := local.cf_create_time;
 
--
  SELECT max(ckp_key)
    INTO local.ckp_key
    FROM ckp
    WHERE dbinc_key = this_dbinc_key;
 
  IF (local.ckp_key IS NULL) THEN
    deb('beginCkpt - first checkpoint for this incarnation '|| this_dbinc_key);
    local_reset_watermarks := TRUE;
  ELSIF (cf_type = 'CURRENT' OR
         (cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
--
--
--
--
--
--
    IF (ckp_type = 'FULL' AND this_dbinc_key = local.dbinc_key) THEN
      IF  (ckp_scn < local.ckp_scn) THEN
        deb('beginCkpt - cf scn='||ckp_scn||',catalog cf scn='||local.ckp_scn);
        raise_application_error(-20032, 'Invalid checkpoint SCN');
      ELSIF (ckp_scn = local.ckp_scn AND ckp_cf_seq < local.ckp_cf_seq) THEN
        deb('beginCkpt - cf seq='||ckp_cf_seq||',catalog cf seq='||
            local.ckp_cf_seq);
        raise_application_error(-20033, 'Invalid checkpoint cf seq#');
      ELSIF (ckp_scn = local.ckp_scn AND ckp_cf_seq = local.ckp_cf_seq) THEN
        raise_application_error(-20034, 'Resync not needed');
      END IF;
    END IF;
 
    deb('beginCkpt:last_cf_version' || local.cf_create_time);
    deb('beginCkpt:cf_version' || cf_version);
 
    IF (cf_version = local.cf_create_time) THEN
      deb('beginCkpt - Resync from same last control file');
--
--
--
--
--
      IF (ckp_cf_seq < local.ckp_cf_seq AND
          this_dbinc_key = local.dbinc_key) THEN
        deb('beginCkpt - cf seq='||ckp_cf_seq||',catalog cf seq='||
            local.ckp_cf_seq);
        raise_application_error(-20033, 'Invalid checkpoint cf seq#');
      ELSIF (ckp_cf_seq = local.ckp_cf_seq AND
             this_dbinc_key = local.dbinc_key) THEN
        raise_application_error(-20034, 'Resync not needed');
      END IF;
 
      local_reset_watermarks := FALSE;
    ELSE
      deb('beginCkpt - Resync from different control file');
      local_reset_watermarks := TRUE;
    END IF;
  ELSE
--
--
    IF (ckp_db_status = 'BACKUP') THEN
       deb('beginCkpt - Resync from control file copy');
       local_reset_watermarks := TRUE;
    ELSE
--
--
       IF (kccdivts = sessionWaterMarks.last_kccdivts) THEN
          deb('beginCkpt - Resync from same backup control file');
          local_reset_watermarks := FALSE;
       ELSE
          deb('beginCkpt - Resync from different backup control file');
          local_reset_watermarks := TRUE;
       END IF;
    END IF;
  END IF;
 
  IF (local_reset_watermarks) THEN
     deb('beginCkpt - init session watermarks');
     sessionWaterMarks := init_sessionWaterMarks;
     sessionWaterMarks.last_kccdivts := kccdivts;
  END IF;
 
--
--
  IF (cf_type = 'CURRENT' OR cf_type = 'CREATED' OR 
      (cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
    IF NOT local_reset_watermarks THEN
       deb('beginCkpt - update ckp_scn and use existing water marks');
--
       UPDATE node SET
         ckp_scn =
           greatest(ckp_scn,
             decode(beginCkpt.ckp_type, 'FULL', beginCkpt.ckp_scn, 0)),
         full_ckp_cf_seq =
           greatest(full_ckp_cf_seq,
             decode(beginCkpt.ckp_type, 'FULL', beginCkpt.ckp_cf_seq, 0)),
         job_ckp_cf_seq =
           greatest(job_ckp_cf_seq,
             decode(beginCkpt.ckp_type, 'PARTIAL', beginCkpt.ckp_cf_seq, 0)),
         bcr_in_use = nvl2(high_bcr_recid, 'YES', 'NO')
       WHERE site_key = this_site_key;
    ELSE
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
       deb('beginCkpt - update ckp_scn and reset water marks, this_site_key '||
           this_site_key);
       UPDATE node SET
         cf_create_time = beginCkpt.cf_version,
         dbinc_key = this_dbinc_key,
         ckp_scn =
           decode(beginCkpt.ckp_type, 'FULL', beginCkpt.ckp_scn, 0),
         full_ckp_cf_seq =
           decode(beginCkpt.ckp_type, 'FULL', beginCkpt.ckp_cf_seq, 0),
         job_ckp_cf_seq =
           decode(beginCkpt.ckp_type, 'PARTIAL', beginCkpt.ckp_cf_seq, 0),
         high_ic_recid = 0,  
         high_ts_recid = NULL,
         high_df_recid = NULL,
         high_rt_recid = NULL,
         high_orl_recid = NULL,
         high_offr_recid = 0,
         high_rlh_recid = 0,
         high_al_recid = 0,
         high_bs_recid = 0,
         high_bp_recid = 0,
         high_bdf_recid = 0,
         high_cdf_recid = 0,
         high_brl_recid = 0,
         high_bcb_recid = 0,
         high_ccb_recid = 0,
         high_do_recid = 0,
         high_pc_recid = 0,
         high_bsf_recid = 0,
         high_rsr_recid = 0,
         high_tf_recid = 0,
         high_grsp_recid = 0,
         high_nrsp_recid = 0,
         high_bcr_recid = 0,
         bcr_in_use = nvl2(high_bcr_recid, 'YES', 'NO'),
         high_pdb_recid = NULL,
         high_pic_recid = 0
       WHERE site_key = this_site_key;
    END IF;
  ELSE
--
     UPDATE node SET
       cf_create_time = beginCkpt.cf_version,
       dbinc_key = this_dbinc_key,
       ckp_scn =
         decode(beginCkpt.ckp_type, 'FULL', beginCkpt.ckp_scn, 0),
       full_ckp_cf_seq =
         decode(beginCkpt.ckp_type, 'FULL', beginCkpt.ckp_cf_seq, 0),
       job_ckp_cf_seq =
         decode(beginCkpt.ckp_type, 'PARTIAL', beginCkpt.ckp_cf_seq, 0),
       bcr_in_use = nvl2(high_bcr_recid, 'YES', 'NO')
     WHERE site_key = this_site_key;
  END IF;
 
  SELECT max(ckp_scn) INTO last_full_ckp_scn
    FROM ckp 
   WHERE ckp_type = 'FULL'
     AND dbinc_key = this_dbinc_key;
  deb('beginCkpt - last_full_ckp_scn ' || last_full_ckp_scn);
 
--
  BEGIN
    INSERT INTO ckp
       (ckp_key, ckp_scn, ckp_cf_seq, cf_create_time, ckp_time,
        dbinc_key, ckp_type, ckp_db_status, resync_time, site_key)
    VALUES
       (rman_seq.nextval, ckp_scn, ckp_cf_seq, cf_version, ckp_time,
        this_dbinc_key, beginCkpt.ckp_type, ckp_db_status, sysdate,
        this_site_key)
    RETURNING ckp_key INTO this_ckp_key;
  EXCEPTION
    WHEN dup_val_on_index THEN
      IF (cf_type = 'CURRENT' OR 
          (cf_type = 'STANDBY' AND this_db_unique_name IS NOT NULL)) THEN
        RAISE;
      ELSE
--
--
--
--
--
--
        raise_application_error(-20034, 'Resync not needed');
      END IF;
  END;
 
  SELECT count(*) INTO  node_count
  FROM   node
  WHERE  node.db_key = this_db_key AND
         node.db_unique_name = this_db_unique_name;
 
  IF (node_count = 0 AND this_db_unique_name IS NOT NULL) THEN
     IF substr(this_db_unique_name,1,1) = '$' THEN
        db_role := 'RA';
     ELSE
        IF (cf_type = 'STANDBY') THEN
           db_role := 'STANDBY';
        ELSE
           db_role := 'PRIMARY';
        END IF;
     END IF;
     deb('beginCkpt - adding node row with force_resync2cf=NO' ||
         ', db_unique_name = ' || this_db_unique_name ||
         ', db_role = ' || db_role);
     INSERT INTO node(db_unique_name,      db_key,      high_conf_recid,
                      force_resync2cf,     database_role, site_key)
               VALUES(this_db_unique_name, this_db_key, 0,
                      'NO',                db_role,       rman_seq.nextval);
  END IF;
  this_ckp_scn := ckp_scn;
  this_ckp_time := ckp_time;
  this_cf_type := cf_type;
  this_cf_version := cf_version;
  this_ckp_type := ckp_type;
  deb('beginCkpt:this_cf_type' || this_cf_type);
  deb('beginCkpt:this_cf_version' || this_cf_version);
  deb('beginCkpt: clearing watermark indexes');
 
--
--
  select rman_seq.currval into this_bp_key_poll from dual;
  getPolled_AL := TRUE;
  IF feedback_al%ISOPEN THEN CLOSE feedback_al; END IF;
  IF feedback_bp%ISOPEN THEN CLOSE feedback_bp; END IF;
  
  IF this_is_ors THEN
     this_enable_populate_rsr := getValueFromConfig('_enable_populate_rsr_key');
  END IF;
--
EXCEPTION
  WHEN OTHERS THEN
    deb('beginCkpt - error, rollback, release locks');
    rollback;
    RAISE;
 
END beginCkpt;
 
 
PROCEDURE clearCursorVariables IS
 
BEGIN
  pdbRec.guid := NULL;
  picRec.guid := NULL;
  tsRec.ts# := NULL;
  dfRec.file# := NULL;
  tfRec.file# := NULL;
  rtRec.thread# := NULL;
  orlRec.fname := NULL;
  grspRec.rspname := NULL;
 
END clearCursorVariables;
 
 
PROCEDURE endCkpt IS
 
BEGIN
 
  deb('endCkpt:Updating watermarks for backup related columns');
  deb('endCkpt:this_curr_bp_recid=' || this_curr_bp_recid);
  deb('endCkpt:this_high_bp_recid=' || this_high_bp_recid);
  IF this_curr_bp_recid <> this_high_bp_recid THEN
     UPDATE watermarks SET high_bp_recid  = this_high_bp_recid
     WHERE db_key = this_db_key;
  END IF;
--
  IF this_ckp_type = 'FULL' THEN
     deb('endCkpt:this_cf_version updated=' || this_cf_version);
     UPDATE watermarks SET cf_version_stamp = this_cf_version
     WHERE db_key = this_db_key;
  END IF;
 
--
--
  this_enable_populate_rsr := NULL;
  this_upstream_site_key := NULL;
--
  IF this_lock_ors_inspect THEN
    this_ckp_key := NULL;                 -- and update state variable
    this_ckp_scn := NULL;
    this_ckp_time := NULL;
    this_cf_type := NULL;
    this_cf_version := NULL;
    last_cf_version_time := NULL;
    this_ckp_type := NULL;
    commitChanges('endCkpt-1');
  ELSE
    checkResync;
 
    IF (tsRec.ts# IS NOT NULL) THEN
      raise_application_error(-20041, 'Tablespace resync not completed');
    END IF;
    IF (dfRec.file# IS NOT NULL) THEN
      raise_application_error(-20051, 'Datafile resync not completed');
    END IF;
 
    this_ckp_key := NULL;                 -- and update state variable
    this_ckp_scn := NULL;
    this_ckp_time := NULL;
    this_cf_type := NULL;
    this_cf_version := NULL;
    last_cf_version_time := NULL;
    this_ckp_type := NULL;
 
--
    clearCursorVariables;   
 
--
--
--
    IF this_clr_ba_newinc_err AND this_is_ors THEN
       deb('endckpt:fix_error for db_key = '||this_db_key);
--
--
--
       EXECUTE IMMEDIATE
        'BEGIN dbms_rai_fix_error(
          error_num => -64735);
         END;' ;
       this_clr_ba_newinc_err := FALSE;
    END IF;
    commitChanges('endCkpt-2');
 
    prev_sessionWaterMarks := sessionWaterMarks;
 
    /* Do not run cleanupTempResource when connected to a virtual catalog,
     * because we do not allow merging catalogs to be done by a virtual
     * catalog user. The cleanuopTempResource executes DDLs that will
     * automatically commit and release locks */
    if user = g_catowner then
      cleanupTempResource;
    end if;
  END IF;
 
END endCkpt;
 
 
PROCEDURE cancelCkpt IS
 
BEGIN
  deb('cancelCkpt - rollback, release locks');
  rollback;
 
--
--
  this_enable_populate_rsr := NULL;
  this_upstream_site_key := NULL;
--
--
  IF this_lock_ors_inspect THEN
     return;
  END IF;
 
  sessionWaterMarks := prev_sessionWaterMarks;
 
  IF (this_ckp_key IS NOT NULL) THEN
--
    this_ckp_key := NULL;
    this_ckp_scn := NULL;
    this_ckp_time := NULL;
  END IF;
  IF pdbQ%ISOPEN     THEN CLOSE pdbQ;   END IF;
  IF picQ%ISOPEN     THEN CLOSE picQ;   END IF;
  IF tsQ%ISOPEN      THEN CLOSE tsQ;    END IF;
  IF dfQ%ISOPEN      THEN CLOSE dfQ;    END IF;
  IF tfQ%ISOPEN      THEN CLOSE tfQ;    END IF;
  IF rtQ%ISOPEN      THEN CLOSE rtQ;    END IF;
  IF orlQ%ISOPEN     THEN CLOSE orlQ;   END IF;
  IF grspQ%ISOPEN    THEN CLOSE grspQ;  END IF;
  IF bpq%ISOPEN      THEN CLOSE bpq;    END IF;
  IF feedback_al%ISOPEN THEN CLOSE feedback_al; END IF;
  IF feedback_bp%ISOPEN THEN CLOSE feedback_bp; END IF;
  
--
  clearCursorVariables;   
 
--
  last_resync_cksum := NULL;
END cancelCkpt;
 
--
--
FUNCTION lastFullCkpt RETURN NUMBER IS
BEGIN
   RETURN last_full_ckp_scn;
END lastFullCkpt;
 
/* Feedback about files polled by OAM */
FUNCTION getPolledAl (rec_type OUT NUMBER,
                      recid    OUT NUMBER,
                      stamp    OUT NUMBER,
                      fname    OUT VARCHAR2) RETURN BOOLEAN IS
   al_rec       feedback_al%ROWTYPE;
BEGIN
 
   IF NOT feedback_al%ISOPEN THEN 
      deb('getPolledAl - open feedback_al, bp_key_poll=' || curr_bp_key_poll);
      OPEN feedback_al(curr_bp_key_poll);
   END IF;
 
   FETCH feedback_al INTO al_rec;
 
   IF feedback_al%NOTFOUND THEN
      CLOSE feedback_al;
      deb('getPolledAl - closing feedback_al');
      RETURN FALSE;
   ELSE
      deb('getPolledAl - recid='||al_rec.recid||',stamp='||al_rec.stamp);
      deb('getPolledAl - fname='||al_rec.fname);
      rec_type := RTYP_ARCHIVED_LOG;
      recid    := al_rec.recid;
      stamp    := al_rec.stamp;
      fname    := al_rec.fname;
      RETURN TRUE;
   END IF;
 
END getPolledAl;
 
FUNCTION haveProcessedBS (disk_bs_key IN NUMBER) RETURN BOOLEAN IS
   to_process NUMBER;
BEGIN
   deb('haveProcessedBS - disk_bs_key='||disk_bs_key);
 
--
--
   to_process := 0;
   FOR cur_rec IN
      (SELECT file#, create_scn, ckp_scn, dbinc_key 
          FROM bdf where bs_key=disk_bs_key
       MINUS 
       SELECT a.file#, a.create_scn, a.ckp_scn, a.dbinc_key
          FROM bdf a, 
              (SELECT bs_key, file#, create_scn, ckp_scn, dbinc_key
                  FROM bdf 
                  WHERE bs_key=disk_bs_key) b
          WHERE b.file#=a.file# 
            AND b.create_scn=a.create_scn 
            AND b.ckp_scn=a.ckp_scn 
            AND b.dbinc_key=a.dbinc_key 
            AND b.bs_key <> a.bs_key
            AND a.bs_key IN
                (SELECT bs_key FROM bp 
                    WHERE handle like 'VB$%' 
                      AND db_key = this_db_key 
                      AND bp_key > curr_bp_key_poll))
   LOOP
      to_process := to_process + 1;
      deb('haveProcessedBS - not processed file#='||cur_rec.file#||
          ',create_scn='||cur_rec.create_scn||
          ',ckp_scn='||cur_rec.ckp_scn||
          ',dbinc_key='||cur_rec.dbinc_key);
   END LOOP;
   IF to_process = 0 THEN
      deb('haveProcessedBS - Disk backupset processed by OAM');
      return TRUE;
   END IF;
 
   return FALSE;
END haveProcessedBS;
 
FUNCTION getPolledBP (rec_type OUT NUMBER,
                      recid    OUT NUMBER,
                      stamp    OUT NUMBER,
                      fname    OUT VARCHAR2) RETURN BOOLEAN IS
   to_process   NUMBER;
   bp_rec       feedback_bp%ROWTYPE;
 
BEGIN
 
   IF NOT feedback_bp%ISOPEN THEN 
      deb('getPolledBP - open feedback_bp, bp_key_poll=' || curr_bp_key_poll);
      OPEN feedback_bp(curr_bp_key_poll);
   END IF;
 
   IF disk_bp_rec.bs_key is NOT NULL THEN
      deb('getPolledBP - have cached disk piece = '||disk_bp_rec.handle);
      IF haveProcessedBS(disk_bp_rec.bs_key) THEN
         rec_type := RTYP_BACKUP_PIECE;
         recid    := disk_bp_rec.recid;
         stamp    := disk_bp_rec.stamp;
         fname    := disk_bp_rec.handle;
         disk_bp_rec := null;
         deb('getPolledBP - Purgable');
         RETURN TRUE;
      ELSE
         disk_bp_rec := null;
         deb('getPolledBP - Not purgable');
      END IF;
   END IF;
 
<<get_next_rec>>
 
   FETCH feedback_bp INTO bp_rec;
 
   IF feedback_bp%NOTFOUND THEN
      CLOSE feedback_bp;
      deb('getPolledBP - closing feedback_bp');
      RETURN FALSE;
   ELSE
 
      deb('getPolledBP - bs_key='||bp_rec.bs_key||',piece#='||bp_rec.piece#||
       ',device_type='||bp_rec.device_type||',ba_access='||bp_rec.ba_access);
      deb('getPolledBP - recid='||bp_rec.recid||',stamp='||bp_rec.stamp||
         ',site_key='||bp_rec.site_key);
      deb('getPolledBP - handle='||bp_rec.handle);
 
      IF disk_bp_rec.bs_key is NULL AND
         bp_rec.device_type = 'DISK' THEN
         disk_bp_rec := bp_rec;
         deb('getPolledBP - fetch next; Chk disk piece=' ||disk_bp_rec.handle);
         goto get_next_rec;
      END IF;
 
      IF disk_bp_rec.bs_key is NULL THEN
         deb('getPolledBP - fetch next; No disk piece to check');
         goto get_next_rec;
      END IF;
 
      IF bp_rec.device_type = 'SBT_TAPE' AND
         bp_rec.bs_key = disk_bp_rec.bs_key AND
         bp_rec.piece# = disk_bp_rec.piece#  THEN
 
--
--
         IF bp_rec.ba_access = 'L' OR bp_rec.ba_access = 'T' THEN
            rec_type := RTYP_BACKUP_PIECE;
            recid    := disk_bp_rec.recid;
            stamp    := disk_bp_rec.stamp;
            fname    := disk_bp_rec.handle;
            disk_bp_rec := null;
            deb('getPolledBP - Disk backuppiece copied by OAM');
            RETURN TRUE;
--
--
--
         ELSIF bp_rec.ba_access = 'D' AND
               haveProcessedBS(disk_bp_rec.bs_key) THEN
            rec_type := RTYP_BACKUP_PIECE;
            recid    := disk_bp_rec.recid;
            stamp    := disk_bp_rec.stamp;
            fname    := disk_bp_rec.handle;
            disk_bp_rec := null;
            deb('getPolledBP - Polled Disk backuppiece processed');
            RETURN TRUE;
         END IF;
      ELSE
--
--
         IF haveProcessedBS(disk_bp_rec.bs_key) THEN
            rec_type := RTYP_BACKUP_PIECE;
            recid    := disk_bp_rec.recid;
            stamp    := disk_bp_rec.stamp;
            fname    := disk_bp_rec.handle;
            IF bp_rec.device_type = 'DISK' THEN
               disk_bp_rec := bp_rec;    -- save for next call, to avoid fetch
            ELSE
               disk_bp_rec := null;
            END IF;
            return TRUE;
         ELSE
            deb('getPolledBP - Disk backuppiece not processed - check next');
            IF bp_rec.device_type = 'DISK' THEN
               disk_bp_rec := bp_rec;    -- save for next call, to avoid fetch
            ELSE
               disk_bp_rec := null;
            END IF;
         END IF;
      END IF;
      deb('getPolledBP - fetch next');
      goto get_next_rec;
   END IF;
 
 
END getPolledBP;
 
FUNCTION getPolledRec(rec_type OUT NUMBER,
                      recid    OUT NUMBER,
                      stamp    OUT NUMBER,
                      fname    OUT VARCHAR2) RETURN BOOLEAN IS
BEGIN
 
--
--
   IF NOT (this_is_ors AND
           (this_cf_type = 'CURRENT' OR
            (this_cf_type = 'STANDBY' and this_db_unique_name is not null)))
                                                                          THEN
      RETURN FALSE;
   END IF;
 
   SELECT bp_key_poll into curr_bp_key_poll 
      FROM node
      WHERE site_key = this_site_key;
 
   IF getPolled_AL THEN
      IF (getPolledAL(rec_type, recid, stamp, fname)) THEN
         RETURN TRUE;
      ELSE
         getPolled_BP := TRUE;
         getPolled_AL := FALSE;
      END IF;
   END IF;
 
   IF getPolled_BP THEN
      IF (getPolledBP(rec_type, recid, stamp, fname)) THEN
         RETURN TRUE;
      END IF;
   END IF;
 
--
--
   deb('getPolledRec - Polling bp_key='||this_bp_key_poll);
   getPolled_BP := FALSE;
   getPolled_AL := FALSE;
 
   RETURN FALSE;
 
END getPolledRec;
 
/*---------------------*
 * Pluggable DB Resync *
 *---------------------*/
PROCEDURE fetchPdb IS                   -- this is private to the pkg body
 
BEGIN
  FETCH pdbQ INTO pdbRec;               -- get next row
  IF pdbQ%NOTFOUND THEN
    pdbRec.guid := NULL;                -- indicate end of fetch
    CLOSE pdbQ;
  ELSE
    deb('fetchPdb - '||pdbRec.name||' ('||to_char(pdbRec.con_id)||') '||
        to_char(pdbRec.guid));
  END IF;
END fetchPdb;
 
PROCEDURE addPdb(
   name       IN VARCHAR2
  ,con_id     IN NUMBER
  ,db_id      IN NUMBER
  ,create_scn IN NUMBER
  ,guid       IN RAW
  ,nobackup   IN VARCHAR2) IS
  local pdb%rowtype;
BEGIN
  deb('addPdb - db_id '|| to_char(db_id));
 
  INSERT INTO pdb
    (pdb_key, db_key, name, con_id, db_id, create_scn, guid, nobackup)
  VALUES
    (rman_seq.nextval, this_db_key, name, con_id, db_id, create_scn, guid,
     nobackup);
END addPdb;
 
PROCEDURE dropPdb(                       -- private to package body
  pdb_key    IN NUMBER
 ,drop_scn   IN NUMBER
 ,drop_time  IN DATE
) IS
 
BEGIN
  deb('dropPdb - pdb_key ' || to_char(pdb_key));
 
  UPDATE pdb_dbinc SET
         drop_scn  = dropPdb.drop_scn,
         drop_time = dropPdb.drop_time
   WHERE pdb_dbinc.dbinc_key = this_dbinc_key
     AND pdb_dbinc.pdb_key   = dropPdb.pdb_key
     AND pdb_dbinc.drop_scn IS NULL;
END dropPdb;
 
FUNCTION beginPluggableDBResync(
  high_pdb_recid IN NUMBER)
RETURN BOOLEAN IS
 
BEGIN
 
  checkResync;
 
--
--
--
--
--
  
  SELECT high_pdb_recid INTO last_pdb_recid
  FROM node  
  WHERE site_key = this_site_key;
 
  IF (high_pdb_recid = last_pdb_recid) THEN
    deb('beginPluggableDBResync - Resync of PDBs not needed');
    RETURN FALSE;
  ELSIF (high_pdb_recid > last_pdb_recid OR last_pdb_recid IS NULL) THEN
    deb('beginPluggableDBResync - Catalog pdb_recid: '|| last_pdb_recid);
    deb('beginPluggableDBResync - High pdb_recid: '|| high_pdb_recid);
    last_pdb_recid := high_pdb_recid;
  
    OPEN pdbQ;                          -- just open that cursor please
    fetchPdb;                           -- do priming read
    last_guid := NULL;                  -- initialize for ordering assert
 
    if resync_reason = RESYNC_REASON_PDB then
      fullResyncAction.active  := TRUE;
      fullResyncAction.valid   := TRUE;
      fullResyncAction.objtype := RESYNC_OBJECT_PDB;
    else
      fullResyncAction.active  := FALSE;
    end if;
 
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
  
END beginPluggableDBResync;
 
PROCEDURE checkPluggableDB(
  name       IN VARCHAR2
 ,con_id     IN NUMBER
 ,db_id      IN NUMBER
 ,create_scn IN NUMBER
 ,guid       IN RAW
 ,nobackup   IN VARCHAR2 DEFAULT 'N'
) IS
   dbinc_key   number;
BEGIN
  IF (last_guid >= guid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  last_guid := guid;
 
  WHILE (guid > pdbRec.guid) LOOP
    dropPdb(pdbRec.pdb_key, this_ckp_scn, this_ckp_time);
    incResyncActions(RESYNC_ACTION_DROP, pdbRec.con_id, pdbRec.name);
    fetchPdb;
  END LOOP;
 
  IF (guid < pdbRec.guid OR pdbRec.guid IS NULL) THEN
    IF (con_id IN (1, 0)) THEN
       IF (guid != ZERO_GUID) THEN
          UPDATE pdb SET guid = checkPluggableDb.guid
           WHERE pdb.db_key = this_db_key
             AND pdb.con_id = checkPluggableDb.con_id;
       END IF;
    ELSE
--
       addPdb(name, con_id, db_id, create_scn, guid, nobackup);
       incResyncActions(RESYNC_ACTION_ADD, con_id, name);
    END IF;
  ELSE -- (guid = pdbRec.guid)
--
    IF (pdbRec.con_id != con_id OR pdbRec.create_scn != create_scn) THEN
       deb('checkPluggableDb - drop and add new pdb');
       dropPdb(pdbRec.pdb_key, this_ckp_scn, this_ckp_time);
       UPDATE pdb
          SET name       = checkPluggableDB.name,
              con_id     = checkPluggableDB.con_id,
              create_scn = checkPluggableDB.create_scn,
              db_id      = checkPluggableDB.db_id,
              nobackup   = checkPluggableDB.nobackup
        WHERE pdb.pdb_key = pdbRec.pdb_key;
       incResyncActions(RESYNC_ACTION_RENAME, con_id, name);
    ELSIF (pdbRec.name != name OR pdbRec.nobackup != nobackup) THEN
       deb('checkPluggableDb - update name/nobackup');
       UPDATE pdb
          SET name     = checkPluggableDB.name,
              nobackup = checkPluggableDB.nobackup
        WHERE pdb.pdb_key = pdbRec.pdb_key;
       incResyncActions(RESYNC_ACTION_RENAME, con_id, name);
    ELSE
       deb('checkPluggableDb - pdb already known');
    END IF;
    fetchPdb;
  END IF;
END checkPluggableDB;
 
PROCEDURE endPluggableDBResync IS
 
BEGIN
  checkResync;
  WHILE (pdbRec.guid IS NOT NULL) LOOP   -- while extra tablespaces in rcvcat
     dropPdb(pdbRec.pdb_key, this_ckp_scn, this_ckp_time);
     incResyncActions(RESYNC_ACTION_DROP, pdbRec.con_id, pdbRec.name);
     fetchPdb;
  END LOOP;
 
--
  pdbRec.guid := NULL;
 
--
  UPDATE node SET high_pdb_recid = nvl(last_pdb_recid, high_pdb_recid)
  WHERE site_key = this_site_key;
 
  last_pdb_recid := NULL;
END endPluggableDBResync;
 
/*-------------------*
 * Tablespace Resync *
 *-------------------*/
 
PROCEDURE fetchTs IS                    -- this is private to the pkg body
 
BEGIN
  FETCH tsQ INTO tsRec;                 -- get next row
  IF tsQ%NOTFOUND THEN
    tsRec.con_id       := MAXNUMVAL;          -- indicate end of fetch
    tsRec.ts#          := MAXNUMVAL;
    tsRec.pdb_drop_scn := NULL;
    CLOSE tsQ;
  ELSE
    deb('fetchTs - '||tsRec.ts_name||' ('||to_char(tsRec.ts#)||') '||
        to_char(tsRec.create_scn) || ';plugin_scn='||to_char(tsRec.plugin_scn));    
  END IF;
END fetchTs;
 
PROCEDURE addTs(
  ts_name                     IN VARCHAR2
 ,ts#                         IN NUMBER
 ,create_scn                  IN NUMBER
 ,create_time                 IN DATE
 ,rbs_count                   IN NUMBER
 ,included_in_database_backup IN VARCHAR2
 ,bigfile                     IN VARCHAR2
 ,temporary                   IN VARCHAR2
 ,encrypt_in_backup           IN VARCHAR2
 ,plugin_scn                  IN NUMBER
 ,pdbinc_key                  IN NUMBER
) IS
BEGIN
  deb('addTs - tablespace '||ts_name||' ('||to_char(ts#)||') '||
      to_char(create_scn) || ',plugin_scn=' || to_char(plugin_scn));
  INSERT INTO ts
    (dbinc_key, ts#, ts_name, create_scn,
     create_time, included_in_database_backup, bigfile, temporary, 
     encrypt_in_backup, plugin_scn, pdbinc_key)
  VALUES
    (this_dbinc_key, ts#, ts_name, create_scn, create_time,
     included_in_database_backup, bigfile, temporary, encrypt_in_backup,
     plugin_scn, pdbinc_key);
 
  INSERT INTO tsatt
    (dbinc_key, ts#, create_scn, start_ckp_key, rbs_count, plugin_scn,
     pdbinc_key)
  VALUES 
    (this_dbinc_key, ts#, create_scn, this_ckp_key, rbs_count, plugin_scn,
     pdbinc_key);
 
END addTs;
 
PROCEDURE dropTs(                       -- private to package body
  ts#        IN NUMBER
 ,create_scn IN NUMBER
 ,drop_scn   IN NUMBER
 ,drop_time  IN DATE
 ,plugin_scn IN NUMBER
 ,pdbinc_key IN NUMBER
) IS
 
BEGIN
  deb('dropTs - tablespace '||to_char(ts#)||' - '||to_char(create_scn) ||
      ',plugin_scn - ' || plugin_scn);
  UPDATE ts SET
    drop_scn     = dropTs.drop_scn,
    drop_time    = dropTs.drop_time
  WHERE ts.dbinc_key  = this_dbinc_key
  AND   ts.pdbinc_key = dropTs.pdbinc_key
  AND   ts.ts#        = dropTs.ts#
  AND   ts.create_scn = dropTs.create_scn
  AND   ts.plugin_scn = dropTs.plugin_scn;
  deb('dropTs - returning');
END dropTs;
 
PROCEDURE renameTs(
  ts_name    IN VARCHAR2
 ,dbinc_key  IN NUMBER
 ,ts#        IN NUMBER
 ,create_scn IN NUMBER
 ,plugin_scn IN NUMBER
 ,pdbinc_key IN NUMBER
) IS
 
BEGIN
  UPDATE ts SET
     ts.ts_name = renameTs.ts_name
  WHERE ts.dbinc_key  = renameTs.dbinc_key
  AND   ts.pdbinc_key = renameTs.pdbinc_key
  AND   ts.ts#        = renameTs.ts#
  AND   ts.create_scn = renameTs.create_scn
  AND   ts.plugin_scn = renameTs.plugin_scn;
END renameTs;
 
FUNCTION beginTableSpaceResync(
  high_ts_recid IN NUMBER,
  force         IN BOOLEAN DEFAULT FALSE)
RETURN BOOLEAN IS
 
BEGIN
 
  checkResync;
 
--
--
 
--
--
--
--
--
 
  SELECT high_ts_recid INTO last_ts_recid
  FROM node
  WHERE site_key = this_site_key;
 
 
  IF (high_ts_recid = last_ts_recid AND NOT force) THEN
    deb('beginTableSpaceResync - Resync of tablespaces not needed');
    RETURN FALSE;
  ELSIF (high_ts_recid > last_ts_recid OR last_ts_recid IS NULL OR
         high_ts_recid IS NULL OR force) THEN
 
    deb('beginTableSpaceResync - Catalog ts_recid: '||last_ts_recid);
    deb('beginTableSpaceResync - High ts_recid: '||high_ts_recid);
    last_ts_recid := high_ts_recid;
 
    OPEN tsQ;                           -- just open that cursor please
    fetchTs;                            -- do priming read
    last_con_id_ts# := -1;              -- initialize for ordering assert
    last_ts# := -1;                     -- initialize for ordering assert
 
    if resync_reason = RESYNC_REASON_TS then
      fullResyncAction.active  := TRUE;
      fullResyncAction.valid   := TRUE;
      fullResyncAction.objtype := RESYNC_OBJECT_TABLESPACE;
    else
      fullResyncAction.active  := FALSE;
    end if;
 
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
 
END beginTableSpaceResync;
 
 
PROCEDURE checkTableSpace(
  ts_name                     IN VARCHAR2
 ,ts#                         IN NUMBER
 ,create_scn                  IN NUMBER
 ,create_time                 IN DATE
 ,rbs_count                   IN NUMBER   DEFAULT NULL
 ,included_in_database_backup IN VARCHAR2 DEFAULT NULL
 ,bigfile                     IN VARCHAR2 DEFAULT NULL
 ,temporary                   IN VARCHAR2 DEFAULT NULL
 ,encrypt_in_backup           IN VARCHAR2 DEFAULT NULL
 ,plugin_scn                  IN NUMBER   DEFAULT 0
 ,con_id                      IN NUMBER   DEFAULT 0
 ,pdb_dict_check              IN BOOLEAN  DEFAULT FALSE
) IS
--
--
--
idb              varchar2(3) := nvl(included_in_database_backup, 'YES');
--
bf               varchar2(3) := nvl(bigfile, 'NO');   -- actual default value
tmp              varchar2(3) := nvl(temporary, 'NO'); -- actual default value
ts_changed       boolean     := FALSE;
local_pdbinc_key number;
local_pdb_key    number;
BEGIN
  IF (tsRec.ts# IS NULL) THEN   -- assert beginTableSpaceResync was called
    raise_application_error(-20040, 'Tablespace resync not started');
  END IF;
 
--
  IF (last_con_id_ts# > con_id) THEN
     raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (con_id > last_con_id_ts#) THEN
     last_ts# := -1;
  END IF;
 
  IF (last_ts# >= ts#) THEN 
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
--
--
--
--
  IF (temporary IS NOT NULL) THEN
     do_temp_ts_resync := TRUE;
  END IF;
 
--
  last_con_id_ts# := con_id;
  last_ts# := ts#;
 
--
--
--
  IF (create_scn > this_ckp_scn AND tmp = 'NO') THEN
    raise_application_error(-20042, 'Invalid tablespace create SCN');
  END IF;
 
--
--
--
--
--
--
--
  deb('checkTableSpace - ts#: ' || ts# || ' tsRec.ts#: ' || tsRec.ts# ||
      ' con_id ' || con_id || ' tsRec.con_id: ' || tsRec.con_id ||
      ' tsRec.pdb_drop_scn ' || tsRec.pdb_drop_scn);
  WHILE (con_id > tsRec.con_id OR
         (tsRec.con_id = con_id AND ts# > tsRec.ts#) OR
         tsRec.pdb_drop_scn IS NOT NULL) LOOP
    IF (tsRec.temporary = 'NO' OR -- is a permanent tablespace 
        do_temp_ts_resync) THEN   -- is a 10gR2 or later rman client
       deb('checkTableSpace - before calling dropTS');
       dropTs(tsRec.ts#, tsRec.create_scn, this_ckp_scn, this_ckp_time,
              tsRec.plugin_scn, tsRec.pdbinc_key);
       deb('checkTableSpace - before calling incResyncActions');
       begin
          incResyncActions(RESYNC_ACTION_DROP, tsRec.ts#, tsRec.ts_name);
       exception
          when others then
              deb('checkTableSpace - (DO_NOT_IGNORE)caught exception ' ||
                  substr(sqlerrm, 1, 132));
       end;
       deb('checkTableSpace - after calling incResyncActions');
    END IF;
    deb('checkTableSpace - before calling fetchTS');
    fetchTs;
    deb('checkTableSpace - after calling fetchTS');
  END LOOP;
 
  deb('checkTableSpace -out of loop, ts#: ' || ts# || ' tsRec.ts#: ' ||
       tsRec.ts# || ' con_id ' || con_id || ' tsRec.con_id: ' || tsRec.con_id);
 
  IF (con_id != tsRec.con_id OR ts# < tsRec.ts#) THEN
    IF (pdb_dict_check) THEN
        deb('checkTableSpace - skipping tablespace needs dictionary check');
    ELSE
--
       local_pdbinc_key := getPdbInc(greatest(create_scn, plugin_scn),
                                     con_id, local_pdb_key);
       addTs(ts_name, ts#, create_scn, create_time, rbs_count, idb, bf,
             tmp, encrypt_in_backup, plugin_scn, local_pdbinc_key);
    END IF;
    incResyncActions(RESYNC_ACTION_ADD, ts#, ts_name);
  ELSE -- (con_id = tsRec.con_id AND ts# = tsRec.ts#)
    IF (pdb_dict_check) THEN
       deb('checkTableSpace - skipping tablespace needs dictionary check');
       goto next_Ts;
    END IF;
 
    IF (create_scn = tsRec.create_scn) THEN
--
--
      IF (create_time <> tsRec.create_time) THEN
        raise_application_error(-20043, 'Invalid tablespace create time');
      END IF;
 
      IF (plugin_scn > 0) THEN
        IF (tsRec.plugin_scn < checkTableSpace.plugin_scn) THEN
          deb('checkTableSpace - plugin read only tbs dropped and replugged');
--
--
--
          dropTs(tsRec.ts#, tsRec.create_scn, this_ckp_scn, this_ckp_time,
                 tsRec.plugin_scn, tsRec.pdbinc_key);
          incResyncActions(RESYNC_ACTION_DROP, tsRec.ts#, tsRec.ts_name);
          addTs(ts_name, ts#, create_scn, create_time, rbs_count, idb, bf, tmp, 
                encrypt_in_backup, plugin_scn, tsRec.curr_pdbinc_key);
          incResyncActions(RESYNC_ACTION_ADD, ts#, ts_name);
          goto next_Ts;
        ELSIF (tsRec.plugin_scn > checkTableSpace.plugin_scn) THEN
--
          raise_application_error(-20055, 'Invalid tablespace plugin SCN');
        ELSE
--
--
          deb('checkTableSpace - known plugged in tablespace');
        END IF;
      END IF;
 
--
--
      IF (ts_name <> tsRec.ts_name) THEN
        renameTs(ts_name, this_dbinc_key, tsRec.ts#, tsRec.create_scn, 
                 tsRec.plugin_scn, tsRec.pdbinc_key);
        incResyncActions(RESYNC_ACTION_RENAME, tsRec.ts#, ts_name);
      END IF;
 
--
--
      IF (idb <> nvl(tsRec.included_in_database_backup,'XX')) THEN
        UPDATE ts SET ts.included_in_database_backup =
                                  checkTableSpace.included_in_database_backup
        WHERE  ts.dbinc_key  = this_dbinc_key
        AND    ts.ts#        = tsRec.ts#
        AND    ts.pdbinc_key = tsRec.pdbinc_key
        AND    ts.create_scn = tsRec.create_scn
        AND    ts.plugin_scn = tsRec.plugin_scn;
        ts_changed := TRUE;
      END IF;
 
--
--
      IF (tsRec.encrypt_in_backup is null and encrypt_in_backup is not null OR
          tsRec.encrypt_in_backup is not null and encrypt_in_backup is null OR
          tsRec.encrypt_in_backup <> encrypt_in_backup) THEN
        UPDATE ts SET ts.encrypt_in_backup = checkTableSpace.encrypt_in_backup
        WHERE  ts.dbinc_key  = this_dbinc_key
        AND    ts.ts#        = tsRec.ts#
        AND    ts.pdbinc_key = tsRec.pdbinc_key
        AND    ts.create_scn = tsRec.create_scn
        AND    ts.plugin_scn = tsRec.plugin_scn;
        ts_changed := TRUE;
      END IF;
 
--
--
      IF (rbs_count <> nvl(tsRec.rbs_count,-1)) THEN
        UPDATE tsatt SET end_ckp_key = this_ckp_key
        WHERE tsatt.dbinc_key = this_dbinc_key
        AND   tsatt.ts# = tsRec.ts#
        AND   tsatt.pdbinc_key = tsRec.pdbinc_key
        AND   tsatt.create_scn = tsRec.create_scn
        AND   tsatt.plugin_scn = tsRec.plugin_scn
        AND   tsatt.end_ckp_key IS NULL;
 
        INSERT INTO tsatt(dbinc_key, ts#, create_scn, start_ckp_key, rbs_count,
               plugin_scn, pdbinc_key)
        VALUES(this_dbinc_key, tsRec.ts#, tsRec.create_scn, this_ckp_key,
               rbs_count, tsRec.plugin_scn, tsRec.pdbinc_key);
        ts_changed := TRUE;
      END IF;
 
      if ts_changed then
        incResyncActions(RESYNC_ACTION_CHANGE, tsRec.ts#, tsRec.ts_name);
      end if;
    ELSIF (create_scn = 0 AND tmp = 'YES') THEN
--
--
--
       dropTs(tsRec.ts#, tsRec.create_scn, create_scn, create_time, 
              tsRec.plugin_scn, tsRec.pdbinc_key);
 
       DELETE FROM ts
        WHERE ts.dbinc_key = this_dbinc_key
          AND ts.ts# = checkTableSpace.ts#
          AND ts.pdbinc_key = tsRec.pdbinc_key
          AND ts.create_scn = 0
          AND ts.plugin_scn = 0;
 
       addTs(ts_name, ts#, create_scn, create_time, rbs_count, idb, bf, tmp,
             encrypt_in_backup, plugin_scn, tsRec.curr_pdbinc_key);
       incResyncActions(RESYNC_ACTION_CHANGE, tsRec.ts#, ts_name);
    ELSE
       IF (tmp = 'YES') THEN
--
--
          IF (tsRec.temporary = 'NO') THEN
             dropTs(tsRec.ts#, tsRec.create_scn, create_scn, create_time,
                    tsRec.plugin_scn, tsRec.pdbinc_key);
          END IF;
 
--
--
--
--
--
          DELETE FROM ts
            WHERE ts.dbinc_key = this_dbinc_key
              AND ts.ts# = checkTablespace.ts#
              AND ts.pdbinc_key = tsRec.pdbinc_key
              AND ts.temporary = 'YES';
          deb('Deleting tablespace entry for ts#=' || ts# ||
              ', ts_name=' || ts_name);
 
          addTs(ts_name, ts#, create_scn, create_time, rbs_count, idb,
                bf, tmp, encrypt_in_backup, plugin_scn, tsRec.curr_pdbinc_key);
          deb('Added tablespace entry for ts#=' || ts# || ', ts_name=' ||
              ts_name);
 
          incResyncActions(RESYNC_ACTION_RECREATE, ts#, ts_name);
       ELSE
          IF (create_scn > tsRec.create_scn) THEN
 
--
--
--
--
--
--
--
--
             dropTs(tsRec.ts#, tsRec.create_scn, create_scn, create_time,
                    tsRec.plugin_scn, tsRec.pdbinc_key);
 
             addTs(ts_name, ts#, create_scn, create_time, rbs_count, idb,
                   bf, tmp, encrypt_in_backup, plugin_scn,
                   tsRec.curr_pdbinc_key);
             incResyncActions(RESYNC_ACTION_RECREATE, tsRec.ts#, ts_name);
          ELSE -- (create_scn < tsRec.create_scn)
--
--
--
--
             raise_application_error(-20042,
                                     'Invalid tablespace creation change#');
          END IF;
       END IF;
    END IF;
 
<<next_Ts>>
    fetchTS;                            -- get next row from TS cursor
 
  END IF; -- (ts# < tsRec.ts)
 
END checkTableSpace;
 
 
PROCEDURE endTableSpaceResync IS
 
BEGIN
  checkResync;
 
  deb('endTableSpaceResync - tsRec.ts#: ' || tsRec.ts#);
 
--
--
--
  begin
     WHILE (tsRec.con_id < MAXNUMVAL) LOOP -- while extra tablespaces in rcvcat
       IF (tsRec.temporary = 'NO' OR -- is a permanent tablespace 
           do_temp_ts_resync) THEN   -- is a 10gR2 or later rman client
          deb('endTableSpaceResync - before calling dropTS');
          dropTs(tsRec.ts#, tsRec.create_scn, this_ckp_scn, this_ckp_time,
                 tsRec.plugin_scn, tsRec.pdbinc_key);
          deb('endTableSpaceResync - before calling incResyncActions');
          begin
             incResyncActions(RESYNC_ACTION_DROP, tsRec.ts#, tsRec.ts_name);
          exception
             when others then
                 deb('endTableSpaceResync (DO_NOT_IGNORE)-caught exception ' ||
                     substr(sqlerrm, 1, 132));
          end;
          deb('endTableSpaceResync - after calling incResyncActions');
       END IF;
       deb('endTableSpaceResync - before calling fetchTS');
       fetchTs;
       deb('endTableSpaceResync - after calling fetchTS');
     END LOOP;
  exception
     when others then
        deb('checkTableSpace(DO_NOT_IGNORE) - caugth exception ' ||
            substr(sqlerrm, 1, 132));
  end;
  deb('endTableSpaceResync -out of loop,  tsRec.ts#: ' || tsRec.ts#);
 
--
  tsRec.ts# := NULL;
 
--
  UPDATE node SET high_ts_recid = nvl(last_ts_recid, high_ts_recid)
  WHERE site_key = this_site_key;
 
--
  IF this_is_ors and this_ckp_type = 'FULL' THEN
     UPDATE watermarks SET high_ts_recid = nvl(last_ts_recid, high_ts_recid)
     WHERE db_key = this_db_key;
  END IF;
 
  last_ts_recid := NULL;
 
--
--
  IF (NOT do_temp_ts_resync) THEN
     UPDATE node SET high_tf_recid = 0
      WHERE site_key = this_site_key;
  END IF;
 
END endTableSpaceResync;
 
 
/*-----------------*
 * Datafile Resync *
 *-----------------*/
 
PROCEDURE fetchDF IS                    -- private to package body
BEGIN
  FETCH dfQ INTO dfRec;
  IF dfQ%NOTFOUND THEN
    dfRec.file# := MAXNUMVAL;           -- indicate end-of-fetch
    CLOSE dfQ;
  ELSE
    deb('fetchDF - file#' || dfRec.file#);
  END IF;
END fetchDF;
 
PROCEDURE addDF(file#               IN NUMBER,  -- private to package body
                fname               IN VARCHAR2,
                create_time         IN DATE,
                create_scn          IN NUMBER,
                blocks              IN NUMBER,
                block_size          IN NUMBER,
                ts#                 IN NUMBER,
                stop_scn            IN NUMBER,
                stop_time           IN DATE,
                read_only           IN NUMBER,
                rfile#              IN NUMBER,
                foreign_dbid        IN NUMBER,
                foreign_create_scn  IN NUMBER,
                foreign_create_time IN DATE,
                plugged_readonly    IN varchar2,
                plugin_scn          IN NUMBER,   
                plugin_reset_scn    IN NUMBER,   
                plugin_reset_time   IN DATE,
                create_thread       IN NUMBER,   
                create_size         IN NUMBER,
                pdbinc_key          IN NUMBER,
                pdb_key             IN NUMBER,
                pdb_closed          IN NUMBER,
                pdb_foreign_dbid    IN NUMBER) IS
 
 
ts_create_scn NUMBER;
ts_plugin_scn NUMBER;
ts_pdbinc_key NUMBER;
ts_name       ts.ts_name%type;
local_df_key  NUMBER;
child_rec     exception;
pragma        exception_init(child_rec, -2292);
BEGIN
  SELECT ts.create_scn, ts.plugin_scn, ts.ts_name, ts.pdbinc_key
  INTO ts_create_scn, ts_plugin_scn, ts_name, ts_pdbinc_key
  FROM ts, rci_pdbinc_this_dbinc pdbinc
  WHERE ts.dbinc_key = this_dbinc_key
  AND   ts.ts# = addDF.ts#
  AND   ts.pdbinc_key = pdbinc.pdbinc_key
  AND   pdbinc.pdb_key = addDF.pdb_key
  AND   pdbinc.dbinc_key = this_dbinc_key
  AND   ts.create_scn  < pdbinc.next_inc_scn
  AND   ts.drop_scn IS NULL;            -- in case ts numbers are reused
 
--
--
--
--
--
  BEGIN
    select distinct df_key into local_df_key from df, dbinc 
      where  file#        = addDF.file#
        and  create_scn   = addDF.create_scn
        and  plugin_scn   = addDF.plugin_scn
        and  foreign_dbid = addDF.foreign_dbid
        and  ts#          = addDF.ts#
        and  df.dbinc_key = dbinc.dbinc_key
        and  dbinc.db_key = this_db_key;
 
  EXCEPTION
    WHEN no_data_found THEN
      select rman_seq.nextval into local_df_key from dual;
  END;
 
--
--
--
--
  INSERT INTO df(dbinc_key, file#, create_scn, create_time,
                 ts#, ts_create_scn, ts_plugin_scn, block_size,
                 stop_scn, stop_time, read_only, rfile#, df_key, blocks,
                 foreign_dbid, foreign_create_scn, foreign_create_time,
                 plugged_readonly, plugin_scn, plugin_reset_scn,
                 plugin_reset_time, create_thread, create_size, pdbinc_key,
                 ts_pdbinc_key, pdb_closed, pdb_foreign_dbid)
  VALUES(this_dbinc_key, file#, create_scn, create_time, ts#, ts_create_scn,
         ts_plugin_scn, block_size, stop_scn, stop_time, read_only, rfile#, 
         local_df_key, nvl(blocks, 0), foreign_dbid, foreign_create_scn,
         foreign_create_time, plugged_readonly, plugin_scn, plugin_reset_scn,
         plugin_reset_time, create_thread, create_size, pdbinc_key,
         ts_pdbinc_key, pdb_closed, pdb_foreign_dbid);
 
--
--
--
  BEGIN
    INSERT INTO site_dfatt(df_key, fname, site_key)
    VALUES(local_df_key, fname, this_site_key);
  EXCEPTION
    WHEN dup_val_on_index THEN
--
      UPDATE site_dfatt SET
        fname = addDf.fname
      WHERE 
        site_key = this_site_key AND
        df_key   = local_df_key;      
  END;
END addDf;
 
PROCEDURE setDatafileSize(file#      IN number
                         ,create_scn IN number
                         ,blocks     IN number
                         ,plugin_scn IN number default 0) IS
BEGIN
  IF (this_dbinc_key is NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
  update df
  set df.blocks = setDatafileSize.blocks
     where dbinc_key = this_dbinc_key
     and   df.file# = setDatafileSize.file#
     and   df.create_scn = setDatafileSize.create_scn
     and   df.plugin_scn = setDatafileSize.plugin_scn;
  commitChanges('setDatafileSize');
END setDatafileSize;
 
PROCEDURE dropDf(                       -- private to package body
  file# IN NUMBER
 ,create_scn IN NUMBER
 ,plugin_scn IN NUMBER
 ,drop_scn IN NUMBER
 ,drop_time IN DATE
 ,pdbinc_key IN NUMBER
) IS
 
BEGIN
--
--
--
--
--
--
--
--
 
  UPDATE df SET
    drop_scn     = dropDf.drop_scn,
    drop_time    = dropDf.drop_time
  WHERE df.dbinc_key  = this_dbinc_key
  AND   df.file#      = dropDf.file#
  AND   df.create_scn = dropDf.create_scn
  AND   df.plugin_scn = dropDf.plugin_scn
  AND   df.pdbinc_key = dropDf.pdbinc_key;
END dropDf;
 
FUNCTION beginDataFileResyncForStandby(
  high_df_recid IN number
) return boolean IS
BEGIN
  checkResync;
 
  SELECT high_df_recid INTO last_df_recid
  FROM node
  WHERE node.site_key = this_site_key;
 
  deb('high_df_recid='||high_df_recid||',last_df_recid='||last_df_recid);
 
  IF last_full_ckp_scn IS NULL THEN
     deb('beginDataFileResyncForStandby - no full resync');
     raise_application_error(-20079,
        'full resync from primary database is not done');
  END IF;
 
--
--
--
  IF (high_df_recid > last_df_recid OR last_df_recid IS NULL) THEN
     last_df_recid := high_df_recid;
     last_file# := -1;                   -- initialize for ordering assert
--
--
     IF this_ckp_scn > last_full_ckp_scn THEN
        OPEN dfQ;
        fetchDf;                            -- do priming read
     END IF;
     RETURN TRUE;
  END IF;
  deb('no need to resync datafile names for '||this_db_unique_name||
      ' standby site');
  RETURN FALSE;
END;
 
PROCEDURE checkDataFileForStandby(file#               IN NUMBER,
                                  fname               IN VARCHAR2,
                                  create_scn          IN NUMBER,
                                  create_time         IN DATE,
                                  blocks              IN NUMBER,
                                  block_size          IN NUMBER,
                                  ts#                 IN NUMBER,
                                  rfile#              IN NUMBER,
                                  stop_scn            IN NUMBER,
                                  read_only           IN NUMBER,
                                  foreign_dbid        IN NUMBER,
                                  plugin_scn          IN NUMBER) IS
   local_df_key NUMBER;
BEGIN
   IF (last_file# >= file#) THEN        -- assert rows passed in ascending
      raise_application_error(-20036, 'Invalid record order');
   END IF;
 
   last_file# := file#;                 -- for checking next call
 
--
--
   IF this_ckp_scn > last_full_ckp_scn THEN
      IF (file# != dfRec.file#) THEN
         IF (file# > dfRec.file#) THEN
            deb('checkDataFileResyncForStandby - droped file#=' ||dfRec.file#);
         ELSE
--
            deb('checkDataFileResyncForStandby - added file#=' || file#);
         END IF;
         raise_application_error(-20079, 
            'full resync from primary database is not done');
      ELSE
--
--
--
 
--
--
         IF (create_scn <> dfRec.create_scn OR
             plugin_scn <> dfRec.plugin_scn OR
             stop_scn <> dfRec.stop_scn OR
             stop_scn is null and dfRec.stop_scn is not null OR
             stop_scn is not null and dfRec.stop_scn is null OR
             read_only < dfRec.read_only OR
             read_only is null and dfRec.read_only is not null OR
             read_only is not null and dfRec.read_only is null) THEN
            deb('checkDataFileResyncForStandby - change for file#=' || file#);
            raise_application_error(-20079, 
               'full resync from primary database is not done');
         END IF;
      END IF;
      fetchDF;
   END IF;
 
   BEGIN
--
     select distinct df_key into local_df_key from df, dbinc 
       where  file#        = checkDataFileForStandby.file#
         and  create_scn   = checkDataFileForStandby.create_scn
         and  plugin_scn   = checkDataFileForStandby.plugin_scn
         and  decode(foreign_dbid, 0, checkDataFileForStandby.foreign_dbid,
                     foreign_dbid)  = checkDataFileForStandby.foreign_dbid
         and  ts#          = checkDataFileForStandby.ts#
         and  df.dbinc_key = dbinc.dbinc_key
         and  dbinc.db_key = this_db_key;
   EXCEPTION
--
--
      WHEN no_data_found THEN
         raise_application_error(-20999,
           'Internal error in checkDataFileForStandby - 1 ');
--
      WHEN too_many_rows THEN
         raise_application_error(-20999,
           'Internal error in checkDataFileForStandby - 2 ');
   END;
 
--
--
--
   BEGIN
     INSERT INTO site_dfatt(df_key, fname, site_key)
     VALUES(local_df_key, checkDataFileForStandby.fname, this_site_key);
   EXCEPTION
     WHEN dup_val_on_index THEN
--
       UPDATE site_dfatt SET
         fname = checkDataFileForStandby.fname
       WHERE
         site_key = this_site_key AND
         df_key   = local_df_key;      
   END;
END;
 
PROCEDURE endDataFileResyncForStandby IS
BEGIN
  checkResync;
 
--
  IF (this_ckp_scn > last_full_ckp_scn AND
      dfRec.file# < MAXNUMVAL) THEN
     deb('endDataFileResyncForStandby - dropped file# > ' || dfRec.file#);
     raise_application_error(-20079, 
        'full resync from primary database is not done');
     IF dfQ%ISOPEN THEN 
        CLOSE dfQ; 
     END IF;
  END IF;
 
--
  dfRec.file# := NULL;
 
--
  UPDATE node SET high_df_recid = last_df_recid
  WHERE node.site_key = this_site_key;
 
  last_df_recid := NULL;
END;
 
 
FUNCTION beginDataFileResync(
  high_df_recid IN NUMBER
) RETURN BOOLEAN IS
 
BEGIN
  checkResync;
 
  IF (tsRec.ts# IS NOT NULL) THEN
    raise_application_error(-20041, 'Tablespace resync not completed');
  END IF;
 
  SELECT high_df_recid INTO last_df_recid
  FROM node
  WHERE site_key = this_site_key;
 
  IF (high_df_recid = last_df_recid) THEN
    deb('beginDataFileResync - Resync of datafiles not needed');
    RETURN FALSE;
  ELSIF (high_df_recid > last_df_recid OR last_df_recid IS NULL) THEN
    deb('beginDataFileResync - Catalog df_recid: '||last_df_recid);
    deb('beginDataFileResync - High df_recid: '||high_df_recid);
    last_df_recid := high_df_recid;
 
    OPEN dfQ;
    fetchDf;                            -- do priming read
    last_file# := -1;                   -- initialize for ordering assert
 
    if resync_reason = RESYNC_REASON_DF then
      fullResyncAction.valid   := TRUE;
      fullResyncAction.active  := TRUE;
      fullResyncAction.objtype := RESYNC_OBJECT_DATAFILE;
    else
      fullResyncAction.active  := FALSE;
    end if;
 
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
 
END beginDataFileResync;
 
 
PROCEDURE checkDataFile(file#         IN  NUMBER,
                        fname         IN  VARCHAR2,
                        create_scn    IN  NUMBER,
                        create_time   IN  DATE,
                        blocks        IN  NUMBER,
                        block_size    IN  NUMBER,
                        ts#           IN  NUMBER,
                        stop_scn      IN  NUMBER,
                        read_only     IN  NUMBER,
                        stop_time     IN  DATE     DEFAULT NULL,
                        rfile#        IN  NUMBER   DEFAULT NULL,
                        aux_fname     IN  VARCHAR2 DEFAULT NULL,
                        foreign_dbid        IN NUMBER   DEFAULT 0,
                        foreign_create_scn  IN NUMBER   DEFAULT 0,
                        foreign_create_time IN DATE     DEFAULT NULL,
                        plugged_readonly    IN VARCHAR2 DEFAULT 'NO',
                        plugin_scn          IN NUMBER   DEFAULT 0,
                        plugin_reset_scn    IN NUMBER   DEFAULT 0,
                        plugin_reset_time   IN DATE     DEFAULT NULL,
                        create_thread       IN NUMBER   DEFAULT NULL,
                        create_size         IN NUMBER   DEFAULT NULL,
                        con_id              IN NUMBER   DEFAULT 0,
                        pdb_closed          IN NUMBER   DEFAULT 0,
                        pdb_dict_check      IN BOOLEAN  DEFAULT FALSE,
                        pdb_foreign_dbid    IN NUMBER   DEFAULT 0)
IS
   local_df_key     NUMBER;
   changedauxname   BOOLEAN;
   local_pdbinc_key NUMBER;
   local_pdb_key    NUMBER;
   existing_file    BOOLEAN;
BEGIN
   IF (dfRec.file# IS NULL) THEN -- assert beginDataFileResync was called
      raise_application_error(-20050, 'Datafile resync not started');
   END IF;
   IF (last_file# >= file#) THEN        -- assert rows passed in ascending
      raise_application_error(-20036, 'Invalid record order');
   END IF;
   last_file# := file#;                 -- for checking next call
   
   IF (plugged_readonly = 'NO' AND create_scn > this_ckp_scn) THEN
      raise_application_error(-20052, 'Invalid datafile create SCN');
   ELSIF (plugged_readonly = 'YES' AND plugin_scn > this_ckp_scn) THEN
      raise_application_error(-20055, 'Invalid datafile plugin SCN');
   END IF;
 
--
--
--
--
--
   WHILE (file# > dfRec.file#) LOOP
      deb('checkDatafile - dropping file#: '||to_char(dfRec.file#));
      dropDf(dfRec.file#, dfRec.create_scn, dfRec.plugin_scn,
             this_ckp_scn, this_ckp_time, dfRec.pdbinc_key);
      incResyncActions(RESYNC_ACTION_DROP, dfRec.file#, dfRec.fname);
      fetchDf;
   END LOOP;
 
   IF (file# < dfRec.file#) THEN
      IF (pdb_dict_check) THEN
         deb('checkDataFile - skipping df needs dictionary check');
      ELSE
         local_pdbinc_key := getPdbInc(greatest(create_scn, plugin_scn),
                                       con_id, local_pdb_key);
--
         deb('checkDatafile - adding file#: '||to_char(file#));
         addDF(file#, fname, create_time, create_scn, blocks, block_size,
               ts#, stop_scn, stop_time, read_only, rfile#, foreign_dbid,
               foreign_create_scn, foreign_create_time, plugged_readonly,
               plugin_scn, plugin_reset_scn, plugin_reset_time,
               create_thread, create_size, local_pdbinc_key,
               local_pdb_key, pdb_closed, pdb_foreign_dbid);
--
--
--
         IF (aux_fname is not NULL) THEN
           setCloneName(file#, create_scn, aux_fname, NULL, changedauxname, 
                        plugin_scn);
         END IF;
      END IF;
      incResyncActions(RESYNC_ACTION_ADD, file#, fname);
   ELSE -- (file# = dfRec.file#)
      existing_file := FALSE;
 
      IF (create_scn = dfRec.create_scn AND
          plugin_scn = dfRec.plugin_scn) THEN
--
--
         IF (create_time <> dfRec.create_time) THEN
            raise_application_error(-20053, 'Invalid datafile create time');
         END IF;
         IF (ts# <> dfRec.ts#) THEN
            raise_application_error(-20054, 'Invalid datafile ts#');
         END IF;
 
         existing_file := TRUE;
 
         SELECT DISTINCT df_key INTO local_df_key FROM df 
            WHERE file#        = checkDataFile.file#
              AND create_scn   = checkDataFile.create_scn
              AND plugin_scn   = checkDataFile.plugin_scn
              AND decode(foreign_dbid, 0, checkDataFile.foreign_dbid,
                         foreign_dbid)  = checkDataFile.foreign_dbid
              AND ts#          = checkDataFile.ts#
              AND dbinc_key    = this_dbinc_key;
 
--
--
         IF (fname <> dfRec.fname OR dfRec.fname is NULL) THEN
--
--
--
--
--
           IF (fname = dfRec.clone_fname and dfRec.fname is not null) THEN
              deb('checkDatafile - new datafilename is old auxname');
              setCloneName(dfRec.file#, dfRec.create_scn, dfRec.fname,
                           dfRec.clone_fname, changedauxname,
                           dfRec.plugin_scn);
           END IF;
 
           incResyncActions(RESYNC_ACTION_RENAME, dfRec.file#, fname);
           UPDATE site_dfatt SET
              fname = checkDataFile.fname
           WHERE 
             site_key = this_site_key AND
             df_key   = local_df_key;
 
--
--
           IF sql%rowcount = 0 THEN
              INSERT INTO site_dfatt (df_key, fname, site_key)
              VALUES(local_df_key, checkDataFile.fname, this_site_key);
           END IF;
         END IF;
      END IF;
 
      IF (pdb_dict_check) THEN
         deb('checkDataFile - skipping df needs dictionary check');
      ELSIF (existing_file) THEN
--
--
         IF ((create_thread is not null AND dfRec.create_thread is null) OR
             (create_size is not null AND dfRec.create_size is null)) THEN
           UPDATE df SET
              create_thread    = checkDataFile.create_thread,
              create_size      = checkDataFile.create_size
              WHERE df.df_key  = local_df_key;
         END IF;
 
--
--
         IF foreign_dbid <> 0 AND dfrec.foreign_dbid = 0 THEN
            UPDATE df SET
              foreign_dbid    = checkDataFile.foreign_dbid
              WHERE df.df_key  = local_df_key;
            deb('checkDatafile - foreign_dbid for file#.df_key('||
                local_df_key || ') changed to ' || checkDataFile.foreign_dbid);
         END IF;
 
--
         IF ((blocks <> dfrec.blocks) OR
             (stop_scn <> dfrec.stop_scn) OR
             (stop_scn IS NULL AND dfrec.stop_scn IS NOT NULL) OR
             (stop_scn IS NOT NULL AND dfrec.stop_scn IS NULL) OR
             (pdb_closed <> dfrec.pdb_closed)) THEN
           IF blocks <> dfRec.blocks THEN
              deb('checkDatafile - size changed for file#: '||
                  to_char(file#)||' from '||to_char(dfRec.blocks)||' to '||
                  to_char(blocks));
              incResyncActions(RESYNC_ACTION_RESIZE, dfRec.file#, fname);
           ELSE
              deb('checkDatafile - stopSCN changed for file#: '||
                  to_char(file#)||' from '||
                  nvl(to_char(dfRec.stop_scn), 'NULL')||' to '||
                  nvl(to_char(checkDatafile.stop_scn), 'NULL'));
              incResyncActions(RESYNC_ACTION_CHANGE, dfRec.file#, fname);
           END IF;
 
           UPDATE df SET
              stop_scn     = checkDataFile.stop_scn,
              stop_time    = checkDataFile.stop_time,
              read_only    = checkDataFile.read_only,
              blocks       = checkDataFile.blocks,
              pdb_closed   = checkDataFile.pdb_closed
              WHERE df.dbinc_key  = this_dbinc_key
              AND   df.file#      = dfRec.file#
              AND   df.create_scn = dfRec.create_scn
              AND   df.plugin_scn = dfRec.plugin_scn
              AND   df.pdbinc_key = dfRec.pdbinc_key;
         ELSE
           deb('checkDatafile - stopSCN remains the same for file#: '||
               to_char(file#));
         END IF;
 
--
--
--
         IF (aux_fname is not NULL) THEN
            setCloneName(dfRec.file#, dfRec.create_scn, aux_fname, 
                         dfRec.clone_fname, changedauxname,
                         dfRec.plugin_scn);
            IF changedauxname THEN
               incResyncActions(RESYNC_ACTION_CHANGE, dfRec.file#, fname);
            END IF;
         END IF;
      ELSIF ((case when plugged_readonly = 'NO' then
              create_scn else plugin_scn end) >
             (case when dfRec.plugged_readonly = 'NO' then
              dfRec.create_scn else dfRec.plugin_scn end)) THEN
--
--
--
--
--
--
--
--
--
 
         deb('checkDatafile - file#: '||to_char(file#)||' recreated');
         dropDf(dfRec.file#, dfRec.create_scn, dfRec.plugin_scn,
                this_ckp_scn, this_ckp_time, dfRec.pdbinc_key);
         local_pdbinc_key := getPdbInc(greatest(create_scn, plugin_scn),
                                       con_id, local_pdb_key);
         addDf(file#, fname, create_time, create_scn, blocks, block_size, ts#,
               stop_scn, stop_time, read_only, rfile#, foreign_dbid,
               foreign_create_scn, foreign_create_time, plugged_readonly,
               plugin_scn, plugin_reset_scn, plugin_reset_time,
               create_thread, create_size, local_pdbinc_key,
               local_pdb_key, pdb_closed, pdb_foreign_dbid);
         incResyncActions(RESYNC_ACTION_RECREATE, dfRec.file#, fname);
      ELSE -- (create_scn < dfRec.create_scn)
--
--
--
--
--
         IF (plugged_readonly = 'NO') THEN
            raise_application_error(-20052, 'Invalid datafile create SCN');
         ELSE
            raise_application_error(-20055, 'Invalid datafile plugin SCN');
         END IF;
      END IF;
 
      fetchDF;                          -- get next row from DF cursor
 
   END IF; -- (file# < dfRec.file#)
END checkDataFile;
 
 
PROCEDURE endDataFileResync IS
 
BEGIN
  checkResync;
 
--
  WHILE (dfRec.file# < MAXNUMVAL) LOOP
--
    dropDf(dfRec.file#, dfRec.create_scn, dfRec.plugin_scn,
           this_ckp_scn, this_ckp_time, dfRec.pdbinc_key);
    begin
       incResyncActions(RESYNC_ACTION_DROP, dfRec.file#, dfRec.fname);
    exception
       when others then
           deb('endTableSpaceResync(DO_NOT_IGNORE) - caugth exception ' ||
               substr(sqlerrm, 1, 132));
    end;
    fetchDf;
  END LOOP;
 
--
  dfRec.file# := NULL;
 
--
  UPDATE node SET high_df_recid = last_df_recid
  WHERE site_key = this_site_key;
 
--
  IF this_is_ors and this_ckp_type = 'FULL' THEN
     UPDATE watermarks SET high_df_recid = last_df_recid
     WHERE db_key = this_db_key;
  END IF;
 
  last_df_recid := NULL;
END endDataFileResync;
 
/*-----------------*
 * Tempfile Resync *
 *-----------------*/
 
PROCEDURE fetchTf IS                    -- private to package body
BEGIN
--
  IF tfRec.file# = MAXNUMVAL THEN
     return;
  END IF;
 
  FETCH tfQ INTO tfRec;
  IF tfQ%NOTFOUND THEN
    tfRec.file# := MAXNUMVAL;           -- indicate end-of-fetch
    CLOSE tfQ;
  END IF;
END fetchTf;
 
PROCEDURE addTf(file#          IN NUMBER,  -- private to package body
                fname          IN VARCHAR2,
                create_time    IN DATE,
                create_scn     IN NUMBER,
                blocks         IN NUMBER,
                block_size     IN NUMBER,
                ts#            IN NUMBER,
                rfile#         IN NUMBER,
                autoextend     IN VARCHAR2,
                max_size       IN NUMBER,
                next_size      IN NUMBER,
                con_id         IN NUMBER) IS
   ts_create_scn NUMBER;
   ts_pdbinc_key NUMBER;
   local_tf_key  NUMBER;
   local_pdb_key NUMBER;
BEGIN
   SELECT pdb.pdb_key INTO local_pdb_key
     FROM pdb, pdb_dbinc
    WHERE pdb_dbinc.drop_scn IS NULL
      AND pdb.con_id          = addTf.con_id
      AND pdb.pdb_key         = pdb_dbinc.pdb_key
      AND pdb_dbinc.dbinc_key = this_dbinc_key;
 
   BEGIN
     SELECT ts.create_scn, ts.pdbinc_key
       INTO ts_create_scn, ts_pdbinc_key
     FROM ts, rci_pdbinc_this_dbinc pdbinc
     WHERE ts.dbinc_key = this_dbinc_key
       AND ts.ts# = addTf.ts#
       AND ts.pdbinc_key = pdbinc.pdbinc_key
       AND pdbinc.pdb_key = local_pdb_key
       AND pdbinc.dbinc_key = this_dbinc_key
       AND ts.create_scn  < pdbinc.next_inc_scn
       AND ts.drop_scn IS NULL;            -- in case ts numbers are reused
   EXCEPTION
     WHEN no_data_found THEN
--
--
--
       IF (this_cf_type = 'STANDBY' AND this_db_unique_name is not null) THEN
         RETURN;
       END IF;
   END;
   deb('ts_create_scn=' || ts_create_scn);
 
--
--
  BEGIN
    SELECT DISTINCT tf.tf_key INTO local_tf_key 
    FROM tf, dbinc
    WHERE tf.file#        = addTf.file#
      AND tf.create_scn   = addTf.create_scn
      AND (tf.create_time = addTf.create_time
           OR tf.create_time IS NULL AND addTf.create_time IS NULL)
      AND  tf.ts#         = addTf.ts#
      AND  tf.rfile#      = addTf.rfile#
      AND  tf.dbinc_key   = dbinc.dbinc_key
      AND  dbinc.db_key   = this_db_key;
   EXCEPTION
    WHEN no_data_found THEN
      SELECT rman_seq.nextval INTO local_tf_key FROM dual;
  END;
 
  BEGIN
    INSERT INTO tf(dbinc_key, file#, create_scn, create_time,
                   ts#, ts_create_scn, block_size, rfile#, tf_key, pdb_key,
                   ts_pdbinc_key)
    VALUES(this_dbinc_key, file#, create_scn, create_time, ts#, ts_create_scn,
           block_size, rfile#, local_tf_key, local_pdb_key, ts_pdbinc_key);
  EXCEPTION
    WHEN dup_val_on_index THEN
--
--
--
      IF create_scn = 0 THEN
        UPDATE tf SET 
           create_time   = addTf.create_time,
           ts#           = addTf.ts#,
           ts_create_scn = addTf.ts_create_scn,
           block_size    = addTf.block_size,
           rfile#        = addTf.rfile#,
           pdb_key       = local_pdb_key
        WHERE dbinc_key  = this_dbinc_key
          AND file#      = addTf.file#
          AND create_scn = addTf.create_scn;
      END IF;
 
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
  END;
 
--
--
--
  BEGIN
    INSERT INTO site_tfatt(tf_key, fname, site_key, blocks,
          autoextend, max_size, next_size)
    VALUES(local_tf_key, fname, this_site_key, nvl(addTf.blocks, 0),
           addTf.autoextend, addTf.max_size, addTf.next_size);
--
  EXCEPTION
    WHEN dup_val_on_index THEN
--
      UPDATE site_tfatt SET
        fname = addTf.fname,
        blocks     = nvl(addTf.blocks, 0),
        autoextend = addTf.autoextend,
        max_size   = addTf.max_size,
        next_size  = addTf.next_size,
        drop_scn   = NULL,
        drop_time  = NULL
      WHERE site_key = this_site_key
        AND tf_key   = local_tf_key;      
  END;
END addTf;
 
PROCEDURE dropTf(                       -- private to package body
  tf_key IN NUMBER
 ,drop_scn IN NUMBER
 ,drop_time IN DATE
) IS
 
BEGIN
  UPDATE site_tfatt SET
    drop_scn     = dropTf.drop_scn,
    drop_time    = dropTf.drop_time
  WHERE this_site_key = site_key
    AND tf_key = dropTf.tf_key;
END dropTf;
 
FUNCTION tempFileToResync(
   high_tf_recid IN NUMBER
) RETURN BOOLEAN IS
   tf_recid  number;
BEGIN
  checkResync;
 
  SELECT high_tf_recid INTO tf_recid
  FROM node
  WHERE site_key = this_site_key;
 
  IF (high_tf_recid = tf_recid) THEN
    RETURN FALSE;
  ELSIF (high_tf_recid > tf_recid OR tf_recid IS NULL) THEN
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
END tempFileToResync;
 
--
--
--
FUNCTION beginTempFileResyncForStandby(
  high_tf_recid IN NUMBER
) RETURN BOOLEAN IS
BEGIN
  RETURN beginTempFileResync (high_tf_recid);
END beginTempFileResyncForStandby;
 
--
--
--
--
--
--
--
--
PROCEDURE checkTempFileForStandby
                       (file#          IN  NUMBER,
                        fname          IN  VARCHAR2,
                        create_scn     IN  NUMBER,
                        create_time    IN  DATE,
                        blocks         IN  NUMBER,
                        block_size     IN  NUMBER,
                        ts#            IN  NUMBER,
                        rfile#         IN  NUMBER,
                        autoextend     IN  VARCHAR2,
                        max_size       IN  NUMBER,
                        next_size      IN  NUMBER,
                        con_id         IN  NUMBER DEFAULT 0)
IS
   local_tf_key NUMBER;
BEGIN
    checkTempFile(file#, fname, create_scn, create_time, blocks, block_size,
                  ts#, rfile#, autoextend, max_size, next_size, con_id, FALSE);
END checkTempFileForStandby;
 
PROCEDURE endTempFileResyncForStandby IS
BEGIN
  endTempFileResync;
END endTempFileResyncForStandby;
 
 
FUNCTION beginTempFileResync(
  high_tf_recid IN NUMBER
) RETURN BOOLEAN IS
 
BEGIN
  checkResync;
 
  IF (tsRec.ts# IS NOT NULL) THEN
    raise_application_error(-20041, 'Tablespace resync not completed');
  END IF;
 
  SELECT high_tf_recid INTO last_tf_recid
  FROM node
  WHERE site_key = this_site_key;
 
  IF (high_tf_recid = last_tf_recid) THEN
    deb('beginTempFileResync - Resync of tempfiles not needed');
    RETURN FALSE;
  ELSIF (high_tf_recid > last_tf_recid OR last_tf_recid IS NULL) THEN
    deb('beginTempFileResync - Catalog tf_recid: '||last_tf_recid);
    deb('beginTempFileResync - High tf_recid: '||high_tf_recid);
    last_tf_recid := high_tf_recid;
 
    OPEN tfQ;
    fetchTf;                            -- do priming read
    last_file# := -1;                   -- initialize for ordering assert
 
    if resync_reason = RESYNC_REASON_TF then
      fullResyncAction.active  := TRUE;
      fullResyncAction.valid   := TRUE;
      fullResyncAction.objtype := RESYNC_OBJECT_TEMPFILE;
    else
      fullResyncAction.active  := FALSE;
    end if;
 
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
 
END beginTempFileResync;
 
PROCEDURE checkTempFile(file#          IN  NUMBER,
                        fname          IN  VARCHAR2,
                        create_scn     IN  NUMBER,
                        create_time    IN  DATE,
                        blocks         IN  NUMBER,
                        block_size     IN  NUMBER,
                        ts#            IN  NUMBER,
                        rfile#         IN  NUMBER,
                        autoextend     IN  VARCHAR2,
                        max_size       IN  NUMBER,
                        next_size      IN  NUMBER,
                        con_id         IN  NUMBER  DEFAULT 0,
                        pdb_dict_check IN  BOOLEAN DEFAULT FALSE)
IS
   local_tf_key     NUMBER;
BEGIN
   IF (tfRec.file# IS NULL) THEN -- assert beginTempFileResync was called
      raise_application_error(-20050, 'Tempfile resync not started');
   END IF;
   IF (last_file# >= file#) THEN        -- assert rows passed in ascending
      raise_application_error(-20036, 'Invalid record order');
   END IF;
   last_file# := file#;                 -- for checking next call
 
--
--
--
--
 
--
--
--
--
--
   WHILE (file# > tfRec.file#) LOOP
      dropTf(tfRec.tf_key, this_ckp_scn, this_ckp_time);
      incResyncActions(RESYNC_ACTION_DROP, tfRec.file#, tfRec.fname);
      fetchTf;
   END LOOP;
 
   IF (file# < tfRec.file#) THEN
      IF (pdb_dict_check) THEN
         deb('checkTempFile - skipping tf needs dictionary check');
      ELSE 
         addTf(file#, fname, create_time, create_scn, blocks, block_size, ts#,
               rfile#, autoextend, max_size, next_size, con_id);
      END IF;
      incResyncActions(RESYNC_ACTION_ADD, tfRec.file#, fname);
   ELSE -- (file# = tfRec.file#)
      IF (pdb_dict_check) THEN
         deb('checkTempFile - skipping tf needs dictionary check');
      ELSIF (create_scn = 0) THEN
         addTf(file#, fname, create_time, create_scn, blocks, block_size, ts#,
               rfile#, autoextend, max_size, next_size, con_id);
         incResyncActions(RESYNC_ACTION_CHANGE, file#, fname);
      ELSIF (create_scn = tfRec.create_scn) THEN
--
--
         IF (create_time <> tfRec.create_time) THEN
            raise_application_error(-20053, 'Invalid tempfile create time');
         END IF;
         IF (ts# <> tfRec.ts#) THEN
            raise_application_error(-20054, 'Invalid tempfile ts#');
         END IF;
 
--
         addTf(file#, fname, create_time, create_scn, blocks, block_size, ts#,
               rfile#, autoextend, max_size, next_size, con_id);
 
         IF (fname <> tfRec.fname OR tfRec.fname is NULL) THEN
            incResyncActions(RESYNC_ACTION_RENAME, file#, fname);
         END IF;
 
--
--
         IF (blocks <> tfrec.blocks OR
             autoextend <> tfrec.autoextend OR
             max_size <> tfrec.max_size OR
             next_size <> tfrec.next_size ) THEN
           IF blocks <> tfrec.blocks THEN
              incResyncActions(RESYNC_ACTION_RESIZE, file#, fname);
           ELSE
              incResyncActions(RESYNC_ACTION_CHANGE, file#, fname);
           END IF;
         END IF;
      ELSE
--
--
--
--
--
--
         dropTf(tfRec.tf_key, create_scn, create_time);
         addTf(file#, fname, create_time, create_scn, blocks, block_size, ts#,
               rfile#, autoextend, max_size, next_size, con_id);
         incResyncActions(RESYNC_ACTION_RECREATE, file#, fname);
      END IF;
 
      fetchTf;                          -- get next row from Tf cursor
 
   END IF; -- (file# = tfRec.file#)
   
END checkTempFile;
 
 
PROCEDURE endTempFileResync IS
 
BEGIN
  checkResync;
 
--
  deb('endTempFileResync - entering with tempfile number'||tfRec.file#);
  WHILE (tfRec.file# < MAXNUMVAL) LOOP
    dropTf(tfRec.tf_key, this_ckp_scn, this_ckp_time);
    incResyncActions(RESYNC_ACTION_DROP, tfRec.file#, tfRec.fname);
    fetchTf;
    deb('endTempFileResync - dropping tempfile '||tfRec.file#);
  END LOOP;
 
--
  tfRec.file# := NULL;
 
--
  UPDATE node SET high_tf_recid = last_tf_recid
  WHERE site_key = this_site_key;
 
--
  IF this_is_ors and this_ckp_type = 'FULL' THEN
     UPDATE watermarks SET high_tf_recid = last_tf_recid
     WHERE db_key = this_db_key;
  END IF;
 
  last_tf_recid := NULL;
 
END endTempFileResync;
 
/*---------------------*
 * Redo Thread resync  *
 *---------------------*/
 
PROCEDURE fetchRt IS
 
BEGIN
  FETCH rtQ INTO rtRec;
  IF rtQ%NOTFOUND THEN
    rtRec.thread# := MAXNUMVAL;
    CLOSE rtQ;
  END IF;
END fetchRt;
 
PROCEDURE addRt(
  thread#        IN NUMBER
 ,last_sequence# IN NUMBER
 ,enable_scn     IN NUMBER
 ,enable_time    IN DATE
 ,disable_scn    IN NUMBER
 ,disable_time   IN DATE
 ,status         IN VARCHAR2
) IS
 
BEGIN
  INSERT INTO rt
    (dbinc_key, thread#, sequence#,
     enable_scn, enable_time, disable_scn, disable_time, status)
  VALUES
    (this_dbinc_key, thread#, last_sequence#,
     enable_scn, enable_time, disable_scn, disable_time, status);
END addRt;
 
PROCEDURE dropRt(thread# IN NUMBER) IS
BEGIN
--
  DELETE FROM rt
  WHERE rt.dbinc_key = this_dbinc_key
  AND   rt.thread#   = dropRt.thread#;
END dropRt;
 
FUNCTION beginThreadResync(
  high_rt_recid IN NUMBER
) RETURN BOOLEAN IS
 
BEGIN
  checkResync;
 
  SELECT high_rt_recid INTO last_rt_recid
  FROM node
  WHERE site_key = this_site_key;
 
  IF (high_rt_recid = last_rt_recid) THEN
    deb('beginThreadResync - Resync of redo threads not needed');
    RETURN FALSE;
  ELSIF (high_rt_recid > last_rt_recid OR last_rt_recid IS NULL) THEN
    deb('beginThreadResync - Catalog rt_recid: '||last_rt_recid);
    deb('beginThreadResync - High rt_recid: '||high_rt_recid);
    last_rt_recid := high_rt_recid;
 
    OPEN rtQ;
    fetchRt;                            -- do priming read
    last_thread# := -1;
 
    if resync_reason = RESYNC_REASON_THR then
      fullResyncAction.valid   := TRUE;
      fullResyncAction.active  := TRUE;
      fullResyncAction.objtype := RESYNC_OBJECT_REDOTHREAD;
    else
      fullResyncAction.active  := FALSE;
    end if;
 
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
 
END beginThreadResync;
 
PROCEDURE checkThread(
  thread#        IN NUMBER
 ,last_sequence# IN NUMBER
 ,enable_scn     IN NUMBER
 ,enable_time    IN DATE
 ,disable_scn    IN NUMBER
 ,disable_time   IN DATE
 ,status         IN VARCHAR2
) IS
 
BEGIN
  IF (rtRec.thread# IS NULL) THEN
    raise_application_error(-20061, 'Thread resync not started');
  END IF;
  IF (last_thread# >= thread#) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
  last_thread# := thread#;
 
  WHILE (thread# > rtRec.thread#) LOOP
--
--
--
    dropRt(rtRec.thread#);
    incResyncActions(RESYNC_ACTION_DROP, rtRec.thread#, to_char(NULL));
    fetchRt;
  END LOOP;
  IF (thread# < rtRec.thread#) THEN
--
    addRt(thread#, last_sequence#, enable_scn, enable_time,
          disable_scn, disable_time, status);
    incResyncActions(RESYNC_ACTION_ADD, thread#, to_char(NULL));
  ELSE -- (thread# = rtRec.thread#)
--
    UPDATE rt SET
      sequence# = checkThread.last_sequence#,
      enable_scn = checkThread.enable_scn,
      enable_time = checkThread.enable_time,
      disable_scn = checkThread.disable_scn,
      disable_time = checkThread.disable_time,
      status = checkThread.status
    WHERE rt.dbinc_key = this_dbinc_key
    AND   rt.thread# = checkThread.thread#;
    incResyncActions(RESYNC_ACTION_CHANGE, rtRec.thread#, to_char(NULL));
    fetchRt;
  END IF;
 
END checkThread;
 
PROCEDURE endThreadResync IS
BEGIN
  WHILE (rtRec.thread# < MAXNUMVAL) LOOP
--
--
--
    dropRt(rtRec.thread#);
    fetchRt;
  END LOOP;
 
  rtRec.thread# := NULL;
 
--
  UPDATE node SET high_rt_recid = last_rt_recid
  WHERE site_key = this_site_key;
 
  last_rt_recid := NULL;
END endThreadResync;
 
/*------------------------*
 * Online Redo Log resync *
 *------------------------*/
 
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
FUNCTION nlsnamecmp(n1 IN varchar2, n2 IN varchar2) RETURN NUMBER IS
  CURSOR nlsnamecmp_c(n1 varchar2, n2 varchar2) IS
     SELECT name
       FROM (SELECT n1 name FROM dual
             UNION ALL
             SELECT n2 name FROM dual)
      ORDER BY nlssort(name, 'NLS_COMP=ANSI NLS_SORT=ASCII7');
  ln1  varchar2(1024);
  ln2  varchar2(1024);
BEGIN
  if (n1 is null or n2 is null) then
     return null;
  elsif (n1 = n2) then
     return 0;
  elsif (n1 = chr(1) or n2 = chr(255)) then
     return -1;
  elsif (n2 = chr(1) or n1 = chr(255)) then
     return 1;
  end if;
 
  open nlsnamecmp_c(n1, n2);
  fetch nlsnamecmp_c into ln1;
  fetch nlsnamecmp_c into ln2;
  close nlsnamecmp_c;
  if (ln1 = n1) then
     return -1;
  end if;
  return 1;
END nlsnamecmp;
 
PROCEDURE fetchOrl IS
 
BEGIN
  FETCH orlQ INTO orlRec;
  IF orlQ%NOTFOUND THEN
    orlRec.fname := chr(255);      -- assume chr(255) is greater than any name
    CLOSE orlQ;
  END IF;
END fetchOrl;
 
PROCEDURE addOrl(
  thread#        IN NUMBER
 ,group#         IN NUMBER
 ,fname          IN VARCHAR2
 ,bytes          IN NUMBER
 ,type           IN VARCHAR2
) IS
  thread_not_found EXCEPTION;
  PRAGMA EXCEPTION_INIT(thread_not_found, -2291);
BEGIN
  INSERT INTO orl
    (dbinc_key, thread#, group#, fname, bytes, type, site_key)
  VALUES
    (this_dbinc_key, thread#, group#, fname, bytes, type, this_site_key);
EXCEPTION
  WHEN thread_not_found THEN
--
--
    IF type <> 'STANDBY' THEN
       raise_application_error(-20079,
             'full resync from primary database is not done');
    ELSE
       deb('ignored resync of standby redo log ' || fname);
    END IF;
END addOrl;
 
PROCEDURE dropOrl(fname IN VARCHAR2) IS
BEGIN
--
  DELETE FROM orl
  WHERE orl.dbinc_key = this_dbinc_key
  AND   orl.site_key  = this_site_key
  AND   orl.fname     = dropOrl.fname;
END dropOrl;
 
FUNCTION beginOnlineRedoLogResync(
  high_orl_recid IN NUMBER
) RETURN BOOLEAN IS
 
BEGIN
  checkResync;
 
  SELECT high_orl_recid INTO last_orl_recid
  FROM node
  WHERE site_key = this_site_key;
 
  IF (high_orl_recid = last_orl_recid) THEN
    deb('beginOnlineRedoLogResync - Resync of online logs not needed');
    RETURN FALSE;
  ELSIF (high_orl_recid > last_orl_recid OR last_orl_recid IS NULL) THEN
    deb('beginOnlineRedoLogResync - Catalog orl_recid: '||last_orl_recid);
    deb('beginOnlineRedoLogResync - High orl_recid: '||high_orl_recid);
    last_orl_recid := high_orl_recid;
 
    OPEN orlQ;
    fetchOrl;
    last_fname := chr(1);           -- assume chr(1) is less than any name
 
    if resync_reason = RESYNC_REASON_ORL then
      fullResyncAction.active  := TRUE;
      fullResyncAction.valid   := TRUE;
      fullResyncAction.objtype := RESYNC_OBJECT_ONLINELOG;
    else
      fullResyncAction.active := FALSE;
    end if;
 
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
END beginOnlineRedoLogResync;
 
PROCEDURE checkOnlineRedoLog(
  thread#        IN NUMBER
 ,group#         IN NUMBER
 ,fname          IN VARCHAR2
 ,bytes          IN NUMBER   DEFAULT NULL
 ,type           IN VARCHAR2 DEFAULT 'ONLINE'
) IS
BEGIN
  IF (orlRec.fname IS NULL) THEN
    raise_application_error(-20061, 'Redo resync not started');
  END IF;
  IF (nlsnamecmp(last_fname, fname) >= 0) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
  last_fname := fname;
 
  WHILE (nlsnamecmp(fname, orlRec.fname) > 0) LOOP
--
--
--
    dropOrl(orlRec.fname);
    incResyncActions(RESYNC_ACTION_DROP, to_number(NULL), orlRec.fname);
    fetchOrl;
  END LOOP;
  IF (nlsnamecmp(fname, orlRec.fname) < 0) THEN
--
    addOrl(thread#, group#, fname, bytes, type);
    incResyncActions(RESYNC_ACTION_ADD, to_number(NULL), fname);
  ELSE -- (fname = orlRec.fname)
    UPDATE orl SET
      thread# = checkOnlineRedoLog.thread#,
      group#  = checkOnlineRedoLog.group#,
      bytes   = checkOnlineRedoLog.bytes,
      type    = checkOnlineRedoLog.type
    WHERE orl.dbinc_key = this_dbinc_key
    AND   orl.fname = checkOnlineRedoLog.fname
    AND   orl.site_key = this_site_key;
    incResyncActions(RESYNC_ACTION_CHANGE, to_number(NULL), orlRec.fname);
    fetchOrl;
  END IF;
END checkOnlineRedoLog;
 
PROCEDURE endOnlineRedoLogResync IS
BEGIN
  WHILE (orlRec.fname != chr(255)) LOOP
--
--
--
    dropOrl(orlRec.fname);
    incResyncActions(RESYNC_ACTION_DROP, to_number(NULL), orlRec.fname);
    fetchOrl;
  END LOOP;
 
  orlRec.fname := NULL;
 
--
  UPDATE node SET high_orl_recid = last_orl_recid
  WHERE site_key = this_site_key;
 
  last_orl_recid := NULL;
END endOnlineRedoLogResync;
 
/*---------------------------------*
 * Guaranteed restore point Resync *
 *---------------------------------*/
 
PROCEDURE fetchGrsp IS
BEGIN
  FETCH grspQ INTO grspRec;
  IF grspQ%NOTFOUND THEN
    grspRec.rspname := chr(255);  -- assume chr(255) is greater than any name
    grspRec.pdb_key := null;
    CLOSE grspQ;
  END IF;
END fetchGrsp;
 
PROCEDURE addGrsp(
  rspname        IN VARCHAR2
 ,from_scn       IN NUMBER
 ,to_scn         IN NUMBER
 ,dbinc_key      IN NUMBER
 ,create_time    IN DATE
 ,rsp_time       IN DATE
 ,guaranteed     IN VARCHAR2
 ,pdb_key        IN NUMBER
 ,clean          IN VARCHAR2
) IS
 
BEGIN
  INSERT INTO grsp
    (dbinc_key, rspname, from_scn, to_scn, creation_time, rsptime, 
     guaranteed, site_key, pdb_key, clean)
  VALUES
    (dbinc_key, rspname, from_scn, to_scn, create_time, rsp_time,
     guaranteed, this_site_key, pdb_key, clean);
END addGrsp;
 
PROCEDURE dropGrsp(
  rspname   IN VARCHAR2
 ,pdb_key   IN NUMBER) IS
BEGIN
  DELETE FROM grsp
  WHERE grsp.rspname = dropGrsp.rspname
    AND grsp.site_key = this_site_key
    AND grsp.pdb_key = dropGrsp.pdb_key;
END dropGrsp;
 
FUNCTION beginGuaranteedRPResync(
  high_grsp_recid IN NUMBER
) RETURN BOOLEAN IS
BEGIN
  checkResync;
 
  SELECT node.high_grsp_recid INTO last_grsp_recid
  FROM node
  WHERE site_key = this_site_key;
 
  IF (high_grsp_recid = last_grsp_recid) THEN
    RETURN FALSE;
  ELSIF (high_grsp_recid > last_grsp_recid OR last_grsp_recid IS NULL) THEN
    last_grsp_recid := high_grsp_recid;
 
    OPEN grspQ;
    fetchGrsp;
    last_rspname := chr(1);           -- assume chr(1) is less than any name
    last_pdb_key := -1;
    RETURN TRUE;
  ELSE
    raise_application_error(-20035, 'Invalid high recid');
  END IF;
 
END beginGuaranteedRPResync;
 
PROCEDURE checkGuaranteedRP(
  rspname            IN VARCHAR2
 ,from_scn           IN NUMBER
 ,to_scn             IN NUMBER
 ,resetlogs_change#  IN NUMBER
 ,resetlogs_time     IN DATE
 ,create_time        IN DATE DEFAULT NULL
 ,rsp_time           IN DATE DEFAULT NULL
 ,guaranteed         IN VARCHAR2 DEFAULT 'YES'
 ,con_id             IN NUMBER   DEFAULT NULL
 ,clean              IN VARCHAR2 DEFAULT 'NO'
) IS
   dbinc_key     number;
   local_pdb_key number;
BEGIN
  IF (grspRec.rspname IS NULL) THEN
    raise_application_error(-20099, 'restore point resync not started');
  END IF;
 
  dbinc_key := checkIncarnation(resetlogs_change#, resetlogs_time);
 
--
--
  SELECT pdb.pdb_key INTO local_pdb_key
    FROM pdb, pdb_dbinc
   WHERE pdb_dbinc.drop_scn IS NULL
     AND pdb.con_id IN
         (checkGuaranteedRP.con_id,
          0, 
          decode(checkGuaranteedRP.con_id, 0, 1))
     AND pdb.pdb_key         = pdb_dbinc.pdb_key
     AND pdb_dbinc.dbinc_key = this_dbinc_key;
 
  IF (nlsnamecmp(last_rspname, rspname) >= 0 AND
      (last_pdb_key = local_pdb_key)) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
  last_rspname := rspname;
  last_pdb_key := local_pdb_key;
 
  WHILE (grspRec.pdb_key != local_pdb_key) LOOP
--
--
--
    dropGrsp(grspRec.rspname, grspRec.pdb_key);
    fetchGrsp;
  END LOOP;
 
  WHILE (grspRec.pdb_key = local_pdb_key AND
         nlsnamecmp(rspname, grspRec.rspname) > 0) LOOP
--
--
--
    dropGrsp(grspRec.rspname, grspRec.pdb_key);
    fetchGrsp;
  END LOOP;
 
  IF (grspRec.pdb_key != local_pdb_key OR
      nlsnamecmp(rspname, grspRec.rspname) < 0) THEN
--
    addGrsp(rspname, from_scn, to_scn, dbinc_key, create_time, rsp_time,
            guaranteed, local_pdb_key, clean);
  ELSE -- (rspname = grspRec.rspname)
--
    UPDATE grsp SET
      from_scn = checkGuaranteedRP.from_scn,
      to_scn = checkGuaranteedRP.to_scn,
      rsptime = checkGuaranteedRP.rsp_time,
      guaranteed = checkGuaranteedRP.guaranteed,
      dbinc_key = dbinc_key
    WHERE grsp.rspname = checkGuaranteedRP.rspname
      AND grsp.site_key = this_site_key
      AND grsp.pdb_key = local_pdb_key;
 
    fetchGrsp;
  END IF;
END checkGuaranteedRP;
 
PROCEDURE endGuaranteedRPResync IS
BEGIN
  WHILE (grspRec.rspname != chr(255)) LOOP
--
--
    dropGrsp(grspRec.rspname, grspRec.pdb_key);
    fetchGrsp;
  END LOOP;
 
  grspRec.rspname := NULL;
   
--
  UPDATE node SET high_grsp_recid = last_grsp_recid
  WHERE site_key = this_site_key;
      
  last_grsp_recid := NULL;
END endGuaranteedRPResync;
 
 
/*-----------------------------------*
 * RMAN Configuration records resync *
 *-----------------------------------*/
 
FUNCTION beginConfigResync(
  high_conf_recid IN NUMBER
) RETURN NUMBER IS
 
BEGIN
  checkResync;
 
  SELECT high_conf_recid INTO last_conf_recid
  FROM   node
  WHERE  site_key = this_site_key;
 
  IF (high_conf_recid = last_conf_recid)
  THEN
    RETURN CONFIGRESYNC_NO;                                -- no resync needed
  ELSIF (last_conf_recid IS NULL OR high_conf_recid > last_conf_recid)
  THEN
    last_conf_recid := high_conf_recid;
    RETURN CONFIGRESYNC_TORC;                   -- we need resync from CF to RC
  ELSE
    last_conf_recid := high_conf_recid;
    RETURN CONFIGRESYNC_TOCF;                   -- we need resync from RC to CF
  END IF;
 
END beginConfigResync;
 
PROCEDURE endConfigResync IS
BEGIN
 
--
  UPDATE node SET high_conf_recid = last_conf_recid
  WHERE site_key = this_site_key;
 
  last_conf_recid := NULL;
 
END endConfigResync;
 
FUNCTION beginConfigResync2(
  high_conf_recid IN     NUMBER
) RETURN NUMBER IS
  to_CF                 boolean := FALSE;
  to_Catalog            boolean := FALSE;
  local_force_resync2cf VARCHAR2(3) := 'NO';
  curr_cf_version_time  DATE;
  conf_count            NUMBER;
BEGIN
 
  checkResync;
 
  SELECT node.high_conf_recid, node.force_resync2cf, cf_create_time
  INTO   last_conf_recid,      local_force_resync2cf, curr_cf_version_time
  FROM   node
  WHERE  site_key = this_site_key;
 
--
  IF (local_force_resync2cf = 'YES')
  THEN
     to_CF := TRUE;
  END IF;
 
--
--
  IF (last_cf_version_time is NULL) THEN
     SELECT COUNT(*) INTO conf_count FROM CONF
        WHERE site_key = this_site_key;
     IF conf_count = 0 THEN
        to_Catalog := TRUE;
     END IF;
  END IF;
 
--
--
--
--
  IF (last_cf_version_time <> curr_cf_version_time) THEN
     IF (this_cf_type = 'CURRENT') THEN
        IF high_conf_recid > last_conf_recid THEN
           to_Catalog := TRUE;
        ELSIF (high_conf_recid < last_conf_recid) THEN
           to_CF := TRUE;
        END IF;
     ELSE
        to_CF := TRUE;
     END IF;
  END IF;
 
--
--
--
--
--
  IF (last_cf_version_time = curr_cf_version_time) THEN
     IF (high_conf_recid > last_conf_recid) THEN
        to_Catalog := TRUE;
     ELSIF (high_conf_recid < last_conf_recid) THEN
        to_CF := TRUE;
     END IF;
  END IF;
 
--
--
  IF (NOT to_Catalog AND NOT to_CF)
  THEN
     RETURN CONFIGRESYNC_NO;
  END IF;
 
--
--
  last_conf_recid     := high_conf_recid;
 
--
  IF (NOT to_Catalog AND to_CF) THEN
     RETURN CONFIGRESYNC_TOCF;
  END IF;
 
--
  IF (to_Catalog AND NOT to_CF) THEN
     RETURN CONFIGRESYNC_TORC;
  END IF;
 
--
--
  IF (to_Catalog AND to_CF) THEN
     RETURN CONFIGRESYNC_TORC_TOCF;
  END IF;
 
END beginConfigResync2;
 
PROCEDURE endConfigResync2(sync_to_cf_pending IN boolean DEFAULT FALSE) IS
   cf_pending number := 0;
BEGIN
 
  IF sync_to_cf_pending THEN
    cf_pending := 1;
  END IF;
 
  IF (force_resync2cf = 'YES') THEN
    deb('endConfigResync2 - force_resync2cf = TRUE');
--
--
    UPDATE node SET node.force_resync2cf = 'YES'
      WHERE node.db_key = this_db_key
        AND site_key <> this_site_key;
  END IF;
 
--
--
--
--
--
  UPDATE node SET node.high_conf_recid = last_conf_recid,
                  node.force_resync2cf = decode(cf_pending, 1, 'YES', 'NO')
  WHERE site_key = this_site_key;
 
  deb('endConfigResync2 - last_conf_recid='||last_conf_recid);
 
  force_resync2cf := 'NO';
  last_conf_recid := NULL;
 
END endConfigResync2;
 
PROCEDURE getConfig(
   conf#          OUT    number
  ,name           IN OUT varchar2
  ,value          IN OUT varchar2
  ,first          IN     boolean)
IS
   eof          boolean := FALSE;
BEGIN
 
--
--
   dbms_rcvman.getConfig(conf#, name, value, first);
 
END getConfig;
 
PROCEDURE getRmanOutputLogging(days OUT number)
IS
  conf_value             varchar2(512);
  conf_name              varchar2(512) := 'RMAN OUTPUT';
  conf#                  binary_integer;
  len1                   binary_integer;
  len2                   binary_integer;
  len3                   binary_integer;
BEGIN
  deb('Entering getRmanOutputLogging');
  days := 0;
  dbms_rcvman.getConfig(conf#, conf_name, conf_value, TRUE);
 
  len1 := length('TO KEEP FOR ');
  len2 := length(' DAYS');
  len3 := length(conf_value);
  days := to_number(substr(conf_value, len1, len3-len2-len1+1));
  deb('getRmanOutputLogging - days = '||days);
 
EXCEPTION
  WHEN no_data_found THEN
    deb('getRmanOutputLogging - config not set, taking default');
    days := 7;
 
END getRmanOutputLogging;
 
PROCEDURE setKeepOutputForSession(days IN number)
IS
BEGIN
  deb('setKeepOutputForSession - session_keep_output = ' || days);
  session_keep_output := days;
END setKeepOutputForSession;
 
PROCEDURE updateRestorePoint(
  lowscn  IN NUMBER
 ,highscn IN NUMBER DEFAULT NULL -- next scn by another name
) IS
  nextscn number;
  refs number;
BEGIN
--
  IF (highscn is null) THEN
    nextscn := lowscn + 1;
  ELSE
    nextscn := highscn;
  END IF;
 
--
--
  UPDATE nrsp r SET LONG_TERM = NULL
  WHERE r.to_scn >= lowscn AND r.to_scn <= nextscn
    AND r.long_term IS NOT NULL
    AND r.site_key = this_site_key;
  deb('updateRestorePoint - (lowscn ' || lowscn || ' - highscn ' || nextscn ||
      ') rows updated ' || sql%rowcount);
END updateRestorePoint;
 
/*-------------------------*
 * Redo Log History resync *
 *-------------------------*/
 
FUNCTION beginLogHistoryResync RETURN NUMBER IS
 
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
--
--
     SELECT high_rlh_recid INTO last_rlh_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_rlh_recid := sessionWaterMarks.high_rlh_recid;
  END IF;
 
  RETURN last_rlh_recid;
 
END beginLogHistoryResync;
 
FUNCTION getLogHistoryLowSCN RETURN NUMBER IS
   lowSCN  number;
BEGIN
   checkResync;
   SELECT nvl(max(low_scn), 0)
     INTO lowSCN
     FROM rlh
    WHERE rlh.dbinc_key = this_dbinc_key;
   RETURN lowSCN;
END getLogHistoryLowSCN;
 
PROCEDURE checkLogHistory(
  rlh_recid   IN NUMBER
 ,rlh_stamp   IN NUMBER
 ,thread#     IN NUMBER
 ,sequence#   IN NUMBER
 ,low_scn     IN NUMBER
 ,low_time    IN DATE
 ,next_scn    IN NUMBER
 ,reset_scn   IN number default NULL
 ,reset_time  IN date default NULL
) IS
 
local   rlh%rowtype;
 
BEGIN
  IF (last_rlh_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (rlh_recid < last_rlh_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (rlh_recid > last_rlh_recid + 1) THEN
--
--
    NULL;
  END IF;
  last_rlh_recid := rlh_recid;
 
  IF (last_dbinc_key is NULL or reset_scn is NULL) THEN
     deb('checkLogHistory - Init last_dbinc_key');
     last_dbinc_key := this_dbinc_key;
     select reset_scn, reset_time into last_reset_scn, last_reset_time
        from dbinc
        where dbinc_key = this_dbinc_key;
  END IF;
 
  IF (reset_scn IS NOT NULL and reset_time IS NOT NULL) THEN
    IF (reset_scn <> last_reset_scn or reset_time <> last_reset_time) THEN
      BEGIN
         deb('checkLogHistory - new last_dbinc_key');
         deb('checkLogHistory - for reset_time ' || checkLogHistory.reset_time
             || ' reset_scn ' || checkLogHistory.reset_scn
             || ' this_db_key ' || this_db_key);
         select dbinc_key into last_dbinc_key from dbinc
           where reset_time = checkLogHistory.reset_time and
                 reset_scn = checkLogHistory.reset_scn and
                 db_key = this_db_key;
         last_reset_scn := reset_scn;
         last_reset_time := reset_time;
      EXCEPTION
      WHEN others THEN
         raise_application_error(-29999, 'Unknown Incarnation');
      END;
    END IF;
  END IF;
 
  deb('checkLogHistory - last_dbinc_key='||last_dbinc_key||
      ' reset_scn '||reset_scn || ' reset_time '||reset_time);
 
  BEGIN
    INSERT INTO rlh(
       rlh_key, dbinc_key, rlh_recid, rlh_stamp, thread#, sequence#,
       low_scn, low_time, next_scn)
    VALUES(
       rman_seq.nextval, last_dbinc_key, rlh_recid, rlh_stamp,
       thread#, sequence#, low_scn, low_time, next_scn);
  EXCEPTION
    WHEN dup_val_on_index THEN
--
--
      RETURN;
  END;
END checkLogHistory;
 
PROCEDURE endLogHistoryResync IS
 
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
--
     UPDATE node SET high_rlh_recid = last_rlh_recid
     WHERE site_key = this_site_key;
  END IF;
 
  last_rlh_recid := NULL;
 
END endLogHistoryResync;
 
/*-------------------------*
 * Archived Log resync     *
 *-------------------------*/
 
FUNCTION beginArchivedLogResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_al_recid INTO last_al_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_al_recid := sessionWaterMarks.high_al_recid;
  END IF;
 
  RETURN last_al_recid;
END beginArchivedLogResync;
 
PROCEDURE deleteDuplicateAL(recid IN NUMBER,
                            stamp IN NUMBER,
                            fname in VARCHAR2) IS
   lfname al.fname%TYPE;
BEGIN
   
   lfname := fname;
   IF lfname is null THEN
      BEGIN
         SELECT fname INTO lfname from AL 
         WHERE al_recid = recid
           AND al_stamp = stamp
           AND al.dbinc_key in 
               (select dbinc_key from dbinc where db_key = this_db_key);
      EXCEPTION
         WHEN no_data_found THEN
            RETURN;
         WHEN too_many_rows THEN -- unique key is dbinc_key, al_recid, al_stamp
            RETURN;
      END;
   END IF;
 
--
--
   DELETE al
   WHERE al.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
     AND al.fname    = lfname
     AND ((nvl(al.site_key, this_site_key) = this_site_key) OR
          (logs_shared = TRUE#))
     AND al.fname_hashkey = substr(lfname,1,10)||substr(lfname,-10)
     AND NOT  (al.al_recid = recid AND
               al.al_stamp = stamp );
 
END deleteDuplicateAL;
 
PROCEDURE checkArchivedLog(
  al_recid    IN NUMBER
 ,al_stamp    IN NUMBER
 ,thread#     IN NUMBER
 ,sequence#   IN NUMBER
 ,reset_scn   IN NUMBER
 ,reset_time  IN DATE
 ,low_scn     IN NUMBER
 ,low_time    IN DATE
 ,next_scn    IN NUMBER
 ,next_time   IN DATE
 ,blocks      IN NUMBER
 ,block_size  IN NUMBER
 ,fname       IN VARCHAR2
 ,archived    IN VARCHAR2
 ,completion_time IN DATE
 ,status      IN VARCHAR2
 ,is_standby  IN VARCHAR2
 ,dictionary_begin      IN VARCHAR2   default NULL
 ,dictionary_end        IN VARCHAR2   default NULL
 ,is_recovery_dest_file IN VARCHAR2   default 'NO'
 ,compressed            IN VARCHAR2   default 'NO'
 ,creator               IN VARCHAR2   default NULL
 ,terminal              IN VARCHAR2   default 'NO'
 ,chk_last_recid        IN boolean    default TRUE
) IS
 
local  al%rowtype;
my_dbinc_key NUMBER;
 
BEGIN
--
--
  IF chk_last_recid THEN
     IF (last_al_recid IS NULL) THEN
       raise_application_error(-20037, 'Invalid last recid');
     END IF;
 
     IF (al_recid < last_al_recid) THEN
       raise_application_error(-20036, 'Invalid record order');
     END IF;
 
     IF (al_recid > last_al_recid + 1) THEN
--
--
       NULL;
     END IF;
     last_al_recid := al_recid;
  END IF;
 
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
  IF (al_stamp > 0 and al_stamp < kccdivts) THEN
     deb('checkArchivedLog - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
--
  IF (sequence# = 0) THEN
    RETURN;
  END IF;
 
--
--
--
  my_dbinc_key := checkIncarnation(reset_scn, reset_time);
 
  BEGIN
    IF (status = 'D') THEN
--
       NULL;
    ELSE
       INSERT INTO al
         (al_key, dbinc_key, al_recid, al_stamp, thread#, sequence#,
          low_scn, low_time, next_scn, next_time,
          fname, fname_hashkey, archived, blocks, block_size,
          completion_time, status, is_standby,
          dictionary_begin, dictionary_end, is_recovery_dest_file, 
          compressed, creator, terminal, site_key)
       VALUES
         (rman_seq.nextval, my_dbinc_key, al_recid, al_stamp, thread#,
          sequence#, low_scn, low_time, next_scn, next_time,
          fname, substr(fname,1,10)||substr(fname, -10),
          archived, blocks, checkArchivedLog.block_size, completion_time,
          status, is_standby, dictionary_begin, dictionary_end,
          is_recovery_dest_file, compressed, creator, terminal, this_site_key);
 
       deleteDuplicateAL(al_recid, al_stamp, fname);
    END IF;
 
--
--
--
--
    IF checkArchivedLog.archived = 'N' then
       UPDATE rlh SET
         status = decode(fname, NULL, 'C', status)
       WHERE rlh.dbinc_key = my_dbinc_key
       AND   rlh.thread#   = checkArchivedLog.thread#
       AND   rlh.sequence# = checkArchivedLog.sequence#
       AND   rlh.low_scn   = checkArchivedLog.low_scn;
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('checkArchivedLog - Inside dup_val_on_index exception');
--
--
      SELECT * INTO local
      FROM al
        WHERE al.dbinc_key = my_dbinc_key
        AND   (al.is_standby = checkArchivedLog.is_standby OR
                 (al.is_standby is NULL AND 
                  checkArchivedLog.is_standby is NULL))
        AND   al.al_recid = checkArchivedLog.al_recid
        AND   al.al_stamp = checkArchivedLog.al_stamp;
 
--
      IF client_site_aware AND this_site_key <> local.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (fname <> local.fname) THEN
        deb('checkArchivedLog - input fname ['||fname||']; local.fname ['|| 
            local.fname || ']');
        raise_application_error(-20080, 'Invalid archived log name');
       
      END IF;
  END;
 
END checkArchivedLog;
 
PROCEDURE endArchivedLogResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_al_recid = last_al_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_al_recid := last_al_recid;
  last_al_recid := NULL;
 
END endArchivedLogResync;
 
/*-------------------------*
 * Offline range resync    *
 *-------------------------*/
 
FUNCTION beginOfflineRangeResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_offr_recid INTO last_offr_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_offr_recid := sessionWaterMarks.high_offr_recid;
  END IF;
 
  RETURN last_offr_recid;
END beginOfflineRangeResync;
 
PROCEDURE checkOfflineRange(
  offr_recid     IN NUMBER
 ,offr_stamp     IN NUMBER
 ,file#          IN NUMBER
 ,create_scn     IN NUMBER
 ,offline_scn    IN NUMBER
 ,online_scn     IN NUMBER
 ,online_time    IN DATE
 ,cf_create_time IN DATE
 ,reset_scn      IN number default NULL
 ,reset_time     IN date default NULL
) IS
 
   local   offr%rowtype;
 
--
--
BEGIN
  IF (last_offr_recid IS NULL AND offr_recid IS NOT NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  deb('Checkofflinerange - '||
      ' recid: '||           nvl(to_char(offr_recid), 'NULL')||
      ' stamp: '||           nvl(to_char(offr_stamp), 'NULL')||
      ' file#: '||           file#||
      ' create_scn: '||      nvl(to_char(create_scn), 'NULL')||
      ' offline_scn: '||     offline_scn ||
      ' online_scn: '||      online_scn||
      ' online_time: '||     online_time||
      ' cf_create_time: '||  cf_create_time||
      ' reset_scn:'||        nvl(reset_scn, -1));
 
  last_offr_recid := offr_recid;
 
  IF (last_dbinc_key is NULL OR reset_scn IS NULL) THEN
     deb('checkOfflineRange - Init dbinc_key: '||this_dbinc_key);
     last_dbinc_key := this_dbinc_key;
     SELECT reset_scn, reset_time
       INTO last_reset_scn, last_reset_time
       FROM dbinc
      WHERE dbinc_key = this_dbinc_key;
  END IF;
 
  IF (reset_scn IS NOT NULL and reset_time IS NOT NULL) THEN
    IF (reset_scn <> last_reset_scn or reset_time <> last_reset_time) THEN
      BEGIN
         deb('checkOfflineRange - new incarnation detected'||
             ' reset_scn: '||      reset_scn||
             ' last_reset_scn: '|| last_reset_scn);
         SELECT dbinc_key
           INTO last_dbinc_key
           FROM dbinc
          WHERE reset_time = checkOfflineRange.reset_time
            AND reset_scn  = checkOfflineRange.reset_scn
            AND db_key = this_db_key;
         last_reset_scn  := reset_scn;
         last_reset_time := reset_time;
      EXCEPTION
        WHEN others THEN
          raise_application_error(-20070, 'Unknown Incarnation');
      END;
    END IF;
  END IF;
 
  deb('checkOfflineRange - dbinc_key is: '||last_dbinc_key);
 
  deb('checkOfflineRange - Looking if offline range record already '||
      'exists in OFFR');
  BEGIN
--
    SELECT distinct file#, create_scn, offline_scn,
           online_scn, online_time
      INTO local.file#, local.create_scn, local.offline_scn,
           local.online_scn, local.online_time
      FROM offr
      WHERE dbinc_key      = last_dbinc_key
        AND file#          = checkOfflineRange.file#
        AND create_scn     = checkOfflineRange.create_scn
        AND offline_scn    = checkOfflineRange.offline_scn;
 
    IF local.online_scn <> checkOfflineRange.online_scn THEN
      deb('checkOfflineRange - Online_scn OK?'||
          ' online_scn: '       || online_scn ||
          ' local.online_scn: ' || local.online_scn);
--
    END IF;
 
    IF local.online_time <> checkOfflineRange.online_time THEN
      deb('checkOfflineRange - Online_time OK?'||
          ' online_time: '       || online_time ||
          ' local.online_time: ' || local.online_time);
--
    END IF;
 
  EXCEPTION
    WHEN no_data_found THEN
      NULL; -- offline range record not yet known to catalog, go to insert
 
    WHEN too_many_rows THEN
      RAISE;      -- there must not be more then on offline range with same
--
    WHEN others THEN
      RAISE;
  END;
 
  BEGIN
    INSERT INTO
      offr(offr_key, dbinc_key, offr_recid, offr_stamp,
           file#, create_scn, offline_scn, online_scn,
           online_time, cf_create_time)
      VALUES(rman_seq.nextval, last_dbinc_key, offr_recid, nvl(offr_stamp,0),
             file#, create_scn, offline_scn, online_scn,
             online_time, cf_create_time);
    incResyncActions(RESYNC_ACTION_CHANGE, file#, to_char(NULL));
    deb('checkOfflineRange - Succesfully inserted new OFFR.');
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('checkOfflineRange - record already exists');
      IF offr_recid > 0 AND offr_stamp > 0 THEN
        deb('checkOfflineRange - update new offr_recid, offr_stamp, '||
            'online_scn and online_time');
        UPDATE OFFR
            SET offr_recid = checkOfflineRange.offr_recid,
                offr_stamp = checkOfflineRange.offr_stamp,
                online_scn = checkOfflineRange.online_scn,
                online_time= checkOfflineRange.online_time
          WHERE dbinc_key      = last_dbinc_key
            AND file#          = checkOfflineRange.file#
            AND create_scn     = checkOfflineRange.create_scn
            AND offline_scn    = checkOfflineRange.offline_scn
            AND cf_create_time = checkOfflineRange.cf_create_time;
        incResyncActions(RESYNC_ACTION_CHANGE, file#, to_char(NULL));
      END IF;
  END;
 
  deb('checkOfflineRange - exiting');
END;
 
PROCEDURE endOfflineRangeResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_offr_recid = last_offr_recid
     WHERE site_key = this_site_key;
  END IF;
 
--
  IF this_is_ors and this_ckp_type = 'FULL' THEN
     UPDATE watermarks SET high_offr_recid = last_offr_recid
     WHERE db_key = this_db_key;
  END IF;
 
  sessionWaterMarks.high_offr_recid := last_offr_recid;
  last_offr_recid := NULL;
 
END endOfflineRangeResync;
 
/*-------------------------*
 * Backup Set resync       *
 *-------------------------*/
 
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
--
PROCEDURE updateBackupSetRec(bs_key IN NUMBER) IS
  total_pieces     NUMBER;
  backup_validate  VARCHAR2(3);
  available_pieces NUMBER;
  new_status       VARCHAR2(1);
  bskeep           NUMBER;
  bstype           VARCHAR2(1);
  low              NUMBER := NULL;
  high             NUMBER := NULL;
  bs_site_key      NUMBER := NULL;
  not_purged       NUMBER := 0;
  pieces_on_msite  NUMBER;
  new_site_key     NUMBER;
  need_resync      NUMBER := 0;
  dbkey            NUMBER;
  incr_lvl         NUMBER;
  ra_node_count    NUMBER;
BEGIN
  deb('updateBackupSetRec, bs_key=' || bs_key);
--
--
  BEGIN
    SELECT pieces,input_file_scan_only, keep_options, bck_type, site_key,
           incr_level, db_key
    INTO total_pieces,backup_validate, bskeep, bstype, bs_site_key,
         incr_lvl, dbkey
    FROM bs
    WHERE bs.bs_key = updateBackupSetRec.bs_key;
  EXCEPTION
    WHEN no_data_found THEN
       new_status := 'D'; -- all pieces are deleted or not there
  END;
 
  IF nvl(backup_validate,'NO') <> 'YES' THEN
    SELECT max(count(DISTINCT piece#)) INTO available_pieces
    FROM  bp
    WHERE bp.bs_key = updateBackupSetRec.bs_key
    AND   bp.status = 'A'
    GROUP BY device_type;
 
    SELECT count(*) INTO not_purged
      FROM bp
     WHERE bp.bs_key = updateBackupSetRec.bs_key
       AND bp.purged = 'N'
       AND bp.ba_access IN ('L', 'D');
  END IF;
 
--
  IF bs_site_key IS NULL OR bs_site_key <> this_site_key THEN
    SELECT count(distinct nvl(site_key, 0)) INTO pieces_on_msite
      FROM bp
      WHERE bs_key = updateBackupSetRec.bs_key;
    IF pieces_on_msite = 1 THEN
       SELECT distinct site_key  INTO new_site_key
       FROM BP
       WHERE bs_key = updateBackupSetRec.bs_key;
    END IF;
--
    UPDATE bs SET site_key = new_site_key
    WHERE bs.bs_key = updateBackupSetRec.bs_key;
  END IF;
 
  deb('updateBackupSetRec, total_piece='||total_pieces||
      ', available_pieces='||available_pieces||
      ', not_purged='||not_purged);
  IF (total_pieces = 0 or backup_validate = 'YES') THEN
--
    new_status := 'D';
  ELSIF (available_pieces = total_pieces) THEN
    new_status := 'A';
  ELSE
    BEGIN
--
       SELECT 'O' INTO new_status FROM bp
        WHERE bp.bs_key = updateBackupSetRec.bs_key
          AND bp.status != 'D'
          AND rownum < 2;
    EXCEPTION WHEN no_data_found THEN
       new_status := 'D'; -- all pieces are deleted or not there
    END;
  END IF;
 
  deb('updateBackupSetRec, new_status='||new_status||',val='||backup_validate);
  IF (new_status in ('O', 'A') OR
      backup_validate = 'YES'  OR
      not_purged != 0) THEN
     UPDATE bs SET status = new_status
      WHERE bs.bs_key = updateBackupSetRec.bs_key;
  ELSE
--
     IF (bskeep > 0 and bstype = 'L') THEN
       SELECT min(low_scn), max(next_scn) INTO low, high
       FROM brl
       WHERE bs_key = updateBackupSetRec.bs_key;
     END IF;
     IF (bskeep > 0 and bstype = 'D') THEN
        SELECT min(ckp_scn) INTO low
        FROM bdf
        WHERE bs_key = updateBackupSetRec.bs_key;
     END IF;
    
--
--
--
     IF (this_is_ors
         AND incr_lvl IS NOT NULL 
         AND this_enable_populate_rsr = 1) THEN
       BEGIN 
--
--
         IF this_upstream_site_key IS NULL THEN
           SELECT site_key INTO this_upstream_site_key
           FROM   node
           WHERE  node.db_key = dbkey AND
                  database_role = 'RA' AND
                  db_unique_name like '$%$%' AND
                  db_unique_name not like '$%$%$%';
          END IF;
 
          deb('updateBackupSetRec, this_upstream_site_key = ' || 
                                   this_upstream_site_key);
--
--
          SELECT count(*) INTO need_resync
          FROM   bp
          WHERE  bs_key     =  updateBackupSetRec.bs_key
          AND    ((ba_access = 'L' AND vb_key IS NULL 
                   AND substr(handle,1,6) != 'RA_SBT')
--
--
                  OR (ba_access = 'D' AND rsr_key IS NULL))
--
--
--
          AND    site_key = this_upstream_site_key;
       EXCEPTION
          WHEN no_data_found THEN
            deb('updateBackupSetRec, node table has no RA rows with db_key '
                                                             || dbkey);
       END;
     END IF;
 
     IF need_resync = 0 THEN
--
--
       deb('updateBackupSetRec, deleting rows from BP and BS for bs_key=' || 
                                updateBackupSetRec.bs_key); 
       DELETE from bp WHERE bp.bs_key = updateBackupSetRec.bs_key;
       deb('updateBackupSetRec, deleted rows from bp =' || SQL%ROWCOUNT);
       DELETE FROM bs WHERE bs.bs_key = updateBackupSetRec.bs_key;
       deb('updateBackupSetRec, deleted rows from bs =' || SQL%ROWCOUNT);
     ELSE
       deb('updateBackupSetRec, Skipping deletion of bs_key = ' || bs_key ||
            '. Waiting till resync happens to delete this.');
     END IF;
 
--
     IF (low IS NOT NULL) THEN
       updateRestorePoint(low, high);
     END IF;
  END IF;
END updateBackupSetRec;
 
FUNCTION beginBackupSetResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_bs_recid INTO last_bs_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_bs_recid := sessionWaterMarks.high_bs_recid;
  END IF;
 
  RETURN last_bs_recid;
END beginBackupSetResync;
 
PROCEDURE checkBackupSet(
  bs_recid             IN NUMBER
 ,bs_stamp             IN NUMBER
 ,set_stamp            IN NUMBER
 ,set_count            IN NUMBER
 ,bck_type             IN VARCHAR2
 ,incr_level           IN NUMBER         DEFAULT NULL
 ,pieces               IN NUMBER
 ,start_time           IN DATE
 ,completion_time      IN DATE
 ,controlfile_included IN VARCHAR2       DEFAULT NULL
 ,input_file_scan_only IN VARCHAR2       DEFAULT NULL
 ,keep_options         IN NUMBER         DEFAULT 0
 ,keep_until           IN DATE           DEFAULT NULL
 ,block_size           IN NUMBER         DEFAULT NULL
 ,multi_section        IN VARCHAR2       DEFAULT NULL
 ,chk_last_recid       IN BOOLEAN        DEFAULT TRUE
 ,guid                 IN RAW            DEFAULT NULL
 ,dropped_pdb          IN BINARY_INTEGER DEFAULT 0
) IS
 
local      bs%rowtype;
newbskey   number;
l_pdb_key  number;
 
BEGIN
  IF (chk_last_recid) THEN
     IF (last_bs_recid IS NULL) THEN
       raise_application_error(-20037, 'Invalid last recid');
     END IF;
 
     IF (bs_recid < last_bs_recid) THEN
       raise_application_error(-20036, 'Invalid record order');
     END IF;
 
     IF (bs_recid > last_bs_recid + 1) THEN
--
       NULL;
     END IF;
     last_bs_recid := bs_recid;
  END IF;
 
  IF (bs_stamp > 0 and bs_stamp < kccdivts) THEN
     deb('checkBackupSet - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
  IF (bck_type NOT IN ('D','I','L') OR bck_type IS NULL) THEN
    raise_application_error(-20090, 'Invalid backup set type');
  END IF;
 
  IF (incr_level NOT IN (0,1,2,3,4) OR
      (bck_type NOT IN ('D','I') AND incr_level <> 0)) THEN
    raise_application_error(-20091, 'Invalid backup set level');
  END IF;
 
  l_pdb_key := guidToPdbKey(guid, dropped_pdb);
 
  BEGIN
--
--
    INSERT INTO bs
      (bs_key, db_key, bs_recid, bs_stamp, set_stamp, set_count,
       bck_type, incr_level, pieces, start_time, completion_time, status,
       controlfile_included, input_file_scan_only, keep_options, keep_until,
       block_size, site_key, multi_section, pdb_key)
    VALUES
      (rman_seq.nextval, this_db_key, bs_recid, bs_stamp, set_stamp, set_count,
       bck_type, incr_level, pieces, start_time, completion_time, 'D',
       decode(controlfile_included, 'SBY','STANDBY','YES','BACKUP','NONE'),
       input_file_scan_only, keep_options, keep_until, block_size, 
       this_site_key, decode(multi_section,'YES','Y',null), l_pdb_key)
    RETURNING bs_key INTO newbskey;
 
    cntbs := cntbs + 1;
    updatebs(cntbs) := newbskey;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('checkBackupSet - Inside dup_val_on_index exception');
--
      SELECT * INTO local
      FROM bs
      WHERE bs.db_key = this_db_key
        AND bs.set_stamp = checkBackupSet.set_stamp
        AND bs.set_count = checkBackupSet.set_count;
  
      duplicatebs(local.bs_key) := 1;
 
--
--
--
      IF (pieces > local.pieces) THEN
        UPDATE bs
           SET bs.pieces = checkBackupSet.pieces
        WHERE bs.db_key = this_db_key
        AND bs.bs_key = local.bs_key;
 
--
        cntbs:= cntbs + 1;
        updatebs(cntbs) := local.bs_key;
      END IF;
 
--
--
--
--
 
--
--
      IF local.site_key IS NULL AND cntbs > 0 AND
         updatebs(cntbs) <> local.bs_key THEN
         cntbs := cntbs + 1;
         updatebs(cntbs) := local.bs_key;
      END IF;
 
      IF (local.completion_time <> checkBackupSet.completion_time) THEN
        UPDATE bs
           SET completion_time = checkBackupSet.completion_time
         WHERE bs.db_key = this_db_key
           AND bs.bs_key = local.bs_key;
      END IF;
  END;
END checkBackupSet;
 
PROCEDURE endBackupSetResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
--
     UPDATE node SET high_bs_recid = last_bs_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_bs_recid := last_bs_recid;
  last_bs_recid := NULL;
 
END endBackupSetResync;
 
 /*-------------------------*
 * Backup piece resync     *
 *-------------------------*/
 
FUNCTION beginBackupPieceResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_bp_recid INTO last_bp_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_bp_recid := sessionWaterMarks.high_bp_recid;
  END IF;
 
  RETURN last_bp_recid;
END beginBackupPieceResync;
 
PROCEDURE deleteDuplicateBP(recid IN NUMBER,
                            stamp IN NUMBER,
                            bs_key IN NUMBER,
                            device_type IN VARCHAR2,
                            handle      IN VARCHAR2) IS
   ldevice_type bp.device_type%TYPE;
   lhandle      bp.device_type%TYPE;
BEGIN
 
   ldevice_type := device_type;
   lhandle      := handle;
 
   IF ldevice_type IS NULL OR lhandle IS NULL THEN
      BEGIN
         SELECT device_type, handle INTO ldevice_type, lhandle FROM BP
         WHERE bp.db_key   = this_db_key
           AND bp_recid    = recid
           AND bp_stamp    = stamp
           AND ((disk_backups_shared = TRUE# AND bp.device_type = 'DISK') OR
                (tape_backups_shared = TRUE# AND bp.device_type <> 'DISK') OR
                (this_site_key = nvl(bp.site_key, this_site_key)))
           AND deleteDuplicateBP.bs_key = bp.bs_key;
      EXCEPTION
         WHEN no_data_found THEN
            RETURN;
         WHEN too_many_rows THEN -- unique key is bs_key, recid, stamp
            RETURN;
      END;
   END IF;
 
   IF this_is_ors AND this_ckp_key IS NULL THEN 
      this_enable_populate_rsr := 
                          getValueFromConfig('_enable_populate_rsr_key');
   END IF;
--
--
--
--
   FOR bprec IN bpq(ldevice_type, lhandle, recid, stamp) LOOP
      UPDATE bp SET bp.status = 'D' WHERE bp.bp_key = bprec.bp_key;
      updateBackupSetRec(bprec.bs_key);        -- update the backupset status
   END LOOP;
--
--
   IF this_is_ors AND this_ckp_key IS NULL THEN 
      this_enable_populate_rsr := NULL;
      this_upstream_site_key := NULL;
   END IF;
END deleteDuplicateBP;
 
PROCEDURE checkBackupPiece(
  bp_recid                IN NUMBER
 ,bp_stamp                IN NUMBER
 ,set_stamp               IN NUMBER
 ,set_count               IN NUMBER
 ,piece#                  IN NUMBER
 ,tag                     IN VARCHAR2
 ,device_type             IN VARCHAR2
 ,handle                  IN VARCHAR2
 ,comments                IN VARCHAR2
 ,media                   IN VARCHAR2
 ,concur                  IN VARCHAR2
 ,start_time              IN DATE
 ,completion_time         IN DATE
 ,status                  IN VARCHAR2
 ,copy#                   IN NUMBER         default 1
 ,media_pool              IN NUMBER         default 0
 ,bytes                   IN NUMBER         default NULL
 ,is_recovery_dest_file   IN VARCHAR2       default 'NO'
 ,rsr_recid               IN NUMBER         default NULL
 ,rsr_stamp               IN NUMBER         default NULL
 ,compressed              IN VARCHAR2       default 'NO'
 ,encrypted               IN VARCHAR2       default 'NO'
 ,backed_by_osb           IN VARCHAR2       default 'NO'
 ,ba_access               IN VARCHAR2       default 'U'
 ,vbkey                   IN NUMBER         default NULL
 ,chk_last_recid          IN BOOLEAN        default TRUE
 ,lib_key                 IN NUMBER         default NULL
 ,guid                    IN RAW            default NULL
 ,template_key            IN NUMBER         default NULL
 ,dropped_pdb             IN BINARY_INTEGER default 0
) IS
   bp_key NUMBER;
BEGIN
   bp_key := checkBackupPiece(
                bp_recid               => bp_recid
               ,bp_stamp               => bp_stamp
               ,set_stamp              => set_stamp
               ,set_count              => set_count
               ,piece#                 => piece#
               ,tag                    => tag
               ,device_type            => device_type
               ,handle                 => handle
               ,comments               => comments
               ,media                  => media
               ,concur                 => concur
               ,start_time             => start_time
               ,completion_time        => completion_time
               ,status                 => status
               ,copy#                  => copy#
               ,media_pool             => media_pool
               ,bytes                  => bytes
               ,is_recovery_dest_file  => is_recovery_dest_file
               ,rsr_recid              => rsr_recid
               ,rsr_stamp              => rsr_stamp
               ,compressed             => compressed
               ,encrypted              => encrypted
               ,backed_by_osb          => backed_by_osb
               ,ba_access              => ba_access
               ,vbkey                  => vbkey
               ,chk_last_recid         => chk_last_recid
               ,lib_key                => lib_key
               ,guid                   => guid
               ,template_key           => template_key
               ,dropped_pdb            => dropped_pdb);
END checkBackupPiece;
 
FUNCTION checkBackupPiece(
  bp_recid                IN NUMBER
 ,bp_stamp                IN NUMBER
 ,set_stamp               IN NUMBER
 ,set_count               IN NUMBER
 ,piece#                  IN NUMBER
 ,tag                     IN VARCHAR2
 ,device_type             IN VARCHAR2
 ,handle                  IN VARCHAR2
 ,comments                IN VARCHAR2
 ,media                   IN VARCHAR2
 ,concur                  IN VARCHAR2
 ,start_time              IN DATE
 ,completion_time         IN DATE
 ,status                  IN VARCHAR2
 ,copy#                   IN NUMBER         default 1   -- No longer in use
 ,media_pool              IN NUMBER         default 0
 ,bytes                   IN NUMBER         default NULL
 ,is_recovery_dest_file   IN VARCHAR2       default 'NO'
 ,rsr_recid               IN NUMBER         default NULL
 ,rsr_stamp               IN NUMBER         default NULL
 ,compressed              IN VARCHAR2       default 'NO'
 ,encrypted               IN VARCHAR2       default 'NO'
 ,backed_by_osb           IN VARCHAR2       default 'NO'
 ,ba_access               IN VARCHAR2       default 'U'
 ,vbkey                   IN NUMBER         default NULL
 ,chk_last_recid          IN BOOLEAN        default TRUE
 ,lib_key                 IN NUMBER         default NULL
 ,guid                    IN RAW            default NULL
 ,template_key            IN NUMBER         default NULL
 ,dropped_pdb             IN BINARY_INTEGER default 0
) RETURN NUMBER IS
   localbs  bs%rowtype;
   localbp  bp%rowtype;
   localrsr rsr%rowtype;
   piece_exists BINARY_INTEGER := 0;
   l_copyno NUMBER := 1;
   l_update_bs_pieces BOOLEAN := FALSE;
   l_pdb_key NUMBER;
   l_bp_recid NUMBER;
BEGIN
 
  IF (chk_last_recid) THEN
     IF (last_bp_recid IS NULL) THEN
       raise_application_error(-20037, 'Invalid last recid');
     END IF;
 
     IF (bp_recid < last_bp_recid) THEN
       deb('checkBackupPiece - last_bp_recid=' || last_bp_recid);
       raise_application_error(-20036, 'Invalid record order');
     END IF;
 
     IF (bp_recid > last_bp_recid + 1) THEN
--
--
       NULL;
     END IF;
     last_bp_recid := bp_recid;
  END IF;
 
  IF (bp_stamp > 0 and bp_stamp < kccdivts) THEN
     deb('checkBackupPiece - ignoring record kccdivts='||kccdivts);
     RETURN NULL;               -- obsolete record from a backup controlfile
  END IF;
 
--
  IF handle IS NULL AND status != 'D' THEN
     deb('checkBackupPiece - handle is null, ignore this row');
     RETURN NULL;
  END IF;
 
--
--
--
--
--
--
--
--
--
  IF bp_recid = 0 OR this_wmrec.high_bp_recid >= 0 THEN
     this_high_bp_recid := this_high_bp_recid + 1;
     l_bp_recid := this_high_bp_recid;
     deb('checkBackupPiece - generating bp_recid=' || l_bp_recid ||
         ', ba_access=' || ba_access || ',handle=' || handle);
  ELSE
     l_bp_recid := bp_recid;
  END IF;
 
--
--
--
 
  l_pdb_key := guidToPdbKey(guid, dropped_pdb);
 
--
  BEGIN
    SELECT * into localbs from bs
      WHERE bs.db_key = this_db_key
        AND   bs.set_stamp = checkBackupPiece.set_stamp
        AND   bs.set_count = checkBackupPiece.set_count FOR UPDATE OF bs.bs_key;
    deb('checkBackupPiece - locked bs_key' || localbs.bs_key);
    
    IF this_db_unique_name = upper(this_server.wallet_alias) THEN
      deb('checkBackupPiece - doing reconcile is TRUE');
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
--
--
--
--
--
--
--
    END IF;
 
  EXCEPTION
    WHEN no_data_found THEN
      IF status != 'D' THEN
        INSERT INTO bs
          (bs_key, db_key, bs_recid, bs_stamp,
           set_stamp, set_count,
           bck_type, incr_level, pieces, start_time, completion_time, status,
           controlfile_included, site_key, multi_section, pdb_key)
        VALUES
--
--
--
--
--
--
--
--
          (rman_seq.nextval, this_db_key, 0, checkBackupPiece.set_stamp,
          checkBackupPiece.set_stamp, checkBackupPiece.set_count,
          NULL, NULL, checkBackupPiece.piece#,
          checkBackupPiece.start_time, checkBackupPiece.completion_time, 'O',
          'NONE', this_site_key, NULL, l_pdb_key)
        RETURNING bs_key INTO localbs.bs_key;
 
        cntbs := cntbs + 1;
        updatebs(cntbs) := localbs.bs_key;
      ELSE
--
         RETURN NULL;
      END IF;
  END;
 
--
--
  IF (status = 'D') THEN
     cntbs:= cntbs + 1;
     updatebs(cntbs) := localbs.bs_key;
     RETURN NULL;
  END IF;
 
--
  IF (localbs.bs_recid is null OR localbs.bs_recid = 0) AND
    checkBackupPiece.piece# > localbs.pieces THEN
    l_update_bs_pieces := TRUE;
  ELSE
--
--
--
--
    SELECT NVL(MAX(copy#), 0)+1 INTO l_copyno
      FROM bp
      WHERE piece# = checkBackupPiece.piece#
      AND bs_key = localbs.bs_key;
  END IF;
 
--
  BEGIN
      SELECT rsr_key
        INTO localrsr.rsr_key
        FROM rsr
       WHERE rsr.dbinc_key = this_dbinc_key
         AND (rsr.site_key = this_site_key OR
              rsr.site_key is null AND this_site_key is NULL)
         AND rsr.rsr_stamp = checkBackupPiece.rsr_stamp
         AND rsr.rsr_recid = checkBackupPiece.rsr_recid;
  EXCEPTION
    WHEN no_data_found THEN
--
      NULL;
  END;
 
  SELECT MAX(bp_key) INTO localbp.bp_key
    FROM bp
   WHERE bp.handle                       = checkBackupPiece.handle
     AND bp.device_type                  = checkBackupPiece.device_type
     AND bp.bs_key                       = localbs.bs_key
     AND ((disk_backups_shared = TRUE# AND bp.device_type = 'DISK') OR
          (tape_backups_shared = TRUE# AND bp.device_type <> 'DISK') OR
          (this_site_key = nvl(bp.site_key, this_site_key)));
 
  BEGIN
     IF (localbp.bp_key IS NOT NULL) THEN
        UPDATE bp
           SET bp_recid              = l_bp_recid,
               bp_stamp              = checkBackupPiece.bp_stamp,
               comments              = checkBackupPiece.comments,
               media                 = checkBackupPiece.media,
               media_pool            = checkBackupPiece.media_pool,
               concur                = decode(checkBackupPiece.concur,
                                              'YES', 'Y', 'N'),
               start_time            = checkBackupPiece.start_time,
               completion_time       = checkBackupPiece.completion_time,
               bytes                 = checkBackupPiece.bytes,
               is_recovery_dest_file = checkBackupPiece.is_recovery_dest_file,
               rsr_key               = localrsr.rsr_key,
               site_key              = this_site_key,
               backed_by_osb         = decode(checkBackupPiece.backed_by_osb,
                                              'YES', 'Y', 'N')
         WHERE bp_key = localbp.bp_key;
     ELSE
--
--
--
        IF (this_is_ors             AND -- in ORS schema?
            ba_access = 'U'         AND -- record pushed by rman resync?
            isOrsMedia(checkBackupPiece.media)) THEN
           deb('checkBackupPiece - ORS media backup piece - skipping');
           cntbs:= cntbs + 1;
           updatebs(cntbs) := localbs.bs_key;
           RETURN NULL;
        END IF;
        INSERT INTO bp
           (bp_key, bs_key, piece#, db_key, bp_recid, bp_stamp, tag,
            device_type, copy#, handle, handle_hashkey, comments, media,
            media_pool, concur, start_time, completion_time, status, bytes,
            is_recovery_dest_file, rsr_key, compressed, site_key, encrypted,
            backed_by_osb, ba_access, vb_key, lib_key, purged, pdb_key,
            template_key)
        VALUES
           (rman_seq.nextval, localbs.bs_key, checkBackupPiece.piece#,
            this_db_key, l_bp_recid, checkBackupPiece.bp_stamp,
            checkBackupPiece.tag, checkBackupPiece.device_type,
            l_copyno, checkBackupPiece.handle,
            substr(checkBackupPiece.device_type,1,10) ||
            substr(checkBackupPiece.handle,1,10)      ||
            substr(checkBackupPiece.handle,-10),
            checkBackupPiece.comments, checkBackupPiece.media,
            checkBackupPiece.media_pool,
            decode(checkBackupPiece.concur,'YES','Y','N'),
            checkBackupPiece.start_time,
            checkBackupPiece.completion_time,
            checkBackupPiece.status, checkBackupPiece.bytes,
            checkBackupPiece.is_recovery_dest_file, localrsr.rsr_key, 
            checkBackupPiece.compressed, this_site_key,
            decode(checkBackupPiece.encrypted, 'YES', 'Y', 'N'), 
            decode(checkBackupPiece.backed_by_osb, 'YES', 'Y', 'N'),
            checkBackupPiece.ba_access,
            checkBackupPiece.vbkey, checkBackupPiece.lib_key,
            decode(checkBackupPiece.ba_access, 'U', 'U', 'N'),
            l_pdb_key, checkBackupPiece.template_key)
         RETURNING bp_key INTO localbp.bp_key;
 
--
        IF this_is_ors THEN
          UPDATE sbt_template_db std
             SET std.last_bp_key = localbp.bp_key
           WHERE std.db_key = this_db_key
             AND std.last_bp_key > localbp.bp_key;
        END IF;
 
     END IF;
 
--
     IF this_is_ors AND getValueFromConfig('_enable_populate_rsr_key') = 1 THEN
        EXECUTE IMMEDIATE
         'BEGIN dbms_rai_populate_rsr_key(:1,:2); END;'
         USING localbp.bp_key, localbs.bs_key;
     END IF;
--
     deleteDuplicateBP(l_bp_recid, bp_stamp, localbs.bs_key,
                       device_type, handle);
 
--
--
     IF l_update_bs_pieces THEN
       UPDATE bs
          SET bs.pieces = checkBackupPiece.piece#
        WHERE bs.bs_key = localbs.bs_key
          AND bs.bck_type IS NULL;
     END IF;
 
--
     updateBackupSetRec(localbs.bs_key);
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('checkBackupPiece - Inside dup_val_on_index exception');
--
--
      SELECT * INTO localbp
      FROM bp
      WHERE bp.bs_key = localbs.bs_key
      AND   bp.bp_recid = l_bp_recid
      AND   bp.bp_stamp = checkBackupPiece.bp_stamp;
 
--
      IF client_site_aware AND this_site_key <> localbp.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (piece# <> localbp.piece#) THEN
        raise_application_error(-20093, 'Invalid piece#');
      END IF;
 
--
      IF localbp.site_key IS NULL THEN
         UPDATE bp SET site_key = this_site_key
         WHERE  bp.bs_key = localbs.bs_key
          AND   bp.bp_recid = l_bp_recid
          AND   bp.bp_stamp = checkBackupPiece.bp_stamp;
      END IF;
    WHEN OTHERS THEN
       deb('checkBackupPiece - inside exception hndle='||sqlerrm);
       RAISE;
  END;
 
  RETURN localbp.bp_key;
END checkBackupPiece;
 
PROCEDURE endBackupPieceResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     deb('endBackupPieceResync - last_bp_recid=' || last_bp_recid);
     UPDATE node SET high_bp_recid = last_bp_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_bp_recid := last_bp_recid;
  last_bp_recid := NULL;
 
END endBackupPieceResync;
 
/*-------------------------*
 * Backup Datafile resync  *
 *-------------------------*/
 
PROCEDURE addBackupControlFile(
  bs_key             IN NUMBER
 ,bcf_recid          IN NUMBER
 ,bcf_stamp          IN NUMBER
 ,dbinc_key          IN NUMBER
 ,ckp_scn            IN NUMBER
 ,ckp_time           IN DATE
 ,create_time        IN DATE
 ,min_offr_recid     IN NUMBER
 ,blocks             IN NUMBER
 ,block_size         IN NUMBER
 ,controlfile_type   IN VARCHAR2
 ,cfile_abck_year    IN number
 ,cfile_abck_mon_day IN number
 ,cfile_abck_seq     IN number
 ,pdb_key            IN NUMBER
) IS
 
local    bcf%rowtype;
l_conflict_count NUMBER := 0;
BEGIN
  BEGIN
    INSERT INTO bcf(bcf_key, bs_key, dbinc_key, bcf_recid, bcf_stamp,
                    ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
                    controlfile_type, blocks, autobackup_date,
                    autobackup_sequence, pdb_key)
    VALUES (rman_seq.nextval, bs_key, dbinc_key, bcf_recid, bcf_stamp,
            ckp_scn, ckp_time, create_time, min_offr_recid,block_size,
            controlfile_type, blocks,
            decode(cfile_abck_year, 0, to_date(NULL),
                   to_date(to_char(cfile_abck_year)||
                           lpad(to_char(cfile_abck_mon_day), 4, '0'),
                           'YYYYMMDD', 'NLS_CALENDAR=Gregorian')),
            cfile_abck_seq, pdb_key);
    IF (duplicatebs.exists(addBackupControlFile.bs_key)) THEN
       DECLARE
         CURSOR bcf_conflicts(bs_key_new IN NUMBER ) IS
           SELECT bs_key, bcf_key, dbinc_key, ckp_scn, ckp_time, block_size, 
                  controlfile_type, blocks, pdb_key 
             FROM bcf
            WHERE bcf.bs_key = bs_key_new;
         conflict_rec bcf_conflicts%rowtype;
       BEGIN
         FOR conflict_rec IN bcf_conflicts(addBackupControlFile.bs_key)
         LOOP
           deb('addBackupControlfile set stamp set count conflict rec- ' ||
                ' bs_key '           || to_char(conflict_rec.bs_key)     ||
                ' bcf_key '          || to_char(conflict_rec.bcf_key)    ||
                ' dbinc_key '        || to_char(conflict_rec.dbinc_key)  ||
                ' ckp_scn '          || to_char(conflict_rec.ckp_scn)    ||
                ' ckp_time '         || to_char(conflict_rec.ckp_time)   ||
                ' block_size '       || to_char(conflict_rec.block_size) ||
                ' controlfile_type ' || conflict_rec.controlfile_type    ||
                ' blocks '           || to_char(conflict_rec.blocks)     ||
                ' pdb_key '          || to_char(conflict_rec.pdb_key));
         END LOOP;
       END;
       raise_application_error(-20110, 'set stamp set count conflict');
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addBackupControlfile - Inside dup_val_on_index exception');
      
      SELECT count(*) INTO l_conflict_count 
        FROM bcf
       WHERE bcf.bs_key  =  addBackupControlFile.bs_key 
         AND (
                bcf.ckp_scn          <> addBackupControlFile.ckp_scn 
             OR bcf.ckp_time         <> addBackupControlFile.ckp_time
             OR bcf.block_size       <> addBackupControlFile.block_size
             OR bcf.controlfile_type <> addBackupControlFile.controlfile_type
             OR bcf.blocks           <> addBackupControlFile.blocks
             )
         AND ROWNUM       = 1 ;
      IF (l_conflict_count > 0) THEN
         DECLARE
           CURSOR bcf_conflicts(bs_key_new IN NUMBER, ckp_scn_new IN NUMBER, 
                                ckp_time_new IN DATE, block_size_new IN NUMBER,
                                controlfile_type_new IN VARCHAR2, 
                                blocks_new IN NUMBER)
           IS
             SELECT bs_key, bcf_key, dbinc_key, ckp_scn, ckp_time, block_size, 
                    controlfile_type, blocks, pdb_key
               FROM bcf
              WHERE bcf.bs_key = bs_key_new
                AND (
                       bcf.ckp_scn          <> ckp_scn_new
                    OR bcf.ckp_time         <> ckp_time_new
                    OR bcf.block_size       <> block_size_new
                    OR bcf.controlfile_type <> controlfile_type_new
                    OR bcf.blocks           <> blocks_new
                    );
         BEGIN
           FOR conflict_rec IN bcf_conflicts(addBackupControlFile.bs_key, 
                                   addBackupControlFile.ckp_scn,
                                   addBackupControlFile.ckp_time,
                                   addBackupControlFile.block_size,
                                   addBackupControlFile.controlfile_type,
                                   addBackupControlFile.blocks)
           LOOP
             deb('addBackupControlfile set stamp set count existing rec- ' ||
                 ' bs_key '           || to_char(conflict_rec.bs_key)      ||
                 ' bcf_key '          || to_char(conflict_rec.bcf_key)     ||
                 ' dbinc_key '        || to_char(conflict_rec.dbinc_key)   ||
                 ' ckp_scn '          || to_char(conflict_rec.ckp_scn)     ||
                 ' ckp_time '         || to_char(conflict_rec.ckp_time)    ||
                 ' block_size '       || to_char(conflict_rec.block_size)  ||
                 ' controlfile_type ' || conflict_rec.controlfile_type     ||
                 ' blocks '           || to_char(conflict_rec.blocks)      ||
                 ' pdb_key '          || to_char(conflict_rec.pdb_key));
           END LOOP;
           deb('addBackupControlfile set stamp set count new conflict rec- ' ||
               ' bs_key '      || to_char(addBackupControlfile.bs_key)     ||
               ' dbinc_key '   || to_char(addBackupControlfile.dbinc_key)  ||
               ' ckp_scn '     || to_char(addBackupControlfile.ckp_scn)    ||
               ' ckp_time '    || to_char(addBackupControlfile.ckp_time)   ||
               ' block_size '  || to_char(addBackupControlfile.block_size) ||
               ' controlfile_type ' || addBackupControlfile.controlfile_type ||
               ' blocks '      || to_char(addBackupControlfile.blocks)     ||
               ' pdb_key '     || to_char(addBackupControlfile.pdb_key));
         END;
         raise_application_error(-20110, 'set stamp set count conflict');
      END IF;
 
--
--
      SELECT ckp_scn, ckp_time, bcf_recid, bcf_stamp INTO 
             local.ckp_scn, local.ckp_time, local.bcf_recid, local.bcf_stamp
      FROM bcf
      WHERE bcf.bs_key = addBackupControlFile.bs_key;
 
--
      IF (ckp_scn <> local.ckp_scn or ckp_time <> local.ckp_time) THEN
        deb('addBackupControlfile - ckp_scn '||ckp_scn||' ckp_time '||
            to_char(ckp_time));
        deb('addBackupControlfile - lckp_scn '||local.ckp_scn||' lckp_time '||
            to_char(local.ckp_time));
        raise_application_error(-20095, 'Invalid ckp_scn or ckp_time');
      END IF;
 
--
      IF local.bcf_recid <> bcf_recid or local.bcf_stamp <> bcf_stamp THEN
         UPDATE bcf set bcf_recid = addBackupControlFile.bcf_recid,
                        bcf_stamp = addBackupControlFile.bcf_stamp
         WHERE bcf.bs_key = addBackupControlFile.bs_key;
      END IF;
  END;
END addBackupControlFile;
 
PROCEDURE addBackupDataFile(
  bs_key          IN NUMBER
 ,bdf_recid       IN NUMBER
 ,bdf_stamp       IN NUMBER
 ,file#           IN NUMBER
 ,create_scn      IN NUMBER
 ,dbinc_key       IN NUMBER
 ,incr_level      IN NUMBER
 ,incr_scn        IN NUMBER
 ,ckp_scn         IN NUMBER
 ,ckp_time        IN DATE
 ,abs_fuzzy_scn   IN NUMBER
 ,datafile_blocks IN NUMBER
 ,blocks          IN NUMBER
 ,block_size      IN NUMBER
 ,completion_time IN DATE
 ,blocks_read     IN NUMBER
 ,create_time     IN DATE
 ,marked_corrupt  IN NUMBER
 ,used_chg_track  IN VARCHAR2
 ,used_optim      IN VARCHAR2
 ,foreign_dbid      IN NUMBER
 ,plugged_readonly  IN VARCHAR2
 ,plugin_scn        IN NUMBER
 ,plugin_reset_scn  IN NUMBER
 ,plugin_reset_time IN DATE
 ,section_size      IN NUMBER
 ,pdb_key           IN NUMBER
 ,sparse_backup     IN VARCHAR2
 ,isReSync          IN BOOLEAN
 ,isVirtual         IN BOOLEAN
) IS
 
local            bdf%rowtype;
l_conflict_count NUMBER          := 0;
l_UB4MAXVAL      CONSTANT NUMBER := 4294967295;
 
BEGIN
 
  deb('addBackupDataFile - bs_key ' || bs_key || '  file# ' || file#);
 
  BEGIN
    INSERT INTO bdf(bdf_key, dbinc_key, bdf_recid, bdf_stamp, bs_key,
       file#, create_scn, incr_level, incr_scn,
       ckp_scn, ckp_time, abs_fuzzy_scn, datafile_blocks, blocks, block_size,
       completion_time, blocks_read, create_time, marked_corrupt,
       used_chg_track, used_optim, foreign_dbid, plugged_readonly,
       plugin_scn, plugin_reset_scn, plugin_reset_time, section_size,
       pdb_key, sparse_backup)
    VALUES
      (rman_seq.nextval, dbinc_key, bdf_recid, bdf_stamp, bs_key,
       file#, create_scn, incr_level, incr_scn,
       ckp_scn, ckp_time, abs_fuzzy_scn, datafile_blocks, blocks, block_size,
       completion_time, nvl(blocks_read, datafile_blocks), create_time, 
       marked_corrupt, decode(used_chg_track, 'YES', 'Y', 'N'),
       decode(used_optim, 'YES', 'Y', 'N'), foreign_dbid, plugged_readonly,
       plugin_scn, plugin_reset_scn, plugin_reset_time, section_size,
       pdb_key, sparse_backup);
    IF (duplicatebs.exists(addBackupDataFile.bs_key)) THEN
       DECLARE
         CURSOR bdf_conflicts(bs_key_new IN NUMBER) IS
           SELECT bs_key, bdf_key, dbinc_key, file#, bdf_recid, bdf_stamp,
                  ckp_scn, ckp_time, datafile_blocks, block_size, plugin_scn, 
                  section_size
             FROM bdf
            WHERE bdf.bs_key = bs_key_new;
         conflict_rec bdf_conflicts%rowtype;
       BEGIN
         FOR conflict_rec IN bdf_conflicts(addBackupDatafile.bs_key)
         LOOP
           deb('addBackupDatafile set stamp set count conflict - '         ||
               ' bs_key '          || to_char(conflict_rec.bs_key)          ||
               ' bdf_key '         || to_char(conflict_rec.bdf_key)         ||
               ' dbinc_key '       || to_char(conflict_rec.dbinc_key)       ||
               ' file# '           || to_char(conflict_rec.file#)           ||
               ' bdf_recid '       || to_char(conflict_rec.bdf_recid)       ||
               ' bdf_stamp '       || to_char(conflict_rec.bdf_stamp)       ||
               ' ckp_scn '         || to_char(conflict_rec.ckp_scn)         ||
               ' ckp_time '        || to_char(conflict_rec.ckp_time)        ||
               ' datafile_blocks ' || to_char(conflict_rec.datafile_blocks) ||
               ' block_size '      || to_char(conflict_rec.block_size)      ||
               ' plugin_scn '      || to_char(conflict_rec.plugin_scn)      ||
               ' section_size '    || to_char(conflict_rec.section_size));
           END LOOP;
         END;
       raise_application_error(-20110, 'set stamp set count conflict');
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addBackupDataFile - Inside dup_val_on_index exception');
 
      SELECT count(*) INTO l_conflict_count 
        FROM bdf
       WHERE bdf.bs_key = addBackupDataFile.bs_key 
         AND bdf.file#  = addBackupDataFile.file# 
         AND (
                bdf.ckp_scn         <> addBackupDataFile.ckp_scn 
             OR bdf.ckp_time        <> addBackupDataFile.ckp_time
             OR bdf.datafile_blocks <> addBackupDataFile.datafile_blocks
             OR bdf.block_size      <> addBackupDataFile.block_size
             OR bdf.plugin_scn      <> addBackupDataFile.plugin_scn
             ) 
         AND ROWNUM    = 1;
              
      
      deb('addBackupDataFile - l_conflict_count: '|| l_conflict_count);
      deb('addBackupDataFile - sect size: '|| addBackupDataFile.section_size);
  
      IF (l_conflict_count > 0 AND addBackupDataFile.section_size = 0 ) THEN
         DECLARE
           CURSOR bdf_conflicts(bs_key_new IN NUMBER, file_new IN NUMBER, 
                                ckp_scn_new IN NUMBER, ckp_time_new IN DATE, 
                                datafile_blocks_new IN NUMBER, 
                                block_size_new IN NUMBER, plugin_scn_new IN NUMBER) 
           IS
             SELECT bs_key, bdf_key, dbinc_key, file#, bdf_recid, bdf_stamp,
                    ckp_scn, ckp_time, datafile_blocks, block_size, plugin_scn,
                    section_size 
               FROM bdf
              WHERE bdf.bs_key = bs_key_new
                AND bdf.file#  = file_new
                AND (
                       bdf.ckp_scn         <> ckp_scn_new
                    OR bdf.ckp_time        <> ckp_time_new
                    OR bdf.datafile_blocks <> datafile_blocks_new
                    OR bdf.block_size      <> block_size_new
                    OR bdf.plugin_scn      <> plugin_scn_new
                    );
           conflict_rec bdf_conflicts%rowtype;
         BEGIN
           FOR conflict_rec IN bdf_conflicts(addBackupDatafile.bs_key,
                                   addBackupDatafile.file#, 
                                   addBackupDatafile.ckp_scn, 
                                   addBackupDatafile.ckp_time, 
                                   addBackupDatafile.datafile_blocks, 
                                   addBackupDatafile.block_size, 
                                   addBackupDatafile.plugin_scn)
           LOOP
             deb('addBackupDatafile set stamp set count existing rec-'       ||
                 ' bs_key '         || to_char(conflict_rec.bs_key)          ||
                 ' bdf_key '        || to_char(conflict_rec.bdf_key)         ||
                 ' dbinc_key '      || to_char(conflict_rec.dbinc_key)       ||
                 ' file# '          || to_char(conflict_rec.file#)           ||
                 ' bdf_recid '      || to_char(conflict_rec.bdf_recid)       ||
                 ' bdf_stamp '      || to_char(conflict_rec.bdf_stamp)       ||
                 ' ckp_scn '        || to_char(conflict_rec.ckp_scn)         ||
                 ' ckp_time '       || to_char(conflict_rec.ckp_time)        ||
                 ' datafile_blocks ' || 
                                       to_char(conflict_rec.datafile_blocks) ||
                 ' block_size '     || to_char(conflict_rec.block_size)      ||
                 ' plugin_scn '     || to_char(conflict_rec.plugin_scn)      ||
                 ' section_size '   || to_char(conflict_rec.section_size));
             END LOOP;
             deb('addBackupDatafile set stamp set count new conflict rec- '  ||
                 ' bs_key '         || to_char(addBackupDataFile.bs_key)     ||
                 ' dbinc_key'       || to_char(addBackupDataFile.dbinc_key)  ||
                 ' file# '          || to_char(addBackupDataFile.file#)      ||
                 ' bdf_recid '      || to_char(addBackupDataFile.bdf_recid)  ||
                 ' bdf_stamp '      || to_char(addBackupDataFile.bdf_stamp)  ||
                 ' ckp_scn '        || to_char(addBackupDataFile.ckp_scn)    ||
                 ' ckp_time '        
                                    || to_char(addBackupDataFile.ckp_time)   ||
                 ' datafile_blocks ' || 
                                  to_char(addBackupDataFile.datafile_blocks) ||
                 ' block_size '     || to_char(addBackupDataFile.block_size) ||
                 ' plugin_scn '     || to_char(addBackupDataFile.plugin_scn) ||
                 ' section_size '  || to_char(addBackupDataFile.section_size));
           END;
         raise_application_error(-20110, 'set stamp set count conflict');
      END IF;
 
--
--
      SELECT dbinc_key, create_scn, bdf_recid, bdf_stamp, plugin_scn, 
             completion_time, abs_fuzzy_scn, blocks,
             nvl(blocks_read, datafile_blocks), 
             ckp_scn, ckp_time,
             decode(used_optim, 'YES', 'Y', 'N'),
             decode(used_chg_track, 'YES', 'Y', 'N'),
             nvl(marked_corrupt, 0)
        INTO local.dbinc_key, local.create_scn,local.bdf_recid,
             local.bdf_stamp, local.plugin_scn, local.completion_time,
             local.abs_fuzzy_scn, local.blocks, local.blocks_read, 
             local.ckp_scn, local.ckp_time, local.used_optim,
             local.used_chg_track, local.marked_corrupt
      FROM bdf
      WHERE bdf.bs_key = addBackupDataFile.bs_key
        AND bdf.file#  = addBackupDataFile.file#;
 
--
      deb('addBackupDataFile - dbinc_key:        '||to_char(dbinc_key));
      deb('addBackupDataFile - local.dbinc_key: '|| to_char(local.dbinc_key) ||
          ' local.completion_time: '|| to_char(local.completion_time) ||
          ' local.abs_fuzzy_scn: '|| to_char(local.abs_fuzzy_scn) ||
          ' local.blocks_read: '|| to_char(local.blocks_read) ||
          ' local.marked_corrupt: '|| to_char(local.marked_corrupt));
      IF (dbinc_key <> local.dbinc_key) THEN
        raise_application_error(-20096, 'Invalid dbinc_key');
      END IF;
      IF (create_scn <> local.create_scn AND
          plugin_scn <> local.plugin_scn) THEN
        raise_application_error(-20097, 'Invalid create scn');
      END IF;
 
--
      IF bdf_recid <> local.bdf_recid or bdf_stamp <> local.bdf_stamp THEN
         UPDATE bdf set bdf_recid = addBackupDataFile.bdf_recid,
                        bdf_stamp = addBackupDataFile.bdf_stamp
         WHERE bdf.bs_key = addBackupDataFile.bs_key;
      END IF;
 
      IF (local.completion_time <> addBackupDataFile.completion_time) THEN
        UPDATE bdf
          SET completion_time = addBackupDataFile.completion_time
          WHERE bdf.bs_key = addBackupDataFile.bs_key
            AND bdf.file#  = addBackupDataFile.file#;
      END IF;
 
      /*
       * Following are the scenarios that traverse this code path:
       *
       * 1. Resync: resync can happen for both normal msection
       *    backups and virtual msection backups. If a resync
       *    happens during processing of these backups, like say
       *    there are 5 section pieces, and the resync happens
       *    once after the 3rd piece, and once after all 5 pieces,
       *    then the data stored in blocks_read, blocks and marked_corrupt
       *    may be a little more than it needs to be. However,
       *    ckp_scn and abs_fuzzy_scn would be fine as we are taking
       *    the minimum and maximum of each respectively.
       *    In the case of the other parameters, we need to accumulate
       *    their values across sections and there is no easy way
       *    to achieve that.
       * 2. Prior to 12.2, parameters blocks_read, blocks and marked_corrupt
       *    are at value '0'. However, there is a fix in progress to 
       *    capture the actual values. Once that lands, the scenario
       *    listed in #1 will further manifest itself, with larger than
       *    possible values for blocks, blocks_read and marked_corrupt
       * 3. We need to limit the calculations for copy#1, that way copy to tape
       *    or additional copies created on the backup command using copies
       *    does not further throw off the values on blocks_read, blocks
       *    or marked_corrupt. However, there is no easy way to determine
       *    that at this point in code. Copy2tape currently sets a value
       *    of zero only.
       *
       *  For now, we will take minimum of checkpoint scn's and maximum of 
       *  absolute fuzzy scn's. And only in the case of msection case,
       *  add the values of blocks, blocks_read and marked_corrupt 
       */
 
--
--
      IF (addBackupDataFile.ckp_scn  < local.ckp_scn) THEN
        UPDATE bdf 
          SET ckp_scn  = addBackupDataFile.ckp_scn,
              ckp_time = addBackupDataFile.ckp_time
          WHERE bdf.bs_key = addBackupDataFile.bs_key
            AND bdf.file#  = addBackupDataFile.file#;
 
         deb('addBackupDataFile - updated bdf ckp_scn: '||
             to_char(addBackupDataFile.ckp_scn) || ' local bdf ckp_scn:' ||
             to_char(local.ckp_scn));
      END IF;
 
      /*
       * This applies to regular backups as well but more so for multi-section 
       * backup.
       * Lets assume that there are two sections  (ckp_scn, abs_scn) notation.
       *
       * Section 1: (100, 101)
       * Section 2: (200, 0)  this is not fuzzy. however there can be blocks 
       *           at 150, 160..
       * IF you were to perform a point in time recovery scenario to 150 and 
       * if you take the minimum of the checkpointscns and the maximum of the 
       * absolute fuzzy then the restored datafile will be (100, 101). 
       * This is wrong as recovery will not be able to unfuzzy the block at 
       * scn 150, 160.. as it cannot roll backwards.
       *
       * The correct way of addressing this is to update the bdf with the 
       * appropriate fuzziness by also taking into fact the checkpoint of the 
       * file
       * This is done in restore logic as below (krbr.c:krbr3b2)
       *          f->curfuzz_krbrf =
       *          (*KSCNMAX(f->pkcvfh_krbrf->kcvfhafs,
       *                    KSCNMAX(f->pkcvfh_krbrf->kcvfhrfs,
       *                            f->pkcvfh_krbrf->kcvfhckp.kcvcpscn)));
       *
       * The same is being done below.
       */
 
--
      IF (addBackupDataFile.abs_fuzzy_scn > local.abs_fuzzy_scn) THEN
        UPDATE bdf
          SET abs_fuzzy_scn = addBackupDataFile.abs_fuzzy_scn
          WHERE bdf.bs_key  = addBackupDataFile.bs_key
            AND bdf.file#   = addBackupDataFile.file#;
 
        deb('addBackupDataFile - updated bdf abs_fuzzy_scn: '||
            to_char(addBackupDataFile.abs_fuzzy_scn) || ' 
            local bdf abs_fuzzy_scn:' ||
            to_char(local.abs_fuzzy_scn));
 
      ELSIF (addBackupDataFile.section_size <> 0 AND
             local.abs_fuzzy_scn < addBackupDataFile.ckp_scn) THEN
        
        UPDATE bdf
          SET abs_fuzzy_scn = addBackupDataFile.ckp_scn
          WHERE bdf.bs_key  = addBackupDataFile.bs_key
            AND bdf.file#   = addBackupDataFile.file#;
 
        deb('addBackupDataFile - updated bdf abs_fuzzy_scn to ckp_scn: '||
            to_char(addBackupDataFile.ckp_scn) || ' 
            local bdf abs_fuzzy_scn:' ||
            to_char(local.abs_fuzzy_scn));
 
      END IF;
 
      IF addBackupDataFile.section_size <> 0 AND this_is_ors THEN
 
        deb ('Multi-section on ORS');
--
--
--
--
--
--
        IF (isReSync AND NOT isVirtual) THEN
 
          deb('addBackupDataFile - isResync:TRUE isVirtual:FALSE'||
              to_char(local.blocks_read) || ' ' || 
              to_char(addBackupDataFile.blocks_read));
               
          deb('addBackupDataFile - isResync:TRUE isVirtual:FALSE'||
              to_char(local.blocks) || ' ' || 
              to_char(addBackupDataFile.blocks));
 
          UPDATE bdf
            SET blocks_read    = addBackupDataFile.blocks_read,
                blocks         = addBackupDataFile.blocks,
                marked_corrupt = addBackupDataFile.marked_corrupt
           WHERE bdf.bs_key = addBackupDataFile.bs_key
             AND bdf.file#  = addBackupDataFile.file#
             AND (local.blocks_read    <> addBackupDataFile.blocks_read OR
                  local.blocks         <> addBackupDataFile.blocks      OR
                  local.marked_corrupt <> addBackupDataFile.marked_corrupt);
 
        ELSIF (NOT isReSync AND NOT isVirtual) THEN
--
--
--
--
 
          deb('addBackupDataFile - isResync:FALSE isVirtual:FALSE'||
              to_char(local.blocks_read) || ' ' || 
              to_char(addBackupDataFile.blocks_read));
               
          deb('addBackupDataFile - isResync:FALSE isVirtual:FALSE'||
              to_char(local.blocks) || ' ' || 
              to_char(addBackupDataFile.blocks));
          UPDATE bdf
            SET blocks_read    = CASE WHEN 
                                   local.blocks_read = 0
                                     THEN 
                                       addBackupDataFile.blocks_read 
                                     ELSE 
                                       local.blocks_read 
                                 END,
                blocks         = CASE WHEN 
                                   local.blocks = 0
                                     THEN 
                                       addBackupDataFile.blocks 
                                     ELSE 
                                       local.blocks
                                 END,
                marked_corrupt = CASE WHEN 
                                   local.marked_corrupt = 0
                                     THEN 
                                       addBackupDataFile.marked_corrupt 
                                     ELSE 
                                       local.marked_corrupt 
                                 END
           WHERE bdf.bs_key = addBackupDataFile.bs_key
             AND bdf.file#  = addBackupDataFile.file#
             AND (local.blocks_read = 0 OR
                  local.blocks = 0 OR
                  local.marked_corrupt = 0);
 
        ELSIF (NOT isReSync AND isVirtual) THEN
--
--
--
          deb('addBackupDataFile - isResync:FALSE isVirtual:TRUE'||
              to_char(local.blocks_read) || ' ' || 
              to_char(addBackupDataFile.blocks_read));
               
          deb('addBackupDataFile - isResync:FALSE isVirtual:TRUE'||
              to_char(local.blocks) || ' ' || 
              to_char(addBackupDataFile.blocks));
--
          local.marked_corrupt := local.marked_corrupt + 
                                  addBackupDataFile.marked_corrupt;
 
--
          local.blocks_read := local.blocks_read + 
                               addBackupDataFile.blocks_read;
          local.blocks      := local.blocks + 
                               addBackupDataFile.blocks;
 
          local.blocks := LEAST(local.blocks, 
                                addBackupDataFile.datafile_blocks);
          local.blocks_read := LEAST(local.blocks_read, 
                                     addBackupDataFile.datafile_blocks);
 
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
          IF (local.blocks > l_UB4MAXVAL) THEN
--
             deb('addBackupDataFile - blocks overflow'||
                 to_char(local.blocks) || ' ' || 
                 to_char(addBackupDataFile.blocks));
             
             local.blocks := l_UB4MAXVAL;
             
          END IF;
 
          IF (local.blocks_read > l_UB4MAXVAL) THEN
--
             deb('addBackupDataFile - blocks_read overflow'||
                 to_char(local.blocks_read) || ' ' || 
                 to_char(addBackupDataFile.blocks_read));
             
             local.blocks_read := l_UB4MAXVAL;
             
          END IF;
 
          UPDATE bdf
            SET marked_corrupt = local.marked_corrupt,
                blocks_read    = local.blocks_read, 
                blocks         = local.blocks
           WHERE bdf.bs_key = addBackupDataFile.bs_key
             AND bdf.file#  = addBackupDataFile.file#;
        END IF;
 
      UPDATE bdf
        SET used_chg_track = CASE WHEN 
                               (local.used_chg_track = 'Y' OR 
                                addBackupDataFile.used_chg_track = 'YES')
                                  THEN 
                                    'Y'
                                  ELSE 
                                    'N'
                             END,
            used_optim =    CASE WHEN 
                              (local.used_optim = 'Y' OR
                               addBackupDataFile.used_optim = 'YES')
                                 THEN 
                                   'Y'
                                 ELSE 
                                   'N'
                            END
       WHERE bdf.bs_key = addBackupDataFile.bs_key
         AND bdf.file#  = addBackupDataFile.file#
         AND (local.used_chg_track <> 
                decode(addBackupDataFile.used_chg_track, 'YES', 'Y', 'N') OR
              local.used_optim     <> 
                decode(addBackupDataFile.used_optim, 'YES', 'Y', 'N'));
 
    ELSE
--
      deb('addBackupDataFile - Regular RMAN catalog');
 
      IF (local.completion_time <> addBackupDataFile.completion_time) THEN
        UPDATE bdf
          SET blocks_read    = addBackupDataFile.blocks_read,
              marked_corrupt = addBackupDataFile.marked_corrupt
         WHERE bdf.bs_key = addBackupDataFile.bs_key
           AND bdf.file#  = addBackupDataFile.file#;
      END IF;
    END IF;
  END;
END addBackupDataFile;
 
FUNCTION beginBackupDataFileResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_bdf_recid INTO last_bdf_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_bdf_recid := sessionWaterMarks.high_bdf_recid;
  END IF;
 
  deb('beginBackupDataFileResync returning' || last_bdf_recid);
  RETURN last_bdf_recid;
END beginBackupDataFileResync;
 
 
PROCEDURE checkBackupDataFile(
  bdf_recid       IN NUMBER
 ,bdf_stamp       IN NUMBER
 ,set_stamp       IN NUMBER
 ,set_count       IN NUMBER
 ,file#           IN NUMBER
 ,create_scn      IN NUMBER
 ,create_time     IN DATE
 ,reset_scn       IN NUMBER
 ,reset_time      IN DATE
 ,incr_level      IN NUMBER
 ,incr_scn        IN NUMBER
 ,ckp_scn         IN NUMBER
 ,ckp_time        IN DATE
 ,abs_fuzzy_scn   IN NUMBER
 ,datafile_blocks IN NUMBER
 ,blocks          IN NUMBER
 ,block_size      IN NUMBER
 ,min_offr_recid  IN NUMBER
 ,completion_time IN DATE
 ,controlfile_type
                    IN VARCHAR2       DEFAULT NULL
 ,cfile_abck_year   IN NUMBER         DEFAULT NULL -- contains marked_corrupt
 ,cfile_abck_mon_day
                    IN NUMBER         DEFAULT NULL -- contains media_corrupt
 ,cfile_abck_seq    IN NUMBER         DEFAULT NULL -- contains logical_corrupt
 ,chk_last_recid    IN BOOLEAN        DEFAULT TRUE
 ,blocks_read       IN NUMBER         DEFAULT NULL
 ,used_chg_track    IN VARCHAR2       DEFAULT 'NO'
 ,used_optim        IN VARCHAR2       DEFAULT 'NO'
 ,foreign_dbid      IN NUMBER         DEFAULT 0
 ,plugged_readonly  IN VARCHAR2       DEFAULT 'NO'
 ,plugin_scn        IN NUMBER         DEFAULT 0
 ,plugin_reset_scn  IN NUMBER         DEFAULT 0
 ,plugin_reset_time IN DATE           DEFAULT NULL
 ,section_size      IN NUMBER         DEFAULT NULL
 ,guid              IN RAW            DEFAULT NULL
 ,sparse_backup     IN VARCHAR2       DEFAULT 'NO'
 ,isReSync          IN BOOLEAN        DEFAULT TRUE
 ,isVirtual         IN BOOLEAN        DEFAULT FALSE
 ,dropped_pdb       IN BINARY_INTEGER DEFAULT 0
) IS
 
bs_key    NUMBER;
dbinc_key NUMBER;
l_pdb_key NUMBER;
 
BEGIN
 
--
 IF chk_last_recid THEN
    IF (last_bdf_recid IS NULL) THEN
       raise_application_error(-20037, 'Invalid last recid');
    END IF;
 
    IF (bdf_recid < last_bdf_recid) THEN
       raise_application_error(-20036, 'Invalid record order');
    END IF;
 
    IF (bdf_recid > last_bdf_recid + 1) THEN
--
       NULL;
    END IF;
    last_bdf_recid := bdf_recid;
  END IF;
 
--
  BEGIN
    SELECT bs_key INTO bs_key
    FROM bs
    WHERE bs.db_key = this_db_key
    AND   bs.set_stamp = checkBackupDataFile.set_stamp
    AND   bs.set_count = checkBackupDataFile.set_count FOR UPDATE OF bs.bs_key;
    deb('checkBackupDataFile - locked bs_key' || bs_key);
  EXCEPTION
    WHEN no_data_found THEN
--
--
--
--
--
--
--
       return;
  END;
 
  BEGIN
--
--
    IF (checkBackupDatafile.incr_level > 0) THEN
      UPDATE bs SET bs.incr_level = checkBackupDataFile.incr_level,
                    bs.bck_type = 'I'
      WHERE bs.bs_key = checkBackupDataFile.bs_key
      AND   bs.bck_type IS NULL;
    ELSE
      UPDATE bs SET bs.incr_level = checkBackupDataFile.incr_level,
                    bs.bck_type = 'D'
      WHERE bs.bs_key = checkBackupDataFile.bs_key
      AND   bs.bck_type IS NULL;
    END IF;
    IF (file# = 0 and controlfile_type is not null) then
      UPDATE bs SET bs.controlfile_included=
                 decode(checkBackupDatafile.controlfile_type,'B','BACKUP',
                                                             'S','STANDBY',
                                                             'NONE')
      WHERE bs.bs_key = checkBackupDataFile.bs_key
      AND   bs.controlfile_included = 'NONE';
    END IF;
  END;
 
--
  dbinc_key := checkIncarnation(reset_scn, reset_time);
 
  l_pdb_key := guidToPdbKey(guid, dropped_pdb);
 
  IF (file# = 0) THEN
    addBackupControlFile(bs_key, bdf_recid, bdf_stamp, dbinc_key,
         ckp_scn, ckp_time, create_time, min_offr_recid, blocks, block_size,
         controlfile_type, cfile_abck_year, cfile_abck_mon_day,
         cfile_abck_seq, l_pdb_key);
  ELSE
    addBackupDataFile(bs_key, bdf_recid, bdf_stamp, file#, create_scn,
         dbinc_key, incr_level, incr_scn, ckp_scn, ckp_time,
         abs_fuzzy_scn, datafile_blocks, blocks, block_size, completion_time,
         blocks_read, create_time, cfile_abck_year, used_chg_track, 
         used_optim, foreign_dbid, plugged_readonly, plugin_scn,
         plugin_reset_scn, plugin_reset_time, section_size, l_pdb_key,
         sparse_backup, isReSync, isVirtual);
  END IF;
 
END checkBackupDataFile;
 
PROCEDURE endBackupDataFileResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_bdf_recid = last_bdf_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_bdf_recid := last_bdf_recid;
  last_bdf_recid := NULL;
 
END endBackupDataFileResync;
 
/*-----------------------*
 * Backup SPFILE resync  *
 *-----------------------*/
 
FUNCTION beginBackupSpFileResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_bsf_recid INTO last_bsf_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_bsf_recid := sessionWaterMarks.high_bsf_recid;
  END IF;
 
  RETURN last_bsf_recid;
END beginBackupSpFileResync;
 
PROCEDURE addBackupSpFile(
  bs_key             IN NUMBER
 ,bsf_recid          IN NUMBER
 ,bsf_stamp          IN NUMBER
 ,modification_time  IN DATE
 ,bytes              IN NUMBER
 ,db_unique_name     IN VARCHAR2
 ,pdb_key            IN NUMBER
) IS
 
local    bsf%rowtype;
l_conflict_count number := 0 ;
BEGIN
  deb('addBackupSpfile');
  INSERT INTO bsf(bsf_key, bs_key, db_key, bsf_recid, bsf_stamp,
                  modification_time, bytes, db_unique_name, pdb_key)
  VALUES (rman_seq.nextval, bs_key, this_db_key, bsf_recid, bsf_stamp,
          modification_time, bytes, db_unique_name, pdb_key);
  IF (duplicatebs.exists(addBackupSpFile.bs_key)) THEN
     DECLARE
       CURSOR bsf_conflicts(bs_key_new IN NUMBER ) IS
         SELECT bs_key, bsf_key, bsf_recid, bsf_stamp, bytes, pdb_key 
           FROM bsf
          WHERE bsf.bs_key = bs_key_new;
       conflict_rec bsf_conflicts%rowtype;
     BEGIN
       FOR conflict_rec IN bsf_conflicts(addBackupSpFile.bs_key)
       LOOP
         deb('addBackupSpFile set stamp set count conflict - ' ||
             ' bs_key '    || to_char(conflict_rec.bs_key)       ||
             ' bsf_key '   || to_char(conflict_rec.bsf_key)      ||
             ' bsf_recid ' || to_char(conflict_rec.bsf_recid)    ||
             ' bsf_stamp ' || to_char(conflict_rec.bsf_stamp)    ||
             ' bytes '     || to_char(conflict_rec.bytes)        ||
             ' pdb_key '   || to_char(conflict_rec.pdb_key));
       END LOOP;
     END;
     raise_application_error(-20110, 'set stamp set count conflict');
  END IF;
 
EXCEPTION
  WHEN dup_val_on_index THEN
    deb('addBackupSpfile - Inside dup_val_on_index exception');
--
--
--
--
 
--
--
    SELECT * INTO local
    FROM bsf
    WHERE bsf.bs_key = addBackupSpFile.bs_key;
 
--
    IF (modification_time <> local.modification_time) THEN
      raise_application_error(-20101, 'Invalid modification_time');
    END IF;
 
--
    IF (db_unique_name <> local.db_unique_name) THEN
      raise_application_error(-20101, 
                           'Invalid db_unique_name=' || db_unique_name ||
                           'expected db_unique_name=' || local.db_unique_name);
    END IF;
 
--
    IF local.bsf_recid <> bsf_recid or local.bsf_stamp <> bsf_stamp THEN
       UPDATE bsf set bsf_recid = addBackupSpFile.bsf_recid,
                      bsf_stamp = addBackupSpFile.bsf_stamp
       WHERE bsf.bs_key = addBackupSpFile.bs_key;
    END IF;
END addBackupSpFile;
 
PROCEDURE checkBackupSpFile(
  bsf_recid         IN NUMBER
 ,bsf_stamp         IN NUMBER
 ,set_stamp         IN NUMBER
 ,set_count         IN NUMBER
 ,modification_time IN DATE
 ,bytes             IN NUMBER
 ,chk_last_recid    IN BOOLEAN        DEFAULT TRUE
 ,db_unique_name    IN VARCHAR2       DEFAULT NULL
 ,guid              IN RAW            DEFAULT NULL
 ,dropped_pdb       IN BINARY_INTEGER DEFAULT 0
) IS
 
bs_key    NUMBER;
site_key  NUMBER;
l_pdb_key NUMBER;
 
BEGIN
  IF chk_last_recid THEN
     IF (last_bsf_recid IS NULL) THEN
       raise_application_error(-20037, 'Invalid last recid');
     END IF;
 
    IF (bsf_recid < last_bsf_recid) THEN
       raise_application_error(-20036, 'Invalid record order');
     END IF;
 
     IF (bsf_recid > last_bsf_recid + 1) THEN
--
       NULL;
     END IF;
     last_bsf_recid := bsf_recid;
  END IF;
 
--
  BEGIN
    SELECT bs_key INTO bs_key
    FROM bs
    WHERE bs.db_key = this_db_key
    AND   bs.set_stamp = checkBackupSpFile.set_stamp
    AND   bs.set_count = checkBackupSpFile.set_count FOR UPDATE OF bs.bs_key;
    deb('checkBackupSpFile - locked bs_key' || bs_key);
  EXCEPTION
    WHEN no_data_found THEN
       return;
  END;
 
--
--
  UPDATE bs SET bs.bck_type = 'D'
   WHERE bs.bs_key = checkBackupSpFile.bs_key
     AND bs.bck_type IS NULL;
 
  l_pdb_key := guidToPdbKey(guid, dropped_pdb);
 
  addBackupSpFile(bs_key, bsf_recid, bsf_stamp, modification_time, bytes, 
                  db_unique_name, l_pdb_key);
 
END checkBackupSpFile;
 
PROCEDURE endBackupSpFileResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_bsf_recid = last_bsf_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_bsf_recid := last_bsf_recid;
  last_bsf_recid := NULL;
 
END endBackupSpFileResync;
 
/*-------------------------*
 * Backup Redo Log resync  *
 *-------------------------*/
 
FUNCTION beginBackupRedoLogResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_brl_recid INTO last_brl_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_brl_recid := sessionWaterMarks.high_brl_recid;
  END IF;
 
  RETURN last_brl_recid;
END beginBackupRedoLogResync;
 
PROCEDURE checkBackupRedoLog(
  brl_recid      IN NUMBER
 ,brl_stamp      IN NUMBER
 ,set_stamp      IN NUMBER
 ,set_count      IN NUMBER
 ,thread#        IN NUMBER
 ,sequence#      IN NUMBER
 ,reset_scn      IN NUMBER
 ,reset_time     IN DATE
 ,low_scn        IN NUMBER
 ,low_time       IN DATE
 ,next_scn       IN NUMBER
 ,next_time      IN DATE
 ,blocks         IN NUMBER
 ,block_size     IN NUMBER
 ,chk_last_recid IN BOOLEAN  DEFAULT TRUE
 ,terminal       IN VARCHAR2 DEFAULT 'NO'
 ,activation     IN VARCHAR2 DEFAULT NULL
) IS
 
local brl%rowtype;
bskeep number;
l_conflict_count number :=0;
 
BEGIN
--
--
--
  IF brl_stamp = 0 THEN 
     deb('checkBackupRedoLog: ignoring this record as brl_stamp is 0'); 
     RETURN; 
  END IF; 
 
--
  IF chk_last_recid THEN
     IF (last_brl_recid IS NULL) THEN
        raise_application_error(-20037, 'Invalid last recid');
     END IF;
 
     IF (brl_recid < last_brl_recid) THEN
        raise_application_error(-20036, 'Invalid record order');
     END IF;
 
     IF (brl_recid > last_brl_recid + 1) THEN
--
--
        NULL;
     END IF;
     last_brl_recid := brl_recid;
  END IF;
 
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
--
  BEGIN
    SELECT bs_key,keep_options INTO local.bs_key, bskeep
    FROM bs
    WHERE bs.db_key = this_db_key
    AND   bs.set_stamp = checkBackupRedoLog.set_stamp
    AND   bs.set_count = checkBackupRedoLog.set_count FOR UPDATE OF bs.bs_key;
    deb('checkBackupRedoLog - locked bs_key' || local.bs_key);
  EXCEPTION
    WHEN no_data_found THEN
       RETURN;
  END;
 
  BEGIN
--
--
    UPDATE bs SET bs.bck_type = 'L'
    WHERE bs.bs_key = local.bs_key
    AND   bs.bck_type IS NULL;
  END;
 
--
  local.dbinc_key := checkIncarnation(reset_scn, reset_time);
 
  BEGIN
    INSERT INTO brl
        (brl_key, dbinc_key, brl_recid, brl_stamp,
         thread#, sequence#, low_scn, low_time, next_scn, next_time,
         blocks, block_size, bs_key, terminal, activation)
    VALUES
        (rman_seq.nextval, local.dbinc_key, brl_recid, brl_stamp,
         thread#, sequence#, low_scn, low_time, next_scn, next_time,
         blocks, block_size, local.bs_key, terminal, activation);
    IF (duplicatebs.exists(local.bs_key)) THEN
       DECLARE
         CURSOR brl_conflicts(bs_key_new IN NUMBER) IS
           SELECT bs_key, brl_key, dbinc_key, brl_recid, 
                  brl_stamp, thread#, sequence#, blocks, block_size 
             FROM brl
            WHERE brl.bs_key = bs_key_new;
         conflict_rec brl_conflicts%rowtype;
       BEGIN
         FOR conflict_rec IN brl_conflicts(local.bs_key)
         LOOP
           deb('checkBackupRedoLog set stamp set count conflict - ' ||
               ' bs_key '     || to_char(conflict_rec.bs_key)    ||
               ' brl_key '    || to_char(conflict_rec.brl_key)   ||
               ' dbinc_key '  || to_char(conflict_rec.dbinc_key) ||
               ' brl_recid '  || to_char(conflict_rec.brl_recid) ||
               ' brl_stamp '  || to_char(conflict_rec.brl_stamp) ||
               ' thread# '    || to_char(conflict_rec.thread#)   ||
               ' sequence# '  || to_char(conflict_rec.sequence#) ||
               ' blocks '     || to_char(conflict_rec.blocks)    ||
               ' block_size ' || to_char(conflict_rec.block_size));
         END LOOP;
       END;
       raise_application_error(-20110, 'set stamp set count conflict');
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('checkBackupRedoLog - Inside dup_val_on_index exception');
--
      SELECT count(*) INTO l_conflict_count
      FROM brl
      WHERE brl.bs_key = local.bs_key
        AND  brl.thread# = checkBackupRedoLog.thread#
        AND  brl.sequence# = checkBackupRedoLog.sequence#
        AND (
               brl.blocks     <> checkBackupRedoLog.blocks
            OR brl.block_size <> checkBackupRedoLog.block_size
            )
        AND ROWNUM = 1;
      IF (l_conflict_count > 0) THEN
         DECLARE
           CURSOR brl_conflicts(bs_key_new IN NUMBER, thread_new IN NUMBER, 
                                sequence_new IN NUMBER, blocks_new IN NUMBER,
                                block_size_new IN NUMBER ) 
           IS
             SELECT bs_key, brl_key, dbinc_key, brl_recid, brl_stamp, thread#,
                    sequence#, blocks, block_size
               FROM brl
              WHERE brl.bs_key = bs_key_new
                AND brl.thread# = thread_new
                AND brl.sequence# = sequence_new
                AND (
                       brl.blocks = blocks_new
                    OR brl.block_size = block_size_new
                    );
           conflict_rec brl_conflicts%rowtype;
         BEGIN
           FOR conflict_rec IN brl_conflicts(local.bs_key,
                                             checkBackupRedoLog.thread#,
                                             checkBackupRedoLog.sequence#,
                                             checkBackupRedoLog.blocks,
                                             checkBackupRedoLog.block_size)
           LOOP
             deb('checkBackupRedoLog set stamp set count existing rec-  '  || 
                 ' bs_key '         || to_char(conflict_rec.bs_key)    ||
                 ' brl_key '        || to_char(conflict_rec.brl_key)   ||
                 ' dbinc_key '      || to_char(conflict_rec.dbinc_key) ||
                 ' brl_recid '      || to_char(conflict_rec.brl_recid) ||
                 ' brl_stamp '      || to_char(conflict_rec.brl_stamp) ||
                 ' thread# '        || to_char(conflict_rec.thread#)   ||
                 ' sequence# '      || to_char(conflict_rec.sequence#) ||
                 ' blocks '         || to_char(conflict_rec.blocks)    ||
                 ' block_size '     || to_char(conflict_rec.block_size));
           END LOOP;
           deb('checkBackupRedoLog set stamp set count new conflict rec- '  ||
               ' bs_key '     || to_char(local.bs_key)    ||
               ' dbinc_key '  || to_char(local.dbinc_key) ||
               ' brl_recid '  || to_char(checkBackupRedoLog.brl_recid) ||
               ' brl_stamp '  || to_char(checkBackupRedoLog.brl_stamp) ||
               ' thread# '    || to_char(checkBackupRedoLog.thread#)   ||
               ' sequence# '  || to_char(checkBackupRedoLog.sequence#) ||
               ' blocks '     || to_char(checkBackupRedoLog.blocks)    ||
               ' block_size ' || to_char(checkBackupRedoLog.block_size));
         END;
         
         raise_application_error(-20110, 'set stamp set count conflict');
      END IF;
 
--
--
      SELECT low_scn, brl_recid, brl_stamp 
        INTO local.low_scn, local.brl_recid, local.brl_stamp
      FROM brl
      WHERE brl.bs_key = local.bs_key
       AND  brl.thread# = checkBackupRedoLog.thread#
       AND  brl.sequence# = checkBackupRedoLog.sequence#;
 
--
      IF (low_scn <> local.low_scn) THEN
        raise_application_error(-20098, 'Invalid low scn');
      END IF;
 
--
      IF local.brl_recid <> brl_recid or local.brl_stamp <> brl_stamp THEN
         UPDATE brl set brl_recid = checkBackupRedoLog.brl_recid,
                        brl_stamp = checkBackupRedoLog.brl_stamp
         WHERE brl.bs_key = local.bs_key
           AND brl.thread# = checkBackupRedoLog.thread#
           AND brl.sequence# = checkBackupRedoLog.sequence#;
      END IF;
  END;
 
  IF (bskeep > 0) THEN
    updateRestorePoint(low_scn, next_scn);
  END IF;
 
END checkBackupRedoLog;
 
PROCEDURE endBackupRedoLogResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_brl_recid = last_brl_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_brl_recid := last_brl_recid;
  last_brl_recid := NULL;
 
END endBackupRedoLogResync;
 
/*----------------------------*
 * Datafile Copy resync       *
 *----------------------------*/
 
PROCEDURE deleteDuplicateCCF(recid IN NUMBER,
                             stamp IN NUMBER,
                             fname IN VARCHAR2) IS
   lfname ccf.fname%TYPE;
BEGIN
    lfname := fname;
 
    IF lfname IS NULL THEN
       BEGIN
          SELECT fname INTO lfname FROM ccf
          WHERE ccf.dbinc_key IN
                (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
            AND ccf_recid   = recid
            AND ccf_stamp   = stamp;
       EXCEPTION
          WHEN no_data_found THEN
             RETURN;
          WHEN too_many_rows THEN -- unique_key is dbinc_key, recid and stamp
             RETURN;
       END;
    END IF;
 
--
    DELETE ccf
    WHERE ccf.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
    AND   ccf.fname = lfname
    AND   ((disk_backups_shared = TRUE#) OR
           (this_site_key = nvl(ccf.site_key, this_site_key)))
    AND   ccf.fname_hashkey = substr(lfname, 1, 10) || substr(lfname, -10)
    AND   NOT (ccf.ccf_recid = recid AND
               ccf.ccf_stamp = stamp);
 
END deleteDuplicateCCF;
 
PROCEDURE addControlFileCopy(
  ccf_recid        IN NUMBER
 ,ccf_stamp        IN NUMBER
 ,fname            IN VARCHAR2
 ,tag              IN VARCHAR2
 ,dbinc_key        IN NUMBER
 ,ckp_scn          IN NUMBER
 ,ckp_time         IN DATE
 ,create_time      IN DATE
 ,min_offr_recid   IN NUMBER
 ,block_size       IN NUMBER
 ,completion_time  IN DATE
 ,status           IN VARCHAR2
 ,controlfile_type IN VARCHAR2 DEFAULT NULL
 ,keep_options     IN NUMBER   DEFAULT 0
 ,keep_until       IN DATE     DEFAULT NULL
 ,is_recovery_dest_file IN VARCHAR2
 ,rsr_key          IN NUMBER   DEFAULT NULL
 ,blocks           IN NUMBER
 ,pdb_key          IN NUMBER
) IS
 
local    ccf%rowtype;
 
BEGIN
  BEGIN
    IF (status <> 'D') THEN
       INSERT INTO ccf(ccf_key, dbinc_key, ccf_recid, ccf_stamp, fname,
          fname_hashkey, tag,
          ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
          completion_time, status, controlfile_type, keep_options, keep_until,
          is_recovery_dest_file, rsr_key, blocks, site_key, pdb_key)
       VALUES (rman_seq.nextval, dbinc_key, ccf_recid, ccf_stamp, fname,
         substr(fname,1,10)||substr(fname,-10), tag,
         ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
         completion_time, status, controlfile_type, keep_options, keep_until,
         is_recovery_dest_file, rsr_key, blocks, this_site_key, pdb_key);
   
       deleteDuplicateCCF(ccf_recid, ccf_stamp, fname);
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addControlFileCopy - Inside dup_val_on_index exception');
--
      SELECT * INTO local
      FROM ccf
      WHERE ccf.dbinc_key = addControlFileCopy.dbinc_key
      AND   ccf.ccf_recid = addControlFileCopy.ccf_recid
      AND   ccf.ccf_stamp = addControlFileCopy.ccf_stamp;
 
--
      IF client_site_aware AND this_site_key <> local.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (ckp_scn <> local.ckp_scn) THEN
        raise_application_error(-20095, 'Invalid ckp_scn');
      END IF;
 
--
      IF local.site_key IS NULL THEN
         UPDATE ccf SET site_key = this_site_key
         WHERE ccf.dbinc_key = addControlFileCopy.dbinc_key
           AND ccf.ccf_recid = addControlFileCopy.ccf_recid
           AND ccf.ccf_stamp = addControlFileCopy.ccf_stamp;
      END IF;
  END;
 
END addControlFileCopy;
 
PROCEDURE deleteDuplicateCDF(recid IN NUMBER,
                             stamp IN NUMBER,
                             fname IN VARCHAR2) IS
   lfname cdf.fname%TYPE;
BEGIN
    lfname := fname;
 
    IF lfname IS NULL THEN
       BEGIN
          SELECT fname INTO lfname FROM cdf
          WHERE cdf.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
            AND cdf_recid = recid
            AND cdf_stamp = stamp;
       EXCEPTION
          WHEN no_data_found THEN
             RETURN;
          WHEN too_many_rows THEN -- unique_key is dbinc_key, recid and stamp
             RETURN;
       END;
    END IF;
 
--
    DELETE cdf
    WHERE cdf.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
    AND   cdf.fname = lfname
    AND   ((disk_backups_shared = TRUE#) OR
           (this_site_key = nvl(cdf.site_key, this_site_key)))
    AND   cdf.fname_hashkey = substr(lfname, 1, 10) || substr(lfname, -10)
    AND   NOT (cdf.cdf_recid = recid AND
               cdf.cdf_stamp = stamp);
 
END deleteDuplicateCDF;
 
                             
PROCEDURE addDataFileCopy(
  cdf_recid       IN NUMBER
 ,cdf_stamp       IN NUMBER
 ,fname           IN VARCHAR2
 ,tag             IN VARCHAR2
 ,file#           IN NUMBER
 ,create_scn      IN NUMBER
 ,dbinc_key       IN NUMBER
 ,incr_level      IN NUMBER
 ,ckp_scn         IN NUMBER
 ,ckp_time        IN DATE
 ,onl_fuzzy       IN VARCHAR2
 ,bck_fuzzy       IN VARCHAR2
 ,abs_fuzzy_scn   IN NUMBER
 ,rcv_fuzzy_scn   IN NUMBER
 ,rcv_fuzzy_time  IN DATE
 ,blocks          IN NUMBER
 ,block_size      IN NUMBER
 ,completion_time IN DATE
 ,status          IN VARCHAR2
 ,keep_options    IN NUMBER
 ,keep_until      IN DATE
 ,scanned         IN VARCHAR2
 ,is_recovery_dest_file IN VARCHAR2
 ,rsr_key         IN NUMBER
 ,create_time     IN DATE
 ,marked_corrupt  IN NUMBER
 ,foreign_dbid      IN NUMBER
 ,plugged_readonly  IN VARCHAR2
 ,plugin_scn        IN NUMBER
 ,plugin_reset_scn  IN NUMBER
 ,plugin_reset_time IN DATE
 ,pdb_key           IN NUMBER
 ,sparse_backup     IN VARCHAR2
) IS
 
local    cdf%rowtype;
 
BEGIN
 
  BEGIN
    IF (status <> 'D') THEN
       INSERT INTO cdf(cdf_key, dbinc_key, cdf_recid, cdf_stamp,
          file#, create_scn, fname, fname_hashkey, tag, incr_level,
          ckp_scn, ckp_time, onl_fuzzy, bck_fuzzy, abs_fuzzy_scn,
          rcv_fuzzy_scn, rcv_fuzzy_time, blocks, block_size, completion_time,
          status, keep_options, keep_until, scanned, is_recovery_dest_file, 
          rsr_key, create_time, marked_corrupt, site_key, foreign_dbid,
          plugged_readonly, plugin_scn, plugin_reset_scn, plugin_reset_time,
          pdb_key, sparse_backup)
       VALUES
         (rman_seq.nextval, dbinc_key, cdf_recid, cdf_stamp,
          file#, create_scn, fname, substr(fname,1,10)||substr(fname, -10),
          tag, incr_level, ckp_scn, ckp_time,
          decode(onl_fuzzy,'YES','Y','NO','N'),
          decode(bck_fuzzy,'YES','Y','NO','N'), abs_fuzzy_scn,
          rcv_fuzzy_scn, rcv_fuzzy_time, blocks, block_size, completion_time,
          status, keep_options, keep_until,
          decode(scanned,'YES','Y','NO','N'), is_recovery_dest_file, 
          rsr_key, create_time, marked_corrupt, this_site_key,
          foreign_dbid, plugged_readonly, plugin_scn, plugin_reset_scn,
          plugin_reset_time, pdb_key, sparse_backup);
 
       deleteDuplicateCDF(cdf_recid, cdf_stamp, fname);
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addDataFileCopy - Inside dup_val_on_index exception');
      SELECT * INTO local
      FROM cdf
      WHERE cdf.dbinc_key = addDataFileCopy.dbinc_key
      AND   cdf.cdf_recid = addDataFileCopy.cdf_recid
      AND   cdf.cdf_stamp = addDataFileCopy.cdf_stamp;
 
--
      IF client_site_aware AND this_site_key <> local.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (file# <> local.file#) THEN
        raise_application_error(-20096, 'Invalid file');
      END IF;
      IF (create_scn <> local.create_scn AND
          plugin_scn <> local.plugin_scn) THEN
        raise_application_error(-20097, 'Invalid create scn');
      END IF;
 
--
      IF local.site_key IS NULL THEN
         UPDATE cdf SET site_key = this_site_key
         WHERE cdf.dbinc_key = addDataFileCopy.dbinc_key
           AND cdf.cdf_recid = addDataFileCopy.cdf_recid
           AND cdf.cdf_stamp = addDataFileCopy.cdf_stamp;
      END IF;
  END;
 
END addDataFileCopy;
 
FUNCTION beginDataFileCopyResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_cdf_recid INTO last_cdf_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_cdf_recid := sessionWaterMarks.high_cdf_recid;
  END IF;
 
  RETURN last_cdf_recid;
END beginDataFileCopyResync;
 
PROCEDURE checkDataFileCopy(
  cdf_recid        IN NUMBER
 ,cdf_stamp        IN NUMBER
 ,fname            IN VARCHAR2
 ,tag              IN VARCHAR2
 ,file#            IN NUMBER
 ,create_scn       IN NUMBER
 ,create_time      IN DATE
 ,reset_scn        IN NUMBER
 ,reset_time       IN DATE
 ,incr_level       IN NUMBER
 ,ckp_scn          IN NUMBER
 ,ckp_time         IN DATE
 ,onl_fuzzy        IN VARCHAR2
 ,bck_fuzzy        IN VARCHAR2
 ,abs_fuzzy_scn    IN NUMBER
 ,rcv_fuzzy_scn    IN NUMBER
 ,rcv_fuzzy_time   IN DATE
 ,blocks           IN NUMBER
 ,block_size       IN NUMBER
 ,min_offr_recid   IN NUMBER
 ,completion_time  IN DATE
 ,status           IN VARCHAR2
 ,controlfile_type IN VARCHAR2        DEFAULT NULL
 ,keep_options     IN NUMBER          DEFAULT 0
 ,keep_until       IN DATE            DEFAULT NULL
 ,scanned          IN VARCHAR2        DEFAULT 'NO'
 ,is_recovery_dest_file IN VARCHAR2   DEFAULT 'NO'
 ,rsr_recid        IN NUMBER          DEFAULT NULL
 ,rsr_stamp        IN NUMBER          DEFAULT NULL
 ,marked_corrupt   IN NUMBER          DEFAULT NULL
 ,foreign_dbid      IN NUMBER         DEFAULT 0
 ,plugged_readonly  IN VARCHAR2       DEFAULT 'NO'
 ,plugin_scn        IN NUMBER         DEFAULT 0
 ,plugin_reset_scn  IN NUMBER         DEFAULT 0
 ,plugin_reset_time IN DATE           DEFAULT NULL
 ,guid              IN RAW            DEFAULT NULL
 ,sparse_backup     IN VARCHAR2       DEFAULT 'NO'
 ,dropped_pdb       IN BINARY_INTEGER DEFAULT 0
) IS
 
dbinc_key NUMBER;
localrsr  rsr%rowtype;
l_pdb_key NUMBER;
 
BEGIN
  IF (last_cdf_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (cdf_recid < last_cdf_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (cdf_recid > last_cdf_recid + 1) THEN
--
--
    NULL;
  END IF;
  last_cdf_recid := cdf_recid;
 
  IF (cdf_stamp > 0 and cdf_stamp < kccdivts) THEN
     deb('checkBackupDatafileCopy - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
  dbinc_key := checkIncarnation(reset_scn, reset_time);
 
  l_pdb_key := guidToPdbKey(guid, dropped_pdb);
 
--
  BEGIN
      SELECT rsr_key
        INTO localrsr.rsr_key
        FROM rsr
       WHERE rsr.dbinc_key = this_dbinc_key
         AND (rsr.site_key = this_site_key OR
              rsr.site_key is null AND this_site_key is null)
         AND rsr.rsr_stamp = checkDataFileCopy.rsr_stamp
         AND rsr.rsr_recid = checkDataFileCopy.rsr_recid;
  EXCEPTION
  WHEN no_data_found THEN
--
      NULL;
  END;
 
  IF (file# = 0) THEN
    addControlFileCopy(cdf_recid, cdf_stamp, fname, tag, dbinc_key,
         ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
         completion_time, status, controlfile_type, keep_options, keep_until,
         is_recovery_dest_file, localrsr.rsr_key, blocks, l_pdb_key);
  ELSE
    addDataFileCopy(cdf_recid, cdf_stamp, fname, tag, file#, create_scn,
         dbinc_key, incr_level, ckp_scn, ckp_time,
         onl_fuzzy, bck_fuzzy, abs_fuzzy_scn, rcv_fuzzy_scn, rcv_fuzzy_time,
         blocks, block_size, completion_time, status,
         keep_options, keep_until, scanned, is_recovery_dest_file,
         localrsr.rsr_key, create_time, marked_corrupt, foreign_dbid,
         plugged_readonly, plugin_scn, plugin_reset_scn, plugin_reset_time,
         l_pdb_key, sparse_backup);
  END IF;
END checkDataFileCopy;
 
PROCEDURE endDataFileCopyResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_cdf_recid = last_cdf_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_cdf_recid := last_cdf_recid;
  last_cdf_recid := NULL;
 
END endDataFileCopyResync;
 
/*----------------------------*
 * Proxy Datafile resync      *
 *----------------------------*/
 
PROCEDURE deleteDuplicateXCF(recid       IN NUMBER,
                             stamp       IN NUMBER,
                             device_type IN VARCHAR2,
                             handle      IN VARCHAR2) IS
   lhandle      xcf.handle%TYPE;
   ldevice_type xcf.device_type%TYPE;
 
BEGIN
    lhandle := handle;
 
    IF lhandle IS NULL OR ldevice_type IS NULL THEN
       BEGIN
          SELECT handle, device_type INTO lhandle, ldevice_type FROM xcf
          WHERE xcf.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
            AND xcf_recid = recid
            AND xcf_stamp = stamp;
       EXCEPTION
          WHEN no_data_found THEN
             RETURN;
          WHEN too_many_rows THEN -- unique_key is dbinc_key, recid and stamp
             RETURN;
       END;
    END IF;
 
--
    DELETE xcf
    WHERE xcf.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
    AND   xcf.device_type = ldevice_type
    AND   xcf.handle = lhandle
    AND   ((tape_backups_shared = TRUE#) OR
           (this_site_key = nvl(xcf.site_key, this_site_key)))
    AND   xcf.handle_hashkey =
              substr(ldevice_type, 1, 10) ||
              substr(lhandle, 1, 10) ||
              substr(lhandle, -10)
    AND   NOT (xcf.xcf_recid = recid AND
               xcf.xcf_stamp = stamp);
 
END deleteDuplicateXCF;
 
PROCEDURE addProxyControlFile(
  dbinc_key       IN NUMBER
 ,xcf_recid       IN NUMBER
 ,xcf_stamp       IN NUMBER
 ,tag             IN VARCHAR2
 ,ckp_scn         IN NUMBER
 ,ckp_time        IN DATE
 ,create_time     IN DATE
 ,min_offr_recid  IN NUMBER
 ,block_size      IN NUMBER
 ,device_type     IN VARCHAR2
 ,handle          IN VARCHAR2
 ,comments        IN VARCHAR2
 ,media           IN VARCHAR2
 ,media_pool      IN NUMBER
 ,start_time      IN VARCHAR2
 ,completion_time IN DATE
 ,status          IN VARCHAR2
 ,controlfile_type
                  IN VARCHAR2
 ,keep_options    IN NUMBER
 ,keep_until      IN DATE
 ,rsr_key         IN NUMBER
 ,blocks          IN NUMBER
 ,pdb_key         IN NUMBER
) IS
 
local    xcf%rowtype;
 
BEGIN
  BEGIN
    IF (status <> 'D') THEN
      INSERT INTO xcf(xcf_key, dbinc_key, xcf_recid, xcf_stamp, tag,
        ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
        device_type, handle, handle_hashkey, comments, media, media_pool,
        start_time, completion_time, status, controlfile_type, keep_options,
        keep_until, rsr_key, site_key, pdb_key)
      VALUES (rman_seq.nextval, dbinc_key, xcf_recid, xcf_stamp, tag,
        ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
        device_type, handle,
        substr(device_type,1,10)||substr(handle,1,10)||substr(handle,-10),
        comments, media, media_pool, start_time, completion_time, status,
        controlfile_type, keep_options, keep_until, rsr_key, this_site_key,
        pdb_key);
 
      deleteDuplicateXCF(xcf_recid, xcf_stamp, device_type, handle);
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addProxyControlFile - Inside dup_val_on_index exception');
--
      SELECT * INTO local
      FROM xcf
      WHERE xcf.dbinc_key = addProxyControlFile.dbinc_key
      AND   xcf.xcf_recid = addProxyControlFile.xcf_recid
      AND   xcf.xcf_stamp = addProxyControlFile.xcf_stamp;
 
--
      IF client_site_aware AND this_site_key <> local.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (ckp_scn <> local.ckp_scn) THEN
        raise_application_error(-20095, 'Invalid ckp_scn');
      END IF;
 
--
      IF local.site_key IS NULL THEN
         UPDATE xcf SET site_key = this_site_key
         WHERE xcf.dbinc_key = addProxyControlFile.dbinc_key
           AND xcf.xcf_recid = addProxyControlFile.xcf_recid
           AND xcf.xcf_stamp = addProxyControlFile.xcf_stamp;
      END IF;
  END;
 
END addProxyControlFile;
 
PROCEDURE deleteDuplicateXDF(recid       IN NUMBER,
                             stamp       IN NUMBER,
                             device_type IN VARCHAR2,
                             handle      IN VARCHAR2) IS
   lhandle      xdf.handle%TYPE;
   ldevice_type xdf.device_type%TYPE;
 
BEGIN
    lhandle := handle;
 
    IF lhandle IS NULL OR ldevice_type IS NULL THEN
       BEGIN
          SELECT handle, device_type INTO lhandle, ldevice_type FROM xdf
           WHERE xdf.dbinc_key IN
                 (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
            AND xdf_recid = recid
            AND xdf_stamp = stamp;
       EXCEPTION
          WHEN no_data_found THEN
             RETURN;
          WHEN too_many_rows THEN -- unique_key is dbinc_key, recid and stamp
             RETURN;
       END;
    END IF;
 
--
    DELETE xdf
    WHERE xdf.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
    AND   xdf.device_type = ldevice_type
    AND   xdf.handle = lhandle
    AND   ((tape_backups_shared = TRUE#) OR
           (this_site_key = nvl(xdf.site_key, this_site_key)))
    AND   xdf.handle_hashkey =
              substr(ldevice_type, 1, 10) ||
              substr(lhandle, 1, 10) ||
              substr(lhandle, -10)
    AND   NOT (xdf.xdf_recid = recid AND
               xdf.xdf_stamp = stamp);
 
END deleteDuplicateXDF;
 
PROCEDURE addProxyDataFile(
  dbinc_key       IN NUMBER
 ,xdf_recid       IN NUMBER
 ,xdf_stamp       IN NUMBER
 ,tag             IN VARCHAR2
 ,file#           IN NUMBER
 ,create_scn      IN NUMBER
 ,incr_level      IN NUMBER
 ,ckp_scn         IN NUMBER
 ,ckp_time        IN DATE
 ,onl_fuzzy       IN VARCHAR2
 ,bck_fuzzy       IN VARCHAR2
 ,abs_fuzzy_scn   IN NUMBER
 ,rcv_fuzzy_scn   IN NUMBER
 ,rcv_fuzzy_time  IN DATE
 ,blocks          IN NUMBER
 ,block_size      IN NUMBER
 ,device_type     IN VARCHAR2
 ,handle          IN VARCHAR2
 ,comments        IN VARCHAR2
 ,media           IN VARCHAR2
 ,media_pool      IN NUMBER
 ,start_time      IN VARCHAR2
 ,completion_time IN DATE
 ,status          IN VARCHAR2
 ,keep_options    IN NUMBER   DEFAULT 0
 ,keep_until      IN DATE     DEFAULT NULL
 ,rsr_key         IN NUMBER
 ,create_time     IN DATE
 ,foreign_dbid      IN NUMBER
 ,plugged_readonly  IN VARCHAR2
 ,plugin_scn        IN NUMBER
 ,plugin_reset_scn  IN NUMBER
 ,plugin_reset_time IN DATE
 ,pdb_key           IN NUMBER
) IS
 
local    xdf%rowtype;
 
BEGIN
 
  BEGIN
    IF (status <> 'D') THEN
      INSERT INTO xdf(xdf_key, dbinc_key, xdf_recid, xdf_stamp,
         file#, create_scn, tag, incr_level,
         ckp_scn, ckp_time, onl_fuzzy, bck_fuzzy, abs_fuzzy_scn,
         rcv_fuzzy_scn, rcv_fuzzy_time, blocks, block_size,
         device_type, handle, handle_hashkey, comments, media, media_pool,
         start_time, completion_time, status,
         keep_options, keep_until, rsr_key, site_key, foreign_dbid,
         plugged_readonly, plugin_scn, plugin_reset_scn, plugin_reset_time,
         pdb_key)
      VALUES
        (rman_seq.nextval, dbinc_key, xdf_recid, xdf_stamp,
         file#, create_scn, tag,
         incr_level, ckp_scn, ckp_time, decode(onl_fuzzy,'YES','Y','NO','N'),
         decode(bck_fuzzy,'YES','Y','NO','N'), abs_fuzzy_scn,
         rcv_fuzzy_scn, rcv_fuzzy_time, blocks, block_size,
         device_type, handle,
         substr(device_type,1,10)||substr(handle,1,10)||substr(handle,-10),
         comments, media, media_pool, start_time, completion_time, status,
         keep_options, keep_until, rsr_key, this_site_key, foreign_dbid,
         plugged_readonly, plugin_scn, plugin_reset_scn, plugin_reset_time,
         pdb_key);
 
       deleteDuplicateXDF(xdf_recid, xdf_stamp, device_type, handle);
    END IF;
 
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addProxyDatafile - Inside dup_val_on_index exception');
      SELECT * INTO local
      FROM xdf
      WHERE xdf.dbinc_key = addProxyDataFile.dbinc_key
      AND   xdf.xdf_recid = addProxyDataFile.xdf_recid
      AND   xdf.xdf_stamp = addProxyDataFile.xdf_stamp;
 
--
      IF client_site_aware AND this_site_key <> local.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (file# <> local.file#) THEN
        raise_application_error(-20096, 'Invalid file');
      END IF;
      IF (create_scn <> local.create_scn AND
          plugin_scn <> local.plugin_scn) THEN
        raise_application_error(-20097, 'Invalid create scn');
      END IF;
 
--
      IF local.site_key IS NULL THEN
         UPDATE xdf SET site_key = this_site_key
         WHERE xdf.dbinc_key = addProxyDataFile.dbinc_key
           AND xdf.xdf_recid = addProxyDataFile.xdf_recid
           AND xdf.xdf_stamp = addProxyDataFile.xdf_stamp;
      END IF;
  END;
 
END addProxyDataFile;
 
PROCEDURE deleteDuplicateXAL(recid       IN NUMBER,
                             stamp       IN NUMBER,
                             device_type IN VARCHAR2,
                             handle      IN VARCHAR2) IS
   lhandle      xal.handle%TYPE;
   ldevice_type xal.device_type%TYPE;
 
BEGIN
    lhandle := handle;
 
    IF lhandle IS NULL OR ldevice_type IS NULL THEN
       BEGIN
          SELECT handle, device_type INTO lhandle, ldevice_type FROM xal
          WHERE xal.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
            AND xal_recid = recid
            AND xal_stamp = stamp;
       EXCEPTION
          WHEN no_data_found THEN
             RETURN;
          WHEN too_many_rows THEN -- unique_key is dbinc_key, recid and stamp
             RETURN;
       END;
    END IF;
 
--
    DELETE xal
    WHERE xal.dbinc_key IN
            (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
    AND   xal.device_type = ldevice_type
    AND   xal.handle = lhandle
    AND   ((tape_backups_shared = TRUE#) OR
           (this_site_key = nvl(xal.site_key, this_site_key)))
    AND   xal.handle_hashkey =
              substr(ldevice_type, 1, 10) ||
              substr(lhandle, 1, 10) ||
              substr(lhandle, -10)
    AND   NOT (xal.xal_recid = recid AND
               xal.xal_stamp = stamp);
 
END deleteDuplicateXAL;
 
PROCEDURE addProxyArchivedLog(
  dbinc_key           IN NUMBER
 ,xal_recid           IN NUMBER
 ,xal_stamp           IN NUMBER
 ,tag                 IN VARCHAR2
 ,thread#             IN NUMBER
 ,sequence#           IN NUMBER
 ,resetlogs_change#   IN NUMBER
 ,resetlogs_time      IN DATE
 ,first_change#       IN NUMBER
 ,first_time          IN DATE
 ,next_change#        IN NUMBER
 ,next_time           IN DATE
 ,blocks              IN NUMBER
 ,block_size          IN NUMBER
 ,device_type         IN VARCHAR2
 ,handle              IN VARCHAR2
 ,comments            IN VARCHAR2
 ,media               IN VARCHAR2
 ,media_pool          IN NUMBER
 ,start_time          IN VARCHAR2
 ,completion_time     IN DATE
 ,status              IN VARCHAR2
 ,rsr_key             IN NUMBER
 ,terminal            IN VARCHAR2 default 'NO'
 ,keep_until          IN DATE      default NULL
 ,keep_options        IN NUMBER    default 0
) IS
 
local    xal%rowtype;
 
BEGIN
 
  BEGIN
    IF (status <> 'D') THEN
      INSERT INTO xal(xal_key, dbinc_key, xal_recid, xal_stamp, tag,
         thread#, sequence#, low_scn, low_time, next_scn, next_time,
         blocks, block_size, device_type, handle, handle_hashkey,
         comments, media, media_pool, start_time, completion_time, status,
         rsr_key, terminal, keep_until, keep_options, site_key)
      VALUES
        (rman_seq.nextval, dbinc_key, xal_recid, xal_stamp, tag,
         thread#, sequence#, first_change#, first_time, next_change#,
         next_time, blocks, block_size, device_type, handle,
         substr(device_type,1,10)||substr(handle,1,10)||substr(handle,-10),
         comments, media, media_pool, start_time, completion_time, status,
         rsr_key, terminal, keep_until, keep_options, this_site_key);
 
      deleteDuplicateXAL(xal_recid, xal_stamp, device_type, handle);
    END IF;
 
  EXCEPTION
    WHEN dup_val_on_index THEN
      deb('addProxyArchivedLog - Inside dup_val_on_index exception');
      SELECT * INTO local
      FROM xal
      WHERE xal.dbinc_key = addProxyArchivedLog.dbinc_key
      AND   xal.xal_recid = addProxyArchivedLog.xal_recid
      AND   xal.xal_stamp = addProxyArchivedLog.xal_stamp;
 
--
      IF client_site_aware AND this_site_key <> local.site_key THEN
          raise_application_error(-20081, 'change stamp for the record');
      END IF;
 
--
      IF (first_change# <> local.low_scn) THEN
        raise_application_error(-20098, 'Invalid low scn');
      END IF;
 
--
      IF local.site_key IS NULL THEN
         UPDATE xal SET site_key = this_site_key
         WHERE xal.dbinc_key = addProxyArchivedLog.dbinc_key
           AND xal.xal_recid = addProxyArchivedLog.xal_recid
           AND xal.xal_stamp = addProxyArchivedLog.xal_stamp;
      END IF;
  END;
 
  IF (keep_options > 0) THEN
    updateRestorePoint(first_change#, next_change#);
  END IF;
END addProxyArchivedLog;
 
--
--
FUNCTION beginProxyResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_pc_recid INTO last_xdf_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_xdf_recid := sessionWaterMarks.high_pc_recid;
  END IF;
 
  last_xal_recid := last_xdf_recid;
 
  RETURN last_xdf_recid;
END beginProxyResync;
 
PROCEDURE checkProxyDataFile(
  xdf_recid       IN NUMBER
 ,xdf_stamp       IN NUMBER
 ,tag             IN VARCHAR2
 ,file#           IN NUMBER
 ,create_scn      IN NUMBER
 ,create_time     IN DATE
 ,reset_scn       IN NUMBER
 ,reset_time      IN DATE
 ,incr_level      IN NUMBER
 ,ckp_scn         IN NUMBER
 ,ckp_time        IN DATE
 ,onl_fuzzy       IN VARCHAR2
 ,bck_fuzzy       IN VARCHAR2
 ,abs_fuzzy_scn   IN NUMBER
 ,rcv_fuzzy_scn   IN NUMBER
 ,rcv_fuzzy_time  IN DATE
 ,blocks          IN NUMBER
 ,block_size      IN NUMBER
 ,min_offr_recid  IN NUMBER
 ,device_type     IN VARCHAR2
 ,handle          IN VARCHAR2
 ,comments        IN VARCHAR2
 ,media           IN VARCHAR2
 ,media_pool      IN NUMBER
 ,start_time      IN DATE
 ,completion_time IN DATE
 ,status          IN VARCHAR2
 ,controlfile_type
                  IN VARCHAR2         DEFAULT NULL
 ,keep_options    IN NUMBER           DEFAULT 0
 ,keep_until      IN DATE             DEFAULT NULL
 ,rsr_recid       IN NUMBER           DEFAULT NULL
 ,rsr_stamp       IN NUMBER           DEFAULT NULL
 ,foreign_dbid      IN NUMBER         DEFAULT 0
 ,plugged_readonly  IN VARCHAR2       DEFAULT 'NO'
 ,plugin_scn        IN NUMBER         DEFAULT 0
 ,plugin_reset_scn  IN NUMBER         DEFAULT 0
 ,plugin_reset_time IN DATE           DEFAULT NULL
 ,guid              IN RAW            DEFAULT NULL
 ,dropped_pdb       IN BINARY_INTEGER DEFAULT 0
) IS
 
dbinc_key NUMBER;
localrsr  rsr%rowtype;
l_pdb_key NUMBER;
 
BEGIN
  IF (last_xdf_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (xdf_recid < last_xdf_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
--
--
  last_xdf_recid := xdf_recid;
 
  IF (xdf_stamp > 0 and xdf_stamp < kccdivts) THEN
     deb('checkProxyDatafile - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
--
  dbinc_key := checkIncarnation(reset_scn, reset_time);
 
  l_pdb_key := guidToPdbKey(guid, dropped_pdb);
 
--
  BEGIN
      SELECT rsr_key
        INTO localrsr.rsr_key
        FROM rsr
       WHERE rsr.dbinc_key = this_dbinc_key
         AND (rsr.site_key = this_site_key OR
              rsr.site_key is null AND this_site_key is null)
         AND rsr.rsr_stamp = checkProxyDataFile.rsr_stamp
         AND rsr.rsr_recid = checkProxyDataFile.rsr_recid;
  EXCEPTION
  WHEN no_data_found THEN
--
      NULL;
  END;
 
  IF (file# = 0) THEN
    addProxyControlFile(dbinc_key, xdf_recid, xdf_stamp, tag,
         ckp_scn, ckp_time, create_time, min_offr_recid, block_size,
         device_type, handle, comments, media, media_pool, start_time,
         completion_time, status, controlfile_type, keep_options, keep_until,
         localrsr.rsr_key, blocks, l_pdb_key);
  ELSE
    addProxyDataFile(dbinc_key, xdf_recid, xdf_stamp, tag, file#, create_scn,
         incr_level, ckp_scn, ckp_time,
         onl_fuzzy, bck_fuzzy, abs_fuzzy_scn, rcv_fuzzy_scn, rcv_fuzzy_time,
         blocks, block_size, device_type, handle, comments, media, media_pool,
         start_time, completion_time, status, keep_options, keep_until,
         localrsr.rsr_key, create_time, foreign_dbid, plugged_readonly,
         plugin_scn, plugin_reset_scn, plugin_reset_time, l_pdb_key);
  END IF;
 
END checkProxyDataFile;
 
PROCEDURE checkProxyArchivedLog(
  xal_recid          IN NUMBER
 ,xal_stamp          IN NUMBER
 ,tag                IN VARCHAR2
 ,thread#            IN NUMBER
 ,sequence#          IN NUMBER
 ,resetlogs_change#  IN NUMBER
 ,resetlogs_time     IN DATE
 ,first_change#      IN NUMBER
 ,first_time         IN DATE
 ,next_change#       IN NUMBER
 ,next_time          IN DATE
 ,blocks             IN NUMBER
 ,block_size         IN NUMBER
 ,device_type        IN VARCHAR2
 ,handle             IN VARCHAR2
 ,comments           IN VARCHAR2
 ,media              IN VARCHAR2
 ,media_pool         IN NUMBER
 ,start_time         IN DATE
 ,completion_time    IN DATE
 ,status             IN VARCHAR2
 ,rsr_recid          IN NUMBER
 ,rsr_stamp          IN NUMBER
 ,terminal           IN VARCHAR2  default 'NO'
 ,keep_until         IN DATE      default NULL
 ,keep_options       IN NUMBER    default 0
) IS
dbinc_key NUMBER;
localrsr  rsr%rowtype;
BEGIN
  IF (last_xal_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (xal_recid < last_xal_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
--
--
  last_xal_recid := xal_recid;
 
  IF (xal_stamp > 0 and xal_stamp < kccdivts) THEN
     deb('checkProxyArchivedLog - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
--
  dbinc_key := checkIncarnation(resetlogs_change#, resetlogs_time);
 
--
  BEGIN
      SELECT rsr_key
        INTO localrsr.rsr_key
        FROM rsr
       WHERE rsr.dbinc_key = this_dbinc_key
         AND (rsr.site_key = this_site_key OR
              rsr.site_key is null AND this_site_key is null)
         AND rsr.rsr_stamp = checkProxyArchivedLog.rsr_stamp
         AND rsr.rsr_recid = checkProxyArchivedLog.rsr_recid;
  EXCEPTION
  WHEN no_data_found THEN
--
      NULL;
  END;
 
  addProxyArchivedLog(dbinc_key, xal_recid, xal_stamp, tag, thread#, sequence#,
         resetlogs_change#, resetlogs_time, first_change#, first_time,
         next_change#, next_time, blocks, block_size, device_type, handle,
         comments, media, media_pool, start_time, completion_time, status,
         localrsr.rsr_key, terminal, keep_until, keep_options);
 
END checkProxyArchivedLog;
 
PROCEDURE endProxyResync IS
  last_pc_recid number :=
                   greatest(nvl(last_xdf_recid,0), nvl(last_xal_recid,0));
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_pc_recid = last_pc_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_pc_recid := last_pc_recid;
  last_xdf_recid := NULL;
  last_xal_recid := NULL;
 
END endProxyResync;
 
/*----------------------------*
 * Corrupt Block resync       *
 *----------------------------*/
 
FUNCTION beginBackupCorruptionResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_bcb_recid INTO last_bcb_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_bcb_recid := sessionWaterMarks.high_bcb_recid;
  END IF;
 
  RETURN last_bcb_recid;
END beginBackupCorruptionResync;
 
PROCEDURE checkBackupCorruption(
  bcb_recid       IN NUMBER
 ,bcb_stamp       IN NUMBER
 ,set_stamp       IN NUMBER
 ,set_count       IN NUMBER
 ,piece#          IN NUMBER
 ,file#           IN NUMBER
 ,block#          IN NUMBER
 ,blocks          IN NUMBER
 ,corrupt_scn     IN NUMBER
 ,marked_corrupt  IN VARCHAR2
 ,corruption_type IN VARCHAR2
) IS
 
local   bcb%rowtype;
 
BEGIN
  IF (last_bcb_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (bcb_recid < last_bcb_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (bcb_recid > last_bcb_recid + 1) THEN
--
--
    NULL;
  END IF;
  last_bcb_recid := bcb_recid;
 
  IF (bcb_stamp > 0 and bcb_stamp < kccdivts) THEN
     deb('checkBackupCorruption - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
  BEGIN
    SELECT bdf_key INTO local.bdf_key
    FROM  bdf, bs
    WHERE bdf.bs_key = bs.bs_key
    AND   bs.db_key = this_db_key
    AND   bs.set_stamp = checkBackupCorruption.set_stamp
    AND   bs.set_count = checkBackupCorruption.set_count
    AND   bdf.file# =    checkBackupCorruption.file#;
  EXCEPTION
    WHEN no_data_found THEN
--
      RETURN;
  END;
 
  BEGIN
    INSERT INTO bcb
      (bdf_key, bcb_recid, bcb_stamp, piece#, block#, blocks,
       corrupt_scn, marked_corrupt, corruption_type)
    VALUES
      (local.bdf_key, bcb_recid, bcb_stamp, piece#, block#, blocks,
       corrupt_scn, decode(marked_corrupt,'YES','Y','NO','N'),
       corruption_type);
  EXCEPTION
    WHEN dup_val_on_index THEN
--
      RETURN;
  END;
 
END checkBackupCorruption;
 
PROCEDURE endBackupCorruptionResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_bcb_recid = last_bcb_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_bcb_recid := last_bcb_recid;
  last_bcb_recid := NULL;
 
END endBackupCorruptionResync;
 
FUNCTION beginCopyCorruptionResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_ccb_recid INTO last_ccb_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_ccb_recid :=  sessionWaterMarks.high_ccb_recid;
  END IF;
 
  RETURN last_ccb_recid;
END beginCopyCorruptionResync;
 
PROCEDURE checkCopyCorruption(
  ccb_recid       IN NUMBER
 ,ccb_stamp       IN NUMBER
 ,cdf_recid       IN NUMBER
 ,cdf_stamp       IN NUMBER
 ,file#           IN NUMBER
 ,block#          IN NUMBER
 ,blocks          IN NUMBER
 ,corrupt_scn     IN NUMBER
 ,marked_corrupt  IN VARCHAR2
 ,corruption_type IN VARCHAR2
) IS
 
local   ccb%rowtype;
 
BEGIN
   IF (last_ccb_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (ccb_recid < last_ccb_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (ccb_recid > last_ccb_recid + 1) THEN
--
--
    NULL;
  END IF;
  last_ccb_recid := ccb_recid;
 
  IF (ccb_stamp > 0 and ccb_stamp < kccdivts) THEN
     deb('checkCopyCorruption - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
  BEGIN
    SELECT cdf_key INTO local.cdf_key
    FROM  cdf
    WHERE cdf.dbinc_key = this_dbinc_key
    AND   cdf.cdf_recid = checkCopyCorruption.cdf_recid
    AND   cdf.cdf_stamp = checkCopyCorruption.cdf_stamp
    AND   cdf.file# = checkCopyCorruption.file#;
  EXCEPTION
    WHEN no_data_found THEN
--
      RETURN;
  END;
 
  BEGIN
    INSERT INTO ccb
      (cdf_key, ccb_recid, ccb_stamp, block#, blocks,
       corrupt_scn, marked_corrupt, corruption_type)
    VALUES
      (local.cdf_key, ccb_recid, ccb_stamp, block#, blocks,
       corrupt_scn, decode(marked_corrupt,'YES','Y','NO','N'),
       corruption_type);
  EXCEPTION
    WHEN dup_val_on_index THEN
--
      RETURN;
  END;
END checkCopyCorruption;
 
PROCEDURE endCopyCorruptionResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_ccb_recid = last_ccb_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_ccb_recid := last_ccb_recid;
  last_ccb_recid := NULL;
 
END endCopyCorruptionResync;
 
FUNCTION beginBlockCorruptionResync(
  low_bcr_recid IN number)
RETURN NUMBER IS
   old_bcr_recid number;
BEGIN
  checkResync;
 
  SELECT high_bcr_recid, low_bcr_recid
    INTO last_bcr_recid, old_bcr_recid
    FROM node
   WHERE site_key = this_site_key;
 
--
--
--
--
--
--
--
--
  IF (old_bcr_recid != low_bcr_recid) THEN
     DELETE bcr
      WHERE site_key = this_site_key
        AND bcr_recid < low_bcr_recid;
     UPDATE node SET low_bcr_recid = low_bcr_recid
     WHERE site_key = this_site_key;
  END IF;
 
  RETURN last_bcr_recid;
END beginBlockCorruptionResync;
 
PROCEDURE checkBlockCorruption(
  bcr_recid       IN NUMBER
 ,bcr_stamp       IN NUMBER
 ,file#           IN NUMBER
 ,create_scn      IN NUMBER
 ,create_time     IN DATE
 ,block#          IN NUMBER
 ,blocks          IN NUMBER
 ,corrupt_scn     IN NUMBER
 ,corruption_type IN VARCHAR2
) IS
 
local   df%rowtype;
 
BEGIN
  IF (last_bcr_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (bcr_recid < last_bcr_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (bcr_recid > last_bcr_recid + 1) THEN
--
--
    NULL;
  END IF;
 
  last_bcr_recid := bcr_recid;
 
--
  BEGIN
    SELECT distinct df.df_key INTO local.df_key
    FROM  df, site_dfatt
    WHERE df.df_key = site_dfatt.df_key
    AND   site_dfatt.site_key = this_site_key
    AND   df.file# = checkBlockCorruption.file#
    AND   df.create_scn = checkBlockCorruption.create_scn
    AND   df.create_time = checkBlockCorruption.create_time;
  EXCEPTION
    WHEN no_data_found THEN
--
      deb('checkBlockCorruption - no df_key found');
      RETURN;
  END;
 
  deb('checkBlockCorruption - df_key=' || local.df_key);
 
  BEGIN
    INSERT INTO bcr 
      (bcr_recid, bcr_stamp, df_key, site_key,
       block#, blocks, corrupt_scn, corruption_type)
    VALUES
      (bcr_recid, bcr_stamp, local.df_key, this_site_key,
       block#, blocks, corrupt_scn, corruption_type);
  EXCEPTION
    WHEN dup_val_on_index THEN
--
      RETURN;
  END;
END checkBlockCorruption;
 
PROCEDURE endBlockCorruptionResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_bcr_recid = last_bcr_recid
      WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_bcr_recid := last_bcr_recid;
  last_bcr_recid := NULL;
END endBlockCorruptionResync;
 
/*----------------------------*
 * Deleted Object resync      *
 *----------------------------*/
 
FUNCTION beginDeletedObjectResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_do_recid INTO last_do_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_do_recid := sessionWaterMarks.high_do_recid;
  END IF;
 
  deb('beginDeletedObjectResync - last_do_recid='||last_do_recid);
  RETURN last_do_recid;
END beginDeletedObjectResync;
 
PROCEDURE checkDeletedObject(
  do_recid          IN NUMBER
 ,do_stamp          IN NUMBER
 ,object_type       IN VARCHAR2
 ,object_recid      IN NUMBER
 ,object_stamp      IN NUMBER
 ,object_data       IN NUMBER   DEFAULT NULL
 ,object_fname      IN VARCHAR2 DEFAULT NULL
 ,object_create_scn IN NUMBER   DEFAULT NULL
 ,set_stamp         IN NUMBER   DEFAULT NULL
 ,set_count         IN NUMBER   DEFAULT NULL
 ,handle            IN VARCHAR2 DEFAULT NULL
 ,device_type       IN VARCHAR2 DEFAULT NULL)
IS
  local            bp%rowtype;
  new_status       VARCHAR2(1);
  rc               boolean;
  keep_options     number := NULL;
  keep_until       date   := NULL;
BEGIN
  IF (last_do_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (do_recid < last_do_recid) THEN
    deb('checkDeletedObject - last_do_recid=' || last_do_recid);
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
--
  IF (do_recid > last_do_recid + 1) THEN
--
    NULL;
  END IF;
  last_do_recid := do_recid;
 
  IF (do_stamp > 0 AND do_stamp < kccdivts) THEN
     deb('checkDeletedObject - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
--
--
 
  IF (object_type like 'BACKUP SET%') THEN
    IF (object_type = 'BACKUP SET KEEP UNTIL') THEN 
      IF (object_data > 0) THEN
         keep_until := stamp2date(object_data);
      END IF;
    ELSIF (object_type = 'BACKUP SET KEEP OPTIONS') THEN
       keep_options := object_data;
    ELSE
        raise_application_error(-20999,
           'Internal error in checkDeletedObject(): bad object_type '||
           object_type);
    END IF;
 
    changeBackupSet(object_recid, object_stamp,
                    keep_options, keep_until);
  END IF;
 
  IF (object_type like 'BACKUP PIECE%') THEN
    IF (object_type = 'BACKUP PIECE') THEN
       new_status := 'D';
    ELSIF (object_type = 'BACKUP PIECE AVAILABLE') THEN
       new_status := 'A';
    ELSIF (object_type = 'BACKUP PIECE EXPIRED') THEN
       new_status := 'X';
    ELSIF (object_type = 'BACKUP PIECE UNAVAILABLE') THEN
       new_status := 'U';
    ELSE
       raise_application_error(-20999,
           'Internal error in checkDeletedObject(): bad object_type '||
           object_type);
    END IF;
    changeBackupPiece(object_recid, object_stamp, new_status,
                      set_stamp, set_count, NULL /* osite_key */,
                      NULL /*nsite_key */, handle, device_type);
  END IF;
 
  IF (object_type like 'DATAFILE COPY%') THEN
    IF (object_type = 'DATAFILE COPY') THEN
       new_status := 'D';
    ELSIF (object_type = 'DATAFILE COPY AVAILABLE') THEN
       new_status := 'A';
    ELSIF (object_type = 'DATAFILE COPY EXPIRED') THEN
       new_status := 'X';
    ELSIF (object_type = 'DATAFILE COPY UNAVAILABLE') THEN
       new_status := 'U';
    ELSIF (object_type = 'DATAFILE COPY KEEP UNTIL') THEN
       new_status := NULL;
       keep_until := stamp2date(object_data);
    ELSIF (object_type = 'DATAFILE COPY KEEP OPTIONS') THEN
       new_status := NULL;
       keep_options := object_data;
    ELSE
        raise_application_error(-20999,
           'Internal error in checkDeletedObject(): bad object_type '||
           object_type);
    END IF;
    changeDatafileCopy(object_recid, object_stamp, new_status,
                       keep_options, keep_until);
  END IF;
 
  IF (object_type like 'ARCHIVED LOG%') THEN
    IF (object_type = 'ARCHIVED LOG') THEN
       new_status := 'D';
    ELSIF (object_type = 'ARCHIVED LOG AVAILABLE') THEN
       new_status := 'A';
    ELSIF (object_type = 'ARCHIVED LOG EXPIRED') THEN
       new_status := 'X';
    ELSIF (object_type = 'ARCHIVED LOG UNAVAILABLE') THEN
       new_status := 'U';
    ELSE
       raise_application_error(-20999,
           'Internal error in checkDeletedObject(): bad object_type '||
           object_type);
    END IF;
    changeArchivedLog(object_recid, object_stamp, new_status);
  END IF;
 
  IF (object_type like 'PROXY COPY%') THEN
    IF (object_type = 'PROXY COPY') THEN
       new_status := 'D';
    ELSIF (object_type = 'PROXY COPY AVAILABLE') THEN
       new_status := 'A';
    ELSIF (object_type = 'PROXY COPY EXPIRED') THEN
       new_status := 'X';
    ELSIF (object_type = 'PROXY COPY UNAVAILABLE') THEN
       new_status := 'U';
    ELSIF (object_type = 'PROXY COPY KEEP UNTIL') THEN
       new_status := NULL;
       keep_until := stamp2date(object_data);
    ELSIF (object_type = 'PROXY COPY KEEP OPTIONS') THEN
       new_status := NULL;
       keep_options := object_data;
    ELSE
       raise_application_error(-20999,
           'Internal error in checkDeletedObject(): bad object_type '||
           object_type);
    END IF;
    changeProxyCopy(object_recid, object_stamp, new_status,
                    keep_options, keep_until);
  END IF;
 
  IF (object_type = 'DATAFILE RENAME ON RESTORE')
  THEN
    deb('checkDeletedObject - renaming file#='||object_data||' to '||
        object_fname);
 
--
--
--
--
--
    DECLARE
      local_df_key NUMBER;
    BEGIN
      SELECT DISTINCT df_key INTO local_df_key FROM df
      WHERE dbinc_key = this_dbinc_key
        AND   df.file# = object_data
        AND   df.create_scn = object_create_scn;
 
      UPDATE site_dfatt SET
        fname = object_fname
      WHERE 
        site_key = this_site_key AND
        df_key   = local_df_key;
 
      IF (NOT SQL%FOUND) THEN
        deb('checkDeletedObject - doing an insert');
        INSERT INTO site_dfatt (fname, df_key, site_key)
          VALUES (object_fname, local_df_key, this_site_key);
      END IF;
    EXCEPTION
     WHEN no_data_found THEN
        NULL;
    END;
  END IF;
 
  IF (object_type = 'PLUGGED READONLY RENAME')
  THEN
    deb('In checkDeletedObject, renaming plugged readonly file#='||
        object_data||' to ' ||object_fname);
--
--
--
--
--
--
--
    DECLARE
      CURSOR df_key_plugin_cur(file# IN NUMBER,
                               plugin_scn IN NUMBER) IS
        SELECT df_key FROM df
          WHERE dbinc_key = this_dbinc_key
            AND   df.file# = df_key_plugin_cur.file#
            AND   df.plugin_scn = df_key_plugin_cur.plugin_scn;
    BEGIN
      FOR plugin_df_key IN df_key_plugin_cur(object_data, object_create_scn)
      LOOP
        UPDATE site_dfatt
           SET fname = object_fname
         WHERE site_key = this_site_key AND
           df_key = plugin_df_key.df_key;
 
        IF (NOT SQL%FOUND) THEN
          deb('checkDeletedObject - doing an insert');
          INSERT INTO site_dfatt (fname, df_key, site_key)
            VALUES (object_fname, plugin_df_key.df_key, this_site_key);
        END IF;
      END LOOP;
    END;
  END IF;
 
  IF (object_type = 'TEMPFILE RENAME')
  THEN
    deb('checkDeletedObject - renaming temp file#='||object_data||' to'||
        object_fname);
--
--
--
--
--
    DECLARE
      local_tf_key NUMBER;
    BEGIN
      SELECT tf_key INTO local_tf_key FROM tf
      WHERE dbinc_key = this_dbinc_key
        AND   tf.file# = object_data
        AND   tf.create_scn = object_create_scn;
 
      UPDATE site_tfatt SET
         fname = object_fname
      WHERE 
        site_key = this_site_key AND
        tf_key   = local_tf_key;
 
      IF (NOT SQL%FOUND) THEN
        INSERT INTO site_tfatt (fname, tf_key, site_key)
          VALUES (object_fname, local_tf_key, this_site_key);
      END IF;
    EXCEPTION
     WHEN no_data_found THEN
        NULL;
    END;
  END IF;
 
  IF (object_type = 'DATABASE BLOCK CORRUPTION') THEN
     DELETE bcr
      WHERE site_key  = this_site_key
        AND bcr_recid = object_recid
        AND bcr_stamp = object_stamp;
  END IF;
 
  IF (object_type = 'RESTORE POINT') THEN
    DELETE nrsp
      WHERE site_key  = this_site_key
        AND nrsp_recid = object_recid
        AND nrsp_stamp = object_stamp;
  END IF;
 
  IF (object_type = 'INSTANT RESTORE') THEN
--
--
--
--
    deb('checkDeletedObject - type ='||object_type||
        ' datafile no ='||object_data||' backing file ='||object_fname);
  END IF;
 
END checkDeletedObject;
 
PROCEDURE endDeletedObjectResync IS
BEGIN
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     deb('endDeletedObjectResync - last_do_recid=' || last_do_recid);
     UPDATE node SET high_do_recid = last_do_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_do_recid := last_do_recid;
  last_do_recid := NULL;
 
END endDeletedObjectResync;
 
 
/*----------------------------*
 * RMAN Output resync         *
 *----------------------------*/
 
FUNCTION beginRmanOutputResync(start_timestamp IN NUMBER) RETURN NUMBER IS
  doRoutMining    BOOLEAN;
  last_rout_stamp NUMBER;
BEGIN
  last_rout_stamp := beginRmanOutputResync(start_timestamp, doRoutMining);
  RETURN last_rout_stamp;
END beginRmanOutputResync;
 
FUNCTION beginRmanOutputResync(start_timestamp IN  NUMBER,
                               doRoutMining    OUT BOOLEAN)
RETURN NUMBER IS
BEGIN
--
  getRmanOutputLogging(rman_keep_output);
 
--
  IF (session_keep_output = 0) or (rman_keep_output = 0) THEN
     deb('beginRmanOutputResync - rman output logging is disabled');
     return MAXNUMVAL;
  END IF;
 
  deb('beginRmanOutputResync - input instance start time='||start_timestamp);
 
  checkResync;
 
--
  SELECT inst_startup_stamp, high_rout_stamp into 
     last_inst_startup_stamp, last_rout_stamp from node where
     node.db_key = this_db_key and
     ((this_db_unique_name is null and node.db_unique_name is null) or
      node.db_unique_name = this_db_unique_name);
 
  deb('beginRmanOutputResync - last_inst_startup_stamp='||
      last_inst_startup_stamp||',last_rout_stamp='||last_rout_stamp);
  
--
--
  IF (last_inst_startup_stamp <> start_timestamp) THEN
     last_rout_stamp := sessionWaterMarks.high_rout_stamp;
     last_inst_startup_stamp := start_timestamp;
     doRoutMining := TRUE;
  ELSE
     doRoutMining := FALSE;
  END IF;
 
  RETURN last_rout_stamp;
END beginRmanOutputResync;
 
--
PROCEDURE insertCachedROUT IS
  errors NUMBER;
  dml_errors EXCEPTION;
  PRAGMA EXCEPTION_INIT(dml_errors, -24381);
BEGIN
  IF lrout_curridx = 0 THEN
    RETURN;
  END IF;
  deb('doing bulk update of ' || lrout_curridx || ' rows into ROUT');
  BEGIN
    FORALL i in 1..lrout_curridx SAVE EXCEPTIONS
      INSERT INTO ROUT VALUES lrout_table(i);
  EXCEPTION
    WHEN dml_errors THEN
      errors := SQL%BULK_EXCEPTIONS.COUNT;
      deb('Number of statements that failed: ' || errors);
      FOR i IN 1..errors LOOP
        deb('Error #' || i || ' occurred during '|| 
            'iteration #' || SQL%BULK_EXCEPTIONS(i).ERROR_INDEX); 
        deb('Error message is ' || 
            SQLERRM(-SQL%BULK_EXCEPTIONS(i).ERROR_CODE));
--
        IF -SQL%BULK_EXCEPTIONS(i).ERROR_CODE = -1 THEN
          UPDATE /*+ index(ROUT ROUT_U1) */ ROUT 
            SET rout_text = lrout_table(i).rout_text
            WHERE db_key = this_db_key
              AND rsr_key = lrout_table(i).rsr_key
              AND rout_skey = lrout_table(i).rout_skey
              AND rout_stamp = lrout_table(i).rout_stamp
              AND rout_recid = lrout_table(i).rout_recid
              AND site_key = this_site_key;
        ELSE
          lrout_curridx := 0;
          RAISE;
        END IF;
      END LOOP;
   END;
   lrout_curridx := 0;
END insertCachedROUT;
 
PROCEDURE checkRmanOutput( recid             IN NUMBER
                          ,stamp             IN NUMBER
                          ,session_recid     IN NUMBER
                          ,session_stamp     IN NUMBER
                          ,rman_status_recid IN NUMBER
                          ,rman_status_stamp IN NUMBER
                          ,output       IN VARCHAR2) IS
BEGIN
    deb('checkRmanOutput', RCVCAT_LEVEL_HI);
 
--
    IF (session_keep_output = 0) or (rman_keep_output = 0) THEN
        deb('checkRmanOutput - rman output logging is disabled');
        return;
    END IF;
 
--
    IF (last_rout_stamp < stamp) THEN
       last_rout_stamp := stamp;
    END IF;
 
    IF lrman_status_recid = rman_status_recid AND 
       lrman_status_stamp = rman_status_stamp THEN
       goto rsr_key_known;
    END IF;
 
    deb('checkRmanOutput - Find rsr_ key');
    BEGIN
      select rsr_key into lrsr_key from rsr where 
             rsr.dbinc_key = this_dbinc_key and
             ((rsr.site_key = this_site_key) or
              (rsr.site_key is null AND this_site_key is null)) and
             rsr.rsr_recid = rman_status_recid and
             rsr.rsr_stamp = rman_status_stamp;
    EXCEPTION
    WHEN no_data_found THEN
--
      deb('checkRmanOutput - ignoring following RMAN output row');
      RETURN;
    END;
 
<<rsr_key_known>>
 
    IF lsession_recid = session_recid AND 
       lsession_stamp = session_stamp THEN
       goto rout_skey_known;
    END IF;
 
    deb('checkRmanOutput - Find session key');
    BEGIN
--
--
--
--
--
      select max(rsr_key) into lrout_skey from rsr, dbinc where 
             rsr.dbinc_key  = dbinc.dbinc_key and
             dbinc.db_key   = this_db_key and
             (rsr.site_key = this_site_key or
              rsr.site_key is null AND this_site_key is null) and
             rsr.rsr_srecid = session_recid and
             rsr.rsr_sstamp = session_stamp and
             rsr.rsr_type   = 'SESSION';
    EXCEPTION
    WHEN no_data_found THEN
--
      deb('checkRmanOutput - ignoring following RMAN output row, cause2');
      RETURN;
    WHEN others THEN
      deb('checkRmanOutput - signal error for RMAN output row');
      RAISE;
    END;
 
<<rout_skey_known>>
 
    IF lrout_skey is null THEN
--
      deb('checkRmanOutput: skey not found, ignoring RMAN output row');
      RETURN;
    END IF;
 
--
--
    BEGIN
      lrout_curridx := lrout_curridx + 1;        
      lrout_table(lrout_curridx).db_key        := this_db_key;
      lrout_table(lrout_curridx).rsr_key       := lrsr_key;
      lrout_table(lrout_curridx).rout_skey     := lrout_skey;
      lrout_table(lrout_curridx).rout_recid    := recid;
      lrout_table(lrout_curridx).rout_stamp    := stamp;
      lrout_table(lrout_curridx).site_key      := this_site_key;
      lrout_table(lrout_curridx).rout_text     :=
          substrb(output, 1, krbmror_llength_bytes); -- bug 5906892
--
      IF lrout_curridx = 1000 THEN
        insertCachedROUT;
      END IF;
    END;
 
    lrman_status_recid := rman_status_recid;
    lrman_status_stamp := rman_status_stamp;
    lsession_recid := session_recid;
    lsession_stamp := session_stamp;
 
END checkRmanOutput;
 
PROCEDURE endRmanOutputResync IS
BEGIN
 
--
   IF (session_keep_output = 0) or (rman_keep_output = 0) THEN
      deb('endRmanOutputResync - rman output logging is disabled');
      return;
   END IF;
 
   IF lrout_curridx > 0 THEN
      insertCachedROUT;
   END IF;
 
   UPDATE node SET high_rout_stamp    = last_rout_stamp,
                   inst_startup_stamp = last_inst_startup_stamp
     WHERE node.db_key = this_db_key and
           ((this_db_unique_name is null and node.db_unique_name is null) or
            node.db_unique_name = this_db_unique_name);
  sessionWaterMarks.high_rout_stamp := last_rout_stamp;
  last_rout_stamp := NULL;
  last_inst_startup_stamp := NULL;
  lrsr_key := NULL;
  lrout_skey := NULL;
  lsession_recid := NULL;
  lsession_stamp := NULL;
  lrman_status_recid := NULL;
  lrman_status_stamp := NULL;
 
END endRmanOutputResync;
 
/*----------------------------*
 * RMAN Status resync         *
 *----------------------------*/
 
FUNCTION beginRmanStatusResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_rsr_recid INTO last_rsr_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_rsr_recid := sessionWaterMarks.high_rsr_recid;
  END IF;
 
  RETURN last_rsr_recid;
END beginRmanStatusResync;
 
PROCEDURE checkRmanStatus( recid            IN NUMBER
                          ,stamp            IN NUMBER
                          ,parent_recid     IN NUMBER
                          ,parent_stamp     IN NUMBER
                          ,row_level        IN NUMBER
                          ,row_type         IN VARCHAR2
                          ,command_id       IN VARCHAR2
                          ,operation        IN VARCHAR2
                          ,status           IN VARCHAR2
                          ,mbytes_processed IN NUMBER
                          ,start_time       IN DATE
                          ,end_time         IN DATE
                          ,ibytes           IN NUMBER default null
                          ,obytes           IN NUMBER default null
                          ,optimized        IN VARCHAR2 default null
                          ,otype            IN VARCHAR2 default null
                          ,session_recid    IN NUMBER default null
                          ,session_stamp    IN NUMBER default null
                          ,odevtype         IN VARCHAR2 default null
                          ,osb_allocated    IN VARCHAR2 default 'NO') IS
 
parent   rsr%rowtype;
 
BEGIN
  IF (last_rsr_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (recid < last_rsr_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (stamp < kccdivts) THEN
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
  parent.rsr_pkey := NULL;
  parent.rsr_l0key := NULL;
--
--
--
  IF (checkRmanStatus.row_level > 0)
  THEN
    deb('checkRmanStatus - row_level='||to_char(checkRmanStatus.row_level));
    BEGIN
      SELECT rsr_key, decode(rsr_level, 0, rsr_key, rsr_l0key)
      INTO parent.rsr_key, parent.rsr_l0key
      FROM rsr
      WHERE rsr.dbinc_key = this_dbinc_key
      AND   (rsr.site_key = this_site_key OR
             rsr.site_key is null AND this_site_key is null)
      AND   rsr.rsr_stamp  = checkRmanStatus.parent_stamp
      AND   rsr.rsr_recid  = checkRmanStatus.parent_recid;
    EXCEPTION
    WHEN no_data_found THEN
--
      deb('checkRmanStatus - ignoring this record');
      RETURN;
    END;
  END IF;
 
  BEGIN
 
    deb('checkRmanStatus - inserting into rsr');
    deb('checkRmanStatus - this_dbinc_key:'||to_char(this_dbinc_key));
    deb('checkRmanStatus - recid:         '||to_char(recid));
    deb('checkRmanStatus - stamp:         '||to_char(stamp));
    deb('checkRmanStatus - srecid:        '||to_char(session_recid));
    deb('checkRmanStatus - sstamp:        '||to_char(session_stamp));
    INSERT INTO rsr
      (rsr_key, dbinc_key, rsr_recid, rsr_stamp, rsr_pkey,
       rsr_l0key, rsr_level, rsr_type, rsr_oper, rsr_cmdid,
       rsr_status, rsr_mbytes, rsr_start, rsr_end, rsr_ibytes, 
       rsr_obytes, rsr_optimized, rsr_otype, rsr_srecid, rsr_sstamp, 
       rsr_odevtype, site_key, rsr_osb_allocated)
    VALUES
      (rman_seq.nextval, this_dbinc_key, recid, stamp, parent.rsr_key,
       parent.rsr_l0key, row_level, row_type, operation, command_id,
       status, mbytes_processed, start_time, end_time, ibytes, 
       obytes, optimized, otype, session_recid, session_stamp, odevtype,
       this_site_key, decode(osb_allocated, 'YES', 'Y', 'N'));
 
--
--
    DELETE rsr WHERE
           rsr.dbinc_key = this_dbinc_key 
       AND rsr.rsr_recid = recid
       AND rsr.rsr_stamp = stamp
       AND ((this_site_key is not null AND rsr.site_key is NULL) OR
            (this_site_key is null and rsr.site_key is not null));
  EXCEPTION
     WHEN dup_val_on_index THEN
--
     deb('checkRmanStatus - exception catch');
     deb('checkRmanStatus - this_dbinc_key:'||to_char(this_dbinc_key));
     deb('checkRmanStatus - recid:         '||to_char(recid));
     deb('checkRmanStatus - stamp:         '||to_char(stamp));
     deb('checkRmanStatus - srecid:        '||to_char(session_recid));
     deb('checkRmanStatus - sstamp:        '||to_char(session_stamp));
     UPDATE rsr
        SET rsr_pkey   = parent.rsr_key,
            rsr_l0key  = parent.rsr_l0key,
            rsr_level  = row_level,
            rsr_type   = row_type,
            rsr_oper   = operation,
            rsr_cmdid  = command_id,
            rsr_status = status,
            rsr_mbytes = mbytes_processed,
            rsr_start  = start_time,
            rsr_end    = end_time,
            rsr_ibytes = ibytes,
            rsr_obytes = obytes,
            rsr_optimized = optimized,
            rsr_otype =  otype,
            rsr_odevtype = odevtype,
            rsr_osb_allocated = decode(osb_allocated, 'YES', 'Y', 'N')
      WHERE rsr.rsr_stamp = stamp
      AND   rsr.rsr_recid = recid
      AND   (rsr.site_key = this_site_key OR
             rsr.site_key is null and this_site_key is null)
      AND   rsr.rsr_srecid = session_recid
      AND   rsr.rsr_sstamp = session_stamp
      AND   rsr.dbinc_key = this_dbinc_key;
  END;
END checkRmanStatus;
 
PROCEDURE endRmanStatusResync(recid number) IS
BEGIN
 
  IF (last_rsr_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (recid < last_rsr_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_rsr_recid = recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_rsr_recid := recid;
  last_rsr_recid := NULL;
 
END endRmanStatusResync;
 
PROCEDURE updateRmanStatusRow( recid   IN number
                              ,stamp   IN number
                              ,mbytes  IN number
                              ,status  IN binary_integer ) IS
BEGIN
 
  IF (this_dbinc_key IS NULL) THEN
    return;
  END IF;
 
  UPDATE rsr SET
         rsr_status = decode(status, 1, 'RUNNING',
                                   1+8, 'RUNNING WITH WARNINGS',
                                  1+16, 'RUNNING WITH ERRORS',
                                1+8+16, 'RUNNING WITH ERRORS',
                                     2, 'COMPLETED',
                                   2+8, 'COMPLETED WITH WARNINGS',
                                  2+16, 'COMPLETED WITH ERRORS',
                                2+8+16, 'COMPLETED WITH ERRORS',
                                        'FAILED'),
         rsr_mbytes = mbytes
   WHERE rsr.rsr_stamp = stamp
   AND   rsr.rsr_recid = recid
   AND   (rsr.site_key = this_site_key OR
          rsr.site_key is null AND this_site_key is null)
   AND   rsr.dbinc_key = this_dbinc_key;
 
   commitChanges('updateRmanStatusRow');
 
END updateRmanStatusRow;
 
PROCEDURE rsDeleteBackupPiece(bp_key IN number, purged IN varchar2)
IS
   bs_key number;
   db_key number;
   retention_time timestamp with time zone;
BEGIN
 
--
  SELECT bp.bs_key, bp.db_key INTO bs_key, db_key
    FROM bp, bs
   WHERE bp.bp_key = rsDeleteBackupPiece.bp_key 
     AND bp.bs_key = bs.bs_key FOR UPDATE OF bs.bs_key;
  deb('rsDeleteBackupPiece - locked bs_key' || bs_key);
 
--
  UPDATE bp SET bp.status = 'D',
                bp.purged = rsDeleteBackupPiece.purged
   WHERE bp.bp_key = rsDeleteBackupPiece.bp_key;
 
  IF (purged = 'Y') THEN
     IF this_is_ors THEN
        this_enable_populate_rsr := 
                             getValueFromConfig('_enable_populate_rsr_key');
     END IF;
     updateBackupSetRec(bs_key);
--
--
     IF this_is_ors THEN
        this_enable_populate_rsr := NULL;
        this_upstream_site_key := NULL;
     END IF;
  END IF;
 
--
END rsDeleteBackupPiece;
 
FUNCTION getValueFromConfig(entry IN VARCHAR2) RETURN varchar2 IS
   entryVal config.value%TYPE;
BEGIN
   SELECT UPPER(value)
     INTO entryVal
     FROM config
     WHERE name = entry;
   
   return entryVal;
END getValueFromConfig;
 
/*-------------------*
 * Change Procedures *
 *-------------------*/
 
/*
 * In these change procedures, we don't check that we found the record,
 * because we are processing a DL record - i.e. we already processed the
 * other cf records and hence it might already be flagged deleted.  This
 * applies to any status change because we might make it available and
 * then delete the object, so the object would be marked as deleted when
 * we process the object and the available status change (first DL) would
 * fail to find the object, so would a following DL that marks it deleted.
 */
 
PROCEDURE changeDatafileCopy(
  cdf_recid    IN NUMBER
 ,cdf_stamp    IN NUMBER
 ,status       IN VARCHAR2
 ,keep_options IN NUMBER DEFAULT NULL  -- null means do not update
 ,keep_until   IN DATE   DEFAULT NULL
 ,osite_key    IN number DEFAULT NULL  -- old site_key for the record
 ,nsite_key    IN number DEFAULT NULL  -- null means do not update
) IS
  local    dbinc%rowtype;
  fno      cdf.file#%type;
BEGIN
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
--
  BEGIN
--
     SELECT file# into fno
       FROM cdf
      WHERE dbinc_key in
               (select dbinc_key from dbinc where db_key = this_db_key)
      AND   ((osite_key is null AND cdf.site_key is null) OR 
             cdf.site_key = nvl(osite_key, cdf.site_key))
      AND   cdf.cdf_recid = changeDatafileCopy.cdf_recid
      AND   cdf.cdf_stamp = changeDatafileCopy.cdf_stamp;
  EXCEPTION
     WHEN no_data_found THEN
        BEGIN
--
           SELECT 0 into fno
             FROM ccf
            WHERE dbinc_key in
                     (select dbinc_key from dbinc where db_key = this_db_key)
            AND   ((osite_key is null AND ccf.site_key is null) OR 
                   ccf.site_key = nvl(osite_key, ccf.site_key))
            AND   ccf.ccf_recid = changeDatafileCopy.cdf_recid
            AND   ccf.ccf_stamp = changeDatafileCopy.cdf_stamp;
 
--
           changeControlfileCopy(cdf_recid, cdf_stamp, status,
                                 keep_options, keep_until,
                                 osite_key, nsite_key);
           RETURN;
        EXCEPTION
           WHEN no_data_found THEN
              RETURN; -- already deleted (we are processing a DL record)
        END;
     WHEN OTHERS THEN
        RAISE;
  END;
 
  IF  status IS NULL THEN
--
--
    IF keep_until IS NOT NULL THEN
      UPDATE cdf SET keep_until = changeDatafileCopy.keep_until
      WHERE cdf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND cdf.site_key is null) OR 
             cdf.site_key = nvl(osite_key, cdf.site_key))
      AND   cdf.cdf_recid = changeDatafileCopy.cdf_recid
      AND   cdf.cdf_stamp = changeDatafileCopy.cdf_stamp;
    END IF;
    IF keep_options IS NOT NULL THEN
      UPDATE cdf SET keep_options = changeDatafileCopy.keep_options
      WHERE cdf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND cdf.site_key is null) OR 
             cdf.site_key = nvl(osite_key, cdf.site_key))
      AND   cdf.cdf_recid = changeDatafileCopy.cdf_recid
      AND   cdf.cdf_stamp = changeDatafileCopy.cdf_stamp;
    END IF;
  ELSIF status IN ('A','U','X') THEN
--
--
    UPDATE cdf SET status = changeDatafileCopy.status,
                   site_key = nvl(nsite_key, site_key)
    WHERE cdf.dbinc_key in
      (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND cdf.site_key is null) OR 
             cdf.site_key = nvl(osite_key, cdf.site_key))
      AND   cdf.cdf_recid = changeDatafileCopy.cdf_recid
      AND   cdf.cdf_stamp = changeDatafileCopy.cdf_stamp;
--
    IF sql%rowcount > 0 and nsite_key is not null THEN
       deleteDuplicateCDF(cdf_recid, cdf_stamp, null);
    END IF;
 
  ELSIF status IN ('R','D') THEN
    DELETE FROM cdf
    WHERE cdf.dbinc_key in
      (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND cdf.site_key is null) OR 
             cdf.site_key = nvl(osite_key, cdf.site_key))
      AND   cdf.cdf_recid = changeDatafileCopy.cdf_recid
      AND   cdf.cdf_stamp = changeDatafileCopy.cdf_stamp;
  ELSE
    raise_application_error(-20100, 'Invalid status');
  END IF;
 
--
  commitChanges('changeDatafileCopy');
 
END changeDatafileCopy;
 
PROCEDURE changeControlfileCopy(
  cdf_recid    IN NUMBER
 ,cdf_stamp    IN NUMBER
 ,status       IN VARCHAR2
 ,keep_options IN NUMBER DEFAULT NULL  -- null means do not update
 ,keep_until   IN DATE   DEFAULT NULL
 ,osite_key    IN number DEFAULT NULL  -- old site_key for the record
 ,nsite_key    IN number DEFAULT NULL  -- null means do not update
) IS
 
local    dbinc%rowtype;
 
BEGIN
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
  IF  status IS NULL THEN
--
--
    IF keep_until IS NOT NULL THEN
      UPDATE ccf SET keep_until = changeControlfileCopy.keep_until
      WHERE  ccf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
        AND  ((osite_key is null AND ccf.site_key is null) OR 
              ccf.site_key = nvl(osite_key, ccf.site_key))
        AND  ccf.ccf_recid = changeControlfileCopy.cdf_recid
        AND  ccf.ccf_stamp = changeControlfileCopy.cdf_stamp;
    END IF;
    IF keep_options IS NOT NULL THEN
      UPDATE ccf SET keep_options = changeControlfileCopy.keep_options
      WHERE  ccf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
        AND  ((osite_key is null AND ccf.site_key is null) OR 
              ccf.site_key = nvl(osite_key, ccf.site_key))
        AND  ccf.ccf_recid = changeControlfileCopy.cdf_recid
        AND  ccf.ccf_stamp = changeControlfileCopy.cdf_stamp;
    END IF;
  ELSIF status IN ('A','U','X') THEN
--
--
    UPDATE ccf SET status = changeControlfileCopy.status,
                   site_key = nvl(nsite_key, site_key)
    WHERE  ccf.dbinc_key in
      (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND  ((osite_key is null AND ccf.site_key is null) OR 
            ccf.site_key = nvl(osite_key, ccf.site_key))
      AND   ccf.ccf_recid = changeControlfileCopy.cdf_recid
      AND   ccf.ccf_stamp = changeControlfileCopy.cdf_stamp;
--
    IF sql%rowcount > 0 and nsite_key is not null THEN
       deleteDuplicateCCF(cdf_recid, cdf_stamp, null);
    END IF;
  ELSIF status IN ('R','D') THEN
    DELETE FROM ccf
    WHERE ccf.dbinc_key in
      (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND  ((osite_key is null AND ccf.site_key is null) OR 
            ccf.site_key = nvl(osite_key, ccf.site_key))
      AND   ccf.ccf_recid = changeControlfileCopy.cdf_recid
      AND   ccf.ccf_stamp = changeControlfileCopy.cdf_stamp;
  ELSE
    raise_application_error(-20100, 'Invalid status');
  END IF;
 
--
  commitChanges('changeControlfileCopy');
 
END changeControlfileCopy;
 
PROCEDURE changeArchivedLog(
  al_recid  IN NUMBER
 ,al_stamp  IN NUMBER
 ,status    IN VARCHAR2
 ,osite_key IN NUMBER DEFAULT NULL        -- old site_key for the record
 ,nsite_key IN NUMBER DEFAULT NULL        -- null means do not update
) IS
BEGIN
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
  IF status IN ('A','U','X') THEN
--
--
     UPDATE al SET status = changeArchivedLog.status,
                   site_key = nvl(nsite_key, site_key)
     WHERE al.dbinc_key in
       (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
     AND   al.al_recid = changeArchivedLog.al_recid
     AND   al.al_stamp = changeArchivedLog.al_stamp
     AND   ((osite_key is null AND al.site_key is null) OR 
            al.site_key = nvl(osite_key, al.site_key));
--
     IF sql%rowcount > 0 and nsite_key is not null THEN
        deleteDuplicateAL(al_recid, al_stamp, null);
     END IF;
  ELSIF status IN ('R','D') THEN
--
--
--
    DELETE FROM al
    WHERE al.dbinc_key IN
      (SELECT dbinc_key FROM dbinc WHERE dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND al.site_key is null) OR 
             al.site_key = nvl(osite_key, al.site_key))
      AND al.al_recid = changeArchivedLog.al_recid
      AND al.al_stamp = changeArchivedLog.al_stamp;
  ELSE
    raise_application_error(-20100, 'Invalid status');
  END IF;
 
--
  commitChanges('changeArchivedLog');
 
END changeArchivedLog;
 
PROCEDURE changeBackupSet(
  recid        IN number
 ,stamp        IN number
 ,keep_options IN number   -- null means do not update
 ,keep_until   IN date
 ,osite_key    IN number    DEFAULT NULL  -- old site_key for the record
 ,nsite_key    IN number    DEFAULT NULL  -- null means do not update
) IS
   local    bs%rowtype;
   CURSOR dflist IS
      SELECT * FROM bdf
      WHERE bdf.bs_key = local.bs_key;
   CURSOR rllist IS
      SELECT * FROM brl
      WHERE brl.bs_key = local.bs_key;
   has_virtual boolean := TRUE;
   l_virtual   number  := 0;
BEGIN
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  BEGIN
    SELECT * INTO local
    FROM bs
    WHERE bs.db_key = this_db_key
    AND   bs.bs_recid = changeBackupSet.recid
    AND   bs.bs_stamp = changeBackupSet.stamp FOR UPDATE OF bs.bs_key;
    deb('changeBackupSet - locked bs_key' || local.bs_key);
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN; -- already deleted (we are processing a DL record)
  END;
 
  BEGIN
    SELECT bp.vb_key INTO l_virtual 
    FROM bp,bs 
    WHERE bp.bs_key = bs.bs_key
    AND   bp.vb_key IS NOT NULL
    AND   bs.bs_key = local.bs_key;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      has_virtual := FALSE;  -- no virtual found
  END;  
 
  IF has_virtual THEN
    deb('changeBackupSet - virtual backup piece key (AM)' || l_virtual);
  END IF;
 
  IF nsite_key is not null THEN
     UPDATE bp SET site_key = nsite_key WHERE bs_key = local.bs_key;
     UPDATE bs SET site_key = nsite_key WHERE bs_key = local.bs_key;
  END IF;
 
  IF NOT has_virtual THEN 
    UPDATE bs SET bs.keep_until = changeBackupSet.keep_until
    WHERE  bs.bs_key = local.bs_key;
  END IF;
 
  IF NOT has_virtual AND keep_options IS NOT NULL THEN
    UPDATE bs SET bs.keep_options = changeBackupSet.keep_options
    WHERE  bs.bs_key = local.bs_key;
 
--
    IF (local.bck_type = 'L') THEN
      FOR rlrec IN rllist LOOP
        updateRestorePoint(rlrec.low_scn, rlrec.next_scn);
      END LOOP;
    END IF;
    IF (local.bck_type = 'D') THEN
      FOR dfrec IN dflist LOOP
        updateRestorePoint(dfrec.ckp_scn, null);
      END LOOP;
    END IF;
  END IF;
 
--
  commitChanges('changeBackupSet');
 
END changeBackupSet;
 
PROCEDURE changeBackupPiece(
  bp_recid  IN NUMBER
 ,bp_stamp  IN NUMBER
 ,status    IN VARCHAR2
 ,set_stamp IN NUMBER    DEFAULT NULL
 ,set_count IN NUMBER    DEFAULT NULL
 ,osite_key IN NUMBER    DEFAULT NULL  -- old site_key for the record
 ,nsite_key IN NUMBER    DEFAULT NULL  -- null means do not update
 ,handle    IN VARCHAR2  DEFAULT NULL  -- null means not known
 ,device_type IN VARCHAR2 DEFAULT NULL -- null means not known
) IS
   CURSOR bsQ(bp_recid IN NUMBER, bp_stamp IN NUMBER) IS
      SELECT bs_key
        FROM bp
       WHERE bp.db_key   = this_db_key
         AND ((osite_key is null AND bp.site_key is null) OR 
              bp.site_key = nvl(osite_key, bp.site_key))
         AND bp.bp_recid = bsQ.bp_recid
         AND bp.bp_stamp = bsQ.bp_stamp;
   bsQrec       bsQ%ROWTYPE;
   totalbp      number;
   chgbskey     number := NULL;
   l_ba_access  bp.ba_access%type;
   l_bp_key     number;
   l_bp_recid   number;
   l_bp_stamp   number;
BEGIN
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  l_bp_recid := changeBackupPiece.bp_recid; 
  l_bp_stamp := changeBackupPiece.bp_stamp; 
 
--
--
  IF (set_stamp is not null AND set_count is not null) THEN
     BEGIN
        SELECT bs_key INTO chgbskey
          FROM bs
         WHERE bs.db_key    = this_db_key
           AND bs.set_stamp = changeBackupPiece.set_stamp
           AND bs.set_count = changeBackupPiece.set_count 
         FOR UPDATE OF bs.bs_key;
        deb('changeBackupPiece - locked bs_key' || chgbskey);
     EXCEPTION
       WHEN NO_DATA_FOUND THEN
         RETURN; -- already deleted (we are processing a DL record)
     END;
     deb('changeBackupPiece - chgbskey=' || chgbskey);
  ELSE
--
--
--
--
--
       
     SELECT count(*) INTO totalbp
       FROM bp
      WHERE bp.db_key   = this_db_key
        AND ((osite_key is null AND bp.site_key is null) OR 
             bp.site_key = nvl(osite_key, bp.site_key))
        AND bp.bp_recid = l_bp_recid
        AND bp.bp_stamp = l_bp_stamp
        AND bp.bs_key   = nvl(chgbskey, bp.bs_key);
 
     deb('changeBackupPiece - number of backup pieces match ' || totalbp);
 
     IF totalbp = 0 then
        RETURN; -- already deleted (we are processing a DL record)
     END IF;
  END IF;
 
  IF changeBackupPiece.handle is NOT NULL AND chgbskey IS NOT NULL THEN
     BEGIN
       deb('changeBackupPiece - Override bp_recid/bp_stamp for ' ||
           handle || ', on ' || device_type);
       SELECT bp_recid, bp_stamp INTO
              l_bp_recid, l_bp_stamp
         FROM bp
         WHERE bp.db_key = this_db_key
           AND bp.bs_key = chgbskey
           AND bp.handle = changeBackupPiece.handle
           AND bp.device_type = changeBackupPiece.device_type;
     EXCEPTION
       WHEN NO_DATA_FOUND THEN
         deb('changeBackupPiece - Not found ');
       WHEN OTHERS THEN
         deb('changeBackupPiece(DO_NOT_IGNORE) - Error ' || sqlerrm);
     END;
     deb('changeBackupPiece - recid='||l_bp_recid||', stamp='||l_bp_stamp);
  END IF;
 
--
--
--
 
  IF status in ('A','U','X') THEN
--
--
    UPDATE bp SET status = changeBackupPiece.status,
                  site_key = nvl(nsite_key, site_key)
    WHERE bp.db_key   = this_db_key
      AND ((osite_key is null AND bp.site_key is null) OR 
           bp.site_key = nvl(osite_key, bp.site_key))
      AND bp.bp_recid = l_bp_recid
      AND bp.bp_stamp = l_bp_stamp
      AND bp.bs_key   = nvl(chgbskey, bp.bs_key)
      AND bp.ba_access != 'L'    -- AM: no status changing
      AND bp.status  != 'D';
 
--
    IF sql%rowcount > 0 and nsite_key is not null THEN
--
--
       IF chgbskey is not null THEN
          UPDATE bs SET site_key=null WHERE bs_key = chgbskey; 
       ELSE
          UPDATE bs SET site_key=null WHERE bs_key in 
            (SELECT bs_key FROM bp
             WHERE bp.db_key   = this_db_key
              AND ((osite_key is null AND bp.site_key is null) OR
                   bp.site_key = nvl(osite_key, bp.site_key))
              AND bp.bp_recid = l_bp_recid
              AND bp.bp_stamp = l_bp_stamp);
       END IF;
       deleteDuplicateBP(l_bp_recid, l_bp_stamp, chgbskey, null, null);
    END IF;
  ELSIF status not in ('R', 'D') THEN
     raise_application_error(-20100, 'Invalid status');
  END IF;
 
  IF this_is_ors AND this_ckp_key IS NULL THEN
     this_enable_populate_rsr := getValueFromConfig('_enable_populate_rsr_key');
  END IF;
 
  IF (chgbskey IS NULL) THEN
     FOR bsQrec in bsQ(l_bp_recid, l_bp_stamp) LOOP
--
--
        IF status in ('R', 'D') THEN
           UPDATE bp SET bp.status = 'D'
            WHERE bp.db_key   = this_db_key
              AND ((osite_key is null AND bp.site_key is null) OR 
                   bp.site_key = nvl(osite_key, bp.site_key))
              AND bp.bp_recid = l_bp_recid
              AND bp.bp_stamp = l_bp_stamp
              AND bp.bs_key   = bsQrec.bs_key
           RETURNING bp.ba_access, bp.bp_key INTO l_ba_access, l_bp_key;
 
--
           IF l_ba_access != 'U' THEN
              DELETE FROM bp WHERE bp.bp_key = l_bp_key;
           END IF;
        END IF;
--
        updateBackupSetRec(bsQrec.bs_key);
     END LOOP;
  ELSE
     IF status in ('R', 'D') THEN
         UPDATE bp SET bp.status = 'D'
         WHERE bp.db_key   = this_db_key
           AND ((osite_key is null AND bp.site_key is null) OR 
                bp.site_key = nvl(osite_key, bp.site_key))
           AND bp.bp_recid = l_bp_recid
           AND bp.bp_stamp = l_bp_stamp
           AND bp.bs_key   = chgbskey
           AND bp.ba_access != 'L'    -- AM: no status changing
         RETURNING bp.ba_access, bp.bp_key INTO l_ba_access, l_bp_key;
 
--
        IF l_ba_access = 'U' THEN
           DELETE FROM bp WHERE bp.bp_key = l_bp_key;
        END IF;
     END IF;
--
     updateBackupSetRec(chgbskey);
  END IF;
--
--
  IF this_is_ors AND this_ckp_key IS NULL THEN
     this_enable_populate_rsr := NULL;
     this_upstream_site_key := NULL;
  END IF;
--
  commitChanges('changeBackupPiece');
 
END changeBackupPiece;
 
PROCEDURE changeProxyCopy(
  pc_recid     IN NUMBER
 ,pc_stamp     IN NUMBER
 ,status       IN VARCHAR2
 ,keep_options IN NUMBER DEFAULT NULL  -- null means do not update
 ,keep_until   IN DATE   DEFAULT NULL
 ,osite_key    IN number DEFAULT NULL  -- old site_key for the record
 ,nsite_key    IN number DEFAULT NULL  -- null means do not update
) IS
  low_scn   number;
  next_scn  number;
  xobjid    rowid;  -- proxy object rowid
BEGIN
  IF this_db_key IS NULL THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  IF status IS NULL THEN
--
--
    IF keep_until IS NOT NULL THEN
      UPDATE xdf SET xdf.keep_until = changeProxyCopy.keep_until
      WHERE   xdf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND xdf.site_key is null) OR 
             xdf.site_key = nvl(osite_key, xdf.site_key))
      AND    xdf.xdf_recid = changeProxyCopy.pc_recid
      AND    xdf.xdf_stamp = changeProxyCopy.pc_stamp;
--
      IF sql%rowcount = 0 THEN
        UPDATE xcf SET xcf.keep_until = changeProxyCopy.keep_until
        WHERE  xcf.dbinc_key in
          (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
        AND   ((osite_key is null AND xcf.site_key is null) OR 
               xcf.site_key = nvl(osite_key, xcf.site_key))
        AND   xcf.xcf_recid = changeProxyCopy.pc_recid
        AND   xcf.xcf_stamp = changeProxyCopy.pc_stamp;
      END IF;
    END IF;
    IF keep_options IS NOT NULL THEN
      SELECT min(ckp_scn), min(rowid) into low_scn, xobjid
      FROM xdf
      WHERE   xdf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND    ((osite_key is null AND xdf.site_key is null) OR 
              xdf.site_key = nvl(osite_key, xdf.site_key))
      AND    xdf.xdf_recid = changeProxyCopy.pc_recid
      AND    xdf.xdf_stamp = changeProxyCopy.pc_stamp;
 
--
      IF xobjid IS NOT NULL THEN
        updateRestorePoint(low_scn, null);
        UPDATE xdf SET xdf.keep_options = changeProxyCopy.keep_options
        WHERE rowid = xobjid;
      ELSE
--
        UPDATE xcf SET xcf.keep_options = changeProxyCopy.keep_options
        WHERE  xcf.dbinc_key in
          (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
        AND ((osite_key is null AND xcf.site_key is null) OR 
             xcf.site_key = nvl(osite_key, xcf.site_key))
        AND   xcf.xcf_recid = changeProxyCopy.pc_recid
        AND   xcf.xcf_stamp = changeProxyCopy.pc_stamp;
--
        IF sql%rowcount = 0 THEN
          SELECT min(xal.low_scn), min(xal.next_scn), min(rowid)
                 into low_scn, next_scn, xobjid
          FROM xal
          WHERE  xal.dbinc_key in
            (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
          AND ((osite_key is null AND xal.site_key is null) OR 
               xal.site_key = nvl(osite_key, xal.site_key))
          AND   xal.xal_recid = changeProxyCopy.pc_recid
          AND   xal.xal_stamp = changeProxyCopy.pc_stamp;
--
          IF xobjid IS NOT NULL THEN
            updateRestorePoint(low_scn, next_scn);
            UPDATE xal SET xal.keep_options = changeProxyCopy.keep_options
            WHERE rowid = xobjid;
          END IF;
        END IF;
      END IF;
    END IF;
  ELSIF status in ('A','U','X') THEN
--
--
    UPDATE xdf SET status = changeProxyCopy.status,
                   site_key = nvl(nsite_key, site_key)
    WHERE xdf.dbinc_key in
      (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
    AND   ((osite_key is null AND xdf.site_key is null) OR 
           xdf.site_key = nvl(osite_key, xdf.site_key))
    AND   xdf.xdf_recid = changeProxyCopy.pc_recid
    AND   xdf.xdf_stamp = changeProxyCopy.pc_stamp;
--
    IF sql%rowcount > 0 and nsite_key is not null THEN
       deleteDuplicateXDF(pc_recid, pc_stamp, null, null);
    END IF;
 
--
    IF sql%rowcount = 0 THEN
      UPDATE xcf SET status = changeProxyCopy.status,
                   site_key = nvl(nsite_key, site_key)
      WHERE xcf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND xcf.site_key is null) OR 
             xcf.site_key = nvl(osite_key, xcf.site_key))
      AND   xcf.xcf_recid = changeProxyCopy.pc_recid
      AND   xcf.xcf_stamp = changeProxyCopy.pc_stamp;
--
      IF sql%rowcount > 0 and nsite_key is not null THEN
         deleteDuplicateXCF(pc_recid, pc_stamp, null, null);
      END IF;
    END IF;
 
--
    IF sql%rowcount = 0 THEN
      UPDATE xal SET status = changeProxyCopy.status,
                   site_key = nvl(nsite_key, site_key)
      WHERE xal.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND xal.site_key is null) OR 
             xal.site_key = nvl(osite_key, xal.site_key))
      AND   xal.xal_recid = changeProxyCopy.pc_recid
      AND   xal.xal_stamp = changeProxyCopy.pc_stamp;
--
      IF sql%rowcount > 0 and nsite_key is not null THEN
          deleteDuplicateXAL(pc_recid, pc_stamp, null, null);
      END IF;
    END IF;
  ELSIF status IN ('R','D') THEN
--
    SELECT min(ckp_scn), min(rowid) into low_scn, xobjid
    FROM xdf
    WHERE xdf.dbinc_key in
      (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
    AND   ((osite_key is null AND xdf.site_key is null) OR 
           xdf.site_key = nvl(osite_key, xdf.site_key))
    AND   xdf.xdf_recid = changeProxyCopy.pc_recid
    AND   xdf.xdf_stamp = changeProxyCopy.pc_stamp;
--
    IF xobjid IS NOT NULL THEN
      updateRestorePoint(low_scn, null);
      DELETE FROM xdf
      WHERE rowid = xobjid;
    ELSE
--
      DELETE FROM xcf
      WHERE xcf.dbinc_key in
        (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
      AND   ((osite_key is null AND xcf.site_key is null) OR 
             xcf.site_key = nvl(osite_key, xcf.site_key))
      AND   xcf.xcf_recid = changeProxyCopy.pc_recid
      AND   xcf.xcf_stamp = changeProxyCopy.pc_stamp;
 
--
      IF sql%rowcount = 0 THEN
        SELECT min(xal.low_scn), min(xal.next_scn), min(rowid)
               into low_scn, next_scn, xobjid
        FROM xal
        WHERE xal.dbinc_key in
          (select dbinc_key from dbinc where dbinc.db_key = this_db_key)
        AND   ((osite_key is null AND xal.site_key is null) OR 
               xal.site_key = nvl(osite_key, xal.site_key))
        AND   xal.xal_recid = changeProxyCopy.pc_recid
        AND   xal.xal_stamp = changeProxyCopy.pc_stamp;
        IF xobjid IS NOT NULL THEN
          updateRestorePoint(low_scn, next_scn);
          DELETE FROM xal
          WHERE rowid = xobjid;
        END IF;
      END IF;
    END IF;
  ELSE
    raise_application_error(-20100, 'Invalid status');
  END IF;
 
--
  commitChanges('changeProxyCopy');
 
END changeProxyCopy;
 
/*----------------------------*
 * Stored Script Procedures   *
 *----------------------------*/
 
PROCEDURE createScript(name IN VARCHAR2) IS
BEGIN
  createScript(name, NULL, FALSE);
END;
 
PROCEDURE createScript(name IN VARCHAR2,
                       scr_com IN VARCHAR2,
                       global IN boolean) IS
  foo NUMBER;
  dbkey  NUMBER := this_db_key;
BEGIN
  scr_key := NULL;                      -- for safety
  IF global THEN
     dbkey := NULL;
     scr_glob := TRUE;
  ELSE
     scr_glob := FALSE;
     IF (this_db_key IS NULL) THEN
        raise_application_error(-20021, 'Database not set');
     END IF;
  END IF;
  SELECT count(*)
    INTO foo
    FROM scr
   WHERE ((dbkey is not null and scr.db_key = dbkey)
      OR  (dbkey is null and scr.db_key is null))
     AND scr.scr_name = createScript.name;
  IF foo > 0 THEN
    raise_application_error(-20401, 'script '||name||' already exists');
  END IF;
 
  INSERT INTO scr VALUES(rman_seq.nextval, dbkey, name, scr_com)
  RETURNING scr_key INTO scr_key;
  scr_line := 1;
 
--
  commitChanges('createScript');
 
END;
 
PROCEDURE replaceScript(name IN VARCHAR2) IS
 
BEGIN
   replaceScript(name, NULL, FALSE);
END;
 
PROCEDURE replaceScript(name IN VARCHAR2,
                        scr_com IN VARCHAR2,
                        global IN boolean) IS
  dbkey  NUMBER := this_db_key;
BEGIN
  IF global THEN
     dbkey := NULL;
     scr_glob := TRUE;
  ELSE
     scr_glob := FALSE;
     IF (this_db_key IS NULL) THEN
        raise_application_error(-20021, 'Database not set');
     END IF;
  END IF;
 
  SELECT scr_key
    INTO scr_key
    FROM scr
   WHERE ((dbkey is not null and scr.db_key = dbkey)
      OR  (dbkey is null and scr.db_key is null))
     AND scr.scr_name = replaceScript.name;
 
  UPDATE scr
     SET scr_comment = scr_com
   WHERE scr.scr_key = dbms_rcvcat.scr_key;
 
  DELETE FROM scrl
   WHERE scrl.scr_key = dbms_rcvcat.scr_key;
 
  scr_line := 1;
 
--
  commitChanges('replaceScript');
 
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    createScript(name, scr_com, global);
 
END;
 
PROCEDURE putLine(line IN VARCHAR2) IS
 
BEGIN
  IF not scr_glob and this_db_key IS NULL THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
  IF (scr_key IS NULL) THEN
    raise_application_error(-20402, 'createScript or replaceScript not done');
  END IF;
 
  INSERT INTO scrl(scr_key, linenum, text) VALUES(scr_key, scr_line, line);
  scr_line := scr_line + 1;
 
END;
 
PROCEDURE deleteScript(name IN VARCHAR2) IS
 
BEGIN
   deleteScript(name, 0);
END;
 
PROCEDURE deleteScript(name IN VARCHAR2, glob IN NUMBER) IS
  dbkey  NUMBER := this_db_key;
BEGIN
  IF glob = 1 THEN
     dbkey := NULL;
  ELSE
     IF (this_db_key IS NULL) THEN
        raise_application_error(-20021, 'Database not set');
     END IF;
  END IF;
 
  SELECT scr_key INTO scr_key
  FROM scr
   WHERE ((dbkey is not null and scr.db_key = dbkey)
      OR  (dbkey is null and scr.db_key is null))
     AND scr.scr_name = deleteScript.name;
 
  DELETE FROM scr
  WHERE scr.scr_key = dbms_rcvcat.scr_key;
  scr_key := NULL;
 
--
  commitChanges('deleteScript');
 
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    scr_key := NULL;
    raise_application_error(-20400, 'stored script not found');
 
END;
 
PROCEDURE getScript(name IN VARCHAR2) IS
BEGIN
   getScript(name, 0);
END;
 
PROCEDURE getScript(name IN VARCHAR2, glob IN NUMBER) IS
  dbkey  NUMBER := this_db_key;
BEGIN
  IF glob = 1 THEN
     dbkey := NULL;
     scr_glob := TRUE;
  ELSE
     scr_glob := FALSE;
     IF (this_db_key IS NULL) THEN
        raise_application_error(-20021, 'Database not set');
     END IF;
  END IF;
 
  SELECT scr_key INTO scr_key
  FROM scr
   WHERE ((dbkey is not null and scr.db_key = dbkey)
      OR  (dbkey is null and scr.db_key is null))
     AND scr.scr_name = getScript.name;
 
  IF scrlQ%ISOPEN THEN
    CLOSE scrlQ;
  END IF;
  OPEN scrlQ(scr_key);
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    scr_key := NULL;
    raise_application_error(-20400, 'stored script not found');
END;
 
FUNCTION getLine RETURN VARCHAR2 IS
  scrl_row   scrlQ%rowtype;
BEGIN
  IF not scr_glob and this_db_key IS NULL THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
  IF NOT scrlQ%ISOPEN THEN
    raise_application_error(-20403, 'getScript not done');
  END IF;
  FETCH scrlQ INTO scrl_row;
  IF scrlQ%NOTFOUND THEN  -- end of fetch
    close scrlQ;
    return NULL;
  END IF;
  RETURN scrl_row.text;
END;
 
PROCEDURE commitChanges(msg IN varchar2 DEFAULT NULL) IS
BEGIN
  IF (this_ckp_key IS NULL) THEN
    deb(msg || ',commitChanges commit, release locks');
    commit;
  ELSE
    deb(msg || ',resync active, commitChanges ignored');
  END IF;
END;
 
/*---------------------------------------*
 * Procedures for EM xml store support   *
 *---------------------------------------*/
procedure createXMLFile (name        IN varchar2 
                        ,name_tag    IN varchar2
                        ,xmldoc      IN     clob
                        ,doctype     IN varchar2
                        ,xml_comment IN varchar2
                        ,schema_ver  IN varchar2
                        ) IS
begin
 
--
   insert into xmlstore
      (store_key, 
       creation_time, 
       name, 
       name_tag, 
       doctype, 
       schema_ver, 
       xml_comment,
       xmldoc) 
   values
      (rman_seq.nextval, 
       sys_extract_utc(systimestamp),
       createXMLFile.name,
       createXMLFile.name_tag, 
       createXMLFile.doctype,
       createXMLFile.schema_ver, 
       createXMLFile.xml_comment,
       XMLType.createXML(createXMLFile.xmldoc, schema_ver));
   commitChanges('createXMLFile');
 
end createXMLFile;
 
procedure updateXMLFile (name        IN varchar2
                        ,name_tag    IN varchar2
                        ,xmldoc      IN clob
                        ,xml_comment IN varchar2
                        ,schema_ver  IN varchar2
                        ,new_name    IN varchar2
                        ,new_name_tag IN varchar2
                        ,new_version  IN BOOLEAN ) IS
   oldrec          xmlstore%ROWTYPE;
begin
 
--
   begin
      select * into oldrec
      from xmlstore
      where name = updateXMLFile.name
         and (name_tag = updateXMLFile.name_tag or
              (name_tag is null and updateXMLFile.name_tag is null))
      for update;
   exception
      when too_many_rows then
         select * into oldrec
         from xmlstore
          where name = updateXMLFile.name
            and (name_tag = updateXMLFile.name_tag or
                 (name_tag is null and updateXMLFile.name_tag is null))
            and version_num = 
                (select max(version_num) from xmlstore
                 where name = updateXMLFile.name
                   and (name_tag = updateXMLFile.name_tag or
                         (name_tag is null 
                          and updateXMLFile.name_tag is null)))
          for update;
   end;
 
--
   if xmldoc is not null then
      oldrec.xmldoc := 
         XMLType.createXML(xmldoc, nvl(schema_ver, oldrec.schema_ver));
   end if;
 
   if xml_comment is not null then
      oldrec.xml_comment := xml_comment;
   end if;
 
   if schema_ver is not null then
      oldrec.schema_ver := schema_ver;
   end if;
 
   if new_name is not null then
      oldrec.name := new_name;
   end if;
 
   if new_name_tag is not null then
      oldrec.name_tag := new_name_tag;
   end if;
 
--
--
   if new_version then
      oldrec.version_num := oldrec.version_num + 1;
      select rman_seq.nextval into oldrec.store_key from dual;
      insert into xmlstore values oldrec;
   else
     oldrec.modified_time := sys_extract_utc(systimestamp);
      update xmlstore p set row = oldrec
         where p.name = updateXMLFile.name
            and (p.name_tag = updateXMLFile.name_tag or
                 (p.name_tag is null and updateXMLFile.name_tag is null))
            and p.version_num = oldrec.version_num;
   end if;
 
   commitChanges('updateXMLFile');
 
end updateXMLFile;
 
procedure deleteXMLFile (name     IN varchar2
                        ,name_tag IN varchar2) IS
begin
 
--
   delete xmlstore 
      where name = deleteXMLFile.name
        and (name_tag = deleteXMLFile.name_tag or
             (name_tag is null and deleteXMLFile.name_tag is null));
   if sql%rowcount = 0 then
      raise no_data_found;
   end if;
   commitChanges('deleteXMLFile');
 
end deleteXMLFile;
 
procedure readXMLFile   (name        IN varchar2
                        ,name_tag    IN varchar2
                        ,version_num IN number
                        ,xmldoc      OUT clob) IS
begin
 
--
   if version_num is null then
      begin
         select XMLType.getClobVal(xmldoc) into readXMLFile.xmldoc
         from xmlstore
         where name = readXMLFile.name
            and (name_tag = readXMLFile.name_tag or
                 (name_tag is null and readXMLFile.name_tag is null));
      exception
         when too_many_rows then
            select XMLType.getClobVal(xmldoc) into readXMLFile.xmldoc
            from xmlstore
             where name = readXMLFile.name
              and (name_tag = readXMLFile.name_tag or
                   (name_tag is null and readXMLFile.name_tag is null))
              and version_num = 
                  (select max(version_num) from xmlstore
                   where name = readXMLFile.name
                    and (name_tag = readXMLFile.name_tag or
                         (name_tag is null and readXMLFile.name_tag is null)));
      end;
   else
      select XMLType.getClobVal(xmldoc) into readXMLFile.xmldoc
      from xmlstore
      where name = readXMLFile.name
         and (name_tag = readXMLFile.name_tag or
              (name_tag is null and readXMLFile.name_tag is null))
         and version_num = readXMLFile.version_num;
   end if;
 
end readXMLFile;
 
procedure getXMLFileAttr (name        IN varchar2
                         ,name_tag    IN varchar2
                         ,version_num IN number
                         ,doctype     OUT varchar2
                         ,file_size   OUT number
                         ,xml_comment OUT varchar2
                         ,schema_ver  OUT varchar2) is
   myrec xmlstore%ROWTYPE;
begin
 
--
--
   if version_num is null then
      begin
         select * into myrec
         from xmlstore
         where name = getXMLFileAttr.name
           and (name_tag = getXMLFileAttr.name_tag or 
                (name_tag is null and getXMLFileAttr.name_tag is null));
      exception
         when too_many_rows then
            select * into myrec
            from xmlstore
             where name = getXMLFileAttr.name
               and (name_tag = getXMLFileAttr.name_tag or
                    (name_tag is null and getXMLFileAttr.name_tag is null))
               and version_num = 
                   (select max(version_num) from xmlstore
                    where name = getXMLFileAttr.name
                      and (name_tag = getXMLFileAttr.name_tag or
                           (name_tag is null 
                            and getXMLFileAttr.name_tag is null)));
      end;
   else
      select * into myrec
      from xmlstore
      where name = getXMLFileAttr.name
         and (name_tag = getXMLFileAttr.name_tag or
              (name_tag is null and getXMLFileAttr.name_tag is null))
         and version_num = getXMLFileAttr.version_num;
   end if;
 
--
   doctype := myrec.doctype;
   file_size := dbms_lob.getlength(XMLType.getClobVal(myrec.xmldoc));
   xml_comment := myrec.xml_comment;
   schema_ver := myrec.schema_ver;
 
end getXMLFileAttr;
 
--
--
--
FUNCTION getPackageVersion RETURN VARCHAR2 IS
   version raschemaver.version%type;
   table_not_found EXCEPTION;
   PRAGMA EXCEPTION_INIT(table_not_found, -942);
BEGIN
  if version_counter > version_max_index then
    version_counter := 1;
    return null;
  end if;
  version_counter := version_counter + 1;
 
--
  BEGIN
     SELECT to_char(max(version), '09')
       INTO version 
       FROM raschemaver;
  EXCEPTION
     WHEN table_not_found THEN
        version := NULL;
  END;
 
  IF (version IS NULL) THEN
     version := '00';
  END IF;
 
--
  return version_list(version_counter - 1) || '.' || version;
END;
 
FUNCTION getCatalogVersion RETURN VARCHAR2 IS
version rcver.version%type;
BEGIN
  IF NOT rcverQ%ISOPEN THEN
    open rcverQ;
  END IF;
 
  FETCH rcverQ into version;
 
  IF rcverQ%NOTFOUND THEN  -- end of fetch
    close rcverQ;
    return NULL;
  END IF;
 
  RETURN version;
 
END;
 
/*---------------------------------------*
 * Procedures for clone database support *
 *---------------------------------------*/
 
PROCEDURE setCloneName(file#            IN  NUMBER
                      ,creation_change# IN  NUMBER
                      ,new_clone_fname  IN  VARCHAR2
                      ,old_clone_fname  IN  VARCHAR2
                      ,changedauxname   OUT boolean
                      ,plugin_change#   IN NUMBER   DEFAULT 0) IS
lfname df.clone_fname%TYPE;
BEGIN
  deb('setCloneName: file#='           || to_char(file#)||
                  ', creation_fname='  || to_char(nvl(creation_change#, ''))||
                  ', plugin_change#='  || to_char(nvl(plugin_change#, ''))||
                  ', old_clone_fname=' || old_clone_fname ||
                  ', new_clone_fname=' || new_clone_fname);
  changedauxname := FALSE;
--
--
  IF (new_clone_fname = 'UNKNOWN') THEN
     RETURN;
  END IF;
  IF old_clone_fname is NULL THEN
     IF new_clone_fname = 'NONE' THEN
--
        RETURN;
     ELSE
        lfname := new_clone_fname;
     END IF;
  ELSE
     IF new_clone_fname = 'NONE' THEN
        lfname := NULL;
     ELSIF old_clone_fname = new_clone_fname THEN
--
        RETURN;
     ELSE
        lfname := new_clone_fname;
     END IF;
  END IF;
 
  UPDATE df SET df.clone_fname = lfname
   WHERE df.dbinc_key = this_dbinc_key
     AND df.file# = setCloneName.file#
     AND df.create_scn = setCloneName.creation_change#
     AND df.plugin_scn = setCloneName.plugin_change#;
  changedauxname := TRUE;
 
  deb('setCloneName - changed auxname for file# '||to_char(file#)||
      ' from '||nvl(old_clone_fname, 'NULL')||' to '||
      nvl(lfname, 'NULL'));
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    raise_application_error(-20105, 'datafile missing');
END;
 
--
--
FUNCTION getCloneName( file#            IN NUMBER
                      ,creation_change# IN NUMBER
                      ,plugin_change#   IN NUMBER DEFAULT 0)
RETURN VARCHAR2 IS
 
ret df.clone_fname%TYPE;
 
BEGIN
 
--
--
  ret := dbms_rcvman.getCloneName(file#, creation_change#, plugin_change#);
 
  RETURN ret;
 
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    raise_application_error(-20105, 'datafile missing');
END;
 
 
/*-----------------------------------*
 * Procedures for RMAN configuration *
 *-----------------------------------*/
 
--
PROCEDURE setConfig(conf#            IN NUMBER
                   ,name             IN VARCHAR2
                   ,value            IN VARCHAR2) IS
BEGIN
 
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  INSERT INTO
         conf(     db_key, conf#, name, value, cleanup, db_unique_name,
                 site_key)
       VALUES(this_db_key, conf#, name, value,   'YES',           NULL,
                        0);
 
EXCEPTION
  WHEN dup_val_on_index THEN
    UPDATE conf SET
           conf.name = name,
           conf.value = value WHERE conf.conf# = conf#
                              AND   conf.db_key = this_db_key;
  RETURN;
END;
 
--
PROCEDURE setConfig2(conf#     IN NUMBER
                    ,name      IN VARCHAR2
                    ,value     IN VARCHAR2
                    ,nodespec  IN BOOLEAN) IS
  lname    conf.name%TYPE;
  lvalue   conf.value%TYPE;
BEGIN
 
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
--
--
  IF (nodespec)
  THEN
    INSERT INTO
          conf(     db_key, conf#, name, value,  cleanup, db_unique_name,
                  site_key)
        VALUES(this_db_key, conf#, name, value,     'NO', this_db_unique_name,
               this_site_key);
  ELSE
    INSERT INTO
          conf(     db_key, conf#, name, value,  cleanup,  db_unique_name,
                  site_key)
        VALUES(this_db_key, conf#, name, value,     'NO',           NULL,
                           0);
 
  END IF;
 
  deb('setConfig - Added name=(' || name ||
      '), value=(' || value ||
      ') to node ' || this_db_unique_name ||
      '('|| conf# ||')');
 
  EXCEPTION
--
--
    WHEN dup_val_on_index THEN
      select name, value into lname, lvalue from conf where
             conf.conf# = setConfig2.conf# AND
             conf.db_key = this_db_key AND
             db_unique_name = this_db_unique_name;
      IF (lname = name AND lvalue = value) THEN
        RETURN;
      END IF;
      deb('setConfig - lname=' || lname ||
          ', lvalue=' || lvalue);
      RAISE;
    WHEN others THEN
      deb('setConfig - this_db_unique_name='||this_db_unique_name||
          ', conf#='||conf#);
      RAISE;
END;
 
--
--
FUNCTION setConfig3(name            IN VARCHAR2
                    ,value           IN VARCHAR2
                    ,db_unique_name  IN VARCHAR2) 
  RETURN NUMBER IS
  lname     conf.name%TYPE NOT NULL := name;
  lvalue    conf.value%TYPE NOT NULL := value;
  ldbuname  conf.db_unique_name%TYPE NOT NULL := upper(db_unique_name);
  lsite_key NUMBER;
  lconf_key NUMBER;
BEGIN
 
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  deb('setConfig3 - Remote setConfig for '||ldbuname);
 
  SELECT site_key into lsite_key from node 
         where node.db_unique_name = ldbuname AND
         node.db_key = this_db_key;
  deb('setConfig3 - remote_site_key='||lsite_key);
 
--
--
  INSERT INTO conf(db_key, conf#, name, value,  cleanup, db_unique_name,
                   site_key)
      VALUES(this_db_key, rman_seq.nextval, name, value, 'NO', ldbuname,
             lsite_key)
  RETURNING conf# INTO lconf_key;
 
  UPDATE node SET node.force_resync2cf = 'YES'
    WHERE node.db_key = this_db_key
      AND node.db_unique_name = ldbuname;
 
  commitChanges('setConfig3');
  deb('setConfig3 - Added name=(' || lname ||
      '), value=(' || lvalue ||
      ') to node ' || ldbuname ||
      '('|| lconf_key ||')');
 
  RETURN lconf_key;
 
EXCEPTION
  WHEN OTHERS THEN
    deb('setConfig3 - rollback all, release locks');
    ROLLBACK;
    RAISE;
END;
 
--
--
PROCEDURE deleteConfig3(conf#        IN NUMBER
                    ,db_unique_name  IN VARCHAR2) IS
BEGIN
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
--
--
--
  DELETE conf
  WHERE  conf.db_key = this_db_key AND
         conf.db_unique_name = deleteConfig3.db_unique_name AND
         conf.conf# = deleteConfig3.conf#;
 
  IF sql%rowcount <> 1 AND sql%rowcount <> 0 THEN
     raise_application_error(-20999,
            'Internal error in deleteConfig3, deleted rows= ' || sql%rowcount);
  END IF;
 
  UPDATE node SET node.force_resync2cf = 'YES'
    WHERE node.db_key = this_db_key
      AND node.db_unique_name = deleteconfig3.db_unique_name;
 
  commitChanges('deleteConfig3');
EXCEPTION
  WHEN OTHERS THEN
    deb('deleteConfig3 - rollback all, release locks');
    ROLLBACK;
    RAISE;
END;
 
PROCEDURE resetConfig IS
BEGIN
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  DELETE conf
  WHERE  conf.db_key = this_db_key;
 
EXCEPTION
  WHEN NO_DATA_FOUND THEN
--
  RETURN;
END resetConfig;
 
PROCEDURE resetConfig2 (nodespec IN BOOLEAN, high_conf_recid IN NUMBER DEFAULT NULL) IS
BEGIN
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
--
--
--
  DELETE conf
  WHERE  conf.db_key = this_db_key AND conf.cleanup = 'YES';
 
--
--
--
  IF (nodespec)
  THEN
    DELETE conf
    WHERE  conf.db_key = this_db_key AND
           conf.db_unique_name = this_db_unique_name;
  ELSE
 
--
--
--
    force_resync2cf := 'YES';
 
    DELETE conf
    WHERE  conf.db_key = this_db_key AND
           conf.db_unique_name IS NULL;
  END IF;
 
  IF high_conf_recid IS NOT NULL THEN
     last_conf_recid := high_conf_recid;
     deb('resetConfig2 - updated last_conf_recid=' || last_conf_recid);
  END IF;
 
EXCEPTION
  WHEN NO_DATA_FOUND THEN
--
  RETURN;
END resetConfig2;
 
PROCEDURE deleteConfig(conf#            IN NUMBER) IS
BEGIN
--
--
  raise_application_error(-20999,
         'Internal error in deleteConfig should not be called ');
END;
 
/*-------------------------*
 * Catalog upgrade support *
 *-------------------------*/
 
/* NOTES:
 *
 * These procedures *must* tolerate being called *before* dbinc_key
 * has been set.
 */
 
/*-------------------*
 * Utility functions *
 *-------------------*/
 
PROCEDURE bsStatusRecalc(status IN varchar2) IS
 
cursor bsQ(status varchar2) IS
  SELECT bs_key
  FROM bs
  WHERE bs.status = bsStatusRecalc.status
--
  AND bs.db_key = this_db_key;
 
bsQrec bsQ%ROWTYPE;
 
BEGIN
 
  IF this_is_ors AND this_ckp_key IS NULL THEN
     this_enable_populate_rsr := getValueFromConfig('_enable_populate_rsr_key');
  END IF;
 
  FOR bsQrec in bsQ(status) LOOP
    updateBackupSetRec(bsQrec.bs_key);
  END LOOP;
--
--
  IF this_is_ors AND this_ckp_key IS NULL THEN
     this_enable_populate_rsr := NULL;
     this_upstream_site_key := NULL;
  END IF;
 
END;
 
PROCEDURE reNormalize(newname IN varchar2, oldname OUT varchar2) IS
BEGIN
   IF newname IS NULL THEN -- initialize
      IF reNorm_dfatt_c%ISOPEN THEN
         CLOSE reNorm_dfatt_c;
      END IF;
      IF reNorm_orl_c%ISOPEN THEN
         CLOSE reNorm_orl_c;
      END IF;
      IF reNorm_al_c%ISOPEN THEN
         CLOSE reNorm_al_c;
      END IF;
      IF reNorm_bp_c%ISOPEN THEN
         CLOSE reNorm_bp_c;
      END IF;
      IF reNorm_ccf_c%ISOPEN THEN
         CLOSE reNorm_ccf_c;
      END IF;
      IF reNorm_cdf_c%ISOPEN THEN
         CLOSE reNorm_cdf_c;
      END IF;
      IF reNorm_tfatt_c%ISOPEN THEN
         CLOSE reNorm_tfatt_c;
      END IF;
 
      reNorm_state := RENORM_DFATT;
   ELSE -- update the previous row
      IF reNorm_state = RENORM_DFATT THEN
         UPDATE site_dfatt SET fname = newname WHERE CURRENT OF reNorm_dfatt_c;
      ELSIF reNorm_state = RENORM_ORL THEN
         UPDATE orl SET fname = newname WHERE CURRENT OF reNorm_orl_c;
      ELSIF reNorm_state = RENORM_AL THEN
         UPDATE al SET fname = newname,
           fname_hashkey = substr(newname,1,10) || substr(newname, -10)
           WHERE CURRENT OF reNorm_al_c;
      ELSIF reNorm_state = RENORM_BP THEN
         UPDATE bp SET handle = newname,
           handle_hashkey = substr(device_type,1,10) ||
                            substr(newname,1,10)     ||
                            substr(newname,-10)
           WHERE CURRENT OF reNorm_bp_c;
      ELSIF reNorm_state = RENORM_CCF THEN
         UPDATE ccf SET fname = newname,
           fname_hashkey = substr(newname,1,10) || substr(newname, -10)
           WHERE CURRENT OF reNorm_ccf_c;
      ELSIF reNorm_state = RENORM_CDF THEN
         UPDATE cdf SET fname = newname,
           fname_hashkey = substr(newname,1,10) || substr(newname, -10)
           WHERE CURRENT OF reNorm_cdf_c;
      ELSIF reNorm_state = RENORM_TFATT THEN
         UPDATE site_tfatt SET fname = newname WHERE CURRENT OF reNorm_tfatt_c;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_DFATT THEN
      IF NOT reNorm_dfatt_c%ISOPEN THEN
         OPEN reNorm_dfatt_c;
      END IF;
 
      FETCH reNorm_dfatt_c INTO oldname;
 
      IF reNorm_dfatt_c%NOTFOUND THEN
         CLOSE reNorm_dfatt_c;
         reNorm_state := RENORM_ORL;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_ORL THEN
      IF NOT reNorm_orl_c%ISOPEN THEN
         OPEN reNorm_orl_c;
      END IF;
 
      FETCH reNorm_orl_c INTO oldname;
 
      IF reNorm_orl_c%NOTFOUND THEN
         CLOSE reNorm_orl_c;
         reNorm_state := RENORM_AL;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_AL THEN
      IF NOT reNorm_al_c%ISOPEN THEN
         OPEN reNorm_al_c;
      END IF;
 
      FETCH reNorm_al_c INTO oldname;
 
      IF reNorm_al_c%NOTFOUND THEN
         CLOSE reNorm_al_c;
         reNorm_state := RENORM_BP;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_BP THEN
      IF NOT reNorm_bp_c%ISOPEN THEN
         OPEN reNorm_bp_c;
      END IF;
 
      FETCH reNorm_bp_c INTO oldname;
 
      IF reNorm_bp_c%NOTFOUND THEN
         CLOSE reNorm_bp_c;
         reNorm_state := RENORM_CCF;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_CCF THEN
      IF NOT reNorm_ccf_c%ISOPEN THEN
         OPEN reNorm_ccf_c;
      END IF;
 
      FETCH reNorm_ccf_c INTO oldname;
 
      IF reNorm_ccf_c%NOTFOUND THEN
         CLOSE reNorm_ccf_c;
         reNorm_state := RENORM_CDF;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_CDF THEN
      IF NOT reNorm_cdf_c%ISOPEN THEN
         OPEN reNorm_cdf_c;
      END IF;
 
      FETCH reNorm_cdf_c INTO oldname;
 
      IF reNorm_cdf_c%NOTFOUND THEN
         CLOSE reNorm_cdf_c;
         reNorm_state := RENORM_TFATT;
      END IF;
   END IF;
 
   IF reNorm_state = RENORM_TFATT THEN
      IF NOT reNorm_tfatt_c%ISOPEN THEN
         OPEN reNorm_tfatt_c;
      END IF;
 
      FETCH reNorm_tfatt_c INTO oldname;
 
      IF reNorm_tfatt_c%NOTFOUND THEN
         CLOSE reNorm_tfatt_c;
         reNorm_state := NULL;
         oldname := NULL;
         commitChanges('reNormalize');
      END IF;
   END IF;
END reNormalize;
 
 
 
 
--
--
--
 
--
PROCEDURE cleanupResyncedBS;
PROCEDURE cleanupCKP;
PROCEDURE cleanupRLH;
PROCEDURE cleanupRSR;
PROCEDURE cleanupBV;
PROCEDURE cleanupROUT;
PROCEDURE cleanupNRS;
PROCEDURE cleanupDO;
PROCEDURE sanityCheck IS
BEGIN
 
  cleanupResyncedBS;
  cleanupCKP;
  cleanupRLH;
  cleanupRSR;
  cleanupBV;
  cleanupROUT;
  cleanupNRS;
  cleanupDO;
 
END sanityCheck;
 
PROCEDURE cleanupDO IS
  start_time     DATE := sysdate;
BEGIN
 
--
   DELETE deleted_object 
      WHERE db_key = this_db_key
        AND create_time < start_time - 30;
 
   deb('cleanupDO - deleted ' || sql%rowcount || ' rows from deleted_object');
   deb('cleanupDO - took ' || ((sysdate - start_time) * 86400) || ' seconds');
 
END cleanupDO;
 
PROCEDURE cleanupResyncedBS IS
   cnt    number;
BEGIN
 
--
--
--
--
 
  deb('cleanupResyncedBS - cntbs='||cntbs);
 
  IF cntbs is NULL THEN
    raise_application_error(-20107, 'invalid bskey counter');
  END IF;
 
  FOR i IN 1 .. cntbs LOOP
    SELECT count(*) into cnt from bs where bs_key = updatebs(i);
    IF cnt > 0 THEN
      deb('cleanupResyncedBS - updating bs_key='||updatebs(i));
      updateBackupSetRec(updatebs(i));
    END IF;
  END LOOP;
 
  cntbs := 0;
 
END cleanupResyncedBS;
 
PROCEDURE cleanupCKP IS
  scn            NUMBER;
  seq            NUMBER;
  keep_ckp_key_1 NUMBER;
  keep_ckp_key_2 NUMBER;
  start_time     DATE := sysdate;
BEGIN
 
--
--
--
--
 
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  dbms_rcvman.getCheckpoint(scn, seq, keep_ckp_key_1, keep_ckp_key_2);
 
  deb('cleanupCKP - scn=' || scn);
  deb('cleanupCKP - seq=' || seq);
  deb('cleanupCKP - keep_ckp_key_1=' || keep_ckp_key_1);
  deb('cleanupCKP - keep_ckp_key_2=' || keep_ckp_key_2);
 
--
--
--
 
  delete from ckp where dbinc_key = this_dbinc_key and ckp_key in
    (select ckp_key1 from
     (select ckp_key ckp_key1 from ckp where dbinc_key = this_dbinc_key) ckp1,
     (select keep_ckp_key_1 ckp_key2 from dual union
      select keep_ckp_key_2 from dual union
      select nvl(max(ckp_key),0) from ckp where dbinc_key=this_dbinc_key union
      select start_ckp_key from tsatt where dbinc_key = this_dbinc_key union
      select nvl(end_ckp_key,0) from tsatt where dbinc_key = this_dbinc_key)
     ckp2
     where ckp_key1 = ckp_key2(+) and ckp_key2 is null) and
    site_key = this_site_key;
 
   deb('cleanupCKP - deleted ' || sql%rowcount || ' rows from ckp table');
   deb('cleanupCKP - took ' || ((sysdate - start_time) * 86400) || ' seconds');
 
END cleanupCKP;
 
PROCEDURE cleanupRLH IS
  oldscn         NUMBER;
  start_time     DATE := sysdate;
BEGIN
 
--
--
--
--
--
 
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  SELECT nvl(min(scn),power(2,64)-1)
    INTO oldscn
    FROM
        (
         SELECT min(brl.low_scn) scn
           FROM brl
          WHERE brl.dbinc_key = this_dbinc_key
         UNION
         SELECT min(al.low_scn)
           FROM al
          WHERE al.dbinc_key = this_dbinc_key
         UNION
         SELECT min(xal.low_scn)
           FROM xal
          WHERE xal.dbinc_key = this_dbinc_key
         UNION
         SELECT min(bdf.ckp_scn)
           FROM bdf
          WHERE bdf.dbinc_key = this_dbinc_key
         UNION
         SELECT min(cdf.ckp_scn)
           FROM cdf
          WHERE cdf.dbinc_key = this_dbinc_key
         UNION
         SELECT min(xdf.ckp_scn)
           FROM xdf
          WHERE xdf.dbinc_key = this_dbinc_key
         UNION
         SELECT min(bcf.ckp_scn)
           FROM bcf
          WHERE bcf.dbinc_key = this_dbinc_key
         UNION
         SELECT min(ccf.ckp_scn)
           FROM ccf
          WHERE ccf.dbinc_key = this_dbinc_key
         UNION
         SELECT min(xcf.ckp_scn)
           FROM xcf
          WHERE xcf.dbinc_key = this_dbinc_key
           );
 
  deb('cleanupRLH - scn='||oldscn);
 
  DELETE
    FROM rlh
   WHERE rlh.dbinc_key = this_dbinc_key
     AND low_scn < oldscn;
 
   deb('cleanupRLH - deleted ' || sql%rowcount || ' rows from rlh table');
   deb('cleanupRLH - took ' || ((sysdate - start_time) * 86400) || ' seconds');
 
END cleanupRLH;
 
PROCEDURE cleanupBV IS
  start_time DATE := sysdate;
BEGIN
 
--
--
--
 
   DELETE FROM bs
     WHERE db_key = this_db_key
       AND ((input_file_scan_only='YES' AND SYSDATE - completion_time >= 60)
            OR
            (nvl(input_file_scan_only,'NO')='NO' AND status='D'))
       AND NOT EXISTS (SELECT 1 FROM bp
                        WHERE bp.bs_key = bs.bs_key);
 
   deb('cleanupBV - deleted ' || sql%rowcount || ' rows from bs table');
   deb('cleanupBV - took ' || ((sysdate - start_time) * 86400) || ' seconds');
 
END cleanupBV;
 
FUNCTION getDbid RETURN NUMBER IS
  dbid   NUMBER;
BEGIN
  SELECT db.db_id
    INTO dbid
    FROM db
   WHERE db_key = this_db_key
     AND curr_dbinc_key = this_dbinc_key;
  RETURN dbid;
  EXCEPTION
     WHEN no_data_found THEN
        raise_application_error(-20001, 'Database not found');
END getDbid;
 
FUNCTION beginIncarnationResync(return_Recid in boolean DEFAULT FALSE)
RETURN NUMBER IS
local_kccdivts number;
BEGIN
  checkResync;
 
  IF return_Recid THEN
    IF (this_cf_type = 'CURRENT' OR
        (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
       SELECT high_ic_recid INTO last_ic_recid
       FROM node
       WHERE site_key = this_site_key;
    ELSE
       last_ic_recid := sessionWaterMarks.high_ic_recid;
    END IF;
 
    RETURN last_ic_recid;
  ELSE
    IF (this_cf_type = 'CURRENT' OR
        (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
       SELECT last_kccdivts INTO local_kccdivts
       FROM node
       WHERE site_key = this_site_key;
    ELSE
       local_kccdivts := 0;
    END IF;
 
    IF (local_kccdivts IS NULL) THEN
       local_kccdivts := 0;
    END IF;
    RETURN local_kccdivts;
  END IF;
 
END beginIncarnationResync;
 
 
--
--
--
 
FUNCTION checkIncarnation(reset_scn         IN NUMBER,
                          reset_time        IN DATE,
                          prior_reset_scn   IN NUMBER DEFAULT NULL,
                          prior_reset_time  IN DATE DEFAULT NULL,
                          db_name           IN VARCHAR2 DEFAULT 'UNKNOWN')
                        RETURN NUMBER IS
local             dbinc%rowtype;
prior_dbinc_key   number := NULL;
d_name            VARCHAR2(8);
 
BEGIN
 
  BEGIN
    SELECT dbinc_key, parent_dbinc_key, db_name
       INTO local.dbinc_key, local.parent_dbinc_key, local.db_name
    FROM dbinc
    WHERE dbinc.db_key = this_db_key
    AND   dbinc.reset_scn = checkIncarnation.reset_scn
    AND   dbinc.reset_time = checkIncarnation.reset_time;
  EXCEPTION
    WHEN no_data_found THEN
      local.dbinc_key := NULL;
      local.parent_dbinc_key := NULL;
      local.db_name := 'UNKNOWN';
  END;
 
  IF (local.parent_dbinc_key IS NULL AND
      checkIncarnation.prior_reset_scn IS NOT NULL) THEN
     BEGIN
        SELECT dbinc_key
           INTO prior_dbinc_key
        FROM dbinc
        WHERE dbinc.db_key = this_db_key
        AND   dbinc.reset_scn = checkIncarnation.prior_reset_scn
        AND   dbinc.reset_time = checkIncarnation.prior_reset_time;
     EXCEPTION
       WHEN no_data_found THEN
          prior_dbinc_key := NULL;
     END;
  END IF;
 
  IF (local.dbinc_key IS NOT NULL) THEN
--
    IF (local.parent_dbinc_key IS NULL AND
        prior_dbinc_key IS NOT NULL) THEN
      UPDATE dbinc SET parent_dbinc_key = prior_dbinc_key
      WHERE dbinc.dbinc_key = local.dbinc_key;
    END IF;
 
--
    IF (local.db_name != 'UNKNOWN' AND
        checkIncarnation.db_name != 'UNKNOWN') THEN
      UPDATE dbinc SET db_name = checkIncarnation.db_name
      WHERE dbinc.dbinc_key = local.dbinc_key;
    END IF;
 
    RETURN local.dbinc_key;
  END IF;
 
  IF (this_lock_ors_inspect) THEN
--
--
    deb('checkincarnation:server_error for db_key='||this_db_key);
    EXECUTE IMMEDIATE 'BEGIN  dbms_ra_scheduler.log_error(
            p_component => ''INSPECT'',
            p_severity  => dbms_ra_scheduler.SEVERITY_WARNING,
            p_db_key => :1,
            p_keep_stack => TRUE,
            p_errno => dbms_ra_scheduler.E_NEW_INC_ERROR_NUM); END;'
            USING this_db_key ;
  END IF;
--
  BEGIN
--
    d_name := checkIncarnation.db_name;
    IF (d_name = 'UNKNOWN') THEN
       BEGIN
          SELECT db_name
             INTO d_name
          FROM db, dbinc
          WHERE dbinc.db_key = this_db_key
            AND db.curr_dbinc_key = dbinc.dbinc_key;
          EXCEPTION
             WHEN no_data_found THEN
                deb('database name not set');
                d_name := checkIncarnation.db_name;
       END;
    END IF;
    
    INSERT INTO dbinc
      (dbinc_key, db_key, db_name, reset_scn, reset_time, parent_dbinc_key)
    VALUES
      (rman_seq.nextval, this_db_key,
       upper(d_name), checkIncarnation.reset_scn,checkIncarnation.reset_time,
       prior_dbinc_key)
    RETURNING dbinc_key INTO local.dbinc_key;
  EXCEPTION
    WHEN dup_val_on_index THEN
      raise_application_error(-20009, 'Db incarnation already registered');
  END;
 
  inheritPdbInc(
     this_db_key, local.dbinc_key, reset_scn, prior_dbinc_key);
  RETURN local.dbinc_key;
END checkIncarnation;
 
PROCEDURE endIncarnationResync(high_kccdivts IN NUMBER,
                               high_ic_recid IN NUMBER DEFAULT 0) IS
BEGIN
 
--
--
  IF (last_ic_recid IS NOT NULL) THEN
    IF (this_cf_type = 'CURRENT' OR
        (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
--
       UPDATE node SET high_ic_recid = endIncarnationResync.high_ic_recid,
                       last_kccdivts = endIncarnationResync.high_kccdivts
              WHERE site_key = this_site_key;
    END IF;
    sessionWaterMarks.high_ic_recid := high_ic_recid;
    last_ic_recid := NULL;
  ELSE
    IF (this_cf_type = 'CURRENT' OR
        (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
       UPDATE node SET last_kccdivts = high_kccdivts
       WHERE site_key = this_site_key;
    END IF;
  END IF;
 
--
  recomputeDbincStatus(this_db_key, this_dbinc_key);
 
--
--
  IF NOT this_lock_ors_inspect THEN
   this_clr_ba_newinc_err := TRUE;
  END IF;
 
END endIncarnationResync;
 
/*--------------------------------*
 * Pluggable DB Incaration Resync *
 *--------------------------------*/
 
PROCEDURE fetchPic IS                   -- this is private to the pkg body
BEGIN
  FETCH picQ INTO picRec;               -- get next row
  IF picQ%NOTFOUND THEN
    picRec      := NULL;
    picRec.guid := NULL;                -- indicate end of fetch
    CLOSE picQ;
  ELSE
    deb('fetchPic - '||picRec.con_id||'('||to_char(picRec.con_id)||') '||
        to_char(picRec.pdbinc_key));
  END IF;
END fetchPic;
 
FUNCTION beginPluggableDbincResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_pic_recid INTO last_pic_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_pic_recid := sessionWaterMarks.high_pic_recid;
  END IF;
 
  IF (picQ%ISOPEN) THEN
     CLOSE picQ;
  END IF;
 
  OPEN picQ;                          -- just open that cursor please
  fetchPic;                           -- do priming read
 
  RETURN last_pic_recid;
END beginPluggableDbincResync;
 
PROCEDURE checkPluggableDbinc(
  recid               IN NUMBER
 ,guid                IN RAW
 ,curr_pdbinc         IN VARCHAR2
 ,inc_scn             IN NUMBER
 ,begin_reset_scn     IN NUMBER
 ,begin_reset_time    IN DATE
 ,end_reset_scn       IN NUMBER
 ,db_reset_scn        IN NUMBER
 ,db_reset_time       IN DATE
 ,pr_inc_scn          IN NUMBER
 ,pr_end_reset_scn    IN NUMBER
 ,pr_db_reset_scn     IN NUMBER
 ,pr_db_reset_time    IN DATE
 ,chk_last_recid      IN BOOLEAN
) IS
   local_pdb_key       number;
   local_pdbinc_key    number;
   local_pr_pdbinc_key number;
   born_dbinc_key      number;
   pr_born_dbinc_key   number;
   pr_pdbinc_key       number;
BEGIN
  IF (chk_last_recid) THEN
     IF (last_pic_recid IS NULL) THEN
       raise_application_error(-20037, 'Invalid last recid');
     END IF;
 
--
--
--
--
     last_pic_recid := recid;
  END IF;
 
  IF (end_reset_scn = 0) THEN
     deb('checkPluggableDbinc - skipping partial record');
     RETURN;
  END IF;
 
--
  WHILE (guid > picRec.guid)
  LOOP
    fetchPic;
  END LOOP;
 
--
  WHILE (guid = picRec.guid  AND
         (begin_reset_scn  > picRec.begin_reset_scn   OR
          begin_reset_time > picRec.begin_reset_time  OR
          end_reset_scn    > picRec.end_reset_scn))
  LOOP
     fetchPic;
  END LOOP;
 
--
  IF (guid             = picRec.guid             AND
      begin_reset_scn  = picRec.begin_reset_scn  AND
      begin_reset_time = picRec.begin_reset_time AND 
      end_reset_scn    = picRec.end_reset_scn) THEN
 
     deb('checkPluggableDbinc - pdbinc already known');
 
--
     local_pdb_key       := picRec.pdb_key;
     local_pdbinc_key    := picRec.pdbinc_key;
     local_pr_pdbinc_key := picRec.parent_pdbinc_key;
  END IF;
 
  IF (local_pr_pdbinc_key IS NULL) THEN
--
     IF (local_pdb_key IS NULL) THEN
        local_pdb_key := guidToPdbKey(checkPluggableDbinc.guid, 0);
        deb('checkPluggableDbinc - pdb_key=' || local_pdb_key);
     END IF;
 
     IF (pr_db_reset_scn = 0 OR pr_end_reset_scn = 0) THEN
        pr_pdbinc_key := NULL;
        deb('checkPluggableDbinc - no parent_pdbinc_key');
     ELSE
--
        pr_born_dbinc_key :=
           checkIncarnation(pr_db_reset_scn, pr_db_reset_time);
        deb('checkPluggableDbinc - pr_born_dbinc_key=' || pr_born_dbinc_key);
 
        BEGIN
           SELECT pdbinc_key
             INTO pr_pdbinc_key
             FROM pdbinc
            WHERE pdbinc.born_dbinc_key = pr_born_dbinc_key
              AND pdbinc.pdb_key        = local_pdb_key
              AND pdbinc.end_reset_scn  = pr_end_reset_scn;
        EXCEPTION
           WHEN no_data_found THEN
              pr_pdbinc_key := NULL;
        END;
 
        deb('checkPluggableDbinc - parent_pdbinc_key=' || pr_pdbinc_key);
     END IF;
  END IF;
 
  IF (local_pdbinc_key IS NULL) THEN
     born_dbinc_key := checkIncarnation(db_reset_scn, db_reset_time);
     deb('checkPluggableDbinc - born_dbinc_key=' || born_dbinc_key);
 
--
     INSERT INTO pdbinc
       (pdbinc_key, pdb_key, born_dbinc_key, inc_scn, begin_reset_scn,
        begin_reset_time, end_reset_scn, parent_pdbinc_key)
     VALUES
       (rman_seq.nextval, local_pdb_key, born_dbinc_key, inc_scn,
        begin_reset_scn, begin_reset_time, end_reset_scn, pr_pdbinc_key)
     RETURNING pdbinc_key INTO local_pdbinc_key;
  ELSE
     IF (local_pr_pdbinc_key IS NULL AND pr_pdbinc_key IS NOT NULL) THEN
        UPDATE pdbinc SET parent_pdbinc_key = pr_pdbinc_key
         WHERE pdbinc_key = local_pdbinc_key;
     END IF;
  END IF;
 
  IF (curr_pdbinc = 'YES') THEN
     UPDATE pdb_dbinc
        SET curr_pdbinc_key = local_pdbinc_key,
            drop_scn = NULL
      WHERE dbinc_key = this_dbinc_key
        AND pdb_key   = local_pdb_key;
 
     IF (sql%rowcount = 0) THEN 
        INSERT INTO pdb_dbinc
           (dbinc_key, pdb_key, drop_scn, drop_time, curr_pdbinc_key)
        VALUES
           (this_dbinc_key, local_pdb_key, NULL, NULL, local_pdbinc_key);
     END IF;
  END IF;
END checkPluggableDbinc;
 
PROCEDURE endPluggableDbincResync(high_pic_recid IN NUMBER) IS
BEGIN
  IF (last_pic_recid IS NOT NULL) THEN
     IF (this_cf_type = 'CURRENT' OR
         (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
        UPDATE node SET high_pic_recid = endPluggableDbincResync.high_pic_recid
        WHERE site_key = this_site_key;
     END IF;
 
     sessionWaterMarks.high_pic_recid := high_pic_recid;
     last_pic_recid := NULL;
  END IF;
 
--
  recomputePluggableDbincStatus(this_dbinc_key);
 
  IF (picQ%ISOPEN) THEN
     picRec.guid := NULL;         -- indicate end of fetch
     CLOSE picQ;
  END IF;
END endPluggableDbincResync;
 
/*-----------------------------*
 * Normal restore point Resync *
 *-----------------------------*/
 
FUNCTION beginRestorePointResync RETURN NUMBER IS
BEGIN
  checkResync;
 
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     SELECT high_nrsp_recid INTO last_nrsp_recid
     FROM node
     WHERE site_key = this_site_key;
  ELSE
     last_nrsp_recid := sessionWaterMarks.high_nrsp_recid;
  END IF;
 
  RETURN last_nrsp_recid;
END beginRestorePointResync;
 
PROCEDURE checkRestorePoint(
  nrsp_recid    IN NUMBER
 ,nrsp_stamp    IN NUMBER
 ,nrsp_name     IN VARCHAR2
 ,reset_scn     IN NUMBER
 ,reset_time    IN DATE
 ,to_scn        IN NUMBER
 ,nrsp_time     IN DATE
 ,create_time   IN DATE
 ,deleted       IN NUMBER
 ,con_id        IN NUMBER   DEFAULT NULL
 ,clean         IN VARCHAR2 DEFAULT 'NO'
) IS
   my_dbinc_key  NUMBER;
   inscheck      NUMBER;
   local_pdb_key NUMBER;
BEGIN
  IF (last_nrsp_recid IS NULL) THEN
    raise_application_error(-20037, 'Invalid last recid');
  END IF;
 
  IF (nrsp_recid < last_nrsp_recid) THEN
    raise_application_error(-20036, 'Invalid record order');
  END IF;
 
  IF (nrsp_recid > last_nrsp_recid + 1) THEN
--
--
    NULL;
  END IF;
  last_nrsp_recid := nrsp_recid;
 
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
  IF (nrsp_stamp > 0 and nrsp_stamp < kccdivts) THEN
     deb('checkRestorePoint - ignoring record kccdivts='||kccdivts);
     RETURN;                    -- obsolete record from a backup controlfile
  END IF;
 
--
  my_dbinc_key := checkIncarnation(reset_scn, reset_time);
 
--
--
  SELECT pdb.pdb_key INTO local_pdb_key
    FROM pdb, pdb_dbinc
   WHERE pdb_dbinc.drop_scn IS NULL
     AND pdb.con_id  IN
         (checkRestorePoint.con_id,
          0, 
          decode(checkRestorePoint.con_id, 0, 1))
     AND pdb.pdb_key         = pdb_dbinc.pdb_key
     AND pdb_dbinc.dbinc_key = this_dbinc_key;
 
--
  IF (deleted = 1)
  THEN
    DELETE nrsp
    WHERE checkRestorePoint.nrsp_recid = nrsp_recid
      AND checkRestorePoint.nrsp_stamp = nrsp_stamp
      AND my_dbinc_key = nrsp.dbinc_key
      AND this_site_key = site_key;
  ELSE
--
--
    DELETE nrsp
    WHERE this_site_key = nrsp.site_key
      AND local_pdb_key = nrsp.pdb_key
      AND checkRestorePoint.nrsp_name = nrsp.rspname;
 
    IF SQL%ROWCOUNT > 0 THEN
       deb('checkRestorePoint:deleted duplicate restore point:' 
           || checkRestorePoint.nrsp_name);
    END IF;
 
    INSERT INTO nrsp
        (nrsp_recid
        ,nrsp_stamp
        ,rspname
        ,dbinc_key
        ,site_key
        ,to_scn
        ,rsptime
        ,creation_time
        ,long_term
        ,pdb_key
        ,clean)
    VALUES
        (checkRestorePoint.nrsp_recid
        ,checkRestorePoint.nrsp_stamp
        ,checkRestorePoint.nrsp_name
        ,my_dbinc_key
        ,this_site_key
        ,checkRestorePoint.to_scn
        ,checkRestorePoint.nrsp_time
        ,checkRestorePoint.create_time
        ,NULL   -- UNKNOWN: cleanupNRS will reset to YES/NO
        ,local_pdb_key
        ,clean);
  END IF;
 
  EXCEPTION
      WHEN dup_val_on_index THEN
        deb('checkRestorePoint - Inside dup_val_on_index exception for' ||
            ' recid ' || checkRestorePoint.nrsp_recid ||
            ' stamp ' || checkRestorePoint.nrsp_stamp);
        SELECT min(nrsp.nrsp_recid) INTO inscheck
        FROM nrsp
        WHERE nrsp.nrsp_recid = checkRestorePoint.nrsp_recid
          AND nrsp.nrsp_stamp = checkRestorePoint.nrsp_stamp
          AND nrsp.dbinc_key = my_dbinc_key
          AND nrsp.site_key = this_site_key
          AND nrsp.rspname = checkRestorePoint.nrsp_name
          AND nrsp.to_scn = checkRestorePoint.to_scn
          AND nrsp.pdb_key = local_pdb_key;
        IF inscheck IS NULL THEN -- Some internal error to indicate no match
          raise_application_error(-20999,
                              'internal error: no match for restore point');
        END IF;
        RETURN;
      WHEN others THEN
         RAISE;
END checkRestorePoint;
 
PROCEDURE endRestorePointResync(lowrecid IN number) IS
   lowscn number;
BEGIN
--
  IF (lowrecid = 0)
  THEN
    low_nrsp_recid := NULL;
  ELSE
    low_nrsp_recid := lowrecid;
  END IF;
 
--
  IF (this_cf_type = 'CURRENT' OR
      (this_cf_type = 'STANDBY' AND this_db_unique_name is not null)) THEN
     UPDATE node SET high_nrsp_recid = last_nrsp_recid
     WHERE site_key = this_site_key;
  END IF;
 
  sessionWaterMarks.high_nrsp_recid := last_nrsp_recid;
  last_nrsp_recid := NULL;
END endRestorePointResync;
 
 
PROCEDURE listScriptNames(glob IN number,
                          allnames IN number) IS
   lglob number  := NULL;
   lalln number  := NULL;
BEGIN
    deb('listScriptNames - List script Names called with glob: '||
        nvl(to_char(glob), 'NULL')||'and allnames: '||
        nvl(to_char(allnames), 'NULL'));
    IF glob = 1 then
       lglob := 1;
    END IF;
    IF allnames = 1 then
       lalln := 1;
    END IF;
    IF lscrnames_c%ISOPEN THEN
       deb('listScriptNames - Closing lscrnames_c cursor');
       CLOSE lscrnames_c;
    END IF;
    deb('listScriptNames - Opening lscrnames_c cursor');
    OPEN lscrnames_c(lglob, lalln);
END listScriptNames;
 
PROCEDURE getScriptNames(dbname  OUT varchar2,
                         scnm    OUT varchar2,
                         sccom   OUT varchar2) IS
   ldum  number  := NULL;
BEGIN
   IF NOT lscrnames_c%ISOPEN THEN
      raise_application_error(-20403, 'listScriptNames not done');
   END IF;
 
    deb('getScriptNames - Fetching lscrnames_c cursor');
   FETCH lscrnames_c
   INTO  ldum, dbname, scnm, sccom;
 
   IF lscrnames_c%NOTFOUND THEN
      deb('getScriptNames - Closing lscrnames_c cursor');
      CLOSE lscrnames_c;
      raise no_data_found;
   END IF;
END getScriptNames;
 
--
--
PROCEDURE cleanupRSR IS
  nowTime date;
BEGIN
 
  SELECT SYSDATE INTO nowTime from dual;
 
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
--
--
--
--
 
  DELETE FROM rsr
        WHERE rsr_end < nowTime-60 
          AND rsr.dbinc_key IN 
              (select dbinc_key from dbinc 
               where dbinc.db_key = this_db_key);
 
   deb('cleanupRSR - deleted ' || sql%rowcount || ' rows from rsr table');
   deb('cleanupRSR - took ' || ((sysdate - nowTime) * 86400) || ' seconds');
 
END cleanupRSR;
 
--
--
PROCEDURE cleanupROUT IS
  start_time       date;
  high_stamp       number;
  high_session_key number;
  days             number;
BEGIN
  IF (this_db_key IS NULL) THEN
    raise_application_error(-20021, 'Database not set');
  END IF;
 
  IF session_keep_output IS NULL THEN 
     getRmanOutputLogging(days);
     deb('cleanupROUT - keep output is configured to ' || days);
  ELSIF session_keep_output = 0 THEN
     deb('cleanupROUT - session keep output is set to 0, not cleaning up');
     return;
  ELSE
     days := session_keep_output;
     deb('cleanupROUT - session keep output is set to ' || days);
  END IF;
 
  start_time      := SYSDATE;
  high_stamp      := date2stamp(start_time-days);
 
  SELECT max(rsr_key) into high_session_key
    FROM rsr, dbinc
   WHERE dbinc.db_key = this_db_key
     AND rsr.dbinc_key = dbinc.dbinc_key
     AND rsr.site_key = this_site_key
     AND rsr.rsr_stamp < high_stamp;
 
  deb('cleanupROUT select took ' || ((sysdate - start_time) * 86400) ||
      ' seconds');
 
--
  If high_session_key IS NOT NULL THEN
     DELETE FROM rout
     WHERE  db_key     = this_db_key
       AND  (site_key IS NULL) or (site_key  = this_site_key)
       AND  rout_skey <= high_session_key;
     deb('cleanupROUT deleted ' || sql%rowcount || ' rows from rout table');
  END IF;
 
  deb('cleanupROUT took ' || ((sysdate - start_time) * 86400) || ' seconds');
 
END cleanupROUT;
 
--
PROCEDURE cleanupNRS IS
  start_time       date;
BEGIN
  deb('cleanupNRS - low_nrsp_recid is ' ||
      NVL(TO_CHAR(low_nrsp_recid), 'NULL'));
  start_time := SYSDATE;
 
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
  UPDATE nrsp SET LONG_TERM = 'YES'
  WHERE long_term IS NULL
   AND this_site_key = site_key
   AND nrsp_recid in
   (SELECT nrsp.nrsp_recid
    FROM bs, brl, nrsp
    WHERE bs.bs_key = brl.bs_key
      AND bs.keep_options > 0
      AND brl.low_scn <= nrsp.to_scn
      AND brl.next_scn > nrsp.to_scn
      AND this_site_key = bs.site_key
      AND this_site_key = nrsp.site_key
   UNION
    SELECT nrsp.nrsp_recid
    FROM xal, nrsp
    WHERE xal.keep_options > 0
      AND xal.low_scn <= nrsp.to_scn
      AND xal.next_scn > nrsp.to_scn
      AND this_site_key = xal.site_key
      AND this_site_key = nrsp.site_key
   UNION
    SELECT nrsp_recid
    FROM bs, bdf, nrsp
    WHERE bs.bs_key = bdf.bs_key
      AND bs.keep_options > 0
      AND bdf.ckp_scn = nrsp.to_scn+1
      AND this_site_key = bs.site_key
      AND this_site_key = nrsp.site_key
   UNION
    SELECT nrsp_recid
    FROM xdf, nrsp
    WHERE xdf.keep_options > 0
      AND xdf.ckp_scn = nrsp.to_scn+1
      AND this_site_key = xdf.site_key
      AND this_site_key = nrsp.site_key);
  deb('cleanupNRS - updated ' || sql%rowcount || ' rows to LONG_TERM = YES');
 
--
  UPDATE nrsp SET LONG_TERM = 'NO'
  WHERE long_term IS NULL
   AND this_site_key = site_key;
  deb('cleanupNRS - updated ' || sql%rowcount || ' rows to LONG_TERM = NO');
 
--
--
  DELETE nrsp WHERE nrsp_recid < low_nrsp_recid
                AND long_term = 'NO'
                AND site_key = this_site_key;
  low_nrsp_recid := NULL;
  deb('cleanupNRS - deleted ' || sql%rowcount || ' rows from nrsp table');
  deb('cleanupNRS - took ' || ((sysdate - start_time) * 86400) || ' seconds');
END;
 
PROCEDURE updateOldestFlashbackSCN (
   oldest_flashback_scn     IN NUMBER -- obsolete column
  ,oldest_flashback_time    IN DATE   DEFAULT NULL
) IS
  tmp    NUMBER;
BEGIN
 
   deb('updateOldestFlashbackSCN - guaranteed_flashback_scn=' ||
       nvl(to_char(oldest_flashback_scn), 'NULL') || ' flashback_time=' ||
       nvl(to_char(oldest_flashback_time), 'NULL'));
 
--
   IF (oldest_flashback_scn IS NULL AND oldest_flashback_time IS NULL) THEN
      DELETE FROM fb
       WHERE db_unique_name = this_db_unique_name
         AND dbinc_key      = this_dbinc_key;
      RETURN;
   END IF;
 
   BEGIN
      SELECT 0 INTO tmp
        FROM fb
       WHERE db_unique_name = this_db_unique_name
         AND dbinc_key      = this_dbinc_key;
   EXCEPTION
      WHEN no_data_found THEN
         INSERT INTO fb
            (dbinc_key, db_unique_name, oldest_flashback_scn,
             oldest_flashback_time)
         VALUES
            (this_dbinc_key, this_db_unique_name, oldest_flashback_scn,
             oldest_flashback_time);
         RETURN;
      WHEN others THEN
         RAISE;
   END;
 
   UPDATE fb SET
     oldest_flashback_scn =
        updateOldestFlashbackSCN.oldest_flashback_scn,
     oldest_flashback_time =
        updateOldestFlashbackSCN.oldest_flashback_time
   WHERE db_unique_name = this_db_unique_name
     AND dbinc_key      = this_dbinc_key;
END updateOldestFlashbackSCN;
 
FUNCTION getDbinc RETURN NUMBER IS
BEGIN
  IF (this_dbinc_key IS NULL) THEN
    raise_application_error(-20020, 'Database incarnation not set');
  END IF;
 
  RETURN this_dbinc_key;
END getDbinc;
 
--
--
--
FUNCTION isDuplicateRecord(recid    IN NUMBER
                          ,stamp    IN NUMBER
                          ,type     IN VARCHAR2) RETURN BOOLEAN IS
  rec_count NUMBER;
BEGIN
   checkResync;
 
   IF (type = 'AL') THEN
      SELECT count(*)
        INTO rec_count
        FROM al, dbinc
       WHERE dbinc.db_key = this_db_key
         AND al.dbinc_key = dbinc.dbinc_key
         AND isDuplicateRecord.recid = al.al_recid
         AND isDuplicateRecord.stamp = al.al_stamp
         AND al.site_key = this_site_key;
   ELSIF (type = 'BP') THEN
      SELECT count(*)
        INTO rec_count
        FROM bp
       WHERE bp.db_key = this_db_key
         AND isDuplicateRecord.recid = bp.bp_recid
         AND isDuplicateRecord.stamp = bp.bp_stamp
         AND bp.site_key = this_site_key;
   ELSIF (type = 'DC') THEN
      SELECT count(*)
        INTO rec_count 
        FROM cdf, dbinc
       WHERE dbinc.db_key = this_db_key
         AND cdf.dbinc_key = dbinc.dbinc_key
         AND isDuplicateRecord.recid = cdf.cdf_recid
         AND isDuplicateRecord.stamp = cdf.cdf_stamp
         AND cdf.site_key = this_site_key;
 
      IF (rec_count = 0) THEN
         SELECT count(*)
           INTO rec_count 
           FROM ccf, dbinc
          WHERE dbinc.db_key = this_db_key
            AND ccf.dbinc_key = dbinc.dbinc_key
            AND isDuplicateRecord.recid = ccf.ccf_recid
            AND isDuplicateRecord.stamp = ccf.ccf_stamp
            AND ccf.site_key = this_site_key;
      END IF;
   ELSE
      raise_application_error(-20999,
         'Internal error in isDuplicateRecord(): bad type '|| type);
   END IF;
 
   IF rec_count > 0 THEN
      RETURN TRUE;
   ELSE
      RETURN FALSE;
   END IF;
END isDuplicateRecord;
 
--
--
--
--
--
--
FUNCTION doDuplicateMining RETURN BOOLEAN IS
  last_recid number;
BEGIN
  checkResync;
 
  IF (this_cf_type != 'CURRENT' and this_cf_type != 'STANDBY') THEN
     RETURN TRUE;
  END IF;
 
--
--
  IF (this_cf_type = 'STANDBY' and this_db_unique_name is NULL) THEN
     RETURN TRUE;
  END IF;
 
--
--
--
  SELECT high_rlh_recid INTO last_recid
     FROM node
     WHERE site_key = this_site_key;
 
  IF (last_recid = 0) THEN
     deb('doDuplicateMining returns TRUE');
     RETURN TRUE;
  ELSE
     RETURN FALSE;
  END IF;
 
END doDuplicateMining;
 
--
FUNCTION isRoutDuplicateRecord(recid             IN NUMBER
                              ,stamp             IN NUMBER
                              ,session_recid     IN NUMBER
                              ,session_stamp     IN NUMBER
                              ,rman_status_recid IN NUMBER
                              ,rman_status_stamp IN NUMBER)
 
  RETURN BOOLEAN IS
  lrsrkey    NUMBER;
  lroutskey  NUMBER;
  rec_count  NUMBER;
BEGIN
   checkResync;
 
    deb('isRoutDuplicateRecord - Find rsr_ key');
    BEGIN
      select rsr_key into lrsrkey from rsr, dbinc where 
             rsr.dbinc_key = dbinc.dbinc_key and
             dbinc.db_key  = this_db_key and
             dbinc.dbinc_key = this_dbinc_key and
             ((rsr.site_key = this_site_key) or
              (rsr.site_key is null AND this_site_key is null)) and
             rsr.rsr_recid = rman_status_recid and
             rsr.rsr_stamp = rman_status_stamp;
    EXCEPTION
    WHEN no_data_found THEN
--
      deb('isRoutDuplicateRecord - ignoring following RMAN output row');
      RETURN FALSE;
    END;
 
 
    deb('isRoutDuplicateRecord - Find session key');
    BEGIN
--
--
--
--
--
      select rsr_key into lroutskey from rsr, dbinc where
             rsr.dbinc_key  = dbinc.dbinc_key and
             dbinc.db_key   = this_db_key and
             (rsr.site_key = this_site_key or
              rsr.site_key is null AND this_site_key is null) and
             rsr.rsr_srecid = session_recid and
             rsr.rsr_sstamp = session_stamp and
             rsr.rsr_type   = 'SESSION';
    EXCEPTION
    WHEN no_data_found THEN
--
      deb('isRoutDuplicateRecord -ignoring following RMAN output row, cause2');
      RETURN FALSE;
    WHEN others THEN
      deb('isRoutDuplicateRecord(DO_NOT_IGNORE) - signal err');
      RETURN FALSE;
    END;
 
--
   SELECT count(*)
     INTO rec_count
     FROM rout, db
    WHERE db.db_key = this_db_key
      AND rout.rout_recid = isRoutDuplicateRecord.recid
      AND rout.rout_stamp = isRoutDuplicateRecord.stamp
      AND rout.rsr_key    = lrsrkey
      AND rout.rout_skey  = lroutskey
      AND rout.site_key = this_site_key;
 
   IF rec_count > 0 THEN
    deb('isRoutDuplicateRecord - Return TRUE');
      RETURN TRUE;
   ELSE
    deb('isRoutDuplicateRecord - Return FALSE');
      RETURN FALSE;
   END IF;
END isRoutDuplicateRecord;
 
PROCEDURE unregisterSite(db_unique_name IN VARCHAR2,
                         incbcks        IN BINARY_INTEGER ) IS
 
  lsite_key        number;
  new_ckp_site_key number;
  cnt              number := 0;
  db_role          node.database_role%TYPE;
 
BEGIN
 
   deb('unregisterSite - remove meta-data for node '|| db_unique_name);
 
   IF (this_db_key IS NULL) THEN
      raise_application_error(-20021, 'Database not set');
   END IF;
 
--
   IF this_db_unique_name = upper(db_unique_name) THEN
      raise_application_error(-20244,
                              db_unique_name || 
                              ' can not unregister connected target database');
   END IF;
 
--
   BEGIN
      select site_key, database_role into lsite_key, db_role from node 
         where node.db_unique_name = upper(unregisterSite.db_unique_name)
           and node.db_key = this_db_key;
 
   EXCEPTION
      WHEN no_data_found THEN
         raise_application_error(
                             -20243,
                             upper(unregisterSite.db_unique_name) || 
                             ' db_unique_name unknown to recovery catalog:');
   END;
   lockForCkpt;
 
--
--
   select count(*) into cnt from bp
      where bp.site_key = lsite_key and bp.ba_access != 'U';
 
   IF (cnt <> 0) THEN
     cancelCkpt;
     raise_application_error(-20301, 'Cannot unregister database');
   END IF;
  
--
--
--
--
   select site_key into new_ckp_site_key from 
      (select site_key from node
         where db_key=this_db_key
           and site_key <> lsite_key
         order by database_role)
      where rownum = 1;
   IF new_ckp_site_key is not null THEN
      update ckp set site_key = new_ckp_site_key 
         where site_key = lsite_key
           and ckp_type = 'FULL'
           and ckp_key in
               (select start_ckp_key from tsatt 
                  where dbinc_key in 
                     (select dbinc_key from dbinc
                         where db_key=this_db_key)
                union
                select end_ckp_key from tsatt 
                  where dbinc_key in 
                     (select dbinc_key from dbinc
                         where db_key=this_db_key));
      deb('updated ' || sql%rowcount || ' rows in ckp, site_key to ' || 
          new_ckp_site_key);
   END IF;
 
--
   IF incbcks <> 0 THEN
      delete bp WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from bp table');
      delete bs WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from bs table');
      delete ccf WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from ccf table');
      delete xcf WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from xcf table');
      delete cdf WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from cdf table');
      delete xdf WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from xdf table');
      delete xal WHERE site_key = lsite_key;
      deb('deleted ' || sql%rowcount || ' rows from xal table');
   ELSE
      update bp set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from bp table');
      update bs set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from bs table');
      update ccf set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from ccf table');
      update xcf set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from xcf table');
      update cdf set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from cdf table');
      update xdf set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from xdf table');
      update xal set site_key = NULL WHERE site_key = lsite_key;
      deb('updated ' || sql%rowcount || ' rows from xal table');
   END IF;
 
--
   delete node where site_key = lsite_key;
   deb('deleted ' || sql%rowcount || ' rows from node table');
   delete fb
      where db_unique_name = unregisterSite.db_unique_name 
        and dbinc_key in 
            (select dbinc_key from dbinc where db_key = this_db_key);
   deb('deleted ' || sql%rowcount || ' rows from fb table');
 
  UPDATE db
    SET reg_db_unique_name =
      (SELECT max(db_unique_name) FROM
         (SELECT db_unique_name 
          FROM node, db
          WHERE node.db_key = this_db_key
            AND node.db_unique_name <> unRegisterSite.db_unique_name
            AND node.db_unique_name <> db.reg_db_unique_name
            AND node.db_unique_name NOT LIKE '%$%'
            AND node.database_role IN ('PRIMARY','STANDBY')
          ORDER BY node.database_role, node.db_unique_name)
          WHERE ROWNUM =1)
	WHERE db_key = this_db_key
	  AND reg_db_unique_name = unregisterSite.db_unique_name;
 
--
--
--
   delete conf 
     where name = 'DB_UNIQUE_NAME' 
       and db_key = this_db_key 
       and upper(unregisterSite.db_unique_name) =
           upper(substr(value, 2, instr(substr(value, 2, 32), 
                                               substr(value, 1,1))-1))
       and db_unique_name is null;
   deb('deleted ' || sql%rowcount || ' rows from conf table(2)');
 
--
--
   if sql%rowcount <> 0 then
      update node set force_resync2cf = 'YES'
         where db_key = this_db_key;
   end if;
 
--
   delete conf where site_key = lsite_key;
   deb('deleted ' || sql%rowcount || ' rows from conf table (site rows)');
   commitChanges('unregisterSite');
 
END unregisterSite;
 
--
PROCEDURE renameSite(from_db_unique_name IN VARCHAR2, 
                     to_db_unique_name IN VARCHAR2) IS
   rec_count NUMBER;
   my_dbinc_key NUMBER;
BEGIN
   deb('renameSite - rename meta-data from '|| from_db_unique_name ||
       ' to ' || to_db_unique_name);
 
--
   IF this_db_key IS NULL THEN
      BEGIN
         SELECT curr_dbinc_key into my_dbinc_key FROM db
         WHERE db_key = (SELECT db_key FROM node where
                            db_unique_name = upper(from_db_unique_name));
         setDatabase(my_dbinc_key);
      EXCEPTION
         WHEN no_data_found THEN
            raise_application_error(-20243,
                                    from_db_unique_name || 
                                    ' site unknown to recovery catalog:');
      END;
   END IF;
 
   IF (this_db_key IS NULL) THEN
      raise_application_error(-20021, 'Database not set');
   END IF;
 
--
   IF this_db_unique_name = upper(from_db_unique_name) THEN
      raise_application_error(-20244,
                              from_db_unique_name || 
                              ' can not rename connected target database');
   END IF;
 
--
   SELECT count(*) INTO rec_count FROM node 
      WHERE node.db_unique_name = upper(from_db_unique_name) 
        AND node.db_key = this_db_key;
 
   IF rec_count = 0 THEN
      raise_application_error(-20243,
                              from_db_unique_name || 
                              ' site unknown to recovery catalog:');
   END IF;
 
--
   SELECT count(*) INTO rec_count FROM node 
      WHERE node.db_unique_name = upper(to_db_unique_name) 
        AND node.db_key = this_db_key;
 
   IF rec_count = 1 THEN
      raise_application_error(-20246,
                              to_db_unique_name || 
                              ' site known to recovery catalog:');
   END IF;
 
   UPDATE NODE SET db_unique_name = upper(to_db_unique_name)
      WHERE db_unique_name = upper(from_db_unique_name)
        AND db_key = this_db_key;
   deb('renamed db_unique_name ' || sql%rowcount || ' row updated');
 
   UPDATE CONF SET db_unique_name = upper(to_db_unique_name)
      WHERE db_unique_name = upper(from_db_unique_name)
        AND db_key = this_db_key;
   deb('updated ' || sql%rowcount || ' rows in conf table');
 
--
   UPDATE FB SET db_unique_name = upper(to_db_unique_name)
      WHERE db_unique_name = upper(from_db_unique_name)
        AND dbinc_key IN 
            (select dbinc_key from dbinc where db_key = this_db_key);
   deb('updated ' || sql%rowcount || ' rows in fb table');
 
   commitChanges('renameSite');
 
END renameSite;
 
--
--
 
PROCEDURE resyncAddDBUname(cdbunstr IN varchar2) IS
     dbuname    node.db_unique_name%TYPE;
     numentries number;
 
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
    deb('resyncAddDBUname - cdbunstr = '|| cdbunstr);
    dbuname := substr(cdbunstr, 2, 30); -- strip out the first quote
--
--
    deb('resyncAddDBUname - dbuname before = '|| dbuname);
    dbuname := substr(dbuname, 1, instr(dbuname, substr(cdbunstr,1,1))-1);
 
    deb('resyncAddDBUname - db_unique_name = '|| dbuname);
 
--
--
    insert into node (db_unique_name, db_key, force_resync2cf, 
                      database_role, site_key)
    values(upper(dbuname), this_db_key, 'YES', 'STANDBY', rman_seq.nextval);
 
    deb('resyncAddDBUname - adding node row with value ' || dbuname);
 
EXCEPTION
   WHEN dup_val_on_index THEN
--
     RETURN;
 
END resyncAddDBUname;
 
 
--
FUNCTION getThisSiteKey(db_unique_name in VARCHAR2 DEFAULT NULL) 
   return NUMBER
IS
   ret_site_key number;
BEGIN
   deb('getThisSiteKey - This site key is '||this_site_key);
   if db_unique_name is not null then
      ret_site_key := dbms_rcvman.getSiteKey(db_unique_name);
   else
      ret_site_key := this_site_key;
   end if;
   deb('Returning site key is '||ret_site_key);
   return ret_site_key;
END getThisSiteKey;
 
--
FUNCTION  isAMSchema RETURN BOOLEAN IS
BEGIN
   return this_is_ors;
END isAMSchema;
 
--
FUNCTION  getAMTstlevel RETURN NUMBER IS
   am_tst_level number := 0;
BEGIN
   if this_is_ors then
      select nvl(max(value), 0) into am_tst_level from config 
         where name = '_oam_tst_level';
      deb('getAMTstlevel from config ='||am_tst_level);
   else
      deb('getAMTstlevel='||am_tst_level||' for non-OAM schema');
   end if;
   return am_tst_level;
END getAMTstlevel;
 
PROCEDURE enableResyncActions IS
BEGIN
   deb('enableResyncActions - resync action tracing enabled');
   doResyncReasons := TRUE;
END enableResyncActions;
 
PROCEDURE setReason(reason IN number, forceSet IN boolean default FALSE) IS
BEGIN
   IF doResyncReasons THEN
--
--
      IF resync_reason = RESYNC_REASON_NONE OR forceSet THEN
         resync_reason := reason;
         deb('setReason - resync_reason: '||to_char(resync_reason));
      END IF;
   ELSE
      resync_reason := RESYNC_REASON_NOACTION;
   END IF;
END setReason;
 
FUNCTION getReason RETURN number IS
BEGIN
   IF doResyncReasons THEN
      deb('getReason - resync_reason: '||to_char(resync_reason));
      RETURN resync_reason;
   ELSE
      RETURN RESYNC_REASON_NOACTION;
   END IF;
END getReason;
 
PROCEDURE incResyncActions(action      IN number,
                           objno       IN number,
                           objname     IN varchar2) IS
BEGIN
   IF not doResyncReasons THEN
      deb('incResynActions - Not debugging');
      RETURN;
   END IF;
 
   BEGIN
      deb('incResynActions - for action: '||to_char(action)||' objno '||
          nvl(to_char(objno), 'IS NULL')||' objname '||nvl(objname, 'IS NULL'),
          RCVCAT_LEVEL_HI);
      IF debOK(RCVCAT_LEVEL_HI) THEN
         dumpResyncActions;
      END IF;
      IF not fullResyncAction.active THEN
         RETURN;
      END IF;
      IF objno is NOT NULL THEN
         IF fullResyncAction.lastobjno = objno THEN
            IF fullResyncAction.actTaken(action) THEN
--
               deb('incResyncActions - '||
                   RESYNC_ACTION_OBJECTS(fullResyncAction.objtype)||' '||
                   to_char(objno)||' already '||
                   RESYNC_ACTION_NAMES(action), RCVCAT_LEVEL_HI);
               RETURN;
            ELSE
               fullResyncAction.actTaken(action) := TRUE;
            END IF;
         ELSE
--
            fullResyncAction.lastobjno := objno;
            fullResyncAction.actTaken  := 
                resyncActionTaken_t(FALSE, FALSE, FALSE, 
                                    FALSE, FALSE, FALSE);
            fullResyncAction.actTaken(action) := TRUE;
         END IF;
      END IF;
 
      fullResyncAction.actCount(action) := fullResyncAction.actCount(action) + 1;
      fullResyncAction.valid := TRUE;
      IF objno is NOT NULL THEN
         IF objname is NOT NULL THEN
            deb('incResyncActions - '||
                RESYNC_ACTION_OBJECTS(fullResyncAction.objtype)||' '||
                objname||'('||to_char(objno)||') '||
                RESYNC_ACTION_NAMES(action), RCVCAT_LEVEL_HI);
         ELSE
            deb('incResyncActions - '||
                RESYNC_ACTION_OBJECTS(fullResyncAction.objtype)||' '||
                to_char(objno)||' '|| RESYNC_ACTION_NAMES(action), 
                RCVCAT_LEVEL_HI);
         END IF;
      ELSE
         deb('incResyncActions - '||
             RESYNC_ACTION_OBJECTS(fullResyncAction.objtype)||' '||
             to_char(objname)||' '|| RESYNC_ACTION_NAMES(action),
             RCVCAT_LEVEL_HI);
      END IF;
      deb('incResyncActions - Exiting', RCVCAT_LEVEL_HI);
   EXCEPTION
      WHEN others THEN
         deb('incResyncActions(DO_NOT_IGNORE) - caught exception '||
             substr(sqlerrm, 1, 132) || ' for '||
             to_char(action) || ' objno ' || nvl(to_char(objno), 'IS NULL') ||
             ' objname ' || nvl(objname, 'IS NULL'));
   END;
END incResyncActions;
 
PROCEDURE dumpResyncActions IS
   i   number;
BEGIN
   IF not doResyncReasons OR not debOK(RCVCAT_LEVEL_HI) THEN
      RETURN;
   END IF;
 
   deb('dumpResyncActions - resync_reason: '||to_char(nvl(resync_reason, -1)));
 
   IF resync_reason = RESYNC_REASON_NOACTION THEN
      RETURN;
   END IF;
 
   IF fullResyncAction.active THEN
      deb('dumpResyncActions - Container is active');
   ELSE
      deb('dumpResyncActions - Container is NOT active');
   END IF;
   IF fullResyncAction.valid THEN
      deb('dumpResyncActions - Container is valid');
   ELSE
      deb('dumpResyncActions - Container is NOT valid');
   END IF;
   IF fullResyncAction.objtype IS NOT NULL THEN
      deb('dumpResyncActions - objtype: '||
          RESYNC_ACTION_OBJECTS(fullResyncAction.objtype));
   ELSE
      deb('dumpResyncActions - objtype is NULL');
   END IF;
   IF fullResyncAction.lastobjno IS NOT NULL THEN
      deb('dumpResyncActions - lastobjno: '||
          to_char(fullResyncAction.lastobjno));
   ELSE
      deb('dumpResyncActions - lastobjno is NULL');
   END IF;
 
   FOR i IN 1..6 LOOP
      IF fullResyncAction.actTaken(i) THEN
         deb('dumpResyncActions - '||RESYNC_ACTION_NAMES(i)||' TRUE - '||
             fullResyncAction.actCount(i));
      ELSE
         deb('dumpResyncActions - '||RESYNC_ACTION_NAMES(i)||' FALSE - '||
             fullResyncAction.actCount(i));
      END IF;
   END LOOP;
END dumpResyncActions;
 
PROCEDURE getResyncActions(valid      OUT boolean
                           ,added     OUT number
                           ,dropped   OUT number
                           ,changed   OUT number
                           ,recreated OUT number
                           ,renamed   OUT number
                           ,resized   OUT number) IS                        
BEGIN
   IF doResyncReasons THEN
      IF debOK(RCVCAT_LEVEL_HI) THEN
         deb('getResyncActions - called', RCVCAT_LEVEL_HI);
         dumpResyncActions;
      END IF;
      fullResyncAction.active := FALSE;
      valid  := fullResyncAction.valid;
      fullResyncAction.valid := FALSE;
      added     := fullResyncAction.actCount(RESYNC_ACTION_ADD);
      dropped   := fullResyncAction.actCount(RESYNC_ACTION_DROP);
      changed   := fullResyncAction.actCount(RESYNC_ACTION_CHANGE);
      recreated := fullResyncAction.actCount(RESYNC_ACTION_RECREATE);
      renamed   := fullResyncAction.actCount(RESYNC_ACTION_RENAME);
      resized   := fullResyncAction.actCount(RESYNC_ACTION_RESIZE);
      setReason(RESYNC_REASON_NONE, TRUE);
   ELSE
      setReason(RESYNC_REASON_NOACTION, TRUE);
   END IF;
END getResyncActions;
 
PROCEDURE clearResyncActions IS
BEGIN
   fullResyncAction.active    := FALSE;
   fullResyncAction.valid     := FALSE;
   fullResyncAction.lastobjno := -1;
   fullResyncAction.objtype   := NULL;
   fullResyncAction.actTaken  := resyncActionTaken_t(FALSE, FALSE, FALSE, 
                                                     FALSE, FALSE, FALSE);
   fullResyncAction.actCount  := resyncActionCounts_t(0, 0, 0, 0, 0, 0);
   dumpResyncActions;
END clearResyncActions;
 
 
/*-------------------------------------------------*
 * Private functions for import catalog processing *
 *-------------------------------------------------*/
--
--
--
--
--
--
PROCEDURE adjustRmanSeq
IS
  currval                          NUMBER;
  newval                           NUMBER;
  incseq                           NUMBER;
BEGIN
  LOOP
    SELECT rman_seq.nextval
      INTO currval
      FROM dual;
 
    EXECUTE IMMEDIATE
      'SELECT rman_seq.nextval@'
    || dbms_assert.qualified_sql_name(import_dblink)
    || ' FROM dual'
      INTO incseq;
 
    EXECUTE IMMEDIATE
    'ALTER SEQUENCE rman_seq INCREMENT BY ' || incseq;
 
    SELECT rman_seq.nextval - incseq
      INTO import_offset
      FROM dual;
 
    EXECUTE IMMEDIATE
    'ALTER SEQUENCE rman_seq INCREMENT BY 1';
 
--
--
--
--
    EXIT WHEN (import_offset >= currval); 
  END LOOP;
END adjustRmanSeq;
 
--
--
--
--
--
--
--
FUNCTION isColumnASeq(
  column_name                      IN VARCHAR2
)
RETURN BOOLEAN
IS
BEGIN
  IF (column_name LIKE '%KEY')
  THEN
    FOR i in 1..key_columns.COUNT
    LOOP
      IF (key_columns(i) = column_name)
      THEN
        RETURN TRUE;
      END IF;
    END LOOP;
 
--
--
--
--
    RAISE_APPLICATION_ERROR(-20999, 'Internal error in ' ||
                            'isColumnASeq(): bad column '|| column_name);
  END IF;
  RETURN FALSE;
END isColumnASeq; 
 
--
--
--
--
--
--
--
FUNCTION getColumnName(
  table_name                       IN VARCHAR2
, offset                           IN NUMBER DEFAULT NULL
)
RETURN VARCHAR2
IS
  v_table                          user_objects.object_name%TYPE;
  v_column                         VARCHAR2(1024);
  isaseq                           BOOLEAN;
 
  CURSOR column_c(tname VARCHAR2)
  IS
    SELECT column_name, data_type
      FROM user_tab_columns
     WHERE table_name = tname
     ORDER BY column_name;
 
  FUNCTION add_comma (
    v_column                       IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    IF (v_column IS NULL)
    THEN
      RETURN NULL;
    ELSE
      RETURN ',';
    END IF;
  END add_comma;
 
  FUNCTION add_offset(
    offset                         IN NUMBER
  , data_type                      IN VARCHAR2
  , column_name                    IN VARCHAR2
  )
  RETURN VARCHAR2
  IS
  BEGIN
    IF (offset IS NULL)
    THEN
      RETURN NULL;
    END IF;
    IF (data_type = 'NUMBER' AND isColumnASeq(column_name))
    THEN
      RETURN '+' || offset;
    END IF;
    RETURN null;
  END add_offset;
BEGIN
  SELECT object_name
    INTO v_table
    FROM user_objects
   WHERE object_name = UPPER(table_name)
     AND object_type = 'TABLE';
 
--
  FOR cRec in column_c(v_table)
  LOOP
    v_column := v_column
             || add_comma(v_column)
             || dbms_assert.simple_sql_name(table_name)
             || '.'
             || dbms_assert.simple_sql_name(cRec.column_name)
             || add_offset(offset, cRec.data_type, cRec.column_name);
  END LOOP;
  RETURN v_column;
END getColumnName;
 
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
PROCEDURE importTable (
  table_name                       IN VARCHAR2
, from_table                       IN ts_name_list
, uniq_rows                        IN BOOLEAN
, where_clause                     IN VARCHAR2
)
IS
  insert_columns                   VARCHAR2(2048);
  from_columns                     VARCHAR2(2048);
  source_table                     VARCHAR2(2048);
  uniq_keyword                     VARCHAR2(8);
  start_time                       DATE;
BEGIN
  deb('Entering importTable table=' || table_name);
  insert_columns := getColumnName(table_name);
  from_columns   := getColumnName(table_name, import_offset);
  source_table   := dbms_assert.qualified_sql_name(
                      table_name || '@' || import_dblink
                    );
  FOR i IN 1..from_table.COUNT
  LOOP
    source_table := source_table
                 || ','
                 || dbms_assert.qualified_sql_name(
                      from_table(i) || '@' || import_dblink
                    );
  END LOOP;
 
  IF (uniq_rows)
  THEN
    uniq_keyword := 'DISTINCT';
  END IF;
--
--
--
--
--
--
--
--
  start_time := SYSDATE;
  EXECUTE IMMEDIATE
     'INSERT INTO '
  || dbms_assert.qualified_sql_name(table_name)
  || '('
  || insert_columns
  || ')'
  || 'SELECT ' || uniq_keyword || ' ' || from_columns
  || '  FROM ' || source_table
  || ' '
  || where_clause;
 
  deb('imported rows = ' || SQL%ROWCOUNT);
  deb('importTable took ' || ((SYSDATE - start_time) * 86400) || ' seconds');
  deb('Finished importTable table=' || table_name);
END importTable;
 
--
--
--
--
--
--
--
--
PROCEDURE registerImportDb (
  idb                              IN VARCHAR2
, idbinc                           IN VARCHAR2
)
IS
  TYPE cur_typ IS ref CURSOR;
  update_c                         cur_typ;
  from_table                       ts_name_list;
  from_columns                     VARCHAR2(2048);
  currkeys                         numTab_t;
  dbids                            numTab_t;
  dbkeys                           numTab_t;
  reg_dbuname                      key_columns_list;
  strg_prov                        sp_columns_list;
BEGIN
  deb('Entering registerImportDb');
  from_columns := getColumnName('db', import_offset);
 
--
--
--
--
--
--
--
--
  EXECUTE IMMEDIATE
     'INSERT INTO db (db.db_key, db.db_id)'
  || 'SELECT db.db_key + ' || import_offset
  || '     , db.db_id'
  || '  FROM db@' ||  dbms_assert.qualified_sql_name(import_dblink)
  || '    ,' || dbms_assert.qualified_sql_name(idb || '@' || import_dblink)
  || ' WHERE db.db_key = '
  || dbms_assert.simple_sql_name(idb) || '.db_key';
 
  deb('Total db imported = ' || SQL%ROWCOUNT);
 
--
  from_table.DELETE;
  from_table(1) := idbinc;
  importTable (
    table_name   => 'dbinc'
  , from_table   => from_table
  , uniq_rows    => FALSE
  , where_clause => 'WHERE dbinc.dbinc_key = '
                 || idbinc || '.dbinc_key'
  );
 
--
--
  OPEN update_c FOR
--
--
--
--
--
--
--
  'SELECT ' || from_columns || 
  '  FROM db@' || dbms_assert.qualified_sql_name(import_dblink)
               || ','
               || dbms_assert.qualified_sql_name(idb || '@' || import_dblink)
               ||
  ' WHERE db.db_key = ' || dbms_assert.simple_sql_name(idb) || '.db_key';
  FETCH update_c BULK COLLECT INTO currkeys, dbids, dbkeys, reg_dbuname, 
                                   strg_prov;
  CLOSE update_c;
 
--
  FORALL i IN 1..dbids.COUNT
   UPDATE db
      SET curr_dbinc_key = currkeys(i)
        , reg_db_unique_name = reg_dbuname(i)
        , storage_prov = strg_prov(i)
    WHERE db.db_key = dbkeys(i);
 
  deb('Finished registerImportDb');
EXCEPTION
  WHEN DUP_VAL_ON_INDEX
  THEN RAISE_APPLICATION_ERROR(-20512, 'Database already registered');
END registerImportDb;
 
--
--
--
--
--
--
--
--
PROCEDURE dropTempResource (
  name                             IN VARCHAR2
, data_type                        IN VARCHAR2
)
IS
  e_dblink_not_found               EXCEPTION;
  e_dblink_not_open                EXCEPTION;
  e_resource_not_found             EXCEPTION;
  e_table_not_found                EXCEPTION;
  PRAGMA EXCEPTION_INIT(e_dblink_not_found, -2024);
  PRAGMA EXCEPTION_INIT(e_dblink_not_open, -2081);
  PRAGMA EXCEPTION_INIT(e_resource_not_found, -20509);
  PRAGMA EXCEPTION_INIT(e_table_not_found, -942);
BEGIN
  deb('Entering dropTempResource name = ' || name ||
      ' , data_type = '|| data_type);
  commitChanges('dropTempResource');
 
--
  IF (NOT lockTempResource(name, data_type))
  THEN
    deb('Finished dropTempResource - resource busy');
    RETURN;
  END IF;
 
  IF (data_type = 'TABLE')
  THEN
    EXECUTE IMMEDIATE
      'DROP TABLE ' || dbms_assert.qualified_sql_name(name);
  ELSIF (data_type = 'DBLINK')
  THEN
    BEGIN
      EXECUTE IMMEDIATE
        'ALTER SESSION CLOSE DATABASE LINK '
      || dbms_assert.qualified_sql_name(name);
    EXCEPTION
      WHEN e_dblink_not_open
      THEN NULL;
    END;
    EXECUTE IMMEDIATE
      'DROP DATABASE LINK '
    || dbms_assert.qualified_sql_name(name);
  END IF;
 
--
  DELETE tempres
   WHERE tempres.name = dropTempResource.name;
 
  commitChanges('dropTempResource-2');
  deb('Finished dropTempResource');
EXCEPTION
  WHEN e_resource_not_found THEN
    DELETE FROM tempres WHERE tempres.name = dropTempResource.name;
    commitChanges('dropTempResource-3');
    deb('Finished dropTempResource - resource_not_found ' || name);
  WHEN e_dblink_not_found THEN
    deb('Finished dropTempResource - dblink_not_found' || name);
  WHEN e_table_not_found THEN
    deb('Finished dropTempResource - table_not_found' || name);
  WHEN OTHERS THEN
    deb('(DO_NOT_IGNORE)caught exception during dropTempResource ' ||
         SUBSTR(SQLERRM, 1, 512));
END dropTempResource;
 
--
--
--
--
--
--
--
--
PROCEDURE importGlobalScript
IS
   type cur_typ is ref cursor;
   global_scr_c    cur_typ;
   global_scr_q    varchar2(1024);
   local           scr%rowtype;
   given_name      scr.scr_name%type;
   from_table      ts_name_list;
   from_columns   varchar2(2048);
   copycnt         number;
   unique_violated exception;
   pragma exception_init(unique_violated, -1);
BEGIN
--
   from_columns := getColumnName('scr');
   global_scr_q := 'SELECT '
                || from_columns
                || '  FROM scr@'
                || dbms_assert.qualified_sql_name(import_dblink)
                || ' WHERE db_key IS NULL';
 
   OPEN global_scr_c FOR global_scr_q;
   LOOP
      FETCH global_scr_c
       INTO local.db_key, local.scr_comment, 
            local.scr_key, local.scr_name;
      EXIT WHEN global_scr_c%NOTFOUND;
      copycnt := 0;
      given_name := local.scr_name;
<<tryagain>>
      BEGIN
--
--
--
--
--
         EXECUTE IMMEDIATE
         'INSERT INTO scr (' || from_columns || ') VALUES ' ||
         '( null,' ||
            case when local.scr_comment is not null then
               '''' || local.scr_comment || ''','
            else
               'null,'
            end ||
            local.scr_key || '+' || import_offset || ',' ||
            '''' || local.scr_name || ''')';
      EXCEPTION
         WHEN unique_violated THEN
--
            copycnt := copycnt + 1;
            IF (copycnt = 1) THEN
               local.scr_name := 'COPY OF ' || given_name;
            ELSE
               local.scr_name := 'COPY(' || copycnt || ') OF ' || given_name;
            END IF;
            goto tryagain;
      END;
   END LOOP;
 
--
   from_table.delete;
   from_table(1) := 'scr';
   importTable(table_name    => 'scrl'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where scr.db_key is null' ||
                                '  and scr.scr_key = scrl.scr_key');
END importGlobalScript;
 
/*------------------------------------------------*
 * Public functions for import catalog processing *
 *------------------------------------------------*/
--
--
--
--
--
PROCEDURE createTempResource(
   name       IN varchar2
  ,data_type  IN varchar2)
IS
   unique_violated exception;
   pragma exception_init(unique_violated ,-1);
BEGIN
--
   INSERT INTO tempres (name, data_type)
   VALUES (name, data_type);
   commitChanges('createTempResource');
EXCEPTION
   WHEN unique_violated THEN
      raise_application_error(-20508, 'resource already in use ' || name);
END createTempResource;
 
--
--
--
--
--
--
FUNCTION lockTempResource(
   name      IN varchar2
  ,data_type IN varchar2)
RETURN BOOLEAN IS
   local         tempres%ROWTYPE;
   found         number;
   resource_busy exception;
   pragma exception_init(resource_busy, -54);
BEGIN
  deb('Entering lockTempResource ' || name);
 
  SELECT name, data_type
    INTO local.name, local.data_type
    FROM tempres
   WHERE name = lockTempResource.name
     FOR UPDATE NOWAIT;
 
  IF (data_type = 'TABLE') THEN
     SELECT count(*)
       INTO found
       FROM user_tab_columns
      WHERE table_name = lockTempResource.name;
  ELSIF (data_type = 'DBLINK') THEN
     SELECT count(*)
       INTO found
       FROM user_db_links
      WHERE db_link = lockTempResource.name;
  ELSE
     raise_application_error(-20999,
        'Internal error in localTempResource(): bad data_type '|| data_type);
  END IF;
 
  IF (found = 0) THEN
     deb('Finished lockTempResource with resource not found');
     raise_application_error(-20509, 'resource not found ' || name);
  END IF;
 
  deb('Finished lockTempResource'); 
  RETURN TRUE;
EXCEPTION
  WHEN resource_busy THEN
     deb('Finished lockTempResource with resource_busy');
     RETURN FALSE;
  WHEN no_data_found THEN
     deb('Finished lockTempResource with no_data_found'); 
     RETURN FALSE;
END lockTempResource;
 
--
--
--
--
--
PROCEDURE cleanupTempResource IS
   CURSOR temp_c IS
      SELECT name, data_type
        FROM tempres;
BEGIN
   FOR tempRec in temp_c LOOP
      dropTempResource(tempRec.name, tempRec.data_type);
   END LOOP;
END cleanupTempResource;
 
--
--
--
--
--
--
--
--
PROCEDURE addDbidToImport(
   first    IN binary_integer
  ,idb      IN varchar2
  ,idbinc   IN varchar2
  ,dbid     IN number    DEFAULT NULL
  ,dbname   IN varchar2  DEFAULT NULL)
IS
   dummy       tempres.name%TYPE;
   ldbid       db.db_id%TYPE := dbid;
   dbid_exists number;
BEGIN
--
   IF (NOT lockTempResource(idb, 'TABLE')) THEN
      raise_application_error(-20508, 'resource already in use ' || idb);
   ELSIF (NOT lockTempResource(idbinc, 'TABLE')) THEN
      raise_application_error(-20508, 'resource already in use ' || idbinc);
   END IF;
 
   IF (dbid IS NULL AND dbname IS NULL) THEN
      EXECUTE IMMEDIATE
      'INSERT INTO ' || idb || '(db_key, db_id) ' ||
      '(SELECT db_key, db_id FROM db)';
 
      IF (sql%rowcount = 0) THEN
         raise_application_error(-20510, 'import database not found');
      END IF;
 
      EXECUTE IMMEDIATE
      'INSERT INTO ' || idbinc || '(dbinc_key) ' ||
      '(SELECT dbinc_key ' ||
      '   FROM dbinc, ' || idb ||
      '  WHERE dbinc.db_key = ' || idb ||'.db_key)'; 
      commitChanges('addDbidToImport');
      RETURN;
   ELSIF (dbname IS NOT NULL) THEN
      BEGIN
         SELECT db.db_id
           INTO ldbid
           FROM db, dbinc
          WHERE db.curr_dbinc_key = dbinc.dbinc_key
            AND dbinc.db_name = upper(addDbidtoImport.dbname);
      EXCEPTION
         WHEN no_data_found THEN
           raise_application_error(-20510, 'import database not found');
         WHEN too_many_rows THEN
           raise_application_error(-20511, 'import database name is ambiguous');
      END;
   ELSE
      BEGIN
         SELECT db.db_id
           INTO ldbid
           FROM db
          WHERE db.db_id = ldbid;
      EXCEPTION
         WHEN no_data_found THEN
           raise_application_error(-20510, 'import database not found');
      END;
   END IF;
 
--
--
   IF (first = 0) THEN
      FOR i in 1..import_dbid.count LOOP
         EXECUTE IMMEDIATE
            'SELECT count(*) FROM ' || idb ||
            ' WHERE ' || idb || '.db_id =' || import_dbid(i)
            INTO dbid_exists;
         IF (dbid_exists != 1) THEN
            raise_application_error(-20508, 'resource already in use ' || idb);
         END IF;
      END LOOP;
 
      EXECUTE IMMEDIATE
         'SELECT count(*) FROM ' || idb INTO dbid_exists;
      IF (dbid_exists != import_dbid.count) THEN
         raise_application_error(-20508, 'resource already in use ' || idb);
      END IF;
      import_dbid(import_dbid.count + 1) := ldbid;
   ELSE
      import_dbid.delete;
      import_dbid(1) := ldbid;
   END IF;
 
   EXECUTE IMMEDIATE
   'INSERT INTO ' || idb || '(db_key, db_id) ' ||
   '(SELECT db_key, db_id FROM db ' ||
   '  WHERE db_id = ' || ldbid || ')';
 
   EXECUTE IMMEDIATE
   'INSERT INTO ' || idbinc || '(dbinc_key) ' ||
   '(SELECT dbinc_key ' ||
   '    FROM dbinc, ' || idb ||
   '  WHERE dbinc.db_key = ' || idb || '.db_key ' ||
   '    AND ' || idb || '.db_id = ' || ldbid || ')'; 
 
   commitChanges('addDbidToImport-2');
 
--
   IF (NOT lockTempResource(idb, 'TABLE')) THEN
      raise_application_error(-20508, 'resource already in use ' || idb);
   ELSIF (NOT lockTempResource(idbinc, 'TABLE')) THEN
      raise_application_error(-20508, 'resource already in use ' || idbinc);
   END IF;
END addDbidToImport;
 
--
--
--
--
--
--
--
PROCEDURE lockDbidToImport(
   idb   IN varchar2)
IS
   TYPE cur_typ IS ref CURSOR;
   idb_c           cur_typ;
   idb_q           varchar2(512);
   local_db_key    db.db_key%TYPE;
   local_db_id     db.db_key%TYPE;
BEGIN
   idb_q := 'SELECT db_key FROM ' || idb;
   OPEN idb_c FOR idb_q;
   LOOP
      FETCH idb_c INTO local_db_key; 
      EXIT WHEN idb_c%NOTFOUND;
      SELECT db_id INTO local_db_id
        FROM db
       WHERE db.db_key = local_db_key
         FOR UPDATE OF db.db_key;
   END LOOP;
END lockDbidToImport;
 
--
--
--
--
--
--
PROCEDURE importSchema(
   dblink  IN varchar2
  ,idb     IN varchar2
  ,idbinc  IN varchar2)
IS
   from_table     ts_name_list;
BEGIN
   import_dblink := dblink;
   adjustRmanSeq;
   registerImportDb(idb, idbinc);
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'pdb'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where pdb.db_key = ' ||
                                idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   from_table(2) := 'pdb';
   importTable(table_name    => 'pdbinc'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where pdb.db_key = ' ||
                                idb || '.db_key'  ||
                                '  and pdbinc.pdb_key = pdb.pdb_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'pdb_dbinc'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where pdb_dbinc.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'conf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where conf.db_key = ' || idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'node'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where node.db_key = ' || idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'ckp'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where ckp.dbinc_key = ' ||
                                 idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'ts'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where ts.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'tsatt'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where tsatt.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'df'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where df.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   from_table(2) := 'node';
   importTable(table_name    => 'site_dfatt'
              ,from_table    => from_table
              ,uniq_rows     => TRUE
              ,where_clause  => 'where node.db_key = ' ||
                                idb || '.db_key'  ||
                                '  and site_dfatt.site_key = node.site_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'offr'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where offr.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'tf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where tf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   from_table(2) := 'node';
   importTable(table_name    => 'site_tfatt'
              ,from_table    => from_table
              ,uniq_rows     => TRUE
              ,where_clause  => 'where node.db_key = ' ||
                                idb || '.db_key'  ||
                                '  and site_tfatt.site_key = node.site_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'rr'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where rr.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'rt'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where rt.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'orl'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where orl.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'rlh'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where rlh.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'al'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where al.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'bs'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bs.db_key = ' || idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'bp'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bp.db_key = ' || idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'bcf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bcf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'ccf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where ccf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'xcf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where xcf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'bsf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bsf.db_key = ' || idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'bdf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bdf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'cdf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where cdf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'xdf'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where xdf.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'xal'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where xal.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'brl'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where brl.dbinc_key = ' ||
                                idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   from_table(2) := 'bdf';
   importTable(table_name    => 'bcb'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bdf.bdf_key = bcb.bdf_key' ||
                                '  and bdf.dbinc_key = ' ||
                                       idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   from_table(2) := 'cdf';
   importTable(table_name    => 'ccb'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where cdf.cdf_key = ccb.cdf_key' ||
                                '  and cdf.dbinc_key = ' ||
                                       idbinc || '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'rsr'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where rsr.dbinc_key= ' ||idbinc||
                                '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'scr'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where scr.db_key = ' || idb || '.db_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   from_table(2) := 'scr';
   importTable(table_name    => 'scrl'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where scr.db_key = ' || idb || '.db_key' ||
                                '  and scr.scr_key = scrl.scr_key');
 
--
   importGlobalScript;
 
--
   from_table.delete;
   from_table(1) := idb;
   importTable(table_name    => 'rout'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where rout.db_key = ' || idb || '.db_key');
 
--
--
--
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'fb'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where fb.dbinc_key = '|| idbinc ||
                                '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'grsp'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where grsp.dbinc_key = '|| idbinc ||
                                '.dbinc_key');
 
--
   from_table.delete;
   from_table(1) := idb;
   from_table(2) := 'node';
   importTable(table_name    => 'bcr'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where bcr.site_key = node.site_key' ||
                                '  and node.db_key = ' ||
                                       idb || '.db_key');
--
   from_table.delete;
   from_table(1) := idbinc;
   importTable(table_name    => 'nrsp'
              ,from_table    => from_table
              ,uniq_rows     => FALSE
              ,where_clause  => 'where nrsp.dbinc_key = '|| idbinc ||
                                '.dbinc_key');
 
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
--
 
   commitChanges('importSchema');
EXCEPTION
   WHEN others THEN
      deb('importSchema - release locks');
      ROLLBACK;
      RAISE;
END importSchema;
 
--
--
--
--
--
--
PROCEDURE unregisterDatabase(
   idb   IN varchar2)
IS
   TYPE cur_typ IS ref CURSOR;
   idb_c           cur_typ;
   idb_q           varchar2(512);
   local_db_id     db.db_id%TYPE;
BEGIN
   idb_q := 'SELECT db_id FROM ' || idb;
   OPEN idb_c FOR idb_q;
   LOOP
      FETCH idb_c INTO local_db_id;
      EXIT WHEN idb_c%NOTFOUND;
      unregisterDatabase(db_id => local_db_id);
   END LOOP;
END unregisterDatabase;
 
PROCEDURE clearUnarchivedLogs IS 
BEGIN
   deb('clearUnarchivedLogs for this_db_key='||this_db_key);
   delete from al
    where archived = 'N'
      and dbinc_key in (select dbinc_key from dbinc where db_key = this_db_key);
   commitChanges('clearUnarchivedLogs');
END clearUnarchivedLogs;
 
/*----------------------------*
 * Virtual Private Catalog    *
 *----------------------------*/
 
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
 
FUNCTION grant_get_dbid(dbname IN varchar2) RETURN number IS
   dbid number;
   cnt  number;
BEGIN
   select max(db_id), count(*) into dbid, cnt from db join
      (select distinct db_key,db_name from dbinc) using(db_key)
      where db_name = dbname;
 
   deb('grant_get_dbid - got dbid and cnt ' || dbid || ' cnt ' ||
       cnt);
 
   if cnt = 0 then
      raise_application_error(-20018, 'database ' || dbname ||
                              ' not found in recovery catalog');
   end if;
 
   if cnt > 1 then
      raise_application_error(-20019, 'database ' || dbname ||
                              ' not unique in recovery catalog');
   end if;
 
   return dbid;
END;
 
FUNCTION get_db_numeric_uid(uname IN VARCHAR2) RETURN NUMBER IS
  numeric_uid NUMBER;
BEGIN
  SELECT user_id INTO numeric_uid FROM all_users WHERE username = uname;
  RETURN numeric_uid;
  EXCEPTION
    WHEN NO_DATA_FOUND
    THEN raise_application_error(-20022, 'user ' || uname || ' not found');
END;
 
PROCEDURE clean_old_uids IS
BEGIN
  DELETE vpc_databases
   WHERE filter_uid NOT IN (SELECT user_id FROM all_users);
  DELETE vpc_users
   WHERE filter_uid NOT IN (SELECT user_id FROM all_users);
 
--
--
 
END;
 
FUNCTION is_vpd_enabled (
  i_full_check                     BOOLEAN DEFAULT TRUE
)
RETURN BOOLEAN
IS
  l_dummy                          VARCHAR2(1);
BEGIN
--
--
  IF (this_is_ors)
  THEN
    RETURN TRUE;
  END IF;
--
--
  IF (SYS_CONTEXT('SYS_SESSION_ROLES', 'RECOVERY_CATALOG_OWNER_VPD') = 'TRUE')
  THEN
    NULL;
  ELSE
--
--
    BEGIN
      SELECT 'x'
        INTO l_dummy
        FROM session_privs
       WHERE privilege IN (
--
               'ADMINISTER DATABASE TRIGGER'
--
             , 'CREATE ANY SYNONYM', 'DROP ANY SYNONYM'
             )
      HAVING COUNT(*) = 3;
    EXCEPTION
      WHEN NO_DATA_FOUND
      THEN RETURN FALSE;
    END;
  END IF;
--
--
--
  BEGIN
    SELECT 'x'
      INTO l_dummy
      FROM all_tab_privs
     WHERE (table_schema, table_name, privilege, grantee) IN (
             ('SYS', 'DBMS_RLS', 'EXECUTE', 'RECOVERY_CATALOG_OWNER_VPD')
           , ('SYS', 'DBMS_RLS', 'EXECUTE', 'RECOVERY_CATALOG_OWNER')
           , ('SYS', 'DBMS_RLS', 'EXECUTE', g_catowner)
           )
    HAVING COUNT(*) > 0;
  EXCEPTION
    WHEN NO_DATA_FOUND
    THEN RETURN FALSE;
  END;
  IF (i_full_check)
  THEN
--
--
--
    BEGIN
      SELECT 'x'
        INTO l_dummy
        FROM user_policies
      HAVING COUNT(*) = g_vpd_required_policies;
    EXCEPTION
      WHEN NO_DATA_FOUND
      THEN RETURN FALSE;
    END;
--
    BEGIN
      SELECT 'x'
        INTO l_dummy
        FROM user_triggers
       WHERE trigger_name = 'VPC_CONTEXT_TRG';
      EXCEPTION
        WHEN NO_DATA_FOUND
        THEN RETURN FALSE;
    END;
  END IF;
  RETURN TRUE;
END;
 
PROCEDURE revoke_clean_userid (
  userid                           IN VARCHAR2
)
IS
  l_privs_remaining                NUMBER;
  l_schema                         user_users.username%TYPE;
  l_eschema                        VARCHAR2(130);
  e_no_synonym                     EXCEPTION;
  PRAGMA EXCEPTION_INIT(e_no_synonym, -1434);
BEGIN
  assert_schema(l_schema, l_eschema, userid);
  
  DELETE vpc_users
   WHERE filter_user = userid
     AND add_new_db = 'N'
     AND NOT EXISTS (
           SELECT *
             FROM vpc_databases
            WHERE filter_user = l_schema
         );
 
  commitChanges('revoke_clean_userid');
 
  SELECT COUNT(*)
    INTO l_privs_remaining
    FROM vpc_users
   WHERE filter_user = l_schema;
 
  deb('revoke_clean_userid - commit, release locks, userid=' || l_schema ||
      ', privs_remaining=' || l_privs_remaining);
 
  IF l_privs_remaining > 0 THEN RETURN; END IF;
 
  IF (NOT this_is_ors)
  THEN
    BEGIN
      EXECUTE IMMEDIATE
        'REVOKE recovery_catalog_user FROM ' || l_eschema;
    EXCEPTION
      WHEN OTHERS
      THEN  deb('revoke_clean_userid(DO_NOT_IGNORE)');
    END;
  END IF;
  BEGIN
    EXECUTE IMMEDIATE
      'DROP SYNONYM ' || l_eschema || '."DBMS_RCVMAN"';
  EXCEPTION
    WHEN e_no_synonym
    THEN NULL;
  END;
  BEGIN
    EXECUTE IMMEDIATE
      'DROP SYNONYM ' || l_eschema || '."DBMS_RCVCAT"';
  EXCEPTION
    WHEN e_no_synonym
    THEN NULL;
  END;
END;
 
PROCEDURE do_vpc_grants (
  userid                           IN VARCHAR2
)
IS
  l_schema                         user_users.username%TYPE;
  l_eschema                        VARCHAR2(130);
BEGIN
  IF (NOT is_vpd_enabled)
  THEN
    RAISE_APPLICATION_ERROR(e_no_vpd_setup, e_no_vpd_setup_m);
  END IF;
 
  assert_schema(l_schema, l_eschema, userid);
 
  IF (NOT this_is_ors)
  THEN
    EXECUTE IMMEDIATE
      'GRANT recovery_catalog_user TO ' || l_eschema;
  END IF;
  /* While stemming from rman client PL/SQL context alter session
   * set current_schema has no effect as far as visibility to packages
   * are concerned. So the additional steps below. (bandaid)
   * Since DDL is, in general, bad activity we have to limit it
   * making sure that the synonyms are created only if they don't
   * exist.
   */
  FOR s IN (
    SELECT synonym_name
      FROM (
           SELECT 'DBMS_RCVMAN' synonym_name FROM dual
            UNION ALL
           SELECT 'DBMS_RCVCAT' FROM dual
           ) x
     WHERE NOT EXISTS (
             SELECT NULL
               FROM all_synonyms s
              WHERE s.table_owner = g_catowner
                AND s.table_name = x.synonym_name
                AND s.owner = userid
                AND s.synonym_name = x.synonym_name
           )
  )
  LOOP
    EXECUTE IMMEDIATE
      'CREATE OR REPLACE SYNONYM '
    || l_eschema   || '.' || s.synonym_name
    || ' FOR '
    || g_ecatowner || '.' || s.synonym_name;
  END LOOP;
END;
 
PROCEDURE grant_catalog(userid IN varchar2, dbname IN varchar2) IS
BEGIN
  grant_catalog(userid, grant_get_dbid(dbname));
END;
 
PROCEDURE grant_catalog(userid             IN varchar2, 
                        dbid               IN number,
                        reg_db_unique_name IN varchar2 default null) IS
   user_count  number;
   numeric_uid number;
   p_dbun      node.db_unique_name%TYPE := upper(reg_db_unique_name);
   p_dbid      number not null := dbid;
BEGIN
   clean_old_uids;
 
   do_vpc_grants(userid);
 
   SELECT COUNT(*), MAX(filter_uid) INTO user_count, numeric_uid FROM vpc_users
    WHERE filter_user = userid;
 
   if user_count = 0 then
      numeric_uid := get_db_numeric_uid(userid);
      insert into vpc_users(filter_user, filter_uid, add_new_db)
         values(userid, numeric_uid, 'N');
   end if;
 
   insert into vpc_databases
      (filter_user, filter_uid, reg_db_unique_name, db_id)
      select userid, numeric_uid, decode(dbid, 0, p_dbun, null), dbid from dual
         where not exists
            (select 1 from vpc_databases
                where filter_user = userid 
                  and ((db_id = 0 and reg_db_unique_name = p_dbun) or
                       (db_id <> 0 and db_id = dbid))
            );
   commitChanges('grant_catalog');
END;
 
PROCEDURE grant_register(userid IN varchar2) IS
   numeric_uid number := get_db_numeric_uid(userid);
BEGIN
   clean_old_uids;
 
   do_vpc_grants(userid);
 
   MERGE INTO vpc_users USING dual ON (filter_user = userid)
      WHEN MATCHED THEN UPDATE SET add_new_db = 'Y'
      WHEN NOT MATCHED THEN
         INSERT(filter_user, filter_uid,  add_new_db)
         VALUES(userid,      numeric_uid, 'Y');
 
   commitChanges('grant_register');
END;
 
PROCEDURE revoke_catalog(userid IN varchar2, dbname IN varchar2) IS
BEGIN
   revoke_catalog(userid, grant_get_dbid(dbname));
END;
 
PROCEDURE revoke_catalog(userid             IN varchar2, 
                         dbid               IN number,
                         reg_db_unique_name IN varchar2 default null) IS
   p_dbun      node.db_unique_name%TYPE := upper(reg_db_unique_name);
   p_dbid      number not null := dbid;
BEGIN
   IF (NOT is_vpd_enabled)
   THEN
     RAISE_APPLICATION_ERROR(e_no_vpd_setup, e_no_vpd_setup_m);
   END IF;
   clean_old_uids;
   deb('revoke_catalog - After clean_old_uids');
   delete from vpc_databases 
      where filter_user = userid 
        and ((dbid <> 0 and db_id = dbid) or
             (dbid = 0 and reg_db_unique_name = p_dbun));
   revoke_clean_userid(userid);
   commitChanges('revoke_catalog');
END;
 
PROCEDURE revoke_register(userid IN varchar2) IS
BEGIN
   IF (NOT is_vpd_enabled)
   THEN
     RAISE_APPLICATION_ERROR(e_no_vpd_setup, e_no_vpd_setup_m);
   END IF;
   clean_old_uids;
   update vpc_users set add_new_db='N' where filter_user=userid;
   revoke_clean_userid(userid);
   commitChanges('revoke_register');
END;
 
PROCEDURE revoke_all (
  userid                           IN VARCHAR2
)
IS
BEGIN
  clean_old_uids;
  DELETE vpc_databases WHERE filter_user = userid;
  DELETE vpc_users     WHERE filter_user = userid;
  revoke_clean_userid(userid);
  commitChanges('revoke_all');
END;
 
PROCEDURE create_virtual_catalog IS
BEGIN
  NULL;
END create_virtual_catalog;
 
PROCEDURE drop_virtual_catalog IS
BEGIN
  NULL;
END drop_virtual_catalog;
 
/*
 * New VPC based on VPD 
 * This routine is setup to be idempotent, can be called
 * again and again.
 * Input i_oper: Can be used to drop the policies on an upgrade
 * when i_oper == 0. Sets the policies when i_oper <> 0
 */
PROCEDURE setupVPD (
  i_oper                           NUMBER
)
IS
  lc_policy_str                    CONSTANT VARCHAR2(32) :=
    'dbms_rcvvpc.f_';
  lc_policy_str_passall            CONSTANT VARCHAR2(32) :=
    'dbms_rcvvpc.filter_pass_all';
  l_policy_str                     VARCHAR2(64);
  e_policy_notexists               EXCEPTION;
  PRAGMA                           EXCEPTION_INIT(e_policy_notexists, -28102);
  l_granted_to                     VARCHAR2(32) := CASE
                                                     WHEN this_is_ors
                                                     THEN 'PUBLIC'
                                                     ELSE 'recovery_catalog_user'
                                                   END;
BEGIN
--
--
--
--
--
  IF (      SYS_CONTEXT('USERENV', 'SESSION_USER') <> g_catowner
     OR NOT is_vpd_enabled(i_full_check => FALSE)
  )
  THEN
    RETURN;
  END IF;
 
  FOR i IN 1..vpd_table_list.COUNT
  LOOP
    EXECUTE IMMEDIATE 'GRANT '
                   || vtr_privs(i)
                   || ' ON '
                   || vtr_tname(i)
                   || ' TO '
                   || l_granted_to;
    IF vtr_policy_required(i)
    THEN
      BEGIN
        EXECUTE IMMEDIATE '
        BEGIN
          dbms_rls.drop_policy (
            object_schema => :1
          , object_name   => :2
          , policy_name   => :3
          );
        END;' USING g_catowner
                  , vtr_tname(i)
                  , vtr_tname(i);
      EXCEPTION
        WHEN e_policy_notexists
        THEN NULL;
      END;
 
      IF (i_oper = 0)
      THEN 
        l_policy_str := lc_policy_str_passall;
      ELSE
        l_policy_str := lc_policy_str || vtr_tname(i);
      END IF;
      
      IF (vtr_update_check(i))
      THEN
        EXECUTE IMMEDIATE '
        BEGIN
           dbms_rls.add_policy (
             object_schema   => :1
           , object_name     => :2
           , policy_name     => :3
           , function_schema => :4
           , policy_function => :5
           , policy_type     => dbms_rls.shared_context_sensitive
           , update_check    => TRUE
           );
         END;' USING g_catowner
                   , vtr_tname(i)
                   , vtr_tname(i)
                   , g_catowner
                   , l_policy_str;
      ELSE
        EXECUTE IMMEDIATE '
        BEGIN
           dbms_rls.add_policy (
             object_schema   => :1
           , object_name     => :2
           , policy_name     => :3
           , function_schema => :4
           , policy_function => :5
           , policy_type     => dbms_rls.shared_context_sensitive
           , update_check    => FALSE
           );
         END;' USING g_catowner
                   , vtr_tname(i)
                   , vtr_tname(i)
                   , g_catowner
                   , l_policy_str;
      END IF;
    END IF;
  END LOOP;
 
  FOR r IN (
    SELECT view_name
      FROM user_views
     WHERE view_name LIKE 'RC~_%' ESCAPE '~'
        OR view_name LIKE 'RCI~_%' ESCAPE '~'
        OR view_name LIKE '~_RS~_RC~_%' ESCAPE '~'
        OR view_name LIKE '~_RS~_RCI~_%' ESCAPE '~'
  )
  LOOP
    EXECUTE IMMEDIATE
      'GRANT SELECT ON '
    || dbms_assert.enquote_name(r.view_name)
    || ' TO ' || l_granted_to;
  END LOOP;
 
  IF (this_is_ors)
  THEN
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_owner TO PUBLIC';
    EXECUTE IMMEDIATE
      'CREATE OR REPLACE PUBLIC SYNONYM dbms_rai_owner FOR dbms_rai_owner';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_verifier TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_inst_addresses TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_sbt_parms TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_url TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_wallet2url TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_throttle_alloc TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_fix_error TO PUBLIC';
    EXECUTE IMMEDIATE 'GRANT EXECUTE ON dbms_rai_populate_rsr_key TO PUBLIC';
  END IF;
 
  deb('setupVPD - after adding policy and grants');
 
--
--
--
  IF (i_oper <> 0)
  THEN
    IF (this_is_ors)
    THEN
    EXECUTE IMMEDIATE REGEXP_REPLACE(q'{
CREATE OR REPLACE TRIGGER vpc_context_trg
AFTER LOGON ON DATABASE
WHEN (
  SYS_CONTEXT('USERENV', 'SESSION_USER') NOT IN (
    'SYSBACKUP', 'XDB', 'SYSMAN', 'ANONYMOUS', 'APPQOSSYS'
  , 'AUDSYS', 'CTXSYS', 'DIP'
  , 'DMSYS', 'EXFSYS', 'GSMADMIN_INTERNAL', 'GSMCATUSER'
  , 'GSMUSER' , 'MDSYS', 'ORABPEL', 'ORACLE_OCM'
  , 'ORAESB', 'ORAWSM', 'ORDPLUGINS', 'ORDSYS', 'OUTLN'
  , 'SI_INFORMTN_SCHEMA', 'SYSDG', 'SYSKM', 'TSMSYS'
  , 'WKSYS', 'WMSYS', 'XS$NULL'
  )
)
DECLARE
  l_dummy                          VARCHAR2(1);
BEGIN
--
--
--
  IF (  SYS_CONTEXT('USERENV', 'SESSION_USER') IN (
         '%o', 'SYS', 'SYSTEM', 'DBSNMP'
        )
  )
  THEN
    EXECUTE IMMEDIATE
      'ALTER SESSION SET NLS_NUMERIC_CHARACTERS = ''.,''';
  ELSE
--
--
    SELECT 'Y'
      INTO l_dummy
      FROM vpc_users
     WHERE filter_uid = SYS_CONTEXT('USERENV', 'SESSION_USERID');
    EXECUTE IMMEDIATE
      'ALTER SESSION SET NLS_NUMERIC_CHARACTERS = ''.,'' CURRENT_SCHEMA = %o';
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND
  THEN -- All regular users who have access to RA catalog must
--
       IF (SYS_CONTEXT('SYS_SESSION_ROLES', 'RA_CATALOG_SELECT') = 'TRUE')
       THEN
         EXECUTE IMMEDIATE
           'ALTER SESSION SET NLS_NUMERIC_CHARACTERS = ''.,''';
       END IF;
END;
}', '%o', g_catowner);
    ELSE
    EXECUTE IMMEDIATE REGEXP_REPLACE(q'{
CREATE OR REPLACE TRIGGER vpc_context_trg
AFTER LOGON ON DATABASE
WHEN (
  SYS_CONTEXT('USERENV', 'SESSION_USER') NOT IN (
    '%o', 'SYS', 'SYSTEM', 'SYSBACKUP'
  , 'XDB', 'SYSMAN', 'ANONYMOUS', 'APPQOSSYS'
  , 'AUDSYS', 'CTXSYS', 'DIP'
  , 'DMSYS', 'EXFSYS', 'GSMADMIN_INTERNAL', 'GSMCATUSER'
  , 'GSMUSER' , 'MDSYS', 'ORABPEL', 'ORACLE_OCM'
  , 'ORAESB', 'ORAWSM', 'ORDPLUGINS', 'ORDSYS', 'OUTLN'
  , 'SI_INFORMTN_SCHEMA', 'SYSDG', 'SYSKM', 'TSMSYS'
  , 'WKSYS', 'WMSYS', 'XS$NULL'
  )
)
DECLARE
  l_dummy                          VARCHAR2(1);
BEGIN
  SELECT 'Y'
    INTO l_dummy
    FROM vpc_users
   WHERE filter_uid = SYS_CONTEXT('USERENV', 'SESSION_USERID');
  EXECUTE IMMEDIATE 'ALTER SESSION SET CURRENT_SCHEMA = %o';
EXCEPTION
  WHEN NO_DATA_FOUND
  THEN NULL;
END;
}', '%o', g_catowner);
    END IF;
  END IF;
 
  deb('setupVPD - create or replace trigger');
END setupVPD;
 
PROCEDURE dumpPkgState (msg in varchar2 default NULL) IS
begin
   deb('Global variables package state ' || nvl(msg,' '));
   deb('Number variables');
   deb('dbglvl: ' || nvl(to_char(dbglvl), 'NULL'));
   deb('this_db_key: ' || nvl(to_char(this_db_key), 'NULL'));
   deb('this_dbinc_key: ' || nvl(to_char(this_dbinc_key), 'NULL'));
   deb('this_ckp_key: ' || nvl(to_char(this_ckp_key), 'NULL'));
   deb('this_ckp_scn: ' || nvl(to_char(this_ckp_scn), 'NULL'));
   deb('this_site_key: ' || nvl(to_char(this_site_key), 'NULL'));
   deb('logs_shared: ' || nvl(to_char(logs_shared), 'NULL'));
   deb('disk_backups_shared: ' || nvl(to_char(disk_backups_shared), 'NULL'));
   deb('tape_backups_shared: ' || nvl(to_char(tape_backups_shared), 'NULL'));
   deb('reNorm_state: ' || nvl(to_char(reNorm_state), 'NULL'));
   deb('resync_reason: ' || nvl(to_char(resync_reason), 'NULL'));
   deb('scr_key: ' || nvl(to_char(scr_key), 'NULL'));
   deb('scr_line: ' || nvl(to_char(scr_line), 'NULL'));
   deb('kccdivts: ' || nvl(to_char(kccdivts), 'NULL'));
   deb('cntbs: ' || nvl(to_char(cntbs), 'NULL'));
   deb('last_full_ckp_scn: ' || nvl(to_char(last_full_ckp_scn), 'NULL'));
   deb('last_con_id_ts#: ' || nvl(to_char(last_con_id_ts#), 'NULL'));
   deb('last_ts#: ' || nvl(to_char(last_ts#), 'NULL'));
   deb('last_file#: ' || nvl(to_char(last_file#), 'NULL'));
   deb('last_thread#: ' || nvl(to_char(last_thread#), 'NULL'));
   deb('last_ts_recid: ' || nvl(to_char(last_ts_recid), 'NULL'));
   deb('last_df_recid: ' || nvl(to_char(last_df_recid), 'NULL'));
   deb('last_tf_recid: ' || nvl(to_char(last_tf_recid), 'NULL'));
   deb('last_rt_recid: ' || nvl(to_char(last_rt_recid), 'NULL'));
   deb('last_orl_recid: ' || nvl(to_char(last_orl_recid), 'NULL'));
   deb('last_conf_recid: ' || nvl(to_char(last_conf_recid), 'NULL'));
   deb('force_resync2cf: ' || nvl(to_char(force_resync2cf), 'NULL'));
   deb('last_rlh_recid: ' || nvl(to_char(last_rlh_recid), 'NULL'));
   deb('last_al_recid: ' || nvl(to_char(last_al_recid), 'NULL'));
   deb('last_offr_recid: ' || nvl(to_char(last_offr_recid), 'NULL'));
   deb('last_bs_recid: ' || nvl(to_char(last_bs_recid), 'NULL'));
   deb('last_bp_recid: ' || nvl(to_char(last_bp_recid), 'NULL'));
   deb('last_bdf_recid: ' || nvl(to_char(last_bdf_recid), 'NULL'));
   deb('last_bsf_recid: ' || nvl(to_char(last_bsf_recid), 'NULL'));
   deb('last_brl_recid: ' || nvl(to_char(last_brl_recid), 'NULL'));
   deb('last_cdf_recid: ' || nvl(to_char(last_cdf_recid), 'NULL'));
   deb('last_bcb_recid: ' || nvl(to_char(last_bcb_recid), 'NULL'));
   deb('last_ccb_recid: ' || nvl(to_char(last_ccb_recid), 'NULL'));
   deb('last_do_recid: ' || nvl(to_char(last_do_recid), 'NULL'));
   deb('last_xdf_recid: ' || nvl(to_char(last_xdf_recid), 'NULL'));
   deb('last_xal_recid: ' || nvl(to_char(last_xal_recid), 'NULL'));
   deb('last_rsr_recid: ' || nvl(to_char(last_rsr_recid), 'NULL'));
   deb('last_rout_stamp: ' || nvl(to_char(last_rout_stamp), 'NULL'));
   deb('last_inst_startup_stamp: ' ||
       nvl(to_char(last_inst_startup_stamp), 'NULL'));
   deb('lrsr_key: ' || nvl(to_char(lrsr_key), 'NULL'));
   deb('lrout_skey: ' || nvl(to_char(lrout_skey), 'NULL'));
   deb('lsession_recid: ' || nvl(to_char(lsession_recid), 'NULL'));
   deb('lsession_stamp: ' || nvl(to_char(lsession_stamp), 'NULL'));
   deb('lrman_status_recid: ' || nvl(to_char(lrman_status_recid), 'NULL'));
   deb('lrman_status_stamp: ' || nvl(to_char(lrman_status_stamp), 'NULL'));
   deb('krbmror_llength_bytes: ' ||
       nvl(to_char(krbmror_llength_bytes), 'NULL'));
   deb('last_ic_recid: ' || nvl(to_char(last_ic_recid), 'NULL'));
   deb('last_reset_scn: ' || nvl(to_char(last_reset_scn), 'NULL'));
   deb('last_dbinc_key: ' || nvl(to_char(last_dbinc_key), 'NULL'));
   deb('low_nrsp_recid: ' || nvl(to_char(low_nrsp_recid), 'NULL'));
   deb('last_nrsp_recid: ' || nvl(to_char(last_nrsp_recid), 'NULL'));
   deb('last_grsp_recid: ' || nvl(to_char(last_grsp_recid), 'NULL'));
   deb('last_bcr_recid: ' || nvl(to_char(last_bcr_recid), 'NULL'));
   deb('last_pdb_recid: ' || nvl(to_char(last_pdb_recid), 'NULL'));
   deb('last_resync_cksum: ' || nvl(to_char(last_resync_cksum), 'NULL'));
 
   deb('Date variables');
   deb('this_ckp_time: ' ||
       nvl(to_char(this_ckp_time, 'DD/MM/YYYY HH24:MI:SS'), 'NULL'));
   deb('last_reset_time: ' ||
       nvl(to_char(last_reset_time, 'DD/MM/YYYY HH24:MI:SS'), 'NULL'));
   deb('last_cf_version_time: ' ||
        nvl(to_char(last_cf_version_time, 'DD/MM/YYYY HH24:MI:SS'), 'NULL'));
 
   deb('Char variables');
   deb('last_fname: ' || nvl(last_fname, 'NULL'));
   deb('last_rspname: '|| nvl(last_rspname, 'NULL'));         
   deb('last_pdb_key: '|| last_pdb_key);
   deb('this_cf_type: '|| nvl(this_cf_type, 'NULL'));
   deb('this_db_unique_name: ' || nvl(this_db_unique_name, 'NULL'));
 
   deb('Boolean variables');
   if debug is NULL then
      deb('debug is NULL');
   elsif scr_glob then
      deb('debug is TRUE');
   else
      deb('debug is FALSE');
   end if;
 
   if client_site_aware is NULL then
      deb('client_site_aware is NULL');
   elsif client_site_aware then
      deb('client_site_aware is TRUE');
   else
      deb('client_site_ware is FALSE');
   end if;
 
   if scr_glob is NULL then
      deb('scr_glob is NULL');
   elsif scr_glob then
      deb('scr_glob is TRUE');
   else
      deb('scr_glob is FALSE');
   end if;
 
   if do_temp_ts_resync is NULL then
      deb('do_temp_ts_resync is NULL');
   elsif do_temp_ts_resync then
      deb('do_temp_ts_resync is TRUE');
   else
      deb('do_temp_ts_resync is FALSE');
   end if;
end dumpPkgState;
 
/*--------------------------------------------------*
 * package private fns required for recover server  *
 *--------------------------------------------------*/
PROCEDURE read_from_http(resp IN OUT utl_http.resp, clobvar IN OUT CLOB) IS
   data raw(1024);
   pos Integer;
   amt Binary_Integer;
BEGIN
   dbms_lob.createtemporary(clobvar, TRUE);
   pos := 1;
   BEGIN
      LOOP
         utl_http.read_raw(resp, data);
         amt := length(utl_raw.cast_to_varchar2(data)); 
         dbms_lob.write(clobvar, amt, pos, utl_raw.cast_to_varchar2(data));
         pos := pos + amt;
      END LOOP;
   EXCEPTION
      WHEN utl_http.end_of_body THEN
      NULL;
   END;
END;
 
PROCEDURE display_http_response(resp IN OUT utl_http.resp) IS
   hdrcnt integer;
   name varchar2(256);
   value varchar2(256);
BEGIN
   IF not debug THEN
      RETURN;
   END IF;
   hdrcnt := utl_http.get_header_count(resp);
   deb('^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ');
   deb('Response header count = ' || hdrcnt);
   deb('^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ');
   loop
      exit when hdrcnt = 0;
      utl_http.get_header(resp, hdrcnt, name, value);
      deb('Hdr# '|| hdrcnt || ' Name= ' || name || 
                           ' Value= ' || value);
      hdrcnt := hdrcnt - 1;
   end loop;
END;
 
PROCEDURE display_clob(val IN CLOB) IS
   buf varchar2(2048 CHAR);
   origlen integer;
   len integer;
   pos integer;
   amt integer;
BEGIN
   IF not debug THEN
      RETURN;
   END IF;
   deb('DISPLAY_CLOB BEGIN');
   amt := 2048; -- amount to read at a time
   len := dbms_lob.getlength(val);
   origlen := len;
   deb('xmlfile len = ' || len);
   pos := 1;
   loop
      exit when pos > origlen;
      if len < amt then
         dbms_lob.read(val, len, pos, buf);
         pos := pos + len;
      else
         dbms_lob.read(val, amt, pos, buf);
         len := len - amt;
         pos := pos + amt;
      end if;
--
--
--
   end loop;
   deb('DISPLAY_CLOB END');
END;
 
FUNCTION get_Complete_URL (bktname in varchar2,
                           objname in varchar2,
                           awsr    OUT varchar2,
                           parms   in varchar2 DEFAULT null) 
RETURN VARCHAR2 IS
   complete_url varchar2(2048);
   aws_resource varchar2(512);
BEGIN
   IF this_amazon_server THEN
     complete_url := bktname || '.' || this_server_url;
   ELSE
     complete_url := this_server_url || '/orssv';
     aws_resource := '/orssv';
   END IF;
 
   IF this_server_url IS NULL THEN
      raise_application_error(-20999, 'internal error:this_server_url');
   END IF;
 
   IF this_url_db_unique_name IS NULL THEN
      raise_application_error(-20999, 'internal error:this_url_db_unique_name');
   END IF;
 
   complete_url := complete_url || '/rcfile/' || objname || '?' || 
                   'x-DBUName=' || this_url_db_unique_name;
   if parms is not null then
      complete_url := complete_url || '&' || parms;
   end if;
 
   aws_resource := aws_resource || '/rcfile/' || objname;
   awsr := aws_resource;
 
   IF debug THEN
      deb('URL=' || complete_url);
      deb('awsr=' || awsr);
   END IF;
   RETURN complete_url;
END get_Complete_URL;
 
/*----------------------------------------------------*
 * package public fns required for recover server     *
 * The concept of bucket is used only for amazon S3   *
 *----------------------------------------------------*/
PROCEDURE put_bucket(bktname in varchar2) IS
   objval CLOB := NULL;
BEGIN
   IF this_amazon_server THEN
     put_object(bktname, null, null, objval);
   END IF;
END;
 
FUNCTION get_bucket(bktname in varchar2) RETURN CLOB IS
BEGIN
   IF this_amazon_server THEN
      return get_object(bktname, null);
   END IF;
   return NULL;
END;
 
PROCEDURE delete_bucket(bktname in varchar2) IS
BEGIN
   IF this_amazon_server THEN
      delete_object(bktname, null);
   END IF;
END;
 
PROCEDURE utl_httl_12cR1fns(req IN OUT utl_http.req, awsr IN VARCHAR2) IS
BEGIN
   $IF DBMS_DB_VERSION.VERSION < 12 $THEN
      raise_application_error(-20999,
         'internal error: not support database version');
   $ELSE
      IF this_amazon_server then
         execute immediate 'begin utl_http.set_property(
                               :req,  
                               ''aws-canonicalized-resource'', :awsr); end;'
                 using in out req, in awsr;
         deb('sqlcode after set_property on: ' || sqlcode);
 
         execute immediate ' begin 
                                 utl_http.set_authentication_from_wallet(
                                                  r      => :req, 
                                                  alias  => :wallet_alias,
                                                  scheme => ''AWS''); end;'
                  using in out req, in this_server.wallet_alias;
         deb('sqlcode after set_authentication_from_wallet on: ' || sqlcode);
      ELSE
         execute immediate ' begin 
                                utl_http.set_authentication(
                                                 r        => :req,
                                                 username => :username,
                                                 password => :password,
                                                 scheme   => ''Digest''); end;'
                 using in out req, in this_server_uname,
                       in this_server_passw;
         deb('sqlcode after set_authentication on: ' || sqlcode);
      END IF;
   $END
END;
 
PROCEDURE put_object(bktname in varchar2, 
                     objname in varchar2,
                     objtype in varchar2,
                     objval in out CLOB) is
   req utl_http.req;
   resp utl_http.resp;
   len Integer;
   pos Integer;
   amt Integer;
   origlen Integer;
   buf raw(2048);
   cbuf varchar2(2048 CHAR);
   local_response_val CLOB;
   ct timestamp(0);
   cdt varchar2(50);
   awsr varchar2(512);
   timeout_secs number;
   retries      number := -1;
 
BEGIN
 
   IF this_server.server_host IS NULL THEN
      deb('put_object: this_server.server_host is null');
      return;
   END IF;
 
   deb('Enter put_object ' || objname);
 
   timeout_secs := this_server.timeout_secs;
 
<<retry_request_again>>
 
   BEGIN
      retries := retries + 1;
      deb('retries count is ' || retries);
      timeout_secs := timeout_secs + retries * timeout_secs;
 
      select sys_extract_utc(current_timestamp) into ct from dual;
      cdt := to_char(ct, 'Dy, DD Mon YYYY HH24:MI:SS') || ' GMT';
 
      utl_http.set_transfer_timeout(timeout_secs);
      req := utl_http.begin_request(get_Complete_URL(bktname, objname, awsr), 
                                    'PUT', utl_http.HTTP_VERSION_1_1);
 
      utl_http.set_header(req, 'Date', cdt);
      if objname is not null then
         utl_http.set_header(req, 'Content-Type', objtype);
      end if;
      
      utl_httl_12cR1fns( req, awsr);
 
      IF objname is null THEN
         utl_http.set_header(req, 'Content-Length', 0);
      ELSE
         dbms_lob.open(objval, dbms_lob.lob_readonly);
         len := dbms_lob.getlength(objval);
         deb('Content-length = ' || len);
         utl_http.set_header(req, 'Content-Length',len);
         origlen := len;
         amt := 2048;
         pos := 1;
         if lower(objtype) = 'text/xml' then
            loop
               exit when pos > origlen;
               if len < amt then
                  dbms_lob.read(objval, len, pos, cbuf);
                  pos := pos + len;
               else
                  dbms_lob.read(objval, amt, pos, cbuf);
                  len := len - amt;
                  pos := pos + amt;
               end if;
               utl_http.write_raw(req, utl_raw.cast_to_raw(cbuf));
            end loop;
         ELSE
            loop
               exit when pos > origlen;
               if len < amt then
                  dbms_lob.read(objval, len, pos, buf);
                  pos := pos + len;
               else
                  dbms_lob.read(objval, amt, pos, buf);
                  len := len - amt;
                  pos := pos + amt;
               end if;
               utl_http.write_raw(req, buf);
            end loop;
         end if;
         dbms_lob.close(objval);
      END IF;
 
      resp := utl_http.get_response(req);
 
      display_http_response(resp);
 
      read_from_http(resp, local_response_val);
      display_clob(local_response_val);
 
      utl_http.end_response(resp);
      deb('Exit put_object');
 
   EXCEPTION
      WHEN Utl_Http.request_failed THEN
         deb ('Request_Failed:' || 
                               Utl_Http.get_detailed_sqlerrm);
         Utl_Http.end_request (req);
      WHEN Utl_Http.http_server_error THEN
         deb ('Http_Server_Error: ' ||
                               Utl_Http.get_detailed_sqlerrm);
         Utl_Http.end_request (req);
      WHEN Utl_Http.http_client_error THEN
         deb ('Http_Client_Error: ' || 
                               Utl_Http.get_detailed_sqlerrm);
         Utl_Http.end_request (req);
      WHEN OTHERS THEN
         deb('HTTP Other error:' || sqlerrm);
         if retries <= 5 and substr(sqlerrm, 1, 9)  = 'ORA-29276' then
            goto retry_request_again;
         else
            raise;
         end if;
   END;
END put_object;
 
PROCEDURE delete_object(bktname in varchar2, objname in varchar2) is
   req utl_http.req;
   resp utl_http.resp;
   tmpfile CLOB := NULL;
   ct timestamp(0);
   cdt varchar2(50);
   awsr varchar2(512);
BEGIN
 
   IF this_server.server_host IS NULL THEN
      deb('delete_object: this_server.server_host is null');
      return;
   END IF;
 
   deb('Enter delete_object');
 
   select sys_extract_utc(current_timestamp) into ct from dual;
   cdt := to_char(ct, 'Dy, DD Mon YYYY HH24:MI:SS') || ' GMT';
 
   utl_http.set_transfer_timeout(this_server.timeout_secs);
   req := utl_http.begin_request(get_Complete_URL(bktname, objname, awsr), 
                                 'DELETE', utl_http.HTTP_VERSION_1_1);
 
   utl_http.set_header(req, 'Date', cdt);
      
   utl_httl_12cR1fns( req, awsr);
 
   resp := utl_http.get_response(req);
   display_http_response(resp);
 
   read_from_http(resp, tmpfile);
   display_clob(tmpfile);
 
   utl_http.end_response(resp);
   deb('Exit delete_object');
 
EXCEPTION
   WHEN Utl_Http.request_failed THEN
      deb ('Request_Failed:' || 
                            Utl_Http.get_detailed_sqlerrm);
      Utl_Http.end_request (req);
   WHEN Utl_Http.http_server_error THEN
      deb ('Http_Server_Error: ' ||
                            Utl_Http.get_detailed_sqlerrm);
      Utl_Http.end_request (req);
   WHEN Utl_Http.http_client_error THEN
      deb ('Http_Client_Error: ' || 
                            Utl_Http.get_detailed_sqlerrm);
      Utl_Http.end_request (req);
      return;
   WHEN OTHERS THEN
      deb('HTTP Other error: ' || sqlerrm);
      raise;
END delete_object;
 
FUNCTION get_object(bktname in varchar2, 
                    objname in varchar2,
                    parms   in varchar2 DEFAULT null) return CLOB is
   req          utl_http.req;
   resp         utl_http.resp;
   objval       CLOB := NULL;
   ct           timestamp(0);
   cdt          varchar2(50);
   awsr         varchar2(512);
   timeout_secs number;
   retries      number := -1;
 
begin
 
   IF this_server.server_host IS NULL THEN
      deb('get_object: this_server.server_host is null');
      return NULL;
   END IF;
 
   deb('Enter get_object ' || objname);
 
   timeout_secs := this_server.timeout_secs;
 
<<retry_request_again>>
 
   BEGIN
      retries := retries + 1;
      deb('retries count is ' || retries);
      timeout_secs := timeout_secs + retries * timeout_secs;
 
 
      select sys_extract_utc(current_timestamp) into ct from dual;
      cdt := to_char(ct, 'Dy, DD Mon YYYY HH24:MI:SS') || ' GMT';
 
      utl_http.set_transfer_timeout(timeout_secs);
      req := utl_http.begin_request(
                                  get_Complete_URL(bktname,objname,awsr,parms),
                                  'GET', utl_http.HTTP_VERSION_1_1);
 
      utl_http.set_header(req, 'Date', cdt);
      utl_httl_12cR1fns( req, awsr);
      resp := utl_http.get_response(req);
      display_http_response(resp);
 
      read_from_http(resp, objval);
      display_clob(objval);
 
      utl_http.end_response(resp);
 
      IF dbms_lob.getlength(objval) > 0 then
         IF objname is null THEN
            delete from rcfile where name = parms;
            insert into rcfile(rcfile_key, creation_time, name, xmldoc)
               values (rman_seq.nextval, sys_extract_utc(systimestamp), parms, 
                       XMLType.createXML(objval));
            deb('got rows for ' || parms);
            commitChanges('get_object-1');
         ELSE
            delete from rcfile where name = objname;
            insert into rcfile(rcfile_key, creation_time, name, xmldoc)
               values (rman_seq.nextval, sys_extract_utc(systimestamp),objname,
                       XMLType.createXML(objval));
            deb('got rows for ' || objname);
            commitChanges('get_object-2');
         END IF;
      END IF;
      deb('Exit get_object');
   
   return objval;
   EXCEPTION
      WHEN Utl_Http.request_failed THEN
         deb ('Request_Failed:' || 
                               Utl_Http.get_detailed_sqlerrm);
         Utl_Http.end_request (req);
      WHEN Utl_Http.http_server_error THEN
         deb ('Http_Server_Error: ' ||
                               Utl_Http.get_detailed_sqlerrm);
         Utl_Http.end_request (req);
      WHEN Utl_Http.http_client_error THEN
         deb ('Http_Client_Error: ' || 
                               Utl_Http.get_detailed_sqlerrm);
         Utl_Http.end_request (req);
      WHEN OTHERS THEN
         deb('HTTP Other error: ' || sqlerrm);
         if retries <= 5 and substr(sqlerrm, 1, 9)  = 'ORA-29276' then
            goto retry_request_again;
         else
            raise;
         end if;
   END;
END get_object;
 
PROCEDURE displayRCWatermarks(cmt IN VARCHAR2, wmrec IN RC_WATERMARKS%ROWTYPE) IS
BEGIN
   IF NOT debug THEN
      RETURN;
   END IF;
 
   deb(cmt || 'DB_KEY=' || wmrec.DB_KEY);
   deb(cmt || 'DB_UNIQUE_NAME=' || wmrec.DB_UNIQUE_NAME);
   deb(cmt || 'RS_VERSION_STAMP=' || wmrec.RS_VERSION_STAMP);
   deb(cmt || 'HIGH_BP_RECID=' || wmrec.HIGH_BP_RECID);
   deb(cmt || 'HIGH_DO_KEY=' || wmrec.HIGH_DO_KEY);
   deb(cmt || 'CF_VERSION_STAMP=' || wmrec.CF_VERSION_STAMP);
   deb(cmt || 'HIGH_DF_RECID=' || wmrec.HIGH_DF_RECID);
   deb(cmt || 'HIGH_TS_RECID=' || wmrec.HIGH_TS_RECID);
   deb(cmt || 'HIGH_TF_RECID=' || wmrec.HIGH_TF_RECID);
   deb(cmt || 'HIGH_OFFR_RECID=' || wmrec.HIGH_OFFR_RECID);
END displayRCWatermarks;
 
PROCEDURE displayRCSite(cmt IN VARCHAR2, siterec IN RC_SITE%ROWTYPE) IS
BEGIN
   IF NOT debug THEN
      RETURN;
   END IF;
 
   deb(cmt || 'SITE_KEY=' || siterec.SITE_KEY);
   deb(cmt || 'DB_KEY=' || siterec.DB_KEY);
   deb(cmt || 'DATABASE_ROLE=' || siterec.DATABASE_ROLE);
   deb(cmt || 'CF_CREATE_TIME=' || siterec.CF_CREATE_TIME);
   deb(cmt || 'DB_UNIQUE_NAME=' || siterec.DB_UNIQUE_NAME);
   deb(cmt || 'HIGH_CONF_RECID=' || siterec.HIGH_CONF_RECID);
   deb(cmt || 'FORCE_RESYNC2CF=' || siterec.FORCE_RESYNC2CF);
   deb(cmt || 'HIGH_ROUT_STAMP=' || siterec.HIGH_ROUT_STAMP);
   deb(cmt || 'INST_STARTUP_STAMP=' || siterec.INST_STARTUP_STAMP);
   deb(cmt || 'LAST_KCCDIVTS=' || stamp2date(siterec.LAST_KCCDIVTS));
   deb(cmt || 'HIGH_IC_RECID=' || siterec.HIGH_IC_RECID);
   deb(cmt || 'DBINC_KEY=' || siterec.DBINC_KEY);
   deb(cmt || 'CKP_SCN=' || siterec.CKP_SCN);
   deb(cmt || 'FULL_CKP_CF_SEQ=' || siterec.FULL_CKP_CF_SEQ);
   deb(cmt || 'JOB_CKP_CF_SEQ=' || siterec.JOB_CKP_CF_SEQ);
   deb(cmt || 'HIGH_TS_RECID=' || siterec.HIGH_TS_RECID);
   deb(cmt || 'HIGH_DF_RECID=' || siterec.HIGH_DF_RECID);
   deb(cmt || 'HIGH_RT_RECID=' || siterec.HIGH_RT_RECID);
   deb(cmt || 'HIGH_ORL_RECID=' || siterec.HIGH_ORL_RECID);
   deb(cmt || 'HIGH_OFFR_RECID=' || siterec.HIGH_OFFR_RECID);
   deb(cmt || 'HIGH_RLH_RECID=' || siterec.HIGH_RLH_RECID);
   deb(cmt || 'HIGH_AL_RECID=' || siterec.HIGH_AL_RECID);
   deb(cmt || 'HIGH_BS_RECID=' || siterec.HIGH_BS_RECID);
   deb(cmt || 'HIGH_BP_RECID=' || siterec.HIGH_BP_RECID);
   deb(cmt || 'HIGH_BDF_RECID=' || siterec.HIGH_BDF_RECID);
   deb(cmt || 'HIGH_CDF_RECID=' || siterec.HIGH_CDF_RECID);
   deb(cmt || 'HIGH_BRL_RECID=' || siterec.HIGH_BRL_RECID);
   deb(cmt || 'HIGH_BCB_RECID=' || siterec.HIGH_BCB_RECID);
   deb(cmt || 'HIGH_CCB_RECID=' || siterec.HIGH_CCB_RECID);
   deb(cmt || 'HIGH_DO_RECID=' || siterec.HIGH_DO_RECID);
   deb(cmt || 'HIGH_PC_RECID=' || siterec.HIGH_PC_RECID);
   deb(cmt || 'HIGH_BSF_RECID=' || siterec.HIGH_BSF_RECID);
   deb(cmt || 'HIGH_RSR_RECID=' || siterec.HIGH_RSR_RECID);
   deb(cmt || 'HIGH_TF_RECID=' || siterec.HIGH_TF_RECID);
   deb(cmt || 'HIGH_GRSP_RECID=' || siterec.HIGH_GRSP_RECID);
   deb(cmt || 'HIGH_NRSP_RECID=' || siterec.HIGH_NRSP_RECID);
   deb(cmt || 'HIGH_BCR_RECID=' || siterec.HIGH_BCR_RECID);
   deb(cmt || 'LOW_BCR_RECID=' || siterec.LOW_BCR_RECID);
   deb(cmt || 'BCR_IN_USE=' || siterec.BCR_IN_USE);
   deb(cmt || 'HIGH_PDB_RECID=' || siterec.HIGH_PDB_RECID);
   deb(cmt || 'HIGH_PIC_RECID=' || siterec.HIGH_PIC_RECID);
END displayRCSite;
 
PROCEDURE displayRCDatabaseIncarnation
   (cmt IN VARCHAR2, icrec IN RC_DATABASE_INCARNATION%ROWTYPE) IS
BEGIN
   IF NOT debug THEN
      RETURN;
   END IF;
 
   deb(cmt || 'DB_KEY=' || icrec.DB_KEY); 
   deb(cmt || 'DBID=' || icrec.DBID); 
   deb(cmt || 'DBINC_KEY=' || icrec.DBINC_KEY);  
   deb(cmt || 'NAME=' || icrec.NAME); 
   deb(cmt || 'RESETLOGS_CHANGE#=' || icrec.RESETLOGS_CHANGE#); 
   deb(cmt || 'RESETLOGS_TIME=' || icrec.RESETLOGS_TIME);
   deb(cmt || 'CURRENT_INCARNATION=' || icrec.CURRENT_INCARNATION);
   deb(cmt || 'PARENT_DBINC_KEY=' || icrec.PARENT_DBINC_KEY); 
   deb(cmt || 'PRIOR_RESETLOGS_CHANGE#=' || icrec.PRIOR_RESETLOGS_CHANGE#);
   deb(cmt || 'PRIOR_RESETLOGS_TIME=' || icrec.PRIOR_RESETLOGS_TIME);
   deb(cmt || 'STATUS=' || icrec.STATUS);
   deb(cmt || 'REG_DB_UNIQUE_NAME=' || icrec.REG_DB_UNIQUE_NAME);
   deb(cmt || 'CON_ID=' || icrec.CON_ID);
   deb(cmt || 'GUID=' || icrec.GUID);
END displayRCDatabaseIncarnation;
 
PROCEDURE writeForWatermarks(bktname   IN VARCHAR2 DEFAULT NULL,
                             full_ckpt IN BOOLEAN) IS
   v_ctx     DBMS_XMLGen.ctxHandle;
   v_xml     CLOB;
   v_xml_tmp CLOB;
   type record_sql_type is table of varchar2(2048) 
             index by binary_integer;
   type record_tbl_type is table of varchar2(30) 
             index by binary_integer;
   record_sql  record_sql_type;
   record_tbl  record_tbl_type;
   my_dbid      number;
   write_xml_filename rcfile.name%TYPE;
 
BEGIN
--
   IF this_server.server_host IS NULL THEN
      deb('writeForWaterMarks: this_server.server_host is null');
      return;
   END IF;
 
   IF (this_db_key IS NULL) THEN
      raise_application_error(-20021, 'Database not set');
   END IF;
 
   SELECT DBID INTO MY_DBID FROM RC_DATABASE WHERE DB_KEY=this_db_key;
 
   record_tbl(0)   := 'RC_DATABASE';
   record_sql(0)   := 'SELECT * FROM RC_DATABASE ' || 
                              'WHERE DB_KEY = ' || this_db_key;
 
   record_tbl(1)   := 'RC_DATABASE_INCARNATION';
   record_sql(1)   := 'SELECT * FROM RC_DATABASE_INCARNATION ' ||
                              'WHERE DB_KEY = ' || this_db_key;
 
   record_tbl(2)   := 'RC_SITE';
   IF full_ckpt THEN
      deb('writeForWatermarks for remote readFixedSections');
--
--
--
      record_sql(2)   := 'SELECT -1 high_bp_recid FROM RCI_SITE ' ||
                            'WHERE DB_KEY   = ' || this_db_key ||
                            '  AND SITE_KEY = ' || this_site_key;
   ELSE
      deb('writeForWatermarks for local readBackupSections');
      record_sql(2)   := 'SELECT * FROM RCI_SITE ' ||
                            'WHERE DB_KEY   = ' || this_db_key ||
                            '  AND SITE_KEY = ' || this_site_key;
   END IF;
 
   v_xml := this_xml_signature_beg;
   dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
 
   FOR idx in 0..2 LOOP
      deb('writing XML file for ' || idx);
      deb('executing query:'|| record_sql(idx));
 
      v_ctx := DBMS_XMLGen.newContext(record_sql(idx));
 
--
      DBMS_XMLGen.setRowsetTag(v_ctx, 'TABLE_' || record_tbl(idx));
      v_xml_tmp := DBMS_XMLGen.GetXML(v_ctx);
      DBMS_XMLGen.closeContext(v_ctx);
 
      deb('XML len for ' || idx || '=' || DBMS_LOB.GETLENGTH(v_xml_tmp));
      IF v_xml_tmp IS NOT NULL THEN
         DBMS_LOB.COPY(v_xml, v_xml_tmp, DBMS_LOB.LOBMAXSIZE, 
                       DBMS_LOB.GETLENGTH(v_xml),
                       DBMS_LOB.INSTR(v_xml_tmp, 
                                  '<TABLE_' || record_tbl(idx) ||'>'));
      END IF;
   END LOOP;
 
   v_xml := v_xml || this_xml_signature_end;
 
   write_xml_filename := this_forwatermarks || my_dbid || '.xml';
   put_bucket(bktname);
   put_object(bktname, write_xml_filename, 'text/xml', v_xml);
 
END writeForWatermarks;
 
PROCEDURE rsReadWaterMarks(bktname  IN VARCHAR2 DEFAULT NULL) IS
   xml_filename RCFILE.NAME%TYPE;
   my_dbid      NUMBER;
   v_xml_tmp    CLOB;
   v_ctx        DBMS_XMLGen.ctxHandle;
   verrec       RC_RCVER%ROWTYPE;
   curr_inc     RC_DATABASE_INCARNATION%ROWTYPE;
   wmrec        RC_WATERMARKS%ROWTYPE;
 
   CURSOR rc_rcver_c IS
   SELECT VERSION
   FROM "_RS_RC_RCVER_"
   WHERE RS_RCFILE_NAME = xml_filename;
 
   CURSOR rc_watermarks_c IS
   SELECT DB_KEY,
          DB_UNIQUE_NAME,
          RS_VERSION_STAMP,
          HIGH_BP_RECID,
          HIGH_DO_KEY,
          CF_VERSION_STAMP,
          HIGH_DF_RECID,
          HIGH_TS_RECID,
          HIGH_TF_RECID,
          HIGH_OFFR_RECID
   FROM "_RS_RC_WATERMARKS_"
   WHERE RS_RCFILE_NAME = xml_filename;
 
   CURSOR rc_database_incarnation_c IS
   SELECT DB_KEY, 
          DBID, 
          DBINC_KEY,  
          NAME, 
          RESETLOGS_CHANGE#, 
          RESETLOGS_TIME,
          CURRENT_INCARNATION,
          PARENT_DBINC_KEY, 
          PRIOR_RESETLOGS_CHANGE#,
          PRIOR_RESETLOGS_TIME,
          STATUS,
          REG_DB_UNIQUE_NAME,
          CON_ID,
          GUID
   FROM "_RS_RC_DATABASE_INCARNATION_"
   WHERE RS_RCFILE_NAME = xml_filename
     AND STATUS = 'CURRENT';
 
   upstream_dbrec RCI_RA_UPSTREAM_DATABASE%ROWTYPE;
   CURSOR rci_ra_upstream_database_c IS
   SELECT  DBID,
           NAME
   FROM "_RS_RCI_RA_UPSTREAM_DATABASE_"
   WHERE RS_RCFILE_NAME = xml_filename;
 
   CURSOR local_current_incarnation_c IS
   SELECT *
   FROM RC_DATABASE_INCARNATION
   WHERE DBINC_KEY = this_dbinc_key;
   local_curr_inc RC_DATABASE_INCARNATION%ROWTYPE;
 
BEGIN
 
--
   IF this_server.server_host IS NULL THEN
      deb('rsReadWaterMarks: this_server.server_host is null');
      return;
   END IF;
 
   IF (this_db_key IS NULL) THEN
      raise_application_error(-20021, 'Database not set');
   END IF;
 
   SELECT DBID INTO MY_DBID FROM RC_DATABASE
      WHERE DB_KEY=this_db_key;
 
--
--
   xml_filename := this_watermarks || my_dbid || '.xml';
   v_xml_tmp := get_object(bktname, xml_filename);
 
--
   dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
 
--
   OPEN rc_rcver_c;
   FETCH rc_rcver_c INTO verrec;
   CLOSE rc_rcver_c;
 
--
   OPEN rc_watermarks_c;
   FETCH rc_watermarks_c INTO wmrec;
   CLOSE rc_watermarks_c;
 
--
   OPEN rc_database_incarnation_c;
   FETCH rc_database_incarnation_c INTO curr_inc;
   CLOSE rc_database_incarnation_c;
 
--
   OPEN local_current_incarnation_c;
   FETCH local_current_incarnation_c INTO local_curr_inc;
   CLOSE local_current_incarnation_c;
 
--
   OPEN rci_ra_upstream_database_c;
   FETCH rci_ra_upstream_database_c INTO upstream_dbrec;
   CLOSE rci_ra_upstream_database_c;
 
--
   IF curr_inc.dbid <> rsReadWaterMarks.my_dbid THEN
      raise_application_error(-20141, 
                 'Target database id mismatch with registered database in ORS');
   END IF;
 
--
--
--
--
   v_ctx := DBMS_XMLGen.newContext('SELECT DB_KEY, DB_UNIQUE_NAME, ' ||
                'RS_VERSION_STAMP,'||
                'HIGH_BP_RECID,'||
                'HIGH_DO_KEY, CF_VERSION_STAMP,'||
                'HIGH_DF_RECID, '||
                'HIGH_TS_RECID, HIGH_TF_RECID, HIGH_OFFR_RECID '||
                'FROM "_RS_RC_WATERMARKS_" '||
                'WHERE RS_RCFILE_NAME = ''' || xml_filename || '''');
 
--
   DBMS_XMLGen.setRowsetTag(v_ctx, 'TABLE_RC_WATERMARKS');
   this_v_wmrec := DBMS_XMLGen.GetXML(v_ctx);
   DBMS_XMLGen.closeContext(v_ctx);
 
   IF upstream_dbrec.name IS NULL THEN
      raise_application_error(-20999, 'internal error: no rows for upstreamdb');
   END IF;
 
   this_verrec := verrec;
   this_curr_inc := curr_inc;
   this_wmrec  := wmrec;
   this_upstream_dbrec := upstream_dbrec;
 
   IF debug THEN
      deb('rsReadWatermarks: remote reconcile dbname=' || 
           this_upstream_dbrec.name);
      deb('this_curr_inc.dbid: ' || this_curr_inc.dbid);
 
      displayRCWatermarks('rsReadWatermarks remote:', this_wmrec);
      displayRCDatabaseIncarnation('rsReadWatermarks remote:', curr_inc);
      displayRCDatabaseIncarnation('rsReadWatermarks local:', local_curr_inc);
   END IF;
 
   
--
--
   IF curr_inc.resetlogs_change# <> local_curr_inc.resetlogs_change# OR
      curr_inc.resetlogs_time <> local_curr_inc.resetlogs_time THEN
      deb('Clearing this_wmrec.cf_version_stamp so all fixed records are sent');
      this_wmrec.cf_version_stamp := NULL;
   END IF;
 
END rsReadWaterMarks;
 
--
PROCEDURE rsWriteWaterMarks (input_xml_filename  IN VARCHAR2,
                             bktname IN VARCHAR2 DEFAULT NULL) IS
   v_ctx     DBMS_XMLGen.ctxHandle;
   v_xml     CLOB;
   v_xml_tmp CLOB;
   type record_sql_type is table of varchar2(2048) 
             index by binary_integer;
   type record_tbl_type is table of varchar2(30) 
             index by binary_integer;
   record_sql  record_sql_type;
   record_tbl  record_tbl_type;
   my_dbid      number;
   l_max_do_key number;
   l_high_do_key number;
 
   write_xml_filename rcfile.name%TYPE;
 
   curr_inc RC_DATABASE_INCARNATION%ROWTYPE;
   CURSOR rc_database_incarnation_c(curr_inc_val IN VARCHAR2 DEFAULT NULL) IS
   SELECT DB_KEY, 
          DBID, 
          DBINC_KEY,  
          NAME, 
          RESETLOGS_CHANGE#, 
          RESETLOGS_TIME,
          CURRENT_INCARNATION,
          PARENT_DBINC_KEY, 
          PRIOR_RESETLOGS_CHANGE#,
          PRIOR_RESETLOGS_TIME,
          STATUS,
          REG_DB_UNIQUE_NAME,
          CON_ID,
          GUID
   FROM "_RS_RC_DATABASE_INCARNATION_"
   WHERE RS_RCFILE_NAME = input_xml_filename
     AND (curr_inc_val IS NULL OR
          CURRENT_INCARNATION = curr_inc_val);
 
   CURSOR rc_site_c IS
   SELECT  SITE_KEY,
           DB_KEY,
           DATABASE_ROLE,
           CF_CREATE_TIME,
           DB_UNIQUE_NAME,
           HIGH_CONF_RECID,
           FORCE_RESYNC2CF,
           HIGH_ROUT_STAMP,
           INST_STARTUP_STAMP,
           LAST_KCCDIVTS,
           HIGH_IC_RECID,
           DBINC_KEY,
           CKP_SCN,
           FULL_CKP_CF_SEQ,
           JOB_CKP_CF_SEQ,
           HIGH_TS_RECID,
           HIGH_DF_RECID,
           HIGH_RT_RECID,
           HIGH_ORL_RECID,
           HIGH_OFFR_RECID,
           HIGH_RLH_RECID,
           HIGH_AL_RECID,
           HIGH_BS_RECID,
           HIGH_BP_RECID,
           HIGH_BDF_RECID,
           HIGH_CDF_RECID,
           HIGH_BRL_RECID,
           HIGH_BCB_RECID,
           HIGH_CCB_RECID,
           HIGH_DO_RECID,
           HIGH_PC_RECID,
           HIGH_BSF_RECID,
           HIGH_RSR_RECID,
           HIGH_TF_RECID,
           HIGH_GRSP_RECID,
           HIGH_NRSP_RECID,
           HIGH_BCR_RECID,
           LOW_BCR_RECID,
           BCR_IN_USE,
           HIGH_PDB_RECID,
           HIGH_PIC_RECID
   FROM "_RS_RC_SITE_"
   WHERE RS_RCFILE_NAME = input_xml_filename;
 
   l_stmts   NUMBER;
 
BEGIN
 
--
   my_dbid := to_number(substr(input_xml_filename, 
                               length(this_forwatermarks)+1, 
                               instr(input_xml_filename,'.xml')-1
                                     -length(this_forwatermarks)));
 
   deb('rsWriteWaterMarks:remote my_dbid=' || my_dbid);
 
--
   dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
 
--
   OPEN rc_database_incarnation_c(curr_inc_val => 'YES');
   FETCH rc_database_incarnation_c INTO curr_inc;
   CLOSE rc_database_incarnation_c;
 
--
   OPEN rc_site_c;
   FETCH rc_site_c INTO this_rsiterec;
   CLOSE rc_site_c;
   IF debug THEN
      displayRCSite('rsWriteWaterMarks:remote ', this_rsiterec);
   END IF;
 
--
   IF curr_inc.dbid <> my_dbid THEN
      raise_application_error(-20141, 
                 'Target database id mismatch with registered database in ORS');
   END IF;
 
 
   record_tbl(0)   := 'RCI_RA_UPSTREAM_DATABASE';
   record_sql(0)   := 'SELECT * FROM RCI_RA_UPSTREAM_DATABASE';
 
   record_tbl(1)   := 'RC_RCVER';
   record_sql(1)   := 'SELECT * FROM RC_RCVER ';
 
 
   l_stmts  := 1;
 
--
   BEGIN
      SELECT DB_KEY INTO this_db_key FROM DB
         WHERE db_id = my_dbid;
 
      deb('rsWriteWaterMarks:this_db_key=' || this_db_key);
 
--
      dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
 
 
      record_tbl(2)   := 'RC_DATABASE_INCARNATION';
      record_sql(2)   := 'SELECT * FROM RC_DATABASE_INCARNATION ' || 
                            'WHERE DB_KEY = ' || this_db_key;
 
      record_tbl(3)   := 'RC_WATERMARKS';
      record_sql(3)   := 'SELECT * FROM RC_WATERMARKS ' || 
                            'WHERE DB_KEY = ' || this_db_key;
 
      l_stmts  := 3;
 
--
      BEGIN
         SELECT high_do_key INTO l_high_do_key 
            FROM watermarks WHERE db_key = this_db_key;
         SELECT curr_value INTO l_max_do_key 
            FROM do_seq WHERE db_key = this_db_key;
         IF l_max_do_key > l_high_do_key THEN
            UPDATE watermarks
               SET high_do_key = l_max_do_key
               WHERE db_key = this_db_key;
            commitChanges('rsWritewaterMarks, set high_do_key='||l_max_do_key);
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND THEN
            NULL;
      END;
 
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         deb('rsWritewaterMarks: database with dbid=' || my_dbid ||
                                 ' not found in recovery catalog');
   END;
 
 
--
      v_xml := this_xml_signature_beg;
      FOR idx in 0..l_stmts  LOOP
         deb('writing XML file for ' || idx);
         deb('executing query:'|| record_sql(idx));
   
         v_ctx := DBMS_XMLGen.newContext(record_sql(idx));
 
--
         DBMS_XMLGen.setRowsetTag(v_ctx, 'TABLE_' || record_tbl(idx));
         v_xml_tmp := DBMS_XMLGen.GetXML(v_ctx);
         DBMS_XMLGen.closeContext(v_ctx);
 
         deb('XML len for ' || idx || '=' || DBMS_LOB.GETLENGTH(v_xml_tmp));
         IF v_xml_tmp IS NOT NULL THEN
            DBMS_LOB.COPY(v_xml, v_xml_tmp, DBMS_LOB.LOBMAXSIZE, 
                          DBMS_LOB.GETLENGTH(v_xml),
                          DBMS_LOB.INSTR(v_xml_tmp, 
                                     '<TABLE_' || record_tbl(idx) ||'>'));
         END IF;
      END LOOP;
      v_xml := v_xml || this_xml_signature_end;
 
--
      write_xml_filename := this_watermarks || my_dbid || '.xml';
      IF this_amazon_server THEN
         put_object(null /*bktname */, write_xml_filename, 'text/xml', v_xml);
      ELSE
         delete rcfile where name = write_xml_filename;
         insert into rcfile(rcfile_key, creation_time, name, xmldoc)
            values (rman_seq.nextval, sys_extract_utc(systimestamp), 
                    write_xml_filename, XMLType.createXML(v_xml));
      END IF;
 
--
      IF this_rsiterec.high_bp_recid >= 0 THEN
         deb('rsWritewaterMarks called for remote readBackupSections');
         writeBackupSections(input_xml_filename, bktname);
      ELSE
         deb('rsWritewaterMarks called for local readFixedSections');
      END IF;
 
--
      IF this_amazon_server THEN
         delete_object(null, this_forwatermarks || my_dbid || '.xml');
      ELSE
         delete rcfile where name = this_forwatermarks || my_dbid || '.xml';
      END IF;
 
      commitChanges('rsWritewaterMarks(suc)');
   EXCEPTION
      WHEN OTHERS THEN
         deb('rsWriteWatermarks(fail)- rollback (release locks):' || sqlerrm);
         rollback;
         raise;
END rsWriteWaterMarks;
 
PROCEDURE writeFixedSections(bktname IN VARCHAR2 DEFAULT NULL) IS
   v_ctx     DBMS_XMLGen.ctxHandle;
   v_xml     CLOB;
   v_xml_tmp CLOB;
   type record_sql_type is table of varchar2(2048) 
             index by binary_integer;
   type record_tbl_type is table of varchar2(30) 
             index by binary_integer;
   type record_watermarks_type is table of number 
             index by binary_integer;
   record_sql  record_sql_type;
   record_tbl  record_tbl_type;
   read_wm     record_watermarks_type;
   curr_wm     record_watermarks_type;
 
   my_dbid      number;
   write_xml_filename rcfile.name%TYPE;
 
   full_ckp_key      number;
   curr_dbinc_key    number;
   full_ckp_site_key number;
   noderec           node%ROWTYPE;
 
BEGIN
 
   deb('writeFixedSections - enter');
 
--
   IF this_server.server_host IS NULL THEN
      deb('writeFixedSections: this_server.server_host is null');
      return;
   END IF;
 
   IF (this_db_key IS NULL) THEN
      raise_application_error(-20021, 'Database not set');
   END IF;
 
--
--
   BEGIN
      writeForWatermarks(bktname, TRUE);
      rsReadWaterMarks(bktname);
   EXCEPTION
      WHEN OTHERS THEN
         deb('writeFixedSections: error during watermarks read:' || sqlerrm);
         RAISE;
   END;
 
--
   BEGIN
      SELECT ckp_key, site_key INTO full_ckp_key, full_ckp_site_key
         FROM RC_CHECKPOINT
         WHERE CKP_KEY =
            (SELECT MAX(CKP_KEY) FROM RC_CHECKPOINT 
               WHERE DB_KEY = this_db_key 
               AND CKP_TYPE = 'FULL');
   EXCEPTION
      WHEN no_data_found THEN
         deb('writeFixedSections: no full resync yet done for this database');
         return;
   END;
 
   SELECT dbinc_key INTO curr_dbinc_key FROM RC_DATABASE
      WHERE DB_KEY = this_db_key;
 
   SELECT * INTO noderec FROM NODE WHERE SITE_KEY=full_ckp_site_key;
   deb('noderec.cf_version_stamp: ' || noderec.cf_create_time);
   deb('noderec.db_unique_name: ' || noderec.db_unique_name);
   deb('noderec.high_df_recid: ' || noderec.high_df_recid);
   deb('noderec.high_tf_recid: ' || noderec.high_tf_recid);
   deb('noderec.high_ts_recid: ' || noderec.high_ts_recid);
   select nvl(max(offr_key), 0) INTO noderec.high_offr_recid 
      from rc_offline_range where db_key=this_db_key;
   deb('noderec.high_offr_recid: ' || noderec.high_offr_recid);
 
--
   IF noderec.cf_create_time = this_wmrec.cf_version_stamp AND
      noderec.high_ts_recid = this_wmrec.high_ts_recid AND
      noderec.high_df_recid = this_wmrec.high_df_recid AND
      noderec.high_tf_recid = this_wmrec.high_tf_recid AND
      noderec.high_offr_recid = this_wmrec.high_offr_recid THEN
     deb('writeFixedSections: No event of interest occured at catalog');
     return;
   END IF;
 
   IF noderec.cf_create_time <> this_wmrec.cf_version_stamp OR
      this_wmrec.cf_version_stamp IS NULL THEN
      deb('Clearing fixed record section watermarks');
      this_wmrec.high_df_recid := NULL;
      this_wmrec.high_ts_recid := NULL;
      this_wmrec.high_tf_recid := NULL;
      this_wmrec.high_offr_recid := NULL;
   END IF;
 
   SELECT DBID INTO MY_DBID FROM RC_DATABASE WHERE DB_KEY=this_db_key;
 
   record_tbl(0)   := 'RC_DATABASE';
   record_sql(0)   := 'SELECT * FROM RC_DATABASE ' || 
                         'WHERE DB_KEY = ' || this_db_key;
   read_wm(0)      := -1; /* no watermark check for this record */
 
   record_tbl(1)   := 'RC_DATABASE_INCARNATION';
   record_sql(1)   := 'SELECT * FROM RC_DATABASE_INCARNATION ' ||
                         'WHERE DB_KEY = ' || this_db_key;
   read_wm(1)      := -1; /* no watermark check for this record */
 
   record_tbl(2)   := 'RC_SITE';
   record_sql(2)   := 'SELECT * FROM RCI_SITE ' ||
                         'WHERE DB_KEY   = ' || this_db_key ||
                         '  AND SITE_KEY = ' || full_ckp_site_key;
   read_wm(2)      := -1; /* no watermark check for this record */
 
   record_tbl(3)   := 'RC_RMAN_CONFIGURATION';
   record_sql(3)   := 'SELECT * FROM RC_RMAN_CONFIGURATION ' ||
                         'WHERE DB_KEY   = ' || this_db_key ||
                         '  AND (SITE_KEY = ' || full_ckp_site_key ||
                         '       OR SITE_KEY = 0)';
   read_wm(3)      := -1; /* no watermark check for this record */
 
   record_tbl(4)   := 'RC_TABLESPACE';
   record_sql(4)   := 'SELECT * FROM RC_TABLESPACE ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND DBINC_KEY = ' || curr_dbinc_key ||
                         '  AND DROP_CHANGE# IS NULL';
   read_wm(4)      := -1; /* watermark check only if required */
   IF this_wmrec.cf_version_stamp = noderec.cf_create_time THEN
      read_wm(4)      := this_wmrec.high_ts_recid;
      curr_wm(4)      := noderec.high_ts_recid;
   END IF;
 
   record_tbl(5)   := 'RC_REDO_THREAD';
   record_sql(5)   := 'SELECT * FROM RC_REDO_THREAD ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND DBINC_KEY = ' || curr_dbinc_key;
   read_wm(5)      := -1; /* no watermark check for this record */
 
   record_tbl(6)   := 'RCI_DATAFILE';
   record_sql(6)   := 'SELECT * FROM RCI_DATAFILE ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND DBINC_KEY = ' || curr_dbinc_key ||
                         '  AND SITE_KEY = ' || full_ckp_site_key ||
                         '  AND DROP_CHANGE# IS NULL';
   read_wm(6)      := -1; /* watermark check only if required */
   IF this_wmrec.cf_version_stamp = noderec.cf_create_time THEN
      read_wm(6)      := this_wmrec.high_df_recid;
      curr_wm(6)      := noderec.high_df_recid;
   END IF;
 
   record_tbl(7)   := 'RCI_TEMPFILE';
   record_sql(7)   := 'SELECT * FROM RCI_TEMPFILE ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND DBINC_KEY = ' || curr_dbinc_key ||
                         '  AND SITE_KEY = ' || full_ckp_site_key ||
                         '  AND DROP_CHANGE# IS NULL';
   read_wm(7)      := -1; /* watermark check only if required */
   IF this_wmrec.cf_version_stamp = noderec.cf_create_time THEN
      read_wm(7)      := this_wmrec.high_tf_recid;
      curr_wm(7)      := noderec.high_tf_recid;
   END IF;
 
   record_tbl(8)   := 'RC_REDO_LOG';
   record_sql(8)   := 'SELECT * FROM RC_REDO_LOG ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND DBINC_KEY = ' || curr_dbinc_key ||
                         '  AND SITE_KEY = ' || full_ckp_site_key;
   read_wm(8)      := -1; /* no watermark check for this record */
 
   record_tbl(9)   := 'RC_CHECKPOINT';
   record_sql(9)   := 'SELECT * FROM RC_CHECKPOINT ' ||
                         'WHERE CKP_KEY = ' || full_ckp_key;
   read_wm(9)      := -1; /* no watermark check for this record */
 
   record_tbl(10)  := 'RC_OFFLINE_RANGE';
   record_sql(10)  := 'SELECT * FROM RC_OFFLINE_RANGE ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND OFFR_KEY >'||nvl(this_wmrec.high_offr_recid,0);
   read_wm(10)     := -1; /* watermark check not required, send new records */
 
   record_tbl(11)   := 'RCI_PDBS';
   record_sql(11)   := 'SELECT * FROM RCI_PDBS ' || 
                         'WHERE DB_KEY = ' || this_db_key;
   read_wm(11)      := -1; /* no watermark check for this record */
 
   record_tbl(12)   := 'RC_PLUGGABLE_DATABASE_INC';
   record_sql(12)   := 'SELECT * FROM RC_PLUGGABLE_DATABASE_INC ' ||
                         'WHERE DB_KEY = ' || this_db_key;
   read_wm(12)      := -1; /* no watermark check for this record */
 
   record_tbl(13)   := 'RCI_RA_UPSTREAM_DATABASE';
   record_sql(13)   := 'SELECT * FROM RCI_RA_UPSTREAM_DATABASE';
   read_wm(13)      := -1; /* no watermark check for this record */
 
--
   v_xml := this_xml_signature_beg;
   dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
   FOR idx in 0..13 LOOP
      deb('writing XML file for ' || idx);
      IF read_wm (idx) <> -1 AND read_wm(idx) <> 0 AND 
         read_wm(idx) = curr_wm(idx) THEN
         deb('skipping query:'|| record_sql(idx));
      ELSE
         deb('executing query:'|| record_sql(idx));
         v_ctx := DBMS_XMLGen.newContext(record_sql(idx));
 
--
         DBMS_XMLGen.setRowsetTag(v_ctx, 'TABLE_' || record_tbl(idx));
         v_xml_tmp := DBMS_XMLGen.GetXML(v_ctx);
         DBMS_XMLGen.closeContext(v_ctx);
 
         deb('XML len for ' || idx || '=' || DBMS_LOB.GETLENGTH(v_xml_tmp));
         IF v_xml_tmp IS NOT NULL THEN
            DBMS_LOB.COPY(v_xml, v_xml_tmp, DBMS_LOB.LOBMAXSIZE, 
                          DBMS_LOB.GETLENGTH(v_xml),
                          DBMS_LOB.INSTR(v_xml_tmp, 
                                     '<TABLE_' || record_tbl(idx) ||'>'));
         END IF;
      END IF;
   END LOOP;
 
   deb('XML len for v_wmrec=' || DBMS_LOB.GETLENGTH(this_v_wmrec));
   IF this_v_wmrec IS NOT NULL THEN
            DBMS_LOB.COPY(v_xml, this_v_wmrec, DBMS_LOB.LOBMAXSIZE, 
                          DBMS_LOB.GETLENGTH(v_xml),
                          DBMS_LOB.INSTR(this_v_wmrec, 
                                     '<TABLE_RC_WATERMARKS>'));
   ELSE
      deb('writeFixedSections: no watermarks added to uploaded file');
   END IF;
 
   v_xml := v_xml || this_xml_signature_end;
 
--
   write_xml_filename := this_database || my_dbid || '.xml';
   put_bucket(bktname);
   put_object(bktname, write_xml_filename, 'text/xml', v_xml);
 
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
  
   deb('writeFixedSections:(suc)');
END writeFixedSections;
 
--
PROCEDURE readFixedSections(input_xml_filename  IN VARCHAR2,
                            bktname IN VARCHAR2 DEFAULT NULL) IS
 
   ckp_type     NUMBER;
 
   database_already_registered EXCEPTION;
   PRAGMA EXCEPTION_INIT(database_already_registered, -20002);
 
   incarnation_already_registered EXCEPTION;
   PRAGMA EXCEPTION_INIT(incarnation_already_registered, -20009);
 
   resync_not_needed EXCEPTION;
   PRAGMA EXCEPTION_INIT(resync_not_needed, -20034);
 
   local_wmrec  RC_WATERMARKS%ROWTYPE;
   wmrec  RC_WATERMARKS%ROWTYPE;
   CURSOR rc_watermarks_c IS
   SELECT DB_KEY,
          DB_UNIQUE_NAME,
          RS_VERSION_STAMP,
          HIGH_BP_RECID,
          HIGH_DO_KEY,
          CF_VERSION_STAMP,
          HIGH_DF_RECID,
          HIGH_TS_RECID,
          HIGH_TF_RECID,
          HIGH_OFFR_RECID
   FROM "_RS_RC_WATERMARKS_"
   WHERE RS_RCFILE_NAME = input_xml_filename;
 
   local_siterec RC_SITE%ROWTYPE;
   siterec RC_SITE%ROWTYPE;
   CURSOR rc_site_c IS
   SELECT  SITE_KEY,
           DB_KEY,
           DATABASE_ROLE,
           CF_CREATE_TIME,
           DB_UNIQUE_NAME,
           HIGH_CONF_RECID,
           FORCE_RESYNC2CF,
           HIGH_ROUT_STAMP,
           INST_STARTUP_STAMP,
           LAST_KCCDIVTS,
           HIGH_IC_RECID,
           DBINC_KEY,
           CKP_SCN,
           FULL_CKP_CF_SEQ,
           JOB_CKP_CF_SEQ,
           HIGH_TS_RECID,
           HIGH_DF_RECID,
           HIGH_RT_RECID,
           HIGH_ORL_RECID,
           HIGH_OFFR_RECID,
           HIGH_RLH_RECID,
           HIGH_AL_RECID,
           HIGH_BS_RECID,
           HIGH_BP_RECID,
           HIGH_BDF_RECID,
           HIGH_CDF_RECID,
           HIGH_BRL_RECID,
           HIGH_BCB_RECID,
           HIGH_CCB_RECID,
           HIGH_DO_RECID,
           HIGH_PC_RECID,
           HIGH_BSF_RECID,
           HIGH_RSR_RECID,
           HIGH_TF_RECID,
           HIGH_GRSP_RECID,
           HIGH_NRSP_RECID,
           HIGH_BCR_RECID,
           LOW_BCR_RECID,
           BCR_IN_USE,
           HIGH_PDB_RECID,
           HIGH_PIC_RECID
   FROM "_RS_RC_SITE_"
   WHERE RS_RCFILE_NAME = input_xml_filename;
 
   upstream_dbrec rci_ra_upstream_database%ROWTYPE;
   CURSOR rci_ra_upstream_database_c IS
   SELECT  DBID,
           NAME
   FROM "_RS_RCI_RA_UPSTREAM_DATABASE_"
   WHERE RS_RCFILE_NAME = input_xml_filename;
 
   icrec    RC_DATABASE_INCARNATION%ROWTYPE;
   curr_inc RC_DATABASE_INCARNATION%ROWTYPE;
   CURSOR rc_database_incarnation_c(curr_inc_val IN VARCHAR2 DEFAULT NULL) IS
   SELECT DB_KEY, 
          DBID, 
          DBINC_KEY,  
          NAME, 
          RESETLOGS_CHANGE#, 
          RESETLOGS_TIME,
          CURRENT_INCARNATION,
          PARENT_DBINC_KEY, 
          PRIOR_RESETLOGS_CHANGE#,
          PRIOR_RESETLOGS_TIME,
          STATUS,
          REG_DB_UNIQUE_NAME,
          CON_ID,
          GUID
   FROM "_RS_RC_DATABASE_INCARNATION_"
   WHERE RS_RCFILE_NAME = input_xml_filename
     AND (curr_inc_val IS NULL OR
          CURRENT_INCARNATION = curr_inc_val)
   ORDER BY RESETLOGS_CHANGE#;
   
   tbsrec RC_TABLESPACE%ROWTYPE;
   CURSOR rc_tablespace_c IS
   SELECT DB_KEY, 
          DBINC_KEY,
          DB_NAME,
          CON_ID,
          PDB_NAME,
          PDB_KEY,
          PDBINC_KEY,
          TS#, 
          NAME,
          CREATION_CHANGE#,
          CREATION_TIME,
          DROP_CHANGE#,
          DROP_TIME,
          INCLUDED_IN_DATABASE_BACKUP,
          BIGFILE,
          TEMPORARY,
          ENCRYPT_IN_BACKUP,
          PLUGIN_CHANGE#
   FROM "_RS_RC_TABLESPACE_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY CON_ID, TS#;
 
   dfrec RCI_DATAFILE%ROWTYPE;
   CURSOR rci_datafile_c IS
   SELECT  DB_KEY,
           DBINC_KEY,
           DB_NAME,
           CON_ID,
           PDB_NAME,
           PDB_KEY,
           PDB_CLOSED,
           PDBINC_KEY,
           TS#,
           TABLESPACE_NAME,
           FILE#,
           CREATION_CHANGE#,
           CREATION_TIME,
           DROP_CHANGE#,
           DROP_TIME,
           BYTES,
           BLOCKS,
           BLOCK_SIZE,
           NAME,
           STOP_CHANGE#,
           STOP_TIME,
           READ_ONLY,
           RFILE#,
           INCLUDED_IN_DATABASE_BACKUP,
           AUX_NAME,
           ENCRYPT_IN_BACKUP,
           SITE_KEY,
           DB_UNIQUE_NAME,
           FOREIGN_DBID,
           FOREIGN_CREATION_CHANGE#,
           FOREIGN_CREATION_TIME,
           PLUGGED_READONLY,
           PLUGIN_CHANGE#,
           PLUGIN_RESETLOGS_CHANGE#,
           PLUGIN_RESETLOGS_TIME,
           CREATION_THREAD,
           CREATION_SIZE,
           PDB_FOREIGN_DBID,
           PDB_NOBACKUP
   FROM "_RS_RCI_DATAFILE_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY FILE#;
 
   tfrec RCI_TEMPFILE%ROWTYPE;
   CURSOR rci_tempfile_c IS 
   SELECT  DB_KEY,
           DBINC_KEY,
           DB_NAME,
           CON_ID,
           PDB_NAME,
           PDB_KEY,
           TS#,
           TABLESPACE_NAME,
           FILE#,
           CREATION_CHANGE#,
           CREATION_TIME,
           DROP_CHANGE#,
           DROP_TIME,
           BYTES,
           BLOCKS,
           BLOCK_SIZE,
           NAME,
           RFILE#,
           AUTOEXTEND,
           MAXSIZE,
           NEXTSIZE,
           BIGFILE,
           SITE_KEY,
           DB_UNIQUE_NAME,
           TABLESPACE_CREATION_CHANGE#,
           TABLESPACE_CREATION_TIME,
           TABLESPACE_DROP_CHANGE#,
           TABLESPACE_DROP_TIME
   FROM "_RS_RCI_TEMPFILE_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY FILE#;
 
   rtrec RC_REDO_THREAD%ROWTYPE;
   CURSOR rc_redo_thread_c IS 
   SELECT  DB_KEY,
           DBINC_KEY,
           DB_NAME,
           THREAD#,
           STATUS,
           SEQUENCE#,
           ENABLE_CHANGE#,
           ENABLE_TIME,
           DISABLE_CHANGE#,
           DISABLE_TIME
   FROM "_RS_RC_REDO_THREAD_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY THREAD#;
 
   orlrec RC_REDO_LOG%ROWTYPE;
   CURSOR rc_redo_log_c IS 
   SELECT  DB_KEY,
           DBINC_KEY,
           DB_NAME,
           THREAD#,
           GROUP#,
           NAME,
           SITE_KEY,
           BYTES,
           TYPE
   FROM "_RS_RC_REDO_LOG_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY NAME;
 
   ckprec RC_CHECKPOINT%ROWTYPE;
   CURSOR rc_checkpoint_c IS 
   SELECT  DB_KEY,
           DBINC_KEY,
           DB_NAME,
           CKP_KEY,
           CKP_SCN,
           CKP_CF_SEQ,
           CKP_TIME,
           CKP_TYPE,
           CKP_DB_STATUS,
           SITE_KEY
   FROM "_RS_RC_CHECKPOINT_"
   WHERE RS_RCFILE_NAME = input_xml_filename;
 
   offrrec RC_OFFLINE_RANGE%ROWTYPE;
   CURSOR rc_offline_range_c IS 
   SELECT  DB_KEY,
           DBINC_KEY,
           DB_NAME,
           RECID,
           STAMP,
           FILE#,
           CREATION_CHANGE#,
           OFFLINE_CHANGE#,
           ONLINE_CHANGE#,
           ONLINE_TIME,
           CF_CREATE_TIME,
           RESETLOGS_CHANGE#,
           RESETLOGS_TIME,
           OFFR_KEY
   FROM "_RS_RC_OFFLINE_RANGE_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY OFFR_KEY;
 
   pdbrec  RCI_PDBS%ROWTYPE;
   CURSOR rci_pdbs_c IS 
   SELECT PDB_KEY,
          DB_KEY,
          NAME,
          CON_ID,
          DBID,
          CREATION_CHANGE#,
          GUID,
          NOBACKUP
   FROM "_RS_RCI_PDBS_"
   WHERE RS_RCFILE_NAME = input_xml_filename;
 
   picrec1 RC_PLUGGABLE_DATABASE_INC%ROWTYPE;
   CURSOR rc_pluggable_database_inc_c IS 
   SELECT PDB_KEY,
          NAME,
          CON_ID,
          DBID,
          GUID,
          CREATE_SCN,
          PDBINC_KEY,
          DB_KEY,
          CURRENT_INCARNATION,
          INCARNATION_SCN,
          BEGIN_RESETLOGS_SCN,
          BEGIN_RESETLOGS_TIME,
          END_RESETLOGS_SCN,
          DBINC_KEY,
          DB_RESETLOGS_SCN,
          DB_RESETLOGS_TIME,
          PRIOR_PDBINC_KEY,
          PRIOR_INCARNATION_SCN,
          PRIOR_BEGIN_RESETLOGS_SCN,
          PRIOR_BEGIN_RESETLOGS_TIME,
          PRIOR_END_RESETLOGS_SCN,
          PRIOR_DBINC_KEY,
          PRIOR_DB_RESETLOGS_SCN,
          PRIOR_DB_RESETLOGS_TIME,
          STATUS
   FROM "_RS_RC_PLUGGABLE_DATABASE_INC_"
   WHERE RS_RCFILE_NAME = input_xml_filename
   ORDER BY PDBINC_KEY;
 
   offr_key              NUMBER;
   parent_dbinc_key      NUMBER;
   dbid                  NUMBER;
   foundrec              BOOLEAN;
   newincarnation        BOOLEAN := FALSE;
   loc_db_key            NUMBER;
   cdb_mode              NUMBER;
   last_full_ckp_scn     NUMBER;
   local_dbincrec        RC_DATABASE_INCARNATION%ROWTYPE;
 
BEGIN
 
   deb('readFixedSections - enter');
 
--
   dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
 
--
   dbid := to_number(substr(input_xml_filename,length(this_database)+1, 
                    instr(input_xml_filename,'.xml')-1-length(this_database)));
 
   deb('readFixedSections: dbid=' || dbid);
 
--
   OPEN rc_watermarks_c;
   LOOP
      FETCH rc_watermarks_c INTO wmrec;
      EXIT WHEN rc_watermarks_c%NOTFOUND;
   END LOOP;
   CLOSE rc_watermarks_c;
   IF debug THEN
      displayRCWatermarks('readFixedSections remote:', wmrec);
   END IF;
 
--
   OPEN rc_site_c;
   FETCH rc_site_c INTO siterec;
   CLOSE rc_site_c;
   IF debug THEN
      displayRCSite('readFixedSections:remote ', siterec);
   END IF;
 
--
   OPEN rci_ra_upstream_database_c;
   FETCH rci_ra_upstream_database_c INTO upstream_dbrec;
   CLOSE rci_ra_upstream_database_c;
   this_upstream_dbrec := upstream_dbrec;
   deb('readFixedSections: upstream dbname=' || this_upstream_dbrec.name ||
       'reconcile dbname' || siterec.db_unique_name);
 
--
   OPEN rc_database_incarnation_c(curr_inc_val => 'YES');
   FETCH rc_database_incarnation_c INTO curr_inc;
   CLOSE rc_database_incarnation_c;
   IF debug THEN
      displayRCDatabaseIncarnation('readFixedSections: remote ', curr_inc);
   END IF;
 
--
   OPEN rc_checkpoint_c;
   FETCH rc_checkpoint_c INTO ckprec;
   CLOSE rc_checkpoint_c;
 
--
   IF curr_inc.dbid IS NULL THEN
      raise_application_error(-20999, 'internal error: no rows for curr_inc');
   END IF;
 
--
   IF curr_inc.dbid <> readFixedSections.dbid THEN
      raise_application_error(-20141, 
                 'Target database id mismatch with registered database in ORS');
   END IF;
 
--
   BEGIN
      registerDatabase(db_id => curr_inc.dbid,
                       db_name => curr_inc.name,
                       reset_scn => curr_inc.resetlogs_change#,
                       reset_time => curr_inc.resetlogs_time,
                       db_unique_name => curr_inc.reg_db_unique_name,
                       con_id => curr_inc.con_id,
                       guid => curr_inc.guid);
     deb('readFixedSections: registered dbname=' || siterec.db_unique_name);
   EXCEPTION
      WHEN database_already_registered THEN
         deb('database already registered');
         NULL;
     WHEN no_data_found THEN
         deb('database has yet to be added via add_db, dbuname=' || 
             siterec.db_unique_name);
         raise_application_error(-20140, 'Database not yet registered in ORS');
   END;
 
--
--
--
   BEGIN
      SELECT * INTO local_dbincrec
         FROM rc_database_incarnation
         WHERE dbid = dbid 
           AND resetlogs_change# = curr_inc.resetlogs_change#
           AND resetlogs_time = curr_inc.resetlogs_time;
      IF local_dbincrec.status = 'CURRENT' THEN
--
--
         SELECT max(ckp_scn) INTO last_full_ckp_scn
            FROM ckp
            WHERE ckp_type = 'FULL'
              AND dbinc_key = local_dbincrec.dbinc_key;
         IF last_full_ckp_scn > ckprec.ckp_scn THEN
            deb('readFixedSections (wait):Downstream ahead of upstream');
            RETURN;
         END IF;
      ELSE
--
--
--
--
         deb('readFixedSections: Upstream is in a different incarnation');
         IF debug THEN
            displayRCDatabaseIncarnation('readFixedSections: local ',
                                          local_dbincrec);
         END IF;
         IF local_dbincrec.status = 'ORPHAN' THEN
            deb('readFixedSections: local incarnation is orphan');
         ELSE
            deb('readFixedSections (wait): Let upstream catchup');
            RETURN;
         END IF;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         deb('readFixedSections: New incarnation in upstream');
         IF debug THEN
            displayRCDatabaseIncarnation('readFixedSections: remote ',
                                          curr_inc);
         END IF;
   END;
 
--
   BEGIN
      resetDatabase(db_id => curr_inc.dbid,
                    db_name => curr_inc.name,
                    reset_scn => curr_inc.resetlogs_change#,
                    reset_time => curr_inc.resetlogs_time,
                    parent_reset_scn => curr_inc.prior_resetlogs_change#,
                    parent_reset_time => curr_inc.prior_resetlogs_time);
      deb('readFixedSections: registered newinc ' || siterec.db_unique_name);
      newincarnation := TRUE;
   EXCEPTION
      WHEN incarnation_already_registered THEN
         deb('database incarnation already known');
         NULL;
   END;
 
--
--
   curr_inc.dbinc_key :=
      resetDatabase(db_id => curr_inc.dbid,
                    db_name => curr_inc.name,
                    reset_scn => curr_inc.resetlogs_change#,
                    reset_time => curr_inc.resetlogs_time,
                    parent_reset_scn => curr_inc.prior_resetlogs_change#,
                    parent_reset_time => curr_inc.prior_resetlogs_time);
 
   deb('current dbinc_key = ' || curr_inc.dbinc_key);
 
--
   IF this_upstream_dbrec.name IS NULL THEN
      raise_application_error(-20999, 'internal error: no rows for upstreamdb');
   END IF;
   IF siterec.site_key IS NULL THEN
      raise_application_error(-20999, 'internal error: no rows for siterec');
   END IF;
 
   setDatabase(db_name => curr_inc.name,
               reset_scn => curr_inc.resetlogs_change#,
               reset_time => curr_inc.resetlogs_time,
               db_id => curr_inc.dbid,
               db_unique_name => 
                        this_upstream_dbrec.name||'$'||siterec.db_unique_name,
               dummy_instance => FALSE,
               cf_type => CF_STANDBY,
               site_aware => TRUE,
               ors_instance => TRUE);
 
--
   IF newincarnation THEN
      deb('Clearing watermarks at catalog');
      UPDATE watermarks SET
         high_df_recid = 0,
         high_ts_recid = 0,
         high_tf_recid = 0,
         high_offr_recid = 0,
         cf_version_stamp = null
      WHERE db_key = this_db_key;
      UPDATE node SET
         high_df_recid = 0,
         high_ts_recid = 0,
         high_tf_recid = 0,
         high_offr_recid = 0
      WHERE db_key = this_db_key
        AND site_key = this_site_key;
      commitChanges('readFixedSections-1');
   END IF;
 
   select * into local_siterec from rci_site 
      where db_key = this_db_key
        and site_key = this_site_key;
   displayRCSite('readFixedSections:local ', local_siterec);
 
--
--
--
   BEGIN
      SELECT db_key, db_unique_name, rs_version_stamp, high_bp_recid, 
             high_do_key, cf_version_stamp, high_df_recid, high_ts_recid, 
             high_tf_recid, high_offr_recid 
         INTO local_wmrec 
         FROM watermarks 
         WHERE db_key = this_db_key;
      IF debug THEN
         displayRCWatermarks('readFixedSections:local ', local_wmrec);
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         deb('readFixedSections:database with db_key=' || this_db_key ||
                                 ' no watermarks set yet');
   END;
 
--
--
--
   IF local_siterec.last_kccdivts = 0 THEN
      local_siterec.last_kccdivts := date2stamp(local_wmrec.cf_version_stamp);
      update node set last_kccdivts = local_siterec.last_kccdivts,
                      cf_create_time = local_wmrec.cf_version_stamp,
                      high_ts_recid = local_wmrec.high_ts_recid,
                      high_df_recid = local_wmrec.high_df_recid,
                      high_tf_recid = local_wmrec.high_tf_recid,
                      high_offr_recid = local_wmrec.high_offr_recid,
                      high_bp_recid = local_wmrec.high_bp_recid,
                      high_do_recid = local_wmrec.high_do_key
         where db_key = this_db_key
           and site_key = this_site_key;
      commitChanges('readFixedSections-2');
 
      select * into local_siterec from rci_site 
         where db_key = this_db_key
           and site_key = this_site_key;
      displayRCSite('readFixedSections:local,re-fetch ', local_siterec);
   END IF;
 
   deb('CRossed  from watermarks');
 
--
--
--
   IF wmrec.rs_version_stamp is not null AND
      local_wmrec.rs_version_stamp <> wmrec.rs_version_stamp THEN
      raise_application_error(-20142, 
         'The version stamp of backup XML set is different');
   END IF;
 
--
--
   lockForCkpt;
   ckp_type := ckptNeeded(ckp_scn => siterec.ckp_scn,
                          ckp_cf_seq => siterec.full_ckp_cf_seq,
                          cf_version => stamp2date(siterec.last_kccdivts),
                          cf_type => CF_CURRENT,
                          high_df_recid => siterec.high_df_recid,
                          high_orl_recid => siterec.high_orl_recid,
                          high_cdf_recid => NULL,
                          high_al_recid => NULL,
                          high_bp_recid => NULL,
                          high_do_recid => NULL,
                          high_offr_recid => siterec.high_offr_recid,
                          high_pc_recid => NULL,
                          high_conf_recid => siterec.high_conf_recid,
                          rltime => curr_inc.resetlogs_time,
                          high_ts_recid => siterec.high_ts_recid,
                          high_bs_recid => NULL,
                          lopen_reset_scn => NULL,
                          lopen_reset_time => NULL,
                          high_ic_recid => siterec.high_ic_recid,
                          high_tf_recid => siterec.high_tf_recid,
                          high_rt_recid => siterec.high_rt_recid,
                          high_grsp_recid => NULL,
                          high_nrsp_recid => NULL,
                          high_bcr_recid => NULL,
                          high_pdb_recid => siterec.high_pdb_recid,
                          high_pic_recid => siterec.high_pic_recid);
 
   IF ckp_type <> RESYNC_FULL AND ckp_type <> RESYNC_PARTIAL THEN
      deb('readFixedSections (abort) - ckptNeeded returned ' || ckp_type);
      cancelCkpt;
      return;
   END IF;
 
   BEGIN
      beginCkpt(ckp_scn => siterec.ckp_scn,
                ckp_cf_seq => siterec.full_ckp_cf_seq,
                cf_version => stamp2date(siterec.last_kccdivts),
                ckp_time => ckprec.ckp_time,
                ckp_type => 'FULL',
                ckp_db_status => ckprec.ckp_db_status,
                high_df_recid => siterec.high_df_recid,
                cf_type => 'CURRENT');
   EXCEPTION
      WHEN resync_not_needed THEN
         deb('readFixedSections (abort) - resync not needed ' || ckp_type);
         return;
   END;
 
   deb('readFixedSections: have records to read ' || siterec.db_unique_name);
 
--
   foundrec := FALSE;
   IF beginPluggableDBResync(siterec.high_pdb_recid) then
      OPEN rci_pdbs_c;
      LOOP
         FETCH rci_pdbs_c INTO pdbrec;
         EXIT WHEN rci_pdbs_c%NOTFOUND;
 
         deb('Calling checkPluggableDB name=' ||
              pdbrec.name || ' dbid=' || pdbrec.dbid);
         checkPluggableDB(pdbrec.name, pdbrec.con_id, pdbrec.dbid,
                          pdbrec.creation_change#, pdbrec.guid,
                          pdbrec.nobackup);
         foundrec := TRUE;
      END LOOP;
      CLOSE rci_pdbs_c;
      endPluggableDBResync;
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20150, 
                'no records found to resync for plugged databases');
      END IF;
   END IF;
 
--
   foundrec := FALSE;
   newincarnation := FALSE;
   IF siterec.high_ic_recid > beginIncarnationResync(return_recid => TRUE) THEN
      OPEN rc_database_incarnation_c;
      LOOP
         FETCH rc_database_incarnation_c INTO icrec;
         EXIT WHEN rc_database_incarnation_c%NOTFOUND;
 
         deb('Calling checkIncarnation from readfixedsection');
         parent_dbinc_key :=
             dbms_rcvcat.checkIncarnation(icrec.resetlogs_change#,
                                          icrec.resetlogs_time,
                                          icrec.prior_resetlogs_change#,
                                          icrec.prior_resetlogs_time,
                                          icrec.name);
         foundrec := TRUE;
         newincarnation := TRUE;
      END LOOP;
      CLOSE rc_database_incarnation_c;
      endIncarnationResync(siterec.last_kccdivts, siterec.high_ic_recid);
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20143, 
                'no records found to resync for incarnation view');
      END IF;
   END IF;
 
--
--
   SELECT count(*) INTO cdb_mode FROM pdb 
   WHERE con_id >= 1 
     AND db_key = this_db_key;
  
--
   foundrec := FALSE;
   IF ((cdb_mode > 0) AND 
       (siterec.high_pic_recid > beginPluggableDbincResync OR
        newincarnation)) THEN
      OPEN rc_pluggable_database_inc_c;
      LOOP
       FETCH rc_pluggable_database_inc_c INTO picrec1;
         EXIT WHEN rc_pluggable_database_inc_c%NOTFOUND;
  
         deb('Calling checkPluggableDbinc for record' ||
             ' pdbname= ' || picrec1.name ||
             ' dbid=' || picrec1.dbid ||
             ' curr=' || picrec1.current_incarnation ||
             ' inc_scn=' || picrec1.incarnation_scn ||
             ' end_reset_scn=' || picrec1.end_resetlogs_scn ||
             ' pr_inc_scn=' || nvl(picrec1.prior_incarnation_scn, '') ||
             ' pr_reset_scn=' || nvl(picrec1.prior_end_resetlogs_scn, ''));
         checkPluggableDbinc(
            NULL, picrec1.guid,
            picrec1.current_incarnation, picrec1.incarnation_scn,
            picrec1.begin_resetlogs_scn, picrec1.begin_resetlogs_time,
            picrec1.end_resetlogs_scn, picrec1.db_resetlogs_scn,
            picrec1.db_resetlogs_time, picrec1.prior_incarnation_scn,
            picrec1.prior_end_resetlogs_scn, picrec1.prior_db_resetlogs_scn,
            picrec1.prior_db_resetlogs_time, FALSE);
         foundrec := TRUE;
      END LOOP;
      CLOSE rc_pluggable_database_inc_c;
      endPluggableDbincResync(siterec.high_pic_recid);
  
      IF NOT foundrec THEN
--
--
         raise_application_error(-20151, 
                'no records found to resync for pluggable db incarnation view');
      END IF;
   END IF;
 
--
   foundrec := FALSE;
   IF beginTableSpaceResync(siterec.high_ts_recid, FALSE) THEN
      OPEN rc_tablespace_c;
      LOOP
         FETCH rc_tablespace_c INTO tbsrec;
         EXIT WHEN rc_tablespace_c%NOTFOUND;
 
--
--
         deb('Calling checkTableSpace');
         checkTableSpace(tbsrec.name, tbsrec.ts#, tbsrec.creation_change#,
                         tbsrec.creation_time, 0 /* rbs_count not populated */,
                         tbsrec.included_in_database_backup, tbsrec.bigfile,
                         tbsrec.temporary, tbsrec.encrypt_in_backup,
                         tbsrec.plugin_change#, tbsrec.con_id, FALSE);
         foundrec := TRUE;
      END LOOP;
      CLOSE rc_tablespace_c;
      endTableSpaceResync;
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20144, 
                'no records found to resync for tablespace view');
      END IF;
   END IF;
 
--
   foundrec := FALSE;
   IF beginDataFileResync(siterec.high_df_recid) THEN
      OPEN rci_datafile_c;
      LOOP
         FETCH rci_datafile_c INTO dfrec;
         EXIT WHEN rci_datafile_c%NOTFOUND;
 
         checkDataFile(
            file#               => dfRec.file#,
            fname               => dfRec.name,
            create_scn          => dfRec.creation_change#,
            create_time         => dfRec.creation_time,
            blocks              => dfRec.blocks,
            block_size          => dfRec.block_size,
            ts#                 => dfRec.ts#,
            stop_scn            => dfRec.stop_change#,
            read_only           => dfRec.read_only,
            stop_time           => dfRec.stop_time,
            rfile#              => dfRec.rfile#,
            aux_fname           => dfRec.aux_name,
            foreign_dbid        => dfRec.foreign_dbid,
            foreign_create_scn  => dfRec.foreign_creation_change#,
            foreign_create_time => dfRec.foreign_creation_time,
            plugged_readonly    => dfRec.plugged_readonly,
            plugin_scn          => dfRec.plugin_change#,
            plugin_reset_scn    => dfRec.plugin_resetlogs_change#,
            plugin_reset_time   => dfRec.plugin_resetlogs_time,
            create_thread       => dfRec.creation_thread,
            create_size         => dfRec.creation_size,
            con_id              => dfRec.con_id,
            pdb_closed          => dfRec.pdb_closed,
            pdb_dict_check      => FALSE,
            pdb_foreign_dbid    => dfRec.pdb_foreign_dbid);
 
         foundrec := TRUE;
      END LOOP;
      CLOSE rci_datafile_c;
      endDataFileResync;
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20145, 
                'no records found to resync for datafile view');
      END IF;
   END IF;
 
--
--
   foundrec := FALSE;
   IF beginTempFileResync(siterec.high_tf_recid) THEN
      OPEN rci_tempfile_c;
      LOOP
         FETCH rci_tempfile_c INTO tfrec;
         EXIT WHEN rci_tempfile_c%NOTFOUND;
 
         checkTempFile(
            file#          => tfrec.file#,
            fname          => tfrec.name,
            create_scn     => tfrec.creation_change#,
            create_time    => tfrec.creation_time,
            blocks         => tfrec.blocks,
            block_size     => tfrec.block_size,
            ts#            => tfrec.ts#,
            rfile#         => tfrec.rfile#,
            autoextend     => tfrec.autoextend,
            max_size       => tfrec.maxsize,
            next_size      => tfrec.nextsize,
            con_id         => tfrec.con_id,
            pdb_dict_check => FALSE);
         foundrec := TRUE;
 
      END LOOP;
      CLOSE rci_tempfile_c;
      endTempFileResync;
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20145, 
                'no records found to resync for tempfile view');
      END IF;
   END IF;
 
--
   foundrec := FALSE;
   IF beginThreadResync(siterec.high_rt_recid) THEN
      OPEN rc_redo_thread_c;
      LOOP
         FETCH rc_redo_thread_c INTO rtrec;
         EXIT WHEN rc_redo_thread_c%NOTFOUND;
 
         dbms_rcvcat.checkThread
              (rtrec.thread#, rtrec.sequence#, rtrec.enable_change#,
              rtrec.enable_time, rtrec.disable_change#, rtrec.disable_time,
              rtrec.status);
         foundrec := TRUE;
      END LOOP;
      CLOSE rc_redo_thread_c;
      endThreadResync;
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20146, 
                'no records found to resync for thread view');
      END IF;
   END IF;
 
--
   foundrec := FALSE;
   IF beginOnlineRedoLogResync(siterec.high_orl_recid) THEN
      OPEN rc_redo_log_c;
      LOOP
         FETCH rc_redo_log_c INTO orlrec;
         EXIT WHEN rc_redo_log_c%NOTFOUND;
 
         checkOnlineRedoLog
              (orlrec.thread#, orlrec.group#, orlrec.name,
               orlrec.bytes, orlrec.type);
         foundrec := TRUE;
      END LOOP;
      CLOSE rc_redo_log_c;
      endOnlineRedoLogResync;
 
      IF NOT foundrec THEN
--
--
         raise_application_error(-20147, 
                'no records found to resync for online redo log view');
      END IF;
   END IF;
 
   offr_key := beginOfflineRangeResync;
   IF ((wmrec.high_offr_recid IS NOT NULL AND
        wmrec.high_offr_recid > 0 AND
        local_wmrec.high_offr_recid = wmrec.high_offr_recid) OR
       (siterec.high_offr_recid = 0)) THEN
      foundrec := TRUE;
   ELSE
      foundrec := FALSE;
   END IF;
 
   OPEN rc_offline_range_c;
   LOOP
      FETCH rc_offline_range_c INTO offrrec;
      EXIT WHEN rc_offline_range_c%NOTFOUND;
 
--
--
--
      checkOfflineRange(
              offrrec.offr_key, offrrec.stamp, offrrec.file#,
              offrrec.creation_change#,
              offrrec.offline_change#, offrrec.online_change#,
              offrrec.online_time, offrrec.cf_create_time, 
              offrrec.resetlogs_change#, offrrec.resetlogs_time);
      foundrec := TRUE;
   END LOOP;
   CLOSE rc_offline_range_c;
   endOfflineRangeResync;
 
   IF NOT foundrec THEN
--
--
      deb('wmrec.high_offr_recid=' || wmrec.high_offr_recid);
      deb('local_wmrec.high_offr_recid=' || local_wmrec.high_offr_recid);
      raise_application_error(-20148, 
             'no records found to resync for offline range view');
   END IF;
   
 
--
 
   sanityCheck;
   endCkpt;
 
   deb('readFixedSections: (suc) ' || siterec.db_unique_name);
 
EXCEPTION
   WHEN OTHERS THEN
      deb('readFixedSections: (fail) ' || siterec.db_unique_name);
      cancelCkpt;
      raise;
END readFixedSections;
 
--
--
PROCEDURE writeBackupSections(input_xml_filename  IN VARCHAR2,
                              bktname IN VARCHAR2 DEFAULT NULL) IS
   v_ctx     DBMS_XMLGen.ctxHandle;
   v_xml     CLOB;
   v_xml_tmp CLOB;
   type record_sql_type is table of varchar2(2048) 
             index by binary_integer;
   type record_tbl_type is table of varchar2(30) 
             index by binary_integer;
   record_sql  record_sql_type;
   record_tbl  record_tbl_type;
   my_dbid      number;
 
   write_xml_filename rcfile.name%TYPE;
   local_wmrec        rc_watermarks%ROWTYPE;
   l_prefix           rcfile.name%TYPE;
   l_job_ckp_cf_seq   number;
 
BEGIN
 
   deb('writeBackupSections:enter, expects caller to commit');
 
--
   my_dbid := to_number(substr(input_xml_filename,
                               length(this_forwatermarks)+1,
                               instr(input_xml_filename,'.xml')-1
                                     -length(this_forwatermarks)));
   deb('writeBackupSections:my_dbid=' || my_dbid);
 
   BEGIN
      SELECT DB_KEY INTO this_db_key FROM DB
         WHERE db_id = my_dbid;
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         deb('writeBackupSections:database with dbid=' || my_dbid ||
                                 ' not found in recovery catalog');
         RETURN;
   END;
 
   deb('writeBackupSections:this_db_key=' || this_db_key);
   BEGIN
      select * into local_wmrec from rc_watermarks where db_key = this_db_key;
      displayRCWatermarks('writeBackupSections local:', local_wmrec);
   EXCEPTION
      WHEN NO_DATA_FOUND THEN
         deb('writeBackupSections:database with dbid=' || my_dbid ||
                                 ' no setDatabase called yet');
         RETURN;
   END;
 
--
   l_job_ckp_cf_seq := local_wmrec.high_bp_recid + local_wmrec.high_do_key;
   IF this_rsiterec.cf_create_time = local_wmrec.rs_version_stamp AND
      this_rsiterec.job_ckp_cf_seq = l_job_ckp_cf_seq THEN
      deb('writeBackupSections:No new backup piece or deleted object added');
      RETURN;
   END IF;
 
   record_tbl(0)   := 'RC_DATABASE';
   record_sql(0)   := 'SELECT * FROM RC_DATABASE ' || 
                         'WHERE DB_KEY = ' || this_db_key;
 
   record_tbl(1)   := 'RCI_BACKUP_SET';
   record_sql(1)   := 'SELECT * FROM RCI_BACKUP_SET ' || 
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND BS_KEY IN ' ||
                         '    (SELECT DISTINCT BS_KEY FROM BP ' ||
                         '       WHERE BP_RECID > '||
                         NVL(this_rsiterec.high_bp_recid, 0) ||
                         '    )';
 
   record_tbl(2)   := 'RCI_BACKUP_PIECE';
   record_sql(2)   := 'SELECT * FROM RCI_BACKUP_PIECE ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND RECID > ' || 
                         NVL(this_rsiterec.high_bp_recid,0);
 
   record_tbl(3)   := 'RCI_BACKUP_DATAFILE';
   record_sql(3)   := 'SELECT * FROM RCI_BACKUP_DATAFILE ' ||
                         'WHERE DB_KEY   = ' || this_db_key ||
                         '  AND BS_KEY IN ' ||
                         '    (SELECT DISTINCT BS_KEY FROM BP ' ||
                         '       WHERE BP_RECID > ' || 
                         NVL(this_rsiterec.high_bp_recid, 0) ||
                         '    )';
 
   record_tbl(4)   := 'RCI_BACKUP_CONTROLFILE';
   record_sql(4)   := 'SELECT * FROM RCI_BACKUP_CONTROLFILE ' ||
                         'WHERE DB_KEY   = ' || this_db_key ||
                         '  AND BS_KEY IN ' ||
                         '    (SELECT DISTINCT BS_KEY FROM BP ' ||
                         '       WHERE BP_RECID > ' || 
                         NVL(this_rsiterec.high_bp_recid, 0) ||
                         '    )';
 
   record_tbl(5)   := 'RC_BACKUP_REDOLOG';
   record_sql(5)   := 'SELECT * FROM RC_BACKUP_REDOLOG ' ||
                         'WHERE DB_KEY   = ' || this_db_key ||
                         '  AND BS_KEY IN ' ||
                         '    (SELECT DISTINCT BS_KEY FROM BP ' ||
                         '       WHERE BP_RECID > ' || 
                         NVL(this_rsiterec.high_bp_recid, 0) ||
                         '    )';
 
   record_tbl(6)   := 'RCI_BACKUP_SPFILE';
   record_sql(6)   := 'SELECT * FROM RCI_BACKUP_SPFILE ' ||
                         'WHERE DB_KEY   = ' || this_db_key ||
                         '  AND BS_KEY IN ' ||
                         '    (SELECT DISTINCT BS_KEY FROM BP ' ||
                         '       WHERE BP_RECID > ' || 
                         NVL(this_rsiterec.high_bp_recid, 0) ||
                         '    )';
 
   record_tbl(7)   := 'RC_RCVER';
   record_sql(7)   := 'SELECT * FROM RC_RCVER ';
 
 
   record_tbl(8)   := 'RC_WATERMARKS';
   record_sql(8)   := 'SELECT * FROM RC_WATERMARKS ' || 
                         'WHERE DB_KEY = ' || this_db_key;
 
   record_tbl(9)   := 'RC_DELETED_OBJECT';
   record_sql(9)   := 'SELECT * FROM RC_DELETED_OBJECT ' ||
                         'WHERE DB_KEY = ' || this_db_key ||
                         '  AND DO_KEY > ' || 
                         NVL(this_rsiterec.high_do_recid, 0);
 
--
   v_xml := this_xml_signature_beg;
   FOR idx in 0..9 LOOP
      deb('writing XML file for ' || idx);
      deb('executing query:'|| record_sql(idx));
 
      v_ctx := DBMS_XMLGen.newContext(record_sql(idx));
 
--
      DBMS_XMLGen.setRowsetTag(v_ctx, 'TABLE_' || record_tbl(idx));
      v_xml_tmp := DBMS_XMLGen.GetXML(v_ctx);
      DBMS_XMLGen.closeContext(v_ctx);
 
      deb('XML len for '||idx||'=' || DBMS_LOB.GETLENGTH(v_xml_tmp));
      IF v_xml_tmp IS NOT NULL THEN
         DBMS_LOB.COPY(v_xml, v_xml_tmp, DBMS_LOB.LOBMAXSIZE, 
                       DBMS_LOB.GETLENGTH(v_xml),
                       DBMS_LOB.INSTR(v_xml_tmp, 
                                  '<TABLE_' || record_tbl(idx) ||'>'));
      END IF;
   END LOOP;
   v_xml := v_xml || this_xml_signature_end;
 
--
   l_prefix := 
      this_backupsets || my_dbid || '_' || 
      date2stamp(local_wmrec.rs_version_stamp) || '_';
   write_xml_filename :=  l_prefix || l_job_ckp_cf_seq || '.xml';
 
   IF this_amazon_server THEN
      put_object(null /*bktname */, write_xml_filename, 'text/xml', v_xml);
   ELSE
      DELETE rcfile WHERE name LIKE l_prefix || '%';
      IF SQL%ROWCOUNT > 0 THEN
         deb('writeBackupSections:deleted old backupset rows:' ||SQL%ROWCOUNT);
      END IF;
      INSERT INTO rcfile(rcfile_key, creation_time, name, xmldoc)
         VALUES (rman_seq.nextval, sys_extract_utc(systimestamp), 
                 write_xml_filename, XMLType.createXML(v_xml));
   END IF;
 
   deb('writeBackupSections:(suc) file:' || write_xml_filename);
END writeBackupSections;
 
PROCEDURE readBackupSections(bktname IN VARCHAR2 DEFAULT NULL) IS
 
 
   bs_xml_filename RCFILE.NAME%TYPE;
 
   bsrec  RCI_BACKUP_SET%ROWTYPE;
   CURSOR rci_backup_set_c IS
   SELECT DB_KEY,
          DB_ID, 
          BS_KEY,  
          RECID, 
          STAMP, 
          SET_STAMP,
          SET_COUNT,
          BACKUP_TYPE,
          INCREMENTAL_LEVEL,
          PIECES,
          START_TIME,
          COMPLETION_TIME,
          ELAPSED_SECONDS,
          STATUS,
          CONTROLFILE_INCLUDED,
          INPUT_FILE_SCAN_ONLY,
          KEEP,
          KEEP_UNTIL,
          decode (KEEP_OPTIONS, 'LOGS'         , 256
                              , 'NOLOGS'       , 512
                              , 'BACKUP_LOGS'  , 1024
                                               , 0) KEEP_OPTIONS,
          BLOCK_SIZE,
          SITE_KEY,
          MULTI_SECTION,
          GUID,
          PDB_KEY
   FROM "_RS_RCI_BACKUP_SET_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   ORDER BY RECID;
 
   bprec  RCI_BACKUP_PIECE%ROWTYPE;
   CURSOR rci_backup_piece_c IS
   SELECT DB_KEY,
          DB_ID,
          BP_KEY,
          RECID,
          STAMP,
          BS_KEY,
          SET_STAMP,
          SET_COUNT,
          BACKUP_TYPE,
          INCREMENTAL_LEVEL,
          PIECE#,
          COPY#,
          DEVICE_TYPE,
          HANDLE,
          COMMENTS,
          MEDIA,
          MEDIA_POOL,
          CONCUR,
          TAG,
          START_TIME,
          COMPLETION_TIME,
          ELAPSED_SECONDS,
          STATUS,
          BYTES,
          IS_RECOVERY_DEST_FILE,
          RSR_KEY,
          COMPRESSED,
          SITE_KEY,
          ENCRYPTED,
          BACKED_BY_OSB,
          SUBSTR(BA_ACCESS,1,1) BA_ACCESS,
          VB_KEY,
          VIRTUAL,
          LIB_KEY,
          GUID,
          PDB_KEY
   FROM "_RS_RCI_BACKUP_PIECE_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   ORDER BY RECID;
 
   bdfrec  RCI_BACKUP_DATAFILE%ROWTYPE;
   CURSOR rci_backup_datafile_c IS
   SELECT DB_KEY,
          DBINC_KEY,
          DB_NAME,
          BDF_KEY,
          RECID,
          STAMP,
          BS_KEY,
          SET_STAMP,
          SET_COUNT,
          BS_RECID,
          BS_STAMP,
          BACKUP_TYPE,
          INCREMENTAL_LEVEL,
          COMPLETION_TIME,
          FILE#,
          CREATION_CHANGE#,
          CREATION_TIME,
          RESETLOGS_CHANGE#,
          RESETLOGS_TIME,
          INCREMENTAL_CHANGE#,
          CHECKPOINT_CHANGE#,
          CHECKPOINT_TIME,
          ABSOLUTE_FUZZY_CHANGE#,
          DATAFILE_BLOCKS,
          BLOCKS,
          BLOCK_SIZE,
          STATUS,
          BS_LEVEL,
          PIECES,
          BLOCKS_READ,
          MARKED_CORRUPT,
          USED_CHANGE_TRACKING,
          USED_OPTIMIZATION,
          PCT_NOTREAD,
          FOREIGN_DBID,
          PLUGGED_READONLY,
          PLUGIN_CHANGE#,
          PLUGIN_RESETLOGS_CHANGE#,
          PLUGIN_RESETLOGS_TIME,
          SECTION_SIZE,
          GUID,
          SPARSE_BACKUP,
          PDB_KEY
   FROM "_RS_RCI_BACKUP_DATAFILE_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   UNION
      SELECT DB_KEY,
          DBINC_KEY,
          DB_NAME,
          BCF_KEY,
          RECID,
          STAMP,
          BS_KEY,
          SET_STAMP,
          SET_COUNT,
          BS_RECID,      
          BS_STAMP,      
          decode(BS_LEVEL, 0, 'D', null, 'D', 'I') BACKUP_TYPE,
          BS_LEVEL INCREMENTAL_LEVEL,
          COMPLETION_TIME,
          0 FILE#,
          0 CREATION_CHANGE#,
          CREATION_TIME,
          RESETLOGS_CHANGE#,
          RESETLOGS_TIME,
          0 INCREMENTAL_CHANGE#,
          CHECKPOINT_CHANGE#,
          CHECKPOINT_TIME,
          0 ABSOLUTE_FUZZY_CHANGE#,
          0 DATAFILE_BLOCKS,
          BLOCKS,        
          BLOCK_SIZE,
          STATUS,        
          BS_LEVEL,
          NULL PIECES,
          NULL BLOCKS_READ,
--
--
--
          to_number(to_char(to_date(autobackup_date),'YYYY')) MARKED_CORRUPT,
          'NO' USED_CHANGE_TRACKING,
          'NO' USED_OPTIMIZATION,
          AUTOBACKUP_SEQUENCE PCT_NOTREAD,
          0 FOREIGN_DBID,
          CONTROLFILE_TYPE PLUGGED_READONLY, 
          OLDEST_OFFLINE_RANGE PLUGIN_CHANGE#,
          to_number(to_char(to_date(autobackup_date),'MM')||
             to_char(to_date(autobackup_date),'DD')) PLUGIN_RESETLOGS_CHANGE#,
          NULL PLUGIN_RESETLOGS_TIME,
          NULL SECTION_SIZE,
          GUID,
          'NO' sparse_backup,
          PDB_KEY
   FROM "_RS_RCI_BACKUP_CONTROLFILE_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   ORDER BY 5;
 
   bsfrec  RCI_BACKUP_SPFILE%ROWTYPE;
   CURSOR rci_backup_spfile_c IS
   SELECT DB_KEY,
          BSF_KEY,
          RECID,
          STAMP,
          BS_KEY,
          SET_STAMP,
          SET_COUNT,
          MODIFICATION_TIME,
          STATUS,
          BS_RECID,
          BS_STAMP,
          COMPLETION_TIME,
          BYTES,
          DB_UNIQUE_NAME,
          GUID
   FROM "_RS_RCI_BACKUP_SPFILE_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   ORDER BY RECID;
 
   brlrec  RC_BACKUP_REDOLOG%ROWTYPE;
   CURSOR rc_backup_redolog_c IS
   SELECT DB_KEY,
          DBINC_KEY,                                
          DB_NAME,                                  
          BRL_KEY,                                  
          RECID,                                    
          STAMP,                                    
          BS_KEY,                                   
          SET_STAMP,                                
          SET_COUNT,                                
          BACKUP_TYPE,                                       
          COMPLETION_TIME,                                   
          THREAD#,                                  
          SEQUENCE#,                                
          RESETLOGS_CHANGE#,                        
          RESETLOGS_TIME,                           
          FIRST_CHANGE#,                            
          FIRST_TIME,                               
          NEXT_CHANGE#,                             
          NEXT_TIME,                                
          BLOCKS,                                   
          BLOCK_SIZE,                               
          STATUS,                                            
          BS_RECID,
          BS_STAMP,
          PIECES,
          TERMINAL,
          PARTIAL
   FROM "_RS_RC_BACKUP_REDOLOG_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   ORDER BY RECID;
 
   dlrec  RC_DELETED_OBJECT%ROWTYPE;
   CURSOR rc_deleted_object_c IS
   SELECT DB_KEY,
          DO_KEY,
          OBJECT_TYPE,
          OBJECT_KEY,                                  
          OBJECT_STAMP,                                  
          OBJECT_DATA,                                    
          SET_STAMP,                                
          SET_COUNT,
          HANDLE,
          DEVICE_TYPE
   FROM "_RS_RC_DELETED_OBJECT_"
   WHERE RS_RCFILE_NAME = bs_xml_filename
   ORDER BY DO_KEY;
 
   bsname "_RS_RC_GET_OBJECT_LIST_"%ROWTYPE;
   CURSOR bsnames_c(prefix in varchar2) IS
   SELECT *
   FROM "_RS_RC_GET_OBJECT_LIST_"
   WHERE prefix = bsnames_c.prefix
   ORDER BY rcfile_name asc;
 
   my_dbid      NUMBER;
   ckp_type     NUMBER;
   v_xml_tmp    CLOB; -- TODO remove this after changing get_object prototype
   local_node   NODE%ROWTYPE;
   prefix_value    "_RS_RC_GET_OBJECT_LIST_".PREFIX%TYPE;
 
   resync_not_needed EXCEPTION;
   PRAGMA EXCEPTION_INIT(resync_not_needed, -20034);
 
   incarnation_not_known EXCEPTION;
   PRAGMA EXCEPTION_INIT(incarnation_not_known, -20003);
   
   max_copy_num     NUMBER;
   l_bs_key         NUMBER;
   cursor_name      INTEGER;
   dummy            INTEGER;
   
   r_bp_key       NUMBER;
   r_dbver        NUMBER;
   r_dbid         NUMBER;
   r_db_name      dbinc.db_name%TYPE;
   r_handle       bp.handle%TYPE;
   r_setstamp     NUMBER;
   r_setcount     NUMBER;
   r_pieceno      NUMBER;
   r_pieceblksize NUMBER;
   r_tag          bp.tag%TYPE;
   r_bstyp        NUMBER;
   r_cnt          NUMBER;
   l_dbkey        NUMBER;
   l_cnt          NUMBER;
   l_compressed   bp.compressed%TYPE;
   l_encrypted    bp.encrypted%TYPE;
   l_incremental  NUMBER;
   l_bstyp        NUMBER;
   l_recid        NUMBER;
   l_job_ckp_cf_seq NUMBER;
 
BEGIN
   deb('readBackupSections - enter');
 
--
   IF this_server.server_host IS NULL THEN
      deb('readBackupSections: this_server.server_host is null');
      return;
   END IF;
 
   IF this_curr_inc.dbid IS NULL OR this_wmrec.cf_version_stamp IS NULL THEN
      deb('readBackupSections: DB was registered or inc mismatch, re-read');
      writeForWatermarks(bktname, TRUE);
      rsReadWaterMarks(bktname);
   END IF;
 
--
   IF this_upstream_dbrec.name IS NULL THEN
      raise_application_error(-20999, 'internal error: no rows for upstreamdb');
   END IF;
 
   IF this_url_db_unique_name IS NULL THEN
      raise_application_error(-20999,'internal error:this_url_db_unique_name2');
   END IF;
 
   IF (this_curr_inc.dbid IS NULL) THEN
      raise_application_error(-20021, 'Database not set');
   END IF;
 
   BEGIN 
      setDatabase(db_name => this_curr_inc.name,
                  reset_scn => this_curr_inc.resetlogs_change#,
                  reset_time => this_curr_inc.resetlogs_time,
                  db_id => this_curr_inc.dbid,
                  db_unique_name => 
                     this_upstream_dbrec.name||'$'|| this_url_db_unique_name,
                  dummy_instance => FALSE,
                  cf_type => CF_STANDBY,
                  site_aware => TRUE,
                  ors_instance => TRUE);
   EXCEPTION
      WHEN incarnation_not_known THEN
--
--
--
         deb('readBackupSections: New incarnation not yet known to upstream');
         IF debug THEN
            displayRCDatabaseIncarnation('readBackupSections: remote ', 
                                          this_curr_inc);
         END IF;
         RETURN;
   END;
 
--
   dbms_session.set_nls('NLS_DATE_FORMAT','''DD-MM-YYYY HH24:MI:SS''');
 
--
--
   BEGIN
      writeForWatermarks(bktname, FALSE);
      rsReadWaterMarks(bktname);
   EXCEPTION
      WHEN OTHERS THEN
         deb('readBackupSections: error during watermarks read:' || sqlerrm);
         RAISE;
   END;
 
   IF this_wmrec.rs_version_stamp is NULL THEN
      deb('readBackupSections: no watermarks yet populated at ORS');
      RETURN;
   END IF;
 
   SELECT * into local_node FROM NODE
      WHERE db_key = this_db_key
        AND site_key = this_site_key;
 
--
   l_job_ckp_cf_seq := this_wmrec.high_bp_recid + this_wmrec.high_do_key;
   IF local_node.cf_create_time = this_wmrec.rs_version_stamp AND
      local_node.job_ckp_cf_seq = l_job_ckp_cf_seq THEN
--
      deb('readBackuupSections: no events to reconcile from ORS to catalog');
      return;
   END IF;
 
   deb('local_node.cf_create_time='||local_node.cf_create_time);
   deb('local_node.job_ckp_cf_seq='||local_node.job_ckp_cf_seq);
 
--
   lockForCkpt;
   ckp_type := ckptNeeded(ckp_scn => 0,
                          ckp_cf_seq => l_job_ckp_cf_seq,
                          cf_version => this_wmrec.rs_version_stamp,
                          cf_type => CF_STANDBY,
                          high_df_recid => 0,
                          high_orl_recid => 0,
                          high_cdf_recid => 0,
                          high_al_recid => 0,
                          high_bp_recid => this_wmrec.high_bp_recid,
                          high_do_recid => this_wmrec.high_do_key,
                          high_offr_recid => 0,
                          high_pc_recid => 0,
                          high_conf_recid => 0,
                          rltime => this_curr_inc.resetlogs_time,
                          high_ts_recid => 0,
                          high_bs_recid => 0,
                          lopen_reset_scn => NULL,
                          lopen_reset_time => NULL,
                          high_ic_recid => 0,
                          high_tf_recid => 0,
                          high_rt_recid => 0,
                          high_grsp_recid => 0,
                          high_nrsp_recid => 0,
                          high_bcr_recid => 0,
                          high_pdb_recid => 0,
                          high_pic_recid => 0);
   IF ckp_type <> RESYNC_PARTIAL THEN
      deb('readBackupSections: resync not required, ckp_type=' || ckp_type);
      cancelCkpt;
      return;
   END IF;
 
   BEGIN
      beginCkpt(ckp_scn => 0,
                ckp_cf_seq => l_job_ckp_cf_seq,
                cf_version => this_wmrec.rs_version_stamp,
                ckp_time => NULL,
                ckp_type => 'PARTIAL',
                ckp_db_status => NULL,
                high_df_recid => 0,
                cf_type => 'STANDBY');
   EXCEPTION
      WHEN resync_not_needed THEN
         deb('readBackupSections:resync not needed from beginCkpt');
         return;
   END;
 
--
--
   SELECT DBID INTO MY_DBID FROM RC_DATABASE
      WHERE DB_KEY=this_db_key;
 
--
   prefix_value := this_backupsets || my_dbid || '_' ||
                   date2stamp(this_wmrec.rs_version_stamp) || '_';
 
--
--
--
   v_xml_tmp := get_object(bktname, null, 'prefix='||prefix_value);
 
   OPEN bsnames_c(prefix_value);
   LOOP
      FETCH bsnames_c INTO bsname;
      EXIT WHEN bsnames_c%NOTFOUND;
      bs_xml_filename := bsname.rcfile_name;
 
      deb('readBackupSections: got file ' || bs_xml_filename);
 
--
      v_xml_tmp := get_object(bktname, bs_xml_filename);
 
--
      l_recid := beginBackupsetResync;
      OPEN rci_backup_set_c;
      LOOP
         FETCH rci_backup_set_c INTO bsrec;
         EXIT WHEN rci_backup_set_c%NOTFOUND;
 
         deb('Calling checkBackupset');
         dbms_rcvcat.checkBackupSet(
                 bsrec.recid, bsrec.stamp, bsrec.set_stamp, bsrec.set_count,
                 bsrec.backup_type, bsrec.incremental_level, bsrec.pieces,
                 bsrec.start_time, bsrec.completion_time,
                 bsrec.controlfile_included, bsrec.input_file_scan_only,
                 bsrec.keep_options, bsrec.keep_until, bsrec.block_size,
                 bsrec.multi_section, FALSE /* chk_last_recid */,
                 bsrec.guid);
      END LOOP;
      CLOSE rci_backup_set_c;
      endBackupsetResync;
 
--
      l_recid := beginBackupPieceResync;
      OPEN rci_backup_piece_c;
      LOOP
         FETCH rci_backup_piece_c INTO bprec;
         EXIT WHEN rci_backup_piece_c%NOTFOUND;
         
--
         SELECT bs_key INTO l_bs_key FROM bs
           WHERE bs.db_key = this_db_key
             AND   bs.set_stamp = bprec.set_stamp
             AND   bs.set_count = bprec.set_count;
         
         SELECT NVL(MAX(copy#), 0)
           INTO max_copy_num 
           FROM bp
           WHERE bs_key = l_bs_key;
         
         IF bprec.ba_access != 'U' THEN
            deb('Calling CheckBackupPiece, handle='|| bprec.handle ||',tag=' ||
                bprec.tag || ',max_copy_num=' || max_copy_num ||',l_bs_key=' ||
                l_bs_key || ',bp_recid=' || bprec.recid);
 
            dbms_rcvcat.checkBackupPiece(
                 bprec.recid, bprec.stamp, bprec.set_stamp, bprec.set_count,
                 bprec.piece#, bprec.tag, 'SBT_TAPE', bprec.handle,
                 bprec.comments, this_server.rep_server_name, bprec.concur,
                 bprec.start_time, bprec.completion_time, bprec.status,
                 max_copy_num + 1, bprec.media_pool,
                 bprec.bytes, bprec.is_recovery_dest_file,
                 bprec.rsr_key, null /* rman_status_stamp */,
                 bprec.compressed, bprec.encrypted, bprec.backed_by_osb,
                 'R', NULL /* bprec.vb_key */,
                 TRUE /* chk_last_recid */, this_lib_key, bprec.guid, NULL);
              
--
           SELECT bp_key INTO r_bp_key
             FROM bp
             WHERE handle=bprec.handle
               AND piece#=bprec.piece#;
              
--
--
           EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM sbt_catalog WHERE bp_key=:1'
                       INTO l_cnt USING r_bp_key;
 
           IF l_cnt = 0 THEN 
--
--
             cursor_name := dbms_sql.open_cursor;
                
             sys.dbms_sql.parse(cursor_name,   
                               'begin sys.kbrsi_icd.rsAddToSbtCatalog(
                                    bpkey        => :l_bpkey,
                                    dbver        => :l_dbver,
                                    dbname       => :l_dbname,
                                    dbid         => :l_dbid,
                                    handle       => :l_handle,
                                    setstamp     => :l_setstamp,
                                    setcount     => :l_setcount,
                                    pieceno      => :l_pieceno,
                                    pieceblksize => :l_pieceblksize,
                                    tag          => :l_tag,
                                    bstyp        => :l_bstyp
                                 ); end;',
                                 DBMS_SQL.NATIVE);
                
--
--
--
             IF l_dbkey IS NULL THEN
               SELECT db_key INTO l_dbkey
                 FROM db
                 WHERE db_id=bprec.db_id;
 
               EXECUTE IMMEDIATE 'SELECT MAX(cmpvsn) FROM vbdf WHERE db_key=:1'
                 INTO r_dbver USING l_dbkey;
 
               SELECT db_name INTO r_db_name FROM dbinc
                 WHERE dbinc_key = 
                       (SELECT curr_dbinc_key FROM db WHERE db_key = l_dbkey);
             END IF;
                
             IF r_dbver IS NULL THEN
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
               deb ('readBackupSections: r_dbver is NULL - set to 0.0.0.0');
               r_dbver := 0;
             END IF;
 
             SELECT block_size, incr_level
               INTO r_pieceblksize, l_incremental
               FROM bs
               WHERE bs_key=l_bs_key;
                
             r_dbid         := bprec.db_id;
             r_handle       := bprec.handle;
             r_setstamp     := bprec.set_stamp;
             r_setcount     := bprec.set_count;
             r_pieceno      := bprec.piece#;
             r_tag          := bprec.tag;
             
--
--
             r_bstyp := 4;
--
--
             IF l_incremental IS NOT NULL THEN
               r_bstyp := r_bstyp + 16;
             END IF;
                
             IF bprec.compressed = 'YES' THEN
               r_bstyp := r_bstyp + 32;
             END IF;
 
             IF bprec.encrypted = 'Y' THEN
               r_bstyp := r_bstyp + 64;
             END IF;
                
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_bpkey',   r_bp_key   );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_dbver',   r_dbver    );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_dbname',  r_db_name  );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_dbid',    r_dbid     );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_handle',  r_handle   );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_setstamp',r_setstamp );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_setcount',r_setcount );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_pieceno', r_pieceno  );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_pieceblksize',
                                                            r_pieceblksize);
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_tag',     r_tag      );
             DBMS_SQL.BIND_VARIABLE(cursor_name, ':l_bstyp',   r_bstyp    );
 
             deb('Calling rsAddToSbtCatalog');
             dummy := sys.dbms_sql.execute(cursor_name);
             sys.dbms_sql.close_cursor(cursor_name);
 
           END IF;
                
        ELSE 
          deb('Calling CheckBackupPiece ***NOT***, handle='||bprec.handle||
              ',tag=' || bprec.tag || ',max_copy_num=' || max_copy_num ||
              ',l_bs_key=' || l_bs_key || ',bp_recid=' || bprec.recid);
          deb('Ignoring BP rec with bprec.ba_access=' || bprec.ba_access);
        END IF;
      END LOOP;
      CLOSE rci_backup_piece_c;
      endBackupPieceResync;
 
--
      l_recid := beginBackupDataFileResync;
      OPEN rci_backup_datafile_c;
      LOOP
         FETCH rci_backup_datafile_c INTO bdfrec;
         EXIT WHEN rci_backup_datafile_c%NOTFOUND;
 
         deb('Calling checkBackupDatafile');
         dbms_rcvcat.checkBackupDataFile(
                 bdfrec.recid, bdfrec.stamp, bdfrec.set_stamp,
                 bdfrec.set_count, bdfrec.file#, bdfrec.creation_change#,
                 bdfrec.creation_time,
                 bdfrec.resetlogs_change#, bdfrec.resetlogs_time,
                 bdfrec.incremental_level, bdfrec.incremental_change#,
                 bdfrec.checkpoint_change#, bdfrec.checkpoint_time,
                 bdfrec.absolute_fuzzy_change#, bdfrec.datafile_blocks,
                 bdfrec.blocks, bdfrec.block_size, 
                 bdfrec.plugin_change# /*oldest_offline_range*/,
                 bdfrec.completion_time, 
                 bdfrec.plugged_readonly /*controlfile_type*/,
                 bdfrec.marked_corrupt /* cfile_abck_year */, 
                 bdfrec.plugin_resetlogs_change# /* cfile_abck_mon_day */,
                 bdfrec.pct_notread /* cfile_abck_seq */, 
                 FALSE /* chk_last_recid */, bdfrec.blocks_read,
                 bdfrec.used_change_tracking, bdfrec.used_optimization,
                 bdfrec.foreign_dbid, bdfrec.plugged_readonly,
                 bdfrec.plugin_change#, bdfrec.plugin_resetlogs_change#,
                 bdfrec.plugin_resetlogs_time,
                 bdfrec.section_size, bdfrec.guid, bdfrec.sparse_backup);
      END LOOP;
      CLOSE rci_backup_datafile_c;
      endBackupDatafileResync;
 
--
      l_recid := beginBackupRedoLogResync;
      OPEN rc_backup_redolog_c;
      LOOP
         FETCH rc_backup_redolog_c INTO brlrec;
         EXIT WHEN rc_backup_redolog_c%NOTFOUND;
  
         deb('Calling checkBackupRedoLog');
         dbms_rcvcat.checkBackupRedoLog(
                 brlrec.recid, brlrec.stamp, brlrec.set_stamp,
                 brlrec.set_count,
                 brlrec.thread#, brlrec.sequence#, brlrec.resetlogs_change#,
                 brlrec.resetlogs_time, brlrec.first_change#,
                 brlrec.first_time,
                 brlrec.next_change#, brlrec.next_time, brlrec.blocks,
                 brlrec.block_size, FALSE /* chk_last_recid */,
                 brlrec.terminal);
      END LOOP;
      CLOSE rc_backup_redolog_c;
      endBackupRedoLogResync;
 
--
      l_recid := beginBackupSpFileResync;
      OPEN rci_backup_spfile_c;
      LOOP
         FETCH rci_backup_spfile_c INTO bsfrec;
         EXIT WHEN rci_backup_spfile_c%NOTFOUND;
  
         deb('Calling checkBackupSpFile');
         dbms_rcvcat.checkBackupSpFile(
                 bsfrec.recid, bsfrec.stamp, bsfrec.set_stamp, 
                 bsfrec.set_count, bsfrec.modification_time, bsfrec.bytes, 
                 FALSE /* chk_last_recid */,
                 bsfrec.db_unique_name, bsfrec.guid);
      END LOOP;
      CLOSE rci_backup_spfile_c;
      endBackupSpFileResync;
 
--
      l_recid := beginDeletedObjectResync;
      OPEN rc_deleted_object_c;
      LOOP
         FETCH rc_deleted_object_c INTO dlrec;
         EXIT WHEN rc_deleted_object_c%NOTFOUND;
  
         deb('Calling checkDeletedObject for do_key(recid) ' ||
               dlrec.do_key                ||' object type '    ||
               dlrec.object_type           ||' with key '       ||
               dlrec.object_key            ||', stamp '       ||
               dlrec.object_stamp          ||', data '       ||
               dlrec.object_data           ||' and set_stamp '  ||
               nvl(to_char(dlrec.set_stamp), 'NULL') ||' and set_count '  ||
               nvl(to_char(dlrec.set_count), 'NULL') ||', handle ' ||
               dlrec.handle                ||', device_type ' ||
               dlrec.device_type);
 
         dbms_rcvcat.checkDeletedObject(
              dlrec.do_key /* do_recid */, 0 /* do_stamp */,
              dlrec.object_type, dlrec.object_key, dlrec.object_stamp,
              dlrec.object_data,
              null /* dlrec.object_fname */,
              null /* dlrec.object_create_scn */,
              dlrec.set_stamp, dlrec.set_count,
              dlrec.handle, dlrec.device_type);
      END LOOP;
      CLOSE rc_deleted_object_c;
      endDeletedObjectResync;
 
   END LOOP;
   CLOSE bsnames_c;
 
   sanityCheck;
 
--
   deb('readBackupSections:last xml file resynced ' || bs_xml_filename);
   delete rcfile where name like prefix_value || '%' 
                      and name <> bs_xml_filename;
   IF bs_xml_filename IS NOT NULL THEN
      endCkpt;
      deb('readBackupSections(suc)');
   ELSE
      deb('readBackupSections(no_files_found - re-try again)');
      cancelCkpt;
   END IF;
EXCEPTION
   WHEN OTHERS THEN
      deb('readBackupSections(fail):'||sqlerrm);
      cancelCkpt;
      raise;
END readBackupSections;
 
/*----------------------------------------------------------------------------
 * This is for oam front end congestion control. If the requirement for a new  
 * backup job exceeds the system bottleneck the job will have to wait.
 *--------------------------------------------------------------------------*/
 
PROCEDURE throttle_me(p_oam_job_id         IN VARCHAR2,
                      p_channels_reqd      IN NUMBER,
                      p_request_time       IN DATE,
                      p_wait               OUT BOOLEAN,
                      p_error_str          OUT VARCHAR2)
IS
   l_wait      integer;
BEGIN
  
--
  IF NOT this_is_ors THEN
    p_wait := false;
    RETURN;
  END IF;
  
  deb ('throttle_me: request for db: ' || this_db_unique_name);
  
  EXECUTE IMMEDIATE 'begin dbms_rai_throttle_alloc(
                     :p_oam_job_id, :p_db_unique_name, :p_channels_reqd, 
                     :p_request_time, :l_wait, :p_error_str); end;'
          USING IN p_oam_job_id, IN this_db_unique_name, IN p_channels_reqd, 
                IN p_request_time, OUT l_wait, OUT p_error_str;
 
  p_wait := (l_wait > 0);
 
END throttle_me;
 
FUNCTION assert_schema (
  i_schema                   IN VARCHAR2
, i_enquote                  IN BOOLEAN DEFAULT FALSE
)
RETURN VARCHAR2
IS
  l_eschema                  VARCHAR2(130);
  l_schema                   VARCHAR2(130);
BEGIN
  dbms_utility.canonicalize(
    dbms_assert.enquote_name(i_schema, FALSE)
  , l_eschema, 130
  );
  l_schema := dbms_assert.schema_name(l_eschema);
  IF (i_enquote)
  THEN
    RETURN l_eschema;
  ELSE
    RETURN l_schema;
  END IF;
END assert_schema;
 
PROCEDURE assert_schema (
  o_schema                   OUT NOCOPY VARCHAR2
, o_eschema                  OUT NOCOPY VARCHAR2
, i_schema                   IN VARCHAR2
)
IS
BEGIN
  dbms_utility.canonicalize(dbms_assert.enquote_name(i_schema), o_eschema, 130);
  o_schema := dbms_assert.schema_name(o_eschema);
END assert_schema;
 
/*--------------------------------------------------*
 * Package Instantiation:  Initialize Package State *
 *--------------------------------------------------*/
BEGIN
 
  tsRec.ts# := NULL;                    -- not in TableSpaceResync
  dfRec.file# := NULL;                  -- not in middle of dfResync
  version_list(1) := '08.00.04.00';
 
--
--
--
--
--
--
  version_list(2) := '08.00.05.00';
 
--
--
  version_list(3) := '08.01.03.00';
 
--
--
  version_list(4) := '08.01.06.00';
 
--
--
--
--
--
--
--
  version_list(5) := '08.01.07.00';
 
--
--
--
--
--
  version_list(6) := '09.00.00.00';
 
--
--
--
--
--
--
  version_list(7) := '09.02.00.00';
--
--
--
 
  version_list(8) := '10.01.00.00';
 
--
  version_list(9)  := '10.02.00.00';
  version_list(10) := '10.02.00.01';
 
--
  version_list(11) := '11.01.00.00';
  version_list(12) := '11.01.00.01';
  version_list(13) := '11.01.00.02';
  version_list(14) := '11.01.00.03';
  version_list(15) := '11.01.00.04';
  version_list(16) := '11.01.00.05';
  version_list(17) := '11.01.00.06';
  version_list(18) := '11.01.00.07';
  version_list(19) := '11.02.00.00';
  version_list(20) := '11.02.00.01';
  version_list(21) := '11.02.00.02';
 
--
  version_list(22) := '12.01.00.00';
  version_list(23) := '12.01.00.01';
  version_list(24) := '12.01.00.02';
 
--
  version_list(25) := '12.02.00.00';
  version_list(26) := '12.02.00.01';
 
  version_max_index := 26;
  
--
  initOrsAdmin;
 
  IF (orsadmin_user = user) THEN
     this_is_ors_admin := TRUE;
  END IF;
 
  IF (orsadmin_user IS NOT NULL) THEN
     this_is_ors := TRUE;
  END IF;
 
--
  FOR i IN 1..vpd_table_list.COUNT
  LOOP
    IF (vtr_policy_required(i))
    THEN
      g_vpd_required_policies := g_vpd_required_policies + 1;
    END IF;
  END LOOP;
 
  init_package_vars(NULL);
END dbms_rcvcat;
