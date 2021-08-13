package com.renmoney_ha.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowBookRequest implements Serializable {
    @NotBlank(message = "This field should not be empty")
    private List<Long> bookId;
    @NotBlank(message = "This field should not be empty")
    private Long recipientUserId;
}
