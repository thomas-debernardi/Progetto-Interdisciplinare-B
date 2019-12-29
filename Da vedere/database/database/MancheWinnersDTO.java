package database;

public class MancheWinnersDTO {
    private ManchesDTO manche;
    private UsersDTO user;
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MancheWinnersDTO(ManchesDTO manche, UsersDTO user, int amount) {
        this.manche = manche;
        this.user = user;
        this.amount = amount;
    }

    public MancheWinnersDTO(){}
}
