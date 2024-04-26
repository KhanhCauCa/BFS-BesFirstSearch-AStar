package A_sao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A_sao {
    public int n, m;
    public Map<String, List<EdgeWeight>> adj;
    public String inPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\A_sao\\input.txt";
    public String outPath = "D:\\Code_Java\\Java_Project\\LearningAI_Java\\src\\A_sao\\output.txt";
    public FileWriter writer;
    private Map<String, Integer> heuristicValues = new HashMap<>();

   
    public A_sao() {
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
            
            String line = br.readLine();
            String[] tokens = line.split(" ");
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[1]);
            adj = new HashMap<>();

            for (int i = 0; i < n; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                String node = tokens[0].toString();
                int heuristicValue = Integer.parseInt(tokens[1]);
                heuristicValues.put(node, heuristicValue);
                adj.put(node, new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                line = br.readLine();
                tokens = line.split(" ");
                String from = tokens[0].toString();
                String to = tokens[1].toString();
                int weight = Integer.parseInt(tokens[2]);
                adj.get(from).add(new EdgeWeight(to, weight));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void aSao(String a, String b) {
    	try {
    		PriorityQueue<NodeWeight> PQ = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
            Map<String, String> parent = new HashMap<>(); 
            Map<String, Integer> gCosts = new HashMap<>();
            Map<String, Integer> hCosts = new HashMap<>();
            Set<String> set = new HashSet<>();

            PQ.add(new NodeWeight(a, 0, heuristic(a, b), 0));
            parent.put(a, null);
            gCosts.put(a, 0);
            writeToBoth("=============================================================================",writer);  
            String header = String.format("%-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-30s\n", "TT", "TTK", "k(u,v)", "h(v)", "g(v)", "f(v)", "Danh sách L");
            writeToBoth2(header, writer);
            writeToBoth("=============================================================================", writer);
            

            while (!PQ.isEmpty()) {
                NodeWeight x = PQ.poll();
                String xName = x.tenDinh;

                if (xName.equals(b)) {
                	writeToBoth(x.tenDinh + "     | Trạng thái kết thúc", writer);
                	writeToBoth("=> Found " + xName+" !", writer);
                    inDuongDi(parent, b);
                    return;
                }
                
                if (!set.contains(xName)) {
                	set.add(xName);

                    for (EdgeWeight edge : adj.getOrDefault(xName, Collections.emptyList())) {
                        String xDen = edge.dinhDen;
                        int newGCost = gCosts.get(xName) + edge.cost;

                        if (!set.contains(xDen) || newGCost < gCosts.getOrDefault(xDen, Integer.MAX_VALUE)) {
                            int heuristic = heuristic(xDen, b);
                            int fCost = newGCost + heuristic;

                            PQ.add(new NodeWeight(xDen, newGCost, heuristic, fCost));
                            parent.put(xDen, xName);
                            gCosts.put(xDen, newGCost);
                            hCosts.put(xDen, heuristic);

                            List<NodeWeight> sapXep = new ArrayList<>(PQ);
                            Collections.sort(sapXep, Comparator.comparingInt(node -> node.f));
                            String line = String.format("%-5s | %-5s | %-5s  | %-5s | %-5s | %-5s | %-30s\n",
                            		xName, xDen, edge.cost, heuristic, newGCost, fCost, sapXep);
                            writeToBoth2(line, writer);
                        }
                    }
                }
            }
            writeToBoth("Not Found " + b+ " !", writer);
    	}catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    private int heuristic(String node, String goal) {
        return heuristicValues.getOrDefault(node, Integer.MAX_VALUE);
    }

    private void inDuongDi(Map<String, String> parent, String goal) {
    	
    	try {
    		List<String> path = new ArrayList<>();
            String xName = goal;
            while (xName != null) {
                path.add(xName);
                xName = parent.get(xName);
            }
            Collections.reverse(path);
            
            writeToBoth2("=> Đường đi: ", writer);

            for (int i = 0; i < path.size(); i++) {
            	writeToBoth2(path.get(i), writer);

                if (i < path.size() - 1) {
                	writeToBoth2("->", writer);
                }
            }
            writeToBoth("", writer);
            writeToBoth("=> Độ dài ngắn nhất: " + tinhKC(path), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int tinhKC(List<String> p) {
        int kc = 0;

        for (int i = 0; i < p.size() - 1; i++) {
            String x = p.get(i);
            String xNext = p.get(i + 1);

            for (EdgeWeight edge : adj.getOrDefault(x, Collections.emptyList())) {
                if (edge.dinhDen.equals(xNext)) {
                    kc += edge.cost;
                    break;
                }
            }
        }
        return kc;
    }
    private void writeToBoth(String text, FileWriter writer) throws IOException {
        System.out.println(text);
        writer.write(text + System.lineSeparator());
    }
    private void writeToBoth2(String text, FileWriter writer) throws IOException {
        System.out.print(text);
        writer.write(text);
    }
}