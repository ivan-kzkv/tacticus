import { Injectable } from '@angular/core';
import {Store} from '@ngrx/store';
import {selectTheme} from '../store/app-state/app-state.selector';

@Injectable({
  providedIn: 'root'
})
export class AppThemeService {

  constructor(private store: Store) {

  }

  handleThemeChange(): void {
    this.store.select(selectTheme)
      .subscribe(theme => {
        theme !== 'light' ? document.body.classList.add('dark-theme') : document.body.classList.remove('dark-theme');
      });
  }
}
