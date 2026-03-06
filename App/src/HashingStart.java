import java.util.*;

// --- SYSTEM MODULES ---

class UsernameSystem {
    Map<String, String> users = new HashMap<>();
    Map<String, Integer> attempts = new HashMap<>();
    public String check(String name) {
        attempts.put(name, attempts.getOrDefault(name, 0) + 1);
        if (users.containsKey(name)) return "Taken. Suggestions: " + name + "1, " + name + "_pro";
        users.put(name, "ID-" + name.hashCode());
        return "Available! Registered.";
    }
}

class InventoryManager {
    int stock = 0;
    Queue<String> waiters = new LinkedList<>();
    public String buy(String id) {
        if (stock > 0) { stock--; return "Success! Stock: " + stock; }
        waiters.add(id); return "Waitlisted at #" + waiters.size();
    }
}

class DNSCache {
    Map<String, String> cache = new HashMap<>();
    public String resolve(String domain, String ip) {
        if (cache.containsKey(domain)) return "HIT: " + cache.get(domain);
        cache.put(domain, ip); return "MISS: Cached " + ip;
    }
}

class PlagiarismDetector {
    Map<String, Set<String>> index = new HashMap<>();
    public String check(String docId, String text) {
        String[] words = text.split(" ");
        int matches = 0;
        for (String w : words) {
            if (index.containsKey(w)) matches++;
            index.computeIfAbsent(w, k -> new HashSet<>()).add(docId);
        }
        return "Matches found: " + matches;
    }
}

class Analytics {
    Map<String, Integer> views = new HashMap<>();
    public void log(String url) { views.put(url, views.getOrDefault(url, 0) + 1); }
    public String getStats() { return "Stats: " + views.toString(); }
}

class RateLimiter {
    Map<String, Integer> tokens = new HashMap<>();
    public String access(String id) {
        int count = tokens.getOrDefault(id, 5);
        if (count > 0) { tokens.put(id, count - 1); return "Allowed. Remaining: " + (count - 1); }
        return "Denied! Limit exceeded.";
    }
}

class Autocomplete {
    Map<String, Integer> queries = new HashMap<>();
    public void add(String q) { queries.put(q, queries.getOrDefault(q, 0) + 1); }
    public List<String> search(String pre) {
        List<String> res = new ArrayList<>();
        for (String q : queries.keySet()) if (q.startsWith(pre)) res.add(q);
        return res;
    }
}

class ParkingLot {
    String[] spots = new String[10];
    public String park(String plate) {
        int h = Math.abs(plate.hashCode() % 10);
        for (int i = 0; i < 10; i++) {
            int s = (h + i) % 10;
            if (spots[s] == null) { spots[s] = plate; return "Spot #" + s; }
        }
        return "Full";
    }
}

class FraudDetector {
    public String findPairs(int[] txs, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int x : txs) {
            if (seen.contains(target - x)) return "Fraud Pair: " + x + " & " + (target - x);
            seen.add(x);
        }
        return "Clear";
    }
}

class VideoCache {
    Map<String, String> l1 = new HashMap<>(); // Simplified for Demo
    public String watch(String id) {
        if (l1.containsKey(id)) return "L1 HIT";
        l1.put(id, "Data"); return "L3 MISS -> Cached to L1";
    }
}

// --- MAIN MENU ---

public class HashingStart {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsernameSystem p1 = new UsernameSystem();
        InventoryManager p2 = new InventoryManager();
        DNSCache p3 = new DNSCache();
        PlagiarismDetector p4 = new PlagiarismDetector();
        Analytics p5 = new Analytics();
        RateLimiter p6 = new RateLimiter();
        Autocomplete p7 = new Autocomplete();
        ParkingLot p8 = new ParkingLot();
        FraudDetector p9 = new FraudDetector();
        VideoCache p10 = new VideoCache();

        while (true) {
            System.out.println("\n--- TEST PROBLEMS (1-10) ---");
            System.out.println("\n1. User Name Checker\n2. Inventory Manager\n3. DNS Caching\n4. Plagiarism Detection" +
                    "\n5. Analytics Dashboard\n6. Rate Limiter\n7. Auto Complete\n8. Parking Lot\n9. Fraud Checker" +
                    "\n10. Video Caching");
            System.out.print("\nEnter Problem # (or 0 to exit): ");
            int choice = sc.nextInt(); sc.nextLine();
            if (choice == 0) break;
0
            switch (choice) {
                case 1: System.out.print("Username: "); System.out.println(p1.check(sc.nextLine())); break;
                case 2: System.out.print("Initial Stock: "); p2.stock = sc.nextInt(); sc.nextLine();
                    System.out.print("User ID: "); System.out.println(p2.buy(sc.nextLine())); break;
                case 3: System.out.print("Domain: "); String d = sc.nextLine();
                    System.out.print("IP: "); System.out.println(p3.resolve(d, sc.nextLine())); break;
                case 4: System.out.print("Doc Content: "); System.out.println(p4.check("D1", sc.nextLine())); break;
                case 5: System.out.print("URL Visited: "); p5.log(sc.nextLine()); System.out.println(p5.getStats()); break;
                case 6: System.out.print("API Client ID: "); System.out.println(p6.access(sc.nextLine())); break;
                case 7: System.out.print("Add Query: "); p7.add(sc.nextLine());
                    System.out.print("Search Prefix: "); System.out.println(p7.search(sc.nextLine())); break;
                case 8: System.out.print("Plate #: "); System.out.println(p8.park(sc.nextLine())); break;
                case 9: System.out.print("Enter 3 amounts (space separated): ");
                    int[] a = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
                    System.out.print("Target: "); System.out.println(p9.findPairs(a, sc.nextInt())); break;
                case 10: System.out.print("Video ID: "); System.out.println(p10.watch(sc.nextLine())); break;
                default: System.out.println("Invalid.");
            }
        }
    }
}
