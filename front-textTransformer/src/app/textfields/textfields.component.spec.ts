import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TextfieldsComponent } from './textfields.component';

describe('TextfieldsComponent', () => {
  let component: TextfieldsComponent;
  let fixture: ComponentFixture<TextfieldsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TextfieldsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TextfieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
