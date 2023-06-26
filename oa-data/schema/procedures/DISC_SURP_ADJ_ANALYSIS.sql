CREATE OR REPLACE PROCEDURE OPENACCESS.DISC_SURP_ADJ_ANALYSIS(v_r_month varchar2,v_r_year varchar2) AS 
-- under development :
--to check surplus case discrepancy between HT & OA -- by rini AE/LT5
BEGIN
  DECLARE
  v_subquery_a varchar2(1000):=''; v_subquery_b varchar2(1000):='';v_query varchar2(2000):='';
 
  BEGIN
  execute immediate 'truncate table r_disc_int_adj';   
execute immediate 'truncate table r_disc_f_energy';  

v_subquery_a:='select  r.reading_dt
,r.created_dt,r.READING_MNTH,r.reading_yr,r.buyer_service_no,r.seller_service_no,nvl(c1,0),nvl(c2,0),nvl(c3,0),nvl(c4,0),nvl(c5,0),
surp_c1,surp_c2,surp_c3,surp_c4,surp_c5, 
surp_c1-nvl(c1,0) as diff_C1,surp_c2-nvl(c2,0) as diff_C2,surp_c3-nvl(c3,0) as diff_C3,surp_c4-nvl(c4,0) as diff_C4,
surp_c5-nvl(c5,0) as diff_C5,
surp_c1-nvl(c1,0)+surp_c2-nvl(c2,0)+surp_c3-nvl(c3,0)+surp_c4-nvl(c4,0)+surp_c5-nvl(c5,0) as TOT_DIFF_surp,
adj_c1-f.adjusted_c1+adj_c2-f.adjusted_c2+adj_c3-f.adjusted_c3+adj_c4-f.adjusted_c4+adj_c5-f.adjusted_c5 as TOT_DIFF_ADJ,
adj_c1-f.adjusted_c1,adj_c2-f.adjusted_c2,adj_c3-f.adjusted_c3,adj_c4-f.adjusted_c4,adj_c5-f.adjusted_c5
from R_SURPLUS_CASES_FROM_HT r,F_ENERGY_ADJUSTMET f
where r.buyer_service_no=f.service_no 
and r.seller_service_no=f.suplr_code 
and r.READING_MNTH='||v_r_month||' AND r.READING_YR='||v_r_year||' and f.reading_yr=r.READING_YR 
--and trim(r.reading_mnth)=trim(substr(f.reading_mnth,1) )
and r.reading_mnth=f.reading_mnth
and (adj_c1-adjusted_c1!=0 or adj_c2-adjusted_c2!=0 or adj_c3-adjusted_c3 !=0 or adj_c4-adjusted_c4! =0 or adj_c5-adjusted_c5!=0)';

v_subquery_b :='select  r.READING_MNTH,r.reading_yr,r.buyer_service_no,r.seller_service_no,nvl(c1,0),nvl(c2,0),nvl(c3,0),nvl(c4,0),nvl(c5,0),
surp_c1,surp_c2,surp_c3,surp_c4,surp_c5, 
surp_c1-nvl(c1,0) as diff_C1,surp_c2-nvl(c2,0) as diff_C2,surp_c3-nvl(c3,0) as diff_C3,surp_c4-nvl(c4,0) as diff_C4,
surp_c5-nvl(c5,0) as diff_C5,
surp_c1-nvl(c1,0)+surp_c2-nvl(c2,0)+surp_c3-nvl(c3,0)+surp_c4-nvl(c4,0)+surp_c5-nvl(c5,0) as TOT_DIFF_surp,
adj_c1-f.adjusted_c1+adj_c2-f.adjusted_c2+adj_c3-f.adjusted_c3+adj_c4-f.adjusted_c4+adj_c5-f.adjusted_c5 as TOT_DIFF_ADJ,
adj_c1-f.adjusted_c1,adj_c2-f.adjusted_c2,adj_c3-f.adjusted_c3,adj_c4-f.adjusted_c4,adj_c5-f.adjusted_c5
from R_SURPLUS_CASES_FROM_HT r,F_ENERGY_ADJUSTMET f
where r.buyer_service_no=f.service_no 
and r.seller_service_no=f.suplr_code 
and r.READING_MNTH='||v_r_month||' AND r.READING_YR='||v_r_year||' and f.reading_yr=r.READING_YR 
--and trim(r.reading_mnth)=trim(substr(f.reading_mnth,1) )
and r.reading_mnth=f.reading_mnth
and (surp_c1-nvl(c1,0)!=0 or surp_c2-nvl(c2,0)!=0 or surp_c3-nvl(c3,0) !=0 or surp_c4-nvl(c4,0) !=0 or surp_c5-nvl(c5,0)!=0)';

v_query := v_subquery_a||'union'|| v_subquery_b;

FOR LOOP_SURP IN (select  f.reading_dt
,f.created_dt,r.READING_MNTH,r.reading_yr,r.buyer_service_no,r.seller_service_no,nvl(c1,0),nvl(c2,0),nvl(c3,0),nvl(c4,0),nvl(c5,0),
surp_c1,surp_c2,surp_c3,surp_c4,surp_c5, 
surp_c1-nvl(c1,0) as diff_C1,surp_c2-nvl(c2,0) as diff_C2,surp_c3-nvl(c3,0) as diff_C3,surp_c4-nvl(c4,0) as diff_C4,
surp_c5-nvl(c5,0) as diff_C5,
surp_c1-nvl(c1,0)+surp_c2-nvl(c2,0)+surp_c3-nvl(c3,0)+surp_c4-nvl(c4,0)+surp_c5-nvl(c5,0) as TOT_DIFF_surp,
adj_c1-f.adjusted_c1+adj_c2-f.adjusted_c2+adj_c3-f.adjusted_c3+adj_c4-f.adjusted_c4+adj_c5-f.adjusted_c5 as TOT_DIFF_ADJ,
adj_c1-f.adjusted_c1,adj_c2-f.adjusted_c2,adj_c3-f.adjusted_c3,adj_c4-f.adjusted_c4,adj_c5-f.adjusted_c5
from R_SURPLUS_CASES_FROM_HT r,F_ENERGY_ADJUSTMET f
where r.buyer_service_no=f.service_no 
and r.seller_service_no=f.suplr_code 
and r.READING_MNTH='||v_r_month||' AND r.READING_YR='||v_r_year||' and f.reading_yr=r.READING_YR 
--and trim(r.reading_mnth)=trim(substr(f.reading_mnth,1) )
and r.reading_mnth=f.reading_mnth
and (adj_c1-adjusted_c1!=0 or adj_c2-adjusted_c2!=0 or adj_c3-adjusted_c3 !=0 or adj_c4-adjusted_c4! =0 or adj_c5-adjusted_c5!=0)
union
select   f.reading_dt
,f.created_dt,r.READING_MNTH,r.reading_yr,r.buyer_service_no,r.seller_service_no,nvl(c1,0),nvl(c2,0),nvl(c3,0),nvl(c4,0),nvl(c5,0),
surp_c1,surp_c2,surp_c3,surp_c4,surp_c5, 
surp_c1-nvl(c1,0) as diff_C1,surp_c2-nvl(c2,0) as diff_C2,surp_c3-nvl(c3,0) as diff_C3,surp_c4-nvl(c4,0) as diff_C4,
surp_c5-nvl(c5,0) as diff_C5,
surp_c1-nvl(c1,0)+surp_c2-nvl(c2,0)+surp_c3-nvl(c3,0)+surp_c4-nvl(c4,0)+surp_c5-nvl(c5,0) as TOT_DIFF_surp,
adj_c1-f.adjusted_c1+adj_c2-f.adjusted_c2+adj_c3-f.adjusted_c3+adj_c4-f.adjusted_c4+adj_c5-f.adjusted_c5 as TOT_DIFF_ADJ,
adj_c1-f.adjusted_c1,adj_c2-f.adjusted_c2,adj_c3-f.adjusted_c3,adj_c4-f.adjusted_c4,adj_c5-f.adjusted_c5
from R_SURPLUS_CASES_FROM_HT r,F_ENERGY_ADJUSTMET f
where r.buyer_service_no=f.service_no 
and r.seller_service_no=f.suplr_code 
and r.READING_MNTH='||v_r_month||' AND r.READING_YR='||v_r_year||' and f.reading_yr=r.READING_YR 
--and trim(r.reading_mnth)=trim(substr(f.reading_mnth,1) )
and r.reading_mnth=f.reading_mnth
and (surp_c1-nvl(c1,0)!=0 or surp_c2-nvl(c2,0)!=0 or surp_c3-nvl(c3,0) !=0 or surp_c4-nvl(c4,0) !=0 or surp_c5-nvl(c5,0)!=0);)
LOOP




END LOOP;
  END;
END DISC_SURP_ADJ_ANALYSIS;