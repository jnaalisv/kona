import React from 'react';
import ReactDOM from 'react-dom';
import KonaWebApp from './KonaWebApp';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<KonaWebApp />, div);
});
