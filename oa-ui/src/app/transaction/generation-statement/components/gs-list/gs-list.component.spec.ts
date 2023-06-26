import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GsListComponent } from './gs-list.component';

describe('GsListComponent', () => {
  let component: GsListComponent;
  let fixture: ComponentFixture<GsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
