import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AppState} from './app-state.action';

export const selectAppState = createFeatureSelector<AppState>('appState');

export const selectTheme = createSelector(
  selectAppState,
  (state: AppState) => state.theme
);
