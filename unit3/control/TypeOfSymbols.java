package unit3.control;

import unit3.util.Util;

public enum TypeOfSymbols {
    NUMBERS(1, "цифры"), RUSSYMBOLS(2, "русские буквы"), LATSYMBOLS(3, "латинские буквы");
    int index;
    String text;

    TypeOfSymbols(int index, String text) {
        this.index = index;
        this.text = text;
    }
    public int getIndex() {
        return index;
    }
    public String getText() {
        return text;
    }

    public String toString() {
        return String.format("index=%d; text=%s; name=%s, ordinal=%d",getIndex(),getText(),name(),ordinal());
    }
}
