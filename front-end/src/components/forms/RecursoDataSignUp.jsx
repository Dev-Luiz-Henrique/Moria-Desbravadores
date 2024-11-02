import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { apiRequest } from "../../utils/api.jsx";
import { validateRecurso } from "../../utils/validation.jsx";
import { getPayments } from "../../utils/paymentOptions.jsx";
import { getPaymentCategories } from "../../utils/PaymentCategory.jsx";
import { getPayStatus } from "../../utils/PaymentStatus.jsx";
import "./RecursoDataSignUp.css";

export function RecursoSignUp({initialData = null }) {
    const { eventoId } = useParams();

    // Chama as funções e atribui o retorno a payOptions, payCategories e statusPagamentoOptions
    const payOptions = getPayments();
    const payCategories = getPaymentCategories();
    const statusPagamentoOptions = getPayStatus();

    const [formData, setFormData] = useState(
        initialData || {
            nome: "",
            descricao: "",
            valor: "",
            quantidade: "",
            formaPagamento: payOptions[0].value,
            categoria: payCategories[0].value,
            statusPagamento: "PAGO", 
            evento: {
                id: eventoId,
            },
        }
    );

    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { id, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [id]: value,
        }));
    };

    const handleInputBlur = (e) => {
        const { id, value } = e.target;
        const cleanedValue = value.trim();
        setFormData((prevData) => ({
            ...prevData,
            [id]: cleanedValue,
        }));

        const errorMessage = validateRecurso[id]
            ? validateRecurso[id](cleanedValue)
            : "";
        setErrors((prevErrors) => ({
            ...prevErrors,
            [id]: errorMessage,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const hasErrors = Object.values(errors).some((error) => error !== "");
        console.log(formData);

        if (hasErrors) {
            alert("Corrija os erros antes de enviar.");
        } else {
            const { error: submitError } = await apiRequest(
                `/recursos`,
                "POST",
                formData
            );
            if (submitError) {
                alert("Erro ao cadastrar o recurso: " + submitError);
            } else {
                alert("Recurso cadastrado com sucesso!");
                navigate(`/detalhes-evento/${eventoId}`);
            }
        }
    };

    return (
        <div className="recurso-register">
            <h3>Cadastro de Recurso</h3>
            <form onSubmit={handleSubmit}>
                <div className="recurso-register-input">
                    <label htmlFor="nome">Nome do recurso:</label>
                    <input
                        id="nome"
                        type="text"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.nome || ""}
                    />
                    {errors.nome && <p className="error-message">{errors.nome}</p>}
                </div>

                <div className="recurso-register-input">
                    <label htmlFor="descricao">Descrição:</label>
                    <textarea
                        id="descricao"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.descricao || ""}
                    />
                    {errors.descricao && (
                        <p className="error-message">{errors.descricao}</p>
                    )}
                </div>

                <div className="recurso-register-input">
                    <label htmlFor="valor">Valor:</label>
                    <input
                        id="valor"
                        type="number"
                        step="0.01"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.valor || ""}
                    />
                    {errors.valor && (
                        <p className="error-message">{errors.valor}</p>
                    )}
                </div>

                <div className="recurso-register-input">
                    <label htmlFor="quantidade">Quantidade:</label>
                    <input
                        id="quantidade"
                        type="number"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.quantidade || ""}
                    />
                    {errors.quantidade && (
                        <p className="error-message">{errors.quantidade}</p>
                    )}
                </div>

                <div className="recurso-register-input">
                    <label htmlFor="formaPagamento">Forma de Pagamento:</label>
                    <select
                        id="formaPagamento"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.formaPagamento || ""}
                    >
                        {payOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    {errors.formaPagamento && (
                        <p className="error-message">{errors.formaPagamento}</p>
                    )}
                </div>

                <div className="recurso-register-input">
                    <label htmlFor="categoria">Categoria:</label>
                    <select
                        id="categoria"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.categoria || ""}
                    >
                        {payCategories.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    {errors.categoria && (
                        <p className="error-message">{errors.categoria}</p>
                    )}
                </div>

                <div className="recurso-register-input">
                    <label htmlFor="statusPagamento">Status Pagamento:</label>
                    <select
                        id="statusPagamento"
                        onBlur={handleInputBlur}
                        onChange={handleInputChange}
                        required
                        value={formData.statusPagamento || ""}
                    >
                        {statusPagamentoOptions.map((option) => (
                            <option key={option.value} value={option.value}>
                                {option.label}
                            </option>
                        ))}
                    </select>
                    {errors.statusPagamento && (
                        <p className="error-message">{errors.statusPagamento}</p>
                    )}
                </div>

                <div className="pagination-buttons">
                    <button type="submit">Salvar</button>
                </div>
            </form>
        </div>
    );
}
