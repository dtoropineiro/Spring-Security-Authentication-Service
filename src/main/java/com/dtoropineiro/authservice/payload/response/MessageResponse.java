package com.dtoropineiro.authservice.payload.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
	private String message;

}
