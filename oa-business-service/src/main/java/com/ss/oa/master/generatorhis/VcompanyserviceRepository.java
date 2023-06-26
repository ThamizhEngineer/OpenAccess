package com.ss.oa.master.generatorhis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.oa.master.vo.VCompanyServiceHis;

@Repository
public interface VcompanyserviceRepository extends JpaRepository<VCompanyServiceHis, String> {

	Iterable<VCompanyServiceHis> findByMCompServNumber(String mCompServNumber);

}
