import {message, Card, Avatar, Button, Row, Col } from 'antd';
import { ArrowLeftOutlined,LogoutOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';
import userBack from '../img/user-back.png';
import avataric from '../img/cat.png';
import '../css/UserInfoPage.css';
import {useSelector, useDispatch} from "react-redux";
import AuthService from "../services/auth.service";
import UserService from "../services/userService";
import React, {useEffect, useState} from "react";
const UserInfoPage = () => {
    const dispatch = useDispatch();
    // Данные пользователя, которые нужно отобразить на странице
    const user = JSON.parse(localStorage.getItem("user"));
    const userId = useSelector((state) => state.user.user.id);

    useEffect(() => {
        UserService.getUser(userId, dispatch);
    }, []);

    const handleLogout = () => {
        AuthService.logout();
        message.success("Вы успешно вышли! До свидания!")
    };

    return (
        <div className="user-page-container" style={{ backgroundImage: `url(${userBack})` }}>
            <div className="user-card">
                <Card style={{ width: 450, height: 300, borderWidth: '3.5px', borderColor: '#4d789d' }} className="user-info-card">
                    <Row style={{marginLeft: 30 }}>
                        <Col span={8}>
                            <div>
                                <h2>{user.username}</h2>
                                <p>{user.email}</p>
                                <Link to="/task">
                                    <Button type="primary" icon={< ArrowLeftOutlined/>} style={{ backgroundColor:'#2d5a7e', color:"white", marginLeft: 40, marginTop: 20}}>
                                        Назад
                                    </Button>
                                </Link>
                            </div>
                        </Col>
                        <Col span={16}>
                            <div style={{ textAlign: 'center' }}>
                                <img className="avatar" src={avataric} />
                                <Button  type="primary" icon={<LogoutOutlined />} style={{ backgroundColor:'#2d5a7e', color:"white", marginLeft: 40, marginTop: 24}}>
                                    Выход
                                </Button>
                            </div>
                        </Col>
                    </Row>
                </Card>
            </div>
        </div>
    );
};

export default UserInfoPage;