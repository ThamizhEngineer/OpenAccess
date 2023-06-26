export class StandingClearence {
    id?: string;
    code?: string;
    sldcName?: string;
    esIntentId?: string;
    esIntentCode?: string;
    orgId?: string;
    orgCode?: string;
    orgName?: string;
    substationId?: string;
    substationName?: string;
    substationCode?: string;
    feederId?: string;
    feederName?: string;
    feederCode?: string;
    region?: string;
    maxDrawalCeiling?: string;
    buyerCompanyServiceId?: string;
    buyerCompanyServiceNumber?: string;
    buyerCompanyId?: string;
    buyerCompanyName?: string;
    buyerCompanyCode?: string;
    approvalDate?: string;
    enabled?: string;
    createdBy?: string;createdDate?: string;
    fromDate?: string; fromMonth?: string; fromYear?: string;
    toDate?: string; toMonth?: string; toYear?: string;
    stateTransLoss?: string; stateTransLossPercent?: string;
    stateTransCharges?: string; stateTransChargesPercent?: string;
    schedulingCharges?: string; schedulingChargesPercent?: string;
    systemOprCharges?: string; systemoprChargesPercent?: string;
    modifiedDate?: string; modifiedBy?: string;
    statusCode?: string;
    isTransmissionLoss?: string;
    isTransmissionCharges?: string;
    flowTypeCode?: string;
    constructor(){
        this.maxDrawalCeiling="1";
        this.stateTransLossPercent="0.0307";
        this.stateTransChargesPercent="126.55";
        this.schedulingChargesPercent="160";
        this.systemoprChargesPercent="33.84";
    }
}