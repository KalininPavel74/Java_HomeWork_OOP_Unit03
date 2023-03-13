package unit3.view;

import unit3.IView;
import unit3.control.ProgException;
import unit3.control.TypeOfSymbols;
import unit3.util.MyLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ViewConsole implements IView {
    private static final Logger logger = Logger.getLogger(ViewConsole.class.getName());
    private BufferedReader reader = null;
    private InputStreamReader inputStreamReader = null;

    public ViewConsole() {
        try {
            inputStreamReader = new InputStreamReader(System.in, MyLog.CHARSET_CONSOLE);
            reader = new BufferedReader(inputStreamReader);
            logger.info("Открыт слушатель консоли System.in");
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    public void close() {
        logger.info("Перед закрытием консоли System.in");
        try {
            reader.close();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        try {
            inputStreamReader.close();
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }

    @Override
    public TypeOfSymbols getTypeOfSymbols() throws ProgException {
        while (true) {
            logger.warning("Выберите тип символов: ");
            TypeOfSymbols[] types = TypeOfSymbols.values();
            StringBuilder sb = new StringBuilder();
            for(TypeOfSymbols t: types){
                sb.append(t.getIndex()).append(") ").append(t.getText()).append("\n");
            }
            sb.append(":");
            logger.warning(sb.toString());
            try {
                String s = reader.readLine();
                logger.info("Пользователь ввел "+s);
                int index = Integer.valueOf(s);
                if(index <= 0 || index > types.length) {
                    logger.warning("Номер типа символов дожен быть целым числом (<=" + types.length + ")");
                    continue;
                } else {
                    for(TypeOfSymbols t: types)
                        if(t.getIndex() == index) {
                            logger.info("Возвращается "+t.getText());
                            return t;
                        }
                    throw new ProgException("Не нашел что вернуть.");
                }
            } catch (IOException e) {
                logger.info(e.toString());
                logger.warning("Что-то пошло не так. Нужно повторить.");
                throw new ProgException(e.getMessage());
            }
        }
    }

    @Override
    public int getLenQuestWord(TypeOfSymbols typeOfSymbols, int maxSymbols) {
        while (true) {
            logger.warning(String.format("Введите длинну загадываемого слова. (%s <=%d)", typeOfSymbols.getText(), maxSymbols));
            try {
                String s = reader.readLine();
                logger.info("Пользователь ввел "+s);
                int len = Integer.valueOf(s);
                if(len <= 0 || len > maxSymbols) {
                    logger.warning("Длинна загадываемого слова дожна быть целым числом (<=" + maxSymbols + ")");
                    continue;
                } else {
                    logger.info("Возвращаю "+len);
                    return len;
                }
            } catch (IOException e) {
                logger.info(e.toString());
                logger.warning("Что-то пошло не так. Нужно повторить.");
            }
        }
    }

    @Override
    public String getTrySymbols(int lenQuestWord) {
        while (true) {
            logger.warning("Введите слово: ");
            try {
                String s = reader.readLine();
                logger.info("Пользователь ввел "+s);
                if(s == null || s.length() <= 0 || s.length() > lenQuestWord) {
                    logger.warning("Длинна загадываемого слова дожна быть " + lenQuestWord + " символов.");
                    continue;
                } else {
                    s = s.toUpperCase();
                    logger.info("Возвращаю "+s);
                    return s;
                }
            } catch (IOException e) {
                logger.info(e.toString());
                logger.warning("Что-то пошло не так. Нужно повторить.");
            }
        }
    }

    @Override
    public String getSymbol(String text) {
        while (true) {
            logger.warning(text);
            try {
                String s = reader.readLine();
                logger.info("Пользователь ввел "+s);
                if(s == null || s.length() <= 0) {
                    logger.warning("Ответ пустой.");
                    continue;
                } else {
                    s = s.toUpperCase();
                    logger.info("Возвращаю "+s);
                    return s;
                }
            } catch (IOException e) {
                logger.info(e.toString());
                logger.warning("Что-то пошло не так. Нужно повторить.");
            }
        }
    }

    @Override
    public void viewText(String text) {
        logger.warning(text);
    }

}
