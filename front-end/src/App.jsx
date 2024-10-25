import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { PrivateRoute } from "./components/PrivateRoute";
import { Landing } from "./pages/Landing";
import { LoginPage } from "./pages/LoginPage";
import { UserPage } from "./components/UserPage";
import { Authorities, getVoluntarios } from "./utils/authorities";
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
                        <PrivateRoute allowedAuthorities={getVoluntarios()}>
                            <UserPage />
                        </PrivateRoute>
                    }
                />
            </Routes>
        </Router>
    );
}

export default App;