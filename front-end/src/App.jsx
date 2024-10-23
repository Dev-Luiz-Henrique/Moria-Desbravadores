import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Landing } from "./pages/Landing"
import { LoginPage } from "./pages/LoginPage"
import "./App.css"

function App() {

  return (
    <Router>
        <Routes>
            <Route path="/" element={<Landing />} />
            <Route path="/login" element={<LoginPage />} />
        </Routes>
    </Router>
  )
}

export default App;