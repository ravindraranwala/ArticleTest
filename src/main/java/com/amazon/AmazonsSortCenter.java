package com.amazon;

import java.util.Arrays;

public class AmazonsSortCenter {

	public static void main(String[] args) {
		final int truckSpace = 90;
		int[] packagesSpace = { 1, 10, 25, 35, 60 };
		// int[] packagesSpace = { 1, 10, 23, 25, 35, 37, 60 };
		int[] loadedProducts = findProductsFitIn(truckSpace, packagesSpace);
		System.out.println(Arrays.toString(loadedProducts));
	}

	public static int[] findProductsFitIn(int truckSpace, int[] packagesSpace) {
		final int effectiveTruckSpace = truckSpace - 30;
		Arrays.sort(packagesSpace);
		// To store the indexes of the result pair.
		int pkgOne = -1, pkgTwo = -1;
		for (int l = 0, r = packagesSpace.length - 1; l < r;) {
			int diff = effectiveTruckSpace - (packagesSpace[l] + packagesSpace[r]);
			if (diff > 0)
				l++;
			else if (diff < 0)
				r--;
			else {
				/*
				 * If you have multiple pairs, select the pair with the largest package. Just
				 * return the first found package pair and that would include the largest
				 * package among all the ties. So that does the trick here !
				 */
				pkgOne = l;
				pkgTwo = r;
				break;
			}
		}
		return new int[] { pkgOne, pkgTwo };
	}
}
