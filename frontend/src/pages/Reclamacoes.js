import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Reclamacoes.css";

export default function Reclamacoes() {
  const [reclamacoes, setReclamacoes] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchReclamacoes = async () => {
      const token = localStorage.getItem("token");

      if (!token) {
        setError("Token não encontrado. Faça login novamente.");
        navigate("/login");
        return;
      }

      const response = await fetch(
        "http://localhost:8080/api/reclamacoes/minhas-reclamacoes",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.ok) {
        const data = await response.json();
        setReclamacoes(data);
      } else {
        const errorData = await response.text();
        setError(`Erro ${response.status}: ${errorData}`);
      }
    };

    fetchReclamacoes();
  }, [navigate]);
const handleDelete = async (id) => {
  const token = localStorage.getItem("token");


  const reclamacao = reclamacoes.find(r => r.id === id);
  
  if (reclamacao && reclamacao.respondida) {
    alert("Não é possível excluir uma reclamação que já foi respondida.");
    return;
  }

  const confirmDelete = window.confirm("Tem certeza que deseja excluir essa reclamação?");
  if (!confirmDelete) return;

  try {
    const response = await fetch(`http://localhost:8080/api/reclamacoes/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (response.ok) {
      setReclamacoes(prevReclamacoes => prevReclamacoes.filter((r) => r.id !== id));
    } else {
      const errorData = await response.text();
      setError(`Erro ao excluir: ${response.status} - ${errorData}`);
    }
  } catch (error) {
    console.error("Erro na requisição:", error);
    setError("Erro de conexão ao tentar excluir a reclamação");
  }
};


  return (
    <div className="reclamacoes-container">
      <h2>Minhas Reclamações</h2>

      {error && <p className="error-message">{error}</p>}

      {reclamacoes.length === 0 ? (
        <p className="status-message">Nenhuma reclamação encontrada.</p>
      ) : (
        reclamacoes.map((reclamacao) => (
          <div className="reclamacao-card" key={reclamacao.id}>
            <div className="reclamacao-info">
              <strong>{reclamacao.titulo}</strong> —{" "}
              <span
                className={reclamacao.respondida ? "respondida" : "pendente"}
              >
                {reclamacao.respondida ? "Respondida" : "Pendente"}
              </span>
            </div>
            <div className="card-actions">
              {/* RF-06*/}
              <button
                className="detalhes-btn"
                onClick={() => navigate("/reclamacoes/" + reclamacao.id)}
              >
                Ver detalhes
              </button>
               {/* RF-13*/}
              <button
                className="excluir-btn"
                onClick={() => handleDelete(reclamacao.id)}
              >
                Excluir
              </button>
            </div>
          </div>
        ))
      )}

      <button className="add-btn" onClick={() => navigate("/nova-reclamacao")}>
        +
      </button>
    </div>
  );
}
