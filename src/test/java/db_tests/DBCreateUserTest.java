package db_tests;

import example.com.db.DbClient;
import example.com.db.DbConfig;
import example.com.db.SQLQuery;
import example.com.enums.Operation;
import example.com.enums.Role;
import example.com.flows.LoginApi;
import example.com.flows.UserApi;
import example.com.models.req_models.CreateUserRequestModel;
import example.com.models.resp_models.LoginUserResponseModel;
import example.com.utills.DataGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;

@Tag("DB")
@Tag("REGRESSION")
public class DBCreateUserTest {
    @Test
    void shouldLoadAdminFromDb() {
        LoginUserResponseModel adminLogin = LoginApi.loginWithAdmin();

        CreateUserRequestModel user = DataGenerator.generate(CreateUserRequestModel.class);
        UserApi.createByAdmin(user, adminLogin.getToken(), Role.USER);

        DbConfig dbConfig = DbConfig.initConfig();

        SQLQuery sqlQuery = SQLQuery.builder()
                .operation(Operation.SELECT)
                .all()
                .from("customers")
                .whereEquals("username", user.getUsername())
                .build();

        DbClient dbClient = new DbClient(dbConfig, sqlQuery);

        List<CreateUserRequestModel> raw = dbClient.execQuery().extractListAs(CreateUserRequestModel.class);
        Assertions.assertEquals(user.getUsername(), raw.get(0).getUsername());
    }
}
