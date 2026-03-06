import java.util.*;

public class DNSCache {
    class DNSEntry {
        String ip;
        long expiryTime;
        DNSEntry(String ip, long ttlSeconds) {
            this.ip = ip;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000);
        }
    }

    private final Map<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {
        DNSEntry entry = cache.get(domain);
        if (entry == null) return "Cache MISS";

        if (System.currentTimeMillis() > entry.expiryTime) {
            cache.remove(domain);
            return "Cache EXPIRED";
        }
        return "Cache HIT -> " + entry.ip;
    }

    public void addToCache(String domain, String ip, long ttl) {
        cache.put(domain, new DNSEntry(ip, ttl));
    }
}
