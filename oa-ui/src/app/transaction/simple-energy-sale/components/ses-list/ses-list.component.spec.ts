import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SesListComponent } from './ses-list.component';

describe('EaListComponent', () => {
  let component: SesListComponent;
  let fixture: ComponentFixture<SesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
