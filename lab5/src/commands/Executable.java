package commands;

import exception.ArgumentException;

import java.io.IOException;

/**
 * Интерфейс для всех выполняемых команд.
 */
public interface Executable {
    /**
     * Выполнить что-либо.
     *
     * @param arguments Аргумент для выполнения
     * @return результат выполнения
     */
    void apply(String arguments) throws ArgumentException, IOException;
}
