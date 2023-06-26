export class TranscoInvoice{


    ackno?: string
    agmtDt?: string
    agmtDuration?: string
    agmtType?: string
    amenDt?: string
    bankAccNo?: string
    bankBranch?: string
    bankIfscCode?: string
    bankName?: string
    cess?: string
    cgstTaxAmt?: string
    cgstTaxPer?: string
    commDt?: string
    createdBy?: string
    createdDate?: string
    cusAddress?: string
    cusEmail?: string
    cusGst?: string
    cusName?: string
    cusPan?: string
    cusPhNo?: string
    cusPin?: string
    cusState?: string
    cusStateCode?: string
    description?: string
    docId?: string
    documenttype?: string
    eeOperation?: string
    emailSentBy?: string
    emailSentDt?: string
    hsnsac?: string
    id?: string
    igstTaxAmt?: string
    igstTaxPer?: string
    invAmtInWords?: string
    invApprovedBy?: string
    invApprovedDate?: string
    invCode?: string
    invCreatedBy?: string
    invCreatedDate?: string
    invForwardBy?: string
    invForwardDate?: string
    invFromDt?: string
    invStatus?: string
    invToDt?: string
    invoiceDt?: string
    invoiceLines?: string
    invoiceType?: string
    irn?: string
    isEmailSent?: string
    lineMonth?: string
    lineYear?: string
    mCompServId?: string
    mCompServNo?: string
    mCompanyId?: string
    mOrgId?: string
    mOrgName?: string
    mSubstationCode?: string
    mSubstationId?: string
    mSubstationName?: string
    modifiedBy?: string
    modifiedDate?: string
    pdfDocId?: string
    qrCode?: string
    qrCodeReceivedDt?: string
    quantity?: string
    refInvNo?: string
    reversecharge?: string
    sgstTaxAmt?: string
    sgstTaxPer?: string
    statuscons?: string
    subTotalForGst?: string
    subTotalForOthers?: string
    supGst?: string
    supName?: string
    supPan?: string
    supState?: string
    supStateCode?: string
    supladdr1?: string
    suplieremail?: string
    suplierlocality?: string
    suplierphone?: string
    suplierpincode?: string
    totalInvAmt?: number
    totalTaxAmt?: string
    totalinwords?: string
    transactiontype?: string
    uqc?: string
    zenremarks?: string
    viritualAccno?: string
    zenPostedDate?: string
        invoiceline?:Array<InvoiceLine>; 
        
        
    }
    
    export interface InvoiceLine{
        
        lineId? :string;
        tInvHdrId?: string;
        itemNo? :string;
        chargeCode?: string;
        itemCode? :string;
        itemName? :string;
        itemDesc? :string;
        itemNotes? :string;
        itemUnitCode?: string;
        itemUnitName? :string;
        itemUnitCount? :string;
        itemRate? :string;
        itemBeforeTaxAmt?: string;
        hasGst? :string;
        igstTaxPer?: string;
        cgstTaxPer? :string;
        sgstTaxPer? :string;
        igstTaxAmt? :string;
        cgstTaxAmt? :string;
        sgstTaxAmt? :string;
        itemTaxAmt? :string;
        itemTotalAmt? :string;
        itemInvHdrId? :string;
        itemInvLineId? :string;
        createdBy? :string;
        createdDate? :string;
        modifiedBy? :string;
        modifiedDate? :string;
        itemunitcount? :string;
    }
    
    