import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { PrivateRoute } from "./components/PrivateRoute";
import { Landing } from "./pages/Landing";
import { LoginPage } from "./pages/LoginPage";
import { UserPage } from "./components/UserPage";
import "./App.css";

function App() {
    return (
        <Router>
            <Routes>
                {/* Rotas públicas */}
                <Route path='/' element={<Landing />} />
                <Route path='/login' element={<LoginPage />} />

                {/* Rotas protegidas */}
                <Route 
                    path='/membros'
                    element={
                        <PrivateRoute>
                            <UserPage />
                        </PrivateRoute>
                    }
                />
            </Routes>
        </Router>
    );
}

export default App;
