package agh.cs.lab2;

import java.util.Arrays;
import java.util.Random;

public class Genes {
    int[] genes;
    public Genes(int[] genes){
        this.genes = genes;
    }
    public int takeRand(){
        return genes[new Random().nextInt(32)];
    }

    public Genes createNewGenes(Genes parent){
        int bound1 = new Random().nextInt(29)+1;
        int bound2 = new Random().nextInt(32-bound1)+bound1;

        int[] newGenes = new int[32];

        int[] numbers = new int[8];
        for(int i = 0; i < 8; i++) numbers[i] = 0;

        for(int i = 0; i < bound1; i++){
            newGenes[i] = this.genes[i];
            numbers[this.genes[i]]++;
        }
        for(int i = bound1; i < bound2; i++){
            newGenes[i] = parent.genes[i];
            numbers[parent.genes[i]]++;
        }
        for(int i = bound2; i < 32; i++){
            newGenes[i] = this.genes[i];
            numbers[this.genes[i]]++;
        }

        for(int i = 0; i < 8; i++){
            if(numbers[i]==0){
                int arrayPlace;
                do{
                    arrayPlace = new Random().nextInt(32);
                }
                while(numbers[newGenes[arrayPlace]] < 2);
                numbers[newGenes[arrayPlace]]--;
                newGenes[arrayPlace] = i;
                numbers[i]++;
            }
        }

        Arrays.sort(newGenes);
        return new Genes(newGenes);
    }

    public static Genes randomGenes(){
        int[] newGenes = new int[32];
        int[] numbers = new int[8];
        for(int i = 0; i < 8; i++) numbers[i] = 0;

        for(int i = 0; i < 32; i++){
            int rand = new Random().nextInt(8);
            newGenes[i] = rand;
            numbers[rand]++;
        }

        for(int i = 0; i < 8; i++){
            if(numbers[i] == 0){
                int arrayPlace;
                do{
                    arrayPlace = new Random().nextInt(32);
                }
                while(numbers[newGenes[arrayPlace]] < 2);
                numbers[newGenes[arrayPlace]]--;
                newGenes[arrayPlace] = i;
                numbers[i]++;
            }
        }
        Arrays.sort(newGenes);
        return new Genes(newGenes);
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < 32; i++){
            str += genes[i];
        }
        return str;
    }
}
