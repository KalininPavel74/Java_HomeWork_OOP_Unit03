package unit3.control;

import unit3.*;
import unit3.model.*;
import unit3.util.Util;

import java.util.logging.Logger;

public class Game {
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    protected int maxTry;
    protected IView view;
    protected TypeOfSymbols typeOfSymbols;
    protected int lenQuestWord;
    protected ISymbols symbols;
    protected int index;
    public Game(int maxTry, IView view) throws UserException, ProgException {
        this.maxTry = maxTry;
        this.view = view;
        view.viewText("Игра \"Быки и Коровы\"");
        view.viewText("------------------------------");
        typeOfSymbols = this.view.getTypeOfSymbols();
        logger.info(""+typeOfSymbols.toString());
        switch (typeOfSymbols) {
            case NUMBERS: symbols = new SymbolsNumbers(); break;
            case RUSSYMBOLS: symbols = new SymbolsRusLetters(); break;
            case LATSYMBOLS: symbols = new SymbolsLatLetters(); break;
            default: throw new ProgException("Неизвестный набор символов.");
        }
        this.lenQuestWord = view.getLenQuestWord(typeOfSymbols, symbols.getMaxSymbols());
        symbols.createQuestSymbols(lenQuestWord);
        view.viewText("-------------------------------");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<lenQuestWord; i++)
            sb.append("X");
        view.viewText("Загадано слово ("+typeOfSymbols.getText()+") "+sb.toString());
    }
    public void start() throws UserException {
        logger.info("Игра началась ...");
        index = maxTry;
        logger.info("Всего попыток "+index);
        StringBuilder sb = new StringBuilder();
        sb.append("История ходов:\n");
        boolean win = false;
        while(index>0) {
            logger.warning("Попытка "+(maxTry - index + 1));
            sb.append("Попытка ").append(maxTry - index + 1);
            String tryWord = view.getTrySymbols(lenQuestWord);
            sb.append(" - ").append(tryWord);
            TryResult tryResult = symbols.getCowsAndBulls(tryWord);
            sb.append(" - ").append(tryResult).append("\n");
            view.viewText(tryResult.toString());
            if(tryResult.bulls == lenQuestWord) {
                win = true;
                break;
            }
            index--;
        }
        if(win) {
            view.viewText(Util.symbol(127942)+" Победа на попытке "+(maxTry - index + 1));
        } else {
            view.viewText("Проигрыш. Попытки закончились.");
        }
        view.viewText("Загаднное слово: ---\""+symbols.getQuestWord()+"\"---");
        showHistory(sb);
    }

    private void showHistory(StringBuilder sb) {
        String s = view.getSymbol("\nВывести историю ходов? (да-Д,Y; нет-Н,N):");
        if(s != null && !s.isBlank() && ( s.equals("Y") || s.equals("Д") ) )
            view.viewText(sb.toString());
    }
}
