package com.bruno.spring.Project.Spring.exceptions;

import java.util.Date;

public record ExceptionResponse(Date date, String mensagem, String detalhes) {
}
