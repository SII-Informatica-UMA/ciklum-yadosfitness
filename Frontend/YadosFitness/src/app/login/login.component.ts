import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Login } from '../entities/login';
import { UsuariosService } from '../services/usuarios.service';
import { Router } from '@angular/router';
import { Output, EventEmitter } from '@angular/core';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginInfo: Login = {email: '', password: ''};
  error: string = '';
  @Output() cerrar = new EventEmitter<void>();
  constructor(private usuarioService: UsuariosService, private router: Router) {}

  login() {
    this.usuarioService.doLogin(this.loginInfo).subscribe({
      next: (usuario) => {
        this.router.navigateByUrl('/');
        this.cerrar.emit();
      },
      error: (error) => {
        this.loginInfo = {email: '', password: ''};
        if (error.status === 401) {
          this.error = 'Usuario o contraseña incorrectos';
        } else {
          this.error = error.statusText;
        }

      }
    });
  }

  get usuario() {
    return this.usuarioService.getUsuarioSesion();
  }

}
