package t.it.simplespringclient.interfaces.models.requests;

public record AddProduct(
        String name,
        String color,
        String capacity,
        Integer year,
        Double price
        ) {
}
