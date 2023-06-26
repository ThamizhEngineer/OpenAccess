package com.ss.oa.transaction.losscalculation;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.LossCalculation;

@Scope("prototype")
public interface LossCalculationDao {

		public abstract List<LossCalculation> fetchLossCalculation(Map<String,String> criteria);
}
