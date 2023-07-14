import React, { useState, useEffect } from 'react';
import { Radio } from 'antd';
import { Link, useLocation } from 'react-router-dom';
import '../css/RegistrationPage.css';

const ButtonGroup = () => {
    const [activeButton, setActiveButton] = useState('login');
    const location = useLocation();

    useEffect(() => {
        if (location.pathname === '/registration') {
            setActiveButton('registration');
        } else {
            setActiveButton('login');
        }
    }, [location]);

    const handleButtonClick = (url, buttonType) => {
        window.location.assign(url);
        setActiveButton(buttonType);
    };

    return (
        <>
            <Radio.Group defaultValue="login" buttonStyle="default" style={{ marginBottom: 20, marginLeft: 95 }}>
                <Radio.Button
                    value="registration"
                    onClick={() => handleButtonClick('/api/auth/signup', 'registration')}
                    className={activeButton === 'registration' ? 'reg-button active' : 'reg-button'}
                >
                    Регистрация
                </Radio.Button>
                <Radio.Button
                    value="login"
                    onClick={() => handleButtonClick('/api/auth/signin', 'login')}
                    className={activeButton === 'login' ? 'reg-button active' : 'reg-button'}
                >
                    Вход
                </Radio.Button>
            </Radio.Group>
        </>
    );
};

export default ButtonGroup;