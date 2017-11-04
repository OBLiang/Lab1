package Lab1final;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {
    @Test
    public void queryBridgeWords1() throws Exception {

    String bridge1st="";
    String bridge2nd="";
        Matrix mt0=new Matrix();
        ExerciseHash Ehash0=new ExerciseHash();
        String finalstr0=new String();
        finalstr0="to explore strange new worlds to seek out new life and new civilizations to explore new and strange life to go new to walk new just add something ";
        String splitstr0[]=finalstr0.split(" ");
        Ehash0.calcletter(finalstr0);
        mt0.Creatematrix(Ehash0, finalstr0);
        Graph G0=new Graph(mt0.size, splitstr0.length, mt0);
        int[][] path0=G0.Floyd(mt0);
        String dotFormat0=G0.Createpicture(mt0);
        String[] printstr0=G0.LinklistPrint1(mt0);
        String finalbridge=G0.queryBridgeWords1(bridge1st, bridge2nd);
        assertEquals(null,finalbridge);
    }



}