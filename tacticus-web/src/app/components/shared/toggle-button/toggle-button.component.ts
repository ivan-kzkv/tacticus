import {Component, Input, output} from '@angular/core';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-toggle-button',
  standalone: true,
  imports: [NgStyle],
  templateUrl: './toggle-button.component.html',
  styleUrl: './toggle-button.component.scss'
})
export class ToggleButtonComponent {
  @Input() buttonConfigurations: {size?: number, bgColor?: string, bgColorChecked?: string, circleColor?: string, circleColorChecked?: string} | undefined;
  @Input() toggleState: boolean | null = null;
  toggleStateChanges= output<boolean>({alias: 'onToggleChange'});


  handleCheckedEvent(event: Event) {
    this.toggleState = (event.target as HTMLInputElement).checked;
    this.toggleStateChanges.emit(this.toggleState);
  }

  getCheckboxStyles() {
      return {
        '--size': this.buttonConfigurations?.size && `${this.buttonConfigurations?.size}px`,
        '--bgColor': this.buttonConfigurations?.bgColor,
        '--bgColorChecked': this.buttonConfigurations?.bgColorChecked,
        '--circleColor': this.buttonConfigurations?.circleColor,
        '--circleColorChecked': this.buttonConfigurations?.circleColorChecked
      };
  }
}
