import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GsDetailComponent } from './gs-detail.component';

describe('GsDetailComponent', () => {
  let component: GsDetailComponent;
  let fixture: ComponentFixture<GsDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GsDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
