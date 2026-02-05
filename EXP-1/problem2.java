interface IdentityVerification {
    void verify();
}

interface EnvironmentCheck {
    void check();
}

interface BehaviourMonitoring {
    void monitor();
}

class HumanIdentity implements IdentityVerification {
    public void verify() {
        System.out.println("Human assisted identity verification");
    }
}

class AIIdentity implements IdentityVerification {
    public void verify() {
        System.out.println("AI based identity verification");
    }
}

class AIEnvironment implements EnvironmentCheck {
    public void check() {
        System.out.println("AI environment checking");
    }
}

class AIBehaviour implements BehaviourMonitoring {
    public void monitor() {
        System.out.println("AI behaviour monitoring");
    }
}

class HumanBehaviour implements BehaviourMonitoring {
    public void monitor() {
        System.out.println("Human behaviour monitoring");
    }
}

class ProctoringController {

    private IdentityVerification identity;
    private EnvironmentCheck environment;
    private BehaviourMonitoring behaviour;

    public ProctoringController(
            IdentityVerification identity,
            EnvironmentCheck environment,
            BehaviourMonitoring behaviour) {
        this.identity = identity;
        this.environment = environment;
        this.behaviour = behaviour;
    }

    public void runSession() {
        if(identity != null)
            identity.verify();

        if(environment != null)
            environment.check();

        if(behaviour != null)
            behaviour.monitor();

        System.out.println("Proctoring pipeline executed\n");
    }
}

public class problem2 {
    public static void main(String[] args) {

        ProctoringController exam1 =
                new ProctoringController(
                        new HumanIdentity(),
                        null,
                        null);
        exam1.runSession();

        ProctoringController exam2 =
                new ProctoringController(
                        new AIIdentity(),
                        new AIEnvironment(),
                        new AIBehaviour());
        exam2.runSession();

        ProctoringController exam3 =
                new ProctoringController(
                        new AIIdentity(),
                        new AIEnvironment(),
                        new HumanBehaviour());
        exam3.runSession();
    }
}
