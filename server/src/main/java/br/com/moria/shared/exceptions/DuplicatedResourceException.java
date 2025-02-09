package br.com.moria.shared.exceptions;

import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para indicar que uma entidade duplicada foi encontrada.
 *
 * <p>Essa exceção é lançada quando uma tentativa de criar uma entidade resulta em duplicação.
 * Ela retorna um status HTTP 409 (Conflict) e fornece detalhes como o tipo da entidade.</p>
 *
 * @see ApplicationException
 * @see EntityType
 */
public class DuplicatedResourceException extends ApplicationException {

    private final EntityType entityType;

    /**
     * Construtor privado para criar a exceção com os detalhes da entidade duplicada.
     *
     * @param entityType o tipo da entidade duplicada.
     * @param message a mensagem descritiva do erro.
     * @param errorCode o código de erro associado.
     */
    private DuplicatedResourceException(EntityType entityType, String message, String errorCode) {
        super(HttpStatus.CONFLICT, message, errorCode);
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * Cria uma nova instância da exceção para uma entidade duplicada com mensagem personalizada.
     *
     * @param entityType o tipo da entidade.
     * @param messageKey a chave da mensagem personalizada.
     * @return uma nova instância de {@link DuplicatedResourceException}.
     */
    public static DuplicatedResourceException forEntity(EntityType entityType, String messageKey) {
        String message = MessageUtil.getMessage(messageKey);
        String errorCode = entityType.name().toUpperCase() + "_DUPLICATED";
        return new DuplicatedResourceException(entityType, message, errorCode);
    }
}
