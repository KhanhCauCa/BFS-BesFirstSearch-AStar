package BestFirstSearch;

import java.io.*;
import java.util.*;


public class BestFirstSearch {
    private int n;
    private Map<String, List<String>> adj = new HashMap<>();
    private Map<String, Integer> hm = new HashMap<>();
    private String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\input.txt"; 
    private String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\BestFirstSearch\\output.txt"; 
    public FileWriter writer;

    public BestFirstSearch() {
        try {
            writer = new FileWriter(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() throws IOException {
        FileInputStream fis = new FileInputStream(inPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        
        n = Integer.parseInt(br.readLine());
        
        String line;     
        for(int i = 0; i < n; i++) {
        	line = br.readLine();
        	String[] tokens = line.split(" ");
        	String dinh = tokens[0].toString();
        	int heuristicValue = Integer.parseInt(tokens[1].toString());
        	hm.put(dinh, heuristicValue);
        	adj.put(dinh, new ArrayList<>());
        	
        }
        while((line = br.readLine()) != null && !line.isEmpty()) {
        	String[] tokens = line.split(" ");
        	String from = tokens[0].toString();
        	for(int i = 1; i<tokens.length;i++) {
        		String to = tokens[i].toString();
        		adj.get(from).add(to);
        	}
        }
        br.close();
    }
    public void bestFirstSearch(String start, String goal) throws IOException {        
    	PriorityQueue<Node> PQ = new PriorityQueue<>(
    		Comparator.comparingInt(node -> node.heuristic)
    	); // Khởi tạo PQ sắp xếp theo heurristic tăng dần
        Map<String, String> duongDi = new HashMap<>(); // Khởi tạo Map để lưu trữ đường đi
                                                       // Key là tên đỉnh, và value là tên đỉnh trước đó trên đường đi từ đỉnh bắt đầu.
        Set<String> set = new HashSet<>(); // Khởi tạo Set để lưu trữ các đỉnh đã được phát triển
        
        PQ.add(new Node(start, hm.getOrDefault(start, 0)));
        duongDi.put(start, null); 
        boolean kt = false; 
        
        // In tiêu đề cột
        writeToBoth("==========================================================================================", writer);
        String header = String.format("%-25s | %-25s | %-25s", "Trạng thái phát triển", "Trạng thái kề", "Danh sách L");
        writeToBoth(header, writer);
        writeToBoth("==========================================================================================", writer);

        while (!PQ.isEmpty() && !kt) {
            Node dinhHienTai = PQ.poll();

            if (dinhHienTai.dinh.equals(goal)) {
                kt = true;
                break;
            }

            set.add(dinhHienTai.dinh);

            String ttKe = "";
            List<String> lstDinhKe = adj.get(dinhHienTai.dinh); // Danh sách đỉnh kề đỉnh hiện tại
            if (lstDinhKe != null) { // Kiểm tra có tồn tại không
                for (String dinhKe : lstDinhKe) {
                    if (!set.contains(dinhKe) && !duongDi.containsKey(dinhKe)) {
                        int heuristicDinhKe = hm.getOrDefault(dinhKe, 0);
                        Node nodeDinhKe = new Node(dinhKe, heuristicDinhKe);
                        PQ.add(nodeDinhKe);
                        duongDi.put(dinhKe, dinhHienTai.dinh);
                        ttKe += dinhKe + "-" + heuristicDinhKe + " ";
                    }
                }
            }
            
            // In danh sách L
            String dsL = "";
            List<Node> sapXepPQ = new ArrayList<>(PQ);
            sapXepPQ.sort(Comparator.comparingInt(node -> node.heuristic));
            for (Node node : sapXepPQ) {
                dsL += node.dinh + "-" + node.heuristic + " ";
            }
            String buocHienTai = String.format("%-25s | %-25s | %-25s", dinhHienTai.dinh + "-" + dinhHienTai.heuristic, ttKe.toString(), dsL);
            writeToBoth(buocHienTai, writer);        
        }
        
        if (kt) {
        	String ketThuc = String.format("%-25s | %-25s", goal + "-" + hm.get(goal), "Trạng thái kết thúc", "");
        	writeToBoth(ketThuc, writer);
        	writeToBoth("Found " + goal + "!", writer);
            inDuongDi(duongDi, start, goal);
            
        } else {
        	writeToBoth("Không có đường đi từ " + start + " tới " + goal + " !", writer);            
        }

        writer.close();
    }
    private void inDuongDi(Map<String, String> path, String start, String goal) throws IOException {
        List<String> duongDi = new ArrayList<>();
        String v = goal;
        while (v != null) {
            duongDi.add(v);
            v = path.get(v);
        }
        Collections.reverse(duongDi);
        StringBuilder duongDiStr = new StringBuilder();
        for (int i = 0; i < duongDi.size(); i++) {
            duongDiStr.append(duongDi.get(i));
            if (i < duongDi.size() - 1) {
                duongDiStr.append("->");
            }
        }
        writeToBoth("=> Đường đi: " + duongDiStr.toString(),writer);
        writer.flush();
    }
    private void writeToBoth(String text, FileWriter writer) throws IOException {
        System.out.println(text);
        writer.write(text + System.lineSeparator());
    }
}
