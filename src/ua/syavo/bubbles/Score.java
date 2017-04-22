package ua.syavo.bubbles;


import java.io.*;

public class Score {

    public static Score SCORE = new Score();

    public int scoreOfGame;
    private int HighScore;
    File file = new File("C:\\Temp\\score.txt");

    private Score(){

    }

    public int getHighScore(){
        return HighScore;
    }

    void tryReadingElseCreate() throws IOException {

        try {

            BufferedReader bf = new BufferedReader(new FileReader(file));
            HighScore = Integer.parseInt(bf.readLine());

        } catch (FileNotFoundException e) {
            file.createNewFile();

        }catch (EOFException e){
            HighScore = 0;
        }
        catch (IOException e){
            HighScore = 0;
        }
    }

    void updatingScore() throws IOException {
        if (scoreOfGame > HighScore) {
            HighScore = scoreOfGame;
            FileWriter myFile = new FileWriter(file);
            //BufferedWriter buff = new BufferedWriter(myFile);
            //buff.write(scoreOfGame);
            myFile.write("smitty " + scoreOfGame);
            //buff.close();
            myFile.close();


        }
    }

}
