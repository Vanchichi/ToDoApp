import {Route, Routes} from "react-router-dom";
import {NotFoundPage} from "./pages/NotFoundPage";
import LoginPage from "./pages/LoginPage";
import RegistrationPage from "./pages/RegistrationPage";
import UserInfoPage from "./pages/UserInfoPage";
import TaskPage from "./pages/TaskPage";
import ArchivePage from "./pages/ArchivePage";
function App() {
  return (
   <div>
      <Routes>
          <Route path="/api/auth/signup" element={<RegistrationPage/>}/>
          <Route path="/api/auth/signin" element={<LoginPage/>}/>
          <Route path="*" element={<NotFoundPage/>}/>
          <Route path="/user" element={<UserInfoPage/>}/>
          <Route path="/task" element={<TaskPage/>}/>
          <Route path="/archive" element={<ArchivePage/>}/>
          </Routes>
    </div>
  );
}

export default App;