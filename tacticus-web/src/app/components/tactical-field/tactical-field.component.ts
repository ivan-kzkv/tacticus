import { Component } from '@angular/core';
import { MapComponent } from '@maplibre/ngx-maplibre-gl';
import {ToggleButtonComponent} from '../shared/toggle-button/toggle-button.component';

@Component({
  selector: 'app-map-component',
  standalone: true,
  imports: [MapComponent, ToggleButtonComponent],
  templateUrl: './tactical-field.component.html',
  styleUrl: './tactical-field.component.scss'
})
export class TacticalFieldComponent {

}
