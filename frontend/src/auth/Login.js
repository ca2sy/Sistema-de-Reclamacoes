import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Login.css";

export default function Login(){ //criando e exportando componente Login 
const[cpf, setCpf] = useState(""); //variavel cpf e setcpf atualiza o valor. começa vazia
const[senha, setSenha] = useState(""); //mesma coisa que o cpf
const navigate = useNavigate();

const handleLogin = async (e) => { //função ao apertar o botão de entrar. espera a resposta da api(async)
    e.preventDefault();
    const response = await fetch ("http://localhost:8080/auth/login",{
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({cpf, senha}),
    });

    if(response.ok){
        const data = await response.json();
        localStorage.setItem("token", data.token);
        alert("Login realizado com sucesso!");
        navigate("/reclamacoes");
    } else {
        alert("CPF ou senha inválidos.");
    }
 };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <div className="form-group">
            <label htmlFor="cpf">CPF</label>
            <input
              id="cpf"
              type="text"
              value={cpf}
              onChange={(e) => setCpf(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="senha">Senha</label>
            <input
              id="senha"
              type="password"
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              required
            />
          </div>

          <button type="submit">Entrar</button>
        </form>

        <p className="cadastro-redirect">
          Ainda não tem conta? <a href="/">Cadastre-se aqui</a>
        </p>
      </div>
    </div>
  );
}