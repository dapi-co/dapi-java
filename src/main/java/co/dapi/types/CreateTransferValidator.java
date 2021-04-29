package co.dapi.types;

public class CreateTransferValidator {
    private final CreateTransferValidatorProps local;
    private final CreateTransferValidatorProps same;

    public CreateTransferValidator(CreateTransferValidatorProps local, CreateTransferValidatorProps same) {
        this.local = local;
        this.same = same;
    }

    public CreateTransferValidatorProps getLocal() {
        return local;
    }

    public CreateTransferValidatorProps getSame() {
        return same;
    }
}
