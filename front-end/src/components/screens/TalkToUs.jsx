import "./TalkToUs.css";
import { useState } from "react";

export function TalkToUs() {
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        phone: "",
        message: ""
    });

    const handleChange = (e) => {
        const { id, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [id]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        const mailtoLink = `mailto:leitepereira.daniel2005@gmail.com?subject=Fale%20Conosco&body=
                            Nome: ${encodeURIComponent(formData.name)}
                            %0AEmail: ${encodeURIComponent(formData.email)}
                            %0ATelefone: ${encodeURIComponent(formData.phone)}
                            %0AMensagem: ${encodeURIComponent(formData.message)}`;
        
        window.location.href = mailtoLink;
    };

    return (
        <div className="talk-container">
            <h3>FALE CONOSCO</h3>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="name">NOME:</label>
                    <input id="name" type="text" value={formData.name} onChange={handleChange} />
                </div>

                <div>
                    <label htmlFor="email">EMAIL:</label>
                    <input id="email" type="email" value={formData.email} onChange={handleChange} />
                </div>

                <div>
                    <label htmlFor="phone">TELEFONE:</label>
                    <input id="phone" type="tel" value={formData.phone} onChange={handleChange} />
                </div>

                <div className="fdc">
                    <label htmlFor="message">MENSAGEM:</label>
                    <textarea className="fixed-text-area" id="message" value={formData.message} onChange={handleChange}></textarea>
                </div>
                
                <button type="submit">ENVIAR</button>
            </form>
        </div>
    );
}