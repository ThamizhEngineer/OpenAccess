package com.ss.oa.transaction.meterreading;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.ss.oa.transaction.vo.MeterReading;
import com.ss.oa.transaction.vo.MeterReadingSlot;

@Scope("prototype")
@Component
public class MeterReadingService {
	
	@Autowired
	MeterReadingDao dao;
	
	String meterReadingId;
	String meterReadingSlotId;
	
	public MeterReadingService() {
		super();
	}
	
	public List<MeterReading> getAllMeterReadings(Map<String,String> criteria){
		
		return dao.getAllMeterReadings(criteria);
	}
	
	public MeterReading getMeterReadingById(String id) {
		
		return dao.getMeterReadingById(id);
	}
	
	public String addMeterReading(MeterReading meterReading) {
		meterReadingId = dao.addMeterReading(meterReading);
		System.out.println("meterReadingid" + meterReadingId);
		if(meterReading.getMeterReadingSlot()!=null) {
			for(MeterReadingSlot meterReadingSlot :meterReading.getMeterReadingSlot()) {
				meterReadingSlot.setMeterReadingHeaderId(meterReadingId);
				meterReadingSlotId = dao.addMeterReadingSlot(meterReadingSlot);
				System.out.println("meterReadingSlotId"+meterReadingSlotId);				
			}
			
		}
		return meterReadingId;
	}
	
	public String updateMeterReading(String id,MeterReading meterReading) {
		meterReadingId = dao.updateMeterReading(id, meterReading);
		if(meterReading.getMeterReadingSlot()!=null) {
			for(MeterReadingSlot meterReadingSlot:meterReading.getMeterReadingSlot()) {
				if(meterReadingSlot.getId()!=null && meterReadingSlot.getId().trim().length()>0) {
					dao.updateMeterReadingSlot(meterReadingSlot);					
				}else {
					meterReadingSlot.setMeterReadingHeaderId(id);
					dao.addMeterReading(meterReading);
				}
			}
		}
		return meterReadingId;
	}
}