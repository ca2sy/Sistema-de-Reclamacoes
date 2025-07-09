import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/NovaReclamacao.css";

export default function NovaReclamacao() {
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    const response = await fetch("http://localhost:8080/api/reclamacoes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token
      },
      body: JSON.stringify({ titulo, descricao })
    });

    if (response.ok) {
      alert("Reclamação enviada com sucesso!");
      navigate("/reclamacoes");
    } else {
      alert("Erro ao enviar reclamação.");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Nova Reclamação</h2>
      <input
        type="text"
        placeholder="Título"
        value={titulo}
        onChange={(e) => setTitulo(e.target.value)}
        required
      />
      <br />
      <textarea
        placeholder="Descrição"
        value={descricao}
        onChange={(e) => setDescricao(e.target.value)}
        required
      />
      <br />
      <button type="submit">Enviar</button>
    </form>
  );
}

