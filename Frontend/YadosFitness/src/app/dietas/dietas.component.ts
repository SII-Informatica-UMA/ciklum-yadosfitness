import { Component } from '@angular/core';
import { CommonModule, TitleCasePipe } from '@angular/common';
import { UsuariosService } from '../services/usuarios.service';

@Component({
  selector: 'app-dietas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dietas.component.html',
  styleUrl: './dietas.component.css'
})
export class DietasComponent {

  constructor(private usuarioService: UsuariosService){

  }

  get usuarioSesion() {
    return this.usuarioService.getUsuarioSesion();
  }

}
