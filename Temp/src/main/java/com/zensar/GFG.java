package com.zensar;
class GFG {
	public static void main(String[] args) {
		Geek g1 = new Geek("aa", 1, 20);
		Geek g2 = new Geek("aa", 1, 21);
		if (g1.hashCode() == g2.hashCode()) {
			if (g1.equals(g2))
				System.out.println("Both Objects are equal. ");
			else
				System.out.println("Both Objects are not equal. ");
		} else {
			System.out.println("Both Objects are not equal.... ");
		}
	}
}
