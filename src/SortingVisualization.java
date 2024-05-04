import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class SortingVisualization {
	
	JFrame frame;
	Canvas canvas;
	BufferedImage image;
	
	String alg = "None";
	int swaps = 0;
	int comparisons = 0;
	int writesmain = 0;
	int writesaux = 0;
	int indexa, indexb;
	int check = -1;
	int[] array = new int[2000];
	
	public SortingVisualization() {
		frame = new JFrame("Sorting Visualization");
		canvas = new Canvas();
		frame.setSize(800,800);
		frame.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
		
		canvas.createBufferStrategy(3);
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		
		
		new Thread(() -> {
			while(true) {
				render();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
//		while(true) {
//			shuffle();
//			quicksort();
//			checkSorted();			
//		}
		
		while(0 < 1) {
//			shuffle();
//			bogosort();
//			checkSorted();
			
			shuffle();
			countingsort();
			checkSorted();
			
			shuffle();
			gnomesort();
			checkSorted();
			
			shuffle();
			combsort();
			checkSorted();
			
			shuffle();
			combsort11();
			checkSorted();
			
			shuffle();
			oddevensort();
			checkSorted();
			
			shuffle();
			myinsertionsort();
			checkSorted();
			
			shuffle();
			quicksort();
			checkSorted();
			
			shuffle();
			mergesort();
			checkSorted();
			
			shuffle();
			mergesortinplace();
			checkSorted();
			
			shuffle();
			selectionsort();
			checkSorted();
			
			shuffle();
			doubleselectionsort();
			checkSorted();
			
			shuffle();
			insertionsort();
			checkSorted();
			
			shuffle();
			heapsort();
			checkSorted();
			
			shuffle();
			randomsort();
			checkSorted();
			
			shuffle();
			minheapsort();
			checkSorted();
			
			shuffle();
			bubblesort();
			checkSorted();
			
			shuffle();
			reversebubblesort();
			checkSorted();
			
			shuffle();
			radix16sort();
			checkSorted();
		}
//		for(int i = 0; i < array.length; i++) {
//			if(array[i/2] < array[i]) System.out.println("Not heap");
//		}
	}
	
//	int t = array[i];
//	int start = 0, end = i;
//	System.out.println(i);
//	while(end>start+1) {
//		int mid = (end-start+1)/2;
//		System.out.println(start+", " + mid + ", " + end);
//		if(array[mid] > t) {
//			end = mid;
//		} else {
//			start = mid;
//		}
//	}
//	
//	array[i] = array[start];
//	array[start] = t;
	
	public void bogosort() {
		alg = "Bogosort";
		boolean b = true;
		int j = 0;
		while(!isordered()) {
			shufflealg();
			j++;
			sleep(1,j,1000000);
		}
	}
	
	public void countingsort() {
		alg = "Counting Sort";
		int min = array[0], max = array[0];
		indexa = 0;
		for(int i = 1; i < array.length; i++) {
			indexb = i;
			if(array[i] < min) {
				min = array[i];
				indexa = i;
				comparisons++;
			} else if(array[i] > max) {
				max = array[i];
				indexa = i;
				comparisons+=2;
			} else {
				comparisons+=2;
			}
		}

		int[] aux = new int[max-min+1];
		
		for(int i = 0; i < array.length; i++) {
			aux[array[i]-min] += 1;
			indexa = i;
			indexb = i;
			writesaux++;
			sleep(1, i, 2);
		}
		
		for(int i = 0, j = 0; i < aux.length; i++) {
			for(int k = 0; k < aux[i]; k++) {
				indexa = j;
				indexb = j;
				array[j++] = i+min;
				writesmain++;
			}
			sleep(1, i, 2);
		}
	}
	
	public void gnomesort() {
		alg = "Gnome Sort";
		int i = 0;
		while(i < array.length) {
			if(i==0 || array[i] >= array[i-1]) i++;
			else {
				swap(i, i-1);
				i--;
			}
			comparisons++;
			sleep(1,i,300);
		}
	}
	
	public void combsort() {
		alg = "Combsort";
		int size = array.length;
		int swaps, i;
		do {
			if(size > 1) {
				size /= 1.3;
			}
			swaps = 0;
			i = 0;
			
			while(i+size < array.length) {
				indexa = i;
				indexb = i+size;
				if(array[i] > array[i+size]) {
					swap(i,i+size);
					swaps++;
				}
				comparisons++;
				i++;
				sleep(1,i,4);
			}
			
		} while(size != 1 || swaps != 0);
	}
	
	public void combsort11() {
		alg = "Combsort 11";
		int size = array.length;
		int swaps, i;
		do {
			if(size > 1) {
				size /= 1.3;
			}
			if(size == 9 || size == 10) size = 11;
			swaps = 0;
			i = 0;
			
			while(i+size < array.length) {
				if(array[i] > array[i+size]) {
					swap(i,i+size);
					swaps++;
				}
				comparisons++;
				i++;
				sleep(1,i,4);
			}
			
		} while(size != 1 || swaps != 0);
	}
	
	public void oddevensort() {
		alg = "Odd-Even Sort";
		boolean sorted = true;
		while(sorted) {
			sorted = false;
			for(int i = 1; i < array.length-1; i+=2) {
				if(array[i] > array[i+1]) {
					swap(i, i+1);
					sorted = true;
				}
				comparisons++;
			}	
			for(int i = 0; i < array.length-1; i+=2) {
				if(array[i] > array[i+1]) {
					swap(i, i+1);
					sorted = true;
				}
				comparisons++;
			}
			sleep(2,0);
		}
	}
	
	public void myinsertionsort() {
		alg = "El Moga Sort";
		boolean c = true;
		while(c) {
			c = false;
			for(int i = 0; i < array.length; i++) {
				int t = array[i];
				int start = 0, end = i-1;
				while(end > start) {
					int mid = (end+start)/2;
					if(t > array[mid]) {
						start = mid+1;
					} else {
						end = mid-1;
					}
					comparisons++;
				}
				start = t > array[start] ? start+1:start;
				comparisons++;
				if(start != i) c = true;
				swap(start,i);
				sleep(1,i,200);
			}
		}
	}
	
	//TODO fix quicksort,, add counts
	public void quicksort() {
		alg = "QuickSort";
		quicksort(0,array.length);
	}
	
	public void quicksort(int start, int end) {
		if(end <= start) return;
		int pivot = array[start];
		int i = start-1;
		for(int k = start; k < end; k++) {
			if(array[k] <= pivot) {
				i++;
				swap(k,i);
			}
			comparisons++;
		}
		swap(start,i+1);
//		if(i > 0) swap(start,i-1);
		sleep(1, 0);
//		System.out.println(array[i] + ", " + pivot);
		quicksort(start,i);
		quicksort(i+2,end);
	}
	
	public void radix16sort() {		
		alg = "Radix Base 16 Sort";
		int[] count = new int[16];
		int mask = 0xf;
		int rs = 0;
		int[] aux = new int[array.length];
		while(count[1] != array.length) {
			Arrays.fill(count, 0);
			for(int i = array.length-1; i>=0; i--) { // count bit mask
				indexa = i;
				count[(array[i]&mask)>>rs] += 1;
				writesaux++;
				sleep(1, i, 4);
			}
			for(int i = 1; i < count.length; i++) {
				count[i] += count[i-1];
			}
			for(int i = array.length-1; i>=0; i--) {
				indexa = i;
				int r = (array[i]&mask) >> rs;
				aux[--count[r]] = array[i];
				writesaux++;
				sleep(1, i, 4);
			}
			for(int i = 0; i < array.length; i++) {
				indexa = i;
				array[i] = aux[i];
				writesmain++;
				sleep(1, i,4);
			}
			rs += 4;
			mask <<= 4;
		}
	}
	
	public void mergesort() {
		alg = "MergeSort";
		mergesort(0, array.length);
	}
	
	public void mergesort(int start, int end) {
		if(end-start <= 1) return;
		int mid = (start+end)/2;
		mergesort(start,mid);
		mergesort(mid, end);
		
		int a = start, b = mid;
		int[] merge = new int[end-start];
		for(int i = 0; i < merge.length; i++) {
			writesaux++;
			if(b >= end) {
				merge[i] = array[a];
				a++;
				continue;
			} else if(a >= mid) {
				merge[i] = array[b];
				b++;
				continue;
			}
			if(array[a] < array[b]) {
				merge[i] = array[a];
				a++;
			} else {
				merge[i] = array[b];
				b++;
			}
			comparisons++;
		}
		
		for(int i = 0, j = start; i < merge.length; i++, j++) {
			array[j] = merge[i];
			writesmain++;
		}
		sleep(2, 0);
	}
	
	public void mergesortinplace() {
		alg = "In-place MergeSort";
		mergesortinplace(0, array.length);
	}
	
	public void mergesortinplace(int start, int end) {
		if(end-start <= 1) return;
		int mid = (start+end)/2;
		mergesortinplace(start,mid);
		mergesortinplace(mid, end);
		
		int a = start, b = mid;
		for(int i = 0; i < end-start && a < mid && b < end; i++) {
			comparisons++;
//			System.out.println(a+"," + b);
			if(array[a] < array[b]) {
				a++;
				continue;
			} else {
				insert(b, a);
				b++;
				a++;
				mid++;
			}
			sleep(1,0);
		}
		
		sleep(1, 0);
	}
	
	public void selectionsort() {
		alg = "Selection Sort";
		for(int i = 0; i < array.length; i++) {
			int min = i;
			for(int j = i+1; j < array.length;j++) {
				if(array[j] < array[min]) min = j;
				comparisons++;
			}
			swap(min, i);
			sleep(1, 0);
		}
	}
	
	public void doubleselectionsort() {
		alg = "Double Selection Sort";
		for(int i = 0, i2 = array.length-1; i < i2; ) {
			int min = i;
			int max = i2;
			for(int j = i+1; j < i2+1;j++) {
				if(array[j] < array[min]) min = j;
				
				comparisons++;
			}
//			System.out.println(Arrays.toString(array));

			swap(min, i++);
//			if(i != i2)
			for(int j = i; j < i2+1;j++) {
				if(array[j] > array[max]) max = j;
				
				comparisons++;
			}
			swap(max, i2--);
			sleep(1, 0);
		}
	}
	
	public void insertionsort() {
		alg = "Insertion Sort";
		for(int i = 0; i < array.length; i++) {
			int j = i;
			while(j > 0 && array[j-1] > array[j]) {
				comparisons++;
				swap(j-1,j);
				j--;
			}
			if(j>0) comparisons++;
			sleep(1,0);
		}
	}
	
	public void heapsort() {
		alg = "Heapsort";
		for(int i = 0; i < array.length; i++) {
			int j = i;
			while(array[j] > array[j/2]) {
				swap(j,j/=2);
				sleep(1, 0);
				comparisons++;
			}
			comparisons++;
		}
		
		int size = array.length-1;
		while(size > 0) {
			swap(0,size--);
			int swap = 0;
			int root = 0;
			int child = 1;
			while(child <= size) {
				if(array[child] > array[swap]) {
					swap = child;
				}
				if(++child < size && array[child] > array[swap]){
					swap = child;
				}
				comparisons+=2;
				if(swap==root) break;
				else {
					swap(swap, root);
					root = swap;
					child = root*2;
				}
			}
			sleep(1,0);
		}
	}
	
	public void randomsort() {
		alg = "Randomsort";
		int i = 0;
		Random r = new Random();
		w:while(i < array.length) {
			int k = i + r.nextInt(array.length-i);
			for(int j = i; j < array.length; j++) {
				comparisons++;
				if(array[k] > array[j]) {
					continue w;
				}
			}
			sleep(1,0);
			swap(k,i++);
		}
	}
	
	public void minheapsort() {
		alg = "Minheap Sort";
		for(int i = 0; i < array.length; i++) {
			int j = i;
			while(array[j] < array[j/2]) {
				swap(j,j/=2);
				sleep(1, 0);
				comparisons++;
			}
			comparisons++;
		}
		
		int size = array.length-1;
		while(size > 0) {
			swap(0,size--);
			int swap = 0;
			int root = 0;
			int child = 1;
			while(child <= size) {
				if(array[child] < array[swap]) {
					swap = child;
				}
				if(++child < size && array[child] < array[swap]){
					swap = child;
				}
				comparisons+=2;
				if(swap==root) break;
				else {
					swap(swap, root);
					root = swap;
					child = root*2;
				}
			}
			sleep(1,0);
		}
		
		for(int i = 0, j = array.length-1; i<j; i++, j--) {
			swap(i, j);
			sleep(1,0);
		}
	}
	
	public void bubblesort() {
		alg = "Bubble Sort";
		for(int i = array.length-1; i >= 0; i--) {
			for(int j = i-1; j >= 0; j--) {
				if(array[j] > array[i]) {
					swap(i,j);
				}
				comparisons++;
			}
			sleep(2,0);
		}
	}
	
	public void reversebubblesort() {
		alg = "Reverse Bubble Sort";
		
		for(int i = 0; i < array.length; i++) {
			for(int j = i+1; j < array.length; j++) {
				if(array[j] < array[i]) {
					swap(i,j);
				}
				comparisons++;
			}
			sleep(2,0);
		}
	}
	
	public boolean isordered() {
		for(int i = 0; i < array.length-1; i++) {
			if(array[i] > array[i+1]) {
				comparisons++;
				return false;
			}
		}
		comparisons++;
		return true;
	}
	
	public void checkSorted() {
		for(check = 0; check < array.length; check++) {
			if(array[check] != check) {
				return;
			}
			check++;
			sleep(1,0);
		}
	}

	public void insert(int move, int index) {
		int t = array[move];
		for(int i = move; i > index;) {
			array[i] = array[--i];
		}
		array[index] = t;
		writesmain += move-index;
	}

	public void swap(int[] array, int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
		swaps++;
		writesaux+=2;
	}

	public void swap(int i, int j) {
		int t = array[i];
		array[i] = array[j];
		array[j] = t;
		
		indexa = i;
		indexb = j;
		swaps++;
		writesmain+=2;
	}

	public void sleep(int ms, int i, int mod) {
		if(i%mod!=0) return;
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void sleep(int ms, int ns) {
		try {
			Thread.sleep(ms, ns);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void shuffle() {
		check = -1;
		swaps = 0;
		comparisons = 0;
		writesmain = 0;
		writesaux = 0;
		
		Random r = new Random(27);
		for(int i = array.length-1; i >= 0; i--) {
			int index = r.nextInt(i+1);
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
			sleep(1,i,4);
		}
	}
	
	public void shufflealg() {
		Random r = new Random();
		for(int i = array.length-1; i >= 0; i--) swap(i, r.nextInt(i+1));
	}
	
	public void render() {
		BufferStrategy b = canvas.getBufferStrategy();
		Graphics g = b.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		g.setColor(Color.white);
		int h = 0;
		g.setFont(new Font("Sans", Font.BOLD, 20));
		g.drawString(alg, 10, h+=20);
		g.drawString("Input size: " + array.length, 10, h+=20);
		g.drawString("Swaps: " + swaps, 10, h+=20);
		g.drawString("Comparisons: " + comparisons, 10, h+=20);
		g.drawString("Writes main array: " + writesmain, 10, h+=20);
		g.drawString("Writes aux array: " + writesaux, 10, h+=20);
		for(int i = 0; i < array.length; i++) {
			if(check >= 0 && i < check) g.setColor(Color.green);
			else g.setColor(Color.white);
			double angle = i/(double)array.length*2*Math.PI;
			double radius = array[i]/(double)array.length*400;

			int x = (int) (Math.cos(angle)*radius+400);
			int y = (int) (Math.sin(angle)*radius+400);
			
			g.fillOval(x, y, 4, 4);
		}

		double anglea = indexa/(double)array.length*2*Math.PI;
		double radiusa = array[indexa]/(double)array.length*400;
		double angleb = indexb/(double)array.length*2*Math.PI;
		double radiusb = array[indexb]/(double)array.length*400;
//		g.sets
		if(check < 0) {
			Graphics2D g2 = (Graphics2D)g;
			int xa = (int) (Math.cos(anglea)*radiusa+400);
			int ya = (int) (Math.sin(anglea)*radiusa+400);
			int xb = (int) (Math.cos(angleb)*radiusb+400);
			int yb = (int) (Math.sin(angleb)*radiusb+400);
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(xa-8, ya-8, 16, 16);
			g2.drawRect(xb-8, yb-8, 16, 16);			
		}
		
		
		g.dispose();
		b.show();
	}
	
	
	public static void main(String[] args) {
		new SortingVisualization();
	}
}
