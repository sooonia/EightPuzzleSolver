// 8 Puzzle A* Search Algorithm
// Sonia Kopel

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		// To use a heap:
		EightPuzzle game = new EightPuzzle(new PriorityQueueHeap(), new int[]{3,1,2,0,4,5,6,7,8});

		// To use a linked list:
		// EightPuzzle game = new EightPuzzle(new PriorityQueueLinkedList(), new int[]{3,1,2,0,4,5,6,7,8});



	}

}
