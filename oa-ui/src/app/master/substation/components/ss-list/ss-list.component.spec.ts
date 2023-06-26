import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SsListComponent } from './ss-list.component';

describe('SsListComponent', () => {
  let component: SsListComponent;
  let fixture: ComponentFixture<SsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
