package unit3;

import unit3.control.ProgException;
import unit3.control.UserException;

import java.text.ParseException;

public interface ISymbols {
    int getIndexBySymbol(char symbol) throws UserException;
    int getMaxSymbols();
    String createQuestSymbols(int lenQuestWord) throws UserException, ProgException;
    TryResult getCowsAndBulls(String tryWord) throws UserException;
    String getQuestWord();
}
