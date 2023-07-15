import React from 'react';
import {Button, Card, Checkbox, Form, Input} from 'antd';
import ButtonGroup from "./ButtonGroup";
import '../css/RegistrationPage.css';
import { EyeTwoTone, EyeInvisibleOutlined } from '@ant-design/icons';
import authService from "../services/auth.service";
const RegistrationCart = () => {
    const [form] = Form.useForm();
    const onFinish = (values) => {
        authService.register(values)
    };

    return (
        <Card style={{width: 450, height: 300,  borderColor: '#1c4567', borderWidth:2 }} className="registration-page-form">
            <Form
                form={form}
                name="register"
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 16,
                }}
                style={{
                    maxWidth: 600,
                    marginLeft: 39,
                }}
                initialValues={{
                    remember: true,
                }}

                onFinish={onFinish}
                autoComplete="off"
                scrollToFirstError
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

                >
                    <Input className="registration-string"/>
                </Form.Item>

                <Form.Item
                    label="Почта"
                    name="email"
                    rules={[
                        {
                            required: true,
                            message: 'Заполните поле!',
                        },
                        {
                            type: 'email',
                            message: 'Некорректный email'
                        }
                    ]}
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
                    hasFeedback
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
                    <Button type="primary" htmlType="submit"
                            style={{width: 180, backgroundColor: '#1c4567', color: "white"}}>
                        Зарегистрироваться
                    </Button>
                </Form.Item>
            </Form>
        </Card>
    );
};
export default RegistrationCart;