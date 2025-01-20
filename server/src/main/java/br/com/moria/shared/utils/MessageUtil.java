package br.com.moria.shared.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * Utilitário para manipulação de mensagens internacionalizadas.
 *
 * <p>Fornece métodos estáticos para obtenção de mensagens traduzidas e formatadas
 * com base em chaves e argumentos fornecidos.</p>
 *
 * <p>É necessário que o {@link ApplicationContext} seja configurado corretamente
 * para que o {@link MessageSource} seja acessível.</p>
 */
@Component
public class MessageUtil implements ApplicationContextAware {

    private static MessageSource messageSource;

    /**
     * Configura o {@link ApplicationContext} para acesso ao {@link MessageSource}.
     *
     * @param applicationContext o contexto da aplicação que contém os beans configurados.
     */
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    /**
     * Obtém uma mensagem traduzida com base na chave fornecida.
     *
     * <p>Se a mensagem exigir parâmetros para formatação, eles podem ser passados
     * como argumentos adicionais.</p>
     *
     * @param key  a chave da mensagem a ser obtida.
     * @param args os argumentos para formatação da mensagem.
     * @return a mensagem traduzida e formatada.
     * @throws IllegalArgumentException se a chave não for encontrada e uma mensagem padrão não estiver configurada.
     */
    public static String getMessage(String key, Object... args) {
        String message = messageSource.getMessage(key, null, Locale.getDefault());
        return MessageFormat.format(message, args);
    }

    /**
     * Obtém uma mensagem traduzida com base na chave fornecida, ou uma mensagem padrão caso a chave não seja encontrada.
     *
     * <p>Se a mensagem exigir parâmetros para formatação, eles podem ser passados
     * como argumentos adicionais.</p>
     *
     * @param key            a chave da mensagem a ser obtida.
     * @param defaultMessage a mensagem padrão a ser retornada caso a chave não seja encontrada.
     * @param args           os argumentos para formatação da mensagem.
     * @return a mensagem traduzida e formatada, ou a mensagem padrão se a chave não for encontrada.
     */
    public static String getMessageOrDefault(String key, String defaultMessage, Object... args) {
        String message = messageSource.getMessage(key, null, defaultMessage, Locale.getDefault());
        if (message == null) return defaultMessage;
        return MessageFormat.format(message, args);
    }
}
