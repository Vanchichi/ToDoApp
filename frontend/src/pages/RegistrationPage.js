import React from 'react';
import { Radio } from 'antd';
import { Link } from 'react-router-dom';
import RegistrationCart from '../components/RegistrationCart';
import transparentLogo from '../img/rei.png';
import '../css/RegistrationPage.css';

const RegistrationPage = () => (
    <div className="registration-page-container">
        <div className="registration-page-card">
            <RegistrationCart />
        </div>
        <div className="registration-page-text">
            <h1>Planify</h1>
            <h2>Организуй свою жизнь с легкостью - начни с Planify!</h2>
            <img src={transparentLogo} alt="Logo" />
        </div>
    </div>
);

export default RegistrationPage;