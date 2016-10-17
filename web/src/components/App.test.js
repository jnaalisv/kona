import React from 'react'
import {render } from 'react-dom'
import App from './App'

test('App renders without crashing', () => {
    const div = document.createElement('div');
    render(<App />, div);
});