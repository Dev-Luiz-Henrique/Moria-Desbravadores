import "./ViewMemberData.css"
import Delete from "../assets/img/Delete.svg"
import Edit from "../assets/img/Edit.svg"
import ProfileLogo from "../assets/img/user-card-icon.svg"

export function ViewMemberData() {
    return (
        <>
            <div class="profile-card-member-view">
                <div className="profile-card-buttons">
                    <button><img src={Edit} alt="" /></button>
                    <button><img src={Delete} alt="" /></button>
                </div>
                <div className="profile-card-header">
                    <img src={ProfileLogo} alt="" />
                    <h4>Luiz Henrique de Santana</h4>
                </div>

                <span>
                    <b>TIPO:</b>
                    <p>Desbravador</p>
                </span>

                <span>
                    <b>STATUS:</b>
                    <p>Ativo</p>
                </span>

                <span>
                    <b>MENSALIDADE:</b>
                    <p>Pendente</p>
                </span>

                <span>
                    <b>SEXO:</b>
                    <p>Masculino</p>
                </span>

                <span>
                    <b>DATA DE NASCIMENTO:</b>
                    <p>26/10/2004</p>
                </span>

                <span>
                    <b>ENDEREÇO:</b>
                    <p>Rua das Flores, 123, Jardim das Rosas, São Paulo - SP, CEP 01234-567</p>
                </span>

                <span>
                    <b>EMAIL:</b>
                    <p>luizhenriquesantana@gmail.com</p>
                </span>

                <span>
                    <b>CELULAR:</b>
                    <p>+55 11 93019-4902</p>
                </span>

                <span>
                    <b>TELEFONE:</b>
                    <p>+55 11 93015-1756</p>
                </span>

                <span>
                    <b>CPF:</b>
                    <p>123.456.789-09</p>
                </span>

                <span>
                    <b>RG:</b>
                    <p>12.345.678-9</p>
                </span>

                <span>
                    <b>ÓRGÃO EXPEDIDOR:</b>
                    <p>Masculino</p>
                </span>

                <span>
                    <b>TAMANHO DA CAMISA::</b>
                    <p>PP</p>
                </span>

                <span>
                    <b>ESTADO CIVIL:</b>
                    <p>Solteiro</p>
                </span>

                <span>
                    <b>BATIZADO:</b>
                    <p>Não</p>
                </span>

                <span>
                    <b>FICHA DE SAÚDE:</b>
                    <p><a href="ficha_saude.png" download>ficha_saude.png</a></p>
                </span>

                <span>
                    <b>PROFISSIONAL DA SAÚDE:</b>
                    <p>João Paulo da Sillva</p>
                </span>
            </div>
        </>
    )
}