/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MealyMachineJAVA;

/**
 *
 * @author Pradyumn
 */
import java.util.Scanner;
public class MealyMachineJAVA {

    /**
     * @param args the command line arguments
     */
    
    String dna_sequence,name_of_patient;
    int age;
    double weight,height;
    int counter;
    Scanner input=new Scanner(System.in);
    int state=0;
    int[] out_line;
    int prev_batch,batch;
    
    void set_input(){
        System.out.println("Enter the name of the patient:");
        name_of_patient=input.nextLine();
        System.out.println("Enter the patient's age:");
        age=input.nextInt();
        System.out.println("enter the height(cms) and weight(kg) of the patient:");
        height=input.nextDouble();
        weight=input.nextDouble();
        input.nextLine();
        System.out.println("Enter the sequence:");
        dna_sequence=input.nextLine();
        dna_sequence = dna_sequence + "*";
        int arr_size = dna_sequence.length();
        arr_size = arr_size + 5;
        out_line = new int[arr_size];
    }
    
    void detect_sequence(){
       
        for(int i=0;i<dna_sequence.length();i++){
            
            if(state == 0 && (dna_sequence.charAt(i)=='T' ||dna_sequence.charAt(i)=='A' || dna_sequence.charAt(i)=='G')){
            //no change in the state and output is 0
            out_line[i] = 0;
            }
            else if(state == 0 && dna_sequence.charAt(i)==' '){
            //no change in state and no output
            }
            else if(state == 0 && dna_sequence.charAt(i)=='C' ){
            //change the state
            state = 1;
            out_line[i] = 0;
            }
            
            else if(state == 1 && (dna_sequence.charAt(i)=='T' || dna_sequence.charAt(i)=='G' )){
            // change in the state and output is 0
            state = 0;
            out_line[i] = 0;
            }
            else if(state == 1 && dna_sequence.charAt(i)==' '){
            //change in state and no output
                state = 0;  
            }
            else if(state == 1 && dna_sequence.charAt(i)=='C' ){
            //self
            state = 1;
            out_line[i] = 0;
            }
            else if(state == 1 && dna_sequence.charAt(i)=='A' ){
            //change the state
            state = 2;
            out_line[i] = 0;
            }
            
            else if(state == 2 && (dna_sequence.charAt(i)=='T' || dna_sequence.charAt(i)=='A' )){
            // change in the state and output is 0
            state = 0;
            out_line[i] = 0;
            }
            else if(state == 2 && dna_sequence.charAt(i)==' '){
            //change in state and no output
                state = 0;  
            }
            else if(state == 2 && dna_sequence.charAt(i)=='C' ){
            //To C (state 1)
            state = 1;
            out_line[i] = 0;
            }
            else if(state == 2 && dna_sequence.charAt(i)=='G' ){
            //change the state
            state = 3;
            out_line[i] = 1;                    
            }
            
            else if(state == 3 && (dna_sequence.charAt(i)=='T' || dna_sequence.charAt(i)=='A' || dna_sequence.charAt(i)=='G')){
            // change in the state and output is 0
            state = 0;
            out_line[i] = 0;
            }
            else if(state == 3 && dna_sequence.charAt(i)==' '){
            //change in state and no output
                state = 0;  
            }
            else if(state == 3 && dna_sequence.charAt(i)=='*' ){
            //To final state
            state = 4;
            out_line[i] = 0;
            out_line[i+1] = 0;
            out_line[i+2] = 0;
            out_line[i+3] = 0;
            }
            else if(state == 3 && dna_sequence.charAt(i)=='C' ){
            //change the state
            state = 1;
            out_line[i] = 0;
            }
           
        }
   
    }
    
    void analyse_count(){
       counter=1;
        prev_batch = 0;
        for(int j=0;j<out_line.length;j++){
          if(out_line[j]==1 && out_line[j-1]==0 && out_line[j-2]==0 ){
              if(out_line[j+3]==1){
              counter++;
              }
              else{
              batch=counter;
              counter=1;
              
              }
          }
          if(prev_batch<batch)
              prev_batch=batch;

        }  
    
    }
    
    String result_of_test(){
        String result = null;
        if(prev_batch>=0 && prev_batch<=9){
        result="NOT HUMAN";
        } 
        if(prev_batch>=10 && prev_batch<=35){
        result="NORMAL";
        }
        if(prev_batch>=36 && prev_batch<=39){
        result="HIGH RISK";
        }
        if(prev_batch>=40 && prev_batch<=180){
        result="HUNTINGTON'S DISEASE";
        }
        if(prev_batch>=181){
        result="Alien!!!NOT HUMAN";
        }

        return result;
    }
    
    void generate_report(){
        System.out.println("\n\n");
        System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t\t\t\t HOGWARDS HOSPITAL");    
        System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t NAME: " + name_of_patient);
        System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\t AGE: " + age +"\tHEIGHT: " + height + " cms\tWEIGHT: " + weight +" kg");
        System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n\t The count of CAG continuous repetition is: " + prev_batch);
        System.out.println("\t The report has been analysed by a very intellegent machine");
        System.out.println("\tand the result is: " + result_of_test());
        System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\n\tThe intellegent software is developed by SAI PRADYUMN SHRIVASTAVA");        
    }
    
    public static void main(String[] args) {
        
        MealyMachineJAVA t=new MealyMachineJAVA();
        t.set_input();
        t.detect_sequence();
        t.analyse_count();
        t.generate_report();
        
    }
    
}
