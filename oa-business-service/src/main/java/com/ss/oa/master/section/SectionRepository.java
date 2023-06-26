package com.ss.oa.master.section;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Integer>{
List<Section> findByMOrgId(String mOrgId);
}
