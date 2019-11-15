package Database;

/**
 * La classe dei Data Transfer Objects relativi alle tuple della tabella "MancheJoiners". I suoi metodi permettono la costruzione e l'ottenimento dei campi dell'oggetto
 */
public class MancheJoinersDTO {
    private ManchesDTO manche;
    private UsersDTO user;
    private boolean observer;

    public ManchesDTO getManche() {
        return manche;
    }

    public void setManche(ManchesDTO manche) {
        this.manche = manche;
    }

    public UsersDTO getUser() {
        return user;
    }

    public void setUser(UsersDTO user) {
        this.user = user;
    }

    public boolean isObserver() {
        return observer;
    }

    public void setObserver(boolean observer) {
        this.observer = observer;
    }

    public MancheJoinersDTO(ManchesDTO manche, UsersDTO user, boolean observer) {
        this.manche = manche;
        this.user = user;
        this.observer = observer;
    }

    public MancheJoinersDTO(){}
}
