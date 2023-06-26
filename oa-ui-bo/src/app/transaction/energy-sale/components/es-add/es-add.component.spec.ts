import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EsAddComponent } from './es-add.component';

describe('EsAddComponent', () => {
  let component: EsAddComponent;
  let fixture: ComponentFixture<EsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
