import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleDietaComponent } from './detalle-dieta.component';

describe('DetalleDietaComponent', () => {
  let component: DetalleDietaComponent;
  let fixture: ComponentFixture<DetalleDietaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalleDietaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetalleDietaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('debe mostrar el atributo "Alimentos" debajo de duracionDias', () => {
    component.dieta = {
      nombre: 'Musculacion',
      descripcion: 'Ganancia de masa muscular',
      observaciones: 'Ganar alrededor de 2 kg al mes',
      objetivo: 'Mejorar la condición física',
      duracionDias: 90,
      alimentos: ['Macarrones', 'Atun', 'Tomate frito', 'Huevo'],
      recomendaciones: 'Beber suficiente agua',
      id: 1,
      usuarioId: 4,
      creadorId: 2
    };
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('#duracionDias + div')).not.toBeNull();
    expect(compiled.querySelector('#duracionDias + div label')?.textContent).toContain('Alimentos');
  });

  it('debe mostrar las recomendaciones para las dietas (probando con Beber suficiente agua)', () => {
    component.dieta = {
      nombre: 'Musculacion',
      descripcion: 'Ganancia de masa muscular',
      observaciones: 'Ganar alrededor de 2 kg al mes',
      objetivo: 'Mejorar la condición física',
      duracionDias: 90,
      alimentos: ['Macarrones', 'Atun', 'Tomate frito', 'Huevo'],
      recomendaciones: 'Beber suficiente agua',
      id: 1,
      usuarioId: 4,
      creadorId: 2
    };
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('#alimentos + div')).not.toBeNull();
    expect(compiled.querySelector('#alimentos + div span')?.textContent).toContain('Beber suficiente agua');
  });

});

