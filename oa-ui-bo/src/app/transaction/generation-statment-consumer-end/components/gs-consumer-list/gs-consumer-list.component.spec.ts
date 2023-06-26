import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GsConsumerListComponent } from './gs-consumer-list.component';

describe('GsConsumerListComponent', () => {
  let component: GsConsumerListComponent;
  let fixture: ComponentFixture<GsConsumerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GsConsumerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GsConsumerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
