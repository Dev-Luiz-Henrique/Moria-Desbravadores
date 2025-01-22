package br.com.moria.shared.exceptions;

import br.com.moria.shared.enums.EntityType;
import br.com.moria.shared.utils.MessageUtil;
import org.springframework.http.HttpStatus;

/**
 * Exceção personalizada para representar erros de entidade não encontrada.
 * <p>
 * Esta exceção é lançada quando uma entidade específica não é encontrada no sistema.
 * Ela fornece detalhes como o tipo da entidade e seu identificador.
 * </p>
 *
 * @see ApplicationException
 * @see EntityType
 */
public class NotFoundException extends ApplicationException {

    private final EntityType entityType;
    private final Object entityId;

    /**
     * Construtor privado para criar uma instância da exceção.
     *
     * @param entityType o tipo da entidade que não foi encontrada.
     * @param entityId o identificador da entidade.
     * @param message a mensagem de erro associada.
     * @param errorCode o código de erro associado.
     */
    private NotFoundException(EntityType entityType, Object entityId, String message, String errorCode) {
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
     * Cria uma nova instância da exceção com base no tipo de entidade e seu identificador.
     *
     * @param entityType o tipo da entidade.
     * @param entityId o identificador da entidade.
     * @return uma nova instância de {@link NotFoundException}.
     */
    public static NotFoundException forEntity(EntityType entityType, Object entityId) {
        String messageKey = String.format("%s.not_found", entityType.getKey());
        String message = MessageUtil.getMessage(messageKey, entityId);
        String errorCode = entityType.name() + "_NOT_FOUND";
        return new NotFoundException(entityType, entityId, message, errorCode);
    }
}
