export interface AuthUser{
    id?: string;
    firstName?: string;
    lastName?: string;
    userName?: string;
    password?: string;
    userTypeCode?: string;
    isSuperUser?: string;
    systemKeyCode?: string;
    systemRefKey?: string;
    edcCode?: string;
    companyId?: string
    accessList?: Access[];
    token?: string;
    companyServiceId?: string;
    loginStopMessage?: string;
}

export class Access{
    functionality?: string;
	feature?: string;
	systemKey?: string;
	orgType?: string;
}