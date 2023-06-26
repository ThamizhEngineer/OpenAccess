import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GcAddComponent } from './gc-add.component';

describe('GcAddComponent', () => {
  let component: GcAddComponent;
  let fixture: ComponentFixture<GcAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GcAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GcAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
