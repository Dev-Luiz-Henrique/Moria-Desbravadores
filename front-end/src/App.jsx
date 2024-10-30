import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { PrivateRoute } from "./components/PrivateRoute";
import { NotFound } from "./pages/NotFound";
import { Landing } from "./pages/Landing";
import { LoginPage } from "./pages/LoginPage";
import { UserPage } from "./components/UserPage";
import { Mensalidades } from "./pages/Mensalidades";
import { MemberRegisterPage } from "./pages/MemberRegisterPage"; 
import { EventoRegisterPage } from "./pages/EventoRegisterPage";
import { RecursoRegisterPage } from "./pages/RecursoRegisterPage.jsx";
import { Evento } from "./pages/Evento";
import { Authorities, getVoluntarios } from "./utils/authorities";
import "./App.css";
import { ManageEvents } from "./pages/ManageEvents";
import { ViewMemberData } from "./components/ViewMemberData.jsx";
import { MemberView } from "./pages/MemberView.jsx";

function App() {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    {/* Rotas públicas */}
                    <Route path="*" element={<NotFound />} />
                    <Route path='/' element={<Landing />} />
                    <Route path='/login' element={<LoginPage />} />
                    <Route path='/teste' element={<EventoRegisterPage />} />

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
                        path='/gerenciar-eventos'
                        element={
                            <PrivateRoute allowedAuthorities={getVoluntarios()}>
                                <ManageEvents />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path='/cadastrar-recurso/:id?'
                        element={
                            <PrivateRoute allowedAuthorities={getVoluntarios()}>
                                <RecursoRegisterPage />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path='/cadastrar-membro/:id?'
                        element={
                            <PrivateRoute allowedAuthorities={
                                [Authorities.SECRETARIO, Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO]
                            }>
                                <MemberRegisterPage />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path='/cadastrar-evento/:id?'
                        element={
                            <PrivateRoute allowedAuthorities={
                                [Authorities.SECRETARIO, Authorities.DIRETOR_CLUBE, Authorities.DIRETOR_ASSOCIADO]
                            }>
                                <EventoRegisterPage />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path='/detalhes-evento/:id?'
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