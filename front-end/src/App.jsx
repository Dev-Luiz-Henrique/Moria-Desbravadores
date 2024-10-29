import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { PrivateRoute } from "./components/PrivateRoute";
import { NotFound } from "./pages/NotFound";
import { Landing } from "./pages/Landing";
import { LoginPage } from "./pages/LoginPage";
import { UserPage } from "./components/UserPage";
import { Mensalidades } from "./pages/Mensalidades";
import { MemberRegisterPage } from "./pages/MemberRegisterPage.jsx"; 
import { EventoRegisterPage } from "./pages/EventoRegisterPage";
import { Evento } from "./pages/Evento";
import { Authorities, getVoluntarios } from "./utils/authorities";
import "./App.css";
import { ManageEvents } from "./pages/ManageEvents";
import { CardEvents } from "./components/CardEvents";

function App() {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    {/* Rotas públicas */}
                    <Route path="*" element={<NotFound />} />
                    <Route path='/' element={<Landing />} />
                    <Route path='/login' element={<LoginPage />} />
                    <Route path='/teste' element={<ManageEvents />} />

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
                    <Route
                        path='/cadastrar-membro'
                        element={
                            <PrivateRoute allowedAuthorities={
                                [Authorities.SECRETARIO, Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO]
                            }>
                                <MemberRegisterPage />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path='/cadastrar-evento'
                        element={
                            <PrivateRoute allowedAuthorities={
                                [Authorities.SECRETARIO, Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO]
                            }>
                                <EventoRegisterPage />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path='/gerenciar-evento'
                        element={
                            <PrivateRoute allowedAuthorities={getVoluntarios()}>
                                <Evento />
                            </PrivateRoute>
                        }
                    />
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;