

import {Route, Routes} from "react-router-dom";
import {NotFoundPage} from "./pages/NotFoundPage";
import LoginPage from "./pages/LoginPage";
import RegistrationPage from "./pages/RegistrationPage";
import UserInfoPage from "./pages/UserInfoPage";

function App() {
  return (
   <div>
      <Routes>
          <Route path="/registration" element={<RegistrationPage/>}/>
        <Route path="/auth" element={<LoginPage/>}/>
        <Route path="*" element={<NotFoundPage/>}/>
      </Routes>
    </div>
  );
}

export default App;
