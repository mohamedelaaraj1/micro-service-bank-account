package service;

import dto.BankAccountRequestDTO;
import dto.BankAccountResponseDTO;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
