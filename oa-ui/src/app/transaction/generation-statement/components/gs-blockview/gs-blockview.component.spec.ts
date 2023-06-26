import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GsBlockviewComponent } from './gs-blockview.component';

describe('GsBlockviewComponent', () => {
  let component: GsBlockviewComponent;
  let fixture: ComponentFixture<GsBlockviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GsBlockviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GsBlockviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
