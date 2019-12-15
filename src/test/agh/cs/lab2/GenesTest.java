package agh.cs.lab2;

import org.junit.Test;

public class GenesTest {
    @Test
    public void testRandom(){

        for(int i = 0; i < 10000; i++){
            Genes rand = Genes.randomGenes();
            int[] num = new int[8];
            for(int j = 0; j < 8; j++) num[j] = 0;
            for(int j = 0; j < 32; j++){
                num[rand.genes[j]]++;
            }
            for(int j = 0; j < 8; j++) if(num[j] == 0) System.out.println(rand);
        }

    }
    @Test
    public void testChild(){
        for(int i = 0; i < 10000; i++){
            Genes rand1 = Genes.randomGenes();
            Genes rand2 = Genes.randomGenes();
            Genes child = rand1.createNewGenes(rand2);
            int[] num = new int[8];
            for(int j = 0; j < 8; j++) num[j] = 0;
            for(int j = 0; j < 32; j++){
                num[child.genes[j]]++;
            }
            for(int j = 0; j < 8; j++) if(num[j] == 0) System.out.println(child);
        }
    }
}
