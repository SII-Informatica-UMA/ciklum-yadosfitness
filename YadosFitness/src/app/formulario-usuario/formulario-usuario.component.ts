import { Component } from '@angular/core';
import { Usuario, UsuarioImpl } from '../entities/usuario';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BackendFakeService } from '../services/backend.fake.service';
import { Dieta } from '../entities/dieta';

@Component({
  selector: 'app-formulario-usuario',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './formulario-usuario.component.html',
  styleUrl: './formulario-usuario.component.css'
})
export class FormularioUsuarioComponent {
  accion?: "Añadir" | "Editar";
  _usuario: Usuario = new UsuarioImpl();
  usuarios: Usuario[] = [];
  dietas: Dieta[] = [];
  usuarioSeleccionadoId: number=0;
  rpassword: string = '';
  error: string = '';

  constructor(public modal: NgbActiveModal, private backendService: BackendFakeService) { }

  ngOnInit(): void {
    // Suscribirse al Observable para obtener usuarios
    this.backendService.getUsuarios().subscribe(usuarios => {
      this.usuarios = usuarios;
    });

    // Suscribirse al Observable para obtener dietas
    this.backendService.getDietas().subscribe(dietas => {
      this.dietas = dietas;
    });
  }
  seleccionarUsuario(usuarioId: number): void {
    this.usuarioSeleccionadoId = usuarioId;
  }

  get usuario () {
    return this._usuario;
  }

  set usuario(u: Usuario) {
    this._usuario = u;
    this._usuario.password='';
  }

  guardarUsuario(): void {
    if (this._usuario.password != this.rpassword) {
      this.error="Las contraseñas no coinciden";
      return;
    }

    this.modal.close(this.usuario);
  }
}
