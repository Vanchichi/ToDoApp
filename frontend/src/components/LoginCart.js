import React from 'react';
import {Button, Card, Checkbox, Form, Input} from 'antd';
import ButtonGroup from "./ButtonGroup";
import '../css/RegistrationPage.css';
import authService from "../services/auth.service";
import {useDispatch} from "react-redux";
import {login} from "../slices/authSlice";
import {useNavigate} from "react-router-dom";
import {EyeInvisibleOutlined, EyeTwoTone} from "@ant-design/icons";


const LoginCart = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onFinish = (values) => {
        authService.login(values).then((user) => {
            console.log(user)
            dispatch(login(user))
            navigate("/task")
        })
    };
    return(
    <Card style={{width: 450, height: 300, borderColor: '#1c4567', borderWidth:2 }} className="registration-page-form">
        <Form
            name="basic"
            labelCol={{
                span: 8,
            }}
            wrapperCol={{
                span: 16,
            }}
            style={{
                maxWidth: 600,
                marginLeft: 37,
            }}
            initialValues={{
                remember: true,
            }}
            onFinish={onFinish}
            autoComplete="off"
        >
            <ButtonGroup/>
            <Form.Item
                label="Логин"
                name="username"
                rules={[
                    {
                        required: true,
                        message: 'Заполните поле!',
                    },
                ]}
                style={{marginTop: 25,}}
            >
                <Input className="registration-string"/>
            </Form.Item>

            <Form.Item
                label="Пароль"
                name="password"
                rules={[
                    {
                        required: true,
                        message: 'Заполните поле!',
                    },
                ]}
                style={{marginBottom: 40, marginTop: 40,}}
            >
                <Input.Password
                    style={{backgroundColor: "#e5eaf6"}}
                    inputStyle={{backgroundColor: "#e5eaf6"}}
                    className="password-input"
                    iconRender={visible => (visible ? <EyeTwoTone/> : <EyeInvisibleOutlined/>)}
                />
            </Form.Item>

            <Form.Item
                wrapperCol={{
                    offset: 8,
                    span: 16,
                }}
            >
                <Button htmlType="submit" style={{width: 180, backgroundColor: '#1c4567', color: "white"}}>
                    Войти
                </Button>
            </Form.Item>
        </Form>
    </Card>
)
};
export default LoginCart;