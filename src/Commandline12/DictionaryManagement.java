package Commandline12;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    private final static String filedata  ="dictionaries.txt";
    //private final static String filewrite  ="dictionaries_write.txt";
    public int n;
    public Scanner sc = new Scanner(System.in);
    private boolean check(String word){
        for (Word i : listWord){
            if (word.trim().equals(i.getWord_target())) {
                System.out.println("The word : " + word + " is already in the dictionary.");
                return false;
            }
        }
        return true;
    }
    public void insertFromCommandline(){
        System.out.println("-------Insert from Commandline---------");
        System.out.print("Insert the number of words :");
        n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Insert word : " );
            String w = sc.nextLine();
            if (check(w)==false) {
                i--;continue;
            }
            else {
                System.out.print("Insert meaning of the word : " );
                String ww = sc.nextLine();
                listWord.add(new Word(w,ww));
            }
        }
    }
    public static void insertFromFile() throws IOException {
        BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filedata), "UTF-8"));
            String line = "";
            String w="",ww = "" ;
            while ((line = br.readLine()) != null){
                if (line.indexOf("@") != -1 && line.indexOf("/") != -1) {
                    if (w != ""){ listWord.add(new Word(w,ww));w="";ww = "" ;}
                    w = line.substring(line.indexOf("@")+1,line.indexOf("/")-1);
                    ww = line.substring(line.indexOf("/"),line.length());
                }
                if (line.indexOf("@") != -1 && line.indexOf("/") == -1) {
                    if (w != ""){ listWord.add(new Word(w,ww));w="";ww = "" ;}
                    w = line.substring(line.indexOf("@")+1,line.length());
                }
                if (line.indexOf("@") < 0) ww = ww.concat("\n").concat(line);
            }
            br.close();
    }
    public void  dictionaryLookup() {
        System.out.print("\nInsert word to search :");
        String str = sc.nextLine();
        int dem1 = 0;
        for (Word w : listWord) {
            if (str.equals(w.getWord_target()) == true) {
                System.out.println(w.getWord_target() + "\n" + w.getWord_explain());
                dem1 = 1;
            }
        }
            if (dem1 == 0) System.out.print("Can not find this word.\n");
    }
    public void addword(){
        String a;
        int dem2 = 0;
        System.out.print("\nInsert word to add : " );
        a = sc.nextLine();
        for ( Word w : listWord){
            if (a.equals(w.getWord_target())==true) {
            System.out.println("This word is already in the dictionary.");
            dem2 ++;
            break;
            }
        }
        if (dem2 == 0){
        System.out.print("\nInsert meaning of the word : " );
        String b = sc.nextLine();
        listWord.add(new Word(a,b));
            System.out.print("Add successfully.\n");
        }
    }
    public void editword(){
        System.out.print("\nInsert word to edit :");
        String a = sc.nextLine();
        int dem3 =0;
        for ( Word w : listWord) {
            if (a.equals(w.getWord_target()) == true) {
                System.out.println("\n" + w.getWord_target() + " : \n" + w.getWord_explain());
                System.out.print("\nInsert edit-meaning :");
                String b = sc.nextLine();
                w.setWord_explain(b);
                System.out.print("\nEdit successfully.\n");
                System.out.println("\n" + w.getWord_target() + " :\n" + w.getWord_explain());
                dem3++;
            }
        }
        if (dem3 ==0) System.out.print("Can not find this word.\n");
    }
    public void remoteword() {
        System.out.print("\nInsert word to remove : ");
        int dem4 = 0,k = -1;
        String a = sc.nextLine();
        for (Word w : listWord) {
            if (a.equals(w.getWord_target()) == true) {
                k = listWord.indexOf(w);
                dem4 ++;
            }
        }
        if (k>=0){
        listWord.remove(listWord.get(k));
        System.out.print("\nRemove successfully.\n");}
        if (dem4 ==0) System.out.print("Can not find this word.\n");
    }
    public static void dictionaryExportToFile() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dictionaries.txt"), "UTF8"));
            int dem = 1;
            out.append("@");
            for(Iterator var3 = listWord.iterator(); var3.hasNext(); ++dem) {
                Word w = (Word)var3.next();
                String var10001 = w.getWord_target().trim();
                out.append(var10001 + "\n" + w.getWord_explain().trim() + "\n@");
            }
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException var5) {
            System.out.println(var5.getMessage());
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        } catch (Exception var7) {
            System.out.println(var7.getMessage());
        }
    }
}
