package com.ss.oa.master.tariff;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.MasterRestService;
import com.ss.oa.master.vo.Tariff;


@RestController
@Scope("prototype")
public class TariffRestService extends MasterRestService {
	
	@Autowired
	private TariffService service;
	
	
	
	@RequestMapping(value="/tariffs", method = RequestMethod.GET)
	public ResponseEntity<List<Tariff>> getTariffs(@RequestParam(name="from_date",required=false) String fromdate,@RequestParam(name="to_date",required=false) String todate)
	{
		try {
			Map<String, Date> criteria = new HashMap<String, Date>();
			if(fromdate!=null)
			{
				java.util.Date date=new SimpleDateFormat("dd/MM/yyyy").parse(fromdate);
				Date from_date = new Date(date.getTime()); 
				criteria.put("from_date",from_date);
			}
			if(todate!=null)
			{
				java.util.Date date=new SimpleDateFormat("dd/MM/yyyy").parse(todate);
				Date to_date = new Date(date.getTime()); 
				criteria.put("to_date",to_date);
			}
			if(!criteria.isEmpty())
			{
				for (Entry<String, Date> element : criteria.entrySet()){
					if(element.getKey().equals("from_date"))
						System.out.println("criteria from_date-->"+element.getValue().toString()+"");
					if(element.getKey().equals("to_date"))
						System.out.println("criteria to_date-->"+element.getValue().toString()+"");
				}
			}
			
			//List<Tariff> a = service.getAllTariff(criteria);
			return ResponseEntity.ok(service.getAllTariff(criteria));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@RequestMapping(value="/tariff/{id}", method = RequestMethod.GET)
	public ResponseEntity<Tariff> getTariffById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getTariffById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/tariff", method = RequestMethod.POST)
	public ResponseEntity<String> addTariff(@RequestBody Tariff tariff)
	{
		String result = "";
		try {
			result = service.addTariff(tariff);
			if(result.matches("FAILURE")){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
			else{
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {

			e.printStackTrace();
			result =  "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
		
	}
	
	@RequestMapping(value="/tariff/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTariff(@PathVariable("id")String id,@RequestBody Tariff tariff)
	{
		String result = "";
		try {
			result = service.updateTariff(id, tariff);
			if(result.matches("FAILURE")){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
			else{
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result =  "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}

	@RequestMapping(value="/tariff/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTariff(@PathVariable("id")String id)
	{
		String result = "";
		try {
			result = service.deleteTariff(id);
			if(result.matches("FAILURE")){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
			else{
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result =  "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}
	
}
