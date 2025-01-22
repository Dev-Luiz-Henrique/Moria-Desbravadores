package br.com.moria.shared.enums;

/**
 * Enumeração que define os tipos de entidades disponíveis.
 *
 * <p>Os tipos de entidades são utilizados para identificar e categorizar diferentes tipos de dados no sistema.</p>
 */
public enum EntityType {
    ENDERECO("endereco"),
    EVENTO("evento"),
    INSCRICAO("inscricao"),
    MENSALIDADE("mensalidade"),
    MEMBRO("membro"),
    RECURSO("recurso");

    private final String key;

    /**
     * Construtor para inicializar o tipo de entidade.
     *
     * @param key A chave associada ao tipo de entidade
     */
    EntityType(String key) {
        this.key = key;
    }

    /**
     * Obtém a chave associada ao tipo de entidade.
     *
     * @return A chave do tipo de entidade
     */
    public String getKey() {
        return key;
    }
}
