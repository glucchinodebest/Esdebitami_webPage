import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EsdebitamiDashboardComponent } from './esdebitami-dashboard.component';

describe('EsdebitamiDashboardComponent', () => {
  let component: EsdebitamiDashboardComponent;
  let fixture: ComponentFixture<EsdebitamiDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EsdebitamiDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EsdebitamiDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
