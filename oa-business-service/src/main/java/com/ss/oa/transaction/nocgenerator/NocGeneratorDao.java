package com.ss.oa.transaction.nocgenerator;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.NocGenerator;
import com.ss.oa.transaction.vo.NocGeneratorLine;

@Scope("prototype")
public interface NocGeneratorDao {
	
	public abstract List<NocGenerator> getAllNocGenerators(Map<String,String> criteria);
	public abstract NocGenerator getNocGeneratorById(String id);
	public abstract String addNocGenerator(NocGenerator nocGenerator);
	public abstract String updateNocGenerator(String id,NocGenerator nocGenerator);
	public abstract String addNocGeneratorLine(NocGeneratorLine nocGeneratorLine);
	public abstract String updateNocGeneratorLine(NocGeneratorLine nocGeneratorLine);
}
