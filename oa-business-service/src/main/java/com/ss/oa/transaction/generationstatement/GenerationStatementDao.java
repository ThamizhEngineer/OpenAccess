package com.ss.oa.transaction.generationstatement;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oa.transaction.vo.GenerationStatementCharge;
import com.ss.oa.transaction.vo.GenerationStatementSlot;

@Scope("prototype")
public interface GenerationStatementDao {
	
	public abstract List<GenerationStatement> getAllGenerationStatements(Map<String, String> criteria);
	public abstract List<GenerationStatement> getAllBuyerGenerationStatements(Map<String, String> criteria);
	public abstract GenerationStatement getGenerationStatementById(String id);
	public abstract String addGenerationStatement(GenerationStatement generationStatement);
	public abstract String updateGenerationStatement(String id,GenerationStatement generationStatement);
	public abstract String addGenerationStatementCharge(GenerationStatementCharge generationStatementCharge);
	public abstract String updateGenerationStatementCharge(GenerationStatementCharge generationStatementCharge);
	public abstract String addGenerationStatementSlot(GenerationStatementSlot generationStatementSlot);
	public abstract String updateGenerationStatementSlot(GenerationStatementSlot generationStatementSlot);
	
	
	

}
