import React from 'react';
import {shallow} from 'enzyme';
import LoginPage from './LoginPage';


describe('LoginPage', () => {

    it('contains two inputs and a button', () => {
        const loginMockFunction = jest.fn();

        const loginPage = shallow(<LoginPage login={loginMockFunction} />);

        const usernameInput = loginPage.find('input').at(0);
        expect(usernameInput).toBeDefined();

        const passwordInput = loginPage.find('input').at(1);
        expect(passwordInput).toBeDefined();

        const submitButton = loginPage.find('button');
        expect(submitButton).toBeDefined();

    });

});

