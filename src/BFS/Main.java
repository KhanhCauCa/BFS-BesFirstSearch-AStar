package BFS;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		BFS_AI b = new BFS_AI();
		b.input();
		int lc = 0;
		do {
			System.out.println("------------------MENU-------------------");
			System.out.println("|1. BFS theo đường đi từ 1 đỉnh cho trước|");
			System.out.println("|2. BFS tìm đường đi đến 1 đỉnh bất kì   |");
			System.out.println("|3. Thoát !                              |");
			System.out.println("-----------------------------------------");
			System.out.print("Nhập lựa chọn: ");  lc = sc.nextInt();
	        if(lc == 1) {
	        	System.out.print("Nhập đỉnh bạn muốn đi: ");
	        	int v = sc.nextInt();
	        	b.BFS_TimDuong(v);
	        	System.out.println();
	        } else if(lc == 2) {
	        	System.out.print("Nhập đỉnh bạn muốn tìm: ");
	        	int v = sc.nextInt();
	        	b.BFS_TimDinh(v);
	        	System.out.println();
	        } else if(lc == 3) {
	        	break;
	        }
		}while (lc != 0);   
		sc.close();
    }
}