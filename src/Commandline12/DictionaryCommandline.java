package Commandline12;

import java.io.IOException;

public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords(){
        System.out.println("No\t|English\t\t|Vietnamese");
        for(int i=0;i<listWord.size(); i++){
            System.out.printf("%-4d|%-15s|%s\n",i+1,listWord.get(i).getWord_target(), listWord.get(i).getWord_explain());
        }
    }
    public void  dictionaryBasic() throws IOException {
        insertFromCommandline();
        showAllWords();
    }
    public void dictionaryAdvanced() throws IOException {
        insertFromFile();
        //showAllWords();
        dictionaryLookup();
        addword();
        editword();
        remoteword();
    }
    public void dictionarySearcher(){
        System.out.print("Insert word to search : ");
        String a = sc.nextLine();
        for ( Word w : listWord){
            if (w.getWord_target().startsWith(a)==true) System.out.println(w.getWord_target());
        }
    }

}
