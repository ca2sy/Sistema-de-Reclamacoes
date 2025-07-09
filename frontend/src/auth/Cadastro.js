import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Cadastro.css";

export default function Cadastro() {
  const [cpf, setCpf] = useState("");
  const [nome, setNome] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate(); 

  const handleCadastro = async (e) => {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/auth/registro", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ cpf, nome, senha }),
    });

    if (response.ok) {
      alert("Cadastro realizado com sucesso!");
      navigate("/login");
    } else {
      alert("Erro ao cadastrar. Tente novamente.");
    }
  };

  return(
    <form onSubmit={handleCadastro}>
    <h2>Cadastro</h2>
    <input type="text" placeholder="Nome" value={nome} onChange={(e) => setNome(e.target.value)} required></input>
    <input type="text" placeholder="CPF" value={cpf} onChange={(e) => setCpf(e.target.value)} required></input>
    <input type="text" placeholder="Senha" value={senha} onChange={(e) => setSenha(e.target.value)} required></input>
    <button type="submit">Cadastrar</button>
    <p>Já possui cadastro? <a href="/login">Clique para login</a></p>
    </form>

  )
}
