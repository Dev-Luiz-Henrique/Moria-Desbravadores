import "./TalkToUs.css"

export function TalkToUs() {
    return (
        <div className="talk-container">
            <h3>FALE CONOSCO</h3>
            <form action="">
                <div>
                    <label htmlFor="name">NOME:</label>
                    <input id="name" type="text" />
                </div>

                <div>
                    <label htmlFor="email">EMAIL:</label>
                    <input id="email" type="email" />
                </div>

                <div>
                    <label htmlFor="phone">TELEFONE:</label>
                    <input id="phone" type="tel" pattern="[0-9]{2} [0-9]{5}-[0-9]{4}" />
                </div>

                <div className="fdc">
                    <label htmlFor="message">MENSAGEM:</label>
                    <textarea className="fixed-text-area" id="message"></textarea>
                </div>
                <button type="reset">ENVIAR</button>
            </form>

            {/* <img src={BgTalkToUs} alt="" /> */}
        </div>
    )
}