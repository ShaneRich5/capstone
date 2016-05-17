package models.Forms;

import play.data.validation.Constraints;

/**
 * Created by uhhh on 5/16/16.
 */
public class AssignmentForm {

    @Constraints.Required
    public String name;
    @Constraints.Required
    public String description;
    @Constraints.Required
    public String language;
    @Constraints.Required
    public int amount;

    public String name0, name1, name2, name3, name4,name5,name6,name7,name8,name9;
    public String description0, description1, description2, description3, description4,description5,description6,description7,description8,description9;
    public String mark0, mark1, mark2, mark3, mark4,mark5,mark6,mark7,mark8,mark9;


}
