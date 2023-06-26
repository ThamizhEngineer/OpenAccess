package com.ss.oa.oadocumentservice.printer;



import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;

import com.ss.oa.oadocumentservice.printer.gs.GenerationStatementPrinter;




public class ForkJoinTask {
	@Autowired
	GenerationStatementPrinter generationStatementPrinter;
	
	private ExecutorService executor = Executors.newSingleThreadExecutor();
   
  public Future<List<String>> calculate(List<String> idList,String docBatch) {        
      return executor.submit(() -> {
          Thread.sleep(3000);
          System.out.println("ForkJoinTask-->"+idList+"  docBatch-->"+docBatch);
//          generationStatementPrinter.generateConcPdfFile(idList,docBatch);
//          System.out.println(data);
          return idList;
      });
  }
	
}
