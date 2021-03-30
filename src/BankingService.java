import models.account.Account;
import models.card.Card;
import models.client.BusinessClient;
import models.client.Client;
import models.client.IndividualClient;
import services.AccountsService;

import java.util.*;

public class BankingService {
    private List<Client> clients = new ArrayList<Client>();
    private final AccountsService accountsService;

    BankingService() {
        this.accountsService = AccountsService.getInstance();
    }

    public Client registerNewClientAndSetupAccount(String firstName, String lastName, String cnp, String country, String city, String address,
    String email, String phoneNumber) {
        IndividualClient client = new IndividualClient(firstName, lastName, cnp, country, city, address, email, phoneNumber);
        clients.add(client);
        accountsService.createAccount(client);
        return client;
    }

    public Client registerNewClientAndSetupAccount(String businessName, String CUI, String country, String city, String address,
                                            String email, String phoneNumber) {
        BusinessClient client = new BusinessClient(businessName, CUI, country, city, address, email, phoneNumber);
        clients.add(client);
        accountsService.createAccount(client);
        return client;
    }

    public void addNewCard(String IBAN) throws Exception {
        Account account = this.accountsService.getAccount(IBAN);
        account.createCard();
    }

    public void deactivateCard(String IBAN, String cardNumber) throws  Exception {
        Account account = this.accountsService.getAccount(IBAN);
        Card card = account.getCard(cardNumber);

        card.deactivate();
    }

    public void createClientAccount(Client client) {
        client.createAccount();
    }

    public void createSavingsAccount(IndividualClient client) {
        client.createSavingsAccount();
    }
}
