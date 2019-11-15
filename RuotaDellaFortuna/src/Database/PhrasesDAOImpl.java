package Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'interfaccia {@link PhrasesDAO}
 */
public class PhrasesDAOImpl implements PhrasesDAO {
    private Connection con;
    PhrasesDAOImpl(Connection c){
        this.con=c;
    }
    @Override
    public List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3) throws SQLException {
        String query5Phrases = "SELECT * FROM "+PhraseTable+" WHERE "+PhrasePhraseAttribute+" NOT IN " +
                "(SELECT "+PhrasePhraseAttribute+" FROM Manches M JOIN MancheJoiners MJ ON M.id=MJ.id AND M.number = MJ.number " +
                "WHERE idPlayer = '"+idPlayer1+"' OR idPlayer = '"+idPlayer2+"' OR idPlayer = '"+idPlayer3+"' " +
                "GROUP BY "+PhrasePhraseAttribute+");";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query5Phrases);
        if(resultSet==null)
            return null;
        ArrayList<PhrasesDTO> pDTO = new ArrayList<>();
        int i = 0;
        while(resultSet.next() && i<5) {
            pDTO.add(new PhrasesDTO(resultSet.getString(PhraseThemeAttribute), resultSet.getString(PhrasePhraseAttribute)));
            i++;
        }
        return pDTO;
    }

    @Override
    public boolean addPhrases(ArrayList<PhrasesDTO> phrases) throws SQLException {
        String queryAdd = "";
        for(PhrasesDTO phrasesDTO : phrases){
           queryAdd = "INSERT INTO "+PhraseTable+" ("+PhraseThemeAttribute+","+PhrasePhraseAttribute+") VALUES ";
           queryAdd += "('"+phrasesDTO.getTheme()+"','"+phrasesDTO.getPhrase()+"');";
           Statement stmt = con.createStatement();
           try {
               stmt.executeUpdate(queryAdd);
           }catch (SQLException e){
                System.err.println("Frase non aggiunta");
           }finally {
               queryAdd = "";
           }
       }
        return true;
    }

    @Override
    public List<PhrasesDTO> getAllPhrases() throws SQLException {
        String query = "SELECT * FROM "+PhraseTable+";";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        if(resultSet==null)
            return null;
        ArrayList<PhrasesDTO> pDTO = new ArrayList<>();
        while(resultSet.next())
            pDTO.add(new PhrasesDTO(resultSet.getString(PhraseThemeAttribute), resultSet.getString(PhrasePhraseAttribute)));
        return pDTO;
    }
}
