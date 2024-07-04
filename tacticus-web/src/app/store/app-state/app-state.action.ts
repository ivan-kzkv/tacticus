import {createActionGroup, props} from '@ngrx/store';

export interface AppState {
  theme: AppTheme
}

export type AppTheme = 'light' | 'dark';

export const AppStateActions = createActionGroup({
  source: 'AppState',
  events: {
    'Set Theme': props<{theme: AppTheme}>(),
  }
});
