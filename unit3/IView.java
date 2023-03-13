package unit3;

import unit3.control.ProgException;
import unit3.control.TypeOfSymbols;

public interface IView {
    String getSymbol(String text);
    TypeOfSymbols getTypeOfSymbols() throws ProgException;
    int getLenQuestWord(TypeOfSymbols typeOfSymbols, int maxSymbols);

    String getTrySymbols(int lenQuestWord);
    void viewText(String text);
}
