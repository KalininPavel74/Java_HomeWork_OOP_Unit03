package unit3;

import unit3.util.Util;

public class TryResult {
    public int cows = 0;
    public int bulls = 0;

    @Override
    public String toString() {
        return String.format("%s коровы = %d;  %s быки = %d"
                , Util.symbol(128004), cows
                , Util.symbol(128046), bulls);
    }
}
