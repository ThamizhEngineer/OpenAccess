package com.ss.oashared.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.TranscoInvoiceRecipt;

@Component
public class CommonUtils {

	@Value("${docs.folder.delimitter}")
	private String delimitter;
	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private DataSource dataSource;

	public String generateId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String calculateTransmissionLoss(String injectionVoltage, String drawalVoltage,
			String unitsTransfered) {
		return "" + (Float.parseFloat(unitsTransfered) * 0.05);
	}

	public static String calculateDistributionLoss(String injectionVoltage, String drawalVoltage,
			String unitsTransfered) {
		// float f = Float.parseFloat("25");
		// String s = Float.toString(25.0f);

		return "" + (Float.parseFloat(unitsTransfered) * 0.05);
	}

	public static String calculateNetUnits(String injectionVoltage, String drawalVoltage, String unitsTransfered) {
		// float f = Float.parseFloat("25");
		// String s = Float.toString(25.0f);

		return "" + (Float.parseFloat(unitsTransfered) * 0.90);
	}

	public static HttpHeaders getHttpHeader(String token, String userName) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("token", token);
		headers.add("username", userName);
		headers.add("systemkeycode", "OA");

		return headers;
	}

	@Bean
	// @LoadBalanced
	public static RestTemplate getTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		messageConverters.add(new MappingJackson2HttpMessageConverter());

		// Add the message converters to the restTemplate
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

	public static String convertDateFormat(String dateAsString, String dateFormat) {
		String formattedDate = "";
		try {
			// System.out.println("dateAsString-"+dateAsString);
			if (dateAsString != null && dateFormat.equals("DD/MM/YYYY")) {
				String a[] = dateAsString.split(" ")[0].split("-");
				formattedDate = a[2] + "/" + a[1] + "/" + a[0];
			} else
				formattedDate = dateAsString;
		} catch (Exception e) {
			e.printStackTrace();
			formattedDate = dateAsString;
		}

		return formattedDate;
	}


	
	public ResponseEntity<StreamingResponseBody> fetchFileAsStreamResponse(PrintPayload pl) {
		try {
			 HttpHeaders respHeaders = new HttpHeaders();
			    if(pl.getFileExtension().equalsIgnoreCase("csv"))
			    	respHeaders.setContentType(MediaType.parseMediaType("text/csv"));
			    else
			    	respHeaders.setContentType(MediaType.parseMediaType("application/"+pl.getFileExtension()));
	            respHeaders.add("Content-Disposition", "attachment; filename="+ pl.getFileNameToUser());
	            respHeaders.add("X-filename", pl.getFileNameToUser());
	            return ResponseEntity.ok()
	            				.headers(respHeaders)
	            				.body(prepareStreamResponse(pl.getDocPath()));
	            
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}

	}

	public static String getFile(PrintPayload pl) {
		
		
		return pl.getDocPath();
		
		
	} 
	public static String monthConversion(Integer month) {

		Map<Integer, String> monthMap = new HashMap<Integer, String>();
		monthMap.put(1, "January");
		monthMap.put(2, "February");
		monthMap.put(3, "March");
		monthMap.put(4, "April");
		monthMap.put(5, "May");
		monthMap.put(6, "June");
		monthMap.put(7, "July");
		monthMap.put(8, "August");
		monthMap.put(9, "September");
		monthMap.put(10, "October");
		monthMap.put(11, "November");
		monthMap.put(12, "December");
		return monthMap.get(month).toString();
	}
	
	public static String monthConversiontoInteger(String month) {

		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("Jan","01");
		monthMap.put("Feb","02");
		monthMap.put("Mar","03");
		monthMap.put("Apr","04");
		monthMap.put("May","05");
		monthMap.put("Jun","06");
		monthMap.put("Jul","07");
		monthMap.put("Aug","08");
		monthMap.put("Sep","09");
		monthMap.put("Oct","10");
		monthMap.put("Nov","11");
		monthMap.put("Dec","12");
		return monthMap.get(month);
	}
	
	
	
	

	// checks if the path exists and if doesnt exist creates the path
	public void checkAndCreateDir(String fullFilePath) {
		try {
			String dirName = fullFilePath.substring(0, fullFilePath.lastIndexOf(delimitter));
			File directory = new File(dirName);
			if (!directory.exists()) {
				directory.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
	}

	public void generatePdf(PrintPayload pl, String htmlContent) {
		try {
			checkAndCreateDir(pl.getDocPath());
			FileOutputStream os = null;
			final File outputFile =  new File(pl.getDocPath());
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(new FileOutputStream(pl.getDocPath()));
			renderer.finishPDF();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
	}
	public void generatePdfforRecipt(TranscoInvoiceRecipt transcoInvoiceRecipt, String htmlContent) {
		try {
			checkAndCreateDir(transcoInvoiceRecipt.getDocPath());
			FileOutputStream os = null;
			final File outputFile =  new File(transcoInvoiceRecipt.getDocPath());
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(new FileOutputStream(transcoInvoiceRecipt.getDocPath()));
			renderer.finishPDF();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
	}

	public void generateFile(PrintPayload pl, String fileContent) {
		try { 
			checkAndCreateDir(pl.getDocPath());
			final File outputFile =  new File(pl.getDocPath());  
			FileUtils.writeStringToFile(outputFile, fileContent,"UTF-8"); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
	}

	public void generateLargeFile(PrintPayload pl, StringBuilder fileContent) {
		try { 
			int BUFFER_SIZE = 1000;
			checkAndCreateDir(pl.getDocPath());
			final File outputFile =  new File(pl.getDocPath());  
			try {
			    BufferedWriter bw = new BufferedWriter(
			            new OutputStreamWriter(
			                    new FileOutputStream(outputFile, true), "UTF-8"), BUFFER_SIZE);
			    try {
			        final int length = fileContent.length();
			        final char[] chars = new char[BUFFER_SIZE];
			        int idxEnd;
			        for ( int idxStart=0; idxStart<length; idxStart=idxEnd ) {
			            idxEnd = Math.min(idxStart + BUFFER_SIZE, length);
			            fileContent.getChars(idxStart, idxEnd, chars, 0);
			            bw.write(chars, 0, idxEnd - idxStart);
			        }
			        bw.flush();
			    } finally {
			        bw.close();
			    }
			} catch ( IOException ex ) {
			    ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
	}
	public static String monthConversionString(String month) {
		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("01", "January");
		monthMap.put("02", "February");
		monthMap.put("03", "March");
		monthMap.put("04", "April");
		monthMap.put("05", "May");
		monthMap.put("06", "June");
		monthMap.put("07", "July");
		monthMap.put("08", "August");
		monthMap.put("09", "September");
		monthMap.put("10", "October");
		monthMap.put("11", "November");
		monthMap.put("12", "December");
		return monthMap.get(month).toString();
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	// -------------------------------------for Excel
	// print--------------------------------------------

	/// Reflection
	public void setValue(String value, Object target, String targetAttrib) {
		Field targetField = ReflectionUtils.findField(target.getClass(), targetAttrib);
		ReflectionUtils.makeAccessible(targetField);
		ReflectionUtils.setField(targetField, target, value);
	}

	public void setValue(int value, Object target, String targetAttrib) {
		Field targetField = ReflectionUtils.findField(target.getClass(), targetAttrib);
		ReflectionUtils.makeAccessible(targetField);
		ReflectionUtils.setField(targetField, target, value);
	}

	public static String getValue(Object source, String sourceAttrib) {
		try {
			Field sourceField = ReflectionUtils.findField(source.getClass(), sourceAttrib);
			if (sourceField != null) {
				ReflectionUtils.makeAccessible(sourceField);
			}
//			System.out.println("sourceField" + sourceField);
//			System.out.println("source" + source);
//			System.out.println("sourceField.get(source)" + sourceField.get(source));
//			System.out.println("source" + source);
			if (sourceField == null || sourceField.get(source) == null) {
				return null;
			} else {
				return sourceField.get(source).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Float round(String inputStr) {
		if(inputStr== null || inputStr.isEmpty()) {
			inputStr = "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		return  Float.parseFloat(df.format(Float.parseFloat(inputStr)));
	}
	
	public Float round(Float input) {
		DecimalFormat df = new DecimalFormat("0.0000");
		return  Float.parseFloat(df.format(input));
	}
	
	// when Integer.parseInt() is used to extract integer from String,
	// values like 0.0 or 1.00 throws exception
	// this method is to handle those scenarios too
	public int toInt(String input) {
		String outputStr="";
		int output = 0;
		if(input == null || input.trim().isEmpty()) {
			return output;
		}
		Pattern p = Pattern.compile("-?\\d+");
		Matcher m = p.matcher(input);
		while (m.find()) {
			outputStr = m.group();
			// m is an array. for 1.00, two values will be return.
			// for "1.0.1-" three values will be returned
			// so we are taking the first value alone and exiting loop
			break; 
		}
		if(!outputStr.isEmpty()) {
			output = Integer.parseInt(outputStr);
		}
		return output;
	}
	
	public Integer toInteger(String input) {
		return Integer.valueOf(this.toInt(input));
	}
	 
	
	
	public  String getDateTime(LocalDateTime dateTime, String dateFormat) {
        if(dateTime == null) {
        	dateTime = LocalDateTime.now();
        }
        if(dateFormat == null || dateFormat.isEmpty()) {
        	dateFormat = "yyyy-MM-dd-H-mm-ss";
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return dateTime.format(formatter); 
	}
	
	public StreamingResponseBody prepareStreamResponse(String fileLocation) {
		try {
			InputStream inputStream = new FileInputStream(new File(fileLocation));
		    StreamingResponseBody responseBody = outputStream -> {
		        int numberOfBytesToWrite;
		        byte[] data = new byte[1024];
		        while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
//		            System.out.println("Writing some bytes..");
		            outputStream.write(data, 0, numberOfBytesToWrite);
		        }
		        inputStream.close();
		    };

	        return responseBody;
		} catch (Exception e) {
			throw new OpenAccessException(e.getMessage());
		}
	}
	private static final String[] specialNames = {
	        "",
	        " Thousand",
	        " million",
	        " billion",
	        " trillion",
	        " quadrillion",
	        " quintillion"
	    };
	    
	    private static final String[] tensNames = {
	        "",
	        " Ten",
	        " Twenty",
	        " Thirty",
	        " Forty",
	        " Fifty",
	        " Sixty",
	        " Seventy",
	        " Eighty",
	        " Ninety"
	    };
	    
	    private static final String[] numNames = {
	        "",
	        " One",
	        " Two",
	        " Three",
	        " Four",
	        " Five",
	        " Six  ",
	        " Seven  ",
	        " Eight  ",
	        " Nine  ",
	        " Ten",
	        " Eleven",
	        " Twelve",
	        " Thirteen",
	        " Fourteen",
	        " Fifteen",
	        " Sixteen",
	        " Seventeen",
	        " Eighteen",
	        " Nineteen"
	    };
	    
	     String convertLessThanOneThousand(int number) {
	        String current;
	        
	        if (number % 100 < 20){
	            current = numNames[number % 100];
	            number /= 100;
	        }
	        else {
	            current = numNames[number % 10];
	            number /= 10;
	            
	            current = tensNames[number % 10] + current;
	            number /= 10;
	        }
	        if (number == 0) return current;
	        return numNames[number] + " hundred" + current;
	    }
	    
	    public String convert(int number) {

	        if (number == 0) { return "zero"; }
	        
	        String prefix = "";
	        
	        if (number < 0) {
	            number = -number;
	            prefix = "negative";
	        }
	        
	        String current = "";
	        int place = 0;
	        
	        do {
	            int n = number % 1000;
	            if (n != 0){
	                String s = convertLessThanOneThousand(n);
	                current = s + specialNames[place] + current;
	            }
	            place++;
	            number /= 1000;
	        } while (number > 0);
	        
	        return (prefix + current).trim();
	    }
	    
	    

	
	
}
