import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouteGuardComponent } from './route-guard.component';

describe('RouteGuardComponent', () => {
  let component: RouteGuardComponent;
  let fixture: ComponentFixture<RouteGuardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RouteGuardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RouteGuardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
