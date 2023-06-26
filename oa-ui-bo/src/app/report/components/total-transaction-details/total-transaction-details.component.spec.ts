import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalTransactionDetailsComponent } from './total-transaction-details.component';

describe('TotalTransactionDetailsComponent', () => {
  let component: TotalTransactionDetailsComponent;
  let fixture: ComponentFixture<TotalTransactionDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TotalTransactionDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TotalTransactionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
