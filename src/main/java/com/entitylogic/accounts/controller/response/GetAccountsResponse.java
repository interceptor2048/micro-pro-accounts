package com.entitylogic.accounts.controller.response;

import com.entitylogic.accounts.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetAccountsResponse {
    private List<AccountDto> accounts;
}
