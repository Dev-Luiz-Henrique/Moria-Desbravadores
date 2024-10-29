import { ButtonGoBack } from "../components/ButtonGoBack";
import { Footer } from "../components/Footer";
import { Header } from "../components/header";
import { MemberDataSignUp } from "../components/MemberDataSignUp";
import "./MemberRegisterPage.css"

export function MemberRegisterPage() {
    return (
        <>
            <Header />
            <div className="container-register-page">
                <div className="return-btn"><ButtonGoBack /></div>
                <MemberDataSignUp initialData={{
                    "nome": "Gabriel Gomes da Cruz",
                    "sexo": "M",
                    "dataNascimento": "2001-09-23",
                    "telefone": "5326399776",
                    "celular": "11985425117",
                    "email": "gabril.gomes@outlook.com",
                    "senha": "54747522Aa!",
                    "logradouro": "Rua Formosa",
                    "numero": 25,
                    "cpf": "58308393400",
                    "rg": "201467082",
                    "orgaoExpedidor": "SSP",
                    "tamanhoCamisa": "M",
                    "estadoCivil": "SOLTEIRO",
                    "batizado": false,
                    "dataCadastro": "2024-10-27T00:00:00",
                    "ativo": true,
                    "fichaSaude": "/",
                    "tipo": "DIRETOR_CLUBE",
                    "endereco": {
                        "cep": "29163670",
                        "bairro": "Cidade Continental-Setor Ásia",
                        "cidade": "Serra",
                        "estado": "ES"
                    }  
            }}/>
            </div>
            <Footer />
        </>
    )
}