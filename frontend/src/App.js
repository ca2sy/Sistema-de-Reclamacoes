import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./auth/Login";
import Cadastro from "./auth/Cadastro";
import Reclamacoes from "./pages/Reclamacoes";
import DetalhesReclamacao from "./pages/DetalhesReclamacao";
import NovaReclamacao from './pages/NovaReclamacao';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Cadastro />} />  
        <Route path="/reclamacoes" element={<Reclamacoes />} />
        <Route path="/reclamacoes/:id" element={<DetalhesReclamacao />} />
        <Route path="/nova-reclamacao" element={<NovaReclamacao />} />
      </Routes>
    </Router>
  );
}

export default App;
