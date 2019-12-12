package Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsersDAOImpl implements UsersDAO {
    private Connection con;
    UsersDAOImpl(Connection c){
        this.con=c;
    }
    public boolean addUser(UsersDTO user) throws SQLException {
        String queryAdd = "INSERT INTO "+UserTable+" ("+UserIdAttribute+","+UserTipoAttribute+","+UserNameAttribute+","+
                UserSurnameAttribute+","+UserNicknameAttribute+","+UserEmailAttribute+","+UserPasswordAttribute+") " +
                "VALUES ('"+user.getId()+"',"+(user.isAdmin()? 1 : 0) +",'"+user.getName()+"','"+user.getSurname()+"','"
                +user.getNickname()+"','"+user.getEmail()+"','"+user.getPassword()+"');";

        Statement stmt = con.createStatement();
        boolean res =  stmt.executeUpdate(queryAdd) > 0;
        return res;
    }

    public UsersDTO getUserByEmail(String email) throws SQLException {
        String querySearch = "SELECT * FROM "+UserTable+" WHERE "+UserEmailAttribute+" = '"+email+"';";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(querySearch);
        if(resultSet==null)
            return null;
        if(resultSet.next()) {
            return new UsersDTO(resultSet.getString(UserIdAttribute), resultSet.getInt(UserTipoAttribute) == 1,
                    resultSet.getString(UserNameAttribute), resultSet.getString(UserSurnameAttribute),
                    resultSet.getString(UserNicknameAttribute), email,
                    resultSet.getString(UserPasswordAttribute));
        }else
            return null;
    }

    public UsersDTO getUserByNickname(String nickname) throws SQLException {
        String querySearch = "SELECT * FROM "+UserTable+" WHERE "+UserNicknameAttribute+" = '"+nickname+"';";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(querySearch);
        if(resultSet==null)
            return null;
        if(resultSet.next()) {
            return new UsersDTO(resultSet.getString(UserIdAttribute), resultSet.getInt(UserTipoAttribute) == 1,
                    resultSet.getString(UserNameAttribute), resultSet.getString(UserSurnameAttribute), nickname,
                    resultSet.getString(UserEmailAttribute), resultSet.getString(UserPasswordAttribute));
        }else
            return null;
    }

    public UsersDTO getUserById(String id) throws SQLException {
        String querySearch = "SELECT * FROM "+UserTable+" WHERE "+UserIdAttribute+" = '"+id+"';";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(querySearch);
        if(resultSet==null) {
            return null;
        }
        resultSet.next();
        return new UsersDTO(id,(resultSet.getInt(UserTipoAttribute) == 1), resultSet.getString(UserNameAttribute),
                resultSet.getString(UserSurnameAttribute), resultSet.getString(UserNicknameAttribute),
                resultSet.getString(UserEmailAttribute), resultSet.getString(UserPasswordAttribute));
    }

    public boolean deleteUser(String id) throws SQLException {
        String queryDelete = "DELETE FROM "+UserTable+" WHERE "+UserIdAttribute+" = '"+id+"';";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryDelete) > 0;
    }

    public List<UsersDTO> getAllAdmin() throws SQLException {
        List<UsersDTO> list = new ArrayList<>();
        String queryGet = "SELECT * FROM "+ UserTable + " WHERE "+UserTipoAttribute + " = 1;";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(queryGet);
        if(resultSet == null){
            return list;
        }else{
            while(resultSet.next()){
                list.add(new UsersDTO(resultSet.getString(UserIdAttribute),resultSet.getBoolean(UserTipoAttribute),
                        resultSet.getString(UserNameAttribute),resultSet.getString(UserSurnameAttribute), resultSet.getString(UserNicknameAttribute),
                        resultSet.getString(UserEmailAttribute), resultSet.getString(UserPasswordAttribute)));
            }
            resultSet.close();
            return list;
        }
    }

    public boolean updateUser(UsersDTO user) throws SQLException {
        String queryUpdate = "UPDATE "+UserTable+" SET "+UserNameAttribute+" = '"+user.getName()+"', "+UserSurnameAttribute+" = '"+user.getSurname()+"', "+
                UserNicknameAttribute+" = '"+user.getNickname()+"', "+UserPasswordAttribute+" = '"+user.getPassword()+"' WHERE "+UserIdAttribute+" = '"+user.getId()+"';";
        Statement stmt = con.createStatement();
        boolean bool = stmt.executeUpdate(queryUpdate) > 0;
        return bool;
    }

    public UsersDTO getBestPlayerByManche() throws SQLException {
        String queryBest = "SELECT * FROM " + UserTable + " U JOIN "+MancheWinnersDAO.manchesWinnersTable +" MW ON U.id = MW.idPlayer WHERE "+MancheWinnersDAO.manchesWinnersAmountAttribute+" =" +
                " (SELECT MAX(amount) FROM "+MancheWinnersDAO.manchesWinnersTable+");";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryBest);
        if(rs.next()) {
            UsersDTO user = new UsersDTO();
            user.setNickname(rs.getString(UserNicknameAttribute));
            user.setName(rs.getString(UserNameAttribute));
            user.setId(rs.getString(UserIdAttribute));
            user.setSurname(UserSurnameAttribute);
            user.setEmail(UserEmailAttribute);
            return user;
        }else
            return null;
    }

    public UsersDTO getBestPlayerByMatch() throws SQLException {
        String queryBest = "SELECT * FROM " + UserTable + " U JOIN "+MatchWinnersDAO.matchWinnersTable +" MW ON U.id = MW.idPlayer WHERE "+MatchWinnersDAO.matchWinnersAmountAttribute+" =" +
                " (SELECT MAX(amount) FROM "+MatchWinnersDAO.matchWinnersTable+");";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryBest);
        if(rs.next()) {
            UsersDTO user = new UsersDTO();
            user.setNickname(rs.getString(UserNicknameAttribute));
            user.setName(rs.getString(UserNameAttribute));
            user.setId(rs.getString(UserIdAttribute));
            user.setSurname(UserSurnameAttribute);
            user.setEmail(UserEmailAttribute);
            return user;
        }else
            return null;
    }

    public UsersDTO getUserForMoreManchesPlayed() throws SQLException {
        String queryBest = "SELECT "+UserNicknameAttribute+", COUNT(U."+UserIdAttribute+") AS count FROM " +UserTable+" U JOIN "+MancheJoinersDAO.mancheJoinersTable+" MJ ON U.id = MJ.idPlayer GROUP BY " +
                "U."+UserIdAttribute+" ORDER BY count desc";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryBest);
        if(rs != null) {
            if(rs.next()) {
                UsersDTO user = new UsersDTO();
                user.setNickname(rs.getString(UserNicknameAttribute));
                return user;
            }else
                return null;
        }else
            return null;
    }

    public UsersDTO getUserForBestMancheAverage() throws SQLException {
        String queryBest = "SELECT U."+UserNicknameAttribute+", AVG(MW."+MancheWinnersDAO.manchesWinnersAmountAttribute+") AS avg FROM " +UserTable+" U JOIN "+MancheWinnersDAO.manchesWinnersTable+" MW ON U.id = MW.idPlayer GROUP BY " +
                "U."+UserIdAttribute+" ORDER BY avg desc";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryBest);
        if(rs.next()) {
            UsersDTO user = new UsersDTO();
            user.setNickname(rs.getString(UserNicknameAttribute));
            return user;
        }else
            return null;
    }

    public UsersDTO getUserForMostLostTurn() throws SQLException{
        String queryBest = "SELECT "+UserNicknameAttribute+", COUNT(U."+UserIdAttribute+") AS count FROM " +UserTable+" U " +
                "JOIN "+MancheJoinersDAO.mancheJoinersTable+" MW ON U.id = MW.idplayer " +
                "JOIN "+ManchesDAO.ManchesTable+" MT ON MT.number = MW.number AND MT.id = MW.id " +
                "JOIN " + MovesDAO.MovesTable + " M ON M.number = MT.number AND MT.id = M.idmanche AND U.id = M.id " +
                "WHERE M." + MovesDAO.MovesOutcomeAttribute + " = 0 AND M."+ MovesDAO.MovesMoveTypeAttribute + " <> 'perde' AND M."+ MovesDAO.MovesMoveTypeAttribute + " <> 'passa' " +
                "GROUP BY U." +UserIdAttribute +" ORDER BY count desc";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryBest);
        if(rs.next()) {
            UsersDTO user = new UsersDTO();
            user.setNickname(rs.getString(UserNicknameAttribute));
            return user;
        }else
            return null;
    }

    public UsersDTO getUserForMostLosses() throws SQLException{
        String queryBest = "SELECT "+UserNicknameAttribute+", COUNT(U."+UserIdAttribute+") AS count FROM " +UserTable+" U " +
                "JOIN "+MancheJoinersDAO.mancheJoinersTable+" MW ON U.id = MW.idplayer " +
                "JOIN "+ManchesDAO.ManchesTable+" MT ON MT.number = MW.number AND MT.id = MW.id " +
                "JOIN " + MovesDAO.MovesTable + " M ON M.number = MT.number AND MT.id = M.idmanche AND U.id = M.id " +
                "WHERE M."+ MovesDAO.MovesMoveTypeAttribute + " = 'perde' " +
                "GROUP BY U." +UserIdAttribute +" ORDER BY count desc";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryBest);
        if(rs.next()) {
            UsersDTO user = new UsersDTO();
            user.setNickname(rs.getString(UserNicknameAttribute));
            return user;
        }else
            return null;
    }
}
