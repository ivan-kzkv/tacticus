import { Component } from '@angular/core';
import {TacticalFieldComponent} from './components/tactical-field/tactical-field.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TacticalFieldComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'tacticus-w';
}
