import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EsListComponent } from './es-list.component';

describe('EaListComponent', () => {
  let component: EsListComponent;
  let fixture: ComponentFixture<EsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
