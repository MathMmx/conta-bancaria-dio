/**
 * Classe que representa uma conta bancária.
 * Contém o número da conta, agência, nome do titular e saldo.
 */
public class Account {
    private int accountNumber;
    private String agency;
    private String accountOwnerName;
    private double balance;

    // Construtor
    public Account(int accountNumber, String agency, String accountOwnerName, double balance) {
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.accountOwnerName = accountOwnerName;
        this.balance = balance;
    }

    // Métodos getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAgency() {
        return agency;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public double getBalance() {
        return balance;
    }

    // Método para atualizar o saldo
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Método para exibir um resumo da conta
    public String getAccountSummary() {
        return String.format("Titular: %s | Agência: %s | Número da Conta: %d | Saldo: R$ %.2f",
                accountOwnerName, agency, accountNumber, balance);
    }
}