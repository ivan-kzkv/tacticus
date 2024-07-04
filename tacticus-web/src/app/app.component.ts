import {Component, OnInit} from '@angular/core';
import {TacticalFieldComponent} from './components/tactical-field/tactical-field.component';
import {Store} from '@ngrx/store';
import {AppStateActions, AppTheme} from './store/app-state/app-state.action';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TacticalFieldComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  constructor(private readonly store: Store) {
  }

  ngOnInit() {
      this.store.dispatch(AppStateActions.setTheme({theme: localStorage.getItem('theme') as AppTheme || 'light'}));
  }
}
