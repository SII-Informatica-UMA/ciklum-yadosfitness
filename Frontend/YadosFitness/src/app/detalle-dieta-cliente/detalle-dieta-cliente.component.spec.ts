import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleDietaClienteComponent } from './detalle-dieta-cliente.component';

describe('DetalleDietaClienteComponent', () => {
  let component: DetalleDietaClienteComponent;
  let fixture: ComponentFixture<DetalleDietaClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalleDietaClienteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetalleDietaClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('debe mostrar el atributo "Descripcion" debajo de nombre', () => {
    component.dieta = {
      nombre: 'Mantenimiento',
      descripcion: 'Mantener peso y masa muscular',
      observaciones: 'Llevar una dieta equilibrada',
      objetivo: 'Mantener forma fisica',
      duracionDias: 180,
      alimentos: ['Ternera', 'Patata', 'Verduras'],
      recomendaciones: 'Ser constante',
      id: 1,
      usuarioId: 5,
      creadorId: 2
    };
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('#nombre + div')).not.toBeNull();
    expect(compiled.querySelector('#nombre + div label')?.textContent).toContain('Descripción');
  });

  it('debe mostrar el duariacionDias para las dietas (probando con 180)', () => {
    component.dieta = {
      nombre: 'Mantenimiento',
      descripcion: 'Mantener peso y masa muscular',
      observaciones: 'Llevar una dieta equilibrada',
      objetivo: 'Mantener forma fisica',
      duracionDias: 180,
      alimentos: ['Ternera', 'Patata', 'Verduras'],
      recomendaciones: 'Ser constante',
      id: 1,
      usuarioId: 5,
      creadorId: 2
    };
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('#objetivo + div')).not.toBeNull();
    expect(compiled.querySelector('#objetivo + div span')?.textContent).toContain(180);
  });

});
