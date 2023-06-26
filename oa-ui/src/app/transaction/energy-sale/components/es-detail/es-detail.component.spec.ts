import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EsDetailComponent } from './es-detail.component';

describe('EaDetailComponent', () => {
  let component: EsDetailComponent;
  let fixture: ComponentFixture<EsDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EsDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
