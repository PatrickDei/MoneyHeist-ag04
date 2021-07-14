import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeistEditComponent } from './heist-edit.component';

describe('HeistEditComponent', () => {
  let component: HeistEditComponent;
  let fixture: ComponentFixture<HeistEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeistEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeistEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
