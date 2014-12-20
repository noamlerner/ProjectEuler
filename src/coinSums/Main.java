package coinSums;

import com.sun.javafx.geom.AreaOp.IntOp;


/*In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?*/
public class Main {
	public static void main(String[] args) {
		int count =0;
		for (int i = 200; i >=0; i-=200) {
			for (int j = i; j>=0; j-=100) {
				for(int k = j; k>=0; k-= 50) {
					for(int h = k; h>=0; h-=20){
						for(int g = h; g>=0; g-=10){
							for(int f = g; f>=0; f-=5){
								for(int t = f; t>=0; t-=2){
									count++;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(count);
	}
}
