package com.dbs.bankingservice.controllers;



import com.dbs.bankingservice.dto.TransactionDto;
import com.dbs.bankingservice.dto.TransactionRequest;
import com.dbs.bankingservice.dto.TransactionsDto;
import com.dbs.bankingservice.dto.UserAccountDto;
import com.dbs.bankingservice.entities.Transaction;
import com.dbs.bankingservice.enums.TransactionType;
import com.dbs.bankingservice.services.UserAccountService;
import com.dbs.bankingservice.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/users")
    public ResponseEntity getAll(){
        return ResponseEntity.ok("hello");
    }


    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request,response);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<UserAccountDto>> getAllTransactions(){
        List<UserAccountDto> userAccountDtos = userAccountService.getUserAccountDetails();
        return ResponseEntity.ok(userAccountDtos);
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionsDto>getAllTransactions(@RequestParam Long accountNumber, @RequestParam int pageNumber, @RequestParam int pageSize){
        TransactionsDto userTransactions = userAccountService.getUserTransactions(accountNumber,pageNumber, pageSize);
        return ResponseEntity.ok(userTransactions);
    }

    @PostMapping("/send")
    public ResponseEntity sendMoney(@RequestBody @Valid TransactionRequest transactionRequest){
        String status = userAccountService.sendMoney(transactionRequest);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam @Positive Long amount){
        userAccountService.depositMoney(amount);
        return ResponseEntity.ok("Successfully deposited");
    }

//    return new ServiceResponse<UserDto>(authenticatedUser);
}
