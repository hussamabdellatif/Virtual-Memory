import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class VirtualMemory {

	
	private static long[] array ;
	private static File loc = new File ("/Users/hussamabdellatif/Desktop/bbnnhtew3qst.txt");
	private static RandomAccessFile store;
	private static int ArraySize= 1000;
	private static int RecordLength;
	private static int count =0;
	private final static int VIRTRATIO = 100;
	
	public static int getCount() {
		return count;
	}



	public static void setCount(int count) {
		VirtualMemory.count = count;
	}



	public VirtualMemory(int size, int portion) throws IOException{
		this.ArraySize=1000;
		array  = new long[100];
		Random random = new Random();
		store = new RandomAccessFile(loc, "rw");
		for (int i=0; i<ArraySize; i++){
			store.seek(i);
			store.writeLong(random.nextLong());
		}
		
	}
	
	
	
	public static void put(int index, long value) throws IOException{
		store.seek(index);
		store.writeLong(value);
	
		
			
			
			
			
			
		
			
			
			
		
	}
	
	
	public static void arrayReallocation(int index) throws IOException{
		 
		
		
		
		 int reference = index;
		 int portion = index/100;
		 int lowerBound = portion*100;
		 int higherBound = portion+100;
		 long toStore;
		 
		 
		  
		 
			 for (int i =0; i<100; i++){
			 
			 store.seek(lowerBound);
			 
			 toStore = store.readLong();
			 
			 array[i] = toStore;
			 lowerBound++;
		 
			 }
		
		
		
		
	}
	public static long get(int index) throws IOException{
		
		
		arrayReallocation(index);
		int reference = index;
		int portion = reference/100;
		int lowerBound = portion*100;
		int arrayIndex = index - lowerBound;
		long toReturn = array [arrayIndex];
		
		
		return toReturn;
		
		
	}
	
	public static void main(String [] args) throws IOException{
		
		VirtualMemory array = new VirtualMemory(ArraySize,ArraySize/VIRTRATIO);
		Random random = new Random();
		int index;
		long value;
		long gotten=0;
	
		long startTime= System.nanoTime();
		
		for (int j=0; j<ArraySize;j++){
			
			for(int i=0; i<10; i++){
				index= random.nextInt(ArraySize);
				value = random.nextLong();
				try{
					array.put(index, value);
					gotten = array.get(index);
				}catch (IndexOutOfBoundsException e){
					long upper = ArraySize-1;
					System.out.println("The value" + index + "is outside 0" + upper);
				}
				if (gotten!= value){
					
					System.out.println("error at " +i);
					return;
				}
			}
			if (j==0){
				System.out.println("working");
				
			}else{
				if ((j%50) ==0){
					System.out.println(".");
				}
			}
		}
		long endTime = System.nanoTime();
		System.out.println("\nElapsed time - part 1 (millisec) is " + (endTime - startTime)/1000000); 
		
		
		int j;
		startTime = System.nanoTime();
		for (int i=0; i<ArraySize; i++){
			array.put(i, i);
			gotten = array.get(i);
			if (i != gotten){
				System.out.println("error01");
			}
			j= (ArraySize-1)-i;
			array.put(j,j);
			gotten = array.get(j);
			if (j!= gotten){
				System.out.println("error02");
			}
			
		}
		endTime = System.nanoTime();
		System.out.println("\nElapsed time - part 2 (millisec) is " + (endTime - startTime)/1000000);
		
		int indexValue01 = random.nextInt(ArraySize);
		int prevIndex = indexValue01;
		long storeValue01 = random.nextLong();
		array.put(indexValue01, storeValue01);

		int indexValue02;
		while (prevIndex == (indexValue02 = random.nextInt(ArraySize)));
		prevIndex = indexValue02;
		long storeValue02 = random.nextLong();
		array.put(indexValue02, storeValue02);
		
		startTime = System.nanoTime();
		for (int i = 0; i < (ArraySize); ++i)
		{
			gotten = array.get(indexValue01);
			if (gotten != storeValue01)
			{
				System.out.println("error03");
			}
			gotten = array.get(indexValue02);
			if (gotten != storeValue02)
			{
				System.out.println("error04");
			}
			while (prevIndex == (indexValue01 = random.nextInt(ArraySize)));
			prevIndex = indexValue01;
			storeValue01 = random.nextLong();
			array.put(indexValue01, storeValue01);
			
			while (prevIndex == (indexValue02 = random.nextInt(ArraySize)));
			prevIndex = indexValue02;
			storeValue02 = random.nextLong();
			array.put(indexValue02, storeValue02);
			
			
		}
			
		 endTime = System.nanoTime();
		System.out.println("\nElapsed time - part 3 (millisec) is " + (endTime - startTime)/1000000);
		
		System.out.println("Test is complete");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
