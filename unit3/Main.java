package unit3;

import unit3.control.Game;
import unit3.view.ViewConsole;
import unit3.util.*;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        MyLog.loggerInit(MyLog.LOG_FILE);
        logger.info("Программа запущена.");
        ViewConsole viewConsole = null;
        try {
            viewConsole = new ViewConsole();
            Game game = new Game(100, viewConsole);
            game.start();
        } catch (Exception e) {
            logger.warning(e.getMessage());
        } finally {
            viewConsole.close();
        }
        logger.info("Программа завершена.");
    }
}