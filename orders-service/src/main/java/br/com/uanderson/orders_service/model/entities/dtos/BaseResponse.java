package br.com.uanderson.orders_service.model.entities.dtos;

public record BaseResponse(String[] errorMessages) {
    public boolean hasErrors(){
        return errorMessages != null && errorMessages.length > 0;
    }
}
