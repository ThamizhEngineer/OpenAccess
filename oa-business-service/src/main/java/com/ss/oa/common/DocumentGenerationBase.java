package com.ss.oa.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.DocGenKeyValue;
@Scope("prototype")
public class DocumentGenerationBase{
	
	protected List<DocGenKeyValue> params = null;
	
	protected String getExtension(String fileName){
		int extStart = -1;
		String ext = "";
		
		if(fileName != null && fileName != "" && fileName.contains("."))
			 extStart = fileName.lastIndexOf(".");
			 
		
		if(extStart >= 0)
			ext = fileName.substring(extStart+1, fileName.length());
			
		return ext;
	}
	
	protected String replaceParams(String readText) {
		String key = "";
		try
		{
			if(readText != null && readText.trim() != ""){
			for(DocGenKeyValue param:this.params){
				key = "{$" + param.getKey() + "}";
				if (key != null && key != "" && readText.contains(key)){
					readText = readText.replace(key, param.getValue());
				}
			}
			}
			return readText;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	protected String readDocFile(String fileName) throws IOException {
		WordExtractor we = null;
		HWPFDocument doc = null;
		FileInputStream fis = null;
		try{
			File file = new File(fileName);
			fis = new FileInputStream(file.getAbsolutePath());
		
			doc=new HWPFDocument(fis);
			we = new WordExtractor(doc);
		
			String readText = "";
		
			String[] paragraphs = we.getParagraphText();
		
			if(paragraphs != null){
			for(String paragraph: paragraphs){
				readText += replaceParams(paragraph);
			}
		}
		
		return readText;
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally
		{
			if(we != null)
				we.close();
			if(doc != null)
				doc.close();
			if(fis != null)
				fis.close();
		}
	}
}