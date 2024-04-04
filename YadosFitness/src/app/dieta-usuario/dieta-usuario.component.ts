import { Component } from '@angular/core';
import { UsuariosService } from '../services/usuarios.service';

@Component({
  selector: 'app-dieta-usuario',
  standalone: true,
  imports: [],
  templateUrl: './dieta-usuario.component.html',
  styleUrl: './dieta-usuario.component.css'
})
export class DietaUsuarioComponent {
  constructor(private usuarioService: UsuariosService){

  }

  get nombre(){
    return this.usuarioService.getUsuarioSesion()?.nombre;
  }
}
