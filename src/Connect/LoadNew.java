/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

/**
 *
 * @author WIN 10
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Model.ThongTin;

/**
 *
 * @author WIN 10
 */
public class LoadNew {
    public ArrayList<ThongTin>loadnew(){
        ArrayList<ThongTin> listQuestion = new ArrayList<>();
        try{
            File f = new File("C:\\Users\\WIN 10\\Desktop\\TB.txt");
            BufferedReader br = new BufferedReader( new FileReader(f));
            String line = br.readLine();
            while(line!=null){
            String q[]=line.split(";");
            ThongTin ques = new ThongTin(q[0],q[1]);
            listQuestion.add(ques);
            line=br.readLine();
            }
        }catch(IOException e){
        e.printStackTrace();
        }
        return listQuestion;
    }
    public static void main(String []args){
    
        LoadNew ld = new LoadNew();
        ArrayList <ThongTin> lq = new ArrayList<>();
        lq = ld.loadnew();
        
    
    }
}