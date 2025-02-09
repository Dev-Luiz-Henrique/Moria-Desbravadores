package br.com.moria.shared.exceptions;

import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para indicar que uma entidade não foi encontrada.
 *
 * <p>Essa exceção é lançada quando uma entidade específica não está disponível no sistema.
 * Ela retorna um status HTTP 404 (Not Found) e fornece detalhes como o tipo da entidade.</p>
 *
 * @see ApplicationException
 * @see EntityType
 */
public class NotFoundResourceException extends ApplicationException {

    private final EntityType entityType;
    private final Object entityId;

    /**
     * Construtor privado para criar a exceção com os detalhes da entidade não encontrada.
     *
     * @param entityType o tipo da entidade que não foi encontrada.
     * @param entityId o identificador da entidade.
     * @param message a mensagem descritiva do erro.
     * @param errorCode o código de erro associado.
     */
    private NotFoundResourceException(EntityType entityType, Object entityId, String message, String errorCode) {
        super(HttpStatus.NOT_FOUND, message, errorCode);
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Object getEntityId() {
        return entityId;
    }

    /**
     * Cria uma nova instância da exceção para uma entidade não encontrada com mensagem personalizada
     * e identificador específico.
     *
     * @param entityType o tipo da entidade.
     * @param entityId o identificador da entidade (pode ser null).
     * @param messageKey a chave da mensagem personalizada.
     * @return uma nova instância de {@link NotFoundResourceException}.
     */
    private static NotFoundResourceException forEntity(EntityType entityType, Object entityId, String messageKey) {
        String message = MessageUtil.getMessage(messageKey, entityId);
        String errorCode = entityType.name().toUpperCase() + "_NOT_FOUND";
        return new NotFoundResourceException(entityType, entityId, message, errorCode);
    }

    /**
     * Cria uma nova instância da exceção para uma entidade não encontrada com um identificador específico.
     *
     * @param entityType o tipo da entidade.
     * @param entityId o identificador da entidade.
     * @return uma nova instância de {@link NotFoundResourceException}.
     */
    public static NotFoundResourceException forEntity(EntityType entityType, Object entityId) {
        String messageKey = String.format("business.%s.not_found", entityType.getKey());
        return forEntity(entityType, entityId, messageKey);
    }

    /**
     * Cria uma nova instância da exceção para uma entidade não encontrada sem identificador específico,
     * usando mensagem personalizada.
     *
     * @param entityType o tipo da entidade.
     * @param messageKey a mensagem personalizada.
     * @return uma nova instância de {@link NotFoundResourceException}.
     */
    public static NotFoundResourceException forEntity(EntityType entityType, String messageKey) {
        return forEntity(entityType, null, messageKey);
    }
}
