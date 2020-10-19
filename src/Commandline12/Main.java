package Commandline12;

import java.io.IOException;

public class Main extends DictionaryCommandline {
    public static void main(String[] args) throws IOException {
        DictionaryCommandline dm = new DictionaryCommandline();
        //dm.dictionaryBasic();
        //dm.dictionaryAdvanced();
        dm.insertFromFile();
        //dm.dictionarySearcher();
        dm.addword();
        //dm.dictionaryExportToFile();
        dm.dictionaryLookup();
        dm.dictionaryExportToFile();
    }
}
