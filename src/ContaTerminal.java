import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContaTerminal {

    static List<Account> accounts = new ArrayList<>(); // Lista para armazenar várias contas

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação automática da primeira conta antes do menu
        System.out.println("Bem-vindo! Vamos começar criando sua conta.");
        createAccount(scanner);

        // Exibir o menu após a criação da primeira conta
        while (true) {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Buscar conta por número");
            System.out.println("3. Exibir todas as contas");
            System.out.println("4. Depositar");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    searchAccount(scanner);
                    break;
                case 3:
                    displayAllAccounts();
                    break;
                case 4:
                    depositAccount(scanner);
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

/**
 * Cria uma nova conta bancária com base na entrada do usuário.
 * Valida o número da conta, formato da agência, nome do titular e saldo inicial.
 *
 * @param scanner O objeto Scanner usado para ler a entrada do usuário.
 */ 
private static void createAccount(Scanner scanner) {
        System.out.println("\n---- Criação de Nova Conta ----");
    
        // Validando número da conta
        int accountNumber = 0;
        while (true) {
            System.out.print("Digite o número da conta (inteiro de 1000 a 9999): ");
            if (scanner.hasNextInt()) {
                accountNumber = scanner.nextInt();
                if (accountNumber >= 1000 && accountNumber <= 9999) {
                    break;
                } else {
                    System.out.println("Número da conta inválido! Deve estar entre 1000 e 9999.");
                }
            } else {
                System.out.println("Número da conta inválido! Deve ser um número inteiro.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
    
        // Validando agência
        String agency;
        while (true) {
            System.out.print("Digite o número da agência (formato XXX-X): ");
            agency = scanner.next();
            if (agency.matches("\\d{3}-\\d")) {
                break;
            } else {
                System.out.println("Agência inválida! Deve estar no formato 'XXX-X'.");
            }
        }
    
        // Verificando se a conta já existe na agência
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getAgency().equals(agency)) {
                System.out.println("Uma conta com este número já existe na agência especificada. Por favor, tente novamente.");
                return; // Retorna para que o usuário possa tentar criar a conta novamente
            }
        }
    
        // Validando nome do cliente
        String accountOwnerName;
        while (true) {
            System.out.print("Digite o nome do titular da conta (texto, deve conter sobrenome): ");
            scanner.nextLine(); // Consumir a quebra de linha
            accountOwnerName = scanner.nextLine();
            if (accountOwnerName.trim().isEmpty() || accountOwnerName.split(" ").length < 2) {
                System.out.println("Nome do titular inválido! Deve conter pelo menos um sobrenome.");
            } else {
                break;
            }
        }
    
        // Validando saldo
        double balance = 0.0;
        while (true) {
            System.out.print("Digite o saldo inicial (deve ser pelo menos R$ 100,00): ");
            if (scanner.hasNextDouble()) {
                balance = scanner.nextDouble();
                if (balance >= 100) {
                    break;
                } else {
                    System.out.println("Saldo inválido! O saldo deve ser de pelo menos R$ 100,00.");
                }
            } else {
                System.out.println("Saldo inválido! Deve ser um número decimal.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
    
        // Criando e armazenando a conta
        Account newAccount = new Account(accountNumber, agency, accountOwnerName, balance);
        accounts.add(newAccount);
    
        // Exibindo detalhes da conta criada
        System.out.println("\nConta criada com sucesso!");
        System.out.println("Detalhes da Conta:");
        System.out.println("Titular: " + accountOwnerName);
        System.out.println("Agência: " + agency);
        System.out.println("Número da Conta: " + accountNumber);
        System.out.printf("Saldo: R$ %.2f%n", balance);
    }
/**
 * Busca uma conta bancária pelo número da conta.
 * Exibe os detalhes da conta se encontrada.
 *
 * @param scanner O objeto Scanner usado para ler a entrada do usuário.
 */
    private static void searchAccount(Scanner scanner) {
        System.out.print("\nDigite o número da conta que deseja buscar: ");
        int accountNumber = scanner.nextInt();

        Account foundAccount = null;
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                foundAccount = account;
                break;
            }
        }

        if (foundAccount != null) {
            System.out.println("\nConta encontrada:");
            System.out.println(foundAccount.getAccountSummary());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    // Método para exibir todas as contas
    private static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\nNenhuma conta cadastrada.");
        } else {
            System.out.println("\n---- Lista de Contas ----");
            for (Account account : accounts) {
                System.out.println(account.getAccountSummary());
            }
        }
    }
/**
 * Realiza um depósito em uma conta bancária após validar o número da conta e a agência.
 * Exibe uma mensagem de confirmação após um depósito bem-sucedido.
 *
 * @param scanner O objeto Scanner usado para ler a entrada do usuário.
 */
    private static void depositAccount(Scanner scanner) {
        System.out.print("\nDigite o número da conta que deseja depositar: ");
        int accountNumber = scanner.nextInt();
    
        System.out.print("Digite o número da agência correspondente (formato XXX-X): ");
        String agency = scanner.next();
    
        // Verifica se a conta existe e se a agência é válida
        Account accountToDeposit = null;
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getAgency().equals(agency)) {
                accountToDeposit = account;
                break;
            }
        }
    
        if (accountToDeposit == null) {
            System.out.println("Conta ou agência não encontrados.");
            return;
        }
    
        // Solicita o valor a ser depositado
        double depositAmount = 0.0;
        while (true) {
            System.out.print("Digite o valor do depósito: ");
            if (scanner.hasNextDouble()) {
                depositAmount = scanner.nextDouble();
                if (depositAmount > 0) {
                    break;
                } else {
                    System.out.println("O valor do depósito deve ser maior que R$ 0,00.");
                }
            } else {
                System.out.println("Valor inválido! Deve ser um número decimal.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
    
        // Atualiza o saldo da conta
        accountToDeposit.setBalance(accountToDeposit.getBalance() + depositAmount);
    
        // Exibe a mensagem de sucesso com o nome do titular
        System.out.printf("Depósito de R$ %.2f realizado com sucesso na conta %d (Titular: %s).%n",
                depositAmount, accountToDeposit.getAccountNumber(), accountToDeposit.getAccountOwnerName());
    }
    
    
}
