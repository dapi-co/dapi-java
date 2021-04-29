package co.dapi.types;

public class Validators {
    private final CreateBeneficiaryValidator createBeneficiary;
    private final CreateTransferValidator createTransfer;

    public Validators(CreateBeneficiaryValidator createBeneficiary, CreateTransferValidator createTransfer) {
        this.createBeneficiary = createBeneficiary;
        this.createTransfer = createTransfer;
    }

    public CreateBeneficiaryValidator getCreateBeneficiary() {
        return createBeneficiary;
    }

    public CreateTransferValidator getCreateTransfer() {
        return createTransfer;
    }
}
