package dto;

/**
 *
 * @author purvi
 */
public class InspectionItemDTO 
{
    private int inspectionId;
    private String itemName;
    private String itemStatus;
    private String comment;
    private String ImagePath;

    public InspectionItemDTO() {}

    public InspectionItemDTO(int inspectionId, String itemName, String itemStatus, String comment, String ImagePath) {
        this.inspectionId = inspectionId;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.comment = comment;
        this.ImagePath = ImagePath;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
    
}
