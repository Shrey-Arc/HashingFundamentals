import java.util.*;

public class UserNameChecker {
    private final Map<String, Integer> registeredUsers = new HashMap<>();
    private final Map<String, Integer> attemptCounts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attemptCounts.put(username, attemptCounts.getOrDefault(username, 0) + 1);
        return !registeredUsers.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int i = 1;
        while (suggestions.size() < 3) {
            String suggestion = username + i;
            if (!registeredUsers.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
            i++;
        }
        return suggestions;
    }

    public String getMostAttempted() {
        return Collections.max(attemptCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
