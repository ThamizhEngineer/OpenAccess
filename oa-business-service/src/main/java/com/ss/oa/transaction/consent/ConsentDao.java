package com.ss.oa.transaction.consent;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Consent;

@Scope("prototype")
public interface ConsentDao {

		public abstract List<Consent> getAllConsents(Map<String,String> criteria);
		public abstract Consent getConsentById(String id);
		public abstract String addConsent(Consent consent);
		public abstract String updateConsent(String id, Consent consent);
}
