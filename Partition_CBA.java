
//----------------------------------------------
//
// Name: Caleb Appiagyei
//
// Date: 1/21/21
//
// Program: Partition
//
// Description: This program prompts the user
// for a text file and performs a timed 
// partitioning of the input set into subsets
//
//-----------------------------------------------

// Import your classes
import java.util.*;

// Import the file I/O routines
import java.io.*;

// Import all text classes
import java.text.*;

public class Partition_CBA 
{
   /**
    * @param args
    */
   public static void main(String[] args)
   {
   
      System.out.println("*** P|r|o|g|r|a|m|-|P|a|r|t|i|i|t|i|o|n ***\n");
   
   // Create your variables
      String inputfile;
      long et;
      int n, filesum = 0, length, s1a1sum = 0, s2a1sum = 0, s1a2sum = 0, s2a2sum = 0, skip1 = 1, skip2 = 0;
      double alg1ros, alg2ros;
   
   // Name your Scanner class
      Scanner input = new Scanner (System.in);
   
   // Prompt the user for the input file
      System.out.print("Input file name: ");
      inputfile = input.next();
      System.out.println();
      
      // Algorithm 1 
      System.out.println("####################################################################");
      System.out.println("Algorithm: Alg1");
      readIntFile(inputfile, 0);
      int algarray[] = readIntFile(inputfile, 1);
      n = algarray.length;
      for( int i = 0; i < algarray.length; i++ ){
         filesum = filesum + algarray[i];
      }
      System.out.println("n: " + n + ", Sum: " + filesum);
      System.out.println("Partitioning ...");
      length = (algarray.length) / 2;
      
      // Create arrays for your two subsets
      int s1a1[] = new int [length];
      int s2a1[] = new int [algarray.length - length];
      
      // Begin tracking the time
      et = System.nanoTime();
      
      // Assign variables to your first subset
      for( int g = 0; g < length; g++ ){
         s1a1[g] = algarray[g]; 
      }
      for( int y = length; y < algarray.length; y++ ){
         s2a1[y - length] = algarray[y];
      }
      
      // Finish tracking time
      et = System.nanoTime() - et;
      
      // Calculate the sums of the subsets
      for(int u = 0; u < s1a1.length; u++){
         s1a1sum = s1a1sum + s1a1[u];
      }
      System.out.print("Sum1: " + s1a1sum);
      for(int k = 0; k < s2a1.length; k++){
         s2a1sum = s2a1sum + s2a1[k];
      }
      System.out.println(", Sum2: " + s2a1sum);
      
      // Calculate the ROS
      if( s1a1sum > s2a1sum ){
         alg1ros = (double)s1a1sum / (double)s2a1sum;
      }
      else {
         alg1ros = (double)s2a1sum / (double)s1a1sum;
      }
      // Format your ROS
      DecimalFormat decFor = new DecimalFormat("0.00000000");
      System.out.print("ROS: " + decFor.format(alg1ros));
      System.out.print(", ET: ");
      System.out.printf("%.2e nsecs",(double)et);
      System.out.println("\n");
      
      // Write an output file for both subsets
      writeIntFile("halfnhalf_s1_" + inputfile, s1a1);
      writeIntFile("halfnhalf_s2_" + inputfile, s2a1);
      
      // Algorithm 2 
      System.out.println("####################################################################");
      System.out.println("Algorithm: Alg2");
      readIntFile(inputfile, 0);
      System.out.println("n: " + n + ", Sum: " + filesum);
      System.out.println("Partitioning ...");
      length = (algarray.length) / 2;
      
       // Create arrays for your two subsets
      int s1a2[] = new int [length];
      int s2a2[] = new int [algarray.length - length];
      
      // Begin tracking the time
      et = System.nanoTime();
      
      // Partition
      for( int t = 0; t < s1a2.length; t++ ){
         s1a2[t] = algarray[skip1];
         skip1 = skip1 + 2;
      }
      
      for( int j = 0; j < s2a2.length; j++ ){
         s2a2[j] = algarray[skip2];
         skip2 = skip2 + 2;
      }
      
      // Finish tracking time
      et = System.nanoTime() - et;
      
       // Calculate the sums of the subsets
      for(int u = 0; u < s1a2.length; u++){
         s1a2sum = s1a2sum + s1a2[u];
      }
      System.out.print("Sum1: " + s1a2sum);
      for(int k = 0; k < s2a2.length; k++){
         s2a2sum = s2a2sum + s2a2[k];
      }
      System.out.println(", Sum2: " + s2a2sum);
      
       // Calculate the ROS
      if( s1a2sum > s2a2sum ){
         alg2ros = (double)s1a2sum / (double)s2a2sum;
      }
      else {
         alg2ros = (double)s2a2sum / (double)s1a2sum;
      }
      
      // Format your ROS
      System.out.print("ROS: " + decFor.format(alg2ros));
      System.out.print(", ET: ");
      System.out.printf("%.2e nsecs",(double)et);
      System.out.println("\n");
      
      // Write an output file for both subsets
      writeIntFile("everyother_s1_" + inputfile, s1a2);
      writeIntFile("everyother_s2_" + inputfile, s2a2);

   
   }
   public static int[] readIntFile(String inFileName, int x){
   // Open input file
      if ( x == 0 )
         System.out.println("Reading input file:" + inFileName);
      File file = new File(inFileName);
      Scanner input = null;
      try {
         input = new Scanner(file);
      }
      catch (FileNotFoundException ex) {
         System.out.println();
         System.out.println("File read error, cannot open: " + inFileName);
         System.exit(1); // Quit the program
      }
      // Determine input file size
      int n = 0;
      while (input.hasNextInt()){
         input.nextInt();
         n++;
      }
      input.close();
      
      // Read integers from input file and store them in an array
      try {
         input = new Scanner(file);
      }
      catch (FileNotFoundException ex){
         System.out.println("File read error, cannot open: " + inFileName);
         System.exit(1); // Quit the program
      }
      int[] intArray = new int[n];
      for (int i = 0; input.hasNextInt(); i++){
         intArray[i] = input.nextInt();
      }
      input.close();
      
      // Return integer array to calling method
      return intArray;
   } // End readIntFile
   
   // Create a method to write your output files
   public static void writeIntFile(String outFileName, int[] intArray)
   {
   // Open Output File
      File file = new File(outFileName);
   
      System.out.println("Writing output file: " + outFileName);
      PrintWriter output = null;
      try
      {
         output = new PrintWriter(file);
      }
      catch (FileNotFoundException ex)
      {
         System.out.println("File write error, cannot create: " + outFileName);
         System.exit(1);
      }
      // write the integers to the file 
      for (int i = 0; i < intArray.length; i++){
         output.print(String.valueOf(intArray[i]) + " ");
      }
      output.close();
   }

}