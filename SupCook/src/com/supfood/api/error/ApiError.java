package com.supfood.api.error;

import java.util.ArrayList;
import java.util.Arrays;

public class ApiError {

    private static ArrayList<ApiError> getErrorsList() {
        return new ArrayList<>(Arrays.asList(
                new ApiError("DB_ERROR", "Une erreur s'est produite avec la base de données"),
                new ApiError("BAD_REQUEST", "Requête incorrecte"),
                new ApiError("BAD_CREDENTIALS", "Aucun utilisateur trouvé avec cet identifiant et ce mot de passe"),
                new ApiError("BAD_REQUEST", "Requête incorrecte"),
                new ApiError("INVALID_FIELDS", "Un ou plusieurs champs sont incorrects")
        ));
    }

    private String identifier;
    private String message;

    public ApiError(String identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getMessage() {
        return message;
    }

    public static ApiError getError(String identifier) {
        ArrayList<ApiError> errors = ApiError.getErrorsList();
        for(ApiError error : errors) {
            if (error.getIdentifier().equals(identifier)) {
                return error;
            }
        }
        return new ApiError("UNKNOWN_ERROR", "Une erreur inconnue s'est produite");
    }
}
