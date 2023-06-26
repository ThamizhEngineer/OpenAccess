import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PowerplantListComponent } from './powerplant-list.component';

describe('PowerplantListComponent', () => {
  let component: PowerplantListComponent;
  let fixture: ComponentFixture<PowerplantListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PowerplantListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PowerplantListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
