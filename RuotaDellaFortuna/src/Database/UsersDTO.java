package Database;

public class UsersDTO {
    private String id;
    private boolean isAdmin;
    private String name;
    private String surname;
    private String nickname;
    private String email;
    private String password;

    /**
     * Ritorna l'id dell'utente
     *
     * @return l'id dell'utente
     */
    public String getId() {
        return id;
    }

    /**
     * Setta l'id dell'utente
     *
     * @param id l'id da settare
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return <code>true</code> se l'utente e' admin, <code>false</code> in caso contrario
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Setta i permessi admin dell'utente
     *
     * @param admin <code>true</code> per settare l'utente come admin, <code>false</code> in caso contrario
     */
    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }

    /**
     * @return il nome dell'utente
     */
    public String getName() {
        return name;
    }

    /**
     * Setta il nome dell'utente
     *
     * @param name il nome dell'utente
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return il cognome dell'utente
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setta il congome dell'utente
     *
     * @param surname il cognome dell'utente
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return il nickname dell'utente
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setta il nickname dell'utente
     *
     * @param nickname il nickname dell'utente
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return la mail dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setta la mail dell'utente
     *
     * @param email la mail dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return la password codificata dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setta la password dell'utente
     *
     * @param password la password codificata dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public UsersDTO() {
    }

    public UsersDTO(String id, boolean isAdmin, String name, String surname, String nickname, String email, String password) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
