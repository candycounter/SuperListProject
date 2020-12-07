import java.util.ArrayList;

public class SuperListRunner
{
	public SuperListRunner(){
		SuperList<Integer> list = new SuperList<Integer>();
		for(int x = 0; x<30; x++){
			int num = (int)(Math.random()*1000)+1;
			list.add(num);
		}
		System.out.println("List: "+list.toString());       //Output the "ArrayList"
		System.out.println("\nSize: "+list.size());			//Output the Size

		SuperList<Integer> stack = new SuperList<Integer>();
		while(list.size() > 0){
			stack.push(list.poll());
		}
		System.out.println("\nStack: "+stack.toString());   //Conversion from "ArrayList" to "Stack"

		SuperList<Integer> queue = new SuperList<Integer>();
		while (stack.size() > 0){
			queue.add(stack.pop());
		}
		System.out.println("\nQueue: "+queue.toString());   //Conversion from "Stack" to "Queue"

		for(int i = 0; i<30; i++){
			list.add(i,0);
		}
		while(list.contains(0)){
			try{
				int index = (int)(Math.random()*30);
				if(list.get(index) == 0){
					int num = queue.poll();
					list.add(index, num);
					list.remove(index+1);
				}
			 } catch (NullPointerException e){}
		}
		System.out.println("\nList Randomized: "+list.toString());     //Conversion from "Queue" to "ArrayList" except in random order

		int overallSum = 0;
		int oddIndexSum = 0;
		int evenIndexSum = 0;
		SuperList<Integer> evenList = new SuperList<Integer>();
		for(int i = 0; i < list.size(); i++){
			overallSum+= list.get(i);
			if(i%2==0)
				evenIndexSum += list.get(i);
			else
				oddIndexSum += list.get(i);

			if(list.get(i) % 2 == 0){
				evenList.add(list.get(i));
			}
		}
		System.out.println("\nSum of List: "+overallSum);          //Sum of the entire SuperList
		System.out.println("Sum of Even Indexed Values: "+evenIndexSum); //Sum of the Even Indexed Values
		System.out.println("Sum of Odd Indexed Values: "+oddIndexSum);   //Sum of the Odd Indexed Values

		while(evenList.size() > 0){
			list.add(evenList.poll());
		}
		System.out.println("\nList with Even Value Duplicates: "+list.toString());  //SuperList with Even Value Duplicates added to the back of the list.

		int checkIndex = 0;
		while(checkIndex < list.size()){
			if(list.get(checkIndex)%3 == 0){
				list.remove(checkIndex);
			}
			else{
				checkIndex++;
			}
		}
		System.out.println("\nList without Numbers Divisible by 3: "+list.toString());        //SuperList without any instances of values that are divisible by 3

		list.add(4, 55555);
		System.out.println("\nList with New Added Number: "+list.toString());                 //SuperList with 55555 added into the list
		sortIntegerList(list);
		System.out.println("\nSorted Number List: "+list.toString());                         //SuperList that was sorted.

		SuperList<Integer> beforeMedian = new SuperList<Integer>();
		SuperList<Integer> afterMedian = new SuperList<Integer>();
		int oddMedian = 0;
		double evenMedian = 0.0;
		if(list.size() % 2 == 0){
			int median1 = list.get(list.size()/2);
			int median2 = list.get(list.size()/2 - 1);
			evenMedian = (median1+median2)/ 2;
			System.out.println("\nMedian: "+evenMedian);                                     //Median for Even Number of Values
			for(int i = 0; i < (list.size()/2 - 1); i++){
				beforeMedian.add(list.get(i));
			}
			for(int i = list.size()/2 + 1; i < list.size(); i++){
				afterMedian.add(list.get(i));
			}
		}
		else{
			oddMedian = (list.size())/2+1;
			System.out.println("\nMedian: "+list.get(oddMedian));                           //Median for Odd Number of Values
			for(int i = 0; i < oddMedian; i++){
				beforeMedian.add(list.get(i));
			}
			for(int i = oddMedian + 1; i < list.size(); i++){
				afterMedian.add(list.get(i));
			}
		}
		System.out.println("Before the Median: "+beforeMedian);
		System.out.println("After the Median: "+afterMedian);

		SuperList<String> stringList = new SuperList<String>();                           //New String SuperList
		String sentence = "The efficiency we have at removing trash has made creating trash more acceptable.";
		while(sentence.contains(" ")){
			stringList.add(sentence.substring(0, sentence.indexOf(" ")));
			sentence = sentence.substring(sentence.indexOf(" ")+1);
		}
		stringList.add(sentence.substring(0, sentence.indexOf(".")));
		System.out.println("\nString List: "+stringList);                              //Each word was stored in the String SuperList

		for(int i=0; i < stringList.size(); i++){
			if(stringList.get(i).length() <= 3){
				stringList.remove(i);
			}
		}
		System.out.println("\nString List without Strings with 3 or less characters: "+stringList);  //String List without Strings with 3 or less characters
		sortStringList(stringList);
		System.out.println("\nSorted String List in Ascending Order: "+stringList);                 //Sorted String List by alphabetical order
		double averageWordLength = 0.0;
		for(int i = 0; i < stringList.size(); i++){
			averageWordLength += stringList.get(i).length();
		}
		averageWordLength /= stringList.size();
		System.out.println("\nAverage Word Length: "+averageWordLength);                          //Average Word Length
	}

	public void sortIntegerList(SuperList<Integer>list){

		for(int i = 1; i < list.size(); i++){
			int num = list.get(i);
			list.remove(i);
			int j = i - 1;
			while (j >=0 && num < list.get(j)){
				list.add(j+1, list.get(j));
				list.remove(j);
				j--;
			}
			list.add(j+1, num);
		}
	}

	public void sortStringList(SuperList<String>list){
		for(int i = 1; i < list.size(); i++){
			String word = list.get(i);
			list.remove(i);
			int j = i-1;
			while (j >=0){
				if(word.compareToIgnoreCase(list.get(j)) > 0){
					break;
				}
				list.add(j+1, list.get(j));
				list.remove(j);
				j--;
			}
			list.add(j+1, word);
		}
	}

	public int compareTo(String other){
		String word = this.toString();
		if(word.compareTo(other)>0) return 1;
		if(word.compareTo(other)<0) return -1;
		return 0;
	}
	public static void main(String[]args){
		SuperListRunner App = new SuperListRunner();
	}
}