package com.sid.bank_account_service.mappers;


import com.sid.bank_account_service.entities.BankAccount;
import dto.BankAccountResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO accountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, accountResponseDTO);
        return accountResponseDTO;
    }
}
