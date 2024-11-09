package com.sid.bank_account_service.web;

import com.sid.bank_account_service.entities.BankAccount;
import com.sid.bank_account_service.mappers.AccountMapper;
import com.sid.bank_account_service.repositories.BankAccountRepository;
import dto.BankAccountRequestDTO;
import dto.BankAccountResponseDTO;
import org.springframework.web.bind.annotation.*;
import service.BankAccountService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")

public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;

    private BankAccountService bankAccountService;

    private AccountMapper accountMapper;

    public AccountRestController (BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->
        new RuntimeException(String.format("Bank account with id %s not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO createBankAccount(@RequestBody BankAccountRequestDTO requestDTO) {
            return bankAccountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccount updateBankAccount(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null)
            account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!=null)
            account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null)
            account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null)
            account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteBankAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }
}
