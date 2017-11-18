/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache_simulation;

/**
 *
 * @author Bharath Kumar N L
 */
public class Main_Memory {
    int main_Mem_Size = 16;
    int main_Mem_Lines = 16;
    int Mem = 0;
    int w_Hit =0, w_Miss=0;
    int[][] Main_Mem_Array = new int[main_Mem_Size][main_Mem_Lines];
   
    //Initialies the main memory as described in project description
    public void initMain(){
        for(int i=0;i<main_Mem_Lines;i++){
            for(int j=0;j<main_Mem_Lines;j++)
            {
                Main_Mem_Array[i][j] = Mem;
                Mem++;
            }
            Mem = Mem - 6;
        }
    }
    
    //Prints the contents of main memory
    public void Print_Mem(){
        System.out.println("=================Main Memory=================");
        for(int i=0;i<main_Mem_Lines;i++){
            for(int j=0;j<main_Mem_Lines;j++)
            {
                System.out.print( Main_Mem_Array[i][j] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("=============================================");
    }
    
    //If there is a cache hit it sends true to the function in Cache_SImulation class and increments hit else increments miss
    public boolean is_cache_hit(int block, int offset, int write_Element){
        return (Main_Mem_Array[block][offset] == write_Element);
    }
    
    //Copies the offset value to the memory block of interest
    public void Store_to_Mem(int block_write, int offset_write, int write_Element){
        Main_Mem_Array[block_write][offset_write] = write_Element;
    }
    
    //This function tells what operation is carried in the cache
    public void get_CPU_action(int block_test, int off_set, int element){
        System.out.println("get_CPU_action() : Action = Write, Block = " + block_test + ", Off_Set = " + off_set + " Value = " + element);
    }
    
    //This function handles exception if the input crosses boundary
    public boolean check_input(int block_test,int off_set){
        if(block_test > 15 || off_set > 15){
            System.out.println("PLease Enter Correct values");
            return true;
        }
        else{
            return false;
        }
    }
}
