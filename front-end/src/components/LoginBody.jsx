import { Footer } from "./Footer"
import { Header } from "./header"
import "./LoginBody.css"

export function LoginBody() {
    return (
        <section className="container-login-body">
            {/* btn */}
            <div className="credenciais">
                <h3>LOGIN</h3>

                <form action="">
                    <div className="credenciais-input">
                        <label htmlFor="email">EMAIL:</label>
                        <input id="email" type="email" />
                    </div>

                    <div className="credenciais-input">
                        <label htmlFor="password">SENHA:</label>
                        <input id="password" type="password" />
                    </div>

                    <a href=""><p>Esqueci minha senha</p></a>
                    <button>LOGIN</button>
                </form>
            </div>
        </section>
    )
}