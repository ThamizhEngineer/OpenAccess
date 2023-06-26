export class BadgeItem {
    type: string;
    value: string;
}
export class ChildrenItems {
    state: string;
    name: string;
    type ? : string;
}
export class Menu {
    state: string;
    name: string;
    type: string;
    icon: string;

    badge ? : BadgeItem[];
    children ? : ChildrenItems[];
    states ? : string[];
}
