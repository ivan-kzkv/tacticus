import { Component } from '@angular/core';
import { MapComponent } from '@maplibre/ngx-maplibre-gl';
import {ThemeSwitcherComponent} from '../theme-switcher/theme-switcher.component';

@Component({
  selector: 'app-map-component',
  standalone: true,
  imports: [MapComponent, ThemeSwitcherComponent],
  templateUrl: './tactical-field.component.html',
  styleUrl: './tactical-field.component.scss'
})
export class TacticalFieldComponent {

}
