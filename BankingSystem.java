
import java.util.Scanner;

//Exceptional classes entended by built-in Exception class
class VerificationException extends Exception{}
class InsufficientBalance extends Exception{}

//BankAccount class
class BankAccount{
    String Name;
    String BranchName;
    int AccountNumber;
    double Balance;
    int ATMpin;
    double LoanAmount;

    public BankAccount(){
        Name = " ";
        AccountNumber = 0;
        Balance = 0.0;
        BranchName = " ";
        ATMpin = 0;
        LoanAmount = 0.0;
    }

    public BankAccount(String Name,int AccountNumber,double Balance,String BranchName,int ATMpin){
        this.Name = Name;
        this.AccountNumber = AccountNumber;
        this.Balance = Balance;
        this.BranchName = BranchName;   
        this.ATMpin = ATMpin;
    }

    void KYCverification(Scanner sc)throws VerificationException{
        try{
        System.out.println("Enter the ATM pin for verification: ");
        int pin = sc.nextInt();
        
        if(pin==ATMpin){
            System.out.println("Verification successful!!");
        }
        else{
            throw new VerificationException();
        }
        }
        catch(Exception e){
            System.out.println("Wrong PIN!!"); 
            System.out.println("Verification failed!!");
        }
    }

    double Deposit(double amount){
        Balance += amount;
        return Balance;
    }

    double withdraw(double amount) throws InsufficientBalance{
        try{
            if(amount<=Balance){
                Balance -= amount;
            }
            else{
                throw new InsufficientBalance();
            }   
        }
        catch(InsufficientBalance e){
            System.out.println("Insufficient Balance!!");
        }
        return Balance;
    }

    double getBalance(){
        return Balance;
    }

    void getBankDetails(){
        System.out.println("Name: "+Name);
        System.out.println("Account Number: "+AccountNumber);
        System.out.println("Branch Name: "+BranchName);
        System.out.println("Balance: "+Balance);
    }

    double demandLoan(double LoanAmount,Scanner sc){
        this.LoanAmount = LoanAmount;
        
        System.out.println("Enter 1 to demand loan of 100,\n 2 to demand loan of 200,\n 3 to demand loan of 300,\n 4 to demand loan of 400,\n 5 to demand loan of 500: ");
        int m = sc.nextInt();
        System.out.println("Loan demanded: "+LoanAmount);
        switch(m){
            case 1:
            System.out.println("Loan of 100 is approved!!");
            break;
            case 2:
            System.out.println("Loan of 200 is approved!!");
            break;
            case 3:
            System.out.println("Loan of 300 is approved!!");
            break;
            case 4:
            System.out.println("Loan of 400 is approved!!");
            break;
            case 5:
            System.out.println("Loan of 500 is approved!!");
            break;
            default:
            System.out.println("Loan not approved!!");
        }
        Balance += LoanAmount;
        return Balance;
    }

}
public class BankingSystem {
    public static void main(String[] args) {   
        Scanner sc = new Scanner(System.in);

        //Entering Account Details
        System.out.println("Enter the name of the account holder: ");
        String Name = sc.nextLine();
        System.out.println("Enter the account number: ");
        int AccountNumber = sc.nextInt();
        System.out.println("Enter the branch name: ");
        String BranchName = sc.next();
        System.out.println("Enter the initial balance: ");
        double Balance = sc.nextDouble();
        System.out.println("Enter the ATM pin: ");
        int ATMpin = sc.nextInt();

        //creating instance of BankAccount
        BankAccount b1 = new BankAccount(Name,AccountNumber,Balance,BranchName,ATMpin);
        
        System.out.println("");

        //Banking System
        System.out.println("Welcome to the Banking System!!");
        //Performing operations
        while(1>0){
        int n;
        System.out.println("");
        System.out.println("Choose the operation to be performed: ");
        System.out.print("Enter 1 to Deposit,\n 2 to Withdraw,\n 3 to Check Balance,\n 4 to demand Loan,\n 5 to get Bank Details,\n 6 to Exit: ");
        n = sc.nextInt();
        switch(n){
            case 1:
            System.out.print("Enter the amount to be deposited: ");
            double amount = sc.nextDouble();
            try{
                b1.KYCverification(sc);
            }
            catch(Exception e){
                System.out.println("Wrong PIN!!"); 
                System.out.println("Verification failed!!");
            }
            System.out.println("Your Balance : "+b1.Deposit(amount));
            break;

            case 2:
            try{
            System.out.print("Enter the amount to be withdrawn: ");
            double amount1 = sc.nextDouble();
            try{
                b1.KYCverification(sc);
            }
            catch(Exception e){
                System.out.println("Wrong PIN!!"); 
                System.out.println("Verification failed!!");
            }
            System.out.println("Your Balance : "+b1.withdraw(amount1));
            }
            catch(InsufficientBalance e){
                System.out.println("Insufficient Balance!!");
            }
            break;

            case 3:
            System.out.println("Checking the balance: ");
            try{
                b1.KYCverification(sc);
            }
            catch(Exception e){
                System.out.println("Wrong PIN!!"); 
                System.out.println("Verification failed!!");
            }
            System.out.print("Your Balance: "+b1.getBalance());
            break;

            case 4:
            System.out.println("Loan Amounts available: 100, 200, 300, 400, 500");
            System.out.print("Enter the amount of loan you want to demand: ");
            double LoanAmount = sc.nextDouble();
            try{
                b1.KYCverification(sc);
            }
            catch(Exception e){
                System.out.println("Wrong PIN!!"); 
                System.out.println("Verification failed!!");
            }
            System.out.println("Your New Balance: "+b1.demandLoan(LoanAmount,sc));
            
            case 5:
            System.out.println("Getting Bank Details: ");
            b1.getBankDetails();
            try{
                b1.KYCverification(sc);
            }
            catch(Exception e){
                System.out.println("Wrong PIN!!"); 
                System.out.println("Verification failed!!");
            }
            break;

            case 6:
            System.out.println("Exiting...");
            System.out.println("Thank you for using our services!!");
            break;

            default:
                System.out.println("Invalid choice");
            
        }
        if(n==4){
            break;
        }
        }
        sc.close();
    }
    
}
