package br.com.uanderson.inventory_service.model.entities.dtos;

public record BaseResponse(String[] errorMessages) {
    public boolean hasErrors(){
        return errorMessages != null && errorMessages.length > 0;
    }
}
