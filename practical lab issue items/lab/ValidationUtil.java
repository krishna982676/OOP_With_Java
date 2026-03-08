package lab;

public class ValidationUtil {

    public static void validateUid(String uid) {
        if (uid == null || uid.length() < 5)
            throw new IllegalArgumentException("Invalid UID");
    }

    public static void validateAssetId(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException("Invalid Asset ID");
    }

    public static void validateHours(int hrs) {
        if (hrs <= 0 || hrs > 6)
            throw new IllegalArgumentException("Hours must be between 1 and 6");
    }
}