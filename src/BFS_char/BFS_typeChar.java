package BFS_char;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_typeChar {
   public int soCanh, soDinh;
   public ArrayList<Character>[] adj; 
   public boolean[] visited;
   public String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BFS_char\\input.txt";
   public String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BFS_char\\output.txt";
   public FileWriter writer;
   
   public BFS_typeChar() {
	   try {
           writer = new FileWriter(outPath);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void input() {
	   try {
		   FileInputStream fis = new FileInputStream(inPath);
		   BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		   String line = br.readLine(); // Đọc từng dòng
		   String[] tokens = line.split(" ");
		   soDinh = Integer.parseInt(tokens[0].toString());
		   soCanh = Integer.parseInt(tokens[1].toString());
		   
		   adj = new ArrayList[1001];
           visited = new boolean[1001];
		   
		   for(int i = 0;i<soDinh;i++) {
			   adj[i] = new ArrayList<>(); // Khởi tạo đỉnh
		   }
		   
		   for(int i = 0; i < soCanh;i++) {
			   line = br.readLine();
               tokens = line.split(" ");
			   char x = tokens[0].charAt(0);
			   char y = tokens[1].charAt(0);
			   adj[x-'A'].add(y);
		   }
		   Arrays.fill(visited, false);
		   br.close();
	} catch (Exception e) {
		
	}
   }
   public void BFS_TimDuong(char dinh) {
	   Queue<Character> q = new LinkedList<>();
	   q.add(dinh);
	   visited[dinh - 'A'] = true;
	   System.out.print("=> ");
	   while(!q.isEmpty()){
		   char v = q.poll(); // Đỉnh đầu hàng đợi
		   System.out.print(" "+v);
		   if(adj[v - 'A'] != null) {
			   for(Character x: adj[v-'A']) {
				   if(!visited[x - 'A']) {
					   q.add(x);
					   visited[x-'A'] = true;
				   }
			   }
		   }
		   
	   }
	   Arrays.fill(visited, false);
   }
   
   public void BFS_TimDinh(char u) {
       try {
           Queue<Character> q = new LinkedList<>();
           char[] path = new char[soDinh + 10];
           boolean[] visited = new boolean[soDinh + 10];
           Arrays.fill(path, '\0');
           Arrays.fill(visited, false);

           q.add('A');
           visited['A' - 'A'] = true;
           path['A' - 'A'] = '\0';
           System.out.println("====================================================================");
           writer.write("==========================================================================" + System.lineSeparator());
           System.out.println("Phat trien trang thai\tTrang thai ke\t\tDanh Sach L");
           writer.write("Phat trien trang thai\tTrang thai ke\t\tDanh Sach L" + System.lineSeparator());
           System.out.println("====================================================================");
           writer.write("==========================================================================" + System.lineSeparator());

           boolean kt = false;

           while (!q.isEmpty()) {
               char x = q.poll();

               if (x == u) {
                   kt = true;
                   System.out.print("Found " + u);
                   writer.write("Found " + u);
                   System.out.println();
                   writer.write(System.lineSeparator());
                   System.out.print("=> ");
                   writer.write("=> ");
                   inDuongDi(path, u, true);
                   System.out.println();
                   writer.write(System.lineSeparator());
                   break;
               }
               if (adj[x - 'A'] != null) {
                   System.out.print(x + "\t\t\t");
                   writer.write(x + "\t\t\t");
                   System.out.print("|");
                   writer.write("|");
                   for (char c : adj[x - 'A']) {
                       System.out.print(c + " ");
                       writer.write(c + " ");
                   }
                   System.out.print("\t\t\t|");
                   writer.write("\t\t\t|");

                   for (char k : adj[x - 'A']) {
                       if (!visited[k - 'A']) {
                           q.add(k);
                           visited[k - 'A'] = true;
                           path[k - 'A'] = x;
                       }
                   }
                   inDSachL(q);
               }
           }
           if (!kt) {
               System.out.print("Not Found " + u + " !");
               writer.write("Not Found " + u + " !");
           }
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           try {
               writer.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

   }

   public void inDSachL(Queue<Character> queue) {
       try {
           System.out.print("|");
           writer.write("|");

           for (char c : queue) {
               System.out.print(c + " ");
               writer.write(c + " ");
           }
           System.out.println();
           writer.write(System.lineSeparator());
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public void inDuongDi(char[] path, char dinh, boolean isLast) {
    	try {
    		if (dinh == '\0') return;
    		inDuongDi(path, path[dinh - 'A'], false);
    		System.out.print(dinh);
            writer.write(String.valueOf(dinh));
            if (!isLast) {
                System.out.print("->");
                writer.write("->");
            }
		} catch (IOException e) {
            e.printStackTrace();
        }	
    }
}