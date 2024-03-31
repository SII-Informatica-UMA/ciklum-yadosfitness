import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NgbNavModule, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { DietasComponent } from './dietas/dietas.component';
import { InicioComponent } from './inicio/inicio.component';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgbNavModule, NgbDropdownModule, DietasComponent, CommonModule, InicioComponent], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'YadosFitness';
  active = 1;
}


