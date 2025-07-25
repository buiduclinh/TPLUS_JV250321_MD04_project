package ra.MD4Project.presetation.login;

import ra.MD4Project.utils.DBHelper;

public class LoginImpl implements ILogin {
    @Override
    public Admin admin(String username, String password) {
        String sql = "{CALL login(?,?)}";
        return DBHelper.executeQuery(sql, rs -> {
            if (rs.next()) {
                Admin login = new Admin();
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                return login;
            }
            return null;
        }, username, password);
    }

    @Override
    public Admin getUser(String username) {
        String sql = "{CALL get_admin_user(?)}";
        return DBHelper.executeQuery(sql, rs -> {
            if (rs.next()) {
                Admin login = new Admin();
                login.setUsername(rs.getString("username"));
                return login;
            }
            return null;
        }, username);
    }
}
