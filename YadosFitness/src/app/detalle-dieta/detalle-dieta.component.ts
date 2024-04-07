import { Component, Input } from '@angular/core';
import { Dieta } from '../entities/dieta';
@Component({
  selector: 'app-detalle-dieta',
  standalone: true,
  imports: [],
  templateUrl: './detalle-dieta.component.html',
  styleUrl: './detalle-dieta.component.css'
})
export class DetalleDietaComponent {
  @Input() dieta?: Dieta;

  constructor() {
  }
}
