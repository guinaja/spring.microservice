package spring.microservice;

import org.junit.Before;
import spring.microservice.accounts.Account;
import spring.microservice.accounts.AccountRepository;
import spring.microservice.accounts.AccountsController;

import java.util.ArrayList;
import java.util.List;

public class AccountsControllerTests extends AbstractAccountControllerTests {

    protected static final Account theAccount = new Account(ACCOUNT_1,
            ACCOUNT_1_NAME);
    protected TestAccountRepository testRepo = new TestAccountRepository();

    @Before
    public void setup() {
        accountController = new AccountsController(testRepo);
    }

    protected static class TestAccountRepository implements AccountRepository {

        @Override
        public Account findByNumber(String accountNumber) {
            if (accountNumber.equals(ACCOUNT_1))
                return theAccount;
            else
                return null;
        }

        @Override
        public List<Account> findByOwnerContainingIgnoreCase(String partialName) {
            List<Account> accounts = new ArrayList<Account>();

            if (ACCOUNT_1_NAME.toLowerCase().indexOf(partialName.toLowerCase()) != -1)
                accounts.add(theAccount);

            return accounts;
        }

        @Override
        public int countAccounts() {
            return 1;
        }
    }
}
