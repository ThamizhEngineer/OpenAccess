package com.ss.oa.master.generatorhis;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.oa.master.vo.Generatorhis;

@Repository
public interface GeneratorhisRepository extends CrudRepository<Generatorhis, String> {

	List<Generatorhis> findByMCompServNumber(String mCompServNumber);

}
