package com.ss.oa.transaction.ipaastandingclearance;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.transaction.IpaStandingClearance;
import java.lang.String;
import java.util.List;

public interface IpaaStandingClearanceRepository extends CrudRepository<IpaStandingClearance, String>{

	IpaStandingClearance findByIexNocId(String iexnocid);
	
	IpaStandingClearance findByScId(String scid);

	List<IpaStandingClearance> findByIpaId(String ipaid);
}
