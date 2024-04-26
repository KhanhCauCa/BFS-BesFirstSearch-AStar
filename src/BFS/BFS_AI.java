package BFS;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_AI {
	public int n;
	public ArrayList<Integer>[] adj;
    public boolean[] visited;
    
    public void input() {
    	try {
            FileInputStream fis = new FileInputStream("D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BFS\\input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            String[] tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            adj = new ArrayList[n + 1];
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            Arrays.fill(visited, false);

            for (int i = 0; i < m; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                adj[x].add(y);
            }	           
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void BFS_TimDuong(int u) {
        Queue<Integer> q = new LinkedList<>();
        q.add(u);
        visited[u] = true;
        System.out.print("=> ");
        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");
            for (int x : adj[v]) {
                if (!visited[x]) {
                    q.add(x);
                    visited[x] = true;
                }
            }
        }
        Arrays.fill(visited, false);
    }
    public void BFS_TimDinh(int u) {
        Queue<Integer> q = new LinkedList<>();
        int[] path = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);

        q.add(1); 
        visited[1] = true;
        path[1] = 0; 
        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == u) {
                printPath(path, u);
                return;
            }
            for (int k : adj[x]) {
                if (!visited[k]) {
                    q.add(k);
                    visited[k] = true;
                    path[k] = x;
                }
            }
        }
    }

    private void printPath(int[] path, int j) {
        if (j == 0) return; 
        printPath(path, path[j]); // Đệ quy
        System.out.print(j + " ");
    }
}