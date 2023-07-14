import React, {useEffect, useState} from "react";
import {
    Card,
    Button,
    Input,
    Checkbox,
    Tooltip,
    Dropdown,
    Menu,
    DatePicker,
    TimePicker,
    message
} from "antd";
import {
    EditOutlined,
    DeleteOutlined,
    SyncOutlined,

    CalendarOutlined, ExclamationOutlined,
} from "@ant-design/icons";
import {useDispatch, useSelector} from "react-redux";
import taskService from "../services/taskService";
import '../css/TaskPage.css';
const {RangePicker} = TimePicker;

const TaskCard = ({task, handleDeleteTask, handleArchiveTask, selectedTask}) => {
    const [editedTitle, setEditedTitle] = useState(task.title);
    const [isEditing, setIsEditing] = useState(false);
    const [editedDescription, setEditedDescription] = useState(task.description);
    const [showNotificationForm, setShowNotificationForm] = useState(false);
    const [showRegularityMenu, setShowRegularityMenu] = useState(false);
    const [showPriorityMenu, setShowPriorityMenu] = useState(false);
    const [notificationDate, setNotificationDate] = useState(null);
    const [notificationTime, setNotificationTime] = useState(null);
    const [selectedRegularity, setSelectedRegularity] = useState("не выставлена");
    const [selectedReminder] = useState("не выставлено");
    const [selectedPriority, setSelectedPriority] = useState("не выставлен");
    const [isDescriptionEditing, setIsDescriptionEditing] = useState(false);
    const [isCompleted, setIsCompleted] = useState(task.completed);
    const dispatch = useDispatch();
    const statuses = useSelector((state) => state.tasks.statuses);
    const regularities = useSelector((state) => state.tasks.regularities);
    const priorities = useSelector((state) => state.tasks.priorities);

    useEffect(() => {
        taskService.getStatuses(dispatch)
        taskService.getPriorities(dispatch)
        taskService.getRegularities(dispatch)
    }, [selectedTask]);

    const handleTaskTitleEdit = (e) => {
        setEditedTitle(e.target.value);
    };

    const handleDescriptionEdit = (e) => {
        setEditedDescription(e.target.value);
    };
    const updateTaskTitle = (taskId, editedTitle) => {
        const updatedTask = { ...task, title: editedTitle };
        taskService
            .updateTask(task.category_id, updatedTask, dispatch)
            .then(() => {
                taskService.getAllTasks(dispatch);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    const updateTaskDescription = (taskId, editedDescription) => {
        const updatedTask = { ...task, description: editedDescription };
        taskService
            .updateTask(task.category_id, updatedTask, dispatch)
            .then(() => {
                taskService.getAllTasks(dispatch);
            })
            .catch((error) => {
                console.error(error);
            });
    };
    const handleDescriptionSave = () => {
        setIsDescriptionEditing(false);
        updateTaskDescription(task.id, editedDescription);
    };

    const handleEditClick = () => {
        setIsEditing(true);
    };

    const handleTitleSave = () => {
        if (editedTitle.trim() !== "") {
            setIsEditing(false);
            updateTaskTitle(task.id, editedTitle);
        }
    };

    const handleTitleCancel = () => {
        setIsEditing(false);
        setEditedTitle(task.title);
    };

    const handleArchiveClick = () => {
        handleArchiveTask(task.id);
    };

    const handleDeleteClick = () => {
        handleDeleteTask(task.id);
    };

    const handleCheckboxChange = (e) => {
        setIsCompleted(e.target.checked);
        if (e.target.checked) {
            message.success("Задача выполнена");
        }
    };

    const handleNotificationClick = () => {
        setShowNotificationForm(!showNotificationForm);
    };

    const handleRegularityClick = () => {
        setShowRegularityMenu(!showRegularityMenu);
    };

    const handlePriorityClick = () => {
        setShowPriorityMenu(!showPriorityMenu);
    }

    const handleNotificationDateChange = (date, dateString) => {
        setNotificationDate(dateString);
    };

    const handleNotificationTimeChange = (time, timeString) => {
        setNotificationTime(timeString);
    };

    const handleNotificationSave = () => {
        console.log("Notification Date:", notificationDate);
        console.log("Notification Time:", notificationTime);
    };

    const handleNotificationCancel = () => {
        setNotificationDate(null);
        setNotificationTime(null);
        setShowNotificationForm(false);
    };

    const handleRegularitySelect = (e) => {
        setSelectedRegularity(e.item.props.children);
    };

    const handlePrioritySelect = (e) => {
        setSelectedPriority(e.item.props.children);
    };

    const handleEditTask = (taskId, editedTitle) => {
        console.log("handleEditTask:", taskId, editedTitle);
    };

    const menuRegularity = (
        <Menu onClick={handleRegularitySelect}>
            {regularities.map((regularity) => (
                <Menu.Item >
                    {regularity}
                </Menu.Item>
            ))}
        </Menu>
    );

    const menuPriority = (
        <Menu onClick={handlePrioritySelect}>
            {priorities.map((priority) => (
                <Menu.Item >
                    {priority}
                </Menu.Item>
            ))}
        </Menu>
    );

    return (
        <Card
            key={task.id}
            title={
                <div style={{display: "flex", alignItems: "center"}}>
                    <Checkbox
                        checked={isCompleted}
                        disabled={task.archived}
                        onChange={handleCheckboxChange}
                        style={{marginRight: "30px"}}
                        className={"input"}
                    />
                    <span style={{textDecoration: isCompleted ? "line-through" : "none", color: "#4d789d", fontFamily: 'Roboto', fontSize: '20px'}}>
                        {isEditing ? (
                            <Input
                                value={editedTitle}
                                onChange={handleTaskTitleEdit}
                                onPressEnter={handleTitleSave}
                                onBlur={handleTitleCancel}
                                autoFocus
                            />
                        ) : (
                            task.title
                        )}
                    </span>
                    <div style={{display: "flex"}}>
                        <Tooltip title="Редактировать" placement="bottom">
                            <Button
                                type="text"
                                icon={<EditOutlined/>}
                                onClick={handleEditClick}
                                disabled={task.archived}
                                style={{marginLeft: 480,backgroundColor: "#4d789d", color: "white"}}
                            />
                        </Tooltip>
                        <Tooltip title="Удалить" placement="bottom">
                            <Button
                                style={{marginLeft: 20,backgroundColor: "#4d789d", color: "white"}}
                                type="text"
                                icon={<DeleteOutlined/>}
                                onClick={handleDeleteClick}
                                disabled={task.archived}
                            />
                        </Tooltip>
                    </div>
                </div>
            }
            style={{
                width: "800px",
                marginBottom: "16px",
                backgroundColor: "#c7d1d0",
                color: task.archived ? "#517dfc" : "inherit",
            }}
            hoverable
        >
            {isDescriptionEditing ? (
                <Input.TextArea
                    value={editedDescription}
                    onChange={handleDescriptionEdit}
                    onPressEnter={handleDescriptionSave}
                    onBlur={handleDescriptionSave}
                    autoFocus
                    rows={4}
                />
            ) : (
                <div style={{marginTop: "8px"}}>
                    <span style={{fontWeight: "bold", color: "#27425f"}}>Описание:</span>{" "}
                    {task.description ? (
                        <>
                            {task.description}{" "}
                            <Tooltip title="Редактировать описание" placement="top">
                                <Button
                                    type="text"
                                    size="small"
                                    icon={<EditOutlined />}
                                    onClick={() => setIsDescriptionEditing(true)}
                                    style={{color: "#27425f"}}
                                />
                            </Tooltip>
                        </>
                    ) : (
                        <Tooltip title="Добавить описание" placement="top">
                            <Button
                                type="text"
                                size="small"
                                icon={<EditOutlined/>}
                                onClick={() => setIsDescriptionEditing(true)}
                            />
                        </Tooltip>
                    )}
                </div>
            )}



            <div style={{ display: "flex", alignItems: "center", marginTop: "20px"  }}>

                <div style={{ marginRight: "16px", marginLeft: "10px"}} >
                    <Dropdown
                        overlay={menuPriority}
                        visible={showPriorityMenu}
                        onVisibleChange={handlePriorityClick}
                        placement="bottomLeft"
                    >
                        <Button
                            type="text"
                            icon={<ExclamationOutlined />}
                            disabled={task.archive}
                            style={{backgroundColor: "#4d789d", color: "white",  borderRadius: '20px',}}
                        />
                    </Dropdown>
                    <span style={{ fontWeight: "bold" , color: "#27425f" }}>  Приоритет: </span> {selectedPriority}
                </div>

                <div style={{ marginRight: "16px" }}>
                    <Dropdown
                        overlay={
                            <div style={{padding: "8px"}}>
                                <DatePicker locale="ru" onChange={handleNotificationDateChange}/>
                                <TimePicker locale="ru" format="HH:mm" onChange={handleNotificationTimeChange}/>
                                <Button
                                    type="primary"
                                    onClick={handleNotificationSave}
                                    style={{marginLeft: "4px", backgroundColor: "#544e4e"}}
                                >
                                    Сохранить
                                </Button>
                                <Button onClick={handleNotificationCancel} style={{marginLeft: "4px"}}>
                                    Отмена
                                </Button>
                            </div>
                        }
                        visible={showNotificationForm}
                        onVisibleChange={handleNotificationClick}
                        placement="bottomLeft"
                        trigger={['click']}
                    >
                        <Tooltip title="Оповещение" placement="bottom">
                            <Button
                                type="text"
                                icon={<CalendarOutlined/>}
                                disabled={task.archive}
                                style={{backgroundColor: "#4d789d", color: "white",  borderRadius: '20px'}}
                            />
                        </Tooltip>
                    </Dropdown>
                    <span style={{ fontWeight: "bold", color: "#27425f" }}>  Крайний срок: </span> {selectedReminder}
                </div>


                <div style={{ marginRight: "16px" }}>
                    <Dropdown
                        overlay={menuRegularity}
                        visible={showRegularityMenu}
                        onVisibleChange={handleRegularityClick}
                        placement="bottomLeft"
                    >
                        <Button
                            type="text"
                            icon={<SyncOutlined />}
                            disabled={task.archive}
                            style={{backgroundColor: "#4d789d", color: "white",  borderRadius: '20px'}}
                        />
                    </Dropdown>
                    <span style={{ fontWeight: "bold", color: "#27425f" }}>  Регулярность: </span>
                    {selectedRegularity}
                </div>

            </div>

            <div style={{display: "flex", justifyContent: "space-between", marginTop: "8px"}}>
                <div style={{display: "flex", alignItems: "center"}}>



                </div>

            </div>
        </Card>
    );
};

export default TaskCard;