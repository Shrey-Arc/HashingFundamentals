import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FlashSaleManager {
    private final Map<String, Integer> stock = new ConcurrentHashMap<>();
    private final Map<String, Queue<Integer>> waitingLists = new HashMap<>();

    public void addProduct(String productId, int count) {
        stock.put(productId, count);
        waitingLists.put(productId, new LinkedList<>());
    }

    public synchronized String purchaseItem(String productId, int userId) {
        int currentStock = stock.getOrDefault(productId, 0);
        if (currentStock > 0) {
            stock.put(productId, currentStock - 1);
            return "Success, " + (currentStock - 1) + " units remaining";
        } else {
            Queue<Integer> waitList = waitingLists.get(productId);
            waitList.add(userId);
            return "Added to waiting list, position #" + waitList.size();
        }
    }
}
