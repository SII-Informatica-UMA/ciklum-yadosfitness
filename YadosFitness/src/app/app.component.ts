import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NgbNavModule, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgbNavModule, NgbDropdownModule], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'YadosFitness';
  active = 1;
}


