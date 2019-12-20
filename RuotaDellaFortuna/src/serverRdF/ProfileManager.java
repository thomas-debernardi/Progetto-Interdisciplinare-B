package serverRdF;



import java.util.Random;

import database.DBManager;
import database.UsersDTO;
import services.CryptPassword;
import services.EmailManager;


public class ProfileManager {
    private DBManager dbManager;
    private EmailManager emailManager;
    private static ProfileManager profileManager = null;

    private ProfileManager(DBManager dbmng, EmailManager emailManager) {
        dbManager = dbmng;
        this.emailManager = emailManager;
    }

    public static ProfileManager createProfileManager(DBManager dbmng, EmailManager emailManager) {
        if (profileManager == null) {
            profileManager = new ProfileManager(dbmng, emailManager);
            return profileManager;
        } else {
            return profileManager;
        }
    }


    public boolean changePassword(String password, String idUser){
        UsersDTO user = dbManager.getUserById(idUser);
        user.setPassword(password);
        String email = user.getEmail();
        String sub = "Password changed RDF";
        String txt = user.getName()+"Your password has been changed";
        emailManager.sendEmail(email,sub,txt);
        return dbManager.updateUser(user);
    }

    public boolean resetPassword(String email){
        UsersDTO user = dbManager.getUserByEmail(email);
        if(user != null) {
            String password = generateRandomPassword();
            String sub = "Reset password RDF";
            String txt = user.getName()+"This is your new password" + password;
            emailManager.sendEmail(email,sub,txt);
            user.setPassword(CryptPassword.crypt(password));
            dbManager.updateUser(user);
            return true;
        }else{
            return false;
        }
    }

    private static String generateRandomPassword(){
        Random rnd = new Random();
        String result = "P";
        for(int i=0; i<8; i++) {
            int chars = rnd.nextInt(3);
            switch (chars) {
                case 0:
                    result += rnd.nextInt(10);
                    break;
                case 1:
                    result += (char)(rnd.nextInt(26) + 65);
                    break;
                case 2:
                    result += (char)(rnd.nextInt(26) + 97);
                    break;
            }
        }
        return result;
    }
    
    public boolean changeName(String name, String idUser) {
        UsersDTO user = dbManager.getUserById(idUser);
        if(user != null) {
            user.setName(name);
            return dbManager.updateUser(user);
        }else
            return false;
    }

   
    public boolean changeSurname(String surname, String idUser) {
        UsersDTO user = dbManager.getUserById(idUser);
        user.setSurname(surname);
        return dbManager.updateUser(user);
    }

   
    public boolean changeNickname(String nickname, String idUser) {
        UsersDTO user = dbManager.getUserById(idUser);
        user.setNickname(nickname);
        return dbManager.updateUser(user);
    }
}
