package lab;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Student> students = new HashMap<>();
        students.put("24BCS80373", new Student("24BCS80373", "Krishna", 0, 1));
        students.put("ABC12345", new Student("ABC12345", "Rahul", 50, 0));
        students.put("KRG55555", new Student("KRG55555", "Amit", 0, 2));

        AssetStore store = new AssetStore();
        store.addAsset(new Asset("LAB-101", "HDMI Cable", true, 1));
        store.addAsset(new Asset("LAB-102", "Projector", true, 3));
        store.addAsset(new Asset("LAB-103", "LAN Cable", false, 1));
        store.addAsset(new Asset("LAB-104", "Microscope", true, 2));

        CheckoutService service = new CheckoutService(students, store);

        System.out.print("Enter number of checkout requests: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.println("Request " + (i+1));

            System.out.print("Enter UID: ");
            String uid = sc.nextLine();

            System.out.print("Enter Asset ID: ");
            String asset = sc.nextLine();

            System.out.print("Enter Hours: ");
            int hrs = sc.nextInt();
            sc.nextLine();

            CheckoutRequest req = new CheckoutRequest(uid, asset, hrs);

            try {
                String receipt = service.checkout(req);
                System.out.println("SUCCESS: " + receipt);

            } catch (IllegalArgumentException e) {
                AuditLogger.logError(e);

            } catch (NullPointerException e) {
                AuditLogger.logError(e);

            } catch (SecurityException e) {
                AuditLogger.logError(e);

            } catch (IllegalStateException e) {
                AuditLogger.logError(e);

            } finally {
                AuditLogger.log("Attempt finished for UID=" + uid + ", asset=" + asset);
                System.out.println("------------------------");
            }
        }

        sc.close();
    }
}