import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GscDetailComponent } from './gs-consumer-detail.component';

describe('GscDetailComponent', () => {
  let component: GscDetailComponent;
  let fixture: ComponentFixture<GscDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GscDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GscDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
