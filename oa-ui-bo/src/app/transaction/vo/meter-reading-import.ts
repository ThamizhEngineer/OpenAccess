export class MeterReadingImport{
            id: string;
            importDate?:string ; 
            readingMonthCode?: string;
            readingYear?: string;  
            fromDate?:string ;    
            toDate ?:string  ; 
            statusCode?:string ;   
            statusName?:string;
            remarks :string;  
            error?:string ;
            mrSourceCode?:string ;
            mrSourceName ?:string ;   
            createdBy?:string ;
            createdDate?:string ;   
            modifiedBy?:string ;  
            modifiedDate ?:string;
            meterReadingImportLines?:Array<MeterReadingImportLine>;  
          } 

    export class MeterReadingImportLine {
           statusCode?:string;
           statusName?:string;
           remarks?:string;
           impMrHeaderId?:string;
           meterNumber?:string;
           mf?:string;
           serviceNumber?:string;
           systemDate?:string;
           initReadingDate?:string;
           finalReadingDate?:string;
           impInitS1?:string;
           impInitS2?:string;
           impInitS3?:string;
           impInitS4?:string;
           impInitS5?:string;
           impFinalS1?:string;
           impFinalS2?:string;
           impFinalS3?:string;
           impFinalS4?:string;
           impFinalS5?:string;
           expInitS1?:string;
           expInitS2?:string;
           expInitS3?:string;
           expInitS4?:string;
           expInitS5?:string;
           expFinalS1?:string;
           expFinalS2?:string;
           expFinalS3?:string;
           expFinalS4?:string;
           expFinalS5?:string;
           impkVahInit?:string;
           impkVahFinal?:string;
           expkVahInit?:string;
           expkVahFinal?:string;
            impRkvahInit?:string;
           impRkvahFinal?:string;
           expRkvahInit?:string;
           expRkvahFinal?:string;
           createdBy?:string;
           createdDate?:string;
           modifiedBy?:string;
           modifiedDate?:string;    
           readingMonthCode?:string;
           readingYear?:string
         }