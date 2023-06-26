package com.ss.oa.transaction.iexnoc;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.oa.model.transaction.IexNoc;

@Scope("prototype")
public interface IexNocRepository extends PagingAndSortingRepository<IexNoc,String>{


}
