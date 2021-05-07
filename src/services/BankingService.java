package services;

import models.account.Account;
import models.card.Card;
import models.client.BusinessClient;
import models.client.Client;
import models.client.IndividualClient;
import services.AccountsService;

import java.io.FileNotFoundException;
import java.util.*;

public class BankingService {
    private List<Client> clients = new ArrayList<Client>();
    private final AccountsService accountsService;
    private final ReaderService reader;
    private final AuditService audit;

    public BankingService() {
        this.accountsService = AccountsService.getInstance();
        this.reader = ReaderService.getInstance();
        this.audit = AuditService.getInstance();
        this.loadClients();
    }

    private void loadClients() {
        try {
            List<List<String>> data = this.reader.read("src/data/clients.csv");
            for (List<String> row: data) {
                String accountType = row.get(0);

                if (accountType.equals("business")) {
                    clients.add(new BusinessClient(row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7)));
                } else {
                    clients.add(new IndividualClient(row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8), row.get(9)));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Client registerNewClientAndSetupAccount(String firstName, String lastName, String cnp, String country, String city, String address,
    String email, String phoneNumber) {
        audit.logAction("Action 1.1: Create individual client account");
        IndividualClient client = new IndividualClient(firstName, lastName, cnp, country, city, address, email, phoneNumber);
        clients.add(client);
        accountsService.createAccount(client);
        return client;
    }

    public Client registerNewClientAndSetupAccount(String businessName, String CUI, String country, String city, String address,
                                            String email, String phoneNumber) {
        audit.logAction("Action 1.2: Create business client account");

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
