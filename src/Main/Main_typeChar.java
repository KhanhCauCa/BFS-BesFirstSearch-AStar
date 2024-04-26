package Main;
import java.io.IOException;

import java.util.List;
import java.util.Scanner;

import A_sao.A_sao;
import BFS_char.BFS_typeChar;
import BestFirstSearch.BestFirstSearch;
import LogicMenhDe.LogicMenhDe;

public class Main_typeChar {

	public static void main(String[] args) throws IOException {
		Scanner sc  = new Scanner(System.in);
		int lc = 0;
		do {
			System.out.println("-------------------MENU--------------------");
			System.out.println("|1. BFS theo đường đi từ 1 đỉnh cho trước |");
			System.out.println("|2. BFS tìm đường đi đến 1 đỉnh bất kì    |");
			System.out.println("|3. Best First Search                     |");
			System.out.println("|4. Thuật toán A*                         |");
			System.out.println("|5. Chứng minh bác bỏ bằng luật phân giải |");
			System.out.println("|6. Thoát !                               |");
			System.out.println("-------------------Group7------------------");
			System.out.print("Nhập lựa chọn: ");  lc = sc.nextInt();
	        if(lc == 1) {
	        	System.out.print("Nhập đỉnh bạn muốn đi: ");
	        	char v = sc.next().charAt(0);
	    		BFS_typeChar b = new BFS_typeChar();
	    		b.input();
	        	b.BFS_TimDuong(v);
	        	System.out.println();
	        } else if(lc == 2) {
	        	System.out.print("Nhập đỉnh bạn muốn tìm: ");
	        	char v = sc.next().charAt(0);
	    		BFS_typeChar b = new BFS_typeChar();
	    		b.input();
	        	b.BFS_TimDinh(v);
	        	System.out.println();
	        } else if(lc == 3) {
				BestFirstSearch bfs = new BestFirstSearch();
				bfs.input();
				System.out.print("Nhập đỉnh bạn muốn tìm: ");
				String v = sc.next().toString();
				bfs.bestFirstSearch("A", v);	        
	        } else if(lc == 4) {
	        	A_sao a = new A_sao();
	            a.input();
	            System.out.print("Nhập đỉnh bạn muốn tìm: ");
	            String v = sc.next().toString();
	            a.aSao("A", v);
	        } else if(lc == 5) {
	        	LogicMenhDe lg = new LogicMenhDe();
	        	lg.input();
	        	System.out.print("Nhập công thức cần chứng minh: ");
	        	String s = sc.next().toString();
	        	lg.Prove(s);
	        } else if(lc == 6) {
	        	break;
	        }
		}while (lc != 0);   
		sc.close();
	}
}
