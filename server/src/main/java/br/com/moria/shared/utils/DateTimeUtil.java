package br.com.moria.shared.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * Converte uma string para LocalDateTime no formato padrão (ISO-8601).
     *
     * @param dateTimeStr String com a data/hora no formato ISO-8601.
     * @return LocalDateTime correspondente.
     * @throws IllegalArgumentException Se a string não estiver no formato esperado.
     */
    public static LocalDateTime parse(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                "Erro ao converter a string para LocalDateTime. Formato esperado: yyyy-MM-dd'T'HH:mm:ss", e
            );
        }
    }

    /**
     * Formata um LocalDateTime para string no formato padrão (ISO-8601).
     *
     * @param dateTime LocalDateTime a ser formatado.
     * @return String formatada no padrão ISO-8601.
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMATTER);
    }

    /**
     * Converte uma string para LocalDateTime com um formato customizado.
     *
     * @param dateTimeStr String com a data/hora no formato especificado.
     * @param formatter Formatter personalizado.
     * @return LocalDateTime correspondente.
     * @throws IllegalArgumentException Se a string não estiver no formato esperado.
     */
    public static LocalDateTime parseCustom(String dateTimeStr, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Erro ao converter a string para LocalDateTime com formato customizado.", e);
        }
    }

    /**
     * Formata um LocalDateTime para string com um formato customizado.
     *
     * @param dateTime LocalDateTime a ser formatado.
     * @param formatter Formatter personalizado.
     * @return String formatada no formato especificado.
     */
    public static String formatCustom(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }
}
