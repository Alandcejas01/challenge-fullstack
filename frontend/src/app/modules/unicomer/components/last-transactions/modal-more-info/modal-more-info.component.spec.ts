import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalMoreInfoComponent } from './modal-more-info.component';

describe('ModalMoreInfoComponent', () => {
  let component: ModalMoreInfoComponent;
  let fixture: ComponentFixture<ModalMoreInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalMoreInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalMoreInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
