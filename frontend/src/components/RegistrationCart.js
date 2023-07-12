import React from 'react';
import {Button, Card, Checkbox, Form, Input} from 'antd';
import ButtonGroup from "./ButtonGroup";
import '../css/RegistrationPage.css';
import { EyeTwoTone, EyeInvisibleOutlined } from '@ant-design/icons';
const onFinish = (values) => {
    console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};
const RegistrationCart = () => (
    <Card style={{width:450, height:300, borderColor: '#0749a3'}} className="registration-page-form">
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
            marginLeft: 39,
        }}
        initialValues={{
            remember: true,
        }}

        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"

    >
        <ButtonGroup/>
        <Form.Item
            label="Username"
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
            label="Email"
            name="email"
            rules={[
                {
                    required: true,
                    message: 'Заполните поле!',
                },
            ]}
        >
            <Input  className="registration-string"/>
        </Form.Item>
        <Form.Item
            label="Password"
            name="password"
            rules={[
                {
                    required: true,
                    message: 'Заполните поле!',
                },
            ]}
        >

            <Input.Password
                style={{ backgroundColor: "#e5ecff" }}
                inputStyle={{ backgroundColor: "#e5ecff" }}
                className="password-input"
                iconRender={visible => (visible ? <EyeTwoTone /> : <EyeInvisibleOutlined />)}
            />
        </Form.Item>

        <Form.Item
            wrapperCol={{
                offset: 8,
                span: 16,
            }}
        >
            <Button type="primary" htmlType="submit" style={{width:120, backgroundColor:'#0a59c0', color:"white"}}>
                Регистрация
            </Button>
        </Form.Item>
    </Form>
    </Card>
);
export default RegistrationCart;