package Database;

public class MatchWinnersDTO {
    private MatchesDTO match;
    private UsersDTO user;
    private int amount;

    public MatchesDTO getMatch() {
        return match;
    }

    public void setMatch(MatchesDTO match) {
        this.match = match;
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

    public MatchWinnersDTO(MatchesDTO match, UsersDTO user, int amount) {
        this.match = match;
        this.user = user;
        this.amount = amount;
    }

    public MatchWinnersDTO(){}
}
