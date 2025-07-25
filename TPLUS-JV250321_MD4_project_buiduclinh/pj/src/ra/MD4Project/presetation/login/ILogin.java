package ra.MD4Project.presetation.login;

public interface ILogin {
    Admin admin(String username, String password);
    Admin getUser(String username);
}
