import { Component } from '@angular/core';
import { Dieta } from '../entities/dieta';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UsuariosService } from '../services/usuarios.service';
import { Usuario } from '../entities/usuario';

@Component({
  selector: 'app-formulario-dieta',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './formulario-dieta.component.html',
  styleUrl: './formulario-dieta.component.css'
})
export class FormularioDietaComponent {
  usuariosConRolCliente: Usuario[] = [];
  constructor(public modal: NgbActiveModal, private usuarioService: UsuariosService) {}

  ngOnInit(): void {
    this.usuarioService.getUsuariosConRolCliente().subscribe(
      usuarios => {
        this.usuariosConRolCliente = usuarios;
      }
    );
  }
  accion?: "Añadir" | "Editar";
  dieta : Dieta = { nombre: '', descripcion: '', observaciones:'', objetivo:'', duracionDias: 0, 
    alimentos: [], recomendaciones:'', id: 0, usuarioId:0, creadorId:this.usuarioService.getUsuarioSesion()?.id }


  guardarDieta(): void {
    this.modal.close(this.dieta);
  }

}
