package unit3.model;

import unit3.ISymbols;
import unit3.TryResult;
import unit3.control.ProgException;
import unit3.control.UserException;
import java.util.Random;
import java.util.logging.Logger;


abstract public class ASymbols implements ISymbols {
    private static final Logger logger = Logger.getLogger(ASymbols.class.getName());
    protected int maxSymbols;
    protected char[] symbols = null;
    private int[] questSymbols = null;
    private int lenQuestWord;

    private String questWord = null;

    @Override
    public int getIndexBySymbol(char symbol) throws UserException {
        for (int i = 0; i < maxSymbols; i++)
            if (symbols[i] == symbol)
                return i;
        throw new UserException("Недопустимый символ.");
    }

    public String createQuestSymbols(int lenQuestWord) throws UserException, ProgException {
        logger.info("Загадать слово длинной " + lenQuestWord);
        if (maxSymbols >= lenQuestWord)
            this.lenQuestWord = lenQuestWord;
        else throw new UserException("Длинна загаданного слова больше кол-ва уникальных символов.");
        if (questSymbols == null)
            questSymbols = new int[maxSymbols];
        else
            for (int i = 0; i < maxSymbols; i++)
                questSymbols[i] = 0;
        char[] word = new char[lenQuestWord];
        int wordPosition = 1;
        Random random = new Random();
        int i = 0, max_i = 1000;
        while (wordPosition <= lenQuestWord && i++ <= max_i) {
            int n = random.nextInt(maxSymbols);
            if (questSymbols[n] == 0) {
                questSymbols[n] = wordPosition;
                word[wordPosition - 1] = symbols[n];
                wordPosition++;
            }
            logger.info("n = " + n + " maxSymbols=" + maxSymbols + " lenQuestWord=" + lenQuestWord + " wordPosition=" + wordPosition);
            logger.info("word = " + charArrayToString(word) + " questSymbols=" + intArrayToString(questSymbols));
        }
        if (i >= max_i) {
            throw new ProgException("Не смог загадать слово.");
        }
        questWord = String.valueOf(word);
        logger.info("Загаданное слово "+questWord);
        return questWord;
    }

    public TryResult getCowsAndBulls(String tryWord) throws UserException {
        logger.info("Буду считать " + tryWord);
        TryResult tryResult = new TryResult();
        int[] trySymbols = new int[maxSymbols];
        char[] tryWordChars = tryWord.toCharArray();
        // преобразовать ответ во внутренний формат
        for (int i = 0; i < lenQuestWord; i++) {
            trySymbols[getIndexBySymbol(tryWordChars[i])] = i + 1;
        }
        // сравнить
        logger.info("Буду сравнивать " + tryWord);
        for (int i = 0; i < maxSymbols; i++) {
            if (questSymbols[i] != 0 && trySymbols[i] != 0)
                if (questSymbols[i] == trySymbols[i])
                    tryResult.bulls++;
                else
                    tryResult.cows++;
        }
        logger.info("Возвращается " + tryResult);
        return tryResult;
    }

    public int getMaxSymbols() {
        return maxSymbols;
    }

    public String intArrayToString(int[] ar) {
        if (ar == null || ar.length == 0)
            return "Массив пустой.";
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ar.length; i++)
                sb.append(ar[i]).append("; ");
            return sb.toString();
        }
    }

    public String charArrayToString(char[] ar) {
        if (ar == null || ar.length == 0)
            return "Массив пустой.";
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ar.length; i++)
                sb.append(ar[i]).append("; ");
            return sb.toString();
        }
    }

    public String getQuestWord() {
        return questWord;
    }
}
