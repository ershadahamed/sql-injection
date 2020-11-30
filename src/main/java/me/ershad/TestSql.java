package me.ershad;

import java.io.IOException;

public class TestSql {

    public int run(String someTxt) throws IOException {
        String sql = "SELECT * FROM names WHERE pplname = ?";
        String[] param = {someTxt};
        return new DbCon().runQuery(sql, param);
    }

}
