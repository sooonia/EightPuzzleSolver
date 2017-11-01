// 8 Puzzle A* Search Algorithm
// Sonia Kopel


import sun.plugin.javascript.navig.Array;

import java.util.*;

public class EightPuzzle  {
	
	PriorityQueue<BoardState> openNodesQueue;	
	Map<String, BoardState> closedNodes;
	BoardState GoalState;
	int AllPossibleStates[][];
	
	public EightPuzzle(PriorityQueue<BoardState> dataStructure, int[] Goal){

		openNodesQueue = dataStructure;
		GoalState = new BoardState(Goal);
		generateAllPossibleStates();

		long totalRunTime = 0;
		int statesSolved = 0;
		
		for(int i = 0;i < AllPossibleStates.length;++i){

			if(checkReachable(AllPossibleStates[i], GoalState.getCurrentState())){
				// Do the EightPuzzle/A* algorithm:
				openNodesQueue.empty();
				closedNodes = new HashMap<String, BoardState>();

				++statesSolved;
				long startTime = System.currentTimeMillis();

				boolean goalFound = false;
				openNodesQueue.PriorityEnqueue(new BoardState(AllPossibleStates[i]));
				while(!goalFound){
					BoardState current = (BoardState) openNodesQueue.PriorityDequeue();
					if(this.GoalState.equals(current)){
						int numMoves = printPath(current, 0);
						double Time = (System.currentTimeMillis()-startTime)/1000.0;
						totalRunTime += Time;
						System.out.println("==================================================");
						System.out.println("              PREVIOUS SOLUTION DATA              ");
						System.out.printf("Number of moves: %d, Time Needed:  %.2f Seconds\n", numMoves, (System.currentTimeMillis()-startTime)/1000.0);
						System.out.printf("States solved:   %d, Total Running Time: %.2f Minutes\n", statesSolved, totalRunTime/60.0);
						System.out.println("==================================================");
						goalFound = true;
					}else{
						closedNodes.putIfAbsent(makeKey(current), current);
						BoardState child;
						child = move(current, 0, 1);
						if(child != null && closedNodes.get(makeKey(child)) == null){
							openNodesQueue.PriorityEnqueue(child);
						}
						child = move(current, 0, -1);
						if(child != null && closedNodes.get(makeKey(child)) == null){
							openNodesQueue.PriorityEnqueue(child);
						}
						child = move(current, 1, 0);
						if(child != null && closedNodes.get(makeKey(child)) == null){
							openNodesQueue.PriorityEnqueue(child);
						}
						child = move(current, -1, 0);
						if(child != null && closedNodes.get(makeKey(child)) == null){
							openNodesQueue.PriorityEnqueue(child);
						}
					}
				}


			}
		}
		
	}
	
	
	private String makeKey(BoardState current){
		String retval = "";
		for(int i = 0;i< current.getCurrentState().length;++i){
			retval += current.getCurrentState()[i];
		}
		return retval;
	}
	
	private BoardState move(BoardState current, int rowMove, int colMove){
		int index = 0;
		int[] moved = new int[9];
		for(int i = 0;i < 9;++i){
			moved[i] = current.getCurrentState()[i];
			if(current.getCurrentState()[i] == 0){
				index = i;
			}
		}

		int newrow = index/3 + rowMove;
		int newcol = index%3 + colMove;
		if(newrow >= 0 && newrow <= 2 && newcol >= 0 && newcol <= 2){
			moved[index] = moved[index+3*rowMove+colMove];
			moved[index+3*rowMove+colMove] = 0;
		}
		else{
			return null;
		}

		BoardState child = new BoardState(moved);
		child.setG(current.getG()+1);
		child.setH(manhattan(child.getCurrentState(), current.getCurrentState()));
		child.setParent(current);
		return child;
	}
	
	public boolean checkReachable(int state1[], int state2[]){
		int invCount1 = 0;
		for(int i=0; i<8; i++){
			int current = state1[i];
			if(current!=0){
				for (int j=i+1; j<9;j++){
					int comp = state1[j];
					if(comp!=0 && current > comp){
						invCount1++;
					}
				}
			}
		}

		int invCount2=0;
		for(int i=0; i<8; i++){
			int current = state2[i];
			if(current!=0){
				for (int j=i+1; j<9;j++){
					int comp = state2[j];
					if(comp!=0 && current > comp){
						invCount2++;
					}
				}
			}
		}

		return (invCount1 % 2) == (invCount2 % 2);
	}
	
	public int manhattan(int[] initialState, int[] endState){
		int dist = 0;
		for(int i = 0; i<9; i++){
			int num = initialState[i];
			if(num!=0) {
				int spot = -1;
				int j = 0;
				while (spot < 0) {
					if (endState[j] == num) {
						spot = j;
					}
					j++;
				}

				int hordist = Math.abs(spot%3 - i%3);
				int vertdist = Math.abs(spot/3 - spot/3);
				dist = dist + hordist + vertdist;
			}
		}
		return dist;
	}

	public int printPath(BoardState current, int count){
		if(current != null){
			count = 1 + printPath(current.getParent(), count);
			System.out.print(current.toString() + "---\n");

		}
		return count;
	}


	private void generateAllPossibleStates(){
		System.out.print("Generating All Possible States... ");
		AllPossibleStates = new int[362880][9];


		//int temp[] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(0);
		temp.add(1);
		temp.add(2);
		temp.add(3);
		temp.add(4);
		temp.add(5);
		temp.add(6);
		temp.add(7);
		temp.add(8);

		int count = 0;
		for(int a = 0; a < 9;++a){
			int one = temp.remove(a);
			for(int b = 0; b < 8;++b){
				int two = temp.remove(b);
				for(int c = 0; c < 7;++c){
					int three = temp.remove(c);
					for(int d = 0; d < 6;++d){
						int four = temp.remove(d);
						for(int e = 0; e < 5;++e){
							int five = temp.remove(e);
							for(int f = 0; f < 4;++f){
								int six = temp.remove(f);
								for(int g = 0; g < 3;++g){
									int seven = temp.remove(g);
									for(int h = 0; h < 2;++h){
										int eight = temp.remove(h);
										for(int i = 0; i < 1;++i){
											int nine = temp.remove(i);
											AllPossibleStates[count][0] = one;
											AllPossibleStates[count][1] = two;
											AllPossibleStates[count][2] = three;
											AllPossibleStates[count][3] = four;
											AllPossibleStates[count][4] = five;
											AllPossibleStates[count][5] = six;
											AllPossibleStates[count][6] = seven;
											AllPossibleStates[count][7] = eight;
											AllPossibleStates[count][8] = nine;
											++count;
											temp.add(0, nine);
										}
										temp.add(0, eight);
									}
									temp.add(0, seven);
								}
								temp.add(0, six);
							}
							temp.add(0, five);
						}
						temp.add(0, four);
					}
					temp.add(0, three);
				}
				temp.add(0, two);
			}
			temp.add(0, one);
		}
		System.out.println("Done!\n");
	}
	
}
