import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Landing } from "./pages/Landing"
import { LoginPage } from "./pages/LoginPage"
import { Teste } from "./Teste";
import "./App.css"

function App() {

  return (
    <Router>
        <Routes>
            <Route path="/" element={<Landing />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/teste" element={<Teste />} />
        </Routes>
    </Router>
  )
}

export default App;