export class MeterReading {

    id?: string;
    companyMeterId?: string;
    companyMeterNumber?: string;
    companyServiceId?: string;
    companyServiceNumber?: string;
    companyId?: string;
    companyName?: string;
    companyCode?: string;
    statusCode?: string;
    statusName?: string;
    remarks?: string;
    referenceNumber?: string;
    mf?: string;
    systemDate?: string;
    readingMonthCode?: string;
    readingMonthName?: string;
    readingYear?: string;
    initReadingDate?: string;
    finalReadingDate?: string;
    rKvahInitial?: string;
    rKvahFinal?: string;
    rKvahDifference?: string;
    rKvahUnits?: string;
    kVahInitial?: string;
    kVahFinal?: string;
    kVahDifference?: string;
    kVahUnits?: string;
    totalImportGeneration?: string;
    totalExportGeneration?: string;
    createdBy?: string;
    createdDate?: string;
    modifiedBy?: string;
    modifiedDate?: string;
    modemNumber?:string;
    orgId?:string;
    fuelTypeCode?:string;
    fuelTypeName?:string;
    mrSourceCode?:string;
    meterReadingSlot?: Array<MeterReadingSlot>;
}

export class MeterReadingSlot {

    id?: string;
    meterReadingHeaderId?: string;
    remarks?: string;
    referenceNumber?: string;
    slotCode?: string;
    slotName?: string;
    impInitial?: string;
    impFinal?: string;
    impDifference?: string;
    impUnits?: string;
    expInitial?: string;
    expFinal?: string;
    expDifference?: string;
    expUnits?: string;
    createdBy?: string;
    createdDate?: string;
    modifiedBy?: string;
    modifiedDate?: string;

}


export class MeterReadingForPrint {

    id?: string;
    meterReadingHeaderId?: string;
    remarks?: string;
    referenceNumber?: string;
    slotCode?: string;
    slotName?: string;
    impInitial?: string;
    impFinal?: string;
    impDifference?: string;
    impUnits?: string;
    expInitial?: string;
    expFinal?: string;
    expDifference?: string;
    expUnits?: string;
    createdBy?: string;
    createdDate?: string;
    modifiedBy?: string;
    modifiedDate?: string;

    heading?: string;
    c1?: string;
    c2?: string;

    c3?: string;

    c4?: string;

    c5?: string;

    c6?: string;
    c7?: string;
    c8?: string;


}