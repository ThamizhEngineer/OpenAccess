package com.ss.oa.ZenInvoice;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.BankingIntegrationService.model.BankingSavrResponseImp;
import com.ss.oa.vo.McMeterReading;
import com.ss.oa.vo.QrcodeInfoData;
import com.ss.oashared.model.InvoiceHeader;
import com.ss.oashared.model.ZenInvoice;

@RestController
@RequestMapping("/zen")
public class ZenIntegrationService {

	@Autowired
	ZenIntegrationRepository zenrepo;
	@Resource
	@Qualifier("zenJdbc")
	private JdbcOperations jdbcOperations;
	@Resource
	@Qualifier("appJdbc")
	private JdbcOperations jdbcOperationsAppdb;

	@CrossOrigin(origins = "*")
	@GetMapping("/getall")
	private Iterable<ZenInvoice> getAllZenIntegration() {
		return zenrepo.findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/getzen")
	private ZenInvoice getAllByZenId(@RequestParam(name = "serviceNo", required = false) String serviceNo,
			@RequestParam(name = "billMonth", required = false) String billMonth,
			@RequestParam(name = "billYear", required = false) String billYear) {
		System.out.println(serviceNo);
		return zenrepo.findById(serviceNo, billMonth, billYear);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{serviceNo}")
	private Optional<ZenInvoice> getpostdata(@PathVariable("serviceNo") String serviceNo) {
		return zenrepo.findById(serviceNo);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/{id}")
	private Iterable<ZenInvoice> findZenId(@PathVariable("id") String id, @RequestBody ZenInvoice data) {
		zenrepo.save(data);
		return zenrepo.findAllById(id);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/insertforzen/_internal")
	private ZenInvoice internal(@RequestBody ZenInvoice zeninvoice) {

//		zeninvoice.setId("OA1001");
//		zenrepo.save(zeninvoice);
		LocalDateTime localDate = LocalDateTime.now();
		//zeninvoice.getOtherChrg().substring(0, zeninvoice.getOtherChrg().indexOf("."));
		//int taxable = Integer.parseInt(zeninvoice.getTotalAmount().substring(0, zeninvoice.getTotalAmount().indexOf(".")))- Integer.parseInt(zeninvoice.getOtherChrg())-180;
		
		String sql = "INSERT INTO OA_EINVDATA(ID, SERVICENO, BILL_MNTH, BILL_YR,TAXPAYERGSTIN, CUSTOMERGSTIN, DOCUMENTNO, DOCUMENTDATE, DOCUMENTTYPE,TRANSACTIONTYPE, HSNSAC,DESCRIPTI0N, TAXABLEVALUE, GSTTAXRATE,UQC, QUANTITY, IGSTAMOUNT, SGST, CGST, CESS, PLACEOFSUPPLY,\n"
				+ "REVERSECHRG, SUPLRLEGALNAME, SUPLRADDR1, SUPLRADDR2, SUPLIERLOCALITY, SUPLRPINCODE, SUPLRPHONE, SUPLREMAIL, RECIPIENTLEGALNAME, RECIPIENTADDR1,RECIPIENTADDR2, RECEIPIENTLOCALITY, RECIPIENTPIN\n"
				+ ",RECIPIENTPHONE, RECIPIENTEMAIL, IRN, SQRCODE,STATUSEINV,ACKNO,ACKDATE,DEPARTMENT,OTHERCHRG,TOTALAMT,STATUS_CONS) VALUES(ENIVOICE_ID.nextval,'"
				+ zeninvoice.getServiceNo() + "'," + zeninvoice.getBillMonth() + "," + zeninvoice.getBillYear() + ",'"
				+ zeninvoice.getTaxPayerGstIn() + "','" + zeninvoice.getCustomerGstIn() + "','"
				+ zeninvoice.getDocumentNo() + "','" + zeninvoice.getDocumentDate() + "','"
				+ zeninvoice.getDocumentType() + "','" + zeninvoice.getTransactionType() + "','"
				+ zeninvoice.getHsnSac() + "','" + zeninvoice.getDescription() + "'," + zeninvoice.getTaxableValue()
				+ ",'18','" + zeninvoice.getUqc().substring(0, 3) + "','"
				+ zeninvoice.getQuantity() + "'," + zeninvoice.getIgstAmount() + ", "+ zeninvoice.getSgstAmount() +"," + zeninvoice.getCgstAmount() +",'" + zeninvoice.getCess().substring(0, 4) + "','"
				+ zeninvoice.getPlaceOfsupply().substring(0, 2) + "','" + zeninvoice.getReverseChrg() + "','"
				+ zeninvoice.getSuplrLegalName() + "','" + zeninvoice.getSuplrAddr1() + "','"
				+ zeninvoice.getSuplrAddr2() + "','" + zeninvoice.getSuplrLocality() + "','"
				+ zeninvoice.getSuplrPinCode() + "','" + zeninvoice.getSuplrPhone() + "','" + zeninvoice.getSuplrEmail()
				+ "','" + zeninvoice.getRecipientLegalName() + "','" + zeninvoice.getRecipientAddr1() + "','"
				+ zeninvoice.getRecipientAddr2() + "','" + zeninvoice.getRecipientLocality().substring(0, 19) + "','638008','" + zeninvoice.getRecipientPhone() + "','"
				+ zeninvoice.getRecipientEmail() + "'," + zeninvoice.getIrn() + "," + zeninvoice.getSqrCode() + ","
				+ zeninvoice.getStatusEinv() + "," + zeninvoice.getAckNo() + "," + zeninvoice.getAckDate() + ",'"
				+ zeninvoice.getDepartment() + "'," + zeninvoice.getOtherChrg() + "," + zeninvoice.getTotalAmount()
				+ ",'Regular')";

		
		jdbcOperations.execute(sql);
		



		System.out.println("Inserted data to zen");

		return zeninvoice;
	}

	@Scheduled(fixedDelay = 120000, initialDelay = 120000)
	private List<QrcodeInfoData> GettingQrCode() {
		System.out.println("came here for zen");

		List<QrcodeInfoData> QrCodefromInrInfo = new ArrayList<>();
		LocalDate Month = LocalDate.now().minusMonths(1);
		String Sql = "SELECT * FROM OA_EINVDATA WHERE BILL_MNTH = " + Month.getMonthValue() + " AND BILL_YR  = "
				+ Month.getYear() + " AND SQRCODE IS NULL ";
		System.out.println(Sql);

		List<ZenInvoice> QrCodeGetter = jdbcOperations.query(Sql, new InvoiceMapper());
		System.out.println(QrCodeGetter.size());
		System.out.println(!QrCodeGetter.isEmpty());

		if (!QrCodeGetter.isEmpty()) {
			for (int i = 0; i <= QrCodeGetter.size()-1; i++) {

				System.out.println("came in");

				String Sqlgettingqr = "SELECT * FROM OA_IRN_INFO WHERE INVOICE_NO = '"
						+ QrCodeGetter.get(i).getDocumentNo() + "' AND (QRCODE IS NOT NULL OR MESSAGE IS NOT NULL) ";
				QrCodefromInrInfo = jdbcOperations.query(Sqlgettingqr, new InrInfoMapper());

				System.out.println(Sqlgettingqr);
				System.out.println(QrCodefromInrInfo);

				if (!QrCodefromInrInfo.isEmpty()) {

					if (QrCodefromInrInfo.get(0).getQRCODE() != null) {
						LocalDate localDate = LocalDate.now();
						System.out.println(localDate);
						System.out.println("Not Null");

						jdbcOperations.execute("UPDATE OA_EINVDATA SET SQRCODE='" + QrCodefromInrInfo.get(0).getQRCODE()
								+ "' WHERE DOCUMENTNO ='" + QrCodefromInrInfo.get(0).getINVOICE_NO() + "'");
						jdbcOperationsAppdb.execute("UPDATE T_INVOICE_HDR SET QRCODE='"
								+ QrCodefromInrInfo.get(0).getQRCODE() + "', QRCODERECEIVEDDT =TO_DATE('" + localDate
								+ "', 'YYYY/MM/DD'), INVSTATUS = 'CONFIRMED' WHERE DOCID ='"
								+ QrCodefromInrInfo.get(0).getINVOICE_NO() + "'");
					}
					if (QrCodefromInrInfo.get(0).getMESSAGE() != null) {
						System.out.println("feched");
						
						jdbcOperationsAppdb
								.execute("UPDATE T_INVOICE_HDR SET ZENREMARKS='" + QrCodefromInrInfo.get(0).getMESSAGE().replaceAll("'","")
										+ "' WHERE DOCID ='" + QrCodefromInrInfo.get(0).getINVOICE_NO() + "'");

					}
				}

				

			}
		}

		return QrCodefromInrInfo;

	}

	final class InvoiceMapper implements RowMapper<ZenInvoice> {

		public InvoiceMapper() {
			super();
		}

		public ZenInvoice mapRow(ResultSet resultSet, int rownum) throws SQLException {
			ZenInvoice zenInvoice = new ZenInvoice();
			zenInvoice.setDocumentNo(resultSet.getString("DOCUMENTNO"));

			return zenInvoice;

		}

	}

	// inr info mapper
	final class InrInfoMapper implements RowMapper<QrcodeInfoData> {

		public InrInfoMapper() {
			super();
		}

		public QrcodeInfoData mapRow(ResultSet resultSet, int rownum) throws SQLException {
			QrcodeInfoData InrData = new QrcodeInfoData();

			InrData.setINVOICE_NO(resultSet.getString("INVOICE_NO"));
			InrData.setQRCODE(resultSet.getString("QRCODE"));
			InrData.setMESSAGE(resultSet.getString("MESSAGE"));

			return InrData;

		}

	}

}