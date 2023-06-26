package com.ss.oa.transaction.sldcNoc;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.SldcNocLine;

@Scope
public interface SldcLineRepo extends CrudRepository<SldcNocLine, String> {



	Optional<SldcNocLine> findByCompServNo(String compServNo);

}
