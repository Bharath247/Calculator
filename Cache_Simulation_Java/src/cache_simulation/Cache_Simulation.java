/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache_simulation;

import java.util.*;
/**
 *
 * @author Bharath Kumar N L
 */
public class Cache_Simulation {
    public static void main(String[] args) {
        int element, write_Element = 0;
        int sets;
        int r_hit =0, r_miss =0,w_hit =0, w_miss =0;
        int Choice ,block_test = 0, off_set = 0;
        int block_write = 0,offset_write = 0;
        
        //Variable for User Input
        Scanner Scan_Variable = new Scanner(System.in);
        
        Main_Memory M1 = new Main_Memory();
        Cache C1 = new Cache();
        System.out.println("Direct Mapped Cache " + C1.Calc_No_Of_sets());
        
        //Initialising Main Memory and cache Memory
        
        M1.initMain();
        C1.initCache();
        C1.setTag();
        
        //Loop for User Inputs
        
        do{
            System.out.println("Enter your Choice");
            System.out.println("[0] - To read from memory \n[1] - To write from Memory \n[2] - Quit");
            Choice = Scan_Variable.nextInt();
            switch(Choice){
                case 0: {
                    do{
                        try{
                        System.out.print("Enter Block no :");
                        block_test = Scan_Variable.nextInt(); //Variable to access the memory block
                        System.out.print("Enter offset value :");
                        off_set = Scan_Variable.nextInt(); //offset value to access the memory block
                        }catch(InputMismatchException e){
                            System.out.println("Characters not allowed, Program exiting.....");
                            System.exit(0); //If any characters are entered as the input the program exits                          
                        }
                    } while(M1.check_input(block_test,off_set));
                        C1.get_CPU_action(block_test,off_set);
                        //Performing read operation
                        element = M1.Main_Mem_Array[block_test][off_set]; //Used to search for element is present in Cache.
                        if(C1.is_cache_hit(element) == true){
                            r_hit = r_hit + 1;
                            System.out.println("Cache Read Hit -> Block [" + block_test + "]" + " Off_set [" + off_set + "]");
                        }
                        else{
                            r_miss = r_miss + 1;
                            System.out.println("Cache Read Miss -> Block [" + block_test + "]" + " Off_set [" + off_set + "]");
                            C1.load_from_Mem(block_test,M1.Main_Mem_Array);
                        }
                        System.out.println("==================Cache Performance======================");
                        System.out.println("Read Hits : " + r_hit + "\nRead Misses :" + r_miss);
                        System.out.println("Write Hits : " + w_hit + "\nWrite Misses " + w_miss);
                        System.out.println("==========================================================");
                       
                        M1.Print_Mem();
                        C1.print_Cache(block_test);
                }break;
                
                case 1: {
                    do{
                        try{
                        System.out.print("Enter Block no :");
                        block_write = Scan_Variable.nextInt(); //Variable to access the memory block
                        System.out.print("Enter offset value :");
                        offset_write = Scan_Variable.nextInt(); //offset value to access the memory block
                        System.out.println("Enter the Element to write to Memory");
                        write_Element = Scan_Variable.nextInt(); //Element that replaces the one in main memory
                    }catch(InputMismatchException e){
                            System.out.println("Characters Not allowed, Program exiting....");
                            System.exit(0);
                    }
                    }while(M1.check_input(block_write,offset_write));
                        M1.get_CPU_action(block_write, offset_write,write_Element);
                        {
                        if(M1.is_cache_hit(block_write, offset_write, write_Element) == true){
                            w_hit = w_hit + 1;
                            System.out.println("Cache Write Hit -> Block [" + block_write + "]" + " Off_set [" + offset_write + "]");
                            C1.write_to_Cache(block_write,offset_write,write_Element, M1.Main_Mem_Array);
                        }
                        else{
                            w_miss = w_miss + 1;
                            System.out.println("Cache Write Miss -> Block [" + block_write + "]" + " Off_set [" + offset_write + "]");
                            M1.Store_to_Mem(block_write, offset_write, write_Element);
                            C1.write_to_Cache(block_write,offset_write,write_Element, M1.Main_Mem_Array);
                        }
                        System.out.println("==================Cache Performance======================");
                        System.out.println("Read Hits : " + r_hit + "\nRead Misses :" + r_miss);
                        System.out.println("Write Hits : " + w_hit + "\nWrite Misses " + w_miss);
                        System.out.println("==========================================================");

                        M1.Print_Mem();
                        C1.print_Cache(block_write);
                }
                }
                break;
                
                case 2: System.out.println("Exiting... ");
                        break;
                        
                default: System.out.println("Wrong Input, Please try again...");
                        break;
                        
            }
        }while(Choice != 2);
    }
}