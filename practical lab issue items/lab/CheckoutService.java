package lab;

import java.time.LocalDate;
import java.util.HashMap;

public class CheckoutService {

    HashMap<String, Student> students;
    AssetStore store;

    public CheckoutService(HashMap<String, Student> students, AssetStore store) {
        this.students = students;
        this.store = store;
    }

    public String checkout(CheckoutRequest req)
            throws IllegalArgumentException, IllegalStateException,
            SecurityException, NullPointerException {

        ValidationUtil.validateUid(req.uid);
        ValidationUtil.validateAssetId(req.assetId);
        ValidationUtil.validateHours(req.hoursRequested);

        Student s = students.get(req.uid);
        if (s == null)
            throw new NullPointerException("Student not found: " + req.uid);

        Asset a = store.findAsset(req.assetId);

        if (s.fineAmount > 0)
            throw new IllegalStateException("Pending fine exists");

        if (s.currentBorrowCount >= 2)
            throw new IllegalStateException("Borrow limit reached");

        if (!a.available)
            throw new IllegalStateException("Asset unavailable");

        if (a.securityLevel == 3 && !req.uid.startsWith("KRG"))
            throw new SecurityException("Restricted asset. UID not allowed.");

        int hrs = req.hoursRequested;

        if (hrs == 6)
            System.out.println("Note: Max duration selected. Return strictly on time.");

        if (a.assetName.contains("Cable") && hrs > 3) {
            hrs = 3;
            System.out.println("Policy applied: Cables can be issued max 3 hours. Updated to 3.");
        }

        store.markBorrowed(a);
        s.currentBorrowCount++;

        String txn = "TXN-" + LocalDate.now().toString().replace("-", "")
                + "-" + a.assetId + "-" + req.uid;

        return txn;
    }
}