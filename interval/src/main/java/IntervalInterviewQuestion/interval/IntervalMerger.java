package IntervalInterviewQuestion.interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalMerger {
	
	private List<int[]> intervalList= new ArrayList<int[]>();

	public void addInterval(int[] a) {
		this.intervalList.add(a);
		
	}

	public List<int[]> getResult() {
		return intervalList;
	}
	
/*
	public void sort() {
		
		for(int i=0;i<intervalList.size();i++) {
			if(i!=intervalList.size()-1) {
				if(intervalList.get(i)[0]<=intervalList.get(i+1)[0]) {
					intervalList.get(i)
					
				}
			}
		}
		
		
	}*/

	public void merge() {
	
		for(int i=0;i<intervalList.size()-1;i++) {
				
			//case merge
			//need to sort
			if(intervalList.get(i)[1]>=intervalList.get(i+1)[1]&&intervalList.get(i)[1]<intervalList.get(i+1)[1]) {
				int[] newInterval = {intervalList.get(i)[0],intervalList.get(i+1)[1]};
				intervalList.remove(i);
				intervalList.remove(i+1);
				intervalList.add(newInterval);
			}
		}
		
	}
}
