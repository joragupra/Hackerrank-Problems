import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
	
	private static StringReducer initialize() {
    	BufferedReader reader = 
		          new BufferedReader(new InputStreamReader(System.in));
    	int nCadenas = readInteger(reader);
    	String[] cadenas = readStrings(reader, nCadenas);
		return new StringReducer(cadenas);
    }
	
	private static String[] readStrings(BufferedReader reader, int numCadenas){
    	String[] cadenas = new String[numCadenas];
    	try {
			for(int i=0;i<numCadenas;i++){
				cadenas[i] = reader.readLine();
			}
			return cadenas;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	
	private static int readInteger(BufferedReader reader){
    	try {
			return Integer.parseInt(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
    }
	
	public static void main(String[] args){
		StringReducer sol = initialize();
		sol.reduceAll();
	}
	
	static class StringReducer{
		private String[] strings;
		
		private Map<String, String> reductions;
		
		StringReducer(String[] inputStrings){
			this.strings = inputStrings;
			reductions = new HashMap<String, String>();
			reductions.put("ab", "c");
			reductions.put("ac", "b");
			reductions.put("aa", "aa");
			reductions.put("ba", "c");
			reductions.put("bc", "a");
			reductions.put("bb", "bb");
			reductions.put("ca", "b");
			reductions.put("cb", "a");
			reductions.put("cc", "cc");
		}
		
		void reduceAll(){
			for(String s : strings){
				reduce(s);
				System.out.println(min);
				min = 1;
				val = 1;
			}
		}
		
		int min = 1;
		int val = 1;
		
		boolean reduce(String s){
			char[] chars = s.toCharArray();
			int len = chars.length;
			min = chars.length;
			//base cases
			if(chars.length==1){
				val = 1;
				return true;
			}
			if(chars.length == 2 && chars[0]==chars[1]){
				val = 2;
				return true;
			}
			//recursive case
			for(int i = 0;i<chars.length-1;i++){
				if(chars[i]!=chars[i+1]){
					char other = reductions.get(Character.toString(chars[i]).concat(Character.toString(chars[i+1])).intern()).toCharArray()[0];
					String one = "";
					String two = Character.toString(other);
					String three = "";
					if(i==1){
						one = Character.toString(chars[0]);
					}else if(i>1){
						one = new String(Arrays.copyOfRange(chars, 0, i));
					}
					if(i+2<len-1){
						three = new String(Arrays.copyOfRange(chars, i+2, len));
					}else if(i+2==len-1){
						three = Character.toString(chars[i+2]);
					}
					String aux = one + two + three;
					boolean stop = reduce(aux);
					if(stop){
						min = Math.min(min, val);
						return stop;
					}
				}
			}
			return false;
		}
	}
}
