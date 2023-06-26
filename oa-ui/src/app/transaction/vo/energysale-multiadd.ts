export class EnergySaleMultiAddHeader {

    id?: string;
    energySaleId?: string;
    generationStatementId?: string;
    batchId?: string;
    totalCount?: string;
    successCount?: string;
    errorCount?: string;
    sellerCompanyServiceId?: string;
    totalC1?: string;
    totalC2?: string;
    totalC3?: string;
    totalC4?: string;
    totalC5?: string;
    totalC001?: string;
    totalC002?: string;
    totalC003?: string;
    totalC004?: string;
    totalC005?: string;
    totalC006?: string;
    totalC007?: string;
    totalC008?: string;
    remarks?: string;
    createdDt?: string;
    modifiedBy?: string;
    modifiedDt?: string;
    enabled?: string;
    energySaleMultiAddLines?: Array<EnergySaleMultiAddLine>;

}

export class EnergySaleMultiAddLine {
    id?: string;
    energySaleMultiAddHeaderId?: string;
    tradeRelationshipId?: string;
    companyServiceNumber?: string;
    companyServiceId?: string;
    c1?: string;
    c2?: string;
    c3?: string;
    c4?: string;
    c5?: string;
    c001?: string;
    c002?: string;
    c003?: string;
    c004?: string;
    c005?: string;
    c006?: string;
    c007?: string;
    c008?: string;
    c009?: string;
    isClean?: string;
    imported?: string;
    importRemarks?: string;
    remarks?: string;
    createdDt?: string;
    modifiedBy?: string;
    modifiedDt?: string;
    enabled?: string;
}