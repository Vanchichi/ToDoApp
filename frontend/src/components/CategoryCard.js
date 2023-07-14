import React, {useEffect, useState} from "react";
import {Card, Button, Input, Popconfirm, Space, ColorPicker, Row, Col, message} from "antd";
import {EditOutlined, PlusOutlined, DeleteOutlined} from "@ant-design/icons";
import TaskCard from "./TaskCard";
import CategoryService from "../services/categoryService";
import {useDispatch, useSelector} from "react-redux";
import taskService from "../services/taskService";

export const CategoryCard = ({category}) => {
    const [editedTitle, setEditedTitle] = useState(category.name);
    const [isEditing, setIsEditing] = useState(false);
    const [cardColor, setCardColor] = useState("#4d789d");
    const [tasks, setTasks] = useState([]);
    const dispatch = useDispatch();
    const categories = useSelector((state) => state.categories.categories);
    const handleCategoryTitleEdit = (e) => {
        setEditedTitle(e.target.value);
    };
    const handleDeleteTask = (taskId, categoryId) => {
        const user = JSON.parse(localStorage.getItem("user"));
        const currentUserId = user ? user.id : null;
        if (currentUserId) {
            taskService
                .deleteTask(taskId, categoryId, dispatch)
                .then(() => {
                    message.success("Задача успешно удалена!");
                    taskService.getAllTasks(dispatch);
                })
                .catch((error) => {
                    console.error(error);
                    message.error("Не удалось удалить задачу.");
                });
        }
    };
    const handleEditClick = () => {
        setIsEditing(true);
    };

    const handleTitleChange = () => {
        setIsEditing(false);
        const updatedCategory = {...category, name: editedTitle};
        CategoryService.updateCategory(category.id, updatedCategory, dispatch)
            .then(() => {
                message.success("Имя категории изменено!")
                CategoryService.getCategories(dispatch);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const handleTitleCancel = () => {
        setIsEditing(false);
        setEditedTitle(category.name);
    };

    const handleColorChange = (color) => {
        setCardColor(color.rgb);
    };

    const handleAddTask = (categoryId) => {
        const user = JSON.parse(localStorage.getItem("user"));
        const currentUserId = user ? user.id : null;
        if (currentUserId) {
            const newTask = {

                title: `Новая задача`,
                status: false,
                archive: false,
                category_id: categoryId
            };
            taskService
                .createTask(categoryId, newTask, dispatch)
                .then(() => {
                    message.success("Задача успешно добавлена!");
                    taskService.getAllTasks(dispatch);
                })
                .catch((error) => {
                    console.error(error);
                    message.error("Не удалось добавить задачу.");
                });
            setTasks([...tasks, newTask]);
        }
    };

    const handleDeleteCategory = (categoryId) => {
        CategoryService.deleteCategory(category.id, dispatch)
            .then(() => {
                console.log("Категория удалена!")
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return (
        <Card
            key={category.id}
            title={
                <div style={{display: "flex", alignItems: "center", color: "#EFEADCFF", fontFamily: 'Roboto', fontSize: '30px', borderWidth: '3.5px', borderColor: "#27425f"}}>

                     {" "}
                    {isEditing ? (
                        <Input
                            value={editedTitle}
                            onChange={handleCategoryTitleEdit}
                            onPressEnter={handleTitleChange}
                            onBlur={handleTitleCancel}
                            autoFocus
                        />
                    ) : (
                        category.name
                    )}
                    <Button type="primary"  icon={ <EditOutlined/>} style={{marginLeft: 30,marginRight: "8px",backgroundColor: '#2D5A7EFF' }}
                            onClick={handleEditClick}/>

                        <Button type="primary"  icon={<DeleteOutlined/>} style={{marginLeft: 10,backgroundColor: '#2D5A7EFF'}}
                                onClick={() => handleDeleteCategory(category.id)}/>

                </div>
            }

            style={{width: "850px", marginBottom: "16px", backgroundColor: cardColor, borderWidth: '3px', borderColor: "#27425f", }}
            hoverable
        >
            <Space>
                <Button
                    style={{backgroundColor: "#c7d1d0", color: "#27425f",marginLeft: 330}}
                    type="primary"
                    onClick={() => handleAddTask(category.id)}
                >
                    Добавить задачу
                </Button>

            </Space>
            <div style={{marginTop: "16px"}}>

                    {tasks.map((task) => (
                            <TaskCard task={task}/>

                    ))}
            </div>
        </Card>
    );
};

export default CategoryCard;