import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { PrivateRoute } from "./components/PrivateRoute";
import { NotFound } from "./pages/NotFound";
import { Landing } from "./pages/Landing";
import { LoginPage } from "./pages/LoginPage";
import { UserPage } from "./components/UserPage";
import { Mensalidades } from "./pages/Mensalidades";
import { Authorities, getVoluntarios } from "./utils/authorities";
import "./App.css";
import { DropDownProfile } from "./components/DropDownProfile";

function App() {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    {/* Rotas públicas */}
                    <Route path="*" element={<NotFound />} />
                    <Route path='/' element={<Landing />} />
                    <Route path='/login' element={<LoginPage />} />
                    <Route path='/teste' element={<DropDownProfile />} />

                    {/* Rotas protegidas */}
                    <Route 
                        path='/gerenciar-membros'
                        element={
                            <PrivateRoute allowedAuthorities={getVoluntarios()}>
                                <UserPage />
                            </PrivateRoute>
                        }
                    />
                    <Route 
                        path='/gerenciar-mensalidades' 
                        element={
                            <PrivateRoute allowedAuthorities={getVoluntarios()}>
                                <Mensalidades />
                            </PrivateRoute>
                        } 
                    />
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;