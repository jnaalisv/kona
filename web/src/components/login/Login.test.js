import React from 'react'
import {render } from 'react-dom'
import Login from './Login'

test('Login renders without crashing', () => {
    const div = document.createElement('div');
    render(<Login />, div);
});