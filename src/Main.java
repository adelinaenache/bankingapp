import models.card.Card;
import models.client.BusinessClient;
import models.client.IndividualClient;
import services.AtmService;
import services.TransactionService;

public class Main {
    public static void main(String [] args) {
        BankingService service = new BankingService();
        AtmService atmService = new AtmService();
        TransactionService transactionService = TransactionService.getInstance();
        // Functionality 1: Register clients with accounts

        // create a business account
        BusinessClient client1 = (BusinessClient) service.registerNewClientAndSetupAccount("Company.SRL", "J/12313021", "Romania", "Bucharest", "Str. Cool nr 47", "business@mail.com", "+40770415865");
        Card client1Card = client1.getFirstAccount().getFirstCard();

        // create a individual account
        IndividualClient client2 = (IndividualClient) service.registerNewClientAndSetupAccount("Adelina", "Enache", "299060399012102", "Romania", "Bucharest", "Calea Giulesti 43", "enache.adelina99@gmail.com", "+40770416856");


        System.out.println(client1);
        System.out.println(client2);

        // Functionality 2: Create multiple accounts per client
        service.createClientAccount(client2);
        service.createSavingsAccount(client2);

        // Functionality 3: Create atms.
        atmService.createAtm("ING1", "Str. Zorilor 43", "code1");
        atmService.createAtm("ING2", "Str. Cuiului 12", "code2");

        // Functionality 4: deposit money as a bank manager.
        try {
            atmService.deposit("ING1", "code1", 250000);
            System.out.println(atmService.getAtm("ING1"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Functionality 5: Deposit money in accounts (without atm)
        try {
            atmService.depositInAccount("ING1", client1Card.getNumber(), client1Card.getPin(), 400);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Functionality 6: Using a card, extract money from atm.
        try {
            atmService.withdraw("ING1", client1Card.getNumber(), client1Card.getPin(), 200);

            System.out.println(client1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Functionality 7: Make a transaction between 2 accounts.
        try {
            transactionService.makeTransaction(client1.getFirstAccount().getIBAN(), client2.getFirstAccount().getIBAN(), 100);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Functionality 8: List all transactions for account, in descending order

        try {
            System.out.println(transactionService.getClientTransactions(client1.getFirstAccount().getIBAN()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Functionality 9: Add a new card.
        try {
            service.addNewCard(client1.getFirstAccount().getIBAN());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Functionality 10: Deactivate card
        try {
            service.deactivateCard(client1.getFirstAccount().getIBAN(), client1Card.getNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
