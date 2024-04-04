import { Component } from '@angular/core';
import { CommonModule, TitleCasePipe } from '@angular/common';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { NgbNavModule, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { DietasComponent } from './dietas/dietas.component';
import { InicioComponent } from './inicio/inicio.component';
import { UsuariosService } from './services/usuarios.service';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { ListadoUsuarioComponent } from './listado-usuario/listado-usuario.component';
import { DietaUsuarioComponent } from './dieta-usuario/dieta-usuario.component'
import { Rol } from './entities/login';
import { DietaEntrenadorComponent } from './dieta-entrenador/dieta-entrenador.component'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, NgbNavModule, NgbDropdownModule, DietasComponent, CommonModule, InicioComponent, 
    RouterLink, FormsModule, TitleCasePipe, ListadoUsuarioComponent, DietaUsuarioComponent, DietaEntrenadorComponent], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'YadosFitness';
  active = 1;
  _rolIndex: number = 0;
  mostrarVentana: boolean = false;
  constructor(private usuarioService: UsuariosService, private router: Router) {
    this.actualizarRol()
  }

  get rolIndex() {
    return this._rolIndex;
  }

  set rolIndex(i: number) {
    this._rolIndex = i;
    this.actualizarRol();
  }

  actualizarRol() {
    let u = this.usuarioSesion;
    if (u) {
      this.usuarioService.rolCentro = u.roles[this.rolIndex];
    } else {
      this.usuarioService.rolCentro = undefined;
    }
  }

  get rol() {
    return this.usuarioService.rolCentro;
  }

  get usuarioSesion() {
    return this.usuarioService.getUsuarioSesion();
  }
  mostrarLogin() {
  this.mostrarVentana = true;
}
  logout() {
    this.usuarioService.doLogout();
    this.actualizarRol();
    this.router.navigateByUrl('/login');
  }

  isAdmin() : boolean {
    return this.usuarioService._rolCentro?.rol == Rol.ADMINISTRADOR;
  }

}



