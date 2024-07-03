import { Component } from '@angular/core';
import { MapComponent } from '@maplibre/ngx-maplibre-gl';

@Component({
  selector: 'app-map-component',
  standalone: true,
  imports: [MapComponent],
  templateUrl: './tactical-field.component.html',
  styleUrl: './tactical-field.component.scss'
})
export class TacticalFieldComponent {

}
