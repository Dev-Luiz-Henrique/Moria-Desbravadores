import "./MemberDataSignUp.css";
import { useState } from "react";
import { validate } from "../utils/validation.jsx";
import { states } from "../utils/states.jsx";
import { getAuthorities as roles } from "../utils/authorities.jsx";
import { normalizeUnderscore, memberUtils } from "../utils/stringHelpers.jsx";
import { apiRequest } from "../utils/api.jsx";

export function MemberDataSignUp({ initialData = null }) {
    const [formData, setFormData] = useState(initialData || {});
    const [currentPage, setCurrentPage] = useState(0);
    const [errors, setErrors] = useState({});

    const validateField = (field, value) => {
        const message = validate[field] ? validate[field](value) : "";
        console.log(message)
        setErrors((prevErrors) => ({
            ...prevErrors,
            [field]: message,
        }));
    };

    const handleInputChange = (e) => {
        const { id, value } = e.target;
    
        setFormData((prevData) => ({
            ...prevData,
            [id.includes("endereco.") ? "endereco" : id]: 
                id.includes("endereco.")
                ? { ...prevData.endereco, [id.split(".")[1]]: value }
                : value,
        }));
    };
    
    const handleInputBlur = (e) => {
        const { id, value } = e.target;
        let cleanedValue = value.trim();
    
        setFormData((prevData) => ({
            ...prevData,
            [id.includes("endereco.") ? "endereco" : id]: 
                id.includes("endereco.")
                ? { ...prevData.endereco, [id.split(".")[1]]: cleanedValue }
                : cleanedValue,
        }));
    
        validateField(id, cleanedValue);
    };
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        const hasErrors = Object.values(errors).some((error) => error !== "");
        if (hasErrors)
            alert("Por favor, corrija os erros no formulário antes de enviá-lo.");
        else {
            const { error: submitError } = await apiRequest(`/membros`, "POST", formData);
            if (submitError)
                alert("Não foi possível realizar o cadastro. " + submitError);
        }
    };

    const formPages = [
        <div key='page1'>
            <div className='member-register-input'>
                <label htmlFor='nome'>NOME:</label>
                <input id='nome' type='text' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.nome || ""} />
                {errors.nome && <p className="error-message">{errors.nome}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='dataNascimento'>DATA DE NASCIMENTO:</label>
                <input id='dataNascimento' type='date' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.dataNascimento || ""} />
                {errors.dataNascimento && <p className="error-message">{errors.data}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="email">EMAIL:</label>
                <input id="email" type="text" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.email || ""} />
                {errors.email && <p className="error-message">{errors.email}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='senha'>SENHA:</label>
                <input id='senha' type='text' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.senha || ""} />
                {errors.senha && <p className="error-message">{errors.senha}</p>}
            </div>
        </div>,

        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////

        <div key='page2'>
            <div className='member-register-input'>
                <label htmlFor='cep'>CEP:</label>
                <input id='cep' type='text' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.endereco.cep || ""} />
                {errors.cep && <p className="error-message">{errors.cep}</p>}
            </div>
            
            <div className='member-register-input'>
                <label htmlFor='estado'>ESTADO:</label>
                <select id='estado' name='estado' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.endereco.estado || ""} >
                    {states.map((state) => (
                        <option key={state.value} value={state.abbreviation}>
                            {state.label}
                        </option>
                    ))}
                </select>
                {errors.estado && <p className="error-message">{errors.estado}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='cidade'>CIDADE:</label>
                <input id='cidade' type='text' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.endereco.cidade || ""} />
                {errors.cidade && <p className="error-message">{errors.cidade}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='bairro'>BAIRRO:</label>
                <input id='bairro' type='text' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.endereco.bairro || ""} />
                {errors.bairro && <p className="error-message">{errors.bairro}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='logradouro'>LOGRADOURO:</label>
                <input id='logradouro' type='text' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.logradouro || ""} />
                {errors.logradouro && <p className="error-message">{errors.logradouro}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='numero'>NUMERO:</label>
                <input id='numero' type='number' onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.numero || ""} />
                {errors.numero && <p className="error-message">{errors.numero}</p>}
            </div>
        </div>,

        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////

        <div key='page3'>
            <div className="member-register-input">
                <label htmlFor="celular">CELULAR:</label>
                <input id="celular" type="text" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.celular || ""} />
                {errors.celular && <p className="error-message">{errors.celular}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="telefone">TELEFONE:</label>
                <input id="telefone" type="text" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.telefone || ""} />
                {errors.telefone && <p className="error-message">{errors.telefone}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="cpf">CPF:</label>
                <input id="cpf" type="text" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.cpf || ""} />
                {errors.cpf && <p className="error-message">{errors.cpf}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="rg">RG:</label>
                <input id="rg" type="text" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.rg || ""} />
                {errors.rg && <p className="error-message">{errors.rg}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="orgaoExpedidor">ORGÃO EXPEDIDOR:</label>
                <input id="orgaoExpedidor" type="text" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.orgaoExpedidor || ""} />
                {errors.orgaoExpedidor && <p className="error-message">{errors.orgaoExpedidor}</p>}
            </div>
        </div>,

        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////

        <div key='page4'>
            <div className="member-register-input">
                <label htmlFor="estadoCivil">ESTADO CIVIL:</label>
                <select id="estadoCivil" name="estado-civil" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.estadoCivil || ""} >
                    <option value="SOLTEIRO">Solteiro</option>
                    <option value="CASADO">Casado</option>
                    <option value="VIUVO">Viúvo</option>
                    <option value="DIVORCIADO">Divorciado</option>
                    <option value="SEPARADO">Separado</option>
                    <option value="UNIAO_ESTAVEL">União Estável</option>
                </select>
                {errors.estadoCivil && <p className="error-message">{errors.estadoCivil}</p>}
            </div>

            <div className='member-register-input'>
                <label htmlFor='sexo'>SEXO:</label>
                <select id='sexo' name="sexo" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.sexo || ""} >
                    <option value="M">Masculino</option>
                    <option value="F">Feminino</option>
                    <option value="O">Outro</option>
                </select>
                {errors.sexo && <p className="error-message">{errors.sexo}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="tipo">FUNÇÃO:</label>
                <select id="tipo" name="membro-funcao" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.tipo || ""} >
                    {roles().map((role) => (
                        <option key={role} value={role}>{normalizeUnderscore(role)}</option>
                    ))}
                </select>
                {errors.tipo && <p className="error-message">{errors.tipo}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="batizado">Batizado: </label>
                <select name="batizado" id="batizado" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.batizado || ""}>
                    <option value="S">SIM</option>
                    <option value="N">NÃO</option>
                </select>
                {errors.batizado && <p className="error-message">{errors.batizado}</p>}
            </div>

            <div className="member-register-input">
                <label htmlFor="tamanhoCamisa">TAMANHO DA CAMISA:</label>
                <select id="tamanhoCamisa" name="tamanho-camisa" onBlur={handleInputBlur} 
                    onChange={handleInputChange} value={formData.tamanhoCamisa || ""} >
                    <option value="p">P</option>
                    <option value="m">M</option>
                    <option value="g">G</option>
                    <option value="gg">GG</option>
                    <option value="xg">XG</option>
                </select>
                {errors.tamanhoCamisa && <p className="error-message">{errors.tamanhoCamisa}</p>}
            </div>

            <div className="member-register-input input-file">
                <label htmlFor="arquivo">Selecione um arquivo:</label>
                <input type="file" name="arquivo" id="arquivo" />
            </div>
        </div>,
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
        <div className='member-register'>
            <h3>CADASTRO DE MEMBRO</h3>
            <form onSubmit={handleSubmit}>
                {formPages[currentPage]}
                <div className='pagination-buttons'>
                    {currentPage > 0 ? (
                        <button type='button' onClick={handlePrev}>
                            Anterior
                        </button>
                    ) : (
                        <div style={{ width: '100px' }}></div>  // Simula o espaço do botão "Anterior"
                    )}
                    {currentPage < formPages.length - 1 && (
                        <button type='button' onClick={handleNext}>
                            Próximo
                        </button>
                    )}
                    {currentPage === formPages.length - 1 && (
                        <button type='submit'>Salvar</button>
                    )}
                </div>
            </form>
        </div>
    );
}