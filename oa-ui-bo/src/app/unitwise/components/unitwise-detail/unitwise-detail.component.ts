import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormsModule } from '@angular/forms';
//import { SessionStorage} from "angular2-localstorage/WebStorage";

import { UnitwiseEvent } from './../../services/unitwise.event';
import { UnitwiseService } from './../../services/unitwise.service';
import { CompanyService } from './../../../master/company/services/company.service';
import { Unitwise } from './../../services/unitwise';

@Component({
  selector: 'unitwise-detail',
  templateUrl: './unitwise-detail.component.html',
  providers: [UnitwiseService, CompanyService]
})
export class UnitwiseDetailComponent implements OnInit {

  unitwise: Unitwise;


  companyNames = [];
  consumerServices = [];
  generatorServices = [];
  serviceNames = [];

  nextUnitwiseStatus: string = 'Applied';
  nextAction: string = 'Apply';
  disableControls: boolean = false;
  edcname: string = '';
  consumerDetails = { 'number': '' };

  rowIndex: number;
  addScreen: boolean = true;
  unitwiseType: string = "generation";

  genRows = [
    { unitName: 'Unit-A', grossGeneration: '', auxGeneration: '', generation: '', netGeneration: '', captiveUse: '', thirdParty: '', saleToBoard: '' },
    { unitName: 'Unit-B', grossGeneration: '', auxGeneration: '', generation: '', netGeneration: '', captiveUse: '', thirdParty: '', saleToBoard: '' },
    { unitName: 'Unit-C', grossGeneration: '', auxGeneration: '', generation: '', netGeneration: '', captiveUse: '', thirdParty: '', saleToBoard: '' }
  ];
  captiveRows = [
    { unitName: 'Unit-D', captiveName: 'Unit-D Test1', serviceNumber: 'CS-111', edcName: '400 - CHENNAI (SOUTH) - I', injectionVoltage: '11kV', drawalVoltage: '10kV', quantum: '' },
    { unitName: 'Unit-E', captiveName: 'Unit-E Test2', serviceNumber: 'CS-222', edcName: '472 - TIRUNELVELI', injectionVoltage: '22kV', drawalVoltage: '20kV', quantum: '' },
    { unitName: 'Unit-F', captiveName: 'Unit-D Test3', serviceNumber: 'CS-333', edcName: '435 - COIMBATORE METRO', injectionVoltage: '33kV', drawalVoltage: '30kV', quantum: '' }
  ];

  unitwiseTypes = ['generation', 'captive'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: UnitwiseService,
    private companyService: CompanyService,
    private unitwiseEvent: UnitwiseEvent
  ) { }

  ngOnInit() {
    this.setCompanyNames();
    const t = this.route.params['_value']['type'];
    if (this.unitwiseTypes.indexOf(t) === -1) {
      this.router.navigateByUrl('/unitwise/generation');
      return;
    }
    this.unitwiseType = t;

    this.unitwiseEvent.unitwise$.subscribe(latestValue => {
      this.unitwise = latestValue;
      //this.setNextStatus(this.unitwise.header.status);
      // this.unitwise.header.unitwiseType = this.unitwiseType;
    });
    this.unitwiseEvent.load();

    if (this.route.params['_value']['_id']) {
      //route.params['value'] will have the '_id' in it, during edit 
      this.addScreen = false;
      this.route.params
        //.switchMap((params: Params) => this.service.getUnitwiseById(params['_id']))
        .subscribe((_unitwise: Unitwise) => {
          this.unitwise = _unitwise;
          this.unitwiseEvent.set(_unitwise);
          //this.setNextStatus(this.unitwise.header.status);
          //sessionStorage.setItem('company', JSON.stringify(this.company));
        });
    }

    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        const t = this.route.params['_value']['type'];
        this.unitwiseType = this.unitwiseTypes.indexOf(t) ? t : "generation";
      }
    });

  }

  setNextStatus(status: string) {

    switch (status) {
      case 'Initiated':
        this.nextUnitwiseStatus = 'Applied';
        this.nextAction = 'Apply';
        break;
      case 'Applied':
        this.nextUnitwiseStatus = 'Approved';
        this.nextAction = 'Approve';
        break;
      case 'Approved':
        this.nextUnitwiseStatus = '';
        this.nextAction = '';
        break;
      case 'Disapproved':
        this.nextUnitwiseStatus = '';
        this.nextAction = '';
        break;

      default:
        break;
    }
    this.disableControls = (this.nextUnitwiseStatus === '') ? true : false;

  }

  saveUnitwise(status: string) {
    //save can be add or update
    if (status != '') {
      //this.unitwise.header.status = status;
      var date = new Date();
      var dateString = date.getDate() + '-' + date.getMonth() + '-' + date.getFullYear();
      switch (status) {
        case 'Applied':
          //this.unitwise.header.appliedDate = dateString;
          break;
        case 'Approved':
          //	this.unitwise.header.approvedDate = dateString;
          break;
        case 'Disapproved':
          //	this.unitwise.header.disapprovedDate = dateString;
          break;

        default:
          break;
      }
    }

    if (this.unitwise._id == '') {
      this.addUnitwise();
    }
    else {
      this.updateUnitwise();
    }
  }

  addUnitwise() {
    this.service.addUnitwise(this.unitwise).subscribe(
      result => {
        this.unitwise._id = result;
        this.listUnitwises();
      },
      error => {
        console.error('Error adding NOC Application!');
        console.error(error);
      }
    );
  }

  updateUnitwise() {
    this.service.updateUnitwise(this.unitwise).subscribe(
      result => {
        this.listUnitwises();
      },
      error => {
        console.error('Error updating Unitwise!');
        console.error(error);
      }
    );
  }
  listUnitwises() {
    this.router.navigateByUrl('/unitwise/unitwise-list/' + this.unitwiseType);
    // to-do
    // this still needs to be fixed as giving '/order' is unecessary maintenance hazzle

  }

  setCompanyNames() {
    this.companyService.getCompanies().subscribe(
      _companies => {
        _companies.forEach(company => {
          this.companyNames.push({ 'key': company.companyName, 'name': company.companyName });
          if (company.services) {
            company.services.forEach(service => {
              if (service.type == "Consumer") {
                service.companyId = company.companyName;
                this.consumerServices.push(service);
              }
              if (service.type == "Generator") {
                service.companyId = company.companyName;
                this.generatorServices.push(service);

              }
            });
          }
        });
      }
    );
  }

  setServiceNames(_service) {
    this.serviceNames = [];
    this.consumerServices.forEach(service => {
      if (service.number == _service.value) {
        this.edcname = service.orgName;
      }
    });
  }
}
