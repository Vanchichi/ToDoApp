import React from 'react';
import {Button, Card, Checkbox, Form, Input} from 'antd';
import ButtonGroup from "./ButtonGroup";
import '../css/RegistrationPage.css';
import {EyeInvisibleOutlined, EyeTwoTone} from "@ant-design/icons";
//npm install @ant-design/icons --save
//ДЛЯ УСТАНОВКИ
const onFinish = (values) => {
    console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};
const LoginCart = () => (
    <Card style={{width:450, height:300, borderColor: '#0749a3' }} className="registration-page-form">
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
            style={{marginTop:25,}}
        >
            <Input className="registration-string"/>
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
            style={{marginBottom: 40, marginTop:40,}}
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
            <Button  htmlType="submit" style={{width:120, backgroundColor:'#0a59c0', color:"white"}} >
                Вход
            </Button >
        </Form.Item>
    </Form>
    </Card>
);
export default LoginCart;