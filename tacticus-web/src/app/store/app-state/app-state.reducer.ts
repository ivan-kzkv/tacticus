import {AppState, AppStateActions, AppTheme} from './app-state.action';
import {createReducer, on} from '@ngrx/store';

export const initialState: AppState  = {theme: 'light'};

export const AppStateReducer = createReducer(
  initialState,
  on(AppStateActions.setTheme, (state: AppState, {theme}) => ({...state, theme}))
);
