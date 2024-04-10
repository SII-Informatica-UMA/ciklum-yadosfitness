import { TestBed } from '@angular/core/testing';
import { Dieta } from '../entities/dieta';
import { DietaService } from './dieta.service';
import { usuarios } from './usuarios.db.service';

describe('DietaService', () => {
  let service: DietaService;
  let dietas : Dieta[];

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DietaService);
    service.getDietas().subscribe(dietass => {
      dietas = dietass;
    })
  });

  it('deberia tener el atributo nombre', () => {
    expect(dietas[0].nombre).toBeDefined();
  });

  it('deberia tener el atributo id', () => {
    expect(dietas[0].id).toBeDefined();
  });
  
  it('deberia tener el atributo creadoId', () => {
    expect(dietas[0].creadorId).toBeDefined();
  });

});
