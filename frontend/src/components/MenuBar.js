import { useState } from 'react';
import {Layout, Menu, Input, Button} from 'antd';
import {
    SearchOutlined,
    UserOutlined,
    FileOutlined,
    DeleteOutlined,
    NotificationOutlined,
    BulbOutlined,
    MenuOutlined
} from '@ant-design/icons';
import { Link, useLocation } from 'react-router-dom';
import '../css/TaskPage.css';
import taskBack from '../img/task-back.png';
import secretImg from '../img/secret.png';

const { Header, Content, Sider } = Layout;

const MenuBar = () => {
    const [searchValue, setSearchValue] = useState('');
    const location = useLocation();
    const [collapsed, setCollapsed] = useState(true);
    const handleSearch = (value) => {
        setSearchValue(value);
    };

    const toggleSidebar = () => {
        setCollapsed(!collapsed);
    };

    const handleMenuClick = () => {
        if (collapsed) {
            setCollapsed(false);
        }
    };
    const renderSecretImages = () => {
        const secretImages = [];
        for (let i = 0; i < 17; i++) {
            secretImages.push(<img key={i} src={secretImg} alt="Secret" style={{ marginLeft: i === 0 ? 20 : 20, width: 34, height: 34 }} />);
        }
        return secretImages;
    };

    return (
        <Layout style={{ minHeight: '500vh', background: '#EFEADCFF', backgroundImage: `url(${taskBack})`, backgroundSize: 'cover' }}>
            <div style={{ }}></div>
            <Header style={{
                background: '#23496c',
                padding: '0 24px',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                position: 'fixed',
                width: '100%',
                zIndex: 1,
            }}>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <Button
                        type="text"
                        style={{color: '#EFEADCFF', marginRight: '16px'}}
                        onClick={toggleSidebar}
                        icon={<MenuOutlined/>}/>
                    <h1 style={{ color: '#EFEADCFF',  fontFamily: 'Roboto', fontSize: '34px' }}>Planify</h1>
                    {searchValue === '/\\//\\//\\//\\//\\/FLOWER\//\\//\\//\\//\\/' && <div style={{ display: 'flex' }}>{renderSecretImages()}</div>}
                </div>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <Input
                        className="search-input"
                        inputStyle={{backgroundColor: "#EFEADCFF"}}
                        style={{ width: '300px', borderRadius: '20px', backgroundColor: '#EFEADCFF', marginRight: '50px' }}
                        placeholder="Поиск задач..."
                        prefix={<SearchOutlined />}
                        value={searchValue}
                        onChange={(e) => handleSearch(e.target.value)}
                    />
                </div>
            </Header>
            <Layout style={{ marginTop: '64px', background: 'transparent' }}>
                <Sider
                    theme="dark"
                    collapsible
                    collapsed={collapsed}
                    onCollapse={toggleSidebar}
                    width={200}
                    style={{
                        overflow: 'auto',
                        height: '100vh',
                        position: 'fixed',
                        left: 0,
                        background: '#23496c'
                    }}
                >
                    <Menu
                        style={{ background: '#23496c' }}
                        theme="dark"
                        mode="inline"
                        selectedKeys={[location.pathname]}
                        onClick={handleMenuClick}
                    >
                        <Menu.Item key="/user" icon={<UserOutlined />}>
                            <Link to="/user">Профиль</Link>
                        </Menu.Item>
                        <Menu.Item key="/task" icon={<BulbOutlined />}>
                            <Link to="/task">Задачи</Link>
                        </Menu.Item>
                        <Menu.Item key="/archive" icon={<DeleteOutlined />}>
                            <Link to="/archive">Архив</Link>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout style={{
                    marginLeft: 200,
                    padding: '0 24px 24px',
                    background: 'transparent'
                }}>
                    <Content style={{
                        margin: '24px 0',
                        padding: 24,
                        height: '100%'
                    }}>
                        {/* addyour content here */}
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    );
};

export default MenuBar;