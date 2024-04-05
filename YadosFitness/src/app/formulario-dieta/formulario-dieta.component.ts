import { Component } from '@angular/core';
import { Dieta } from '../entities/dieta';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-formulario-dieta',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './formulario-dieta.component.html',
  styleUrl: './formulario-dieta.component.css'
})
export class FormularioDietaComponent {
  constructor(public modal: NgbActiveModal) {}
  accion?: "Añadir" | "Editar";
  dieta : Dieta = { nombre: '', descripcion: '', observaciones:'', objetivo:'', duracionDias: 0, 
    alimentos: [], recomendaciones:'', id: 0, usuarioId:0 }
  guardarDieta(): void {
    this.modal.close(this.dieta);
  }

}
