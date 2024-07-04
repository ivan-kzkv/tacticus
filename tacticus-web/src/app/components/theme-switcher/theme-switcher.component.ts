import { Component } from '@angular/core';
import {ToggleButtonComponent} from '../shared/toggle-button/toggle-button.component';
import {Store} from '@ngrx/store';
import {map, Observable} from 'rxjs';
import {selectTheme} from '../../store/app-state/app-state.selector';
import {AppStateActions} from '../../store/app-state/app-state.action';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-theme-switcher',
  standalone: true,
  imports: [ToggleButtonComponent, CommonModule],
  templateUrl: './theme-switcher.component.html',
  styleUrl: './theme-switcher.component.scss'
})
export class ThemeSwitcherComponent {

  constructor(private readonly store: Store) {
  }

  get isBlackTheme$(): Observable<boolean> {
    return this.store.select(selectTheme).pipe(map(theme => theme === 'dark'));

  }

  handleToggleChangeState(isChecked: boolean) {
    const theme = isChecked ? 'dark' : 'light';
    localStorage.setItem('theme', theme);
    this.store.dispatch(AppStateActions.setTheme({theme}));
  }
}
