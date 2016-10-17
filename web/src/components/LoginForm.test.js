import React from 'react';
import {shallow, mount} from 'enzyme';
import LoginForm from './LoginForm';


describe('LoginForm', () => {

    it('contains two inputs and a button', () => {
        const loginMockFunction = jest.fn();

        const loginPage = shallow(<LoginForm login={loginMockFunction} />);

        const usernameInput = loginPage.find('input').at(0);
        expect(usernameInput).toBeDefined();

        const passwordInput = loginPage.find('input').at(1);
        expect(passwordInput).toBeDefined();

        const submitButton = loginPage.find('button');
        expect(submitButton).toBeDefined();

    });


    // shallow-render it, and assert on the presence of the two inputs and the button
    // shallow-render it, and assert that the root is a <form> with an "onSubmit" prop that has a function
    // it('LoginForm calls login after click', () => {
    //
    //     const loginMockFunction = jest.fn();
    //
    //     const loginPage = shallow(<LoginForm login={loginMockFunction} />);
    //
    //     const usernameInput = loginPage.find('input').at(0);
    //     const passwordInput = loginPage.find('input').at(1);
    //
    //     usernameInput.simulate('change', { target: { value: 'admin' } });
    //     passwordInput.simulate('change', { target: { value: 'salasana' } });
    //
    //     const form = loginPage.find('form').at(0);
    //     const children = form.render().children().children();
    //     form.simulate('submit', { target: { children }, preventDefault() {} });
    //
    //     //loginPage.simulate('submit');
    //     //loginPage.find('button').simulate('click');
    //
    //     expect(loginMockFunction.mock.calls.length).toBe(1);
    //     expect(loginMockFunction.mock.calls[0][0]).toBe('first arg');
    // });
});

