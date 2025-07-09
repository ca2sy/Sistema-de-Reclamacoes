import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../css/DetalhesReclamacao.css";

export default function DetalhesReclamacao() {
  const { id } = useParams();
  const [reclamacao, setReclamacao] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:8080/api/reclamacoes/" + id, {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((res) => res.json())
      .then((data) => setReclamacao(data))
      .catch(() => alert("Erro ao carregar detalhes"));
  }, [id]);

  if (!reclamacao) return <p>Carregando...</p>;

  return (
    <div>
      <h2>{reclamacao.titulo}</h2>
      <p><b>Descrição:</b> {reclamacao.descricao}</p>
      <p><b>Data:</b> {reclamacao.data}</p>
      <p><b>Status:</b> {reclamacao.respondida ? "Respondida" : "Pendente"}</p>
    </div>
  );
}
