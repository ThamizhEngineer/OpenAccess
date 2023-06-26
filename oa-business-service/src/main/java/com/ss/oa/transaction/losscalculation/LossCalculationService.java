package com.ss.oa.transaction.losscalculation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.vo.LossCalculation;

@Scope("prototype")
@Component
public class LossCalculationService {


	@Autowired
	LossCalculationDao dao;
	static HashMap<String, LossCalculation> lossCalcChart = null;

	public List<LossCalculation> fetchLossCalculation(Map<String, String> criteria) {
		
		return dao.fetchLossCalculation(criteria);
	}

	public LossCalculation calculateLoss(String injectionVoltage, String drawalVoltage, String injectedUnits) {

		if (lossCalcChart == null) {
			lossCalcChart = new HashMap<String, LossCalculation>();
			List<LossCalculation> list = dao.fetchLossCalculation(new HashMap<String, String>());

			for (LossCalculation lc : list) {
				
				lossCalcChart.put(lc.getInjectionVoltageCode()+"-"+lc.getDrawalVoltageCode(), lc);
			}
		}

		float tlu, dlu, tl, iu, drawalunits;

		LossCalculation lossCalculation = lossCalcChart.get(injectionVoltage+"-"+drawalVoltage);
		if (lossCalculation != null) {
			iu = Float.parseFloat(injectedUnits);
			tlu = Float.parseFloat(lossCalculation.getTransLossPercent()) * iu / 100;
			dlu = Float.parseFloat(lossCalculation.getDistLossPercent()) * iu / 100;
			tl = tlu + dlu;
			drawalunits = iu - tl;

			lossCalculation.setDistLossUnits(dlu + "");
			lossCalculation.setTransLossUnits(tlu + "");
			lossCalculation.setInjectedUnits(injectedUnits);
			lossCalculation.setTotalLossUnits(tl + "");
			lossCalculation.setDrawalUnits(drawalunits + "");

		}else {
			throw new OpenAccessException("Loss Calculation Exception there is no entry for injectionVoltage "+injectionVoltage+"drawalVoltage"+drawalVoltage);	
		}
		return lossCalculation;

	}

}
