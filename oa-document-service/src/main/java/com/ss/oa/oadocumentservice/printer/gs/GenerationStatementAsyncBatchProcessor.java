package com.ss.oa.oadocumentservice.printer.gs;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.doc.DocInfoRepository;
import com.ss.oashared.model.DocInfo;
import com.ss.oashared.model.PrintPayload;

import oracle.jdbc.OracleTypes;

@Component

public class GenerationStatementAsyncBatchProcessor {


	@Resource
	private DataSource dataSource;
	
	@Autowired
	DocInfoRepository docInfoRepository;
	
	@Autowired
	GenerationStatementPrinter generationStatementPrinter;
	
	@Autowired
	CommonUtils commonUtils;
	@Lazy
	@Async("asyncThreadPoolTaskExecutor")
	public List<String> generatePdfFile(List<String> ids, String txnId,String month,String year) throws IOException {
		String commonLog ="GenerationStatment Printer - txnId "+txnId;
		DocInfo doc = new DocInfo();
		doc.setBatchKey(commonUtils.generateId());
		System.out.println(commonLog + " forkJoin-call starts-->  " +  LocalDateTime.now());
		try {
			forkJoin(ids,doc.getBatchKey(),txnId, month, year);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		updateDocIdInGenStmt(doc.getBatchKey(),txnId);
		return ids;
	}
	

	//--------------------Fork Join
	
//	@Lazy
//	@Async("asyncThreadPoolTaskExecutor")
	public String forkJoin(List<String> gslist ,String docBatch,String txnId,String month,String year) throws InterruptedException, ExecutionException, IOException {
		
		System.out.println("docBatch-->"+docBatch);
		
		List<String> temp1 = new ArrayList<String>();
		List<String> temp2 = new ArrayList<String>();
		List<String> temp3 = new ArrayList<String>();
		List<String> temp4 = new ArrayList<String>();
		List<String> temp5 = new ArrayList<String>();
		List<String> temp6 = new ArrayList<String>();
		List<String> temp7 = new ArrayList<String>();
		List<String> temp8 = new ArrayList<String>();
		List<String> temp9 = new ArrayList<String>();
		List<String> temp10 = new ArrayList<String>();
		
		for (int i = 0; i < gslist.size(); i += 10) {
			List<String> sub = gslist.subList(i, Math.min(gslist.size(),i+10));
			System.out.println("loopSize-->"+i);
			System.out.println("iterateList-->"+sub);
				if(temp1.isEmpty()) {
					temp1.addAll(sub);
				}else if(temp2.isEmpty()) {
					temp2.addAll(sub);
				}else if(temp3.isEmpty()) {
					temp3.addAll(sub);
				}else if(temp4.isEmpty()) {
					temp4.addAll(sub);
				}else if(temp5.isEmpty()) {
					temp5.addAll(sub);
				}else if(temp6.isEmpty()) {
					temp6.addAll(sub);
				}else if(temp7.isEmpty()) {
					temp7.addAll(sub);
				}else if(temp8.isEmpty()) {
					temp8.addAll(sub);
				}else if(temp9.isEmpty()) {
					temp9.addAll(sub);
				}else if(temp10.isEmpty()) {
					temp10.addAll(sub);
				}		
		}
		
		System.out.println("temp1-->"+temp1);
		System.out.println("temp2-->"+temp2);
		System.out.println("temp3-->"+temp3);
		
		Future<List<String>> future1 = calculate(temp1, docBatch, month, year);
		Future<List<String>> future2 = calculate(temp2, docBatch, month, year);
		Future<List<String>> future3 = calculate(temp3, docBatch, month, year);
		Future<List<String>> future4 = calculate(temp4, docBatch, month, year);
		Future<List<String>> future5 = calculate(temp5, docBatch, month, year);
		Future<List<String>> future6 = calculate(temp6, docBatch, month, year);
		Future<List<String>> future7 = calculate(temp7, docBatch, month, year);
		Future<List<String>> future8 = calculate(temp8, docBatch, month, year);
		Future<List<String>> future9 = calculate(temp9, docBatch, month, year);
		Future<List<String>> future10 = calculate(temp10, docBatch, month, year);
	
		while (!(future1.isDone() && future2.isDone() && future3.isDone() && future4.isDone() && future5.isDone() && future6.isDone() && future7.isDone() && future8.isDone() && future9.isDone() && future10.isDone())) {
//		    System.out.println(
//		      String.format(
//		        "future1 is %s and future2 is %s and future3 is %s and future4 is %s and future5 is %s and future6 is %s and future7 is %s and future8 is %s and future9 is %s and future10 is %s", 
//		        future1.isDone() ? "done" : "not done", 
//		        future2.isDone() ? "done" : "not done",
//		        future3.isDone() ? "done" : "not done",
//		        future4.isDone() ? "done" : "not done",
//		        future5.isDone() ? "done" : "not done",
//		        future6.isDone() ? "done" : "not done",
//		        future7.isDone() ? "done" : "not done",
//		        future8.isDone() ? "done" : "not done",
//		        future9.isDone() ? "done" : "not done",
//		        future10.isDone() ? "done" : "not done"
//		      )
//		    );
//		    Thread.sleep(10);
		}
		return docBatch;
	}
	
	public Future<List<String>> calculate(List<String> idList,String docBatch,String month,String year) throws IOException {
		 ExecutorService executor = Executors.newSingleThreadExecutor();
	    return executor.submit(new Callable<List<String>>() {
	        @Override
	        public List<String> call() throws Exception {
	        	return generateConcPdfFile(idList,docBatch,null,month, year);
	        }
	    });
	}
	
//Dynamic pool size	
	
//	public  void whatever(List<String> idList,String docBatch) throws InterruptedException {
//		int poolSize=10;
//		Executor executor = Executors.newFixedThreadPool(poolSize);
//		CompletionService<List<String>> completionService = 
//		       new ExecutorCompletionService<List<String>>(executor);
//		 for(int i = 0; i < poolSize; i++) {
//			   completionService.submit(new Callable<List<String>>() {
//			       public List<String> call() {
//			           return generateConcPdfFile(idList,docBatch,null);
//			       }
//			   });
//			}
//		 
//		 int received = 0;
//		 boolean errors = false;
//		 
//		 while(received < poolSize && !errors) {
//		      Future<List<String>> resultFuture = completionService.take(); //blocks if none available
//		      try {
//		    	  List<String> result = resultFuture.get();
//		         received ++;
//		      }
//		      catch(Exception e) {
//		         errors = true;
//		      }
//		}
//	}
	
	public List<String> generateConcPdfFile(List<String> gsIds,String docBatch,String txnId,String month,String year){
		
		System.out.println("gsId-->"+gsIds+" docBatch-->"+docBatch);
		
		PrintPayload payload = new PrintPayload();
		try {
			for(String ids:gsIds) {
				payload.setName(PrintPayload.PrintTypes.GenerationStatement);
				payload.getFilterCriteria().put("id", ids);
				if(payload.getName().equals(PrintPayload.PrintTypes.GenerationStatement)) {
					System.out.println(ids);
					generationStatementPrinter.process(payload, docBatch,txnId, month, year);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gsIds;
	}
	
	
	 // ----------------Update Doc_id in GS

		public String updateDocIdInGenStmt(String batchKey,String txnId) {
			String commonLog ="GenerationStatment Printer - txnId "+txnId;

			Connection conn = null;
			CallableStatement stmt = null;
			String updateDoc = "{call DOC_INFO_PACK.UPDATE_GENSTMT_BY_BATCH(?,?)}";
			String result = "Success";
			System.out.println(commonLog + "doc-batch -->" +  batchKey + "TIme-->" + LocalDateTime.now());
			try {
				conn =  dataSource.getConnection();
				stmt = conn.prepareCall(updateDoc);
				stmt.setString(1, batchKey);
				stmt.registerOutParameter(2, OracleTypes.VARCHAR);
				stmt.execute();
				result = stmt.getString(1);
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				
				try {
					if((stmt!= null) && (!stmt.isClosed())) {
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if((conn!= null) && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return result;
		}
	
	
}
