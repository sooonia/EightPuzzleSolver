// 8 Puzzle A* Search Algorithm
// Sonia Kopel

import java.util.ArrayList;
import java.util.Collections;

public class PriorityQueueHeap<T extends Comparable<T>> extends PriorityQueue<T> {
	ArrayList <Comparable <T>> structure;

	PriorityQueueHeap(){
		super();
		this.structure = new ArrayList<Comparable<T>>();
	}
	
	public Comparable PriorityDequeue(){
		Comparable <T> ret = this.structure.get(0);
		ArrayList <Comparable<T>> temp = new ArrayList <Comparable<T>>();
		temp.add(this.structure.get(this.structure.size() - 1));
		for(int i=1; i <this.structure.size()-1;i++){
			temp.add(this.structure.get(i));
		}
		int loc = 0;
		while(2*loc +2 < temp.size()&&(temp.get(loc).compareTo((T) temp.get(2 * loc + 1)) >0||temp.get(loc).compareTo((T) temp.get(2 * loc + 2))>0)){
			if(temp.size() == 2*loc+2 || temp.get(2*loc+1).compareTo((T) temp.get(2*loc+2))<0){
				Collections.swap(temp, 2*loc+1, loc);
				loc= 2*loc+1;
			}
			else{
				Collections.swap(temp, 2*loc+2, loc);
				loc= 2*loc+2;
			}
		}
		this.structure = temp;
		return ret;
	}
	
	public void PriorityEnqueue(Comparable<T> item){

		if(this.structure.size() == 0){
			this.structure.add(item);
		}
		else{
			this.structure.add(item);
			Comparable<T> added = item;
			int newIdx = this.structure.size() -1;
			int parentIdx = (newIdx -1)/2;
			while(item.compareTo((T) this.structure.get(parentIdx))<0 && parentIdx >=0){
				Collections.swap(this.structure, newIdx, parentIdx);
				newIdx = parentIdx;
				parentIdx = (newIdx -1)/2;
			}
		}
	}

	public void Print(){
		for (int i=0; i<structure.size();i++){
			System.out.print(structure.get(i));
		}
	}
	
	
}
