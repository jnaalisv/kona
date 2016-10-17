import React from 'react';
import {shallow} from 'enzyme';
import LoginPage from './LoginPage';

it('LoginPage calls login after click', () => {

    const loginMock = jest.fn();

    const loginPage = shallow(<LoginPage login={loginMock} />);
});