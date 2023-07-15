import {configureStore} from '@reduxjs/toolkit'
import authReducer from "./slices/authSlice";
import userReducer from "./slices/userSlice";
import taskReducer from "./slices/taskSlice";
import categoryReducer from "./slices/categorySlice";
export default configureStore({
    reducer: {
        auth: authReducer,
        user: userReducer,
        tasks: taskReducer,
        categories: categoryReducer,
    },
})
