package database;

import java.sql.SQLException;
import java.util.List;

public interface UsersDAO {
	String UserTable = "users";
	String UserIdAttribute = "id";
	String UserTipoAttribute = "tipo";
	String UserNameAttribute = "name";
	String UserSurnameAttribute = "surname";
	String UserNicknameAttribute = "nickname";
	String UserEmailAttribute = "email";
	String UserPasswordAttribute = "password";

	boolean addUser(UsersDTO user) throws SQLException;

	UsersDTO getUserByEmail(String email) throws SQLException;

	UsersDTO getUserByNickname(String nickname) throws SQLException;

	UsersDTO getUserById(String id) throws SQLException;

	boolean deleteUser(String id) throws SQLException;

	List<UsersDTO> getAllAdmin() throws SQLException;

	boolean updateUser(UsersDTO user) throws SQLException;

	UsersDTO getBestPlayerByManche() throws SQLException;

	UsersDTO getBestPlayerByMatch() throws SQLException;

	UsersDTO getUserForMoreManchesPlayed() throws SQLException;

	UsersDTO getUserForBestMancheAverage() throws SQLException;

	UsersDTO getUserForMostLostTurn() throws SQLException;

	UsersDTO getUserForMostLosses() throws SQLException;
}
