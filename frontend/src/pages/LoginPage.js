import LoginCart from "../components/LoginCart";
import transparentLogo from '../css/rei.png';
import '../css/RegistrationPage.css';
import React from "react";

const LoginPage = () => (
    <div className="registration-page-container">
        <div className="registration-page-card">
            <LoginCart />
        </div>
        <div className="registration-page-text">
            <h1>Planify</h1>
            <h2>Организуй свою жизнь с легкостью - начни с Planify!</h2>
            <img src={transparentLogo} alt="Logo" />
        </div>
    </div>
);

export default LoginPage;