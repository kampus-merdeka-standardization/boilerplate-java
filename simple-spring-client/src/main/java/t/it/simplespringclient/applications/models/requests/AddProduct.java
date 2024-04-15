package t.it.simplespringclient.applications.models.requests;

public record AddProduct(
        String name,
        String color,
        String capacity,
        Integer year,
        Double price
        ) {
}
