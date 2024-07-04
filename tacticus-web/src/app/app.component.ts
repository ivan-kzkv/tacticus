import { Component } from '@angular/core';
import {TacticalFieldComponent} from './components/tactical-field/tactical-field.component';
import {Store} from '@ngrx/store';
import {selectTheme} from './store/app-state/app-state.selector';
import {AppStateActions} from './store/app-state/app-state.action';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TacticalFieldComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  constructor(private readonly store: Store) {
    this.store.select(selectTheme)
      .subscribe(theme => console.log(theme));

    setTimeout(() => {
      this.store.dispatch(AppStateActions.setTheme({theme: 'dark'}));
    },1000)
  }
}
