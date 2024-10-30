import "./EventoDataSignUp.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiRequest } from "../utils/api.jsx";
import { validadeEvento as validate } from "../utils/validation.jsx";
import { states } from "../utils/states.jsx";

export function EventoDataSignUp({ initialData = null }) {
    const [formData, setFormData] = useState(initialData || {
        nome: "",
        descricao: "",
        publico: false,
        dataInicio: "",
        dataFim: "",
        logradouro: "",
        numero: "",
        endereco: {
            bairro: "",
            cidade: "",
            estado: "SP",
            cep: "",
        },
    });
    const [currentPage, setCurrentPage] = useState(0);
    const [errors, setErrors] = useState({});
    const navigate = useNavigate();

    const validateField = (field, value) => {
        const fieldName = field.includes("endereco-") ? field.split("-")[1] : field;
        const message = validate[fieldName] ? validate[fieldName](value) : "";
        setErrors((prevErrors) => ({
            ...prevErrors,
            [fieldName]: message,
        }));
    };

    const handleInputChange = (e) => {
        const { id, value } = e.target;
        
        setFormData((prevData) => {
            const isEnderecoField = id.startsWith("endereco-");
            const fieldKey = isEnderecoField ? id.split("-")[1] : id;

            if (fieldKey === "publico") {
                return {
                    ...prevData,
                    [fieldKey]: value === "sim",
                };
            }
    
            if (isEnderecoField) {
                return {
                    ...prevData,
                    endereco: {
                        ...prevData.endereco,
                        [fieldKey]: value,
                    },
                };
            } else {
                return {
                    ...prevData,
                    [fieldKey]: value,
                };
            }
        });
    };
    
    const handleInputBlur = (e) => {
        const { id, value } = e.target;
        const cleanedValue = value.trim();

        setFormData((prevData) => {
            const isEnderecoField = id.includes("endereco-");
            const fieldKey = isEnderecoField ? id.split("-")[1] : id;

            if (fieldKey === "publico") {
                return {
                    ...prevData,
                    [fieldKey]: value === "sim",
                };
            }

            return {
                ...prevData,
                [isEnderecoField ? "endereco" : fieldKey]: 
                    isEnderecoField
                    ? { ...prevData.endereco, [fieldKey]: cleanedValue }
                    : cleanedValue,
            };
        });
        validateField(id, cleanedValue);

        if(id == "dataFim"){
            const dataInicio = new Date(formData.dataInicio);
            const dataFim = new Date(value);
            if(dataFim < dataInicio)
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    dataFim: "Data fim não pode ser anterior à data início.",
                }));
        } else {
            setErrors((prevErrors) => ({
                ...prevErrors,
                dataFim: "",
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        const hasErrors = Object.values(errors).some((error) => error !== "");
        if (hasErrors) 
            alert("Corrija os erros antes de enviar.");
        else { console.log(formData)
            const { error: submitError } = await apiRequest(`/eventos`, "POST", formData);
            if (submitError) 
                alert("Erro no salvamento do evento. " + submitError);
            else {
                alert("Evento salvo com sucesso!");
                navigate("/gerenciar-eventos");
            }
        }
    };

    const formPages = [
        <div key='page1'>
            <div className="event-register-input">
                <label htmlFor="publico">EVENTO PÚBLICO:</label>
                <select id="publico" onBlur={handleInputBlur} required
                    onChange={handleInputChange} value={formData.publico ? "sim" : "nao" || ""}>
                    <option value="sim">Sim</option>
                    <option value="nao">Não</option>
                </select>
                {errors.publico && <p className="error-message">{errors.publico}</p>}
            </div>

            <div className='event-register-input'>
                <label htmlFor='nome'>NOME:</label>
                <input id='nome' type='text' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.nome || ""} />
                {errors.nome && <p className="error-message">{errors.nome}</p>}
            </div>

            <div className='event-register-input'>
                <label htmlFor='dataInicio'>DATA INÍCIO:</label>
                <input id='dataInicio' type='datetime-local' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.dataInicio || ""} />
                {errors.dataInicio && <p className="error-message">{errors.dataInicio}</p>}
            </div>

            <div className='event-register-input'>
                <label htmlFor='dataFim'>DATA FIM:</label>
                <input id='dataFim' type='datetime-local' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.dataFim || ""} />
                {errors.dataFim && <p className="error-message">{errors.dataFim}</p>}
            </div>

            <div className="event-register-input">
                <label htmlFor="atracao">ATRAÇÃO:</label>
                <input id="atracao" type="text" onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.atracao || ""} />
                {errors.atracao && <p className="error-message">{errors.atracao}</p>}
            </div>

            <div className="event-register-input">
                <label htmlFor="descricao">DESCRIÇÃO:</label>
                <textarea id="descricao" onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.descricao || ""} />
                {errors.descricao && <p className="error-message">{errors.descricao}</p>}
            </div>

            {/* <div className="event-register-input input-file">
                <label htmlFor="imagem">IMAGEM:</label>
                <input type="file" id="imagem"/>
            </div> */}
        </div>,

        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////

        <div key='page2'>
            <div className='member-register-input'>
                <label htmlFor='endereco-cep'>CEP:</label>
                <input id='endereco-cep' type='text' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.endereco?.cep || ""} />
                {errors.cep && <p className="error-message">{errors.cep}</p>}
            </div>
            
            <div className='member-register-input'>
                <label htmlFor='endereco-estado'>ESTADO:</label>
                <select id='endereco-estado' name='estado' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.endereco?.estado || ""} >
                    {states.map((state) => (
                        <option key={state.value} value={state.abbreviation}>
                            {state.label}
                        </option>
                    ))}
                </select>
                {errors.estado && <p className="error-message">{errors.estado}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='endereco-cidade'>CIDADE:</label>
                <input id='endereco-cidade' type='text' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.endereco?.cidade || ""} />
                {errors.cidade && <p className="error-message">{errors.cidade}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='endereco-bairro'>BAIRRO:</label>
                <input id='endereco-bairro' type='text' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.endereco?.bairro || ""} />
                {errors.bairro && <p className="error-message">{errors.bairro}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='logradouro'>LOGRADOURO:</label>
                <input id='logradouro' type='text' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.logradouro || ""} />
                {errors.logradouro && <p className="error-message">{errors.logradouro}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='numero'>NUMERO:</label>
                <input id='numero' type='number' onBlur={handleInputBlur} required 
                    onChange={handleInputChange} value={formData.numero || ""} />
                {errors.numero && <p className="error-message">{errors.numero}</p>}
            </div>
        </div>
    ];

    const handleNext = () => {
        setCurrentPage((prevPage) =>
            Math.min(prevPage + 1, formPages.length - 1)
        );
    };
    const handlePrev = () => {
        setCurrentPage((prevPage) => Math.max(prevPage - 1, 0));
    };

    return (
        <div className='event-register'>
            <h3>CADASTRO DE EVENTO</h3>
            <form onSubmit={handleSubmit}>
                {formPages[currentPage]}
                <div className='pagination-buttons'>
                    {currentPage > 0 ? (
                        <button type='button' onClick={handlePrev}>Anterior</button>
                    ) : <div style={{ width: '100px' }}></div>}
                    {currentPage < formPages.length - 1 && (
                        <button type='button' onClick={handleNext}>Próximo</button>
                    )}
                    {currentPage === formPages.length - 1 && (
                        <button type='submit'>Salvar</button>
                    )}
                </div>
            </form>
        </div>
    );
}