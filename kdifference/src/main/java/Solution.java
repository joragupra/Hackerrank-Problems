import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int totalNumbers = scanner.nextInt();
		int diff = scanner.nextInt();
		int numbers[] = new int[totalNumbers];
		for (int i = 0; i < totalNumbers; i++) {
			numbers[i] = scanner.nextInt();
		}
		Arrays.sort(numbers);
		solve(totalNumbers, numbers, diff);
	}

	private static void solve(int totalNumbers, int[] numbers, int diff) {
		int solution = 0;
		for (int i = 0; i < totalNumbers - 1; i++) {
			for (int j = i + 1; j < totalNumbers; j++) {
				if (numbers[j] - numbers[i] == diff) {
					solution++;
					break;
				} else if (numbers[j] - numbers[i] > diff) {
					break;
				}
			}
		}
		System.out.println(solution);
	}
}