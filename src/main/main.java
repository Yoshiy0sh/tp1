package main;

public class main {
	private static void iter() {
		for (int i = 0; i < 10; i++) {
			if(i < 5) System.out.println(i);
			else break;
		}
		System.out.println("fin");
	}
	
	public static void main(String[] args) {
		iter();
	}
}
