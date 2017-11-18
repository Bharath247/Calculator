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
public class Cache {
    //Data Members.
    private final static int Cache_Size = 16;
    private final static int Cache_Lines = 8;
    private int Counter = 0;
    private int[] Tag = new int[Cache_Lines]; //Determines which block is copied to Cache memory
    private int[] ValidBit = new int[Cache_Lines]; //If a block is copied to cache memory Validbit becomes 1
    int[][] Cache_Array = new int[Cache_Lines][Cache_Size];
    
    //This function copies the memory block to cache memory
    public void load_from_Mem(int block_test, int[][] Mem_Block){
        for(int i =0 ; i< 8; i++){
            for(int j =0 ; j< 16; j++){
                if( i == (block_test%8)){
                    Cache_Array[i][j] = Mem_Block[block_test][j];
                }
            } 
        }
    }
    
    //When a write occurs, data changed in main memory is copied to cache
    public void write_to_Cache(int block_write,int offset_write,int write_Element, int[][] Mem_Block){
        System.arraycopy(Mem_Block[block_write], 0, Cache_Array[(block_write%8)], 0, Cache_Size);
    }
    
    //Function that initialises cache to 0.
    public void initCache(){
        for(int i =0 ; i < Cache_Lines ; i++){
            for(int j=0; j < Cache_Size ; j++){
                Cache_Array[i][j] = 0;
            }
        }
    }
    
    //Prints the initial values of Cache at the beginning
    public void init_Cache(){
        for(int i =0 ; i < Cache_Lines ; i++){
            for(int j=0; j < Cache_Size ; j++){
                System.out.print( Cache_Array[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    
    //Function that prints the cache after necessary actions have been completed.
    public void print_Cache(int block){
        for(int k=0; k<Cache_Size ; k++)
        {
            if(k==(block%8)){
                ValidBit[k] = 1; // If the block is found make the valid bit to 1
                Tag[k] = block; // Indicate the block to be copied and value of tag is block no 
            }
        }
        System.out.println("===================Cache Memory==========================");
        for(int i =0 ; i < Cache_Lines ; i++){
            System.out.print("Set :" + i + " V :" + ValidBit[i] + " Tag :" + Tag[i] + "\t| ");
            for(int j=0; j < Cache_Size ; j++){
                System.out.print(Cache_Array[i][j] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("=========================================================");
    }
    
    //This function sets the initial value of tag to -99, as given in the project description
    public void setTag(){
        for(int i =0 ; i< Cache_Lines; i++)
            Tag[i] = -99;
    }
    
    //If there is a cache hit it sends true to the function in Cache_SImulation class and increments hit else increments miss
    public boolean is_cache_hit(int element){
        for(int i =0 ; i< Cache_Lines; i++){
            for(int j =0 ; j< Cache_Size; j++){
                if(Cache_Array[i][j] == element)
                {
                    Counter++;
                }
            }
        }
        return (Counter > 0);
    }
    
    //Since we are using direct mapped cache sets = no of blocks in cache and no computation is needed
    public int Calc_No_Of_sets(){
        System.out.print("Sets in Direct Mapped cache is the No of blocks in Cache \nNo of Sets ");
        return Cache_Lines;
    }
    
    //This function tells what operation is carried in the cache
    public void get_CPU_action(int block, int offset){
        System.out.println("get_CPU_action() : Action = Read, Block = " + block + ", Off_Set = " + offset);
    }
}