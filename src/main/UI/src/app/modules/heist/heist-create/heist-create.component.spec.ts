import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeistCreateComponent } from './heist-create.component';

describe('HeistCreateComponent', () => {
  let component: HeistCreateComponent;
  let fixture: ComponentFixture<HeistCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeistCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeistCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
