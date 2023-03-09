package co.dapi.types;

import java.util.Optional;

public class CategorizedTransaction {
    private final float amount;
    private final String date;
    private final TransactionType type;
    private final String description;
    private final String details;
    private final Currency currency;
    private final Float beforeAmount;
    private final Float afterAmount;
    private final String reference;
    private final String category;

    public CategorizedTransaction(float amount, String date, TransactionType type, String description, String details, Currency currency, Float beforeAmount, Float afterAmount, String reference, String category) {
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.description = description;
        this.details = details;
        this.currency = currency;
        this.beforeAmount = beforeAmount;
        this.afterAmount = afterAmount;
        this.reference = reference;
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Optional<Float> getBeforeAmount() {
        return Optional.ofNullable(beforeAmount);
    }

    public Optional<Float> getAfterAmount() {
        return Optional.ofNullable(afterAmount);
    }

    public Optional<String> getReference() {
        return Optional.ofNullable(reference);
    }

    public Optional<String> getCategory() {
        return Optional.ofNullable(category);
    }

    public enum TransactionType {
        credit,
        debit
    }
}
