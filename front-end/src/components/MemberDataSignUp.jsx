export function MemberDataSignUp() {
    return (
        <div className="member-register">
            <h3>CADASTRO DE MEMBRO</h3>
            <form>
                <div className="member-register-input">
                    <label htmlFor="nome">NOME:</label>
                    <input id="nome" type="text" />
                </div>

                <div className="member-register-input">
                    <label htmlFor="sexo">SEXO:</label>
                    <input id="sexo" type="text" />
                </div>

                <div className="member-register-input">
                    <label htmlFor="data">DATA DE NASCIMENTO:</label>
                    <input id="data" type="date" />
                </div>

                <div className="member-register-input">
                    <label htmlFor="password">SENHA:</label>
                    <input id="password" type="text" />
                </div>

                <div className="member-register-input-password">
                    <p>A senha deve conter mais de 8 caracteres.</p>
                    <p>Usar letras maiúsculas, minúsculas e caracteres especiais como @, # e $</p>
                </div>


                <hr />


                <div className="member-register-input">
                    <label htmlFor="cep">CEP:</label>
                    <input id="cep" type="text" />
                </div>

                <div className="member-register-input">
                    <label htmlFor="bairro">BAIRRO:</label>
                    <input id="bairro" type="text" />
                </div>

                <div className="member-register-input">
                    <label htmlFor="cidade">CIDADE:</label>
                    <input id="cidade" type="text" />
                </div>

                <div className="member-register-input">
                    <label for="estado">ESTADO:</label>
                    <select id="estado" name="estado">
                        <option value="acre">Acre</option>
                        <option value="alagoas">Alagoas</option>
                        <option value="amapa">Amapá</option>
                        <option value="amazonas">Amazonas</option>
                        <option value="bahia">Bahia</option>
                        <option value="ceara">Ceará</option>
                        <option value="espirito-santo">Espírito Santo</option>
                        <option value="goias">Goiás</option>
                        <option value="maranhao">Maranhão</option>
                        <option value="mato-grosso">Mato Grosso</option>
                        <option value="mato-grosso-do-sul">Mato Grosso do Sul</option>
                        <option value="minas-gerais">Minas Gerais</option>
                        <option value="para">Pará</option>
                        <option value="paraiba">Paraíba</option>
                        <option value="parana">Paraná</option>
                        <option value="pernambuco">Pernambuco</option>
                        <option value="piaui">Piauí</option>
                        <option value="rio-de-janeiro">Rio de Janeiro</option>
                        <option value="rio-grande-do-norte">Rio Grande do Norte</option>
                        <option value="rio-grande-do-sul">Rio Grande do Sul</option>
                        <option value="rondonia">Rondônia</option>
                        <option value="roraima">Roraima</option>
                        <option value="santa-catarina">Santa Catarina</option>
                        <option value="sao-paulo">São Paulo</option>
                        <option value="sergipe">Sergipe</option>
                        <option value="tocantins">Tocantins</option>
                        <option value="distrito-federal">Distrito Federal</option>
                    </select>
                </div>

                <div className="member-register-input">
                    <label htmlFor="cidade">CIDADE:</label>
                    <input id="cidade" type="text" />
                </div>

                <a href=""><p>Esqueci minha senha</p></a>
                <button type="submit">
                    SALVAR
                </button>
            </form>
        </div>
    )
}