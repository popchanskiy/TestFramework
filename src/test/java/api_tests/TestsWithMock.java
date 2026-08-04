package api_tests;

import example.com.annotations.WithMock;
import example.com.enums.MockType;
import example.com.enums.Role;
import example.com.extensions.MockExtension;
import example.com.flows.AccountApi;
import example.com.flows.DepositApi;
import example.com.flows.LoginApi;
import example.com.flows.TransferApi;
import example.com.flows.UserApi;
import example.com.models.req_models.CreateUserRequestModel;
import example.com.models.req_models.DepositRequestModel;
import example.com.models.req_models.LoginUserRequestModel;
import example.com.models.req_models.TransferRequestModel;
import example.com.models.resp_models.CreateAccountResponseModel;
import example.com.models.resp_models.LoginUserResponseModel;
import example.com.models.resp_models.TransferResponseModel;
import example.com.utills.DataGenerator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockExtension.class)
@Tag("INTEGRATION")
public class TestsWithMock {
    @Test
    @WithMock(MockType.AF_SUCCESS)
    public void transferTestWithMock() {
        LoginUserResponseModel adminLogin = LoginApi.loginWithAdmin();

        CreateUserRequestModel user1 = DataGenerator.generate(CreateUserRequestModel.class);
        CreateUserRequestModel user2 = DataGenerator.generate(CreateUserRequestModel.class);

        UserApi.createByAdmin(user1, adminLogin.getToken(), Role.USER);
        UserApi.createByAdmin(user2, adminLogin.getToken(), Role.USER);


        LoginUserResponseModel userLogin1 = LoginApi.loginWithUser(
                new LoginUserRequestModel(user1.getUsername(), user1.getPassword())
        );

        LoginUserResponseModel userLogin2 = LoginApi.loginWithUser(
                new LoginUserRequestModel(user2.getUsername(), user2.getPassword())
        );
        CreateAccountResponseModel account1 = AccountApi.createAccountByUser(userLogin1.getToken());
        CreateAccountResponseModel account2 = AccountApi.createAccountByUser(userLogin2.getToken());

        double deposit = Math.round((Math.random() * 4999 + 1) * 100.0) / 100.0;

        DepositRequestModel depositRequest = new DepositRequestModel(account1.getAccountId(), deposit, "");

        DepositApi.depositToAccount(depositRequest, userLogin1.getToken());

        TransferRequestModel transferRequestModel = new TransferRequestModel(account1.getAccountId(), account2.getAccountId(), deposit, "transfer");

        TransferResponseModel transfer = TransferApi.transfer(transferRequestModel, userLogin1.getToken());
        System.out.println(transfer);
    }
}
