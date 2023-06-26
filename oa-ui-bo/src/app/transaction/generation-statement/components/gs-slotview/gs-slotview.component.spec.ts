import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GsSlotviewComponent } from './gs-slotview.component';

describe('GsSlotviewComponent', () => {
  let component: GsSlotviewComponent;
  let fixture: ComponentFixture<GsSlotviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GsSlotviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GsSlotviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
