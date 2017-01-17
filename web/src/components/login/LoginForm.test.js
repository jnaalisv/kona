import React from 'react';
import {shallow, mount} from 'enzyme';
import LoginForm from './LoginForm';

describe('LoginForm', () => {

    it('contains two inputs and a button', () => {
        const loginMockFunction = jest.fn();

        const wrapper = shallow(<LoginForm login={loginMockFunction} />);

        const usernameInput = wrapper.find('input').at(0);
        expect(usernameInput).toBeDefined();

        const passwordInput = wrapper.find('input').at(1);
        expect(passwordInput).toBeDefined();

        const submitButton = wrapper.find('button');
        expect(submitButton).toBeDefined();

    });

    it('LoginForm calls login after click', () => {

        const loginMockFunction = jest.fn();

        const loginPage = shallow(<LoginForm login={loginMockFunction} />);

        const usernameInput = loginPage.find('input').at(0);
        const passwordInput = loginPage.find('input').at(1);

        usernameInput.simulate('change', { target: { name: 'username', value: 'admin'} });
        passwordInput.simulate('change', { target: { name: 'password', value: '4dminPa55'} });

        loginPage.find('form').simulate('submit', { preventDefault() {} });

        expect(loginMockFunction.mock.calls.length).toBe(1);
        expect(loginMockFunction.mock.calls[0][0]).toBe('admin');
        expect(loginMockFunction.mock.calls[0][1]).toBe('4dminPa55');
    });
});

