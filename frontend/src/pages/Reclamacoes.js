import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Reclamacoes.css";

export default function Reclamacoes() {
  const [reclamacoes, setReclamacoes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchReclamacoes = async () => {
      try {
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
      } catch (err) {
        setError("Erro de conexão: " + err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchReclamacoes();
  }, [navigate]);

  if (loading) return <div className="status-message">Carregando...</div>;
  if (error) return <div className="error-message">Erro: {error}</div>;

  return (
    <div className="reclamacoes-container">
      <h2>Minhas Reclamações</h2>

      {reclamacoes.length === 0 ? (
        <p className="status-message">Nenhuma reclamação encontrada.</p>
      ) : (
        reclamacoes.map((rec) => (
          <div className="reclamacao-card" key={rec.id}>
            <div className="reclamacao-info">
              <strong>{rec.titulo}</strong> —{" "}
              <span className={rec.respondida ? "respondida" : "pendente"}>
                {rec.respondida ? "Respondida" : "Pendente"}
              </span>
            </div>
            <button
              className="detalhes-btn"
              onClick={() => navigate("/reclamacoes/" + rec.id)}
            >
              Ver detalhes
            </button>
          </div>
        ))
      )}

      <button
        className="add-btn"
        onClick={() => navigate("/nova-reclamacao")}
      >
        +
      </button>
    </div>
  );
}
