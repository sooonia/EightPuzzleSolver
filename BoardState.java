// 8 Puzzle A* Search Algorithm
// Sonia Kopel

import java.util.Arrays;

public class BoardState implements Comparable{
	private int[] currentState;
	private BoardState parent;
	private int g;
	private int h;
	
	public BoardState(int[] data){
		this.currentState = new int[9];
		for(int i = 0;i < 9;++i){
			this.currentState[i] = data[i];
		}
		this.parent = null;
		this.g = 0;
		this.h = 0;
	}
	
	public int compareTo(Object item){

		BoardState temp = (BoardState)  item;
		int ret =0;
		if(this.g + this.h > temp.getG() + temp.getH()){
			ret = 1;
		}
		else if(this.g + this.h < temp.getG() + temp.getH()){
			ret=-1;
		}

		return ret;
	}
	

	public boolean equals(Object item){
		// You must implement this method
		// The objects are equal if the currentState[] arrays
		// are identical
		BoardState temp = (BoardState)  item;
		boolean same = true;

		int [] tempState = temp.getCurrentState();

		int ind = 0;
		while(same && ind<9){
			if(tempState[ind] != this.currentState[ind]){
				same = false;
			}
			ind++;
		}

		return same;
	}
	

	public String toString(){
		// " \n" + " "
		String ret;
		String s =  Integer.toString(this.currentState[0]);
		for(int i = 1; i<9; i++){
			s = s + Integer.toString(this.currentState[i]);
		}
		ret = s.substring(0,3) + "\n" +  s.substring(3,6) + "\n" +  s.substring(6) + "\n---";
		return ret;
	}
	
	
	public int[] getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int[] currentState) {
		this.currentState = currentState;
	}

	public BoardState getParent() {
		return parent;
	}

	public void setParent(BoardState parent) {
		this.parent = parent;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

 
}
